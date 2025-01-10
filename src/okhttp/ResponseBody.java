package okhttp;

import java.io.Closeable;
import okhttp.internal.Util;
import okio.Buffer;
import okio.BufferedSource;

public abstract class ResponseBody
  implements Closeable
{
  public ResponseBody() {}
  
  public static ResponseBody create(MediaType paramMediaType, long paramLong, BufferedSource paramBufferedSource)
  {
    if (paramBufferedSource != null) {
      return new b0.a(paramMediaType, paramLong, paramBufferedSource);
    }
    throw new NullPointerException("source == null");
  }
  
  public static ResponseBody create(MediaType paramMediaType, byte[] paramArrayOfByte)
  {
    Buffer localBuffer = new Buffer().write(paramArrayOfByte);
    return create(paramMediaType, paramArrayOfByte.length, localBuffer);
  }
  
  public void close()
  {
    Util.closeQuietly(source());
  }
  
  public abstract long contentLength();
  
  public abstract BufferedSource source();
}
