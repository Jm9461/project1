package com.lawyer_smartCalendar.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout.LayoutParams;

public class Utils
{
  public Utils() {}
  
  private static View build(Activity paramActivity, int paramInt)
  {
    View localView = new View(paramActivity);
    localView.setLayoutParams(new LinearLayout.LayoutParams(-1, getStatusBarHeight(paramActivity)));
    localView.setBackgroundColor(paramInt);
    return localView;
  }
  
  private static int getStatusBarHeight(Context paramContext)
  {
    return paramContext.getResources().getDimensionPixelSize(paramContext.getResources().getIdentifier("status_bar_height", "dimen", "android"));
  }
  
  private static void init(Activity paramActivity)
  {
    paramActivity = (ViewGroup)((ViewGroup)paramActivity.findViewById(16908290)).getChildAt(0);
    paramActivity.setFitsSystemWindows(true);
    paramActivity.setClipToPadding(true);
  }
  
  public void initialize(Activity paramActivity, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      paramActivity.getWindow().addFlags(67108864);
      ((ViewGroup)paramActivity.getWindow().getDecorView()).addView(build(paramActivity, paramInt));
      init(paramActivity);
    }
  }
}
