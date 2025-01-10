package me.zhanghai.android.materialprogressbar.internal;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.graphics.Path;
import android.util.Property;

@TargetApi(21)
class ObjectAnimatorCompatLollipop
{
  private ObjectAnimatorCompatLollipop() {}
  
  public static ObjectAnimator ofArgb(Object paramObject, Property paramProperty, int... paramVarArgs)
  {
    return ObjectAnimator.ofArgb(paramObject, paramProperty, paramVarArgs);
  }
  
  public static ObjectAnimator ofArgb(Object paramObject, String paramString, int... paramVarArgs)
  {
    return ObjectAnimator.ofArgb(paramObject, paramString, paramVarArgs);
  }
  
  public static ObjectAnimator ofFloat(Object paramObject, Property paramProperty1, Property paramProperty2, Path paramPath)
  {
    return ObjectAnimator.ofFloat(paramObject, paramProperty1, paramProperty2, paramPath);
  }
  
  public static ObjectAnimator ofFloat(Object paramObject, String paramString1, String paramString2, Path paramPath)
  {
    return ObjectAnimator.ofFloat(paramObject, paramString1, paramString2, paramPath);
  }
  
  public static ObjectAnimator ofInt(Object paramObject, Property paramProperty1, Property paramProperty2, Path paramPath)
  {
    return ObjectAnimator.ofInt(paramObject, paramProperty1, paramProperty2, paramPath);
  }
  
  public static ObjectAnimator ofInt(Object paramObject, String paramString1, String paramString2, Path paramPath)
  {
    return ObjectAnimator.ofInt(paramObject, paramString1, paramString2, paramPath);
  }
}
