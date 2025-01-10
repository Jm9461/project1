package apps.android.common.skin;

import apps.android.common.e;

public class update_date_format
  extends e
{
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    if (paramFloat1 == 0.0F) {
      return Float.valueOf(paramFloat2);
    }
    float f2 = paramFloat1 / paramFloat4;
    if (f2 == 1.0F) {
      return Float.valueOf(paramFloat2 + paramFloat3);
    }
    paramFloat1 = 0.3F * paramFloat4;
    float f1 = paramFloat1 / 4.0F;
    f2 -= 1.0F;
    return Float.valueOf(-((float)Math.pow(2.0D, f2 * 10.0F) * paramFloat3 * (float)Math.sin((f2 * paramFloat4 - f1) * 6.2831855F / paramFloat1)) + paramFloat2);
  }
}
