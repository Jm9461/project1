package okio;

import java.io.Closeable;

public abstract interface Source
  extends Closeable
{
  public abstract void close();
  
  public abstract long read(Buffer paramBuffer, long paramLong);
  
  public abstract Timeout timeout();
}
