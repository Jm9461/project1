package android.support.v4.app;

import android.app.Notification;
import android.os.Build.VERSION;
import android.os.Bundle;

public class PlaybackStateCompat
{
  public static Bundle getExtras(Notification paramNotification)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 19) {
      return extras;
    }
    if (i >= 16) {
      return NotificationCompatJellybean.getExtras(paramNotification);
    }
    return null;
  }
}
