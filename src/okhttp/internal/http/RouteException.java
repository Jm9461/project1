package okhttp.internal.http;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class RouteException
  extends RuntimeException
{
  private static final Method addSuppressedExceptionMethod;
  private IOException lastException;
  
  static
  {
    Object localObject;
    try
    {
      Method localMethod = Throwable.class.getDeclaredMethod("addSuppressed", new Class[] { Throwable.class });
    }
    catch (Exception localException)
    {
      localObject = null;
    }
    addSuppressedExceptionMethod = localObject;
  }
  
  public RouteException(IOException paramIOException)
  {
    super(paramIOException);
    lastException = paramIOException;
  }
  
  private void addSuppressedIfPossible(IOException paramIOException1, IOException paramIOException2)
  {
    Method localMethod = addSuppressedExceptionMethod;
    if (localMethod != null) {
      try
      {
        localMethod.invoke(paramIOException1, new Object[] { paramIOException2 });
        return;
      }
      catch (IllegalAccessException paramIOException1) {}catch (InvocationTargetException paramIOException1) {}
    }
  }
  
  public void addConnectException(IOException paramIOException)
  {
    addSuppressedIfPossible(paramIOException, lastException);
    lastException = paramIOException;
  }
  
  public IOException getLastConnectException()
  {
    return lastException;
  }
}
