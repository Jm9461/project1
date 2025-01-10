package me.zhanghai.android.materialprogressbar.internal;

import android.content.Context;
import android.content.res.TypedArray;

public class ThemeUtils
{
  private ThemeUtils() {}
  
  public static int getColorFromAttrRes(int paramInt1, int paramInt2, Context paramContext)
  {
    paramContext = paramContext.obtainStyledAttributes(new int[] { paramInt1 });
    try
    {
      paramInt1 = paramContext.getColor(0, paramInt2);
      return paramInt1;
    }
    finally
    {
      paramContext.recycle();
    }
  }
  
  public static float getFloatFromAttrRes(int paramInt, float paramFloat, Context paramContext)
  {
    paramContext = paramContext.obtainStyledAttributes(new int[] { paramInt });
    try
    {
      paramFloat = paramContext.getFloat(0, paramFloat);
      return paramFloat;
    }
    finally
    {
      paramContext.recycle();
    }
  }
}
