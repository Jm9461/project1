package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.view.accessibility.AccessibilityNodeInfo.CollectionInfo;
import android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo;

public class AccessibilityNodeInfoCompat
{
  private final AccessibilityNodeInfo IMPL;
  public int height = -1;
  
  private AccessibilityNodeInfoCompat(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    IMPL = paramAccessibilityNodeInfo;
  }
  
  private void format(int paramInt, boolean paramBoolean)
  {
    Bundle localBundle = getExtras();
    if (localBundle != null)
    {
      int i = 0;
      int j = localBundle.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0);
      if (paramBoolean) {
        i = paramInt;
      }
      localBundle.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i | j & paramInt);
    }
  }
  
  private static String getActionSymbolicName(int paramInt)
  {
    if (paramInt != 1)
    {
      if (paramInt != 2)
      {
        switch (paramInt)
        {
        default: 
          return "ACTION_UNKNOWN";
        case 131072: 
          return "ACTION_SET_SELECTION";
        case 65536: 
          return "ACTION_CUT";
        case 32768: 
          return "ACTION_PASTE";
        case 16384: 
          return "ACTION_COPY";
        case 8192: 
          return "ACTION_SCROLL_BACKWARD";
        case 4096: 
          return "ACTION_SCROLL_FORWARD";
        case 2048: 
          return "ACTION_PREVIOUS_HTML_ELEMENT";
        case 1024: 
          return "ACTION_NEXT_HTML_ELEMENT";
        case 512: 
          return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
        case 256: 
          return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
        case 128: 
          return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
        case 64: 
          return "ACTION_ACCESSIBILITY_FOCUS";
        case 32: 
          return "ACTION_LONG_CLICK";
        case 16: 
          return "ACTION_CLICK";
        case 8: 
          return "ACTION_CLEAR_SELECTION";
        }
        return "ACTION_SELECT";
      }
      return "ACTION_CLEAR_FOCUS";
    }
    return "ACTION_FOCUS";
  }
  
  public static AccessibilityNodeInfoCompat obtain()
  {
    return obtain(AccessibilityNodeInfo.obtain());
  }
  
  public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
  {
    return obtain(AccessibilityNodeInfo.obtain(IMPL));
  }
  
  public static AccessibilityNodeInfoCompat obtain(View paramView)
  {
    return obtain(AccessibilityNodeInfo.obtain(paramView));
  }
  
  public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    return new AccessibilityNodeInfoCompat(paramAccessibilityNodeInfo);
  }
  
  public void addAction(int paramInt)
  {
    IMPL.addAction(paramInt);
  }
  
  public void addChild(View paramView)
  {
    IMPL.addChild(paramView);
  }
  
  public void addChild(View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      IMPL.addChild(paramView, paramInt);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject == null) {
      return false;
    }
    if (getClass() != paramObject.getClass()) {
      return false;
    }
    paramObject = (AccessibilityNodeInfoCompat)paramObject;
    AccessibilityNodeInfo localAccessibilityNodeInfo = IMPL;
    if (localAccessibilityNodeInfo == null)
    {
      if (IMPL != null) {
        return false;
      }
    }
    else if (!localAccessibilityNodeInfo.equals(IMPL)) {
      return false;
    }
    return true;
  }
  
  public int getActions()
  {
    return IMPL.getActions();
  }
  
  public void getBoundsInParent(Rect paramRect)
  {
    IMPL.getBoundsInParent(paramRect);
  }
  
  public void getBoundsInScreen(Rect paramRect)
  {
    IMPL.getBoundsInScreen(paramRect);
  }
  
  public CharSequence getClassName()
  {
    return IMPL.getClassName();
  }
  
  public CharSequence getContentDescription()
  {
    return IMPL.getContentDescription();
  }
  
  public Bundle getExtras()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return IMPL.getExtras();
    }
    return new Bundle();
  }
  
  public AccessibilityNodeInfo getInfo()
  {
    return IMPL;
  }
  
  public CharSequence getPackageName()
  {
    return IMPL.getPackageName();
  }
  
  public CharSequence getText()
  {
    return IMPL.getText();
  }
  
  public String getViewIdResourceName()
  {
    if (Build.VERSION.SDK_INT >= 18) {
      return IMPL.getViewIdResourceName();
    }
    return null;
  }
  
  public int hashCode()
  {
    AccessibilityNodeInfo localAccessibilityNodeInfo = IMPL;
    if (localAccessibilityNodeInfo == null) {
      return 0;
    }
    return localAccessibilityNodeInfo.hashCode();
  }
  
  public boolean isAccessibilityFocused()
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return IMPL.isAccessibilityFocused();
    }
    return false;
  }
  
  public boolean isCheckable()
  {
    return IMPL.isCheckable();
  }
  
  public boolean isChecked()
  {
    return IMPL.isChecked();
  }
  
  public boolean isClickable()
  {
    return IMPL.isClickable();
  }
  
  public boolean isEnabled()
  {
    return IMPL.isEnabled();
  }
  
  public boolean isFocusable()
  {
    return IMPL.isFocusable();
  }
  
  public boolean isFocused()
  {
    return IMPL.isFocused();
  }
  
  public boolean isLongClickable()
  {
    return IMPL.isLongClickable();
  }
  
  public boolean isPassword()
  {
    return IMPL.isPassword();
  }
  
  public boolean isScrollable()
  {
    return IMPL.isScrollable();
  }
  
  public boolean isSelected()
  {
    return IMPL.isSelected();
  }
  
  public boolean isVisibleToUser()
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return IMPL.isVisibleToUser();
    }
    return false;
  }
  
  public void obtain(View paramView, int paramInt)
  {
    height = paramInt;
    if (Build.VERSION.SDK_INT >= 16) {
      IMPL.setParent(paramView, paramInt);
    }
  }
  
  public void obtain(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      IMPL.setDismissable(paramBoolean);
    }
  }
  
  public boolean removeAction(AccessibilityActionCompat paramAccessibilityActionCompat)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return IMPL.removeAction((AccessibilityNodeInfo.AccessibilityAction)mInfo);
    }
    return false;
  }
  
  public void setAccessibilityFocused(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      IMPL.setAccessibilityFocused(paramBoolean);
    }
  }
  
  public void setBoundsInParent(Rect paramRect)
  {
    IMPL.setBoundsInParent(paramRect);
  }
  
  public void setBoundsInScreen(Rect paramRect)
  {
    IMPL.setBoundsInScreen(paramRect);
  }
  
  public void setClassName(CharSequence paramCharSequence)
  {
    IMPL.setClassName(paramCharSequence);
  }
  
  public void setClickable(boolean paramBoolean)
  {
    IMPL.setClickable(paramBoolean);
  }
  
  public void setCollectionInfo(Object paramObject)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      AccessibilityNodeInfo localAccessibilityNodeInfo = IMPL;
      if (paramObject == null) {
        paramObject = null;
      } else {
        paramObject = (AccessibilityNodeInfo.CollectionInfo)mInfo;
      }
      localAccessibilityNodeInfo.setCollectionInfo(paramObject);
    }
  }
  
  public void setCollectionItemInfo(Object paramObject)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      AccessibilityNodeInfo localAccessibilityNodeInfo = IMPL;
      if (paramObject == null) {
        paramObject = null;
      } else {
        paramObject = (AccessibilityNodeInfo.CollectionItemInfo)mInfo;
      }
      localAccessibilityNodeInfo.setCollectionItemInfo(paramObject);
    }
  }
  
  public void setContentDescription(CharSequence paramCharSequence)
  {
    IMPL.setContentDescription(paramCharSequence);
  }
  
  public void setContentInvalid(boolean paramBoolean)
  {
    IMPL.setCheckable(paramBoolean);
  }
  
  public int setEnabled()
  {
    return IMPL.getChildCount();
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    IMPL.setEnabled(paramBoolean);
  }
  
  public void setError(CharSequence paramCharSequence)
  {
    IMPL.setText(paramCharSequence);
  }
  
  public void setError(boolean paramBoolean)
  {
    IMPL.setChecked(paramBoolean);
  }
  
  public void setFocusable(boolean paramBoolean)
  {
    IMPL.setFocusable(paramBoolean);
  }
  
  public void setFocused(boolean paramBoolean)
  {
    IMPL.setFocused(paramBoolean);
  }
  
  public void setLongClickable(boolean paramBoolean)
  {
    IMPL.setLongClickable(paramBoolean);
  }
  
  public void setPackageName(CharSequence paramCharSequence)
  {
    IMPL.setPackageName(paramCharSequence);
  }
  
  public void setParent()
  {
    IMPL.recycle();
  }
  
  public void setParent(View paramView)
  {
    IMPL.setParent(paramView);
  }
  
  public void setParent(View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      IMPL.setSource(paramView, paramInt);
    }
  }
  
  public void setParent(CharSequence paramCharSequence)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      IMPL.setError(paramCharSequence);
    }
  }
  
  public void setParent(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      IMPL.setContentInvalid(paramBoolean);
    }
  }
  
  public void setScrollable(boolean paramBoolean)
  {
    IMPL.setScrollable(paramBoolean);
  }
  
  public void setSelected(boolean paramBoolean)
  {
    IMPL.setSelected(paramBoolean);
  }
  
  public void setSource(View paramView)
  {
    IMPL.setSource(paramView);
  }
  
  public void setText(CharSequence paramCharSequence)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 26)
    {
      IMPL.setHintText(paramCharSequence);
      return;
    }
    if (i >= 19) {
      IMPL.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY", paramCharSequence);
    }
  }
  
  public void setText(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      IMPL.setShowingHintText(paramBoolean);
      return;
    }
    format(4, paramBoolean);
  }
  
  public void setVisibleToUser(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      IMPL.setVisibleToUser(paramBoolean);
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(super.toString());
    Object localObject = new Rect();
    getBoundsInParent((Rect)localObject);
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("; boundsInParent: ");
    localStringBuilder2.append(localObject);
    localStringBuilder1.append(localStringBuilder2.toString());
    getBoundsInScreen((Rect)localObject);
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("; boundsInScreen: ");
    localStringBuilder2.append(localObject);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder1.append("; packageName: ");
    localStringBuilder1.append(getPackageName());
    localStringBuilder1.append("; className: ");
    localStringBuilder1.append(getClassName());
    localStringBuilder1.append("; text: ");
    localStringBuilder1.append(getText());
    localStringBuilder1.append("; contentDescription: ");
    localStringBuilder1.append(getContentDescription());
    localStringBuilder1.append("; viewId: ");
    localStringBuilder1.append(getViewIdResourceName());
    localStringBuilder1.append("; checkable: ");
    localStringBuilder1.append(isCheckable());
    localStringBuilder1.append("; checked: ");
    localStringBuilder1.append(isChecked());
    localStringBuilder1.append("; focusable: ");
    localStringBuilder1.append(isFocusable());
    localStringBuilder1.append("; focused: ");
    localStringBuilder1.append(isFocused());
    localStringBuilder1.append("; selected: ");
    localStringBuilder1.append(isSelected());
    localStringBuilder1.append("; clickable: ");
    localStringBuilder1.append(isClickable());
    localStringBuilder1.append("; longClickable: ");
    localStringBuilder1.append(isLongClickable());
    localStringBuilder1.append("; enabled: ");
    localStringBuilder1.append(isEnabled());
    localStringBuilder1.append("; password: ");
    localStringBuilder1.append(isPassword());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("; scrollable: ");
    ((StringBuilder)localObject).append(isScrollable());
    localStringBuilder1.append(((StringBuilder)localObject).toString());
    localStringBuilder1.append("; [");
    int i = getActions();
    while (i != 0)
    {
      int k = 1 << Integer.numberOfTrailingZeros(i);
      int j = i & k;
      localStringBuilder1.append(getActionSymbolicName(k));
      i = j;
      if (j != 0)
      {
        localStringBuilder1.append(", ");
        i = j;
      }
    }
    localStringBuilder1.append("]");
    return localStringBuilder1.toString();
  }
  
  public class AccessibilityActionCompat
  {
    public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS;
    public static final AccessibilityActionCompat ACTION_FOCUS;
    
    static
    {
      Object localObject2 = null;
      ACTION_FOCUS = new AccessibilityActionCompat(1, null);
      ACTION_CLEAR_FOCUS = new AccessibilityActionCompat(2, null);
      new AccessibilityActionCompat(4, null);
      new AccessibilityActionCompat(8, null);
      new AccessibilityActionCompat(16, null);
      new AccessibilityActionCompat(32, null);
      new AccessibilityActionCompat(64, null);
      new AccessibilityActionCompat(128, null);
      new AccessibilityActionCompat(256, null);
      new AccessibilityActionCompat(512, null);
      new AccessibilityActionCompat(1024, null);
      new AccessibilityActionCompat(2048, null);
      new AccessibilityActionCompat(4096, null);
      new AccessibilityActionCompat(8192, null);
      new AccessibilityActionCompat(16384, null);
      new AccessibilityActionCompat(32768, null);
      new AccessibilityActionCompat(65536, null);
      new AccessibilityActionCompat(131072, null);
      new AccessibilityActionCompat(262144, null);
      new AccessibilityActionCompat(524288, null);
      new AccessibilityActionCompat(1048576, null);
      new AccessibilityActionCompat(2097152, null);
      if (Build.VERSION.SDK_INT >= 23) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN;
      } else {
        localObject1 = null;
      }
      new AccessibilityActionCompat(localObject1);
      if (Build.VERSION.SDK_INT >= 23) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION;
      } else {
        localObject1 = null;
      }
      new AccessibilityActionCompat(localObject1);
      if (Build.VERSION.SDK_INT >= 23) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP;
      } else {
        localObject1 = null;
      }
      new AccessibilityActionCompat(localObject1);
      if (Build.VERSION.SDK_INT >= 23) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT;
      } else {
        localObject1 = null;
      }
      new AccessibilityActionCompat(localObject1);
      if (Build.VERSION.SDK_INT >= 23) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN;
      } else {
        localObject1 = null;
      }
      new AccessibilityActionCompat(localObject1);
      if (Build.VERSION.SDK_INT >= 23) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT;
      } else {
        localObject1 = null;
      }
      new AccessibilityActionCompat(localObject1);
      if (Build.VERSION.SDK_INT >= 23) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK;
      } else {
        localObject1 = null;
      }
      new AccessibilityActionCompat(localObject1);
      if (Build.VERSION.SDK_INT >= 24) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS;
      } else {
        localObject1 = null;
      }
      new AccessibilityActionCompat(localObject1);
      if (Build.VERSION.SDK_INT >= 26) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW;
      } else {
        localObject1 = null;
      }
      new AccessibilityActionCompat(localObject1);
      if (Build.VERSION.SDK_INT >= 28) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP;
      } else {
        localObject1 = null;
      }
      new AccessibilityActionCompat(localObject1);
      Object localObject1 = localObject2;
      if (Build.VERSION.SDK_INT >= 28) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP;
      }
      new AccessibilityActionCompat(localObject1);
    }
    
    public AccessibilityActionCompat(CharSequence paramCharSequence)
    {
      this();
    }
    
    AccessibilityActionCompat() {}
  }
  
  public class CollectionInfoCompat
  {
    CollectionInfoCompat() {}
    
    public static CollectionInfoCompat obtain(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
    {
      int i = Build.VERSION.SDK_INT;
      if (i >= 21) {
        return new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(paramInt1, paramInt2, paramBoolean, paramInt3));
      }
      if (i >= 19) {
        return new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(paramInt1, paramInt2, paramBoolean));
      }
      return new CollectionInfoCompat(null);
    }
  }
  
  public class CollectionItemInfoCompat
  {
    CollectionItemInfoCompat() {}
    
    public static CollectionItemInfoCompat obtain(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2)
    {
      int i = Build.VERSION.SDK_INT;
      if (i >= 21) {
        return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean1, paramBoolean2));
      }
      if (i >= 19) {
        return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean1));
      }
      return new CollectionItemInfoCompat(null);
    }
  }
}
