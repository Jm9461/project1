package android.support.design.widget;

import android.graphics.drawable.Drawable;

public abstract interface ShadowViewDelegate
{
  public abstract float getRadius();
  
  public abstract void setBackgroundDrawable(Drawable paramDrawable);
  
  public abstract boolean setBackgroundDrawable();
  
  public abstract void setShadowPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}
