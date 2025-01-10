package android.support.constraint.asm;

import java.util.Arrays;

public class d
{
  private final h a;
  private float[] b;
  private int c;
  int d = 0;
  private final b e;
  private int[] f;
  private int[] i;
  private Label k = null;
  private int n = 8;
  private int x;
  private boolean z;
  
  d(h paramH, b paramB)
  {
    int j = n;
    i = new int[j];
    f = new int[j];
    b = new float[j];
    c = -1;
    x = -1;
    z = false;
    a = paramH;
    e = paramB;
  }
  
  private boolean a(Label paramLabel, i paramI)
  {
    return z <= 1;
  }
  
  public final float a(Label paramLabel)
  {
    int m = c;
    int j = 0;
    while ((m != -1) && (j < d))
    {
      if (i[m] == i) {
        return b[m];
      }
      m = f[m];
      j += 1;
    }
    return 0.0F;
  }
  
  public final float a(Label paramLabel, boolean paramBoolean)
  {
    if (k == paramLabel) {
      k = null;
    }
    if (c == -1) {
      return 0.0F;
    }
    int j = c;
    int i1 = -1;
    int m = 0;
    while ((j != -1) && (m < d))
    {
      if (i[j] == i)
      {
        if (j == c)
        {
          c = f[j];
        }
        else
        {
          int[] arrayOfInt = f;
          arrayOfInt[i1] = arrayOfInt[j];
        }
        if (paramBoolean) {
          paramLabel.a(a);
        }
        z -= 1;
        d -= 1;
        i[j] = -1;
        if (z) {
          x = j;
        }
        return b[j];
      }
      i1 = j;
      j = f[j];
      m += 1;
    }
    return 0.0F;
  }
  
  final Label a(int paramInt)
  {
    int m = c;
    int j = 0;
    while ((m != -1) && (j < d))
    {
      if (j == paramInt) {
        return e.b[i[m]];
      }
      m = f[m];
      j += 1;
    }
    return null;
  }
  
