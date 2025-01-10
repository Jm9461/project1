package butterknife;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ButterKnife
{
  static final Map<Class<?>, Constructor<? extends Unbinder>> BINDERS = new LinkedHashMap();
  private static boolean debug = false;
  
  private ButterKnife()
  {
    throw new AssertionError("No instances.");
  }
  
  private static Constructor get(Class paramClass)
  {
    Object localObject1 = (Constructor)BINDERS.get(paramClass);
    Object localObject2;
    if (localObject1 != null)
    {
      if (debug)
      {
        Log.d("ButterKnife", "HIT: Cached in binding map.");
        return localObject1;
      }
    }
    else
    {
      String str = paramClass.getName();
      if ((!str.startsWith("android.")) && (!str.startsWith("java.")))
      {
        try
        {
          localObject1 = paramClass.getClassLoader();
          Object localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(str);
          ((StringBuilder)localObject3).append("_ViewBinding");
          localObject1 = ((ClassLoader)localObject1).loadClass(((StringBuilder)localObject3).toString());
          localObject3 = ((Class)localObject1).getConstructor(new Class[] { paramClass, View.class });
          localObject1 = localObject3;
          if (debug)
          {
            Log.d("ButterKnife", "HIT: Loaded binding class and constructor.");
            localObject1 = localObject3;
          }
        }
        catch (NoSuchMethodException paramClass)
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Unable to find binding constructor for ");
          ((StringBuilder)localObject1).append(str);
          throw new RuntimeException(((StringBuilder)localObject1).toString(), paramClass);
        }
        catch (ClassNotFoundException localClassNotFoundException)
        {
          if (debug)
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Not found. Trying superclass ");
            ((StringBuilder)localObject2).append(paramClass.getSuperclass().getName());
            Log.d("ButterKnife", ((StringBuilder)localObject2).toString());
          }
          localObject2 = get(paramClass.getSuperclass());
        }
        BINDERS.put(paramClass, localObject2);
        return localObject2;
      }
      if (debug) {
        Log.d("ButterKnife", "MISS: Reached framework class. Abandoning search.");
      }
      return null;
    }
    return localObject2;
  }
  
  public static Unbinder getView(Activity paramActivity)
  {
    return inject(paramActivity, paramActivity.getWindow().getDecorView());
  }
  
  private static Unbinder inject(Object paramObject, View paramView)
  {
    Object localObject = paramObject.getClass();
    if (debug)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Looking up binding for ");
      localStringBuilder.append(((Class)localObject).getName());
      Log.d("ButterKnife", localStringBuilder.toString());
    }
    localObject = get((Class)localObject);
    if (localObject == null) {
      return Unbinder.debug;
    }
    try
    {
      paramObject = ((Constructor)localObject).newInstance(new Object[] { paramObject, paramView });
      return (Unbinder)paramObject;
    }
    catch (InvocationTargetException paramObject)
    {
      paramObject = paramObject.getCause();
      if (!(paramObject instanceof RuntimeException))
      {
        if ((paramObject instanceof Error)) {
          throw ((Error)paramObject);
        }
        throw new RuntimeException("Unable to create binding instance.", paramObject);
      }
      throw ((RuntimeException)paramObject);
    }
    catch (InstantiationException paramObject)
    {
      paramView = new StringBuilder();
      paramView.append("Unable to invoke ");
      paramView.append(localObject);
      throw new RuntimeException(paramView.toString(), paramObject);
    }
    catch (IllegalAccessException paramObject)
    {
      paramView = new StringBuilder();
      paramView.append("Unable to invoke ");
      paramView.append(localObject);
      throw new RuntimeException(paramView.toString(), paramObject);
    }
  }
  
  public static abstract interface Action<T extends View> {}
  
  public static abstract interface Setter<T extends View, V> {}
}
