package me.zhanghai.android.materialprogressbar;

import android.graphics.drawable.Drawable;

abstract class BaseProgressDrawable
  extends BasePaintDrawable
  implements IntrinsicPaddingDrawable
{
  protected boolean mUseIntrinsicPadding = true;
  
  BaseProgressDrawable() {}
  
  public boolean getUseIntrinsicPadding()
  {
    return mUseIntrinsicPadding;
  }
  
  public void setUseIntrinsicPadding(boolean paramBoolean)
  {
    if (mUseIntrinsicPadding != paramBoolean)
    {
      mUseIntrinsicPadding = paramBoolean;
      invalidateSelf();
    }
  }
}
