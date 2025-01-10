package ch.acra.widget;

import android.os.Build.VERSION;
import android.view.View;
import apps.v4.animation.Animator;
import apps.v4.animation.Animator.AnimatorListener;
import apps.v4.animation.ObjectAnimator;
import java.lang.ref.WeakReference;

public class ViewAnimationUtils
{
  private static final boolean LOLLIPOP_PLUS;
  
  static
  {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 21) {
      bool = true;
    } else {
      bool = false;
    }
    LOLLIPOP_PLUS = bool;
  }
  
  public static ValueAnimatorCompat.Impl createCircularReveal(View paramView, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2)
  {
    if ((paramView.getParent() instanceof RevealAnimator))
    {
      RevealAnimator localRevealAnimator = (RevealAnimator)paramView.getParent();
      localRevealAnimator.setTarget(new Target(paramInt1, paramInt2, paramFloat1, paramFloat2, new WeakReference(paramView)));
      if (LOLLIPOP_PLUS) {
        return new ValueAnimatorCompatImplHoneycombMr1(android.view.ViewAnimationUtils.createCircularReveal(paramView, paramInt1, paramInt2, paramFloat1, paramFloat2), localRevealAnimator);
      }
      paramView = ObjectAnimator.ofFloat(localRevealAnimator, RevealAnimator.LOLLIPOP_PLUS, new float[] { paramFloat1, paramFloat2 });
      paramView.addListener(getRevealFinishListener(localRevealAnimator));
      return new ViewPropertyAnimatorCompat(paramView, localRevealAnimator);
    }
    throw new IllegalArgumentException("View must be inside RevealFrameLayout or RevealLinearLayout.");
  }
  
  private static Animator.AnimatorListener getRevealFinishListener(RevealAnimator paramRevealAnimator)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 18) {
      return new ImageHelper(paramRevealAnimator);
    }
    if (i >= 14) {
      return new AbsActionBarView.VisibilityAnimListener(paramRevealAnimator);
    }
    return new ActionBarContextView(paramRevealAnimator);
  }
}
