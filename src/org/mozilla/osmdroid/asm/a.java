package org.mozilla.osmdroid.asm;

import a.a.a.b.b;
import a.a.a.b.b.d;
import java.util.HashMap;
import java.util.Map.Entry;

public class a<K, V>
  extends b<K, V>
{
  private HashMap<K, b.d<K, V>> b = new HashMap();
  
  public a() {}
  
  protected Attribute a(Object paramObject)
  {
    return (Attribute)b.get(paramObject);
  }
  
  public Object b(Object paramObject1, Object paramObject2)
  {
    Attribute localAttribute = a(paramObject1);
    if (localAttribute != null) {
      return value;
    }
    b.put(paramObject1, a(paramObject1, paramObject2));
    return null;
  }
  
  public boolean contains(Object paramObject)
  {
    return b.containsKey(paramObject);
  }
  
  public Map.Entry getValue(Object paramObject)
  {
    if (contains(paramObject)) {
      return b.get(paramObject)).b;
    }
    return null;
  }
  
  public Object remove(Object paramObject)
  {
    Object localObject = super.remove(paramObject);
    b.remove(paramObject);
    return localObject;
  }
}
