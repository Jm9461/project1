package org.org.asm;

import android.animation.Animator;

abstract interface Identity
{
  public abstract void onAnimationPause(Animator paramAnimator);
  
  public abstract void onAnimationResume(Animator paramAnimator);
}
