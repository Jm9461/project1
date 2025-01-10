package android.support.constraint.asm.asm;

import android.support.constraint.asm.b;
import android.support.constraint.asm.i;
import java.util.ArrayList;

public class d
{
  public static float EDGE = 0.5F;
  float A;
  int B = 0;
  protected h[] C = { b, g, c, a, q, x };
  float D = 1.0F;
  public int E = -1;
  public int F = -1;
  ByteVector G;
  float H;
  ByteVector I;
  protected int J;
  private int K;
  protected int L;
  protected int M;
  int N;
  int[] P = new int[2];
  private int Q;
  private int R;
  private int[] V = { Integer.MAX_VALUE, Integer.MAX_VALUE };
  h a = new h(this, c.c);
  h b = new h(this, c.a);
  protected d[] bottom;
  h c = new h(this, c.i);
  protected int d;
  protected int e;
  int f;
  private int first;
  h g = new h(this, c.d);
  int h = 0;
  protected d[] height;
  protected XLayoutStyle[] i;
  private String id;
  protected ArrayList<android.support.constraint.i.j.d> j = new ArrayList();
  int k = 0;
  h l = new h(this, c.e);
  private Object left;
  int m = 0;
  protected int n;
  h o = new h(this, c.b);
  int p;
  private String parent;
  h q = new h(this, c.g);
  d r;
  float[] right;
  private int s;
  private float size = 0.0F;
  int t = 0;
  int top;
  protected int type;
  float u = 1.0F;
  int v = -1;
  int w = 0;
  int width;
  h x = new h(this, c.h);
  protected float y;
  float z = 1.0F;
  
  public d()
  {
    XLayoutStyle localXLayoutStyle = XLayoutStyle.a;
    i = new XLayoutStyle[] { localXLayoutStyle, localXLayoutStyle };
    r = null;
    p = 0;
    f = 0;
    y = 0.0F;
    J = -1;
    n = 0;
    type = 0;
    first = 0;
    K = 0;
    L = 0;
    M = 0;
    N = 0;
    float f1 = EDGE;
    A = f1;
    H = f1;
    s = 0;
    id = null;
    parent = null;
    width = 0;
    top = 0;
    right = new float[] { -1.0F, -1.0F };
    height = new d[] { null, null };
    bottom = new d[] { null, null };
    add();
  }
  
