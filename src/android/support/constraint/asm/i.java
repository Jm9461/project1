package android.support.constraint.asm;

import android.support.constraint.asm.asm.c;
import java.util.Arrays;
import java.util.HashMap;

public class i
{
  private static int o = 1000;
  public static m w;
  h[] a;
  int b;
  private int c = 32;
  private final ByteVector d;
  private int f;
  int h;
  int i = 0;
  private HashMap<String, android.support.constraint.i.i> j = null;
  final b k;
  private Label[] l;
  private boolean[] n;
  private int p;
  public boolean r;
  private int s;
  private ByteVector x;
  
  public i()
  {
    int m = c;
    f = m;
    a = null;
    r = false;
    n = new boolean[m];
    h = 1;
    b = 0;
    p = m;
    l = new Label[o];
    s = 0;
    h[] arrayOfH = new h[m];
    a = new h[m];
    clear();
    k = new b();
    x = new o(k);
    d = new h(k);
  }
  
  private int a(ByteVector paramByteVector)
  {
    int i2 = 0;
    int m = 0;
    int i1;
    for (;;)
    {
      i1 = i2;
      if (m >= b) {
        break;
      }
      paramByteVector = a;
      if ((c.d != f.b) && (i < 0.0F))
      {
        i1 = 1;
        break;
      }
      m += 1;
    }
    if (i1 != 0)
    {
      int i3 = 0;
      int i10;
      for (m = 0; i3 == 0; m = i10)
      {
        paramByteVector = w;
        if (paramByteVector != null) {
          l += 1L;
        }
        i10 = m + 1;
        float f1 = Float.MAX_VALUE;
        m = 0;
        i1 = -1;
        i2 = -1;
        int i4 = 0;
        Object localObject;
        while (i4 < b)
        {
          paramByteVector = a[i4];
          int i6;
          float f2;
          int i7;
          int i8;
          if (c.d == f.b)
          {
            i6 = m;
            f2 = f1;
            i7 = i1;
            i8 = i2;
          }
          else if (h)
          {
            i6 = m;
            f2 = f1;
            i7 = i1;
            i8 = i2;
          }
          else
          {
            i6 = m;
            f2 = f1;
            i7 = i1;
            i8 = i2;
            if (i < 0.0F)
            {
              int i5 = 1;
              for (;;)
              {
                i6 = m;
                f2 = f1;
                i7 = i1;
                i8 = i2;
                if (i5 >= h) {
                  break;
                }
                localObject = k.b[i5];
                float f3 = b.a((Label)localObject);
                int i9;
                if (f3 <= 0.0F)
                {
                  i7 = m;
                  f2 = f1;
                  i8 = i1;
                  i9 = i2;
                }
                else
                {
                  i6 = 0;
                  for (;;)
                  {
                    i7 = m;
                    f2 = f1;
                    i8 = i1;
                    i9 = i2;
                    if (i6 >= 7) {
                      break;
                    }
                    f2 = a[i6] / f3;
                    if ((f2 >= f1) || (i6 != m))
                    {
                      i7 = m;
                      if (i6 <= m) {}
                    }
                    else
                    {
                      f1 = f2;
                      i1 = i4;
                      i2 = i5;
                      i7 = i6;
                    }
                    i6 += 1;
                    m = i7;
                  }
                }
                i5 += 1;
                m = i7;
                f1 = f2;
                i1 = i8;
                i2 = i9;
              }
            }
          }
          i4 += 1;
          m = i6;
          f1 = f2;
          i1 = i7;
          i2 = i8;
        }
        if (i1 != -1)
        {
          paramByteVector = a[i1];
          c.c = -1;
          localObject = w;
          if (localObject != null) {
            c += 1L;
          }
          paramByteVector.a(k.b[i2]);
          localObject = c;
          c = i1;
          ((Label)localObject).c(paramByteVector);
        }
        else
        {
          i3 = 1;
        }
        if (i10 > h / 2) {
          i3 = 1;
        }
      }
    }
    return 0;
    return m;
  }
  
