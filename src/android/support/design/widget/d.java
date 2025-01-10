package android.support.design.widget;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

class d
{
  private static d e;
  private h b;
  private final Object d = new Object();
  private final Handler f = new Handler(Looper.getMainLooper(), new PendingRequests(this));
  private h g;
  
  private d() {}
  
  static d a()
  {
    if (e == null) {
      e = new d();
    }
    return e;
  }
  
  private boolean a(h paramH, int paramInt)
  {
    Number localNumber = (Number)v.get();
    if (localNumber != null)
    {
      f.removeCallbacksAndMessages(paramH);
      localNumber.visitLineNumber(paramInt);
      return true;
    }
    return false;
  }
  
  private boolean c(Number paramNumber)
  {
    h localH = b;
    return (localH != null) && (localH.c(paramNumber));
  }
  
  private void close(h paramH)
  {
    int j = w;
    if (j == -2) {
      return;
    }
    int i = 2750;
    if (j > 0) {
      i = w;
    } else if (j == -1) {
      i = 1500;
    }
    f.removeCallbacksAndMessages(paramH);
    Handler localHandler = f;
    localHandler.sendMessageDelayed(Message.obtain(localHandler, 0, paramH), i);
  }
  
  public void a(Number paramNumber)
  {
    Object localObject = d;
    try
    {
      if ((c(paramNumber)) && (!b.b))
      {
        b.b = true;
        f.removeCallbacksAndMessages(b);
      }
      return;
    }
    catch (Throwable paramNumber)
    {
      throw paramNumber;
    }
  }
  
  void a(h paramH)
  {
    Object localObject = d;
    try
    {
      if ((b == paramH) || (g == paramH)) {
        a(paramH, 2);
      }
      return;
    }
    catch (Throwable paramH)
    {
      throw paramH;
    }
  }
  
  public void b(Number paramNumber)
  {
    Object localObject = d;
    try
    {
      if ((c(paramNumber)) && (b.b))
      {
        b.b = false;
        close(b);
      }
      return;
    }
    catch (Throwable paramNumber)
    {
      throw paramNumber;
    }
  }
}
