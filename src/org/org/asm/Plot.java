package org.org.asm;

import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Plot
  extends Frame
{
  private static Method c;
  private static Method d;
  private static boolean e;
  private static boolean s;
  
  Plot() {}
  
  private void a()
  {
    if (!e)
    {
      try
      {
        Method localMethod = View.class.getDeclaredMethod("transformMatrixToLocal", new Class[] { Matrix.class });
        d = localMethod;
        localMethod = d;
        localMethod.setAccessible(true);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToLocal method", localNoSuchMethodException);
      }
      e = true;
    }
  }
  
  private void b()
  {
    if (!s)
    {
      try
      {
        Method localMethod = View.class.getDeclaredMethod("transformMatrixToGlobal", new Class[] { Matrix.class });
        c = localMethod;
        localMethod = c;
        localMethod.setAccessible(true);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToGlobal method", localNoSuchMethodException);
      }
      s = true;
    }
  }
  
  public void a(View paramView, Matrix paramMatrix)
  {
    b();
    Method localMethod = c;
    if (localMethod != null) {
      try
      {
        localMethod.invoke(paramView, new Object[] { paramMatrix });
        return;
      }
      catch (InvocationTargetException paramView)
      {
        throw new RuntimeException(paramView.getCause());
      }
      catch (IllegalAccessException paramView) {}
    }
  }
  
  public void draw(View paramView, Matrix paramMatrix)
  {
    a();
    Method localMethod = d;
    if (localMethod != null) {
      try
      {
        localMethod.invoke(paramView, new Object[] { paramMatrix });
        return;
      }
      catch (InvocationTargetException paramView)
      {
        throw new RuntimeException(paramView.getCause());
      }
      catch (IllegalAccessException paramView) {}
    }
  }
}
