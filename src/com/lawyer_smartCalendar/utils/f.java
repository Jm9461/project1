package com.lawyer_smartCalendar.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class f
{
  Context a;
  
  public f(Context paramContext)
  {
    a = paramContext;
  }
  
  public void a(Context paramContext, View paramView)
  {
    int i;
    if ((paramView instanceof ViewGroup))
    {
      paramView = (ViewGroup)paramView;
      i = 0;
    }
    try
    {
      for (;;)
      {
        int j = paramView.getChildCount();
        if (i >= j) {
          break;
        }
        if (!(paramView instanceof Spinner)) {
          a(paramContext, paramView.getChildAt(i));
        }
        i += 1;
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    if ((paramView instanceof TextView))
    {
      paramContext = (TextView)paramView;
      paramContext.setTypeface(setText());
      paramContext = (TextView)paramView;
      paramContext.setTextSize(15.0F);
      return;
    }
    return;
  }
  
  public Typeface get()
  {
    return Typeface.createFromAsset(a.getResources().getAssets(), "fonts/IRANSansMobile_Medium.ttf");
  }
  
  public Typeface load()
  {
    return Typeface.createFromAsset(a.getResources().getAssets(), "fonts/IRANSansMobile_UltraLight.ttf");
  }
  
  public Typeface setText()
  {
    return Typeface.createFromAsset(a.getResources().getAssets(), "fonts/IRANSansMobile_Light.ttf");
  }
}
