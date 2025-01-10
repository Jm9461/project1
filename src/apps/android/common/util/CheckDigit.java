package apps.android.common.util;

import apps.android.common.e;

public class CheckDigit
  extends e
{
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramFloat1 /= paramFloat4;
    if (paramFloat1 < 0.36363637F) {
      return Float.valueOf(7.5625F * paramFloat1 * paramFloat1 * paramFloat3 + paramFloat2);
    }
    if (paramFloat1 < 0.72727275F)
    {
      paramFloat1 -= 0.54545456F;
      return Float.valueOf((paramFloat1 * 7.5625F * paramFloat1 + 0.75F) * paramFloat3 + paramFloat2);
    }
    if (paramFloat1 < 0.9090909090909091D)
    {
      paramFloat1 -= 0.8181818F;
      return Float.valueOf((paramFloat1 * 7.5625F * paramFloat1 + 0.9375F) * paramFloat3 + paramFloat2);
    }
    paramFloat1 -= 0.95454544F;
    return Float.valueOf((paramFloat1 * 7.5625F * paramFloat1 + 0.984375F) * paramFloat3 + paramFloat2);
  }
}
