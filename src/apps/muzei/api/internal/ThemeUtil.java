package apps.muzei.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.TypedValue;
import apps.muzei.api.R.attr;

public class ThemeUtil
{
  private static TypedValue value;
  
  public static int colorControlActivated(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return getColor(paramContext, 16843818, paramInt);
    }
    return getColor(paramContext, R.attr.colorControlActivated, paramInt);
  }
  
  public static int colorControlHighlight(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return getColor(paramContext, 16843820, paramInt);
    }
    return getColor(paramContext, R.attr.colorControlHighlight, paramInt);
  }
  
  public static int colorControlNormal(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return getColor(paramContext, 16843817, paramInt);
    }
    return getColor(paramContext, R.attr.colorControlNormal, paramInt);
  }
  
  public static int dpToPx(Context paramContext, int paramInt)
  {
    return (int)(TypedValue.applyDimension(1, paramInt, paramContext.getResources().getDisplayMetrics()) + 0.5F);
  }
  
  private static int getColor(Context paramContext, int paramInt1, int paramInt2)
  {
    if (value == null) {
      value = new TypedValue();
    }
    try
    {
      Resources.Theme localTheme = paramContext.getTheme();
      if (localTheme != null)
      {
        TypedValue localTypedValue = value;
        boolean bool = localTheme.resolveAttribute(paramInt1, localTypedValue, true);
        if (bool)
        {
          if ((valuetype >= 16) && (valuetype <= 31)) {
            return valuedata;
          }
          if (valuetype == 3)
          {
            paramContext = paramContext.getResources();
            paramInt1 = valueresourceId;
            paramInt1 = paramContext.getColor(paramInt1);
            return paramInt1;
          }
        }
      }
    }
    catch (Exception paramContext) {}
    return paramInt2;
  }
  
  public static int getType(TypedArray paramTypedArray, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramTypedArray.getType(paramInt);
    }
    paramTypedArray = paramTypedArray.peekValue(paramInt);
    if (paramTypedArray == null) {
      return 0;
    }
    return type;
  }
}
