package okhttp.internal.spdy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import okhttp.internal.Util;
import okio.Buffer;
import okio.ByteString;

final class i
{
  int a;
  int b = 0;
  Label[] c = new Label[8];
  int d = c.length - 1;
  int e = 0;
  private int j = Integer.MAX_VALUE;
  private boolean l;
  private final Buffer v;
  private final boolean w;
  
  i(int paramInt, boolean paramBoolean, Buffer paramBuffer)
  {
    a = paramInt;
    w = paramBoolean;
    v = paramBuffer;
  }
  
  i(Buffer paramBuffer)
  {
    this(4096, true, paramBuffer);
  }
  
  private int a(int paramInt)
  {
    int m = 0;
    if (paramInt > 0)
    {
      int i = c.length - 1;
      int k = paramInt;
      paramInt = m;
      while ((i >= d) && (k > 0))
      {
        arrayOfLabel = c;
        k -= f;
        b -= f;
        e -= 1;
        paramInt += 1;
        i -= 1;
      }
      Label[] arrayOfLabel = c;
      i = d;
      System.arraycopy(arrayOfLabel, i + 1, arrayOfLabel, i + 1 + paramInt, e);
      arrayOfLabel = c;
      i = d;
      Arrays.fill(arrayOfLabel, i + 1, i + 1 + paramInt, null);
      d += paramInt;
      return paramInt;
    }
    return 0;
  }
  
  private void a()
  {
    Arrays.fill(c, null);
    d = (c.length - 1);
    e = 0;
    b = 0;
  }
  
  private void b()
  {
    int i = a;
    int k = b;
    if (i < k)
    {
      if (i == 0)
      {
        a();
        return;
      }
      a(k - i);
    }
  }
  
  private void b(Label paramLabel)
  {
    int i = f;
    int k = a;
    if (i > k)
    {
      a();
      return;
    }
    a(b + i - k);
    k = e;
    Label[] arrayOfLabel1 = c;
    if (k + 1 > arrayOfLabel1.length)
    {
      Label[] arrayOfLabel2 = new Label[arrayOfLabel1.length * 2];
      System.arraycopy(arrayOfLabel1, 0, arrayOfLabel2, arrayOfLabel1.length, arrayOfLabel1.length);
      d = (c.length - 1);
      c = arrayOfLabel2;
    }
    k = d;
    d = (k - 1);
    c[k] = paramLabel;
    e += 1;
    b += i;
  }
  
  void a(List paramList)
  {
    int i;
    if (l)
    {
      i = j;
      if (i < a) {
        add(i, 31, 32);
      }
      l = false;
      j = Integer.MAX_VALUE;
      add(a, 31, 32);
    }
    int m = 0;
    int i3 = paramList.size();
    while (m < i3)
    {
      Label localLabel = (Label)paramList.get(m);
      ByteString localByteString1 = c.toAsciiLowercase();
      ByteString localByteString2 = g;
      int i1 = -1;
      int k = -1;
      Integer localInteger = (Integer)Attribute.b.get(localByteString1);
      i = i1;
      int n;
      if (localInteger != null)
      {
        n = localInteger.intValue() + 1;
        i = i1;
        k = n;
        if (n > 1)
        {
          i = i1;
          k = n;
          if (n < 8) {
            if (Util.get(a1g, localByteString2))
            {
              i = n;
              k = n;
            }
            else
            {
              i = i1;
              k = n;
              if (Util.get(ag, localByteString2))
              {
                i = n + 1;
                k = n;
              }
            }
          }
        }
      }
      i1 = i;
      int i2 = k;
      if (i == -1)
      {
        n = d + 1;
        int i4 = c.length;
        for (;;)
        {
          i1 = i;
          i2 = k;
          if (n >= i4) {
            break;
          }
          i1 = k;
          if (Util.get(c[n].c, localByteString1))
          {
            if (Util.get(c[n].g, localByteString2))
            {
              i1 = n - d + Attribute.a.length;
              i2 = k;
              break;
            }
            i1 = k;
            if (k == -1) {
              i1 = n - d + Attribute.a.length;
            }
          }
          n += 1;
          k = i1;
        }
      }
      if (i1 != -1)
      {
        add(i1, 127, 128);
      }
      else if (i2 == -1)
      {
        v.writeByte(64);
        add(localByteString1);
        add(localByteString2);
        b(localLabel);
      }
      else if ((localByteString1.write(Label.b)) && (!Label.TARGET_AUTHORITY.equals(localByteString1)))
      {
        add(i2, 15, 0);
        add(localByteString2);
      }
      else
      {
        add(i2, 63, 64);
        add(localByteString2);
        b(localLabel);
      }
      m += 1;
    }
  }
  
  void add(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 < paramInt2)
    {
      v.writeByte(paramInt3 | paramInt1);
      return;
    }
    v.writeByte(paramInt3 | paramInt2);
    paramInt1 -= paramInt2;
    while (paramInt1 >= 128)
    {
      v.writeByte(paramInt1 & 0x7F | 0x80);
      paramInt1 >>>= 7;
    }
    v.writeByte(paramInt1);
  }
  
  void add(ByteString paramByteString)
  {
    if ((w) && (Type.a().a(paramByteString) < paramByteString.size()))
    {
      Buffer localBuffer = new Buffer();
      Type.a().a(paramByteString, localBuffer);
      paramByteString = localBuffer.read();
      add(paramByteString.size(), 127, 128);
      v.write(paramByteString);
      return;
    }
    add(paramByteString.size(), 127, 0);
    v.write(paramByteString);
  }
  
  void b(int paramInt)
  {
    paramInt = Math.min(paramInt, 16384);
    int i = a;
    if (i == paramInt) {
      return;
    }
    if (paramInt < i) {
      j = Math.min(j, paramInt);
    }
    l = true;
    a = paramInt;
    b();
  }
}
