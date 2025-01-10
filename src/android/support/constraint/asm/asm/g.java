package android.support.constraint.asm.asm;

import android.support.constraint.asm.i;

public class g
{
  static boolean[] b = new boolean[3];
  
  static void a(int paramInt, d paramD)
  {
    paramD.updateMenuView();
    Object localObject = b.a();
    m localM1 = c.a();
    m localM2 = g.a();
    m localM3 = a.a();
    if ((paramInt & 0x8) == 8) {
      paramInt = 1;
    } else {
      paramInt = 0;
    }
    int i;
    if ((i[0] == XLayoutStyle.c) && (b(paramD, 0))) {
      i = 1;
    } else {
      i = 0;
    }
    if ((y != 4) && (y != 4)) {
      if ((i[0] != XLayoutStyle.a) && ((i == 0) || (paramD.l() != 8)))
      {
        if (i != 0)
        {
          i = paramD.size();
          ((m)localObject).a(1);
          localM2.a(1);
          if ((b.b == null) && (g.b == null))
          {
            if (paramInt != 0) {
              localM2.a((m)localObject, 1, paramD.putByte());
            } else {
              localM2.a((m)localObject, i);
            }
          }
          else if ((b.b != null) && (g.b == null))
          {
            if (paramInt != 0) {
              localM2.a((m)localObject, 1, paramD.putByte());
            } else {
              localM2.a((m)localObject, i);
            }
          }
          else if ((b.b == null) && (g.b != null))
          {
            if (paramInt != 0) {
              ((m)localObject).a(localM2, -1, paramD.putByte());
            } else {
              ((m)localObject).a(localM2, -i);
            }
          }
          else if ((b.b != null) && (g.b != null))
          {
            if (paramInt != 0)
            {
              paramD.putByte().a((Label)localObject);
              paramD.putByte().a(localM2);
            }
            if (y == 0.0F)
            {
              ((m)localObject).a(3);
              localM2.a(3);
              ((m)localObject).b(localM2, 0.0F);
              localM2.b((m)localObject, 0.0F);
            }
            else
            {
              ((m)localObject).a(2);
              localM2.a(2);
              ((m)localObject).b(localM2, -i);
              localM2.b((m)localObject, i);
              paramD.d(i);
            }
          }
        }
      }
      else if ((b.b == null) && (g.b == null))
      {
        ((m)localObject).a(1);
        localM2.a(1);
        if (paramInt != 0) {
          localM2.a((m)localObject, 1, paramD.putByte());
        } else {
          localM2.a((m)localObject, paramD.size());
        }
      }
      else if ((b.b != null) && (g.b == null))
      {
        ((m)localObject).a(1);
        localM2.a(1);
        if (paramInt != 0) {
          localM2.a((m)localObject, 1, paramD.putByte());
        } else {
          localM2.a((m)localObject, paramD.size());
        }
      }
      else if ((b.b == null) && (g.b != null))
      {
        ((m)localObject).a(1);
        localM2.a(1);
        ((m)localObject).a(localM2, -paramD.size());
        if (paramInt != 0) {
          ((m)localObject).a(localM2, -1, paramD.putByte());
        } else {
          ((m)localObject).a(localM2, -paramD.size());
        }
      }
      else if ((b.b != null) && (g.b != null))
      {
        ((m)localObject).a(2);
        localM2.a(2);
        if (paramInt != 0)
        {
          paramD.putByte().a((Label)localObject);
          paramD.putByte().a(localM2);
          ((m)localObject).b(localM2, -1, paramD.putByte());
          localM2.b((m)localObject, 1, paramD.putByte());
        }
        else
        {
          ((m)localObject).b(localM2, -paramD.size());
          localM2.b((m)localObject, paramD.size());
        }
      }
    }
    if ((i[1] == XLayoutStyle.c) && (b(paramD, 1))) {
      i = 1;
    } else {
      i = 0;
    }
    if ((y != 4) && (y != 4)) {
      if ((i[1] != XLayoutStyle.a) && ((i == 0) || (paramD.l() != 8)))
      {
        if (i != 0)
        {
          i = paramD.getValue();
          localM1.a(1);
          localM3.a(1);
          if ((c.b == null) && (a.b == null))
          {
            if (paramInt != 0)
            {
              localM3.a(localM1, 1, paramD.b());
              return;
            }
            localM3.a(localM1, i);
            return;
          }
          if ((c.b != null) && (a.b == null))
          {
            if (paramInt != 0)
            {
              localM3.a(localM1, 1, paramD.b());
              return;
            }
            localM3.a(localM1, i);
            return;
          }
          if ((c.b == null) && (a.b != null))
          {
            if (paramInt != 0)
            {
              localM1.a(localM3, -1, paramD.b());
              return;
            }
            localM1.a(localM3, -i);
            return;
          }
          if ((c.b != null) && (a.b != null))
          {
            if (paramInt != 0)
            {
              paramD.b().a(localM1);
              paramD.putByte().a(localM3);
            }
            if (y == 0.0F)
            {
              localM1.a(3);
              localM3.a(3);
              localM1.b(localM3, 0.0F);
              localM3.b(localM1, 0.0F);
              return;
            }
            localM1.a(2);
            localM3.a(2);
            localM1.b(localM3, -i);
            localM3.b(localM1, i);
            paramD.c(i);
            if (N > 0) {
              q.a().a(1, localM1, N);
            }
          }
        }
      }
      else if ((c.b == null) && (a.b == null))
      {
        localM1.a(1);
        localM3.a(1);
        if (paramInt != 0) {
          localM3.a(localM1, 1, paramD.b());
        } else {
          localM3.a(localM1, paramD.getValue());
        }
        localObject = q;
        if (b != null)
        {
          ((h)localObject).a().a(1);
          localM1.a(1, q.a(), -N);
        }
      }
      else if ((c.b != null) && (a.b == null))
      {
        localM1.a(1);
        localM3.a(1);
        if (paramInt != 0) {
          localM3.a(localM1, 1, paramD.b());
        } else {
          localM3.a(localM1, paramD.getValue());
        }
        if (N > 0) {
          q.a().a(1, localM1, N);
        }
      }
      else if ((c.b == null) && (a.b != null))
      {
        localM1.a(1);
        localM3.a(1);
        if (paramInt != 0) {
          localM1.a(localM3, -1, paramD.b());
        } else {
          localM1.a(localM3, -paramD.getValue());
        }
        if (N > 0) {
          q.a().a(1, localM1, N);
        }
      }
      else if ((c.b != null) && (a.b != null))
      {
        localM1.a(2);
        localM3.a(2);
        if (paramInt != 0)
        {
          localM1.b(localM3, -1, paramD.b());
          localM3.b(localM1, 1, paramD.b());
          paramD.b().a(localM1);
          paramD.putByte().a(localM3);
        }
        else
        {
          localM1.b(localM3, -paramD.getValue());
          localM3.b(localM1, paramD.getValue());
        }
        if (N > 0) {
          q.a().a(1, localM1, N);
        }
      }
    }
  }
  
