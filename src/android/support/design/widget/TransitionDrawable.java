package android.support.design.widget;

import android.graphics.drawable.GradientDrawable;

class TransitionDrawable
  extends GradientDrawable
{
  TransitionDrawable() {}
  
  public boolean isStateful()
  {
    return true;
  }
}
