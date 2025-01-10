package android.support.v7.widget;

import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import org.org.jaxen.pattern.TraceCompat;

final class a
  implements Runnable
{
  static Comparator<m0.c> a = new m0.a();
  static final ThreadLocal<m0> t = new ThreadLocal();
  ArrayList<RecyclerView> c = new ArrayList();
  long e;
  private ArrayList<m0.c> l = new ArrayList();
  long n;
  
  a() {}
  
  private RecyclerView.d0 a(RecyclerView paramRecyclerView, int paramInt, long paramLong)
  {
    if (findViewHolderForPosition(paramRecyclerView, paramInt)) {
      return null;
    }
    RecyclerView.v localV = mRecycler;
    try
    {
      paramRecyclerView.onEnterLayoutOrScroll();
      RecyclerView.d0 localD0 = localV.getViewForPosition(paramInt, false, paramLong);
      if (localD0 != null) {
        try
        {
          boolean bool = localD0.isBound();
          if (bool)
          {
            bool = localD0.isInvalid();
            if (!bool)
            {
              localV.recycleView(itemView);
              break label88;
            }
          }
          localV.b(localD0, false);
        }
        catch (Throwable localThrowable1)
        {
          break label98;
        }
      }
      label88:
      paramRecyclerView.a(false);
      return localD0;
    }
    catch (Throwable localThrowable2)
    {
      label98:
      paramRecyclerView.a(false);
      throw localThrowable2;
    }
  }
  
  private void a()
  {
    Object localObject2 = c;
    Object localObject1 = this;
    int i1 = ((ArrayList)localObject2).size();
    int j = 0;
    int i = 0;
    int k;
    while (i < i1)
    {
      localObject2 = c;
      localObject2 = (RecyclerView)((ArrayList)localObject2).get(i);
      k = j;
      if (((View)localObject2).getWindowVisibility() == 0)
      {
        d.a((RecyclerView)localObject2, false);
        k = j + d.j;
      }
      i += 1;
      j = k;
    }
    localObject2 = l;
    ((ArrayList)localObject2).ensureCapacity(j);
    i = 0;
    j = 0;
    while (j < i1)
    {
      localObject2 = c;
      RecyclerView localRecyclerView = (RecyclerView)((ArrayList)localObject2).get(j);
      int m;
      if (localRecyclerView.getWindowVisibility() != 0)
      {
        m = i;
        localObject2 = localObject1;
      }
      else
      {
        m0.b localB = d;
        int i2 = Math.abs(d) + Math.abs(e);
        k = 0;
        for (;;)
        {
          m = i;
          localObject2 = localObject1;
          if (k >= j * 2) {
            break;
          }
          if (i >= l.size())
          {
            localObject2 = new m0.c();
            l.add(localObject2);
          }
          else
          {
            localObject2 = (m0.c)l.get(i);
          }
          m = o[(k + 1)];
          boolean bool;
          if (m <= i2) {
            bool = true;
          } else {
            bool = false;
          }
          o = bool;
          d = i2;
          g = m;
          l = localRecyclerView;
          s = o[k];
          i += 1;
          k += 2;
        }
      }
      j += 1;
      i = m;
      localObject1 = localObject2;
    }
    Collections.sort(l, a);
  }
  
  private void a(long paramLong)
  {
    int i = 0;
    while (i < l.size())
    {
      m0.c localC = (m0.c)l.get(i);
      if (l == null) {
        return;
      }
      b(localC, paramLong);
      localC.b();
      i += 1;
    }
  }
  
  private void a(RecyclerView paramRecyclerView, long paramLong)
  {
    if (paramRecyclerView == null) {
      return;
    }
    if ((mDataSetHasChangedAfterLayout) && (mChildHelper.getUnfilteredChildCount() != 0)) {
      paramRecyclerView.setAdapterInternal();
    }
    m0.b localB = d;
    localB.a(paramRecyclerView, true);
    if (j != 0) {
      try
      {
        TraceCompat.beginSection("RV Nested Prefetch");
        mState.a(mAdapter);
        int i = 0;
        for (;;)
        {
          int j = j;
          if (i >= j * 2) {
            break;
          }
          a(paramRecyclerView, o[i], paramLong);
          i += 2;
        }
        TraceCompat.endSection();
        return;
      }
      catch (Throwable paramRecyclerView)
      {
        TraceCompat.endSection();
        throw paramRecyclerView;
      }
    }
  }
  
  private void b(m0.c paramC, long paramLong)
  {
    long l1;
    if (o) {
      l1 = Long.MAX_VALUE;
    } else {
      l1 = paramLong;
    }
    paramC = a(l, s, l1);
    if ((paramC != null) && (l != null) && (paramC.isBound()) && (!paramC.isInvalid())) {
      a((RecyclerView)l.get(), paramLong);
    }
  }
  
  static boolean findViewHolderForPosition(RecyclerView paramRecyclerView, int paramInt)
  {
    int j = mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      RecyclerView.d0 localD0 = RecyclerView.getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
      if ((mPosition == paramInt) && (!localD0.isInvalid())) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public void a(RecyclerView paramRecyclerView)
  {
    c.add(paramRecyclerView);
  }
  
  void a(RecyclerView paramRecyclerView, int paramInt1, int paramInt2)
  {
    if ((paramRecyclerView.isAttachedToWindow()) && (e == 0L))
    {
      e = paramRecyclerView.getNanoTime();
      paramRecyclerView.post(this);
    }
    d.b(paramInt1, paramInt2);
  }
  
  public void close(RecyclerView paramRecyclerView)
  {
    c.remove(paramRecyclerView);
  }
  
  public void run()
  {
    try
    {
      TraceCompat.beginSection("RV Prefetch");
      boolean bool = c.isEmpty();
      if (bool)
      {
        e = 0L;
        TraceCompat.endSection();
        return;
      }
      int j = c.size();
      long l1 = 0L;
      int i = 0;
      while (i < j)
      {
        RecyclerView localRecyclerView = (RecyclerView)c.get(i);
        int k = localRecyclerView.getWindowVisibility();
        l2 = l1;
        if (k == 0) {
          l2 = Math.max(localRecyclerView.getDrawingTime(), l1);
        }
        i += 1;
        l1 = l2;
      }
      if (l1 == 0L)
      {
        e = 0L;
        TraceCompat.endSection();
        return;
      }
      l1 = TimeUnit.MILLISECONDS.toNanos(l1);
      long l2 = n;
      setValue(l1 + l2);
      e = 0L;
      TraceCompat.endSection();
      return;
    }
    catch (Throwable localThrowable)
    {
      e = 0L;
      TraceCompat.endSection();
      throw localThrowable;
    }
  }
  
  void setValue(long paramLong)
  {
    a();
    a(paramLong);
  }
}
