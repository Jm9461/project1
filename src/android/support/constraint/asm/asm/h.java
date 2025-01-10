package android.support.constraint.asm.asm;

import android.support.constraint.asm.Label;
import android.support.constraint.asm.b;
import android.support.constraint.asm.f;

public class h
{
  final d a;
  h b;
  Label c;
  final c d;
  private m e = new m(this);
  public int g = 0;
  private int n;
  int p = -1;
  private SizeLayoutType s = SizeLayoutType.P;
  
  public h(d paramD, c paramC)
  {
    AllowedSolution localAllowedSolution = AllowedSolution.c;
    n = 0;
    a = paramD;
    d = paramC;
  }
  
  public m a()
  {
    return e;
  }
  
  public void a(b paramB)
  {
    paramB = c;
    if (paramB == null)
    {
      c = new Label(f.b, null);
      return;
    }
    paramB.a();
  }
  
  public boolean a(h paramH, int paramInt1, int paramInt2, SizeLayoutType paramSizeLayoutType, int paramInt3, boolean paramBoolean)
  {
    if (paramH == null)
    {
      b = null;
      g = 0;
      p = -1;
      s = SizeLayoutType.P;
      n = 2;
      return true;
    }
    if ((!paramBoolean) && (!b(paramH))) {
      return false;
    }
    b = paramH;
    if (paramInt1 > 0) {
      g = paramInt1;
    } else {
      g = 0;
    }
    p = paramInt2;
    s = paramSizeLayoutType;
    n = paramInt3;
    return true;
  }
  
  public boolean a(h paramH, int paramInt1, SizeLayoutType paramSizeLayoutType, int paramInt2)
  {
    return a(paramH, paramInt1, -1, paramSizeLayoutType, paramInt2, false);
  }
  
  public int b()
  {
    if (a.l() == 8) {
      return 0;
    }
    if (p > -1)
    {
      h localH = b;
      if ((localH != null) && (a.l() == 8)) {
        return p;
      }
    }
    return g;
  }
  
  public boolean b(h paramH)
  {
    boolean bool4 = false;
    boolean bool3 = false;
    if (paramH == null) {
      return false;
    }
    c localC1 = paramH.e();
    c localC2 = d;
    boolean bool2;
    if (localC1 == localC2)
    {
      if (localC2 == c.g)
      {
        if (paramH.m().isEmpty())
        {
          if (m().isEmpty()) {
            break label318;
          }
          return false;
        }
      }
      else {
        return true;
      }
    }
    else
    {
      boolean bool1;
      switch (Edge.c[localC2.ordinal()])
      {
      default: 
        throw new AssertionError(d.name());
      case 6: 
      case 7: 
      case 8: 
      case 9: 
        return false;
      case 4: 
      case 5: 
        if ((localC1 != c.i) && (localC1 != c.c)) {
          bool1 = false;
        } else {
          bool1 = true;
        }
        bool2 = bool1;
        if (!(paramH.m() instanceof MethodWriter)) {
          break;
        }
        if (!bool1)
        {
          bool1 = bool3;
          if (localC1 != c.e) {}
        }
        else
        {
          bool1 = true;
        }
        return bool1;
      case 2: 
      case 3: 
        if ((localC1 != c.a) && (localC1 != c.d)) {
          bool1 = false;
        } else {
          bool1 = true;
        }
        bool2 = bool1;
        if (!(paramH.m() instanceof MethodWriter)) {
          break;
        }
        if (!bool1)
        {
          bool1 = bool4;
          if (localC1 != c.b) {}
        }
        else
        {
          bool1 = true;
        }
        return bool1;
      case 1: 
        if ((localC1 == c.g) || (localC1 == c.b) || (localC1 == c.e)) {
          break label322;
        }
        return true;
      }
    }
    return false;
    label318:
    return true;
    return bool2;
    label322:
    return false;
  }
  
  public Label c()
  {
    return c;
  }
  
  public void d()
  {
    b = null;
    g = 0;
    p = -1;
    s = SizeLayoutType.s;
    n = 0;
    AllowedSolution localAllowedSolution = AllowedSolution.c;
    e.b();
  }
  
  public c e()
  {
    return d;
  }
  
  public boolean equals()
  {
    return b != null;
  }
  
  public h getValue()
  {
    return b;
  }
  
  public int l()
  {
    return n;
  }
  
  public d m()
  {
    return a;
  }
  
  public SizeLayoutType setChecked()
  {
    return s;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(a.getId());
    localStringBuilder.append(":");
    localStringBuilder.append(d.toString());
    return localStringBuilder.toString();
  }
}
