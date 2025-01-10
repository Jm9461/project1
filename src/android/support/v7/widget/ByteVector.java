package android.support.v7.widget;

import java.util.List;

class ByteVector
{
  final s0.a a;
  
  ByteVector(s0.a paramA)
  {
    a = paramA;
  }
  
  private int a(List paramList)
  {
    int j = 0;
    int i = paramList.size() - 1;
    while (i >= 0)
    {
      int k;
      if (getd == 8)
      {
        k = j;
        if (j != 0) {
          return i;
        }
      }
      else
      {
        k = 1;
      }
      i -= 1;
      j = k;
    }
    return -1;
  }
  
  private void a(List paramList, int paramInt1, int paramInt2)
  {
    Label localLabel1 = (Label)paramList.get(paramInt1);
    Label localLabel2 = (Label)paramList.get(paramInt2);
    int i = d;
    if (i != 1)
    {
      if (i != 2)
      {
        if (i != 4) {
          return;
        }
        a(paramList, paramInt1, localLabel1, paramInt2, localLabel2);
        return;
      }
      b(paramList, paramInt1, localLabel1, paramInt2, localLabel2);
      return;
    }
    add(paramList, paramInt1, localLabel1, paramInt2, localLabel2);
  }
  
  private void add(List paramList, int paramInt1, Label paramLabel1, int paramInt2, Label paramLabel2)
  {
    int i = 0;
    if (c < a) {
      i = 0 - 1;
    }
    int j = i;
    if (a < a) {
      j = i + 1;
    }
    i = a;
    int k = a;
    if (i <= k) {
      a = (k + c);
    }
    i = a;
    k = c;
    if (i <= k) {
      c = (k + c);
    }
    a += j;
    paramList.set(paramInt1, paramLabel2);
    paramList.set(paramInt2, paramLabel1);
  }
  
  void a(List paramList, int paramInt1, Label paramLabel1, int paramInt2, Label paramLabel2)
  {
    Label localLabel1 = null;
    Label localLabel2 = null;
    int i = c;
    int j = a;
    int k;
    if (i < j)
    {
      a = (j - 1);
    }
    else
    {
      k = c;
      if (i < j + k)
      {
        c = (k - 1);
        localLabel1 = a.a(4, a, 1, b);
      }
    }
    i = a;
    j = a;
    if (i <= j)
    {
      a = (j + 1);
    }
    else
    {
      k = c;
      if (i < j + k)
      {
        j = j + k - i;
        localLabel2 = a.a(4, i + 1, j, b);
        c -= j;
      }
    }
    paramList.set(paramInt2, paramLabel1);
    if (c > 0)
    {
      paramList.set(paramInt1, paramLabel2);
    }
    else
    {
      paramList.remove(paramInt1);
      a.d(paramLabel2);
    }
    if (localLabel1 != null) {
      paramList.add(paramInt1, localLabel1);
    }
    if (localLabel2 != null) {
      paramList.add(paramInt1, localLabel2);
    }
  }
  
  void b(List paramList)
  {
    for (;;)
    {
      int i = a(paramList);
      if (i == -1) {
        break;
      }
      a(paramList, i, i + 1);
    }
  }
  
  void b(List paramList, int paramInt1, Label paramLabel1, int paramInt2, Label paramLabel2)
  {
    Label localLabel = null;
    int k = 0;
    int n = a;
    int i1 = c;
    int i;
    int j;
    if (n < i1)
    {
      m = 0;
      i = k;
      j = m;
      if (a == n)
      {
        i = k;
        j = m;
        if (c == i1 - n)
        {
          i = 1;
          j = m;
        }
      }
    }
    else
    {
      m = 1;
      i = k;
      j = m;
      if (a == i1 + 1)
      {
        i = k;
        j = m;
        if (c == n - i1)
        {
          i = 1;
          j = m;
        }
      }
    }
    k = c;
    int m = a;
    if (k < m)
    {
      a = (m - 1);
    }
    else
    {
      n = c;
      if (k < m + n)
      {
        c = (n - 1);
        d = 2;
        c = 1;
        if (c != 0) {
          return;
        }
        paramList.remove(paramInt2);
        a.d(paramLabel2);
        return;
      }
    }
    k = a;
    m = a;
    if (k <= m)
    {
      a = (m + 1);
    }
    else
    {
      n = c;
      if (k < m + n)
      {
        localLabel = a.a(2, k + 1, m + n - k, null);
        c = (a - a);
      }
    }
    if (i != 0)
    {
      paramList.set(paramInt1, paramLabel2);
      paramList.remove(paramInt2);
      a.d(paramLabel1);
      return;
    }
    if (j != 0)
    {
      if (localLabel != null)
      {
        i = a;
        if (i > a) {
          a = (i - c);
        }
        i = c;
        if (i > a) {
          c = (i - c);
        }
      }
      i = a;
      if (i > a) {
        a = (i - c);
      }
      i = c;
      if (i > a) {
        c = (i - c);
      }
    }
    else
    {
      if (localLabel != null)
      {
        i = a;
        if (i >= a) {
          a = (i - c);
        }
        i = c;
        if (i >= a) {
          c = (i - c);
        }
      }
      i = a;
      if (i >= a) {
        a = (i - c);
      }
      i = c;
      if (i >= a) {
        c = (i - c);
      }
    }
    paramList.set(paramInt1, paramLabel2);
    if (a != c) {
      paramList.set(paramInt2, paramLabel1);
    } else {
      paramList.remove(paramInt2);
    }
    if (localLabel != null) {
      paramList.add(paramInt1, localLabel);
    }
  }
}
