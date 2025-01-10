package com.aurelhubert.ahbottomnavigation;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import org.org.v4.gui.helpers.Resources;

public class Label
{
  private int b = 0;
  private int c = -7829368;
  private String f = "";
  private Drawable j;
  private int k = 0;
  private int m = 0;
  
  public Label(int paramInt1, int paramInt2, int paramInt3)
  {
    b = paramInt1;
    k = paramInt2;
    m = paramInt3;
  }
  
  public int get(Context paramContext)
  {
    int i = m;
    if (i != 0) {
      return ContextCompat.getColor(paramContext, i);
    }
    return c;
  }
  
  public Drawable getIcon(Context paramContext)
  {
    int i = k;
    if (i != 0) {
      try
      {
        Drawable localDrawable = Resources.getDrawable(paramContext, i);
        return localDrawable;
      }
      catch (Resources.NotFoundException localNotFoundException)
      {
        return ContextCompat.getDrawable(paramContext, k);
      }
    }
    return j;
  }
  
  public String getValue(Context paramContext)
  {
    int i = b;
    if (i != 0) {
      return paramContext.getString(i);
    }
    return f;
  }
}
