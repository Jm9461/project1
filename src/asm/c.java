package asm;

import android.os.Handler;
import android.os.Looper;

final class c
  implements AnnotationVisitor
{
  private static final Handler h = new Handler(Looper.getMainLooper());
  
  c() {}
  
  public void a(Runnable paramRunnable)
  {
    h.post(paramRunnable);
  }
}
