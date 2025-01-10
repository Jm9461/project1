package android.support.v4.app;

import android.animation.Animator;

class Animation
{
  public final Animator mActivity;
  public final android.view.animation.Animation mView;
  
  Animation(Animator paramAnimator)
  {
    mView = null;
    mActivity = paramAnimator;
    if (paramAnimator != null) {
      return;
    }
    throw new IllegalStateException("Animator cannot be null");
  }
  
  Animation(android.view.animation.Animation paramAnimation)
  {
    mView = paramAnimation;
    mActivity = null;
    if (paramAnimation != null) {
      return;
    }
    throw new IllegalStateException("Animation cannot be null");
  }
}
