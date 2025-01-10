package android.support.constraint.asm.asm;

import android.support.constraint.asm.i;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class f
  extends b
{
  protected i a = new i();
  l[] b = new l[4];
  private boolean c = false;
  int d;
  int e;
  private int i = 3;
  private e j;
  int l;
  l[] m = new l[4];
  int n = 0;
  private boolean p = false;
  int r = 0;
  int s;
  private boolean x = false;
  
  public f() {}
  
  private void c(d paramD)
  {
    int k = n;
    l[] arrayOfL = m;
    if (k + 1 >= arrayOfL.length) {
      m = ((l[])Arrays.copyOf(arrayOfL, arrayOfL.length * 2));
    }
    m[n] = new l(paramD, 1, k());
    n += 1;
  }
  
  private void clearAll()
  {
    r = 0;
    n = 0;
  }
  
  private void d(d paramD)
  {
    int k = r;
    l[] arrayOfL = b;
    if (k + 1 >= arrayOfL.length) {
      b = ((l[])Arrays.copyOf(arrayOfL, arrayOfL.length * 2));
    }
    b[r] = new l(paramD, 0, k());
    r += 1;
  }
  
  public void a()
  {
    a.b();
    l = 0;
    s = 0;
    e = 0;
    d = 0;
    super.a();
  }
  
  public void a(int paramInt)
  {
    super.a(paramInt);
    int i1 = c.size();
    int k = 0;
    while (k < i1)
    {
      ((d)c.get(k)).a(paramInt);
      k += 1;
    }
  }
  
  public void a(i paramI, boolean[] paramArrayOfBoolean)
  {
    paramArrayOfBoolean[2] = false;
    c(paramI);
    int i1 = c.size();
    int k = 0;
    while (k < i1)
    {
      d localD = (d)c.get(k);
      localD.c(paramI);
      if ((i[0] == XLayoutStyle.c) && (localD.size() < localD.e())) {
        paramArrayOfBoolean[2] = true;
      }
      if ((i[1] == XLayoutStyle.c) && (localD.getValue() < localD.getItemId())) {
        paramArrayOfBoolean[2] = true;
      }
      k += 1;
    }
  }
  
  public boolean add()
  {
    return false;
  }
  
  public void b(int paramInt)
  {
    i = paramInt;
  }
  
  public void b(int paramInt1, int paramInt2)
  {
    ByteVector localByteVector;
    if (i[0] != XLayoutStyle.b)
    {
      localByteVector = I;
      if (localByteVector != null) {
        localByteVector.a(paramInt1);
      }
    }
    if (i[1] != XLayoutStyle.b)
    {
      localByteVector = G;
      if (localByteVector != null) {
        localByteVector.a(paramInt2);
      }
    }
  }
  
  public boolean b(i paramI)
  {
    a(paramI);
    int i1 = c.size();
    int k = 0;
    while (k < i1)
    {
      d localD = (d)c.get(k);
      if ((localD instanceof f))
      {
        Object localObject = i;
        XLayoutStyle localXLayoutStyle = localObject[0];
        localObject = localObject[1];
        if (localXLayoutStyle == XLayoutStyle.b) {
          localD.b(XLayoutStyle.a);
        }
        if (localObject == XLayoutStyle.b) {
          localD.a(XLayoutStyle.a);
        }
        localD.a(paramI);
        if (localXLayoutStyle == XLayoutStyle.b) {
          localD.b(localXLayoutStyle);
        }
        if (localObject == XLayoutStyle.b) {
          localD.a((XLayoutStyle)localObject);
        }
      }
      else
      {
        g.a(this, paramI, localD);
        localD.a(paramI);
      }
      k += 1;
    }
    if (r > 0) {
      ClassReader.a(this, paramI, 0);
    }
    if (n > 0) {
      ClassReader.a(this, paramI, 1);
    }
    return true;
  }
  
  public void c(boolean paramBoolean)
  {
    p = paramBoolean;
  }
  
  public void clear()
  {
    int i1 = c.size();
    setChecked();
    int k = 0;
    while (k < i1)
    {
      ((d)c.get(k)).setChecked();
      k += 1;
    }
  }
  
  void d(d paramD, int paramInt)
  {
    if (paramInt == 0)
    {
      d(paramD);
      return;
    }
    if (paramInt == 1) {
      c(paramD);
    }
  }
  
  public boolean f(int paramInt)
  {
    return (i & paramInt) == paramInt;
  }
  
  public int h()
  {
    return i;
  }
  
  public void i()
  {
    clear();
    a(i);
  }
  
  public boolean k()
  {
    return p;
  }
  
  public boolean o()
  {
    return c;
  }
  
  public void onCreate()
  {
    if (!f(8)) {
      a(i);
    }
    setTitle();
  }
  
  public boolean r()
  {
    return x;
  }
  
  public void run()
  {
    int i5 = n;
    int i6 = type;
    int i7 = Math.max(0, size());
    int i8 = Math.max(0, getValue());
    x = false;
    c = false;
    if (r != null)
    {
      if (j == null) {
        j = new e(this);
      }
      j.a(this);
      append(l);
      setText(e);
      flagActionItems();
      a(a.g());
    }
    else
    {
      n = 0;
      type = 0;
    }
    if (i != 0)
    {
      if (!f(8)) {
        clear();
      }
      onCreate();
      a.r = true;
    }
    else
    {
      a.r = false;
    }
    Object localObject2 = i;
    Object localObject1 = localObject2[1];
    localObject2 = localObject2[0];
    clearAll();
    int i9 = c.size();
    int k = 0;
    Object localObject3;
    while (k < i9)
    {
      localObject3 = (d)c.get(k);
      if ((localObject3 instanceof b)) {
        ((b)localObject3).run();
      }
      k += 1;
    }
    int i11 = 1;
    int i1 = 0;
    k = 0;
    Object localObject4;
    int i2;
    while (i11 != 0)
    {
      int i4 = i1 + 1;
      localObject3 = a;
      int i10 = i11;
      try
      {
        ((i)localObject3).b();
        localObject3 = a;
        i10 = i11;
        d((i)localObject3);
        i1 = 0;
        while (i1 < i9)
        {
          localObject3 = c;
          i10 = i11;
          localObject3 = ((ArrayList)localObject3).get(i1);
          localObject3 = (d)localObject3;
          localObject5 = a;
          i10 = i11;
          ((d)localObject3).d((i)localObject5);
          i1 += 1;
        }
        localObject3 = a;
        i10 = i11;
        bool = b((i)localObject3);
        i11 = bool;
        if (bool)
        {
          localObject3 = a;
          i10 = i11;
          ((i)localObject3).d();
        }
        i10 = i11;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        Object localObject5 = System.out;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("EXCEPTION : ");
        localStringBuilder.append(localException);
        ((PrintStream)localObject5).println(localStringBuilder.toString());
      }
      if (i10 != 0)
      {
        a(a, g.b);
      }
      else
      {
        c(a);
        i1 = 0;
        while (i1 < i9)
        {
          localObject4 = (d)c.get(i1);
          if (i[0] == XLayoutStyle.c) {
            if (((d)localObject4).size() < ((d)localObject4).e())
            {
              g.b[2] = true;
              break;
            }
          }
          if ((i[1] == XLayoutStyle.c) && (((d)localObject4).getValue() < ((d)localObject4).getItemId()))
          {
            g.b[2] = true;
            break;
          }
          i1 += 1;
        }
      }
      i11 = 0;
      boolean bool = false;
      i1 = k;
      i10 = i11;
      if (i4 < 8)
      {
        i1 = k;
        i10 = i11;
        if (g.b[2] != 0)
        {
          int i3 = 0;
          i2 = 0;
          i1 = 0;
          while (i1 < i9)
          {
            localObject4 = (d)c.get(i1);
            i3 = Math.max(i3, n + ((d)localObject4).size());
            i2 = Math.max(i2, type + ((d)localObject4).getValue());
            i1 += 1;
          }
          i1 = Math.max(d, i3);
          i3 = Math.max(e, i2);
          i2 = k;
          i11 = bool;
          if (localObject2 == XLayoutStyle.b)
          {
            i2 = k;
            i11 = bool;
            if (size() < i1)
            {
              d(i1);
              i[0] = XLayoutStyle.b;
              i2 = 1;
              i11 = 1;
            }
          }
          i1 = i2;
          i10 = i11;
          if (localObject1 == XLayoutStyle.b)
          {
            i1 = i2;
            i10 = i11;
            if (getValue() < i3)
            {
              c(i3);
              i[1] = XLayoutStyle.b;
              i1 = 1;
              i10 = 1;
            }
          }
        }
      }
      i2 = Math.max(d, size());
      k = i1;
      if (i2 > size())
      {
        d(i2);
        i[0] = XLayoutStyle.a;
        k = 1;
        i10 = 1;
      }
      i1 = Math.max(e, getValue());
      if (i1 > getValue())
      {
        c(i1);
        i[1] = XLayoutStyle.a;
        k = 1;
        i10 = 1;
      }
      i1 = k;
      i11 = i10;
      if (k == 0)
      {
        i1 = k;
        i11 = i10;
        if (i[0] == XLayoutStyle.b)
        {
          i1 = k;
          i11 = i10;
          if (i7 > 0)
          {
            i1 = k;
            i11 = i10;
            if (size() > i7)
            {
              x = true;
              i1 = 1;
              i[0] = XLayoutStyle.a;
              d(i7);
              i11 = 1;
            }
          }
        }
        if ((i[1] == XLayoutStyle.b) && (i8 > 0) && (getValue() > i8))
        {
          c = true;
          i[1] = XLayoutStyle.a;
          c(i8);
          i11 = 1;
          i1 = 1;
        }
        else {}
      }
      k = i1;
      i1 = i4;
    }
    if (r != null)
    {
      i1 = Math.max(d, size());
      i2 = Math.max(e, getValue());
      j.b(this);
      d(l + i1 + s);
      c(e + i2 + d);
    }
    else
    {
      n = i5;
      type = i6;
    }
    if (k != 0)
    {
      localObject4 = i;
      localObject4[0] = localObject2;
      localObject4[1] = localObject1;
    }
    a(a.g());
    if (this == close()) {
      next();
    }
  }
  
  public void setTitle()
  {
    m localM1 = a(c.a).a();
    m localM2 = a(c.i).a();
    localM1.a(null, 0.0F);
    localM2.a(null, 0.0F);
  }
}
