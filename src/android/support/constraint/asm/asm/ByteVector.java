package android.support.constraint.asm.asm;

public class ByteVector
  extends Label
{
  float a = 0.0F;
  
  public ByteVector() {}
  
  public void a()
  {
    b = 2;
  }
  
  public void a(int paramInt)
  {
    if ((b == 0) || (a != paramInt))
    {
      a = paramInt;
      if (b == 1) {
        setText();
      }
      draw();
    }
  }
  
  public void b()
  {
    super.b();
    a = 0.0F;
  }
}