  private void a(i paramI, boolean paramBoolean1, android.support.constraint.asm.Label paramLabel1, android.support.constraint.asm.Label paramLabel2, XLayoutStyle paramXLayoutStyle, boolean paramBoolean2, h paramH1, h paramH2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, boolean paramBoolean3, boolean paramBoolean4, int paramInt5, int paramInt6, int paramInt7, float paramFloat2, boolean paramBoolean5)
  {
    android.support.constraint.asm.Label localLabel2 = paramI.a(paramH1);
    android.support.constraint.asm.Label localLabel1 = paramI.a(paramH2);
    android.support.constraint.asm.Label localLabel3 = paramI.a(paramH1.getValue());
    android.support.constraint.asm.Label localLabel4 = paramI.a(paramH2.getValue());
    if ((r) && (ab == 1) && (ab == 1))
    {
      if (i.size() != null)
      {
        paramLabel1 = i.size();
        r += 1L;
      }
      paramH1.a().a(paramI);
      paramH2.a().a(paramI);
      if ((!paramBoolean4) && (paramBoolean1)) {
        paramI.b(paramLabel2, localLabel1, 0, 6);
      }
    }
    else
    {
      Object localObject;
      if (i.size() != null)
      {
        localObject = i.size();
        q += 1L;
      }
      boolean bool1 = paramH1.equals();
      boolean bool2 = paramH2.equals();
      boolean bool3 = x.equals();
      int i2 = 0;
      if (bool1) {
        i2 = 0 + 1;
      }
      int i1 = i2;
      if (bool2) {
        i1 = i2 + 1;
      }
      i2 = i1;
      if (bool3) {
        i2 = i1 + 1;
      }
      i1 = paramInt5;
      if (paramBoolean3) {
        i1 = 3;
      }
      paramInt5 = Item.a[paramXLayoutStyle.ordinal()];
      if (paramInt5 != 1)
      {
        if (paramInt5 != 2)
        {
          if (paramInt5 != 3)
          {
            if (paramInt5 != 4) {
              paramInt5 = 0;
            } else if (i1 == 4) {
              paramInt5 = 0;
            } else {
              paramInt5 = 1;
            }
          }
          else {
            paramInt5 = 0;
          }
        }
        else {
          paramInt5 = 0;
        }
      }
      else {
        paramInt5 = 0;
      }
      if (s == 8)
      {
        paramInt2 = 0;
        paramInt5 = 0;
      }
      if (paramBoolean5) {
        if ((!bool1) && (!bool2) && (!bool3)) {
          paramI.a(localLabel2, paramInt1);
        } else if ((bool1) && (!bool2)) {
          paramI.a(localLabel2, localLabel3, paramH1.b(), 6);
        }
      }
      if (paramInt5 == 0)
      {
        if (paramBoolean2)
        {
          paramI.a(localLabel1, localLabel2, 0, 3);
          if (paramInt3 > 0) {
            paramI.b(localLabel1, localLabel2, paramInt3, 6);
          }
          if (paramInt4 < Integer.MAX_VALUE) {
            paramI.d(localLabel1, localLabel2, paramInt4, 6);
          }
        }
        else
        {
          paramI.a(localLabel1, localLabel2, paramInt2, 6);
        }
      }
      else
      {
        paramInt1 = paramInt6;
        if (paramInt6 == -2) {
          paramInt1 = paramInt2;
        }
        paramInt4 = paramInt7;
        if (paramInt7 == -2) {
          paramInt4 = paramInt2;
        }
        paramInt6 = paramInt2;
        if (paramInt1 > 0)
        {
          if (paramBoolean1) {
            paramI.b(localLabel1, localLabel2, paramInt1, 6);
          } else {
            paramI.b(localLabel1, localLabel2, paramInt1, 6);
          }
          paramInt6 = Math.max(paramInt2, paramInt1);
        }
        int i3;
        if (paramInt4 > 0)
        {
          if (paramBoolean1) {
            paramI.d(localLabel1, localLabel2, paramInt4, 1);
          } else {
            paramI.d(localLabel1, localLabel2, paramInt4, 6);
          }
          i3 = Math.min(paramInt6, paramInt4);
        }
        else
        {
          i3 = paramInt6;
        }
        if (i1 == 1)
        {
          if (paramBoolean1)
          {
            paramI.a(localLabel1, localLabel2, i3, 6);
            paramInt2 = paramInt5;
          }
          else if (paramBoolean4)
          {
            paramI.a(localLabel1, localLabel2, i3, 4);
            paramInt2 = paramInt5;
          }
          else
          {
            paramI.a(localLabel1, localLabel2, i3, 1);
            paramInt2 = paramInt5;
          }
        }
        else if (i1 == 2)
        {
          if ((paramH1.e() != c.i) && (paramH1.e() != c.c))
          {
            paramXLayoutStyle = paramI.a(r.a(c.a));
            localObject = paramI.a(r.a(c.d));
          }
          else
          {
            paramXLayoutStyle = paramI.a(r.a(c.i));
            localObject = paramI.a(r.a(c.c));
          }
          android.support.constraint.asm.h localH = paramI.a();
          localH.a(localLabel1, localLabel2, (android.support.constraint.asm.Label)localObject, paramXLayoutStyle, paramFloat2);
          paramI.a(localH);
          paramInt2 = 0;
        }
        else
        {
          paramInt2 = paramInt5;
        }
        paramInt5 = paramInt2;
        paramInt6 = paramInt1;
        paramInt7 = paramInt4;
        if (paramInt2 != 0)
        {
          paramInt5 = paramInt2;
          paramInt6 = paramInt1;
          paramInt7 = paramInt4;
          if (i2 != 2)
          {
            paramInt5 = paramInt2;
            paramInt6 = paramInt1;
            paramInt7 = paramInt4;
            if (!paramBoolean3)
            {
              paramInt5 = 0;
              paramInt6 = Math.max(paramInt1, i3);
              paramInt2 = paramInt6;
              if (paramInt4 > 0) {
                paramInt2 = Math.min(paramInt4, paramInt6);
              }
              paramI.a(localLabel1, localLabel2, paramInt2, 6);
              paramInt7 = paramInt4;
              paramInt6 = paramInt1;
            }
          }
        }
      }
      if ((paramBoolean5) && (!paramBoolean4))
      {
        if ((!bool1) && (!bool2) && (!bool3))
        {
          if (paramBoolean1)
          {
            paramI.b(paramLabel2, localLabel1, 0, 5);
            paramLabel1 = localLabel1;
          }
          else
          {
            paramLabel1 = localLabel1;
          }
        }
        else if ((bool1) && (!bool2))
        {
          if (paramBoolean1)
          {
            paramI.b(paramLabel2, localLabel1, 0, 5);
            paramLabel1 = localLabel1;
          }
          else
          {
            paramLabel1 = localLabel1;
          }
        }
        else if ((!bool1) && (bool2))
        {
          paramI.a(localLabel1, localLabel4, -paramH2.b(), 6);
          if (paramBoolean1)
          {
            paramI.b(localLabel2, paramLabel1, 0, 5);
            paramLabel1 = localLabel1;
          }
          else
          {
            paramLabel1 = localLabel1;
          }
        }
        else if ((bool1) && (bool2))
        {
          paramInt1 = 0;
          paramInt4 = 0;
          if (paramInt5 != 0)
          {
            if ((paramBoolean1) && (paramInt3 == 0)) {
              paramI.b(localLabel1, localLabel2, 0, 6);
            }
            if (i1 == 0)
            {
              paramInt2 = 6;
              if ((paramInt7 > 0) || (paramInt6 > 0))
              {
                paramInt2 = 4;
                paramInt1 = 1;
              }
              paramI.a(localLabel2, localLabel3, paramH1.b(), paramInt2);
              paramI.a(localLabel1, localLabel4, -paramH2.b(), paramInt2);
              if (paramInt7 <= 0)
              {
                paramInt2 = paramInt4;
                if (paramInt6 <= 0) {}
              }
              else
              {
                paramInt2 = 1;
              }
              paramInt3 = paramInt1;
              paramInt1 = 5;
            }
            else if (i1 == 1)
            {
              paramInt1 = 6;
              paramInt3 = 1;
              paramInt2 = 1;
            }
            else if (i1 == 3)
            {
              if ((!paramBoolean3) && (v != -1) && (paramInt7 <= 0)) {
                paramInt1 = 6;
              } else {
                paramInt1 = 4;
              }
              paramI.a(localLabel2, localLabel3, paramH1.b(), paramInt1);
              paramI.a(localLabel1, localLabel4, -paramH2.b(), paramInt1);
              paramInt3 = 1;
              paramInt2 = 1;
              paramInt1 = 5;
            }
            else
            {
              paramInt3 = 0;
              paramInt2 = 0;
              paramInt1 = 5;
            }
          }
          else
          {
            if (paramBoolean1)
            {
              paramI.b(localLabel2, localLabel3, paramH1.b(), 5);
              paramI.d(localLabel1, localLabel4, -paramH2.b(), 5);
            }
            paramInt3 = 0;
            paramInt2 = 1;
            paramInt1 = 5;
          }
          if (paramInt2 != 0) {
            paramI.add(localLabel2, localLabel3, paramH1.b(), paramFloat1, localLabel4, localLabel1, paramH2.b(), paramInt1);
          }
          if (paramInt3 != 0)
          {
            paramI.b(localLabel2, localLabel3, paramH1.b(), 6);
            paramI.d(localLabel1, localLabel4, -paramH2.b(), 6);
          }
          if (paramBoolean1)
          {
            paramI.b(localLabel2, paramLabel1, 0, 6);
            paramLabel1 = localLabel1;
          }
          else
          {
            paramLabel1 = localLabel1;
          }
        }
        else
        {
          paramLabel1 = localLabel1;
        }
        if (paramBoolean1) {
          paramI.b(paramLabel2, paramLabel1, 0, 6);
        }
      }
      else if ((i2 < 2) && (paramBoolean1))
      {
        paramI.b(localLabel2, paramLabel1, 0, 6);
        paramI.b(paramLabel2, localLabel1, 0, 6);
      }
    }
  }
  
