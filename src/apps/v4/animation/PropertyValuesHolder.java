package apps.v4.animation;

import android.util.Log;
import apps.v4.widget.IntProperty;
import apps.v4.widget.Property;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class PropertyValuesHolder
  implements Cloneable
{
  private static Class[] DOUBLE_VARIANTS;
  private static Class[] FLOAT_VARIANTS;
  private static Class[] INTEGER_VARIANTS;
  private static final TypeEvaluator sFloatEvaluator;
  private static final HashMap<Class, HashMap<String, Method>> sGetterPropertyMap = new HashMap();
  private static final TypeEvaluator sIntEvaluator = new IntEvaluator();
  private static final HashMap<Class, HashMap<String, Method>> sSetterPropertyMap;
  private Object mAnimatedValue;
  private TypeEvaluator mEvaluator;
  private Method mGetter = null;
  KeyframeSet mKeyframeSet = null;
  protected Property mProperty;
  final ReentrantReadWriteLock mPropertyMapLock = new ReentrantReadWriteLock();
  String mPropertyName;
  Method mSetter = null;
  final Object[] mTmpValueArray = new Object[1];
  Class mValueType;
  
  static
  {
    sFloatEvaluator = new FloatEvaluator();
    Class localClass2 = Float.TYPE;
    Class localClass3 = Double.TYPE;
    Class localClass1 = Integer.TYPE;
    DOUBLE_VARIANTS = new Class[] { localClass2, Float.class, localClass3, localClass1, Double.class, Integer.class };
    localClass2 = Float.TYPE;
    localClass3 = Double.TYPE;
    FLOAT_VARIANTS = new Class[] { localClass1, Integer.class, localClass2, localClass3, Float.class, Double.class };
    INTEGER_VARIANTS = new Class[] { localClass3, Double.class, Float.TYPE, Integer.TYPE, Float.class, Integer.class };
    sSetterPropertyMap = new HashMap();
  }
  
  private PropertyValuesHolder(Property paramProperty)
  {
    mProperty = paramProperty;
    if (paramProperty != null) {
      mPropertyName = paramProperty.getName();
    }
  }
  
  private PropertyValuesHolder(String paramString)
  {
    mPropertyName = paramString;
  }
  
  static String getMethodName(String paramString1, String paramString2)
  {
    Object localObject = paramString1;
    if (paramString2 != null)
    {
      if (paramString2.length() == 0) {
        return paramString1;
      }
      char c = Character.toUpperCase(paramString2.charAt(0));
      paramString2 = paramString2.substring(1);
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString1);
      ((StringBuilder)localObject).append(c);
      ((StringBuilder)localObject).append(paramString2);
      localObject = ((StringBuilder)localObject).toString();
    }
    return localObject;
  }
  
  private Method getPropertyFunction(Class paramClass1, String paramString, Class paramClass2)
  {
    Method localMethod1 = null;
    String str = getMethodName(paramString, mPropertyName);
    if (paramClass2 == null) {
      try
      {
        paramString = paramClass1.getMethod(str, null);
        return paramString;
      }
      catch (NoSuchMethodException paramClass2)
      {
        paramString = localMethod1;
        try
        {
          paramClass1 = paramClass1.getDeclaredMethod(str, null);
          paramString = paramClass1;
          paramClass1.setAccessible(true);
          return paramClass1;
        }
        catch (NoSuchMethodException paramClass1)
        {
          paramClass1 = new StringBuilder();
          paramClass1.append("Couldn't find no-arg method for property ");
          paramClass1.append(mPropertyName);
          paramClass1.append(": ");
          paramClass1.append(paramClass2);
          Log.e("PropertyValuesHolder", paramClass1.toString());
          return paramString;
        }
      }
    }
    Class[] arrayOfClass = new Class[1];
    if (mValueType.equals(Float.class)) {
      paramString = DOUBLE_VARIANTS;
    } else if (mValueType.equals(Integer.class)) {
      paramString = FLOAT_VARIANTS;
    } else if (mValueType.equals(Double.class)) {
      paramString = INTEGER_VARIANTS;
    } else {
      paramString = new Class[] { mValueType };
    }
    int j = paramString.length;
    paramClass2 = null;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramString[i];
      arrayOfClass[0] = localObject;
      try
      {
        localMethod1 = paramClass1.getMethod(str, arrayOfClass);
        mValueType = localObject;
        return localMethod1;
      }
      catch (NoSuchMethodException localNoSuchMethodException1)
      {
        try
        {
          Method localMethod2 = paramClass1.getDeclaredMethod(str, arrayOfClass);
          paramClass2 = localMethod2;
          localMethod2.setAccessible(true);
          mValueType = localObject;
          return localMethod2;
        }
        catch (NoSuchMethodException localNoSuchMethodException2)
        {
          i += 1;
        }
      }
    }
    paramClass1 = new StringBuilder();
    paramClass1.append("Couldn't find setter/getter for property ");
    paramClass1.append(mPropertyName);
    paramClass1.append(" with value type ");
    paramClass1.append(mValueType);
    Log.e("PropertyValuesHolder", paramClass1.toString());
    return paramClass2;
  }
  
  public static PropertyValuesHolder ofFloat(Property paramProperty, float... paramVarArgs)
  {
    return new FloatPropertyValuesHolder(paramVarArgs);
  }
  
  public static PropertyValuesHolder ofFloat(String paramString, float... paramVarArgs)
  {
    return new FloatPropertyValuesHolder(paramVarArgs);
  }
  
  private void setupGetter(Class paramClass)
  {
    mGetter = setupSetterOrGetter(paramClass, sGetterPropertyMap, "get", null);
  }
  
  private Method setupSetterOrGetter(Class paramClass1, HashMap paramHashMap, String paramString, Class paramClass2)
  {
    Method localMethod = null;
    try
    {
      mPropertyMapLock.writeLock().lock();
      HashMap localHashMap = (HashMap)paramHashMap.get(paramClass1);
      if (localHashMap != null) {
        localMethod = (Method)localHashMap.get(mPropertyName);
      }
      Object localObject = localMethod;
      if (localMethod == null)
      {
        localMethod = getPropertyFunction(paramClass1, paramString, paramClass2);
        paramString = localMethod;
        paramClass2 = localHashMap;
        if (localHashMap == null)
        {
          localObject = new HashMap();
          paramClass2 = (Class)localObject;
          paramHashMap.put(paramClass1, localObject);
        }
        paramClass2.put(mPropertyName, localMethod);
        localObject = paramString;
      }
      mPropertyMapLock.writeLock().unlock();
      return localObject;
    }
    catch (Throwable paramClass1)
    {
      mPropertyMapLock.writeLock().unlock();
      throw paramClass1;
    }
  }
  
  void calculateValue(float paramFloat)
  {
    mAnimatedValue = mKeyframeSet.getValue(paramFloat);
  }
  
  public PropertyValuesHolder clone()
  {
    try
    {
      Object localObject = super.clone();
      localObject = (PropertyValuesHolder)localObject;
      mPropertyName = mPropertyName;
      mProperty = mProperty;
      KeyframeSet localKeyframeSet = mKeyframeSet;
      localKeyframeSet = localKeyframeSet.clone();
      mKeyframeSet = localKeyframeSet;
      mEvaluator = mEvaluator;
      return localObject;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException) {}
    return null;
  }
  
  Object getAnimatedValue()
  {
    return mAnimatedValue;
  }
  
  public String getPropertyName()
  {
    return mPropertyName;
  }
  
  void init()
  {
    if (mEvaluator == null)
    {
      localObject = mValueType;
      if (localObject == Integer.class) {
        localObject = sIntEvaluator;
      } else if (localObject == Float.class) {
        localObject = sFloatEvaluator;
      } else {
        localObject = null;
      }
      mEvaluator = ((TypeEvaluator)localObject);
    }
    Object localObject = mEvaluator;
    if (localObject != null) {
      mKeyframeSet.setEvaluator((TypeEvaluator)localObject);
    }
  }
  
  void setAnimatedValue(Object paramObject)
  {
    Object localObject1 = mProperty;
    if (localObject1 != null) {
      ((Property)localObject1).set(paramObject, getAnimatedValue());
    }
    if (mSetter != null)
    {
      localObject1 = mTmpValueArray;
      try
      {
        Object localObject2 = getAnimatedValue();
        localObject1[0] = localObject2;
        localObject1 = mSetter;
        localObject2 = mTmpValueArray;
        ((Method)localObject1).invoke(paramObject, (Object[])localObject2);
        return;
      }
      catch (IllegalAccessException paramObject)
      {
        Log.e("PropertyValuesHolder", paramObject.toString());
        return;
      }
      catch (InvocationTargetException paramObject)
      {
        Log.e("PropertyValuesHolder", paramObject.toString());
      }
    }
  }
  
  public void setEvaluator(TypeEvaluator paramTypeEvaluator)
  {
    mEvaluator = paramTypeEvaluator;
    mKeyframeSet.setEvaluator(paramTypeEvaluator);
  }
  
  public void setFloatValues(float... paramVarArgs)
  {
    mValueType = Float.TYPE;
    mKeyframeSet = KeyframeSet.ofFloat(paramVarArgs);
  }
  
  public void setProperty(Property paramProperty)
  {
    mProperty = paramProperty;
  }
  
  public void setPropertyName(String paramString)
  {
    mPropertyName = paramString;
  }
  
  void setupSetter(Class paramClass)
  {
    mSetter = setupSetterOrGetter(paramClass, sSetterPropertyMap, "set", mValueType);
  }
  
  void setupSetterAndGetter(Object paramObject)
  {
    Object localObject1 = mProperty;
    Object localObject4;
    if (localObject1 != null) {
      try
      {
        ((Property)localObject1).get(paramObject);
        localObject1 = mKeyframeSet.mKeyframes;
        localObject1 = ((ArrayList)localObject1).iterator();
        for (;;)
        {
          boolean bool = ((Iterator)localObject1).hasNext();
          if (!bool) {
            break;
          }
          localObject3 = (Keyframe)((Iterator)localObject1).next();
          bool = ((Keyframe)localObject3).hasValue();
          if (!bool)
          {
            localObject4 = mProperty;
            ((Keyframe)localObject3).setValue(((Property)localObject4).get(paramObject));
          }
        }
        return;
      }
      catch (ClassCastException localClassCastException)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("No such property (");
        ((StringBuilder)localObject2).append(mProperty.getName());
        ((StringBuilder)localObject2).append(") on target object ");
        ((StringBuilder)localObject2).append(paramObject);
        ((StringBuilder)localObject2).append(". Trying reflection instead");
        Log.e("PropertyValuesHolder", ((StringBuilder)localObject2).toString());
        mProperty = null;
      }
    }
    Object localObject2 = paramObject.getClass();
    if (mSetter == null) {
      setupSetter((Class)localObject2);
    }
    Object localObject3 = mKeyframeSet.mKeyframes.iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (Keyframe)((Iterator)localObject3).next();
      if (!((Keyframe)localObject4).hasValue())
      {
        if (mGetter == null) {
          setupGetter((Class)localObject2);
        }
        Method localMethod = mGetter;
        try
        {
          ((Keyframe)localObject4).setValue(localMethod.invoke(paramObject, new Object[0]));
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          Log.e("PropertyValuesHolder", localIllegalAccessException.toString());
        }
        catch (InvocationTargetException localInvocationTargetException)
        {
          Log.e("PropertyValuesHolder", localInvocationTargetException.toString());
        }
      }
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(mPropertyName);
    localStringBuilder.append(": ");
    localStringBuilder.append(mKeyframeSet.toString());
    return localStringBuilder.toString();
  }
  
  class FloatPropertyValuesHolder
    extends PropertyValuesHolder
  {
    float mFloatAnimatedValue;
    FloatKeyframeSet mFloatKeyframeSet;
    private IntProperty mFloatProperty;
    
    public FloatPropertyValuesHolder(float... paramVarArgs)
    {
      super(null);
      setFloatValues(paramVarArgs);
      if ((this$1 instanceof IntProperty)) {
        mFloatProperty = ((IntProperty)mProperty);
      }
    }
    
    public FloatPropertyValuesHolder(float... paramVarArgs)
    {
      super(null);
      setFloatValues(paramVarArgs);
    }
    
    void calculateValue(float paramFloat)
    {
      mFloatAnimatedValue = mFloatKeyframeSet.getFloatValue(paramFloat);
    }
    
    public FloatPropertyValuesHolder clone()
    {
      FloatPropertyValuesHolder localFloatPropertyValuesHolder = (FloatPropertyValuesHolder)super.clone();
      mFloatKeyframeSet = ((FloatKeyframeSet)mKeyframeSet);
      return localFloatPropertyValuesHolder;
    }
    
    Object getAnimatedValue()
    {
      return Float.valueOf(mFloatAnimatedValue);
    }
    
    void setAnimatedValue(Object paramObject)
    {
      Object localObject = mFloatProperty;
      if (localObject != null)
      {
        ((IntProperty)localObject).setValue(paramObject, mFloatAnimatedValue);
        return;
      }
      localObject = mProperty;
      if (localObject != null)
      {
        ((Property)localObject).set(paramObject, Float.valueOf(mFloatAnimatedValue));
        return;
      }
      if (mSetter != null)
      {
        localObject = mTmpValueArray;
        float f = mFloatAnimatedValue;
        localObject[0] = Float.valueOf(f);
        localObject = mSetter;
        Object[] arrayOfObject = mTmpValueArray;
        try
        {
          ((Method)localObject).invoke(paramObject, arrayOfObject);
          return;
        }
        catch (IllegalAccessException paramObject)
        {
          Log.e("PropertyValuesHolder", paramObject.toString());
          return;
        }
        catch (InvocationTargetException paramObject)
        {
          Log.e("PropertyValuesHolder", paramObject.toString());
        }
      }
    }
    
    public void setFloatValues(float... paramVarArgs)
    {
      super.setFloatValues(paramVarArgs);
      mFloatKeyframeSet = ((FloatKeyframeSet)mKeyframeSet);
    }
    
    void setupSetter(Class paramClass)
    {
      if (mProperty != null) {
        return;
      }
      super.setupSetter(paramClass);
    }
  }
}
