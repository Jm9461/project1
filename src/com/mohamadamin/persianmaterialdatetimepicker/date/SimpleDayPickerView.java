package com.mohamadamin.persianmaterialdatetimepicker.date;

import android.content.Context;

public class SimpleDayPickerView
  extends DayPickerView
{
  public SimpleDayPickerView(Context paramContext, DatePickerController paramDatePickerController)
  {
    super(paramContext, paramDatePickerController);
  }
  
  public MonthAdapter createMonthAdapter(Context paramContext, DatePickerController paramDatePickerController)
  {
    return new SimpleMonthAdapter(paramContext, paramDatePickerController);
  }
}
