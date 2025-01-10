package org.org.jaxen.util;

import java.util.ConcurrentModificationException;
import java.util.Map;

public class f<K, V>
{
  static Object[] b;
  static int e;
  static Object[] q;
  static int s;
  Object[] a;
  int[] c;
  int n;
  
  public f()
  {
    c = ContainerHelpers.c;
    a = ContainerHelpers.a;
    n = 0;
  }
  
  public f(int paramInt)
  {
    if (paramInt == 0)
    {
      c = ContainerHelpers.c;
      a = ContainerHelpers.a;
    }
    else
    {
      a(paramInt);
    }
    n = 0;
  }
  
  public f(f paramF)
  {
    this();
    if (paramF != null) {
      a(paramF);
    }
  }
  
  private static int a(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    try
    {
      paramInt1 = ContainerHelpers.get(paramArrayOfInt, paramInt1, paramInt2);
      return paramInt1;
    }
    catch (ArrayIndexOutOfBoundsException paramArrayOfInt)
    {
      throw new ConcurrentModificationException();
    }
  }
  
  private void a(int paramInt)
  {
    if (paramInt == 8) {
      try
      {
        if (q != null)
        {
          Object[] arrayOfObject1 = q;
          a = arrayOfObject1;
          q = (Object[])arrayOfObject1[0];
          c = ((int[])arrayOfObject1[1]);
          arrayOfObject1[1] = null;
          arrayOfObject1[0] = null;
          s -= 1;
          return;
        }
      }
      catch (Throwable localThrowable1)
      {
        throw localThrowable1;
      }
    }
    if (paramInt == 4) {
      try
      {
        if (b != null)
        {
          Object[] arrayOfObject2 = b;
          a = arrayOfObject2;
          b = (Object[])arrayOfObject2[0];
          c = ((int[])arrayOfObject2[1]);
          arrayOfObject2[1] = null;
          arrayOfObject2[0] = null;
          e -= 1;
          return;
        }
      }
      catch (Throwable localThrowable2)
      {
        throw localThrowable2;
      }
    }
    c = new int[paramInt];
    a = new Object[paramInt << 1];
  }
  
  private static void a(int[] paramArrayOfInt, Object[] paramArrayOfObject, int paramInt)
  {
    if (paramArrayOfInt.length == 8) {
      try
      {
        if (s < 10)
        {
          paramArrayOfObject[0] = q;
          paramArrayOfObject[1] = paramArrayOfInt;
          paramInt = (paramInt << 1) - 1;
          break label118;
          q = paramArrayOfObject;
          s += 1;
        }
        return;
      }
      catch (Throwable paramArrayOfInt)
      {
        throw paramArrayOfInt;
      }
    }
    if (paramArrayOfInt.length == 4) {}
    for (;;)
    {
      try
      {
        if (e < 10)
        {
          paramArrayOfObject[0] = b;
          paramArrayOfObject[1] = paramArrayOfInt;
          paramInt = (paramInt << 1) - 1;
          break label134;
          b = paramArrayOfObject;
          e += 1;
        }
        return;
      }
      catch (Throwable paramArrayOfInt)
      {
        throw paramArrayOfInt;
      }
      return;
      label118:
      while (paramInt >= 2)
      {
        paramArrayOfObject[paramInt] = null;
        paramInt -= 1;
      }
      break;
      label134:
      while (paramInt >= 2)
      {
        paramArrayOfObject[paramInt] = null;
        paramInt -= 1;
      }
    }
  }
  
  int a()
  {
    int j = n;
    if (j == 0) {
      return -1;
    }
    int k = a(c, j, 0);
    if (k < 0) {
      return k;
    }
    if (a[(k << 1)] == null) {
      return k;
    }
    int i = k + 1;
    while ((i < j) && (c[i] == 0))
    {
      if (a[(i << 1)] == null) {
        return i;
      }
      i += 1;
    }
    j = k - 1;
    while ((j >= 0) && (c[j] == 0))
    {
      if (a[(j << 1)] == null) {
        return j;
      }
      j -= 1;
    }
    return i;
  }
  
  int a(Object paramObject, int paramInt)
  {
    int j = n;
    if (j == 0) {
      return -1;
    }
    int k = a(c, j, paramInt);
    if (k < 0) {
      return k;
    }
    if (paramObject.equals(a[(k << 1)])) {
      return k;
    }
    int i = k + 1;
    while ((i < j) && (c[i] == paramInt))
    {
      if (paramObject.equals(a[(i << 1)])) {
        return i;
      }
      i += 1;
    }
    j = k - 1;
    while ((j >= 0) && (c[j] == paramInt))
    {
      if (paramObject.equals(a[(j << 1)])) {
        return j;
      }
      j -= 1;
    }
    return i;
  }
  
