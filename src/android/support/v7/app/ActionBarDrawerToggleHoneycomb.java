package android.support.v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.reflect.Method;

class ActionBarDrawerToggleHoneycomb
{
  private static final int[] THEME_ATTRS = { 16843531 };
  
  public static Drawable getThemeUpIndicator(Activity paramActivity)
  {
    paramActivity = paramActivity.obtainStyledAttributes(THEME_ATTRS);
    Drawable localDrawable = paramActivity.getDrawable(0);
    paramActivity.recycle();
    return localDrawable;
  }
  
  public static SetIndicatorInfo setActionBarDescription(SetIndicatorInfo paramSetIndicatorInfo, Activity paramActivity, int paramInt)
  {
    SetIndicatorInfo localSetIndicatorInfo = paramSetIndicatorInfo;
    if (paramSetIndicatorInfo == null) {
      localSetIndicatorInfo = new SetIndicatorInfo(paramActivity);
    }
    if (setHomeAsUpIndicator != null) {
      try
      {
        paramSetIndicatorInfo = paramActivity.getActionBar();
        paramActivity = setHomeActionContentDescription;
        paramActivity.invoke(paramSetIndicatorInfo, new Object[] { Integer.valueOf(paramInt) });
        if (Build.VERSION.SDK_INT <= 19) {
          paramSetIndicatorInfo.setSubtitle(paramSetIndicatorInfo.getSubtitle());
        }
        return localSetIndicatorInfo;
      }
      catch (Exception paramSetIndicatorInfo)
      {
        Log.w("ActionBarDrawerToggleHC", "Couldn't set content description via JB-MR2 API", paramSetIndicatorInfo);
      }
    }
    return localSetIndicatorInfo;
  }
  
  public static SetIndicatorInfo setActionBarUpIndicator(SetIndicatorInfo paramSetIndicatorInfo, Activity paramActivity, Drawable paramDrawable, int paramInt)
  {
    paramSetIndicatorInfo = new SetIndicatorInfo(paramActivity);
    if (setHomeAsUpIndicator != null) {
      try
      {
        paramActivity = paramActivity.getActionBar();
        Method localMethod = setHomeAsUpIndicator;
        localMethod.invoke(paramActivity, new Object[] { paramDrawable });
        paramDrawable = setHomeActionContentDescription;
        paramDrawable.invoke(paramActivity, new Object[] { Integer.valueOf(paramInt) });
        return paramSetIndicatorInfo;
      }
      catch (Exception paramActivity)
      {
        Log.w("ActionBarDrawerToggleHC", "Couldn't set home-as-up indicator via JB-MR2 API", paramActivity);
        return paramSetIndicatorInfo;
      }
    }
    paramActivity = upIndicatorView;
    if (paramActivity != null)
    {
      paramActivity.setImageDrawable(paramDrawable);
      return paramSetIndicatorInfo;
    }
    Log.w("ActionBarDrawerToggleHC", "Couldn't set home-as-up indicator");
    return paramSetIndicatorInfo;
  }
  
  class SetIndicatorInfo
  {
    public Method setHomeActionContentDescription;
    public Method setHomeAsUpIndicator;
    public ImageView upIndicatorView;
    
    SetIndicatorInfo()
    {
      try
      {
        Object localObject = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", new Class[] { Drawable.class });
        setHomeAsUpIndicator = ((Method)localObject);
        localObject = Integer.TYPE;
        localObject = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", new Class[] { localObject });
        setHomeActionContentDescription = ((Method)localObject);
        return;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        this$1 = this$1.findViewById(16908332);
        if (this$1 == null) {
          return;
        }
        this$1 = (ViewGroup)this$1.getParent();
        if (this$1.getChildCount() != 2) {
          return;
        }
        View localView1 = this$1.getChildAt(0);
        View localView2 = this$1.getChildAt(1);
        this$1 = localView1;
        if (localView1.getId() == 16908332) {
          this$1 = localView2;
        }
        if ((this$1 instanceof ImageView)) {
          upIndicatorView = ((ImageView)this$1);
        }
      }
    }
  }
}
