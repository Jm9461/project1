package org.org.asm;

import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class JRockit131Instantiator
  extends Plot
{
  private static boolean m;
  private static Method newConstructorForSerializationMethod;
  
  JRockit131Instantiator() {}
  
  private void initialize()
  {
    if (!m)
    {
      Object localObject = Integer.TYPE;
      Class localClass1 = Integer.TYPE;
      Class localClass2 = Integer.TYPE;
      Class localClass3 = Integer.TYPE;
      try
      {
        localObject = View.class.getDeclaredMethod("setLeftTopRightBottom", new Class[] { localObject, localClass1, localClass2, localClass3 });
        newConstructorForSerializationMethod = (Method)localObject;
        localObject = newConstructorForSerializationMethod;
        ((Method)localObject).setAccessible(true);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        Log.i("ViewUtilsApi22", "Failed to retrieve setLeftTopRightBottom method", localNoSuchMethodException);
      }
      m = true;
    }
  }
  
  public void initialize(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    initialize();
    Method localMethod = newConstructorForSerializationMethod;
    if (localMethod != null) {
      try
      {
        localMethod.invoke(paramView, new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), Integer.valueOf(paramInt4) });
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
