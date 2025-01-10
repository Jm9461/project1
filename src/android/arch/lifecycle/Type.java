package android.arch.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Type
{
  final Method c;
  final int d;
  
  Type(int paramInt, Method paramMethod)
  {
    d = paramInt;
    c = paramMethod;
    c.setAccessible(true);
  }
  
  void a(h paramH, c paramC, Object paramObject)
  {
    int i = d;
    Method localMethod;
    if (i != 0) {
      if (i != 1)
      {
        if (i != 2) {
          return;
        }
        localMethod = c;
      }
    }
    try
    {
      localMethod.invoke(paramObject, new Object[] { paramH, paramC });
      return;
    }
    catch (IllegalAccessException paramH)
    {
      throw new RuntimeException(paramH);
    }
    catch (InvocationTargetException paramH)
    {
      throw new RuntimeException("Failed to call observer method", paramH.getCause());
    }
    paramC = c;
    paramC.invoke(paramObject, new Object[] { paramH });
    return;
    paramH = c;
    paramH.invoke(paramObject, new Object[0]);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject != null)
    {
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (Type)paramObject;
      return (d == d) && (c.getName().equals(c.getName()));
    }
    return false;
  }
  
  public int hashCode()
  {
    return d * 31 + c.getName().hashCode();
  }
}
