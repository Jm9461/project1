package android.support.constraint.asm.asm;

import android.support.constraint.asm.Label;
import android.support.constraint.asm.i;
import java.util.ArrayList;

class ClassReader
{
  static void a(f paramF, i paramI, int paramInt)
  {
    int i;
    int j;
    l[] arrayOfL;
    if (paramInt == 0)
    {
      i = 0;
      j = r;
      arrayOfL = b;
    }
    else
    {
      i = 2;
      j = n;
      arrayOfL = m;
    }
    int k = 0;
    while (k < j)
    {
      l localL = arrayOfL[k];
      localL.b();
      if (paramF.f(4))
      {
        if (!g.a(paramF, paramI, paramInt, i, localL)) {
          a(paramF, paramI, paramInt, i, localL);
        }
      }
      else {
        a(paramF, paramI, paramInt, i, localL);
      }
      k += 1;
    }
  }
  
  static void a(f paramF, i paramI, int paramInt1, int paramInt2, l paramL)
  {
    d localD1 = b;
    d localD2 = g;
    Object localObject1 = a;
    d localD3 = j;
    Object localObject5 = k;
    float f1 = n;
    Object localObject2 = e;
    localObject2 = d;
    int i1;
    if (paramF.i[paramInt1] == XLayoutStyle.b) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    int i;
    int j;
    int k;
    int i3;
    int m;
    int n;
    int i2;
    if (paramInt1 == 0)
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
      localObject3 = localD1;
      i3 = 0;
      m = i;
      n = j;
      i2 = k;
      i = i3;
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
      if (top == 2) {
        m = 1;
      } else {
        m = 0;
      }
      localObject3 = localD1;
      i = 0;
      i2 = m;
      n = k;
      m = j;
    }
    Object localObject4;
    while (i == 0)
    {
      localObject2 = C[paramInt2];
      j = 4;
      if ((i1 != 0) || (i2 != 0)) {
        j = 1;
      }
      k = ((h)localObject2).b();
      localObject4 = b;
      i3 = k;
      if (localObject4 != null)
      {
        i3 = k;
        if (localObject3 != localD1) {
          i3 = k + ((h)localObject4).b();
        }
      }
      if ((i2 != 0) && (localObject3 != localD1) && (localObject3 != localObject1))
      {
        k = 6;
      }
      else
      {
        k = j;
        if (m != 0)
        {
          k = j;
          if (i1 != 0) {
            k = 4;
          }
        }
      }
      localObject4 = b;
      if (localObject4 != null)
      {
        if (localObject3 == localObject1) {
          paramI.b(c, c, i3, 5);
        } else {
          paramI.b(c, c, i3, 6);
        }
        paramI.a(c, b.c, i3, k);
      }
      if (i1 != 0)
      {
        if ((((d)localObject3).l() != 8) && (i[paramInt1] == XLayoutStyle.c))
        {
          localObject2 = C;
          paramI.b(1c, c, 0, 5);
        }
        paramI.b(C[paramInt2].c, C[paramInt2].c, 0, 6);
      }
      localObject2 = C[(paramInt2 + 1)].b;
      if (localObject2 != null)
      {
        localObject4 = a;
        localObject2 = localObject4;
        localObject4 = C;
        if ((b != null) && (b.a == localObject3)) {
          break label604;
        }
        localObject2 = null;
      }
      else
      {
        localObject2 = null;
      }
      label604:
      if (localObject2 == null)
      {
        i = 1;
        localObject2 = localObject3;
      }
      localObject3 = localObject2;
    }
    if (localD3 != null)
    {
      localObject2 = C;
      if (1b != null)
      {
        localObject3 = C[(paramInt2 + 1)];
        paramI.d(c, 1b.c, -((h)localObject3).b(), 5);
      }
    }
    if (i1 != 0)
    {
      paramF = C[(paramInt2 + 1)].c;
      localObject2 = C;
      paramI.b(paramF, 1c, localObject2[(paramInt2 + 1)].b(), 6);
    }
    Object localObject3 = w;
    Object localObject7;
    Object localObject6;
    Object localObject8;
    if (localObject3 != null)
    {
      j = ((ArrayList)localObject3).size();
      if (j > 1)
      {
        float f2 = f1;
        if (s)
        {
          f2 = f1;
          if (!l) {
            f2 = c;
          }
        }
        paramF = null;
        i = 0;
        for (float f3 = 0.0F; i < j; f3 = f1)
        {
          localObject2 = (d)((ArrayList)localObject3).get(i);
          float f4 = right[paramInt1];
          f1 = f4;
          if (f4 < 0.0F)
          {
            if (l)
            {
              localObject2 = C;
              paramI.a(1c, c, 0, 4);
              f1 = f3;
              break label1037;
            }
            f1 = 1.0F;
          }
          if (f1 == 0.0F)
          {
            localObject2 = C;
            paramI.a(1c, c, 0, 6);
            f1 = f3;
          }
          else
          {
            if (paramF != null)
            {
              localObject4 = C;
              paramF = c;
              localObject4 = 1c;
              localObject7 = C;
              localObject6 = c;
              localObject7 = 1c;
              localObject8 = paramI.a();
              ((android.support.constraint.asm.h)localObject8).a(f3, f2, f1, paramF, (Label)localObject4, (Label)localObject6, (Label)localObject7);
              paramI.a((android.support.constraint.asm.h)localObject8);
            }
            paramF = (f)localObject2;
          }
          label1037:
          i += 1;
        }
      }
      else {}
    }
    if (localObject1 != null)
    {
      if ((localObject1 != localD3) && (i2 == 0)) {
        break label1267;
      }
      paramF = C;
      localObject3 = paramF[paramInt2];
      localObject2 = C[(paramInt2 + 1)];
      if (b != null) {
        paramF = b.c;
      } else {
        paramF = null;
      }
      paramL = C;
      if (1b != null) {
        paramL = 1b.c;
      } else {
        paramL = null;
      }
      if (localObject1 == localD3)
      {
        localObject2 = C;
        localObject3 = localObject2[paramInt2];
        localObject2 = localObject2[(paramInt2 + 1)];
      }
      if ((paramF != null) && (paramL != null))
      {
        if (paramInt1 == 0) {
          f1 = A;
        } else {
          f1 = H;
        }
        paramInt1 = ((h)localObject3).b();
        i = ((h)localObject2).b();
        paramI.add(c, paramF, paramInt1, f1, paramL, c, i, 5);
      }
      break label2295;
    }
    label1267:
    if ((m != 0) && (localObject1 != null))
    {
      i = c;
      if ((i > 0) && (i == i)) {
        k = 1;
      } else {
        k = 0;
      }
      paramF = (f)localObject1;
      localObject3 = localObject1;
      while (paramF != null)
      {
        localObject6 = bottom[paramInt1];
        if ((localObject6 == null) && (paramF != localD3)) {
          break label1722;
        }
        localObject4 = C[paramInt2];
        localObject8 = c;
        paramL = b;
        if (paramL != null) {
          localObject2 = c;
        } else {
          localObject2 = null;
        }
        if (localObject3 != paramF)
        {
          paramL = C[(paramInt2 + 1)].c;
        }
        else
        {
          paramL = (l)localObject2;
          if (paramF == localObject1)
          {
            paramL = (l)localObject2;
            if (localObject3 == paramF)
            {
              paramL = C;
              if (b != null) {
                paramL = b.c;
              } else {
                paramL = null;
              }
            }
          }
        }
        localObject2 = null;
        i1 = ((h)localObject4).b();
        j = C[(paramInt2 + 1)].b();
        i = j;
        if (localObject6 != null)
        {
          localObject2 = C[paramInt2];
          localObject5 = c;
          localObject4 = C[(paramInt2 + 1)].c;
        }
        else
        {
          localObject7 = C[(paramInt2 + 1)].b;
          if (localObject7 != null) {
            localObject2 = c;
          }
          localObject4 = C[(paramInt2 + 1)].c;
          localObject5 = localObject2;
          localObject2 = localObject7;
        }
        if (localObject2 != null) {
          i = j + ((h)localObject2).b();
        }
        j = i1;
        if (localObject3 != null) {
          j = i1 + C[(paramInt2 + 1)].b();
        }
        if ((localObject8 != null) && (paramL != null) && (localObject5 != null) && (localObject4 != null))
        {
          if (paramF == localObject1) {
            j = C[paramInt2].b();
          }
          if (paramF == localD3) {
            i = C[(paramInt2 + 1)].b();
          }
          if (k != 0) {
            i1 = 6;
          } else {
            i1 = 4;
          }
          paramI.add((Label)localObject8, paramL, j, 0.5F, (Label)localObject5, (Label)localObject4, i, i1);
        }
        label1722:
        localObject3 = paramF;
        paramF = (f)localObject6;
      }
    }
    else if ((n != 0) && (localObject1 != null))
    {
      paramF = (f)localObject1;
      i = c;
      if ((i > 0) && (i == i)) {
        i = 1;
      } else {
        i = 0;
      }
      localObject3 = localObject1;
      while (paramF != null)
      {
        localObject2 = bottom[paramInt1];
        paramL = (l)localObject2;
        if (paramF != localObject1)
        {
          paramL = (l)localObject2;
          if (paramF != localD3)
          {
            paramL = (l)localObject2;
            if (localObject2 != null)
            {
              paramL = (l)localObject2;
              if (localObject2 == localD3) {
                paramL = null;
              }
              localObject2 = C[paramInt2];
              localObject7 = c;
              localObject4 = b;
              if (localObject4 != null) {
                localObject4 = c;
              }
              localObject8 = C[(paramInt2 + 1)].c;
              i1 = ((h)localObject2).b();
              k = C[(paramInt2 + 1)].b();
              if (paramL != null)
              {
                localObject4 = C[paramInt2];
                localObject5 = c;
                localObject2 = b;
                if (localObject2 != null) {
                  localObject2 = c;
                } else {
                  localObject2 = null;
                }
              }
              else
              {
                localObject4 = C[(paramInt2 + 1)].b;
                if (localObject4 != null) {
                  localObject2 = c;
                } else {
                  localObject2 = null;
                }
                localObject6 = C[(paramInt2 + 1)].c;
                localObject5 = localObject2;
                localObject2 = localObject6;
              }
              j = k;
              if (localObject4 != null) {
                j = k + ((h)localObject4).b();
              }
              k = i1;
              if (localObject3 != null) {
                k = i1 + C[(paramInt2 + 1)].b();
              }
              if (i != 0) {
                i1 = 6;
              } else {
                i1 = 4;
              }
              if ((localObject7 != null) && (localObject8 != null) && (localObject5 != null) && (localObject2 != null)) {
                paramI.add((Label)localObject7, (Label)localObject8, k, 0.5F, (Label)localObject5, (Label)localObject2, j, i1);
              }
            }
          }
        }
        localObject3 = paramF;
        paramF = paramL;
      }
      paramL = C[paramInt2];
      localObject3 = C[paramInt2].b;
      localObject2 = C[(paramInt2 + 1)];
      paramF = C[(paramInt2 + 1)].b;
      if (localObject3 != null) {
        if (localObject1 != localD3) {
          paramI.a(c, c, paramL.b(), 5);
        } else if (paramF != null) {
          paramI.add(c, c, paramL.b(), 0.5F, c, c, ((h)localObject2).b(), 5);
        } else {}
      }
      if ((paramF != null) && (localObject1 != localD3)) {
        paramI.a(c, c, -((h)localObject2).b(), 5);
      }
    }
    label2295:
    if (((m != 0) || (n != 0)) && (localObject1 != null))
    {
      localObject2 = C[paramInt2];
      localObject3 = C[(paramInt2 + 1)];
      paramF = b;
      if (paramF != null) {
        paramL = c;
      } else {
        paramL = null;
      }
      paramF = b;
      if (paramF != null) {
        paramF = c;
      } else {
        paramF = null;
      }
      if (localD2 != localD3)
      {
        paramF = C[(paramInt2 + 1)].b;
        if (paramF != null) {
          paramF = c;
        } else {
          paramF = null;
        }
      }
      if (localObject1 == localD3)
      {
        localObject1 = C;
        localObject2 = localObject1[paramInt2];
        localObject3 = localObject1[(paramInt2 + 1)];
      }
      if ((paramL != null) && (paramF != null))
      {
        paramInt1 = ((h)localObject2).b();
        localObject1 = localD3;
        if (localD3 == null) {
          localObject1 = localD2;
        }
        paramInt2 = C[(paramInt2 + 1)].b();
        paramI.add(c, paramL, paramInt1, 0.5F, paramF, c, paramInt2, 5);
      }
    }
  }
}
