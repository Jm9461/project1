package apps.v4.animation;

import android.view.View;
import apps.v4.internal.view.AnimatorProxy;
import apps.v4.widget.Property;
import b.h.b.c;
import java.util.HashMap;
import java.util.Map;

public final class ObjectAnimator
  extends ValueAnimator
{
  private static final Map<String, c> PROXY_PROPERTIES = new HashMap();
  private Property mProperty;
  private String mPropertyName;
  private Object mTarget;
  
  static
  {
    PROXY_PROPERTIES.put("alpha", PreHoneycombCompat.SCROLL_X);
    PROXY_PROPERTIES.put("pivotX", PreHoneycombCompat.SCROLL_Y);
    PROXY_PROPERTIES.put("pivotY", PreHoneycombCompat.PIVOT_X);
    PROXY_PROPERTIES.put("translationX", PreHoneycombCompat.PIVOT_Y);
    PROXY_PROPERTIES.put("translationY", PreHoneycombCompat.TRANSLATION_X);
    PROXY_PROPERTIES.put("rotation", PreHoneycombCompat.TRANSLATION_Y);
    PROXY_PROPERTIES.put("rotationX", PreHoneycombCompat.ROTATION);
    PROXY_PROPERTIES.put("rotationY", PreHoneycombCompat.ROTATION_X);
    PROXY_PROPERTIES.put("scaleX", PreHoneycombCompat.ROTATION_Y);
    PROXY_PROPERTIES.put("scaleY", PreHoneycombCompat.SCALE_X);
    PROXY_PROPERTIES.put("scrollX", PreHoneycombCompat.SCALE_Y);
    PROXY_PROPERTIES.put("scrollY", PreHoneycombCompat.X);
    PROXY_PROPERTIES.put("x", PreHoneycombCompat.Y);
    PROXY_PROPERTIES.put("y", PreHoneycombCompat.ALPHA);
  }
  
  public ObjectAnimator() {}
  
  private ObjectAnimator(Object paramObject, Property paramProperty)
  {
    mTarget = paramObject;
    setProperty(paramProperty);
  }
  
  private ObjectAnimator(Object paramObject, String paramString)
  {
    mTarget = paramObject;
    setPropertyName(paramString);
  }
  
  public static ObjectAnimator ofFloat(Object paramObject, Property paramProperty, float... paramVarArgs)
  {
    paramObject = new ObjectAnimator(paramObject, paramProperty);
    paramObject.setFloatValues(paramVarArgs);
    return paramObject;
  }
  
  public static ObjectAnimator ofFloat(Object paramObject, String paramString, float... paramVarArgs)
  {
    paramObject = new ObjectAnimator(paramObject, paramString);
    paramObject.setFloatValues(paramVarArgs);
    return paramObject;
  }
  
  void animateValue(float paramFloat)
  {
    super.animateValue(paramFloat);
    int j = mValues.length;
    int i = 0;
    while (i < j)
    {
      mValues[i].setAnimatedValue(mTarget);
      i += 1;
    }
  }
  
  public ObjectAnimator clone()
  {
    return (ObjectAnimator)super.clone();
  }
  
  void initAnimation()
  {
    if (!mInitialized)
    {
      if ((mProperty == null) && (AnimatorProxy.NEEDS_PROXY) && ((mTarget instanceof View)) && (PROXY_PROPERTIES.containsKey(mPropertyName))) {
        setProperty((Property)PROXY_PROPERTIES.get(mPropertyName));
      }
      int j = mValues.length;
      int i = 0;
      while (i < j)
      {
        mValues[i].setupSetterAndGetter(mTarget);
        i += 1;
      }
      super.initAnimation();
    }
  }
  
  public ObjectAnimator setDuration(long paramLong)
  {
    super.setDuration(paramLong);
    return this;
  }
  
  public void setFloatValues(float... paramVarArgs)
  {
    Object localObject = mValues;
    if ((localObject != null) && (localObject.length != 0))
    {
      super.setFloatValues(paramVarArgs);
      return;
    }
    localObject = mProperty;
    if (localObject != null)
    {
      setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat((Property)localObject, paramVarArgs) });
      return;
    }
    setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat(mPropertyName, paramVarArgs) });
  }
  
  public void setProperty(Property paramProperty)
  {
    Object localObject = mValues;
    if (localObject != null)
    {
      localObject = localObject[0];
      String str = ((PropertyValuesHolder)localObject).getPropertyName();
      ((PropertyValuesHolder)localObject).setProperty(paramProperty);
      mValuesMap.remove(str);
      mValuesMap.put(mPropertyName, localObject);
    }
    if (mProperty != null) {
      mPropertyName = paramProperty.getName();
    }
    mProperty = paramProperty;
    mInitialized = false;
  }
  
  public void setPropertyName(String paramString)
  {
    Object localObject = mValues;
    if (localObject != null)
    {
      localObject = localObject[0];
      String str = ((PropertyValuesHolder)localObject).getPropertyName();
      ((PropertyValuesHolder)localObject).setPropertyName(paramString);
      mValuesMap.remove(str);
      mValuesMap.put(paramString, localObject);
    }
    mPropertyName = paramString;
    mInitialized = false;
  }
  
  public void start()
  {
    super.start();
  }
  
  public String toString()
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("ObjectAnimator@");
    ((StringBuilder)localObject1).append(Integer.toHexString(hashCode()));
    ((StringBuilder)localObject1).append(", target ");
    ((StringBuilder)localObject1).append(mTarget);
    Object localObject2 = ((StringBuilder)localObject1).toString();
    localObject1 = localObject2;
    if (mValues != null)
    {
      int i = 0;
      while (i < mValues.length)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("\n    ");
        ((StringBuilder)localObject2).append(mValues[i].toString());
        localObject1 = ((StringBuilder)localObject2).toString();
        i += 1;
      }
    }
    return localObject2;
    return localObject1;
  }
}
