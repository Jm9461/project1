package org.org.android.asm;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class Label
{
  private long a = 300L;
  private int b = 0;
  private TimeInterpolator c = null;
  private long d = 0L;
  private int e = 1;
  
  public Label(long paramLong1, long paramLong2)
  {
    d = paramLong1;
    a = paramLong2;
  }
  
  public Label(long paramLong1, long paramLong2, TimeInterpolator paramTimeInterpolator)
  {
    d = paramLong1;
    a = paramLong2;
    c = paramTimeInterpolator;
  }
  
  static Label a(ValueAnimator paramValueAnimator)
  {
    Label localLabel = new Label(paramValueAnimator.getStartDelay(), paramValueAnimator.getDuration(), b(paramValueAnimator));
    b = paramValueAnimator.getRepeatCount();
    e = paramValueAnimator.getRepeatMode();
    return localLabel;
  }
  
  private static TimeInterpolator b(ValueAnimator paramValueAnimator)
  {
    TimeInterpolator localTimeInterpolator = paramValueAnimator.getInterpolator();
    if ((!(localTimeInterpolator instanceof AccelerateDecelerateInterpolator)) && (localTimeInterpolator != null))
    {
      if ((localTimeInterpolator instanceof AccelerateInterpolator)) {
        return Item.b;
      }
      paramValueAnimator = localTimeInterpolator;
      if ((localTimeInterpolator instanceof DecelerateInterpolator)) {
        paramValueAnimator = Item.a;
      }
      return paramValueAnimator;
    }
    return Item.c;
  }
  
  public long a()
  {
    return d;
  }
  
  public void add(Animator paramAnimator)
  {
    paramAnimator.setStartDelay(a());
    paramAnimator.setDuration(getValue());
    paramAnimator.setInterpolator(getName());
    if ((paramAnimator instanceof ValueAnimator))
    {
      ((ValueAnimator)paramAnimator).setRepeatCount(getCount());
      ((ValueAnimator)paramAnimator).setRepeatMode(next());
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject != null)
    {
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (Label)paramObject;
      if (a() != paramObject.a()) {
        return false;
      }
      if (getValue() != paramObject.getValue()) {
        return false;
      }
      if (getCount() != paramObject.getCount()) {
        return false;
      }
      if (next() != paramObject.next()) {
        return false;
      }
      return getName().getClass().equals(paramObject.getName().getClass());
    }
    return false;
  }
  
  public int getCount()
  {
    return b;
  }
  
  public TimeInterpolator getName()
  {
    TimeInterpolator localTimeInterpolator = c;
    if (localTimeInterpolator != null) {
      return localTimeInterpolator;
    }
    return Item.c;
  }
  
  public long getValue()
  {
    return a;
  }
  
  public int hashCode()
  {
    return ((((int)(a() ^ a() >>> 32) * 31 + (int)(getValue() ^ getValue() >>> 32)) * 31 + getName().getClass().hashCode()) * 31 + getCount()) * 31 + next();
  }
  
  public int next()
  {
    return e;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('\n');
    localStringBuilder.append(getClass().getName());
    localStringBuilder.append('{');
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    localStringBuilder.append(" delay: ");
    localStringBuilder.append(a());
    localStringBuilder.append(" duration: ");
    localStringBuilder.append(getValue());
    localStringBuilder.append(" interpolator: ");
    localStringBuilder.append(getName().getClass());
    localStringBuilder.append(" repeatCount: ");
    localStringBuilder.append(getCount());
    localStringBuilder.append(" repeatMode: ");
    localStringBuilder.append(next());
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
}
