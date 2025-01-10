package org.org.android.ui;

import android.graphics.drawable.Drawable;

public abstract interface List
  extends Collection
{
  public abstract void add();
  
  public abstract void clear();
  
  public abstract int getCircularRevealScrimColor();
  
  public abstract Label getRevealInfo();
  
  public abstract void setCircularRevealOverlayDrawable(Drawable paramDrawable);
  
  public abstract void setCircularRevealScrimColor(int paramInt);
  
  public abstract void setRevealInfo(Label paramLabel);
}
