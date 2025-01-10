package me.zhanghai.android.materialprogressbar;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;

abstract class BaseSingleCircularProgressDrawable
  extends BaseProgressDrawable
{
  private static final RectF RECT_BOUND = new RectF(-21.0F, -21.0F, 21.0F, 21.0F);
  private static final RectF RECT_PADDED_BOUND = new RectF(-24.0F, -24.0F, 24.0F, 24.0F);
  private static final RectF RECT_PROGRESS = new RectF(-19.0F, -19.0F, 19.0F, 19.0F);
  
  BaseSingleCircularProgressDrawable() {}
  
  protected void drawRing(Canvas paramCanvas, Paint paramPaint, float paramFloat1, float paramFloat2)
  {
    paramCanvas.drawArc(RECT_PROGRESS, paramFloat1 - 90.0F, paramFloat2, false, paramPaint);
  }
  
  protected void onDraw(Canvas paramCanvas, int paramInt1, int paramInt2, Paint paramPaint)
  {
    if (mUseIntrinsicPadding)
    {
      paramCanvas.scale(paramInt1 / RECT_PADDED_BOUND.width(), paramInt2 / RECT_PADDED_BOUND.height());
      paramCanvas.translate(RECT_PADDED_BOUND.width() / 2.0F, RECT_PADDED_BOUND.height() / 2.0F);
    }
    else
    {
      paramCanvas.scale(paramInt1 / RECT_BOUND.width(), paramInt2 / RECT_BOUND.height());
      paramCanvas.translate(RECT_BOUND.width() / 2.0F, RECT_BOUND.height() / 2.0F);
    }
    onDrawRing(paramCanvas, paramPaint);
  }
  
  protected abstract void onDrawRing(Canvas paramCanvas, Paint paramPaint);
  
  protected void onPreparePaint(Paint paramPaint)
  {
    paramPaint.setStyle(Paint.Style.STROKE);
    paramPaint.setStrokeWidth(4.0F);
  }
}
