package apps.android.google.api.dummy;

import android.view.View;
import apps.android.google.api.Object;
import apps.v4.animation.Animator;
import apps.v4.animation.AnimatorSet;
import apps.v4.animation.ObjectAnimator;

public class SingleSampleSource
  extends Object
{
  public SingleSampleSource() {}
  
  public void prepare(View paramView)
  {
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "rotationX", new float[] { 90.0F, -15.0F, 15.0F, 0.0F }), ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 0.25F, 0.5F, 0.75F, 1.0F }) });
  }
}
