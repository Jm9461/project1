package android.support.v7.widget;

import android.content.Context;
import android.support.v4.view.Frame;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnHoverListener;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

class h
  implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener
{
  private static h c;
  private static h l;
  private final View a;
  private final Runnable b = new m1.b(this);
  private final Runnable e = new m1.a(this);
  private boolean i;
  private final CharSequence k;
  private final int o;
  private int p;
  private e r;
  private int s;
  
  private h(View paramView, CharSequence paramCharSequence)
  {
    a = paramView;
    k = paramCharSequence;
    o = Frame.get(ViewConfiguration.get(a.getContext()));
    d();
    a.setOnLongClickListener(this);
    a.setOnHoverListener(this);
  }
  
  private boolean a(MotionEvent paramMotionEvent)
  {
    int j = (int)paramMotionEvent.getX();
    int m = (int)paramMotionEvent.getY();
    if ((Math.abs(j - s) <= o) && (Math.abs(m - p) <= o)) {
      return false;
    }
    s = j;
    p = m;
    return true;
  }
  
  private void b()
  {
    a.removeCallbacks(e);
  }
  
  private static void b(h paramH)
  {
    h localH = l;
    if (localH != null) {
      localH.b();
    }
    l = paramH;
    paramH = l;
    if (paramH != null) {
      paramH.e();
    }
  }
  
  private void d()
  {
    s = Integer.MAX_VALUE;
    p = Integer.MAX_VALUE;
  }
  
  private void e()
  {
    a.postDelayed(e, ViewConfiguration.getLongPressTimeout());
  }
  
  public static void setTitle(View paramView, CharSequence paramCharSequence)
  {
    h localH = l;
    if ((localH != null) && (a == paramView)) {
      b(null);
    }
    if (TextUtils.isEmpty(paramCharSequence))
    {
      paramCharSequence = c;
      if ((paramCharSequence != null) && (a == paramView)) {
        paramCharSequence.a();
      }
      paramView.setOnLongClickListener(null);
      paramView.setLongClickable(false);
      paramView.setOnHoverListener(null);
      return;
    }
    new h(paramView, paramCharSequence);
  }
  
  void a()
  {
    if (c == this)
    {
      c = null;
      e localE = r;
      if (localE != null)
      {
        localE.a();
        r = null;
        d();
        a.removeOnAttachStateChangeListener(this);
      }
      else
      {
        Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
      }
    }
    if (l == this) {
      b(null);
    }
    a.removeCallbacks(b);
  }
  
  void a(boolean paramBoolean)
  {
    if (!ViewCompat.setText(a)) {
      return;
    }
    b(null);
    h localH = c;
    if (localH != null) {
      localH.a();
    }
    c = this;
    i = paramBoolean;
    r = new e(a.getContext());
    r.a(a, s, p, i, k);
    a.addOnAttachStateChangeListener(this);
    long l1;
    if (i) {
      l1 = 2500L;
    } else if ((ViewCompat.getWindowSystemUiVisibility(a) & 0x1) == 1) {
      l1 = 3000L - ViewConfiguration.getLongPressTimeout();
    } else {
      l1 = 15000L - ViewConfiguration.getLongPressTimeout();
    }
    a.removeCallbacks(b);
    a.postDelayed(b, l1);
  }
  
  public boolean onHover(View paramView, MotionEvent paramMotionEvent)
  {
    if ((r != null) && (i)) {
      return false;
    }
    paramView = (AccessibilityManager)a.getContext().getSystemService("accessibility");
    if ((paramView.isEnabled()) && (paramView.isTouchExplorationEnabled())) {
      return false;
    }
    int j = paramMotionEvent.getAction();
    if (j != 7)
    {
      if (j != 10) {
        return false;
      }
      d();
      a();
      return false;
    }
    if ((a.isEnabled()) && (r == null) && (a(paramMotionEvent))) {
      b(this);
    }
    return false;
  }
  
  public boolean onLongClick(View paramView)
  {
    s = (paramView.getWidth() / 2);
    p = (paramView.getHeight() / 2);
    a(true);
    return true;
  }
  
  public void onViewAttachedToWindow(View paramView) {}
  
  public void onViewDetachedFromWindow(View paramView)
  {
    a();
  }
}
