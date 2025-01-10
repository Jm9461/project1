package android.support.constraint.asm.asm;

import android.support.constraint.i.j.e;
import java.util.ArrayList;

public class b
  extends d
{
  protected ArrayList<e> c = new ArrayList();
  
  public b() {}
  
  public void a()
  {
    c.clear();
    super.a();
  }
  
  public void a(d paramD)
  {
    c.add(paramD);
    if (paramD.get() != null) {
      ((b)paramD.get()).b(paramD);
    }
    paramD.e(this);
  }
  
  public void a(android.support.constraint.asm.b paramB)
  {
    super.a(paramB);
    int j = c.size();
    int i = 0;
    while (i < j)
    {
      ((d)c.get(i)).a(paramB);
      i += 1;
    }
  }
  
  public void b(d paramD)
  {
    c.remove(paramD);
    paramD.e(null);
  }
  
  public f close()
  {
    d localD2 = get();
    f localF = null;
    d localD1 = localD2;
    if ((this instanceof f))
    {
      localF = (f)this;
      localD1 = localD2;
    }
    for (;;)
    {
      d localD3 = localD1;
      if (localD3 == null) {
        break;
      }
      localD2 = localD3.get();
      localD1 = localD2;
      if ((localD3 instanceof f))
      {
        localF = (f)localD3;
        localD1 = localD2;
      }
    }
    return localF;
  }
  
  public void d(int paramInt1, int paramInt2)
  {
    super.d(paramInt1, paramInt2);
    paramInt2 = c.size();
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      ((d)c.get(paramInt1)).d(getItem(), split());
      paramInt1 += 1;
    }
  }
  
  public void next()
  {
    super.next();
    Object localObject = c;
    if (localObject == null) {
      return;
    }
    int j = ((ArrayList)localObject).size();
    int i = 0;
    while (i < j)
    {
      localObject = (d)c.get(i);
      ((d)localObject).d(max(), getWidth());
      if (!(localObject instanceof f)) {
        ((d)localObject).next();
      }
      i += 1;
    }
  }
  
  public void run()
  {
    next();
    Object localObject = c;
    if (localObject == null) {
      return;
    }
    int j = ((ArrayList)localObject).size();
    int i = 0;
    while (i < j)
    {
      localObject = (d)c.get(i);
      if ((localObject instanceof b)) {
        ((b)localObject).run();
      }
      i += 1;
    }
  }
  
  public void write()
  {
    c.clear();
  }
}
