package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;

public class AccessibilityRecordCompat
{
  public static void obtain(AccessibilityRecord paramAccessibilityRecord, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 15) {
      paramAccessibilityRecord.setMaxScrollY(paramInt);
    }
  }
  
  public static void obtain(AccessibilityRecord paramAccessibilityRecord, View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      paramAccessibilityRecord.setSource(paramView, paramInt);
    }
  }
  
  public static void setMaxScrollX(AccessibilityRecord paramAccessibilityRecord, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 15) {
      paramAccessibilityRecord.setMaxScrollX(paramInt);
    }
  }
}
