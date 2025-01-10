package okio;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

public class Timeout
{
  public static final Timeout NONE = new Timeout()
  {
    public Timeout deadlineNanoTime(long paramAnonymousLong)
    {
      return this;
    }
    
    public void throwIfReached() {}
    
    public Timeout timeout(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
    {
      return this;
    }
  };
  private long deadlineNanoTime;
  private boolean hasDeadline;
  private long timeoutNanos;
  
  public Timeout() {}
  
  public Timeout clearDeadline()
  {
    hasDeadline = false;
    return this;
  }
  
  public Timeout clearTimeout()
  {
    timeoutNanos = 0L;
    return this;
  }
  
  public long deadlineNanoTime()
  {
    if (hasDeadline) {
      return deadlineNanoTime;
    }
    throw new IllegalStateException("No deadline");
  }
  
  public Timeout deadlineNanoTime(long paramLong)
  {
    hasDeadline = true;
    deadlineNanoTime = paramLong;
    return this;
  }
  
  public boolean hasDeadline()
  {
    return hasDeadline;
  }
  
  public void throwIfReached()
  {
    if (!Thread.interrupted())
    {
      if (hasDeadline)
      {
        if (deadlineNanoTime - System.nanoTime() > 0L) {
          return;
        }
        throw new InterruptedIOException("deadline reached");
      }
    }
    else {
      throw new InterruptedIOException("thread interrupted");
    }
  }
  
  public Timeout timeout(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong >= 0L)
    {
      if (paramTimeUnit != null)
      {
        timeoutNanos = paramTimeUnit.toNanos(paramLong);
        return this;
      }
      throw new IllegalArgumentException("unit == null");
    }
    paramTimeUnit = new StringBuilder();
    paramTimeUnit.append("timeout < 0: ");
    paramTimeUnit.append(paramLong);
    throw new IllegalArgumentException(paramTimeUnit.toString());
  }
  
  public long timeoutNanos()
  {
    return timeoutNanos;
  }
}