  private void add()
  {
    j.add(b);
    j.add(c);
    j.add(g);
    j.add(a);
    j.add(o);
    j.add(l);
    j.add(x);
    j.add(q);
  }
  
  public h a(c paramC)
  {
    switch (Item.b[paramC.ordinal()])
    {
    default: 
      throw new AssertionError(paramC.name());
    case 9: 
      return null;
    case 8: 
      return l;
    case 7: 
      return o;
    case 6: 
      return x;
    case 5: 
      return q;
    case 4: 
      return a;
    case 3: 
      return g;
    case 2: 
      return c;
    }
    return b;
  }
  
  public void a()
  {
    b.d();
    c.d();
    g.d();
    a.d();
    q.d();
    o.d();
    l.d();
    x.d();
    r = null;
    size = 0.0F;
    p = 0;
    f = 0;
    y = 0.0F;
    J = -1;
    n = 0;
    type = 0;
    first = 0;
    K = 0;
    L = 0;
    M = 0;
    N = 0;
    d = 0;
    e = 0;
    R = 0;
    Q = 0;
    float f1 = EDGE;
    A = f1;
    H = f1;
    Object localObject = i;
    XLayoutStyle localXLayoutStyle = XLayoutStyle.a;
    localObject[0] = localXLayoutStyle;
    localObject[1] = localXLayoutStyle;
    left = null;
    s = 0;
    parent = null;
    width = 0;
    top = 0;
    localObject = right;
    localObject[0] = -1.0F;
    localObject[1] = -1.0F;
    E = -1;
    F = -1;
    localObject = V;
    localObject[0] = Integer.MAX_VALUE;
    localObject[1] = Integer.MAX_VALUE;
    t = 0;
    h = 0;
    z = 1.0F;
    D = 1.0F;
    k = Integer.MAX_VALUE;
    B = Integer.MAX_VALUE;
    m = 0;
    w = 0;
    v = -1;
    u = 1.0F;
    localObject = I;
    if (localObject != null) {
      ((ByteVector)localObject).b();
    }
    localObject = G;
    if (localObject != null) {
      ((ByteVector)localObject).b();
    }
  }
  
