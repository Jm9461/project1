package apps.android.common.ui;

import apps.android.common.e;

public class ac
  extends e
{
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramFloat1 /= paramFloat4;
    return Float.valueOf(paramFloat1 * paramFloat3 * paramFloat1 + paramFloat2);
  }
}
