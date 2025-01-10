package okhttp;

import java.util.List;
import okhttp.internal.httpclient.HttpMethod;

public final class Request
{
  final RequestBody body;
  private volatile CacheControl cacheControl;
  final Headers headers;
  final String method;
  final Object tag;
  final HttpUrl url;
  
  Request(Builder paramBuilder)
  {
    url = url;
    method = method;
    headers = headers.build();
    body = body;
    paramBuilder = tag;
    if (paramBuilder == null) {
      paramBuilder = this;
    }
    tag = paramBuilder;
  }
  
  public RequestBody body()
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
  
  public String header(String paramString)
  {
    return headers.get(paramString);
  }
  
  public List headers(String paramString)
  {
    return headers.values(paramString);
  }
  
  public Headers headers()
  {
    return headers;
  }
  
  public boolean isHttps()
  {
    return url.isHttps();
  }
  
  public String method()
  {
    return method;
  }
  
  public Builder newBuilder()
  {
    return new Builder();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Request{method=");
    localStringBuilder.append(method);
    localStringBuilder.append(", url=");
    localStringBuilder.append(url);
    localStringBuilder.append(", tag=");
    Object localObject = tag;
    if (localObject == this) {
      localObject = null;
    }
    localStringBuilder.append(localObject);
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
  
  public HttpUrl url()
  {
    return url;
  }
  
  public class Builder
  {
    RequestBody body;
    Headers.Builder headers;
    String method;
    Object tag;
    HttpUrl url;
    
    public Builder()
    {
      method = "GET";
      headers = new Headers.Builder();
    }
    
    Builder()
    {
      url = url;
      method = method;
      body = body;
      tag = tag;
      headers = headers.newBuilder();
    }
    
    public Request build()
    {
      if (url != null) {
        return new Request(this);
      }
      throw new IllegalStateException("url == null");
    }
    
    public Builder cacheControl(CacheControl paramCacheControl)
    {
      paramCacheControl = paramCacheControl.toString();
      if (paramCacheControl.isEmpty())
      {
        removeHeader("Cache-Control");
        return this;
      }
      header("Cache-Control", paramCacheControl);
      return this;
    }
    
    public Builder header(String paramString1, String paramString2)
    {
      headers.set(paramString1, paramString2);
      return this;
    }
    
    public Builder headers(Headers paramHeaders)
    {
      headers = paramHeaders.newBuilder();
      return this;
    }
    
    public Builder method(String paramString, RequestBody paramRequestBody)
    {
      if (paramString != null)
      {
        if (paramString.length() != 0)
        {
          if ((paramRequestBody != null) && (!HttpMethod.get(paramString)))
          {
            paramRequestBody = new StringBuilder();
            paramRequestBody.append("method ");
            paramRequestBody.append(paramString);
            paramRequestBody.append(" must not have a request body.");
            throw new IllegalArgumentException(paramRequestBody.toString());
          }
          if ((paramRequestBody == null) && (HttpMethod.requiresRequestBody(paramString)))
          {
            paramRequestBody = new StringBuilder();
            paramRequestBody.append("method ");
            paramRequestBody.append(paramString);
            paramRequestBody.append(" must have a request body.");
            throw new IllegalArgumentException(paramRequestBody.toString());
          }
          method = paramString;
          body = paramRequestBody;
          return this;
        }
        throw new IllegalArgumentException("method.length() == 0");
      }
      throw new NullPointerException("method == null");
    }
    
    public Builder removeHeader(String paramString)
    {
      headers.removeAll(paramString);
      return this;
    }
    
    public Builder url(String paramString)
    {
      if (paramString != null)
      {
        Object localObject;
        if (paramString.regionMatches(true, 0, "ws:", 0, 3))
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("http:");
          ((StringBuilder)localObject).append(paramString.substring(3));
          localObject = ((StringBuilder)localObject).toString();
        }
        else
        {
          localObject = paramString;
          if (paramString.regionMatches(true, 0, "wss:", 0, 4))
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("https:");
            ((StringBuilder)localObject).append(paramString.substring(4));
            localObject = ((StringBuilder)localObject).toString();
          }
        }
        paramString = HttpUrl.parse((String)localObject);
        if (paramString != null)
        {
          url(paramString);
          return this;
        }
        paramString = new StringBuilder();
        paramString.append("unexpected url: ");
        paramString.append((String)localObject);
        throw new IllegalArgumentException(paramString.toString());
      }
      throw new NullPointerException("url == null");
    }
    
    public Builder url(HttpUrl paramHttpUrl)
    {
      if (paramHttpUrl != null)
      {
        url = paramHttpUrl;
        return this;
      }
      throw new NullPointerException("url == null");
    }
  }
}
