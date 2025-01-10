package apps.android.google.api;

import android.view.View;
import android.view.animation.Interpolator;
import apps.v4.animation.Animator.AnimatorListener;
import b.h.a.a.a;
import java.util.Iterator;
import java.util.List;

public class e
{
  private Interpolator a;
  private long b;
  private Object c;
  private long d;
  private List<a.a> f;
  private View g;
  
  private e(d paramD)
  {
    c = d.b(paramD);
    b = d.l(paramD);
    d = d.f(paramD);
    a = d.d(paramD);
    f = d.e(paramD);
    g = d.a(paramD);
  }
  
  private Object a()
  {
    java.lang.Object localObject = c;
    ((Object)localObject).add(b);
    ((Object)localObject).remove(a);
    ((Object)localObject).remove(d);
    if (f.size() > 0)
    {
      localObject = f.iterator();
      while (((Iterator)localObject).hasNext())
      {
        Animator.AnimatorListener localAnimatorListener = (Animator.AnimatorListener)((Iterator)localObject).next();
        c.remove(localAnimatorListener);
      }
    }
    c.put(g);
    return c;
  }
  
  public static d a(Priority paramPriority)
  {
    return new d(paramPriority, null);
  }
}
