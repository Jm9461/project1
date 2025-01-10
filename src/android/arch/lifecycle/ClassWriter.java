package android.arch.lifecycle;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class ClassWriter
{
  static ClassWriter b = new ClassWriter();
  private final Map<Class, Boolean> c = new HashMap();
  private final Map<Class, a.a> d = new HashMap();
  
  ClassWriter() {}
  
  private ByteVector a(Class paramClass, Method[] paramArrayOfMethod)
  {
    Object localObject1 = paramClass.getSuperclass();
    HashMap localHashMap = new HashMap();
    if (localObject1 != null)
    {
      localObject1 = b((Class)localObject1);
      if (localObject1 != null) {
        localHashMap.putAll(a);
      }
    }
    localObject1 = paramClass.getInterfaces();
    int j = localObject1.length;
    int i = 0;
    Object localObject2;
    Object localObject3;
    while (i < j)
    {
      localObject2 = ba.entrySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (Map.Entry)((Iterator)localObject2).next();
        get(localHashMap, (Type)((Map.Entry)localObject3).getKey(), (c)((Map.Entry)localObject3).getValue(), paramClass);
      }
      i += 1;
    }
    if (paramArrayOfMethod == null) {
      paramArrayOfMethod = getMethod(paramClass);
    }
    int k = paramArrayOfMethod.length;
    boolean bool = false;
    j = 0;
    while (j < k)
    {
      localObject1 = paramArrayOfMethod[j];
      localObject3 = (Segment)((Method)localObject1).getAnnotation(l.class);
      if (localObject3 != null)
      {
        bool = true;
        localObject2 = ((Method)localObject1).getParameterTypes();
        i = 0;
        if (localObject2.length > 0)
        {
          i = 1;
          if (!localObject2[0].isAssignableFrom(e.class)) {
            throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
          }
        }
        localObject3 = ((Segment)localObject3).value();
        if (localObject2.length > 1)
        {
          i = 2;
          if (localObject2[1].isAssignableFrom(c.a.class))
          {
            if (localObject3 != c.ON_ANY) {
              throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
            }
          }
          else {
            throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
          }
        }
        if (localObject2.length <= 2) {
          get(localHashMap, new Type(i, (Method)localObject1), (c)localObject3, paramClass);
        }
      }
      else
      {
        j += 1;
        continue;
      }
      throw new IllegalArgumentException("cannot have more than 2 params");
    }
    paramArrayOfMethod = new ByteVector(localHashMap);
    d.put(paramClass, paramArrayOfMethod);
    c.put(paramClass, Boolean.valueOf(bool));
    return paramArrayOfMethod;
  }
  
  private void get(Map paramMap, Type paramType, c paramC, Class paramClass)
  {
    c localC = (c)paramMap.get(paramType);
    if ((localC != null) && (paramC != localC))
    {
      paramMap = c;
      paramType = new StringBuilder();
      paramType.append("Method ");
      paramType.append(paramMap.getName());
      paramType.append(" in ");
      paramType.append(paramClass.getName());
      paramType.append(" already declared with different @OnLifecycleEvent value: previous value ");
      paramType.append(localC);
      paramType.append(", new value ");
      paramType.append(paramC);
      throw new IllegalArgumentException(paramType.toString());
    }
    if (localC == null) {
      paramMap.put(paramType, paramC);
    }
  }
  
  private Method[] getMethod(Class paramClass)
  {
    try
    {
      paramClass = paramClass.getDeclaredMethods();
      return paramClass;
    }
    catch (NoClassDefFoundError paramClass)
    {
      throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", paramClass);
    }
  }
  
  boolean a(Class paramClass)
  {
    if (c.containsKey(paramClass)) {
      return ((Boolean)c.get(paramClass)).booleanValue();
    }
    Method[] arrayOfMethod = getMethod(paramClass);
    int j = arrayOfMethod.length;
    int i = 0;
    while (i < j)
    {
      if ((Segment)arrayOfMethod[i].getAnnotation(l.class) != null)
      {
        a(paramClass, arrayOfMethod);
        return true;
      }
      i += 1;
    }
    c.put(paramClass, Boolean.valueOf(false));
    return false;
  }
  
  ByteVector b(Class paramClass)
  {
    ByteVector localByteVector = (ByteVector)d.get(paramClass);
    if (localByteVector != null) {
      return localByteVector;
    }
    return a(paramClass, null);
  }
}
