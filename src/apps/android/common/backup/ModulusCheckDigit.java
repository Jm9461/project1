package apps.android.common.backup;

import apps.android.common.e;

public class ModulusCheckDigit
  extends e
{
  private float modulus;
  
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramFloat1 = paramFloat1 / paramFloat4 - 1.0F;
    paramFloat4 = modulus;
    return Float.valueOf((paramFloat1 * paramFloat1 * ((paramFloat4 + 1.0F) * paramFloat1 + paramFloat4) + 1.0F) * paramFloat3 + paramFloat2);
  }
}
