package apps.android.common.util;

import apps.android.common.e;

public class SubnetUtils
  extends e
{
  private CheckDigit ISBN10_CHECK_DIGIT;
  
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    return Float.valueOf(paramFloat3 - ISBN10_CHECK_DIGIT.calculate(paramFloat4 - paramFloat1, 0.0F, paramFloat3, paramFloat4).floatValue() + paramFloat2);
  }
}
