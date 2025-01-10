package org.mozilla.osmdroid.http;

public abstract class HttpRequest
{
  public HttpRequest() {}
  
  public abstract boolean get();
  
  public abstract void post(Runnable paramRunnable);
  
  public abstract void put(Runnable paramRunnable);
}