  private final int a(ByteVector paramByteVector, boolean paramBoolean)
  {
    Object localObject1 = w;
    if (localObject1 != null) {
      v += 1L;
    }
    int i3 = 0;
    int i4 = 0;
    int i2 = 0;
    int i1;
    int m;
    for (;;)
    {
      i1 = i4;
      m = i3;
      if (i2 >= h) {
        break;
      }
      n[i2] = false;
      i2 += 1;
    }
    while (m == 0)
    {
      localObject1 = w;
      if (localObject1 != null) {
        w += 1L;
      }
      i4 = i1 + 1;
      if (i4 >= h * 2) {
        return i4;
      }
      if (paramByteVector.getKey() != null) {
        n[getKeyi] = true;
      }
      localObject1 = paramByteVector.a(this, n);
      Object localObject2;
      if (localObject1 != null)
      {
        localObject2 = n;
        i1 = i;
        if (localObject2[i1] != 0) {
          return i4;
        }
        localObject2[i1] = 1;
      }
      if (localObject1 != null)
      {
        float f1 = Float.MAX_VALUE;
        i2 = -1;
        i1 = 0;
        while (i1 < b)
        {
          localObject2 = a[i1];
          float f2;
          if (c.d == f.b)
          {
            i3 = i2;
            f2 = f1;
          }
          else if (h)
          {
            i3 = i2;
            f2 = f1;
          }
          else
          {
            i3 = i2;
            f2 = f1;
            if (((h)localObject2).d((Label)localObject1))
            {
              float f3 = b.a((Label)localObject1);
              i3 = i2;
              f2 = f1;
              if (f3 < 0.0F)
              {
                f3 = -i / f3;
                i3 = i2;
                f2 = f1;
                if (f3 < f1)
                {
                  f2 = f3;
                  i3 = i1;
                }
              }
            }
          }
          i1 += 1;
          i2 = i3;
          f1 = f2;
        }
        if (i2 > -1)
        {
          localObject2 = a[i2];
          c.c = -1;
          m localM = w;
          if (localM != null) {
            c += 1L;
          }
          ((h)localObject2).a((Label)localObject1);
          localObject1 = c;
          c = i2;
          ((Label)localObject1).c((h)localObject2);
        }
        else
        {
          m = 1;
        }
        i1 = i4;
      }
      else
      {
        m = 1;
        i1 = i4;
      }
    }
    return i1;
  }
  
  private Label a(f paramF, String paramString)
  {
    Object localObject = (Label)k.i.a();
    if (localObject == null)
    {
      Label localLabel = new Label(paramF, paramString);
      localObject = localLabel;
      localLabel.a(paramF, paramString);
    }
    else
    {
      ((Label)localObject).a();
      ((Label)localObject).a(paramF, paramString);
    }
    int m = s;
    int i1 = o;
    if (m >= i1)
    {
      o = i1 * 2;
      l = ((Label[])Arrays.copyOf(l, o));
    }
    paramF = l;
    m = s;
    s = (m + 1);
    paramF[m] = localObject;
    return localObject;
  }
  
  public static h a(i paramI, Label paramLabel1, Label paramLabel2, Label paramLabel3, float paramFloat, boolean paramBoolean)
  {
    h localH = paramI.a();
    if (paramBoolean) {
      paramI.d(localH);
    }
    localH.a(paramLabel1, paramLabel2, paramLabel3, paramFloat);
    return localH;
  }
  
  private void add()
  {
    c *= 2;
    a = ((h[])Arrays.copyOf(a, c));
    Object localObject = k;
    b = ((Label[])Arrays.copyOf(b, c));
    int m = c;
    n = new boolean[m];
    f = m;
    p = m;
    localObject = w;
    if (localObject != null)
    {
      size += 1L;
      n = Math.max(n, m);
      localObject = w;
      o = n;
    }
  }
  
  private final void b(h paramH)
  {
    Object localObject = a;
    int m = b;
    if (localObject[m] != null) {
      k.k.a(localObject[m]);
    }
    localObject = a;
    m = b;
    localObject[m] = paramH;
    localObject = c;
    c = m;
    b = (m + 1);
    ((Label)localObject).c(paramH);
  }
  
  private final void c(h paramH)
  {
    if (b > 0)
    {
      b.a(paramH, a);
      if (b.d == 0) {
        h = true;
      }
    }
  }
  
  private void clear()
  {
    int m = 0;
    for (;;)
    {
      Object localObject = a;
      if (m >= localObject.length) {
        break;
      }
      localObject = localObject[m];
      if (localObject != null) {
        k.k.a(localObject);
      }
      a[m] = null;
      m += 1;
    }
  }
  
  private void d(h paramH)
  {
    paramH.a(this, 0);
  }
  
  public static m size()
  {
    return w;
  }
  
  private void visitMaxs()
  {
    int m = 0;
    while (m < b)
    {
      h localH = a[m];
      c.g = i;
      m += 1;
    }
  }
  
  public Label a(int paramInt, String paramString)
  {
    m localM = w;
    if (localM != null) {
      m += 1L;
    }
    if (h + 1 >= f) {
      add();
    }
    paramString = a(f.c, paramString);
    i += 1;
    h += 1;
    int m = i;
    i = m;
    e = paramInt;
    k.b[m] = paramString;
    x.b(paramString);
    return paramString;
  }
  
