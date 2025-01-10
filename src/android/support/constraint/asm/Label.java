package android.support.constraint.asm;

import java.util.Arrays;

public class Label
{
  private static int f = 1;
  float[] a = new float[7];
  h[] b = new h[8];
  int c = -1;
  f d;
  public int e = 0;
  public float g;
  private String h;
  public int i = -1;
  int k = 0;
  public int z = 0;
  
  public Label(f paramF, String paramString)
  {
    d = paramF;
  }
  
  static void b()
  {
    f += 1;
  }
  
  public void a()
  {
    h = null;
    d = f.o;
    e = 0;
    i = -1;
    c = -1;
    g = 0.0F;
    k = 0;
    z = 0;
  }
  
  public void a(f paramF, String paramString)
  {
    d = paramF;
  }
  
  public final void a(h paramH)
  {
    int n = k;
    int j = 0;
    while (j < n)
    {
      if (b[j] == paramH)
      {
        int m = 0;
        while (m < n - j - 1)
        {
          paramH = b;
          paramH[(j + m)] = paramH[(j + m + 1)];
          m += 1;
        }
        k -= 1;
        return;
      }
      j += 1;
    }
  }
  
  public final void b(h paramH)
  {
    int j = 0;
    int m;
    for (;;)
    {
      m = k;
      if (j >= m) {
        break;
      }
      if (b[j] == paramH) {
        return;
      }
      j += 1;
    }
    h[] arrayOfH = b;
    if (m >= arrayOfH.length) {
      b = ((h[])Arrays.copyOf(arrayOfH, arrayOfH.length * 2));
    }
    arrayOfH = b;
    j = k;
    arrayOfH[j] = paramH;
    k = (j + 1);
  }
  
  public final void c(h paramH)
  {
    int m = k;
    int j = 0;
    while (j < m)
    {
      h[] arrayOfH = b;
      b.b(arrayOfH[j], paramH, false);
      j += 1;
    }
    k = 0;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(h);
    return localStringBuilder.toString();
  }
}
