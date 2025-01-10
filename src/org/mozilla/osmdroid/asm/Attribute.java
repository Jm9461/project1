package org.mozilla.osmdroid.asm;

import a.a.a.b.b.d;
import java.util.Map.Entry;

class Attribute<K, V>
  implements Map.Entry<K, V>
{
  b.d<K, V> a;
  b.d<K, V> b;
  final K type;
  final V value;
  
  Attribute(Object paramObject1, Object paramObject2)
  {
    type = paramObject1;
    value = paramObject2;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof Attribute)) {
      return false;
    }
    paramObject = (Attribute)paramObject;
    return (type.equals(type)) && (value.equals(value));
  }
  
  public Object getKey()
  {
    return type;
  }
  
  public Object getValue()
  {
    return value;
  }
  
  public Object setValue(Object paramObject)
  {
    throw new UnsupportedOperationException("An entry modification is not supported");
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(type);
    localStringBuilder.append("=");
    localStringBuilder.append(value);
    return localStringBuilder.toString();
  }
}
