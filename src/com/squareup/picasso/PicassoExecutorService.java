package com.squareup.picasso;

import android.net.NetworkInfo;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class PicassoExecutorService
  extends ThreadPoolExecutor
{
  PicassoExecutorService()
  {
    super(3, 3, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new d0.c());
  }
  
  private void setThreadCount(int paramInt)
  {
    setCorePoolSize(paramInt);
    setMaximumPoolSize(paramInt);
  }
  
  void adjustThreadCount(NetworkInfo paramNetworkInfo)
  {
    if ((paramNetworkInfo != null) && (paramNetworkInfo.isConnectedOrConnecting()))
    {
      int i = paramNetworkInfo.getType();
      if (i != 0)
      {
        if ((i != 1) && (i != 6) && (i != 9))
        {
          setThreadCount(3);
          return;
        }
        setThreadCount(4);
        return;
      }
      i = paramNetworkInfo.getSubtype();
      switch (i)
      {
      default: 
        switch (i)
        {
        default: 
          setThreadCount(3);
          return;
        case 13: 
        case 14: 
        case 15: 
          setThreadCount(3);
          return;
        }
      case 3: 
      case 4: 
      case 5: 
      case 6: 
        setThreadCount(2);
        return;
      }
      setThreadCount(1);
      return;
    }
    setThreadCount(3);
  }
  
  public Future submit(Runnable paramRunnable)
  {
    paramRunnable = new PicassoFutureTask((BitmapHunter)paramRunnable);
    execute(paramRunnable);
    return paramRunnable;
  }
  
  final class PicassoFutureTask
    extends FutureTask<c>
    implements Comparable<v.a>
  {
    PicassoFutureTask()
    {
      super(null);
    }
    
    public int compareTo(PicassoFutureTask paramPicassoFutureTask)
    {
      Picasso.Priority localPriority1 = getPriority();
      Picasso.Priority localPriority2 = hunter.getPriority();
      int i;
      int j;
      if (localPriority1 == localPriority2)
      {
        i = sequence;
        j = hunter.sequence;
      }
      else
      {
        i = localPriority2.ordinal();
        j = localPriority1.ordinal();
      }
      return i - j;
    }
  }
}
