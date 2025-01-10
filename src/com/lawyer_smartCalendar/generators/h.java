package com.lawyer_smartCalendar.generators;

import com.mohamadamin.persianmaterialdatetimepicker.views.Calendar;

public class h
{
  public static String get(Long paramLong)
  {
    Calendar localCalendar = new Calendar();
    localCalendar.setTimeInMillis(paramLong.longValue());
    return localCalendar.convert();
  }
}
