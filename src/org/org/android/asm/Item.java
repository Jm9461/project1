package org.org.android.asm;

import android.animation.TimeInterpolator;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class Item
{
  public static final TimeInterpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
  public static final TimeInterpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new LinearInterpolator();
  public static final TimeInterpolator a;
  public static final TimeInterpolator b;
  public static final TimeInterpolator c = new FastOutSlowInInterpolator();
  
  static
  {
    b = new FastOutLinearInInterpolator();
    a = new LinearOutSlowInInterpolator();
  }
  
  public static int append(int paramInt1, int paramInt2, float paramFloat)
  {
    return Math.round((paramInt2 - paramInt1) * paramFloat) + paramInt1;
  }
  
  public static float lerp(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return (paramFloat2 - paramFloat1) * paramFloat3 + paramFloat1;
  }
}
