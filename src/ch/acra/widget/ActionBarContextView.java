package ch.acra.widget;

import apps.v4.animation.Animator;
import d.a.a.a;
import java.lang.ref.WeakReference;

public class ActionBarContextView
  extends ScrollingTabContainerView.VisibilityAnimListener
{
  WeakReference<a> mReference;
  
  ActionBarContextView(RevealAnimator paramRevealAnimator)
  {
    mReference = new WeakReference(paramRevealAnimator);
  }
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    ((RevealAnimator)mReference.get()).onRevealAnimationEnd();
  }
  
  public void onAnimationStart(Animator paramAnimator)
  {
    ((RevealAnimator)mReference.get()).onRevealAnimationStart();
  }
}
