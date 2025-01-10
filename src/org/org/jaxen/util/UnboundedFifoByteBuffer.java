package org.org.jaxen.util;

import a.b.g.g.h;
import java.util.Map;

class UnboundedFifoByteBuffer
  extends h<K, V>
{
  UnboundedFifoByteBuffer(Label paramLabel) {}
  
  protected int add(Object paramObject)
  {
    return a.add(paramObject);
  }
  
  protected Object add(int paramInt, Object paramObject)
  {
    return a.a(paramInt, paramObject);
  }
  
  protected void add()
  {
    a.clear();
  }
  
  protected void add(int paramInt)
  {
    a.d(paramInt);
  }
  
  protected void add(Object paramObject1, Object paramObject2)
  {
    a.put(paramObject1, paramObject2);
  }
  
  protected int get(Object paramObject)
  {
    return a.read(paramObject);
  }
  
  protected Object get(int paramInt1, int paramInt2)
  {
    return a.a[((paramInt1 << 1) + paramInt2)];
  }
  
  protected Map get()
  {
    return a;
  }
  
  protected int size()
  {
    return a.n;
  }
}
