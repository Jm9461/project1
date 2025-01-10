package apps.objectweb.asm;

import android.content.Context;
import b.d.a.c.d;

public class a<T extends c.d>
{
  private float[] d;
  private float[][] k;
  
  public a(Context paramContext) {}
  
  protected a a()
  {
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public a a(int paramInt)
  {
    d = Label.getColor(paramInt);
    return a();
  }
  
  public a a(int[] paramArrayOfInt)
  {
    k = new float[paramArrayOfInt.length][];
    int i = 0;
    while (i < paramArrayOfInt.length)
    {
      k[i] = Label.getColor(paramArrayOfInt[i]);
      i += 1;
    }
    return a();
  }
  
  float[][] b()
  {
    return k;
  }
  
  float[] getValue()
  {
    return d;
  }
}
