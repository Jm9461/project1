package org.org.jaxen.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;

public class LruCache<K, V>
{
  private int createCount;
  private int evictionCount;
  private int hitCount;
  private final LinkedHashMap<K, V> map;
  private int maxSize;
  private int missCount;
  private int putCount;
  private int size;
  
  public LruCache(int paramInt)
  {
    if (paramInt > 0)
    {
      maxSize = paramInt;
      map = new LinkedHashMap(0, 0.75F, true);
      return;
    }
    throw new IllegalArgumentException("maxSize <= 0");
  }
  
  private int safeSizeOf(Object paramObject1, Object paramObject2)
  {
    int i = sizeOf(paramObject1, paramObject2);
    if (i >= 0) {
      return i;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Negative size: ");
    localStringBuilder.append(paramObject1);
    localStringBuilder.append("=");
    localStringBuilder.append(paramObject2);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  protected Object create(Object paramObject)
  {
    return null;
  }
  
  protected void entryRemoved(boolean paramBoolean, Object paramObject1, Object paramObject2, Object paramObject3) {}
  
  public final Object get(Object paramObject)
  {
    if (paramObject != null)
    {
      try
      {
        localObject1 = map.get(paramObject);
        if (localObject1 == null) {}
      }
      catch (Throwable paramObject) {}
      try
      {
        hitCount += 1;
        return localObject1;
      }
      catch (Throwable paramObject)
      {
        for (;;) {}
      }
      missCount += 1;
      Object localObject1 = create(paramObject);
      if (localObject1 == null) {
        return null;
      }
      try
      {
        createCount += 1;
        Object localObject2 = map.put(paramObject, localObject1);
        if (localObject2 != null) {
          map.put(paramObject, localObject2);
        } else {
          size += safeSizeOf(paramObject, localObject1);
        }
        if (localObject2 != null)
        {
          entryRemoved(false, paramObject, localObject1, localObject2);
          return localObject2;
        }
        trimToSize(maxSize);
        return localObject1;
      }
      catch (Throwable paramObject)
      {
        throw paramObject;
      }
      throw paramObject;
    }
    paramObject = new NullPointerException("key == null");
    throw paramObject;
  }
  
  public final Object put(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 != null) && (paramObject2 != null))
    {
      try
      {
        putCount += 1;
        size += safeSizeOf(paramObject1, paramObject2);
        localObject = map.put(paramObject1, paramObject2);
        if (localObject == null) {}
      }
      catch (Throwable paramObject1)
      {
        try
        {
          Object localObject;
          size -= safeSizeOf(paramObject1, localObject);
          if (localObject != null) {
            entryRemoved(false, paramObject1, localObject, paramObject2);
          }
          trimToSize(maxSize);
          return localObject;
        }
        catch (Throwable paramObject1)
        {
          for (;;) {}
        }
        paramObject1 = paramObject1;
      }
      throw paramObject1;
    }
    paramObject1 = new NullPointerException("key == null || value == null");
    throw paramObject1;
  }
  
  protected int sizeOf(Object paramObject1, Object paramObject2)
  {
    return 1;
  }
  
  public final String toString()
  {
    for (;;)
    {
      try
      {
        i = hitCount + missCount;
        if (i != 0)
        {
          i = hitCount * 100 / i;
          String str = String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[] { Integer.valueOf(maxSize), Integer.valueOf(hitCount), Integer.valueOf(missCount), Integer.valueOf(i) });
          return str;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      int i = 0;
    }
  }
  
  public void trimToSize(int paramInt)
  {
    localObject2 = this;
    for (;;)
    {
      localObject1 = localObject2;
      try
      {
        int i = size;
        Object localObject3 = localObject2;
        if (i >= 0)
        {
          localObject1 = localObject3;
          if (map.isEmpty())
          {
            localObject1 = localObject3;
            if (size != 0) {}
          }
          else
          {
            localObject1 = localObject2;
            i = size;
            if (i > paramInt)
            {
              localObject1 = localObject2;
              if (!map.isEmpty())
              {
                localObject1 = localObject2;
                localObject3 = (Map.Entry)map.entrySet().iterator().next();
                localObject1 = localObject2;
                Object localObject4 = ((Map.Entry)localObject3).getKey();
                try
                {
                  localObject5 = ((Map.Entry)localObject3).getValue();
                  localObject1 = localObject2;
                }
                catch (Throwable localThrowable1)
                {
                  try
                  {
                    Object localObject5;
                    map.remove(localObject4);
                    localObject1 = localObject2;
                    i = size;
                    localObject3 = localObject2;
                    localObject1 = localObject3;
                    size = (i - ((LruCache)localObject2).safeSizeOf(localObject4, localObject5));
                    localObject1 = localObject3;
                    i = evictionCount;
                    localObject2 = localObject3;
                    localObject1 = localObject2;
                    evictionCount = (i + 1);
                    localObject1 = localObject2;
                    ((LruCache)localObject2).entryRemoved(true, localObject4, localObject5, null);
                  }
                  catch (Throwable localThrowable3)
                  {
                    for (;;)
                    {
                      StringBuilder localStringBuilder;
                      localObject2 = localObject1;
                    }
                  }
                  localThrowable1 = localThrowable1;
                }
              }
            }
            localObject1 = localObject2;
            return;
          }
        }
        localObject1 = localObject2;
        localStringBuilder = new StringBuilder();
        localObject1 = localObject2;
        localStringBuilder.append(localObject2.getClass().getName());
        localObject1 = localObject2;
        localStringBuilder.append(".sizeOf() is reporting inconsistent results!");
        localObject1 = localObject2;
        throw new IllegalStateException(localStringBuilder.toString());
      }
      catch (Throwable localThrowable2)
      {
        localObject2 = localObject1;
      }
    }
    localObject1 = localObject2;
    throw localThrowable2;
  }
}
