package apps.android.google.api.dash;

import android.view.View;
import apps.android.google.api.Object;
import apps.v4.animation.Animator;
import apps.v4.animation.AnimatorSet;
import apps.v4.animation.ObjectAnimator;

public class FrameworkSampleSource
  extends Object
{
  public FrameworkSampleSource() {}
  
  public void prepare(View paramView)
  {
    paramView.getParent();
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 1.0F, 0.0F }), ObjectAnimator.ofFloat(paramView, "translationY", new float[] { 0.0F, -paramView.getBottom() }) });
  }
}
