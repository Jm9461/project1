package org.org.android.ui;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewAnimationUtils;

public final class g
{
  public static Animator.AnimatorListener a(List paramList)
  {
    return new MainActivity.29(paramList);
  }
  
  public static Animator a(List paramList, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofObject(paramList, Cache.l, Type.c, new Label[] { new Label(paramFloat1, paramFloat2, paramFloat3) });
    if (Build.VERSION.SDK_INT >= 21)
    {
      Object localObject = paramList.getRevealInfo();
      if (localObject != null)
      {
        float f = h;
        paramList = ViewAnimationUtils.createCircularReveal((View)paramList, (int)paramFloat1, (int)paramFloat2, f, paramFloat3);
        localObject = new AnimatorSet();
        ((AnimatorSet)localObject).playTogether(new Animator[] { localObjectAnimator, paramList });
        return localObject;
      }
      throw new IllegalStateException("Caller must set a non-null RevealInfo before calling this.");
    }
    return localObjectAnimator;
  }
}
