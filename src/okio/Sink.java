package okio;

import java.io.Closeable;
import java.io.Flushable;

public abstract interface Sink
  extends Closeable, Flushable
{
  public abstract void close();
  
  public abstract void flush();
  
  public abstract Timeout timeout();
  
  public abstract void write(Buffer paramBuffer, long paramLong);
}
