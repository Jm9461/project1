package apps.android.common.battery;

import apps.android.common.e;

public class ad
  extends e
{
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramFloat3 = -paramFloat3;
    paramFloat1 /= paramFloat4;
    return Float.valueOf(paramFloat3 * ((float)Math.sqrt(1.0F - paramFloat1 * paramFloat1) - 1.0F) + paramFloat2);
  }
}
