package apps.android.google.api.a;

import android.view.View;
import apps.android.google.api.Object;
import apps.v4.animation.Animator;
import apps.v4.animation.AnimatorSet;
import apps.v4.animation.ObjectAnimator;

public class l
  extends Object
{
  public l() {}
  
  public void prepare(View paramView)
  {
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 0.0F, 1.0F, 1.0F, 1.0F }), ObjectAnimator.ofFloat(paramView, "translationY", new float[] { -paramView.getHeight(), 1.10624768E9F, -1.05486746E9F, 0.0F }) });
  }
}
