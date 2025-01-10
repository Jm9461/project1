package video.api.a;

import android.graphics.Typeface;

public class d
{
  private int a = f.d();
  private int b = f.b();
  private int c = f.add();
  private int d = f.i();
  private boolean h = f.k();
  private int n = f.r();
  private int x = f.a();
  private Typeface z = f.l();
  
  private d() {}
  
  public static d a()
  {
    return new d();
  }
  
  public d a(int paramInt)
  {
    n = paramInt;
    return this;
  }
  
  public d a(Typeface paramTypeface)
  {
    z = paramTypeface;
    return this;
  }
  
  public void c()
  {
    f.d(x);
    f.b(b);
    f.c(d);
    f.e(a);
    f.a(c);
    f.a(z);
    f.f(n);
    f.a(h);
  }
}
