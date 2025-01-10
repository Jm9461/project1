package me.zhanghai.android.materialprogressbar.internal;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Build.VERSION;
import android.util.Property;

public class ObjectAnimatorCompat
{
  private ObjectAnimatorCompat() {}
  
  public static ObjectAnimator ofArgb(Object paramObject, Property paramProperty, int... paramVarArgs)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return ObjectAnimatorCompatLollipop.ofArgb(paramObject, paramProperty, paramVarArgs);
    }
    return ObjectAnimatorCompatBase.ofArgb(paramObject, paramProperty, paramVarArgs);
  }
  
  public static ObjectAnimator ofArgb(Object paramObject, String paramString, int... paramVarArgs)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return ObjectAnimatorCompatLollipop.ofArgb(paramObject, paramString, paramVarArgs);
    }
    return ObjectAnimatorCompatBase.ofArgb(paramObject, paramString, paramVarArgs);
  }
  
  public static ObjectAnimator ofFloat(Object paramObject, Property paramProperty1, Property paramProperty2, Path paramPath)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return ObjectAnimatorCompatLollipop.ofFloat(paramObject, paramProperty1, paramProperty2, paramPath);
    }
    return ObjectAnimatorCompatBase.ofFloat(paramObject, paramProperty1, paramProperty2, paramPath);
  }
  
  public static ObjectAnimator ofFloat(Object paramObject, String paramString1, String paramString2, Path paramPath)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return ObjectAnimatorCompatLollipop.ofFloat(paramObject, paramString1, paramString2, paramPath);
    }
    return ObjectAnimatorCompatBase.ofFloat(paramObject, paramString1, paramString2, paramPath);
  }
  
  public static ObjectAnimator ofInt(Object paramObject, Property paramProperty1, Property paramProperty2, Path paramPath)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return ObjectAnimatorCompatLollipop.ofInt(paramObject, paramProperty1, paramProperty2, paramPath);
    }
    return ObjectAnimatorCompatBase.ofInt(paramObject, paramProperty1, paramProperty2, paramPath);
  }
  
  public static ObjectAnimator ofInt(Object paramObject, String paramString1, String paramString2, Path paramPath)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return ObjectAnimatorCompatLollipop.ofInt(paramObject, paramString1, paramString2, paramPath);
    }
    return ObjectAnimatorCompatBase.ofInt(paramObject, paramString1, paramString2, paramPath);
  }
}
