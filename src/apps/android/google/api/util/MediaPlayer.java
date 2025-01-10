package apps.android.google.api.util;

import android.view.View;
import apps.android.google.api.Object;
import apps.v4.animation.Animator;
import apps.v4.animation.AnimatorSet;
import apps.v4.animation.ObjectAnimator;

public class MediaPlayer
  extends Object
{
  public MediaPlayer() {}
  
  public void prepare(View paramView)
  {
    float f1 = paramView.getWidth() - paramView.getPaddingRight();
    float f2 = paramView.getHeight() - paramView.getPaddingBottom();
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "rotation", new float[] { -90.0F, 0.0F }), ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 0.0F, 1.0F }), ObjectAnimator.ofFloat(paramView, "pivotX", new float[] { f1, f1 }), ObjectAnimator.ofFloat(paramView, "pivotY", new float[] { f2, f2 }) });
  }
}
