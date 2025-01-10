package apps.android.common.widget;

import apps.android.common.e;

public class ad
  extends e
{
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramFloat1 = paramFloat1 / paramFloat4 - 1.0F;
    return Float.valueOf((paramFloat1 * paramFloat1 * paramFloat1 * paramFloat1 * paramFloat1 + 1.0F) * paramFloat3 + paramFloat2);
  }
}
