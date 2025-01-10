package android.support.v4.content.view;

import java.lang.reflect.Array;

final class k
{
  public static int[] a(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int[] arrayOfInt = paramArrayOfInt;
    if (paramInt1 + 1 > paramArrayOfInt.length)
    {
      arrayOfInt = new int[getStep(paramInt1)];
      System.arraycopy(paramArrayOfInt, 0, arrayOfInt, 0, paramInt1);
    }
    arrayOfInt[paramInt1] = paramInt2;
    return arrayOfInt;
  }
  
  public static Object[] a(Object[] paramArrayOfObject, int paramInt, Object paramObject)
  {
    Object[] arrayOfObject = paramArrayOfObject;
    if (paramInt + 1 > paramArrayOfObject.length)
    {
      arrayOfObject = (Object[])Array.newInstance(paramArrayOfObject.getClass().getComponentType(), getStep(paramInt));
      System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, paramInt);
    }
    arrayOfObject[paramInt] = paramObject;
    return arrayOfObject;
  }
  
  public static int getStep(int paramInt)
  {
    if (paramInt <= 4) {
      return 8;
    }
    return paramInt * 2;
  }
}
