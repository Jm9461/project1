package android.support.design.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.view.View;

class ClassWriter
  extends GradientDrawable
{
  private final RectF f;
  private final Paint paint = new Paint(1);
  private int s;
  
  ClassWriter()
  {
    init();
    f = new RectF();
  }
  
  private void a(Canvas paramCanvas)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      s = paramCanvas.saveLayer(0.0F, 0.0F, paramCanvas.getWidth(), paramCanvas.getHeight(), null);
      return;
    }
    s = paramCanvas.saveLayer(0.0F, 0.0F, paramCanvas.getWidth(), paramCanvas.getHeight(), null, 31);
  }
  
  private void b(Canvas paramCanvas)
  {
    Drawable.Callback localCallback = getCallback();
    if (get(localCallback))
    {
      ((View)localCallback).setLayerType(2, null);
      return;
    }
    a(paramCanvas);
  }
  
  private void c(Canvas paramCanvas)
  {
    if (!get(getCallback())) {
      paramCanvas.restoreToCount(s);
    }
  }
  
  private boolean get(Drawable.Callback paramCallback)
  {
    return paramCallback instanceof View;
  }
  
  private void init()
  {
    paint.setStyle(Paint.Style.FILL_AND_STROKE);
    paint.setColor(-1);
    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
  }
  
  void a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    RectF localRectF = f;
    if ((paramFloat1 != left) || (paramFloat2 != top) || (paramFloat3 != right) || (paramFloat4 != bottom))
    {
      f.set(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
      invalidateSelf();
    }
  }
  
  void a(RectF paramRectF)
  {
    a(left, top, right, bottom);
  }
  
  boolean a()
  {
    return f.isEmpty() ^ true;
  }
  
  void c()
  {
    a(0.0F, 0.0F, 0.0F, 0.0F);
  }
  
  public void draw(Canvas paramCanvas)
  {
    b(paramCanvas);
    super.draw(paramCanvas);
    paramCanvas.drawRect(f, paint);
    c(paramCanvas);
  }
}
