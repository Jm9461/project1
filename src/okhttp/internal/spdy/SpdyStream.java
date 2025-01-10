package okhttp.internal.spdy;

import e.f0.i.c;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;
import okio.BufferedSource;
import okio.Context;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class SpdyStream
{
  long bytesLeftInWriteWindow;
  final SpdyConnection connection;
  private List<c> data;
  ErrorCode file = null;
  final int id;
  final Buffer out = new Buffer(this);
  private boolean responseHeaders;
  final SpdyDataSink sink;
  private final SpdyDataSource source;
  final Buffer this$0 = new Buffer(this);
  long unacknowledgedBytesRead = 0L;
  
  SpdyStream(int paramInt, SpdyConnection paramSpdyConnection, boolean paramBoolean1, boolean paramBoolean2, List paramList)
  {
    if (paramSpdyConnection != null)
    {
      if (paramList != null)
      {
        id = paramInt;
        connection = paramSpdyConnection;
        bytesLeftInWriteWindow = peerSettings.get();
        source = new SpdyDataSource(okHttpSettings.get());
        sink = new SpdyDataSink();
        source.size = paramBoolean2;
        sink.closed = paramBoolean1;
        return;
      }
      throw new NullPointerException("requestHeaders == null");
    }
    throw new NullPointerException("connection == null");
  }
  
  private boolean closeInternal(ErrorCode paramErrorCode)
  {
    try
    {
      if (file != null) {
        return false;
      }
      if ((source.size) && (sink.closed)) {
        return false;
      }
      file = paramErrorCode;
      notifyAll();
      connection.removeStream(id);
      return true;
    }
    catch (Throwable paramErrorCode)
    {
      throw paramErrorCode;
    }
  }
  
  void addBytesToWriteWindow(long paramLong)
  {
    bytesLeftInWriteWindow += paramLong;
    if (paramLong > 0L) {
      notifyAll();
    }
  }
  
  void close()
  {
    for (;;)
    {
      try
      {
        if ((source.size) || (!source.closed)) {
          break label92;
        }
        if (sink.closed) {
          break label87;
        }
        if (!sink.finished) {
          break label92;
        }
      }
      catch (Throwable localThrowable)
      {
        boolean bool;
        throw localThrowable;
      }
      bool = isOpen();
      if (i != 0)
      {
        close(ErrorCode.CANCEL);
        return;
      }
      if (!bool)
      {
        connection.removeStream(id);
        return;
      }
      return;
      label87:
      int i = 1;
      continue;
      label92:
      i = 0;
    }
  }
  
  public void close(ErrorCode paramErrorCode)
  {
    if (!closeInternal(paramErrorCode)) {
      return;
    }
    connection.writeSynReset(id, paramErrorCode);
  }
  
  public void closeLater(ErrorCode paramErrorCode)
  {
    if (!closeInternal(paramErrorCode)) {
      return;
    }
    connection.writeSynResetLater(id, paramErrorCode);
  }
  
  void execute()
  {
    try
    {
      wait();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new InterruptedIOException();
    }
  }
  
  public int getId()
  {
    return id;
  }
  
  public Sink getSink()
  {
    try
    {
      if ((!responseHeaders) && (!isLocallyInitiated())) {
        throw new IllegalStateException("reply before requesting the sink");
      }
      return sink;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public Source getSource()
  {
    return source;
  }
  
  public boolean isLocallyInitiated()
  {
    int i;
    if ((id & 0x1) == 1) {
      i = 1;
    } else {
      i = 0;
    }
    return connection.client == i;
  }
  
  public boolean isOpen()
  {
    try
    {
      ErrorCode localErrorCode = file;
      if (localErrorCode != null) {
        return false;
      }
      if (((source.size) || (source.closed)) && ((sink.closed) || (sink.finished)))
      {
        boolean bool = responseHeaders;
        if (bool) {
          return false;
        }
      }
      return true;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public List read()
  {
    for (;;)
    {
      try
      {
        if (isLocallyInitiated()) {
          this$0.enter();
        }
      }
      catch (Throwable localThrowable2)
      {
        List localList1;
        List localList2;
        throw localThrowable2;
      }
      try
      {
        localList1 = data;
        if (localList1 == null)
        {
          try
          {
            if (file == null) {
              execute();
            }
          }
          catch (Throwable localThrowable1)
          {
            continue;
          }
        }
        else
        {
          this$0.close();
          localList2 = data;
          if (localList2 != null)
          {
            data = null;
            return localList2;
          }
          throw new DatagramChannelWrapper(file);
        }
      }
      catch (Throwable localThrowable3) {}
    }
    this$0.close();
    throw localList2;
    throw new IllegalStateException("servers cannot read response headers");
  }
  
  public Timeout readTimeout()
  {
    return this$0;
  }
  
  void receiveData(BufferedSource paramBufferedSource, int paramInt)
  {
    source.receive(paramBufferedSource, paramInt);
  }
  
  void receiveFin()
  {
    try
    {
      source.size = true;
      boolean bool = isOpen();
      notifyAll();
      if (!bool)
      {
        connection.removeStream(id);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  void receiveHeaders(List paramList)
  {
    boolean bool = true;
    try
    {
      responseHeaders = true;
      if (data == null)
      {
        data = paramList;
        bool = isOpen();
        notifyAll();
      }
      else
      {
        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll(data);
        localArrayList.add(null);
        localArrayList.addAll(paramList);
        data = localArrayList;
      }
      if (!bool)
      {
        connection.removeStream(id);
        return;
      }
    }
    catch (Throwable paramList)
    {
      throw paramList;
    }
  }
  
  void receiveRstStream(ErrorCode paramErrorCode)
  {
    try
    {
      if (file == null)
      {
        file = paramErrorCode;
        notifyAll();
      }
      return;
    }
    catch (Throwable paramErrorCode)
    {
      throw paramErrorCode;
    }
  }
  
  void write()
  {
    Object localObject = sink;
    if (!finished)
    {
      if (!closed)
      {
        localObject = file;
        if (localObject == null) {
          return;
        }
        throw new DatagramChannelWrapper((ErrorCode)localObject);
      }
      throw new IOException("stream finished");
    }
    throw new IOException("stream closed");
  }
  
  public Timeout writeTimeout()
  {
    return out;
  }
  
  final class SpdyDataSink
    implements Sink
  {
    boolean closed;
    boolean finished;
    private final okio.Buffer sendBuffer = new okio.Buffer();
    
    SpdyDataSink() {}
    
    /* Error */
    private void write(boolean paramBoolean)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   4: astore 7
      //   6: aload 7
      //   8: monitorenter
      //   9: aload_0
      //   10: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   13: getfield 36	okhttp/internal/spdy/SpdyStream:out	Lokhttp/internal/spdy/Buffer;
      //   16: invokevirtual 41	okio/Context:enter	()V
      //   19: aload_0
      //   20: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   23: getfield 45	okhttp/internal/spdy/SpdyStream:bytesLeftInWriteWindow	J
      //   26: lconst_0
      //   27: lcmp
      //   28: ifgt +37 -> 65
      //   31: aload_0
      //   32: getfield 47	okhttp/internal/spdy/SpdyStream$SpdyDataSink:closed	Z
      //   35: ifne +30 -> 65
      //   38: aload_0
      //   39: getfield 49	okhttp/internal/spdy/SpdyStream$SpdyDataSink:finished	Z
      //   42: ifne +23 -> 65
      //   45: aload_0
      //   46: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   49: getfield 53	okhttp/internal/spdy/SpdyStream:file	Lokhttp/internal/spdy/ErrorCode;
      //   52: ifnonnull +13 -> 65
      //   55: aload_0
      //   56: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   59: invokevirtual 56	okhttp/internal/spdy/SpdyStream:execute	()V
      //   62: goto -43 -> 19
      //   65: aload_0
      //   66: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   69: getfield 36	okhttp/internal/spdy/SpdyStream:out	Lokhttp/internal/spdy/Buffer;
      //   72: invokevirtual 61	okhttp/internal/spdy/Buffer:close	()V
      //   75: aload_0
      //   76: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   79: invokevirtual 63	okhttp/internal/spdy/SpdyStream:write	()V
      //   82: aload_0
      //   83: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   86: getfield 45	okhttp/internal/spdy/SpdyStream:bytesLeftInWriteWindow	J
      //   89: aload_0
      //   90: getfield 27	okhttp/internal/spdy/SpdyStream$SpdyDataSink:sendBuffer	Lokio/Buffer;
      //   93: invokevirtual 67	okio/Buffer:size	()J
      //   96: invokestatic 73	java/lang/Math:min	(JJ)J
      //   99: lstore_3
      //   100: aload_0
      //   101: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   104: astore 8
      //   106: aload 8
      //   108: aload 8
      //   110: getfield 45	okhttp/internal/spdy/SpdyStream:bytesLeftInWriteWindow	J
      //   113: lload_3
      //   114: lsub
      //   115: putfield 45	okhttp/internal/spdy/SpdyStream:bytesLeftInWriteWindow	J
      //   118: aload 7
      //   120: monitorexit
      //   121: aload_0
      //   122: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   125: getfield 36	okhttp/internal/spdy/SpdyStream:out	Lokhttp/internal/spdy/Buffer;
      //   128: invokevirtual 41	okio/Context:enter	()V
      //   131: aload_0
      //   132: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   135: getfield 77	okhttp/internal/spdy/SpdyStream:connection	Lokhttp/internal/spdy/SpdyConnection;
      //   138: astore 7
      //   140: aload_0
      //   141: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   144: getfield 81	okhttp/internal/spdy/SpdyStream:id	I
      //   147: istore_2
      //   148: iload_1
      //   149: ifeq +24 -> 173
      //   152: aload_0
      //   153: getfield 27	okhttp/internal/spdy/SpdyStream$SpdyDataSink:sendBuffer	Lokio/Buffer;
      //   156: invokevirtual 67	okio/Buffer:size	()J
      //   159: lstore 5
      //   161: lload_3
      //   162: lload 5
      //   164: lcmp
      //   165: ifne +8 -> 173
      //   168: iconst_1
      //   169: istore_1
      //   170: goto +5 -> 175
      //   173: iconst_0
      //   174: istore_1
      //   175: aload 7
      //   177: iload_2
      //   178: iload_1
      //   179: aload_0
      //   180: getfield 27	okhttp/internal/spdy/SpdyStream$SpdyDataSink:sendBuffer	Lokio/Buffer;
      //   183: lload_3
      //   184: invokevirtual 87	okhttp/internal/spdy/SpdyConnection:writeData	(IZLokio/Buffer;J)V
      //   187: aload_0
      //   188: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   191: getfield 36	okhttp/internal/spdy/SpdyStream:out	Lokhttp/internal/spdy/Buffer;
      //   194: invokevirtual 61	okhttp/internal/spdy/Buffer:close	()V
      //   197: return
      //   198: astore 7
      //   200: aload_0
      //   201: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   204: getfield 36	okhttp/internal/spdy/SpdyStream:out	Lokhttp/internal/spdy/Buffer;
      //   207: invokevirtual 61	okhttp/internal/spdy/Buffer:close	()V
      //   210: aload 7
      //   212: athrow
      //   213: astore 8
      //   215: aload_0
      //   216: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   219: getfield 36	okhttp/internal/spdy/SpdyStream:out	Lokhttp/internal/spdy/Buffer;
      //   222: invokevirtual 61	okhttp/internal/spdy/Buffer:close	()V
      //   225: aload 8
      //   227: athrow
      //   228: astore 8
      //   230: aload 7
      //   232: monitorexit
      //   233: goto +3 -> 236
      //   236: aload 8
      //   238: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	239	0	this	SpdyDataSink
      //   0	239	1	paramBoolean	boolean
      //   147	31	2	i	int
      //   99	85	3	l1	long
      //   159	4	5	l2	long
      //   4	172	7	localObject	Object
      //   198	33	7	localThrowable1	Throwable
      //   104	5	8	localSpdyStream	SpdyStream
      //   213	13	8	localThrowable2	Throwable
      //   228	9	8	localThrowable3	Throwable
      // Exception table:
      //   from	to	target	type
      //   131	148	198	java/lang/Throwable
      //   152	161	198	java/lang/Throwable
      //   175	187	198	java/lang/Throwable
      //   19	62	213	java/lang/Throwable
      //   9	19	228	java/lang/Throwable
      //   65	121	228	java/lang/Throwable
      //   215	228	228	java/lang/Throwable
      //   230	233	228	java/lang/Throwable
    }
    
    /* Error */
    public void close()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   4: astore_1
      //   5: aload_1
      //   6: monitorenter
      //   7: aload_0
      //   8: getfield 49	okhttp/internal/spdy/SpdyStream$SpdyDataSink:finished	Z
      //   11: ifeq +6 -> 17
      //   14: aload_1
      //   15: monitorexit
      //   16: return
      //   17: aload_1
      //   18: monitorexit
      //   19: aload_0
      //   20: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   23: getfield 91	okhttp/internal/spdy/SpdyStream:sink	Lokhttp/internal/spdy/SpdyStream$SpdyDataSink;
      //   26: getfield 47	okhttp/internal/spdy/SpdyStream$SpdyDataSink:closed	Z
      //   29: ifne +54 -> 83
      //   32: aload_0
      //   33: getfield 27	okhttp/internal/spdy/SpdyStream$SpdyDataSink:sendBuffer	Lokio/Buffer;
      //   36: invokevirtual 67	okio/Buffer:size	()J
      //   39: lconst_0
      //   40: lcmp
      //   41: ifle +23 -> 64
      //   44: aload_0
      //   45: getfield 27	okhttp/internal/spdy/SpdyStream$SpdyDataSink:sendBuffer	Lokio/Buffer;
      //   48: invokevirtual 67	okio/Buffer:size	()J
      //   51: lconst_0
      //   52: lcmp
      //   53: ifle +30 -> 83
      //   56: aload_0
      //   57: iconst_1
      //   58: invokespecial 93	okhttp/internal/spdy/SpdyStream$SpdyDataSink:write	(Z)V
      //   61: goto -17 -> 44
      //   64: aload_0
      //   65: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   68: astore_1
      //   69: aload_1
      //   70: getfield 77	okhttp/internal/spdy/SpdyStream:connection	Lokhttp/internal/spdy/SpdyConnection;
      //   73: aload_1
      //   74: getfield 81	okhttp/internal/spdy/SpdyStream:id	I
      //   77: iconst_1
      //   78: aconst_null
      //   79: lconst_0
      //   80: invokevirtual 87	okhttp/internal/spdy/SpdyConnection:writeData	(IZLokio/Buffer;J)V
      //   83: aload_0
      //   84: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   87: astore_1
      //   88: aload_1
      //   89: monitorenter
      //   90: aload_0
      //   91: iconst_1
      //   92: putfield 49	okhttp/internal/spdy/SpdyStream$SpdyDataSink:finished	Z
      //   95: aload_1
      //   96: monitorexit
      //   97: aload_0
      //   98: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   101: getfield 77	okhttp/internal/spdy/SpdyStream:connection	Lokhttp/internal/spdy/SpdyConnection;
      //   104: invokevirtual 96	okhttp/internal/spdy/SpdyConnection:flush	()V
      //   107: aload_0
      //   108: getfield 19	okhttp/internal/spdy/SpdyStream$SpdyDataSink:this$0	Lokhttp/internal/spdy/SpdyStream;
      //   111: invokevirtual 97	okhttp/internal/spdy/SpdyStream:close	()V
      //   114: return
      //   115: astore_2
      //   116: aload_1
      //   117: monitorexit
      //   118: aload_2
      //   119: athrow
      //   120: astore_2
      //   121: aload_1
      //   122: monitorexit
      //   123: goto +3 -> 126
      //   126: aload_2
      //   127: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	128	0	this	SpdyDataSink
      //   4	118	1	localSpdyStream	SpdyStream
      //   115	4	2	localThrowable1	Throwable
      //   120	7	2	localThrowable2	Throwable
      // Exception table:
      //   from	to	target	type
      //   90	97	115	java/lang/Throwable
      //   116	118	115	java/lang/Throwable
      //   7	16	120	java/lang/Throwable
      //   17	19	120	java/lang/Throwable
      //   121	123	120	java/lang/Throwable
    }
    
    public void flush()
    {
      SpdyStream localSpdyStream = SpdyStream.this;
      try
      {
        write();
        while (sendBuffer.size() > 0L)
        {
          write(false);
          connection.flush();
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    public Timeout timeout()
    {
      return out;
    }
    
    public void write(okio.Buffer paramBuffer, long paramLong)
    {
      sendBuffer.write(paramBuffer, paramLong);
      while (sendBuffer.size() >= 16384L) {
        write(false);
      }
    }
  }
  
  final class SpdyDataSource
    implements Source
  {
    boolean closed;
    private final long maxByteCount;
    private final okio.Buffer readBuffer = new okio.Buffer();
    private final okio.Buffer receiveBuffer = new okio.Buffer();
    boolean size;
    
    SpdyDataSource(long paramLong)
    {
      maxByteCount = paramLong;
    }
    
    private void checkNotClosed()
    {
      if (!closed)
      {
        ErrorCode localErrorCode = file;
        if (localErrorCode == null) {
          return;
        }
        throw new DatagramChannelWrapper(localErrorCode);
      }
      throw new IOException("stream closed");
    }
    
    private void read()
    {
      this$0.enter();
      try
      {
        for (;;)
        {
          long l = readBuffer.size();
          if (l != 0L) {
            break;
          }
          boolean bool = size;
          if (bool) {
            break;
          }
          bool = closed;
          if (bool) {
            break;
          }
          ErrorCode localErrorCode = file;
          if (localErrorCode != null) {
            break;
          }
          execute();
        }
        this$0.close();
        return;
      }
      catch (Throwable localThrowable)
      {
        this$0.close();
        throw localThrowable;
      }
    }
    
    public void close()
    {
      SpdyStream localSpdyStream = SpdyStream.this;
      try
      {
        closed = true;
        readBuffer.clear();
        notifyAll();
        SpdyStream.this.close();
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    public long read(okio.Buffer paramBuffer, long paramLong)
    {
      if (paramLong >= 0L)
      {
        Object localObject = SpdyStream.this;
        try
        {
          read();
          checkNotClosed();
          if (readBuffer.size() == 0L) {
            return -1L;
          }
          paramLong = readBuffer.read(paramBuffer, Math.min(paramLong, readBuffer.size()));
          paramBuffer = SpdyStream.this;
          unacknowledgedBytesRead += paramLong;
          if (unacknowledgedBytesRead >= connection.okHttpSettings.get() / 2)
          {
            connection.writeWindowUpdateLater(id, unacknowledgedBytesRead);
            unacknowledgedBytesRead = 0L;
          }
          paramBuffer = connection;
          try
          {
            localObject = connection;
            unacknowledgedBytesRead += paramLong;
            if (connection.unacknowledgedBytesRead >= connection.okHttpSettings.get() / 2)
            {
              connection.writeWindowUpdateLater(0, connection.unacknowledgedBytesRead);
              connection.unacknowledgedBytesRead = 0L;
            }
            return paramLong;
          }
          catch (Throwable localThrowable)
          {
            throw localThrowable;
          }
          paramBuffer = new StringBuilder();
        }
        catch (Throwable paramBuffer)
        {
          throw paramBuffer;
        }
      }
      paramBuffer.append("byteCount < 0: ");
      paramBuffer.append(paramLong);
      throw new IllegalArgumentException(paramBuffer.toString());
    }
    
    void receive(BufferedSource paramBufferedSource, long paramLong)
    {
      SpdyStream localSpdyStream;
      if (paramLong > 0L) {
        localSpdyStream = SpdyStream.this;
      }
      for (;;)
      {
        try
        {
          boolean bool = size;
          long l1 = readBuffer.size();
          long l2 = maxByteCount;
          int j = 1;
          if (l1 + paramLong <= l2) {
            break label200;
          }
          i = 1;
          if (i != 0)
          {
            paramBufferedSource.skip(paramLong);
            closeLater(ErrorCode.FLOW_CONTROL_ERROR);
            return;
          }
          if (bool)
          {
            paramBufferedSource.skip(paramLong);
            return;
          }
          l1 = paramBufferedSource.read(receiveBuffer, paramLong);
          if (l1 != -1L)
          {
            paramLong -= l1;
            localSpdyStream = SpdyStream.this;
            try
            {
              if (readBuffer.size() != 0L) {
                break label206;
              }
              i = j;
              readBuffer.writeAll(receiveBuffer);
              if (i != 0) {
                notifyAll();
              }
            }
            catch (Throwable paramBufferedSource)
            {
              throw paramBufferedSource;
            }
          }
          throw new EOFException();
        }
        catch (Throwable paramBufferedSource)
        {
          throw paramBufferedSource;
        }
        return;
        label200:
        int i = 0;
        continue;
        label206:
        i = 0;
      }
    }
    
    public Timeout timeout()
    {
      return this$0;
    }
  }
}
