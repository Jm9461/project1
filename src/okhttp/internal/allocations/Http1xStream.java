package okhttp.internal.allocations;

import java.io.EOFException;
import java.io.IOException;
import java.net.Proxy;
import okhttp.Headers;
import okhttp.Headers.Builder;
import okhttp.HttpHost;
import okhttp.HttpUrl;
import okhttp.OkHttpClient;
import okhttp.Protocol;
import okhttp.Request;
import okhttp.Response;
import okhttp.ResponseBody;
import okhttp.Route;
import okhttp.a0.a;
import okhttp.internal.Internal;
import okhttp.internal.http.RealConnection;
import okhttp.internal.http.StreamAllocation;
import okhttp.internal.httpclient.HttpStream;
import okhttp.internal.httpclient.OkHeaders;
import okhttp.internal.httpclient.RequestLine;
import okhttp.internal.httpclient.ResponseBody.1;
import okhttp.internal.httpclient.StatusLine;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http1xStream
  implements HttpStream
{
  final OkHttpClient httpEngine;
  private long pos = 262144L;
  final BufferedSink sink;
  final BufferedSource source;
  int state = 0;
  final StreamAllocation streamAllocation;
  
  public Http1xStream(OkHttpClient paramOkHttpClient, StreamAllocation paramStreamAllocation, BufferedSource paramBufferedSource, BufferedSink paramBufferedSink)
  {
    httpEngine = paramOkHttpClient;
    streamAllocation = paramStreamAllocation;
    source = paramBufferedSource;
    sink = paramBufferedSink;
  }
  
  private String read()
  {
    String str = source.read(pos);
    pos -= str.length();
    return str;
  }
  
  void access$400(ForwardingTimeout paramForwardingTimeout)
  {
    Timeout localTimeout = paramForwardingTimeout.delegate();
    paramForwardingTimeout.setDelegate(Timeout.NONE);
    localTimeout.clearDeadline();
    localTimeout.clearTimeout();
  }
  
  public Sink createRequestBody(Request paramRequest, long paramLong)
  {
    if ("chunked".equalsIgnoreCase(paramRequest.header("Transfer-Encoding"))) {
      return newChunkedSink();
    }
    if (paramLong != -1L) {
      return newFixedLengthSink(paramLong);
    }
    throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
  }
  
  public void finishRequest()
  {
    sink.flush();
  }
  
  public Sink newChunkedSink()
  {
    if (state == 1)
    {
      state = 2;
      return new HttpConnection.ChunkedSink(this);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("state: ");
    localStringBuilder.append(state);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  public Source newChunkedSource(HttpUrl paramHttpUrl)
  {
    if (state == 4)
    {
      state = 5;
      return new HttpConnection.ChunkedSource(this, paramHttpUrl);
    }
    paramHttpUrl = new StringBuilder();
    paramHttpUrl.append("state: ");
    paramHttpUrl.append(state);
    throw new IllegalStateException(paramHttpUrl.toString());
  }
  
  public Sink newFixedLengthSink(long paramLong)
  {
    if (state == 1)
    {
      state = 2;
      return new HttpConnection.FixedLengthSink(this, paramLong);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("state: ");
    localStringBuilder.append(state);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  public Source newFixedLengthSource(long paramLong)
  {
    if (state == 4)
    {
      state = 5;
      return new HttpConnection.FixedLengthSource(this, paramLong);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("state: ");
    localStringBuilder.append(state);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  public Source newUnknownLengthSource()
  {
    if (state == 4)
    {
      localObject = streamAllocation;
      if (localObject != null)
      {
        state = 5;
        ((StreamAllocation)localObject).connectionFailed();
        return new HttpConnection.UnknownLengthSource(this);
      }
      throw new IllegalStateException("streamAllocation == null");
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("state: ");
    ((StringBuilder)localObject).append(state);
    throw new IllegalStateException(((StringBuilder)localObject).toString());
  }
  
  public ResponseBody openResponseBody(Response paramResponse)
  {
    Object localObject = streamAllocation;
    this$0.toString(buffer);
    localObject = paramResponse.header("Content-Type");
    if (!OkHeaders.hasBody(paramResponse)) {
      return new ResponseBody.1((String)localObject, 0L, Okio.buffer(newFixedLengthSource(0L)));
    }
    if ("chunked".equalsIgnoreCase(paramResponse.header("Transfer-Encoding"))) {
      return new ResponseBody.1((String)localObject, -1L, Okio.buffer(newChunkedSource(paramResponse.request().url())));
    }
    long l = OkHeaders.contentLength(paramResponse);
    if (l != -1L) {
      return new ResponseBody.1((String)localObject, l, Okio.buffer(newFixedLengthSource(l)));
    }
    return new ResponseBody.1((String)localObject, -1L, Okio.buffer(newUnknownLengthSource()));
  }
  
  public Headers readHeaders()
  {
    Headers.Builder localBuilder = new Headers.Builder();
    for (;;)
    {
      String str = read();
      if (str.length() == 0) {
        break;
      }
      Internal.instance.addLenient(localBuilder, str);
    }
    return localBuilder.build();
  }
  
  public a0.a readResponse(boolean paramBoolean)
  {
    int i = state;
    Object localObject1;
    if ((i != 1) && (i != 3))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("state: ");
      ((StringBuilder)localObject1).append(state);
      throw new IllegalStateException(((StringBuilder)localObject1).toString());
    }
    try
    {
      localObject1 = StatusLine.parse(read());
      localObject2 = new a0.a();
      Object localObject3 = protocol;
      ((a0.a)localObject2).protocol((Protocol)localObject3);
      i = code;
      ((a0.a)localObject2).code(i);
      localObject3 = message;
      ((a0.a)localObject2).message((String)localObject3);
      ((a0.a)localObject2).headers(readHeaders());
      if ((paramBoolean) && (code == 100)) {
        return null;
      }
      if (code == 100)
      {
        state = 3;
        return localObject2;
      }
      state = 4;
      return localObject2;
    }
    catch (EOFException localEOFException)
    {
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("unexpected end of stream on ");
      ((StringBuilder)localObject2).append(streamAllocation);
      localObject2 = new IOException(((StringBuilder)localObject2).toString());
      ((IOException)localObject2).initCause(localEOFException);
      throw ((Throwable)localObject2);
    }
  }
  
  public void writeRequest(Headers paramHeaders, String paramString)
  {
    if (state == 0)
    {
      sink.writeUtf8(paramString).writeUtf8("\r\n");
      int i = 0;
      int j = paramHeaders.value();
      while (i < j)
      {
        sink.writeUtf8(paramHeaders.name(i)).writeUtf8(": ").writeUtf8(paramHeaders.value(i)).writeUtf8("\r\n");
        i += 1;
      }
      sink.writeUtf8("\r\n");
      state = 1;
      return;
    }
    paramHeaders = new StringBuilder();
    paramHeaders.append("state: ");
    paramHeaders.append(state);
    paramHeaders = new IllegalStateException(paramHeaders.toString());
    throw paramHeaders;
  }
  
  public void writeRequestBody()
  {
    sink.flush();
  }
  
  public void writeRequestHeaders(Request paramRequest)
  {
    String str = RequestLine.get(paramRequest, streamAllocation.get().getRoute().getProxy().type());
    writeRequest(paramRequest.headers(), str);
  }
}
