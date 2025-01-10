package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityManager;

public final class ByteVector
{
  public static boolean a(AccessibilityManager paramAccessibilityManager, ClassWriter paramClassWriter)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      if (paramClassWriter == null) {
        return false;
      }
      return paramAccessibilityManager.removeTouchExplorationStateChangeListener(new Label(paramClassWriter));
    }
    return false;
  }
  
  public static boolean b(AccessibilityManager paramAccessibilityManager, ClassWriter paramClassWriter)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      if (paramClassWriter == null) {
        return false;
      }
      return paramAccessibilityManager.addTouchExplorationStateChangeListener(new Label(paramClassWriter));
    }
    return false;
  }
}
