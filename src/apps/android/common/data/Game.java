package apps.android.common.data;

import apps.android.common.e;

public class Game
  extends e
{
  public Float calculate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    return Float.valueOf(paramFloat3 * paramFloat1 / paramFloat4 + paramFloat2);
  }
}
