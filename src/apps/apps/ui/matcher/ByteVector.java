package apps.apps.ui.matcher;

public class ByteVector
{
  private int a;
  private int b;
  private int data;
  private int n;
  
  public ByteVector() {}
  
  public ByteVector a(int paramInt)
  {
    n = paramInt;
    return this;
  }
  
  public a a()
  {
    return new a(b, n, data, a, null);
  }
  
  public ByteVector b(int paramInt)
  {
    a = paramInt;
    return this;
  }
  
  public ByteVector putShort(int paramInt)
  {
    b = paramInt;
    return this;
  }
  
  public ByteVector write(int paramInt)
  {
    data = paramInt;
    return this;
  }
}
