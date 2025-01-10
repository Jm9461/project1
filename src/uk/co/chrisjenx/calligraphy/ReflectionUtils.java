package uk.co.chrisjenx.calligraphy;

import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ReflectionUtils
{
  private static final String TAG = ReflectionUtils.class.getSimpleName();
  
  ReflectionUtils() {}
  
  static Field getField(Class paramClass, String paramString)
  {
    try
    {
      paramClass = paramClass.getDeclaredField(paramString);
      paramClass.setAccessible(true);
      return paramClass;
    }
    catch (NoSuchFieldException paramClass) {}
    return null;
  }
  
  static Method getMethod(Class paramClass, String paramString)
  {
    paramClass = paramClass.getMethods();
    int j = paramClass.length;
    int i = 0;
    while (i < j)
    {
      Method localMethod = paramClass[i];
      if (localMethod.getName().equals(paramString))
      {
        localMethod.setAccessible(true);
        return localMethod;
      }
      i += 1;
    }
    return null;
  }
  
  static Object getValue(Field paramField, Object paramObject)
  {
    try
    {
      paramField = paramField.get(paramObject);
      return paramField;
    }
    catch (IllegalAccessException paramField) {}
    return null;
  }
  
  static void invokeMethod(Object paramObject, Method paramMethod, Object... paramVarArgs)
  {
    if (paramMethod == null) {
      return;
    }
    try
    {
      paramMethod.invoke(paramObject, paramVarArgs);
      return;
    }
    catch (InvocationTargetException paramObject) {}catch (IllegalAccessException paramObject) {}
    Log.d(TAG, "Can't invoke method using reflection", paramObject);
  }
  
  static void setValue(Field paramField, Object paramObject1, Object paramObject2)
  {
    try
    {
      paramField.set(paramObject1, paramObject2);
      return;
    }
    catch (IllegalAccessException paramField) {}
  }
}
