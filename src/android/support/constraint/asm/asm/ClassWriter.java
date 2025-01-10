package android.support.constraint.asm.asm;

class ClassWriter
{
  private h a;
  private h b;
  private int k;
  private SizeLayoutType w;
  private int x;
  
  public ClassWriter(h paramH)
  {
    b = paramH;
    a = paramH.getValue();
    x = paramH.b();
    w = paramH.setChecked();
    k = paramH.l();
  }
  
  public void b(d paramD)
  {
    b = paramD.a(b.e());
    paramD = b;
    if (paramD != null)
    {
      a = paramD.getValue();
      x = b.b();
      w = b.setChecked();
      k = b.l();
      return;
    }
    a = null;
    x = 0;
    w = SizeLayoutType.s;
    k = 0;
  }
  
  public void c(d paramD)
  {
    paramD.a(b.e()).a(a, x, w, k);
  }
}
