package com.mohamadamin.persianmaterialdatetimepicker.date;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import com.mohamadamin.persianmaterialdatetimepicker.views.Calendar;
import java.util.HashMap;

public abstract class MonthAdapter
  extends BaseAdapter
  implements MonthView.OnDayClickListener
{
  private final Context mContext;
  protected final DatePickerController mController;
  private CalendarDay mSelectedDay;
  
  public MonthAdapter(Context paramContext, DatePickerController paramDatePickerController)
  {
    mContext = paramContext;
    mController = paramDatePickerController;
    init();
    setSelectedDay(mController.getSelectedDay());
  }
  
  private boolean isSelectedDayInMonth(int paramInt1, int paramInt2)
  {
    CalendarDay localCalendarDay = mSelectedDay;
    return (year == paramInt1) && (month == paramInt2);
  }
  
  public abstract MonthView createMonthView(Context paramContext);
  
  public int getCount()
  {
    return (mController.getMaxYear() - mController.getMinYear() + 1) * 12;
  }
  
  public Object getItem(int paramInt)
  {
    return null;
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject = null;
    if (paramView != null)
    {
      paramViewGroup = (MonthView)paramView;
      paramView = (HashMap)paramViewGroup.getTag();
    }
    else
    {
      paramView = createMonthView(mContext);
      paramViewGroup = paramView;
      paramView.setLayoutParams(new AbsListView.LayoutParams(-1, -1));
      paramView.setClickable(true);
      paramView.setOnDayClickListener(this);
      paramView = (View)localObject;
    }
    localObject = paramView;
    if (paramView == null) {
      localObject = new HashMap();
    }
    ((HashMap)localObject).clear();
    int i = paramInt % 12;
    int j = paramInt / 12 + mController.getMinYear();
    paramInt = -1;
    if (isSelectedDayInMonth(j, i)) {
      paramInt = mSelectedDay.day;
    }
    paramViewGroup.reuse();
    ((HashMap)localObject).put("selected_day", Integer.valueOf(paramInt));
    ((HashMap)localObject).put("year", Integer.valueOf(j));
    ((HashMap)localObject).put("month", Integer.valueOf(i));
    ((HashMap)localObject).put("week_start", Integer.valueOf(mController.getFirstDayOfWeek()));
    paramViewGroup.setMonthParams((HashMap)localObject);
    paramViewGroup.invalidate();
    return paramViewGroup;
  }
  
  public boolean hasStableIds()
  {
    return true;
  }
  
  protected void init()
  {
    mSelectedDay = new CalendarDay(System.currentTimeMillis());
  }
  
  public void onDayClick(MonthView paramMonthView, CalendarDay paramCalendarDay)
  {
    if (paramCalendarDay != null) {
      onDayTapped(paramCalendarDay);
    }
  }
  
  protected void onDayTapped(CalendarDay paramCalendarDay)
  {
    mController.tryVibrate();
    mController.onDayOfMonthSelected(year, month, day);
    setSelectedDay(paramCalendarDay);
  }
  
  public void setSelectedDay(CalendarDay paramCalendarDay)
  {
    mSelectedDay = paramCalendarDay;
    notifyDataSetChanged();
  }
  
  public class CalendarDay
  {
    private Calendar calendar;
    int day;
    int month;
    int year;
    
    public CalendarDay()
    {
      setTime(System.currentTimeMillis());
    }
    
    public CalendarDay(int paramInt1, int paramInt2)
    {
      setDay(this$1, paramInt1, paramInt2);
    }
    
    public CalendarDay()
    {
      setTime(this$1);
    }
    
    public CalendarDay()
    {
      year = this$1.get();
      month = this$1.set();
      day = this$1.getTime();
    }
    
    private void setTime(long paramLong)
    {
      if (calendar == null) {
        calendar = new Calendar();
      }
      calendar.setTimeInMillis(paramLong);
      month = calendar.set();
      year = calendar.get();
      day = calendar.getTime();
    }
    
    public void set(CalendarDay paramCalendarDay)
    {
      year = year;
      month = month;
      day = day;
    }
    
    public void setDay(int paramInt1, int paramInt2, int paramInt3)
    {
      year = paramInt1;
      month = paramInt2;
      day = paramInt3;
    }
  }
}
