package okhttp.internal.internal;

import java.lang.reflect.Method;

final class Type
{
  private final Method a;
  private final Method b;
  private final Method d;
  
  Type(Method paramMethod1, Method paramMethod2, Method paramMethod3)
  {
    a = paramMethod1;
    b = paramMethod2;
    d = paramMethod3;
  }
  
  static Type a()
  {
    Object localObject;
    Method localMethod3;
    Method localMethod2;
    try
    {
      localObject = Class.forName("dalvik.system.CloseGuard");
      Method localMethod1 = ((Class)localObject).getMethod("get", new Class[0]);
      localMethod3 = ((Class)localObject).getMethod("open", new Class[] { String.class });
      localObject = ((Class)localObject).getMethod("warnIfOpen", new Class[0]);
    }
    catch (Exception localException)
    {
      localMethod2 = null;
      localMethod3 = null;
      localObject = null;
    }
    return new Type(localMethod2, localMethod3, (Method)localObject);
  }
  
  Object a(String paramString)
  {
    Object localObject = a;
    if (localObject != null) {
      try
      {
        localObject = ((Method)localObject).invoke(null, new Object[0]);
        Method localMethod = b;
        localMethod.invoke(localObject, new Object[] { paramString });
        return localObject;
      }
      catch (Exception paramString) {}
    }
    return null;
  }
  
  boolean a(Object paramObject)
  {
    if (paramObject != null)
    {
      Method localMethod = d;
      try
      {
        localMethod.invoke(paramObject, new Object[0]);
        return true;
      }
      catch (Exception paramObject) {}
    }
    return false;
  }
}
