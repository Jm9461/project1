package okio;

import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;

public abstract interface BufferedSource
  extends Source, ReadableByteChannel
{
  public abstract Buffer buffer();
  
  public abstract boolean exhausted();
  
  public abstract InputStream inputStream();
  
  public abstract long read(byte paramByte);
  
  public abstract String read(long paramLong);
  
  public abstract boolean read(long paramLong, ByteString paramByteString);
  
  public abstract byte readByte();
  
  public abstract byte[] readByteArray();
  
  public abstract byte[] readByteArray(long paramLong);
  
  public abstract ByteString readByteString(long paramLong);
  
  public abstract long readDecimalLong();
  
  public abstract void readFully(byte[] paramArrayOfByte);
  
  public abstract long readHexadecimalUnsignedLong();
  
  public abstract int readInt();
  
  public abstract int readIntLe();
  
  public abstract short readShort();
  
  public abstract short readShortLe();
  
  public abstract String readUtf8LineStrict();
  
  public abstract void require(long paramLong);
  
  public abstract void skip(long paramLong);
}
