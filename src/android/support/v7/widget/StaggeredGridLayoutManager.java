package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class StaggeredGridLayoutManager
  extends RecyclerView.o
  implements RecyclerView.z.a
{
  MethodWriter B;
  MethodWriter a;
  f[] b;
  private int bottom = 2;
  private int c = -1;
  private BitSet d;
  private final Runnable e = new a();
  boolean f = false;
  private boolean g;
  private int h;
  private final Item i;
  boolean j = false;
  private final b k = new b();
  d l = new d();
  private int m;
  private final Rect mTmpRect = new Rect();
  private int mWidthSpec;
  int n = -1;
  private e o;
  private boolean p = false;
  int q = Integer.MIN_VALUE;
  private int[] r;
  private boolean s = true;
  private boolean y;
  
  public StaggeredGridLayoutManager(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    paramContext = RecyclerView.o.getProperties(paramContext, paramAttributeSet, paramInt1, paramInt2);
    setOrientation(orientation);
    setSpanCount(spanCount);
    setReverseLayout(reverseLayout);
    i = new Item();
    d();
  }
  
  private int a(int paramInt)
  {
    int i2 = b[0].a(paramInt);
    int i1 = 1;
    while (i1 < c)
    {
      int i4 = b[i1].a(paramInt);
      int i3 = i2;
      if (i4 > i2) {
        i3 = i4;
      }
      i1 += 1;
      i2 = i3;
    }
    return i2;
  }
  
  private int a(RecyclerView.a0 paramA0)
  {
    if (getChildCount() == 0) {
      return 0;
    }
    return d.b(paramA0, a, a(s ^ true), b(s ^ true), this, s);
  }
  
  private int a(RecyclerView.v paramV, Item paramItem, RecyclerView.a0 paramA0)
  {
    d.set(0, c, true);
    int i1;
    if (i.i)
    {
      if (j == 1) {
        i1 = Integer.MAX_VALUE;
      } else {
        i1 = Integer.MIN_VALUE;
      }
    }
    else if (j == 1) {
      i1 = d + g;
    } else {
      i1 = a - g;
    }
    a(j, i1);
    int i4;
    if (j) {
      i4 = a.a();
    } else {
      i4 = a.f();
    }
    int i3;
    for (int i2 = 0; paramItem.a(paramA0); i3 = 1)
    {
      if ((!i.i) && (d.isEmpty())) {
        break;
      }
      View localView = paramItem.next(paramV);
      c localC = (c)localView.getLayoutParams();
      int i8 = localC.a();
      i2 = l.d(i8);
      int i7;
      if (i2 == -1) {
        i7 = 1;
      } else {
        i7 = 0;
      }
      f localF;
      if (i7 != 0)
      {
        if (i) {
          localF = b[0];
        } else {
          localF = a(paramItem);
        }
        l.b(i8, localF);
      }
      else
      {
        localF = b[i2];
      }
      c = localF;
      if (j == 1) {
        addView(localView);
      } else {
        addView(localView, 0);
      }
      measureChildWithDecorationsAndMargin(localView, localC, false);
      int i6;
      StaggeredGridLayoutManager.d.a localA;
      int i5;
      if (j == 1)
      {
        if (i) {
          i2 = a(i4);
        } else {
          i2 = localF.a(i4);
        }
        i6 = a.remove(localView) + i2;
        if ((i7 != 0) && (i))
        {
          localA = b(i2);
          b = -1;
          a = i8;
          l.a(localA);
        }
        i5 = i2;
      }
      else
      {
        if (i) {
          i2 = f(i4);
        } else {
          i2 = localF.get(i4);
        }
        i5 = a.remove(localView);
        if ((i7 != 0) && (i))
        {
          localA = d(i2);
          b = 1;
          a = i8;
          l.a(localA);
        }
        i6 = i2;
        i5 = i2 - i5;
      }
      if ((i) && (k == -1)) {
        if (i7 != 0)
        {
          p = true;
        }
        else
        {
          boolean bool;
          if (j == 1) {
            bool = f() ^ true;
          } else {
            bool = equals() ^ true;
          }
          if (bool)
          {
            localA = l.a(i8);
            if (localA != null) {
              f = true;
            }
            p = true;
          }
        }
      }
      b(localView, localC, paramItem);
      if ((get()) && (h == 1))
      {
        if (i) {
          i3 = B.a();
        } else {
          i3 = B.a() - (c - 1 - i) * m;
        }
        i7 = i3 - B.remove(localView);
      }
      else
      {
        if (i) {
          i3 = B.f();
        } else {
          i3 = i * m + B.f();
        }
        i8 = B.remove(localView);
        i7 = i3;
        i3 = i8 + i3;
      }
      if (h == 1) {
        measureChildWithDecorationsAndMargin(localView, i7, i5, i3, i6);
      } else {
        measureChildWithDecorationsAndMargin(localView, i5, i7, i6, i3);
      }
      if (i) {
        a(i.j, i1);
      } else {
        a(localF, i.j, i1);
      }
      b(paramV, i);
      if ((i.h) && (localView.hasFocusable())) {
        if (i) {
          d.clear();
        } else {
          d.set(i, false);
        }
      }
    }
    if (i3 == 0) {
      b(paramV, i);
    }
    if (i.j == -1)
    {
      i1 = f(a.f());
      i1 = a.f() - i1;
    }
    else
    {
      i1 = a(a.a()) - a.a();
    }
    if (i1 > 0) {
      return Math.min(g, i1);
    }
    return 0;
  }
  
  private f a(Item paramItem)
  {
    int i1;
    int i2;
    int i3;
    if (add(j))
    {
      i1 = c - 1;
      i2 = -1;
      i3 = -1;
    }
    else
    {
      i1 = 0;
      i2 = c;
      i3 = 1;
    }
    f localF;
    int i6;
    int i5;
    if (j == 1)
    {
      paramItem = null;
      i4 = Integer.MAX_VALUE;
      i7 = a.f();
      while (i1 != i2)
      {
        localF = b[i1];
        i6 = localF.a(i7);
        i5 = i4;
        if (i6 < i4)
        {
          paramItem = localF;
          i5 = i6;
        }
        i1 += i3;
        i4 = i5;
      }
      return paramItem;
    }
    paramItem = null;
    int i4 = Integer.MIN_VALUE;
    int i7 = a.a();
    while (i1 != i2)
    {
      localF = b[i1];
      i6 = localF.get(i7);
      i5 = i4;
      if (i6 > i4)
      {
        paramItem = localF;
        i5 = i6;
      }
      i1 += i3;
      i4 = i5;
    }
    return paramItem;
  }
  
  private void a(int paramInt1, int paramInt2)
  {
    int i1 = 0;
    while (i1 < c)
    {
      if (!b[i1].c.isEmpty()) {
        a(b[i1], paramInt1, paramInt2);
      }
      i1 += 1;
    }
  }
  
  private void a(int paramInt, RecyclerView.a0 paramA0)
  {
    Item localItem = i;
    boolean bool2 = false;
    g = 0;
    c = paramInt;
    int i3 = 0;
    int i4 = 0;
    int i1 = i3;
    int i2 = i4;
    if (endChangeAnimationIfNecessary())
    {
      int i5 = paramA0.getTargetScrollPosition();
      i1 = i3;
      i2 = i4;
      if (i5 != -1)
      {
        boolean bool3 = j;
        if (i5 < paramInt) {
          bool1 = true;
        } else {
          bool1 = false;
        }
        if (bool3 == bool1)
        {
          i2 = a.get();
          i1 = i3;
        }
        else
        {
          i1 = a.get();
          i2 = i4;
        }
      }
    }
    if (shouldIgnore())
    {
      i.a = (a.f() - i1);
      i.d = (a.a() + i2);
    }
    else
    {
      i.d = (a.e() + i2);
      i.a = (-i1);
    }
    paramA0 = i;
    h = false;
    b = true;
    boolean bool1 = bool2;
    if (a.remove() == 0)
    {
      bool1 = bool2;
      if (a.e() == 0) {
        bool1 = true;
      }
    }
    i = bool1;
  }
  
  private void a(RecyclerView.v paramV, int paramInt)
  {
    while (getChildCount() > 0)
    {
      View localView = getChildAt(0);
      if ((a.a(localView) > paramInt) || (a.b(localView) > paramInt)) {
        break;
      }
      c localC = (c)localView.getLayoutParams();
      if (i)
      {
        int i1 = 0;
        while (i1 < c)
        {
          if (b[i1].c.size() == 1) {
            return;
          }
          i1 += 1;
        }
        i1 = 0;
        while (i1 < c)
        {
          b[i1].a();
          i1 += 1;
        }
      }
      else
      {
        if (c.c.size() == 1) {
          return;
        }
        c.a();
      }
      b(localView, paramV);
    }
  }
  
  private void a(RecyclerView.v paramV, RecyclerView.a0 paramA0, boolean paramBoolean)
  {
    b localB = k;
    if (((o != null) || (n != -1)) && (paramA0.size() == 0))
    {
      removeAndRecycleAllViews(paramV);
      localB.a();
      return;
    }
    boolean bool = i;
    int i2 = 1;
    if ((bool) && (n == -1) && (o == null)) {
      i1 = 0;
    } else {
      i1 = 1;
    }
    if (i1 != 0)
    {
      localB.a();
      if (o != null)
      {
        a(localB);
      }
      else
      {
        onSaveInstanceState();
        k = j;
      }
      b(paramA0, localB);
      i = true;
    }
    if ((o == null) && (n == -1) && ((k != g) || (get() != y)))
    {
      l.clear();
      e = true;
    }
    Object localObject;
    if (getChildCount() > 0)
    {
      localObject = o;
      if ((localObject == null) || (b < 1)) {
        if (e)
        {
          i1 = 0;
          while (i1 < c)
          {
            b[i1].i();
            i3 = a;
            if (i3 != Integer.MIN_VALUE) {
              b[i1].f(i3);
            }
            i1 += 1;
          }
        }
        else
        {
          if ((i1 == 0) && (k.o != null)) {
            i1 = 0;
          }
          while (i1 < c)
          {
            localObject = b[i1];
            ((f)localObject).i();
            ((f)localObject).f(k.o[i1]);
            i1 += 1;
            continue;
            i1 = 0;
            while (i1 < c)
            {
              b[i1].a(j, a);
              i1 += 1;
            }
            k.a(b);
          }
        }
      }
    }
    detachAndScrapAttachedViews(paramV);
    i.b = false;
    p = false;
    updateMeasureSpecs(B.get());
    a(c, paramA0);
    if (k)
    {
      init(-1);
      a(paramV, i, paramA0);
      init(1);
      localObject = i;
      c = (c + k);
      a(paramV, (Item)localObject, paramA0);
    }
    else
    {
      init(1);
      a(paramV, i, paramA0);
      init(-1);
      localObject = i;
      c = (c + k);
      a(paramV, (Item)localObject, paramA0);
    }
    update();
    if (getChildCount() > 0) {
      if (j)
      {
        d(paramV, paramA0, true);
        b(paramV, paramA0, false);
      }
      else
      {
        b(paramV, paramA0, true);
        d(paramV, paramA0, false);
      }
    }
    int i3 = 0;
    int i1 = i3;
    if (paramBoolean)
    {
      i1 = i3;
      if (!paramA0.get())
      {
        if ((bottom == 0) || (getChildCount() <= 0) || ((p) || (b() == null))) {
          i2 = 0;
        }
        i1 = i3;
        if (i2 != 0)
        {
          removeCallbacks(e);
          i1 = i3;
          if (close()) {
            i1 = 1;
          }
        }
      }
    }
    if (paramA0.get()) {
      k.a();
    }
    g = k;
    y = get();
    if (i1 != 0)
    {
      k.a();
      a(paramV, paramA0, false);
    }
  }
  
  private void a(b paramB)
  {
    Object localObject = o;
    int i1 = b;
    if (i1 > 0) {
      if (i1 == c)
      {
        int i2 = 0;
        while (i2 < c)
        {
          b[i2].i();
          localObject = o;
          int i3 = a[i2];
          i1 = i3;
          if (i3 != Integer.MIN_VALUE) {
            if (g) {
              i1 = i3 + a.a();
            } else {
              i1 = i3 + a.f();
            }
          }
          b[i2].f(i1);
          i2 += 1;
        }
      }
      else
      {
        ((e)localObject).d();
        localObject = o;
        c = y;
      }
    }
    localObject = o;
    y = x;
    setReverseLayout(f);
    onSaveInstanceState();
    localObject = o;
    i1 = c;
    if (i1 != -1)
    {
      n = i1;
      k = g;
    }
    else
    {
      k = j;
    }
    paramB = o;
    if (d > 1)
    {
      localObject = l;
      o = n;
      c = e;
    }
  }
  
  private void a(f paramF, int paramInt1, int paramInt2)
  {
    int i1 = paramF.size();
    if (paramInt1 == -1)
    {
      if (paramF.clear() + i1 <= paramInt2) {
        d.set(i, false);
      }
      return;
    }
    if (paramF.getItemId() - i1 >= paramInt2) {
      d.set(i, false);
    }
  }
  
  private boolean a(f paramF)
  {
    if (j)
    {
      if (paramF.getItemId() < a.a())
      {
        ArrayList localArrayList = c;
        return cgetsize1i ^ true;
      }
    }
    else if (paramF.clear() > a.f()) {
      return cc.get(0)).i ^ true;
    }
    return false;
  }
  
  private boolean add(int paramInt)
  {
    int i1;
    if (h == 0)
    {
      if (paramInt == -1) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      return i1 != j;
    }
    if (paramInt == -1) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    if (i1 == j) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    return i1 == get();
  }
  
  private int b(RecyclerView.a0 paramA0)
  {
    if (getChildCount() == 0) {
      return 0;
    }
    return d.a(paramA0, a, a(s ^ true), b(s ^ true), this, s, j);
  }
  
  private StaggeredGridLayoutManager.d.a b(int paramInt)
  {
    StaggeredGridLayoutManager.d.a localA = new StaggeredGridLayoutManager.d.a();
    c = new int[c];
    int i1 = 0;
    while (i1 < c)
    {
      c[i1] = (paramInt - b[i1].a(paramInt));
      i1 += 1;
    }
    return localA;
  }
  
  private void b(RecyclerView.v paramV, int paramInt)
  {
    int i1 = getChildCount() - 1;
    while (i1 >= 0)
    {
      View localView = getChildAt(i1);
      if ((a.f(localView) < paramInt) || (a.c(localView) < paramInt)) {
        break;
      }
      c localC = (c)localView.getLayoutParams();
      if (i)
      {
        int i2 = 0;
        while (i2 < c)
        {
          if (b[i2].c.size() == 1) {
            return;
          }
          i2 += 1;
        }
        i2 = 0;
        while (i2 < c)
        {
          b[i2].b();
          i2 += 1;
        }
      }
      else
      {
        if (c.c.size() == 1) {
          return;
        }
        c.b();
      }
      b(localView, paramV);
      i1 -= 1;
    }
  }
  
  private void b(RecyclerView.v paramV, Item paramItem)
  {
    if (b)
    {
      if (i) {
        return;
      }
      if (g == 0)
      {
        if (j == -1)
        {
          b(paramV, d);
          return;
        }
        a(paramV, a);
        return;
      }
      if (j == -1)
      {
        i1 = a;
        i1 -= equals(i1);
        if (i1 < 0) {
          i1 = d;
        } else {
          i1 = d - Math.min(i1, g);
        }
        b(paramV, i1);
        return;
      }
      int i1 = c(d) - d;
      if (i1 < 0) {
        i1 = a;
      } else {
        i1 = a + Math.min(i1, g);
      }
      a(paramV, i1);
    }
  }
  
  private void b(RecyclerView.v paramV, RecyclerView.a0 paramA0, boolean paramBoolean)
  {
    int i1 = f(Integer.MAX_VALUE);
    if (i1 == Integer.MAX_VALUE) {
      return;
    }
    i1 -= a.f();
    if (i1 > 0)
    {
      i1 -= a(i1, paramV, paramA0);
      if ((paramBoolean) && (i1 > 0)) {
        a.a(-i1);
      }
    }
  }
  
  private void b(View paramView, c paramC, Item paramItem)
  {
    if (j == 1)
    {
      if (i)
      {
        d(paramView);
        return;
      }
      c.a(paramView);
      return;
    }
    if (i)
    {
      c(paramView);
      return;
    }
    c.b(paramView);
  }
  
  private int c(int paramInt)
  {
    int i2 = b[0].a(paramInt);
    int i1 = 1;
    while (i1 < c)
    {
      int i4 = b[i1].a(paramInt);
      int i3 = i2;
      if (i4 < i2) {
        i3 = i4;
      }
      i1 += 1;
      i2 = i3;
    }
    return i2;
  }
  
  private void c(View paramView)
  {
    int i1 = c - 1;
    while (i1 >= 0)
    {
      b[i1].b(paramView);
      i1 -= 1;
    }
  }
  
  private int calculateScrollDirectionForPosition(int paramInt)
  {
    if (getChildCount() == 0)
    {
      if (j) {
        return 1;
      }
    }
    else
    {
      int i1;
      if (paramInt < c()) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      if (i1 != j) {
        return -1;
      }
      return 1;
    }
    return -1;
  }
  
  private int d(RecyclerView.a0 paramA0)
  {
    if (getChildCount() == 0) {
      return 0;
    }
    return d.a(paramA0, a, a(s ^ true), b(s ^ true), this, s);
  }
  
  private StaggeredGridLayoutManager.d.a d(int paramInt)
  {
    StaggeredGridLayoutManager.d.a localA = new StaggeredGridLayoutManager.d.a();
    c = new int[c];
    int i1 = 0;
    while (i1 < c)
    {
      c[i1] = (b[i1].get(paramInt) - paramInt);
      i1 += 1;
    }
    return localA;
  }
  
  private void d()
  {
    a = MethodWriter.a(this, h);
    B = MethodWriter.a(this, 1 - h);
  }
  
  private void d(RecyclerView.v paramV, RecyclerView.a0 paramA0, boolean paramBoolean)
  {
    int i1 = a(Integer.MIN_VALUE);
    if (i1 == Integer.MIN_VALUE) {
      return;
    }
    i1 = a.a() - i1;
    if (i1 > 0)
    {
      i1 -= -a(-i1, paramV, paramA0);
      if ((paramBoolean) && (i1 > 0)) {
        a.a(i1);
      }
    }
  }
  
  private void d(View paramView)
  {
    int i1 = c - 1;
    while (i1 >= 0)
    {
      b[i1].a(paramView);
      i1 -= 1;
    }
  }
  
  private int equals(int paramInt)
  {
    int i2 = b[0].get(paramInt);
    int i1 = 1;
    while (i1 < c)
    {
      int i4 = b[i1].get(paramInt);
      int i3 = i2;
      if (i4 > i2) {
        i3 = i4;
      }
      i1 += 1;
      i2 = i3;
    }
    return i2;
  }
  
  private int f(int paramInt)
  {
    int i2 = b[0].get(paramInt);
    int i1 = 1;
    while (i1 < c)
    {
      int i4 = b[i1].get(paramInt);
      int i3 = i2;
      if (i4 < i2) {
        i3 = i4;
      }
      i1 += 1;
      i2 = i3;
    }
    return i2;
  }
  
  private int findFirstReferenceChildPosition(int paramInt)
  {
    int i2 = getChildCount();
    int i1 = 0;
    while (i1 < i2)
    {
      int i3 = a(getChildAt(i1));
      if ((i3 >= 0) && (i3 < paramInt)) {
        return i3;
      }
      i1 += 1;
    }
    return 0;
  }
  
  private int findLastReferenceChildPosition(int paramInt)
  {
    int i1 = getChildCount() - 1;
    while (i1 >= 0)
    {
      int i2 = a(getChildAt(i1));
      if ((i2 >= 0) && (i2 < paramInt)) {
        return i2;
      }
      i1 -= 1;
    }
    return 0;
  }
  
  private void handleUpdate(int paramInt1, int paramInt2, int paramInt3)
  {
    int i3;
    if (j) {
      i3 = add();
    } else {
      i3 = c();
    }
    int i2;
    int i1;
    if (paramInt3 == 8)
    {
      if (paramInt1 < paramInt2)
      {
        i2 = paramInt2 + 1;
        i1 = paramInt1;
      }
      else
      {
        i2 = paramInt1 + 1;
        i1 = paramInt2;
      }
    }
    else
    {
      i1 = paramInt1;
      i2 = paramInt1 + paramInt2;
    }
    l.clear(i1);
    if (paramInt3 != 1)
    {
      if (paramInt3 != 2)
      {
        if (paramInt3 == 8)
        {
          l.a(paramInt1, 1);
          l.c(paramInt2, 1);
        }
      }
      else {
        l.a(paramInt1, paramInt2);
      }
    }
    else {
      l.c(paramInt1, paramInt2);
    }
    if (i2 <= i3) {
      return;
    }
    if (j) {
      paramInt1 = c();
    } else {
      paramInt1 = add();
    }
    if (i1 <= paramInt1) {
      requestLayout();
    }
  }
  
  private void init(int paramInt)
  {
    Item localItem = i;
    j = paramInt;
    boolean bool2 = j;
    int i1 = 1;
    boolean bool1;
    if (paramInt == -1) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    if (bool2 == bool1) {
      paramInt = i1;
    } else {
      paramInt = -1;
    }
    k = paramInt;
  }
  
  private void measureChildWithDecorationsAndMargin(View paramView, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    calculateItemDecorationsForChild(paramView, mTmpRect);
    c localC = (c)paramView.getLayoutParams();
    int i1 = leftMargin;
    Rect localRect = mTmpRect;
    paramInt1 = updateSpecWithExtra(paramInt1, i1 + left, rightMargin + right);
    i1 = topMargin;
    localRect = mTmpRect;
    paramInt2 = updateSpecWithExtra(paramInt2, i1 + top, bottomMargin + bottom);
    if (paramBoolean) {
      paramBoolean = shouldReMeasureChild(paramView, paramInt1, paramInt2, localC);
    } else {
      paramBoolean = shouldMeasureChild(paramView, paramInt1, paramInt2, localC);
    }
    if (paramBoolean) {
      paramView.measure(paramInt1, paramInt2);
    }
  }
  
  private void measureChildWithDecorationsAndMargin(View paramView, c paramC, boolean paramBoolean)
  {
    if (i)
    {
      if (h == 1)
      {
        measureChildWithDecorationsAndMargin(paramView, mWidthSpec, RecyclerView.o.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), height, true), paramBoolean);
        return;
      }
      measureChildWithDecorationsAndMargin(paramView, RecyclerView.o.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), width, true), mWidthSpec, paramBoolean);
      return;
    }
    if (h == 1)
    {
      measureChildWithDecorationsAndMargin(paramView, RecyclerView.o.getChildMeasureSpec(m, getWidthMode(), 0, width, false), RecyclerView.o.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), height, true), paramBoolean);
      return;
    }
    measureChildWithDecorationsAndMargin(paramView, RecyclerView.o.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), width, true), RecyclerView.o.getChildMeasureSpec(m, getHeightMode(), 0, height, false), paramBoolean);
  }
  
  private void onSaveInstanceState()
  {
    if ((h != 1) && (get()))
    {
      j = (f ^ true);
      return;
    }
    j = f;
  }
  
  private int readInt(int paramInt)
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
              if (h == 1) {
                return 1;
              }
              return Integer.MIN_VALUE;
            }
            if (h == 0) {
              return 1;
            }
            return Integer.MIN_VALUE;
          }
          if (h == 1) {
            return -1;
          }
          return Integer.MIN_VALUE;
        }
        if (h == 0) {
          return -1;
        }
        return Integer.MIN_VALUE;
      }
      if (h == 1) {
        return 1;
      }
      if (get()) {
        return -1;
      }
      return 1;
    }
    if (h == 1) {
      return -1;
    }
    if (get()) {
      return 1;
    }
    return -1;
  }
  
  private void update()
  {
    if (B.remove() == 1073741824) {
      return;
    }
    float f1 = 0.0F;
    int i3 = getChildCount();
    int i1 = 0;
    View localView;
    while (i1 < i3)
    {
      localView = getChildAt(i1);
      float f3 = B.remove(localView);
      if (f3 >= f1)
      {
        float f2 = f3;
        if (((c)localView.getLayoutParams()).i()) {
          f2 = 1.0F * f3 / c;
        }
        f1 = Math.max(f1, f2);
      }
      i1 += 1;
    }
    int i4 = m;
    int i2 = Math.round(c * f1);
    i1 = i2;
    if (B.remove() == Integer.MIN_VALUE) {
      i1 = Math.min(i2, B.get());
    }
    updateMeasureSpecs(i1);
    if (m == i4) {
      return;
    }
    i1 = 0;
    while (i1 < i3)
    {
      localView = getChildAt(i1);
      c localC = (c)localView.getLayoutParams();
      if (!i)
      {
        int i5;
        if ((get()) && (h == 1))
        {
          i2 = c;
          i5 = c.i;
          localView.offsetLeftAndRight(-(i2 - 1 - i5) * m - -(i2 - 1 - i5) * i4);
        }
        else
        {
          i5 = c.i;
          i2 = m * i5;
          i5 *= i4;
          if (h == 1) {
            localView.offsetLeftAndRight(i2 - i5);
          } else {
            localView.offsetTopAndBottom(i2 - i5);
          }
        }
      }
      i1 += 1;
    }
  }
  
  private boolean updateAnchorFromChildren(RecyclerView.a0 paramA0, b paramB)
  {
    int i1;
    if (g) {
      i1 = findLastReferenceChildPosition(paramA0.size());
    } else {
      i1 = findFirstReferenceChildPosition(paramA0.size());
    }
    c = i1;
    a = Integer.MIN_VALUE;
    return true;
  }
  
  private int updateSpecWithExtra(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt2 == 0) && (paramInt3 == 0)) {
      return paramInt1;
    }
    int i1 = View.MeasureSpec.getMode(paramInt1);
    if ((i1 != Integer.MIN_VALUE) && (i1 != 1073741824)) {
      return paramInt1;
    }
    return View.MeasureSpec.makeMeasureSpec(Math.max(0, View.MeasureSpec.getSize(paramInt1) - paramInt2 - paramInt3), i1);
  }
  
  int a(int paramInt, RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    if (getChildCount() != 0)
    {
      if (paramInt == 0) {
        return 0;
      }
      b(paramInt, paramA0);
      int i2 = a(paramV, i, paramA0);
      int i1;
      if (i.g < i2)
      {
        i1 = paramInt;
      }
      else
      {
        i1 = i2;
        if (paramInt < 0) {
          i1 = -i2;
        }
      }
      a.a(-i1);
      g = j;
      paramA0 = i;
      g = 0;
      b(paramV, paramA0);
      return i1;
    }
    return 0;
  }
  
  public Parcelable a()
  {
    e localE = o;
    if (localE != null) {
      return new e(localE);
    }
    localE = new e();
    f = f;
    g = g;
    x = y;
    d localD = l;
    if (localD != null)
    {
      int[] arrayOfInt = o;
      if (arrayOfInt != null)
      {
        n = arrayOfInt;
        d = n.length;
        e = c;
        break label117;
      }
    }
    d = 0;
    label117:
    if (getChildCount() > 0)
    {
      if (g) {
        i1 = add();
      } else {
        i1 = c();
      }
      c = i1;
      y = e();
      int i1 = c;
      b = i1;
      a = new int[i1];
      int i2 = 0;
      while (i2 < c)
      {
        int i3;
        if (g)
        {
          i3 = b[i2].a(Integer.MIN_VALUE);
          i1 = i3;
          if (i3 != Integer.MIN_VALUE) {
            i1 = i3 - a.a();
          }
        }
        else
        {
          i3 = b[i2].get(Integer.MIN_VALUE);
          i1 = i3;
          if (i3 != Integer.MIN_VALUE) {
            i1 = i3 - a.f();
          }
        }
        a[i2] = i1;
        i2 += 1;
      }
      return localE;
    }
    c = -1;
    y = -1;
    b = 0;
    return localE;
  }
  
  public View a(View paramView, int paramInt, RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    if (getChildCount() == 0) {
      return null;
    }
    paramView = findContainingItemView(paramView);
    if (paramView == null) {
      return null;
    }
    onSaveInstanceState();
    int i3 = readInt(paramInt);
    if (i3 == Integer.MIN_VALUE) {
      return null;
    }
    Object localObject = (c)paramView.getLayoutParams();
    boolean bool1 = i;
    localObject = c;
    if (i3 == 1) {
      paramInt = add();
    } else {
      paramInt = c();
    }
    a(paramInt, paramA0);
    init(i3);
    Item localItem = i;
    c = (k + paramInt);
    g = ((int)(a.get() * 0.33333334F));
    localItem = i;
    h = true;
    int i2 = 0;
    b = false;
    a(paramV, localItem, paramA0);
    g = j;
    if (!bool1)
    {
      paramV = ((f)localObject).a(paramInt, i3);
      if ((paramV != null) && (paramV != paramView)) {
        return paramV;
      }
    }
    if (add(i3))
    {
      i1 = c - 1;
      while (i1 >= 0)
      {
        paramV = b[i1].a(paramInt, i3);
        if ((paramV != null) && (paramV != paramView)) {
          return paramV;
        }
        i1 -= 1;
      }
    }
    else
    {
      i1 = 0;
      while (i1 < c)
      {
        paramV = b[i1].a(paramInt, i3);
        if ((paramV != null) && (paramV != paramView)) {
          return paramV;
        }
        i1 += 1;
      }
    }
    boolean bool2 = f;
    if (i3 == -1) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    paramInt = i2;
    if ((bool2 ^ true) == i1) {
      paramInt = 1;
    }
    if (!bool1)
    {
      if (paramInt != 0) {
        i1 = ((f)localObject).get();
      } else {
        i1 = ((f)localObject).next();
      }
      paramV = findViewByPosition(i1);
      if ((paramV != null) && (paramV != paramView)) {
        return paramV;
      }
    }
    if (add(i3))
    {
      i1 = c - 1;
      while (i1 >= 0)
      {
        if (i1 != i)
        {
          if (paramInt != 0) {
            i2 = b[i1].get();
          } else {
            i2 = b[i1].next();
          }
          paramV = findViewByPosition(i2);
          if ((paramV != null) && (paramV != paramView)) {
            return paramV;
          }
        }
        i1 -= 1;
      }
      return null;
    }
    int i1 = 0;
    while (i1 < c)
    {
      if (paramInt != 0) {
        i2 = b[i1].get();
      } else {
        i2 = b[i1].next();
      }
      paramV = findViewByPosition(i2);
      if ((paramV != null) && (paramV != paramView)) {
        return paramV;
      }
      i1 += 1;
    }
    return null;
  }
  
  View a(boolean paramBoolean)
  {
    int i2 = a.f();
    int i3 = a.a();
    int i4 = getChildCount();
    Object localObject1 = null;
    int i1 = 0;
    while (i1 < i4)
    {
      View localView = getChildAt(i1);
      int i5 = a.f(localView);
      Object localObject2 = localObject1;
      if (a.a(localView) > i2) {
        if (i5 >= i3)
        {
          localObject2 = localObject1;
        }
        else if (i5 < i2)
        {
          if (!paramBoolean) {
            return localView;
          }
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = localView;
          }
        }
        else
        {
          return localView;
        }
      }
      i1 += 1;
      localObject1 = localObject2;
    }
    return localObject1;
  }
  
  public void a(int paramInt1, int paramInt2, RecyclerView.a0 paramA0, RecyclerView.o.c paramC)
  {
    if (h != 0) {
      paramInt1 = paramInt2;
    }
    if (getChildCount() != 0)
    {
      if (paramInt1 == 0) {
        return;
      }
      b(paramInt1, paramA0);
      Object localObject = r;
      if ((localObject == null) || (localObject.length < c)) {
        r = new int[c];
      }
      paramInt1 = 0;
      paramInt2 = 0;
      while (paramInt2 < c)
      {
        localObject = i;
        int i2;
        if (k == -1)
        {
          i1 = a;
          i2 = i1 - b[paramInt2].get(i1);
        }
        else
        {
          i2 = b[paramInt2].a(d) - i.d;
        }
        int i1 = paramInt1;
        if (i2 >= 0)
        {
          r[paramInt1] = i2;
          i1 = paramInt1 + 1;
        }
        paramInt2 += 1;
        paramInt1 = i1;
      }
      Arrays.sort(r, 0, paramInt1);
      paramInt2 = 0;
      while ((paramInt2 < paramInt1) && (i.a(paramA0)))
      {
        paramC.a(i.c, r[paramInt2]);
        localObject = i;
        c += k;
        paramInt2 += 1;
      }
    }
  }
  
  public void a(RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    a(paramV, paramA0, true);
  }
  
  public void a(RecyclerView paramRecyclerView, RecyclerView.v paramV)
  {
    super.a(paramRecyclerView, paramV);
    removeCallbacks(e);
    int i1 = 0;
    while (i1 < c)
    {
      b[i1].i();
      i1 += 1;
    }
    paramRecyclerView.requestLayout();
  }
  
  boolean a(RecyclerView.a0 paramA0, b paramB)
  {
    boolean bool2 = paramA0.get();
    boolean bool1 = false;
    if (!bool2)
    {
      int i1 = n;
      if (i1 == -1) {
        return false;
      }
      if ((i1 >= 0) && (i1 < paramA0.size()))
      {
        paramA0 = o;
        if ((paramA0 != null) && (c != -1) && (b >= 1))
        {
          a = Integer.MIN_VALUE;
          c = n;
          return true;
        }
        paramA0 = findViewByPosition(n);
        if (paramA0 != null)
        {
          if (j) {
            i1 = add();
          } else {
            i1 = c();
          }
          c = i1;
          if (q != Integer.MIN_VALUE)
          {
            if (k)
            {
              a = (a.a() - q - a.a(paramA0));
              return true;
            }
            a = (a.f() + q - a.f(paramA0));
            return true;
          }
          if (a.remove(paramA0) > a.get())
          {
            if (k) {
              i1 = a.a();
            } else {
              i1 = a.f();
            }
            a = i1;
            return true;
          }
          i1 = a.f(paramA0) - a.f();
          if (i1 < 0)
          {
            a = (-i1);
            return true;
          }
          i1 = a.a() - a.a(paramA0);
          if (i1 < 0)
          {
            a = i1;
            return true;
          }
          a = Integer.MIN_VALUE;
        }
        else
        {
          c = n;
          i1 = q;
          if (i1 == Integer.MIN_VALUE)
          {
            if (calculateScrollDirectionForPosition(c) == 1) {
              bool1 = true;
            }
            k = bool1;
            paramB.b();
          }
          else
          {
            paramB.a(i1);
          }
          e = true;
        }
        return true;
      }
      n = -1;
      q = Integer.MIN_VALUE;
    }
    return false;
  }
  
  int add()
  {
    int i1 = getChildCount();
    if (i1 == 0) {
      return 0;
    }
    return a(getChildAt(i1 - 1));
  }
  
  public void assertNotInLayoutOrScroll(String paramString)
  {
    if (o == null) {
      super.assertNotInLayoutOrScroll(paramString);
    }
  }
  
  View b()
  {
    int i1 = getChildCount() - 1;
    BitSet localBitSet = new BitSet(c);
    localBitSet.set(0, c, true);
    int i2 = h;
    int i5 = -1;
    if ((i2 == 1) && (get())) {
      i2 = 1;
    } else {
      i2 = -1;
    }
    int i3;
    if (j)
    {
      i3 = 0 - 1;
    }
    else
    {
      i3 = 0;
      i4 = i1 + 1;
      i1 = i3;
      i3 = i4;
    }
    int i4 = i1;
    if (i1 < i3)
    {
      i5 = 1;
      i4 = i1;
    }
    while (i4 != i3)
    {
      View localView = getChildAt(i4);
      c localC = (c)localView.getLayoutParams();
      if (localBitSet.get(c.i))
      {
        if (a(c)) {
          return localView;
        }
        localBitSet.clear(c.i);
      }
      if ((!i) && (i4 + i5 != i3))
      {
        Object localObject = getChildAt(i4 + i5);
        int i6 = 0;
        i1 = 0;
        int i7;
        if (j)
        {
          i6 = a.a(localView);
          i7 = a.a((View)localObject);
          if (i6 < i7) {
            return localView;
          }
          if (i6 == i7) {
            i1 = 1;
          }
        }
        else
        {
          i7 = a.f(localView);
          int i8 = a.f((View)localObject);
          if (i7 > i8) {
            return localView;
          }
          i1 = i6;
          if (i7 == i8) {
            i1 = 1;
          }
        }
        if (i1 != 0)
        {
          localObject = (c)((View)localObject).getLayoutParams();
          if (c.i - c.i < 0) {
            i1 = 1;
          } else {
            i1 = 0;
          }
          if (i2 < 0) {
            i6 = 1;
          } else {
            i6 = 0;
          }
          if (i1 != i6) {
            return localView;
          }
        }
      }
      i4 += i5;
    }
    return null;
  }
  
  View b(boolean paramBoolean)
  {
    int i2 = a.f();
    int i3 = a.a();
    Object localObject1 = null;
    int i1 = getChildCount() - 1;
    while (i1 >= 0)
    {
      View localView = getChildAt(i1);
      int i4 = a.f(localView);
      int i5 = a.a(localView);
      Object localObject2 = localObject1;
      if (i5 > i2) {
        if (i4 >= i3)
        {
          localObject2 = localObject1;
        }
        else if (i5 > i3)
        {
          if (!paramBoolean) {
            return localView;
          }
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = localView;
          }
        }
        else
        {
          return localView;
        }
      }
      i1 -= 1;
      localObject1 = localObject2;
    }
    return localObject1;
  }
  
  void b(int paramInt, RecyclerView.a0 paramA0)
  {
    int i1;
    int i2;
    if (paramInt > 0)
    {
      i1 = 1;
      i2 = add();
    }
    else
    {
      i1 = -1;
      i2 = c();
    }
    i.b = true;
    a(i2, paramA0);
    init(i1);
    paramA0 = i;
    c = (k + i2);
    g = Math.abs(paramInt);
  }
  
  void b(RecyclerView.a0 paramA0, b paramB)
  {
    if (a(paramA0, paramB)) {
      return;
    }
    if (updateAnchorFromChildren(paramA0, paramB)) {
      return;
    }
    paramB.b();
    c = 0;
  }
  
  int c()
  {
    if (getChildCount() == 0) {
      return 0;
    }
    return a(getChildAt(0));
  }
  
  public boolean canScrollHorizontally()
  {
    return h == 0;
  }
  
  public boolean canScrollVertically()
  {
    return h == 1;
  }
  
  public boolean checkLayoutParams(RecyclerView.p paramP)
  {
    return paramP instanceof c;
  }
  
  boolean close()
  {
    if ((getChildCount() != 0) && (bottom != 0))
    {
      if (!isAttachedToWindow()) {
        return false;
      }
      int i1;
      int i2;
      if (j)
      {
        i1 = add();
        i2 = c();
      }
      else
      {
        i1 = c();
        i2 = add();
      }
      if ((i1 == 0) && (b() != null))
      {
        l.clear();
        onResultReceived();
        requestLayout();
        return true;
      }
      if (!p) {
        return false;
      }
      int i3;
      if (j) {
        i3 = -1;
      } else {
        i3 = 1;
      }
      StaggeredGridLayoutManager.d.a localA1 = l.a(i1, i2 + 1, i3, true);
      if (localA1 == null)
      {
        p = false;
        l.b(i2 + 1);
        return false;
      }
      StaggeredGridLayoutManager.d.a localA2 = l.a(i1, a, i3 * -1, true);
      if (localA2 == null) {
        l.b(a);
      } else {
        l.b(a + 1);
      }
      onResultReceived();
      requestLayout();
      return true;
    }
    return false;
  }
  
  public int computeHorizontalScrollExtent(RecyclerView.a0 paramA0)
  {
    return d(paramA0);
  }
  
  public int computeHorizontalScrollOffset(RecyclerView.a0 paramA0)
  {
    return b(paramA0);
  }
  
  public int computeHorizontalScrollRange(RecyclerView.a0 paramA0)
  {
    return a(paramA0);
  }
  
  public int computeVerticalScrollExtent(RecyclerView.a0 paramA0)
  {
    return d(paramA0);
  }
  
  public int computeVerticalScrollOffset(RecyclerView.a0 paramA0)
  {
    return b(paramA0);
  }
  
  public int computeVerticalScrollRange(RecyclerView.a0 paramA0)
  {
    return a(paramA0);
  }
  
  int e()
  {
    View localView;
    if (j) {
      localView = b(true);
    } else {
      localView = a(true);
    }
    if (localView == null) {
      return -1;
    }
    return a(localView);
  }
  
  public void e(int paramInt)
  {
    e localE = o;
    if ((localE != null) && (c != paramInt)) {
      localE.b();
    }
    n = paramInt;
    q = Integer.MIN_VALUE;
    requestLayout();
  }
  
  boolean equals()
  {
    int i2 = b[0].get(Integer.MIN_VALUE);
    int i1 = 1;
    while (i1 < c)
    {
      if (b[i1].get(Integer.MIN_VALUE) != i2) {
        return false;
      }
      i1 += 1;
    }
    return true;
  }
  
  boolean f()
  {
    int i2 = b[0].a(Integer.MIN_VALUE);
    int i1 = 1;
    while (i1 < c)
    {
      if (b[i1].a(Integer.MIN_VALUE) != i2) {
        return false;
      }
      i1 += 1;
    }
    return true;
  }
  
  public RecyclerView.p generateDefaultLayoutParams()
  {
    if (h == 0) {
      return new c(-2, -1);
    }
    return new c(-1, -2);
  }
  
  public RecyclerView.p generateLayoutParams(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new c(paramContext, paramAttributeSet);
  }
  
  public RecyclerView.p generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
      return new c((ViewGroup.MarginLayoutParams)paramLayoutParams);
    }
    return new c(paramLayoutParams);
  }
  
  boolean get()
  {
    return getLayoutDirection() == 1;
  }
  
  public int getColumnCountForAccessibility(RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    if (h == 1) {
      return c;
    }
    return super.getColumnCountForAccessibility(paramV, paramA0);
  }
  
  public int getRowCountForAccessibility(RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    if (h == 0) {
      return c;
    }
    return super.getRowCountForAccessibility(paramV, paramA0);
  }
  
  public void invalidateSpanAssignments()
  {
    l.clear();
    requestLayout();
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    if (getChildCount() > 0)
    {
      View localView1 = a(false);
      View localView2 = b(false);
      if (localView1 != null)
      {
        if (localView2 == null) {
          return;
        }
        int i1 = a(localView1);
        int i2 = a(localView2);
        if (i1 < i2)
        {
          paramAccessibilityEvent.setFromIndex(i1);
          paramAccessibilityEvent.setToIndex(i2);
          return;
        }
        paramAccessibilityEvent.setFromIndex(i2);
        paramAccessibilityEvent.setToIndex(i1);
      }
    }
  }
  
  public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.v paramV, RecyclerView.a0 paramA0, View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
  {
    paramV = paramView.getLayoutParams();
    if (!(paramV instanceof c))
    {
      super.onInitializeAccessibilityNodeInfoForItem(paramView, paramAccessibilityNodeInfoCompat);
      return;
    }
    paramV = (c)paramV;
    int i1;
    if (h == 0)
    {
      i2 = paramV.b();
      if (i) {
        i1 = c;
      } else {
        i1 = 1;
      }
      paramAccessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(i2, i1, -1, -1, i, false));
      return;
    }
    int i2 = paramV.b();
    if (i) {
      i1 = c;
    } else {
      i1 = 1;
    }
    paramAccessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(-1, -1, i2, i1, i, false));
  }
  
  public void onItemsAdded(RecyclerView paramRecyclerView, int paramInt1, int paramInt2)
  {
    handleUpdate(paramInt1, paramInt2, 1);
  }
  
  public void onItemsChanged(RecyclerView paramRecyclerView)
  {
    l.clear();
    requestLayout();
  }
  
  public void onItemsMoved(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, int paramInt3)
  {
    handleUpdate(paramInt1, paramInt2, 8);
  }
  
  public void onItemsRemoved(RecyclerView paramRecyclerView, int paramInt1, int paramInt2)
  {
    handleUpdate(paramInt1, paramInt2, 2);
  }
  
  public void onItemsUpdated(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, Object paramObject)
  {
    handleUpdate(paramInt1, paramInt2, 4);
  }
  
  public void onLayoutChildren(RecyclerView.a0 paramA0)
  {
    super.onLayoutChildren(paramA0);
    n = -1;
    q = Integer.MIN_VALUE;
    o = null;
    k.a();
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if ((paramParcelable instanceof e))
    {
      o = ((e)paramParcelable);
      requestLayout();
    }
  }
  
  public void onScrollStateChanged(int paramInt)
  {
    if (paramInt == 0) {
      close();
    }
  }
  
  public int scrollHorizontallyBy(int paramInt, RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    return a(paramInt, paramV, paramA0);
  }
  
  public int scrollVerticallyBy(int paramInt, RecyclerView.v paramV, RecyclerView.a0 paramA0)
  {
    return a(paramInt, paramV, paramA0);
  }
  
  public void setMeasuredDimension(Rect paramRect, int paramInt1, int paramInt2)
  {
    int i1 = getPaddingLeft() + getPaddingRight();
    int i2 = getPaddingTop() + getPaddingBottom();
    if (h == 1)
    {
      paramInt2 = RecyclerView.o.chooseSize(paramInt2, paramRect.height() + i2, getMinimumHeight());
      paramInt1 = RecyclerView.o.chooseSize(paramInt1, m * c + i1, getMinimumWidth());
    }
    else
    {
      paramInt1 = RecyclerView.o.chooseSize(paramInt1, paramRect.width() + i1, getMinimumWidth());
      paramInt2 = RecyclerView.o.chooseSize(paramInt2, m * c + i2, getMinimumHeight());
    }
    setMeasuredDimension(paramInt1, paramInt2);
  }
  
  public void setOrientation(int paramInt)
  {
    if ((paramInt != 0) && (paramInt != 1)) {
      throw new IllegalArgumentException("invalid orientation.");
    }
    assertNotInLayoutOrScroll(null);
    if (paramInt == h) {
      return;
    }
    h = paramInt;
    MethodWriter localMethodWriter = a;
    a = B;
    B = localMethodWriter;
    requestLayout();
  }
  
  public boolean setOrientation()
  {
    return bottom != 0;
  }
  
  public void setReverseLayout(boolean paramBoolean)
  {
    assertNotInLayoutOrScroll(null);
    e localE = o;
    if ((localE != null) && (f != paramBoolean)) {
      f = paramBoolean;
    }
    f = paramBoolean;
    requestLayout();
  }
  
  public void setSpanCount(int paramInt)
  {
    assertNotInLayoutOrScroll(null);
    if (paramInt != c)
    {
      invalidateSpanAssignments();
      c = paramInt;
      d = new BitSet(c);
      b = new f[c];
      paramInt = 0;
      while (paramInt < c)
      {
        b[paramInt] = new f(paramInt);
        paramInt += 1;
      }
      requestLayout();
    }
  }
  
  public boolean supportsPredictiveItemAnimations()
  {
    return o == null;
  }
  
  void updateMeasureSpecs(int paramInt)
  {
    m = (paramInt / c);
    mWidthSpec = View.MeasureSpec.makeMeasureSpec(paramInt, B.remove());
  }
  
  public void visitFrame(int paramInt)
  {
    super.visitFrame(paramInt);
    int i1 = 0;
    while (i1 < c)
    {
      b[i1].b(paramInt);
      i1 += 1;
    }
  }
  
  public void visitMaxs(int paramInt)
  {
    super.visitMaxs(paramInt);
    int i1 = 0;
    while (i1 < c)
    {
      b[i1].b(paramInt);
      i1 += 1;
    }
  }
  
  class a
    implements Runnable
  {
    a() {}
    
    public void run()
    {
      close();
    }
  }
  
  class b
  {
    int a;
    int c;
    boolean e;
    boolean i;
    boolean k;
    int[] o;
    
    b()
    {
      a();
    }
    
    void a()
    {
      c = -1;
      a = Integer.MIN_VALUE;
      k = false;
      e = false;
      i = false;
      int[] arrayOfInt = o;
      if (arrayOfInt != null) {
        Arrays.fill(arrayOfInt, -1);
      }
    }
    
    void a(int paramInt)
    {
      if (k)
      {
        a = (a.a() - paramInt);
        return;
      }
      a = (a.f() + paramInt);
    }
    
    void a(StaggeredGridLayoutManager.f[] paramArrayOfF)
    {
      int n = paramArrayOfF.length;
      int[] arrayOfInt = o;
      if ((arrayOfInt == null) || (arrayOfInt.length < n)) {
        o = new int[b.length];
      }
      int m = 0;
      while (m < n)
      {
        o[m] = paramArrayOfF[m].get(Integer.MIN_VALUE);
        m += 1;
      }
    }
    
    void b()
    {
      int m;
      if (k) {
        m = a.a();
      } else {
        m = a.f();
      }
      a = m;
    }
  }
  
  public static class c
    extends RecyclerView.p
  {
    StaggeredGridLayoutManager.f c;
    boolean i;
    
    public c(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public c(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public c(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public c(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
    
    public final int b()
    {
      StaggeredGridLayoutManager.f localF = c;
      if (localF == null) {
        return -1;
      }
      return i;
    }
    
    public boolean i()
    {
      return i;
    }
  }
  
  static class d
  {
    List<a> c;
    int[] o;
    
    d() {}
    
    private void b(int paramInt1, int paramInt2)
    {
      Object localObject = c;
      if (localObject == null) {
        return;
      }
      int i = ((List)localObject).size() - 1;
      while (i >= 0)
      {
        localObject = (a)c.get(i);
        int j = a;
        if (j >= paramInt1) {
          a = (j + paramInt2);
        }
        i -= 1;
      }
    }
    
    private int c(int paramInt)
    {
      if (c == null) {
        return -1;
      }
      a localA = a(paramInt);
      if (localA != null) {
        c.remove(localA);
      }
      int k = -1;
      int m = c.size();
      int i = 0;
      int j;
      for (;;)
      {
        j = k;
        if (i >= m) {
          break;
        }
        if (c.get(i)).a >= paramInt)
        {
          j = i;
          break;
        }
        i += 1;
      }
      if (j != -1)
      {
        localA = (a)c.get(j);
        c.remove(j);
        return a;
      }
      return -1;
    }
    
    private void d(int paramInt1, int paramInt2)
    {
      Object localObject = c;
      if (localObject == null) {
        return;
      }
      int i = ((List)localObject).size() - 1;
      while (i >= 0)
      {
        localObject = (a)c.get(i);
        int j = a;
        if (j >= paramInt1) {
          if (j < paramInt1 + paramInt2) {
            c.remove(i);
          } else {
            a = (j - paramInt2);
          }
        }
        i -= 1;
      }
    }
    
    public a a(int paramInt)
    {
      Object localObject = c;
      if (localObject == null) {
        return null;
      }
      int i = ((List)localObject).size() - 1;
      while (i >= 0)
      {
        localObject = (a)c.get(i);
        if (a == paramInt) {
          return localObject;
        }
        i -= 1;
      }
      return null;
    }
    
    public a a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    {
      Object localObject = c;
      if (localObject == null) {
        return null;
      }
      int j = ((List)localObject).size();
      int i = 0;
      while (i < j)
      {
        localObject = (a)c.get(i);
        int k = a;
        if (k >= paramInt2) {
          return null;
        }
        if (k >= paramInt1)
        {
          if ((paramInt3 == 0) || (b == paramInt3)) {
            break label109;
          }
          if ((paramBoolean) && (f)) {
            return localObject;
          }
        }
        i += 1;
      }
      return null;
      label109:
      return localObject;
    }
    
    void a(int paramInt1, int paramInt2)
    {
      int[] arrayOfInt = o;
      if (arrayOfInt != null)
      {
        if (paramInt1 >= arrayOfInt.length) {
          return;
        }
        next(paramInt1 + paramInt2);
        arrayOfInt = o;
        System.arraycopy(arrayOfInt, paramInt1 + paramInt2, arrayOfInt, paramInt1, arrayOfInt.length - paramInt1 - paramInt2);
        arrayOfInt = o;
        Arrays.fill(arrayOfInt, arrayOfInt.length - paramInt2, arrayOfInt.length, -1);
        d(paramInt1, paramInt2);
      }
    }
    
    public void a(a paramA)
    {
      if (c == null) {
        c = new ArrayList();
      }
      int j = c.size();
      int i = 0;
      while (i < j)
      {
        a localA = (a)c.get(i);
        if (a == a) {
          c.remove(i);
        }
        if (a >= a)
        {
          c.add(i, paramA);
          return;
        }
        i += 1;
      }
      c.add(paramA);
    }
    
    int b(int paramInt)
    {
      List localList = c;
      if (localList != null)
      {
        int i = localList.size() - 1;
        while (i >= 0)
        {
          if (c.get(i)).a >= paramInt) {
            c.remove(i);
          }
          i -= 1;
        }
      }
      return clear(paramInt);
    }
    
    void b(int paramInt, StaggeredGridLayoutManager.f paramF)
    {
      next(paramInt);
      o[paramInt] = i;
    }
    
    void c(int paramInt1, int paramInt2)
    {
      int[] arrayOfInt = o;
      if (arrayOfInt != null)
      {
        if (paramInt1 >= arrayOfInt.length) {
          return;
        }
        next(paramInt1 + paramInt2);
        arrayOfInt = o;
        System.arraycopy(arrayOfInt, paramInt1, arrayOfInt, paramInt1 + paramInt2, arrayOfInt.length - paramInt1 - paramInt2);
        Arrays.fill(o, paramInt1, paramInt1 + paramInt2, -1);
        b(paramInt1, paramInt2);
      }
    }
    
    int clear(int paramInt)
    {
      int[] arrayOfInt = o;
      if (arrayOfInt == null) {
        return -1;
      }
      if (paramInt >= arrayOfInt.length) {
        return -1;
      }
      int i = c(paramInt);
      if (i == -1)
      {
        arrayOfInt = o;
        Arrays.fill(arrayOfInt, paramInt, arrayOfInt.length, -1);
        return o.length;
      }
      Arrays.fill(o, paramInt, i + 1, -1);
      return i + 1;
    }
    
    void clear()
    {
      int[] arrayOfInt = o;
      if (arrayOfInt != null) {
        Arrays.fill(arrayOfInt, -1);
      }
      c = null;
    }
    
    int d(int paramInt)
    {
      int[] arrayOfInt = o;
      if ((arrayOfInt != null) && (paramInt < arrayOfInt.length)) {
        return arrayOfInt[paramInt];
      }
      return -1;
    }
    
    int getMessage(int paramInt)
    {
      int i = o.length;
      while (i <= paramInt) {
        i *= 2;
      }
      return i;
    }
    
    void next(int paramInt)
    {
      int[] arrayOfInt1 = o;
      if (arrayOfInt1 == null)
      {
        o = new int[Math.max(paramInt, 10) + 1];
        Arrays.fill(o, -1);
        return;
      }
      if (paramInt >= arrayOfInt1.length)
      {
        arrayOfInt1 = o;
        o = new int[getMessage(paramInt)];
        System.arraycopy(arrayOfInt1, 0, o, 0, arrayOfInt1.length);
        int[] arrayOfInt2 = o;
        Arrays.fill(arrayOfInt2, arrayOfInt1.length, arrayOfInt2.length, -1);
      }
    }
    
    static class a
      implements Parcelable
    {
      public static final Parcelable.Creator<a> CREATOR = new a();
      int a;
      int b;
      int[] c;
      boolean f;
      
      a() {}
      
      a(Parcel paramParcel)
      {
        a = paramParcel.readInt();
        b = paramParcel.readInt();
        int i = paramParcel.readInt();
        boolean bool = true;
        if (i != 1) {
          bool = false;
        }
        f = bool;
        i = paramParcel.readInt();
        if (i > 0)
        {
          c = new int[i];
          paramParcel.readIntArray(c);
        }
      }
      
      int b(int paramInt)
      {
        int[] arrayOfInt = c;
        if (arrayOfInt == null) {
          return 0;
        }
        return arrayOfInt[paramInt];
      }
      
      public int describeContents()
      {
        return 0;
      }
      
      public String toString()
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("FullSpanItem{mPosition=");
        localStringBuilder.append(a);
        localStringBuilder.append(", mGapDir=");
        localStringBuilder.append(b);
        localStringBuilder.append(", mHasUnwantedGapAfter=");
        localStringBuilder.append(f);
        localStringBuilder.append(", mGapPerSpan=");
        localStringBuilder.append(Arrays.toString(c));
        localStringBuilder.append('}');
        return localStringBuilder.toString();
      }
      
      public void writeToParcel(Parcel paramParcel, int paramInt)
      {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
      }
      
      static final class a
        implements Parcelable.Creator<StaggeredGridLayoutManager.d.a>
      {
        a() {}
        
        public StaggeredGridLayoutManager.d.a createFromParcel(Parcel paramParcel)
        {
          return new StaggeredGridLayoutManager.d.a(paramParcel);
        }
        
        public StaggeredGridLayoutManager.d.a[] newArray(int paramInt)
        {
          return new StaggeredGridLayoutManager.d.a[paramInt];
        }
      }
    }
  }
  
  public static class e
    implements Parcelable
  {
    public static final Parcelable.Creator<e> CREATOR = new a();
    int[] a;
    int b;
    int c;
    int d;
    List<StaggeredGridLayoutManager.d.a> e;
    boolean f;
    boolean g;
    int[] n;
    boolean x;
    int y;
    
    public e() {}
    
    e(Parcel paramParcel)
    {
      c = paramParcel.readInt();
      y = paramParcel.readInt();
      b = paramParcel.readInt();
      int i = b;
      if (i > 0)
      {
        a = new int[i];
        paramParcel.readIntArray(a);
      }
      d = paramParcel.readInt();
      i = d;
      if (i > 0)
      {
        n = new int[i];
        paramParcel.readIntArray(n);
      }
      i = paramParcel.readInt();
      boolean bool2 = false;
      if (i == 1) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      f = bool1;
      if (paramParcel.readInt() == 1) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      g = bool1;
      boolean bool1 = bool2;
      if (paramParcel.readInt() == 1) {
        bool1 = true;
      }
      x = bool1;
      e = paramParcel.readArrayList(StaggeredGridLayoutManager.d.a.class.getClassLoader());
    }
    
    public e(e paramE)
    {
      b = b;
      c = c;
      y = y;
      a = a;
      d = d;
      n = n;
      f = f;
      g = g;
      x = x;
      e = e;
    }
    
    void b()
    {
      a = null;
      b = 0;
      c = -1;
      y = -1;
    }
    
    void d()
    {
      a = null;
      b = 0;
      d = 0;
      n = null;
      e = null;
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
      implements Parcelable.Creator<StaggeredGridLayoutManager.e>
    {
      a() {}
      
      public StaggeredGridLayoutManager.e createFromParcel(Parcel paramParcel)
      {
        return new StaggeredGridLayoutManager.e(paramParcel);
      }
      
      public StaggeredGridLayoutManager.e[] newArray(int paramInt)
      {
        return new StaggeredGridLayoutManager.e[paramInt];
      }
    }
  }
  
  class f
  {
    int a = Integer.MIN_VALUE;
    ArrayList<View> c = new ArrayList();
    final int i;
    int p = 0;
    int x = Integer.MIN_VALUE;
    
    f(int paramInt)
    {
      i = paramInt;
    }
    
    int a(int paramInt)
    {
      int j = a;
      if (j != Integer.MIN_VALUE) {
        return j;
      }
      if (c.size() == 0) {
        return paramInt;
      }
      d();
      return a;
    }
    
    int a(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    {
      int i1 = a.f();
      int i2 = a.a();
      int j;
      if (paramInt2 > paramInt1) {
        j = 1;
      } else {
        j = -1;
      }
      while (paramInt1 != paramInt2)
      {
        View localView = (View)c.get(paramInt1);
        int i3 = a.f(localView);
        int i4 = a.a(localView);
        int n = 0;
        int m;
        if (paramBoolean3 ? i3 <= i2 : i3 < i2) {
          m = 1;
        } else {
          m = 0;
        }
        if (paramBoolean3 ? i4 >= i1 : i4 > i1) {
          n = 1;
        }
        if ((m != 0) && (n != 0)) {
          if ((paramBoolean1) && (paramBoolean2))
          {
            if ((i3 >= i1) && (i4 <= i2)) {
              return a(localView);
            }
          }
          else
          {
            if (paramBoolean2) {
              return a(localView);
            }
            if ((i3 < i1) || (i4 > i2)) {
              return a(localView);
            }
          }
        }
        paramInt1 += j;
      }
      return -1;
    }
    
    public View a(int paramInt1, int paramInt2)
    {
      View localView2 = null;
      View localView1 = null;
      StaggeredGridLayoutManager localStaggeredGridLayoutManager;
      if (paramInt2 == -1)
      {
        int j = c.size();
        paramInt2 = 0;
        while (paramInt2 < j)
        {
          localView2 = (View)c.get(paramInt2);
          localStaggeredGridLayoutManager = StaggeredGridLayoutManager.this;
          if ((f) && (localStaggeredGridLayoutManager.a(localView2) <= paramInt1)) {
            break;
          }
          localStaggeredGridLayoutManager = StaggeredGridLayoutManager.this;
          if ((!f) && (localStaggeredGridLayoutManager.a(localView2) >= paramInt1)) {
            return localView1;
          }
          if (!localView2.hasFocusable()) {
            break;
          }
          localView1 = localView2;
          paramInt2 += 1;
        }
        return localView1;
      }
      paramInt2 = c.size() - 1;
      localView1 = localView2;
      while (paramInt2 >= 0)
      {
        localView2 = (View)c.get(paramInt2);
        localStaggeredGridLayoutManager = StaggeredGridLayoutManager.this;
        if ((f) && (localStaggeredGridLayoutManager.a(localView2) >= paramInt1)) {
          break;
        }
        localStaggeredGridLayoutManager = StaggeredGridLayoutManager.this;
        if ((!f) && (localStaggeredGridLayoutManager.a(localView2) <= paramInt1)) {
          return localView1;
        }
        if (!localView2.hasFocusable()) {
          break;
        }
        localView1 = localView2;
        paramInt2 -= 1;
      }
      return localView1;
    }
    
    void a()
    {
      View localView = (View)c.remove(0);
      StaggeredGridLayoutManager.c localC = c(localView);
      c = null;
      if (c.size() == 0) {
        a = Integer.MIN_VALUE;
      }
      if ((localC.next()) || (localC.isItemChanged())) {
        p -= a.remove(localView);
      }
      x = Integer.MIN_VALUE;
    }
    
    void a(View paramView)
    {
      StaggeredGridLayoutManager.c localC = c(paramView);
      c = this;
      c.add(paramView);
      a = Integer.MIN_VALUE;
      if (c.size() == 1) {
        x = Integer.MIN_VALUE;
      }
      if ((localC.next()) || (localC.isItemChanged())) {
        p += a.remove(paramView);
      }
    }
    
    void a(boolean paramBoolean, int paramInt)
    {
      int j;
      if (paramBoolean) {
        j = a(Integer.MIN_VALUE);
      } else {
        j = get(Integer.MIN_VALUE);
      }
      i();
      if (j == Integer.MIN_VALUE) {
        return;
      }
      if ((!paramBoolean) || (j >= a.a()))
      {
        if ((!paramBoolean) && (j > a.f())) {
          return;
        }
        int m = j;
        if (paramInt != Integer.MIN_VALUE) {
          m = j + paramInt;
        }
        a = m;
        x = m;
      }
    }
    
    int add(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      return a(paramInt1, paramInt2, false, false, paramBoolean);
    }
    
    void add()
    {
      x = Integer.MIN_VALUE;
      a = Integer.MIN_VALUE;
    }
    
    void b()
    {
      int j = c.size();
      View localView = (View)c.remove(j - 1);
      StaggeredGridLayoutManager.c localC = c(localView);
      c = null;
      if ((localC.next()) || (localC.isItemChanged())) {
        p -= a.remove(localView);
      }
      if (j == 1) {
        x = Integer.MIN_VALUE;
      }
      a = Integer.MIN_VALUE;
    }
    
    void b(int paramInt)
    {
      int j = x;
      if (j != Integer.MIN_VALUE) {
        x = (j + paramInt);
      }
      j = a;
      if (j != Integer.MIN_VALUE) {
        a = (j + paramInt);
      }
    }
    
    void b(View paramView)
    {
      StaggeredGridLayoutManager.c localC = c(paramView);
      c = this;
      c.add(0, paramView);
      x = Integer.MIN_VALUE;
      if (c.size() == 1) {
        a = Integer.MIN_VALUE;
      }
      if ((localC.next()) || (localC.isItemChanged())) {
        p += a.remove(paramView);
      }
    }
    
    StaggeredGridLayoutManager.c c(View paramView)
    {
      return (StaggeredGridLayoutManager.c)paramView.getLayoutParams();
    }
    
    void c()
    {
      Object localObject = (View)c.get(0);
      StaggeredGridLayoutManager.c localC = c((View)localObject);
      x = a.f((View)localObject);
      if (i)
      {
        localObject = l.a(localC.a());
        if ((localObject != null) && (b == -1)) {
          x -= ((StaggeredGridLayoutManager.d.a)localObject).b(i);
        }
      }
    }
    
    int clear()
    {
      int j = x;
      if (j != Integer.MIN_VALUE) {
        return j;
      }
      c();
      return x;
    }
    
    void d()
    {
      Object localObject = c;
      localObject = (View)((ArrayList)localObject).get(((ArrayList)localObject).size() - 1);
      StaggeredGridLayoutManager.c localC = c((View)localObject);
      a = a.a((View)localObject);
      if (i)
      {
        localObject = l.a(localC.a());
        if ((localObject != null) && (b == 1)) {
          a += ((StaggeredGridLayoutManager.d.a)localObject).b(i);
        }
      }
    }
    
    void f(int paramInt)
    {
      x = paramInt;
      a = paramInt;
    }
    
    public int get()
    {
      if (f) {
        return add(c.size() - 1, -1, true);
      }
      return add(0, c.size(), true);
    }
    
    int get(int paramInt)
    {
      int j = x;
      if (j != Integer.MIN_VALUE) {
        return j;
      }
      if (c.size() == 0) {
        return paramInt;
      }
      c();
      return x;
    }
    
    int getItemId()
    {
      int j = a;
      if (j != Integer.MIN_VALUE) {
        return j;
      }
      d();
      return a;
    }
    
    void i()
    {
      c.clear();
      add();
      p = 0;
    }
    
    public int next()
    {
      if (f) {
        return add(0, c.size(), true);
      }
      return add(c.size() - 1, -1, true);
    }
    
    public int size()
    {
      return p;
    }
  }
}
