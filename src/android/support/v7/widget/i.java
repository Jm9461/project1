package android.support.v7.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;

class i
  extends RecyclerView.n
  implements RecyclerView.s
{
  private static final int[] K = new int[0];
  private static final int[] e = { 16842919 };
  private final RecyclerView.t M = new j0.b(this);
  private RecyclerView a;
  private final Drawable b;
  private final Runnable c = new j0.a(this);
  private final int[] d = new int[2];
  private boolean f = false;
  private final int g;
  private final StateListDrawable h;
  private int i = 0;
  int j;
  int k;
  int l;
  private final int m;
  final ValueAnimator mAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
  int n;
  private final int[] o = new int[2];
  private final int p;
  private boolean q = false;
  private final int r;
  float s;
  private final int t;
  final Drawable this$0;
  final StateListDrawable top;
  private final int u;
  private int v = 0;
  int w = 0;
  private int x = 0;
  private int y = 0;
  float z;
  
  i(RecyclerView paramRecyclerView, StateListDrawable paramStateListDrawable1, Drawable paramDrawable1, StateListDrawable paramStateListDrawable2, Drawable paramDrawable2, int paramInt1, int paramInt2, int paramInt3)
  {
    top = paramStateListDrawable1;
    this$0 = paramDrawable1;
    h = paramStateListDrawable2;
    b = paramDrawable2;
    r = Math.max(paramInt1, paramStateListDrawable1.getIntrinsicWidth());
    u = Math.max(paramInt1, paramDrawable1.getIntrinsicWidth());
    g = Math.max(paramInt1, paramStateListDrawable2.getIntrinsicWidth());
    m = Math.max(paramInt1, paramDrawable2.getIntrinsicWidth());
    t = paramInt2;
    p = paramInt3;
    top.setAlpha(255);
    this$0.setAlpha(255);
    mAnimator.addListener(new j0.c(this));
    mAnimator.addUpdateListener(new j0.d(this));
    a(paramRecyclerView);
  }
  
  private void a(float paramFloat)
  {
    int[] arrayOfInt = add();
    paramFloat = Math.max(arrayOfInt[0], Math.min(arrayOfInt[1], paramFloat));
    if (Math.abs(j - paramFloat) < 2.0F) {
      return;
    }
    int i1 = add(s, paramFloat, arrayOfInt, a.computeHorizontalScrollRange(), a.computeHorizontalScrollOffset(), x);
    if (i1 != 0) {
      a.scrollBy(i1, 0);
    }
    s = paramFloat;
  }
  
  private void a(Canvas paramCanvas)
  {
    int i2 = x;
    int i1 = r;
    i2 -= i1;
    int i4 = n;
    int i3 = k;
    i4 -= i3 / 2;
    top.setBounds(0, 0, i1, i3);
    this$0.setBounds(0, 0, u, y);
    if (n())
    {
      this$0.draw(paramCanvas);
      paramCanvas.translate(r, i4);
      paramCanvas.scale(-1.0F, 1.0F);
      top.draw(paramCanvas);
      paramCanvas.scale(1.0F, 1.0F);
      paramCanvas.translate(-r, -i4);
      return;
    }
    paramCanvas.translate(i2, 0.0F);
    this$0.draw(paramCanvas);
    paramCanvas.translate(0.0F, i4);
    top.draw(paramCanvas);
    paramCanvas.translate(-i2, -i4);
  }
  
  private int[] a()
  {
    int[] arrayOfInt = o;
    int i1 = p;
    arrayOfInt[0] = i1;
    arrayOfInt[1] = (y - i1);
    return arrayOfInt;
  }
  
  private int add(float paramFloat1, float paramFloat2, int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
  {
    int i1 = paramArrayOfInt[1] - paramArrayOfInt[0];
    if (i1 == 0) {
      return 0;
    }
    paramFloat1 = (paramFloat2 - paramFloat1) / i1;
    paramInt1 -= paramInt3;
    paramInt3 = (int)(paramInt1 * paramFloat1);
    paramInt2 += paramInt3;
    if ((paramInt2 < paramInt1) && (paramInt2 >= 0)) {
      return paramInt3;
    }
    return 0;
  }
  
  private void add(float paramFloat)
  {
    int[] arrayOfInt = a();
    paramFloat = Math.max(arrayOfInt[0], Math.min(arrayOfInt[1], paramFloat));
    if (Math.abs(n - paramFloat) < 2.0F) {
      return;
    }
    int i1 = add(z, paramFloat, arrayOfInt, a.computeVerticalScrollRange(), a.computeVerticalScrollOffset(), y);
    if (i1 != 0) {
      a.scrollBy(0, i1);
    }
    z = paramFloat;
  }
  
  private int[] add()
  {
    int[] arrayOfInt = d;
    int i1 = p;
    arrayOfInt[0] = i1;
    arrayOfInt[1] = (x - i1);
    return arrayOfInt;
  }
  
  private void b()
  {
    a.removeCallbacks(c);
  }
  
  private void close(int paramInt)
  {
    b();
    a.postDelayed(c, paramInt);
  }
  
  private void draw(Canvas paramCanvas)
  {
    int i2 = y;
    int i1 = g;
    i2 -= i1;
    int i4 = j;
    int i3 = l;
    i4 -= i3 / 2;
    h.setBounds(0, 0, i3, i1);
    b.setBounds(0, 0, x, m);
    paramCanvas.translate(0.0F, i2);
    b.draw(paramCanvas);
    paramCanvas.translate(i4, 0.0F);
    h.draw(paramCanvas);
    paramCanvas.translate(-i4, -i2);
  }
  
  private void h()
  {
    a.removeItemDecoration(this);
    a.removeOnItemTouchListener(this);
    a.removeOnScrollListener(M);
    b();
  }
  
  private void init()
  {
    a.setAdapter(this);
    a.addOnItemTouchListener(this);
    a.addOnScrollListener(M);
  }
  
  private boolean n()
  {
    return ViewCompat.getLayoutDirection(a) == 1;
  }
  
  void a(int paramInt)
  {
    if ((paramInt == 2) && (v != 2))
    {
      top.setState(e);
      b();
    }
    if (paramInt == 0) {
      clear();
    } else {
      animate();
    }
    if ((v == 2) && (paramInt != 2))
    {
      top.setState(K);
      close(1200);
    }
    else if (paramInt == 1)
    {
      close(1500);
    }
    v = paramInt;
  }
  
  void a(int paramInt1, int paramInt2)
  {
    int i1 = a.computeVerticalScrollRange();
    int i2 = y;
    boolean bool;
    if ((i1 - i2 > 0) && (y >= t)) {
      bool = true;
    } else {
      bool = false;
    }
    q = bool;
    int i3 = a.computeHorizontalScrollRange();
    int i4 = x;
    if ((i3 - i4 > 0) && (x >= t)) {
      bool = true;
    } else {
      bool = false;
    }
    f = bool;
    if ((!q) && (!f))
    {
      if (v != 0) {
        a(0);
      }
    }
    else
    {
      float f1;
      float f2;
      if (q)
      {
        f1 = paramInt2;
        f2 = i2 / 2.0F;
        n = ((int)(i2 * (f1 + f2) / i1));
        k = Math.min(i2, i2 * i2 / i1);
      }
      if (f)
      {
        f1 = paramInt1;
        f2 = i4 / 2.0F;
        j = ((int)(i4 * (f1 + f2) / i3));
        l = Math.min(i4, i4 * i4 / i3);
      }
      paramInt1 = v;
      if ((paramInt1 == 0) || (paramInt1 == 1)) {
        a(1);
      }
    }
  }
  
  public void a(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.a0 paramA0)
  {
    if ((x == a.getWidth()) && (y == a.getHeight()))
    {
      if (w != 0)
      {
        if (q) {
          a(paramCanvas);
        }
        if (f) {
          draw(paramCanvas);
        }
      }
    }
    else
    {
      x = a.getWidth();
      y = a.getHeight();
      a(0);
    }
  }
  
  public void a(RecyclerView paramRecyclerView)
  {
    RecyclerView localRecyclerView = a;
    if (localRecyclerView == paramRecyclerView) {
      return;
    }
    if (localRecyclerView != null) {
      h();
    }
    a = paramRecyclerView;
    if (a != null) {
      init();
    }
  }
  
  public void a(RecyclerView paramRecyclerView, MotionEvent paramMotionEvent)
  {
    if (v == 0) {
      return;
    }
    if (paramMotionEvent.getAction() == 0)
    {
      boolean bool1 = a(paramMotionEvent.getX(), paramMotionEvent.getY());
      boolean bool2 = add(paramMotionEvent.getX(), paramMotionEvent.getY());
      if ((bool1) || (bool2))
      {
        if (bool2)
        {
          i = 1;
          s = ((int)paramMotionEvent.getX());
        }
        else if (bool1)
        {
          i = 2;
          z = ((int)paramMotionEvent.getY());
        }
        a(2);
      }
      return;
    }
    if ((paramMotionEvent.getAction() == 1) && (v == 2))
    {
      z = 0.0F;
      s = 0.0F;
      a(1);
      i = 0;
      return;
    }
    if ((paramMotionEvent.getAction() == 2) && (v == 2))
    {
      animate();
      if (i == 1) {
        a(paramMotionEvent.getX());
      }
      if (i == 2) {
        add(paramMotionEvent.getY());
      }
    }
  }
  
  boolean a(float paramFloat1, float paramFloat2)
  {
    if (n() ? paramFloat1 <= r / 2 : paramFloat1 >= x - r)
    {
      int i1 = n;
      int i2 = k;
      if ((paramFloat2 >= i1 - i2 / 2) && (paramFloat2 <= i1 + i2 / 2)) {
        return true;
      }
    }
    return false;
  }
  
  boolean add(float paramFloat1, float paramFloat2)
  {
    if (paramFloat2 >= y - g)
    {
      int i1 = j;
      int i2 = l;
      if ((paramFloat1 >= i1 - i2 / 2) && (paramFloat1 <= i1 + i2 / 2)) {
        return true;
      }
    }
    return false;
  }
  
  public void animate()
  {
    int i1 = w;
    if (i1 != 0)
    {
      if (i1 != 3) {
        return;
      }
      mAnimator.cancel();
    }
    w = 1;
    ValueAnimator localValueAnimator = mAnimator;
    localValueAnimator.setFloatValues(new float[] { ((Float)localValueAnimator.getAnimatedValue()).floatValue(), 1.0F });
    mAnimator.setDuration(500L);
    mAnimator.setStartDelay(0L);
    mAnimator.start();
  }
  
  void animate(int paramInt)
  {
    int i1 = w;
    if (i1 != 1)
    {
      if (i1 == 2) {}
    }
    else {
      mAnimator.cancel();
    }
    w = 3;
    ValueAnimator localValueAnimator = mAnimator;
    localValueAnimator.setFloatValues(new float[] { ((Float)localValueAnimator.getAnimatedValue()).floatValue(), 0.0F });
    mAnimator.setDuration(paramInt);
    mAnimator.start();
  }
  
  void clear()
  {
    a.invalidate();
  }
  
  public boolean onInterceptTouchEvent(RecyclerView paramRecyclerView, MotionEvent paramMotionEvent)
  {
    int i1 = v;
    if (i1 == 1)
    {
      boolean bool1 = a(paramMotionEvent.getX(), paramMotionEvent.getY());
      boolean bool2 = add(paramMotionEvent.getX(), paramMotionEvent.getY());
      if ((paramMotionEvent.getAction() == 0) && ((bool1) || (bool2)))
      {
        if (bool2)
        {
          i = 1;
          s = ((int)paramMotionEvent.getX());
        }
        else if (bool1)
        {
          i = 2;
          z = ((int)paramMotionEvent.getY());
        }
        a(2);
        return true;
      }
      return false;
    }
    return i1 == 2;
  }
  
  public void onRequestDisallowInterceptTouchEvent(boolean paramBoolean) {}
}
