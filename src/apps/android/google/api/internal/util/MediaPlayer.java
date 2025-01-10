package apps.android.google.api.internal.util;

import android.view.View;
import apps.android.common.Handler;
import apps.android.common.c;
import apps.android.google.api.Object;
import apps.v4.animation.Animator;
import apps.v4.animation.AnimatorSet;
import apps.v4.animation.ObjectAnimator;

public class MediaPlayer
  extends Object
{
  public MediaPlayer() {}
  
  protected void prepare(View paramView)
  {
    int i = paramView.getTop();
    int j = paramView.getHeight();
    AnimatorSet localAnimatorSet = getItem();
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 0.0F, 1.0F });
    c localC = c.i;
    float f = (float)length();
    paramView = ObjectAnimator.ofFloat(paramView, "translationY", new float[] { -(i + j), 0.0F });
    Handler.a(localC, f, paramView);
    localAnimatorSet.playTogether(new Animator[] { localObjectAnimator, paramView });
  }
}
