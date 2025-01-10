package com.mohamadamin.persianmaterialdatetimepicker.date;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.AccessibilityDelegate;
import android.view.View.MeasureSpec;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;
import com.mohamadamin.persianmaterialdatetimepicker.R.color;
import com.mohamadamin.persianmaterialdatetimepicker.R.dimen;
import com.mohamadamin.persianmaterialdatetimepicker.R.string;
import com.mohamadamin.persianmaterialdatetimepicker.TypefaceHelper;
import com.mohamadamin.persianmaterialdatetimepicker.Utils;
import com.mohamadamin.persianmaterialdatetimepicker.views.Calendar;
import java.security.InvalidParameterException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public abstract class MonthView
  extends android.view.View
{
  protected static int DAY_SELECTED_CIRCLE_SIZE;
  protected static int DAY_SEPARATOR_WIDTH = 1;
  protected static int DEFAULT_HEIGHT = 32;
  protected static int MINI_DAY_NUMBER_TEXT_SIZE;
  protected static int MIN_HEIGHT = 10;
  protected static int MONTH_DAY_LABEL_TEXT_SIZE;
  protected static int MONTH_HEADER_SIZE;
  protected static int MONTH_LABEL_TEXT_SIZE;
  private final Calendar mCalendar;
  protected DatePickerController mController;
  protected final Calendar mDayLabelCalendar;
  private int mDayOfWeekStart;
  protected int mDayTextColor;
  protected int mDisabledDayTextColor;
  protected int mEdgePadding;
  protected boolean mHasToday;
  protected int mHighlightedDayTextColor;
  private boolean mLockAccessibilityDelegate;
  protected int mMonth;
  protected Paint mMonthDayLabelPaint;
  protected int mMonthDayTextColor;
  protected Paint mMonthNumPaint;
  protected Paint mMonthTitlePaint;
  private String mMonthTitleTypeface;
  protected int mNumCells;
  protected int mNumDays;
  protected int mNumRows;
  protected OnDayClickListener mOnDayClickListener;
  protected int mRowHeight;
  protected Paint mSelectedCirclePaint;
  protected int mSelectedDay;
  protected int mSelectedDayTextColor;
  private final StringBuilder mStringBuilder;
  protected int mToday;
  protected int mTodayNumberColor;
  private final MonthViewTouchHelper mTouchHelper;
  protected int mWeekStart;
  protected int mWidth;
  protected int mYear;
  
  public MonthView(Context paramContext, AttributeSet paramAttributeSet, DatePickerController paramDatePickerController)
  {
    super(paramContext, paramAttributeSet);
    int j = 0;
    mEdgePadding = 0;
    mRowHeight = DEFAULT_HEIGHT;
    mHasToday = false;
    mSelectedDay = -1;
    mToday = -1;
    mWeekStart = 7;
    mNumDays = 7;
    mNumCells = mNumDays;
    mNumRows = 6;
    mDayOfWeekStart = 0;
    mController = paramDatePickerController;
    paramContext = paramContext.getResources();
    mDayLabelCalendar = new Calendar();
    mCalendar = new Calendar();
    paramContext.getString(R.string.mdtp_day_of_week_label_typeface);
    mMonthTitleTypeface = paramContext.getString(R.string.mdtp_sans_serif);
    paramAttributeSet = mController;
    int i = j;
    if (paramAttributeSet != null)
    {
      i = j;
      if (paramAttributeSet.isThemeDark()) {
        i = 1;
      }
    }
    if (i != 0)
    {
      mDayTextColor = paramContext.getColor(R.color.mdtp_date_picker_text_normal_dark_theme);
      mMonthDayTextColor = paramContext.getColor(R.color.mdtp_date_picker_month_day_dark_theme);
      mDisabledDayTextColor = paramContext.getColor(R.color.mdtp_date_picker_text_disabled_dark_theme);
      mHighlightedDayTextColor = paramContext.getColor(R.color.mdtp_date_picker_text_highlighted_dark_theme);
    }
    else
    {
      mDayTextColor = paramContext.getColor(R.color.mdtp_date_picker_text_normal);
      mMonthDayTextColor = paramContext.getColor(R.color.mdtp_date_picker_month_day);
      mDisabledDayTextColor = paramContext.getColor(R.color.mdtp_date_picker_text_disabled);
      mHighlightedDayTextColor = paramContext.getColor(R.color.mdtp_date_picker_text_highlighted);
    }
    mSelectedDayTextColor = paramContext.getColor(R.color.mdtp_white);
    mTodayNumberColor = paramContext.getColor(R.color.mdtp_accent_color);
    paramContext.getColor(R.color.mdtp_white);
    mStringBuilder = new StringBuilder(50);
    MINI_DAY_NUMBER_TEXT_SIZE = paramContext.getDimensionPixelSize(R.dimen.mdtp_day_number_size);
    MONTH_LABEL_TEXT_SIZE = paramContext.getDimensionPixelSize(R.dimen.mdtp_month_label_size);
    MONTH_DAY_LABEL_TEXT_SIZE = paramContext.getDimensionPixelSize(R.dimen.mdtp_month_day_label_text_size);
    MONTH_HEADER_SIZE = paramContext.getDimensionPixelOffset(R.dimen.mdtp_month_list_item_header_height);
    DAY_SELECTED_CIRCLE_SIZE = paramContext.getDimensionPixelSize(R.dimen.mdtp_day_number_select_circle_radius);
    mRowHeight = ((paramContext.getDimensionPixelOffset(R.dimen.mdtp_date_picker_view_animator_height) - getMonthHeaderSize()) / 6);
    mTouchHelper = getMonthViewTouchHelper();
    ViewCompat.setAccessibilityDelegate(this, mTouchHelper);
    ViewCompat.add(this, 1);
    mLockAccessibilityDelegate = true;
    initView();
  }
  
  private boolean add(int paramInt1, int paramInt2, int paramInt3)
  {
    Calendar[] arrayOfCalendar = mController.getEndDate();
    int j = arrayOfCalendar.length;
    int i = 0;
    while (i < j)
    {
      Calendar localCalendar = arrayOfCalendar[i];
      if (paramInt1 >= localCalendar.get())
      {
        if (paramInt1 <= localCalendar.get())
        {
          if (paramInt2 < localCalendar.set()) {
            break;
          }
          if (paramInt2 <= localCalendar.set())
          {
            if (paramInt3 < localCalendar.getTime()) {
              break;
            }
            if (paramInt3 <= localCalendar.getTime()) {
              break label111;
            }
          }
        }
        i += 1;
        continue;
        label111:
        return true;
      }
    }
    return false;
  }
  
  private int calculateNumRows()
  {
    int i = findDayOffset();
    int k = mNumCells;
    int m = mNumDays;
    int j = (i + k) / m;
    if ((k + i) % m > 0) {
      i = 1;
    } else {
      i = 0;
    }
    return i + j;
  }
  
  private String getMonthAndYearString()
  {
    mStringBuilder.setLength(0);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(mCalendar.getDisplayName());
    localStringBuilder.append(" ");
    localStringBuilder.append(mCalendar.get());
    return com.mohamadamin.persianmaterialdatetimepicker.views.View.format(localStringBuilder.toString());
  }
  
  private boolean isAfterMax(int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject = mController;
    if (localObject == null) {
      return false;
    }
    localObject = ((DatePickerController)localObject).getMaxDate();
    if (localObject == null) {
      return false;
    }
    if (paramInt1 > ((Calendar)localObject).get()) {
      return true;
    }
    if (paramInt1 < ((Calendar)localObject).get()) {
      return false;
    }
    if (paramInt2 > ((Calendar)localObject).set()) {
      return true;
    }
    if (paramInt2 < ((Calendar)localObject).set()) {
      return false;
    }
    return paramInt3 > ((Calendar)localObject).set();
  }
  
  private boolean isBeforeMin(int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject = mController;
    if (localObject == null) {
      return false;
    }
    localObject = ((DatePickerController)localObject).getStartDate();
    if (localObject == null) {
      return false;
    }
    if (paramInt1 < ((Calendar)localObject).get()) {
      return true;
    }
    if (paramInt1 > ((Calendar)localObject).get()) {
      return false;
    }
    if (paramInt2 < ((Calendar)localObject).set()) {
      return true;
    }
    if (paramInt2 > ((Calendar)localObject).set()) {
      return false;
    }
    return paramInt3 < ((Calendar)localObject).getTime();
  }
  
  private void onDayClick(int paramInt)
  {
    if (isOutOfRange(mYear, mMonth, paramInt)) {
      return;
    }
    OnDayClickListener localOnDayClickListener = mOnDayClickListener;
    if (localOnDayClickListener != null) {
      localOnDayClickListener.onDayClick(this, new MonthAdapter.CalendarDay(mYear, mMonth, paramInt));
    }
    mTouchHelper.sendEventForVirtualView(paramInt, 1);
  }
  
  private boolean sameDay(int paramInt, Calendar paramCalendar)
  {
    return (mYear == paramCalendar.get()) && (mMonth == paramCalendar.set()) && (paramInt == paramCalendar.getTime());
  }
  
  public void clearAccessibilityFocus()
  {
    mTouchHelper.clearFocusedVirtualView();
  }
  
  public boolean dispatchHoverEvent(MotionEvent paramMotionEvent)
  {
    if (mTouchHelper.dispatchHoverEvent(paramMotionEvent)) {
      return true;
    }
    return super.dispatchHoverEvent(paramMotionEvent);
  }
  
  public abstract void drawMonthDay(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9);
  
  protected void drawMonthDayLabels(Canvas paramCanvas)
  {
    int j = getMonthHeaderSize();
    int k = MONTH_DAY_LABEL_TEXT_SIZE / 2;
    int m = (mWidth - mEdgePadding * 2) / (mNumDays * 2);
    int i = 0;
    for (;;)
    {
      int n = mNumDays;
      if (i >= n) {
        break;
      }
      int i1 = mWeekStart;
      int i2 = mEdgePadding;
      mDayLabelCalendar.set(7, (i1 + i) % n);
      paramCanvas.drawText(mDayLabelCalendar.getValue().substring(0, 1), (i * 2 + 1) * m + i2, j - k, mMonthDayLabelPaint);
      i += 1;
    }
  }
  
  protected void drawMonthNums(Canvas paramCanvas)
  {
    int k = (mRowHeight + MINI_DAY_NUMBER_TEXT_SIZE) / 2 - DAY_SEPARATOR_WIDTH + getMonthHeaderSize();
    float f = (mWidth - mEdgePadding * 2) / (mNumDays * 2.0F);
    int i = findDayOffset();
    int j = 1;
    while (j <= mNumCells)
    {
      int m = (int)((i * 2 + 1) * f + mEdgePadding);
      int n = mRowHeight;
      int i3 = (MINI_DAY_NUMBER_TEXT_SIZE + n) / 2;
      int i4 = DAY_SEPARATOR_WIDTH;
      int i1 = (int)(m - f);
      int i2 = (int)(m + f);
      i3 = k - (i3 - i4);
      drawMonthDay(paramCanvas, mYear, mMonth, j, m, k, i1, i2, i3, i3 + n);
      n = i + 1;
      m = k;
      i = n;
      if (n == mNumDays)
      {
        m = k + mRowHeight;
        i = 0;
      }
      j += 1;
      k = m;
    }
  }
  
  protected void drawMonthTitle(Canvas paramCanvas)
  {
    int i = (mWidth + mEdgePadding * 2) / 2;
    int j = (getMonthHeaderSize() - MONTH_DAY_LABEL_TEXT_SIZE) / 2;
    paramCanvas.drawText(getMonthAndYearString(), i, j, mMonthTitlePaint);
  }
  
  protected int findDayOffset()
  {
    int j = mDayOfWeekStart;
    int i = j;
    if (j < mWeekStart) {
      i = j + mNumDays;
    }
    return i - mWeekStart;
  }
  
  public MonthAdapter.CalendarDay getAccessibilityFocus()
  {
    int i = mTouchHelper.getFocusedVirtualView();
    if (i >= 0) {
      return new MonthAdapter.CalendarDay(mYear, mMonth, i);
    }
    return null;
  }
  
  public int getDayFromLocation(float paramFloat1, float paramFloat2)
  {
    int i = getInternalDayFromLocation(paramFloat1, paramFloat2);
    if ((i >= 1) && (i <= mNumCells)) {
      return i;
    }
    return -1;
  }
  
  protected int getInternalDayFromLocation(float paramFloat1, float paramFloat2)
  {
    int i = mEdgePadding;
    if ((paramFloat1 >= i) && (paramFloat1 <= mWidth - mEdgePadding))
    {
      int j = (int)(paramFloat2 - getMonthHeaderSize()) / mRowHeight;
      return (int)((paramFloat1 - i) * mNumDays / (mWidth - i - mEdgePadding)) - findDayOffset() + 1 + mNumDays * j;
    }
    return -1;
  }
  
  public int getMonth()
  {
    return mMonth;
  }
  
  protected int getMonthHeaderSize()
  {
    return MONTH_HEADER_SIZE;
  }
  
  protected MonthViewTouchHelper getMonthViewTouchHelper()
  {
    return new MonthViewTouchHelper(this);
  }
  
  public int getYear()
  {
    return mYear;
  }
  
  protected void initView()
  {
    mMonthTitlePaint = new Paint();
    mMonthTitlePaint.setFakeBoldText(true);
    mMonthTitlePaint.setAntiAlias(true);
    mMonthTitlePaint.setTextSize(MONTH_LABEL_TEXT_SIZE);
    mMonthTitlePaint.setTypeface(Typeface.create(mMonthTitleTypeface, 1));
    mMonthTitlePaint.setColor(mDayTextColor);
    mMonthTitlePaint.setTextAlign(Paint.Align.CENTER);
    mMonthTitlePaint.setStyle(Paint.Style.FILL);
    mSelectedCirclePaint = new Paint();
    mSelectedCirclePaint.setFakeBoldText(true);
    mSelectedCirclePaint.setAntiAlias(true);
    mSelectedCirclePaint.setColor(mTodayNumberColor);
    mSelectedCirclePaint.setTextAlign(Paint.Align.CENTER);
    mSelectedCirclePaint.setStyle(Paint.Style.FILL);
    mSelectedCirclePaint.setAlpha(255);
    mMonthDayLabelPaint = new Paint();
    mMonthDayLabelPaint.setAntiAlias(true);
    mMonthDayLabelPaint.setTextSize(MONTH_DAY_LABEL_TEXT_SIZE);
    mMonthDayLabelPaint.setColor(mMonthDayTextColor);
    mMonthDayLabelPaint.setTypeface(TypefaceHelper.get(getContext(), "Roboto-Medium"));
    mMonthDayLabelPaint.setStyle(Paint.Style.FILL);
    mMonthDayLabelPaint.setTextAlign(Paint.Align.CENTER);
    mMonthDayLabelPaint.setFakeBoldText(true);
    mMonthNumPaint = new Paint();
    mMonthNumPaint.setAntiAlias(true);
    mMonthNumPaint.setTextSize(MINI_DAY_NUMBER_TEXT_SIZE);
    mMonthNumPaint.setStyle(Paint.Style.FILL);
    mMonthNumPaint.setTextAlign(Paint.Align.CENTER);
    mMonthNumPaint.setFakeBoldText(false);
  }
  
  protected boolean isHighlighted(int paramInt1, int paramInt2, int paramInt3)
  {
    Calendar[] arrayOfCalendar = mController.getHighlightedDays();
    if (arrayOfCalendar == null) {
      return false;
    }
    int j = arrayOfCalendar.length;
    int i = 0;
    while (i < j)
    {
      Calendar localCalendar = arrayOfCalendar[i];
      if (paramInt1 < localCalendar.get()) {
        return false;
      }
      if (paramInt1 <= localCalendar.get())
      {
        if (paramInt2 < localCalendar.set()) {
          return false;
        }
        if (paramInt2 <= localCalendar.set())
        {
          if (paramInt3 < localCalendar.getTime()) {
            return false;
          }
          if (paramInt3 <= localCalendar.getTime()) {
            break label115;
          }
        }
      }
      i += 1;
      continue;
      label115:
      return true;
    }
    return false;
  }
  
  protected boolean isOutOfRange(int paramInt1, int paramInt2, int paramInt3)
  {
    if (mController.getEndDate() != null) {
      return add(paramInt1, paramInt2, paramInt3) ^ true;
    }
    if (isBeforeMin(paramInt1, paramInt2, paramInt3)) {
      return true;
    }
    return isAfterMax(paramInt1, paramInt2, paramInt3);
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    drawMonthTitle(paramCanvas);
    drawMonthDayLabels(paramCanvas);
    drawMonthNums(paramCanvas);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), mRowHeight * mNumRows + getMonthHeaderSize() + 5);
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mWidth = paramInt1;
    mTouchHelper.invalidateRoot();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() != 1) {
      return true;
    }
    int i = getDayFromLocation(paramMotionEvent.getX(), paramMotionEvent.getY());
    if (i >= 0) {
      onDayClick(i);
    }
    return true;
  }
  
  public boolean restoreAccessibilityFocus(MonthAdapter.CalendarDay paramCalendarDay)
  {
    if ((year == mYear) && (month == mMonth))
    {
      int i = day;
      if (i <= mNumCells)
      {
        mTouchHelper.setFocusedVirtualView(i);
        return true;
      }
    }
    return false;
  }
  
  public void reuse()
  {
    mNumRows = 6;
    requestLayout();
  }
  
  public void setAccessibilityDelegate(View.AccessibilityDelegate paramAccessibilityDelegate)
  {
    if (!mLockAccessibilityDelegate) {
      super.setAccessibilityDelegate(paramAccessibilityDelegate);
    }
  }
  
  public void setDatePickerController(DatePickerController paramDatePickerController)
  {
    mController = paramDatePickerController;
  }
  
  public void setMonthParams(HashMap paramHashMap)
  {
    if ((!paramHashMap.containsKey("month")) && (!paramHashMap.containsKey("year"))) {
      throw new InvalidParameterException("You must specify month and year for this view");
    }
    setTag(paramHashMap);
    int j;
    if (paramHashMap.containsKey("height"))
    {
      mRowHeight = ((Integer)paramHashMap.get("height")).intValue();
      i = mRowHeight;
      j = MIN_HEIGHT;
      if (i < j) {
        mRowHeight = j;
      }
    }
    if (paramHashMap.containsKey("selected_day")) {
      mSelectedDay = ((Integer)paramHashMap.get("selected_day")).intValue();
    }
    mMonth = ((Integer)paramHashMap.get("month")).intValue();
    mYear = ((Integer)paramHashMap.get("year")).intValue();
    Calendar localCalendar = new Calendar();
    mHasToday = false;
    mToday = -1;
    mCalendar.set(mYear, mMonth, 1);
    mDayOfWeekStart = mCalendar.get(7);
    if (paramHashMap.containsKey("week_start")) {
      mWeekStart = ((Integer)paramHashMap.get("week_start")).intValue();
    } else {
      mWeekStart = 7;
    }
    mNumCells = Utils.getDaysInMonth(mMonth, mYear);
    int i = 0;
    while (i < mNumCells)
    {
      j = i + 1;
      if (sameDay(j, localCalendar))
      {
        mHasToday = true;
        mToday = j;
      }
      i += 1;
    }
    mNumRows = calculateNumRows();
    mTouchHelper.invalidateRoot();
  }
  
  public void setOnDayClickListener(OnDayClickListener paramOnDayClickListener)
  {
    mOnDayClickListener = paramOnDayClickListener;
  }
  
  public void setSelectedDay(int paramInt)
  {
    mSelectedDay = paramInt;
  }
  
  public class MonthViewTouchHelper
    extends ExploreByTouchHelper
  {
    private final Calendar mTempCalendar = new Calendar();
    private final Rect mTempRect = new Rect();
    
    public MonthViewTouchHelper(android.view.View paramView)
    {
      super();
    }
    
    public void clearFocusedVirtualView()
    {
      int i = getFocusedVirtualView();
      if (i != Integer.MIN_VALUE) {
        getAccessibilityNodeProvider(MonthView.this).performAction(i, 128, null);
      }
    }
    
    protected void getItemBounds(int paramInt, Rect paramRect)
    {
      MonthView localMonthView = MonthView.this;
      int m = mEdgePadding;
      int k = localMonthView.getMonthHeaderSize();
      localMonthView = MonthView.this;
      int i = mRowHeight;
      int j = (mWidth - mEdgePadding * 2) / mNumDays;
      int n = paramInt - 1 + localMonthView.findDayOffset();
      int i1 = mNumDays;
      paramInt = n / i1;
      m = n % i1 * j + m;
      paramInt = paramInt * i + k;
      paramRect.set(m, paramInt, m + j, paramInt + i);
    }
    
    protected CharSequence getItemDescription(int paramInt)
    {
      Object localObject1 = mTempCalendar;
      Object localObject2 = MonthView.this;
      ((Calendar)localObject1).set(mYear, mMonth, paramInt);
      localObject2 = com.mohamadamin.persianmaterialdatetimepicker.views.View.format(mTempCalendar.format());
      MonthView localMonthView = MonthView.this;
      localObject1 = localObject2;
      if (paramInt == mSelectedDay) {
        localObject1 = localMonthView.getContext().getString(R.string.mdtp_item_is_selected, new Object[] { localObject2 });
      }
      return localObject1;
    }
    
    protected int getVirtualViewAt(float paramFloat1, float paramFloat2)
    {
      int i = getDayFromLocation(paramFloat1, paramFloat2);
      if (i >= 0) {
        return i;
      }
      return Integer.MIN_VALUE;
    }
    
    protected void getVisibleVirtualViews(List paramList)
    {
      int i = 1;
      while (i <= mNumCells)
      {
        paramList.add(Integer.valueOf(i));
        i += 1;
      }
    }
    
    protected boolean onPerformActionForVirtualView(int paramInt1, int paramInt2, Bundle paramBundle)
    {
      if (paramInt2 != 16) {
        return false;
      }
      MonthView.access$getOnDayClick(MonthView.this, paramInt1);
      return true;
    }
    
    protected void onPopulateEventForVirtualView(int paramInt, AccessibilityEvent paramAccessibilityEvent)
    {
      paramAccessibilityEvent.setContentDescription(getItemDescription(paramInt));
    }
    
    protected void onPopulateNodeForVirtualView(int paramInt, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      getItemBounds(paramInt, mTempRect);
      paramAccessibilityNodeInfoCompat.setContentDescription(getItemDescription(paramInt));
      paramAccessibilityNodeInfoCompat.setBoundsInParent(mTempRect);
      paramAccessibilityNodeInfoCompat.addAction(16);
      if (paramInt == mSelectedDay) {
        paramAccessibilityNodeInfoCompat.setSelected(true);
      }
    }
    
    public void setFocusedVirtualView(int paramInt)
    {
      getAccessibilityNodeProvider(MonthView.this).performAction(paramInt, 64, null);
    }
  }
  
  public abstract interface OnDayClickListener
  {
    public abstract void onDayClick(MonthView paramMonthView, MonthAdapter.CalendarDay paramCalendarDay);
  }
}
