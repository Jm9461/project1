package android.support.constraint.asm.asm;

import android.support.constraint.asm.i;

public class m
  extends Label
{
  m a;
  h b;
  private int c = 1;
  float e;
  private ByteVector l = null;
  m n;
  private m p;
  private int s = 1;
  private ByteVector v = null;
  float x;
  int y = 0;
  
  public m(h paramH)
  {
    b = paramH;
  }
  
  public void a()
  {
    h localH = b.getValue();
    if (localH == null) {
      return;
    }
    if (localH.getValue() == b)
    {
      y = 4;
      ay = 4;
    }
    int j = b.b();
    int i = j;
    c localC = b.d;
    if ((localC == c.d) || (localC == c.c)) {
      i = -j;
    }
    a(localH.a(), i);
  }
  
  public void a(int paramInt)
  {
    y = paramInt;
  }
  
  public void a(int paramInt1, m paramM, int paramInt2)
  {
    y = paramInt1;
    n = paramM;
    x = paramInt2;
    n.a(this);
  }
  
  public void a(m paramM, float paramFloat)
  {
    if ((b == 0) || ((a != paramM) && (e != paramFloat)))
    {
      a = paramM;
      e = paramFloat;
      if (b == 1) {
        setText();
      }
      draw();
    }
  }
  
  public void a(m paramM, int paramInt)
  {
    n = paramM;
    x = paramInt;
    n.a(this);
  }
  
  public void a(m paramM, int paramInt, ByteVector paramByteVector)
  {
    n = paramM;
    n.a(this);
    v = paramByteVector;
    c = paramInt;
    v.a(this);
  }
  
  void a(i paramI)
  {
    android.support.constraint.asm.Label localLabel = b.c();
    m localM = a;
    if (localM == null)
    {
      paramI.a(localLabel, (int)e);
      return;
    }
    paramI.a(localLabel, paramI.a(b), (int)e, 6);
  }
  
  public void b()
  {
    super.b();
    n = null;
    x = 0.0F;
    v = null;
    c = 1;
    l = null;
    s = 1;
    a = null;
    e = 0.0F;
    p = null;
    y = 0;
  }
  
  public void b(m paramM, float paramFloat)
  {
    p = paramM;
  }
  
  public void b(m paramM, int paramInt, ByteVector paramByteVector)
  {
    p = paramM;
    l = paramByteVector;
    s = paramInt;
  }
  
  public void d()
  {
    int i = b;
    int j = 1;
    if (i == 1) {
      return;
    }
    if (y == 4) {
      return;
    }
    Object localObject1 = v;
    if (localObject1 != null)
    {
      if (b != 1) {
        return;
      }
      x = (c * a);
    }
    localObject1 = l;
    float f1;
    if (localObject1 != null)
    {
      if (b != 1) {
        return;
      }
      f1 = a;
    }
    if (y == 1)
    {
      localObject1 = n;
      if ((localObject1 == null) || (b == 1))
      {
        localObject1 = n;
        if (localObject1 == null)
        {
          a = this;
          e = x;
        }
        else
        {
          a = a;
          e = (e + x);
        }
        draw();
        return;
      }
    }
    Object localObject2;
    if (y == 2)
    {
      localObject1 = n;
      if ((localObject1 != null) && (b == 1))
      {
        localObject1 = p;
        if (localObject1 != null)
        {
          localObject1 = n;
          if ((localObject1 != null) && (b == 1))
          {
            if (i.size() != null)
            {
              localObject1 = i.size();
              i += 1L;
            }
            a = n.a;
            localObject1 = p;
            a = n.a;
            localObject1 = b.d;
            i = j;
            if (localObject1 != c.d) {
              if (localObject1 == c.c) {
                i = j;
              } else {
                i = 0;
              }
            }
            if (i != 0) {
              f1 = n.e - p.n.e;
            } else {
              f1 = p.n.e - n.e;
            }
            localObject1 = b;
            localObject2 = d;
            if ((localObject2 != c.a) && (localObject2 != c.d))
            {
              f2 = f1 - a.getValue();
              f1 = b.a.H;
            }
            else
            {
              f2 = f1 - b.a.size();
              f1 = b.a.A;
            }
            j = b.b();
            int k = p.b.b();
            if (b.getValue() == p.b.getValue())
            {
              f1 = 0.5F;
              j = 0;
              k = 0;
            }
            float f2 = f2 - j - k;
            if (i != 0)
            {
              localObject1 = p;
              e = (n.e + k + f2 * f1);
              e = (n.e - j - (1.0F - f1) * f2);
            }
            else
            {
              e = (n.e + j + f2 * f1);
              localObject1 = p;
              e = (n.e - k - (1.0F - f1) * f2);
            }
            draw();
            p.draw();
            return;
          }
        }
      }
    }
    if (y == 3)
    {
      localObject1 = n;
      if ((localObject1 != null) && (b == 1))
      {
        localObject1 = p;
        if (localObject1 != null)
        {
          localObject1 = n;
          if ((localObject1 != null) && (b == 1))
          {
            if (i.size() != null)
            {
              localObject1 = i.size();
              b += 1L;
            }
            localObject1 = n;
            a = a;
            localObject2 = p;
            m localM = n;
            a = a;
            e = (e + x);
            e = (e + x);
            draw();
            p.draw();
            return;
          }
        }
      }
    }
    if (y == 5) {
      b.a.draw();
    }
  }
  
  public float e()
  {
    return e;
  }
  
  public String toString()
  {
    if (b == 1)
    {
      if (a == this)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("[");
        localStringBuilder.append(b);
        localStringBuilder.append(", RESOLVED: ");
        localStringBuilder.append(e);
        localStringBuilder.append("]  type: ");
        localStringBuilder.append(toString(y));
        return localStringBuilder.toString();
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      localStringBuilder.append(b);
      localStringBuilder.append(", RESOLVED: ");
      localStringBuilder.append(a);
      localStringBuilder.append(":");
      localStringBuilder.append(e);
      localStringBuilder.append("] type: ");
      localStringBuilder.append(toString(y));
      return localStringBuilder.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("{ ");
    localStringBuilder.append(b);
    localStringBuilder.append(" UNRESOLVED} type: ");
    localStringBuilder.append(toString(y));
    return localStringBuilder.toString();
  }
  
  String toString(int paramInt)
  {
    if (paramInt == 1) {
      return "DIRECT";
    }
    if (paramInt == 2) {
      return "CENTER";
    }
    if (paramInt == 3) {
      return "MATCH";
    }
    if (paramInt == 4) {
      return "CHAIN";
    }
    if (paramInt == 5) {
      return "BARRIER";
    }
    return "UNCONNECTED";
  }
}
