package org.org.android.gcm;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import org.org.v4.gui.helpers.Resources;

public class ResourcesCompat
{
  public static ColorStateList getColorStateList(Context paramContext, TypedArray paramTypedArray, int paramInt)
  {
    if (paramTypedArray.hasValue(paramInt))
    {
      int i = paramTypedArray.getResourceId(paramInt, 0);
      if (i != 0)
      {
        paramContext = Resources.getColorStateList(paramContext, i);
        if (paramContext != null) {
          return paramContext;
        }
      }
    }
    return paramTypedArray.getColorStateList(paramInt);
  }
  
  public static Drawable getDrawable(Context paramContext, TypedArray paramTypedArray, int paramInt)
  {
    if (paramTypedArray.hasValue(paramInt))
    {
      int i = paramTypedArray.getResourceId(paramInt, 0);
      if (i != 0)
      {
        paramContext = Resources.getDrawable(paramContext, i);
        if (paramContext != null) {
          return paramContext;
        }
      }
    }
    return paramTypedArray.getDrawable(paramInt);
  }
}
