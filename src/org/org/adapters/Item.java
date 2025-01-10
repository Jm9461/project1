package org.org.adapters;

class Item
{
  public final long a;
  public final long b;
  
  private Item(long paramLong1, long paramLong2)
  {
    if (paramLong2 == 0L)
    {
      a = 0L;
      b = 1L;
      return;
    }
    a = paramLong1;
    b = paramLong2;
  }
  
  public double a()
  {
    double d1 = a;
    double d2 = b;
    Double.isNaN(d1);
    Double.isNaN(d2);
    return d1 / d2;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(a);
    localStringBuilder.append("/");
    localStringBuilder.append(b);
    return localStringBuilder.toString();
  }
}
