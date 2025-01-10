package android.support.constraint.asm.asm;

import android.support.constraint.asm.Label;
import android.support.constraint.asm.i;
import java.util.ArrayList;

public class MethodWriter
  extends d
{
  private int b = 0;
  private h c = c;
  private boolean e = false;
  protected int f = -1;
  protected int g = -1;
  protected float i = -1.0F;
  
  public MethodWriter()
  {
    new FieldVisitor();
    this.j.clear();
    this.j.add(c);
    int k = C.length;
    int j = 0;
    while (j < k)
    {
      C[j] = c;
      j += 1;
    }
  }
  
  public h a(c paramC)
  {
    switch (Type.c[paramC.ordinal()])
    {
    default: 
      break;
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
      return null;
    case 3: 
    case 4: 
      if (b == 0) {
        return c;
      }
      break;
    case 1: 
    case 2: 
      if (b == 1) {
        return c;
      }
      break;
    }
    throw new AssertionError(paramC.name());
  }
  
  public void a(int paramInt)
  {
    d localD = get();
    if (localD == null) {
      return;
    }
    if (put() == 1)
    {
      c.a().a(1, c.a(), 0);
      a.a().a(1, c.a(), 0);
      if (f != -1)
      {
        b.a().a(1, b.a(), f);
        g.a().a(1, b.a(), f);
        return;
      }
      if (g != -1)
      {
        b.a().a(1, g.a(), -g);
        g.a().a(1, g.a(), -g);
        return;
      }
      if ((i != -1.0F) && (localD.g() == XLayoutStyle.a))
      {
        paramInt = (int)(p * i);
        b.a().a(1, b.a(), paramInt);
        g.a().a(1, b.a(), paramInt);
      }
    }
    else
    {
      b.a().a(1, b.a(), 0);
      g.a().a(1, b.a(), 0);
      if (f != -1)
      {
        c.a().a(1, c.a(), f);
        a.a().a(1, c.a(), f);
        return;
      }
      if (g != -1)
      {
        c.a().a(1, a.a(), -g);
        a.a().a(1, a.a(), -g);
        return;
      }
      if ((i != -1.0F) && (localD.f() == XLayoutStyle.a))
      {
        paramInt = (int)(f * i);
        c.a().a(1, c.a(), paramInt);
        a.a().a(1, c.a(), paramInt);
      }
    }
  }
  
  public void a(i paramI)
  {
    Object localObject2 = (f)get();
    if (localObject2 == null) {
      return;
    }
    h localH = ((d)localObject2).a(c.a);
    Object localObject1 = ((d)localObject2).a(c.d);
    d localD = r;
    int k = 1;
    int j;
    if ((localD != null) && (i[0] == XLayoutStyle.b)) {
      j = 1;
    } else {
      j = 0;
    }
    if (b == 0)
    {
      localH = ((d)localObject2).a(c.i);
      localObject1 = ((d)localObject2).a(c.c);
      localObject2 = r;
      if ((localObject2 != null) && (i[1] == XLayoutStyle.b)) {
        j = k;
      } else {
        j = 0;
      }
    }
    if (f != -1)
    {
      localObject2 = paramI.a(c);
      paramI.a((Label)localObject2, paramI.a(localH), f, 6);
      if (j != 0) {
        paramI.b(paramI.a(localObject1), (Label)localObject2, 0, 5);
      }
      return;
    }
    if (g != -1)
    {
      localObject2 = paramI.a(c);
      localObject1 = paramI.a(localObject1);
      paramI.a((Label)localObject2, (Label)localObject1, -g, 6);
      if (j != 0)
      {
        paramI.b((Label)localObject2, paramI.a(localH), 0, 5);
        paramI.b((Label)localObject1, (Label)localObject2, 0, 5);
      }
    }
    else if (i != -1.0F)
    {
      paramI.a(i.a(paramI, paramI.a(c), paramI.a(localH), paramI.a(localObject1), i, e));
    }
  }
  
  public void b(int paramInt)
  {
    if (b == paramInt) {
      return;
    }
    b = paramInt;
    this.j.clear();
    if (b == 1) {
      c = b;
    } else {
      c = c;
    }
    this.j.add(c);
    int j = C.length;
    paramInt = 0;
    while (paramInt < j)
    {
      C[paramInt] = c;
      paramInt += 1;
    }
  }
  
  public void c(i paramI)
  {
    if (get() == null) {
      return;
    }
    int j = paramI.b(c);
    if (b == 1)
    {
      append(j);
      setText(0);
      c(get().getValue());
      d(0);
      return;
    }
    append(0);
    setText(j);
    d(get().size());
    c(0);
  }
  
  public boolean c()
  {
    return true;
  }
  
  public void e(float paramFloat)
  {
    if (paramFloat > -1.0F)
    {
      i = paramFloat;
      f = -1;
      g = -1;
    }
  }
  
  public ArrayList j()
  {
    return j;
  }
  
  public void next(int paramInt)
  {
    if (paramInt > -1)
    {
      i = -1.0F;
      f = paramInt;
      g = -1;
    }
  }
  
  public int put()
  {
    return b;
  }
  
  public void read(int paramInt)
  {
    if (paramInt > -1)
    {
      i = -1.0F;
      f = -1;
      g = paramInt;
    }
  }
}
