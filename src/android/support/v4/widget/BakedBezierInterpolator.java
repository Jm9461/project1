package android.support.v4.widget;

import android.view.animation.Interpolator;

final class BakedBezierInterpolator
  implements Interpolator
{
  BakedBezierInterpolator() {}
  
  public float getInterpolation(float paramFloat)
  {
    paramFloat -= 1.0F;
    return paramFloat * paramFloat * paramFloat * paramFloat * paramFloat + 1.0F;
  }
}
