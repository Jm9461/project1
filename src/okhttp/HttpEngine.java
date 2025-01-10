package okhttp;

import okhttp.internal.okhttp.DiskLruCache.Editor;
import okhttp.internal.okhttp.HttpRequest;
import okio.Sink;

final class HttpEngine
  implements HttpRequest
{
  private final DiskLruCache.Editor client;
  boolean closed;
  private Sink connection;
  private Sink requestBodyOut;
  
  HttpEngine(Cache paramCache, DiskLruCache.Editor paramEditor)
  {
    client = paramEditor;
    connection = paramEditor.newSink(1);
    requestBodyOut = new c.b.a(this, connection, paramCache, paramEditor);
  }
  
  /* Error */
  public void close()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 21	okhttp/HttpEngine:this$0	Lokhttp/Cache;
    //   4: astore_1
    //   5: aload_1
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 49	okhttp/HttpEngine:closed	Z
    //   11: ifeq +6 -> 17
    //   14: aload_1
    //   15: monitorexit
    //   16: return
    //   17: aload_0
    //   18: iconst_1
    //   19: putfield 49	okhttp/HttpEngine:closed	Z
    //   22: aload_0
    //   23: getfield 21	okhttp/HttpEngine:this$0	Lokhttp/Cache;
    //   26: astore_2
    //   27: aload_2
    //   28: aload_2
    //   29: getfield 53	okhttp/Cache:state	I
    //   32: iconst_1
    //   33: iadd
    //   34: putfield 53	okhttp/Cache:state	I
    //   37: aload_1
    //   38: monitorexit
    //   39: aload_0
    //   40: getfield 34	okhttp/HttpEngine:connection	Lokio/Sink;
    //   43: invokestatic 59	okhttp/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   46: aload_0
    //   47: getfield 26	okhttp/HttpEngine:client	Lokhttp/internal/okhttp/DiskLruCache$Editor;
    //   50: astore_1
    //   51: aload_1
    //   52: invokevirtual 62	okhttp/internal/okhttp/DiskLruCache$Editor:abort	()V
    //   55: return
    //   56: astore_1
    //   57: return
    //   58: astore_2
    //   59: aload_1
    //   60: monitorexit
    //   61: aload_2
    //   62: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	63	0	this	HttpEngine
    //   4	48	1	localObject	Object
    //   56	4	1	localIOException	java.io.IOException
    //   26	3	2	localCache	Cache
    //   58	4	2	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   51	55	56	java/io/IOException
    //   7	16	58	java/lang/Throwable
    //   17	39	58	java/lang/Throwable
    //   59	61	58	java/lang/Throwable
  }
  
  public Sink getRequestBody()
  {
    return requestBodyOut;
  }
}
