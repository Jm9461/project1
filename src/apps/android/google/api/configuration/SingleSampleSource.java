package apps.android.google.api.configuration;

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
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "scaleX", new float[] { 0.1F, 0.475F, 1.0F }), ObjectAnimator.ofFloat(paramView, "scaleY", new float[] { 0.1F, 0.475F, 1.0F }), ObjectAnimator.ofFloat(paramView, "translationX", new float[] { -paramView.getRight(), 48.0F, 0.0F }), ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 0.0F, 1.0F, 1.0F }) });
  }
}