package apps.android.common.wizard;

import apps.android.common.e;

public class mErrorLabel
  extends e
{
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    double d = paramFloat1 / paramFloat4;
    Double.isNaN(d);
    return Float.valueOf((float)Math.sin(d * 1.5707963267948966D) * paramFloat3 + paramFloat2);
  }
}
