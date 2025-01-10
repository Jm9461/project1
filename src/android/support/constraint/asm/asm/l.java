package android.support.constraint.asm.asm;

import android.support.constraint.i.j.e;
import java.util.ArrayList;

public class l
{
  protected d a;
  protected d b;
  protected int c;
  protected d d;
  protected d e;
  private boolean f;
  protected d g;
  protected boolean h;
  protected int i;
  protected d j;
  protected d k;
  protected boolean l;
  private boolean m = false;
  protected float n = 0.0F;
  protected boolean s;
  private int u;
  protected ArrayList<e> w;
  
  public l(d paramD, int paramInt, boolean paramBoolean)
  {
    b = paramD;
    u = paramInt;
    m = paramBoolean;
  }
  
  private void a()
  {
    int i2 = u * 2;
    Object localObject2 = b;
    int i1 = 0;
    boolean bool;
    for (;;)
    {
      bool = true;
      if (i1 != 0) {
        break;
      }
      i += 1;
      Object localObject1 = bottom;
      int i3 = u;
      localObject1[i3] = null;
      height[i3] = null;
      if (((d)localObject2).l() != 8)
      {
        if (a == null) {
          a = ((d)localObject2);
        }
        localObject1 = j;
        if (localObject1 != null) {
          bottom[u] = localObject2;
        }
        j = ((d)localObject2);
        localObject1 = i;
        i3 = u;
        if (localObject1[i3] == XLayoutStyle.c)
        {
          localObject1 = P;
          if ((localObject1[i3] == 0) || (localObject1[i3] == 3) || (localObject1[i3] == 2))
          {
            c += 1;
            localObject1 = right;
            i3 = u;
            float f1 = localObject1[i3];
            if (f1 > 0.0F) {
              n += localObject1[i3];
            }
            if (a((d)localObject2, u))
            {
              if (f1 < 0.0F) {
                s = true;
              } else {
                h = true;
              }
              if (w == null) {
                w = new ArrayList();
              }
              w.add(localObject2);
            }
            if (e == null) {
              e = ((d)localObject2);
            }
            localObject1 = d;
            if (localObject1 != null) {
              height[u] = localObject2;
            }
            d = ((d)localObject2);
          }
        }
      }
      localObject1 = C[(i2 + 1)].b;
      if (localObject1 != null)
      {
        localObject1 = a;
        h[] arrayOfH = C;
        if ((b == null) || (b.a != localObject2)) {
          localObject1 = null;
        }
      }
      else
      {
        localObject1 = null;
      }
      if (localObject1 != null) {
        localObject2 = localObject1;
      } else {
        i1 = 1;
      }
    }
    g = ((d)localObject2);
    if ((u == 0) && (m)) {
      k = g;
    } else {
      k = b;
    }
    if ((!h) || (!s)) {
      bool = false;
    }
    l = bool;
  }
  
  private static boolean a(d paramD, int paramInt)
  {
    if ((paramD.l() != 8) && (i[paramInt] == XLayoutStyle.c))
    {
      paramD = P;
      if ((paramD[paramInt] == 0) || (paramD[paramInt] == 3)) {
        return true;
      }
    }
    return false;
  }
  
  public void b()
  {
    if (!f) {
      a();
    }
    f = true;
  }
}
