package apps.android.google.api.exoplayer;

import android.view.View;
import apps.android.google.api.Object;
import apps.v4.animation.Animator;
import apps.v4.animation.AnimatorSet;
import apps.v4.animation.ObjectAnimator;

public class TrackRenderer
  extends Object
{
  public TrackRenderer() {}
  
  public void prepare(View paramView)
  {
    float f1 = (paramView.getWidth() - paramView.getPaddingLeft() - paramView.getPaddingRight()) / 2 + paramView.getPaddingLeft();
    float f2 = paramView.getHeight() - paramView.getPaddingBottom();
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "rotation", new float[] { 12.0F, -12.0F, 3.0F, -3.0F, 0.0F }), ObjectAnimator.ofFloat(paramView, "pivotX", new float[] { f1, f1, f1, f1, f1 }), ObjectAnimator.ofFloat(paramView, "pivotY", new float[] { f2, f2, f2, f2, f2 }) });
  }
}
