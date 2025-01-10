package me.zhanghai.android.materialprogressbar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.DisplayMetrics;

class BaseSingleHorizontalProgressDrawable
  extends BaseProgressDrawable
{
  private static final int PADDED_INTRINSIC_HEIGHT_DP = 16;
  private static final int PROGRESS_INTRINSIC_HEIGHT_DP = 4;
  protected static final RectF RECT_BOUND = new RectF(-180.0F, -1.0F, 180.0F, 1.0F);
  private static final RectF RECT_PADDED_BOUND = new RectF(-180.0F, -4.0F, 180.0F, 4.0F);
  private int mPaddedIntrinsicHeight;
  private int mProgressIntrinsicHeight;
  
  public BaseSingleHorizontalProgressDrawable(Context paramContext)
  {
    float f = getResourcesgetDisplayMetricsdensity;
    mProgressIntrinsicHeight = Math.round(4.0F * f);
    mPaddedIntrinsicHeight = Math.round(16.0F * f);
  }
  
  public int getIntrinsicHeight()
  {
    if (mUseIntrinsicPadding) {
      return mPaddedIntrinsicHeight;
    }
    return mProgressIntrinsicHeight;
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
    onDrawRect(paramCanvas, paramPaint);
  }
  
  protected void onDrawRect(Canvas paramCanvas, Paint paramPaint)
  {
    paramCanvas.drawRect(RECT_BOUND, paramPaint);
  }
  
  protected void onPreparePaint(Paint paramPaint)
  {
    paramPaint.setStyle(Paint.Style.FILL);
  }
}
