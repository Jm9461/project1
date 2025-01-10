package me.zhanghai.android.materialprogressbar;

import android.graphics.Canvas;
import android.graphics.Paint;

abstract class BasePaintDrawable
  extends BaseDrawable
{
  private Paint mPaint;
  
  BasePaintDrawable() {}
  
  protected final void onDraw(Canvas paramCanvas, int paramInt1, int paramInt2)
  {
    if (mPaint == null)
    {
      mPaint = new Paint();
      mPaint.setAntiAlias(true);
      mPaint.setColor(-16777216);
      onPreparePaint(mPaint);
    }
    mPaint.setAlpha(mAlpha);
    mPaint.setColorFilter(getColorFilterForDrawing());
    onDraw(paramCanvas, paramInt1, paramInt2, mPaint);
  }
  
  protected abstract void onDraw(Canvas paramCanvas, int paramInt1, int paramInt2, Paint paramPaint);
  
  protected abstract void onPreparePaint(Paint paramPaint);
}
