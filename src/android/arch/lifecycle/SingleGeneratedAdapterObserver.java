package android.arch.lifecycle;

public class SingleGeneratedAdapterObserver
  implements GenericLifecycleObserver
{
  private final Label k;
  
  SingleGeneratedAdapterObserver(Label paramLabel)
  {
    k = paramLabel;
  }
  
  public void b(h paramH, c paramC)
  {
    k.a(paramH, paramC, false, null);
    k.a(paramH, paramC, true, null);
  }
}
