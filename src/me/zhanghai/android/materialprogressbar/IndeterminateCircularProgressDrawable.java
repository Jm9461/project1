package me.zhanghai.android.materialprogressbar;

import android.animation.Animator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.DisplayMetrics;

public class IndeterminateCircularProgressDrawable
  extends BaseIndeterminateProgressDrawable
  implements MaterialProgressDrawable
{
  private static final int PADDED_INTRINSIC_SIZE_DP = 48;
  private static final int PROGRESS_INTRINSIC_SIZE_DP = 42;
  private static final RectF RECT_BOUND = new RectF(-21.0F, -21.0F, 21.0F, 21.0F);
  private static final RectF RECT_PADDED_BOUND = new RectF(-24.0F, -24.0F, 24.0F, 24.0F);
  private static final RectF RECT_PROGRESS = new RectF(-19.0F, -19.0F, 19.0F, 19.0F);
  private int mPaddedIntrinsicSize;
  private int mProgressIntrinsicSize;
  private RingPathTransform mRingPathTransform = new RingPathTransform(null);
  private RingRotation mRingRotation = new RingRotation(null);
  
  public IndeterminateCircularProgressDrawable(Context paramContext)
  {
    super(paramContext);
    float f = getResourcesgetDisplayMetricsdensity;
    mProgressIntrinsicSize = Math.round(42.0F * f);
    mPaddedIntrinsicSize = Math.round(48.0F * f);
    mAnimators = new Animator[] { Animators.createIndeterminate(mRingPathTransform), Animators.createIndeterminateRotation(mRingRotation) };
  }
  
  private void drawRing(Canvas paramCanvas, Paint paramPaint)
  {
    int i = paramCanvas.save();
    paramCanvas.rotate(mRingRotation.mRotation);
    RingPathTransform localRingPathTransform = mRingPathTransform;
    float f1 = mTrimPathOffset;
    float f2 = mTrimPathStart;
    float f3 = mTrimPathEnd;
    paramCanvas.drawArc(RECT_PROGRESS, (f1 + f2) * 360.0F - 90.0F, (f3 - f2) * 360.0F, false, paramPaint);
    paramCanvas.restoreToCount(i);
  }
  
  private int getIntrinsicSize()
  {
    if (mUseIntrinsicPadding) {
      return mPaddedIntrinsicSize;
    }
    return mProgressIntrinsicSize;
  }
  
  public int getIntrinsicHeight()
  {
    return getIntrinsicSize();
  }
  
  public int getIntrinsicWidth()
  {
    return getIntrinsicSize();
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
    drawRing(paramCanvas, paramPaint);
  }
  
  protected void onPreparePaint(Paint paramPaint)
  {
    paramPaint.setStyle(Paint.Style.STROKE);
    paramPaint.setStrokeWidth(4.0F);
    paramPaint.setStrokeCap(Paint.Cap.SQUARE);
    paramPaint.setStrokeJoin(Paint.Join.MITER);
  }
  
  private static class RingPathTransform
  {
    public float mTrimPathEnd;
    public float mTrimPathOffset;
    public float mTrimPathStart;
    
    private RingPathTransform() {}
    
    public void setTrimPathEnd(float paramFloat)
    {
      mTrimPathEnd = paramFloat;
    }
    
    public void setTrimPathOffset(float paramFloat)
    {
      mTrimPathOffset = paramFloat;
    }
    
    public void setTrimPathStart(float paramFloat)
    {
      mTrimPathStart = paramFloat;
    }
  }
  
  private static class RingRotation
  {
    private float mRotation;
    
    private RingRotation() {}
    
    public void setRotation(float paramFloat)
    {
      mRotation = paramFloat;
    }
  }
}
