package apps.android.google.api.exoplayer;

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
    double d = paramView.getWidth();
    Double.isNaN(d);
    float f = (float)(d / 100.0D);
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "translationX", new float[] { f * 0.0F, -25.0F * f, 20.0F * f, -15.0F * f, 10.0F * f, -5.0F * f, f * 0.0F, 0.0F }), ObjectAnimator.ofFloat(paramView, "rotation", new float[] { 0.0F, -5.0F, 3.0F, -3.0F, 2.0F, -1.0F, 0.0F }) });
  }
}
