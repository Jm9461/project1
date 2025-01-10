package okhttp.internal.http;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.List;
import okhttp.Address;
import okhttp.HttpHost;
import okhttp.Map;
import okhttp.Route;
import okhttp.URI;
import okhttp.internal.Internal;
import okhttp.internal.Util;
import okhttp.internal.httpclient.HttpStream;
import okhttp.internal.spdy.AsyncSpdyConnection;
import okhttp.internal.spdy.DatagramChannelWrapper;
import okhttp.internal.spdy.ErrorCode;

public final class StreamAllocation
{
  public final Address address;
  public final Map buffer;
  private boolean canceled;
  private RealConnection connection;
  private final okhttp.ConnectionPool connectionPool;
  private boolean host;
  private final Object reader;
  private boolean released;
  private Route route;
  private final RouteSelector routeSelector;
  private Iterator source;
  private int state;
  private HttpStream stream;
  public final HttpHost this$0;
  
  public StreamAllocation(okhttp.ConnectionPool paramConnectionPool, Address paramAddress, Map paramMap, HttpHost paramHttpHost, Object paramObject)
  {
    connectionPool = paramConnectionPool;
    address = paramAddress;
    buffer = paramMap;
    this$0 = paramHttpHost;
    routeSelector = new RouteSelector(paramAddress, routeDatabase(), paramMap, paramHttpHost);
    reader = paramObject;
  }
  
