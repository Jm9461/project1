package apps.android.common.backup;

import apps.android.common.e;

public class Hand
  extends e
{
  private float total;
  
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramFloat1 /= paramFloat4 / 2.0F;
    if (paramFloat1 < 1.0F)
    {
      paramFloat3 /= 2.0F;
      d = total;
      Double.isNaN(d);
      paramFloat4 = (float)(d * 1.525D);
      total = paramFloat4;
      return Float.valueOf(paramFloat3 * (paramFloat1 * paramFloat1 * ((paramFloat4 + 1.0F) * paramFloat1 - total)) + paramFloat2);
    }
    paramFloat3 /= 2.0F;
    paramFloat1 -= 2.0F;
    double d = total;
    Double.isNaN(d);
    paramFloat4 = (float)(d * 1.525D);
    total = paramFloat4;
    return Float.valueOf(paramFloat3 * (paramFloat1 * paramFloat1 * ((paramFloat4 + 1.0F) * paramFloat1 + total) + 2.0F) + paramFloat2);
  }
}