  public void a(float paramFloat)
  {
    A = paramFloat;
  }
  
  public void a(int paramInt)
  {
    g.a(paramInt, this);
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    type = paramInt1;
    f = (paramInt2 - paramInt1);
    paramInt1 = f;
    paramInt2 = e;
    if (paramInt1 < paramInt2) {
      f = paramInt2;
    }
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, float paramFloat)
  {
    t = paramInt1;
    m = paramInt2;
    k = paramInt3;
    z = paramFloat;
    if ((paramFloat < 1.0F) && (t == 0)) {
      t = 2;
    }
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i1 = paramInt3 - paramInt1;
    paramInt3 = paramInt4 - paramInt2;
    n = paramInt1;
    type = paramInt2;
    if (s == 8)
    {
      p = 0;
      f = 0;
      return;
    }
    paramInt1 = i1;
    if (i[0] == XLayoutStyle.a)
    {
      paramInt1 = i1;
      if (i1 < p) {
        paramInt1 = p;
      }
    }
    paramInt2 = paramInt3;
    if (i[1] == XLayoutStyle.a)
    {
      paramInt2 = paramInt3;
      if (paramInt3 < f) {
        paramInt2 = f;
      }
    }
    p = paramInt1;
    f = paramInt2;
    paramInt1 = f;
    paramInt2 = e;
    if (paramInt1 < paramInt2) {
      f = paramInt2;
    }
    paramInt1 = p;
    paramInt2 = d;
    if (paramInt1 < paramInt2) {
      p = paramInt2;
    }
  }
  
  public void a(XLayoutStyle paramXLayoutStyle)
  {
    i[1] = paramXLayoutStyle;
    if (paramXLayoutStyle == XLayoutStyle.b) {
      c(Q);
    }
  }
  
  public void a(c paramC1, d paramD, c paramC2, int paramInt1, int paramInt2)
  {
    a(paramC1).a(paramD.a(paramC2), paramInt1, paramInt2, SizeLayoutType.s, 0, true);
  }
  
  public void a(d paramD, float paramFloat, int paramInt)
  {
    c localC = c.h;
    a(localC, paramD, localC, paramInt, 0);
    size = paramFloat;
  }
  
  public void a(b paramB)
  {
    b.a(paramB);
    c.a(paramB);
    g.a(paramB);
    a.a(paramB);
    q.a(paramB);
    x.a(paramB);
    o.a(paramB);
    l.a(paramB);
  }
  
