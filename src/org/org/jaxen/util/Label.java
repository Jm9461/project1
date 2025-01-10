package org.org.jaxen.util;

import a.b.g.g.h;
import a.b.g.g.n;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Label<K, V>
  extends n<K, V>
  implements Map<K, V>
{
  h<K, V> mCollections;
  
  public Label() {}
  
  public Label(int paramInt)
  {
    super(paramInt);
  }
  
  public Label(f paramF)
  {
    super(paramF);
  }
  
  private MapCollections getCollection()
  {
    if (mCollections == null) {
      mCollections = new UnboundedFifoByteBuffer(this);
    }
    return mCollections;
  }
  
  public Set entrySet()
  {
    return getCollection().getEntrySet();
  }
  
  public boolean equals(Collection paramCollection)
  {
    return MapCollections.retainAllHelper(this, paramCollection);
  }
  
  public Set keySet()
  {
    return getCollection().getKeySet();
  }
  
  public void putAll(Map paramMap)
  {
    add(n + paramMap.size());
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      put(localEntry.getKey(), localEntry.getValue());
    }
  }
  
  public Collection values()
  {
    return getCollection().getValues();
  }
}
