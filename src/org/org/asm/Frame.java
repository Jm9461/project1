package org.org.asm;

import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Frame
  extends LayoutManager
{
  private static boolean b;
  private static Method c;
  private static Method d;
  private static boolean e;
  
  Frame() {}
  
  private void a()
  {
    if (!b)
    {
      Object localObject = Float.TYPE;
      try
      {
        localObject = View.class.getDeclaredMethod("setTransitionAlpha", new Class[] { localObject });
        c = (Method)localObject;
        localObject = c;
        ((Method)localObject).setAccessible(true);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        Log.i("ViewUtilsApi19", "Failed to retrieve setTransitionAlpha method", localNoSuchMethodException);
      }
      b = true;
    }
  }
  
  private void init()
  {
    if (!e)
    {
      try
      {
        Method localMethod = View.class.getDeclaredMethod("getTransitionAlpha", new Class[0]);
        d = localMethod;
        localMethod = d;
        localMethod.setAccessible(true);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        Log.i("ViewUtilsApi19", "Failed to retrieve getTransitionAlpha method", localNoSuchMethodException);
      }
      e = true;
    }
  }
  
  public float a(View paramView)
  {
    init();
    Object localObject = d;
    if (localObject != null) {
      try
      {
        localObject = ((Method)localObject).invoke(paramView, new Object[0]);
        localObject = (Float)localObject;
        float f = ((Float)localObject).floatValue();
        return f;
      }
      catch (InvocationTargetException paramView)
      {
        throw new RuntimeException(paramView.getCause());
      }
      catch (IllegalAccessException localIllegalAccessException) {}
    }
    return super.a(paramView);
  }
  
  public void a(View paramView, float paramFloat)
  {
    a();
    Method localMethod = c;
    if (localMethod != null) {
      try
      {
        localMethod.invoke(paramView, new Object[] { Float.valueOf(paramFloat) });
        return;
      }
      catch (InvocationTargetException paramView)
      {
        throw new RuntimeException(paramView.getCause());
      }
      catch (IllegalAccessException paramView)
      {
        return;
      }
    }
    paramView.setAlpha(paramFloat);
  }
  
  public void init(View paramView) {}
  
  public void onDraw(View paramView) {}
}
