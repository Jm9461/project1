package apps.android.google.api.exoplayer;

import android.view.View;
import apps.android.google.api.Object;
import apps.v4.animation.Animator;
import apps.v4.animation.AnimatorSet;
import apps.v4.animation.ObjectAnimator;

public class StreamWriter
  extends Object
{
  public StreamWriter() {}
  
  public void prepare(View paramView)
  {
    getItem().playTogether(new Animator[] { ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 1.0F, 0.0F, 1.0F, 0.0F, 1.0F }) });
  }
}
