package okhttp.internal.httpclient;

import okio.Buffer;
import okio.ForwardingSink;
import okio.Sink;

final class FaultHidingSink
  extends ForwardingSink
{
  long a;
  
  FaultHidingSink(Sink paramSink)
  {
    super(paramSink);
  }
  
  public void write(Buffer paramBuffer, long paramLong)
  {
    super.write(paramBuffer, paramLong);
    a += paramLong;
  }
}
