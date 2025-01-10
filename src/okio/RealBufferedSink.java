package okio;

import java.nio.ByteBuffer;

final class RealBufferedSink
  implements BufferedSink
{
  public final Buffer buffer = new Buffer();
  boolean closed;
  public final Sink sink;
  
  RealBufferedSink(Sink paramSink)
  {
    if (paramSink != null)
    {
      sink = paramSink;
      return;
    }
    throw new NullPointerException("sink == null");
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
    Object localObject1 = null;
    try
    {
      long l = buffer.size;
      if (l > 0L) {
        sink.write(buffer, buffer.size);
      }
    }
    catch (Throwable localThrowable1) {}
    Object localObject2;
    try
    {
      sink.close();
      localObject2 = localThrowable1;
    }
    catch (Throwable localThrowable2)
    {
      localObject2 = localThrowable1;
      if (localThrowable1 == null) {
        localObject2 = localThrowable2;
      }
    }
    closed = true;
    if (localObject2 == null) {
      return;
    }
    Util.error((Throwable)localObject2);
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public BufferedSink emitCompleteSegments()
  {
    if (!closed)
    {
      long l = buffer.completeSegmentByteCount();
      if (l > 0L)
      {
        sink.write(buffer, l);
        return this;
      }
    }
    else
    {
      throw new IllegalStateException("closed");
    }
    return this;
  }
  
  public void flush()
  {
    if (!closed)
    {
      Buffer localBuffer = buffer;
      long l = size;
      if (l > 0L) {
        sink.write(localBuffer, l);
      }
      sink.flush();
      return;
    }
    throw new IllegalStateException("closed");
  }
  
  public boolean isOpen()
  {
    return closed ^ true;
  }
  
  public Timeout timeout()
  {
    return sink.timeout();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("buffer(");
    localStringBuilder.append(sink);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public int write(ByteBuffer paramByteBuffer)
  {
    if (!closed)
    {
      int i = buffer.write(paramByteBuffer);
      emitCompleteSegments();
      return i;
    }
    throw new IllegalStateException("closed");
  }
  
  public BufferedSink write(byte[] paramArrayOfByte)
  {
    if (!closed)
    {
      buffer.write(paramArrayOfByte);
      emitCompleteSegments();
      return this;
    }
    throw new IllegalStateException("closed");
  }
  
  public BufferedSink write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (!closed)
    {
      buffer.write(paramArrayOfByte, paramInt1, paramInt2);
      emitCompleteSegments();
      return this;
    }
    throw new IllegalStateException("closed");
  }
  
  public void write(Buffer paramBuffer, long paramLong)
  {
    if (!closed)
    {
      buffer.write(paramBuffer, paramLong);
      emitCompleteSegments();
      return;
    }
    throw new IllegalStateException("closed");
  }
  
  public BufferedSink writeByte(int paramInt)
  {
    if (!closed)
    {
      buffer.writeByte(paramInt);
      emitCompleteSegments();
      return this;
    }
    throw new IllegalStateException("closed");
  }
  
  public BufferedSink writeDecimalLong(long paramLong)
  {
    if (!closed)
    {
      buffer.writeDecimalLong(paramLong);
      emitCompleteSegments();
      return this;
    }
    throw new IllegalStateException("closed");
  }
  
  public BufferedSink writeHexadecimalUnsignedLong(long paramLong)
  {
    if (!closed)
    {
      buffer.writeHexadecimalUnsignedLong(paramLong);
      return emitCompleteSegments();
    }
    throw new IllegalStateException("closed");
  }
  
  public BufferedSink writeInt(int paramInt)
  {
    if (!closed)
    {
      buffer.writeInt(paramInt);
      return emitCompleteSegments();
    }
    throw new IllegalStateException("closed");
  }
  
  public BufferedSink writeShort(int paramInt)
  {
    if (!closed)
    {
      buffer.writeShort(paramInt);
      emitCompleteSegments();
      return this;
    }
    throw new IllegalStateException("closed");
  }
  
  public BufferedSink writeUtf8(String paramString)
  {
    if (!closed)
    {
      buffer.writeUtf8(paramString);
      return emitCompleteSegments();
    }
    throw new IllegalStateException("closed");
  }
}
