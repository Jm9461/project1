package com.mohamadamin.persianmaterialdatetimepicker.time;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.mohamadamin.persianmaterialdatetimepicker.HapticFeedbackController;
import com.mohamadamin.persianmaterialdatetimepicker.R.color;
import com.mohamadamin.persianmaterialdatetimepicker.R.drawable;
import com.mohamadamin.persianmaterialdatetimepicker.R.id;
import com.mohamadamin.persianmaterialdatetimepicker.R.layout;
import com.mohamadamin.persianmaterialdatetimepicker.R.string;
import com.mohamadamin.persianmaterialdatetimepicker.TypefaceHelper;
import com.mohamadamin.persianmaterialdatetimepicker.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class TimePickerDialog
  extends DialogFragment
  implements RadialPickerLayout.c
{
  private boolean mAllowAutoAdvance;
  private int mAmKeyCode;
  private android.view.View mAmPmHitspace;
  private TextView mAmPmTextView;
  private String mAmText;
  private OnTimeSetListener mCallback;
  private String mDeletedKeyFormat;
  private String mDoublePlaceholderText;
  private HapticFeedbackController mHapticFeedbackController;
  private String mHourPickerDescription;
  private TextView mHourSpaceView;
  private TextView mHourView;
  private boolean mInKbMode;
  private int mInitialHourOfDay;
  private int mInitialMinute;
  private boolean mIs24HourMode;
  private Node mLegalTimesTree;
  private String mMinutePickerDescription;
  private TextView mMinuteSpaceView;
  private TextView mMinuteView;
  private Button mOkButton;
  private DialogInterface.OnCancelListener mOnCancelListener;
  private DialogInterface.OnDismissListener mOnDismissListener;
  private char mPlaceholderText;
  private int mPmKeyCode;
  private String mPmText;
  private String mSelectHours;
  private String mSelectMinutes;
  private int mSelectedColor;
  private boolean mThemeDark;
  private RadialPickerLayout mTimePicker;
  private String mTitle;
  private ArrayList<Integer> mTypedTimes;
  private int mUnselectedColor;
  
  public TimePickerDialog() {}
  
  private boolean addKeyIfLegal(int paramInt)
  {
    if ((!mIs24HourMode) || (mTypedTimes.size() != 4))
    {
      if ((!mIs24HourMode) && (isTypedTimeFullyLegal())) {
        return false;
      }
      mTypedTimes.add(Integer.valueOf(paramInt));
      if (!isTypedTimeLegalSoFar())
      {
        deleteLastTypedKey();
        return false;
      }
      paramInt = getValFromKeyCode(paramInt);
      Utils.tryAccessibilityAnnounce(mTimePicker, String.format("%d", new Object[] { Integer.valueOf(paramInt) }));
      if (isTypedTimeFullyLegal())
      {
        if ((!mIs24HourMode) && (mTypedTimes.size() <= 3))
        {
          ArrayList localArrayList = mTypedTimes;
          localArrayList.add(localArrayList.size() - 1, Integer.valueOf(7));
          localArrayList = mTypedTimes;
          localArrayList.add(localArrayList.size() - 1, Integer.valueOf(7));
        }
        mOkButton.setEnabled(true);
        return true;
      }
    }
    else
    {
      return false;
    }
    return true;
  }
  
  private int deleteLastTypedKey()
  {
    ArrayList localArrayList = mTypedTimes;
    int i = ((Integer)localArrayList.remove(localArrayList.size() - 1)).intValue();
    if (!isTypedTimeFullyLegal()) {
      mOkButton.setEnabled(false);
    }
    return i;
  }
  
  private void finishKbMode(boolean paramBoolean)
  {
    mInKbMode = false;
    if (!mTypedTimes.isEmpty())
    {
      int[] arrayOfInt = getEnteredTime(null);
      mTimePicker.setTime(arrayOfInt[0], arrayOfInt[1]);
      if (!mIs24HourMode) {
        mTimePicker.setAmOrPm(arrayOfInt[2]);
      }
      mTypedTimes.clear();
    }
    if (paramBoolean)
    {
      updateDisplay(false);
      mTimePicker.trySettingInputEnabled(true);
    }
  }
  
  private void generateLegalTimesTree()
  {
    mLegalTimesTree = new Node(new int[0]);
    if (mIs24HourMode)
    {
      localNode1 = new Node(new int[] { 7, 8, 9, 10, 11, 12 });
      localNode2 = new Node(new int[] { 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 });
      localNode1.addChild(localNode2);
      localNode3 = new Node(new int[] { 7, 8 });
      mLegalTimesTree.addChild(localNode3);
      localNode4 = new Node(new int[] { 7, 8, 9, 10, 11, 12 });
      localNode3.addChild(localNode4);
      localNode4.addChild(localNode1);
      localNode4.addChild(new Node(new int[] { 13, 14, 15, 16 }));
      localNode4 = new Node(new int[] { 13, 14, 15, 16 });
      localNode3.addChild(localNode4);
      localNode4.addChild(localNode1);
      localNode3 = new Node(new int[] { 9 });
      mLegalTimesTree.addChild(localNode3);
      localNode4 = new Node(new int[] { 7, 8, 9, 10 });
      localNode3.addChild(localNode4);
      localNode4.addChild(localNode1);
      localNode4 = new Node(new int[] { 11, 12 });
      localNode3.addChild(localNode4);
      localNode4.addChild(localNode2);
      localNode2 = new Node(new int[] { 10, 11, 12, 13, 14, 15, 16 });
      mLegalTimesTree.addChild(localNode2);
      localNode2.addChild(localNode1);
      return;
    }
    Node localNode1 = new Node(new int[] { getAmOrPmKeyCode(0), getAmOrPmKeyCode(1) });
    Node localNode2 = new Node(new int[] { 8 });
    mLegalTimesTree.addChild(localNode2);
    localNode2.addChild(localNode1);
    Node localNode3 = new Node(new int[] { 7, 8, 9 });
    localNode2.addChild(localNode3);
    localNode3.addChild(localNode1);
    Node localNode4 = new Node(new int[] { 7, 8, 9, 10, 11, 12 });
    localNode3.addChild(localNode4);
    localNode4.addChild(localNode1);
    Node localNode5 = new Node(new int[] { 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 });
    localNode4.addChild(localNode5);
    localNode5.addChild(localNode1);
    localNode4 = new Node(new int[] { 13, 14, 15, 16 });
    localNode3.addChild(localNode4);
    localNode4.addChild(localNode1);
    localNode3 = new Node(new int[] { 10, 11, 12 });
    localNode2.addChild(localNode3);
    localNode2 = new Node(new int[] { 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 });
    localNode3.addChild(localNode2);
    localNode2.addChild(localNode1);
    localNode3 = new Node(new int[] { 9, 10, 11, 12, 13, 14, 15, 16 });
    mLegalTimesTree.addChild(localNode3);
    localNode3.addChild(localNode1);
    localNode2 = new Node(new int[] { 7, 8, 9, 10, 11, 12 });
    localNode3.addChild(localNode2);
    localNode3 = new Node(new int[] { 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 });
    localNode2.addChild(localNode3);
    localNode3.addChild(localNode1);
  }
  
  private int getAmOrPmKeyCode(int paramInt)
  {
    if ((mAmKeyCode == -1) || (mPmKeyCode == -1))
    {
      Object localObject = KeyCharacterMap.load(-1);
      int k = 0;
      while (k < Math.max(mAmText.length(), mPmText.length()))
      {
        int i = "AM".toLowerCase(Locale.getDefault()).charAt(k);
        int j = "PM".toLowerCase(Locale.getDefault()).charAt(k);
        if (i != j)
        {
          localObject = ((KeyCharacterMap)localObject).getEvents(new char[] { i, j });
          if ((localObject != null) && (localObject.length == 4))
          {
            mAmKeyCode = localObject[0].getKeyCode();
            mPmKeyCode = localObject[2].getKeyCode();
            break;
          }
          Log.e("TimePickerDialog", "Unable to find keycodes for AM and PM.");
          break;
        }
        k += 1;
      }
    }
    if (paramInt == 0) {
      return mAmKeyCode;
    }
    if (paramInt == 1) {
      return mPmKeyCode;
    }
    return -1;
  }
  
  private int[] getEnteredTime(Boolean[] paramArrayOfBoolean)
  {
    int i = -1;
    int k = 1;
    boolean bool = mIs24HourMode;
    Boolean localBoolean = Boolean.valueOf(true);
    int m = i;
    int j = k;
    ArrayList localArrayList;
    if (!bool)
    {
      m = i;
      j = k;
      if (isTypedTimeFullyLegal())
      {
        localArrayList = mTypedTimes;
        j = ((Integer)localArrayList.get(localArrayList.size() - 1)).intValue();
        if (j == getAmOrPmKeyCode(0)) {
          i = 0;
        } else if (j == getAmOrPmKeyCode(1)) {
          i = 1;
        }
        j = 2;
        m = i;
      }
    }
    int i2 = -1;
    int i1 = -1;
    int n = j;
    while (n <= mTypedTimes.size())
    {
      localArrayList = mTypedTimes;
      int i3 = getValFromKeyCode(((Integer)localArrayList.get(localArrayList.size() - n)).intValue());
      if (n == j)
      {
        i = i3;
        k = i1;
      }
      else if (n == j + 1)
      {
        i2 += i3 * 10;
        i = i2;
        k = i1;
        if (paramArrayOfBoolean != null)
        {
          i = i2;
          k = i1;
          if (i3 == 0)
          {
            paramArrayOfBoolean[1] = localBoolean;
            i = i2;
            k = i1;
          }
        }
      }
      else if (n == j + 2)
      {
        k = i3;
        i = i2;
      }
      else
      {
        i = i2;
        k = i1;
        if (n == j + 3)
        {
          i1 += i3 * 10;
          i = i2;
          k = i1;
          if (paramArrayOfBoolean != null)
          {
            i = i2;
            k = i1;
            if (i3 == 0)
            {
              paramArrayOfBoolean[0] = localBoolean;
              k = i1;
              i = i2;
            }
          }
        }
      }
      n += 1;
      i2 = i;
      i1 = k;
    }
    return new int[] { i1, i2, m };
  }
  
  private static int getValFromKeyCode(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return -1;
    case 16: 
      return 9;
    case 15: 
      return 8;
    case 14: 
      return 7;
    case 13: 
      return 6;
    case 12: 
      return 5;
    case 11: 
      return 4;
    case 10: 
      return 3;
    case 9: 
      return 2;
    case 8: 
      return 1;
    }
    return 0;
  }
  
  private boolean isTypedTimeFullyLegal()
  {
    if (mIs24HourMode)
    {
      int[] arrayOfInt = getEnteredTime(null);
      if ((arrayOfInt[0] >= 0) && (arrayOfInt[1] >= 0) && (arrayOfInt[1] < 60)) {
        return true;
      }
    }
    else if ((mTypedTimes.contains(Integer.valueOf(getAmOrPmKeyCode(0)))) || (mTypedTimes.contains(Integer.valueOf(getAmOrPmKeyCode(1)))))
    {
      return true;
    }
    return false;
  }
  
  private boolean isTypedTimeLegalSoFar()
  {
    Object localObject = mLegalTimesTree;
    Iterator localIterator = mTypedTimes.iterator();
    while (localIterator.hasNext())
    {
      Node localNode = ((Node)localObject).canReach(((Integer)localIterator.next()).intValue());
      localObject = localNode;
      if (localNode == null) {
        return false;
      }
    }
    return true;
  }
  
  public static TimePickerDialog newInstance(OnTimeSetListener paramOnTimeSetListener, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    TimePickerDialog localTimePickerDialog = new TimePickerDialog();
    localTimePickerDialog.initialize(paramOnTimeSetListener, paramInt1, paramInt2, paramBoolean);
    return localTimePickerDialog;
  }
  
  private boolean processKeyUp(int paramInt)
  {
    if ((paramInt != 111) && (paramInt != 4))
    {
      if (paramInt == 61)
      {
        if (mInKbMode)
        {
          if (!isTypedTimeFullyLegal()) {
            break label373;
          }
          finishKbMode(true);
          return true;
        }
      }
      else
      {
        Object localObject;
        if (paramInt == 66)
        {
          if (mInKbMode)
          {
            if (!isTypedTimeFullyLegal()) {
              return true;
            }
            finishKbMode(false);
          }
          localObject = mCallback;
          if (localObject != null)
          {
            RadialPickerLayout localRadialPickerLayout = mTimePicker;
            ((OnTimeSetListener)localObject).onTimeSet(localRadialPickerLayout, localRadialPickerLayout.getHours(), mTimePicker.getMinutes());
          }
          dismiss();
          return true;
        }
        if (paramInt == 67)
        {
          if ((!mInKbMode) || (mTypedTimes.isEmpty())) {
            break label375;
          }
          paramInt = deleteLastTypedKey();
          if (paramInt == getAmOrPmKeyCode(0)) {
            localObject = mAmText;
          } else if (paramInt == getAmOrPmKeyCode(1)) {
            localObject = mPmText;
          } else {
            localObject = String.format("%d", new Object[] { Integer.valueOf(getValFromKeyCode(paramInt)) });
          }
          Utils.tryAccessibilityAnnounce(mTimePicker, String.format(mDeletedKeyFormat, new Object[] { localObject }));
          updateDisplay(true);
          return false;
        }
        if ((paramInt == 7) || (paramInt == 8) || (paramInt == 9) || (paramInt == 10) || (paramInt == 11) || (paramInt == 12) || (paramInt == 13) || (paramInt == 14) || (paramInt == 15) || (paramInt == 16)) {
          break label307;
        }
        if (mIs24HourMode) {
          break label375;
        }
        if (paramInt == getAmOrPmKeyCode(0)) {
          break label307;
        }
        if (paramInt != getAmOrPmKeyCode(1)) {
          break label375;
        }
        break label307;
      }
      return false;
      label307:
      if (!mInKbMode)
      {
        if (mTimePicker == null)
        {
          Log.e("TimePickerDialog", "Unable to initiate keyboard mode, TimePicker was null.");
          return true;
        }
        mTypedTimes.clear();
        tryStartingKbMode(paramInt);
        return true;
      }
      if (!addKeyIfLegal(paramInt)) {
        break label377;
      }
      updateDisplay(false);
      return true;
    }
    else
    {
      if (!isCancelable()) {
        break label377;
      }
      dismiss();
    }
    label373:
    return true;
    label375:
    return false;
    label377:
    return true;
  }
  
  private void setCurrentItemShowing(int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    mTimePicker.setCurrentItemShowing(paramInt, paramBoolean1);
    int i;
    StringBuilder localStringBuilder;
    if (paramInt == 0)
    {
      int j = mTimePicker.getHours();
      i = j;
      if (!mIs24HourMode) {
        i = j % 12;
      }
      localObject = mTimePicker;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(mHourPickerDescription);
      localStringBuilder.append(": ");
      localStringBuilder.append(i);
      ((android.view.View)localObject).setContentDescription(localStringBuilder.toString());
      if (paramBoolean3) {
        Utils.tryAccessibilityAnnounce(mTimePicker, mSelectHours);
      }
      localObject = mHourView;
    }
    else
    {
      i = mTimePicker.getMinutes();
      localObject = mTimePicker;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(mMinutePickerDescription);
      localStringBuilder.append(": ");
      localStringBuilder.append(i);
      ((android.view.View)localObject).setContentDescription(localStringBuilder.toString());
      if (paramBoolean3) {
        Utils.tryAccessibilityAnnounce(mTimePicker, mSelectMinutes);
      }
      localObject = mMinuteView;
    }
    if (paramInt == 0) {
      i = mSelectedColor;
    } else {
      i = mUnselectedColor;
    }
    if (paramInt == 1) {
      paramInt = mSelectedColor;
    } else {
      paramInt = mUnselectedColor;
    }
    mHourView.setTextColor(i);
    mMinuteView.setTextColor(paramInt);
    Object localObject = Utils.getPulseAnimator((android.view.View)localObject, 0.85F, 1.1F);
    if (paramBoolean2) {
      ((ValueAnimator)localObject).setStartDelay(300L);
    }
    ((ObjectAnimator)localObject).start();
  }
  
  private void setHour(int paramInt, boolean paramBoolean)
  {
    if (mIs24HourMode)
    {
      localObject = "%02d";
    }
    else
    {
      String str = "%d";
      int i = paramInt % 12;
      localObject = str;
      paramInt = i;
      if (i == 0)
      {
        paramInt = 12;
        localObject = str;
      }
    }
    Object localObject = com.mohamadamin.persianmaterialdatetimepicker.views.View.format(String.format((String)localObject, new Object[] { Integer.valueOf(paramInt) }));
    mHourView.setText((CharSequence)localObject);
    mHourSpaceView.setText((CharSequence)localObject);
    if (paramBoolean) {
      Utils.tryAccessibilityAnnounce(mTimePicker, (CharSequence)localObject);
    }
  }
  
  private void setMinute(int paramInt)
  {
    int i = paramInt;
    if (paramInt == 60) {
      i = 0;
    }
    String str = com.mohamadamin.persianmaterialdatetimepicker.views.View.format(String.format(Locale.getDefault(), "%02d", new Object[] { Integer.valueOf(i) }));
    Utils.tryAccessibilityAnnounce(mTimePicker, str);
    mMinuteView.setText(str);
    mMinuteSpaceView.setText(str);
  }
  
  private void tryStartingKbMode(int paramInt)
  {
    if ((mTimePicker.trySettingInputEnabled(false)) && ((paramInt == -1) || (addKeyIfLegal(paramInt))))
    {
      mInKbMode = true;
      mOkButton.setEnabled(false);
      updateDisplay(false);
    }
  }
  
  private void updateAmPmDisplay(int paramInt)
  {
    if (paramInt == 0)
    {
      mAmPmTextView.setText(mAmText);
      Utils.tryAccessibilityAnnounce(mTimePicker, mAmText);
      mAmPmHitspace.setContentDescription(mAmText);
      return;
    }
    if (paramInt == 1)
    {
      mAmPmTextView.setText(mPmText);
      Utils.tryAccessibilityAnnounce(mTimePicker, mPmText);
      mAmPmHitspace.setContentDescription(mPmText);
      return;
    }
    mAmPmTextView.setText(mDoublePlaceholderText);
  }
  
  private void updateDisplay(boolean paramBoolean)
  {
    int i = 0;
    Object localObject = Boolean.valueOf(false);
    if ((!paramBoolean) && (mTypedTimes.isEmpty()))
    {
      int j = mTimePicker.getHours();
      int k = mTimePicker.getMinutes();
      setHour(j, true);
      setMinute(k);
      if (!mIs24HourMode)
      {
        if (j >= 12) {
          i = 1;
        }
        updateAmPmDisplay(i);
      }
      setCurrentItemShowing(mTimePicker.getCurrentItemShowing(), true, true, true);
      mOkButton.setEnabled(true);
      return;
    }
    Boolean[] arrayOfBoolean = new Boolean[2];
    arrayOfBoolean[0] = localObject;
    arrayOfBoolean[1] = localObject;
    int[] arrayOfInt = getEnteredTime(arrayOfBoolean);
    paramBoolean = arrayOfBoolean[0].booleanValue();
    String str = "%02d";
    if (paramBoolean) {
      localObject = "%02d";
    } else {
      localObject = "%2d";
    }
    if (!arrayOfBoolean[1].booleanValue()) {
      str = "%2d";
    }
    if (arrayOfInt[0] == -1) {
      localObject = mDoublePlaceholderText;
    } else {
      localObject = String.format((String)localObject, new Object[] { Integer.valueOf(arrayOfInt[0]) }).replace(' ', mPlaceholderText);
    }
    if (arrayOfInt[1] == -1) {
      str = mDoublePlaceholderText;
    } else {
      str = String.format(str, new Object[] { Integer.valueOf(arrayOfInt[1]) }).replace(' ', mPlaceholderText);
    }
    mHourView.setText(com.mohamadamin.persianmaterialdatetimepicker.views.View.format((String)localObject));
    mHourSpaceView.setText(com.mohamadamin.persianmaterialdatetimepicker.views.View.format((String)localObject));
    mHourView.setTextColor(mUnselectedColor);
    mMinuteView.setText(com.mohamadamin.persianmaterialdatetimepicker.views.View.format(str));
    mMinuteSpaceView.setText(com.mohamadamin.persianmaterialdatetimepicker.views.View.format(str));
    mMinuteView.setTextColor(mUnselectedColor);
    if (!mIs24HourMode) {
      updateAmPmDisplay(arrayOfInt[2]);
    }
  }
  
  public void initialize(OnTimeSetListener paramOnTimeSetListener, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    mCallback = paramOnTimeSetListener;
    mInitialHourOfDay = paramInt1;
    mInitialMinute = paramInt2;
    mIs24HourMode = paramBoolean;
    mInKbMode = false;
    mTitle = "";
    mThemeDark = false;
  }
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    super.onCancel(paramDialogInterface);
    DialogInterface.OnCancelListener localOnCancelListener = mOnCancelListener;
    if (localOnCancelListener != null) {
      localOnCancelListener.onCancel(paramDialogInterface);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((paramBundle != null) && (paramBundle.containsKey("hour_of_day")) && (paramBundle.containsKey("minute")) && (paramBundle.containsKey("is_24_hour_view")))
    {
      mInitialHourOfDay = paramBundle.getInt("hour_of_day");
      mInitialMinute = paramBundle.getInt("minute");
      mIs24HourMode = paramBundle.getBoolean("is_24_hour_view");
      mInKbMode = paramBundle.getBoolean("in_kb_mode");
      mTitle = paramBundle.getString("dialog_title");
      mThemeDark = paramBundle.getBoolean("dark_theme");
    }
  }
  
  public android.view.View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    getDialog().getWindow().requestFeature(1);
    paramLayoutInflater = paramLayoutInflater.inflate(R.layout.mdtp_time_picker_dialog, null);
    Object localObject = new KeyboardListener(null);
    paramLayoutInflater.findViewById(R.id.time_picker_dialog).setOnKeyListener((View.OnKeyListener)localObject);
    paramViewGroup = getResources();
    mHourPickerDescription = paramViewGroup.getString(R.string.mdtp_hour_picker_description);
    mSelectHours = paramViewGroup.getString(R.string.mdtp_select_hours);
    mMinutePickerDescription = paramViewGroup.getString(R.string.mdtp_minute_picker_description);
    mSelectMinutes = paramViewGroup.getString(R.string.mdtp_select_minutes);
    mSelectedColor = paramViewGroup.getColor(R.color.mdtp_white);
    mUnselectedColor = paramViewGroup.getColor(R.color.mdtp_accent_color_focused);
    mHourView = ((TextView)paramLayoutInflater.findViewById(R.id.hours));
    mHourView.setOnKeyListener((View.OnKeyListener)localObject);
    mHourSpaceView = ((TextView)paramLayoutInflater.findViewById(R.id.hour_space));
    mMinuteSpaceView = ((TextView)paramLayoutInflater.findViewById(R.id.minutes_space));
    mMinuteView = ((TextView)paramLayoutInflater.findViewById(R.id.minutes));
    mMinuteView.setOnKeyListener((View.OnKeyListener)localObject);
    mAmPmTextView = ((TextView)paramLayoutInflater.findViewById(R.id.ampm_label));
    mAmPmTextView.setOnKeyListener((View.OnKeyListener)localObject);
    mAmText = "?????????";
    mPmText = "????????";
    mHapticFeedbackController = new HapticFeedbackController(getActivity());
    mTimePicker = ((RadialPickerLayout)paramLayoutInflater.findViewById(R.id.time_picker));
    mTimePicker.setOnValueSelectedListener(this);
    mTimePicker.setOnKeyListener((View.OnKeyListener)localObject);
    mTimePicker.initialize(getActivity(), mHapticFeedbackController, mInitialHourOfDay, mInitialMinute, mIs24HourMode);
    int j = 0;
    int i = j;
    if (paramBundle != null)
    {
      i = j;
      if (paramBundle.containsKey("current_item_showing")) {
        i = paramBundle.getInt("current_item_showing");
      }
    }
    setCurrentItemShowing(i, false, true, true);
    mTimePicker.invalidate();
    mHourView.setOnClickListener(new TaskEditFragment.1(this));
    mMinuteView.setOnClickListener(new DashboardFragment.3(this));
    mOkButton = ((Button)paramLayoutInflater.findViewById(R.id.ok));
    mOkButton.setOnClickListener(new DashboardFragment.1(this));
    mOkButton.setOnKeyListener((View.OnKeyListener)localObject);
    mOkButton.setTypeface(TypefaceHelper.get(getDialog().getContext(), "Roboto-Medium"));
    localObject = (Button)paramLayoutInflater.findViewById(R.id.cancel);
    ((android.view.View)localObject).setOnClickListener(new DashboardFragment.2(this));
    ((TextView)localObject).setTypeface(TypefaceHelper.get(getDialog().getContext(), "Roboto-Medium"));
    if (isCancelable()) {
      i = 0;
    } else {
      i = 8;
    }
    ((android.view.View)localObject).setVisibility(i);
    mAmPmHitspace = paramLayoutInflater.findViewById(R.id.ampm_hitspace);
    if (mIs24HourMode)
    {
      mAmPmTextView.setVisibility(8);
      localObject = new RelativeLayout.LayoutParams(-2, -2);
      ((RelativeLayout.LayoutParams)localObject).addRule(13);
      ((TextView)paramLayoutInflater.findViewById(R.id.separator)).setLayoutParams((ViewGroup.LayoutParams)localObject);
    }
    else
    {
      mAmPmTextView.setVisibility(0);
      if (mInitialHourOfDay < 12) {
        i = 0;
      } else {
        i = 1;
      }
      updateAmPmDisplay(i);
      mAmPmHitspace.setOnClickListener(new DashboardFragment.4(this));
    }
    mAllowAutoAdvance = true;
    setHour(mInitialHourOfDay, true);
    setMinute(mInitialMinute);
    mDoublePlaceholderText = paramViewGroup.getString(R.string.mdtp_time_placeholder);
    mDeletedKeyFormat = paramViewGroup.getString(R.string.mdtp_deleted_key);
    mPlaceholderText = mDoublePlaceholderText.charAt(0);
    mPmKeyCode = -1;
    mAmKeyCode = -1;
    generateLegalTimesTree();
    if (mInKbMode)
    {
      mTypedTimes = paramBundle.getIntegerArrayList("typed_times");
      tryStartingKbMode(-1);
      mHourView.invalidate();
    }
    else if (mTypedTimes == null)
    {
      mTypedTimes = new ArrayList();
    }
    paramBundle = (TextView)paramLayoutInflater.findViewById(R.id.time_picker_header);
    if (!mTitle.isEmpty())
    {
      paramBundle.setVisibility(0);
      paramBundle.setText(mTitle);
    }
    mTimePicker.setTheme(getActivity().getApplicationContext(), mThemeDark);
    paramViewGroup.getColor(R.color.mdtp_white);
    paramViewGroup.getColor(R.color.mdtp_accent_color);
    i = paramViewGroup.getColor(R.color.mdtp_circle_background);
    paramViewGroup.getColor(R.color.mdtp_line_background);
    paramViewGroup.getColor(R.color.mdtp_numbers_text_color);
    paramViewGroup.getColorStateList(R.color.mdtp_done_text_color);
    j = R.drawable.mdtp_done_background_color;
    j = paramViewGroup.getColor(R.color.mdtp_background_color);
    int k = paramViewGroup.getColor(R.color.mdtp_light_gray);
    paramViewGroup.getColor(R.color.mdtp_dark_gray);
    int m = paramViewGroup.getColor(R.color.mdtp_light_gray);
    paramViewGroup.getColor(R.color.mdtp_line_dark);
    paramViewGroup.getColorStateList(R.color.mdtp_done_text_color_dark);
    int n = R.drawable.mdtp_done_background_color_dark;
    paramViewGroup = mTimePicker;
    if (mThemeDark) {
      i = m;
    }
    paramViewGroup.setBackgroundColor(i);
    paramViewGroup = paramLayoutInflater.findViewById(R.id.time_picker_dialog);
    if (mThemeDark) {
      i = k;
    } else {
      i = j;
    }
    paramViewGroup.setBackgroundColor(i);
    return paramLayoutInflater;
  }
  
  public void onDismiss(DialogInterface paramDialogInterface)
  {
    super.onDismiss(paramDialogInterface);
    DialogInterface.OnDismissListener localOnDismissListener = mOnDismissListener;
    if (localOnDismissListener != null) {
      localOnDismissListener.onDismiss(paramDialogInterface);
    }
  }
  
  public void onPause()
  {
    super.onPause();
    mHapticFeedbackController.stop();
  }
  
  public void onResume()
  {
    super.onResume();
    mHapticFeedbackController.start();
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    RadialPickerLayout localRadialPickerLayout = mTimePicker;
    if (localRadialPickerLayout != null)
    {
      paramBundle.putInt("hour_of_day", localRadialPickerLayout.getHours());
      paramBundle.putInt("minute", mTimePicker.getMinutes());
      paramBundle.putBoolean("is_24_hour_view", mIs24HourMode);
      paramBundle.putInt("current_item_showing", mTimePicker.getCurrentItemShowing());
      paramBundle.putBoolean("in_kb_mode", mInKbMode);
      if (mInKbMode) {
        paramBundle.putIntegerArrayList("typed_times", mTypedTimes);
      }
      paramBundle.putString("dialog_title", mTitle);
      paramBundle.putBoolean("dark_theme", mThemeDark);
    }
  }
  
  public void onValueSelected(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    Object localObject2;
    Object localObject1;
    if (paramInt1 == 0)
    {
      setHour(paramInt2, false);
      localObject2 = String.format("%d", new Object[] { Integer.valueOf(paramInt2) });
      localObject1 = localObject2;
      if ((mAllowAutoAdvance) && (paramBoolean))
      {
        setCurrentItemShowing(1, true, true, false);
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append(". ");
        ((StringBuilder)localObject1).append(mSelectMinutes);
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      else
      {
        localObject2 = mTimePicker;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(mHourPickerDescription);
        localStringBuilder.append(": ");
        localStringBuilder.append(paramInt2);
        ((android.view.View)localObject2).setContentDescription(localStringBuilder.toString());
      }
      Utils.tryAccessibilityAnnounce(mTimePicker, (CharSequence)localObject1);
      return;
    }
    if (paramInt1 == 1)
    {
      setMinute(paramInt2);
      localObject1 = mTimePicker;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(mMinutePickerDescription);
      ((StringBuilder)localObject2).append(": ");
      ((StringBuilder)localObject2).append(paramInt2);
      ((android.view.View)localObject1).setContentDescription(((StringBuilder)localObject2).toString());
      return;
    }
    if (paramInt1 == 2)
    {
      updateAmPmDisplay(paramInt2);
      return;
    }
    if (paramInt1 == 3)
    {
      if (!isTypedTimeFullyLegal()) {
        mTypedTimes.clear();
      }
      finishKbMode(true);
    }
  }
  
  public void tryVibrate()
  {
    mHapticFeedbackController.tryVibrate();
  }
  
  class KeyboardListener
    implements View.OnKeyListener
  {
    private KeyboardListener() {}
    
    public boolean onKey(android.view.View paramView, int paramInt, KeyEvent paramKeyEvent)
    {
      if (paramKeyEvent.getAction() == 1) {
        return TimePickerDialog.access$getProcessKeyUp(TimePickerDialog.this, paramInt);
      }
      return false;
    }
  }
  
  class Node
  {
    private ArrayList<e.g> mChildren = new ArrayList();
    
    public Node() {}
    
    public void addChild(Node paramNode)
    {
      mChildren.add(paramNode);
    }
    
    public Node canReach(int paramInt)
    {
      Object localObject = mChildren;
      if (localObject == null) {
        return null;
      }
      localObject = ((ArrayList)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        Node localNode = (Node)((Iterator)localObject).next();
        if (localNode.containsKey(paramInt)) {
          return localNode;
        }
      }
      return null;
    }
    
    public boolean containsKey(int paramInt)
    {
      int i = 0;
      for (;;)
      {
        int[] arrayOfInt = TimePickerDialog.this;
        if (i >= arrayOfInt.length) {
          break;
        }
        if (arrayOfInt[i] == paramInt) {
          return true;
        }
        i += 1;
      }
      return false;
    }
  }
  
  public abstract interface OnTimeSetListener
  {
    public abstract void onTimeSet(RadialPickerLayout paramRadialPickerLayout, int paramInt1, int paramInt2);
  }
}
