package okhttp;

import java.io.Closeable;

public final class Response
  implements Closeable
{
  final ResponseBody body;
  private volatile CacheControl cacheControl;
  final Response cacheResponse;
  final int code;
  final Handshake handshake;
  final Headers headers;
  final long l;
  final long m;
  final String message;
  final Response networkResponse;
  final Response priorResponse;
  final Protocol protocol;
  final Request request;
  
  Response(a0.a paramA)
  {
    request = request;
    protocol = protocol;
    code = code;
    message = message;
    handshake = handshake;
    headers = headers.build();
    body = body;
    networkResponse = networkResponse;
    cacheResponse = cacheResponse;
    priorResponse = priorResponse;
    m = m;
    l = l;
  }
  
  public ResponseBody body()
  {
    return body;
  }
  
  public CacheControl cacheControl()
  {
    CacheControl localCacheControl = cacheControl;
    if (localCacheControl != null) {
      return localCacheControl;
    }
    localCacheControl = CacheControl.parse(headers);
    cacheControl = localCacheControl;
    return localCacheControl;
  }
  
  public Response cacheResponse()
  {
    return cacheResponse;
  }
  
  public void close()
  {
    ResponseBody localResponseBody = body;
    if (localResponseBody != null)
    {
      localResponseBody.close();
      return;
    }
    throw new IllegalStateException("response is not eligible for a body and must not be closed");
  }
  
  public int code()
  {
    return code;
  }
  
  public a0.a get()
  {
    return new a0.a(this);
  }
  
  public long getId()
  {
    return l;
  }
  
  public Handshake handshake()
  {
    return handshake;
  }
  
  public String header(String paramString)
  {
    return header(paramString, null);
  }
  
  public String header(String paramString1, String paramString2)
  {
    paramString1 = headers.get(paramString1);
    if (paramString1 != null) {
      return paramString1;
    }
    return paramString2;
  }
  
  public Headers headers()
  {
    return headers;
  }
  
  public boolean isSuccessful()
  {
    int i = code;
    return (i >= 200) && (i < 300);
  }
  
  public String message()
  {
    return message;
  }
  
  public Response networkResponse()
  {
    return networkResponse;
  }
  
  public Response priorResponse()
  {
    return priorResponse;
  }
  
  public Protocol protocol()
  {
    return protocol;
  }
  
  public Request request()
  {
    return request;
  }
  
  public long size()
  {
    return m;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Response{protocol=");
    localStringBuilder.append(protocol);
    localStringBuilder.append(", code=");
    localStringBuilder.append(code);
    localStringBuilder.append(", message=");
    localStringBuilder.append(message);
    localStringBuilder.append(", url=");
    localStringBuilder.append(request.url());
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}