  public Object a(int paramInt, Object paramObject)
  {
    paramInt = (paramInt << 1) + 1;
    Object[] arrayOfObject = a;
    Object localObject = arrayOfObject[paramInt];
    arrayOfObject[paramInt] = paramObject;
    return localObject;
  }
  
  public void a(f paramF)
  {
    int j = n;
    add(n + j);
    if (n == 0)
    {
      if (j > 0)
      {
        System.arraycopy(c, 0, c, 0, j);
        System.arraycopy(a, 0, a, 0, j << 1);
        n = j;
      }
    }
    else
    {
      int i = 0;
      while (i < j)
      {
        put(paramF.getValue(i), paramF.get(i));
        i += 1;
      }
    }
  }
  
  public int add(Object paramObject)
  {
    if (paramObject == null) {
      return a();
    }
    return a(paramObject, paramObject.hashCode());
  }
  
  public void add(int paramInt)
  {
    int i = n;
    if (c.length < paramInt)
    {
      int[] arrayOfInt = c;
      Object[] arrayOfObject = a;
      a(paramInt);
      if (n > 0)
      {
        System.arraycopy(arrayOfInt, 0, c, 0, i);
        System.arraycopy(arrayOfObject, 0, a, 0, i << 1);
      }
      a(arrayOfInt, arrayOfObject, i);
    }
    if (n == i) {
      return;
    }
    throw new ConcurrentModificationException();
  }
  
  public void clear()
  {
    if (n > 0)
    {
      int[] arrayOfInt = c;
      Object[] arrayOfObject = a;
      int i = n;
      c = ContainerHelpers.c;
      a = ContainerHelpers.a;
      n = 0;
      a(arrayOfInt, arrayOfObject, i);
    }
    if (n <= 0) {
      return;
    }
    throw new ConcurrentModificationException();
  }
  
  public boolean containsKey(Object paramObject)
  {
    return add(paramObject) >= 0;
  }
  
  public boolean containsValue(Object paramObject)
  {
    return read(paramObject) >= 0;
  }
  
  public Object d(int paramInt)
  {
    Object localObject2 = a;
    Object localObject1 = localObject2[((paramInt << 1) + 1)];
    int k = n;
    if (k <= 1)
    {
      a(c, (Object[])localObject2, k);
      c = ContainerHelpers.c;
      a = ContainerHelpers.a;
      paramInt = 0;
    }
    else
    {
      int j = k - 1;
      localObject2 = c;
      int m = localObject2.length;
      int i = 8;
      if ((m > 8) && (n < localObject2.length / 3))
      {
        if (k > 8) {
          i = k + (k >> 1);
        }
        localObject2 = c;
        Object[] arrayOfObject = a;
        a(i);
        if (k == n)
        {
          if (paramInt > 0)
          {
            System.arraycopy(localObject2, 0, c, 0, paramInt);
            System.arraycopy(arrayOfObject, 0, a, 0, paramInt << 1);
          }
          if (paramInt < j)
          {
            System.arraycopy(localObject2, paramInt + 1, c, paramInt, j - paramInt);
            System.arraycopy(arrayOfObject, paramInt + 1 << 1, a, paramInt << 1, j - paramInt << 1);
          }
          paramInt = j;
        }
        else
        {
          throw new ConcurrentModificationException();
        }
      }
      else
      {
        if (paramInt < j)
        {
          localObject2 = c;
          System.arraycopy(localObject2, paramInt + 1, localObject2, paramInt, j - paramInt);
          localObject2 = a;
          System.arraycopy(localObject2, paramInt + 1 << 1, localObject2, paramInt << 1, j - paramInt << 1);
        }
        localObject2 = a;
        localObject2[(j << 1)] = null;
        localObject2[((j << 1) + 1)] = null;
        paramInt = j;
      }
    }
    if (k == n)
    {
      n = paramInt;
      return localObject1;
    }
    throw new ConcurrentModificationException();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    int i;
    int j;
    Object localObject1;
    Object localObject2;
    Object localObject3;
    boolean bool;
    if ((paramObject instanceof f))
    {
      paramObject = (f)paramObject;
      if (size() != paramObject.size()) {
        return false;
      }
      i = 0;
      try
      {
        for (;;)
        {
          j = n;
          if (i < j) {}
          try
          {
            localObject1 = getValue(i);
            localObject2 = get(i);
            localObject3 = paramObject.get(localObject1);
            if (localObject2 == null)
            {
              if (localObject3 != null) {
                break label241;
              }
              bool = paramObject.containsKey(localObject1);
              if (!bool) {
                return false;
              }
            }
            else
            {
              bool = localObject2.equals(localObject3);
              if (!bool) {
                return false;
              }
            }
            i += 1;
          }
          catch (ClassCastException paramObject)
          {
            return false;
          }
        }
        return true;
      }
      catch (NullPointerException paramObject)
      {
        return false;
      }
    }
    if ((paramObject instanceof Map))
    {
      paramObject = (Map)paramObject;
      if (size() != paramObject.size()) {
        return false;
      }
      i = 0;
      try
      {
        for (;;)
        {
          j = n;
          if (i < j) {}
          try
          {
            localObject1 = getValue(i);
            localObject2 = get(i);
            localObject3 = paramObject.get(localObject1);
            if (localObject2 == null)
            {
              if (localObject3 != null) {
                break label241;
              }
              bool = paramObject.containsKey(localObject1);
              if (!bool) {
                return false;
              }
            }
            else
            {
              bool = localObject2.equals(localObject3);
              if (!bool) {
                return false;
              }
            }
            i += 1;
          }
          catch (ClassCastException paramObject)
          {
            return false;
          }
        }
        return true;
      }
      catch (NullPointerException paramObject) {}
    }
    label241:
    return false;
  }
  
