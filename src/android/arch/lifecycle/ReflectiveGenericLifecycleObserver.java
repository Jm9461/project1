package android.arch.lifecycle;

class ReflectiveGenericLifecycleObserver
  implements GenericLifecycleObserver
{
  private final ByteVector d;
  private final Object o;
  
  ReflectiveGenericLifecycleObserver(Object paramObject)
  {
    o = paramObject;
    d = ClassWriter.b.b(o.getClass());
  }
  
  public void b(h paramH, c paramC)
  {
    d.a(paramH, paramC, o);
  }
}
