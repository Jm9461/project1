package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityEvent;

public final class AccessibilityEventCompat
{
  public static int getContentChangeTypes(AccessibilityEvent paramAccessibilityEvent)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return paramAccessibilityEvent.getContentChangeTypes();
    }
    return 0;
  }
  
  public static void setContentChangeTypes(AccessibilityEvent paramAccessibilityEvent, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      paramAccessibilityEvent.setContentChangeTypes(paramInt);
    }
  }
}
