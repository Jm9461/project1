package org.mozilla.osmdroid.http;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncHttpClient
  extends HttpRequest
{
  private ExecutorService executor = Executors.newFixedThreadPool(2);
  private volatile Handler handler;
  private final Object lock = new Object();
  
  public SyncHttpClient() {}
  
  public boolean get()
  {
    return Looper.getMainLooper().getThread() == Thread.currentThread();
  }
  
  public void post(Runnable paramRunnable)
  {
    if (handler == null)
    {
      Object localObject = lock;
      try
      {
        if (handler == null) {
          handler = new Handler(Looper.getMainLooper());
        }
      }
      catch (Throwable paramRunnable)
      {
        throw paramRunnable;
      }
    }
    handler.post(paramRunnable);
  }
  
  public void put(Runnable paramRunnable)
  {
    executor.execute(paramRunnable);
  }
}
