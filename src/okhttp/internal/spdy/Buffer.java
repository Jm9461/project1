package okhttp.internal.spdy;

import java.io.IOException;
import java.net.SocketTimeoutException;
import okio.Context;

class Buffer
  extends Context
{
  Buffer(SpdyStream paramSpdyStream) {}
  
  public void close()
  {
    if (!exit()) {
      return;
    }
    throw newTimeoutException(null);
  }
  
  protected IOException newTimeoutException(IOException paramIOException)
  {
    SocketTimeoutException localSocketTimeoutException = new SocketTimeoutException("timeout");
    if (paramIOException != null) {
      localSocketTimeoutException.initCause(paramIOException);
    }
    return localSocketTimeoutException;
  }
  
  protected void timedOut()
  {
    this$0.closeLater(ErrorCode.CANCEL);
  }
}
