package org.org.asm;

import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class AnnotationWriter
{
  private static boolean b;
  private static Method c;
  
  private static void a()
  {
    if (!b)
    {
      Object localObject = Boolean.TYPE;
      try
      {
        localObject = ViewGroup.class.getDeclaredMethod("suppressLayout", new Class[] { localObject });
        c = (Method)localObject;
        localObject = c;
        ((Method)localObject).setAccessible(true);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        Log.i("ViewUtilsApi18", "Failed to retrieve suppressLayout method", localNoSuchMethodException);
      }
      b = true;
    }
  }
  
  static void a(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    a();
    Method localMethod = c;
    if (localMethod != null) {
      try
      {
        localMethod.invoke(paramViewGroup, new Object[] { Boolean.valueOf(paramBoolean) });
        return;
      }
      catch (InvocationTargetException paramViewGroup)
      {
        Log.i("ViewUtilsApi18", "Error invoking suppressLayout method", paramViewGroup);
        return;
      }
      catch (IllegalAccessException paramViewGroup)
      {
        Log.i("ViewUtilsApi18", "Failed to invoke suppressLayout method", paramViewGroup);
      }
    }
  }
}
