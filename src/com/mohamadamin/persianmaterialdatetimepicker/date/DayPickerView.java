package com.mohamadamin.persianmaterialdatetimepicker.date;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.view.accessibility.AccessibilityRecord;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ListView;
import com.mohamadamin.persianmaterialdatetimepicker.Utils;
import com.mohamadamin.persianmaterialdatetimepicker.views.Calendar;

public abstract class DayPickerView
  extends ListView
  implements AbsListView.OnScrollListener, DatePickerDialog.OnDateChangedListener
{
  public static int LIST_TOP_OFFSET = -1;
  protected MonthAdapter mAdapter;
  private DatePickerController mController;
  protected int mCurrentScrollState = 0;
  protected float mFriction = 1.0F;
  protected Handler mHandler;
  private boolean mPerformingScroll;
  protected int mPreviousScrollState = 0;
  protected ScrollStateRunnable mScrollStateChangedRunnable = new ScrollStateRunnable();
  protected MonthAdapter.CalendarDay mSelectedDay = new MonthAdapter.CalendarDay();
  protected MonthAdapter.CalendarDay mTempDay = new MonthAdapter.CalendarDay();
  
  public DayPickerView(Context paramContext, DatePickerController paramDatePickerController)
  {
    super(paramContext);
    init(paramContext);
    setController(paramDatePickerController);
  }
  
  private MonthAdapter.CalendarDay findAccessibilityFocus()
  {
    int j = getChildCount();
    int i = 0;
    MonthAdapter.CalendarDay localCalendarDay;
    while (i < j)
    {
      android.view.View localView = getChildAt(i);
      if ((localView instanceof MonthView))
      {
        localCalendarDay = ((MonthView)localView).getAccessibilityFocus();
        if (localCalendarDay != null)
        {
          if (Build.VERSION.SDK_INT != 17) {
            break label66;
          }
          ((MonthView)localView).clearAccessibilityFocus();
          return localCalendarDay;
        }
      }
      i += 1;
    }
    return null;
    label66:
    return localCalendarDay;
  }
  
  private static String getMonthAndYearString(MonthAdapter.CalendarDay paramCalendarDay)
  {
    Calendar localCalendar = new Calendar();
    localCalendar.set(year, month, day);
    paramCalendarDay = new StringBuilder();
    paramCalendarDay.append("");
    paramCalendarDay.append(localCalendar.getDisplayName());
    paramCalendarDay = paramCalendarDay.toString();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramCalendarDay);
    localStringBuilder.append(" ");
    paramCalendarDay = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramCalendarDay);
    localStringBuilder.append(localCalendar.get());
    return localStringBuilder.toString();
  }
  
  private boolean restoreAccessibilityFocus(MonthAdapter.CalendarDay paramCalendarDay)
  {
    if (paramCalendarDay == null) {
      return false;
    }
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      android.view.View localView = getChildAt(i);
      if (((localView instanceof MonthView)) && (((MonthView)localView).restoreAccessibilityFocus(paramCalendarDay))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public abstract MonthAdapter createMonthAdapter(Context paramContext, DatePickerController paramDatePickerController);
  
  public int getMostVisiblePosition()
  {
    int i2 = getFirstVisiblePosition();
    int i3 = getHeight();
    int k = 0;
    int m = 0;
    int i = 0;
    int j = 0;
    while (j < i3)
    {
      android.view.View localView = getChildAt(i);
      if (localView == null) {
        break;
      }
      int n = localView.getBottom();
      j = n;
      int i1 = Math.min(n, i3) - Math.max(0, localView.getTop());
      n = k;
      if (i1 > k)
      {
        m = i;
        n = i1;
      }
      i += 1;
      k = n;
    }
    return i2 + m;
  }
  
  public boolean goTo(MonthAdapter.CalendarDay paramCalendarDay, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (paramBoolean2) {
      mSelectedDay.set(paramCalendarDay);
    }
    mTempDay.set(paramCalendarDay);
    int k = (year - mController.getMinYear()) * 12 + month;
    int j;
    for (int i = 0;; i = j)
    {
      j = i + 1;
      paramCalendarDay = getChildAt(i);
      if (paramCalendarDay != null)
      {
        i = paramCalendarDay.getTop();
        if (Log.isLoggable("MonthFragment", 3))
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("child at ");
          localStringBuilder.append(j - 1);
          localStringBuilder.append(" has top ");
          localStringBuilder.append(i);
          Log.d("MonthFragment", localStringBuilder.toString());
        }
        if (i < 0) {}
      }
      else
      {
        if (paramCalendarDay != null) {
          i = getPositionForView(paramCalendarDay);
        } else {
          i = 0;
        }
        if (paramBoolean2) {
          mAdapter.setSelectedDay(mSelectedDay);
        }
        if (Log.isLoggable("MonthFragment", 3))
        {
          paramCalendarDay = new StringBuilder();
          paramCalendarDay.append("GoTo position ");
          paramCalendarDay.append(k);
          Log.d("MonthFragment", paramCalendarDay.toString());
        }
        if ((k == i) && (!paramBoolean3))
        {
          if (paramBoolean2) {
            setMonthDisplayed(mSelectedDay);
          }
        }
        else
        {
          setMonthDisplayed(mTempDay);
          mPreviousScrollState = 2;
          if (paramBoolean1)
          {
            smoothScrollToPositionFromTop(k, LIST_TOP_OFFSET, 250);
            return true;
          }
          postSetSelection(k);
        }
        return false;
      }
    }
  }
  
  public void init(Context paramContext)
  {
    mHandler = new Handler();
    setLayoutParams(new AbsListView.LayoutParams(-1, -1));
    setDrawSelectorOnTop(false);
    setUpListView();
  }
  
  protected void layoutChildren()
  {
    MonthAdapter.CalendarDay localCalendarDay = findAccessibilityFocus();
    super.layoutChildren();
    if (mPerformingScroll)
    {
      mPerformingScroll = false;
      return;
    }
    restoreAccessibilityFocus(localCalendarDay);
  }
  
  public void onDateChanged()
  {
    goTo(mController.getSelectedDay(), false, true, true);
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setItemCount(-1);
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
  
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    MonthView localMonthView = (MonthView)paramAbsListView.getChildAt(0);
    if (localMonthView == null) {
      return;
    }
    long l = paramAbsListView.getFirstVisiblePosition() * localMonthView.getHeight() - localMonthView.getBottom();
    mPreviousScrollState = mCurrentScrollState;
  }
  
  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    mScrollStateChangedRunnable.doScrollStateChange(paramAbsListView, paramInt);
  }
  
  public boolean performAccessibilityAction(int paramInt, Bundle paramBundle)
  {
    if ((paramInt != 4096) && (paramInt != 8192)) {
      return super.performAccessibilityAction(paramInt, paramBundle);
    }
    int i = getFirstVisiblePosition();
    paramBundle = new MonthAdapter.CalendarDay(i / 12 + mController.getMinYear(), i % 12, 1);
    if (paramInt == 4096)
    {
      month += 1;
      if (month == 12)
      {
        month = 0;
        year += 1;
      }
    }
    else if (paramInt == 8192)
    {
      android.view.View localView = getChildAt(0);
      if ((localView != null) && (localView.getTop() >= -1))
      {
        month -= 1;
        if (month == -1)
        {
          month = 11;
          year -= 1;
        }
      }
    }
    Utils.tryAccessibilityAnnounce(this, com.mohamadamin.persianmaterialdatetimepicker.views.View.format(getMonthAndYearString(paramBundle)));
    goTo(paramBundle, true, false, true);
    mPerformingScroll = true;
    return true;
  }
  
  public void postSetSelection(int paramInt)
  {
    clearFocus();
    post(new Futures.CombinedFuture.2(this, paramInt));
    onScrollStateChanged(this, 0);
  }
  
  protected void refreshAdapter()
  {
    MonthAdapter localMonthAdapter = mAdapter;
    if (localMonthAdapter == null) {
      mAdapter = createMonthAdapter(getContext(), mController);
    } else {
      localMonthAdapter.setSelectedDay(mSelectedDay);
    }
    setAdapter(mAdapter);
  }
  
  public void setController(DatePickerController paramDatePickerController)
  {
    mController = paramDatePickerController;
    mController.registerOnDateChangedListener(this);
    refreshAdapter();
    onDateChanged();
  }
  
  protected void setMonthDisplayed(MonthAdapter.CalendarDay paramCalendarDay)
  {
    int i = month;
    invalidateViews();
  }
  
  protected void setUpListView()
  {
    setCacheColorHint(0);
    setDivider(null);
    setItemsCanFocus(true);
    setFastScrollEnabled(false);
    setVerticalScrollBarEnabled(false);
    setOnScrollListener(this);
    setFadingEdgeLength(0);
    setFriction(ViewConfiguration.getScrollFriction() * mFriction);
  }
  
  public class ScrollStateRunnable
    implements Runnable
  {
    private int mNewState;
    
    protected ScrollStateRunnable() {}
    
    public void doScrollStateChange(AbsListView paramAbsListView, int paramInt)
    {
      mHandler.removeCallbacks(this);
      mNewState = paramInt;
      mHandler.postDelayed(this, 40L);
    }
    
    public void run()
    {
      mCurrentScrollState = mNewState;
      Object localObject;
      if (Log.isLoggable("MonthFragment", 3))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("new scroll state: ");
        ((StringBuilder)localObject).append(mNewState);
        ((StringBuilder)localObject).append(" old state: ");
        ((StringBuilder)localObject).append(mPreviousScrollState);
        Log.d("MonthFragment", ((StringBuilder)localObject).toString());
      }
      int i = mNewState;
      if (i == 0)
      {
        localObject = DayPickerView.this;
        int k = mPreviousScrollState;
        if (k != 0)
        {
          int j = 1;
          if (k != 1)
          {
            mPreviousScrollState = i;
            i = 0;
            for (localObject = ((ViewGroup)localObject).getChildAt(0); (localObject != null) && (((android.view.View)localObject).getBottom() <= 0); localObject = ((ViewGroup)localObject).getChildAt(i))
            {
              localObject = DayPickerView.this;
              i += 1;
            }
            if (localObject == null) {
              return;
            }
            i = getFirstVisiblePosition();
            k = getLastVisiblePosition();
            if ((i != 0) && (k != getCount() - 1)) {
              i = j;
            } else {
              i = 0;
            }
            j = ((android.view.View)localObject).getTop();
            k = ((android.view.View)localObject).getBottom();
            int m = getHeight() / 2;
            if ((i != 0) && (j < DayPickerView.LIST_TOP_OFFSET))
            {
              if (k > m)
              {
                smoothScrollBy(j, 250);
                return;
              }
              smoothScrollBy(k, 250);
            }
            return;
          }
        }
      }
      mPreviousScrollState = mNewState;
    }
  }
}
