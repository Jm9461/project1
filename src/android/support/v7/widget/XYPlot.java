package android.support.v7.widget;

import android.view.View;

class XYPlot
{
  p1.a b;
  final p1.b l;
  
  XYPlot(p1.b paramB)
  {
    l = paramB;
    b = new p1.a();
  }
  
  View a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j = l.b();
    int k = l.a();
    int i;
    if (paramInt2 > paramInt1) {
      i = 1;
    } else {
      i = -1;
    }
    Object localObject2;
    for (Object localObject1 = null; paramInt1 != paramInt2; localObject1 = localObject2)
    {
      View localView = l.c(paramInt1);
      int m = l.b(localView);
      int n = l.a(localView);
      b.a(j, k, m, n);
      if (paramInt3 != 0)
      {
        b.b();
        b.b(paramInt3);
        if (b.a()) {
          return localView;
        }
      }
      localObject2 = localObject1;
      if (paramInt4 != 0)
      {
        b.b();
        b.b(paramInt4);
        localObject2 = localObject1;
        if (b.a()) {
          localObject2 = localView;
        }
      }
      paramInt1 += i;
    }
    return localObject1;
  }
  
  boolean a(View paramView, int paramInt)
  {
    b.a(l.b(), l.a(), l.b(paramView), l.a(paramView));
    if (paramInt != 0)
    {
      b.b();
      b.b(paramInt);
      return b.a();
    }
    return false;
  }
}
