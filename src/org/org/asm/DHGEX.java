package org.org.asm;

import android.animation.LayoutTransition;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class DHGEX
{
  private static Method d;
  private static boolean e;
  private static Field field;
  private static LayoutTransition g;
  private static boolean p;
  
  private static void init(LayoutTransition paramLayoutTransition)
  {
    if (!e)
    {
      try
      {
        Method localMethod1 = LayoutTransition.class.getDeclaredMethod("cancel", new Class[0]);
        d = localMethod1;
        localMethod1 = d;
        localMethod1.setAccessible(true);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
      }
      e = true;
    }
    Method localMethod2 = d;
    if (localMethod2 != null) {
      try
      {
        localMethod2.invoke(paramLayoutTransition, new Object[0]);
        return;
      }
      catch (InvocationTargetException paramLayoutTransition)
      {
        Log.i("ViewGroupUtilsApi14", "Failed to invoke cancel method by reflection");
        return;
      }
      catch (IllegalAccessException paramLayoutTransition)
      {
        Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
      }
    }
  }
  
  static void init(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    if (g == null)
    {
      g = new DownloadsFragment.1();
      g.setAnimator(2, null);
      g.setAnimator(0, null);
      g.setAnimator(1, null);
      g.setAnimator(3, null);
      g.setAnimator(4, null);
    }
    Object localObject;
    if (paramBoolean)
    {
      localObject = paramViewGroup.getLayoutTransition();
      if (localObject != null)
      {
        if (((LayoutTransition)localObject).isRunning()) {
          init((LayoutTransition)localObject);
        }
        if (localObject != g) {
          paramViewGroup.setTag(R.id.transition_layout_save, localObject);
        }
      }
      paramViewGroup.setLayoutTransition(g);
      return;
    }
    paramViewGroup.setLayoutTransition(null);
    if (!p)
    {
      try
      {
        localObject = ViewGroup.class.getDeclaredField("mLayoutSuppressed");
        field = (Field)localObject;
        localObject = field;
        ((Field)localObject).setAccessible(true);
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        Log.i("ViewGroupUtilsApi14", "Failed to access mLayoutSuppressed field by reflection");
      }
      p = true;
    }
    paramBoolean = false;
    boolean bool1 = false;
    Field localField = field;
    if (localField != null) {
      try
      {
        boolean bool2 = localField.getBoolean(paramViewGroup);
        paramBoolean = bool2;
        if (bool2)
        {
          localField = field;
          bool1 = paramBoolean;
          localField.setBoolean(paramViewGroup, false);
        }
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        Log.i("ViewGroupUtilsApi14", "Failed to get mLayoutSuppressed field by reflection");
        paramBoolean = bool1;
      }
    }
    if (paramBoolean) {
      paramViewGroup.requestLayout();
    }
    LayoutTransition localLayoutTransition = (LayoutTransition)paramViewGroup.getTag(R.id.transition_layout_save);
    if (localLayoutTransition != null)
    {
      paramViewGroup.setTag(R.id.transition_layout_save, null);
      paramViewGroup.setLayoutTransition(localLayoutTransition);
    }
  }
}
