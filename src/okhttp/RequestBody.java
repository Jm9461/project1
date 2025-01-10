package okhttp;

import okhttp.internal.Util;
import okio.BufferedSink;

public abstract class RequestBody
{
  public RequestBody() {}
  
  public static RequestBody create(MediaType paramMediaType, byte[] paramArrayOfByte)
  {
    return create(paramMediaType, paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static RequestBody create(MediaType paramMediaType, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte != null)
    {
      Util.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
      return new RequestBody.2(paramMediaType, paramInt2, paramArrayOfByte, paramInt1);
    }
    throw new NullPointerException("content == null");
  }
  
  public abstract long contentLength();
  
  public abstract MediaType contentType();
  
  public abstract void writeTo(BufferedSink paramBufferedSink);
}
