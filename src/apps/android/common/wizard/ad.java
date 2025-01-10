package apps.android.common.wizard;

import apps.android.common.e;

public class ad
  extends e
{
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramFloat3 = -paramFloat3 / 2.0F;
    double d1 = paramFloat1;
    Double.isNaN(d1);
    double d2 = paramFloat4;
    Double.isNaN(d2);
    return Float.valueOf(paramFloat3 * ((float)Math.cos(d1 * 3.141592653589793D / d2) - 1.0F) + paramFloat2);
  }
}
