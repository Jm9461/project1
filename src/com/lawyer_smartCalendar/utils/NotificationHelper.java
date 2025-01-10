package com.lawyer_smartCalendar.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.support.v4.app.ByteVector;
import android.support.v4.app.NotificationCompat.Builder;
import com.lawyer_smartCalendar.activity.ScheduleDetailActivity;

public class NotificationHelper
{
  public static void cancelNotification(Context paramContext, int paramInt)
  {
    ((NotificationManager)paramContext.getSystemService("notification")).cancel(paramInt);
  }
  
  public static void showNotification(Context paramContext, int paramInt, String paramString1, String paramString2)
  {
    Bitmap localBitmap = BitmapFactory.decodeResource(paramContext.getResources(), 2131230894);
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    if (Build.VERSION.SDK_INT >= 26)
    {
      localObject = new NotificationChannel("channel_id", "my_channel", 3);
      ((NotificationChannel)localObject).setDescription("channel description");
      localNotificationManager.createNotificationChannel((NotificationChannel)localObject);
    }
    Object localObject = new Intent(paramContext, ScheduleDetailActivity.class);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramInt);
    localStringBuilder.append("");
    ((Intent)localObject).putExtra("id", localStringBuilder.toString());
    localObject = PendingIntent.getActivity(paramContext, paramInt, (Intent)localObject, 134217728);
    paramContext = new NotificationCompat.Builder(paramContext, "channel_id");
    paramContext.setSmallIcon(2131230894);
    paramContext.setLargeIcon(localBitmap);
    paramContext.setContentTitle(paramString1);
    paramContext.setWhen(System.currentTimeMillis());
    paramContext.setContentText(paramString2);
    paramString1 = new ByteVector();
    paramString1.a(paramString2);
    paramContext.setStyle(paramString1);
    paramContext.setDefaults(-1);
    paramContext.setContentIntent((PendingIntent)localObject);
    localNotificationManager.notify(paramInt, paramContext.setPriority(0).build());
  }
}
