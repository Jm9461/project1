package org.org.jaxen.util;

import a.b.g.g.h;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public final class TFloatLinkedList<E>
  implements Collection<E>, Set<E>
{
  private static Object[] a;
  private static Object[] b;
  private static final Object[] head = new Object[0];
  private static int p;
  private static final int[] pos = new int[0];
  private static int q;
  int length;
  Object[] next;
  private int[] size;
  private h<E, E> tail;
  
  public TFloatLinkedList()
  {
    this(0);
  }
  
  public TFloatLinkedList(int paramInt)
  {
    if (paramInt == 0)
    {
      size = pos;
      next = head;
    }
    else
    {
      add(paramInt);
    }
    length = 0;
  }
  
  private MapCollections add()
  {
    if (tail == null) {
      tail = new DistinctMultiHashMap(this);
    }
    return tail;
  }
  
  private void add(int paramInt)
  {
    if (paramInt == 8) {
      try
      {
        if (b != null)
        {
          Object[] arrayOfObject1 = b;
          next = arrayOfObject1;
          b = (Object[])arrayOfObject1[0];
          size = ((int[])arrayOfObject1[1]);
          arrayOfObject1[1] = null;
          arrayOfObject1[0] = null;
          q -= 1;
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
        if (a != null)
        {
          Object[] arrayOfObject2 = a;
          next = arrayOfObject2;
          a = (Object[])arrayOfObject2[0];
          size = ((int[])arrayOfObject2[1]);
          arrayOfObject2[1] = null;
          arrayOfObject2[0] = null;
          p -= 1;
          return;
        }
      }
      catch (Throwable localThrowable2)
      {
        throw localThrowable2;
      }
    }
    size = new int[paramInt];
    next = new Object[paramInt];
  }
  
  private static void add(int[] paramArrayOfInt, Object[] paramArrayOfObject, int paramInt)
  {
    if (paramArrayOfInt.length == 8) {
      try
      {
        if (q < 10)
        {
          paramArrayOfObject[0] = b;
          paramArrayOfObject[1] = paramArrayOfInt;
          paramInt -= 1;
          break label114;
          b = paramArrayOfObject;
          q += 1;
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
        if (p < 10)
        {
          paramArrayOfObject[0] = a;
          paramArrayOfObject[1] = paramArrayOfInt;
          paramInt -= 1;
          break label130;
          a = paramArrayOfObject;
          p += 1;
        }
        return;
      }
      catch (Throwable paramArrayOfInt)
      {
        throw paramArrayOfInt;
      }
      return;
      label114:
      while (paramInt >= 2)
      {
        paramArrayOfObject[paramInt] = null;
        paramInt -= 1;
      }
      break;
      label130:
      while (paramInt >= 2)
      {
        paramArrayOfObject[paramInt] = null;
        paramInt -= 1;
      }
    }
  }
  
  private int get()
  {
    int j = length;
    if (j == 0) {
      return -1;
    }
    int k = ContainerHelpers.get(size, j, 0);
    if (k < 0) {
      return k;
    }
    if (next[k] == null) {
      return k;
    }
    int i = k + 1;
    while ((i < j) && (size[i] == 0))
    {
      if (next[i] == null) {
        return i;
      }
      i += 1;
    }
    j = k - 1;
    while ((j >= 0) && (size[j] == 0))
    {
      if (next[j] == null) {
        return j;
      }
      j -= 1;
    }
    return i;
  }
  
  private int get(Object paramObject, int paramInt)
  {
    int j = length;
    if (j == 0) {
      return -1;
    }
    int k = ContainerHelpers.get(size, j, paramInt);
    if (k < 0) {
      return k;
    }
    if (paramObject.equals(next[k])) {
      return k;
    }
    int i = k + 1;
    while ((i < j) && (size[i] == paramInt))
    {
      if (paramObject.equals(next[i])) {
        return i;
      }
      i += 1;
    }
    j = k - 1;
    while ((j >= 0) && (size[j] == paramInt))
    {
      if (paramObject.equals(next[j])) {
        return j;
      }
      j -= 1;
    }
    return i;
  }
  
  public boolean add(Object paramObject)
  {
    int j;
    if (paramObject == null)
    {
      j = 0;
      i = get();
    }
    else
    {
      i = paramObject.hashCode();
      j = i;
      i = get(paramObject, i);
    }
    if (i >= 0) {
      return false;
    }
    int k = i;
    int m = length;
    Object localObject;
    if (m >= size.length)
    {
      i = 4;
      if (m >= 8) {
        i = (m >> 1) + m;
      } else if (m >= 4) {
        i = 8;
      }
      localObject = size;
      Object[] arrayOfObject = next;
      add(i);
      int[] arrayOfInt = size;
      if (arrayOfInt.length > 0)
      {
        System.arraycopy(localObject, 0, arrayOfInt, 0, localObject.length);
        System.arraycopy(arrayOfObject, 0, next, 0, arrayOfObject.length);
      }
      add((int[])localObject, arrayOfObject, length);
    }
    int i = length;
    if (k < i)
    {
      localObject = size;
      System.arraycopy(localObject, k, localObject, k + 1, i - k);
      localObject = next;
      System.arraycopy(localObject, k, localObject, k + 1, length - k);
    }
    size[k] = j;
    next[k] = paramObject;
    length += 1;
    return true;
  }
  
  public boolean addAll(Collection paramCollection)
  {
    toArray(length + paramCollection.size());
    boolean bool = false;
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      bool |= add(paramCollection.next());
    }
    return bool;
  }
  
  public void clear()
  {
    int i = length;
    if (i != 0)
    {
      add(size, next, i);
      size = pos;
      next = head;
      length = 0;
    }
  }
  
  public boolean contains(Object paramObject)
  {
    return get(paramObject) >= 0;
  }
  
  public boolean containsAll(Collection paramCollection)
  {
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      if (!contains(paramCollection.next())) {
        return false;
      }
    }
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof Set))
    {
      paramObject = (Set)paramObject;
      if (size() != paramObject.size()) {
        return false;
      }
      int i = 0;
      try
      {
        for (;;)
        {
          int j = length;
          if (i < j) {}
          try
          {
            boolean bool = paramObject.contains(get(i));
            if (!bool) {
              return false;
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
    return false;
  }
  
  public int get(Object paramObject)
  {
    if (paramObject == null) {
      return get();
    }
    return get(paramObject, paramObject.hashCode());
  }
  
  public Object get(int paramInt)
  {
    return next[paramInt];
  }
  
  public int hashCode()
  {
    int[] arrayOfInt = size;
    int j = 0;
    int i = 0;
    int k = length;
    while (i < k)
    {
      j += arrayOfInt[i];
      i += 1;
    }
    return j;
  }
  
  public boolean isEmpty()
  {
    return length <= 0;
  }
  
  public Iterator iterator()
  {
    return add().getKeySet().iterator();
  }
  
  public Object remove(int paramInt)
  {
    Object localObject2 = next;
    Object localObject1 = localObject2[paramInt];
    int j = length;
    if (j <= 1)
    {
      add(size, (Object[])localObject2, j);
      size = pos;
      next = head;
      length = 0;
      return localObject1;
    }
    localObject2 = size;
    int k = localObject2.length;
    int i = 8;
    if ((k > 8) && (j < localObject2.length / 3))
    {
      if (j > 8) {
        i = j + (j >> 1);
      }
      localObject2 = size;
      Object[] arrayOfObject = next;
      add(i);
      length -= 1;
      if (paramInt > 0)
      {
        System.arraycopy(localObject2, 0, size, 0, paramInt);
        System.arraycopy(arrayOfObject, 0, next, 0, paramInt);
      }
      i = length;
      if (paramInt < i)
      {
        System.arraycopy(localObject2, paramInt + 1, size, paramInt, i - paramInt);
        System.arraycopy(arrayOfObject, paramInt + 1, next, paramInt, length - paramInt);
      }
      return localObject1;
    }
    length -= 1;
    i = length;
    if (paramInt < i)
    {
      localObject2 = size;
      System.arraycopy(localObject2, paramInt + 1, localObject2, paramInt, i - paramInt);
      localObject2 = next;
      System.arraycopy(localObject2, paramInt + 1, localObject2, paramInt, length - paramInt);
    }
    next[length] = null;
    return localObject1;
  }
  
  public boolean remove(Object paramObject)
  {
    int i = get(paramObject);
    if (i >= 0)
    {
      remove(i);
      return true;
    }
    return false;
  }
  
  public boolean removeAll(Collection paramCollection)
  {
    boolean bool = false;
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      bool |= remove(paramCollection.next());
    }
    return bool;
  }
  
  public boolean retainAll(Collection paramCollection)
  {
    boolean bool = false;
    int i = length - 1;
    while (i >= 0)
    {
      if (!paramCollection.contains(next[i]))
      {
        remove(i);
        bool = true;
      }
      i -= 1;
    }
    return bool;
  }
  
  public int size()
  {
    return length;
  }
  
  public void toArray(int paramInt)
  {
    if (size.length < paramInt)
    {
      int[] arrayOfInt = size;
      Object[] arrayOfObject = next;
      add(paramInt);
      paramInt = length;
      if (paramInt > 0)
      {
        System.arraycopy(arrayOfInt, 0, size, 0, paramInt);
        System.arraycopy(arrayOfObject, 0, next, 0, length);
      }
      add(arrayOfInt, arrayOfObject, length);
    }
  }
  
  public Object[] toArray()
  {
    int i = length;
    Object[] arrayOfObject = new Object[i];
    System.arraycopy(next, 0, arrayOfObject, 0, i);
    return arrayOfObject;
  }
  
  public Object[] toArray(Object[] paramArrayOfObject)
  {
    Object[] arrayOfObject = paramArrayOfObject;
    if (paramArrayOfObject.length < length) {
      arrayOfObject = (Object[])Array.newInstance(paramArrayOfObject.getClass().getComponentType(), length);
    }
    System.arraycopy(next, 0, arrayOfObject, 0, length);
    int i = arrayOfObject.length;
    int j = length;
    if (i > j) {
      arrayOfObject[j] = null;
    }
    return arrayOfObject;
  }
  
  public String toString()
  {
    if (isEmpty()) {
      return "{}";
    }
    StringBuilder localStringBuilder = new StringBuilder(length * 14);
    localStringBuilder.append('{');
    int i = 0;
    while (i < length)
    {
      if (i > 0) {
        localStringBuilder.append(", ");
      }
      Object localObject = get(i);
      if (localObject != this) {
        localStringBuilder.append(localObject);
      } else {
        localStringBuilder.append("(this Set)");
      }
      i += 1;
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}
