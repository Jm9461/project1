package android.support.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

public class ClassWriter
  extends ConstraintLayout.a
{
  public boolean a = false;
  public float d = 1.0F;
  public float h = 0.0F;
  public float i = 0.0F;
  public float j = 0.0F;
  public float k = 0.0F;
  public float n = 0.0F;
  public float r = 0.0F;
  public float s = 0.0F;
  public float w = 1.0F;
  public float x = 0.0F;
  public float y = 0.0F;
  public float z = 1.0F;
  
  public ClassWriter(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }
  
  public ClassWriter(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, IpAddress.ConstraintSet);
    int i1 = paramContext.getIndexCount();
    int m = 0;
    while (m < i1)
    {
      int i2 = paramContext.getIndex(m);
      if (i2 == IpAddress.ConstraintSet_android_alpha)
      {
        w = paramContext.getFloat(i2, w);
      }
      else if (i2 == IpAddress.ConstraintSet_android_elevation)
      {
        x = paramContext.getFloat(i2, x);
        a = true;
      }
      else if (i2 == IpAddress.ConstraintSet_android_rotationX)
      {
        r = paramContext.getFloat(i2, r);
      }
      else if (i2 == IpAddress.ConstraintSet_android_rotationY)
      {
        n = paramContext.getFloat(i2, n);
      }
      else if (i2 == IpAddress.ConstraintSet_android_rotation)
      {
        s = paramContext.getFloat(i2, s);
      }
      else if (i2 == IpAddress.ConstraintSet_android_scaleX)
      {
        z = paramContext.getFloat(i2, z);
      }
      else if (i2 == IpAddress.ConstraintSet_android_scaleY)
      {
        d = paramContext.getFloat(i2, d);
      }
      else if (i2 == IpAddress.ConstraintSet_android_transformPivotX)
      {
        j = paramContext.getFloat(i2, j);
      }
      else if (i2 == IpAddress.ConstraintSet_android_transformPivotY)
      {
        k = paramContext.getFloat(i2, k);
      }
      else if (i2 == IpAddress.ConstraintSet_android_translationX)
      {
        i = paramContext.getFloat(i2, i);
      }
      else if (i2 == IpAddress.ConstraintSet_android_translationY)
      {
        h = paramContext.getFloat(i2, h);
      }
      else if (i2 == IpAddress.ConstraintSet_android_translationZ)
      {
        i = paramContext.getFloat(i2, y);
      }
      m += 1;
    }
  }
}
