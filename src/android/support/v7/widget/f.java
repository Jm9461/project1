package android.support.v7.widget;

import a.b.g.g.j;
import java.util.ArrayList;
import java.util.List;
import org.org.jaxen.util.Pools.Pool;
import org.org.jaxen.util.Pools.SimplePool;

class f
  implements s0.a
{
  final ArrayList<d.b> a = new ArrayList();
  final ArrayList<d.b> c = new ArrayList();
  final g d;
  final boolean f;
  private int g = 0;
  Runnable n;
  final ByteVector r;
  private j<d.b> w = new Pools.SimplePool(30);
  
  f(g paramG)
  {
    this(paramG, false);
  }
  
  f(g paramG, boolean paramBoolean)
  {
    d = paramG;
    f = paramBoolean;
    r = new ByteVector(this);
  }
  
  private void a(Label paramLabel)
  {
    int j = a;
    int k = 0;
    int i4 = a;
    int i5 = c;
    int i3 = -1;
    int i = a;
    while (i < i4 + i5)
    {
      int i1;
      int m;
      int i2;
      if ((d.a(i) == null) && (!b(i)))
      {
        i1 = j;
        m = k;
        if (i3 == 1)
        {
          add(a(4, j, k, b));
          m = 0;
          i1 = i;
        }
        k = 0;
        j = i1;
        i2 = m;
        m = k;
      }
      else
      {
        i1 = j;
        i2 = k;
        if (i3 == 0)
        {
          b(a(4, j, k, b));
          i2 = 0;
          i1 = i;
        }
        m = 1;
        j = i1;
      }
      k = i2 + 1;
      i += 1;
      i3 = m;
    }
    Object localObject = paramLabel;
    if (k != c)
    {
      localObject = b;
      d(paramLabel);
      localObject = a(4, j, k, localObject);
    }
    if (i3 == 0)
    {
      b((Label)localObject);
      return;
    }
    add((Label)localObject);
  }
  
  private void add(Label paramLabel)
  {
    c.add(paramLabel);
    int i = d;
    if (i != 1)
    {
      if (i != 2)
      {
        if (i != 4)
        {
          if (i == 8)
          {
            d.a(a, c);
            return;
          }
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Unknown update op type for ");
          localStringBuilder.append(paramLabel);
          throw new IllegalArgumentException(localStringBuilder.toString());
        }
        d.a(a, c, b);
        return;
      }
      d.subtract(a, c);
      return;
    }
    d.b(a, c);
  }
  
  private int b(int paramInt1, int paramInt2)
  {
    int j = c.size() - 1;
    Label localLabel;
    for (int i = paramInt1; j >= 0; i = paramInt1)
    {
      localLabel = (Label)c.get(j);
      int k = d;
      if (k == 8)
      {
        if (a < c)
        {
          paramInt1 = a;
          k = c;
        }
        else
        {
          paramInt1 = c;
          k = a;
        }
        if ((i >= paramInt1) && (i <= k))
        {
          k = a;
          if (paramInt1 == k)
          {
            if (paramInt2 == 1) {
              c += 1;
            } else if (paramInt2 == 2) {
              c -= 1;
            }
            paramInt1 = i + 1;
          }
          else
          {
            if (paramInt2 == 1) {
              a = (k + 1);
            } else if (paramInt2 == 2) {
              a = (k - 1);
            }
            paramInt1 = i - 1;
          }
        }
        else
        {
          k = a;
          paramInt1 = i;
          if (i < k) {
            if (paramInt2 == 1)
            {
              a = (k + 1);
              c += 1;
              paramInt1 = i;
            }
            else
            {
              paramInt1 = i;
              if (paramInt2 == 2)
              {
                a = (k - 1);
                c -= 1;
                paramInt1 = i;
              }
            }
          }
        }
      }
      else
      {
        int m = a;
        if (m <= i)
        {
          if (k == 1)
          {
            paramInt1 = i - c;
          }
          else
          {
            paramInt1 = i;
            if (k == 2) {
              paramInt1 = i + c;
            }
          }
        }
        else if (paramInt2 == 1)
        {
          a = (m + 1);
          paramInt1 = i;
        }
        else
        {
          paramInt1 = i;
          if (paramInt2 == 2)
          {
            a = (m - 1);
            paramInt1 = i;
          }
        }
      }
      j -= 1;
    }
    paramInt1 = c.size() - 1;
    while (paramInt1 >= 0)
    {
      localLabel = (Label)c.get(paramInt1);
      if (d == 8)
      {
        paramInt2 = c;
        if ((paramInt2 == a) || (paramInt2 < 0))
        {
          c.remove(paramInt1);
          d(localLabel);
        }
      }
      else if (c <= 0)
      {
        c.remove(paramInt1);
        d(localLabel);
      }
      paramInt1 -= 1;
    }
    return i;
  }
  
  private void b(Label paramLabel)
  {
    int i = d;
    if ((i != 1) && (i != 8))
    {
      int i1 = b(a, i);
      int i2 = 1;
      i = a;
      int j = d;
      int k;
      if (j != 2)
      {
        if (j == 4)
        {
          k = 1;
        }
        else
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("op should be remove or update.");
          ((StringBuilder)localObject).append(paramLabel);
          throw new IllegalArgumentException(((StringBuilder)localObject).toString());
        }
      }
      else {
        k = 0;
      }
      int m = 1;
      while (m < c)
      {
        int i3 = b(a + k * m, d);
        j = 0;
        int i6 = d;
        int i5 = 0;
        int i4 = 0;
        if (i6 != 2)
        {
          if (i6 == 4)
          {
            j = i4;
            if (i3 == i1 + 1) {
              j = 1;
            }
          }
        }
        else
        {
          j = i5;
          if (i3 == i1) {
            j = 1;
          }
        }
        if (j != 0)
        {
          j = i2 + 1;
        }
        else
        {
          localObject = a(d, i1, i2, b);
          b((Label)localObject, i);
          d((Label)localObject);
          j = i;
          if (d == 4) {
            j = i + i2;
          }
          i1 = i3;
          i2 = 1;
          i = j;
          j = i2;
        }
        m += 1;
        i2 = j;
      }
      Object localObject = b;
      d(paramLabel);
      if (i2 > 0)
      {
        paramLabel = a(d, i1, i2, localObject);
        b(paramLabel, i);
        d(paramLabel);
      }
    }
    else
    {
      paramLabel = new IllegalArgumentException("should not dispatch add or move for pre layout");
      throw paramLabel;
    }
  }
  
  private boolean b(int paramInt)
  {
    int k = c.size();
    int i = 0;
    while (i < k)
    {
      Label localLabel = (Label)c.get(i);
      int j = d;
      if (j == 8)
      {
        if (a(c, i + 1) == paramInt) {
          return true;
        }
      }
      else if (j == 1)
      {
        int m = a;
        int i1 = c;
        j = a;
        while (j < m + i1)
        {
          if (a(j, i + 1) == paramInt) {
            return true;
          }
          j += 1;
        }
      }
      i += 1;
    }
    return false;
  }
  
  private void c(Label paramLabel)
  {
    int i3 = a;
    int i1 = 0;
    int m = a + c;
    int i2 = -1;
    int i = a;
    while (i < m)
    {
      int j = 0;
      int k = 0;
      if ((d.a(i) == null) && (!b(i)))
      {
        if (i2 == 1)
        {
          add(a(2, i3, i1, null));
          k = 1;
        }
        j = 0;
      }
      else
      {
        if (i2 == 0)
        {
          b(a(2, i3, i1, null));
          j = 1;
        }
        i2 = 1;
        k = j;
        j = i2;
      }
      if (k != 0)
      {
        k = i - i1;
        m -= i1;
        i = 1;
      }
      else
      {
        i1 += 1;
        k = i;
        i = i1;
      }
      k += 1;
      i1 = i;
      i = k;
      i2 = j;
    }
    Label localLabel = paramLabel;
    if (i1 != c)
    {
      d(paramLabel);
      localLabel = a(2, i3, i1, null);
    }
    if (i2 == 0)
    {
      b(localLabel);
      return;
    }
    add(localLabel);
  }
  
  private void f(Label paramLabel)
  {
    add(paramLabel);
  }
  
  private void l(Label paramLabel)
  {
    add(paramLabel);
  }
  
  public int a(int paramInt)
  {
    int m = a.size();
    int k = 0;
    for (int i = paramInt; k < m; i = paramInt)
    {
      Label localLabel = (Label)a.get(k);
      paramInt = d;
      if (paramInt != 1)
      {
        int j;
        if (paramInt != 2)
        {
          if (paramInt != 8)
          {
            paramInt = i;
          }
          else
          {
            paramInt = a;
            if (paramInt == i)
            {
              paramInt = c;
            }
            else
            {
              j = i;
              if (paramInt < i) {
                j = i - 1;
              }
              paramInt = j;
              if (c <= j) {
                paramInt = j + 1;
              }
            }
          }
        }
        else
        {
          j = a;
          paramInt = i;
          if (j <= i)
          {
            paramInt = c;
            if (j + paramInt > i) {
              return -1;
            }
            paramInt = i - paramInt;
          }
        }
      }
      else
      {
        paramInt = i;
        if (a <= i) {
          paramInt = i + c;
        }
      }
      k += 1;
    }
    return i;
  }
  
  int a(int paramInt1, int paramInt2)
  {
    int k = c.size();
    int j = paramInt2;
    for (paramInt2 = paramInt1; j < k; paramInt2 = paramInt1)
    {
      Label localLabel = (Label)c.get(j);
      int m = d;
      int i;
      if (m == 8)
      {
        paramInt1 = a;
        if (paramInt1 == paramInt2)
        {
          paramInt1 = c;
        }
        else
        {
          i = paramInt2;
          if (paramInt1 < paramInt2) {
            i = paramInt2 - 1;
          }
          paramInt1 = i;
          if (c <= i) {
            paramInt1 = i + 1;
          }
        }
      }
      else
      {
        i = a;
        paramInt1 = paramInt2;
        if (i <= paramInt2) {
          if (m == 2)
          {
            paramInt1 = c;
            if (paramInt2 < i + paramInt1) {
              return -1;
            }
            paramInt1 = paramInt2 - paramInt1;
          }
          else
          {
            paramInt1 = paramInt2;
            if (m == 1) {
              paramInt1 = paramInt2 + c;
            }
          }
        }
      }
      j += 1;
    }
    return paramInt2;
  }
  
  public Label a(int paramInt1, int paramInt2, int paramInt3, Object paramObject)
  {
    Label localLabel = (Label)w.acquire();
    if (localLabel == null) {
      return new Label(paramInt1, paramInt2, paramInt3, paramObject);
    }
    d = paramInt1;
    a = paramInt2;
    c = paramInt3;
    b = paramObject;
    return localLabel;
  }
  
  void a()
  {
    r.b(a);
    int j = a.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = (Label)a.get(i);
      int k = d;
      if (k != 1)
      {
        if (k != 2)
        {
          if (k != 4)
          {
            if (k == 8) {
              l((Label)localObject);
            }
          }
          else {
            a((Label)localObject);
          }
        }
        else {
          c((Label)localObject);
        }
      }
      else {
        f((Label)localObject);
      }
      localObject = n;
      if (localObject != null) {
        ((Runnable)localObject).run();
      }
      i += 1;
    }
    a.clear();
  }
  
  void a(List paramList)
  {
    int j = paramList.size();
    int i = 0;
    while (i < j)
    {
      d((Label)paramList.get(i));
      i += 1;
    }
    paramList.clear();
  }
  
  boolean a(int paramInt1, int paramInt2, Object paramObject)
  {
    if (paramInt2 < 1) {
      return false;
    }
    a.add(a(4, paramInt1, paramInt2, paramObject));
    g |= 0x4;
    return a.size() == 1;
  }
  
  boolean add()
  {
    return a.size() > 0;
  }
  
  boolean add(int paramInt)
  {
    return (g & paramInt) != 0;
  }
  
  void b()
  {
    int j = c.size();
    int i = 0;
    while (i < j)
    {
      d.b((Label)c.get(i));
      i += 1;
    }
    a(c);
    g = 0;
  }
  
  void b(Label paramLabel, int paramInt)
  {
    d.a(paramLabel);
    int i = d;
    if (i != 2)
    {
      if (i == 4)
      {
        d.a(paramInt, c, b);
        return;
      }
      throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
    }
    d.offsetPositionsForRemovingInvisible(paramInt, c);
  }
  
  void c()
  {
    b();
    int j = a.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = (Label)a.get(i);
      int k = d;
      if (k != 1)
      {
        if (k != 2)
        {
          if (k != 4)
          {
            if (k == 8)
            {
              d.b((Label)localObject);
              d.a(a, c);
            }
          }
          else
          {
            d.b((Label)localObject);
            d.a(a, c, b);
          }
        }
        else
        {
          d.b((Label)localObject);
          d.offsetPositionsForRemovingInvisible(a, c);
        }
      }
      else
      {
        d.b((Label)localObject);
        d.b(a, c);
      }
      localObject = n;
      if (localObject != null) {
        ((Runnable)localObject).run();
      }
      i += 1;
    }
    a(a);
    g = 0;
  }
  
  public void d(Label paramLabel)
  {
    if (!f)
    {
      b = null;
      w.release(paramLabel);
    }
  }
  
  void e()
  {
    a(a);
    a(c);
    g = 0;
  }
  
  int findPositionOffset(int paramInt)
  {
    return a(paramInt, 0);
  }
  
  boolean size()
  {
    return (!c.isEmpty()) && (!a.isEmpty());
  }
}
