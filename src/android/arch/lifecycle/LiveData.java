package android.arch.lifecycle;

import a.a.a.b.b;
import java.util.Iterator;
import java.util.Map.Entry;
import org.mozilla.osmdroid.asm.DynamicTableModel.a;
import org.mozilla.osmdroid.http.AsyncHttpClient;

public abstract class LiveData<T>
{
  private static final Object o = new Object();
  private b<k<T>, LiveData<T>.b> a = new org.mozilla.osmdroid.asm.h();
  private int b;
  private boolean c;
  private final Object d = new Object();
  private volatile Object e;
  private int k = 0;
  private boolean l;
  private volatile Object x;
  
  public LiveData()
  {
    Object localObject = o;
    x = localObject;
    e = localObject;
    b = -1;
    new a();
  }
  
  private void a(b paramB)
  {
    if (l)
    {
      c = true;
      return;
    }
    l = true;
    do
    {
      c = false;
      b localB;
      if (paramB != null)
      {
        b(paramB);
        localB = null;
      }
      else
      {
        DynamicTableModel.a localA = a.f();
        do
        {
          localB = paramB;
          if (!localA.hasNext()) {
            break;
          }
          b((b)((Map.Entry)localA.next()).getValue());
        } while (!c);
        localB = paramB;
      }
      paramB = localB;
    } while (c);
    l = false;
  }
  
  private void b(b paramB)
  {
    if (!l) {
      return;
    }
    if (!paramB.b())
    {
      paramB.b(false);
      return;
    }
    int i = k;
    int j = b;
    if (i >= j) {
      return;
    }
    k = j;
    a.a(x);
  }
  
  private static void delete(String paramString)
  {
    if (AsyncHttpClient.execute().get()) {
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Cannot invoke ");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" on a background");
    localStringBuilder.append(" thread");
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  protected void a(Object paramObject)
  {
    delete("setValue");
    b += 1;
    x = paramObject;
    a(null);
  }
  
  public void b(Context paramContext)
  {
    delete("removeObserver");
    paramContext = (b)a.remove(paramContext);
    if (paramContext == null) {
      return;
    }
    paramContext.c();
    paramContext.b(false);
  }
  
  public void b(h paramH, Context paramContext)
  {
    if (paramH.a().b() == f.b) {
      return;
    }
    LifecycleBoundObserver localLifecycleBoundObserver = new LifecycleBoundObserver(paramH, paramContext);
    paramContext = (b)a.b(paramContext, localLifecycleBoundObserver);
    if ((paramContext != null) && (!paramContext.b(paramH))) {
      throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
    }
    if (paramContext != null) {
      return;
    }
    paramH.a().a(localLifecycleBoundObserver);
  }
  
  protected void start() {}
  
  protected void stop() {}
  
  class LifecycleBoundObserver
    extends LiveData<T>.b
    implements GenericLifecycleObserver
  {
    final h a;
    
    LifecycleBoundObserver(h paramH, Context paramContext)
    {
      super(paramContext);
      a = paramH;
    }
    
    public void b(h paramH, c paramC)
    {
      if (a.a().b() == f.b)
      {
        b(a);
        return;
      }
      b(b());
    }
    
    boolean b()
    {
      return a.a().b().a(f.c);
    }
    
    boolean b(h paramH)
    {
      return a == paramH;
    }
    
    void c()
    {
      a.a().c(this);
    }
  }
  
  class a
    implements Runnable
  {
    a() {}
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 14	android/arch/lifecycle/LiveData$a:d	Landroid/arch/lifecycle/LiveData;
      //   4: invokestatic 25	android/arch/lifecycle/LiveData:b	(Landroid/arch/lifecycle/LiveData;)Ljava/lang/Object;
      //   7: astore_2
      //   8: aload_2
      //   9: monitorenter
      //   10: aload_0
      //   11: getfield 14	android/arch/lifecycle/LiveData$a:d	Landroid/arch/lifecycle/LiveData;
      //   14: invokestatic 28	android/arch/lifecycle/LiveData:a	(Landroid/arch/lifecycle/LiveData;)Ljava/lang/Object;
      //   17: astore_1
      //   18: aload_0
      //   19: getfield 14	android/arch/lifecycle/LiveData$a:d	Landroid/arch/lifecycle/LiveData;
      //   22: invokestatic 32	android/arch/lifecycle/LiveData:c	()Ljava/lang/Object;
      //   25: invokestatic 35	android/arch/lifecycle/LiveData:a	(Landroid/arch/lifecycle/LiveData;Ljava/lang/Object;)Ljava/lang/Object;
      //   28: pop
      //   29: aload_2
      //   30: monitorexit
      //   31: aload_0
      //   32: getfield 14	android/arch/lifecycle/LiveData$a:d	Landroid/arch/lifecycle/LiveData;
      //   35: aload_1
      //   36: invokevirtual 38	android/arch/lifecycle/LiveData:a	(Ljava/lang/Object;)V
      //   39: return
      //   40: astore_1
      //   41: aload_2
      //   42: monitorexit
      //   43: aload_1
      //   44: athrow
      //   45: astore_1
      //   46: goto -5 -> 41
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	49	0	this	a
      //   17	19	1	localObject1	Object
      //   40	4	1	localThrowable1	Throwable
      //   45	1	1	localThrowable2	Throwable
      //   7	35	2	localObject2	Object
      // Exception table:
      //   from	to	target	type
      //   10	18	40	java/lang/Throwable
      //   18	31	45	java/lang/Throwable
      //   41	43	45	java/lang/Throwable
    }
  }
  
  private abstract class b
  {
    final k<T> a;
    int k = -1;
    boolean l;
    
    b(Context paramContext)
    {
      a = paramContext;
    }
    
    void b(boolean paramBoolean)
    {
      if (paramBoolean == l) {
        return;
      }
      l = paramBoolean;
      int i = LiveData.c(LiveData.this);
      int j = 1;
      if (i == 0) {
        i = 1;
      } else {
        i = 0;
      }
      LiveData localLiveData = LiveData.this;
      int m = LiveData.c(localLiveData);
      if (!l) {
        j = -1;
      }
      LiveData.a(localLiveData, m + j);
      if ((i != 0) && (l)) {
        start();
      }
      if ((LiveData.c(LiveData.this) == 0) && (!l)) {
        stop();
      }
      if (l) {
        LiveData.c(LiveData.this, this);
      }
    }
    
    abstract boolean b();
    
    boolean b(h paramH)
    {
      return false;
    }
    
    void c() {}
  }
}
