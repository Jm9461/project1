package me.zhanghai.android.materialprogressbar;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

class SingleCircularProgressDrawable
  extends BaseSingleCircularProgressDrawable
  implements ShowBackgroundDrawable
{
  private static final int LEVEL_MAX = 10000;
  private static final float START_ANGLE_MAX_DYNAMIC = 360.0F;
  private static final float START_ANGLE_MAX_NORMAL = 0.0F;
  private static final float SWEEP_ANGLE_MAX = 360.0F;
  private boolean mShowBackground;
  private final float mStartAngleMax;
  
  SingleCircularProgressDrawable(int paramInt)
  {
    if (paramInt != 0)
    {
      if (paramInt == 1)
      {
        mStartAngleMax = 360.0F;
        return;
      }
      throw new IllegalArgumentException("Invalid value for style");
    }
    mStartAngleMax = 0.0F;
  }
  
  public boolean getShowBackground()
  {
    return mShowBackground;
  }
  
  protected void onDrawRing(Canvas paramCanvas, Paint paramPaint)
  {
    int i = getLevel();
    if (i == 0) {
      return;
    }
    float f2 = i / 10000.0F;
    float f1 = mStartAngleMax * f2;
    f2 = 360.0F * f2;
    drawRing(paramCanvas, paramPaint, f1, f2);
    if (mShowBackground) {
      drawRing(paramCanvas, paramPaint, f1, f2);
    }
  }
  
  protected boolean onLevelChange(int paramInt)
  {
    invalidateSelf();
    return true;
  }
  
  public void setShowBackground(boolean paramBoolean)
  {
    if (mShowBackground != paramBoolean)
    {
      mShowBackground = paramBoolean;
      invalidateSelf();
    }
  }
}
