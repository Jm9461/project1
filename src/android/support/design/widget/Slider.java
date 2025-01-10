package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

abstract class Slider
  extends AnimatorListenerAdapter
  implements ValueAnimator.AnimatorUpdateListener
{
  private boolean value;
  private float x;
  private float y;
  
  private Slider(FloatingActionButtonImpl paramFloatingActionButtonImpl) {}
  
  protected abstract float getElevation();
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    this$0.mShadowDrawable.setShadowSize(y);
    value = false;
  }
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    if (!value)
    {
      x = this$0.mShadowDrawable.getShadowSize();
      y = getElevation();
      value = true;
    }
    ShadowDrawableWrapper localShadowDrawableWrapper = this$0.mShadowDrawable;
    float f = x;
    localShadowDrawableWrapper.setShadowSize(f + (y - f) * paramValueAnimator.getAnimatedFraction());
  }
}
