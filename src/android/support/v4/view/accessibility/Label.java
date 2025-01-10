package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener;

class Label
  implements AccessibilityManager.TouchExplorationStateChangeListener
{
  final ClassWriter b;
  
  Label(ClassWriter paramClassWriter)
  {
    b = paramClassWriter;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject != null) && (getClass() == paramObject.getClass()))
    {
      paramObject = (Label)paramObject;
      return b.equals(b);
    }
    return false;
  }
  
  public int hashCode()
  {
    return b.hashCode();
  }
  
  public void onTouchExplorationStateChanged(boolean paramBoolean)
  {
    b.onTouchExplorationStateChanged(paramBoolean);
  }
}