  public Label a(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    if (h + 1 >= f) {
      add();
    }
    Label localLabel1;
    if ((paramObject instanceof android.support.constraint.asm.asm.h))
    {
      Label localLabel2 = ((android.support.constraint.asm.asm.h)paramObject).c();
      localLabel1 = localLabel2;
      if (localLabel2 == null)
      {
        ((android.support.constraint.asm.asm.h)paramObject).a(k);
        localLabel1 = ((android.support.constraint.asm.asm.h)paramObject).c();
      }
      int m = i;
      if ((m == -1) || (m > i) || (k.b[m] == null))
      {
        if (i != -1) {
          localLabel1.a();
        }
        i += 1;
        h += 1;
        m = i;
        i = m;
        d = f.b;
        k.b[m] = localLabel1;
        return localLabel1;
      }
    }
    else
    {
      return null;
    }
    return localLabel1;
  }
  
  public h a()
  {
    h localH = (h)k.k.a();
    if (localH == null) {
      localH = new h(k);
    } else {
      localH.d();
    }
    Label.b();
    return localH;
  }
  
  public h a(Label paramLabel1, Label paramLabel2, int paramInt1, int paramInt2)
  {
    h localH = a();
    localH.a(paramLabel1, paramLabel2, paramInt1);
    if (paramInt2 != 6) {
      localH.a(this, paramInt2);
    }
    a(localH);
    return localH;
  }
  
  public void a(Label paramLabel, int paramInt)
  {
    int m = c;
    if (c != -1)
    {
      localH = a[m];
      if (h)
      {
        i = paramInt;
        return;
      }
      if (b.d == 0)
      {
        h = true;
        i = paramInt;
        return;
      }
      localH = a();
      localH.a(paramLabel, paramInt);
      a(localH);
      return;
    }
    h localH = a();
    localH.c(paramLabel, paramInt);
    a(localH);
  }
  
  public void a(Label paramLabel1, Label paramLabel2, boolean paramBoolean)
  {
    h localH = a();
    Label localLabel = c();
    e = 0;
    localH.b(paramLabel1, paramLabel2, localLabel, 0);
    if (paramBoolean) {
      b(localH, (int)(-1.0F * b.a(localLabel)), 1);
    }
    a(localH);
  }
  
  public void a(android.support.constraint.asm.asm.d paramD1, android.support.constraint.asm.asm.d paramD2, float paramFloat, int paramInt)
  {
    Label localLabel1 = a(paramD1.a(c.a));
    Label localLabel3 = a(paramD1.a(c.i));
    Label localLabel2 = a(paramD1.a(c.d));
    Label localLabel5 = a(paramD1.a(c.c));
    paramD1 = a(paramD2.a(c.a));
    Label localLabel6 = a(paramD2.a(c.i));
    Label localLabel4 = a(paramD2.a(c.d));
    paramD2 = a(paramD2.a(c.c));
    h localH = a();
    double d1 = Math.sin(paramFloat);
    double d2 = paramInt;
    Double.isNaN(d2);
    localH.b(localLabel3, localLabel5, localLabel6, paramD2, (float)(d1 * d2));
    a(localH);
    paramD2 = a();
    d1 = Math.cos(paramFloat);
    d2 = paramInt;
    Double.isNaN(d2);
    paramD2.b(localLabel1, localLabel2, paramD1, localLabel4, (float)(d1 * d2));
    a(paramD2);
  }
  
  public void a(h paramH)
  {
    if (paramH == null) {
      return;
    }
    Object localObject = w;
    if (localObject != null)
    {
      e += 1L;
      if (h) {
        d += 1L;
      }
    }
    if ((b + 1 >= p) || (h + 1 >= f)) {
      add();
    }
    int m = 0;
    int i1 = 0;
    if (!h)
    {
      c(paramH);
      if (paramH.c()) {
        return;
      }
      paramH.a();
      m = i1;
      if (paramH.a(this))
      {
        localObject = e();
        c = ((Label)localObject);
        b(paramH);
        i1 = 1;
        d.a(paramH);
        a(d, true);
        m = i1;
        if (c == -1)
        {
          if (c == localObject)
          {
            localObject = paramH.c((Label)localObject);
            if (localObject != null)
            {
              m localM = w;
              if (localM != null) {
                c += 1L;
              }
              paramH.a((Label)localObject);
            }
          }
          if (!h) {
            c.c(paramH);
          }
          b -= 1;
          m = i1;
        }
      }
      if (!paramH.b()) {
        return;
      }
    }
    if (m == 0) {
      b(paramH);
    }
  }
  
