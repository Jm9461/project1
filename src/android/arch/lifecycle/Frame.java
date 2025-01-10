package android.arch.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Frame
{
  private static Map<Class, Integer> c = new HashMap();
  private static Map<Class, List<Constructor<? extends b>>> d = new HashMap();
  
  static GenericLifecycleObserver a(Object paramObject)
  {
    if ((paramObject instanceof FullLifecycleObserver)) {
      return new FullLifecycleObserverAdapter((FullLifecycleObserver)paramObject);
    }
    if ((paramObject instanceof GenericLifecycleObserver)) {
      return (GenericLifecycleObserver)paramObject;
    }
    Object localObject = paramObject.getClass();
    if (get((Class)localObject) == 2)
    {
      localObject = (List)d.get(localObject);
      if (((List)localObject).size() == 1) {
        return new SingleGeneratedAdapterObserver(read((Constructor)((List)localObject).get(0), paramObject));
      }
      Label[] arrayOfLabel = new Label[((List)localObject).size()];
      int i = 0;
      while (i < ((List)localObject).size())
      {
        arrayOfLabel[i] = read((Constructor)((List)localObject).get(i), paramObject);
        i += 1;
      }
      return new CompositeGeneratedAdaptersObserver(arrayOfLabel);
    }
    return new ReflectiveGenericLifecycleObserver(paramObject);
  }
  
  private static int get(Class paramClass)
  {
    if (c.containsKey(paramClass)) {
      return ((Integer)c.get(paramClass)).intValue();
    }
    int i = init(paramClass);
    c.put(paramClass, Integer.valueOf(i));
    return i;
  }
  
  public static String get(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString.replace(".", "_"));
    localStringBuilder.append("_LifecycleAdapter");
    return localStringBuilder.toString();
  }
  
  private static Constructor getInstance(Class paramClass)
  {
    try
    {
      Object localObject = paramClass.getPackage();
      String str = paramClass.getCanonicalName();
      if (localObject != null) {
        localObject = ((Package)localObject).getName();
      } else {
        localObject = "";
      }
      boolean bool = ((String)localObject).isEmpty();
      if (!bool)
      {
        int i = ((String)localObject).length();
        str = str.substring(i + 1);
      }
      str = get(str);
      bool = ((String)localObject).isEmpty();
      if (bool)
      {
        localObject = str;
      }
      else
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append((String)localObject);
        localStringBuilder.append(".");
        localStringBuilder.append(str);
        localObject = localStringBuilder.toString();
      }
      localObject = Class.forName((String)localObject);
      paramClass = ((Class)localObject).getDeclaredConstructor(new Class[] { paramClass });
      bool = paramClass.isAccessible();
      if (!bool)
      {
        paramClass.setAccessible(true);
        return paramClass;
      }
    }
    catch (NoSuchMethodException paramClass)
    {
      throw new RuntimeException(paramClass);
    }
    catch (ClassNotFoundException paramClass)
    {
      return null;
    }
    return paramClass;
  }
  
  private static int init(Class paramClass)
  {
    if (paramClass.getCanonicalName() == null) {
      return 1;
    }
    Object localObject1 = getInstance(paramClass);
    if (localObject1 != null)
    {
      d.put(paramClass, Collections.singletonList(localObject1));
      return 2;
    }
    if (ClassWriter.b.a(paramClass)) {
      return 1;
    }
    Object localObject2 = paramClass.getSuperclass();
    localObject1 = null;
    if (put((Class)localObject2))
    {
      if (get((Class)localObject2) == 1) {
        return 1;
      }
      localObject1 = new ArrayList((Collection)d.get(localObject2));
    }
    Class[] arrayOfClass = paramClass.getInterfaces();
    int j = arrayOfClass.length;
    int i = 0;
    while (i < j)
    {
      Class localClass = arrayOfClass[i];
      if (put(localClass))
      {
        if (get(localClass) == 1) {
          return 1;
        }
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new ArrayList();
        }
        ((List)localObject2).addAll((Collection)d.get(localClass));
        localObject1 = localObject2;
      }
      i += 1;
    }
    if (localObject1 != null)
    {
      d.put(paramClass, localObject1);
      return 2;
    }
    return 1;
  }
  
  private static boolean put(Class paramClass)
  {
    return (paramClass != null) && (d.class.isAssignableFrom(paramClass));
  }
  
  private static Label read(Constructor paramConstructor, Object paramObject)
  {
    try
    {
      paramConstructor = paramConstructor.newInstance(new Object[] { paramObject });
      return (Label)paramConstructor;
    }
    catch (InvocationTargetException paramConstructor)
    {
      throw new RuntimeException(paramConstructor);
    }
    catch (InstantiationException paramConstructor)
    {
      throw new RuntimeException(paramConstructor);
    }
    catch (IllegalAccessException paramConstructor)
    {
      throw new RuntimeException(paramConstructor);
    }
  }
}
