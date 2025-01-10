package okhttp.internal.spdy;

import e.f0.i.c;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;

final class ClassWriter
{
  private final int b;
  Label[] c = new Label[8];
  int d = c.length - 1;
  int e = 0;
  private int f;
  int g = 0;
  private final BufferedSource source;
  private final List<c> x = new ArrayList();
  
  ClassWriter(int paramInt1, int paramInt2, Source paramSource)
  {
    b = paramInt1;
    f = paramInt2;
    source = Okio.buffer(paramSource);
  }
  
  ClassWriter(int paramInt, Source paramSource)
  {
    this(paramInt, paramInt, paramSource);
  }
  
  private int a(int paramInt)
  {
    int k = 0;
    if (paramInt > 0)
    {
      int i = c.length - 1;
      int j = paramInt;
      paramInt = k;
      while ((i >= d) && (j > 0))
      {
        arrayOfLabel = c;
        j -= f;
        g -= f;
        e -= 1;
        paramInt += 1;
        i -= 1;
      }
      Label[] arrayOfLabel = c;
      i = d;
      System.arraycopy(arrayOfLabel, i + 1, arrayOfLabel, i + 1 + paramInt, e);
      d += paramInt;
      return paramInt;
    }
    return 0;
  }
  
  private void a(int paramInt, Label paramLabel)
  {
    x.add(paramLabel);
    int j = f;
    int i = j;
    if (paramInt != -1) {
      i = j - c[c(paramInt)].f;
    }
    j = f;
    if (i > j)
    {
      b();
      return;
    }
    j = a(g + i - j);
    if (paramInt == -1)
    {
      paramInt = e;
      Label[] arrayOfLabel1 = c;
      if (paramInt + 1 > arrayOfLabel1.length)
      {
        Label[] arrayOfLabel2 = new Label[arrayOfLabel1.length * 2];
        System.arraycopy(arrayOfLabel1, 0, arrayOfLabel2, arrayOfLabel1.length, arrayOfLabel1.length);
        d = (c.length - 1);
        c = arrayOfLabel2;
      }
      paramInt = d;
      d = (paramInt - 1);
      c[paramInt] = paramLabel;
      e += 1;
    }
    else
    {
      int k = c(paramInt);
      c[(paramInt + (k + j))] = paramLabel;
    }
    g += i;
  }
  
  private void b()
  {
    Arrays.fill(c, null);
    d = (c.length - 1);
    e = 0;
    g = 0;
  }
  
  private void b(int paramInt)
  {
    if (get(paramInt))
    {
      localObject = Attribute.a[paramInt];
      x.add(localObject);
      return;
    }
    int i = c(paramInt - Attribute.a.length);
    if (i >= 0)
    {
      localObject = c;
      if (i < localObject.length)
      {
        x.add(localObject[i]);
        return;
      }
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Header index too large ");
    ((StringBuilder)localObject).append(paramInt + 1);
    throw new IOException(((StringBuilder)localObject).toString());
  }
  
  private int c(int paramInt)
  {
    return d + 1 + paramInt;
  }
  
  private void c()
  {
    int i = f;
    int j = g;
    if (i < j)
    {
      if (i == 0)
      {
        b();
        return;
      }
      a(j - i);
    }
  }
  
  private ByteString d(int paramInt)
  {
    if (get(paramInt)) {
      return ac;
    }
    int i = c(paramInt - Attribute.a.length);
    if (i >= 0)
    {
      localObject = c;
      if (i < localObject.length) {
        return c;
      }
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Header index too large ");
    ((StringBuilder)localObject).append(paramInt + 1);
    throw new IOException(((StringBuilder)localObject).toString());
  }
  
  private void get()
  {
    ByteString localByteString1 = Attribute.decode(readByteString());
    ByteString localByteString2 = readByteString();
    x.add(new Label(localByteString1, localByteString2));
  }
  
  private boolean get(int paramInt)
  {
    return (paramInt >= 0) && (paramInt <= Attribute.a.length - 1);
  }
  
  private void init(int paramInt)
  {
    ByteString localByteString1 = d(paramInt);
    ByteString localByteString2 = readByteString();
    x.add(new Label(localByteString1, localByteString2));
  }
  
  private void put()
  {
    a(-1, new Label(Attribute.decode(readByteString()), readByteString()));
  }
  
  private void put(int paramInt)
  {
    a(-1, new Label(d(paramInt), readByteString()));
  }
  
  private int readByte()
  {
    return source.readByte() & 0xFF;
  }
  
  void a()
  {
    while (!source.exhausted())
    {
      int i = source.readByte() & 0xFF;
      if (i != 128)
      {
        if ((i & 0x80) == 128)
        {
          b(readInt(i, 127) - 1);
        }
        else if (i == 64)
        {
          put();
        }
        else if ((i & 0x40) == 64)
        {
          put(readInt(i, 63) - 1);
        }
        else if ((i & 0x20) == 32)
        {
          f = readInt(i, 31);
          i = f;
          if ((i >= 0) && (i <= b))
          {
            c();
          }
          else
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Invalid dynamic table size update ");
            localStringBuilder.append(f);
            throw new IOException(localStringBuilder.toString());
          }
        }
        else if ((i != 16) && (i != 0))
        {
          init(readInt(i, 15) - 1);
        }
        else
        {
          get();
        }
      }
      else {
        throw new IOException("index == 0");
      }
    }
  }
  
  public List getAndResetHeaderList()
  {
    ArrayList localArrayList = new ArrayList(x);
    x.clear();
    return localArrayList;
  }
  
  ByteString readByteString()
  {
    int j = readByte();
    int i;
    if ((j & 0x80) == 128) {
      i = 1;
    } else {
      i = 0;
    }
    j = readInt(j, 127);
    if (i != 0) {
      return ByteString.of(Type.a().a(source.readByteArray(j)));
    }
    return source.readByteString(j);
  }
  
  int readInt(int paramInt1, int paramInt2)
  {
    paramInt1 &= paramInt2;
    if (paramInt1 < paramInt2) {
      return paramInt1;
    }
    paramInt1 = 0;
    int i;
    for (;;)
    {
      i = readByte();
      if ((i & 0x80) == 0) {
        break;
      }
      paramInt2 += ((i & 0x7F) << paramInt1);
      paramInt1 += 7;
    }
    return paramInt2 + (i << paramInt1);
  }
}