  public void add(Label paramLabel1, Label paramLabel2, int paramInt1, float paramFloat, Label paramLabel3, Label paramLabel4, int paramInt2, int paramInt3)
  {
    h localH = a();
    localH.a(paramLabel1, paramLabel2, paramInt1, paramFloat, paramLabel3, paramLabel4, paramInt2);
    if (paramInt3 != 6) {
      localH.a(this, paramInt3);
    }
    a(localH);
  }
  
  public void add(Label paramLabel1, Label paramLabel2, Label paramLabel3, Label paramLabel4, float paramFloat, int paramInt)
  {
    h localH = a();
    localH.a(paramLabel1, paramLabel2, paramLabel3, paramLabel4, paramFloat);
    if (paramInt != 6) {
      localH.a(this, paramInt);
    }
    a(localH);
  }
  
  public int b(Object paramObject)
  {
    paramObject = ((android.support.constraint.asm.asm.h)paramObject).c();
    if (paramObject != null) {
      return (int)(g + 0.5F);
    }
    return 0;
  }
  
  public void b()
  {
    int m = 0;
    for (;;)
    {
      localObject = k;
      Label[] arrayOfLabel = b;
      if (m >= arrayOfLabel.length) {
        break;
      }
      localObject = arrayOfLabel[m];
      if (localObject != null) {
        ((Label)localObject).a();
      }
      m += 1;
    }
    i.a(l, s);
    s = 0;
    Arrays.fill(k.b, null);
    Object localObject = j;
    if (localObject != null) {
      ((HashMap)localObject).clear();
    }
    i = 0;
    x.clear();
    h = 1;
    m = 0;
    while (m < b)
    {
      a[m].w = false;
      m += 1;
    }
    clear();
    b = 0;
  }
  
  public void b(Label paramLabel1, Label paramLabel2, int paramInt1, int paramInt2)
  {
    h localH = a();
    Label localLabel = c();
    e = 0;
    localH.a(paramLabel1, paramLabel2, localLabel, paramInt1);
    if (paramInt2 != 6) {
      b(localH, (int)(-1.0F * b.a(localLabel)), paramInt2);
    }
    a(localH);
  }
  
  public void b(Label paramLabel1, Label paramLabel2, boolean paramBoolean)
  {
    h localH = a();
    Label localLabel = c();
    e = 0;
    localH.a(paramLabel1, paramLabel2, localLabel, 0);
    if (paramBoolean) {
      b(localH, (int)(-1.0F * b.a(localLabel)), 1);
    }
    a(localH);
  }
  
  void b(h paramH, int paramInt1, int paramInt2)
  {
    paramH.b(a(paramInt2, null), paramInt1);
  }
  
  public Label c()
  {
    Object localObject = w;
    if (localObject != null) {
      k += 1L;
    }
    if (h + 1 >= f) {
      add();
    }
    localObject = a(f.a, null);
    i += 1;
    h += 1;
    int m = i;
    i = m;
    k.b[m] = localObject;
    return localObject;
  }
  
  public void d()
  {
    m localM = w;
    if (localM != null) {
      p += 1L;
    }
    if (r)
    {
      localM = w;
      if (localM != null) {
        h += 1L;
      }
      int i2 = 1;
      int m = 0;
      int i1;
      for (;;)
      {
        i1 = i2;
        if (m >= b) {
          break;
        }
        if (!a[m].h)
        {
          i1 = 0;
          break;
        }
        m += 1;
      }
      if (i1 == 0)
      {
        d(x);
        return;
      }
      localM = w;
      if (localM != null) {
        z += 1L;
      }
      visitMaxs();
      return;
    }
    d(x);
  }
  
  void d(ByteVector paramByteVector)
  {
    m localM = w;
    if (localM != null)
    {
      s += 1L;
      t = Math.max(t, h);
      localM = w;
      x = Math.max(x, b);
    }
    c((h)paramByteVector);
    a(paramByteVector);
    a(paramByteVector, false);
    visitMaxs();
  }
  
  public void d(Label paramLabel1, Label paramLabel2, int paramInt1, int paramInt2)
  {
    h localH = a();
    Label localLabel = c();
    e = 0;
    localH.b(paramLabel1, paramLabel2, localLabel, paramInt1);
    if (paramInt2 != 6) {
      b(localH, (int)(-1.0F * b.a(localLabel)), paramInt2);
    }
    a(localH);
  }
  
  public Label e()
  {
    Object localObject = w;
    if (localObject != null) {
      f += 1L;
    }
    if (h + 1 >= f) {
      add();
    }
    localObject = a(f.a, null);
    i += 1;
    h += 1;
    int m = i;
    i = m;
    k.b[m] = localObject;
    return localObject;
  }
  
  public b g()
  {
    return k;
  }
}
