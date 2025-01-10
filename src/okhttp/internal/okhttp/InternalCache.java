package okhttp.internal.okhttp;

import okhttp.Request;

public abstract interface InternalCache
{
  public abstract okhttp.Response get(Request paramRequest);
  
  public abstract HttpRequest get(okhttp.Response paramResponse);
  
  public abstract void put(Response paramResponse);
  
  public abstract void remove();
  
  public abstract void remove(Request paramRequest);
  
  public abstract void update(okhttp.Response paramResponse1, okhttp.Response paramResponse2);
}
