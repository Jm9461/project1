package okhttp.internal.spdy;

import java.io.IOException;
import okhttp.internal.http.StreamAllocation;
import okio.Buffer;
import okio.ForwardingSource;
import okio.Source;

class Base64InputStream
  extends ForwardingSource
{
  boolean closed = false;
  long index = 0L;
  
  Base64InputStream(Http2xStream paramHttp2xStream, Source paramSource)
  {
    super(paramSource);
  }
  
  private void close(IOException paramIOException)
  {
    if (closed) {
      return;
    }
    closed = true;
    Http2xStream localHttp2xStream = this$0;
    streamAllocation.streamFinished(false, localHttp2xStream, index, paramIOException);
  }
  
  public void close()
  {
    super.close();
    close(null);
  }
  
  public long read(Buffer paramBuffer, long paramLong)
  {
    try
    {
      paramLong = delegate().read(paramBuffer, paramLong);
      if (paramLong > 0L)
      {
        index += paramLong;
        return paramLong;
      }
    }
    catch (IOException paramBuffer)
    {
      close(paramBuffer);
      throw paramBuffer;
    }
    return paramLong;
  }
}
