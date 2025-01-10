package apps.android.common.util;

import apps.android.common.e;

public class ISBNCheckDigit
  extends e
{
  private SubnetUtils ISBN10_CHECK_DIGIT;
  private CheckDigit ISBN13_CHECK_DIGIT;
  
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    if (paramFloat1 < paramFloat4 / 2.0F) {
      return Float.valueOf(ISBN10_CHECK_DIGIT.calculate(2.0F * paramFloat1, 0.0F, paramFloat3, paramFloat4).floatValue() * 0.5F + paramFloat2);
    }
    return Float.valueOf(ISBN13_CHECK_DIGIT.calculate(2.0F * paramFloat1 - paramFloat4, 0.0F, paramFloat3, paramFloat4).floatValue() * 0.5F + 0.5F * paramFloat3 + paramFloat2);
  }
}
