package apps.android.common;

import apps.v4.animation.ValueAnimator;

public class Handler
{
  public static ValueAnimator a(c paramC, float paramFloat, ValueAnimator paramValueAnimator)
  {
    a(paramC, paramFloat, paramValueAnimator, new x[0]);
    return paramValueAnimator;
  }
  
  public static ValueAnimator a(c paramC, float paramFloat, ValueAnimator paramValueAnimator, x... paramVarArgs)
  {
    paramC = paramC.getMethod(paramFloat);
    if (paramVarArgs != null) {
      paramC.a(paramVarArgs);
    }
    paramValueAnimator.setEvaluator(paramC);
    return paramValueAnimator;
  }
}
