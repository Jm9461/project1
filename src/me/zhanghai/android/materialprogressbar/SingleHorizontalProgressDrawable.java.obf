package me.zhanghai.android.materialprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

class SingleHorizontalProgressDrawable
  extends BaseSingleHorizontalProgressDrawable
  implements ShowBackgroundDrawable
{
  private static final int LEVEL_MAX = 10000;
  private boolean mShowBackground;
  
  public SingleHorizontalProgressDrawable(Context paramContext)
  {
    super(paramContext);
  }
  
  public boolean getShowBackground()
  {
    return mShowBackground;
  }
  
  protected void onDrawRect(Canvas paramCanvas, Paint paramPaint)
  {
    int i = getLevel();
    if (i == 0) {
      return;
    }
    int j = paramCanvas.save();
    paramCanvas.scale(i / 10000.0F, 1.0F, RECT_BOUNDleft, 0.0F);
    super.onDrawRect(paramCanvas, paramPaint);
    if (mShowBackground) {
      super.onDrawRect(paramCanvas, paramPaint);
    }
    paramCanvas.restoreToCount(j);
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
