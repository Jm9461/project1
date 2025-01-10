package spongycastle.mirrajabi.persiancalendar.asm;

import spongycastle.mirrajabi.persiancalendar.crypto.asm.ActionMenuItemView;
import spongycastle.mirrajabi.persiancalendar.crypto.asm.Item;

public final class ByteVector
{
  private static double a(long paramLong, int paramInt)
  {
    double d1 = paramLong;
    double d2 = paramInt;
    Double.isNaN(d2);
    d2 /= 4.0D;
    Double.isNaN(d1);
    d2 = d1 + d2;
    double d3 = d2 / 1236.85D;
    double d4 = d3 * d3;
    double d5 = d4 * d3;
    double d6 = Math.sin((132.87D * d3 + 166.56D - 0.009173D * d4) * 0.0174532925199433D);
    d1 = (29.10535608D * d2 + 359.2242D - 3.33E-5D * d4 - 3.47E-6D * d5) * 0.0174532925199433D;
    double d7 = (385.81691806D * d2 + 306.0253D + 0.0107306D * d4 + 1.236E-5D * d5) * 0.0174532925199433D;
    double d8 = (390.67050646D * d2 + 21.2964D - 0.0016528D * d4 - 2.39E-6D * d5) * 0.0349065850398866D;
    if (paramInt != 0)
    {
      if (paramInt != 1)
      {
        if (paramInt == 2) {
          break label453;
        }
        if (paramInt != 3) {
          return 0.0D;
        }
      }
      d8 = (0.1721D - d3 * 4.0E-4D) * Math.sin(d1) + Math.sin(d1 * 2.0D) * 0.0021D - Math.sin(d7) * 0.628D + Math.sin(d7 * 2.0D) * 0.0089D - Math.sin(3.0D * d7) * 4.0E-4D + Math.sin(d8) * 0.0079D - Math.sin(d1 + d7) * 0.0119D - Math.sin(d1 - d7) * 0.0047D + Math.sin(d8 + d1) * 3.0E-4D - Math.sin(d8 - d1) * 4.0E-4D - Math.sin(d8 + d7) * 6.0E-4D + Math.sin(d8 - d7) * 0.0021D + Math.sin(d1 + d7 * 2.0D) * 3.0E-4D + Math.sin(d1 - d7 * 2.0D) * 4.0E-4D - Math.sin(2.0D * d1 + d7) * 3.0E-4D;
      if (paramInt == 1)
      {
        d1 = d8 + 0.0028D - Math.cos(d1) * 4.0E-4D + Math.cos(d7) * 3.0E-4D;
        break label620;
      }
      d1 = d8 - 0.0028D + Math.cos(d1) * 4.0E-4D - Math.cos(d7) * 3.0E-4D;
      break label620;
    }
    label453:
    d1 = (0.1734D - 3.93E-4D * d3) * Math.sin(d1) + Math.sin(d1 * 2.0D) * 0.0021D - Math.sin(d7) * 0.4068D + Math.sin(d7 * 2.0D) * 0.0161D - Math.sin(3.0D * d7) * 4.0E-4D + Math.sin(d8) * 0.0104D - Math.sin(d1 + d7) * 0.0051D - Math.sin(d1 - d7) * 0.0074D + Math.sin(d8 + d1) * 4.0E-4D - Math.sin(d8 - d1) * 4.0E-4D - Math.sin(d8 + d7) * 6.0E-4D + Math.sin(d8 - d7) * 0.001D + Math.sin(d1 + 2.0D * d7) * 5.0E-4D;
    label620:
    return 29.53058868D * d2 + 2415020.75933D - 1.178E-4D * d4 - 1.55E-7D * d5 + d6 * 3.3E-4D + d1 - (1.2053D * d3 + 0.41D + 0.4992D * d4) / 1440.0D;
  }
  
  public static long a(Item paramItem)
  {
    long l1 = paramItem.getTitle();
    long l2 = paramItem.a();
    long l3 = paramItem.getName();
    if ((l1 <= 1582L) && ((l1 != 1582L) || (l2 <= 10L)) && ((l1 != 1582L) || (l2 != 10L) || (l3 <= 14L))) {
      return read(l1, l2, l3);
    }
    return (4800L + l1 + (l2 - 14L) / 12L) * 1461L / 4L + (l2 - 2L - (l2 - 14L) / 12L * 12L) * 367L / 12L - (l1 + 4900L + (l2 - 14L) / 12L) / 100L * 3L / 4L + l3 - 32075L;
  }
  
  public static Item a(long paramLong)
  {
    if (paramLong > 2299160L)
    {
      long l1 = 68569L + paramLong;
      paramLong = l1 * 4L / 146097L;
      long l2 = l1 - (146097L * paramLong + 3L) / 4L;
      l1 = (1L + l2) * 4000L / 1461001L;
      long l3 = l2 - 1461L * l1 / 4L + 31L;
      l2 = l3 * 80L / 2447L;
      int i = (int)(l3 - 2447L * l2 / 80L);
      l3 = l2 / 11L;
      int j = (int)(2L + l2 - 12L * l3);
      return new Item((int)((paramLong - 49L) * 100L + l1 + l3), j, i);
    }
    return read(paramLong);
  }
  
