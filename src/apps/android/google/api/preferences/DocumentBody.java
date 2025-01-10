package apps.android.google.api.preferences;

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
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 0.0F, 1.0F }), ObjectAnimator.ofFloat(paramView, "translationY", new float[] { paramView.getHeight() / 4, 0.0F }) });
  }
}
