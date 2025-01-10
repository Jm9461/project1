package android.support.constraint.asm.asm;

import android.support.constraint.i.j.k;
import java.util.ArrayList;

public class a
  extends i
{
  private ArrayList<k> a = new ArrayList(4);
  private int i = 0;
  private boolean p = true;
  
  public a() {}
  
  public void a(int paramInt)
  {
    Object localObject = r;
    if (localObject == null) {
      return;
    }
    if (!((f)localObject).f(2)) {
      return;
    }
    paramInt = i;
    m localM;
    if (paramInt != 0)
    {
      if (paramInt != 1)
      {
        if (paramInt != 2)
        {
          if (paramInt != 3) {
            return;
          }
          localM = a.a();
        }
        else
        {
          localM = c.a();
        }
      }
      else {
        localM = g.a();
      }
    }
    else {
      localM = b.a();
    }
    localM.a(5);
    paramInt = i;
    if ((paramInt != 0) && (paramInt != 1))
    {
      b.a().a(null, 0.0F);
      g.a().a(null, 0.0F);
    }
    else
    {
      c.a().a(null, 0.0F);
      a.a().a(null, 0.0F);
    }
    a.clear();
    paramInt = 0;
    while (paramInt < r)
    {
      d localD = b[paramInt];
      if ((p) || (localD.c()))
      {
        localObject = null;
        int j = i;
        if (j != 0)
        {
          if (j != 1)
          {
            if (j != 2)
            {
              if (j == 3) {
                localObject = a.a();
              }
            }
            else {
              localObject = c.a();
            }
          }
          else {
            localObject = g.a();
          }
        }
        else {
          localObject = b.a();
        }
        if (localObject != null)
        {
          a.add(localObject);
          ((Label)localObject).a(localM);
        }
      }
      paramInt += 1;
    }
  }
  
  public void a(android.support.constraint.asm.i paramI)
  {
    Object localObject1 = C;
    a localA = this;
    localObject1[0] = b;
    localObject1[2] = c;
    localObject1[1] = g;
    localObject1[3] = a;
    int j = 0;
    for (;;)
    {
      localObject1 = C;
      if (j >= localObject1.length) {
        break;
      }
      c = paramI.a(localObject1[j]);
      j += 1;
    }
    j = i;
    if ((j >= 0) && (j < 4))
    {
      localObject1 = localObject1[j];
      boolean bool2 = false;
      j = 0;
      boolean bool1;
      Object localObject2;
      int k;
      for (;;)
      {
        bool1 = bool2;
        if (j >= r) {
          break;
        }
        localObject2 = b[j];
        if ((p) || (((d)localObject2).c()))
        {
          k = i;
          if (((k == 0) || (k == 1)) && (((d)localObject2).g() == XLayoutStyle.c))
          {
            bool1 = true;
            break;
          }
          k = i;
          if (((k == 2) || (k == 3)) && (((d)localObject2).f() == XLayoutStyle.c))
          {
            bool1 = true;
            break;
          }
        }
        j += 1;
      }
      j = i;
      if ((j != 0) && (j != 1))
      {
        if (localA.get().f() == XLayoutStyle.b) {
          bool1 = false;
        }
      }
      else if (localA.get().g() == XLayoutStyle.b) {
        bool1 = false;
      }
      j = 0;
      while (j < r)
      {
        Object localObject3 = b[j];
        if ((p) || (((d)localObject3).c()))
        {
          localObject2 = paramI.a(C[i]);
          localObject3 = C;
          k = i;
          c = ((android.support.constraint.asm.Label)localObject2);
          if ((k != 0) && (k != 2)) {
            paramI.b(c, (android.support.constraint.asm.Label)localObject2, bool1);
          } else {
            paramI.a(c, (android.support.constraint.asm.Label)localObject2, bool1);
          }
        }
        j += 1;
      }
      j = i;
      if (j == 0)
      {
        paramI.a(g.c, b.c, 0, 6);
        if (!bool1) {
          paramI.a(b.c, r.g.c, 0, 5);
        }
      }
      else if (j == 1)
      {
        paramI.a(b.c, g.c, 0, 6);
        if (!bool1) {
          paramI.a(b.c, r.b.c, 0, 5);
        }
      }
      else if (j == 2)
      {
        paramI.a(a.c, c.c, 0, 6);
        if (!bool1) {
          paramI.a(c.c, r.a.c, 0, 5);
        }
      }
      else if (j == 3)
      {
        paramI.a(c.c, a.c, 0, 6);
        if (!bool1) {
          paramI.a(c.c, r.c.c, 0, 5);
        }
      }
    }
  }
  
  public boolean c()
  {
    return true;
  }
  
  public void draw()
  {
    float f1 = 0.0F;
    int j = i;
    m localM1;
    if (j != 0)
    {
      if (j != 1)
      {
        if (j != 2)
        {
          if (j != 3) {
            return;
          }
          localM1 = a.a();
        }
        else
        {
          localM1 = c.a();
          f1 = Float.MAX_VALUE;
        }
      }
      else {
        localM1 = g.a();
      }
    }
    else
    {
      localM1 = b.a();
      f1 = Float.MAX_VALUE;
    }
    int k = a.size();
    m localM2 = null;
    j = 0;
    Object localObject;
    for (float f2 = f1; j < k; f2 = f1)
    {
      localObject = (m)a.get(j);
      if (b != 1) {
        return;
      }
      int m = i;
      if ((m != 0) && (m != 2))
      {
        f1 = f2;
        if (e > f2)
        {
          f1 = e;
          localM2 = a;
        }
      }
      else
      {
        f1 = f2;
        if (e < f2)
        {
          f1 = e;
          localM2 = a;
        }
      }
      j += 1;
    }
    if (android.support.constraint.asm.i.size() != null)
    {
      localObject = android.support.constraint.asm.i.size();
      a += 1L;
    }
    a = localM2;
    e = f2;
    localM1.draw();
    j = i;
    if (j != 0)
    {
      if (j != 1)
      {
        if (j != 2)
        {
          if (j != 3) {
            return;
          }
          c.a().a(localM2, f2);
          return;
        }
        a.a().a(localM2, f2);
        return;
      }
      b.a().a(localM2, f2);
      return;
    }
    g.a().a(localM2, f2);
  }
  
  public void set(int paramInt)
  {
    i = paramInt;
  }
  
  public void setChecked()
  {
    super.setChecked();
    a.clear();
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    p = paramBoolean;
  }
}