  static void a(f paramF, i paramI, d paramD)
  {
    int i;
    int j;
    if ((paramF.i[0] != XLayoutStyle.b) && (i[0] == XLayoutStyle.e))
    {
      i = b.g;
      j = paramF.size() - g.g;
      h localH = b;
      c = paramI.a(localH);
      localH = g;
      c = paramI.a(localH);
      paramI.a(b.c, i);
      paramI.a(g.c, j);
      E = 2;
      paramD.c(i, j);
    }
    if ((paramF.i[1] != XLayoutStyle.b) && (i[1] == XLayoutStyle.e))
    {
      i = c.g;
      j = paramF.getValue() - a.g;
      paramF = c;
      c = paramI.a(paramF);
      paramF = a;
      c = paramI.a(paramF);
      paramI.a(c.c, i);
      paramI.a(a.c, j);
      if ((N > 0) || (paramD.l() == 8))
      {
        paramF = q;
        c = paramI.a(paramF);
        paramI.a(q.c, N + i);
      }
      F = 2;
      paramD.a(i, j);
    }
  }
  
  static boolean a(f paramF, i paramI, int paramInt1, int paramInt2, l paramL)
  {
    d localD1 = b;
    Object localObject3 = g;
    Object localObject1 = a;
    d localD2 = j;
    Object localObject2 = k;
    int i3 = 0;
    float f7 = n;
    Object localObject4 = e;
    paramL = d;
    int i;
    int j;
    int m;
    int n;
    if ((paramF.i[paramInt1] == XLayoutStyle.b) || (paramInt1 == 0))
    {
      if (width == 0) {
        i = 1;
      } else {
        i = 0;
      }
      if (width == 1) {
        j = 1;
      } else {
        j = 0;
      }
      if (width == 2) {
        k = 1;
      } else {
        k = 0;
      }
      m = i;
      n = j;
      i = k;
    }
    else
    {
      if (top == 0) {
        j = 1;
      } else {
        j = 0;
      }
      if (top == 1) {
        k = 1;
      } else {
        k = 0;
      }
      if (top == 2)
      {
        i = 1;
        m = j;
        n = k;
      }
      else
      {
        i = 0;
        n = k;
        m = j;
      }
    }
    int i1 = 0;
    int i2 = 0;
    float f3 = 0.0F;
    float f4 = 0.0F;
    paramL = localD1;
    int k = i3;
    float f2;
    float f1;
    while (k == 0)
    {
      j = i2;
      f2 = f3;
      f1 = f4;
      if (paramL.l() != 8)
      {
        j = i2 + 1;
        if (paramInt1 == 0) {
          f2 = f4 + paramL.size();
        } else {
          f2 = f4 + paramL.getValue();
        }
        f1 = f2;
        if (paramL != localObject1) {
          f1 = f2 + C[paramInt2].b();
        }
        f2 = f3 + C[paramInt2].b() + C[(paramInt2 + 1)].b();
      }
      paramF = C[paramInt2];
      i2 = i1;
      if (paramL.l() != 8)
      {
        i2 = i1;
        if (i[paramInt1] == XLayoutStyle.c)
        {
          i2 = i1 + 1;
          if (paramInt1 == 0)
          {
            if (t != 0) {
              return false;
            }
            if (m != 0) {
              break label1722;
            }
            if (k != 0) {
              return false;
            }
          }
          else
          {
            if (h != 0) {
              return false;
            }
            if (w != 0) {
              break label1722;
            }
            if (B != 0) {
              return false;
            }
          }
        }
      }
      paramF = C[(paramInt2 + 1)].b;
      if (paramF != null)
      {
        paramF = a;
        localObject2 = C;
        if ((b != null) && (b.a == paramL)) {
          break label525;
        }
        paramF = null;
      }
      else
      {
        paramF = null;
      }
      label525:
      if (paramF == null)
      {
        k = 1;
        paramF = paramL;
      }
      paramL = paramF;
      i1 = i2;
      i2 = j;
      f3 = f2;
      f4 = f1;
    }
    localObject2 = C[paramInt2].a();
    paramF = C[(paramInt2 + 1)].a();
    localObject3 = n;
    if (localObject3 != null)
    {
      localObject4 = paramF.n;
      if (localObject4 == null) {
        return false;
      }
      if ((b != 1) && (b != 1)) {
        return false;
      }
      if ((i1 > 0) && (i1 != i2)) {
        return false;
      }
      f1 = 0.0F;
      f2 = 0.0F;
      if ((i != 0) || (m != 0) || (n != 0))
      {
        if (localObject1 != null) {
          f2 = C[paramInt2].b();
        }
        f1 = f2;
        if (localD2 != null) {
          f1 = f2 + C[(paramInt2 + 1)].b();
        }
      }
      float f5 = n.e;
      f2 = ne;
      if (f5 < f2) {
        f2 = f2 - f5 - f4;
      } else {
        f2 = f5 - f2 - f4;
      }
      if ((i1 > 0) && (i1 == i2))
      {
        if ((paramL.get() != null) && (geti[paramInt1] == XLayoutStyle.b)) {
          return false;
        }
        float f6 = f2 + f4 - f3;
        paramL = (l)localObject1;
        f4 = f5;
        f2 = f6;
        if (m != 0) {
          f2 = f6 - (f3 - f1);
        }
        paramF = paramL;
        f1 = f4;
        if (m != 0)
        {
          f3 = f5 + C[(paramInt2 + 1)].b();
          localObject1 = bottom[paramInt1];
          paramF = paramL;
          f1 = f3;
          if (localObject1 != null)
          {
            f1 = f3 + C[paramInt2].b();
            paramF = paramL;
          }
        }
        while (paramF != null)
        {
          paramL = i.w;
          if (paramL != null)
          {
            q -= 1L;
            r += 1L;
            g += 1L;
          }
          paramL = bottom[paramInt1];
          if ((paramL == null) && (paramF != localD2)) {
            break label1121;
          }
          f3 = f2 / i1;
          if (f7 > 0.0F) {
            f3 = right[paramInt1] * f2 / f7;
          }
          f1 += C[paramInt2].b();
          C[paramInt2].a().a(a, f1);
          C[(paramInt2 + 1)].a().a(a, f1 + f3);
          C[paramInt2].a().a(paramI);
          C[(paramInt2 + 1)].a().a(paramI);
          f1 = f1 + f3 + C[(paramInt2 + 1)].b();
          label1121:
          paramF = paramL;
        }
        return true;
      }
      if (f2 < f4) {
        return false;
      }
      if (i != 0)
      {
        for (f1 = localD1.n() * (f2 - f1) + f5; localObject1 != null; f1 = f2)
        {
          paramF = i.w;
          if (paramF != null)
          {
            q -= 1L;
            r += 1L;
            g += 1L;
          }
          paramF = bottom[paramInt1];
          if (paramF == null)
          {
            f2 = f1;
            if (localObject1 != localD2) {}
          }
          else
          {
            if (paramInt1 == 0) {
              f2 = ((d)localObject1).size();
            } else {
              f2 = ((d)localObject1).getValue();
            }
            f1 += C[paramInt2].b();
            C[paramInt2].a().a(a, f1);
            C[(paramInt2 + 1)].a().a(a, f1 + f2);
            C[paramInt2].a().a(paramI);
            C[(paramInt2 + 1)].a().a(paramI);
            f2 = f1 + f2 + C[(paramInt2 + 1)].b();
          }
          localObject1 = paramF;
        }
      }
      else
      {
        if ((m == 0) && (n == 0)) {
          break label1720;
        }
        if (m != 0)
        {
          f3 = f2 - f1;
        }
        else
        {
          f3 = f2;
          if (n != 0) {
            f3 = f2 - f1;
          }
        }
        f1 = f3 / (i2 + 1);
        if (n != 0) {
          if (i2 > 1) {
            f1 = f3 / (i2 - 1);
          } else {
            f1 = f3 / 2.0F;
          }
        }
        f3 = f5 + f1;
        f2 = f3;
        if (n != 0)
        {
          f2 = f3;
          if (i2 > 1) {
            f2 = f5 + C[paramInt2].b();
          }
        }
        if ((m != 0) && (localObject1 != null)) {
          f2 += C[paramInt2].b();
        }
        while (localObject1 != null)
        {
          paramF = i.w;
          if (paramF != null)
          {
            q -= 1L;
            r += 1L;
            g += 1L;
          }
          paramF = bottom[paramInt1];
          if ((paramF == null) && (localObject1 != localD2)) {
            break label1714;
          }
          if (paramInt1 == 0) {
            f3 = ((d)localObject1).size();
          } else {
            f3 = ((d)localObject1).getValue();
          }
          C[paramInt2].a().a(a, f2);
          C[(paramInt2 + 1)].a().a(a, f2 + f3);
          C[paramInt2].a().a(paramI);
          C[(paramInt2 + 1)].a().a(paramI);
          f2 += f3 + f1;
          label1714:
          localObject1 = paramF;
        }
      }
      label1720:
      return true;
    }
    label1722:
    return false;
  }
  
  private static boolean b(d paramD, int paramInt)
  {
    XLayoutStyle[] arrayOfXLayoutStyle = paramD.i;
    if (arrayOfXLayoutStyle[paramInt] != XLayoutStyle.c) {
      return false;
    }
    float f = y;
    int i = 1;
    if (f != 0.0F)
    {
      if (paramInt == 0) {
        paramInt = i;
      } else {
        paramInt = 0;
      }
      if (arrayOfXLayoutStyle[paramInt] == XLayoutStyle.c) {}
      return false;
    }
    if (paramInt == 0)
    {
      if (t != 0) {
        return false;
      }
      if (m != 0) {
        break label110;
      }
      if (k != 0) {
        return false;
      }
    }
    else
    {
      if (h != 0) {
        return false;
      }
      if (w != 0) {
        break label110;
      }
      if (B == 0) {
        break label112;
      }
      return false;
    }
    return true;
    label110:
    return false;
    label112:
    return true;
  }
}
