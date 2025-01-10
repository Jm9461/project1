package com.mohamadamin.persianmaterialdatetimepicker.time;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.view.accessibility.AccessibilityRecord;
import android.widget.FrameLayout;
import com.mohamadamin.persianmaterialdatetimepicker.HapticFeedbackController;
import com.mohamadamin.persianmaterialdatetimepicker.R.color;
import java.util.Calendar;
import java.util.List;

public class RadialPickerLayout
  extends FrameLayout
  implements View.OnTouchListener
{
  private final int TAP_TIMEOUT;
  private final int TOUCH_SLOP;
  private AccessibilityManager mAccessibilityManager;
  private AmPmCirclesView mAmPmCirclesView;
  private CircleView mCircleView;
  private int mCurrentHoursOfDay;
  private int mCurrentItemShowing;
  private int mCurrentMinutes;
  private boolean mDoingMove;
  private boolean mDoingTouch;
  private int mDownDegrees;
  private float mDownX;
  private float mDownY;
  private android.view.View mGrayBox;
  private Handler mHandler = new Handler();
  private HapticFeedbackController mHapticFeedbackController;
  private boolean mHideAmPm;
  private RadialSelectorView mHourRadialSelectorView;
  private RadialTextsView mHourRadialTextsView;
  private boolean mInputEnabled;
  private boolean mIs24HourMode;
  private int mIsTouchingAmOrPm = -1;
  private int mLastValueSelected;
  private c mListener;
  private RadialSelectorView mMinuteRadialSelectorView;
  private RadialTextsView mMinuteRadialTextsView;
  private int[] mSnapPrefer30sMap;
  private boolean mTimeInitialized;
  private AnimatorSet mTransition;
  
  public RadialPickerLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setOnTouchListener(this);
    TOUCH_SLOP = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
    mDoingMove = false;
    mCircleView = new CircleView(paramContext);
    addView(mCircleView);
    mAmPmCirclesView = new AmPmCirclesView(paramContext);
    addView(mAmPmCirclesView);
    mHourRadialSelectorView = new RadialSelectorView(paramContext);
    addView(mHourRadialSelectorView);
    mMinuteRadialSelectorView = new RadialSelectorView(paramContext);
    addView(mMinuteRadialSelectorView);
    mHourRadialTextsView = new RadialTextsView(paramContext);
    addView(mHourRadialTextsView);
    mMinuteRadialTextsView = new RadialTextsView(paramContext);
    addView(mMinuteRadialTextsView);
    preparePrefer30sMap();
    mLastValueSelected = -1;
    mInputEnabled = true;
    mGrayBox = new android.view.View(paramContext);
    mGrayBox.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    mGrayBox.setBackgroundColor(getResources().getColor(R.color.mdtp_transparent_black));
    mGrayBox.setVisibility(4);
    addView(mGrayBox);
    mAccessibilityManager = ((AccessibilityManager)paramContext.getSystemService("accessibility"));
    mTimeInitialized = false;
  }
  
  private int getCurrentlyShowingValue()
  {
    int i = getCurrentItemShowing();
    if (i == 0) {
      return mCurrentHoursOfDay;
    }
    if (i == 1) {
      return mCurrentMinutes;
    }
    return -1;
  }
  
  private int getDegreesFromCoords(float paramFloat1, float paramFloat2, boolean paramBoolean, Boolean[] paramArrayOfBoolean)
  {
    int i = getCurrentItemShowing();
    if (i == 0) {
      return mHourRadialSelectorView.getDegreesFromCoords(paramFloat1, paramFloat2, paramBoolean, paramArrayOfBoolean);
    }
    if (i == 1) {
      return mMinuteRadialSelectorView.getDegreesFromCoords(paramFloat1, paramFloat2, paramBoolean, paramArrayOfBoolean);
    }
    return -1;
  }
  
  private boolean isHourInnerCircle(int paramInt)
  {
    return (mIs24HourMode) && (paramInt <= 12) && (paramInt != 0);
  }
  
  private void preparePrefer30sMap()
  {
    mSnapPrefer30sMap = new int['?'];
    int i = 0;
    int n = 1;
    int j = 8;
    int k = 0;
    while (k < 361)
    {
      mSnapPrefer30sMap[k] = i;
      int m;
      if (n == j)
      {
        n = i + 6;
        if (n == 360) {
          i = 7;
        } else if (n % 30 == 0) {
          i = 14;
        } else {
          i = 4;
        }
        j = 1;
        m = i;
      }
      else
      {
        n += 1;
        m = j;
        j = n;
        n = i;
      }
      k += 1;
      i = n;
      n = j;
      j = m;
    }
  }
  
  private int reselectSelector(int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (paramInt == -1) {
      return -1;
    }
    int k = getCurrentItemShowing();
    if ((!paramBoolean2) && (k == 1)) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      i = snapPrefer30s(paramInt);
    } else {
      i = snapOnly30s(paramInt, 0);
    }
    RadialSelectorView localRadialSelectorView;
    if (k == 0)
    {
      localRadialSelectorView = mHourRadialSelectorView;
      j = 30;
    }
    else
    {
      localRadialSelectorView = mMinuteRadialSelectorView;
      j = 6;
    }
    localRadialSelectorView.setSelection(i, paramBoolean1, paramBoolean3);
    localRadialSelectorView.invalidate();
    if (k == 0)
    {
      if (mIs24HourMode)
      {
        if ((i == 0) && (paramBoolean1))
        {
          paramInt = 360;
        }
        else
        {
          paramInt = i;
          if (i == 360)
          {
            paramInt = i;
            if (!paramBoolean1) {
              paramInt = 0;
            }
          }
        }
      }
      else
      {
        paramInt = i;
        if (i == 0) {
          paramInt = 360;
        }
      }
    }
    else
    {
      paramInt = i;
      if (i == 360)
      {
        paramInt = i;
        if (k == 1) {
          paramInt = 0;
        }
      }
    }
    int j = paramInt / j;
    int i = j;
    if (k == 0)
    {
      i = j;
      if (mIs24HourMode)
      {
        i = j;
        if (!paramBoolean1)
        {
          i = j;
          if (paramInt != 0) {
            i = j + 12;
          }
        }
      }
    }
    if (getCurrentItemShowing() == 0)
    {
      mHourRadialTextsView.setSelection(i);
      mHourRadialTextsView.invalidate();
      return i;
    }
    if (getCurrentItemShowing() == 1)
    {
      mMinuteRadialTextsView.setSelection(i);
      mMinuteRadialTextsView.invalidate();
    }
    return i;
  }
  
  private void setItem(int paramInt1, int paramInt2)
  {
    if (paramInt1 == 0)
    {
      setValueForItem(0, paramInt2);
      mHourRadialSelectorView.setSelection(paramInt2 % 12 * 30, isHourInnerCircle(paramInt2), false);
      mHourRadialSelectorView.invalidate();
      mHourRadialTextsView.setSelection(paramInt2);
      mHourRadialTextsView.invalidate();
      return;
    }
    if (paramInt1 == 1)
    {
      setValueForItem(1, paramInt2);
      mMinuteRadialSelectorView.setSelection(paramInt2 * 6, false, false);
      mMinuteRadialSelectorView.invalidate();
      mMinuteRadialTextsView.setSelection(paramInt2);
      mHourRadialTextsView.invalidate();
    }
  }
  
  private void setValueForItem(int paramInt1, int paramInt2)
  {
    if (paramInt1 == 0)
    {
      mCurrentHoursOfDay = paramInt2;
      return;
    }
    if (paramInt1 == 1)
    {
      mCurrentMinutes = paramInt2;
      return;
    }
    if (paramInt1 == 2)
    {
      if (paramInt2 == 0)
      {
        mCurrentHoursOfDay %= 12;
        return;
      }
      if (paramInt2 == 1) {
        mCurrentHoursOfDay = (mCurrentHoursOfDay % 12 + 12);
      }
    }
  }
  
  private static int snapOnly30s(int paramInt1, int paramInt2)
  {
    int i = paramInt1 / 30 * 30;
    int j = i + 30;
    if (paramInt2 == 1) {
      return j;
    }
    if (paramInt2 == -1)
    {
      paramInt2 = i;
      if (paramInt1 == i) {
        paramInt2 = i - 30;
      }
      return paramInt2;
    }
    if (paramInt1 - i < j - paramInt1) {
      return i;
    }
    return j;
  }
  
  private int snapPrefer30s(int paramInt)
  {
    int[] arrayOfInt = mSnapPrefer30sMap;
    if (arrayOfInt == null) {
      return -1;
    }
    return arrayOfInt[paramInt];
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if (paramAccessibilityEvent.getEventType() == 32)
    {
      paramAccessibilityEvent.getText().clear();
      Object localObject = Calendar.getInstance();
      ((Calendar)localObject).set(10, getHours());
      ((Calendar)localObject).set(12, getMinutes());
      long l = ((Calendar)localObject).getTimeInMillis();
      int i = 1;
      if (mIs24HourMode) {
        i = 0x1 | 0x80;
      }
      localObject = com.mohamadamin.persianmaterialdatetimepicker.views.View.format(DateUtils.formatDateTime(getContext(), l, i));
      paramAccessibilityEvent.getText().add(localObject);
      return true;
    }
    return super.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent);
  }
  
  public int getCurrentItemShowing()
  {
    int i = mCurrentItemShowing;
    if ((i != 0) && (i != 1))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Current item showing was unfortunately set to ");
      localStringBuilder.append(mCurrentItemShowing);
      Log.e("RadialPickerLayout", localStringBuilder.toString());
      return -1;
    }
    return mCurrentItemShowing;
  }
  
  public int getHours()
  {
    return mCurrentHoursOfDay;
  }
  
  public int getIsCurrentlyAmOrPm()
  {
    int i = mCurrentHoursOfDay;
    if (i < 12) {
      return 0;
    }
    if (i < 24) {
      return 1;
    }
    return -1;
  }
  
  public int getMinutes()
  {
    return mCurrentMinutes;
  }
  
  public void initialize(Context paramContext, HapticFeedbackController paramHapticFeedbackController, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (mTimeInitialized)
    {
      Log.e("RadialPickerLayout", "Time has already been initialized.");
      return;
    }
    mHapticFeedbackController = paramHapticFeedbackController;
    mIs24HourMode = paramBoolean;
    boolean bool;
    if ((!mAccessibilityManager.isTouchExplorationEnabled()) && (!mIs24HourMode)) {
      bool = false;
    } else {
      bool = true;
    }
    mHideAmPm = bool;
    mCircleView.initialize(paramContext, mHideAmPm);
    mCircleView.invalidate();
    if (!mHideAmPm)
    {
      paramHapticFeedbackController = mAmPmCirclesView;
      if (paramInt1 < 12) {
        i = 0;
      } else {
        i = 1;
      }
      paramHapticFeedbackController.initialize(paramContext, i);
      mAmPmCirclesView.invalidate();
    }
    Resources localResources = paramContext.getResources();
    Object localObject = new int[12];
    Object tmp138_136 = localObject;
    tmp138_136[0] = 12;
    Object tmp143_138 = tmp138_136;
    tmp143_138[1] = 1;
    Object tmp147_143 = tmp143_138;
    tmp147_143[2] = 2;
    Object tmp151_147 = tmp147_143;
    tmp151_147[3] = 3;
    Object tmp155_151 = tmp151_147;
    tmp155_151[4] = 4;
    Object tmp159_155 = tmp155_151;
    tmp159_155[5] = 5;
    Object tmp163_159 = tmp159_155;
    tmp163_159[6] = 6;
    Object tmp169_163 = tmp163_159;
    tmp169_163[7] = 7;
    Object tmp175_169 = tmp169_163;
    tmp175_169[8] = 8;
    Object tmp181_175 = tmp175_169;
    tmp181_175[9] = 9;
    Object tmp187_181 = tmp181_175;
    tmp187_181[10] = 10;
    Object tmp193_187 = tmp187_181;
    tmp193_187[11] = 11;
    tmp193_187;
    String[] arrayOfString3 = new String[12];
    String[] arrayOfString1 = new String[12];
    String[] arrayOfString2 = new String[12];
    int i = 0;
    while (i < 12)
    {
      paramHapticFeedbackController = new Object[1];
      if (paramBoolean)
      {
        paramHapticFeedbackController[0] = Integer.valueOf(new int[] { 0, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23 }[i]);
        paramHapticFeedbackController = String.format("%02d", paramHapticFeedbackController);
      }
      else
      {
        paramHapticFeedbackController[0] = Integer.valueOf(localObject[i]);
        paramHapticFeedbackController = String.format("%d", paramHapticFeedbackController);
      }
      arrayOfString3[i] = com.mohamadamin.persianmaterialdatetimepicker.views.View.format(paramHapticFeedbackController);
      arrayOfString1[i] = com.mohamadamin.persianmaterialdatetimepicker.views.View.format(String.format("%d", new Object[] { Integer.valueOf(localObject[i]) }));
      arrayOfString2[i] = com.mohamadamin.persianmaterialdatetimepicker.views.View.format(String.format("%02d", new Object[] { Integer.valueOf(new int[] { 0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55 }[i]) }));
      i += 1;
    }
    localObject = mHourRadialTextsView;
    if (paramBoolean) {
      paramHapticFeedbackController = arrayOfString1;
    } else {
      paramHapticFeedbackController = null;
    }
    ((RadialTextsView)localObject).initialize(localResources, arrayOfString3, paramHapticFeedbackController, mHideAmPm, true);
    paramHapticFeedbackController = mHourRadialTextsView;
    if (paramBoolean) {
      i = paramInt1;
    } else {
      i = paramInt1 % 12;
    }
    paramHapticFeedbackController.setSelection(i);
    mHourRadialTextsView.invalidate();
    mMinuteRadialTextsView.initialize(localResources, arrayOfString2, null, mHideAmPm, false);
    mMinuteRadialTextsView.setSelection(paramInt2);
    mMinuteRadialTextsView.invalidate();
    setValueForItem(0, paramInt1);
    setValueForItem(1, paramInt2);
    mHourRadialSelectorView.initialize(paramContext, mHideAmPm, paramBoolean, true, paramInt1 % 12 * 30, isHourInnerCircle(paramInt1));
    mMinuteRadialSelectorView.initialize(paramContext, mHideAmPm, false, false, paramInt2 * 6, false);
    mTimeInitialized = true;
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramAccessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
      paramAccessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
      return;
    }
    paramAccessibilityNodeInfo.addAction(4096);
    paramAccessibilityNodeInfo.addAction(8192);
  }
  
  public boolean onTouch(final android.view.View paramView, MotionEvent paramMotionEvent)
  {
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    paramView = new Boolean[1];
    paramView[0] = Boolean.valueOf(false);
    int i = paramMotionEvent.getAction();
    if (i != 0)
    {
      if (i != 1)
      {
        if (i != 2) {
          return false;
        }
        if (!mInputEnabled)
        {
          Log.e("RadialPickerLayout", "Input was disabled, but received ACTION_MOVE.");
          return true;
        }
        float f3 = Math.abs(f2 - mDownY);
        float f4 = Math.abs(f1 - mDownX);
        if (!mDoingMove)
        {
          i = TOUCH_SLOP;
          if ((f4 <= i) && (f3 <= i)) {
            return false;
          }
        }
        i = mIsTouchingAmOrPm;
        if ((i != 0) && (i != 1))
        {
          if (mDownDegrees == -1) {
            return false;
          }
          mDoingMove = true;
          mHandler.removeCallbacksAndMessages(null);
          i = getDegreesFromCoords(f1, f2, true, paramView);
          if (i != -1)
          {
            i = reselectSelector(i, paramView[0].booleanValue(), false, true);
            if (i != mLastValueSelected)
            {
              mHapticFeedbackController.tryVibrate();
              mLastValueSelected = i;
              mListener.onValueSelected(getCurrentItemShowing(), i, false);
              return true;
            }
          }
        }
        else
        {
          mHandler.removeCallbacksAndMessages(null);
          if (mAmPmCirclesView.getIsTouchingAmOrPm(f1, f2) == mIsTouchingAmOrPm) {
            break label770;
          }
          mAmPmCirclesView.setAmOrPmPressed(-1);
          mAmPmCirclesView.invalidate();
          mIsTouchingAmOrPm = -1;
          return false;
        }
      }
      else
      {
        if (!mInputEnabled)
        {
          Log.d("RadialPickerLayout", "Input was disabled, but received ACTION_UP.");
          mListener.onValueSelected(3, 1, false);
          return true;
        }
        mHandler.removeCallbacksAndMessages(null);
        mDoingTouch = false;
        i = mIsTouchingAmOrPm;
        if ((i != 0) && (i != 1))
        {
          if (mDownDegrees != -1)
          {
            i = getDegreesFromCoords(f1, f2, mDoingMove, paramView);
            if (i != -1)
            {
              int k = reselectSelector(i, paramView[0].booleanValue(), mDoingMove ^ true, false);
              int j = k;
              i = j;
              if (getCurrentItemShowing() == 0)
              {
                i = j;
                if (!mIs24HourMode)
                {
                  int m = getIsCurrentlyAmOrPm();
                  if ((m == 0) && (k == 12))
                  {
                    i = 0;
                  }
                  else
                  {
                    i = j;
                    if (m == 1)
                    {
                      i = j;
                      if (k != 12) {
                        i = k + 12;
                      }
                    }
                  }
                }
              }
              setValueForItem(getCurrentItemShowing(), i);
              mListener.onValueSelected(getCurrentItemShowing(), i, true);
            }
          }
          mDoingMove = false;
          return true;
        }
        i = mAmPmCirclesView.getIsTouchingAmOrPm(f1, f2);
        mAmPmCirclesView.setAmOrPmPressed(-1);
        mAmPmCirclesView.invalidate();
        if (i == mIsTouchingAmOrPm)
        {
          mAmPmCirclesView.setAmOrPm(i);
          if (getIsCurrentlyAmOrPm() != i)
          {
            mListener.onValueSelected(2, mIsTouchingAmOrPm, false);
            setValueForItem(2, i);
          }
        }
        mIsTouchingAmOrPm = -1;
        return false;
      }
    }
    else
    {
      if (!mInputEnabled) {
        return true;
      }
      mDownX = f1;
      mDownY = f2;
      mLastValueSelected = -1;
      mDoingMove = false;
      mDoingTouch = true;
      if (!mHideAmPm) {
        mIsTouchingAmOrPm = mAmPmCirclesView.getIsTouchingAmOrPm(f1, f2);
      } else {
        mIsTouchingAmOrPm = -1;
      }
      i = mIsTouchingAmOrPm;
      if ((i != 0) && (i != 1))
      {
        mDownDegrees = getDegreesFromCoords(f1, f2, mAccessibilityManager.isTouchExplorationEnabled(), paramView);
        if (mDownDegrees == -1) {
          break label772;
        }
        mHapticFeedbackController.tryVibrate();
        mHandler.postDelayed(new b(paramView), TAP_TIMEOUT);
        return true;
      }
      mHapticFeedbackController.tryVibrate();
      mDownDegrees = -1;
      mHandler.postDelayed(new a(), TAP_TIMEOUT);
    }
    return true;
    label770:
    return false;
    label772:
    return true;
  }
  
  public boolean performAccessibilityAction(int paramInt, Bundle paramBundle)
  {
    if (super.performAccessibilityAction(paramInt, paramBundle)) {
      return true;
    }
    int i = 0;
    if (paramInt == 4096) {
      i = 1;
    } else if (paramInt == 8192) {
      i = -1;
    }
    if (i != 0)
    {
      paramInt = getCurrentlyShowingValue();
      int j = paramInt;
      int m = 0;
      int n = getCurrentItemShowing();
      if (n == 0)
      {
        j = 30;
        k = paramInt % 12;
        paramInt = j;
      }
      else
      {
        k = j;
        paramInt = m;
        if (n == 1)
        {
          paramInt = 6;
          k = j;
        }
      }
      int k = snapOnly30s(k * paramInt, i) / paramInt;
      i = 0;
      if (n == 0)
      {
        if (mIs24HourMode)
        {
          paramInt = 23;
        }
        else
        {
          paramInt = 12;
          i = 1;
        }
      }
      else {
        paramInt = 55;
      }
      if (k > paramInt)
      {
        j = i;
      }
      else
      {
        j = k;
        if (k < i) {
          j = paramInt;
        }
      }
      setItem(n, j);
      mListener.onValueSelected(n, j, false);
      return true;
    }
    return false;
  }
  
  public void setAmOrPm(int paramInt)
  {
    mAmPmCirclesView.setAmOrPm(paramInt);
    mAmPmCirclesView.invalidate();
    setValueForItem(2, paramInt);
  }
  
  public void setCurrentItemShowing(int paramInt, boolean paramBoolean)
  {
    Object localObject;
    if ((paramInt != 0) && (paramInt != 1))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("TimePicker does not support view at index ");
      ((StringBuilder)localObject).append(paramInt);
      Log.e("RadialPickerLayout", ((StringBuilder)localObject).toString());
      return;
    }
    int i = getCurrentItemShowing();
    mCurrentItemShowing = paramInt;
    int j = 0;
    if ((paramBoolean) && (paramInt != i))
    {
      localObject = new ObjectAnimator[4];
      if (paramInt == 1)
      {
        localObject[0] = mHourRadialTextsView.getDisappearAnimator();
        localObject[1] = mHourRadialSelectorView.getDisappearAnimator();
        localObject[2] = mMinuteRadialTextsView.getReappearAnimator();
        localObject[3] = mMinuteRadialSelectorView.getReappearAnimator();
      }
      else if (paramInt == 0)
      {
        localObject[0] = mHourRadialTextsView.getReappearAnimator();
        localObject[1] = mHourRadialSelectorView.getReappearAnimator();
        localObject[2] = mMinuteRadialTextsView.getDisappearAnimator();
        localObject[3] = mMinuteRadialSelectorView.getDisappearAnimator();
      }
      AnimatorSet localAnimatorSet = mTransition;
      if ((localAnimatorSet != null) && (localAnimatorSet.isRunning())) {
        mTransition.end();
      }
      mTransition = new AnimatorSet();
      mTransition.playTogether((Animator[])localObject);
      mTransition.start();
      return;
    }
    if (paramInt == 0) {
      i = 255;
    } else {
      i = 0;
    }
    if (paramInt == 1) {
      j = 255;
    }
    mHourRadialTextsView.setAlpha(i);
    mHourRadialSelectorView.setAlpha(i);
    mMinuteRadialTextsView.setAlpha(j);
    mMinuteRadialSelectorView.setAlpha(j);
  }
  
  public void setOnValueSelectedListener(c paramC)
  {
    mListener = paramC;
  }
  
  void setTheme(Context paramContext, boolean paramBoolean)
  {
    mCircleView.setTheme(paramContext, paramBoolean);
    mAmPmCirclesView.setTheme(paramContext, paramBoolean);
    mHourRadialTextsView.setTheme(paramContext, paramBoolean);
    mMinuteRadialTextsView.setTheme(paramContext, paramBoolean);
    mHourRadialSelectorView.setTheme(paramContext, paramBoolean);
    mMinuteRadialSelectorView.setTheme(paramContext, paramBoolean);
  }
  
  public void setTime(int paramInt1, int paramInt2)
  {
    setItem(0, paramInt1);
    setItem(1, paramInt2);
  }
  
  public boolean trySettingInputEnabled(boolean paramBoolean)
  {
    boolean bool = mDoingTouch;
    int i = 0;
    if ((bool) && (!paramBoolean)) {
      return false;
    }
    mInputEnabled = paramBoolean;
    android.view.View localView = mGrayBox;
    if (paramBoolean) {
      i = 4;
    }
    localView.setVisibility(i);
    return true;
  }
  
  class a
    implements Runnable
  {
    a() {}
    
    public void run()
    {
      RadialPickerLayout.access$getMAmPmCirclesView(RadialPickerLayout.this).setAmOrPmPressed(RadialPickerLayout.access$getMIsTouchingAmOrPm(RadialPickerLayout.this));
      RadialPickerLayout.access$getMAmPmCirclesView(RadialPickerLayout.this).invalidate();
    }
  }
  
  class b
    implements Runnable
  {
    b(Boolean[] paramArrayOfBoolean) {}
    
    public void run()
    {
      RadialPickerLayout.access$setMDoingMove(RadialPickerLayout.this, true);
      RadialPickerLayout localRadialPickerLayout = RadialPickerLayout.this;
      int i = RadialPickerLayout.access$getReselectSelector(localRadialPickerLayout, RadialPickerLayout.access$getMDownDegrees(localRadialPickerLayout), paramView[0].booleanValue(), false, true);
      RadialPickerLayout.access$setMLastValueSelected(RadialPickerLayout.this, i);
      RadialPickerLayout.access$getMListener(RadialPickerLayout.this).onValueSelected(getCurrentItemShowing(), i, false);
    }
  }
  
  public static abstract interface c
  {
    public abstract void onValueSelected(int paramInt1, int paramInt2, boolean paramBoolean);
  }
}
