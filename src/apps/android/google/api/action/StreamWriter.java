package apps.android.google.api.action;

import android.view.View;
import apps.android.google.api.Object;
import apps.v4.animation.Animator;
import apps.v4.animation.AnimatorSet;
import apps.v4.animation.ObjectAnimator;

public class StreamWriter
  extends Object
{
  public StreamWriter() {}
  
  public void prepare(View paramView)
  {
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 1.0F, 0.0F }), ObjectAnimator.ofFloat(paramView, "translationX", new float[] { 0.0F, paramView.getWidth() / 4 }) });
  }
}