package android.support.v4.app;

import android.os.Bundle;
import android.widget.RemoteViews;

public abstract class AppCompatDelegateImplV7
{
  protected NotificationCompat.Builder j;
  java.lang.CharSequence k;
  java.lang.CharSequence r;
  boolean s = false;
  
  public AppCompatDelegateImplV7() {}
  
  public void a(NotificationCompat.Builder paramBuilder)
  {
    if (j != paramBuilder)
    {
      j = paramBuilder;
      paramBuilder = j;
      if (paramBuilder != null) {
        paramBuilder.setStyle(this);
      }
    }
  }
  
  public RemoteViews create(CharSequence paramCharSequence)
  {
    return null;
  }
  
  public RemoteViews d(CharSequence paramCharSequence)
  {
    return null;
  }
  
  public void d(Bundle paramBundle) {}
  
  public abstract void get(CharSequence paramCharSequence);
  
  public RemoteViews i(CharSequence paramCharSequence)
  {
    return null;
  }
}
