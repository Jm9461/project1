package apps.android.google.api;

import android.view.View;
import android.view.animation.Interpolator;
import apps.v4.animation.Animator.AnimatorListener;
import b.h.a.a.a;
import java.util.ArrayList;
import java.util.List;

public final class d
{
  private Object b;
  private List<a.a> e = new ArrayList();
  private Interpolator f;
  private View g;
  private long h = 0L;
  private long n = 1000L;
  
  private d(Priority paramPriority)
  {
    b = paramPriority.parse();
  }
  
  public a a(View paramView)
  {
    g = paramView;
    return new a(e.a(new e(this, null)), g, null);
  }
  
  public d a(long paramLong)
  {
    n = paramLong;
    return this;
  }
  
  public d a(Animator.AnimatorListener paramAnimatorListener)
  {
    e.add(paramAnimatorListener);
    return this;
  }
}