  private RealConnection connect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    int j = 0;
    Object localObject3 = null;
    List localList = null;
    Object localObject5 = connectionPool;
    for (;;)
    {
      int k;
      try
      {
        if (!released)
        {
          if (stream == null)
          {
            if (!canceled)
            {
              Object localObject1 = connection;
              Socket localSocket = deallocate();
              if (connection != null)
              {
                localObject3 = connection;
                localObject1 = null;
              }
              Object localObject4 = localObject1;
              if (!host) {
                localObject4 = null;
              }
              int i = j;
              Object localObject2 = localObject3;
              localObject1 = localList;
              if (localObject3 == null)
              {
                Internal.instance.get(connectionPool, address, this, null);
                if (connection != null)
                {
                  i = 1;
                  localObject2 = connection;
                  localObject1 = localList;
                }
                else
                {
                  localObject1 = route;
                  localObject2 = localObject3;
                  i = j;
                }
              }
              Util.closeQuietly(localSocket);
              if (localObject4 != null) {
                this$0.toString(buffer, (URI)localObject4);
              }
              if (i != 0) {
                this$0.put(buffer, (URI)localObject2);
              }
              if (localObject2 != null) {
                return localObject2;
              }
              if (localObject1 == null)
              {
                localObject3 = source;
                if ((localObject3 == null) || (!((Iterator)localObject3).hasNext()))
                {
                  source = routeSelector.next();
                  k = 1;
                  continue;
                }
              }
              k = 0;
              localObject4 = connectionPool;
              try
              {
                if (!canceled)
                {
                  j = i;
                  localObject3 = localObject2;
                  if (k != 0)
                  {
                    localList = source.getResult();
                    k = 0;
                    int m = localList.size();
                    j = i;
                    localObject3 = localObject2;
                    if (k < m)
                    {
                      localObject5 = (Route)localList.get(k);
                      Internal.instance.get(connectionPool, address, this, (Route)localObject5);
                      if (connection == null) {
                        break label648;
                      }
                      j = 1;
                      localObject3 = connection;
                      route = ((Route)localObject5);
                    }
                  }
                  localObject2 = localObject3;
                  if (j == 0)
                  {
                    localObject2 = localObject1;
                    if (localObject1 == null) {
                      localObject2 = source.next();
                    }
                    route = ((Route)localObject2);
                    state = 0;
                    localObject1 = new RealConnection(connectionPool, (Route)localObject2);
                    localObject2 = localObject1;
                    release((RealConnection)localObject1, false);
                  }
                  if (j != 0)
                  {
                    this$0.put(buffer, (URI)localObject2);
                    return localObject2;
                  }
                  ((RealConnection)localObject2).connect(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean, buffer, this$0);
                  routeDatabase().connected(((RealConnection)localObject2).getRoute());
                  localObject3 = null;
                  localObject4 = connectionPool;
                  try
                  {
                    host = true;
                    Internal.instance.put(connectionPool, (RealConnection)localObject2);
                    localObject1 = localObject2;
                    if (((RealConnection)localObject2).connect())
                    {
                      localObject3 = Internal.instance.get(connectionPool, address, this);
                      localObject1 = connection;
                    }
                    Util.closeQuietly((Socket)localObject3);
                    this$0.put(buffer, (URI)localObject1);
                    return localObject1;
                  }
                  catch (Throwable localThrowable1)
                  {
                    throw localThrowable1;
                  }
                }
                throw new IOException("Canceled");
              }
              catch (Throwable localThrowable2)
              {
                throw localThrowable2;
              }
            }
            throw new IOException("Canceled");
          }
          throw new IllegalStateException("codec != null");
        }
        throw new IllegalStateException("released");
      }
      catch (Throwable localThrowable3)
      {
        throw localThrowable3;
      }
      label648:
      k += 1;
    }
  }
  
  private Socket deallocate()
  {
    RealConnection localRealConnection = connection;
    if ((localRealConnection != null) && (noNewStreams)) {
      return deallocate(false, false, true);
    }
    return null;
  }
  
  private Socket deallocate(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (paramBoolean3) {
      stream = null;
    }
    if (paramBoolean2) {
      released = true;
    }
    Object localObject2 = null;
    Object localObject1 = connection;
    if (localObject1 != null)
    {
      if (paramBoolean1) {
        noNewStreams = true;
      }
      if ((stream == null) && ((released) || (connection.noNewStreams)))
      {
        release(connection);
        localObject1 = localObject2;
        if (connection.allocations.isEmpty())
        {
          connection.idleAtNanos = System.nanoTime();
          localObject1 = localObject2;
          if (Internal.instance.connectionBecameIdle(connectionPool, connection)) {
            localObject1 = connection.socket();
          }
        }
        connection = null;
        return localObject1;
      }
    }
    return null;
  }
  
  private RealConnection findHealthyConnection(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2)
  {
    for (;;)
    {
      RealConnection localRealConnection = connect(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean1);
      okhttp.ConnectionPool localConnectionPool = connectionPool;
      try
      {
        if (successCount == 0) {
          return localRealConnection;
        }
        if (!localRealConnection.isHealthy(paramBoolean2))
        {
          connectionFailed();
          continue;
        }
        return localRealConnection;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
  }
  
  private void release(RealConnection paramRealConnection)
  {
    int i = 0;
    int j = allocations.size();
    while (i < j)
    {
      if (((Reference)allocations.get(i)).get() == this)
      {
        allocations.remove(i);
        return;
      }
      i += 1;
    }
    paramRealConnection = new IllegalStateException();
    throw paramRealConnection;
  }
  
  private RouteDatabase routeDatabase()
  {
    return Internal.instance.routeDatabase(connectionPool);
  }
  
  public HttpStream cancel()
  {
    okhttp.ConnectionPool localConnectionPool = connectionPool;
    try
    {
      HttpStream localHttpStream = stream;
      return localHttpStream;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  /* Error */
  public HttpStream connect(okhttp.OkHttpClient paramOkHttpClient, okhttp.Connection paramConnection, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_2
    //   1: invokeinterface 245 1 0
    //   6: istore 4
    //   8: aload_2
    //   9: invokeinterface 248 1 0
    //   14: istore 5
    //   16: aload_2
    //   17: invokeinterface 251 1 0
    //   22: istore 6
    //   24: aload_1
    //   25: invokevirtual 256	okhttp/OkHttpClient:open	()I
    //   28: istore 7
    //   30: aload_1
    //   31: invokevirtual 259	okhttp/OkHttpClient:followRedirects	()Z
    //   34: istore 8
    //   36: aload_0
    //   37: iload 4
    //   39: iload 5
    //   41: iload 6
    //   43: iload 7
    //   45: iload 8
    //   47: iload_3
    //   48: invokespecial 261	okhttp/internal/http/StreamAllocation:findHealthyConnection	(IIIIZZ)Lokhttp/internal/http/RealConnection;
    //   51: aload_1
    //   52: aload_2
    //   53: aload_0
    //   54: invokevirtual 265	okhttp/internal/http/RealConnection:newStream	(Lokhttp/OkHttpClient;Lokhttp/Connection;Lokhttp/internal/http/StreamAllocation;)Lokhttp/internal/httpclient/HttpStream;
    //   57: astore_2
    //   58: aload_0
    //   59: getfield 39	okhttp/internal/http/StreamAllocation:connectionPool	Lokhttp/ConnectionPool;
    //   62: astore_1
    //   63: aload_1
    //   64: monitorenter
    //   65: aload_0
    //   66: aload_2
    //   67: putfield 67	okhttp/internal/http/StreamAllocation:stream	Lokhttp/internal/httpclient/HttpStream;
    //   70: aload_1
    //   71: monitorexit
    //   72: aload_2
    //   73: areturn
    //   74: astore_2
    //   75: aload_1
    //   76: monitorexit
    //   77: aload_2
    //   78: athrow
    //   79: astore_1
    //   80: new 267	okhttp/internal/http/RouteException
    //   83: dup
    //   84: aload_1
    //   85: checkcast 167	java/io/IOException
    //   88: invokespecial 270	okhttp/internal/http/RouteException:<init>	(Ljava/io/IOException;)V
    //   91: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	this	StreamAllocation
    //   0	92	1	paramOkHttpClient	okhttp.OkHttpClient
    //   0	92	2	paramConnection	okhttp.Connection
    //   0	92	3	paramBoolean	boolean
    //   6	32	4	i	int
    //   14	26	5	j	int
    //   22	20	6	k	int
    //   28	16	7	m	int
    //   34	12	8	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   65	72	74	java/lang/Throwable
    //   75	77	74	java/lang/Throwable
    //   36	58	79	java/io/IOException
    //   77	79	79	java/io/IOException
  }
  
  public void connect(IOException paramIOException)
  {
    boolean bool1 = false;
    boolean bool2 = false;
    okhttp.ConnectionPool localConnectionPool = connectionPool;
    for (;;)
    {
      try
      {
        if ((paramIOException instanceof DatagramChannelWrapper))
        {
          paramIOException = (DatagramChannelWrapper)paramIOException;
          if (address == ErrorCode.REFUSED_STREAM) {
            state += 1;
          }
          if ((address == ErrorCode.REFUSED_STREAM) && (state <= 1)) {
            break label209;
          }
          bool1 = true;
          route = null;
          break label209;
        }
        if (connection == null) {
          break label209;
        }
        if (connection.connect())
        {
          bool1 = bool2;
          if (!(paramIOException instanceof AsyncSpdyConnection)) {}
        }
        else
        {
          bool2 = true;
          bool1 = bool2;
          if (connection.successCount == 0)
          {
            if ((route != null) && (paramIOException != null)) {
              routeSelector.connectFailed(route, paramIOException);
            }
            route = null;
            bool1 = bool2;
          }
        }
        paramIOException = connection;
        Socket localSocket = deallocate(bool1, false, true);
        if ((connection != null) || (!host)) {
          break label212;
        }
        Util.closeQuietly(localSocket);
        if (paramIOException != null)
        {
          this$0.toString(buffer, paramIOException);
          return;
        }
      }
      catch (Throwable paramIOException)
      {
        throw paramIOException;
      }
      return;
      label209:
      continue;
      label212:
      paramIOException = null;
    }
  }
  
  public boolean connect()
  {
    if (route == null)
    {
      Iterator localIterator = source;
      if (((localIterator == null) || (!localIterator.hasNext())) && (!routeSelector.hasNext())) {
        return false;
      }
    }
    return true;
  }
  
  public void connectionFailed()
  {
    okhttp.ConnectionPool localConnectionPool = connectionPool;
    try
    {
      RealConnection localRealConnection = connection;
      Socket localSocket = deallocate(true, false, false);
      if (connection != null) {
        localRealConnection = null;
      }
      Util.closeQuietly(localSocket);
      if (localRealConnection != null)
      {
        this$0.toString(buffer, localRealConnection);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public Socket deallocate(RealConnection paramRealConnection)
  {
    if ((stream == null) && (connection.allocations.size() == 1))
    {
      Reference localReference = (Reference)connection.allocations.get(0);
      Socket localSocket = deallocate(true, false, false);
      connection = paramRealConnection;
      allocations.add(localReference);
      return localSocket;
    }
    throw new IllegalStateException();
  }
  
  public RealConnection get()
  {
    try
    {
      RealConnection localRealConnection = connection;
      return localRealConnection;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void release()
  {
    okhttp.ConnectionPool localConnectionPool = connectionPool;
    try
    {
      RealConnection localRealConnection = connection;
      Socket localSocket = deallocate(false, true, false);
      if (connection != null) {
        localRealConnection = null;
      }
      Util.closeQuietly(localSocket);
      if (localRealConnection != null)
      {
        this$0.toString(buffer, localRealConnection);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void release(RealConnection paramRealConnection, boolean paramBoolean)
  {
    if (connection == null)
    {
      connection = paramRealConnection;
      host = paramBoolean;
      allocations.add(new ConnectionPool(this, reader));
      return;
    }
    throw new IllegalStateException();
  }
  
  public Route route()
  {
    return route;
  }
  
  public void streamFinished(boolean paramBoolean, HttpStream paramHttpStream, long paramLong, IOException paramIOException)
  {
    this$0.toString(buffer, paramLong);
    okhttp.ConnectionPool localConnectionPool = connectionPool;
    if (paramHttpStream != null) {}
    try
    {
      if (paramHttpStream == stream)
      {
        if (!paramBoolean)
        {
          paramHttpStream = connection;
          successCount += 1;
        }
        paramHttpStream = connection;
        Socket localSocket = deallocate(paramBoolean, false, true);
        if (connection != null) {
          paramHttpStream = null;
        }
        paramBoolean = released;
        Util.closeQuietly(localSocket);
        if (paramHttpStream != null) {
          this$0.toString(buffer, paramHttpStream);
        }
        if (paramIOException != null)
        {
          this$0.add(buffer, paramIOException);
          return;
        }
        if (paramBoolean) {
          this$0.append(buffer);
        }
      }
      else
      {
        paramIOException = new StringBuilder();
        paramIOException.append("expected ");
        paramIOException.append(stream);
        paramIOException.append(" but was ");
        paramIOException.append(paramHttpStream);
        throw new IllegalStateException(paramIOException.toString());
      }
    }
    catch (Throwable paramHttpStream)
    {
      throw paramHttpStream;
    }
  }
  
  public String toString()
  {
    RealConnection localRealConnection = get();
    if (localRealConnection != null) {
      return localRealConnection.toString();
    }
    return address.toString();
  }
}