  Label a(i paramI)
  {
    Object localObject4 = null;
    Object localObject3 = null;
    float f5 = 0.0F;
    float f4 = 0.0F;
    boolean bool4 = false;
    boolean bool3 = false;
    int m = c;
    int j = 0;
    while ((m != -1) && (j < d))
    {
      Object localObject1 = b;
      float f2 = localObject1[m];
      Label localLabel = e.b[i[m]];
      float f1;
      if (f2 < 0.0F)
      {
        if (f2 > -0.001F)
        {
          localObject1[m] = 0.0F;
          f1 = 0.0F;
          localLabel.a(a);
        }
        else
        {
          f1 = f2;
        }
      }
      else
      {
        f1 = f2;
        if (f2 < 0.001F)
        {
          localObject1[m] = 0.0F;
          f1 = 0.0F;
          localLabel.a(a);
        }
      }
      localObject1 = localObject4;
      Object localObject2 = localObject3;
      f2 = f5;
      float f3 = f4;
      boolean bool1 = bool4;
      boolean bool2 = bool3;
      if (f1 != 0.0F) {
        if (d == f.b)
        {
          if (localObject3 == null)
          {
            localObject2 = localLabel;
            bool1 = a(localLabel, paramI);
            localObject1 = localObject4;
            f2 = f1;
            f3 = f4;
            bool2 = bool3;
          }
          else if (f5 > f1)
          {
            localObject2 = localLabel;
            bool1 = a(localLabel, paramI);
            localObject1 = localObject4;
            f2 = f1;
            f3 = f4;
            bool2 = bool3;
          }
          else
          {
            localObject1 = localObject4;
            localObject2 = localObject3;
            f2 = f5;
            f3 = f4;
            bool1 = bool4;
            bool2 = bool3;
            if (!bool4)
            {
              localObject1 = localObject4;
              localObject2 = localObject3;
              f2 = f5;
              f3 = f4;
              bool1 = bool4;
              bool2 = bool3;
              if (a(localLabel, paramI))
              {
                bool1 = true;
                localObject1 = localObject4;
                localObject2 = localLabel;
                f2 = f1;
                f3 = f4;
                bool2 = bool3;
              }
            }
          }
        }
        else
        {
          localObject1 = localObject4;
          localObject2 = localObject3;
          f2 = f5;
          f3 = f4;
          bool1 = bool4;
          bool2 = bool3;
          if (localObject3 == null)
          {
            localObject1 = localObject4;
            localObject2 = localObject3;
            f2 = f5;
            f3 = f4;
            bool1 = bool4;
            bool2 = bool3;
            if (f1 < 0.0F) {
              if (localObject4 == null)
              {
                localObject1 = localLabel;
                bool2 = a(localLabel, paramI);
                localObject2 = localObject3;
                f2 = f5;
                f3 = f1;
                bool1 = bool4;
              }
              else if (f4 > f1)
              {
                localObject1 = localLabel;
                bool2 = a(localLabel, paramI);
                localObject2 = localObject3;
                f2 = f5;
                f3 = f1;
                bool1 = bool4;
              }
              else
              {
                localObject1 = localObject4;
                localObject2 = localObject3;
                f2 = f5;
                f3 = f4;
                bool1 = bool4;
                bool2 = bool3;
                if (!bool3)
                {
                  localObject1 = localObject4;
                  localObject2 = localObject3;
                  f2 = f5;
                  f3 = f4;
                  bool1 = bool4;
                  bool2 = bool3;
                  if (a(localLabel, paramI))
                  {
                    bool2 = true;
                    bool1 = bool4;
                    f3 = f1;
                    f2 = f5;
                    localObject2 = localObject3;
                    localObject1 = localLabel;
                  }
                }
              }
            }
          }
        }
      }
      m = f[m];
      j += 1;
      localObject4 = localObject1;
      localObject3 = localObject2;
      f5 = f2;
      f4 = f3;
      bool4 = bool1;
      bool3 = bool2;
    }
    if (localObject3 != null) {
      return localObject3;
    }
    return localObject4;
  }
  
  Label a(boolean[] paramArrayOfBoolean, Label paramLabel)
  {
    int m = c;
    int j = 0;
    Object localObject1 = null;
    float f2;
    for (float f1 = 0.0F; (m != -1) && (j < d); f1 = f2)
    {
      Object localObject2 = localObject1;
      f2 = f1;
      if (b[m] < 0.0F)
      {
        Label localLabel = e.b[i[m]];
        if (paramArrayOfBoolean != null)
        {
          localObject2 = localObject1;
          f2 = f1;
          if (paramArrayOfBoolean[i] != 0) {}
        }
        else
        {
          localObject2 = localObject1;
          f2 = f1;
          if (localLabel != paramLabel)
          {
            f localF = d;
            if (localF != f.a)
            {
              localObject2 = localObject1;
              f2 = f1;
              if (localF != f.c) {}
            }
            else
            {
              float f3 = b[m];
              localObject2 = localObject1;
              f2 = f1;
              if (f3 < f1)
              {
                f2 = f3;
                localObject2 = localLabel;
              }
            }
          }
        }
      }
      m = f[m];
      j += 1;
      localObject1 = localObject2;
    }
    return localObject1;
  }
  
  void a()
  {
    int m = c;
    int j = 0;
    while ((m != -1) && (j < d))
    {
      float[] arrayOfFloat = b;
      arrayOfFloat[m] *= -1.0F;
      m = f[m];
      j += 1;
    }
  }
  
