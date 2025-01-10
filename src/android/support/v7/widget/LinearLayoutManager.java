package android.support.v7.widget;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v7.widget.helper.ItemTouchHelper.ViewDropHandler;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;
import java.util.List;

public class LinearLayoutManager
  extends RecyclerView.o
  implements ItemTouchHelper.ViewDropHandler, RecyclerView.z.a
{
  MethodWriter a;
  int b = -1;
  boolean c = false;
  d d = null;
  final a e = new a();
  private boolean g;
  int i = Integer.MIN_VALUE;
  int mOrientation = 1;
  private boolean mRecycleChildrenOnDetach;
  private boolean mReverseLayout = false;
  private c n;
  private boolean p = false;
  private final b q = new b();
  private boolean s = true;
  private int z = 2;
  
  public LinearLayoutManager(Context paramContext)
  {
    this(paramContext, 1, false);
  }
  
  public LinearLayoutManager(Context paramContext, int paramInt, boolean paramBoolean)
  {
    setOrientation(paramInt);
    setReverseLayout(paramBoolean);
  }
  
  public LinearLayoutManager(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    paramContext = RecyclerView.o.getProperties(paramContext, paramAttributeSet, paramInt1, paramInt2);
    setOrientation(orientation);
    setReverseLayout(reverseLayout);
    setStackFromEnd(stackFromEnd);
  }
  
  private int a(int paramInt, RecyclerView.v paramV, RecyclerView.a0 paramA0, boolean paramBoolean)
  {
    int j = paramInt - a.f();
    if (j > 0)
    {
      j = -a(j, paramV, paramA0);
      if (paramBoolean)
      {
        paramInt = paramInt + j - a.f();
        if (paramInt > 0)
        {
          a.a(-paramInt);
          return j - paramInt;
        }
      }
      else
      {
        return j;
      }
    }
    else
    {
      return 0;
    }
    return j;
  }
  
  private int a(RecyclerView.a0 paramA0)
  {
    if (getChildCount() == 0) {
      return 0;
    }
    d();
    return d.b(paramA0, a, b(s ^ true, true), a(s ^ true, true), this, s);
  }
  
  private View a(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (c) {
      return a(0, getChildCount(), paramBoolean1, paramBoolean2);
    }
    return a(getChildCount() - 1, -1, paramBoolean1, paramBoolean2);
  }
  
  private void a(int paramInt1, int paramInt2)
  {
    n.e = (paramInt2 - a.f());
    c localC = n;
    f = paramInt1;
    if (c) {
      paramInt1 = 1;
    } else {
      paramInt1 = -1;
    }
    b = paramInt1;
    localC = n;
    j = -1;
    k = paramInt2;
    i = Integer.MIN_VALUE;
  }
  
  private void a(int paramInt1, int paramInt2, boolean paramBoolean, RecyclerView.a0 paramA0)
  {
    n.d = f();
    n.c = c(paramA0);
    paramA0 = n;
    j = paramInt1;
    int j = -1;
    c localC1;
    c localC2;
    if (paramInt1 == 1)
    {
      c += a.b();
      paramA0 = c();
      localC1 = n;
      if (!c) {
        j = 1;
      }
      b = j;
      localC1 = n;
      paramInt1 = a(paramA0);
      localC2 = n;
      f = (paramInt1 + b);
      k = a.a(paramA0);
      paramInt1 = a.a(paramA0) - a.a();
    }
    else
    {
      paramA0 = b();
      localC1 = n;
      c += a.f();
      localC1 = n;
      if (c) {
        j = 1;
      }
      b = j;
      localC1 = n;
      paramInt1 = a(paramA0);
      localC2 = n;
      f = (paramInt1 + b);
      k = a.f(paramA0);
      paramInt1 = -a.f(paramA0) + a.f();
    }
    paramA0 = n;
    e = paramInt2;
    if (paramBoolean) {
      e -= paramInt1;
    }
    n.i = paramInt1;
  }
  
  private void a(RecyclerView.v paramV, int paramInt)
  {
    if (paramInt < 0) {
      return;
    }
    int k = getChildCount();
    View localView;
    if (c)
    {
      j = k - 1;
      while (j >= 0)
      {
        localView = getChildAt(j);
        if ((a.a(localView) <= paramInt) && (a.b(localView) <= paramInt)) {
          j -= 1;
        } else {
          a(paramV, k - 1, j);
        }
      }
      return;
    }
    int j = 0;
    while (j < k)
    {
      localView = getChildAt(j);
      if ((a.a(localView) <= paramInt) && (a.b(localView) <= paramInt)) {
        j += 1;
      } else {
        a(paramV, 0, j);
      }
    }
  }
  
  private void a(RecyclerView.v paramV, int paramInt1, int paramInt2)
  {
    if (paramInt1 == paramInt2) {
      return;
    }
    int j = paramInt1;
    if (paramInt2 > paramInt1)
    {
      paramInt2 -= 1;
      while (paramInt2 >= paramInt1)
      {
        b(paramInt2, paramV);
        paramInt2 -= 1;
      }
      return;
    }
    while (j > paramInt2)
    {
      b(j, paramV);
      j -= 1;
    }
  }
  
  private void a(RecyclerView.v paramV, RecyclerView.a0 paramA0, a paramA)
  {
    if (b(paramA0, paramA)) {
      return;
    }
    if (b(paramV, paramA0, paramA)) {
      return;
    }
    paramA.b();
    int j;
    if (p) {
      j = paramA0.size() - 1;
    } else {
      j = 0;
    }
    w = j;
  }
  
  private int b(int paramInt, RecyclerView.v paramV, RecyclerView.a0 paramA0, boolean paramBoolean)
  {
    int j = a.a() - paramInt;
    if (j > 0)
    {
      j = -a(-j, paramV, paramA0);
      if (paramBoolean)
      {
        paramInt = a.a() - (paramInt + j);
        if (paramInt > 0)
        {
          a.a(paramInt);
          return paramInt + j;
        }
      }
      else
      {
        return j;
      }
    }
    else
    {
      return 0;
    }
    return j;
  }
  
  private int b(RecyclerView.a0 paramA0)
  {
    if (getChildCount() == 0) {
      return 0;
    }
    d();
    return d.a(paramA0, a, b(s ^ true, true), a(s ^ true, true), this, s);
  }
  
  private View b()
  {
    int j;
    if (c) {
      j = getChildCount() - 1;
    } else {
      j = 0;
    }
    return getChildAt(j);
  }
  
  private View b(RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    return a(paramV, paramA0, 0, getChildCount(), paramA0.size());
  }
  
  private View b(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (c) {
      return a(getChildCount() - 1, -1, paramBoolean1, paramBoolean2);
    }
    return a(0, getChildCount(), paramBoolean1, paramBoolean2);
  }
  
  private void b(a paramA)
  {
    a(w, x);
  }
  
  private void b(RecyclerView.v paramV, int paramInt)
  {
    int j = getChildCount();
    if (paramInt < 0) {
      return;
    }
    int k = a.e() - paramInt;
    View localView;
    if (c)
    {
      paramInt = 0;
      while (paramInt < j)
      {
        localView = getChildAt(paramInt);
        if ((a.f(localView) >= k) && (a.c(localView) >= k)) {
          paramInt += 1;
        } else {
          a(paramV, 0, paramInt);
        }
      }
      return;
    }
    paramInt = j - 1;
    while (paramInt >= 0)
    {
      localView = getChildAt(paramInt);
      if ((a.f(localView) >= k) && (a.c(localView) >= k)) {
        paramInt -= 1;
      } else {
        a(paramV, j - 1, paramInt);
      }
    }
  }
  
  private void b(RecyclerView.v paramV, c paramC)
  {
    if (w)
    {
      if (d) {
        return;
      }
      if (j == -1)
      {
        b(paramV, i);
        return;
      }
      a(paramV, i);
    }
  }
  
  private void b(RecyclerView.v paramV, RecyclerView.a0 paramA0, int paramInt1, int paramInt2)
  {
    if ((paramA0.willRunPredictiveAnimations()) && (getChildCount() != 0) && (!paramA0.get()))
    {
      if (!supportsPredictiveItemAnimations()) {
        return;
      }
      int k = 0;
      int m = 0;
      Object localObject = paramV.getValue();
      int i2 = ((List)localObject).size();
      int i3 = a(getChildAt(0));
      int j = 0;
      while (j < i2)
      {
        RecyclerView.d0 localD0 = (RecyclerView.d0)((List)localObject).get(j);
        if (!localD0.isRemoved())
        {
          int i4 = localD0.getLayoutPosition();
          int i1 = 1;
          int i5;
          if (i4 < i3) {
            i5 = 1;
          } else {
            i5 = 0;
          }
          if (i5 != c) {
            i1 = -1;
          }
          if (i1 == -1) {
            k += a.remove(itemView);
          } else {
            m += a.remove(itemView);
          }
        }
        j += 1;
      }
      n.a = ((List)localObject);
      if (k > 0)
      {
        a(a(b()), paramInt1);
        localObject = n;
        c = k;
        e = 0;
        ((c)localObject).setMenu();
        a(paramV, n, paramA0, false);
      }
      if (m > 0)
      {
        d(a(c()), paramInt2);
        localObject = n;
        c = m;
        e = 0;
        ((c)localObject).setMenu();
        a(paramV, n, paramA0, false);
      }
      n.a = null;
    }
  }
  
  private boolean b(RecyclerView.a0 paramA0, a paramA)
  {
    boolean bool1 = paramA0.get();
    boolean bool2 = false;
    if (!bool1)
    {
      int j = b;
      if (j == -1) {
        return false;
      }
      if ((j >= 0) && (j < paramA0.size()))
      {
        w = b;
        paramA0 = d;
        if ((paramA0 != null) && (paramA0.b()))
        {
          c = d.e;
          if (c)
          {
            x = (a.a() - d.d);
            return true;
          }
          x = (a.f() + d.d);
          return true;
        }
        if (i == Integer.MIN_VALUE)
        {
          paramA0 = findViewByPosition(b);
          if (paramA0 != null)
          {
            if (a.remove(paramA0) > a.get())
            {
              paramA.b();
              return true;
            }
            if (a.f(paramA0) - a.f() < 0)
            {
              x = a.f();
              c = false;
              return true;
            }
            if (a.a() - a.a(paramA0) < 0)
            {
              x = a.a();
              c = true;
              return true;
            }
            if (c) {
              j = a.a(paramA0) + a.d();
            } else {
              j = a.f(paramA0);
            }
            x = j;
            return true;
          }
          if (getChildCount() > 0)
          {
            j = a(getChildAt(0));
            if (b < j) {
              bool1 = true;
            } else {
              bool1 = false;
            }
            if (bool1 == c) {
              bool2 = true;
            }
            c = bool2;
          }
          paramA.b();
          return true;
        }
        bool1 = c;
        c = bool1;
        if (bool1)
        {
          x = (a.a() - i);
          return true;
        }
        x = (a.f() + i);
        return true;
      }
      b = -1;
      i = Integer.MIN_VALUE;
    }
    return false;
  }
  
  private boolean b(RecyclerView.v paramV, RecyclerView.a0 paramA0, a paramA)
  {
    int k = getChildCount();
    int j = 0;
    if (k == 0) {
      return false;
    }
    View localView = getFocusedChild();
    if ((localView != null) && (paramA.c(localView, paramA0)))
    {
      paramA.b(localView, a(localView));
      return true;
    }
    if (g != p) {
      return false;
    }
    if (c) {
      paramV = d(paramV, paramA0);
    } else {
      paramV = f(paramV, paramA0);
    }
    if (paramV != null)
    {
      paramA.a(paramV, a(paramV));
      if ((!paramA0.get()) && (supportsPredictiveItemAnimations()))
      {
        if ((a.f(paramV) >= a.a()) || (a.a(paramV) < a.f())) {
          j = 1;
        }
        if (j != 0)
        {
          if (c) {
            j = a.a();
          } else {
            j = a.f();
          }
          x = j;
          return true;
        }
      }
    }
    else
    {
      return false;
    }
    return true;
  }
  
  private View c()
  {
    int j;
    if (c) {
      j = 0;
    } else {
      j = getChildCount() - 1;
    }
    return getChildAt(j);
  }
  
  private View c(RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    return a(paramV, paramA0, getChildCount() - 1, -1, paramA0.size());
  }
  
  private void c(a paramA)
  {
    d(w, x);
  }
  
  private int d(RecyclerView.a0 paramA0)
  {
    if (getChildCount() == 0) {
      return 0;
    }
    d();
    return d.a(paramA0, a, b(s ^ true, true), a(s ^ true, true), this, s, c);
  }
  
  private View d(RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    if (c) {
      return b(paramV, paramA0);
    }
    return c(paramV, paramA0);
  }
  
  private void d(int paramInt1, int paramInt2)
  {
    n.e = (a.a() - paramInt2);
    c localC = n;
    int j;
    if (c) {
      j = -1;
    } else {
      j = 1;
    }
    b = j;
    localC = n;
    f = paramInt1;
    j = 1;
    k = paramInt2;
    i = Integer.MIN_VALUE;
  }
  
  private View f(RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    if (c) {
      return c(paramV, paramA0);
    }
    return b(paramV, paramA0);
  }
  
  private View findFirstReferenceChild(RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    return b(0, getChildCount());
  }
  
  private View findLastReferenceChild(RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    return b(getChildCount() - 1, -1);
  }
  
  private View findReferenceChildClosestToEnd(RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    if (c) {
      return findFirstReferenceChild(paramV, paramA0);
    }
    return findLastReferenceChild(paramV, paramA0);
  }
  
  private View findReferenceChildClosestToStart(RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    if (c) {
      return findLastReferenceChild(paramV, paramA0);
    }
    return findFirstReferenceChild(paramV, paramA0);
  }
  
  private void onSaveInstanceState()
  {
    if ((mOrientation != 1) && (isLayoutRTL()))
    {
      c = (mReverseLayout ^ true);
      return;
    }
    c = mReverseLayout;
  }
  
  int a(int paramInt, RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    if (getChildCount() != 0)
    {
      if (paramInt == 0) {
        return 0;
      }
      n.w = true;
      d();
      int j;
      if (paramInt > 0) {
        j = 1;
      } else {
        j = -1;
      }
      int k = Math.abs(paramInt);
      a(j, k, true, paramA0);
      c localC = n;
      int m = i + a(paramV, localC, paramA0, false);
      if (m < 0) {
        return 0;
      }
      if (k > m) {
        paramInt = j * m;
      }
      a.a(-paramInt);
      n.u = paramInt;
      return paramInt;
    }
    return 0;
  }
  
  int a(RecyclerView.v paramV, c paramC, RecyclerView.a0 paramA0, boolean paramBoolean)
  {
    int m = e;
    int j = i;
    int k;
    if (j != Integer.MIN_VALUE)
    {
      k = e;
      if (k < 0) {
        i = (j + k);
      }
      b(paramV, paramC);
    }
    j = e + c;
    b localB = q;
    do
    {
      do
      {
        if (((!d) && (j <= 0)) || (!paramC.next(paramA0))) {
          break;
        }
        localB.a();
        layoutChunk(paramV, paramA0, paramC, localB);
        if (c) {
          break;
        }
        k += j * j;
        if ((i) && (n.a == null))
        {
          k = j;
          if (paramA0.get()) {}
        }
        else
        {
          k = e;
          int i1 = j;
          e = (k - i1);
          k = j - i1;
        }
        j = i;
        if (j != Integer.MIN_VALUE)
        {
          i = (j + j);
          j = e;
          if (j < 0) {
            i += j;
          }
          b(paramV, paramC);
        }
        j = k;
      } while (!paramBoolean);
      j = k;
    } while (!d);
    return m - e;
  }
  
  public Parcelable a()
  {
    d localD = d;
    if (localD != null) {
      return new d(localD);
    }
    localD = new d();
    if (getChildCount() > 0)
    {
      d();
      boolean bool = g ^ c;
      e = bool;
      if (bool)
      {
        localView = c();
        d = (a.a() - a.a(localView));
        c = a(localView);
        return localD;
      }
      View localView = b();
      c = a(localView);
      d = (a.f(localView) - a.f());
      return localD;
    }
    localD.a();
    return localD;
  }
  
  View a(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    d();
    int k = 0;
    int j;
    if (paramBoolean1) {
      j = 24579;
    } else {
      j = 320;
    }
    if (paramBoolean2) {
      k = 320;
    }
    if (mOrientation == 0) {
      return a.a(paramInt1, paramInt2, j, k);
    }
    return u.a(paramInt1, paramInt2, j, k);
  }
  
  View a(RecyclerView.v paramV, RecyclerView.a0 paramA0, int paramInt1, int paramInt2, int paramInt3)
  {
    d();
    paramV = null;
    paramA0 = null;
    int k = a.f();
    int m = a.a();
    int j;
    if (paramInt2 > paramInt1) {
      j = 1;
    } else {
      j = -1;
    }
    while (paramInt1 != paramInt2)
    {
      View localView = getChildAt(paramInt1);
      int i1 = a(localView);
      Object localObject1 = paramV;
      Object localObject2 = paramA0;
      if (i1 >= 0)
      {
        localObject1 = paramV;
        localObject2 = paramA0;
        if (i1 < paramInt3) {
          if (((RecyclerView.p)localView.getLayoutParams()).next())
          {
            localObject1 = paramV;
            localObject2 = paramA0;
            if (paramV == null)
            {
              localObject1 = localView;
              localObject2 = paramA0;
            }
          }
          else
          {
            if ((a.f(localView) < m) && (a.a(localView) >= k)) {
              return localView;
            }
            localObject1 = paramV;
            localObject2 = paramA0;
            if (paramA0 == null)
            {
              localObject2 = localView;
              localObject1 = paramV;
            }
          }
        }
      }
      paramInt1 += j;
      paramV = (RecyclerView.v)localObject1;
      paramA0 = (RecyclerView.a0)localObject2;
    }
    if (paramA0 != null) {
      return paramA0;
    }
    return paramV;
  }
  
  public View a(View paramView, int paramInt, RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    onSaveInstanceState();
    if (getChildCount() == 0) {
      return null;
    }
    paramInt = fill(paramInt);
    if (paramInt == Integer.MIN_VALUE) {
      return null;
    }
    d();
    d();
    a(paramInt, (int)(a.get() * 0.33333334F), false, paramA0);
    paramView = n;
    i = Integer.MIN_VALUE;
    w = false;
    a(paramV, paramView, paramA0, true);
    if (paramInt == -1) {
      paramView = findReferenceChildClosestToStart(paramV, paramA0);
    } else {
      paramView = findReferenceChildClosestToEnd(paramV, paramA0);
    }
    if (paramInt == -1) {
      paramV = b();
    } else {
      paramV = c();
    }
    if (paramV.hasFocusable())
    {
      if (paramView == null) {
        return null;
      }
      return paramV;
    }
    return paramView;
  }
  
  public void a(int paramInt1, int paramInt2, RecyclerView.a0 paramA0, RecyclerView.o.c paramC)
  {
    if (mOrientation != 0) {
      paramInt1 = paramInt2;
    }
    if (getChildCount() != 0)
    {
      if (paramInt1 == 0) {
        return;
      }
      d();
      if (paramInt1 > 0) {
        paramInt2 = 1;
      } else {
        paramInt2 = -1;
      }
      a(paramInt2, Math.abs(paramInt1), true, paramA0);
      b(paramA0, n, paramC);
    }
  }
  
  public void a(RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    Object localObject = d;
    int m = -1;
    if (((localObject != null) || (b != -1)) && (paramA0.size() == 0))
    {
      removeAndRecycleAllViews(paramV);
      return;
    }
    localObject = d;
    if ((localObject != null) && (((d)localObject).b())) {
      b = d.c;
    }
    d();
    n.w = false;
    onSaveInstanceState();
    localObject = getFocusedChild();
    if ((e.b) && (b == -1) && (d == null))
    {
      if ((localObject != null) && ((a.f((View)localObject) >= a.a()) || (a.a((View)localObject) <= a.f()))) {
        e.b((View)localObject, a((View)localObject));
      }
    }
    else
    {
      e.reset();
      localObject = e;
      c = (c ^ p);
      a(paramV, paramA0, (a)localObject);
      e.b = true;
    }
    int j = c(paramA0);
    if (n.u >= 0)
    {
      k = 0;
    }
    else
    {
      i1 = 0;
      k = j;
      j = i1;
    }
    int i1 = k + a.f();
    int i2 = j + a.b();
    j = i2;
    int k = i1;
    if (paramA0.get())
    {
      int i3 = b;
      j = i2;
      k = i1;
      if (i3 != -1)
      {
        j = i2;
        k = i1;
        if (i != Integer.MIN_VALUE)
        {
          localObject = findViewByPosition(i3);
          j = i2;
          k = i1;
          if (localObject != null)
          {
            if (c)
            {
              j = a.a() - a.a((View)localObject) - i;
            }
            else
            {
              j = a.f((View)localObject);
              k = a.f();
              j = i - (j - k);
            }
            if (j > 0)
            {
              k = i1 + j;
              j = i2;
            }
            else
            {
              j = i2 - j;
              k = i1;
            }
          }
        }
      }
    }
    if (e.c)
    {
      if (c) {
        m = 1;
      }
    }
    else if (!c) {
      m = 1;
    }
    a(paramV, paramA0, e, m);
    detachAndScrapAttachedViews(paramV);
    n.d = f();
    n.r = paramA0.get();
    localObject = e;
    if (c)
    {
      b((a)localObject);
      localObject = n;
      c = k;
      a(paramV, (c)localObject, paramA0, false);
      localObject = n;
      m = k;
      i1 = f;
      i2 = e;
      k = j;
      if (i2 > 0) {
        k = j + i2;
      }
      c(e);
      localObject = n;
      c = k;
      f += b;
      a(paramV, (c)localObject, paramA0, false);
      localObject = n;
      j = k;
      k = m;
      if (e > 0)
      {
        k = e;
        a(i1, m);
        localObject = n;
        c = k;
        a(paramV, (c)localObject, paramA0, false);
        k = n.k;
      }
    }
    else
    {
      c((a)localObject);
      localObject = n;
      c = j;
      a(paramV, (c)localObject, paramA0, false);
      localObject = n;
      m = k;
      i2 = f;
      i1 = e;
      j = k;
      if (i1 > 0) {
        j = k + i1;
      }
      b(e);
      localObject = n;
      c = j;
      f += b;
      a(paramV, (c)localObject, paramA0, false);
      localObject = n;
      i1 = k;
      j = m;
      k = i1;
      if (e > 0)
      {
        j = e;
        d(i2, m);
        localObject = n;
        c = j;
        a(paramV, (c)localObject, paramA0, false);
        j = n.k;
        k = i1;
      }
    }
    m = j;
    i1 = k;
    if (getChildCount() > 0) {
      if ((c ^ p))
      {
        m = b(j, paramV, paramA0, true);
        i1 = k + m;
        k = a(i1, paramV, paramA0, false);
        i1 += k;
        m = j + m + k;
      }
      else
      {
        m = a(k, paramV, paramA0, true);
        j += m;
        i2 = b(j, paramV, paramA0, false);
        i1 = k + m + i2;
        m = j + i2;
      }
    }
    b(paramV, paramA0, i1, m);
    if (!paramA0.get()) {
      a.visitLabel();
    } else {
      e.reset();
    }
    g = p;
  }
  
  void a(RecyclerView.v paramV, RecyclerView.a0 paramA0, a paramA, int paramInt) {}
  
  public void a(RecyclerView paramRecyclerView, RecyclerView.v paramV)
  {
    super.a(paramRecyclerView, paramV);
    if (mRecycleChildrenOnDetach)
    {
      removeAndRecycleAllViews(paramV);
      paramV.clear();
    }
  }
  
  public void assertNotInLayoutOrScroll(String paramString)
  {
    if (d == null) {
      super.assertNotInLayoutOrScroll(paramString);
    }
  }
  
  View b(int paramInt1, int paramInt2)
  {
    d();
    int j;
    if (paramInt2 > paramInt1) {
      j = 1;
    } else if (paramInt2 < paramInt1) {
      j = -1;
    } else {
      j = 0;
    }
    if (j == 0) {
      return getChildAt(paramInt1);
    }
    int k;
    if (a.f(getChildAt(paramInt1)) < a.f())
    {
      j = 16644;
      k = 16388;
    }
    else
    {
      j = 4161;
      k = 4097;
    }
    if (mOrientation == 0) {
      return a.a(paramInt1, paramInt2, j, k);
    }
    return u.a(paramInt1, paramInt2, j, k);
  }
  
  public void b(int paramInt, RecyclerView.o.c paramC)
  {
    d localD = d;
    int k = -1;
    boolean bool;
    if ((localD != null) && (localD.b()))
    {
      localD = d;
      bool = e;
      j = c;
    }
    else
    {
      onSaveInstanceState();
      bool = c;
      if (b == -1)
      {
        if (bool) {
          j = paramInt - 1;
        } else {
          j = 0;
        }
      }
      else {
        j = b;
      }
    }
    if (!bool) {
      k = 1;
    }
    int m = j;
    int j = 0;
    while ((j < z) && (m >= 0) && (m < paramInt))
    {
      paramC.a(m, 0);
      m += k;
      j += 1;
    }
  }
  
  void b(RecyclerView.a0 paramA0, c paramC, RecyclerView.o.c paramC1)
  {
    int j = f;
    if ((j >= 0) && (j < paramA0.size())) {
      paramC1.a(j, Math.max(0, i));
    }
  }
  
  protected int c(RecyclerView.a0 paramA0)
  {
    if (paramA0.has()) {
      return a.get();
    }
    return 0;
  }
  
  public boolean canScrollHorizontally()
  {
    return mOrientation == 0;
  }
  
  public boolean canScrollVertically()
  {
    return mOrientation == 1;
  }
  
  public int computeHorizontalScrollExtent(RecyclerView.a0 paramA0)
  {
    return b(paramA0);
  }
  
  public int computeHorizontalScrollOffset(RecyclerView.a0 paramA0)
  {
    return d(paramA0);
  }
  
  public int computeHorizontalScrollRange(RecyclerView.a0 paramA0)
  {
    return a(paramA0);
  }
  
  public int computeVerticalScrollExtent(RecyclerView.a0 paramA0)
  {
    return b(paramA0);
  }
  
  public int computeVerticalScrollOffset(RecyclerView.a0 paramA0)
  {
    return d(paramA0);
  }
  
  public int computeVerticalScrollRange(RecyclerView.a0 paramA0)
  {
    return a(paramA0);
  }
  
  void d()
  {
    if (n == null) {
      n = log();
    }
  }
  
  public void e(int paramInt)
  {
    b = paramInt;
    i = Integer.MIN_VALUE;
    d localD = d;
    if (localD != null) {
      localD.a();
    }
    requestLayout();
  }
  
  boolean f()
  {
    return (a.remove() == 0) && (a.e() == 0);
  }
  
  int fill(int paramInt)
  {
    if (paramInt != 1)
    {
      if (paramInt != 2)
      {
        if (paramInt != 17)
        {
          if (paramInt != 33)
          {
            if (paramInt != 66)
            {
              if (paramInt != 130) {
                return Integer.MIN_VALUE;
              }
              if (mOrientation == 1) {
                return 1;
              }
              return Integer.MIN_VALUE;
            }
            if (mOrientation == 0) {
              return 1;
            }
            return Integer.MIN_VALUE;
          }
          if (mOrientation == 1) {
            return -1;
          }
          return Integer.MIN_VALUE;
        }
        if (mOrientation == 0) {
          return -1;
        }
        return Integer.MIN_VALUE;
      }
      if (mOrientation == 1) {
        return 1;
      }
      if (isLayoutRTL()) {
        return -1;
      }
      return 1;
    }
    if (mOrientation == 1) {
      return -1;
    }
    if (isLayoutRTL()) {
      return 1;
    }
    return -1;
  }
  
  public int findFirstVisibleItemPosition()
  {
    View localView = a(0, getChildCount(), false, true);
    if (localView == null) {
      return -1;
    }
    return a(localView);
  }
  
  public int findLastVisibleItemPosition()
  {
    View localView = a(getChildCount() - 1, -1, false, true);
    if (localView == null) {
      return -1;
    }
    return a(localView);
  }
  
  public View findViewByPosition(int paramInt)
  {
    int j = getChildCount();
    if (j == 0) {
      return null;
    }
    int k = paramInt - a(getChildAt(0));
    if ((k >= 0) && (k < j))
    {
      View localView = getChildAt(k);
      if (a(localView) == paramInt) {
        return localView;
      }
    }
    return super.findViewByPosition(paramInt);
  }
  
  public RecyclerView.p generateDefaultLayoutParams()
  {
    return new RecyclerView.p(-2, -2);
  }
  
  public int getOrientation()
  {
    return mOrientation;
  }
  
  protected boolean isLayoutRTL()
  {
    return getLayoutDirection() == 1;
  }
  
  void layoutChunk(RecyclerView.v paramV, RecyclerView.a0 paramA0, c paramC, b paramB)
  {
    paramV = paramC.next(paramV);
    if (paramV == null)
    {
      c = true;
      return;
    }
    paramA0 = (RecyclerView.p)paramV.getLayoutParams();
    boolean bool2;
    boolean bool1;
    if (a == null)
    {
      bool2 = c;
      if (j == -1) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      if (bool2 == bool1) {
        addView(paramV);
      } else {
        addView(paramV, 0);
      }
    }
    else
    {
      bool2 = c;
      if (j == -1) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      if (bool2 == bool1) {
        addDisappearingView(paramV);
      } else {
        addDisappearingView(paramV, 0);
      }
    }
    measureChildWithMargins(paramV, 0, 0);
    j = a.remove(paramV);
    int i1;
    int j;
    int k;
    int i2;
    int i3;
    int m;
    if (mOrientation == 1)
    {
      if (isLayoutRTL())
      {
        i1 = getWidth() - getPaddingRight();
        j = i1 - a.getDecoratedMeasurementInOther(paramV);
      }
      else
      {
        k = getPaddingLeft();
        j = k;
        i1 = a.getDecoratedMeasurementInOther(paramV) + k;
      }
      if (j == -1)
      {
        i2 = k;
        i3 = j;
        k = k;
        m = j;
        j = i2 - i3;
      }
      else
      {
        k = k + j;
        i2 = k;
        m = j;
        j = i2;
      }
    }
    else
    {
      k = getPaddingTop();
      j = a.getDecoratedMeasurementInOther(paramV) + k;
      if (j == -1)
      {
        i2 = k - j;
        i1 = k;
        m = j;
        j = k;
        k = m;
        m = i2;
      }
      else
      {
        i2 = k;
        i3 = j;
        i1 = k;
        m = k;
        i2 += i3;
        k = j;
        j = i1;
        i1 = i2;
      }
    }
    measureChildWithDecorationsAndMargin(paramV, m, j, i1, k);
    if ((paramA0.next()) || (paramA0.isItemChanged())) {
      i = true;
    }
    d = paramV.hasFocusable();
  }
  
  c log()
  {
    return new c();
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    if (getChildCount() > 0)
    {
      paramAccessibilityEvent.setFromIndex(findFirstVisibleItemPosition());
      paramAccessibilityEvent.setToIndex(findLastVisibleItemPosition());
    }
  }
  
  public void onLayoutChildren(RecyclerView.a0 paramA0)
  {
    super.onLayoutChildren(paramA0);
    d = null;
    b = -1;
    i = Integer.MIN_VALUE;
    e.reset();
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if ((paramParcelable instanceof d))
    {
      d = ((d)paramParcelable);
      requestLayout();
    }
  }
  
  public int scrollHorizontallyBy(int paramInt, RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    if (mOrientation == 1) {
      return 0;
    }
    return a(paramInt, paramV, paramA0);
  }
  
  public int scrollVerticallyBy(int paramInt, RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    if (mOrientation == 0) {
      return 0;
    }
    return a(paramInt, paramV, paramA0);
  }
  
  public void setOrientation(int paramInt)
  {
    if ((paramInt != 0) && (paramInt != 1))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("invalid orientation:");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    assertNotInLayoutOrScroll(null);
    if ((paramInt != mOrientation) || (a == null))
    {
      a = MethodWriter.a(this, paramInt);
      e.a = a;
      mOrientation = paramInt;
      requestLayout();
    }
  }
  
  public boolean setOrientation()
  {
    return true;
  }
  
  public void setReverseLayout(boolean paramBoolean)
  {
    assertNotInLayoutOrScroll(null);
    if (paramBoolean == mReverseLayout) {
      return;
    }
    mReverseLayout = paramBoolean;
    requestLayout();
  }
  
  public void setStackFromEnd(boolean paramBoolean)
  {
    assertNotInLayoutOrScroll(null);
    if (p == paramBoolean) {
      return;
    }
    p = paramBoolean;
    requestLayout();
  }
  
  boolean shouldMeasureTwice()
  {
    return (getHeightMode() != 1073741824) && (getWidthMode() != 1073741824) && (hasFlexibleChildInBothOrientations());
  }
  
  public boolean supportsPredictiveItemAnimations()
  {
    return (d == null) && (g == p);
  }
  
  static class a
  {
    MethodWriter a;
    boolean b;
    boolean c;
    int w;
    int x;
    
    a()
    {
      reset();
    }
    
    public void a(View paramView, int paramInt)
    {
      if (c) {
        x = (a.a(paramView) + a.d());
      } else {
        x = a.f(paramView);
      }
      w = paramInt;
    }
    
    void b()
    {
      int i;
      if (c) {
        i = a.a();
      } else {
        i = a.f();
      }
      x = i;
    }
    
    public void b(View paramView, int paramInt)
    {
      int i = a.d();
      if (i >= 0)
      {
        a(paramView, paramInt);
        return;
      }
      w = paramInt;
      int k;
      if (c)
      {
        paramInt = a.a() - i - a.a(paramView);
        x = (a.a() - paramInt);
        if (paramInt > 0)
        {
          i = a.remove(paramView);
          j = x;
          k = a.f();
          i = j - i - (Math.min(a.f(paramView) - k, 0) + k);
          if (i < 0) {
            x += Math.min(paramInt, -i);
          }
        }
        return;
      }
      int j = a.f(paramView);
      paramInt = j - a.f();
      x = j;
      if (paramInt > 0)
      {
        k = a.remove(paramView);
        int m = a.a();
        int n = a.a(paramView);
        i = a.a() - Math.min(0, m - i - n) - (k + j);
        if (i < 0) {
          x -= Math.min(paramInt, -i);
        }
      }
    }
    
    boolean c(View paramView, RecyclerView.a0 paramA0)
    {
      paramView = (RecyclerView.p)paramView.getLayoutParams();
      return (!paramView.next()) && (paramView.a() >= 0) && (paramView.a() < paramA0.size());
    }
    
    void reset()
    {
      w = -1;
      x = Integer.MIN_VALUE;
      c = false;
      b = false;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("AnchorInfo{mPosition=");
      localStringBuilder.append(w);
      localStringBuilder.append(", mCoordinate=");
      localStringBuilder.append(x);
      localStringBuilder.append(", mLayoutFromEnd=");
      localStringBuilder.append(c);
      localStringBuilder.append(", mValid=");
      localStringBuilder.append(b);
      localStringBuilder.append('}');
      return localStringBuilder.toString();
    }
  }
  
  protected static class b
  {
    public boolean c;
    public boolean d;
    public boolean i;
    public int j;
    
    protected b() {}
    
    void a()
    {
      j = 0;
      c = false;
      i = false;
      d = false;
    }
  }
  
  static class c
  {
    List<RecyclerView.d0> a = null;
    int b;
    int c = 0;
    boolean d;
    int e;
    int f;
    int i;
    int j;
    int k;
    boolean r;
    int u;
    boolean w = true;
    
    c() {}
    
    private View c()
    {
      int n = a.size();
      int m = 0;
      while (m < n)
      {
        View localView = a.get(m)).itemView;
        RecyclerView.p localP = (RecyclerView.p)localView.getLayoutParams();
        if ((!localP.next()) && (f == localP.a()))
        {
          b(localView);
          return localView;
        }
        m += 1;
      }
      return null;
    }
    
    public View a(View paramView)
    {
      Object localObject1 = a;
      Object localObject2 = this;
      int i3 = ((List)localObject1).size();
      localObject1 = null;
      int n = Integer.MAX_VALUE;
      int m = 0;
      while (m < i3)
      {
        Object localObject4 = a;
        Object localObject3 = localObject2;
        localObject4 = getitemView;
        RecyclerView.p localP = (RecyclerView.p)((View)localObject4).getLayoutParams();
        localObject2 = localObject1;
        int i1 = n;
        if (localObject4 != paramView) {
          if (localP.next())
          {
            localObject2 = localObject1;
            i1 = n;
          }
          else
          {
            int i2 = (localP.a() - f) * b;
            if (i2 < 0)
            {
              localObject2 = localObject1;
              i1 = n;
            }
            else
            {
              localObject2 = localObject1;
              i1 = n;
              if (i2 < n)
              {
                localObject2 = localObject4;
                i1 = i2;
                if (i2 == 0) {
                  return localObject4;
                }
              }
            }
          }
        }
        m += 1;
        localObject1 = localObject2;
        n = i1;
        localObject2 = localObject3;
      }
      return localObject1;
    }
    
    public void b(View paramView)
    {
      paramView = a(paramView);
      if (paramView == null)
      {
        f = -1;
        return;
      }
      f = ((RecyclerView.p)paramView.getLayoutParams()).a();
    }
    
    View next(RecyclerView.v paramV)
    {
      if (a != null) {
        return c();
      }
      paramV = paramV.get(f);
      f += b;
      return paramV;
    }
    
    boolean next(RecyclerView.a0 paramA0)
    {
      int m = f;
      return (m >= 0) && (m < paramA0.size());
    }
    
    public void setMenu()
    {
      b(null);
    }
  }
  
  public static class d
    implements Parcelable
  {
    public static final Parcelable.Creator<d> CREATOR = new a();
    int c;
    int d;
    boolean e;
    
    public d() {}
    
    d(Parcel paramParcel)
    {
      c = paramParcel.readInt();
      d = paramParcel.readInt();
      int i = paramParcel.readInt();
      boolean bool = true;
      if (i != 1) {
        bool = false;
      }
      e = bool;
    }
    
    public d(d paramD)
    {
      c = c;
      d = d;
      e = e;
    }
    
    void a()
    {
      c = -1;
    }
    
    boolean b()
    {
      return c >= 0;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
    
    static final class a
      implements Parcelable.Creator<LinearLayoutManager.d>
    {
      a() {}
      
      public LinearLayoutManager.d createFromParcel(Parcel paramParcel)
      {
        return new LinearLayoutManager.d(paramParcel);
      }
      
      public LinearLayoutManager.d[] newArray(int paramInt)
      {
        return new LinearLayoutManager.d[paramInt];
      }
    }
  }
}
