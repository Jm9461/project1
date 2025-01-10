package android.support.constraint.asm;

public class h
  implements ByteVector
{
  public final d b;
  Label c = null;
  boolean h = false;
  float i = 0.0F;
  boolean w;
  
  public h(b paramB)
  {
    b = new d(this, paramB);
  }
  
  public Label a(i paramI, boolean[] paramArrayOfBoolean)
  {
    return b.a(paramArrayOfBoolean, null);
  }
  
  public h a(float paramFloat1, float paramFloat2, float paramFloat3, Label paramLabel1, Label paramLabel2, Label paramLabel3, Label paramLabel4)
  {
    i = 0.0F;
    if ((paramFloat2 != 0.0F) && (paramFloat1 != paramFloat3))
    {
      if (paramFloat1 == 0.0F)
      {
        b.a(paramLabel1, 1.0F);
        b.a(paramLabel2, -1.0F);
        return this;
      }
      if (paramFloat3 == 0.0F)
      {
        b.a(paramLabel3, 1.0F);
        b.a(paramLabel4, -1.0F);
        return this;
      }
      paramFloat1 = paramFloat1 / paramFloat2 / (paramFloat3 / paramFloat2);
      b.a(paramLabel1, 1.0F);
      b.a(paramLabel2, -1.0F);
      b.a(paramLabel4, paramFloat1);
      b.a(paramLabel3, -paramFloat1);
      return this;
    }
    b.a(paramLabel1, 1.0F);
    b.a(paramLabel2, -1.0F);
    b.a(paramLabel4, 1.0F);
    b.a(paramLabel3, -1.0F);
    return this;
  }
  
  public h a(Label paramLabel, int paramInt)
  {
    if (paramInt < 0)
    {
      i = (paramInt * -1);
      b.a(paramLabel, 1.0F);
      return this;
    }
    i = paramInt;
    b.a(paramLabel, -1.0F);
    return this;
  }
  
  public h a(Label paramLabel1, Label paramLabel2, int paramInt)
  {
    int j = 0;
    int m = 0;
    if (paramInt != 0)
    {
      int k = paramInt;
      j = m;
      if (paramInt < 0)
      {
        k = paramInt * -1;
        j = 1;
      }
      i = k;
    }
    if (j == 0)
    {
      b.a(paramLabel1, -1.0F);
      b.a(paramLabel2, 1.0F);
      return this;
    }
    b.a(paramLabel1, 1.0F);
    b.a(paramLabel2, -1.0F);
    return this;
  }
  
  h a(Label paramLabel1, Label paramLabel2, int paramInt1, float paramFloat, Label paramLabel3, Label paramLabel4, int paramInt2)
  {
    if (paramLabel2 == paramLabel3)
    {
      b.a(paramLabel1, 1.0F);
      b.a(paramLabel4, 1.0F);
      b.a(paramLabel2, -2.0F);
      return this;
    }
    if (paramFloat == 0.5F)
    {
      b.a(paramLabel1, 1.0F);
      b.a(paramLabel2, -1.0F);
      b.a(paramLabel3, -1.0F);
      b.a(paramLabel4, 1.0F);
      if ((paramInt1 > 0) || (paramInt2 > 0))
      {
        i = (-paramInt1 + paramInt2);
        return this;
      }
    }
    else
    {
      if (paramFloat <= 0.0F)
      {
        b.a(paramLabel1, -1.0F);
        b.a(paramLabel2, 1.0F);
        i = paramInt1;
        return this;
      }
      if (paramFloat >= 1.0F)
      {
        b.a(paramLabel3, -1.0F);
        b.a(paramLabel4, 1.0F);
        i = paramInt2;
        return this;
      }
      b.a(paramLabel1, (1.0F - paramFloat) * 1.0F);
      b.a(paramLabel2, (1.0F - paramFloat) * -1.0F);
      b.a(paramLabel3, -1.0F * paramFloat);
      b.a(paramLabel4, paramFloat * 1.0F);
      if ((paramInt1 > 0) || (paramInt2 > 0)) {
        i = (-paramInt1 * (1.0F - paramFloat) + paramInt2 * paramFloat);
      }
    }
    return this;
  }
  
  h a(Label paramLabel1, Label paramLabel2, Label paramLabel3, float paramFloat)
  {
    b.a(paramLabel1, -1.0F);
    b.a(paramLabel2, 1.0F - paramFloat);
    b.a(paramLabel3, paramFloat);
    return this;
  }
  
  public h a(Label paramLabel1, Label paramLabel2, Label paramLabel3, int paramInt)
  {
    int j = 0;
    int m = 0;
    if (paramInt != 0)
    {
      int k = paramInt;
      j = m;
      if (paramInt < 0)
      {
        k = paramInt * -1;
        j = 1;
      }
      i = k;
    }
    if (j == 0)
    {
      b.a(paramLabel1, -1.0F);
      b.a(paramLabel2, 1.0F);
      b.a(paramLabel3, 1.0F);
      return this;
    }
    b.a(paramLabel1, 1.0F);
    b.a(paramLabel2, -1.0F);
    b.a(paramLabel3, -1.0F);
    return this;
  }
  
  public h a(Label paramLabel1, Label paramLabel2, Label paramLabel3, Label paramLabel4, float paramFloat)
  {
    b.a(paramLabel1, -1.0F);
    b.a(paramLabel2, 1.0F);
    b.a(paramLabel3, paramFloat);
    b.a(paramLabel4, -paramFloat);
    return this;
  }
  
  public h a(i paramI, int paramInt)
  {
    b.a(paramI.a(paramInt, "ep"), 1.0F);
    b.a(paramI.a(paramInt, "em"), -1.0F);
    return this;
  }
  
  void a()
  {
    float f = i;
    if (f < 0.0F)
    {
      i = (f * -1.0F);
      b.a();
    }
  }
  
  public void a(ByteVector paramByteVector)
  {
    if ((paramByteVector instanceof h))
    {
      paramByteVector = (h)paramByteVector;
      c = null;
      b.b();
      int j = 0;
      for (;;)
      {
        Object localObject = b;
        if (j >= d) {
          break;
        }
        localObject = ((d)localObject).a(j);
        float f = b.c(j);
        b.a((Label)localObject, f, true);
        j += 1;
      }
    }
  }
  
  void a(Label paramLabel)
  {
    Label localLabel = c;
    if (localLabel != null)
    {
      b.a(localLabel, -1.0F);
      c = null;
    }
    float f = b.a(paramLabel, true) * -1.0F;
    c = paramLabel;
    if (f == 1.0F) {
      return;
    }
    i /= f;
    b.b(f);
  }
  
  boolean a(i paramI)
  {
    boolean bool = false;
    paramI = b.a(paramI);
    if (paramI == null) {
      bool = true;
    } else {
      a(paramI);
    }
    if (b.d == 0) {
      h = true;
    }
    return bool;
  }
  
  h b(Label paramLabel, int paramInt)
  {
    b.a(paramLabel, paramInt);
    return this;
  }
  
  public h b(Label paramLabel1, Label paramLabel2, Label paramLabel3, int paramInt)
  {
    int j = 0;
    int m = 0;
    if (paramInt != 0)
    {
      int k = paramInt;
      j = m;
      if (paramInt < 0)
      {
        k = paramInt * -1;
        j = 1;
      }
      i = k;
    }
    if (j == 0)
    {
      b.a(paramLabel1, -1.0F);
      b.a(paramLabel2, 1.0F);
      b.a(paramLabel3, -1.0F);
      return this;
    }
    b.a(paramLabel1, 1.0F);
    b.a(paramLabel2, -1.0F);
    b.a(paramLabel3, 1.0F);
    return this;
  }
  
  public h b(Label paramLabel1, Label paramLabel2, Label paramLabel3, Label paramLabel4, float paramFloat)
  {
    b.a(paramLabel3, 0.5F);
    b.a(paramLabel4, 0.5F);
    b.a(paramLabel1, -0.5F);
    b.a(paramLabel2, -0.5F);
    i = (-paramFloat);
    return this;
  }
  
  public void b(Label paramLabel)
  {
    float f = 1.0F;
    int j = e;
    if (j == 1) {
      f = 1.0F;
    } else if (j == 2) {
      f = 1000.0F;
    } else if (j == 3) {
      f = 1000000.0F;
    } else if (j == 4) {
      f = 1.0E9F;
    } else if (j == 5) {
      f = 1.0E12F;
    }
    b.a(paramLabel, f);
  }
  
  boolean b()
  {
    Label localLabel = c;
    return (localLabel != null) && ((d == f.b) || (i >= 0.0F));
  }
  
  Label c(Label paramLabel)
  {
    return b.a(null, paramLabel);
  }
  
  h c(Label paramLabel, int paramInt)
  {
    c = paramLabel;
    g = paramInt;
    i = paramInt;
    h = true;
    return this;
  }
  
  public boolean c()
  {
    return (c == null) && (i == 0.0F) && (b.d == 0);
  }
  
  public void clear()
  {
    b.b();
    c = null;
    i = 0.0F;
  }
  
  public void d()
  {
    c = null;
    b.b();
    i = 0.0F;
    h = false;
  }
  
  boolean d(Label paramLabel)
  {
    return b.b(paramLabel);
  }
  
  String doInBackground()
  {
    if (c == null)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("");
      ((StringBuilder)localObject1).append("0");
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    else
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("");
      ((StringBuilder)localObject1).append(c);
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append(" = ");
    Object localObject1 = ((StringBuilder)localObject2).toString();
    localObject2 = localObject1;
    int j = 0;
    if (i != 0.0F)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append(i);
      localObject2 = ((StringBuilder)localObject2).toString();
      j = 1;
    }
    int m = b.d;
    int k = 0;
    while (k < m)
    {
      localObject1 = b.a(k);
      if (localObject1 == null)
      {
        localObject1 = localObject2;
      }
      else
      {
        float f2 = b.c(k);
        float f1 = f2;
        if (f2 == 0.0F)
        {
          localObject1 = localObject2;
        }
        else
        {
          String str = ((Label)localObject1).toString();
          if (j == 0)
          {
            localObject1 = localObject2;
            if (f2 < 0.0F)
            {
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append((String)localObject2);
              ((StringBuilder)localObject1).append("- ");
              localObject1 = ((StringBuilder)localObject1).toString();
              f1 = f2 * -1.0F;
            }
          }
          else if (f2 > 0.0F)
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append((String)localObject2);
            ((StringBuilder)localObject1).append(" + ");
            localObject1 = ((StringBuilder)localObject1).toString();
          }
          else
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append((String)localObject2);
            ((StringBuilder)localObject1).append(" - ");
            localObject1 = ((StringBuilder)localObject1).toString();
            f1 = f2 * -1.0F;
          }
          if (f1 == 1.0F)
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append((String)localObject1);
            ((StringBuilder)localObject2).append(str);
            localObject1 = ((StringBuilder)localObject2).toString();
          }
          else
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append((String)localObject1);
            ((StringBuilder)localObject2).append(f1);
            ((StringBuilder)localObject2).append(" ");
            ((StringBuilder)localObject2).append(str);
            localObject1 = ((StringBuilder)localObject2).toString();
          }
          j = 1;
        }
      }
      k += 1;
      localObject2 = localObject1;
    }
    if (j == 0)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append("0.0");
      return ((StringBuilder)localObject1).toString();
    }
    return localObject2;
  }
  
  public Label getKey()
  {
    return c;
  }
  
  public String toString()
  {
    return doInBackground();
  }
}
