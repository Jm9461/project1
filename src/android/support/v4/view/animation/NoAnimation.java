package android.support.v4.view.animation;

import android.graphics.Path;
import android.os.Build.VERSION;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

public final class NoAnimation
{
  public static Interpolator create(Path paramPath)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return (Interpolator)new PathInterpolator(paramPath);
    }
    return new PathInterpolatorDonut(paramPath);
  }
}
