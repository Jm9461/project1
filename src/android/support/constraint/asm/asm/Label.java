package android.support.constraint.asm.asm;

import android.support.constraint.i.j.m;
import java.util.HashSet;
import java.util.Iterator;

public class Label
{
  HashSet<m> a = new HashSet(2);
  int b = 0;
  
  public Label() {}
  
  public void a(Label paramLabel)
  {
    a.add(paramLabel);
  }
  
  public void b()
  {
    b = 0;
    a.clear();
  }
  
  public boolean c()
  {
    return b == 1;
  }
  
  public void d() {}
  
  public void draw()
  {
    b = 1;
    Iterator localIterator = a.iterator();
    while (localIterator.hasNext()) {
      ((Label)localIterator.next()).d();
    }
  }
  
  public void setText()
  {
    b = 0;
    Iterator localIterator = a.iterator();
    while (localIterator.hasNext()) {
      ((Label)localIterator.next()).setText();
    }
  }
}
