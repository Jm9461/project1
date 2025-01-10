package apps.muzei.api.internal;

import android.graphics.Color;

public class ContextCompat
{
  public static int getColor(int paramInt, float paramFloat)
  {
    return 0xFFFFFF & paramInt | Math.round(Color.alpha(paramInt) * paramFloat) << 24;
  }
}