  public static Item a(ActionMenuItemView paramActionMenuItemView)
  {
    return a(add(paramActionMenuItemView));
  }
  
  public static Item a(spongycastle.mirrajabi.persiancalendar.crypto.asm.Object paramObject)
  {
    return a(add(paramObject));
  }
  
  private static double add(long paramLong)
  {
    double d2 = a(paramLong, 0);
    double d1 = add(d2);
    Double.isNaN(d1);
    double d3 = d2 - d1;
    if (d3 <= 0.5D) {
      return 1.0D + d2;
    }
    d1 = d2;
    if ((d3 - 0.5D) * 24.0D + 3.0D > 6.0D) {
      d1 = 1.0D + d2;
    }
    return d1;
  }
  
  private static long add(double paramDouble)
  {
    return Math.floor(paramDouble);
  }
  
  public static long add(ActionMenuItemView paramActionMenuItemView)
  {
    int j = paramActionMenuItemView.e();
    int i = j;
    int k = paramActionMenuItemView.a();
    int m = paramActionMenuItemView.get();
    if (j < 0) {
      i = j + 1;
    }
    double d1 = add(1048L + (i * 12 + k - 16861));
    double d2 = m;
    Double.isNaN(d2);
    return add(d1 + d2 + 0.5D);
  }
  
  public static long add(spongycastle.mirrajabi.persiancalendar.crypto.asm.Object paramObject)
  {
    int i = paramObject.get();
    int j = paramObject.a();
    int k = paramObject.getId();
    long l1;
    if (i >= 0) {
      l1 = i - 474;
    } else {
      l1 = i - 473;
    }
    long l3 = l1 % 2820L + 474L;
    long l2;
    if (j <= 7) {
      l2 = (j - 1) * 31;
    } else {
      l2 = (j - 1) * 30 + 6;
    }
    return k + l2 + (682L * l3 - 110L) / 2816L + (l3 - 1L) * 365L + l1 / 2820L * 1029983L + 1948320L;
  }
  
  public static spongycastle.mirrajabi.persiancalendar.crypto.asm.Object b(Item paramItem)
  {
    return write(a(paramItem));
  }
  
  public static long read(long paramLong1, long paramLong2, long paramLong3)
  {
    return 367L * paramLong1 - (5001L + paramLong1 + (paramLong2 - 9L) / 7L) * 7L / 4L + 275L * paramLong2 / 9L + paramLong3 + 1729777L;
  }
  
  public static Item read(long paramLong)
  {
    long l1 = paramLong + 1402L;
    paramLong = (l1 - 1L) / 1461L;
    long l2 = l1 - paramLong * 1461L;
    l1 = (l2 - 1L) / 365L - l2 / 1461L;
    long l3 = l2 - 365L * l1 + 30L;
    l2 = l3 * 80L / 2447L;
    int i = (int)(l3 - 2447L * l2 / 80L);
    l3 = l2 / 11L;
    int j = (int)(2L + l2 - 12L * l3);
    return new Item((int)(4L * paramLong + l1 + l3 - 4716L), j, i);
  }
  
  public static long write(int paramInt1, int paramInt2, int paramInt3)
  {
    long l1;
    if (paramInt1 >= 0) {
      l1 = paramInt1 - 474;
    } else {
      l1 = paramInt1 - 473;
    }
    long l3 = l1 % 2820L + 474L;
    long l2;
    if (paramInt2 <= 7) {
      l2 = (paramInt2 - 1) * 31;
    } else {
      l2 = (paramInt2 - 1) * 30 + 6;
    }
    return paramInt3 + l2 + (682L * l3 - 110L) / 2816L + (l3 - 1L) * 365L + l1 / 2820L * 1029983L + 1948320L;
  }
  
  public static spongycastle.mirrajabi.persiancalendar.crypto.asm.Object write(long paramLong)
  {
    long l1 = paramLong - write(475, 1, 1);
    long l2 = l1 / 1029983L;
    l1 %= 1029983L;
    double d;
    if (l1 == 1029982L)
    {
      l1 = 2820L;
    }
    else
    {
      long l3 = l1 / 366L;
      d = 2134L * l3 + 2816L * (l1 % 366L) + 2815L;
      Double.isNaN(d);
      l1 = add(d / 1028522.0D) + l3 + 1L;
    }
    int j = (int)(2820L * l2 + l1 + 474L);
    int i = j;
    if (j <= 0) {
      i = j - 1;
    }
    l1 = paramLong - write(i, 1, 1) + 1L;
    if (l1 <= 186L)
    {
      d = l1;
      Double.isNaN(d);
      j = (int)Math.ceil(d / 31.0D);
    }
    else
    {
      d = l1 - 6L;
      Double.isNaN(d);
      j = (int)Math.ceil(d / 30.0D);
    }
    return new spongycastle.mirrajabi.persiancalendar.crypto.asm.Object(i, j, (int)(paramLong - write(i, j, 1)) + 1);
  }
}