  public final void a(Label paramLabel, float paramFloat)
  {
    if (paramFloat == 0.0F)
    {
      a(paramLabel, true);
      return;
    }
    Object localObject;
    int j;
    if (c == -1)
    {
      c = 0;
      localObject = b;
      j = c;
      localObject[j] = paramFloat;
      i[j] = i;
      f[j] = -1;
      z += 1;
      paramLabel.b(a);
      d += 1;
      if (!z)
      {
        x += 1;
        j = x;
        paramLabel = i;
        if (j >= paramLabel.length)
        {
          z = true;
          x = (paramLabel.length - 1);
        }
      }
    }
    else
    {
      j = c;
      int i2 = -1;
      int m = 0;
      int i1;
      while ((j != -1) && (m < d))
      {
        localObject = i;
        int i3 = localObject[j];
        i1 = i;
        if (i3 == i1)
        {
          b[j] = paramFloat;
          return;
        }
        if (localObject[j] < i1) {
          i2 = j;
        }
        j = f[j];
        m += 1;
      }
      m = x;
      j = m + 1;
      if (z)
      {
        localObject = i;
        if (localObject[m] == -1) {
          j = x;
        } else {
          j = localObject.length;
        }
      }
      localObject = i;
      m = j;
      if (j >= localObject.length)
      {
        m = j;
        if (d < localObject.length)
        {
          i1 = 0;
          for (;;)
          {
            localObject = i;
            m = j;
            if (i1 >= localObject.length) {
              break;
            }
            if (localObject[i1] == -1)
            {
              m = i1;
              break;
            }
            i1 += 1;
          }
        }
      }
      localObject = i;
      j = m;
      if (m >= localObject.length)
      {
        j = localObject.length;
        n *= 2;
        z = false;
        x = (j - 1);
        b = Arrays.copyOf(b, n);
        i = Arrays.copyOf(i, n);
        f = Arrays.copyOf(f, n);
      }
      i[j] = i;
      b[j] = paramFloat;
      if (i2 != -1)
      {
        localObject = f;
        localObject[j] = localObject[i2];
        localObject[i2] = j;
      }
      else
      {
        f[j] = c;
        c = j;
      }
      z += 1;
      paramLabel.b(a);
      d += 1;
      if (!z) {
        x += 1;
      }
      if (d >= i.length) {
        z = true;
      }
      j = x;
      paramLabel = i;
      if (j >= paramLabel.length)
      {
        z = true;
        x = (paramLabel.length - 1);
      }
    }
  }
  
  final void a(Label paramLabel, float paramFloat, boolean paramBoolean)
  {
    if (paramFloat == 0.0F) {
      return;
    }
    Object localObject;
    int j;
    if (c == -1)
    {
      c = 0;
      localObject = b;
      j = c;
      localObject[j] = paramFloat;
      i[j] = i;
      f[j] = -1;
      z += 1;
      paramLabel.b(a);
      d += 1;
      if (!z)
      {
        x += 1;
        j = x;
        paramLabel = i;
        if (j >= paramLabel.length)
        {
          z = true;
          x = (paramLabel.length - 1);
        }
      }
    }
    else
    {
      j = c;
      int i2 = -1;
      int m = 0;
      int i1;
      while ((j != -1) && (m < d))
      {
        localObject = i;
        int i3 = localObject[j];
        i1 = i;
        if (i3 == i1)
        {
          localObject = b;
          localObject[j] += paramFloat;
          if (localObject[j] != 0.0F) {
            return;
          }
          if (j == c)
          {
            c = f[j];
          }
          else
          {
            localObject = f;
            localObject[i2] = localObject[j];
          }
          if (paramBoolean) {
            paramLabel.a(a);
          }
          if (z) {
            x = j;
          }
          z -= 1;
          d -= 1;
          return;
        }
        if (localObject[j] < i1) {
          i2 = j;
        }
        j = f[j];
        m += 1;
      }
      m = x;
      j = m + 1;
      if (z)
      {
        localObject = i;
        if (localObject[m] == -1) {
          j = x;
        } else {
          j = localObject.length;
        }
      }
      localObject = i;
      m = j;
      if (j >= localObject.length)
      {
        m = j;
        if (d < localObject.length)
        {
          i1 = 0;
          for (;;)
          {
            localObject = i;
            m = j;
            if (i1 >= localObject.length) {
              break;
            }
            if (localObject[i1] == -1)
            {
              m = i1;
              break;
            }
            i1 += 1;
          }
        }
      }
      localObject = i;
      j = m;
      if (m >= localObject.length)
      {
        j = localObject.length;
        n *= 2;
        z = false;
        x = (j - 1);
        b = Arrays.copyOf(b, n);
        i = Arrays.copyOf(i, n);
        f = Arrays.copyOf(f, n);
      }
      i[j] = i;
      b[j] = paramFloat;
      if (i2 != -1)
      {
        localObject = f;
        localObject[j] = localObject[i2];
        localObject[i2] = j;
      }
      else
      {
        f[j] = c;
        c = j;
      }
      z += 1;
      paramLabel.b(a);
      d += 1;
      if (!z) {
        x += 1;
      }
      j = x;
      paramLabel = i;
      if (j >= paramLabel.length)
      {
        z = true;
        x = (paramLabel.length - 1);
      }
    }
  }
  
