package apps.android.common.widget;

import apps.android.common.e;

public class Animation
  extends e
{
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramFloat1 /= paramFloat4 / 2.0F;
    if (paramFloat1 < 1.0F) {
      return Float.valueOf(paramFloat3 / 2.0F * paramFloat1 * paramFloat1 * paramFloat1 * paramFloat1 * paramFloat1 + paramFloat2);
    }
    paramFloat3 /= 2.0F;
    paramFloat1 -= 2.0F;
    return Float.valueOf(paramFloat3 * (paramFloat1 * paramFloat1 * paramFloat1 * paramFloat1 * paramFloat1 + 2.0F) + paramFloat2);
  }
}
