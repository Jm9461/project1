package apps.android.google.api.dummy;

import android.view.View;
import apps.android.google.api.Object;
import apps.v4.animation.Animator;
import apps.v4.animation.AnimatorSet;
import apps.v4.animation.ObjectAnimator;

public class DocumentBody
  extends Object
{
  public DocumentBody() {}
  
  public void prepare(View paramView)
  {
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "rotationX", new float[] { 0.0F, 90.0F }), ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 1.0F, 0.0F }) });
  }
}