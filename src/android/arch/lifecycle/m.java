package android.arch.lifecycle;

import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import org.mozilla.osmdroid.asm.DynamicTableModel.a;

public class m
  extends d
{
  private a.a.a.b.a<d, f.b> a = new org.mozilla.osmdroid.asm.a();
  private boolean c = false;
  private int f = 0;
  private ArrayList<c.b> m = new ArrayList();
  private f p;
  private final WeakReference<e> x;
  private boolean y = false;
  
  public m(h paramH)
  {
    x = new WeakReference(paramH);
    p = f.d;
  }
  
  private static c a(f paramF)
  {
    int i = XYPlot.c[paramF.ordinal()];
    if (i != 1) {
      if (i != 2)
      {
        if (i != 3)
        {
          if (i != 4)
          {
            if (i != 5)
            {
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("Unexpected state value ");
              localStringBuilder.append(paramF);
              throw new IllegalArgumentException(localStringBuilder.toString());
            }
          }
          else {
            throw new IllegalArgumentException();
          }
        }
        else {
          return c.ON_RESUME;
        }
      }
      else {
        return c.ON_START;
      }
    }
    return c.ON_CREATE;
  }
  
  static f a(c paramC)
  {
    switch (XYPlot.o[paramC.ordinal()])
    {
    default: 
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unexpected event value ");
      localStringBuilder.append(paramC);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 6: 
      return f.b;
    case 5: 
      return f.g;
    case 3: 
    case 4: 
      return f.c;
    }
    return f.a;
  }
  
  static f a(f paramF1, f paramF2)
  {
    if ((paramF2 != null) && (paramF2.compareTo(paramF1) < 0)) {
      return paramF2;
    }
    return paramF1;
  }
  
  private void a(h paramH)
  {
    DynamicTableModel.a localA = a.f();
    while ((localA.hasNext()) && (!y))
    {
      Map.Entry localEntry = (Map.Entry)localA.next();
      e localE = (e)localEntry.getValue();
      while ((a.compareTo(p) < 0) && (!y) && (a.contains(localEntry.getKey())))
      {
        add(a);
        localE.a(paramH, a(a));
        c();
      }
    }
  }
  
  private boolean a()
  {
    if (a.size() == 0) {
      return true;
    }
    f localF1 = a.getTitle().getValue()).a;
    f localF2 = a.get().getValue()).a;
    return (localF1 == localF2) && (p == localF2);
  }
  
  private void add(f paramF)
  {
    m.add(paramF);
  }
  
  private f b(MethodVisitor paramMethodVisitor)
  {
    paramMethodVisitor = a.getValue(paramMethodVisitor);
    Object localObject = null;
    if (paramMethodVisitor != null) {
      paramMethodVisitor = getValuea;
    } else {
      paramMethodVisitor = null;
    }
    if (!m.isEmpty())
    {
      localObject = m;
      localObject = (f)((ArrayList)localObject).get(((ArrayList)localObject).size() - 1);
    }
    return a(a(p, paramMethodVisitor), (f)localObject);
  }
  
  private void b(f paramF)
  {
    if (p == paramF) {
      return;
    }
    p = paramF;
    if ((!c) && (f == 0))
    {
      c = true;
      initialize();
      c = false;
      return;
    }
    y = true;
  }
  
  private void c()
  {
    ArrayList localArrayList = m;
    localArrayList.remove(localArrayList.size() - 1);
  }
  
  private void initialize()
  {
    h localH = (h)x.get();
    if (localH == null)
    {
      Log.w("LifecycleRegistry", "LifecycleOwner is garbage collected, you shouldn't try dispatch new events from it.");
      return;
    }
    while (!a())
    {
      y = false;
      if (p.compareTo(a.getTitle().getValue()).a) < 0) {
        initialize(localH);
      }
      Map.Entry localEntry = a.get();
      if ((!y) && (localEntry != null) && (p.compareTo(getValuea) > 0)) {
        a(localH);
      }
    }
    y = false;
  }
  
  private void initialize(h paramH)
  {
    Iterator localIterator = a.a();
    while ((localIterator.hasNext()) && (!y))
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      e localE = (e)localEntry.getValue();
      while ((a.compareTo(p) > 0) && (!y) && (a.contains(localEntry.getKey())))
      {
        c localC = valueOf(a);
        add(a(localC));
        localE.a(paramH, localC);
        c();
      }
    }
  }
  
  private static c valueOf(f paramF)
  {
    int i = XYPlot.c[paramF.ordinal()];
    if (i != 1)
    {
      if (i != 2)
      {
        if (i != 3)
        {
          if (i != 4)
          {
            if (i != 5)
            {
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("Unexpected state value ");
              localStringBuilder.append(paramF);
              throw new IllegalArgumentException(localStringBuilder.toString());
            }
            throw new IllegalArgumentException();
          }
          return c.ON_PAUSE;
        }
        return c.ON_STOP;
      }
      return c.ON_DESTROY;
    }
    throw new IllegalArgumentException();
  }
  
  public void a(MethodVisitor paramMethodVisitor)
  {
    Object localObject = p;
    f localF = f.b;
    if (localObject != localF) {
      localF = f.d;
    }
    localObject = new e(paramMethodVisitor, localF);
    if ((e)a.b(paramMethodVisitor, localObject) != null) {
      return;
    }
    h localH = (h)x.get();
    if (localH == null) {
      return;
    }
    int i;
    if ((f == 0) && (!c)) {
      i = 0;
    } else {
      i = 1;
    }
    localF = b(paramMethodVisitor);
    f += 1;
    while ((a.compareTo(localF) < 0) && (a.contains(paramMethodVisitor)))
    {
      add(a);
      ((e)localObject).a(localH, a(a));
      c();
      localF = b(paramMethodVisitor);
    }
    if (i == 0) {
      initialize();
    }
    f -= 1;
  }
  
  public f b()
  {
    return p;
  }
  
  public void c(MethodVisitor paramMethodVisitor)
  {
    a.remove(paramMethodVisitor);
  }
  
  public void d(f paramF)
  {
    b(paramF);
  }
  
  public void toString(c paramC)
  {
    b(a(paramC));
  }
}
