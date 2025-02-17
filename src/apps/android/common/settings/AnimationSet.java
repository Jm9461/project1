package apps.android.common.settings;

import apps.android.common.e;

public class AnimationSet
  extends e
{
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    if (paramFloat1 == paramFloat4) {
      paramFloat1 = paramFloat2 + paramFloat3;
    } else {
      paramFloat1 = (-(float)Math.pow(2.0D, -10.0F * paramFloat1 / paramFloat4) + 1.0F) * paramFloat3 + paramFloat2;
    }
    return Float.valueOf(paramFloat1);
  }
}
