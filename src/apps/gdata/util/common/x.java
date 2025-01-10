package apps.gdata.util.common;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public class x
{
  private static Interpolator a;
  
  public static Interpolator a(Context paramContext)
  {
    if (a == null) {
      if (Build.VERSION.SDK_INT >= 21) {
        a = AnimationUtils.loadInterpolator(paramContext, 17563661);
      } else {
        a = new FastOutSlowInInterpolator();
      }
    }
    return a;
  }
}
