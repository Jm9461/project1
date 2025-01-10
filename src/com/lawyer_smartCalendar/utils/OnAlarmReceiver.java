package com.lawyer_smartCalendar.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class OnAlarmReceiver
  extends BroadcastReceiver
{
  public OnAlarmReceiver() {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str1 = paramIntent.getStringExtra("id");
    String str2 = paramIntent.getStringExtra("title");
    paramIntent = paramIntent.getStringExtra("message");
    NotificationHelper.showNotification(paramContext, Integer.parseInt(str1), str2, paramIntent);
  }
}
