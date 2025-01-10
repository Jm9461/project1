package org.org.android.asm;

import a.b.c.l.i;
import a.b.g.g.n;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import org.org.jaxen.util.f;

public class Attribute
{
  private final n<String, i> b = new f();
  
  public Attribute() {}
  
  public static Attribute a(Context paramContext, int paramInt)
  {
    try
    {
      paramContext = AnimatorInflater.loadAnimator(paramContext, paramInt);
      if ((paramContext instanceof AnimatorSet))
      {
        paramContext = (AnimatorSet)paramContext;
        paramContext = a(paramContext.getChildAnimations());
        return paramContext;
      }
      if (paramContext != null)
      {
        localObject = new ArrayList();
        ((List)localObject).add(paramContext);
        paramContext = a((List)localObject);
        return paramContext;
      }
      return null;
    }
    catch (Exception paramContext)
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Can't load animation resource ID #0x");
      ((StringBuilder)localObject).append(Integer.toHexString(paramInt));
      Log.w("MotionSpec", ((StringBuilder)localObject).toString(), paramContext);
    }
    return null;
  }
  
  public static Attribute a(Context paramContext, TypedArray paramTypedArray, int paramInt)
  {
    if (paramTypedArray.hasValue(paramInt))
    {
      paramInt = paramTypedArray.getResourceId(paramInt, 0);
      if (paramInt != 0) {
        return a(paramContext, paramInt);
      }
    }
    return null;
  }
  
  private static Attribute a(List paramList)
  {
    Attribute localAttribute = new Attribute();
    int i = 0;
    int j = paramList.size();
    while (i < j)
    {
      write(localAttribute, (Animator)paramList.get(i));
      i += 1;
    }
    return localAttribute;
  }
  
  private static void write(Attribute paramAttribute, Animator paramAnimator)
  {
    if ((paramAnimator instanceof ObjectAnimator))
    {
      paramAnimator = (ObjectAnimator)paramAnimator;
      paramAttribute.a(paramAnimator.getPropertyName(), Label.a(paramAnimator));
      return;
    }
    paramAttribute = new StringBuilder();
    paramAttribute.append("Animator must be an ObjectAnimator: ");
    paramAttribute.append(paramAnimator);
    throw new IllegalArgumentException(paramAttribute.toString());
  }
  
  public long a()
  {
    long l = 0L;
    int i = 0;
    int j = b.size();
    while (i < j)
    {
      Label localLabel = (Label)b.get(i);
      l = Math.max(l, localLabel.a() + localLabel.getValue());
      i += 1;
    }
    return l;
  }
  
  public Label a(String paramString)
  {
    if (b(paramString)) {
      return (Label)b.get(paramString);
    }
    throw new IllegalArgumentException();
  }
  
  public void a(String paramString, Label paramLabel)
  {
    b.put(paramString, paramLabel);
  }
  
  public boolean b(String paramString)
  {
    return b.get(paramString) != null;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject != null) && (getClass() == paramObject.getClass()))
    {
      paramObject = (Attribute)paramObject;
      return b.equals(b);
    }
    return false;
  }
  
  public int hashCode()
  {
    return b.hashCode();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('\n');
    localStringBuilder.append(getClass().getName());
    localStringBuilder.append('{');
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    localStringBuilder.append(" timings: ");
    localStringBuilder.append(b);
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}
