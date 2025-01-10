package org.mozilla.osmdroid.asm;

import a.a.a.b.b.d;
import a.a.a.b.b.g;
import java.util.Iterator;
import java.util.Map.Entry;

abstract class RRuleIteratorImpl<K, V>
  implements Iterator<Map.Entry<K, V>>, b.g<K, V>
{
  b.d<K, V> done_;
  b.d<K, V> pendingUtc_;
  
  RRuleIteratorImpl(Attribute paramAttribute1, Attribute paramAttribute2)
  {
    done_ = paramAttribute2;
    pendingUtc_ = paramAttribute1;
  }
  
  private Attribute fetchNext()
  {
    Attribute localAttribute1 = pendingUtc_;
    Attribute localAttribute2 = done_;
    if ((localAttribute1 != localAttribute2) && (localAttribute2 != null)) {
      return a(localAttribute1);
    }
    return null;
  }
  
  abstract Attribute a(Attribute paramAttribute);
  
  abstract Attribute b(Attribute paramAttribute);
  
  public boolean hasNext()
  {
    return pendingUtc_ != null;
  }
  
  public Map.Entry next()
  {
    Attribute localAttribute = pendingUtc_;
    pendingUtc_ = fetchNext();
    return localAttribute;
  }
  
  public void next(Attribute paramAttribute)
  {
    if ((done_ == paramAttribute) && (paramAttribute == pendingUtc_))
    {
      pendingUtc_ = null;
      done_ = null;
    }
    Attribute localAttribute = done_;
    if (localAttribute == paramAttribute) {
      done_ = b(localAttribute);
    }
    if (pendingUtc_ == paramAttribute) {
      pendingUtc_ = fetchNext();
    }
  }
}
