package apps.android.google.api.dash;

import android.view.View;
import android.view.ViewGroup;
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
    int i = ((ViewGroup)paramView.getParent()).getWidth();
    int j = paramView.getLeft();
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 1.0F, 0.0F }), ObjectAnimator.ofFloat(paramView, "translationX", new float[] { 0.0F, i - j }) });
  }
}
