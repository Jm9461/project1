package android.support.constraint.asm.asm;

import java.util.Arrays;

public class i
  extends d
{
  protected d[] b = new d[4];
  protected int r = 0;
  
  public i() {}
  
  public void a(d paramD)
  {
    int i = r;
    d[] arrayOfD = b;
    if (i + 1 > arrayOfD.length) {
      b = ((d[])Arrays.copyOf(arrayOfD, arrayOfD.length * 2));
    }
    arrayOfD = b;
    i = r;
    arrayOfD[i] = paramD;
    r = (i + 1);
  }
  
  public void h()
  {
    r = 0;
  }
}
