package com.heinrichreimersoftware.materialintro.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.Op;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.j;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnAttachStateChangeListener;
import android.view.animation.Interpolator;
import apps.gdata.util.R.styleable;
import apps.gdata.util.common.x;
import java.util.Arrays;

public class InkPageIndicator
  extends View
  implements ViewPager.j, View.OnAttachStateChangeListener
{
  private int a = 0;
  private float b;
  private final Paint borderPaint;
  float bottom;
  private float[] c;
  private int color;
  private g[] current;
  private boolean d;
  private int e;
  private int f = 0;
  private int fillColor;
  private final Paint fillPaint;
  private boolean first;
  private float g;
  float h;
  float height;
  private float i;
  private int j;
  private final Path k;
  private float[] l;
  float left;
  private f mController;
  private long mDuration;
  private final Interpolator mInterpolator;
  private final Path mPath;
  private final RectF mRect;
  private long mStartTime;
  private ViewPager mViewPager;
  private float mY;
  float n;
  private boolean p = false;
  private final Path path;
  float r;
  private boolean ready;
  private int s;
  float size;
  private final Path this$0;
  private float v;
  float value;
  private float[] w;
  private float x;
  private float y;
  private float z;
  
  public InkPageIndicator(Context paramContext)
  {
    this(paramContext, null, 0);
  }
  
  public InkPageIndicator(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public InkPageIndicator(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    int m = (int)getResourcesgetDisplayMetricsdensity;
    paramAttributeSet = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.InkPageIndicator, paramInt, 0);
    s = paramAttributeSet.getDimensionPixelSize(R.styleable.InkPageIndicator_dotDiameter, m * 8);
    b = (s / 2);
    g = (b / 2.0F);
    j = paramAttributeSet.getDimensionPixelSize(R.styleable.InkPageIndicator_dotGap, m * 12);
    mDuration = paramAttributeSet.getInteger(R.styleable.InkPageIndicator_animationDuration, 400);
    mStartTime = (mDuration / 2L);
    fillColor = paramAttributeSet.getColor(R.styleable.InkPageIndicator_pageIndicatorColor, -2130706433);
    color = paramAttributeSet.getColor(R.styleable.InkPageIndicator_currentPageIndicatorColor, -1);
    paramAttributeSet.recycle();
    fillPaint = new Paint(1);
    fillPaint.setColor(fillColor);
    borderPaint = new Paint(1);
    borderPaint.setColor(color);
    mInterpolator = x.a(paramContext);
    k = new Path();
    path = new Path();
    this$0 = new Path();
    mPath = new Path();
    mRect = new RectF();
    addOnAttachStateChangeListener(this);
  }
  
  private void a()
  {
    c = new float[Math.max(f - 1, 0)];
    Arrays.fill(c, 0.0F);
    l = new float[f];
    Arrays.fill(l, 0.0F);
    v = -1.0F;
    z = -1.0F;
    d = true;
  }
  
  private void a(int paramInt, float paramFloat)
  {
    l[paramInt] = paramFloat;
    if (Build.VERSION.SDK_INT >= 16)
    {
      postInvalidateOnAnimation();
      return;
    }
    postInvalidate();
  }
  
  private void a(int paramInt1, int paramInt2)
  {
    if (!p) {
      return;
    }
    int m = getPaddingLeft();
    int i1 = getPaddingTop();
    int i2 = getPaddingRight();
    int i3 = getPaddingBottom();
    float f1 = i1 + (paramInt2 - i3) / 2.0F;
    float f2 = m;
    float f3 = (paramInt1 - i2) / 2.0F;
    float f4 = getRequiredWidth() / 2.0F;
    float f5 = b;
    w = new float[Math.max(1, f)];
    paramInt1 = 0;
    while (paramInt1 < f)
    {
      w[paramInt1] = ((s + j) * paramInt1 + (f2 + f3 - f4 + f5));
      paramInt1 += 1;
    }
    f2 = b;
    i = (f1 - f2);
    x = f1;
    y = (f2 + f1);
    set();
  }
  
  private void a(Canvas paramCanvas)
  {
    k.rewind();
    int m = 0;
    Object localObject;
    for (;;)
    {
      int i1 = f;
      if (m >= i1) {
        break;
      }
      if (m == i1 - 1) {
        i1 = m;
      } else {
        i1 = m + 1;
      }
      localObject = w;
      float f2 = localObject[m];
      float f3 = localObject[i1];
      float f1;
      if (m == f - 1) {
        f1 = -1.0F;
      } else {
        f1 = c[m];
      }
      localObject = draw(m, f2, f3, f1, l[m]);
      if (Build.VERSION.SDK_INT >= 19) {
        k.op((Path)localObject, Path.Op.UNION);
      } else {
        k.addPath((Path)localObject);
      }
      m += 1;
    }
    if (v != -1.0F)
    {
      localObject = getRetreatingJoinPath();
      if (Build.VERSION.SDK_INT >= 19) {
        k.op((Path)localObject, Path.Op.UNION);
      } else {
        k.addPath((Path)localObject);
      }
    }
    paramCanvas.drawPath(k, fillPaint);
  }
  
  private void doDraw(Canvas paramCanvas)
  {
    paramCanvas.drawCircle(mY, x, b, borderPaint);
  }
  
  private Path draw(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    path.rewind();
    if (((paramFloat3 == 0.0F) || (paramFloat3 == -1.0F)) && (paramFloat4 == 0.0F) && ((paramInt != a) || (!d))) {
      path.addCircle(w[paramInt], x, b, Path.Direction.CW);
    }
    Object localObject;
    float f1;
    float f2;
    float f3;
    if ((paramFloat3 > 0.0F) && (paramFloat3 <= 0.5F) && (v == -1.0F))
    {
      this$0.rewind();
      this$0.moveTo(paramFloat1, y);
      localObject = mRect;
      f1 = b;
      ((RectF)localObject).set(paramFloat1 - f1, i, f1 + paramFloat1, y);
      this$0.arcTo(mRect, 90.0F, 180.0F, true);
      r = (b + paramFloat1 + j * paramFloat3);
      left = x;
      f1 = g;
      size = (paramFloat1 + f1);
      value = i;
      f2 = r;
      n = f2;
      f3 = left;
      h = (f3 - f1);
      this$0.cubicTo(size, value, n, h, f2, f3);
      bottom = paramFloat1;
      f1 = y;
      height = f1;
      size = r;
      f2 = left;
      f3 = g;
      value = (f2 + f3);
      n = (paramFloat1 + f3);
      h = f1;
      this$0.cubicTo(size, value, n, h, bottom, height);
      if (Build.VERSION.SDK_INT >= 19) {
        path.op(this$0, Path.Op.UNION);
      } else {
        path.addPath(this$0);
      }
      mPath.rewind();
      mPath.moveTo(paramFloat2, y);
      localObject = mRect;
      f1 = b;
      ((RectF)localObject).set(paramFloat2 - f1, i, f1 + paramFloat2, y);
      mPath.arcTo(mRect, 90.0F, -180.0F, true);
      r = (paramFloat2 - b - j * paramFloat3);
      left = x;
      f1 = g;
      size = (paramFloat2 - f1);
      value = i;
      f2 = r;
      n = f2;
      f3 = left;
      h = (f3 - f1);
      mPath.cubicTo(size, value, n, h, f2, f3);
      bottom = paramFloat2;
      f1 = y;
      height = f1;
      size = r;
      f3 = left;
      f2 = g;
      value = (f3 + f2);
      f3 = bottom;
      n = (f3 - f2);
      h = f1;
      mPath.cubicTo(size, value, n, h, f3, height);
      if (Build.VERSION.SDK_INT >= 19) {
        path.op(mPath, Path.Op.UNION);
      } else {
        path.addPath(mPath);
      }
    }
    if ((paramFloat3 > 0.5F) && (paramFloat3 < 1.0F) && (v == -1.0F))
    {
      f1 = (paramFloat3 - 0.2F) * 1.25F;
      path.moveTo(paramFloat1, y);
      localObject = mRect;
      f2 = b;
      ((RectF)localObject).set(paramFloat1 - f2, i, f2 + paramFloat1, y);
      path.arcTo(mRect, 90.0F, 180.0F, true);
      f3 = b;
      r = (paramFloat1 + f3 + j / 2);
      left = (x - f1 * f3);
      f2 = r;
      size = (f2 - f1 * f3);
      value = i;
      n = (f2 - (1.0F - f1) * f3);
      f3 = left;
      h = f3;
      path.cubicTo(size, value, n, h, f2, f3);
      bottom = paramFloat2;
      f2 = i;
      height = f2;
      f3 = r;
      float f4 = b;
      size = ((1.0F - f1) * f4 + f3);
      value = left;
      n = (f3 + f4 * f1);
      h = f2;
      path.cubicTo(size, value, n, h, bottom, height);
      localObject = mRect;
      f2 = b;
      ((RectF)localObject).set(paramFloat2 - f2, i, f2 + paramFloat2, y);
      path.arcTo(mRect, 270.0F, 180.0F, true);
      f3 = x;
      f2 = b;
      left = (f3 + f1 * f2);
      f3 = r;
      size = (f1 * f2 + f3);
      value = y;
      n = ((1.0F - f1) * f2 + f3);
      f2 = left;
      h = f2;
      path.cubicTo(size, value, n, h, f3, f2);
      bottom = paramFloat1;
      height = y;
      f2 = r;
      f3 = b;
      size = (f2 - (1.0F - f1) * f3);
      value = left;
      n = (f2 - f3 * f1);
      f1 = height;
      h = f1;
      path.cubicTo(size, value, n, h, bottom, f1);
    }
    if ((paramFloat3 == 1.0F) && (v == -1.0F))
    {
      localObject = mRect;
      paramFloat3 = b;
      ((RectF)localObject).set(paramFloat1 - paramFloat3, i, paramFloat3 + paramFloat2, y);
      localObject = path;
      RectF localRectF = mRect;
      paramFloat2 = b;
      ((Path)localObject).addRoundRect(localRectF, paramFloat2, paramFloat2, Path.Direction.CW);
    }
    if (paramFloat4 > 1.0E-5F) {
      path.addCircle(paramFloat1, x, b * paramFloat4, Path.Direction.CW);
    }
    return path;
  }
  
  private int getDesiredHeight()
  {
    return getPaddingTop() + s + getPaddingBottom();
  }
  
  private int getDesiredWidth()
  {
    return getPaddingLeft() + getRequiredWidth() + getPaddingRight();
  }
  
  private int getRequiredWidth()
  {
    int m = f;
    return s * m + (m - 1) * j;
  }
  
  private Path getRetreatingJoinPath()
  {
    path.rewind();
    mRect.set(v, i, z, y);
    Path localPath = path;
    RectF localRectF = mRect;
    float f1 = b;
    localPath.addRoundRect(localRectF, f1, f1, Path.Direction.CW);
    return path;
  }
  
  private void set()
  {
    Object localObject = mViewPager;
    if (localObject != null) {
      a = ((ViewPager)localObject).getCurrentItem();
    } else {
      a = 0;
    }
    localObject = w;
    if (localObject != null) {
      mY = localObject[Math.max(0, Math.min(a, localObject.length - 1))];
    }
  }
  
  private void setPageCount(int paramInt)
  {
    f = paramInt;
    a(getWidth(), getHeight());
    a();
    requestLayout();
  }
  
  private void setPosition(int paramInt, float paramFloat)
  {
    float[] arrayOfFloat = c;
    if (paramInt < arrayOfFloat.length)
    {
      arrayOfFloat[paramInt] = paramFloat;
      if (Build.VERSION.SDK_INT >= 16)
      {
        postInvalidateOnAnimation();
        return;
      }
      postInvalidate();
    }
  }
  
  private void setSelectedPage(int paramInt)
  {
    int m = Math.min(paramInt, f - 1);
    paramInt = a;
    if (m == paramInt) {
      return;
    }
    ready = true;
    e = paramInt;
    a = m;
    int i1 = Math.abs(m - e);
    if (i1 > 1) {
      if (m > e)
      {
        paramInt = 0;
        while (paramInt < i1)
        {
          setPosition(e + paramInt, 1.0F);
          paramInt += 1;
        }
      }
      else
      {
        paramInt = -1;
        while (paramInt > -i1)
        {
          setPosition(e + paramInt, 1.0F);
          paramInt -= 1;
        }
      }
    }
    if (getVisibility() == 0) {
      startAnimation(w[m], e, m, i1).start();
    }
  }
  
  private void start()
  {
    Arrays.fill(c, 0.0F);
    if (Build.VERSION.SDK_INT >= 16)
    {
      postInvalidateOnAnimation();
      return;
    }
    postInvalidate();
  }
  
  private ValueAnimator startAnimation(float paramFloat, int paramInt1, int paramInt2, int paramInt3)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a7 = a6\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  public int getCurrentPageIndicatorColor()
  {
    return borderPaint.getColor();
  }
  
  public int getPageIndicatorColor()
  {
    return fillPaint.getColor();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    if (mViewPager != null)
    {
      if (f == 0) {
        return;
      }
      a(paramCanvas);
      doDraw(paramCanvas);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int m = getDesiredHeight();
    int i1 = View.MeasureSpec.getMode(paramInt2);
    if (i1 != Integer.MIN_VALUE)
    {
      if (i1 != 1073741824) {
        paramInt2 = m;
      } else {
        paramInt2 = View.MeasureSpec.getSize(paramInt2);
      }
    }
    else {
      paramInt2 = Math.min(m, View.MeasureSpec.getSize(paramInt2));
    }
    m = getDesiredWidth();
    i1 = View.MeasureSpec.getMode(paramInt1);
    if (i1 != Integer.MIN_VALUE)
    {
      if (i1 != 1073741824) {
        paramInt1 = m;
      } else {
        paramInt1 = View.MeasureSpec.getSize(paramInt1);
      }
    }
    else {
      paramInt1 = Math.min(m, View.MeasureSpec.getSize(paramInt1));
    }
    setMeasuredDimension(paramInt1, paramInt2);
    if (!p) {
      p = true;
    }
  }
  
  public void onPageScrollStateChanged(int paramInt) {}
  
  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    if (first)
    {
      float f1 = paramFloat;
      if (ready) {
        paramInt2 = e;
      } else {
        paramInt2 = a;
      }
      int m = paramInt1;
      int i1 = m;
      if (paramInt2 != paramInt1)
      {
        paramFloat = 1.0F - paramFloat;
        f1 = paramFloat;
        i1 = m;
        if (paramFloat == 1.0F)
        {
          i1 = Math.min(paramInt2, paramInt1);
          f1 = paramFloat;
        }
      }
      setPosition(i1, f1);
    }
  }
  
  public void onPageSelected(int paramInt)
  {
    if (first)
    {
      setSelectedPage(paramInt);
      return;
    }
    set();
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    a(paramInt1, paramInt2);
  }
  
  public void onViewAttachedToWindow(View paramView)
  {
    first = true;
  }
  
  public void onViewDetachedFromWindow(View paramView)
  {
    first = false;
  }
  
  public void setCurrentPageIndicatorColor(int paramInt)
  {
    borderPaint.setColor(paramInt);
    invalidate();
  }
  
  public void setPageIndicatorColor(int paramInt)
  {
    fillPaint.setColor(paramInt);
    invalidate();
  }
  
  public void setViewPager(ViewPager paramViewPager)
  {
    mViewPager = paramViewPager;
    paramViewPager.addOnPageChangeListener(this);
    setPageCount(paramViewPager.getAdapter().getCount());
    paramViewPager.getAdapter().registerDataSetObserver(new a());
  }
  
  class a
    extends DataSetObserver
  {
    a() {}
    
    public void onChanged()
    {
      InkPageIndicator localInkPageIndicator = InkPageIndicator.this;
      InkPageIndicator.append(localInkPageIndicator, InkPageIndicator.access$getMViewPager(localInkPageIndicator).getAdapter().getCount());
      invalidate();
    }
  }
  
  class b
    extends AnimatorListenerAdapter
  {
    b() {}
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      InkPageIndicator.clear(InkPageIndicator.this);
      InkPageIndicator.get(InkPageIndicator.this, false);
    }
  }
  
  class c
    implements ValueAnimator.AnimatorUpdateListener
  {
    c() {}
    
    public void onAnimationUpdate(ValueAnimator paramValueAnimator)
    {
      InkPageIndicator.normalize(InkPageIndicator.this, ((Float)paramValueAnimator.getAnimatedValue()).floatValue());
      InkPageIndicator.access$getMController(InkPageIndicator.this).add(InkPageIndicator.getY(InkPageIndicator.this));
      if (Build.VERSION.SDK_INT >= 16)
      {
        postInvalidateOnAnimation();
        return;
      }
      postInvalidate();
    }
  }
  
  class d
    extends AnimatorListenerAdapter
  {
    d() {}
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      InkPageIndicator.set(InkPageIndicator.this, true);
    }
    
    public void onAnimationStart(Animator paramAnimator)
    {
      InkPageIndicator.set(InkPageIndicator.this, false);
    }
  }
  
  public class e
    extends InkPageIndicator.j
  {
    public e(float paramFloat)
    {
      super(paramFloat);
    }
    
    boolean a(float paramFloat)
    {
      return paramFloat < d;
    }
  }
  
  public class f
    extends InkPageIndicator.h
  {
    public f(int paramInt1, int paramInt2, int paramInt3, final InkPageIndicator.j paramJ)
    {
      super(paramJ);
      setDuration(InkPageIndicator.calculate(InkPageIndicator.this));
      setInterpolator(InkPageIndicator.getInterpolator(InkPageIndicator.this));
      final float f1;
      if (paramInt2 > paramInt1) {
        f1 = Math.min(InkPageIndicator.getWidth(InkPageIndicator.this)[paramInt1], InkPageIndicator.getY(InkPageIndicator.this)) - InkPageIndicator.b(InkPageIndicator.this);
      } else {
        f1 = InkPageIndicator.getWidth(InkPageIndicator.this)[paramInt2] - InkPageIndicator.b(InkPageIndicator.this);
      }
      float f2;
      if (paramInt2 > paramInt1) {
        f2 = InkPageIndicator.getWidth(InkPageIndicator.this)[paramInt2] - InkPageIndicator.b(InkPageIndicator.this);
      } else {
        f2 = InkPageIndicator.getWidth(InkPageIndicator.this)[paramInt2] - InkPageIndicator.b(InkPageIndicator.this);
      }
      final float f3;
      if (paramInt2 > paramInt1) {
        f3 = InkPageIndicator.getWidth(InkPageIndicator.this)[paramInt2] + InkPageIndicator.b(InkPageIndicator.this);
      } else {
        f3 = Math.max(InkPageIndicator.getWidth(InkPageIndicator.this)[paramInt1], InkPageIndicator.getY(InkPageIndicator.this)) + InkPageIndicator.b(InkPageIndicator.this);
      }
      float f4;
      if (paramInt2 > paramInt1) {
        f4 = InkPageIndicator.getWidth(InkPageIndicator.this)[paramInt2] + InkPageIndicator.b(InkPageIndicator.this);
      } else {
        f4 = InkPageIndicator.getWidth(InkPageIndicator.this)[paramInt2] + InkPageIndicator.b(InkPageIndicator.this);
      }
      InkPageIndicator.subtract(InkPageIndicator.this, new InkPageIndicator.g[paramInt3]);
      paramJ = new int[paramInt3];
      if (f1 != f2)
      {
        setFloatValues(new float[] { f1, f2 });
        paramInt2 = 0;
        while (paramInt2 < paramInt3)
        {
          InkPageIndicator.next(InkPageIndicator.this)[paramInt2] = new InkPageIndicator.g(InkPageIndicator.this, paramInt1 + paramInt2, new InkPageIndicator.i(InkPageIndicator.this, InkPageIndicator.getWidth(InkPageIndicator.this)[(paramInt1 + paramInt2)]));
          paramJ[paramInt2] = (paramInt1 + paramInt2);
          paramInt2 += 1;
        }
        addUpdateListener(new a(InkPageIndicator.this));
      }
      else
      {
        setFloatValues(new float[] { f3, f4 });
        paramInt2 = 0;
        while (paramInt2 < paramInt3)
        {
          InkPageIndicator.next(InkPageIndicator.this)[paramInt2] = new InkPageIndicator.g(InkPageIndicator.this, paramInt1 - paramInt2, new InkPageIndicator.e(InkPageIndicator.this, InkPageIndicator.getWidth(InkPageIndicator.this)[(paramInt1 - paramInt2)]));
          paramJ[paramInt2] = (paramInt1 - paramInt2);
          paramInt2 += 1;
        }
        addUpdateListener(new b(InkPageIndicator.this));
      }
      addListener(new c(InkPageIndicator.this, paramJ, f1, f3));
    }
    
    class a
      implements ValueAnimator.AnimatorUpdateListener
    {
      a(InkPageIndicator paramInkPageIndicator) {}
      
      public void onAnimationUpdate(ValueAnimator paramValueAnimator)
      {
        InkPageIndicator.append(InkPageIndicator.this, ((Float)paramValueAnimator.getAnimatedValue()).floatValue());
        if (Build.VERSION.SDK_INT >= 16) {
          postInvalidateOnAnimation();
        } else {
          postInvalidate();
        }
        paramValueAnimator = InkPageIndicator.next(InkPageIndicator.this);
        int j = paramValueAnimator.length;
        int i = 0;
        while (i < j)
        {
          paramValueAnimator[i].add(InkPageIndicator.get(InkPageIndicator.this));
          i += 1;
        }
      }
    }
    
    class b
      implements ValueAnimator.AnimatorUpdateListener
    {
      b(InkPageIndicator paramInkPageIndicator) {}
      
      public void onAnimationUpdate(ValueAnimator paramValueAnimator)
      {
        InkPageIndicator.set(InkPageIndicator.this, ((Float)paramValueAnimator.getAnimatedValue()).floatValue());
        if (Build.VERSION.SDK_INT >= 16) {
          postInvalidateOnAnimation();
        } else {
          postInvalidate();
        }
        paramValueAnimator = InkPageIndicator.next(InkPageIndicator.this);
        int j = paramValueAnimator.length;
        int i = 0;
        while (i < j)
        {
          paramValueAnimator[i].add(InkPageIndicator.floatValue(InkPageIndicator.this));
          i += 1;
        }
      }
    }
    
    class c
      extends AnimatorListenerAdapter
    {
      c(InkPageIndicator paramInkPageIndicator, int[] paramArrayOfInt, float paramFloat1, float paramFloat2) {}
      
      public void onAnimationEnd(Animator paramAnimator)
      {
        InkPageIndicator.append(InkPageIndicator.this, -1.0F);
        InkPageIndicator.set(InkPageIndicator.this, -1.0F);
        if (Build.VERSION.SDK_INT >= 16)
        {
          postInvalidateOnAnimation();
          return;
        }
        postInvalidate();
      }
      
      public void onAnimationStart(Animator paramAnimator)
      {
        InkPageIndicator.startIfReady(InkPageIndicator.this);
        paramAnimator = paramJ;
        int j = paramAnimator.length;
        int i = 0;
        while (i < j)
        {
          int k = paramAnimator[i];
          InkPageIndicator.add(InkPageIndicator.this, k, 1.0E-5F);
          i += 1;
        }
        InkPageIndicator.append(InkPageIndicator.this, f1);
        InkPageIndicator.set(InkPageIndicator.this, f3);
        if (Build.VERSION.SDK_INT >= 16)
        {
          postInvalidateOnAnimation();
          return;
        }
        postInvalidate();
      }
    }
  }
  
  public class g
    extends InkPageIndicator.h
  {
    private int o;
    
    public g(int paramInt, InkPageIndicator.j paramJ)
    {
      super(paramJ);
      setFloatValues(new float[] { 1.0E-5F, 1.0F });
      o = paramInt;
      setDuration(InkPageIndicator.calculate(InkPageIndicator.this));
      setInterpolator(InkPageIndicator.getInterpolator(InkPageIndicator.this));
      addUpdateListener(new a(InkPageIndicator.this));
      addListener(new b(InkPageIndicator.this));
    }
    
    class a
      implements ValueAnimator.AnimatorUpdateListener
    {
      a(InkPageIndicator paramInkPageIndicator) {}
      
      public void onAnimationUpdate(ValueAnimator paramValueAnimator)
      {
        InkPageIndicator.g localG = InkPageIndicator.g.this;
        InkPageIndicator.add(mView, InkPageIndicator.g.d(localG), ((Float)paramValueAnimator.getAnimatedValue()).floatValue());
      }
    }
    
    class b
      extends AnimatorListenerAdapter
    {
      b(InkPageIndicator paramInkPageIndicator) {}
      
      public void onAnimationEnd(Animator paramAnimator)
      {
        paramAnimator = InkPageIndicator.g.this;
        InkPageIndicator.add(mView, InkPageIndicator.g.d(paramAnimator), 0.0F);
        if (Build.VERSION.SDK_INT >= 16)
        {
          postInvalidateOnAnimation();
          return;
        }
        postInvalidate();
      }
    }
  }
  
  public abstract class h
    extends ValueAnimator
  {
    protected boolean c;
    protected InkPageIndicator.j j;
    
    public h(InkPageIndicator.j paramJ)
    {
      j = paramJ;
      c = false;
    }
    
    public void add(float paramFloat)
    {
      if ((!c) && (j.a(paramFloat)))
      {
        start();
        c = true;
      }
    }
  }
  
  public class i
    extends InkPageIndicator.j
  {
    public i(float paramFloat)
    {
      super(paramFloat);
    }
    
    boolean a(float paramFloat)
    {
      return paramFloat > d;
    }
  }
  
  public abstract class j
  {
    protected float d;
    
    public j(float paramFloat)
    {
      d = paramFloat;
    }
    
    abstract boolean a(float paramFloat);
  }
}