  void a(h paramH, h[] paramArrayOfH)
  {
    int j = c;
    d localD1 = this;
    int m = 0;
    while ((j != -1) && (m < d))
    {
      Object localObject = e.b[i[j]];
      if (c != -1)
      {
        float f1 = b[j];
        localD1.a((Label)localObject, true);
        localObject = paramArrayOfH[c];
        if (!h)
        {
          d localD2 = b;
          m = c;
          j = 0;
          while ((m != -1) && (j < d))
          {
            localD1.a(e.b[i[m]], b[m] * f1, true);
            m = f[m];
            j += 1;
          }
        }
        i += i * f1;
        c.a(paramH);
        j = c;
        m = 0;
      }
      else
      {
        j = f[j];
        m += 1;
      }
    }
  }
  
  public final void b()
  {
    int m = c;
    int j = 0;
    while ((m != -1) && (j < d))
    {
      Label localLabel = e.b[i[m]];
      if (localLabel != null) {
        localLabel.a(a);
      }
      m = f[m];
      j += 1;
    }
    c = -1;
    x = -1;
    z = false;
    d = 0;
  }
  
  void b(float paramFloat)
  {
    int m = c;
    int j = 0;
    while ((m != -1) && (j < d))
    {
      float[] arrayOfFloat = b;
      arrayOfFloat[m] /= paramFloat;
      m = f[m];
      j += 1;
    }
  }
  
  final void b(h paramH1, h paramH2, boolean paramBoolean)
  {
    int j = c;
    int m = 0;
    while ((j != -1) && (m < d))
    {
      int i1 = i[j];
      Object localObject = c;
      if (i1 == i)
      {
        float f1 = b[j];
        a((Label)localObject, paramBoolean);
        localObject = b;
        m = c;
        j = 0;
        while ((m != -1) && (j < d))
        {
          a(e.b[i[m]], b[m] * f1, paramBoolean);
          m = f[m];
          j += 1;
        }
        i += i * f1;
        if (paramBoolean) {
          c.a(paramH1);
        }
        j = c;
        m = 0;
      }
      else
      {
        j = f[j];
        m += 1;
      }
    }
  }
  
  final boolean b(Label paramLabel)
  {
    if (c == -1) {
      return false;
    }
    int m = c;
    int j = 0;
    while ((m != -1) && (j < d))
    {
      if (i[m] == i) {
        return true;
      }
      m = f[m];
      j += 1;
    }
    return false;
  }
  
  final float c(int paramInt)
  {
    int m = c;
    int j = 0;
    while ((m != -1) && (j < d))
    {
      if (j == paramInt) {
        return b[m];
      }
      m = f[m];
      j += 1;
    }
    return 0.0F;
  }
  
  public String toString()
  {
    String str = "";
    int m = c;
    int j = 0;
    while ((m != -1) && (j < d))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append(" -> ");
      str = localStringBuilder.toString();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append(b[m]);
      localStringBuilder.append(" : ");
      str = localStringBuilder.toString();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append(e.b[i[m]]);
      str = localStringBuilder.toString();
      m = f[m];
      j += 1;
    }
    return str;
  }
}
