package org.mozilla.osmdroid.http;

public class AsyncHttpClient
  extends HttpRequest
{
  private static volatile AsyncHttpClient res;
  private HttpRequest sslSocketMiddleware = new SyncHttpClient();
  private HttpRequest this$0 = sslSocketMiddleware;
  
  static
  {
    new Threading.2();
    new ActivityChooserModel.SerialExecutor();
  }
  
  private AsyncHttpClient() {}
  
  public static AsyncHttpClient execute()
  {
    if (res != null) {
      return res;
    }
    try
    {
      if (res == null) {
        res = new AsyncHttpClient();
      }
      return res;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public boolean get()
  {
    return this$0.get();
  }
  
  public void post(Runnable paramRunnable)
  {
    this$0.post(paramRunnable);
  }
  
  public void put(Runnable paramRunnable)
  {
    this$0.put(paramRunnable);
  }
}
