package ch.acra.widget;

import android.view.animation.Interpolator;
import apps.v4.animation.Animator;
import b.h.a.a;
import java.lang.ref.WeakReference;

final class ViewPropertyAnimatorCompat
  extends ValueAnimatorCompat.Impl
{
  WeakReference<a> mView;
  
  ViewPropertyAnimatorCompat(Animator paramAnimator, RevealAnimator paramRevealAnimator)
  {
    super(paramRevealAnimator);
    mView = new WeakReference(paramAnimator);
  }
  
  public void setDuration(int paramInt)
  {
    Animator localAnimator = (Animator)mView.get();
    if (localAnimator != null) {
      localAnimator.setDuration(paramInt);
    }
  }
  
  public void setInterpolator(Interpolator paramInterpolator)
  {
    Animator localAnimator = (Animator)mView.get();
    if (localAnimator != null) {
      localAnimator.setInterpolator(paramInterpolator);
    }
  }
  
  public void start()
  {
    Animator localAnimator = (Animator)mView.get();
    if (localAnimator != null) {
      localAnimator.start();
    }
  }
  
  public void start(AnimatorListenerCompat paramAnimatorListenerCompat)
  {
    Animator localAnimator = (Animator)mView.get();
    if (localAnimator == null) {
      return;
    }
    if (paramAnimatorListenerCompat == null)
    {
      localAnimator.addListener(null);
      return;
    }
    localAnimator.addListener(new AnimatorSet.DependencyListener(this, paramAnimatorListenerCompat));
  }
}
