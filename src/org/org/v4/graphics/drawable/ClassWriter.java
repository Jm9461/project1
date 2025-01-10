package org.org.v4.graphics.drawable;

import a.b.g.g.f;
import a.b.g.g.o;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.StateSet;
import org.org.jaxen.util.LongSparseArray;

class ClassWriter
  extends Document
{
  f<Long> a;
  o<Integer> b;
  
  ClassWriter(ClassWriter paramClassWriter, VectorDrawableCompat paramVectorDrawableCompat, Resources paramResources)
  {
    super(paramClassWriter, paramVectorDrawableCompat, paramResources);
    if (paramClassWriter != null)
    {
      a = a;
      b = b;
      return;
    }
    a = new LongSparseArray();
    b = new org.org.jaxen.util.ClassWriter();
  }
  
  private static long add(int paramInt1, int paramInt2)
  {
    return paramInt1 << 32 | paramInt2;
  }
  
  int a(int paramInt)
  {
    if (paramInt < 0) {
      return 0;
    }
    return ((Integer)b.get(paramInt, Integer.valueOf(0))).intValue();
  }
  
  int a(int paramInt1, int paramInt2)
  {
    long l = add(paramInt1, paramInt2);
    return (int)((Long)a.get(l, Long.valueOf(-1L))).longValue();
  }
  
  int a(int[] paramArrayOfInt)
  {
    int i = super.write(paramArrayOfInt);
    if (i >= 0) {
      return i;
    }
    return super.write(StateSet.WILD_CARD);
  }
  
  int a(int[] paramArrayOfInt, Drawable paramDrawable, int paramInt)
  {
    int i = super.addChild(paramArrayOfInt, paramDrawable);
    b.put(i, Integer.valueOf(paramInt));
    return i;
  }
  
  boolean get(int paramInt1, int paramInt2)
  {
    long l = add(paramInt1, paramInt2);
    return (((Long)a.get(l, Long.valueOf(-1L))).longValue() & 0x200000000) != 0L;
  }
  
  void init()
  {
    a = a.clone();
    b = b.clone();
  }
  
  public Drawable newDrawable()
  {
    return new VectorDrawableCompat(this, null);
  }
  
  public Drawable newDrawable(Resources paramResources)
  {
    return new VectorDrawableCompat(this, paramResources);
  }
  
  int put(int paramInt1, int paramInt2, Drawable paramDrawable, boolean paramBoolean)
  {
    int i = super.addChild(paramDrawable);
    long l2 = add(paramInt1, paramInt2);
    long l1 = 0L;
    if (paramBoolean) {
      l1 = 8589934592L;
    }
    a.append(l2, Long.valueOf(i | l1));
    if (paramBoolean)
    {
      l2 = add(paramInt2, paramInt1);
      a.append(l2, Long.valueOf(i | 0x100000000 | l1));
    }
    return i;
  }
  
  boolean put(int paramInt1, int paramInt2)
  {
    long l = add(paramInt1, paramInt2);
    return (((Long)a.get(l, Long.valueOf(-1L))).longValue() & 0x100000000) != 0L;
  }
}
