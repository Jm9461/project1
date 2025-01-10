package ch.acra.widget;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

@TargetApi(11)
final class ValueAnimatorCompatImplHoneycombMr1
  extends ValueAnimatorCompat.Impl
{
  WeakReference<Animator> mView;
  
  ValueAnimatorCompatImplHoneycombMr1(Animator paramAnimator, RevealAnimator paramRevealAnimator)
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
    localAnimator.addListener(new HoneycombMr1AnimatorCompatProvider.AnimatorListenerCompatWrapper(this, paramAnimatorListenerCompat));
  }
}
