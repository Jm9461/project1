package org.org.jaxen.util;

public class ClassWriter<E>
  implements Cloneable
{
  private static final Object b = new Object();
  private Object[] a;
  private int[] c;
  private int k;
  private boolean m = false;
  
  public ClassWriter()
  {
    this(10);
  }
  
  public ClassWriter(int paramInt)
  {
    if (paramInt == 0)
    {
      c = ContainerHelpers.c;
      a = ContainerHelpers.a;
    }
    else
    {
      paramInt = ContainerHelpers.idealIntArraySize(paramInt);
      c = new int[paramInt];
      a = new Object[paramInt];
    }
    k = 0;
  }
  
  private void d()
  {
    int i1 = k;
    int j = 0;
    int[] arrayOfInt = c;
    Object[] arrayOfObject = a;
    int i = 0;
    while (i < i1)
    {
      Object localObject = arrayOfObject[i];
      int n = j;
      if (localObject != b)
      {
        if (i != j)
        {
          arrayOfInt[j] = arrayOfInt[i];
          arrayOfObject[j] = localObject;
          arrayOfObject[i] = null;
        }
        n = j + 1;
      }
      i += 1;
      j = n;
    }
    m = false;
    k = j;
  }
  
  public void a()
  {
    int j = k;
    Object[] arrayOfObject = a;
    int i = 0;
    while (i < j)
    {
      arrayOfObject[i] = null;
      i += 1;
    }
    k = 0;
    m = false;
  }
  
  public void a(int paramInt, Object paramObject)
  {
    int i = k;
    if ((i != 0) && (paramInt <= c[(i - 1)]))
    {
      put(paramInt, paramObject);
      return;
    }
    if ((m) && (k >= c.length)) {
      d();
    }
    i = k;
    if (i >= c.length)
    {
      int j = ContainerHelpers.idealIntArraySize(i + 1);
      int[] arrayOfInt = new int[j];
      Object[] arrayOfObject = new Object[j];
      Object localObject = c;
      System.arraycopy(localObject, 0, arrayOfInt, 0, localObject.length);
      localObject = a;
      System.arraycopy(localObject, 0, arrayOfObject, 0, localObject.length);
      c = arrayOfInt;
      a = arrayOfObject;
    }
    c[i] = paramInt;
    a[i] = paramObject;
    k = (i + 1);
  }
  
  public void b(int paramInt)
  {
    paramInt = ContainerHelpers.get(c, k, paramInt);
    if (paramInt >= 0)
    {
      Object[] arrayOfObject = a;
      Object localObject1 = arrayOfObject[paramInt];
      Object localObject2 = b;
      if (localObject1 != localObject2)
      {
        arrayOfObject[paramInt] = localObject2;
        m = true;
      }
    }
  }
  
  public ClassWriter clone()
  {
    try
    {
      Object localObject1 = super.clone();
      localObject1 = (ClassWriter)localObject1;
      Object localObject2 = c;
      localObject2 = localObject2.clone();
      c = ((int[])localObject2);
      localObject2 = a;
      localObject2 = localObject2.clone();
      a = ((Object[])localObject2);
      return localObject1;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError(localCloneNotSupportedException);
    }
  }
  
  public void d(int paramInt)
  {
    b(paramInt);
  }
  
  public int get()
  {
    if (m) {
      d();
    }
    return k;
  }
  
  public Object get(int paramInt)
  {
    if (m) {
      d();
    }
    return a[paramInt];
  }
  
  public Object get(int paramInt, Object paramObject)
  {
    paramInt = ContainerHelpers.get(c, k, paramInt);
    Object localObject = paramObject;
    if (paramInt >= 0)
    {
      localObject = a;
      if (localObject[paramInt] == b) {
        return paramObject;
      }
      localObject = localObject[paramInt];
    }
    return localObject;
  }
  
  public int put(int paramInt)
  {
    if (m) {
      d();
    }
    return ContainerHelpers.get(c, k, paramInt);
  }
  
  public void put(int paramInt, Object paramObject)
  {
    int i = ContainerHelpers.get(c, k, paramInt);
    if (i >= 0)
    {
      a[i] = paramObject;
      return;
    }
    int j = i;
    Object localObject1;
    if (j < k)
    {
      localObject1 = a;
      if (localObject1[j] == b)
      {
        c[j] = paramInt;
        localObject1[j] = paramObject;
        return;
      }
    }
    i = j;
    if (m)
    {
      i = j;
      if (k >= c.length)
      {
        d();
        i = ContainerHelpers.get(c, k, paramInt);
      }
    }
    j = k;
    if (j >= c.length)
    {
      j = ContainerHelpers.idealIntArraySize(j + 1);
      localObject1 = new int[j];
      Object[] arrayOfObject = new Object[j];
      Object localObject2 = c;
      System.arraycopy(localObject2, 0, localObject1, 0, localObject2.length);
      localObject2 = a;
      System.arraycopy(localObject2, 0, arrayOfObject, 0, localObject2.length);
      c = ((int[])localObject1);
      a = arrayOfObject;
    }
    j = k;
    if (j - i != 0)
    {
      localObject1 = c;
      System.arraycopy(localObject1, i, localObject1, i + 1, j - i);
      localObject1 = a;
      System.arraycopy(localObject1, i, localObject1, i + 1, k - i);
    }
    c[i] = paramInt;
    a[i] = paramObject;
    k += 1;
  }
  
  public Object remove(int paramInt)
  {
    return get(paramInt, null);
  }
  
  public int toString(int paramInt)
  {
    if (m) {
      d();
    }
    return c[paramInt];
  }
  
  public String toString()
  {
    if (get() <= 0) {
      return "{}";
    }
    StringBuilder localStringBuilder = new StringBuilder(k * 28);
    localStringBuilder.append('{');
    int i = 0;
    while (i < k)
    {
      if (i > 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append(toString(i));
      localStringBuilder.append('=');
      Object localObject = get(i);
      if (localObject != this) {
        localStringBuilder.append(localObject);
      } else {
        localStringBuilder.append("(this Map)");
      }
      i += 1;
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}
