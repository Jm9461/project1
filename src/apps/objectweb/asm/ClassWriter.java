package apps.objectweb.asm;

import b.d.a.d;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class ClassWriter
{
  private final float a;
  private final i[] b;
  private boolean c;
  private final LayoutManager[] f;
  private final Set<d> index;
  private final Queue<d> n;
  private final Switch p;
  private final Random r;
  private final Set<d> s;
  private float t;
  private final Image x;
  private final float z;
  
  public ClassWriter(Switch paramSwitch, float[] paramArrayOfFloat, float paramFloat1, float paramFloat2, Random paramRandom)
  {
    p = paramSwitch;
    r = paramRandom;
    b = new i[c];
    float f1 = b;
    f1 = paramFloat1 + f1 / (f * 2.0F + f1) * (paramFloat2 - paramFloat1);
    x = new Image(paramArrayOfFloat, -1.0F, 1.0F, paramFloat1, f1);
    int i = c;
    paramFloat1 = 2.0F / i;
    float[] arrayOfFloat = read(r, i, paramFloat1, 0.15F);
    z = f1;
    a = paramFloat2;
    i = 0;
    while (i < c)
    {
      byte b1;
      if (i % 2 == 0) {
        b1 = 0;
      } else {
        b1 = 1;
      }
      b[i] = new i(paramArrayOfFloat, arrayOfFloat[i], arrayOfFloat[(i + 1)], f1, paramFloat2, b1, paramRandom);
      i += 1;
    }
    index = Collections.newSetFromMap(new ConcurrentHashMap());
    s = Collections.newSetFromMap(new ConcurrentHashMap());
    n = new LinkedList();
    f = a(paramArrayOfFloat, k);
    Collections.addAll(n, f);
  }
  
  private LayoutManager[] a(float[] paramArrayOfFloat, int paramInt)
  {
    LayoutManager[] arrayOfLayoutManager = new LayoutManager[paramInt];
    int i = 0;
    while (i < paramInt)
    {
      Object localObject = p;
      float f2 = s;
      float f1 = f2;
      if (p) {
        f1 = f2 * (r.nextFloat() * 0.8F + 0.5F);
      }
      f2 = r.nextFloat();
      int j;
      if (r.nextBoolean()) {
        j = 1;
      } else {
        j = -1;
      }
      float f3 = j;
      localObject = new float[paramArrayOfFloat.length];
      System.arraycopy(paramArrayOfFloat, 0, localObject, 0, localObject.length);
      arrayOfLayoutManager[i] = new LayoutManager((float[])localObject, r.nextFloat() * 2.0F - 1.0F, z + f2 * 0.1F * f3, a, f1, r);
      i += 1;
    }
    return arrayOfLayoutManager;
  }
  
  private void read()
  {
    Object localObject2 = r;
    Object localObject1 = this;
    int k = ((Random)localObject2).nextInt(3);
    int i = 0;
    while (i < k)
    {
      LayoutManager localLayoutManager = (LayoutManager)n.poll();
      localObject2 = localObject1;
      if (localLayoutManager != null)
      {
        float f3 = r.nextFloat();
        Object localObject3 = r;
        localObject2 = localObject1;
        int j;
        if (((Random)localObject3).nextBoolean()) {
          j = 1;
        } else {
          j = -1;
        }
        float f4 = j;
        localObject3 = p;
        float f2 = s;
        float f1 = f2;
        if (p) {
          f1 = f2 * (r.nextFloat() * 0.8F + 0.5F);
        }
        localObject2 = r;
        localLayoutManager.init(((Random)localObject2).nextFloat() * 2.0F - 1.0F, z + f3 * 0.1F * f4, a, f1);
        s.add(localLayoutManager);
        localObject2 = localObject1;
      }
      i += 1;
      localObject1 = localObject2;
    }
  }
  
  private static float[] read(Random paramRandom, int paramInt, float paramFloat1, float paramFloat2)
  {
    float[] arrayOfFloat = new float[paramInt + 1];
    paramInt = 0;
    while (paramInt < arrayOfFloat.length)
    {
      if (paramInt == 0)
      {
        arrayOfFloat[paramInt] = -1.0F;
      }
      else
      {
        int i = arrayOfFloat.length;
        float f1 = 1.0F;
        if (paramInt == i - 1)
        {
          arrayOfFloat[paramInt] = 1.0F;
        }
        else
        {
          float f2 = paramRandom.nextFloat();
          if (!paramRandom.nextBoolean()) {
            f1 = -1.0F;
          }
          arrayOfFloat[paramInt] = (paramInt * paramFloat1 - 1.0F + f2 * paramFloat2 * paramFloat1 * f1);
        }
      }
      paramInt += 1;
    }
    return arrayOfFloat;
  }
  
  public void a(float paramFloat1, float paramFloat2)
  {
    i[] arrayOfI = b;
    int j = arrayOfI.length;
    int i = 0;
    while (i < j)
    {
      arrayOfI[i].b(Label.a(paramFloat1, r));
      i += 1;
    }
    float f1 = t;
    if (paramFloat2 > f1)
    {
      t = paramFloat2;
      if (paramFloat1 > 0.25F) {
        read();
      }
    }
    else
    {
      t = Label.put(f1, paramFloat2, 0.8F);
    }
  }
  
  public void a(long paramLong, float paramFloat1, float paramFloat2)
  {
    float f1 = (float)paramLong;
    c = true;
    Object localObject = b;
    int j = localObject.length;
    int i = 0;
    LayoutManager localLayoutManager;
    while (i < j)
    {
      localLayoutManager = localObject[i];
      localLayoutManager.draw(f1 * paramFloat1);
      c &= localLayoutManager.g();
      i += 1;
    }
    index.addAll(s);
    s.clear();
    localObject = index.iterator();
    while (((Iterator)localObject).hasNext())
    {
      localLayoutManager = (LayoutManager)((Iterator)localObject).next();
      localLayoutManager.draw(paramLong, paramFloat2);
      if (localLayoutManager.a())
      {
        n.add(localLayoutManager);
        ((Iterator)localObject).remove();
      }
    }
  }
  
  public void draw()
  {
    Object localObject = b;
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      localObject[i].draw();
      i += 1;
    }
    x.draw();
    localObject = index.iterator();
    while (((Iterator)localObject).hasNext()) {
      ((LayoutManager)((Iterator)localObject).next()).draw();
    }
  }
  
  public boolean get()
  {
    return c;
  }
}