  public Object get(int paramInt)
  {
    return a[((paramInt << 1) + 1)];
  }
  
  public Object get(Object paramObject)
  {
    int i = add(paramObject);
    if (i >= 0) {
      return a[((i << 1) + 1)];
    }
    return null;
  }
  
  public Object getValue(int paramInt)
  {
    return a[(paramInt << 1)];
  }
  
  public int hashCode()
  {
    int[] arrayOfInt = c;
    Object[] arrayOfObject = a;
    int k = 0;
    int j = 0;
    int i = 1;
    int i1 = n;
    while (j < i1)
    {
      Object localObject = arrayOfObject[i];
      int i2 = arrayOfInt[j];
      int m;
      if (localObject == null) {
        m = 0;
      } else {
        m = localObject.hashCode();
      }
      k += (i2 ^ m);
      j += 1;
      i += 2;
    }
    return k;
  }
  
  public boolean isEmpty()
  {
    return n <= 0;
  }
  
  public Object put(Object paramObject1, Object paramObject2)
  {
    int k = n;
    int j;
    if (paramObject1 == null)
    {
      j = 0;
      i = a();
    }
    else
    {
      i = paramObject1.hashCode();
      j = i;
      i = a(paramObject1, i);
    }
    Object localObject;
    if (i >= 0)
    {
      i = (i << 1) + 1;
      paramObject1 = a;
      localObject = paramObject1[i];
      paramObject1[i] = paramObject2;
      return localObject;
    }
    int m = i;
    if (k >= c.length)
    {
      i = 4;
      if (k >= 8) {
        i = (k >> 1) + k;
      } else if (k >= 4) {
        i = 8;
      }
      localObject = c;
      Object[] arrayOfObject = a;
      a(i);
      if (k == n)
      {
        int[] arrayOfInt = c;
        if (arrayOfInt.length > 0)
        {
          System.arraycopy(localObject, 0, arrayOfInt, 0, localObject.length);
          System.arraycopy(arrayOfObject, 0, a, 0, arrayOfObject.length);
        }
        a((int[])localObject, arrayOfObject, k);
      }
      else
      {
        throw new ConcurrentModificationException();
      }
    }
    if (m < k)
    {
      localObject = c;
      System.arraycopy(localObject, m, localObject, m + 1, k - m);
      localObject = a;
      System.arraycopy(localObject, m << 1, localObject, m + 1 << 1, n - m << 1);
    }
    int i = n;
    if (k == i)
    {
      localObject = c;
      if (m < localObject.length)
      {
        localObject[m] = j;
        localObject = a;
        localObject[(m << 1)] = paramObject1;
        localObject[((m << 1) + 1)] = paramObject2;
        n = (i + 1);
        return null;
      }
    }
    throw new ConcurrentModificationException();
  }
  
  int read(Object paramObject)
  {
    int j = n * 2;
    Object[] arrayOfObject = a;
    int i;
    if (paramObject == null)
    {
      i = 1;
      while (i < j)
      {
        if (arrayOfObject[i] == null) {
          return i >> 1;
        }
        i += 2;
      }
    }
    else
    {
      i = 1;
      while (i < j)
      {
        if (paramObject.equals(arrayOfObject[i])) {
          return i >> 1;
        }
        i += 2;
      }
    }
    return -1;
  }
  
  public Object remove(Object paramObject)
  {
    int i = add(paramObject);
    if (i >= 0) {
      return d(i);
    }
    return null;
  }
  
  public int size()
  {
    return n;
  }
  
  public String toString()
  {
    if (isEmpty()) {
      return "{}";
    }
    StringBuilder localStringBuilder = new StringBuilder(n * 28);
    localStringBuilder.append('{');
    int i = 0;
    while (i < n)
    {
      if (i > 0) {
        localStringBuilder.append(", ");
      }
      Object localObject = getValue(i);
      if (localObject != this) {
        localStringBuilder.append(localObject);
      } else {
        localStringBuilder.append("(this Map)");
      }
      localStringBuilder.append('=');
      localObject = get(i);
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
