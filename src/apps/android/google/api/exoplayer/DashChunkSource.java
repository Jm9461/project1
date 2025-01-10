package apps.android.google.api.exoplayer;

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
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "scaleY", new float[] { 1.0F, 1.1F, 1.0F }), ObjectAnimator.ofFloat(paramView, "scaleX", new float[] { 1.0F, 1.1F, 1.0F }) });
  }
}
