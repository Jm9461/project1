package android.support.design.widget;

import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.util.Log;
import java.lang.reflect.Method;

public class DrawableUtils
{
  private static Method sSetConstantStateMethod;
  private static boolean sSetConstantStateMethodFetched;
  
  public static boolean setContainerConstantState(DrawableContainer paramDrawableContainer, Drawable.ConstantState paramConstantState)
  {
    return setContainerConstantStateV9(paramDrawableContainer, paramConstantState);
  }
  
  private static boolean setContainerConstantStateV9(DrawableContainer paramDrawableContainer, Drawable.ConstantState paramConstantState)
  {
    if (!sSetConstantStateMethodFetched)
    {
      try
      {
        Method localMethod1 = DrawableContainer.class.getDeclaredMethod("setConstantState", new Class[] { DrawableContainer.DrawableContainerState.class });
        sSetConstantStateMethod = localMethod1;
        localMethod1 = sSetConstantStateMethod;
        localMethod1.setAccessible(true);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        Log.e("DrawableUtils", "Could not fetch setConstantState(). Oh well.");
      }
      sSetConstantStateMethodFetched = true;
    }
    Method localMethod2 = sSetConstantStateMethod;
    if (localMethod2 != null) {
      try
      {
        localMethod2.invoke(paramDrawableContainer, new Object[] { paramConstantState });
        return true;
      }
      catch (Exception paramDrawableContainer)
      {
        Log.e("DrawableUtils", "Could not invoke setConstantState(). Oh well.");
      }
    }
    return false;
  }
}
