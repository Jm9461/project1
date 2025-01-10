package okio;

import java.nio.channels.WritableByteChannel;

public abstract interface BufferedSink
  extends Sink, WritableByteChannel
{
  public abstract Buffer buffer();
  
  public abstract BufferedSink emitCompleteSegments();
  
  public abstract void flush();
  
  public abstract BufferedSink write(byte[] paramArrayOfByte);
  
  public abstract BufferedSink write(byte[] paramArrayOfByte, int paramInt1, int paramInt2);
  
  public abstract BufferedSink writeByte(int paramInt);
  
  public abstract BufferedSink writeDecimalLong(long paramLong);
  
  public abstract BufferedSink writeHexadecimalUnsignedLong(long paramLong);
  
  public abstract BufferedSink writeInt(int paramInt);
  
  public abstract BufferedSink writeShort(int paramInt);
  
  public abstract BufferedSink writeUtf8(String paramString);
}
