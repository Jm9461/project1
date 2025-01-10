package com.mohamadamin.persianmaterialdatetimepicker.views;

public class Label
{
  public static long a(double paramDouble1, double paramDouble2)
  {
    return (paramDouble1 - Math.floor(paramDouble1 / paramDouble2) * paramDouble2);
  }
  
  public static boolean a(int paramInt)
  {
    double d = a(paramInt - 474L, 2820.0D) + 474L;
    Double.isNaN(d);
    return a((d + 38.0D) * 682.0D, 2816.0D) < 682L;
  }
  
  public static long add(long paramLong, int paramInt1, int paramInt2)
  {
    long l1 = a(paramLong - 474L, 2820.0D);
    double d = (a(paramLong - 474L, 2820.0D) + 474L) * 682L - 110L;
    Double.isNaN(d);
    long l2 = Math.floor(d / 2816.0D);
    d = paramLong - 474L;
    Double.isNaN(d);
    paramLong = Math.floor(d / 2820.0D);
    if (paramInt1 < 7) {
      paramInt1 *= 31;
    } else {
      paramInt1 = paramInt1 * 30 + 6;
    }
    return (l1 + 474L - 1L) * 365L + l2 + 1948320L + paramLong * 1029983L + paramInt1 + paramInt2;
  }
  
  public static long update(long paramLong)
  {
    long l2 = paramLong - add(475L, 0, 1);
    long l1 = a(l2, 1029983.0D);
    if (l1 != 1029982L)
    {
      d1 = l1;
      Double.isNaN(d1);
      l1 = Math.floor((d1 * 2816.0D + 1031337.0D) / 1028522.0D);
    }
    else
    {
      l1 = 2820L;
    }
    double d1 = l2;
    Double.isNaN(d1);
    l1 = Math.floor(d1 / 1029983.0D) * 2820L + 474L + l1;
    l2 = paramLong + 1L - add(l1, 0, 1);
    double d2;
    if (l2 > 186L)
    {
      d1 = l2 - 6L;
      d2 = 30.0D;
    }
    else
    {
      d1 = l2;
      d2 = 31.0D;
    }
    Double.isNaN(d1);
    int i = (int)(Math.ceil(d1 / d2) - 1.0D);
    int j = (int)(paramLong - (add(l1, i, 1) - 1L));
    return l1 << 16 | i << 8 | j;
  }
}
