package android.support.v4.app;

import android.app.Notification.BigTextStyle;
import android.os.Build.VERSION;

public class ByteVector
  extends AppCompatDelegateImplV7
{
  private java.lang.CharSequence a;
  
  public ByteVector() {}
  
  public ByteVector a(java.lang.CharSequence paramCharSequence)
  {
    a = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
    return this;
  }
  
  public void get(CharSequence paramCharSequence)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramCharSequence = new Notification.BigTextStyle(paramCharSequence.getBuilder()).setBigContentTitle(k).bigText(a);
      if (s) {
        paramCharSequence.setSummaryText(r);
      }
    }
  }
}