  public void a(i paramI)
  {
    android.support.constraint.asm.Label localLabel3 = paramI.a(b);
    android.support.constraint.asm.Label localLabel4 = paramI.a(g);
    android.support.constraint.asm.Label localLabel1 = paramI.a(c);
    android.support.constraint.asm.Label localLabel2 = paramI.a(a);
    Object localObject3 = paramI.a(q);
    boolean bool4 = false;
    boolean bool5 = false;
    Object localObject1 = r;
    boolean bool1;
    boolean bool2;
    boolean bool3;
    if (localObject1 != null)
    {
      if ((localObject1 != null) && (i[0] == XLayoutStyle.b)) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      localObject1 = r;
      if ((localObject1 != null) && (i[1] == XLayoutStyle.b)) {
        bool2 = true;
      } else {
        bool2 = false;
      }
      localObject1 = b;
      localObject2 = b;
      if ((localObject2 != null) && (b != localObject1))
      {
        localObject1 = g;
        localObject2 = b;
        if ((localObject2 != null) && (b == localObject1)) {
          ((f)r).d(this, 0);
        }
      }
      localObject1 = b;
      localObject2 = b;
      if ((localObject2 == null) || (b != localObject1))
      {
        localObject1 = g;
        localObject2 = b;
        bool3 = bool4;
        if (localObject2 != null)
        {
          bool3 = bool4;
          if (b != localObject1) {}
        }
      }
      else
      {
        bool3 = true;
      }
      localObject1 = c;
      localObject2 = b;
      if ((localObject2 != null) && (b != localObject1))
      {
        localObject1 = a;
        localObject2 = b;
        if ((localObject2 != null) && (b == localObject1)) {
          ((f)r).d(this, 1);
        }
      }
      localObject1 = c;
      localObject2 = b;
      if ((localObject2 == null) || (b != localObject1))
      {
        localObject1 = a;
        localObject2 = b;
        bool4 = bool5;
        if (localObject2 != null)
        {
          bool4 = bool5;
          if (b != localObject1) {}
        }
      }
      else
      {
        bool4 = true;
      }
      if ((bool1) && (s != 8) && (b.b == null) && (g.b == null)) {
        paramI.b(paramI.a(r.g), localLabel4, 0, 1);
      }
      if ((bool2) && (s != 8) && (c.b == null) && (a.b == null) && (q == null)) {
        paramI.b(paramI.a(r.a), localLabel2, 0, 1);
      }
      bool5 = bool1;
    }
    else
    {
      bool3 = false;
      bool4 = false;
      bool5 = false;
      bool2 = false;
    }
    int i1 = p;
    int i2 = i1;
    if (i1 < d) {
      i2 = d;
    }
    i1 = f;
    int i4 = i1;
    if (i1 < e) {
      i4 = e;
    }
    if (i[0] != XLayoutStyle.c) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    if (i[1] != XLayoutStyle.c) {
      bool6 = true;
    } else {
      bool6 = false;
    }
    i1 = 0;
    v = J;
    float f1 = y;
    u = f1;
    int i3 = t;
    int i8 = h;
    int i5 = i8;
    int i6 = i1;
    int i7 = i3;
    if (f1 > 0.0F)
    {
      i5 = i8;
      i6 = i1;
      i7 = i3;
      if (s != 8)
      {
        int i9 = 1;
        i1 = i3;
        if (i[0] == XLayoutStyle.c)
        {
          i1 = i3;
          if (i3 == 0) {
            i1 = 3;
          }
        }
        i3 = i8;
        if (i[1] == XLayoutStyle.c)
        {
          i3 = i8;
          if (i8 == 0) {
            i3 = 3;
          }
        }
        localObject1 = i;
        localObject2 = localObject1[0];
        XLayoutStyle localXLayoutStyle = XLayoutStyle.c;
        if ((localObject2 == localXLayoutStyle) && (localObject1[1] == localXLayoutStyle) && (i1 == 3) && (i3 == 3))
        {
          a(bool5, bool2, bool1, bool6);
          i5 = i3;
          i6 = i9;
          i7 = i1;
        }
        else
        {
          localObject1 = i;
          localObject2 = localObject1[0];
          localXLayoutStyle = XLayoutStyle.c;
          if ((localObject2 == localXLayoutStyle) && (i1 == 3))
          {
            v = 0;
            i6 = (int)(u * f);
            if (localObject1[1] != localXLayoutStyle)
            {
              i2 = 4;
              i1 = 0;
              i5 = i3;
              i3 = i6;
              break label1032;
            }
            i2 = i1;
            i1 = 1;
            i5 = i3;
            i3 = i6;
            break label1032;
          }
          i5 = i3;
          i6 = i9;
          i7 = i1;
          if (i[1] == XLayoutStyle.c)
          {
            i5 = i3;
            i6 = i9;
            i7 = i1;
            if (i3 == 3)
            {
              v = 1;
              if (J == -1) {
                u = (1.0F / u);
              }
              i4 = (int)(u * p);
              if (i[0] != XLayoutStyle.c)
              {
                i5 = 4;
                i3 = i2;
                i2 = i1;
                i1 = 0;
                break label1032;
              }
              i6 = i2;
              i2 = i1;
              i1 = 1;
              i5 = i3;
              i3 = i6;
              break label1032;
            }
          }
        }
      }
    }
    i3 = i2;
    i2 = i7;
    i1 = i6;
    label1032:
    localObject1 = P;
    localObject1[0] = i2;
    localObject1[1] = i5;
    if (i1 != 0)
    {
      i6 = v;
      if (i6 != 0) {
        if (i6 != -1) {
          break label1080;
        }
      }
      bool6 = true;
      break label1083;
    }
    label1080:
    boolean bool6 = false;
    label1083:
    boolean bool7;
    if ((i[0] == XLayoutStyle.b) && ((this instanceof f))) {
      bool7 = true;
    } else {
      bool7 = false;
    }
    if (x.equals()) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    if (E != 2)
    {
      localObject1 = r;
      if (localObject1 != null) {
        localObject1 = paramI.a(g);
      } else {
        localObject1 = null;
      }
      localObject2 = r;
      if (localObject2 != null) {
        localObject2 = paramI.a(b);
      } else {
        localObject2 = null;
      }
      a(paramI, bool5, (android.support.constraint.asm.Label)localObject2, (android.support.constraint.asm.Label)localObject1, i[0], bool7, b, g, n, i3, d, V[0], A, bool6, bool3, i2, m, k, z, bool1);
    }
    if (F == 2) {
      return;
    }
    if ((i[1] == XLayoutStyle.b) && ((this instanceof f))) {
      bool3 = true;
    } else {
      bool3 = false;
    }
    if (i1 != 0)
    {
      i2 = v;
      if ((i2 == 1) || (i2 == -1))
      {
        bool5 = true;
        break label1332;
      }
    }
    bool5 = false;
    label1332:
    if (N > 0) {
      if (q.a().b == 1)
      {
        q.a().a(paramI);
      }
      else
      {
        paramI.a((android.support.constraint.asm.Label)localObject3, localLabel1, getIcon(), 6);
        localObject1 = q.b;
        if (localObject1 != null)
        {
          paramI.a((android.support.constraint.asm.Label)localObject3, paramI.a(localObject1), 0, 6);
          bool1 = false;
        }
      }
    }
    localObject3 = paramI;
    localObject1 = r;
    if (localObject1 != null) {
      localObject1 = ((i)localObject3).a(a);
    } else {
      localObject1 = null;
    }
    Object localObject2 = r;
    if (localObject2 != null) {
      localObject2 = ((i)localObject3).a(c);
    } else {
      localObject2 = null;
    }
    a(paramI, bool2, (android.support.constraint.asm.Label)localObject2, (android.support.constraint.asm.Label)localObject1, i[1], bool3, c, a, type, i4, e, V[1], H, bool5, bool4, i5, w, B, D, bool1);
    if (i1 != 0) {
      if (v == 1) {
        paramI.add(localLabel2, localLabel1, localLabel4, localLabel3, u, 6);
      } else {
        paramI.add(localLabel4, localLabel3, localLabel2, localLabel1, u, 6);
      }
    }
    if (x.equals()) {
      ((i)localObject3).a(this, x.getValue().m(), (float)Math.toRadians(size + 90.0F), x.b());
    }
  }
  
