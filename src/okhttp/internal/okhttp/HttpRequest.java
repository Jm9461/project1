package okhttp.internal.okhttp;

import okio.Sink;

public abstract interface HttpRequest
{
  public abstract void close();
  
  public abstract Sink getRequestBody();
}
