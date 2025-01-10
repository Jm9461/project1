package okio;

public abstract class ForwardingSink
  implements Sink
{
  private final Sink delegate;
  
  public ForwardingSink(Sink paramSink)
  {
    if (paramSink != null)
    {
      delegate = paramSink;
      return;
    }
    throw new IllegalArgumentException("delegate == null");
  }
  
  public void close()
  {
    delegate.close();
  }
  
  public void flush()
  {
    delegate.flush();
  }
  
  public Timeout timeout()
  {
    return delegate.timeout();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getClass().getSimpleName());
    localStringBuilder.append("(");
    localStringBuilder.append(delegate.toString());
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void write(Buffer paramBuffer, long paramLong)
  {
    delegate.write(paramBuffer, paramLong);
  }
}
