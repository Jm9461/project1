package android.support.constraint.asm.asm;

import android.support.constraint.i.j.n.a;
import java.util.ArrayList;

public class e
{
  private int a;
  private int d;
  private int e;
  private int i;
  private ArrayList<n.a> j = new ArrayList();
  
  public e(d paramD)
  {
    i = paramD.length();
    e = paramD.getType();
    a = paramD.size();
    d = paramD.getValue();
    paramD = paramD.j();
    int k = 0;
    int m = paramD.size();
    while (k < m)
    {
      h localH = (h)paramD.get(k);
      j.add(new ClassWriter(localH));
      k += 1;
    }
  }
  
  public void a(d paramD)
  {
    i = paramD.length();
    e = paramD.getType();
    a = paramD.size();
    d = paramD.getValue();
    int m = j.size();
    int k = 0;
    while (k < m)
    {
      ((ClassWriter)j.get(k)).b(paramD);
      k += 1;
    }
  }
  
  public void b(d paramD)
  {
    paramD.append(i);
    paramD.setText(e);
    paramD.d(a);
    paramD.c(d);
    int k = 0;
    int m = j.size();
    while (k < m)
    {
      ((ClassWriter)j.get(k)).c(paramD);
      k += 1;
    }
  }
}
