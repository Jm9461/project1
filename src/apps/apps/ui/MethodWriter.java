package apps.apps.ui;

import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import apps.apps.ui.action.Util;
import apps.apps.ui.adapters.a;
import apps.apps.ui.matcher.ByteVector;
import apps.apps.ui.matcher.b;
import apps.apps.ui.views.Item;
import java.text.ParseException;

public class MethodWriter
  extends View
{
  private int C;
  private int N;
  private Vec2 a;
  private Interpolator b;
  private a c;
  private int f;
  private int g;
  private int k;
  private int l;
  private String m;
  private Paint mFillPaint;
  private Item o;
  private Paint p;
  private int q;
  private int r;
  private long t;
  private int w;
  private int y;
  
  MethodWriter(ViewGroup paramViewGroup, ViewGroup.LayoutParams paramLayoutParams, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, Item paramItem, String paramString)
  {
    super(paramViewGroup.getContext());
    g = paramInt1;
    y = paramInt2;
    q = paramInt3;
    k = paramInt6;
    l = paramInt7;
    o = paramItem;
    r = paramInt4;
    w = paramInt5;
    m = paramString;
    b();
    paramViewGroup.addView(this, paramLayoutParams);
  }
  
  private PathEffect a(float paramFloat)
  {
    return new DashPathEffect(new float[] { paramFloat, a.y }, 0.0F);
  }
  
  private void a()
  {
    visitFrame();
    visitFieldInsn();
  }
  
  private void a(int paramInt)
  {
    if (f == paramInt) {
      return;
    }
    f = paramInt;
    a localA = c;
    if (localA != null) {
      localA.close(paramInt);
    }
  }
  
  private void b()
  {
    f = 0;
    init();
    drawPath();
    b = new DecelerateInterpolator();
    setLayerType(1, null);
  }
  
  private void drawPath()
  {
    mFillPaint = new Paint();
    mFillPaint.setAntiAlias(true);
    mFillPaint.setStyle(Paint.Style.FILL);
    mFillPaint.setColor(y);
  }
  
  private b getPathParser()
  {
    ByteVector localByteVector = new ByteVector();
    localByteVector.putShort(r);
    localByteVector.a(w);
    localByteVector.write(C);
    localByteVector.b(N);
    return localByteVector.a();
  }
  
  private void init()
  {
    p = new Paint();
    p.setStyle(Paint.Style.STROKE);
    p.setAntiAlias(true);
    p.setStrokeWidth(q);
    p.setColor(g);
  }
  
  private void initialize()
  {
    Object localObject = getPathParser();
    a = new Vec2();
    Vec2 localVec2 = a;
    String str = m;
    try
    {
      localObject = ((b)localObject).parsePath(str);
      x = ((Path)localObject);
    }
    catch (ParseException localParseException)
    {
      a.x = new Path();
    }
    PathMeasure localPathMeasure = new PathMeasure(a.x, true);
    do
    {
      localObject = a;
      y = Math.max(y, localPathMeasure.getLength());
    } while (localPathMeasure.nextContour());
  }
  
  private boolean next(long paramLong)
  {
    return paramLong < k + l;
  }
  
  private void visitFieldInsn()
  {
    if (a != null) {
      return;
    }
    throw new IllegalArgumentException("You must provide a not empty path in order to draw the view properly.");
  }
  
  private void visitFrame()
  {
    if ((r > 0) && (w > 0)) {
      return;
    }
    throw new IllegalArgumentException("You must provide the original image dimensions in order map the coordinates properly.");
  }
  
  public boolean a(long paramLong)
  {
    return paramLong > k;
  }
  
  public boolean c()
  {
    return (f != 0) && (a != null);
  }
  
  public void d()
  {
    a();
    t = System.currentTimeMillis();
    a(1);
    ViewCompat.postInvalidateOnAnimation(this);
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (!c()) {
      return;
    }
    long l1 = System.currentTimeMillis() - t;
    float f1 = Util.clamp(0.0F, 1.0F, (float)l1 * 1.0F / k);
    f1 = b.getInterpolation(f1);
    float f2 = a.y;
    p.setPathEffect(a(f1 * f2));
    paramCanvas.drawPath(a.x, p);
    if (a(l1))
    {
      if (f < 2) {
        a(2);
      }
      f1 = Util.clamp(0.0F, 1.0F, (float)(l1 - k) * 1.0F / l);
      o.a(paramCanvas, f1, this);
      paramCanvas.drawPath(a.x, mFillPaint);
    }
    if (next(l1))
    {
      ViewCompat.postInvalidateOnAnimation(this);
      return;
    }
    a(3);
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    C = paramInt1;
    N = paramInt2;
    initialize();
  }
  
  public void setClippingTransform(Item paramItem)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a3 = a2\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  public void setFillColor(int paramInt)
  {
    y = paramInt;
  }
  
  public void setFillDuration(int paramInt)
  {
    l = paramInt;
  }
  
  public void setOnStateChangeListener(a paramA)
  {
    c = paramA;
  }
  
  public void setStrokeColor(int paramInt)
  {
    g = paramInt;
  }
  
  public void setStrokeDrawingDuration(int paramInt)
  {
    k = paramInt;
  }
  
  public void setStrokeWidth(int paramInt)
  {
    q = paramInt;
  }
  
  public void setSvgPath(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      m = paramString;
      initialize();
      return;
    }
    throw new IllegalArgumentException("You must provide a not empty path in order to draw the view properly.");
  }
}
