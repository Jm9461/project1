package apps.android.common.battery;

import apps.android.common.e;

public class charging_from
  extends e
{
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramFloat1 = paramFloat1 / paramFloat4 - 1.0F;
    return Float.valueOf((float)Math.sqrt(1.0F - paramFloat1 * paramFloat1) * paramFloat3 + paramFloat2);
  }
}
