package com.squareup.picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

final class MarkableInputStream
  extends InputStream
{
  private long defaultMark = -1L;
  private final InputStream in;
  private boolean index = true;
  private long limit;
  private long offset;
  private int pos = -1;
  private long reset;
  
  MarkableInputStream(InputStream paramInputStream)
  {
    this(paramInputStream, 4096);
  }
  
  MarkableInputStream(InputStream paramInputStream, int paramInt)
  {
    this(paramInputStream, paramInt, 1024);
  }
  
  private MarkableInputStream(InputStream paramInputStream, int paramInt1, int paramInt2)
  {
    Object localObject = paramInputStream;
    if (!((InputStream)paramInputStream).markSupported()) {
      localObject = new BufferedInputStream((InputStream)paramInputStream, paramInt1);
    }
    in = ((InputStream)localObject);
    pos = paramInt2;
  }
  
  private void setLimit(long paramLong)
  {
    InputStream localInputStream;
    if ((reset < offset) && (offset <= limit)) {
      localInputStream = in;
    }
    try
    {
      localInputStream.reset();
      localInputStream = in;
      int i = (int)(paramLong - reset);
      localInputStream.mark(i);
      long l1 = reset;
      long l2 = offset;
      skip(l1, l2);
      break label106;
      reset = offset;
      localInputStream = in;
      i = (int)(paramLong - offset);
      localInputStream.mark(i);
      label106:
      limit = paramLong;
      return;
    }
    catch (IOException localIOException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unable to mark: ");
      localStringBuilder.append(localIOException);
      throw new IllegalStateException(localStringBuilder.toString());
    }
  }
  
  private void skip(long paramLong1, long paramLong2)
  {
    while (paramLong1 < paramLong2)
    {
      long l2 = in.skip(paramLong2 - paramLong1);
      long l1 = l2;
      if (l2 == 0L)
      {
        if (read() == -1) {
          return;
        }
        l1 = 1L;
      }
      paramLong1 += l1;
    }
  }
  
  public int available()
  {
    return in.available();
  }
  
  public void close()
  {
    in.close();
  }
  
  public void mark(int paramInt)
  {
    defaultMark = savePosition(paramInt);
  }
  
  public boolean markSupported()
  {
    return in.markSupported();
  }
  
  public int read()
  {
    if (!index)
    {
      long l1 = offset;
      long l2 = limit;
      if (l1 + 1L > l2) {
        setLimit(l2 + pos);
      }
    }
    int i = in.read();
    if (i != -1) {
      offset += 1L;
    }
    return i;
  }
  
  public int read(byte[] paramArrayOfByte)
  {
    if (!index)
    {
      long l = offset;
      if (paramArrayOfByte.length + l > limit) {
        setLimit(l + paramArrayOfByte.length + pos);
      }
    }
    int i = in.read(paramArrayOfByte);
    if (i != -1) {
      offset += i;
    }
    return i;
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (!index)
    {
      long l = offset;
      if (paramInt2 + l > limit) {
        setLimit(l + paramInt2 + pos);
      }
    }
    paramInt1 = in.read(paramArrayOfByte, paramInt1, paramInt2);
    if (paramInt1 != -1) {
      offset += paramInt1;
    }
    return paramInt1;
  }
  
  public void reset()
  {
    reset(defaultMark);
  }
  
  public void reset(long paramLong)
  {
    if ((offset <= limit) && (paramLong >= reset))
    {
      in.reset();
      skip(reset, paramLong);
      offset = paramLong;
      return;
    }
    throw new IOException("Cannot reset");
  }
  
  public void reset(boolean paramBoolean)
  {
    index = paramBoolean;
  }
  
  public long savePosition(int paramInt)
  {
    long l = offset + paramInt;
    if (limit < l) {
      setLimit(l);
    }
    return offset;
  }
  
  public long skip(long paramLong)
  {
    if (!index)
    {
      long l = offset;
      if (l + paramLong > limit) {
        setLimit(l + paramLong + pos);
      }
    }
    paramLong = in.skip(paramLong);
    offset += paramLong;
    return paramLong;
  }
}
