package me.zhanghai.android.materialprogressbar.internal;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Property;

class ObjectAnimatorCompatBase
{
  private static final int NUM_POINTS = 201;
  
  private ObjectAnimatorCompatBase() {}
  
  private static void calculateXYValues(Path paramPath, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    paramPath = new PathMeasure(paramPath, false);
    float f = paramPath.getLength();
    float[] arrayOfFloat = new float[2];
    int i = 0;
    while (i < 201)
    {
      paramPath.getPosTan(i * f / 200.0F, arrayOfFloat, null);
      paramArrayOfFloat1[i] = arrayOfFloat[0];
      paramArrayOfFloat2[i] = arrayOfFloat[1];
      i += 1;
    }
  }
  
  private static void calculateXYValues(Path paramPath, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    paramPath = new PathMeasure(paramPath, false);
    float f = paramPath.getLength();
    float[] arrayOfFloat = new float[2];
    int i = 0;
    while (i < 201)
    {
      paramPath.getPosTan(i * f / 200.0F, arrayOfFloat, null);
      paramArrayOfInt1[i] = Math.round(arrayOfFloat[0]);
      paramArrayOfInt2[i] = Math.round(arrayOfFloat[1]);
      i += 1;
    }
  }
  
  public static ObjectAnimator ofArgb(Object paramObject, Property paramProperty, int... paramVarArgs)
  {
    paramObject = ObjectAnimator.ofInt(paramObject, paramProperty, paramVarArgs);
    paramObject.setEvaluator(new ArgbEvaluator());
    return paramObject;
  }
  
  public static ObjectAnimator ofArgb(Object paramObject, String paramString, int... paramVarArgs)
  {
    paramObject = ObjectAnimator.ofInt(paramObject, paramString, paramVarArgs);
    paramObject.setEvaluator(new ArgbEvaluator());
    return paramObject;
  }
  
  public static ObjectAnimator ofFloat(Object paramObject, Property paramProperty1, Property paramProperty2, Path paramPath)
  {
    float[] arrayOfFloat1 = new float['?'];
    float[] arrayOfFloat2 = new float['?'];
    calculateXYValues(paramPath, arrayOfFloat1, arrayOfFloat2);
    return ObjectAnimator.ofPropertyValuesHolder(paramObject, new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat(paramProperty1, arrayOfFloat1), PropertyValuesHolder.ofFloat(paramProperty2, arrayOfFloat2) });
  }
  
  public static ObjectAnimator ofFloat(Object paramObject, String paramString1, String paramString2, Path paramPath)
  {
    float[] arrayOfFloat1 = new float['?'];
    float[] arrayOfFloat2 = new float['?'];
    calculateXYValues(paramPath, arrayOfFloat1, arrayOfFloat2);
    return ObjectAnimator.ofPropertyValuesHolder(paramObject, new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat(paramString1, arrayOfFloat1), PropertyValuesHolder.ofFloat(paramString2, arrayOfFloat2) });
  }
  
  public static ObjectAnimator ofInt(Object paramObject, Property paramProperty1, Property paramProperty2, Path paramPath)
  {
    int[] arrayOfInt1 = new int['?'];
    int[] arrayOfInt2 = new int['?'];
    calculateXYValues(paramPath, arrayOfInt1, arrayOfInt2);
    return ObjectAnimator.ofPropertyValuesHolder(paramObject, new PropertyValuesHolder[] { PropertyValuesHolder.ofInt(paramProperty1, arrayOfInt1), PropertyValuesHolder.ofInt(paramProperty2, arrayOfInt2) });
  }
  
  public static ObjectAnimator ofInt(Object paramObject, String paramString1, String paramString2, Path paramPath)
  {
    int[] arrayOfInt1 = new int['?'];
    int[] arrayOfInt2 = new int['?'];
    calculateXYValues(paramPath, arrayOfInt1, arrayOfInt2);
    return ObjectAnimator.ofPropertyValuesHolder(paramObject, new PropertyValuesHolder[] { PropertyValuesHolder.ofInt(paramString1, arrayOfInt1), PropertyValuesHolder.ofInt(paramString2, arrayOfInt2) });
  }
}
