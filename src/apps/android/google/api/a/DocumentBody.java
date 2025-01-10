package apps.android.google.api.a;

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
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "translationX", new float[] { paramView.getMeasuredWidth() + paramView.getWidth(), -1.04123597E9F, 1.09261619E9F, 0.0F }), ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 0.0F, 1.0F, 1.0F, 1.0F }) });
  }
}
