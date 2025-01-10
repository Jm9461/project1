package android.support.v4.widget;

import android.animation.TimeInterpolator;
import android.content.res.Resources;
import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

public abstract class i
  implements View.OnTouchListener
{
  private static final int r = ;
  private final Interpolator a = new AccelerateInterpolator();
  private float[] b = { Float.MAX_VALUE, Float.MAX_VALUE };
  private float[] c = { Float.MAX_VALUE, Float.MAX_VALUE };
  private float[] d = { 0.0F, 0.0F };
  private boolean h;
  boolean i;
  private float[] j = { 0.0F, 0.0F };
  private int k;
  private boolean l;
  private float[] m = { 0.0F, 0.0F };
  final AutoScrollHelper.ClampedScroller mScroller = new AutoScrollHelper.ClampedScroller();
  private Runnable n;
  private boolean p;
  boolean s;
  private int w;
  boolean x;
  final View y;
  
  public i(View paramView)
  {
    y = paramView;
    float f = getSystemgetDisplayMetricsdensity;
    int i1 = (int)(1575.0F * f + 0.5F);
    int i2 = (int)(f * 315.0F + 0.5F);
    add(i1, i1);
    size(i2, i2);
    b(1);
    removeItem(Float.MAX_VALUE, Float.MAX_VALUE);
    a(0.2F, 0.2F);
    format(1.0F, 1.0F);
    a(r);
    setRampDownDuration(500);
    setRampUpDuration(500);
  }
  
  static float a(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (paramFloat1 > paramFloat3) {
      return paramFloat3;
    }
    if (paramFloat1 < paramFloat2) {
      return paramFloat2;
    }
    return paramFloat1;
  }
  
  private float a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramFloat1 = a(paramFloat1 * paramFloat2, 0.0F, paramFloat3);
    paramFloat3 = normalize(paramFloat4, paramFloat1);
    paramFloat1 = normalize(paramFloat2 - paramFloat4, paramFloat1) - paramFloat3;
    if (paramFloat1 < 0.0F)
    {
      paramFloat1 = -a.getInterpolation(-paramFloat1);
    }
    else
    {
      if (paramFloat1 <= 0.0F) {
        break label76;
      }
      paramFloat1 = a.getInterpolation(paramFloat1);
    }
    return a(paramFloat1, -1.0F, 1.0F);
    label76:
    return 0.0F;
  }
  
  static int a(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 > paramInt3) {
      return paramInt3;
    }
    if (paramInt1 < paramInt2) {
      return paramInt2;
    }
    return paramInt1;
  }
  
  private void a()
  {
    if (n == null) {
      n = new EventInfoFragment.1(this);
    }
    x = true;
    s = true;
    if (!h)
    {
      int i1 = k;
      if (i1 > 0)
      {
        ViewCompat.add(y, n, i1);
        break label70;
      }
    }
    n.run();
    label70:
    h = true;
  }
  
  private float add(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    paramFloat1 = a(m[paramInt], paramFloat2, b[paramInt], paramFloat1);
    if (paramFloat1 == 0.0F) {
      return 0.0F;
    }
    float f2 = d[paramInt];
    paramFloat2 = j[paramInt];
    float f1 = c[paramInt];
    paramFloat3 = f2 * paramFloat3;
    if (paramFloat1 > 0.0F) {
      return a(paramFloat1 * paramFloat3, paramFloat2, f1);
    }
    return -a(-paramFloat1 * paramFloat3, paramFloat2, f1);
  }
  
  private void d()
  {
    if (s)
    {
      x = false;
      return;
    }
    mScroller.requestStop();
  }
  
  private float normalize(float paramFloat1, float paramFloat2)
  {
    if (paramFloat2 == 0.0F) {
      return 0.0F;
    }
    int i1 = w;
    if ((i1 != 0) && (i1 != 1))
    {
      if (i1 != 2) {
        return 0.0F;
      }
      if (paramFloat1 < 0.0F) {
        return paramFloat1 / -paramFloat2;
      }
    }
    else if (paramFloat1 < paramFloat2)
    {
      if (paramFloat1 >= 0.0F) {
        return 1.0F - paramFloat1 / paramFloat2;
      }
      if ((x) && (w == 1)) {
        return 1.0F;
      }
    }
    return 0.0F;
  }
  
  public i a(float paramFloat1, float paramFloat2)
  {
    float[] arrayOfFloat = m;
    arrayOfFloat[0] = paramFloat1;
    arrayOfFloat[1] = paramFloat2;
    return this;
  }
  
  public i a(int paramInt)
  {
    k = paramInt;
    return this;
  }
  
  public i a(boolean paramBoolean)
  {
    if ((l) && (!paramBoolean)) {
      d();
    }
    l = paramBoolean;
    return this;
  }
  
  public i add(float paramFloat1, float paramFloat2)
  {
    float[] arrayOfFloat = c;
    arrayOfFloat[0] = (paramFloat1 / 1000.0F);
    arrayOfFloat[1] = (paramFloat2 / 1000.0F);
    return this;
  }
  
  public i b(int paramInt)
  {
    w = paramInt;
    return this;
  }
  
  public abstract boolean canTargetScrollHorizontally(int paramInt);
  
  public abstract boolean canTargetScrollVertically(int paramInt);
  
  public i format(float paramFloat1, float paramFloat2)
  {
    float[] arrayOfFloat = d;
    arrayOfFloat[0] = (paramFloat1 / 1000.0F);
    arrayOfFloat[1] = (paramFloat2 / 1000.0F);
    return this;
  }
  
  void onLongPress()
  {
    long l1 = SystemClock.uptimeMillis();
    MotionEvent localMotionEvent = MotionEvent.obtain(l1, l1, 3, 0.0F, 0.0F, 0);
    y.onTouchEvent(localMotionEvent);
    localMotionEvent.recycle();
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (!l) {
      return false;
    }
    int i1 = paramMotionEvent.getActionMasked();
    if (i1 != 0)
    {
      if (i1 != 1)
      {
        if (i1 != 2) {
          if (i1 != 3) {
            break label133;
          }
        }
      }
      else
      {
        d();
        break label133;
      }
    }
    else
    {
      i = true;
      h = false;
    }
    float f1 = add(0, paramMotionEvent.getX(), paramView.getWidth(), y.getWidth());
    float f2 = add(1, paramMotionEvent.getY(), paramView.getHeight(), y.getHeight());
    mScroller.setTargetVelocity(f1, f2);
    if ((!x) && (shouldAnimate())) {
      a();
    }
    label133:
    return (p) && (x);
  }
  
  public i removeItem(float paramFloat1, float paramFloat2)
  {
    float[] arrayOfFloat = b;
    arrayOfFloat[0] = paramFloat1;
    arrayOfFloat[1] = paramFloat2;
    return this;
  }
  
  public abstract void scrollTargetBy(int paramInt1, int paramInt2);
  
  public i setRampDownDuration(int paramInt)
  {
    mScroller.setRampDownDuration(paramInt);
    return this;
  }
  
  public i setRampUpDuration(int paramInt)
  {
    mScroller.setRampUpDuration(paramInt);
    return this;
  }
  
  boolean shouldAnimate()
  {
    AutoScrollHelper.ClampedScroller localClampedScroller = mScroller;
    int i1 = localClampedScroller.getVerticalDirection();
    int i2 = localClampedScroller.getHorizontalDirection();
    return ((i1 != 0) && (canTargetScrollVertically(i1))) || ((i2 != 0) && (canTargetScrollHorizontally(i2)));
  }
  
  public i size(float paramFloat1, float paramFloat2)
  {
    float[] arrayOfFloat = j;
    arrayOfFloat[0] = (paramFloat1 / 1000.0F);
    arrayOfFloat[1] = (paramFloat2 / 1000.0F);
    return this;
  }
}
