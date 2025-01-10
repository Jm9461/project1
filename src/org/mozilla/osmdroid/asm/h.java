package org.mozilla.osmdroid.asm;

import a.a.a.b.b.d;
import a.a.a.b.b.g;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;

public class h<K, V>
  implements Iterable<Map.Entry<K, V>>
{
  private b.d<K, V> a;
  private b.d<K, V> c;
  private WeakHashMap<b.g<K, V>, Boolean> m = new WeakHashMap();
  private int n = 0;
  
  public h() {}
  
  public Iterator a()
  {
    ClassReader localClassReader = new ClassReader(c, a);
    m.put(localClassReader, Boolean.valueOf(false));
    return localClassReader;
  }
  
  protected Attribute a(Object paramObject)
  {
    for (Attribute localAttribute = a; localAttribute != null; localAttribute = a) {
      if (type.equals(paramObject)) {
        return localAttribute;
      }
    }
    return localAttribute;
  }
  
  protected Attribute a(Object paramObject1, Object paramObject2)
  {
    paramObject1 = new Attribute(paramObject1, paramObject2);
    n += 1;
    paramObject2 = c;
    if (paramObject2 == null)
    {
      a = paramObject1;
      c = a;
      return paramObject1;
    }
    a = paramObject1;
    b = paramObject2;
    c = paramObject1;
    return paramObject1;
  }
  
  public Object b(Object paramObject1, Object paramObject2)
  {
    Attribute localAttribute = a(paramObject1);
    if (localAttribute != null) {
      return value;
    }
    a(paramObject1, paramObject2);
    return null;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof h)) {
      return false;
    }
    Object localObject1 = (h)paramObject;
    if (size() != ((h)localObject1).size()) {
      return false;
    }
    paramObject = iterator();
    localObject1 = ((h)localObject1).iterator();
    while ((paramObject.hasNext()) && (((Iterator)localObject1).hasNext()))
    {
      Map.Entry localEntry = (Map.Entry)paramObject.next();
      Object localObject2 = ((Iterator)localObject1).next();
      if ((localEntry == null) && (localObject2 != null)) {
        break label128;
      }
      if ((localEntry != null) && (!localEntry.equals(localObject2))) {
        return false;
      }
    }
    return (!paramObject.hasNext()) && (!((Iterator)localObject1).hasNext());
    label128:
    return false;
  }
  
  public DynamicTableModel.a f()
  {
    DynamicTableModel.a localA = new DynamicTableModel.a(this, null);
    m.put(localA, Boolean.valueOf(false));
    return localA;
  }
  
  public Map.Entry get()
  {
    return c;
  }
  
  public Map.Entry getTitle()
  {
    return a;
  }
  
  public Iterator iterator()
  {
    ByteVector localByteVector = new ByteVector(a, c);
    m.put(localByteVector, Boolean.valueOf(false));
    return localByteVector;
  }
  
  public Object remove(Object paramObject)
  {
    paramObject = a(paramObject);
    if (paramObject == null) {
      return null;
    }
    n -= 1;
    if (!m.isEmpty())
    {
      localObject = m.keySet().iterator();
      while (((Iterator)localObject).hasNext()) {
        ((LocalCache.ReferenceEntry)((Iterator)localObject).next()).next(paramObject);
      }
    }
    Object localObject = b;
    if (localObject != null) {
      a = a;
    } else {
      a = a;
    }
    localObject = a;
    if (localObject != null) {
      b = b;
    } else {
      c = b;
    }
    a = null;
    b = null;
    return value;
  }
  
  public int size()
  {
    return n;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      localStringBuilder.append(((Map.Entry)localIterator.next()).toString());
      if (localIterator.hasNext()) {
        localStringBuilder.append(", ");
      }
    }
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}
