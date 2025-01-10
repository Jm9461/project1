package okhttp.internal.spdy;

import e.f0.i.i;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp.internal.NamedRunnable;
import okhttp.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;

public final class SpdyConnection
  implements Closeable
{
  private static final ExecutorService $assertionsDisabled = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Http2Connection", true));
  long bytesLeftInWriteWindow;
  final boolean client;
  final Set<Integer> currentPushRequests = new LinkedHashSet();
  private final ScheduledExecutorService executor;
  final Http2.Writer frameWriter;
  final String hostname;
  int lastGoodStreamId;
  final FramedConnection.Listener listener;
  int nextStreamId;
  Settings okHttpSettings = new Settings();
  final Settings peerSettings = new Settings();
  private boolean pings;
  private final ExecutorService pushExecutor;
  final Reader readerRunnable;
  boolean receivedInitialPeerSettings = false;
  boolean shutdown;
  final Socket socket;
  final Map<Integer, i> streams = new LinkedHashMap();
  final PushObserver this$0;
  long unacknowledgedBytesRead = 0L;
  
  SpdyConnection(FramedConnection.Builder paramBuilder)
  {
    this$0 = pushObserver;
    boolean bool = client;
    client = bool;
    listener = listener;
    int i;
    if (bool) {
      i = 1;
    } else {
      i = 2;
    }
    nextStreamId = i;
    if (client) {
      nextStreamId += 2;
    }
    if (client) {
      okHttpSettings.set(7, 16777216);
    }
    hostname = hostname;
    executor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(Util.format("OkHttp %s Writer", new Object[] { hostname }), false));
    if (log != 0)
    {
      ScheduledExecutorService localScheduledExecutorService = executor;
      FramedConnection.Reader localReader = new FramedConnection.Reader(this, false, 0, 0);
      i = log;
      localScheduledExecutorService.scheduleAtFixedRate(localReader, i, i, TimeUnit.MILLISECONDS);
    }
    pushExecutor = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory(Util.format("OkHttp %s Push Observer", new Object[] { hostname }), true));
    peerSettings.set(7, 65535);
    peerSettings.set(5, 16384);
    bytesLeftInWriteWindow = peerSettings.get();
    socket = socket;
    frameWriter = new Http2.Writer(sink, client);
    readerRunnable = new Reader(new Http2.Reader(source, client));
  }
  
  private void execute()
  {
    ErrorCode localErrorCode1 = ErrorCode.PROTOCOL_ERROR;
    ErrorCode localErrorCode2 = ErrorCode.PROTOCOL_ERROR;
    try
    {
      close(localErrorCode1, localErrorCode2);
      return;
    }
    catch (IOException localIOException) {}
  }
  
  private SpdyStream newStream(int paramInt, List paramList, boolean paramBoolean)
  {
    boolean bool = paramBoolean ^ true;
    Http2.Writer localWriter = frameWriter;
    for (;;)
    {
      try
      {
        SpdyStream localSpdyStream;
        try
        {
          if (nextStreamId > 1073741823) {
            shutdown(ErrorCode.REFUSED_STREAM);
          }
          if (!shutdown)
          {
            int j = nextStreamId;
            nextStreamId += 2;
            localSpdyStream = new SpdyStream(j, this, bool, false, paramList);
            if ((!paramBoolean) || (bytesLeftInWriteWindow == 0L)) {
              break label221;
            }
            if (bytesLeftInWriteWindow != 0L) {
              break label215;
            }
            break label221;
            if (localSpdyStream.isOpen()) {
              streams.put(Integer.valueOf(j), localSpdyStream);
            }
            if (paramInt == 0)
            {
              frameWriter.synStream(bool, j, paramInt, paramList);
            }
            else
            {
              paramBoolean = client;
              if (paramBoolean) {
                continue;
              }
              frameWriter.pushPromise(paramInt, j, paramList);
            }
            if (i != 0)
            {
              frameWriter.flush();
              return localSpdyStream;
              throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
            }
          }
          else
          {
            throw new AsyncSpdyConnection();
          }
        }
        catch (Throwable paramList)
        {
          throw paramList;
        }
        return localSpdyStream;
      }
      catch (Throwable paramList)
      {
        throw paramList;
      }
      label215:
      int i = 0;
      continue;
      label221:
      i = 1;
    }
  }
  
  void addBytesToWriteWindow(long paramLong)
  {
    bytesLeftInWriteWindow += paramLong;
    if (paramLong > 0L) {
      notifyAll();
    }
  }
  
  public void close()
  {
    close(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
  }
  
  void close(ErrorCode paramErrorCode1, ErrorCode paramErrorCode2)
  {
    Object localObject = null;
    try
    {
      shutdown(paramErrorCode1);
      paramErrorCode1 = (ErrorCode)localObject;
    }
    catch (IOException paramErrorCode1) {}
    SpdyStream[] arrayOfSpdyStream = null;
    try
    {
      if (!streams.isEmpty())
      {
        arrayOfSpdyStream = (SpdyStream[])streams.values().toArray(new SpdyStream[streams.size()]);
        streams.clear();
      }
      localObject = paramErrorCode1;
      if (arrayOfSpdyStream != null)
      {
        int j = arrayOfSpdyStream.length;
        int i = 0;
        for (;;)
        {
          localObject = paramErrorCode1;
          if (i >= j) {
            break;
          }
          localObject = arrayOfSpdyStream[i];
          try
          {
            ((SpdyStream)localObject).close(paramErrorCode2);
            localObject = paramErrorCode1;
          }
          catch (IOException localIOException)
          {
            localObject = paramErrorCode1;
            if (paramErrorCode1 != null) {
              localObject = localIOException;
            }
          }
          i += 1;
          paramErrorCode1 = (ErrorCode)localObject;
        }
      }
      paramErrorCode1 = frameWriter;
      try
      {
        paramErrorCode1.close();
        paramErrorCode1 = (ErrorCode)localObject;
      }
      catch (IOException paramErrorCode2)
      {
        paramErrorCode1 = (ErrorCode)localObject;
        if (localObject == null) {
          paramErrorCode1 = paramErrorCode2;
        }
      }
      paramErrorCode2 = socket;
      try
      {
        paramErrorCode2.close();
      }
      catch (IOException paramErrorCode1) {}
      executor.shutdown();
      pushExecutor.shutdown();
      if (paramErrorCode1 == null) {
        return;
      }
      throw paramErrorCode1;
    }
    catch (Throwable paramErrorCode1)
    {
      throw paramErrorCode1;
    }
  }
  
  void close(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    if (!paramBoolean) {
      try
      {
        boolean bool = pings;
        pings = true;
        if (bool)
        {
          execute();
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    Http2.Writer localWriter = frameWriter;
    try
    {
      localWriter.ping(paramBoolean, paramInt1, paramInt2);
      return;
    }
    catch (IOException localIOException)
    {
      execute();
    }
  }
  
  public void connect()
  {
    connect(true);
  }
  
  void connect(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      frameWriter.connectionPreface();
      frameWriter.settings(okHttpSettings);
      int i = okHttpSettings.get();
      if (i != 65535) {
        frameWriter.windowUpdate(0, i - 65535);
      }
    }
    new Thread(readerRunnable).start();
  }
  
  public void flush()
  {
    frameWriter.flush();
  }
  
  SpdyStream getStream(int paramInt)
  {
    try
    {
      SpdyStream localSpdyStream = (SpdyStream)streams.get(Integer.valueOf(paramInt));
      return localSpdyStream;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public SpdyStream newStream(List paramList, boolean paramBoolean)
  {
    return newStream(0, paramList, paramBoolean);
  }
  
  void pushDataLater(int paramInt1, BufferedSource paramBufferedSource, int paramInt2, boolean paramBoolean)
  {
    Buffer localBuffer = new Buffer();
    paramBufferedSource.require(paramInt2);
    paramBufferedSource.read(localBuffer, paramInt2);
    if (localBuffer.size() == paramInt2)
    {
      pushExecutor.execute(new SpdyConnection.6(this, "OkHttp %s Push Data[%s]", new Object[] { hostname, Integer.valueOf(paramInt1) }, paramInt1, localBuffer, paramInt2, paramBoolean));
      return;
    }
    paramBufferedSource = new StringBuilder();
    paramBufferedSource.append(localBuffer.size());
    paramBufferedSource.append(" != ");
    paramBufferedSource.append(paramInt2);
    throw new IOException(paramBufferedSource.toString());
  }
  
  void pushHeadersLater(int paramInt, List paramList, boolean paramBoolean)
  {
    ExecutorService localExecutorService = pushExecutor;
    String str = hostname;
    try
    {
      localExecutorService.execute(new SpdyConnection.5(this, "OkHttp %s Push Headers[%s]", new Object[] { str, Integer.valueOf(paramInt) }, paramInt, paramList, paramBoolean));
      return;
    }
    catch (RejectedExecutionException paramList) {}
  }
  
  /* Error */
  void pushRequestLater(int paramInt, List paramList)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 108	okhttp/internal/spdy/SpdyConnection:currentPushRequests	Ljava/util/Set;
    //   6: iload_1
    //   7: invokestatic 252	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   10: invokeinterface 425 2 0
    //   15: ifeq +14 -> 29
    //   18: aload_0
    //   19: iload_1
    //   20: getstatic 219	okhttp/internal/spdy/ErrorCode:PROTOCOL_ERROR	Lokhttp/internal/spdy/ErrorCode;
    //   23: invokevirtual 429	okhttp/internal/spdy/SpdyConnection:writeSynResetLater	(ILokhttp/internal/spdy/ErrorCode;)V
    //   26: aload_0
    //   27: monitorexit
    //   28: return
    //   29: aload_0
    //   30: getfield 108	okhttp/internal/spdy/SpdyConnection:currentPushRequests	Ljava/util/Set;
    //   33: iload_1
    //   34: invokestatic 252	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   37: invokeinterface 432 2 0
    //   42: pop
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_0
    //   46: getfield 164	okhttp/internal/spdy/SpdyConnection:pushExecutor	Ljava/util/concurrent/ExecutorService;
    //   49: astore_3
    //   50: aload_0
    //   51: getfield 129	okhttp/internal/spdy/SpdyConnection:hostname	Ljava/lang/String;
    //   54: astore 4
    //   56: aload_3
    //   57: new 434	okhttp/internal/spdy/SpdyConnection$4
    //   60: dup
    //   61: aload_0
    //   62: ldc_w 436
    //   65: iconst_2
    //   66: anewarray 4	java/lang/Object
    //   69: dup
    //   70: iconst_0
    //   71: aload 4
    //   73: aastore
    //   74: dup
    //   75: iconst_1
    //   76: iload_1
    //   77: invokestatic 252	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   80: aastore
    //   81: iload_1
    //   82: aload_2
    //   83: invokespecial 439	okhttp/internal/spdy/SpdyConnection$4:<init>	(Lokhttp/internal/spdy/SpdyConnection;Ljava/lang/String;[Ljava/lang/Object;ILjava/util/List;)V
    //   86: invokeinterface 386 2 0
    //   91: return
    //   92: astore_2
    //   93: return
    //   94: astore_2
    //   95: aload_0
    //   96: monitorexit
    //   97: aload_2
    //   98: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	99	0	this	SpdyConnection
    //   0	99	1	paramInt	int
    //   0	99	2	paramList	List
    //   49	8	3	localExecutorService	ExecutorService
    //   54	18	4	str	String
    // Exception table:
    //   from	to	target	type
    //   56	91	92	java/util/concurrent/RejectedExecutionException
    //   2	28	94	java/lang/Throwable
    //   29	45	94	java/lang/Throwable
    //   95	97	94	java/lang/Throwable
  }
  
  void pushResetLater(int paramInt, ErrorCode paramErrorCode)
  {
    pushExecutor.execute(new SpdyConnection.7(this, "OkHttp %s Push Reset[%s]", new Object[] { hostname, Integer.valueOf(paramInt) }, paramInt, paramErrorCode));
  }
  
  boolean pushedStream(int paramInt)
  {
    return (paramInt != 0) && ((paramInt & 0x1) == 0);
  }
  
  public int read()
  {
    try
    {
      int i = peerSettings.getMaxConcurrentStreams(Integer.MAX_VALUE);
      return i;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  SpdyStream removeStream(int paramInt)
  {
    try
    {
      SpdyStream localSpdyStream = (SpdyStream)streams.remove(Integer.valueOf(paramInt));
      notifyAll();
      return localSpdyStream;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  /* Error */
  public void shutdown(ErrorCode paramErrorCode)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 185	okhttp/internal/spdy/SpdyConnection:frameWriter	Lokhttp/internal/spdy/Http2$Writer;
    //   4: astore_3
    //   5: aload_3
    //   6: monitorenter
    //   7: aload_0
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield 236	okhttp/internal/spdy/SpdyConnection:shutdown	Z
    //   13: ifeq +8 -> 21
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_3
    //   19: monitorexit
    //   20: return
    //   21: aload_0
    //   22: iconst_1
    //   23: putfield 236	okhttp/internal/spdy/SpdyConnection:shutdown	Z
    //   26: aload_0
    //   27: getfield 459	okhttp/internal/spdy/SpdyConnection:lastGoodStreamId	I
    //   30: istore_2
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_0
    //   34: getfield 185	okhttp/internal/spdy/SpdyConnection:frameWriter	Lokhttp/internal/spdy/Http2$Writer;
    //   37: iload_2
    //   38: aload_1
    //   39: getstatic 463	okhttp/internal/Util:EMPTY_BYTE_ARRAY	[B
    //   42: invokevirtual 467	okhttp/internal/spdy/Http2$Writer:goAway	(ILokhttp/internal/spdy/ErrorCode;[B)V
    //   45: aload_3
    //   46: monitorexit
    //   47: return
    //   48: astore_1
    //   49: aload_0
    //   50: monitorexit
    //   51: aload_1
    //   52: athrow
    //   53: astore_1
    //   54: aload_3
    //   55: monitorexit
    //   56: aload_1
    //   57: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	58	0	this	SpdyConnection
    //   0	58	1	paramErrorCode	ErrorCode
    //   30	8	2	i	int
    //   4	51	3	localWriter	Http2.Writer
    // Exception table:
    //   from	to	target	type
    //   9	18	48	java/lang/Throwable
    //   21	33	48	java/lang/Throwable
    //   49	51	48	java/lang/Throwable
    //   7	9	53	java/lang/Throwable
    //   18	20	53	java/lang/Throwable
    //   33	47	53	java/lang/Throwable
    //   51	53	53	java/lang/Throwable
    //   54	56	53	java/lang/Throwable
  }
  
  public boolean shutdown()
  {
    try
    {
      boolean bool = shutdown;
      return bool;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void writeData(int paramInt, boolean paramBoolean, Buffer paramBuffer, long paramLong)
  {
    long l = paramLong;
    if (paramLong == 0L)
    {
      frameWriter.data(paramBoolean, paramInt, paramBuffer, 0);
      return;
    }
    for (;;)
    {
      if (l <= 0L) {
        return;
      }
      for (;;)
      {
        try
        {
          if (bytesLeftInWriteWindow <= 0L) {
            localObject = streams;
          }
        }
        catch (Throwable paramBuffer)
        {
          Object localObject;
          boolean bool;
          int i;
          throw paramBuffer;
        }
        try
        {
          bool = ((Map)localObject).containsKey(Integer.valueOf(paramInt));
          if (bool)
          {
            wait();
          }
          else
          {
            paramBuffer = new IOException("stream closed");
            throw paramBuffer;
          }
        }
        catch (InterruptedException paramBuffer)
        {
          throw new InterruptedIOException();
        }
      }
      i = Math.min((int)Math.min(l, bytesLeftInWriteWindow), frameWriter.maxDataLength());
      bytesLeftInWriteWindow -= i;
      l -= i;
      localObject = frameWriter;
      if ((paramBoolean) && (l == 0L)) {
        bool = true;
      } else {
        bool = false;
      }
      ((Http2.Writer)localObject).data(bool, paramInt, paramBuffer, i);
    }
  }
  
  void writeSynReset(int paramInt, ErrorCode paramErrorCode)
  {
    frameWriter.rstStream(paramInt, paramErrorCode);
  }
  
  void writeSynResetLater(int paramInt, ErrorCode paramErrorCode)
  {
    ScheduledExecutorService localScheduledExecutorService = executor;
    String str = hostname;
    try
    {
      localScheduledExecutorService.execute(new SpdyConnection.1(this, "OkHttp %s stream %d", new Object[] { str, Integer.valueOf(paramInt) }, paramInt, paramErrorCode));
      return;
    }
    catch (RejectedExecutionException paramErrorCode) {}
  }
  
  void writeWindowUpdateLater(int paramInt, long paramLong)
  {
    ScheduledExecutorService localScheduledExecutorService = executor;
    String str = hostname;
    try
    {
      localScheduledExecutorService.execute(new SpdyConnection.2(this, "OkHttp Window Update %s stream %d", new Object[] { str, Integer.valueOf(paramInt) }, paramInt, paramLong));
      return;
    }
    catch (RejectedExecutionException localRejectedExecutionException) {}
  }
  
  class Reader
    extends NamedRunnable
    implements FrameReader.Handler
  {
    final Http2.Reader frameReader;
    
    Reader(Http2.Reader paramReader)
    {
      super(new Object[] { hostname });
      frameReader = paramReader;
    }
    
    private void settings(Settings paramSettings)
    {
      Object localObject = SpdyConnection.this;
      try
      {
        localObject = SpdyConnection.access$getExecutor((SpdyConnection)localObject);
        String str = hostname;
        ((ScheduledExecutorService)localObject).execute(new g.j.c(this, "OkHttp %s ACK Settings", new Object[] { str }, paramSettings));
        return;
      }
      catch (RejectedExecutionException paramSettings) {}
    }
    
    public void data(boolean paramBoolean, int paramInt1, BufferedSource paramBufferedSource, int paramInt2)
    {
      if (pushedStream(paramInt1))
      {
        pushDataLater(paramInt1, paramBufferedSource, paramInt2, paramBoolean);
        return;
      }
      SpdyStream localSpdyStream = getStream(paramInt1);
      if (localSpdyStream == null)
      {
        writeSynResetLater(paramInt1, ErrorCode.PROTOCOL_ERROR);
        paramBufferedSource.skip(paramInt2);
        return;
      }
      localSpdyStream.receiveData(paramBufferedSource, paramInt2);
      if (paramBoolean) {
        localSpdyStream.receiveFin();
      }
    }
    
    /* Error */
    protected void execute()
    {
      // Byte code:
      //   0: getstatic 98	okhttp/internal/spdy/ErrorCode:INTERNAL_ERROR	Lokhttp/internal/spdy/ErrorCode;
      //   3: astore_3
      //   4: getstatic 98	okhttp/internal/spdy/ErrorCode:INTERNAL_ERROR	Lokhttp/internal/spdy/ErrorCode;
      //   7: astore 4
      //   9: aload_3
      //   10: astore_2
      //   11: aload_0
      //   12: getfield 29	okhttp/internal/spdy/SpdyConnection$Reader:frameReader	Lokhttp/internal/spdy/Http2$Reader;
      //   15: astore 5
      //   17: aload_3
      //   18: astore_2
      //   19: aload 5
      //   21: aload_0
      //   22: invokevirtual 104	okhttp/internal/spdy/Http2$Reader:readConnectionPreface	(Lokhttp/internal/spdy/FrameReader$Handler;)V
      //   25: aload_0
      //   26: getfield 29	okhttp/internal/spdy/SpdyConnection$Reader:frameReader	Lokhttp/internal/spdy/Http2$Reader;
      //   29: astore 5
      //   31: aload_3
      //   32: astore_2
      //   33: aload 5
      //   35: iconst_0
      //   36: aload_0
      //   37: invokevirtual 108	okhttp/internal/spdy/Http2$Reader:nextFrame	(ZLokhttp/internal/spdy/FrameReader$Handler;)Z
      //   40: istore_1
      //   41: iload_1
      //   42: ifeq +6 -> 48
      //   45: goto -20 -> 25
      //   48: aload_3
      //   49: astore_2
      //   50: getstatic 111	okhttp/internal/spdy/ErrorCode:NO_ERROR	Lokhttp/internal/spdy/ErrorCode;
      //   53: astore_3
      //   54: aload_3
      //   55: astore_2
      //   56: getstatic 114	okhttp/internal/spdy/ErrorCode:CANCEL	Lokhttp/internal/spdy/ErrorCode;
      //   59: astore 5
      //   61: aload_0
      //   62: getfield 16	okhttp/internal/spdy/SpdyConnection$Reader:this$0	Lokhttp/internal/spdy/SpdyConnection;
      //   65: astore_2
      //   66: aload_2
      //   67: aload_3
      //   68: aload 5
      //   70: invokevirtual 118	okhttp/internal/spdy/SpdyConnection:close	(Lokhttp/internal/spdy/ErrorCode;Lokhttp/internal/spdy/ErrorCode;)V
      //   73: goto +37 -> 110
      //   76: astore_2
      //   77: goto +37 -> 114
      //   80: astore_3
      //   81: goto +41 -> 122
      //   84: astore_2
      //   85: aload_3
      //   86: astore_2
      //   87: getstatic 71	okhttp/internal/spdy/ErrorCode:PROTOCOL_ERROR	Lokhttp/internal/spdy/ErrorCode;
      //   90: astore_3
      //   91: aload_3
      //   92: astore_2
      //   93: getstatic 71	okhttp/internal/spdy/ErrorCode:PROTOCOL_ERROR	Lokhttp/internal/spdy/ErrorCode;
      //   96: astore 5
      //   98: aload_0
      //   99: getfield 16	okhttp/internal/spdy/SpdyConnection$Reader:this$0	Lokhttp/internal/spdy/SpdyConnection;
      //   102: astore_2
      //   103: aload_2
      //   104: aload_3
      //   105: aload 5
      //   107: invokevirtual 118	okhttp/internal/spdy/SpdyConnection:close	(Lokhttp/internal/spdy/ErrorCode;Lokhttp/internal/spdy/ErrorCode;)V
      //   110: goto +4 -> 114
      //   113: astore_2
      //   114: aload_0
      //   115: getfield 29	okhttp/internal/spdy/SpdyConnection$Reader:frameReader	Lokhttp/internal/spdy/Http2$Reader;
      //   118: invokestatic 124	okhttp/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
      //   121: return
      //   122: aload_0
      //   123: getfield 16	okhttp/internal/spdy/SpdyConnection$Reader:this$0	Lokhttp/internal/spdy/SpdyConnection;
      //   126: astore 5
      //   128: aload 5
      //   130: aload_2
      //   131: aload 4
      //   133: invokevirtual 118	okhttp/internal/spdy/SpdyConnection:close	(Lokhttp/internal/spdy/ErrorCode;Lokhttp/internal/spdy/ErrorCode;)V
      //   136: goto +4 -> 140
      //   139: astore_2
      //   140: aload_0
      //   141: getfield 29	okhttp/internal/spdy/SpdyConnection$Reader:frameReader	Lokhttp/internal/spdy/Http2$Reader;
      //   144: invokestatic 124	okhttp/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
      //   147: goto +3 -> 150
      //   150: aload_3
      //   151: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	152	0	this	Reader
      //   40	2	1	bool	boolean
      //   10	57	2	localObject1	Object
      //   76	1	2	localIOException1	IOException
      //   84	1	2	localIOException2	IOException
      //   86	18	2	localObject2	Object
      //   113	18	2	localIOException3	IOException
      //   139	1	2	localIOException4	IOException
      //   3	65	3	localErrorCode1	ErrorCode
      //   80	6	3	localThrowable	Throwable
      //   90	61	3	localErrorCode2	ErrorCode
      //   7	125	4	localErrorCode3	ErrorCode
      //   15	114	5	localObject3	Object
      // Exception table:
      //   from	to	target	type
      //   66	73	76	java/io/IOException
      //   11	17	80	java/lang/Throwable
      //   19	25	80	java/lang/Throwable
      //   33	41	80	java/lang/Throwable
      //   50	54	80	java/lang/Throwable
      //   56	61	80	java/lang/Throwable
      //   87	91	80	java/lang/Throwable
      //   93	98	80	java/lang/Throwable
      //   19	25	84	java/io/IOException
      //   33	41	84	java/io/IOException
      //   103	110	113	java/io/IOException
      //   128	136	139	java/io/IOException
    }
    
    public void goAway(int paramInt, ErrorCode paramErrorCode, ByteString paramByteString)
    {
      paramByteString.size();
      paramErrorCode = SpdyConnection.this;
      try
      {
        paramByteString = (SpdyStream[])streams.values().toArray(new SpdyStream[streams.size()]);
        shutdown = true;
        int j = paramByteString.length;
        int i = 0;
        while (i < j)
        {
          paramErrorCode = paramByteString[i];
          if ((paramErrorCode.getId() > paramInt) && (paramErrorCode.isLocallyInitiated()))
          {
            paramErrorCode.receiveRstStream(ErrorCode.REFUSED_STREAM);
            removeStream(paramErrorCode.getId());
          }
          i += 1;
        }
        return;
      }
      catch (Throwable paramByteString)
      {
        throw paramByteString;
      }
    }
    
    public void headers(boolean paramBoolean, int paramInt1, int paramInt2, List paramList)
    {
      if (pushedStream(paramInt1))
      {
        pushHeadersLater(paramInt1, paramList, paramBoolean);
        return;
      }
      SpdyConnection localSpdyConnection = SpdyConnection.this;
      try
      {
        SpdyStream localSpdyStream = getStream(paramInt1);
        if (localSpdyStream == null)
        {
          if (shutdown) {
            return;
          }
          if (paramInt1 <= lastGoodStreamId) {
            return;
          }
          if (paramInt1 % 2 == nextStreamId % 2) {
            return;
          }
          paramList = new SpdyStream(paramInt1, SpdyConnection.this, false, paramBoolean, paramList);
          lastGoodStreamId = paramInt1;
          streams.put(Integer.valueOf(paramInt1), paramList);
          SpdyConnection.access$getExecutor().execute(new g.j.a(this, "OkHttp %s stream %d", new Object[] { hostname, Integer.valueOf(paramInt1) }, paramList));
          return;
        }
        localSpdyStream.receiveHeaders(paramList);
        if (paramBoolean)
        {
          localSpdyStream.receiveFin();
          return;
        }
      }
      catch (Throwable paramList)
      {
        throw paramList;
      }
    }
    
    public void priority(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {}
    
    public void pushPromise(int paramInt1, int paramInt2, List paramList)
    {
      pushRequestLater(paramInt2, paramList);
    }
    
    public void rstStream(int paramInt, ErrorCode paramErrorCode)
    {
      if (pushedStream(paramInt))
      {
        pushResetLater(paramInt, paramErrorCode);
        return;
      }
      SpdyStream localSpdyStream = removeStream(paramInt);
      if (localSpdyStream != null) {
        localSpdyStream.receiveRstStream(paramErrorCode);
      }
    }
    
    public void settings() {}
    
    public void settings(boolean paramBoolean, int paramInt1, int paramInt2)
    {
      if (paramBoolean)
      {
        localObject = SpdyConnection.this;
        try
        {
          SpdyConnection.access$setReceivedInitialPeerSettings(SpdyConnection.this, false);
          notifyAll();
          return;
        }
        catch (Throwable localThrowable)
        {
          throw localThrowable;
        }
      }
      Object localObject = SpdyConnection.this;
      try
      {
        localObject = SpdyConnection.access$getExecutor((SpdyConnection)localObject);
        SpdyConnection localSpdyConnection = SpdyConnection.this;
        ((ScheduledExecutorService)localObject).execute(new FramedConnection.Reader(localSpdyConnection, true, paramInt1, paramInt2));
        return;
      }
      catch (RejectedExecutionException localRejectedExecutionException) {}
    }
    
    public void settings(boolean paramBoolean, Settings paramSettings)
    {
      long l2 = 0L;
      ExecutorService localExecutorService = null;
      SpdyConnection localSpdyConnection = SpdyConnection.this;
      try
      {
        int i = peerSettings.get();
        if (paramBoolean) {
          peerSettings.clear();
        }
        peerSettings.merge(paramSettings);
        settings(paramSettings);
        int j = peerSettings.get();
        long l1 = l2;
        paramSettings = localExecutorService;
        if (j != -1)
        {
          l1 = l2;
          paramSettings = localExecutorService;
          if (j != i)
          {
            l2 = j - i;
            if (!receivedInitialPeerSettings)
            {
              addBytesToWriteWindow(l2);
              receivedInitialPeerSettings = true;
            }
            l1 = l2;
            paramSettings = localExecutorService;
            if (!streams.isEmpty())
            {
              paramSettings = (SpdyStream[])streams.values().toArray(new SpdyStream[streams.size()]);
              l1 = l2;
            }
          }
        }
        localExecutorService = SpdyConnection.access$getExecutor();
        String str = hostname;
        i = 0;
        localExecutorService.execute(new g.j.b(this, "OkHttp %s settings", new Object[] { str }));
        if ((paramSettings != null) && (l1 != 0L))
        {
          j = paramSettings.length;
          for (;;)
          {
            if (i < j)
            {
              localExecutorService = paramSettings[i];
              try
              {
                localExecutorService.addBytesToWriteWindow(l1);
                i += 1;
              }
              catch (Throwable paramSettings)
              {
                throw paramSettings;
              }
            }
          }
        }
        return;
      }
      catch (Throwable paramSettings)
      {
        throw paramSettings;
      }
    }
    
    public void windowUpdate(int paramInt, long paramLong)
    {
      if (paramInt == 0)
      {
        localObject = SpdyConnection.this;
        try
        {
          SpdyConnection localSpdyConnection = SpdyConnection.this;
          bytesLeftInWriteWindow += paramLong;
          notifyAll();
          return;
        }
        catch (Throwable localThrowable1)
        {
          throw localThrowable1;
        }
      }
      Object localObject = getStream(paramInt);
      if (localObject != null) {
        try
        {
          ((SpdyStream)localObject).addBytesToWriteWindow(paramLong);
          return;
        }
        catch (Throwable localThrowable2)
        {
          throw localThrowable2;
        }
      }
    }
  }
}
