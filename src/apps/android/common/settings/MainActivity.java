package apps.android.common.settings;

import apps.android.common.e;

public class MainActivity
  extends e
{
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    if (paramFloat1 != 0.0F) {
      paramFloat2 = (float)Math.pow(2.0D, (paramFloat1 / paramFloat4 - 1.0F) * 10.0F) * paramFloat3 + paramFloat2;
    }
    return Float.valueOf(paramFloat2);
  }
}