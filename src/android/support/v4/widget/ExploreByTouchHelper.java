package android.support.v4.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewParentCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityRecord;
import java.util.ArrayList;
import java.util.List;

public abstract class ExploreByTouchHelper
  extends AccessibilityDelegateCompat
{
  private static final Rect INVALID_PARENT_BOUNDS = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
  private final AccessibilityManager mAccessibilityManager;
  int mFocusedVirtualViewId = Integer.MIN_VALUE;
  private int mHoveredVirtualViewId = Integer.MIN_VALUE;
  int mManager = Integer.MIN_VALUE;
  private ExploreByTouchNodeProvider mNodeProvider;
  private final int[] mTempGlobalRect = new int[2];
  private final Rect mTempParentRect = new Rect();
  private final Rect mTempScreenRect = new Rect();
  private final Rect mTempVisibleRect = new Rect();
  private final View mView;
  
  static
  {
    new MXParser();
    new PopupMenu();
  }
  
  public ExploreByTouchHelper(View paramView)
  {
    if (paramView != null)
    {
      mView = paramView;
      mAccessibilityManager = ((AccessibilityManager)paramView.getContext().getSystemService("accessibility"));
      paramView.setFocusable(true);
      if (ViewCompat.getImportantForAccessibility(paramView) == 0) {
        ViewCompat.add(paramView, 1);
      }
    }
    else
    {
      throw new IllegalArgumentException("View may not be null");
    }
  }
  
  private AccessibilityEvent createEvent(int paramInt1, int paramInt2)
  {
    if (paramInt1 != -1) {
      return createEventForChild(paramInt1, paramInt2);
    }
    return createEventForHost(paramInt2);
  }
  
  private AccessibilityEvent createEventForChild(int paramInt1, int paramInt2)
  {
    AccessibilityEvent localAccessibilityEvent = AccessibilityEvent.obtain(paramInt2);
    AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = obtain(paramInt1);
    localAccessibilityEvent.getText().add(localAccessibilityNodeInfoCompat.getText());
    localAccessibilityEvent.setContentDescription(localAccessibilityNodeInfoCompat.getContentDescription());
    localAccessibilityEvent.setScrollable(localAccessibilityNodeInfoCompat.isScrollable());
    localAccessibilityEvent.setPassword(localAccessibilityNodeInfoCompat.isPassword());
    localAccessibilityEvent.setEnabled(localAccessibilityNodeInfoCompat.isEnabled());
    localAccessibilityEvent.setChecked(localAccessibilityNodeInfoCompat.isChecked());
    onPopulateEventForVirtualView(paramInt1, localAccessibilityEvent);
    if ((localAccessibilityEvent.getText().isEmpty()) && (localAccessibilityEvent.getContentDescription() == null)) {
      throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
    }
    localAccessibilityEvent.setClassName(localAccessibilityNodeInfoCompat.getClassName());
    AccessibilityRecordCompat.obtain(localAccessibilityEvent, mView, paramInt1);
    localAccessibilityEvent.setPackageName(mView.getContext().getPackageName());
    return localAccessibilityEvent;
  }
  
  private AccessibilityEvent createEventForHost(int paramInt)
  {
    AccessibilityEvent localAccessibilityEvent = AccessibilityEvent.obtain(paramInt);
    mView.onInitializeAccessibilityEvent(localAccessibilityEvent);
    return localAccessibilityEvent;
  }
  
  private AccessibilityNodeInfoCompat createNodeForChild(int paramInt)
  {
    Object localObject = AccessibilityNodeInfoCompat.obtain();
    ((AccessibilityNodeInfoCompat)localObject).setEnabled(true);
    ((AccessibilityNodeInfoCompat)localObject).setFocusable(true);
    ((AccessibilityNodeInfoCompat)localObject).setClassName("android.view.View");
    ((AccessibilityNodeInfoCompat)localObject).setBoundsInParent(INVALID_PARENT_BOUNDS);
    ((AccessibilityNodeInfoCompat)localObject).setBoundsInScreen(INVALID_PARENT_BOUNDS);
    ((AccessibilityNodeInfoCompat)localObject).setParent(mView);
    onPopulateNodeForVirtualView(paramInt, (AccessibilityNodeInfoCompat)localObject);
    if ((((AccessibilityNodeInfoCompat)localObject).getText() == null) && (((AccessibilityNodeInfoCompat)localObject).getContentDescription() == null)) {
      throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
    }
    ((AccessibilityNodeInfoCompat)localObject).getBoundsInParent(mTempParentRect);
    if (!mTempParentRect.equals(INVALID_PARENT_BOUNDS))
    {
      int i = ((AccessibilityNodeInfoCompat)localObject).getActions();
      if ((i & 0x40) == 0)
      {
        if ((i & 0x80) == 0)
        {
          ((AccessibilityNodeInfoCompat)localObject).setPackageName(mView.getContext().getPackageName());
          ((AccessibilityNodeInfoCompat)localObject).setParent(mView, paramInt);
          if (mManager == paramInt)
          {
            ((AccessibilityNodeInfoCompat)localObject).setAccessibilityFocused(true);
            ((AccessibilityNodeInfoCompat)localObject).addAction(128);
          }
          else
          {
            ((AccessibilityNodeInfoCompat)localObject).setAccessibilityFocused(false);
            ((AccessibilityNodeInfoCompat)localObject).addAction(64);
          }
          boolean bool;
          if (mFocusedVirtualViewId == paramInt) {
            bool = true;
          } else {
            bool = false;
          }
          if (bool) {
            ((AccessibilityNodeInfoCompat)localObject).addAction(2);
          } else if (((AccessibilityNodeInfoCompat)localObject).isFocusable()) {
            ((AccessibilityNodeInfoCompat)localObject).addAction(1);
          }
          ((AccessibilityNodeInfoCompat)localObject).setFocused(bool);
          mView.getLocationOnScreen(mTempGlobalRect);
          ((AccessibilityNodeInfoCompat)localObject).getBoundsInScreen(mTempScreenRect);
          if (mTempScreenRect.equals(INVALID_PARENT_BOUNDS))
          {
            ((AccessibilityNodeInfoCompat)localObject).getBoundsInParent(mTempScreenRect);
            if (height != -1)
            {
              AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain();
              for (paramInt = height; paramInt != -1; paramInt = height)
              {
                localAccessibilityNodeInfoCompat.obtain(mView, -1);
                localAccessibilityNodeInfoCompat.setBoundsInParent(INVALID_PARENT_BOUNDS);
                onPopulateNodeForVirtualView(paramInt, localAccessibilityNodeInfoCompat);
                localAccessibilityNodeInfoCompat.getBoundsInParent(mTempParentRect);
                Rect localRect1 = mTempScreenRect;
                Rect localRect2 = mTempParentRect;
                localRect1.offset(left, top);
              }
              localAccessibilityNodeInfoCompat.setParent();
            }
            mTempScreenRect.offset(mTempGlobalRect[0] - mView.getScrollX(), mTempGlobalRect[1] - mView.getScrollY());
          }
          if (mView.getLocalVisibleRect(mTempVisibleRect))
          {
            mTempVisibleRect.offset(mTempGlobalRect[0] - mView.getScrollX(), mTempGlobalRect[1] - mView.getScrollY());
            if (mTempScreenRect.intersect(mTempVisibleRect))
            {
              ((AccessibilityNodeInfoCompat)localObject).setBoundsInScreen(mTempScreenRect);
              if (intersectVisibleToUser(mTempScreenRect))
              {
                ((AccessibilityNodeInfoCompat)localObject).setVisibleToUser(true);
                return localObject;
              }
            }
          }
        }
        else
        {
          throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
      }
      else {
        throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
      }
    }
    else
    {
      localObject = new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
      throw ((Throwable)localObject);
    }
    return localObject;
  }
  
  private boolean intersectVisibleToUser(Rect paramRect)
  {
    if (paramRect != null)
    {
      if (paramRect.isEmpty()) {
        return false;
      }
      if (mView.getWindowVisibility() != 0) {
        return false;
      }
      paramRect = mView.getParent();
      while ((paramRect instanceof View))
      {
        paramRect = (View)paramRect;
        if (paramRect.getAlpha() > 0.0F)
        {
          if (paramRect.getVisibility() != 0) {
            return false;
          }
          paramRect = paramRect.getParent();
        }
        else
        {
          return false;
        }
      }
      if (paramRect != null) {
        return true;
      }
    }
    return false;
  }
  
  private boolean manageFocusForChild(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    if (paramInt2 != 1)
    {
      if (paramInt2 != 2)
      {
        if (paramInt2 != 64)
        {
          if (paramInt2 != 128) {
            return onPerformActionForVirtualView(paramInt1, paramInt2, paramBundle);
          }
          return sendEventForVirtualView(paramInt1);
        }
        return requestAccessibilityFocus(paramInt1);
      }
      return isAccessibilityFocused(paramInt1);
    }
    return clearAccessibilityFocus(paramInt1);
  }
  
  private boolean performActionForHost(int paramInt, Bundle paramBundle)
  {
    return ViewCompat.performAccessibilityAction(mView, paramInt, paramBundle);
  }
  
  private boolean requestAccessibilityFocus(int paramInt)
  {
    if (mAccessibilityManager.isEnabled())
    {
      if (!mAccessibilityManager.isTouchExplorationEnabled()) {
        return false;
      }
      int i = mManager;
      if (i != paramInt)
      {
        if (i != Integer.MIN_VALUE) {
          sendEventForVirtualView(i);
        }
        mManager = paramInt;
        mView.invalidate();
        sendEventForVirtualView(paramInt, 32768);
        return true;
      }
    }
    return false;
  }
  
  private boolean sendEventForVirtualView(int paramInt)
  {
    if (mManager == paramInt)
    {
      mManager = Integer.MIN_VALUE;
      mView.invalidate();
      sendEventForVirtualView(paramInt, 65536);
      return true;
    }
    return false;
  }
  
  private AccessibilityNodeInfoCompat update()
  {
    AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain(mView);
    ViewCompat.onInitializeAccessibilityNodeInfo(mView, localAccessibilityNodeInfoCompat);
    ArrayList localArrayList = new ArrayList();
    getVisibleVirtualViews(localArrayList);
    if ((localAccessibilityNodeInfoCompat.setEnabled() > 0) && (localArrayList.size() > 0)) {
      throw new RuntimeException("Views cannot have both real and virtual children");
    }
    int i = 0;
    int j = localArrayList.size();
    while (i < j)
    {
      localAccessibilityNodeInfoCompat.addChild(mView, ((Integer)localArrayList.get(i)).intValue());
      i += 1;
    }
    return localAccessibilityNodeInfoCompat;
  }
  
  private void updateHoveredVirtualView(int paramInt)
  {
    if (mHoveredVirtualViewId == paramInt) {
      return;
    }
    int i = mHoveredVirtualViewId;
    mHoveredVirtualViewId = paramInt;
    sendEventForVirtualView(paramInt, 128);
    sendEventForVirtualView(i, 256);
  }
  
  protected void addChildrenForAccessibility(AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat) {}
  
  public final boolean clearAccessibilityFocus(int paramInt)
  {
    if ((!mView.isFocused()) && (!mView.requestFocus())) {
      return false;
    }
    int i = mFocusedVirtualViewId;
    if (i == paramInt) {
      return false;
    }
    if (i != Integer.MIN_VALUE) {
      isAccessibilityFocused(i);
    }
    mFocusedVirtualViewId = paramInt;
    createEvent(paramInt, true);
    sendEventForVirtualView(paramInt, 8);
    return true;
  }
  
  protected void createEvent(int paramInt, boolean paramBoolean) {}
  
  public final boolean dispatchHoverEvent(MotionEvent paramMotionEvent)
  {
    if (mAccessibilityManager.isEnabled())
    {
      if (!mAccessibilityManager.isTouchExplorationEnabled()) {
        return false;
      }
      int i = paramMotionEvent.getAction();
      if ((i != 7) && (i != 9))
      {
        if (i != 10) {
          return false;
        }
        if (mHoveredVirtualViewId != Integer.MIN_VALUE)
        {
          updateHoveredVirtualView(Integer.MIN_VALUE);
          return true;
        }
        return false;
      }
      i = getVirtualViewAt(paramMotionEvent.getX(), paramMotionEvent.getY());
      updateHoveredVirtualView(i);
      if (i != Integer.MIN_VALUE) {
        return true;
      }
    }
    return false;
  }
  
  public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View paramView)
  {
    if (mNodeProvider == null) {
      mNodeProvider = new ExploreByTouchNodeProvider();
    }
    return mNodeProvider;
  }
  
  public int getFocusedVirtualView()
  {
    return sendEventForVirtualView();
  }
  
  protected abstract int getVirtualViewAt(float paramFloat1, float paramFloat2);
  
  protected abstract void getVisibleVirtualViews(List paramList);
  
  public final void invalidateRoot()
  {
    show(-1, 1);
  }
  
  public final boolean isAccessibilityFocused(int paramInt)
  {
    if (mFocusedVirtualViewId != paramInt) {
      return false;
    }
    mFocusedVirtualViewId = Integer.MIN_VALUE;
    createEvent(paramInt, false);
    sendEventForVirtualView(paramInt, 8);
    return true;
  }
  
  AccessibilityNodeInfoCompat obtain(int paramInt)
  {
    if (paramInt == -1) {
      return update();
    }
    return createNodeForChild(paramInt);
  }
  
  public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
    onInitializeAccessibilityEvent(paramAccessibilityEvent);
  }
  
  protected void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {}
  
  public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
  {
    super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
    addChildrenForAccessibility(paramAccessibilityNodeInfoCompat);
  }
  
  protected abstract boolean onPerformActionForVirtualView(int paramInt1, int paramInt2, Bundle paramBundle);
  
  protected abstract void onPopulateEventForVirtualView(int paramInt, AccessibilityEvent paramAccessibilityEvent);
  
  protected abstract void onPopulateNodeForVirtualView(int paramInt, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat);
  
  boolean performAction(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    if (paramInt1 != -1) {
      return manageFocusForChild(paramInt1, paramInt2, paramBundle);
    }
    return performActionForHost(paramInt2, paramBundle);
  }
  
  public final int sendEventForVirtualView()
  {
    return mManager;
  }
  
  public final boolean sendEventForVirtualView(int paramInt1, int paramInt2)
  {
    if (paramInt1 != Integer.MIN_VALUE)
    {
      if (!mAccessibilityManager.isEnabled()) {
        return false;
      }
      ViewParent localViewParent = mView.getParent();
      if (localViewParent == null) {
        return false;
      }
      AccessibilityEvent localAccessibilityEvent = createEvent(paramInt1, paramInt2);
      return ViewParentCompat.requestSendAccessibilityEvent(localViewParent, mView, localAccessibilityEvent);
    }
    return false;
  }
  
  public final void show(int paramInt1, int paramInt2)
  {
    if ((paramInt1 != Integer.MIN_VALUE) && (mAccessibilityManager.isEnabled()))
    {
      ViewParent localViewParent = mView.getParent();
      if (localViewParent != null)
      {
        AccessibilityEvent localAccessibilityEvent = createEvent(paramInt1, 2048);
        AccessibilityEventCompat.setContentChangeTypes(localAccessibilityEvent, paramInt2);
        ViewParentCompat.requestSendAccessibilityEvent(localViewParent, mView, localAccessibilityEvent);
      }
    }
  }
  
  class ExploreByTouchNodeProvider
    extends AccessibilityNodeProviderCompat
  {
    ExploreByTouchNodeProvider() {}
    
    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int paramInt)
    {
      return AccessibilityNodeInfoCompat.obtain(obtain(paramInt));
    }
    
    public AccessibilityNodeInfoCompat performAction(int paramInt)
    {
      if (paramInt == 2) {
        paramInt = mManager;
      } else {
        paramInt = mFocusedVirtualViewId;
      }
      if (paramInt == Integer.MIN_VALUE) {
        return null;
      }
      return createAccessibilityNodeInfo(paramInt);
    }
    
    public boolean performAction(int paramInt1, int paramInt2, Bundle paramBundle)
    {
      return ExploreByTouchHelper.this.performAction(paramInt1, paramInt2, paramBundle);
    }
  }
}
