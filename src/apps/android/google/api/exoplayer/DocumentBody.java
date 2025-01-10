package apps.android.google.api.exoplayer;

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
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "scaleX", new float[] { 1.0F, 0.9F, 0.9F, 1.1F, 1.1F, 1.1F, 1.1F, 1.1F, 1.1F, 1.0F }), ObjectAnimator.ofFloat(paramView, "scaleY", new float[] { 1.0F, 0.9F, 0.9F, 1.1F, 1.1F, 1.1F, 1.1F, 1.1F, 1.1F, 1.0F }), ObjectAnimator.ofFloat(paramView, "rotation", new float[] { 0.0F, -3.0F, -3.0F, 3.0F, -3.0F, 3.0F, -3.0F, 3.0F, -3.0F, 0.0F }) });
  }
}
