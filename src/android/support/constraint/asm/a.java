package android.support.constraint.asm;

import android.support.constraint.i.g;

class a<T>
  implements g<T>
{
  private final Object[] a;
  private int r;
  
  a(int paramInt)
  {
    if (paramInt > 0)
    {
      a = new Object[paramInt];
      return;
    }
    throw new IllegalArgumentException("The max pool size must be > 0");
  }
  
  public Object a()
  {
    int i = r;
    if (i > 0)
    {
      int j = i - 1;
      Object[] arrayOfObject = a;
      Object localObject = arrayOfObject[j];
      arrayOfObject[j] = null;
      r = (i - 1);
      return localObject;
    }
    return null;
  }
  
  public void a(Object[] paramArrayOfObject, int paramInt)
  {
    int i = paramInt;
    if (paramInt > paramArrayOfObject.length) {
      i = paramArrayOfObject.length;
    }
    paramInt = 0;
    while (paramInt < i)
    {
      Object localObject = paramArrayOfObject[paramInt];
      int j = r;
      Object[] arrayOfObject = a;
      if (j < arrayOfObject.length)
      {
        arrayOfObject[j] = localObject;
        r = (j + 1);
      }
      paramInt += 1;
    }
  }
  
  public boolean a(Object paramObject)
  {
    int i = r;
    Object[] arrayOfObject = a;
    if (i < arrayOfObject.length)
    {
      arrayOfObject[i] = paramObject;
      r = (i + 1);
      return true;
    }
    return false;
  }
}
