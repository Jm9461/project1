package apps.android.common.settings;

import apps.android.common.e;

public class ad
  extends e
{
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    if (paramFloat1 == 0.0F) {
      return Float.valueOf(paramFloat2);
    }
    if (paramFloat1 == paramFloat4) {
      return Float.valueOf(paramFloat2 + paramFloat3);
    }
    paramFloat1 /= paramFloat4 / 2.0F;
    if (paramFloat1 < 1.0F) {
      return Float.valueOf(paramFloat3 / 2.0F * (float)Math.pow(2.0D, (paramFloat1 - 1.0F) * 10.0F) + paramFloat2);
    }
    return Float.valueOf(paramFloat3 / 2.0F * (-(float)Math.pow(2.0D, (paramFloat1 - 1.0F) * -10.0F) + 2.0F) + paramFloat2);
  }
}
