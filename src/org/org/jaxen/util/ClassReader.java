package org.org.jaxen.util;

public class ClassReader
{
  public static Object a(Object paramObject)
  {
    if (paramObject != null) {
      return paramObject;
    }
    throw new NullPointerException();
  }
  
  public static Object getItem(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 != null) {
      return paramObject1;
    }
    throw new NullPointerException(String.valueOf(paramObject2));
  }
  
  public static int readInt(int paramInt)
  {
    if (paramInt >= 0) {
      return paramInt;
    }
    throw new IllegalArgumentException();
  }
}
