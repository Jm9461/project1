package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.os.Bundle;
import java.util.List;

public class AccessibilityNodeProviderCompat
{
  private final Object mProvider;
  
  public AccessibilityNodeProviderCompat()
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 19)
    {
      mProvider = new AccessibilityNodeInfoCompat.AccessibilityNodeInfoImpl(this);
      return;
    }
    if (i >= 16)
    {
      mProvider = new AccessibilityNodeProviderCompatKitKat.1(this);
      return;
    }
    mProvider = null;
  }
  
  public AccessibilityNodeProviderCompat(Object paramObject)
  {
    mProvider = paramObject;
  }
  
  public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int paramInt)
  {
    return null;
  }
  
  public List findAccessibilityNodeInfosByText(String paramString, int paramInt)
  {
    return null;
  }
  
  public Object getProvider()
  {
    return mProvider;
  }
  
  public AccessibilityNodeInfoCompat performAction(int paramInt)
  {
    return null;
  }
  
  public boolean performAction(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    return false;
  }
}
