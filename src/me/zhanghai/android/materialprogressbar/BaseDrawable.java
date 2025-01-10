package me.zhanghai.android.materialprogressbar;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;

abstract class BaseDrawable
  extends Drawable
  implements TintableDrawable
{
  protected int mAlpha = 255;
  protected ColorFilter mColorFilter;
  private DummyConstantState mConstantState = new DummyConstantState(null);
  protected PorterDuffColorFilter mTintFilter;
  protected ColorStateList mTintList;
  protected PorterDuff.Mode mTintMode = PorterDuff.Mode.SRC_IN;
  
  BaseDrawable() {}
  
  private boolean updateTintFilter()
  {
    ColorStateList localColorStateList = mTintList;
    boolean bool = true;
    if ((localColorStateList != null) && (mTintMode != null))
    {
      mTintFilter = new PorterDuffColorFilter(localColorStateList.getColorForState(getState(), 0), mTintMode);
      return true;
    }
    if (mTintFilter == null) {
      bool = false;
    }
    mTintFilter = null;
    return bool;
  }
  
  public void draw(Canvas paramCanvas)
  {
    Rect localRect = getBounds();
    if (localRect.width() != 0)
    {
      if (localRect.height() == 0) {
        return;
      }
      int i = paramCanvas.save();
      paramCanvas.translate(left, top);
      onDraw(paramCanvas, localRect.width(), localRect.height());
      paramCanvas.restoreToCount(i);
    }
  }
  
  public int getAlpha()
  {
    return mAlpha;
  }
  
  public ColorFilter getColorFilter()
  {
    return mColorFilter;
  }
  
  protected ColorFilter getColorFilterForDrawing()
  {
    ColorFilter localColorFilter = mColorFilter;
    if (localColorFilter != null) {
      return localColorFilter;
    }
    return mTintFilter;
  }
  
  public Drawable.ConstantState getConstantState()
  {
    return mConstantState;
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public boolean isStateful()
  {
    ColorStateList localColorStateList = mTintList;
    return (localColorStateList != null) && (localColorStateList.isStateful());
  }
  
  protected abstract void onDraw(Canvas paramCanvas, int paramInt1, int paramInt2);
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    return updateTintFilter();
  }
  
  public void setAlpha(int paramInt)
  {
    if (mAlpha != paramInt)
    {
      mAlpha = paramInt;
      invalidateSelf();
    }
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mColorFilter = paramColorFilter;
    invalidateSelf();
  }
  
  public void setTint(int paramInt)
  {
    setTintList(ColorStateList.valueOf(paramInt));
  }
  
  public void setTintList(ColorStateList paramColorStateList)
  {
    mTintList = paramColorStateList;
    if (updateTintFilter()) {
      invalidateSelf();
    }
  }
  
  public void setTintMode(PorterDuff.Mode paramMode)
  {
    mTintMode = paramMode;
    if (updateTintFilter()) {
      invalidateSelf();
    }
  }
  
  private class DummyConstantState
    extends Drawable.ConstantState
  {
    private DummyConstantState() {}
    
    public int getChangingConfigurations()
    {
      return 0;
    }
    
    public Drawable newDrawable()
    {
      return BaseDrawable.this;
    }
  }
}
