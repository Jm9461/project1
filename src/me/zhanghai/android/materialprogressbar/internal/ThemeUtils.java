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
      paramContext.recycle();
      return paramInt1;
    }
    catch (Throwable localThrowable)
    {
      paramContext.recycle();
      throw localThrowable;
    }
  }
  
  public static float getFloatFromAttrRes(int paramInt, float paramFloat, Context paramContext)
  {
    paramContext = paramContext.obtainStyledAttributes(new int[] { paramInt });
    try
    {
      paramFloat = paramContext.getFloat(0, paramFloat);
      paramContext.recycle();
      return paramFloat;
    }
    catch (Throwable localThrowable)
    {
      paramContext.recycle();
      throw localThrowable;
    }
  }
}
