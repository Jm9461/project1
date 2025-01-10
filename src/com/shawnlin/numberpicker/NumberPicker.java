package com.shawnlin.numberpicker;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.DecimalFormatSymbols;
import java.util.Formatter;
import java.util.Locale;

public class NumberPicker
  extends LinearLayout
{
  private static final g sTwoDigitFormatter = new g();
  private int a;
  private c b;
  private int color = -16777216;
  private float f = 25.0F;
  private boolean g;
  private final boolean h;
  private int height;
  private int i = 3;
  private int k = 3;
  private View.OnClickListener listener;
  private Context m;
  private final Scroller mAdjustScroller;
  private f mAdjustScrollerCommand;
  private int mBottomSelectionDividerBottom;
  private b mChangeCurrentByOneFromLongPressCommand;
  private int mCurrentScrollOffset;
  private String[] mDisplayedValues;
  private int mDividerColor;
  private final Scroller mFlingScroller;
  private int mInitialScrollOffset;
  private float mInitialTouchX;
  private final EditText mInputText;
  private float mLastDownEventY;
  private int mLastHandledDownDpadKeyCode;
  private float mLastTouchX;
  private float mLastTouchY;
  private long mLongPressUpdateInterval = 300L;
  private int mMaxHeight;
  private int mMaxValue = 100;
  private int mMaxWidth;
  private int mMaximumFlingVelocity;
  private int mMinHeight;
  private int mMinValue = 1;
  private int mMinWidth;
  private int mMinimumFlingVelocity;
  private d mOnScrollListener;
  private e mOnValueChangeListener;
  private int mOrder;
  private int mOrientation;
  private int mPreviousScrollerY;
  private int mScrollState;
  private Drawable mSelectionDivider;
  private int mSelectionDividerHeight;
  private int mSelectionDividersDistance;
  private int mSelectorElementHeight;
  private final SparseArray<String> mSelectorIndexToStringCache = new SparseArray();
  private int[] mSelectorIndices;
  private int mSelectorTextGapHeight;
  private float mTextColor;
  private float mTextSize;
  private int mTopSelectionDividerTop;
  private int mTouchSlop;
  private int mValue;
  private VelocityTracker mVelocityTracker;
  private boolean mWrapSelectorWheel;
  private float n;
  private int o;
  private int p;
  private final Paint paint;
  private boolean q;
  private float s;
  private int textColor = -16777216;
  private float textSize = 25.0F;
  private Typeface typeface;
  private int u;
  
  public NumberPicker(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public NumberPicker(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public NumberPicker(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet);
    int j = k;
    a = (j / 2);
    mSelectorIndices = new int[j];
    mInitialScrollOffset = Integer.MIN_VALUE;
    mDividerColor = -16777216;
    mScrollState = 0;
    mLastHandledDownDpadKeyCode = -1;
    q = true;
    g = true;
    m = paramContext;
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.NumberPicker, paramInt, 0);
    mSelectionDivider = ContextCompat.getDrawable(paramContext, R.drawable.np_numberpicker_selection_divider);
    mDividerColor = paramAttributeSet.getColor(R.styleable.NumberPicker_np_dividerColor, mDividerColor);
    paramInt = (int)TypedValue.applyDimension(1, 48.0F, getResources().getDisplayMetrics());
    mSelectionDividersDistance = paramAttributeSet.getDimensionPixelSize(R.styleable.NumberPicker_np_dividerDistance, paramInt);
    paramInt = (int)TypedValue.applyDimension(1, 2.0F, getResources().getDisplayMetrics());
    mSelectionDividerHeight = paramAttributeSet.getDimensionPixelSize(R.styleable.NumberPicker_np_dividerThickness, paramInt);
    mOrder = paramAttributeSet.getInt(R.styleable.NumberPicker_np_order, 0);
    mOrientation = paramAttributeSet.getInt(R.styleable.NumberPicker_np_orientation, 1);
    mTextColor = paramAttributeSet.getDimensionPixelSize(R.styleable.NumberPicker_np_width, -1);
    mTextSize = paramAttributeSet.getDimensionPixelSize(R.styleable.NumberPicker_np_height, -1);
    init();
    h = true;
    mValue = paramAttributeSet.getInt(R.styleable.NumberPicker_np_value, mValue);
    mMaxValue = paramAttributeSet.getInt(R.styleable.NumberPicker_np_max, mMaxValue);
    mMinValue = paramAttributeSet.getInt(R.styleable.NumberPicker_np_min, mMinValue);
    color = paramAttributeSet.getColor(R.styleable.NumberPicker_np_selectedTextColor, color);
    f = paramAttributeSet.getDimension(R.styleable.NumberPicker_np_selectedTextSize, getDimension(f));
    textColor = paramAttributeSet.getColor(R.styleable.NumberPicker_np_textColor, textColor);
    textSize = paramAttributeSet.getDimension(R.styleable.NumberPicker_np_textSize, getDimension(textSize));
    typeface = Typeface.create(paramAttributeSet.getString(R.styleable.NumberPicker_np_typeface), 0);
    b = getDrawable(paramAttributeSet.getString(R.styleable.NumberPicker_np_formatter));
    q = paramAttributeSet.getBoolean(R.styleable.NumberPicker_np_fadingEdgeEnabled, q);
    g = paramAttributeSet.getBoolean(R.styleable.NumberPicker_np_scrollerEnabled, g);
    k = paramAttributeSet.getInt(R.styleable.NumberPicker_np_wheelItemCount, k);
    setWillNotDraw(false);
    ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(R.layout.number_picker_with_selector_wheel, this, true);
    mInputText = ((EditText)findViewById(R.id.np__numberpicker_input));
    mInputText.setEnabled(false);
    mInputText.setFocusable(false);
    mInputText.setImeOptions(1);
    Object localObject = new Paint();
    ((Paint)localObject).setAntiAlias(true);
    ((Paint)localObject).setTextAlign(Paint.Align.CENTER);
    paint = ((Paint)localObject);
    setSelectedTextColor(color);
    setTextColor(textColor);
    setTextSize(textSize);
    setSelectedTextSize(f);
    setTypeface(typeface);
    setFormatter(b);
    updateInputTextView();
    setValue(mValue);
    setMaxValue(mMaxValue);
    setMinValue(mMinValue);
    setDividerColor(mDividerColor);
    setWheelItemCount(k);
    mWrapSelectorWheel = paramAttributeSet.getBoolean(R.styleable.NumberPicker_np_wrapSelectorWheel, mWrapSelectorWheel);
    setWrapSelectorWheel(mWrapSelectorWheel);
    float f1 = mTextColor;
    if ((f1 != -1.0F) && (mTextSize != -1.0F))
    {
      setScaleX(f1 / mMinWidth);
      setScaleY(mTextSize / mMaxHeight);
    }
    else
    {
      f1 = mTextColor;
      if (f1 != -1.0F)
      {
        setScaleX(f1 / mMinWidth);
        setScaleY(mTextColor / mMinWidth);
      }
      else
      {
        f1 = mTextSize;
        if (f1 != -1.0F)
        {
          setScaleX(f1 / mMaxHeight);
          setScaleY(mTextSize / mMaxHeight);
        }
      }
    }
    localObject = ViewConfiguration.get(paramContext);
    mTouchSlop = ((ViewConfiguration)localObject).getScaledTouchSlop();
    mMinimumFlingVelocity = ((ViewConfiguration)localObject).getScaledMinimumFlingVelocity();
    mMaximumFlingVelocity = (((ViewConfiguration)localObject).getScaledMaximumFlingVelocity() / 8);
    mFlingScroller = new Scroller(paramContext, null, true);
    mAdjustScroller = new Scroller(paramContext, new DecelerateInterpolator(2.5F));
    if ((Build.VERSION.SDK_INT >= 16) && (getImportantForAccessibility() == 0)) {
      setImportantForAccessibility(1);
    }
    paramAttributeSet.recycle();
  }
  
  private void changeValueByOne(boolean paramBoolean)
  {
    mInputText.setVisibility(4);
    if (!moveToFinalScrollerPosition(mFlingScroller)) {
      moveToFinalScrollerPosition(mAdjustScroller);
    }
    if (abs())
    {
      mPreviousScrollerY = 0;
      if (paramBoolean) {
        mFlingScroller.startScroll(0, 0, -mSelectorElementHeight, 0, 300);
      } else {
        mFlingScroller.startScroll(0, 0, mSelectorElementHeight, 0, 300);
      }
    }
    else
    {
      p = 0;
      if (paramBoolean) {
        mFlingScroller.startScroll(0, 0, 0, -mSelectorElementHeight, 300);
      } else {
        mFlingScroller.startScroll(0, 0, 0, mSelectorElementHeight, 300);
      }
    }
    invalidate();
  }
  
  private float convertDpToPixel(float paramFloat)
  {
    return paramFloat / getResourcesgetDisplayMetricsscaledDensity;
  }
  
  private void decrementSelectorIndices(int[] paramArrayOfInt)
  {
    int j = paramArrayOfInt.length - 1;
    while (j > 0)
    {
      paramArrayOfInt[j] = paramArrayOfInt[(j - 1)];
      j -= 1;
    }
    int i1 = paramArrayOfInt[1] - 1;
    j = i1;
    if (mWrapSelectorWheel)
    {
      j = i1;
      if (i1 < mMinValue) {
        j = mMaxValue;
      }
    }
    paramArrayOfInt[0] = j;
    ensureCachedScrollSelectorValue(j);
  }
  
  private float dpToPx(float paramFloat)
  {
    return getResourcesgetDisplayMetricsdensity * paramFloat;
  }
  
  private float drawText(Paint.FontMetrics paramFontMetrics)
  {
    if (paramFontMetrics == null) {
      return 0.0F;
    }
    return Math.abs(top + bottom) / 2.0F;
  }
  
  private void ensureCachedScrollSelectorValue(int paramInt)
  {
    SparseArray localSparseArray = mSelectorIndexToStringCache;
    if ((String)localSparseArray.get(paramInt) != null) {
      return;
    }
    int j = mMinValue;
    Object localObject;
    if ((paramInt >= j) && (paramInt <= mMaxValue))
    {
      localObject = mDisplayedValues;
      if (localObject != null) {
        localObject = localObject[(paramInt - j)];
      } else {
        localObject = formatNumber(paramInt);
      }
    }
    else
    {
      localObject = "";
    }
    localSparseArray.put(paramInt, localObject);
  }
  
  private boolean ensureScrollWheelAdjusted()
  {
    int i1 = mInitialScrollOffset - mCurrentScrollOffset;
    if (i1 != 0)
    {
      int i3 = Math.abs(i1);
      int i2 = mSelectorElementHeight;
      int j = i1;
      if (i3 > i2 / 2)
      {
        j = i2;
        if (i1 > 0) {
          j = -i2;
        }
        j = i1 + j;
      }
      if (abs())
      {
        mPreviousScrollerY = 0;
        mAdjustScroller.startScroll(0, 0, j, 0, 800);
      }
      else
      {
        p = 0;
        mAdjustScroller.startScroll(0, 0, 0, j, 800);
      }
      invalidate();
      return true;
    }
    return false;
  }
  
  private void fling(int paramInt)
  {
    if (abs())
    {
      mPreviousScrollerY = 0;
      if (paramInt > 0) {
        mFlingScroller.fling$69c647f5(0, 0, paramInt, 0, 0, Integer.MAX_VALUE, 0, 0);
      } else {
        mFlingScroller.fling$69c647f5(Integer.MAX_VALUE, 0, paramInt, 0, 0, Integer.MAX_VALUE, 0, 0);
      }
    }
    else
    {
      p = 0;
      if (paramInt > 0) {
        mFlingScroller.fling$69c647f5(0, 0, 0, paramInt, 0, 0, 0, Integer.MAX_VALUE);
      } else {
        mFlingScroller.fling$69c647f5(0, Integer.MAX_VALUE, 0, paramInt, 0, 0, 0, Integer.MAX_VALUE);
      }
    }
    invalidate();
  }
  
  private String formatNumber(int paramInt)
  {
    c localC = b;
    if (localC != null) {
      return localC.format(paramInt);
    }
    return formatNumberWithLocale(paramInt);
  }
  
  private String formatNumberWithLocale(int paramInt)
  {
    return String.format(Locale.getDefault(), "%d", new Object[] { Integer.valueOf(paramInt) });
  }
  
  private float getDimension(float paramFloat)
  {
    return TypedValue.applyDimension(2, paramFloat, getResources().getDisplayMetrics());
  }
  
  private c getDrawable(final String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    return new a(paramString);
  }
  
  private float getMaxTextSize()
  {
    return Math.max(textSize, f);
  }
  
  private int[] getSelectorIndices()
  {
    return mSelectorIndices;
  }
  
  public static final c getTwoDigitFormatter()
  {
    return sTwoDigitFormatter;
  }
  
  private int getWrappedSelectorIndex(int paramInt)
  {
    int i1 = mMaxValue;
    if (paramInt > i1)
    {
      j = mMinValue;
      return j + (paramInt - i1) % (i1 - j) - 1;
    }
    int i2 = mMinValue;
    int j = paramInt;
    if (paramInt < i2) {
      j = i1 - (i2 - paramInt) % (i1 - i2) + 1;
    }
    return j;
  }
  
  private void incrementSelectorIndices(int[] paramArrayOfInt)
  {
    int j = 0;
    while (j < paramArrayOfInt.length - 1)
    {
      paramArrayOfInt[j] = paramArrayOfInt[(j + 1)];
      j += 1;
    }
    int i1 = paramArrayOfInt[(paramArrayOfInt.length - 2)] + 1;
    j = i1;
    if (mWrapSelectorWheel)
    {
      j = i1;
      if (i1 > mMaxValue) {
        j = mMinValue;
      }
    }
    paramArrayOfInt[(paramArrayOfInt.length - 1)] = j;
    ensureCachedScrollSelectorValue(j);
  }
  
  private void init()
  {
    if (abs())
    {
      mMinHeight = -1;
      mMaxHeight = ((int)dpToPx(64.0F));
      mMinWidth = ((int)dpToPx(180.0F));
      mMaxWidth = -1;
      return;
    }
    mMinHeight = -1;
    mMaxHeight = ((int)dpToPx(180.0F));
    mMinWidth = ((int)dpToPx(64.0F));
    mMaxWidth = -1;
  }
  
  private void initializeSelectorWheel()
  {
    initializeSelectorWheelIndices();
    int[] arrayOfInt = getSelectorIndices();
    int j = (arrayOfInt.length - 1) * (int)textSize + (int)f;
    float f1 = arrayOfInt.length;
    if (abs())
    {
      o = ((int)((getRight() - getLeft() - j) / f1));
      mSelectorElementHeight = ((int)getMaxTextSize() + o);
      mInitialScrollOffset = ((int)s - mSelectorElementHeight * a);
    }
    else
    {
      mSelectorTextGapHeight = ((int)((getBottom() - getTop() - j) / f1));
      mSelectorElementHeight = ((int)getMaxTextSize() + mSelectorTextGapHeight);
      mInitialScrollOffset = ((int)n - mSelectorElementHeight * a);
    }
    mCurrentScrollOffset = mInitialScrollOffset;
    updateInputTextView();
  }
  
  private void initializeSelectorWheelIndices()
  {
    mSelectorIndexToStringCache.clear();
    int[] arrayOfInt = getSelectorIndices();
    int i3 = getValue();
    int j = 0;
    while (j < mSelectorIndices.length)
    {
      int i2 = j - a + i3;
      int i1 = i2;
      if (mWrapSelectorWheel) {
        i1 = getWrappedSelectorIndex(i2);
      }
      arrayOfInt[j] = i1;
      ensureCachedScrollSelectorValue(arrayOfInt[j]);
      j += 1;
    }
  }
  
  private int makeMeasureSpec(int paramInt1, int paramInt2)
  {
    if (paramInt2 == -1) {
      return paramInt1;
    }
    int j = View.MeasureSpec.getSize(paramInt1);
    int i1 = View.MeasureSpec.getMode(paramInt1);
    if (i1 != Integer.MIN_VALUE)
    {
      if (i1 != 0)
      {
        if (i1 == 1073741824) {
          return paramInt1;
        }
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Unknown measure mode: ");
        localStringBuilder.append(i1);
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      return View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
    }
    return View.MeasureSpec.makeMeasureSpec(Math.min(j, paramInt2), 1073741824);
  }
  
  private boolean moveToFinalScrollerPosition(Scroller paramScroller)
  {
    paramScroller.forceFinished(true);
    int i3;
    int i4;
    if (abs())
    {
      i2 = paramScroller.getDuration() - paramScroller.getCurrY();
      j = mCurrentScrollOffset;
      i1 = mSelectorElementHeight;
      i1 = mInitialScrollOffset - (j + i2) % i1;
      if (i1 != 0)
      {
        i3 = Math.abs(i1);
        i4 = mSelectorElementHeight;
        j = i1;
        if (i3 > i4 / 2) {
          if (i1 > 0) {
            j = i1 - i4;
          } else {
            j = i1 + i4;
          }
        }
        scrollBy(i2 + j, 0);
        return true;
      }
      return false;
    }
    int i2 = paramScroller.getFinalY() - paramScroller.computeScrollOffset();
    int j = mCurrentScrollOffset;
    int i1 = mSelectorElementHeight;
    i1 = mInitialScrollOffset - (j + i2) % i1;
    if (i1 != 0)
    {
      i3 = Math.abs(i1);
      i4 = mSelectorElementHeight;
      j = i1;
      if (i3 > i4 / 2) {
        if (i1 > 0) {
          j = i1 - i4;
        } else {
          j = i1 + i4;
        }
      }
      scrollBy(0, i2 + j);
      return true;
    }
    return false;
  }
  
  private void notifyChange(int paramInt1, int paramInt2)
  {
    e localE = mOnValueChangeListener;
    if (localE != null) {
      localE.onValueChange(this, paramInt1, mValue);
    }
  }
  
  private void onInterceptTouchEvent()
  {
    if (abs())
    {
      setHorizontalFadingEdgeEnabled(true);
      setFadingEdgeLength((getRight() - getLeft() - (int)textSize) / 2);
      return;
    }
    setVerticalFadingEdgeEnabled(true);
    setFadingEdgeLength((getBottom() - getTop() - (int)textSize) / 2);
  }
  
  private void onScrollStateChange(int paramInt)
  {
    if (mScrollState == paramInt) {
      return;
    }
    mScrollState = paramInt;
    d localD = mOnScrollListener;
    if (localD != null) {
      localD.onScrollStateChange(this, paramInt);
    }
  }
  
  private void onScrollerFinished(Scroller paramScroller)
  {
    if (paramScroller == mFlingScroller)
    {
      if (!ensureScrollWheelAdjusted()) {
        updateInputTextView();
      }
      onScrollStateChange(0);
      return;
    }
    if (mScrollState != 1) {
      updateInputTextView();
    }
  }
  
  private void postChangeCurrentByOneFromLongPress(boolean paramBoolean, long paramLong)
  {
    b localB = mChangeCurrentByOneFromLongPressCommand;
    if (localB == null) {
      mChangeCurrentByOneFromLongPressCommand = new b();
    } else {
      removeCallbacks(localB);
    }
    b.access$getSetIncrement(mChangeCurrentByOneFromLongPressCommand, paramBoolean);
    postDelayed(mChangeCurrentByOneFromLongPressCommand, paramLong);
  }
  
  private float pxToDp(float paramFloat)
  {
    return paramFloat / getResourcesgetDisplayMetricsdensity;
  }
  
  private void removeAllCallbacks()
  {
    Object localObject = mChangeCurrentByOneFromLongPressCommand;
    if (localObject != null) {
      removeCallbacks((Runnable)localObject);
    }
    localObject = mAdjustScrollerCommand;
    if (localObject != null) {
      removeCallbacks((Runnable)localObject);
    }
  }
  
  private void removeChangeCurrentByOneFromLongPress()
  {
    b localB = mChangeCurrentByOneFromLongPressCommand;
    if (localB != null) {
      removeCallbacks(localB);
    }
  }
  
  public static int resolveSizeAndState(int paramInt1, int paramInt2, int paramInt3)
  {
    int j = paramInt1;
    int i1 = View.MeasureSpec.getMode(paramInt2);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    if (i1 != Integer.MIN_VALUE)
    {
      if (i1 != 0) {
        if (i1 != 1073741824) {
          paramInt1 = j;
        } else {
          paramInt1 = paramInt2;
        }
      }
    }
    else if (paramInt2 < paramInt1) {
      paramInt1 = paramInt2 | 0x1000000;
    }
    return 0xFF000000 & paramInt3 | paramInt1;
  }
  
  private int resolveSizeAndStateRespectingMinSize(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 != -1) {
      return resolveSizeAndState(Math.max(paramInt1, paramInt2), paramInt3, 0);
    }
    return paramInt2;
  }
  
  private void setValueInternal(int paramInt, boolean paramBoolean)
  {
    if (mValue == paramInt) {
      return;
    }
    if (mWrapSelectorWheel) {
      paramInt = getWrappedSelectorIndex(paramInt);
    } else {
      paramInt = Math.min(Math.max(paramInt, mMinValue), mMaxValue);
    }
    int j = mValue;
    mValue = paramInt;
    updateInputTextView();
    if (paramBoolean) {
      notifyChange(j, paramInt);
    }
    initializeSelectorWheelIndices();
    invalidate();
  }
  
  private void tryComputeMaxWidth()
  {
    if (!h) {
      return;
    }
    paint.setTextSize(getMaxTextSize());
    int j = 0;
    String[] arrayOfString = mDisplayedValues;
    float f1;
    int i1;
    int i2;
    if (arrayOfString == null)
    {
      f1 = 0.0F;
      j = 0;
      while (j <= 9)
      {
        float f3 = paint.measureText(formatNumberWithLocale(j));
        float f2 = f1;
        if (f3 > f1) {
          f2 = f3;
        }
        j += 1;
        f1 = f2;
      }
      i1 = 0;
      j = mMaxValue;
      while (j > 0)
      {
        i1 += 1;
        j /= 10;
      }
      i2 = (int)(i1 * f1);
    }
    else
    {
      int i3 = arrayOfString.length;
      i1 = 0;
      for (;;)
      {
        i2 = j;
        if (i1 >= i3) {
          break;
        }
        f1 = paint.measureText(mDisplayedValues[i1]);
        i2 = j;
        if (f1 > j) {
          i2 = (int)f1;
        }
        i1 += 1;
        j = i2;
      }
    }
    j = i2 + (mInputText.getPaddingLeft() + mInputText.getPaddingRight());
    if (mMaxWidth != j)
    {
      i1 = mMinWidth;
      if (j > i1) {
        mMaxWidth = j;
      } else {
        mMaxWidth = i1;
      }
      invalidate();
    }
  }
  
  private boolean updateInputTextView()
  {
    Object localObject = mDisplayedValues;
    if (localObject == null) {
      localObject = formatNumber(mValue);
    } else {
      localObject = localObject[(mValue - mMinValue)];
    }
    if ((!TextUtils.isEmpty((CharSequence)localObject)) && (!((String)localObject).equals(mInputText.getText().toString())))
    {
      mInputText.setText((CharSequence)localObject);
      return true;
    }
    return false;
  }
  
  public boolean a()
  {
    return getOrder() == 0;
  }
  
  public boolean abs()
  {
    return getOrientation() == 0;
  }
  
  public boolean canScrollHorizontally()
  {
    return g;
  }
  
  public void computeScroll()
  {
    if (!canScrollHorizontally()) {
      return;
    }
    Scroller localScroller2 = mFlingScroller;
    Scroller localScroller1 = localScroller2;
    if (localScroller2.isFinished())
    {
      localScroller2 = mAdjustScroller;
      localScroller1 = localScroller2;
      if (localScroller2.isFinished()) {
        return;
      }
    }
    localScroller1.draw();
    int j;
    if (abs())
    {
      j = localScroller1.getCurrY();
      if (mPreviousScrollerY == 0) {
        mPreviousScrollerY = localScroller1.getStartY();
      }
      scrollBy(j - mPreviousScrollerY, 0);
      mPreviousScrollerY = j;
    }
    else
    {
      j = localScroller1.computeScrollOffset();
      if (p == 0) {
        p = localScroller1.startScroll();
      }
      scrollBy(0, j - p);
      p = j;
    }
    if (localScroller1.isFinished())
    {
      onScrollerFinished(localScroller1);
      return;
    }
    postInvalidate();
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    int j = paramKeyEvent.getKeyCode();
    if ((j != 19) && (j != 20))
    {
      if ((j == 23) || (j == 66)) {
        removeAllCallbacks();
      }
    }
    else
    {
      int i1 = paramKeyEvent.getAction();
      if (i1 != 0)
      {
        if ((i1 == 1) && (mLastHandledDownDpadKeyCode == j))
        {
          mLastHandledDownDpadKeyCode = -1;
          return true;
        }
      }
      else if ((!mWrapSelectorWheel) && (j != 20) ? getValue() > getMinValue() : getValue() < getMaxValue())
      {
        requestFocus();
        mLastHandledDownDpadKeyCode = j;
        removeAllCallbacks();
        if (!mFlingScroller.isFinished()) {
          break label165;
        }
        boolean bool;
        if (j == 20) {
          bool = true;
        } else {
          bool = false;
        }
        changeValueByOne(bool);
        return true;
      }
    }
    return super.dispatchKeyEvent(paramKeyEvent);
    label165:
    return true;
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    int j = paramMotionEvent.getAction() & 0xFF;
    if ((j == 1) || (j == 3)) {
      removeAllCallbacks();
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }
  
  public boolean dispatchTrackballEvent(MotionEvent paramMotionEvent)
  {
    int j = paramMotionEvent.getAction() & 0xFF;
    if ((j == 1) || (j == 3)) {
      removeAllCallbacks();
    }
    return super.dispatchTrackballEvent(paramMotionEvent);
  }
  
  protected float getBottomFadingEdgeStrength()
  {
    if ((!abs()) && (q)) {
      return 0.9F;
    }
    return 0.0F;
  }
  
  public String[] getDisplayedValues()
  {
    return mDisplayedValues;
  }
  
  public int getDividerColor()
  {
    return mDividerColor;
  }
  
  public float getDividerDistance()
  {
    return pxToDp(mSelectionDividersDistance);
  }
  
  public float getDividerThickness()
  {
    return pxToDp(mSelectionDividerHeight);
  }
  
  public c getFormatter()
  {
    return b;
  }
  
  protected float getLeftFadingEdgeStrength()
  {
    if ((abs()) && (q)) {
      return 0.9F;
    }
    return 0.0F;
  }
  
  public int getMaxValue()
  {
    return mMaxValue;
  }
  
  public int getMinValue()
  {
    return mMinValue;
  }
  
  public int getOrder()
  {
    return mOrder;
  }
  
  public int getOrientation()
  {
    return mOrientation;
  }
  
  protected float getRightFadingEdgeStrength()
  {
    if ((abs()) && (q)) {
      return 0.9F;
    }
    return 0.0F;
  }
  
  public int getSelectedTextColor()
  {
    return color;
  }
  
  public float getSelectedTextSize()
  {
    return f;
  }
  
  public int getTextColor()
  {
    return textColor;
  }
  
  public float getTextSize()
  {
    return getDimension(textSize);
  }
  
  protected float getTopFadingEdgeStrength()
  {
    if ((!abs()) && (q)) {
      return 0.9F;
    }
    return 0.0F;
  }
  
  public Typeface getTypeface()
  {
    return typeface;
  }
  
  public int getValue()
  {
    return mValue;
  }
  
  public int getWheelItemCount()
  {
    return k;
  }
  
  public boolean getWrapSelectorWheel()
  {
    return mWrapSelectorWheel;
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    removeAllCallbacks();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.save();
    float f3;
    float f4;
    float f1;
    float f2;
    if (abs())
    {
      f3 = mCurrentScrollOffset;
      f4 = mInputText.getBaseline() + mInputText.getTop();
      f1 = f3;
      f2 = f4;
      if (i < 3)
      {
        paramCanvas.clipRect(mTopSelectionDividerTop, 0, mBottomSelectionDividerBottom, getBottom());
        f1 = f3;
        f2 = f4;
      }
    }
    else
    {
      f3 = (getRight() - getLeft()) / 2;
      f4 = mCurrentScrollOffset;
      f1 = f3;
      f2 = f4;
      if (i < 3)
      {
        paramCanvas.clipRect(0, u, getRight(), height);
        f2 = f4;
        f1 = f3;
      }
    }
    int[] arrayOfInt = getSelectorIndices();
    int j = 0;
    int i1;
    while (j < arrayOfInt.length)
    {
      if (j == a)
      {
        paint.setTextSize(f);
        paint.setColor(color);
      }
      else
      {
        paint.setTextSize(textSize);
        paint.setColor(textColor);
      }
      if (a()) {
        i1 = j;
      } else {
        i1 = arrayOfInt.length - j - 1;
      }
      i1 = arrayOfInt[i1];
      String str = (String)mSelectorIndexToStringCache.get(i1);
      if ((j != a) || (mInputText.getVisibility() != 0)) {
        if (abs()) {
          paramCanvas.drawText(str, f1, f2, paint);
        } else {
          paramCanvas.drawText(str, f1, drawText(paint.getFontMetrics()) + f2, paint);
        }
      }
      if (abs()) {
        f1 += mSelectorElementHeight;
      } else {
        f2 += mSelectorElementHeight;
      }
      j += 1;
    }
    paramCanvas.restore();
    if (mSelectionDivider != null)
    {
      if (abs())
      {
        j = mTopSelectionDividerTop;
        i1 = mSelectionDividerHeight;
        mSelectionDivider.setBounds(j, 0, i1 + j, getBottom());
        mSelectionDivider.draw(paramCanvas);
        j = mBottomSelectionDividerBottom;
        i1 = mSelectionDividerHeight;
        mSelectionDivider.setBounds(j - i1, 0, j, getBottom());
        mSelectionDivider.draw(paramCanvas);
        return;
      }
      j = u;
      i1 = mSelectionDividerHeight;
      mSelectionDivider.setBounds(0, j, getRight(), i1 + j);
      mSelectionDivider.draw(paramCanvas);
      j = height;
      i1 = mSelectionDividerHeight;
      mSelectionDivider.setBounds(0, j - i1, getRight(), j);
      mSelectionDivider.draw(paramCanvas);
    }
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(NumberPicker.class.getName());
    paramAccessibilityEvent.setScrollable(canScrollHorizontally());
    int j = mMinValue;
    int i2 = mValue;
    int i1 = mSelectorElementHeight;
    i2 = (i2 + j) * i1;
    j = (mMaxValue - j) * i1;
    if (abs())
    {
      paramAccessibilityEvent.setScrollX(i2);
      paramAccessibilityEvent.setMaxScrollX(j);
      return;
    }
    paramAccessibilityEvent.setScrollY(i2);
    paramAccessibilityEvent.setMaxScrollY(j);
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!isEnabled()) {
      return false;
    }
    if ((paramMotionEvent.getAction() & 0xFF) != 0) {
      return false;
    }
    removeAllCallbacks();
    mInputText.setVisibility(4);
    float f1;
    if (abs())
    {
      f1 = paramMotionEvent.getX();
      mLastDownEventY = f1;
      mLastTouchX = f1;
      getParent().requestDisallowInterceptTouchEvent(true);
      if (!mFlingScroller.isFinished())
      {
        mFlingScroller.forceFinished(true);
        mAdjustScroller.forceFinished(true);
        onScrollStateChange(0);
        return true;
      }
      if (!mAdjustScroller.isFinished())
      {
        mFlingScroller.forceFinished(true);
        mAdjustScroller.forceFinished(true);
        return true;
      }
      f1 = mLastDownEventY;
      if ((f1 >= mTopSelectionDividerTop) && (f1 <= mBottomSelectionDividerBottom))
      {
        paramMotionEvent = listener;
        if (paramMotionEvent != null)
        {
          paramMotionEvent.onClick(this);
          return true;
        }
      }
      else
      {
        f1 = mLastDownEventY;
        if (f1 < mTopSelectionDividerTop)
        {
          postChangeCurrentByOneFromLongPress(false, ViewConfiguration.getLongPressTimeout());
          return true;
        }
        if (f1 > mBottomSelectionDividerBottom)
        {
          postChangeCurrentByOneFromLongPress(true, ViewConfiguration.getLongPressTimeout());
          return true;
        }
      }
    }
    else
    {
      f1 = paramMotionEvent.getY();
      mInitialTouchX = f1;
      mLastTouchY = f1;
      getParent().requestDisallowInterceptTouchEvent(true);
      if (!mFlingScroller.isFinished())
      {
        mFlingScroller.forceFinished(true);
        mAdjustScroller.forceFinished(true);
        onScrollStateChange(0);
        return true;
      }
      if (!mAdjustScroller.isFinished())
      {
        mFlingScroller.forceFinished(true);
        mAdjustScroller.forceFinished(true);
        return true;
      }
      f1 = mInitialTouchX;
      if ((f1 >= u) && (f1 <= height))
      {
        paramMotionEvent = listener;
        if (paramMotionEvent != null)
        {
          paramMotionEvent.onClick(this);
          return true;
        }
      }
      else
      {
        f1 = mInitialTouchX;
        if (f1 < u)
        {
          postChangeCurrentByOneFromLongPress(false, ViewConfiguration.getLongPressTimeout());
          return true;
        }
        if (f1 > height) {
          postChangeCurrentByOneFromLongPress(true, ViewConfiguration.getLongPressTimeout());
        }
      }
    }
    return true;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt4 = getMeasuredWidth();
    paramInt3 = getMeasuredHeight();
    paramInt1 = mInputText.getMeasuredWidth();
    paramInt2 = mInputText.getMeasuredHeight();
    paramInt4 = (paramInt4 - paramInt1) / 2;
    paramInt3 = (paramInt3 - paramInt2) / 2;
    mInputText.layout(paramInt4, paramInt3, paramInt4 + paramInt1, paramInt3 + paramInt2);
    s = (mInputText.getX() + mInputText.getMeasuredWidth() / 2);
    n = (mInputText.getY() + mInputText.getMeasuredHeight() / 2);
    if (paramBoolean)
    {
      initializeSelectorWheel();
      onInterceptTouchEvent();
      if (abs())
      {
        paramInt2 = getWidth();
        paramInt1 = mSelectionDividersDistance;
        paramInt2 = (paramInt2 - paramInt1) / 2;
        paramInt3 = mSelectionDividerHeight;
        mTopSelectionDividerTop = (paramInt2 - paramInt3);
        mBottomSelectionDividerBottom = (mTopSelectionDividerTop + paramInt3 * 2 + paramInt1);
        return;
      }
      paramInt2 = getHeight();
      paramInt1 = mSelectionDividersDistance;
      paramInt2 = (paramInt2 - paramInt1) / 2;
      paramInt3 = mSelectionDividerHeight;
      u = (paramInt2 - paramInt3);
      height = (u + paramInt3 * 2 + paramInt1);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(makeMeasureSpec(paramInt1, mMaxWidth), makeMeasureSpec(paramInt2, mMaxHeight));
    setMeasuredDimension(resolveSizeAndStateRespectingMinSize(mMinWidth, getMeasuredWidth(), paramInt1), resolveSizeAndStateRespectingMinSize(mMinHeight, getMeasuredHeight(), paramInt2));
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!isEnabled()) {
      return false;
    }
    if (!canScrollHorizontally()) {
      return false;
    }
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
    mVelocityTracker.addMovement(paramMotionEvent);
    int j = paramMotionEvent.getAction() & 0xFF;
    if (j != 1)
    {
      if (j != 2) {
        return true;
      }
      if (abs())
      {
        f1 = paramMotionEvent.getX();
        if (mScrollState != 1)
        {
          if ((int)Math.abs(f1 - mLastDownEventY) > mTouchSlop)
          {
            removeAllCallbacks();
            onScrollStateChange(1);
          }
        }
        else
        {
          scrollBy((int)(f1 - mLastTouchX), 0);
          invalidate();
        }
        mLastTouchX = f1;
        return true;
      }
      float f1 = paramMotionEvent.getY();
      if (mScrollState != 1)
      {
        if ((int)Math.abs(f1 - mInitialTouchX) > mTouchSlop)
        {
          removeAllCallbacks();
          onScrollStateChange(1);
        }
      }
      else
      {
        scrollBy(0, (int)(f1 - mLastTouchY));
        invalidate();
      }
      mLastTouchY = f1;
      return true;
    }
    removeChangeCurrentByOneFromLongPress();
    VelocityTracker localVelocityTracker = mVelocityTracker;
    localVelocityTracker.computeCurrentVelocity(1000, mMaximumFlingVelocity);
    if (abs())
    {
      j = (int)localVelocityTracker.getXVelocity();
      if (Math.abs(j) > mMinimumFlingVelocity)
      {
        fling(j);
        onScrollStateChange(2);
      }
      else
      {
        j = (int)paramMotionEvent.getX();
        if ((int)Math.abs(j - mLastDownEventY) <= mTouchSlop)
        {
          j = j / mSelectorElementHeight - a;
          if (j > 0) {
            changeValueByOne(true);
          } else if (j < 0) {
            changeValueByOne(false);
          } else {
            ensureScrollWheelAdjusted();
          }
        }
        else
        {
          ensureScrollWheelAdjusted();
        }
        onScrollStateChange(0);
      }
    }
    else
    {
      j = (int)localVelocityTracker.getYVelocity();
      if (Math.abs(j) > mMinimumFlingVelocity)
      {
        fling(j);
        onScrollStateChange(2);
      }
      else
      {
        j = (int)paramMotionEvent.getY();
        if ((int)Math.abs(j - mInitialTouchX) <= mTouchSlop)
        {
          j = j / mSelectorElementHeight - a;
          if (j > 0) {
            changeValueByOne(true);
          } else if (j < 0) {
            changeValueByOne(false);
          } else {
            ensureScrollWheelAdjusted();
          }
        }
        else
        {
          ensureScrollWheelAdjusted();
        }
        onScrollStateChange(0);
      }
    }
    mVelocityTracker.recycle();
    mVelocityTracker = null;
    return true;
  }
  
  public void scrollBy(int paramInt1, int paramInt2)
  {
    if (!canScrollHorizontally()) {
      return;
    }
    int[] arrayOfInt = getSelectorIndices();
    if (abs())
    {
      if (a())
      {
        if ((!mWrapSelectorWheel) && (paramInt1 > 0) && (arrayOfInt[a] <= mMinValue))
        {
          mCurrentScrollOffset = mInitialScrollOffset;
          return;
        }
        if ((!mWrapSelectorWheel) && (paramInt1 < 0) && (arrayOfInt[a] >= mMaxValue)) {
          mCurrentScrollOffset = mInitialScrollOffset;
        }
      }
      else
      {
        if ((!mWrapSelectorWheel) && (paramInt1 > 0) && (arrayOfInt[a] >= mMaxValue))
        {
          mCurrentScrollOffset = mInitialScrollOffset;
          return;
        }
        if ((!mWrapSelectorWheel) && (paramInt1 < 0) && (arrayOfInt[a] <= mMinValue))
        {
          mCurrentScrollOffset = mInitialScrollOffset;
          return;
        }
      }
      mCurrentScrollOffset += paramInt1;
      paramInt1 = o;
    }
    else
    {
      if (a())
      {
        if ((!mWrapSelectorWheel) && (paramInt2 > 0) && (arrayOfInt[a] <= mMinValue))
        {
          mCurrentScrollOffset = mInitialScrollOffset;
          return;
        }
        if ((!mWrapSelectorWheel) && (paramInt2 < 0) && (arrayOfInt[a] >= mMaxValue)) {
          mCurrentScrollOffset = mInitialScrollOffset;
        }
      }
      else
      {
        if ((!mWrapSelectorWheel) && (paramInt2 > 0) && (arrayOfInt[a] >= mMaxValue))
        {
          mCurrentScrollOffset = mInitialScrollOffset;
          return;
        }
        if ((!mWrapSelectorWheel) && (paramInt2 < 0) && (arrayOfInt[a] <= mMinValue))
        {
          mCurrentScrollOffset = mInitialScrollOffset;
          return;
        }
      }
      mCurrentScrollOffset += paramInt2;
      paramInt1 = mSelectorTextGapHeight;
    }
    for (;;)
    {
      paramInt2 = mCurrentScrollOffset;
      if (paramInt2 - mInitialScrollOffset <= paramInt1) {
        break;
      }
      mCurrentScrollOffset = (paramInt2 - mSelectorElementHeight);
      if (a()) {
        decrementSelectorIndices(arrayOfInt);
      } else {
        incrementSelectorIndices(arrayOfInt);
      }
      setValueInternal(arrayOfInt[a], true);
      if ((!mWrapSelectorWheel) && (arrayOfInt[a] < mMinValue)) {
        mCurrentScrollOffset = mInitialScrollOffset;
      }
    }
    for (;;)
    {
      paramInt2 = mCurrentScrollOffset;
      if (paramInt2 - mInitialScrollOffset >= -paramInt1) {
        break;
      }
      mCurrentScrollOffset = (paramInt2 + mSelectorElementHeight);
      if (a()) {
        incrementSelectorIndices(arrayOfInt);
      } else {
        decrementSelectorIndices(arrayOfInt);
      }
      setValueInternal(arrayOfInt[a], true);
      if ((!mWrapSelectorWheel) && (arrayOfInt[a] > mMaxValue)) {
        mCurrentScrollOffset = mInitialScrollOffset;
      }
    }
  }
  
  public void setDisplayedValues(String[] paramArrayOfString)
  {
    if (mDisplayedValues == paramArrayOfString) {
      return;
    }
    mDisplayedValues = paramArrayOfString;
    if (mDisplayedValues != null) {
      mInputText.setRawInputType(524289);
    } else {
      mInputText.setRawInputType(2);
    }
    updateInputTextView();
    initializeSelectorWheelIndices();
    tryComputeMaxWidth();
  }
  
  public void setDividerColor(int paramInt)
  {
    mDividerColor = paramInt;
    mSelectionDivider = new ColorDrawable(paramInt);
  }
  
  public void setDividerColorResource(int paramInt)
  {
    setDividerColor(ContextCompat.getColor(m, paramInt));
  }
  
  public void setDividerDistance(int paramInt)
  {
    mSelectionDividersDistance = ((int)dpToPx(paramInt));
  }
  
  public void setDividerThickness(int paramInt)
  {
    mSelectionDividerHeight = ((int)dpToPx(paramInt));
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    mInputText.setEnabled(paramBoolean);
  }
  
  public void setFadingEdgeEnabled(boolean paramBoolean)
  {
    q = paramBoolean;
  }
  
  public void setFormatter(int paramInt)
  {
    setFormatter(getResources().getString(paramInt));
  }
  
  public void setFormatter(c paramC)
  {
    if (paramC == b) {
      return;
    }
    b = paramC;
    initializeSelectorWheelIndices();
    updateInputTextView();
  }
  
  public void setFormatter(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    setFormatter(getDrawable(paramString));
  }
  
  public void setMaxValue(int paramInt)
  {
    if (paramInt >= 0)
    {
      mMaxValue = paramInt;
      paramInt = mMaxValue;
      if (paramInt < mValue) {
        mValue = paramInt;
      }
      boolean bool;
      if (mMaxValue - mMinValue > mSelectorIndices.length) {
        bool = true;
      } else {
        bool = false;
      }
      setWrapSelectorWheel(bool);
      initializeSelectorWheelIndices();
      updateInputTextView();
      tryComputeMaxWidth();
      invalidate();
      return;
    }
    throw new IllegalArgumentException("maxValue must be >= 0");
  }
  
  public void setMinValue(int paramInt)
  {
    mMinValue = paramInt;
    paramInt = mMinValue;
    if (paramInt > mValue) {
      mValue = paramInt;
    }
    boolean bool;
    if (mMaxValue - mMinValue > mSelectorIndices.length) {
      bool = true;
    } else {
      bool = false;
    }
    setWrapSelectorWheel(bool);
    initializeSelectorWheelIndices();
    updateInputTextView();
    tryComputeMaxWidth();
    invalidate();
  }
  
  public void setNumber(int paramInt1, int paramInt2)
  {
    setNumber(getResources().getString(paramInt1), paramInt2);
  }
  
  public void setNumber(String paramString, int paramInt)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    setTypeface(Typeface.create(paramString, paramInt));
  }
  
  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    listener = paramOnClickListener;
  }
  
  public void setOnLongPressUpdateInterval(long paramLong)
  {
    mLongPressUpdateInterval = paramLong;
  }
  
  public void setOnScrollListener(d paramD)
  {
    mOnScrollListener = paramD;
  }
  
  public void setOnValueChangedListener(e paramE)
  {
    mOnValueChangeListener = paramE;
  }
  
  public void setOrder(int paramInt)
  {
    mOrder = paramInt;
  }
  
  public void setOrientation(int paramInt)
  {
    mOrientation = paramInt;
    init();
  }
  
  public void setScrollerEnabled(boolean paramBoolean)
  {
    g = paramBoolean;
  }
  
  public void setSelectedTextColor(int paramInt)
  {
    color = paramInt;
    mInputText.setTextColor(color);
  }
  
  public void setSelectedTextColorResource(int paramInt)
  {
    setSelectedTextColor(ContextCompat.getColor(m, paramInt));
  }
  
  public void setSelectedTextSize(float paramFloat)
  {
    f = paramFloat;
    mInputText.setTextSize(convertDpToPixel(f));
  }
  
  public void setSelectedTextSize(int paramInt)
  {
    setSelectedTextSize(getResources().getDimension(paramInt));
  }
  
  public void setTextColor(int paramInt)
  {
    textColor = paramInt;
    paint.setColor(textColor);
  }
  
  public void setTextColorResource(int paramInt)
  {
    setTextColor(ContextCompat.getColor(m, paramInt));
  }
  
  public void setTextSize(float paramFloat)
  {
    textSize = paramFloat;
    paint.setTextSize(textSize);
  }
  
  public void setTextSize(int paramInt)
  {
    setTextSize(getResources().getDimension(paramInt));
  }
  
  public void setTypeface(int paramInt)
  {
    setNumber(paramInt, 0);
  }
  
  public void setTypeface(Typeface paramTypeface)
  {
    typeface = paramTypeface;
    paramTypeface = typeface;
    if (paramTypeface != null)
    {
      mInputText.setTypeface(paramTypeface);
      paint.setTypeface(typeface);
      return;
    }
    mInputText.setTypeface(Typeface.MONOSPACE);
    paint.setTypeface(Typeface.MONOSPACE);
  }
  
  public void setTypeface(String paramString)
  {
    setNumber(paramString, 0);
  }
  
  public void setValue(int paramInt)
  {
    setValueInternal(paramInt, false);
  }
  
  public void setWheelItemCount(int paramInt)
  {
    if (paramInt >= 1)
    {
      i = paramInt;
      int j = 3;
      if (paramInt < 3) {
        paramInt = j;
      }
      k = paramInt;
      paramInt = k;
      a = (paramInt / 2);
      mSelectorIndices = new int[paramInt];
      return;
    }
    throw new IllegalArgumentException("Wheel item count must be >= 1");
  }
  
  public void setWrapSelectorWheel(boolean paramBoolean)
  {
    int j;
    if (mMaxValue - mMinValue >= mSelectorIndices.length) {
      j = 1;
    } else {
      j = 0;
    }
    if (((!paramBoolean) || (j != 0)) && (paramBoolean != mWrapSelectorWheel)) {
      mWrapSelectorWheel = paramBoolean;
    }
  }
  
  class a
    implements NumberPicker.c
  {
    a(String paramString) {}
    
    public String format(int paramInt)
    {
      return String.format(Locale.getDefault(), paramString, new Object[] { Integer.valueOf(paramInt) });
    }
  }
  
  class b
    implements Runnable
  {
    private boolean mIncrement;
    
    b() {}
    
    private void setIncrement(boolean paramBoolean)
    {
      mIncrement = paramBoolean;
    }
    
    public void run()
    {
      NumberPicker.access$getChangeValueByOne(NumberPicker.this, mIncrement);
      NumberPicker localNumberPicker = NumberPicker.this;
      localNumberPicker.postDelayed(this, NumberPicker.access$getMLongPressUpdateInterval(localNumberPicker));
    }
  }
  
  public static abstract interface c
  {
    public abstract String format(int paramInt);
  }
  
  public static abstract interface d
  {
    public abstract void onScrollStateChange(NumberPicker paramNumberPicker, int paramInt);
  }
  
  public static abstract interface e
  {
    public abstract void onValueChange(NumberPicker paramNumberPicker, int paramInt1, int paramInt2);
  }
  
  class f
    implements Runnable
  {}
  
  private static class g
    implements NumberPicker.c
  {
    final Object[] mArgs = new Object[1];
    final StringBuilder mBuilder = new StringBuilder();
    Formatter mFmt;
    char mZeroDigit;
    
    g()
    {
      init(Locale.getDefault());
    }
    
    private Formatter createFormatter(Locale paramLocale)
    {
      return new Formatter(mBuilder, paramLocale);
    }
    
    private static char getZeroDigit(Locale paramLocale)
    {
      return new DecimalFormatSymbols(paramLocale).getZeroDigit();
    }
    
    private void init(Locale paramLocale)
    {
      mFmt = createFormatter(paramLocale);
      mZeroDigit = getZeroDigit(paramLocale);
    }
    
    public String format(int paramInt)
    {
      Object localObject = Locale.getDefault();
      if (mZeroDigit != getZeroDigit((Locale)localObject)) {
        init((Locale)localObject);
      }
      mArgs[0] = Integer.valueOf(paramInt);
      localObject = mBuilder;
      ((StringBuilder)localObject).delete(0, ((StringBuilder)localObject).length());
      mFmt.format("%02d", mArgs);
      return mFmt.toString();
    }
  }
}
