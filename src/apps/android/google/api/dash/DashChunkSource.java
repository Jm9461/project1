package apps.android.google.api.dash;

import android.view.View;
import apps.android.google.api.Object;
import apps.v4.animation.Animator;
import apps.v4.animation.AnimatorSet;
import apps.v4.animation.ObjectAnimator;

public class DashChunkSource
  extends Object
{
  public DashChunkSource() {}
  
  public void prepare(View paramView)
  {
    int i = paramView.getTop();
    int j = paramView.getHeight();
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 0.0F, 1.0F }), ObjectAnimator.ofFloat(paramView, "translationY", new float[] { -(i + j), 0.0F }) });
  }
}
