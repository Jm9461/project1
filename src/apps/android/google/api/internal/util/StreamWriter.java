package apps.android.google.api.internal.util;

import android.view.View;
import apps.android.common.Handler;
import apps.android.common.c;
import apps.v4.animation.Animator;
import apps.v4.animation.AnimatorSet;
import apps.v4.animation.ObjectAnimator;
import apps.v4.animation.ValueAnimator;

public class StreamWriter
  extends apps.android.google.api.Object
{
  public StreamWriter() {}
  
  protected void prepare(View paramView)
  {
    AnimatorSet localAnimatorSet = getItem();
    Object localObject = c.systemLanguage;
    float f = (float)length();
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView, "scaleX", new float[] { 1.5F, 1.0F });
    Handler.a((c)localObject, f, localObjectAnimator);
    c localC = c.systemLanguage;
    f = (float)length();
    localObject = ObjectAnimator.ofFloat(paramView, "scaleY", new float[] { 1.5F, 1.0F });
    Handler.a(localC, f, (ValueAnimator)localObject);
    localC = c.systemLanguage;
    f = (float)length();
    paramView = ObjectAnimator.ofFloat(paramView, "alpha", new float[] { 0.0F, 1.0F });
    Handler.a(localC, f, paramView);
    localAnimatorSet.playTogether(new Animator[] { localObjectAnimator, localObject, paramView });
  }
}