package org.org.jaxen.asm;

import android.graphics.Typeface;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SystemProperty
  extends ClassWriter
{
  public SystemProperty() {}
  
  protected Typeface get(Object paramObject)
  {
    Object localObject = type;
    try
    {
      localObject = Array.newInstance((Class)localObject, 1);
      Array.set(localObject, 0, paramObject);
      paramObject = a;
      paramObject = paramObject.invoke(null, new Object[] { localObject, "sans-serif", Integer.valueOf(-1), Integer.valueOf(-1) });
      return (Typeface)paramObject;
    }
    catch (InvocationTargetException paramObject) {}catch (IllegalAccessException paramObject) {}
    throw new RuntimeException(paramObject);
  }
  
  protected Method get(Class paramClass)
  {
    paramClass = Array.newInstance(paramClass, 1).getClass();
    Class localClass = Integer.TYPE;
    paramClass = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", new Class[] { paramClass, String.class, localClass, localClass });
    paramClass.setAccessible(true);
    return paramClass;
  }
}
