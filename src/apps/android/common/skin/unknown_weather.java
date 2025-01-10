package apps.android.common.skin;

import apps.android.common.e;

public class unknown_weather
  extends e
{
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    if (paramFloat1 == 0.0F) {
      return Float.valueOf(paramFloat2);
    }
    paramFloat1 /= paramFloat4;
    if (paramFloat1 == 1.0F) {
      return Float.valueOf(paramFloat2 + paramFloat3);
    }
    float f1 = 0.3F * paramFloat4;
    float f2 = f1 / 4.0F;
    return Float.valueOf((float)Math.pow(2.0D, -10.0F * paramFloat1) * paramFloat3 * (float)Math.sin((paramFloat1 * paramFloat4 - f2) * 6.2831855F / f1) + paramFloat3 + paramFloat2);
  }
}
