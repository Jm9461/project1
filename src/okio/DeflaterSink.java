package okio;

final class DeflaterSink
  implements Sink
{
  DeflaterSink() {}
  
  public void close() {}
  
  public void flush() {}
  
  public Timeout timeout()
  {
    return Timeout.NONE;
  }
  
  public void write(Buffer paramBuffer, long paramLong)
  {
    paramBuffer.skip(paramLong);
  }
}
