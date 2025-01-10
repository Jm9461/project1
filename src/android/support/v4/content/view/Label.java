package android.support.v4.content.view;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;

public abstract class Label
{
  public Label() {}
  
  public abstract void a(int paramInt);
  
  public final void a(int paramInt, Handler paramHandler)
  {
    Handler localHandler = paramHandler;
    if (paramHandler == null) {
      localHandler = new Handler(Looper.getMainLooper());
    }
    localHandler.post(new f.a.b(this, paramInt));
  }
  
  public final void a(Typeface paramTypeface, Handler paramHandler)
  {
    Handler localHandler = paramHandler;
    if (paramHandler == null) {
      localHandler = new Handler(Looper.getMainLooper());
    }
    localHandler.post(new f.a.a(this, paramTypeface));
  }
  
  public abstract void setText(Typeface paramTypeface);
}
