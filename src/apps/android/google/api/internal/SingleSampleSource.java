package apps.android.google.api.internal;

import android.view.View;
import apps.android.common.Handler;
import apps.android.common.c;
import apps.android.google.api.Object;
import apps.v4.animation.Animator;
import apps.v4.animation.AnimatorSet;
import apps.v4.animation.ObjectAnimator;

public class SingleSampleSource
  extends Object
{
  public SingleSampleSource() {}
  
  public void prepare(View paramView)
  {
    float f1 = paramView.getPaddingLeft();
    float f2 = paramView.getPaddingTop();
    AnimatorSet localAnimatorSet = getItem();
    c localC = c.startOffset;
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView, "rotation", new float[] { 0.0F, 80.0F, 60.0F, 80.0F, 60.0F, 60.0F });
    Handler.a(localC, 1300.0F, localObjectAnimator);
    localAnimatorSet.playTogether(new Animator[] { localObjectAnimator, ObjectAnimator.ofFloat(paramView, "translationY", new float[] { 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 700.0F }), ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F }), ObjectAnimator.ofFloat(paramView, "pivotX", new float[] { f1, f1, f1, f1, f1, f1 }), ObjectAnimator.ofFloat(paramView, "pivotY", new float[] { f2, f2, f2, f2, f2, f2 }) });
    add(1300L);
  }
}