  public void a(Object paramObject)
  {
    left = paramObject;
  }
  
  public void a(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      int i1 = -1;
      float f2 = 0.0F;
      float f4 = 0.0F;
      float f3 = 0.0F;
      int i3 = paramString.length();
      int i2 = paramString.indexOf(',');
      String str;
      if ((i2 > 0) && (i2 < i3 - 1))
      {
        str = paramString.substring(0, i2);
        if (str.equalsIgnoreCase("W")) {
          i1 = 0;
        } else if (str.equalsIgnoreCase("H")) {
          i1 = 1;
        }
        i2 += 1;
      }
      else
      {
        i2 = 0;
      }
      int i4 = paramString.indexOf(':');
      float f1;
      if ((i4 >= 0) && (i4 < i3 - 1))
      {
        str = paramString.substring(i2, i4);
        paramString = paramString.substring(i4 + 1);
        f1 = f2;
        if (str.length() > 0)
        {
          f1 = f2;
          if (paramString.length() > 0) {
            try
            {
              f4 = Float.parseFloat(str);
              float f5 = Float.parseFloat(paramString);
              f1 = f3;
              if (f4 > 0.0F)
              {
                f1 = f3;
                if (f5 > 0.0F) {
                  if (i1 == 1)
                  {
                    f1 = f5 / f4;
                    f1 = Math.abs(f1);
                  }
                  else
                  {
                    f1 = f4 / f5;
                    f1 = Math.abs(f1);
                  }
                }
              }
            }
            catch (NumberFormatException paramString)
            {
              f1 = f2;
            }
          }
        }
      }
      else
      {
        paramString = paramString.substring(i2);
        f1 = f4;
        if (paramString.length() > 0) {
          try
          {
            f1 = Float.parseFloat(paramString);
          }
          catch (NumberFormatException paramString)
          {
            f1 = f4;
          }
        }
      }
      if (f1 > 0.0F)
      {
        y = f1;
        J = i1;
      }
    }
    else
    {
      y = 0.0F;
    }
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    if (v == -1) {
      if ((paramBoolean3) && (!paramBoolean4))
      {
        v = 0;
      }
      else if ((!paramBoolean3) && (paramBoolean4))
      {
        v = 1;
        if (J == -1) {
          u = (1.0F / u);
        }
      }
    }
    if ((v == 0) && ((!c.equals()) || (!a.equals()))) {
      v = 1;
    } else if ((v == 1) && ((!b.equals()) || (!g.equals()))) {
      v = 0;
    }
    if ((v == -1) && ((!c.equals()) || (!a.equals()) || (!b.equals()) || (!g.equals()))) {
      if ((c.equals()) && (a.equals()))
      {
        v = 0;
      }
      else if ((b.equals()) && (g.equals()))
      {
        u = (1.0F / u);
        v = 1;
      }
    }
    if (v == -1) {
      if ((paramBoolean1) && (!paramBoolean2))
      {
        v = 0;
      }
      else if ((!paramBoolean1) && (paramBoolean2))
      {
        u = (1.0F / u);
        v = 1;
      }
    }
    if (v == -1) {
      if ((m > 0) && (w == 0))
      {
        v = 0;
      }
      else if ((m == 0) && (w > 0))
      {
        u = (1.0F / u);
        v = 1;
      }
    }
    if ((v == -1) && (paramBoolean1) && (paramBoolean2))
    {
      u = (1.0F / u);
      v = 1;
    }
  }
  
  public void add(float paramFloat)
  {
    right[1] = paramFloat;
  }
  
  public void add(int paramInt)
  {
    top = paramInt;
  }
  
  public void append(int paramInt)
  {
    n = paramInt;
  }
  
  public void append(int paramInt1, int paramInt2)
  {
    n = paramInt1;
    type = paramInt2;
  }
  
  public ByteVector b()
  {
    if (G == null) {
      G = new ByteVector();
    }
    return G;
  }
  
  public void b(float paramFloat)
  {
    right[0] = paramFloat;
  }
  
  public void b(int paramInt1, int paramInt2, int paramInt3, float paramFloat)
  {
    h = paramInt1;
    w = paramInt2;
    B = paramInt3;
    D = paramFloat;
    if ((paramFloat < 1.0F) && (h == 0)) {
      h = 2;
    }
  }
  
  public void b(XLayoutStyle paramXLayoutStyle)
  {
    i[0] = paramXLayoutStyle;
    if (paramXLayoutStyle == XLayoutStyle.b) {
      d(R);
    }
  }
  
  public void c(float paramFloat)
  {
    H = paramFloat;
  }
  
  public void c(int paramInt)
  {
    f = paramInt;
    paramInt = f;
    int i1 = e;
    if (paramInt < i1) {
      f = i1;
    }
  }
  
  public void c(int paramInt1, int paramInt2)
  {
    n = paramInt1;
    p = (paramInt2 - paramInt1);
    paramInt1 = p;
    paramInt2 = d;
    if (paramInt1 < paramInt2) {
      p = paramInt2;
    }
  }
  
  public void c(i paramI)
  {
    int i6 = paramI.b(b);
    int i4 = i6;
    int i7 = paramI.b(c);
    int i3 = i7;
    int i8 = paramI.b(g);
    int i2 = i8;
    int i5 = paramI.b(a);
    int i1 = i5;
    if ((i8 - i6 < 0) || (i5 - i7 < 0) || (i6 == Integer.MIN_VALUE) || (i6 == Integer.MAX_VALUE) || (i7 == Integer.MIN_VALUE) || (i7 == Integer.MAX_VALUE) || (i8 == Integer.MIN_VALUE) || (i8 == Integer.MAX_VALUE) || (i5 == Integer.MIN_VALUE) || (i5 == Integer.MAX_VALUE))
    {
      i4 = 0;
      i3 = 0;
      i2 = 0;
      i1 = 0;
    }
    a(i4, i3, i2, i1);
  }
  
  public void c(String paramString)
  {
    id = paramString;
  }
  
  public boolean c()
  {
    return s != 8;
  }
  
  public void clear(int paramInt)
  {
    V[0] = paramInt;
  }
  
  public void d(int paramInt)
  {
    p = paramInt;
    paramInt = p;
    int i1 = d;
    if (paramInt < i1) {
      p = i1;
    }
  }
  
  public void d(int paramInt1, int paramInt2)
  {
    L = paramInt1;
    M = paramInt2;
  }
  
  public void d(i paramI)
  {
    paramI.a(b);
    paramI.a(c);
    paramI.a(g);
    paramI.a(a);
    if (N > 0) {
      paramI.a(q);
    }
  }
  
  public boolean d()
  {
    return (h == 0) && (y == 0.0F) && (w == 0) && (B == 0) && (i[1] == XLayoutStyle.c);
  }
  
  public void draw() {}
  
  public void draw(int paramInt)
  {
    R = paramInt;
  }
  
  public int e()
  {
    return R;
  }
  
  public void e(int paramInt)
  {
    if (paramInt < 0)
    {
      d = 0;
      return;
    }
    d = paramInt;
  }
  
  public void e(d paramD)
  {
    r = paramD;
  }
  
  public void e(boolean paramBoolean) {}
  
  public boolean equals()
  {
    return (t == 0) && (y == 0.0F) && (m == 0) && (k == 0) && (i[0] == XLayoutStyle.c);
  }
  
  public XLayoutStyle f()
  {
    return i[1];
  }
  
  public void flagActionItems()
  {
    d localD = get();
    if ((localD != null) && ((localD instanceof f)) && (((f)get()).add())) {
      return;
    }
    int i1 = 0;
    int i2 = j.size();
    while (i1 < i2)
    {
      ((h)j.get(i1)).d();
      i1 += 1;
    }
  }
  
  public XLayoutStyle g()
  {
    return i[0];
  }
  
  public void g(int paramInt)
  {
    s = paramInt;
  }
  
  public d get()
  {
    return r;
  }
  
  public int getIcon()
  {
    return N;
  }
  
  public String getId()
  {
    return id;
  }
  
  protected int getItem()
  {
    return n + L;
  }
  
  public int getItemCount()
  {
    return getType() + f;
  }
  
  public int getItemId()
  {
    return Q;
  }
  
  public int getType()
  {
    return type;
  }
  
  public int getValue()
  {
    if (s == 8) {
      return 0;
    }
    return f;
  }
  
  public int getWidth()
  {
    return K + M;
  }
  
  public boolean isEmpty()
  {
    return N > 0;
  }
  
  public ArrayList j()
  {
    return j;
  }
  
  public int l()
  {
    return s;
  }
  
  public void l(int paramInt)
  {
    Q = paramInt;
  }
  
  public void l(boolean paramBoolean) {}
  
  public Object leftValue()
  {
    return left;
  }
  
  public int length()
  {
    return n;
  }
  
  public int max()
  {
    return first + L;
  }
  
  public void measure(int paramInt)
  {
    N = paramInt;
  }
  
  public float n()
  {
    return A;
  }
  
  public void next()
  {
    int i1 = n;
    int i2 = type;
    first = i1;
    K = i2;
  }
  
  public int onClick()
  {
    return length() + p;
  }
  
  public void put(int paramInt)
  {
    V[1] = paramInt;
  }
  
  public ByteVector putByte()
  {
    if (I == null) {
      I = new ByteVector();
    }
    return I;
  }
  
  public void putShort(int paramInt)
  {
    width = paramInt;
  }
  
  public void setCallback(int paramInt)
  {
    if (paramInt < 0)
    {
      e = 0;
      return;
    }
    e = paramInt;
  }
  
  public void setChecked()
  {
    int i1 = 0;
    while (i1 < 6)
    {
      C[i1].a().b();
      i1 += 1;
    }
  }
  
  public void setText(int paramInt)
  {
    type = paramInt;
  }
  
  public int size()
  {
    if (s == 8) {
      return 0;
    }
    return p;
  }
  
  protected int split()
  {
    return type + M;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject = parent;
    String str = "";
    if (localObject != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("type: ");
      ((StringBuilder)localObject).append(parent);
      ((StringBuilder)localObject).append(" ");
      localObject = ((StringBuilder)localObject).toString();
    }
    else
    {
      localObject = "";
    }
    localStringBuilder.append((String)localObject);
    localObject = str;
    if (id != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("id: ");
      ((StringBuilder)localObject).append(id);
      ((StringBuilder)localObject).append(" ");
      localObject = ((StringBuilder)localObject).toString();
    }
    localStringBuilder.append((String)localObject);
    localStringBuilder.append("(");
    localStringBuilder.append(n);
    localStringBuilder.append(", ");
    localStringBuilder.append(type);
    localStringBuilder.append(") - (");
    localStringBuilder.append(p);
    localStringBuilder.append(" x ");
    localStringBuilder.append(f);
    localStringBuilder.append(") wrap: (");
    localStringBuilder.append(R);
    localStringBuilder.append(" x ");
    localStringBuilder.append(Q);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void updateMenuView()
  {
    int i1 = 0;
    while (i1 < 6)
    {
      C[i1].a().a();
      i1 += 1;
    }
  }
}
