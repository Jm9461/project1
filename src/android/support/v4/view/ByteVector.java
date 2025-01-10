package android.support.v4.view;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.PointerIcon;

public final class ByteVector
{
  private Object b;
  
  private ByteVector(Object paramObject)
  {
    b = paramObject;
  }
  
  public static ByteVector a(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return new ByteVector(PointerIcon.getSystemIcon(paramContext, paramInt));
    }
    return new ByteVector(null);
  }
  
  public Object b()
  {
    return b;
  }
}
