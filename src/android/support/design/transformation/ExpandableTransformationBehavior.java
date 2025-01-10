package android.support.design.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public abstract class ExpandableTransformationBehavior
  extends ExpandableBehavior
{
  private AnimatorSet animator;
  
  public ExpandableTransformationBehavior() {}
  
  public ExpandableTransformationBehavior(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected abstract AnimatorSet a(View paramView1, View paramView2, boolean paramBoolean1, boolean paramBoolean2);
  
  protected boolean setChecked(View paramView1, View paramView2, boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool;
    if (animator != null) {
      bool = true;
    } else {
      bool = false;
    }
    if (bool) {
      animator.cancel();
    }
    animator = a(paramView1, paramView2, paramBoolean1, bool);
    animator.addListener(new a());
    animator.start();
    if (!paramBoolean2) {
      animator.end();
    }
    return true;
  }
  
  class a
    extends AnimatorListenerAdapter
  {
    a() {}
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      ExpandableTransformationBehavior.access$setRunningAnimation(ExpandableTransformationBehavior.this, null);
    }
  }
}
