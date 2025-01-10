package apps.android.google.api.exoplayer;

import android.view.View;
import apps.android.google.api.Object;
import apps.v4.animation.Animator;
import apps.v4.animation.AnimatorSet;
import apps.v4.animation.ObjectAnimator;

public class ExoPlayerImpl
  extends Object
{
  public ExoPlayerImpl() {}
  
  public void prepare(View paramView)
  {
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "translationY", new float[] { 0.0F, 0.0F, -30.0F, 0.0F, -15.0F, 0.0F, 0.0F }) });
  }
}
