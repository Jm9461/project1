package okio;

import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;

final class RealBufferedSource
  implements BufferedSource
{
  public final Buffer buffer = new Buffer();
  boolean closed;
  public final Source source;
  
  RealBufferedSource(Source paramSource)
  {
    if (paramSource != null)
    {
      source = paramSource;
      return;
    }
    throw new NullPointerException("source == null");
  }
  
  public Buffer buffer()
  {
    return buffer;
  }
  
  public void close()
  {
    if (closed) {
      return;
    }
    closed = true;
    source.close();
    buffer.clear();
  }
  
  public boolean exhausted()
  {
    if (!closed) {
      return (buffer.exhausted()) && (source.read(buffer, 8192L) == -1L);
    }
    throw new IllegalStateException("closed");
  }
  
  public InputStream inputStream()
  {
    return new RealBufferedSource.1(this);
  }
  
  public boolean isOpen()
  {
    return closed ^ true;
  }
  
  public int read(ByteBuffer paramByteBuffer)
  {
    Buffer localBuffer = buffer;
    if ((size == 0L) && (source.read(localBuffer, 8192L) == -1L)) {
      return -1;
    }
    return buffer.read(paramByteBuffer);
  }
  
  public long read(byte paramByte)
  {
    return read(paramByte, 0L, Long.MAX_VALUE);
  }
  
  public long read(byte paramByte, long paramLong1, long paramLong2)
  {
    if (!closed)
    {
      if ((paramLong1 >= 0L) && (paramLong2 >= paramLong1))
      {
        while (paramLong1 < paramLong2)
        {
          long l = buffer.read(paramByte, paramLong1, paramLong2);
          if (l != -1L) {
            return l;
          }
          localObject = buffer;
          l = size;
          if (l >= paramLong2) {
            break;
          }
          if (source.read((Buffer)localObject, 8192L) == -1L) {
            return -1L;
          }
          paramLong1 = Math.max(paramLong1, l);
        }
        return -1L;
      }
      throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", new Object[] { Long.valueOf(paramLong1), Long.valueOf(paramLong2) }));
    }
    Object localObject = new IllegalStateException("closed");
    throw ((Throwable)localObject);
  }
  
  public long read(Buffer paramBuffer, long paramLong)
  {
    if (paramBuffer != null)
    {
      if (paramLong >= 0L)
      {
        if (!closed)
        {
          Buffer localBuffer = buffer;
          if ((size == 0L) && (source.read(localBuffer, 8192L) == -1L)) {
            return -1L;
          }
          paramLong = Math.min(paramLong, buffer.size);
          return buffer.read(paramBuffer, paramLong);
        }
        throw new IllegalStateException("closed");
      }
      paramBuffer = new StringBuilder();
      paramBuffer.append("byteCount < 0: ");
      paramBuffer.append(paramLong);
      throw new IllegalArgumentException(paramBuffer.toString());
    }
    throw new IllegalArgumentException("sink == null");
  }
  
  public String read(long paramLong)
  {
    if (paramLong >= 0L)
    {
      long l1;
      if (paramLong == Long.MAX_VALUE) {
        l1 = Long.MAX_VALUE;
      } else {
        l1 = paramLong + 1L;
      }
      long l2 = read((byte)10, 0L, l1);
      if (l2 != -1L) {
        return buffer.get(l2);
      }
      if ((l1 < Long.MAX_VALUE) && (request(l1)) && (buffer.getByte(l1 - 1L) == 13) && (request(1L + l1)) && (buffer.getByte(l1) == 10)) {
        return buffer.get(l1);
      }
      localObject1 = new Buffer();
      Object localObject2 = buffer;
      ((Buffer)localObject2).copyTo((Buffer)localObject1, 0L, Math.min(32L, ((Buffer)localObject2).size()));
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("\\n not found: limit=");
      ((StringBuilder)localObject2).append(Math.min(buffer.size(), paramLong));
      ((StringBuilder)localObject2).append(" content=");
      ((StringBuilder)localObject2).append(((Buffer)localObject1).read().hex());
      ((StringBuilder)localObject2).append('?');
      throw new EOFException(((StringBuilder)localObject2).toString());
    }
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("limit < 0: ");
    ((StringBuilder)localObject1).append(paramLong);
    throw new IllegalArgumentException(((StringBuilder)localObject1).toString());
  }
  
  public boolean read(long paramLong, ByteString paramByteString)
  {
    return write(paramLong, paramByteString, 0, paramByteString.size());
  }
  
  public byte readByte()
  {
    require(1L);
    return buffer.readByte();
  }
  
  public byte[] readByteArray()
  {
    buffer.writeAll(source);
    return buffer.readByteArray();
  }
  
  public byte[] readByteArray(long paramLong)
  {
    require(paramLong);
    return buffer.readByteArray(paramLong);
  }
  
  public ByteString readByteString(long paramLong)
  {
    require(paramLong);
    return buffer.readByteString(paramLong);
  }
  
  public long readDecimalLong()
  {
    require(1L);
    int i = 0;
    while (request(i + 1))
    {
      byte b = buffer.getByte(i);
      if (((b >= 48) && (b <= 57)) || ((i == 0) && (b == 45))) {
        i += 1;
      } else if (i == 0) {
        throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", new Object[] { Byte.valueOf(b) }));
      }
    }
    return buffer.readDecimalLong();
  }
  
  public void readFully(byte[] paramArrayOfByte)
  {
    long l = paramArrayOfByte.length;
    try
    {
      require(l);
      buffer.readFully(paramArrayOfByte);
      return;
    }
    catch (EOFException localEOFException)
    {
      int i = 0;
      for (;;)
      {
        Buffer localBuffer = buffer;
        l = size;
        if (l <= 0L) {
          break label75;
        }
        int j = localBuffer.read(paramArrayOfByte, i, (int)l);
        if (j == -1) {
          break;
        }
        i += j;
      }
      throw new AssertionError();
      label75:
      throw localEOFException;
    }
  }
  
  public long readHexadecimalUnsignedLong()
  {
    require(1L);
    int i = 0;
    while (request(i + 1))
    {
      byte b = buffer.getByte(i);
      if (((b >= 48) && (b <= 57)) || ((b >= 97) && (b <= 102)) || ((b >= 65) && (b <= 70))) {
        i += 1;
      } else if (i == 0) {
        throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", new Object[] { Byte.valueOf(b) }));
      }
    }
    return buffer.readHexadecimalUnsignedLong();
  }
  
  public int readInt()
  {
    require(4L);
    return buffer.readInt();
  }
  
  public int readIntLe()
  {
    require(4L);
    return buffer.readIntLe();
  }
  
  public short readShort()
  {
    require(2L);
    return buffer.readShort();
  }
  
  public short readShortLe()
  {
    require(2L);
    return buffer.readShortLe();
  }
  
  public String readUtf8LineStrict()
  {
    return read(Long.MAX_VALUE);
  }
  
  public boolean request(long paramLong)
  {
    if (paramLong >= 0L)
    {
      if (!closed)
      {
        do
        {
          localObject = buffer;
          if (size >= paramLong) {
            break;
          }
        } while (source.read((Buffer)localObject, 8192L) != -1L);
        return false;
        return true;
      }
      throw new IllegalStateException("closed");
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("byteCount < 0: ");
    ((StringBuilder)localObject).append(paramLong);
    localObject = new IllegalArgumentException(((StringBuilder)localObject).toString());
    throw ((Throwable)localObject);
  }
  
  public void require(long paramLong)
  {
    if (request(paramLong)) {
      return;
    }
    throw new EOFException();
  }
  
  public void skip(long paramLong)
  {
    if (!closed)
    {
      while (paramLong > 0L)
      {
        localObject = buffer;
        if ((size == 0L) && (source.read((Buffer)localObject, 8192L) == -1L)) {
          throw new EOFException();
        }
        long l = Math.min(paramLong, buffer.size());
        buffer.skip(l);
        paramLong -= l;
      }
      return;
    }
    Object localObject = new IllegalStateException("closed");
    throw ((Throwable)localObject);
  }
  
  public Timeout timeout()
  {
    return source.timeout();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("buffer(");
    localStringBuilder.append(source);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public boolean write(long paramLong, ByteString paramByteString, int paramInt1, int paramInt2)
  {
    if (!closed)
    {
      if (paramLong >= 0L)
      {
        if ((paramInt1 >= 0) && (paramInt2 >= 0))
        {
          if (paramByteString.size() - paramInt1 < paramInt2) {
            return false;
          }
          int i = 0;
          while (i < paramInt2)
          {
            long l = i + paramLong;
            if (!request(1L + l)) {
              return false;
            }
            if (buffer.getByte(l) != paramByteString.read(paramInt1 + i)) {
              return false;
            }
            i += 1;
          }
          return true;
        }
      }
      else {
        return false;
      }
    }
    else
    {
      paramByteString = new IllegalStateException("closed");
      throw paramByteString;
    }
    return false;
  }
}
