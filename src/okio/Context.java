package okio;

import f.a;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

public class Context
  extends Timeout
{
  private static final long b = TimeUnit.MILLISECONDS.toNanos(i);
  static Context c;
  private static final long i = TimeUnit.SECONDS.toMillis(60L);
  private Context a;
  private long d;
  private boolean inQueue;
  
  public Context() {}
  
  static Context a()
  {
    Context localContext = ca;
    if (localContext == null)
    {
      l1 = System.nanoTime();
      a.class.wait(i);
      if ((ca == null) && (System.nanoTime() - l1 >= b)) {
        return c;
      }
      return null;
    }
    long l1 = localContext.newUTF8(System.nanoTime());
    if (l1 > 0L)
    {
      long l2 = l1 / 1000000L;
      a.class.wait(l2, (int)(l1 - 1000000L * l2));
      return null;
    }
    ca = a;
    a = null;
    return localContext;
  }
  
  private static void a(Context paramContext, long paramLong, boolean paramBoolean)
  {
    try
    {
      if (c == null)
      {
        c = new Context();
        new SqlTileWriter.1().start();
      }
      long l = System.nanoTime();
      if ((paramLong != 0L) && (paramBoolean))
      {
        d = (Math.min(paramLong, paramContext.deadlineNanoTime() - l) + l);
      }
      else if (paramLong != 0L)
      {
        d = (l + paramLong);
      }
      else
      {
        if (!paramBoolean) {
          break label174;
        }
        d = paramContext.deadlineNanoTime();
      }
      paramLong = paramContext.newUTF8(l);
      for (Context localContext = c; (a != null) && (paramLong >= a.newUTF8(l)); localContext = a) {}
      a = a;
      a = paramContext;
      if (localContext == c) {
        a.class.notify();
      }
      return;
      label174:
      throw new AssertionError();
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  private static boolean a(Context paramContext)
  {
    try
    {
      for (Context localContext = c; localContext != null; localContext = a) {
        if (a == paramContext)
        {
          a = a;
          a = null;
          return false;
        }
      }
      return true;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  private long newUTF8(long paramLong)
  {
    return d - paramLong;
  }
  
  public final void enter()
  {
    if (!inQueue)
    {
      long l = timeoutNanos();
      boolean bool = hasDeadline();
      if ((l == 0L) && (!bool)) {
        return;
      }
      inQueue = true;
      a(this, l, bool);
      return;
    }
    throw new IllegalStateException("Unbalanced enter/exit");
  }
  
  final IOException exit(IOException paramIOException)
  {
    if (!exit()) {
      return paramIOException;
    }
    return newTimeoutException(paramIOException);
  }
  
  final void exit(boolean paramBoolean)
  {
    if (exit())
    {
      if (!paramBoolean) {
        return;
      }
      throw newTimeoutException(null);
    }
  }
  
  public final boolean exit()
  {
    if (!inQueue) {
      return false;
    }
    inQueue = false;
    return a(this);
  }
  
  protected IOException newTimeoutException(IOException paramIOException)
  {
    InterruptedIOException localInterruptedIOException = new InterruptedIOException("timeout");
    if (paramIOException != null) {
      localInterruptedIOException.initCause(paramIOException);
    }
    return localInterruptedIOException;
  }
  
  public final Sink sink(Sink paramSink)
  {
    return new AsyncTimeout.1(this, paramSink);
  }
  
  public final Source source(Source paramSource)
  {
    return new AsyncTimeout.2(this, paramSource);
  }
  
  protected void timedOut() {}
}
