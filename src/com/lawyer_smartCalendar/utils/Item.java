package com.lawyer_smartCalendar.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;

public class Item
{
  public PendingIntent a;
  private Context c;
  private long d;
  private Intent g;
  private Calendar v;
  
  public Item(Context paramContext, Intent paramIntent, long paramLong1, long paramLong2)
  {
    c = paramContext;
    g = paramIntent;
    v = Calendar.getInstance();
    v.setTimeInMillis(paramLong1);
    d = paramLong2;
  }
  
  public Item a()
  {
    a = PendingIntent.getBroadcast(c, (int)d, g, 536870912);
    if (a != null) {
      ((AlarmManager)c.getSystemService("alarm")).cancel(a);
    }
    return this;
  }
  
  public Item set()
  {
    if (PendingIntent.getBroadcast(c, (int)d, g, 536870912) == null)
    {
      a = PendingIntent.getBroadcast(c, (int)d, g, 134217728);
      if (a != null) {
        ((AlarmManager)c.getSystemService("alarm")).set(0, v.getTimeInMillis(), a);
      }
    }
    return this;
  }
}
