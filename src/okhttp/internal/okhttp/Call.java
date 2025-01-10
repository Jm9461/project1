package okhttp.internal.okhttp;

import java.io.IOException;
import okhttp.Connection;
import okhttp.Headers;
import okhttp.Headers.Builder;
import okhttp.Protocol;
import okhttp.Request;
import okhttp.ResponseBody;
import okhttp.a0.a;
import okhttp.internal.Internal;
import okhttp.internal.Util;
import okhttp.internal.httpclient.HttpMethod;
import okhttp.internal.httpclient.OkHeaders;
import okhttp.internal.httpclient.ResponseBody.1;
import okio.Okio;
import okio.Sink;

public final class Call
  implements okhttp.Object
{
  final InternalCache this$0;
  
  public Call(InternalCache paramInternalCache)
  {
    this$0 = paramInternalCache;
  }
  
  static boolean add(String paramString)
  {
    return ("Content-Length".equalsIgnoreCase(paramString)) || ("Content-Encoding".equalsIgnoreCase(paramString)) || ("Content-Type".equalsIgnoreCase(paramString));
  }
  
  private static Headers combine(Headers paramHeaders1, Headers paramHeaders2)
  {
    Headers.Builder localBuilder = new Headers.Builder();
    int i = 0;
    int j = paramHeaders1.value();
    while (i < j)
    {
      String str1 = paramHeaders1.name(i);
      String str2 = paramHeaders1.value(i);
      if (((!"Warning".equalsIgnoreCase(str1)) || (!str2.startsWith("1"))) && ((add(str1)) || (!isEndToEnd(str1)) || (paramHeaders2.get(str1) == null))) {
        Internal.instance.add(localBuilder, str1, str2);
      }
      i += 1;
    }
    i = 0;
    j = paramHeaders2.value();
    while (i < j)
    {
      paramHeaders1 = paramHeaders2.name(i);
      if ((!add(paramHeaders1)) && (isEndToEnd(paramHeaders1))) {
        Internal.instance.add(localBuilder, paramHeaders1, paramHeaders2.value(i));
      }
      i += 1;
    }
    return localBuilder.build();
  }
  
  private static okhttp.Response execute(okhttp.Response paramResponse)
  {
    okhttp.Response localResponse = paramResponse;
    if (paramResponse != null)
    {
      localResponse = paramResponse;
      if (paramResponse.body() != null)
      {
        paramResponse = paramResponse.get();
        paramResponse.body(null);
        localResponse = paramResponse.build();
      }
    }
    return localResponse;
  }
  
  private okhttp.Response execute(HttpRequest paramHttpRequest, okhttp.Response paramResponse)
  {
    if (paramHttpRequest == null) {
      return paramResponse;
    }
    Object localObject = paramHttpRequest.getRequestBody();
    if (localObject == null) {
      return paramResponse;
    }
    paramHttpRequest = new HttpEngine.2(this, paramResponse.body().source(), paramHttpRequest, Okio.buffer((Sink)localObject));
    localObject = paramResponse.header("Content-Type");
    long l = paramResponse.body().contentLength();
    paramResponse = paramResponse.get();
    paramResponse.body(new ResponseBody.1((String)localObject, l, Okio.buffer(paramHttpRequest)));
    return paramResponse.build();
  }
  
  static boolean isEndToEnd(String paramString)
  {
    return (!"Connection".equalsIgnoreCase(paramString)) && (!"Keep-Alive".equalsIgnoreCase(paramString)) && (!"Proxy-Authenticate".equalsIgnoreCase(paramString)) && (!"Proxy-Authorization".equalsIgnoreCase(paramString)) && (!"TE".equalsIgnoreCase(paramString)) && (!"Trailers".equalsIgnoreCase(paramString)) && (!"Transfer-Encoding".equalsIgnoreCase(paramString)) && (!"Upgrade".equalsIgnoreCase(paramString));
  }
  
  public okhttp.Response doInBackground(Connection paramConnection)
  {
    Object localObject = this$0;
    if (localObject != null) {
      localObject = ((InternalCache)localObject).get(paramConnection.execute());
    } else {
      localObject = null;
    }
    Response localResponse1 = new CacheStrategy.Factory(System.currentTimeMillis(), paramConnection.execute(), (okhttp.Response)localObject).get();
    Request localRequest = this$0;
    okhttp.Response localResponse = request;
    InternalCache localInternalCache = this$0;
    if (localInternalCache != null) {
      localInternalCache.put(localResponse1);
    }
    if ((localObject != null) && (localResponse == null)) {
      Util.closeQuietly(((okhttp.Response)localObject).body());
    }
    if ((localRequest == null) && (localResponse == null))
    {
      localObject = new a0.a();
      ((a0.a)localObject).request(paramConnection.execute());
      ((a0.a)localObject).protocol(Protocol.HTTP_1_1);
      ((a0.a)localObject).code(504);
      ((a0.a)localObject).message("Unsatisfiable Request (only-if-cached)");
      ((a0.a)localObject).body(Util.log);
      ((a0.a)localObject).add(-1L);
      ((a0.a)localObject).get(System.currentTimeMillis());
      return ((a0.a)localObject).build();
    }
    if (localRequest == null)
    {
      paramConnection = localResponse.get();
      paramConnection.cacheResponse(execute(localResponse));
      return paramConnection.build();
    }
    try
    {
      paramConnection = paramConnection.get(localRequest);
      if ((paramConnection == null) && (localObject != null)) {
        Util.closeQuietly(((okhttp.Response)localObject).body());
      }
      if (localResponse != null)
      {
        if (paramConnection.code() == 304)
        {
          localObject = localResponse.get();
          ((a0.a)localObject).headers(combine(localResponse.headers(), paramConnection.headers()));
          ((a0.a)localObject).add(paramConnection.size());
          ((a0.a)localObject).get(paramConnection.getId());
          ((a0.a)localObject).cacheResponse(execute(localResponse));
          ((a0.a)localObject).networkResponse(execute(paramConnection));
          localObject = ((a0.a)localObject).build();
          paramConnection.body().close();
          this$0.remove();
          this$0.update(localResponse, (okhttp.Response)localObject);
          return localObject;
        }
        Util.closeQuietly(localResponse.body());
      }
      localObject = paramConnection.get();
      ((a0.a)localObject).cacheResponse(execute(localResponse));
      ((a0.a)localObject).networkResponse(execute(paramConnection));
      paramConnection = ((a0.a)localObject).build();
      if (this$0 != null)
      {
        if ((OkHeaders.hasBody(paramConnection)) && (Response.isCacheable(paramConnection, localRequest))) {
          return execute(this$0.get(paramConnection), paramConnection);
        }
        if (HttpMethod.invalidatesCache(localRequest.method()))
        {
          localObject = this$0;
          try
          {
            ((InternalCache)localObject).remove(localRequest);
            return paramConnection;
          }
          catch (IOException localIOException)
          {
            return paramConnection;
          }
        }
      }
      return paramConnection;
    }
    catch (Throwable paramConnection)
    {
      if (localIOException != null) {
        Util.closeQuietly(localIOException.body());
      }
      throw paramConnection;
    }
  }
}
