package okhttp;

import e.x;
import e.x.a;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp.internal.Util;

public final class Dispatcher
{
  private Runnable cancelCallback;
  private ExecutorService executorService;
  private int maxRequests = 64;
  private int maxRequestsPerHost = 5;
  private final Deque<x.a> readyAsyncCalls = new ArrayDeque();
  private final Deque<x.a> runningAsyncCalls = new ArrayDeque();
  private final Deque<x> runningSyncCalls = new ArrayDeque();
  
  public Dispatcher() {}
  
  private void cancel(Deque paramDeque, Object paramObject, boolean paramBoolean)
  {
    try
    {
      if (paramDeque.remove(paramObject))
      {
        if (paramBoolean) {
          promoteCalls();
        }
        int i = finished();
        paramDeque = cancelCallback;
        if ((i == 0) && (paramDeque != null)) {
          paramDeque.run();
        }
      }
      else
      {
        throw new AssertionError("Call wasn't in-flight!");
      }
    }
    catch (Throwable paramDeque)
    {
      throw paramDeque;
    }
  }
  
  private void promoteCalls()
  {
    if (runningAsyncCalls.size() >= maxRequests) {
      return;
    }
    if (readyAsyncCalls.isEmpty()) {
      return;
    }
    Iterator localIterator = readyAsyncCalls.iterator();
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      Call.AsyncCall localAsyncCall = (Call.AsyncCall)localIterator.next();
      if (runningCallsForHost(localAsyncCall) < maxRequestsPerHost)
      {
        localIterator.remove();
        runningAsyncCalls.add(localAsyncCall);
        getExecutorService().execute(localAsyncCall);
      }
    } while (runningAsyncCalls.size() < maxRequests);
  }
  
  private int runningCallsForHost(Call.AsyncCall paramAsyncCall)
  {
    int i = 0;
    Iterator localIterator = runningAsyncCalls.iterator();
    while (localIterator.hasNext())
    {
      Call.AsyncCall localAsyncCall = (Call.AsyncCall)localIterator.next();
      if ((!nextm) && (localAsyncCall.host().equals(paramAsyncCall.host()))) {
        i += 1;
      }
    }
    return i;
  }
  
  void executed(ClassWriter paramClassWriter)
  {
    try
    {
      runningSyncCalls.add(paramClassWriter);
      return;
    }
    catch (Throwable paramClassWriter)
    {
      throw paramClassWriter;
    }
  }
  
  public int finished()
  {
    try
    {
      int i = runningAsyncCalls.size();
      int j = runningSyncCalls.size();
      return i + j;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  void finished(Call.AsyncCall paramAsyncCall)
  {
    cancel(runningAsyncCalls, paramAsyncCall, true);
  }
  
  void finished(ClassWriter paramClassWriter)
  {
    cancel(runningSyncCalls, paramClassWriter, false);
  }
  
  public ExecutorService getExecutorService()
  {
    try
    {
      if (executorService == null) {
        executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Dispatcher", false));
      }
      ExecutorService localExecutorService = executorService;
      return localExecutorService;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
}
