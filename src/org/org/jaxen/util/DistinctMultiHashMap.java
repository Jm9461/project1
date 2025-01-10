package org.org.jaxen.util;

import a.b.g.g.h;
import java.util.Map;

class DistinctMultiHashMap
  extends h<E, E>
{
  DistinctMultiHashMap(TFloatLinkedList paramTFloatLinkedList) {}
  
  protected int add(Object paramObject)
  {
    return this$0.get(paramObject);
  }
  
  protected Object add(int paramInt, Object paramObject)
  {
    throw new UnsupportedOperationException("not a map");
  }
  
  protected void add()
  {
    this$0.clear();
  }
  
  protected void add(int paramInt)
  {
    this$0.remove(paramInt);
  }
  
  protected void add(Object paramObject1, Object paramObject2)
  {
    this$0.add(paramObject1);
  }
  
  protected int get(Object paramObject)
  {
    return this$0.get(paramObject);
  }
  
  protected Object get(int paramInt1, int paramInt2)
  {
    return this$0.next[paramInt1];
  }
  
  protected Map get()
  {
    throw new UnsupportedOperationException("not a map");
  }
  
  protected int size()
  {
    return this$0.length;
  }
}
