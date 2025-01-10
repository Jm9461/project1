package org.org.jaxen.ui;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ClassWriter
{
  private final String a;
  private int b;
  private final Object e = new Object();
  private Handler.Callback g = new MainActivity.6(this);
  private Handler handler;
  private final int q;
  private final int r;
  private HandlerThread thread;
  
  public ClassWriter(String paramString, int paramInt1, int paramInt2)
  {
    a = paramString;
    q = paramInt1;
    r = paramInt2;
    b = 0;
  }
  
  private void init(Runnable paramRunnable)
  {
    Object localObject = e;
    try
    {
      if (thread == null)
      {
        thread = new HandlerThread(a, q);
        thread.start();
        handler = new Handler(thread.getLooper(), g);
        b += 1;
      }
      handler.removeMessages(0);
      handler.sendMessage(handler.obtainMessage(1, paramRunnable));
      return;
    }
    catch (Throwable paramRunnable)
    {
      throw paramRunnable;
    }
  }
  
  void a(Runnable paramRunnable)
  {
    paramRunnable.run();
    paramRunnable = e;
    try
    {
      handler.removeMessages(0);
      handler.sendMessageDelayed(handler.obtainMessage(0), r);
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void a(Callable paramCallable, c paramC)
  {
    init(new Task.2(this, paramCallable, new Handler(), paramC));
  }
  
  public Object get(Callable paramCallable, int paramInt)
  {
    ReentrantLock localReentrantLock = new ReentrantLock();
    Condition localCondition = localReentrantLock.newCondition();
    AtomicReference localAtomicReference = new AtomicReference();
    AtomicBoolean localAtomicBoolean = new AtomicBoolean(true);
    init(new Callables.3(this, localAtomicReference, paramCallable, localReentrantLock, localAtomicBoolean, localCondition));
    localReentrantLock.lock();
    try
    {
      boolean bool = localAtomicBoolean.get();
      if (!bool)
      {
        paramCallable = localAtomicReference.get();
        localReentrantLock.unlock();
        return paramCallable;
      }
      long l1 = TimeUnit.MILLISECONDS.toNanos(paramInt);
      do
      {
        try
        {
          long l2 = localCondition.awaitNanos(l1);
          l1 = l2;
        }
        catch (InterruptedException paramCallable) {}
        bool = localAtomicBoolean.get();
        if (!bool)
        {
          paramCallable = localAtomicReference.get();
          localReentrantLock.unlock();
          return paramCallable;
        }
      } while (l1 > 0L);
      throw new InterruptedException("timeout");
    }
    catch (Throwable paramCallable)
    {
      localReentrantLock.unlock();
      throw paramCallable;
    }
  }
  
  void stop()
  {
    Object localObject = e;
    try
    {
      if (handler.hasMessages(1)) {
        return;
      }
      thread.quit();
      thread = null;
      handler = null;
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
}
