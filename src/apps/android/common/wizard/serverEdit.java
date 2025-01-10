package apps.android.common.wizard;

import apps.android.common.e;

public class serverEdit
  extends e
{
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    float f = -paramFloat3;
    double d = paramFloat1 / paramFloat4;
    Double.isNaN(d);
    return Float.valueOf(f * (float)Math.cos(d * 1.5707963267948966D) + paramFloat3 + paramFloat2);
  }
}
