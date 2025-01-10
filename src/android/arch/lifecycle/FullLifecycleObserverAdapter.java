package android.arch.lifecycle;

class FullLifecycleObserverAdapter
  implements GenericLifecycleObserver
{
  private final FullLifecycleObserver o;
  
  FullLifecycleObserverAdapter(FullLifecycleObserver paramFullLifecycleObserver)
  {
    o = paramFullLifecycleObserver;
  }
  
  public void b(h paramH, c paramC)
  {
    switch (a.o[paramC.ordinal()])
    {
    default: 
      return;
    case 7: 
      throw new IllegalArgumentException("ON_ANY must not been send by anybody");
    case 6: 
      o.collapseItemActionView(paramH);
      return;
    case 5: 
      o.b(paramH);
      return;
    case 4: 
      o.d(paramH);
      return;
    case 3: 
      o.f(paramH);
      return;
    case 2: 
      o.a(paramH);
      return;
    }
    o.c(paramH);
  }
}
