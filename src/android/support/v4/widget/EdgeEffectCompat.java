package android.support.v4.widget;

import android.os.Build.VERSION;
import android.widget.EdgeEffect;

public final class EdgeEffectCompat
{
  public static void draw(EdgeEffect paramEdgeEffect, float paramFloat1, float paramFloat2)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramEdgeEffect.onPull(paramFloat1, paramFloat2);
      return;
    }
    paramEdgeEffect.onPull(paramFloat1);
  }
}