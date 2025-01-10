package me.zhanghai.android.materialprogressbar;

import android.animation.Animator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import me.zhanghai.android.materialprogressbar.internal.ThemeUtils;

public class IndeterminateHorizontalProgressDrawable
  extends BaseIndeterminateProgressDrawable
  implements MaterialProgressDrawable, ShowBackgroundDrawable
{
  private static final int PADDED_INTRINSIC_HEIGHT_DP = 16;
  private static final int PROGRESS_INTRINSIC_HEIGHT_DP = 4;
  private static final RectTransformX RECT_1_TRANSFORM_X = new RectTransformX(-522.6F, 0.1F);
  private static final RectTransformX RECT_2_TRANSFORM_X = new RectTransformX(-197.6F, 0.1F);
  private static final RectF RECT_BOUND = new RectF(-180.0F, -1.0F, 180.0F, 1.0F);
  private static final RectF RECT_PADDED_BOUND = new RectF(-180.0F, -4.0F, 180.0F, 4.0F);
  private static final RectF RECT_PROGRESS = new RectF(-144.0F, -1.0F, 144.0F, 1.0F);
  private float mBackgroundAlpha;
  private int mPaddedIntrinsicHeight;
  private int mProgressIntrinsicHeight;
  private RectTransformX mRect1TransformX = new RectTransformX(RECT_1_TRANSFORM_X);
  private RectTransformX mRect2TransformX = new RectTransformX(RECT_2_TRANSFORM_X);
  private boolean mShowBackground = true;
  
  public IndeterminateHorizontalProgressDrawable(Context paramContext)
  {
    super(paramContext);
    float f = getResourcesgetDisplayMetricsdensity;
    mProgressIntrinsicHeight = Math.round(4.0F * f);
    mPaddedIntrinsicHeight = Math.round(16.0F * f);
    mBackgroundAlpha = ThemeUtils.getFloatFromAttrRes(16842803, 0.0F, paramContext);
    mAnimators = new Animator[] { Animators.createIndeterminateHorizontalRect1(mRect1TransformX), Animators.createIndeterminateHorizontalRect2(mRect2TransformX) };
  }
  
  private static void drawBackgroundRect(Canvas paramCanvas, Paint paramPaint)
  {
    paramCanvas.drawRect(RECT_BOUND, paramPaint);
  }
  
  private static void drawProgressRect(Canvas paramCanvas, RectTransformX paramRectTransformX, Paint paramPaint)
  {
    int i = paramCanvas.save();
    paramCanvas.translate(mTranslateX, 0.0F);
    paramCanvas.scale(mScaleX, 1.0F);
    paramCanvas.drawRect(RECT_PROGRESS, paramPaint);
    paramCanvas.restoreToCount(i);
  }
  
  public int getIntrinsicHeight()
  {
    if (mUseIntrinsicPadding) {
      return mPaddedIntrinsicHeight;
    }
    return mProgressIntrinsicHeight;
  }
  
  public boolean getShowBackground()
  {
    return mShowBackground;
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
    if (mShowBackground)
    {
      paramPaint.setAlpha(Math.round(mAlpha * mBackgroundAlpha));
      drawBackgroundRect(paramCanvas, paramPaint);
      paramPaint.setAlpha(mAlpha);
    }
    drawProgressRect(paramCanvas, mRect2TransformX, paramPaint);
    drawProgressRect(paramCanvas, mRect1TransformX, paramPaint);
  }
  
  protected void onPreparePaint(Paint paramPaint)
  {
    paramPaint.setStyle(Paint.Style.FILL);
  }
  
  public void setShowBackground(boolean paramBoolean)
  {
    if (mShowBackground != paramBoolean)
    {
      mShowBackground = paramBoolean;
      invalidateSelf();
    }
  }
  
  private static class RectTransformX
  {
    public float mScaleX;
    public float mTranslateX;
    
    public RectTransformX(float paramFloat1, float paramFloat2)
    {
      mTranslateX = paramFloat1;
      mScaleX = paramFloat2;
    }
    
    public RectTransformX(RectTransformX paramRectTransformX)
    {
      mTranslateX = mTranslateX;
      mScaleX = mScaleX;
    }
    
    public void setScaleX(float paramFloat)
    {
      mScaleX = paramFloat;
    }
    
    public void setTranslateX(float paramFloat)
    {
      mTranslateX = paramFloat;
    }
  }
}
