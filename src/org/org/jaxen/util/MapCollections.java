package org.org.jaxen.util;

import a.b.g.g.h;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

abstract class MapCollections<K, V>
{
  h<K, V>.b mEntrySet;
  h<K, V>.c mKeySet;
  h<K, V>.e mValues;
  
  MapCollections() {}
  
  public static boolean containsAllHelper(Map paramMap, Collection paramCollection)
  {
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      if (!paramMap.containsKey(paramCollection.next())) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean equalsSetHelper(Set paramSet, Object paramObject)
  {
    if (paramSet == paramObject) {
      return true;
    }
    if ((paramObject instanceof Set))
    {
      paramObject = (Set)paramObject;
      try
      {
        int i = paramSet.size();
        int j = paramObject.size();
        if (i == j)
        {
          boolean bool = paramSet.containsAll(paramObject);
          if (bool) {
            return true;
          }
        }
        return false;
      }
      catch (ClassCastException paramSet)
      {
        return false;
      }
      catch (NullPointerException paramSet) {}
    }
    return false;
  }
  
  public static boolean removeAllHelper(Map paramMap, Collection paramCollection)
  {
    int i = paramMap.size();
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      paramMap.remove(paramCollection.next());
    }
    return i != paramMap.size();
  }
  
  public static boolean retainAllHelper(Map paramMap, Collection paramCollection)
  {
    int i = paramMap.size();
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext()) {
      if (!paramCollection.contains(localIterator.next())) {
        localIterator.remove();
      }
    }
    return i != paramMap.size();
  }
  
  protected abstract int add(Object paramObject);
  
  protected abstract Object add(int paramInt, Object paramObject);
  
  protected abstract void add();
  
  protected abstract void add(int paramInt);
  
  protected abstract void add(Object paramObject1, Object paramObject2);
  
  protected abstract int get(Object paramObject);
  
  protected abstract Object get(int paramInt1, int paramInt2);
  
  protected abstract Map get();
  
  public Set getEntrySet()
  {
    if (mEntrySet == null) {
      mEntrySet = new EntrySet();
    }
    return mEntrySet;
  }
  
  public Set getKeySet()
  {
    if (mKeySet == null) {
      mKeySet = new KeySet();
    }
    return mKeySet;
  }
  
  public Object[] getValue(int paramInt)
  {
    int j = size();
    Object[] arrayOfObject = new Object[j];
    int i = 0;
    while (i < j)
    {
      arrayOfObject[i] = get(i, paramInt);
      i += 1;
    }
    return arrayOfObject;
  }
  
  public Collection getValues()
  {
    if (mValues == null) {
      mValues = new ValuesCollection();
    }
    return mValues;
  }
  
  protected abstract int size();
  
  public Object[] toArray(Object[] paramArrayOfObject, int paramInt)
  {
    int j = size();
    Object[] arrayOfObject = paramArrayOfObject;
    if (paramArrayOfObject.length < j) {
      arrayOfObject = (Object[])Array.newInstance(paramArrayOfObject.getClass().getComponentType(), j);
    }
    int i = 0;
    while (i < j)
    {
      arrayOfObject[i] = get(i, paramInt);
      i += 1;
    }
    if (arrayOfObject.length > j) {
      arrayOfObject[j] = null;
    }
    return arrayOfObject;
  }
  
  final class ArrayIterator<T>
    implements Iterator<T>
  {
    boolean mCanRemove = false;
    int mIndex;
    final int mOffset;
    int mSize;
    
    ArrayIterator(int paramInt)
    {
      mOffset = paramInt;
      mSize = size();
    }
    
    public boolean hasNext()
    {
      return mIndex < mSize;
    }
    
    public Object next()
    {
      if (hasNext())
      {
        Object localObject = get(mIndex, mOffset);
        mIndex += 1;
        mCanRemove = true;
        return localObject;
      }
      throw new NoSuchElementException();
    }
    
    public void remove()
    {
      if (mCanRemove)
      {
        mIndex -= 1;
        mSize -= 1;
        mCanRemove = false;
        add(mIndex);
        return;
      }
      throw new IllegalStateException();
    }
  }
  
  final class EntrySet
    implements Set<Map.Entry<K, V>>
  {
    EntrySet() {}
    
    public boolean add(Map.Entry paramEntry)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(Collection paramCollection)
    {
      int i = MapCollections.this.size();
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramCollection.next();
        add(localEntry.getKey(), localEntry.getValue());
      }
      return i != MapCollections.this.size();
    }
    
    public void clear()
    {
      add();
    }
    
    public boolean contains(Object paramObject)
    {
      if (!(paramObject instanceof Map.Entry)) {
        return false;
      }
      paramObject = (Map.Entry)paramObject;
      int i = add(paramObject.getKey());
      if (i < 0) {
        return false;
      }
      return ContainerHelpers.equal(get(i, 1), paramObject.getValue());
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
      return MapCollections.equalsSetHelper(this, paramObject);
    }
    
    public int hashCode()
    {
      int j = 0;
      int i = MapCollections.this.size() - 1;
      while (i >= 0)
      {
        Object localObject1 = MapCollections.this;
        int m = 0;
        localObject1 = ((MapCollections)localObject1).get(i, 0);
        Object localObject2 = get(i, 1);
        int k;
        if (localObject1 == null) {
          k = 0;
        } else {
          k = localObject1.hashCode();
        }
        if (localObject2 != null) {
          m = localObject2.hashCode();
        }
        j += (m ^ k);
        i -= 1;
      }
      return j;
    }
    
    public boolean isEmpty()
    {
      return MapCollections.this.size() == 0;
    }
    
    public Iterator iterator()
    {
      return new MapCollections.MapIterator(MapCollections.this);
    }
    
    public boolean remove(Object paramObject)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection paramCollection)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(Collection paramCollection)
    {
      throw new UnsupportedOperationException();
    }
    
    public int size()
    {
      return MapCollections.this.size();
    }
    
    public Object[] toArray()
    {
      throw new UnsupportedOperationException();
    }
    
    public Object[] toArray(Object[] paramArrayOfObject)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  final class KeySet
    implements Set<K>
  {
    KeySet() {}
    
    public boolean add(Object paramObject)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(Collection paramCollection)
    {
      throw new UnsupportedOperationException();
    }
    
    public void clear()
    {
      add();
    }
    
    public boolean contains(Object paramObject)
    {
      return add(paramObject) >= 0;
    }
    
    public boolean containsAll(Collection paramCollection)
    {
      return MapCollections.containsAllHelper(get(), paramCollection);
    }
    
    public boolean equals(Object paramObject)
    {
      return MapCollections.equalsSetHelper(this, paramObject);
    }
    
    public int hashCode()
    {
      int j = 0;
      int i = MapCollections.this.size() - 1;
      while (i >= 0)
      {
        Object localObject = MapCollections.this;
        int k = 0;
        localObject = ((MapCollections)localObject).get(i, 0);
        if (localObject != null) {
          k = localObject.hashCode();
        }
        j += k;
        i -= 1;
      }
      return j;
    }
    
    public boolean isEmpty()
    {
      return MapCollections.this.size() == 0;
    }
    
    public Iterator iterator()
    {
      return new MapCollections.ArrayIterator(MapCollections.this, 0);
    }
    
    public boolean remove(Object paramObject)
    {
      int i = add(paramObject);
      if (i >= 0)
      {
        add(i);
        return true;
      }
      return false;
    }
    
    public boolean removeAll(Collection paramCollection)
    {
      return MapCollections.removeAllHelper(get(), paramCollection);
    }
    
    public boolean retainAll(Collection paramCollection)
    {
      return MapCollections.retainAllHelper(get(), paramCollection);
    }
    
    public int size()
    {
      return MapCollections.this.size();
    }
    
    public Object[] toArray()
    {
      return getValue(0);
    }
    
    public Object[] toArray(Object[] paramArrayOfObject)
    {
      return toArray(paramArrayOfObject, 0);
    }
  }
  
  final class MapIterator
    implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V>
  {
    int mEnd = size() - 1;
    boolean mEntryValid = false;
    int mIndex = -1;
    
    MapIterator() {}
    
    public boolean equals(Object paramObject)
    {
      if (mEntryValid)
      {
        if (!(paramObject instanceof Map.Entry)) {
          return false;
        }
        paramObject = (Map.Entry)paramObject;
        return (ContainerHelpers.equal(paramObject.getKey(), get(mIndex, 0))) && (ContainerHelpers.equal(paramObject.getValue(), get(mIndex, 1)));
      }
      throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }
    
    public Object getKey()
    {
      if (mEntryValid) {
        return get(mIndex, 0);
      }
      throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }
    
    public Object getValue()
    {
      if (mEntryValid) {
        return get(mIndex, 1);
      }
      throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }
    
    public boolean hasNext()
    {
      return mIndex < mEnd;
    }
    
    public int hashCode()
    {
      if (mEntryValid)
      {
        Object localObject1 = MapCollections.this;
        int i = mIndex;
        int j = 0;
        localObject1 = ((MapCollections)localObject1).get(i, 0);
        Object localObject2 = get(mIndex, 1);
        if (localObject1 == null) {
          i = 0;
        } else {
          i = localObject1.hashCode();
        }
        if (localObject2 != null) {
          j = localObject2.hashCode();
        }
        return j ^ i;
      }
      throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }
    
    public Map.Entry next()
    {
      if (hasNext())
      {
        mIndex += 1;
        mEntryValid = true;
        return this;
      }
      throw new NoSuchElementException();
    }
    
    public void remove()
    {
      if (mEntryValid)
      {
        add(mIndex);
        mIndex -= 1;
        mEnd -= 1;
        mEntryValid = false;
        return;
      }
      throw new IllegalStateException();
    }
    
    public Object setValue(Object paramObject)
    {
      if (mEntryValid) {
        return add(mIndex, paramObject);
      }
      throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(getKey());
      localStringBuilder.append("=");
      localStringBuilder.append(getValue());
      return localStringBuilder.toString();
    }
  }
  
  final class ValuesCollection
    implements Collection<V>
  {
    ValuesCollection() {}
    
    public boolean add(Object paramObject)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(Collection paramCollection)
    {
      throw new UnsupportedOperationException();
    }
    
    public void clear()
    {
      add();
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
    
    public boolean isEmpty()
    {
      return MapCollections.this.size() == 0;
    }
    
    public Iterator iterator()
    {
      return new MapCollections.ArrayIterator(MapCollections.this, 1);
    }
    
    public boolean remove(Object paramObject)
    {
      int i = get(paramObject);
      if (i >= 0)
      {
        add(i);
        return true;
      }
      return false;
    }
    
    public boolean removeAll(Collection paramCollection)
    {
      int j = MapCollections.this.size();
      boolean bool = false;
      int i = 0;
      while (i < j)
      {
        int k = j;
        int m = i;
        if (paramCollection.contains(get(i, 1)))
        {
          add(i);
          m = i - 1;
          k = j - 1;
          bool = true;
        }
        i = m + 1;
        j = k;
      }
      return bool;
    }
    
    public boolean retainAll(Collection paramCollection)
    {
      int j = MapCollections.this.size();
      boolean bool = false;
      int i = 0;
      while (i < j)
      {
        int k = j;
        int m = i;
        if (!paramCollection.contains(get(i, 1)))
        {
          add(i);
          m = i - 1;
          k = j - 1;
          bool = true;
        }
        i = m + 1;
        j = k;
      }
      return bool;
    }
    
    public int size()
    {
      return MapCollections.this.size();
    }
    
    public Object[] toArray()
    {
      return getValue(1);
    }
    
    public Object[] toArray(Object[] paramArrayOfObject)
    {
      return toArray(paramArrayOfObject, 1);
    }
  }
}
