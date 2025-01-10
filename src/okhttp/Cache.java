package okhttp;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp.internal.Util;
import okhttp.internal.httpclient.HttpMethod;
import okhttp.internal.httpclient.OkHeaders;
import okhttp.internal.httpclient.StatusLine;
import okhttp.internal.internal.Platform;
import okhttp.internal.io.FileSystem;
import okhttp.internal.okhttp.DiskLruCache;
import okhttp.internal.okhttp.DiskLruCache.Editor;
import okhttp.internal.okhttp.DiskLruCache.Snapshot;
import okhttp.internal.okhttp.HttpRequest;
import okhttp.internal.okhttp.InternalCache;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class Cache
  implements Closeable, Flushable
{
  final DiskLruCache cache;
  private int data;
  private int head;
  final InternalCache internalCache = new InternalCache()
  {
    public Response get(Request paramAnonymousRequest)
    {
      return Cache.this.get(paramAnonymousRequest);
    }
    
    public HttpRequest get(Response paramAnonymousResponse)
    {
      return put(paramAnonymousResponse);
    }
    
    public void put(okhttp.internal.okhttp.Response paramAnonymousResponse)
    {
      Cache.this.put(paramAnonymousResponse);
    }
    
    public void remove()
    {
      clear();
    }
    
    public void remove(Request paramAnonymousRequest)
    {
      Cache.this.remove(paramAnonymousRequest);
    }
    
    public void update(Response paramAnonymousResponse1, Response paramAnonymousResponse2)
    {
      Cache.this.update(paramAnonymousResponse1, paramAnonymousResponse2);
    }
  };
  private int next;
  int state;
  int writeSuccessCount;
  
  public Cache(File paramFile, long paramLong)
  {
    this(paramFile, paramLong, FileSystem.SYSTEM);
  }
  
  Cache(File paramFile, long paramLong, FileSystem paramFileSystem)
  {
    cache = DiskLruCache.create(paramFileSystem, paramFile, 201105, 2, paramLong);
  }
  
  private void abortQuietly(DiskLruCache.Editor paramEditor)
  {
    if (paramEditor != null) {
      try
      {
        paramEditor.abort();
        return;
      }
      catch (IOException paramEditor) {}
    }
  }
  
  static int readInt(BufferedSource paramBufferedSource)
  {
    try
    {
      long l = paramBufferedSource.readDecimalLong();
      paramBufferedSource = paramBufferedSource.readUtf8LineStrict();
      if ((l >= 0L) && (l <= 2147483647L))
      {
        boolean bool = paramBufferedSource.isEmpty();
        if (bool) {
          return (int)l;
        }
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("expected an int but was \"");
      localStringBuilder.append(l);
      localStringBuilder.append(paramBufferedSource);
      localStringBuilder.append("\"");
      paramBufferedSource = new IOException(localStringBuilder.toString());
      throw paramBufferedSource;
    }
    catch (NumberFormatException paramBufferedSource)
    {
      throw new IOException(paramBufferedSource.getMessage());
    }
  }
  
  public static String toString(HttpUrl paramHttpUrl)
  {
    return ByteString.encodeUtf8(paramHttpUrl.toString()).md5().hex();
  }
  
  void clear()
  {
    try
    {
      head += 1;
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void close()
  {
    cache.close();
  }
  
  public void flush()
  {
    cache.flush();
  }
  
  Response get(Request paramRequest)
  {
    Object localObject1 = toString(paramRequest.url());
    Object localObject2 = cache;
    try
    {
      localObject1 = ((DiskLruCache)localObject2).get((String)localObject1);
      if (localObject1 == null) {
        return null;
      }
      try
      {
        localObject2 = new Entry(((DiskLruCache.Snapshot)localObject1).getSource(0));
        localObject1 = ((Entry)localObject2).response((DiskLruCache.Snapshot)localObject1);
        if (!((Entry)localObject2).matches(paramRequest, (Response)localObject1))
        {
          Util.closeQuietly(((Response)localObject1).body());
          return null;
        }
        return localObject1;
      }
      catch (IOException paramRequest)
      {
        Util.closeQuietly((Closeable)localObject1);
        return null;
      }
      return null;
    }
    catch (IOException paramRequest) {}
  }
  
  HttpRequest put(Response paramResponse)
  {
    Object localObject1 = paramResponse.request().method();
    if (HttpMethod.invalidatesCache(paramResponse.request().method())) {
      try
      {
        remove(paramResponse.request());
        return null;
      }
      catch (IOException paramResponse)
      {
        return null;
      }
    }
    if (!((String)localObject1).equals("GET")) {
      return null;
    }
    if (OkHeaders.hasVaryAll(paramResponse)) {
      return null;
    }
    Entry localEntry = new Entry(paramResponse);
    localObject1 = null;
    Object localObject2 = cache;
    try
    {
      localObject2 = ((DiskLruCache)localObject2).edit(toString(paramResponse.request().url()));
      paramResponse = (Response)localObject2;
      if (localObject2 == null) {
        return null;
      }
      localObject1 = paramResponse;
      localEntry.writeTo((DiskLruCache.Editor)localObject2);
      localObject1 = paramResponse;
      paramResponse = new HttpEngine(this, (DiskLruCache.Editor)localObject2);
      return paramResponse;
    }
    catch (IOException paramResponse)
    {
      abortQuietly((DiskLruCache.Editor)localObject1);
    }
    return null;
  }
  
  void put(okhttp.internal.okhttp.Response paramResponse)
  {
    try
    {
      data += 1;
      if (this$0 != null) {
        next += 1;
      } else if (request != null) {
        head += 1;
      }
      return;
    }
    catch (Throwable paramResponse)
    {
      throw paramResponse;
    }
  }
  
  void remove(Request paramRequest)
  {
    cache.remove(toString(paramRequest.url()));
  }
  
  void update(Response paramResponse1, Response paramResponse2)
  {
    Entry localEntry = new Entry(paramResponse2);
    paramResponse2 = bodysnapshot;
    paramResponse1 = null;
    try
    {
      DiskLruCache.Editor localEditor = paramResponse2.edit();
      paramResponse2 = localEditor;
      if (localEditor != null)
      {
        paramResponse1 = paramResponse2;
        localEntry.writeTo(localEditor);
        paramResponse1 = paramResponse2;
        localEditor.commit();
      }
      return;
    }
    catch (IOException paramResponse2)
    {
      abortQuietly(paramResponse1);
    }
  }
  
  class CacheResponseBody
    extends ResponseBody
  {
    private final BufferedSource bodySource;
    private final String contentLength;
    
    CacheResponseBody(String paramString1, String paramString2)
    {
      contentLength = paramString2;
      bodySource = Okio.buffer(new c.c.a(this, getSource(1), Cache.this));
    }
    
    public long contentLength()
    {
      if (contentLength != null)
      {
        String str = contentLength;
        try
        {
          long l = Long.parseLong(str);
          return l;
        }
        catch (NumberFormatException localNumberFormatException) {}
      }
      return -1L;
    }
    
    public BufferedSource source()
    {
      return bodySource;
    }
  }
  
  final class Entry
  {
    private static final String data;
    private static final String id;
    private final int code;
    private final Handshake handshake;
    private final String message;
    private final Protocol protocol;
    private final String requestMethod;
    private final Headers responseHeaders;
    private final long size;
    private final String url;
    private final long value;
    private final Headers varyHeaders;
    
    static
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(Platform.get().getPrefix());
      localStringBuilder.append("-Sent-Millis");
      data = localStringBuilder.toString();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(Platform.get().getPrefix());
      localStringBuilder.append("-Received-Millis");
      id = localStringBuilder.toString();
    }
    
    Entry()
    {
      url = this$1.request().url().toString();
      varyHeaders = OkHeaders.varyHeaders(this$1);
      requestMethod = this$1.request().method();
      protocol = this$1.protocol();
      code = this$1.code();
      message = this$1.message();
      responseHeaders = this$1.headers();
      handshake = this$1.handshake();
      size = this$1.size();
      value = this$1.getId();
    }
    
    Entry()
    {
      try
      {
        Object localObject1 = Okio.buffer(this$1);
        url = ((BufferedSource)localObject1).readUtf8LineStrict();
        requestMethod = ((BufferedSource)localObject1).readUtf8LineStrict();
        Object localObject2 = new Headers.Builder();
        int j = Cache.readInt((BufferedSource)localObject1);
        int i = 0;
        while (i < j)
        {
          ((Headers.Builder)localObject2).addLenient(((BufferedSource)localObject1).readUtf8LineStrict());
          i += 1;
        }
        varyHeaders = ((Headers.Builder)localObject2).build();
        localObject2 = StatusLine.parse(((BufferedSource)localObject1).readUtf8LineStrict());
        protocol = protocol;
        code = code;
        message = message;
        localObject2 = new Headers.Builder();
        j = Cache.readInt((BufferedSource)localObject1);
        i = 0;
        while (i < j)
        {
          ((Headers.Builder)localObject2).addLenient(((BufferedSource)localObject1).readUtf8LineStrict());
          i += 1;
        }
        Object localObject3 = ((Headers.Builder)localObject2).get(data);
        Object localObject4 = ((Headers.Builder)localObject2).get(id);
        ((Headers.Builder)localObject2).removeAll(data);
        ((Headers.Builder)localObject2).removeAll(id);
        long l2 = 0L;
        if (localObject3 != null) {
          l1 = Long.parseLong((String)localObject3);
        } else {
          l1 = 0L;
        }
        size = l1;
        long l1 = l2;
        if (localObject4 != null) {
          l1 = Long.parseLong((String)localObject4);
        }
        value = l1;
        responseHeaders = ((Headers.Builder)localObject2).build();
        boolean bool = isHttps();
        if (bool)
        {
          localObject2 = ((BufferedSource)localObject1).readUtf8LineStrict();
          i = ((String)localObject2).length();
          if (i <= 0)
          {
            localObject2 = Language.get(((BufferedSource)localObject1).readUtf8LineStrict());
            localObject3 = readCertificateList((BufferedSource)localObject1);
            localObject4 = readCertificateList((BufferedSource)localObject1);
            bool = ((BufferedSource)localObject1).exhausted();
            if (!bool) {
              localObject1 = TlsVersion.forJavaName(((BufferedSource)localObject1).readUtf8LineStrict());
            } else {
              localObject1 = TlsVersion.SSL_3_0;
            }
            handshake = Handshake.get((TlsVersion)localObject1, (Language)localObject2, (List)localObject3, (List)localObject4);
          }
          else
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("expected \"\" but was \"");
            ((StringBuilder)localObject1).append((String)localObject2);
            ((StringBuilder)localObject1).append("\"");
            throw new IOException(((StringBuilder)localObject1).toString());
          }
        }
        else
        {
          handshake = null;
        }
        this$1.close();
        return;
      }
      catch (Throwable localThrowable)
      {
        this$1.close();
        throw localThrowable;
      }
    }
    
    private boolean isHttps()
    {
      return url.startsWith("https://");
    }
    
    private List readCertificateList(BufferedSource paramBufferedSource)
    {
      int j = Cache.readInt(paramBufferedSource);
      if (j == -1) {
        return Collections.emptyList();
      }
      try
      {
        CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
        ArrayList localArrayList = new ArrayList(j);
        int i = 0;
        while (i < j)
        {
          String str = paramBufferedSource.readUtf8LineStrict();
          Buffer localBuffer = new Buffer();
          localBuffer.write(ByteString.decodeBase64(str));
          localArrayList.add(localCertificateFactory.generateCertificate(localBuffer.inputStream()));
          i += 1;
        }
        return localArrayList;
      }
      catch (CertificateException paramBufferedSource)
      {
        paramBufferedSource = new IOException(paramBufferedSource.getMessage());
        throw paramBufferedSource;
      }
    }
    
    private void writeCertList(BufferedSink paramBufferedSink, List paramList)
    {
      try
      {
        int i = paramList.size();
        long l = i;
        paramBufferedSink.writeDecimalLong(l).writeByte(10);
        i = 0;
        int j = paramList.size();
        while (i < j)
        {
          Object localObject = paramList.get(i);
          localObject = (Certificate)localObject;
          paramBufferedSink.writeUtf8(ByteString.of(((Certificate)localObject).getEncoded()).base64()).writeByte(10);
          i += 1;
        }
        return;
      }
      catch (CertificateEncodingException paramBufferedSink)
      {
        paramBufferedSink = new IOException(paramBufferedSink.getMessage());
        throw paramBufferedSink;
      }
    }
    
    public boolean matches(Request paramRequest, Response paramResponse)
    {
      return (url.equals(paramRequest.url().toString())) && (requestMethod.equals(paramRequest.method())) && (OkHeaders.varyMatches(paramResponse, varyHeaders, paramRequest));
    }
    
    public Response response(DiskLruCache.Snapshot paramSnapshot)
    {
      String str1 = responseHeaders.get("Content-Type");
      String str2 = responseHeaders.get("Content-Length");
      Object localObject = new Request.Builder();
      ((Request.Builder)localObject).url(url);
      ((Request.Builder)localObject).method(requestMethod, null);
      ((Request.Builder)localObject).headers(varyHeaders);
      localObject = ((Request.Builder)localObject).build();
      a0.a localA = new a0.a();
      localA.request((Request)localObject);
      localA.protocol(protocol);
      localA.code(code);
      localA.message(message);
      localA.headers(responseHeaders);
      localA.body(new Cache.CacheResponseBody(paramSnapshot, str1, str2));
      localA.handshake(handshake);
      localA.add(size);
      localA.get(value);
      return localA.build();
    }
    
    public void writeTo(DiskLruCache.Editor paramEditor)
    {
      paramEditor = Okio.buffer(paramEditor.newSink(0));
      paramEditor.writeUtf8(url).writeByte(10);
      paramEditor.writeUtf8(requestMethod).writeByte(10);
      paramEditor.writeDecimalLong(varyHeaders.value()).writeByte(10);
      int i = 0;
      int j = varyHeaders.value();
      while (i < j)
      {
        paramEditor.writeUtf8(varyHeaders.name(i)).writeUtf8(": ").writeUtf8(varyHeaders.value(i)).writeByte(10);
        i += 1;
      }
      paramEditor.writeUtf8(new StatusLine(protocol, code, message).toString()).writeByte(10);
      paramEditor.writeDecimalLong(responseHeaders.value() + 2).writeByte(10);
      i = 0;
      j = responseHeaders.value();
      while (i < j)
      {
        paramEditor.writeUtf8(responseHeaders.name(i)).writeUtf8(": ").writeUtf8(responseHeaders.value(i)).writeByte(10);
        i += 1;
      }
      paramEditor.writeUtf8(data).writeUtf8(": ").writeDecimalLong(size).writeByte(10);
      paramEditor.writeUtf8(id).writeUtf8(": ").writeDecimalLong(value).writeByte(10);
      if (isHttps())
      {
        paramEditor.writeByte(10);
        paramEditor.writeUtf8(handshake.cipherSuite().javaName()).writeByte(10);
        writeCertList(paramEditor, handshake.peerCertificates());
        writeCertList(paramEditor, handshake.localCertificates());
        paramEditor.writeUtf8(handshake.tlsVersion().javaName()).writeByte(10);
      }
      paramEditor.close();
    }
  }
}
