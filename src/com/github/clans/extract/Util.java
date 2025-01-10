package com.github.clans.extract;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;

final class Util
{
  static int dpToPx(Context paramContext, float paramFloat)
  {
    return Math.round(paramFloat * getResourcesgetDisplayMetricsdensity);
  }
  
  static boolean hasJellyBean()
  {
    return Build.VERSION.SDK_INT >= 16;
  }
  
  static boolean hasLollipop()
  {
    return Build.VERSION.SDK_INT >= 21;
  }
}
