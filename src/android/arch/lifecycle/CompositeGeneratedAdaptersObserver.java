package android.arch.lifecycle;

public class CompositeGeneratedAdaptersObserver
  implements GenericLifecycleObserver
{
  private final Label[] d;
  
  CompositeGeneratedAdaptersObserver(Label[] paramArrayOfLabel)
  {
    d = paramArrayOfLabel;
  }
  
  public void b(h paramH, c paramC)
  {
    AnnotationVisitor localAnnotationVisitor = new AnnotationVisitor();
    Label[] arrayOfLabel = d;
    int k = arrayOfLabel.length;
    int j = 0;
    int i = 0;
    while (i < k)
    {
      arrayOfLabel[i].a(paramH, paramC, false, localAnnotationVisitor);
      i += 1;
    }
    arrayOfLabel = d;
    k = arrayOfLabel.length;
    i = j;
    while (i < k)
    {
      arrayOfLabel[i].a(paramH, paramC, true, localAnnotationVisitor);
      i += 1;
    }
  }
}
