package okhttp;

import e.f0.f.c;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp.internal.Util;
import okhttp.internal.http.RealConnection;
import okhttp.internal.http.RouteDatabase;
import okhttp.internal.http.StreamAllocation;
import okhttp.internal.internal.Platform;

public final class ConnectionPool
{
  private static final Executor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp ConnectionPool", true));
  private final Runnable cleanupRunnable = new MonthByWeekFragment.2(this);
  boolean cleanupRunning;
  private final Deque<c> connections = new ArrayDeque();
  private final long keepAliveDurationNs;
  private final int maxIdleConnections;
  final RouteDatabase routeDatabase = new RouteDatabase();
  
  public ConnectionPool()
  {
    this(5, 5L, TimeUnit.MINUTES);
  }
  
  public ConnectionPool(int paramInt, long paramLong, TimeUnit paramTimeUnit)
  {
    maxIdleConnections = paramInt;
    keepAliveDurationNs = paramTimeUnit.toNanos(paramLong);
    if (paramLong > 0L) {
      return;
    }
    paramTimeUnit = new StringBuilder();
    paramTimeUnit.append("keepAliveDuration <= 0: ");
    paramTimeUnit.append(paramLong);
    throw new IllegalArgumentException(paramTimeUnit.toString());
  }
  
  private int pruneAndGetAllocationCount(RealConnection paramRealConnection, long paramLong)
  {
    List localList = allocations;
    int i = 0;
    while (i < localList.size())
    {
      Object localObject1 = (Reference)localList.get(i);
      if (((Reference)localObject1).get() != null)
      {
        i += 1;
      }
      else
      {
        localObject1 = (okhttp.internal.http.ConnectionPool)localObject1;
        Object localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("A connection to ");
        ((StringBuilder)localObject2).append(paramRealConnection.getRoute().getAddress().url());
        ((StringBuilder)localObject2).append(" was leaked. Did you forget to close a response body?");
        localObject2 = ((StringBuilder)localObject2).toString();
        Platform.get().get((String)localObject2, logger);
        localList.remove(i);
        noNewStreams = true;
        if (localList.isEmpty())
        {
          idleAtNanos = (paramLong - keepAliveDurationNs);
          return 0;
        }
      }
    }
    return localList.size();
  }
  
  long cleanup(long paramLong)
  {
    int j = 0;
    int i = 0;
    Object localObject = null;
    long l1 = Long.MIN_VALUE;
    try
    {
      Iterator localIterator = connections.iterator();
      while (localIterator.hasNext())
      {
        RealConnection localRealConnection = (RealConnection)localIterator.next();
        if (pruneAndGetAllocationCount(localRealConnection, paramLong) > 0)
        {
          j += 1;
        }
        else
        {
          int k = i + 1;
          long l2 = paramLong - idleAtNanos;
          i = k;
          if (l2 > l1)
          {
            l1 = l2;
            localObject = localRealConnection;
            i = k;
          }
        }
      }
      if ((l1 < keepAliveDurationNs) && (i <= maxIdleConnections))
      {
        if (i > 0)
        {
          paramLong = keepAliveDurationNs;
          return paramLong - l1;
        }
        if (j > 0)
        {
          paramLong = keepAliveDurationNs;
          return paramLong;
        }
        cleanupRunning = false;
        return -1L;
      }
      connections.remove(localObject);
      Util.closeQuietly(localObject.socket());
      return 0L;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  boolean connectionBecameIdle(RealConnection paramRealConnection)
  {
    if ((!noNewStreams) && (maxIdleConnections != 0))
    {
      notifyAll();
      return false;
    }
    connections.remove(paramRealConnection);
    return true;
  }
  
  Socket get(Address paramAddress, StreamAllocation paramStreamAllocation)
  {
    Iterator localIterator = connections.iterator();
    while (localIterator.hasNext())
    {
      RealConnection localRealConnection = (RealConnection)localIterator.next();
      if ((localRealConnection.get(paramAddress, null)) && (localRealConnection.connect()) && (localRealConnection != paramStreamAllocation.get())) {
        return paramStreamAllocation.deallocate(localRealConnection);
      }
    }
    return null;
  }
  
  RealConnection get(Address paramAddress, StreamAllocation paramStreamAllocation, Route paramRoute)
  {
    Iterator localIterator = connections.iterator();
    while (localIterator.hasNext())
    {
      RealConnection localRealConnection = (RealConnection)localIterator.next();
      if (localRealConnection.get(paramAddress, paramRoute))
      {
        paramStreamAllocation.release(localRealConnection, true);
        return localRealConnection;
      }
    }
    return null;
  }
  
  void put(RealConnection paramRealConnection)
  {
    if (!cleanupRunning)
    {
      cleanupRunning = true;
      executor.execute(cleanupRunnable);
    }
    connections.add(paramRealConnection);
  }
}
