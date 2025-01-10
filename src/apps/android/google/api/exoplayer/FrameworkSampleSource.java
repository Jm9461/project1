package apps.android.google.api.exoplayer;

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
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "scaleX", new float[] { 1.0F, 1.25F, 0.75F, 1.15F, 1.0F }), ObjectAnimator.ofFloat(paramView, "scaleY", new float[] { 1.0F, 0.75F, 1.25F, 0.85F, 1.0F }) });
  }
}
