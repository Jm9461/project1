package com.mohamadamin.persianmaterialdatetimepicker.date;

import com.mohamadamin.persianmaterialdatetimepicker.views.Calendar;

public abstract interface DatePickerController
{
  public abstract Calendar[] getEndDate();
  
  public abstract int getFirstDayOfWeek();
  
  public abstract Calendar[] getHighlightedDays();
  
  public abstract Calendar getMaxDate();
  
  public abstract int getMaxYear();
  
  public abstract int getMinYear();
  
  public abstract MonthAdapter.CalendarDay getSelectedDay();
  
  public abstract Calendar getStartDate();
  
  public abstract boolean isThemeDark();
  
  public abstract void onDayOfMonthSelected(int paramInt1, int paramInt2, int paramInt3);
  
  public abstract void onYearSelected(int paramInt);
  
  public abstract void registerOnDateChangedListener(DatePickerDialog.OnDateChangedListener paramOnDateChangedListener);
  
  public abstract void tryVibrate();
}
