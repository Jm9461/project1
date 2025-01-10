package apps.muzei.api.asm;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import apps.muzei.api.R.styleable;

public class ClassWriter
{
  private static volatile ClassWriter b;
  private Context a;
  private int g;
  private AnnotationWriter u;
  private SparseArray<int[]> v = new SparseArray();
  
  public ClassWriter() {}
  
  public static ClassWriter a()
  {
    if (b == null) {
      try
      {
        if (b == null) {
          b = new ClassWriter();
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    return b;
  }
  
  private int[] a(Context paramContext, int paramInt)
  {
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.getResources().obtainTypedArray(paramInt);
    int[] arrayOfInt = new int[paramContext.length()];
    paramInt = 0;
    while (paramInt < arrayOfInt.length)
    {
      arrayOfInt[paramInt] = paramContext.getResourceId(paramInt, 0);
      paramInt += 1;
    }
    paramContext.recycle();
    return arrayOfInt;
  }
  
  private int[] get(int paramInt)
  {
    Object localObject = v;
    if (localObject == null) {
      return null;
    }
    int[] arrayOfInt = (int[])((SparseArray)localObject).get(paramInt);
    localObject = arrayOfInt;
    if (arrayOfInt == null)
    {
      localObject = a(a, paramInt);
      v.put(paramInt, localObject);
    }
    return localObject;
  }
  
  public static int getStyleId(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ThemableView, paramInt1, paramInt2);
    paramInt1 = paramContext.getResourceId(R.styleable.ThemableView_v_styleId, 0);
    paramContext.recycle();
    return paramInt1;
  }
  
  public int a(int paramInt)
  {
    return get(paramInt, g);
  }
  
  public void a(ClassVisitor paramClassVisitor)
  {
    AnnotationWriter localAnnotationWriter = u;
    if (localAnnotationWriter != null) {
      localAnnotationWriter.visitEnum(paramClassVisitor);
    }
  }
  
  public void b(ClassVisitor paramClassVisitor)
  {
    AnnotationWriter localAnnotationWriter = u;
    if (localAnnotationWriter != null) {
      localAnnotationWriter.a(paramClassVisitor);
    }
  }
  
  public int get(int paramInt1, int paramInt2)
  {
    int[] arrayOfInt = get(paramInt1);
    if (arrayOfInt == null) {
      return 0;
    }
    return arrayOfInt[paramInt2];
  }
}
