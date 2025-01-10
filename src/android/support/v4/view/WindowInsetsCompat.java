package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.WindowInsets;

public class WindowInsetsCompat
{
  private final Object mSource;
  
  private WindowInsetsCompat(Object paramObject)
  {
    mSource = paramObject;
  }
  
  static WindowInsetsCompat unwrap(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    return new WindowInsetsCompat(paramObject);
  }
  
  static Object unwrap(WindowInsetsCompat paramWindowInsetsCompat)
  {
    if (paramWindowInsetsCompat == null) {
      return null;
    }
    return mSource;
  }
  
  public WindowInsetsCompat consumeSystemWindowInsets()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return new WindowInsetsCompat(((WindowInsets)mSource).consumeSystemWindowInsets());
    }
    return null;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject != null)
    {
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (WindowInsetsCompat)paramObject;
      Object localObject = mSource;
      if (localObject == null) {
        return mSource == null;
      }
      return localObject.equals(mSource);
    }
    return false;
  }
  
  public boolean get()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return ((WindowInsets)mSource).hasSystemWindowInsets();
    }
    return false;
  }
  
  public int getSystemWindowInsetBottom()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return ((WindowInsets)mSource).getSystemWindowInsetBottom();
    }
    return 0;
  }
  
  public int getSystemWindowInsetLeft()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return ((WindowInsets)mSource).getSystemWindowInsetLeft();
    }
    return 0;
  }
  
  public int getSystemWindowInsetRight()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return ((WindowInsets)mSource).getSystemWindowInsetRight();
    }
    return 0;
  }
  
  public int getSystemWindowInsetTop()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return ((WindowInsets)mSource).getSystemWindowInsetTop();
    }
    return 0;
  }
  
  public int hashCode()
  {
    Object localObject = mSource;
    if (localObject == null) {
      return 0;
    }
    return localObject.hashCode();
  }
  
  public boolean isConsumed()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return ((WindowInsets)mSource).isConsumed();
    }
    return false;
  }
  
  public WindowInsetsCompat replaceSystemWindowInsets(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return new WindowInsetsCompat(((WindowInsets)mSource).replaceSystemWindowInsets(paramInt1, paramInt2, paramInt3, paramInt4));
    }
    return null;
  }
}
