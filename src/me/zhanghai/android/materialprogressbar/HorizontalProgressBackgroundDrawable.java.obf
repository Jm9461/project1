package me.zhanghai.android.materialprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

class HorizontalProgressBackgroundDrawable
  extends BaseSingleHorizontalProgressDrawable
  implements ShowBackgroundDrawable
{
  private boolean mShow = true;
  
  public HorizontalProgressBackgroundDrawable(Context paramContext)
  {
    super(paramContext);
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (mShow) {
      super.draw(paramCanvas);
    }
  }
  
  public boolean getShowBackground()
  {
    return mShow;
  }
  
  public void setShowBackground(boolean paramBoolean)
  {
    if (mShow != paramBoolean)
    {
      mShow = paramBoolean;
      invalidateSelf();
    }
  }
}
