package android.support.v7.app;

import android.os.Build.VERSION;
import android.support.v7.view.menu.f;
import android.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window.Callback;
import java.util.List;
import org.org.v4.view.SupportActionModeWrapper.CallbackWrapper;

class WindowCallbackWrapper
  extends org.org.v4.view.WindowCallbackWrapper
{
  WindowCallbackWrapper(AppCompatDelegateImplV7 paramAppCompatDelegateImplV7, Window.Callback paramCallback)
  {
    super(paramCallback);
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return (this$0.dispatchKeyEvent(paramKeyEvent)) || (super.dispatchKeyEvent(paramKeyEvent));
  }
  
  public boolean dispatchKeyShortcutEvent(KeyEvent paramKeyEvent)
  {
    return (super.dispatchKeyShortcutEvent(paramKeyEvent)) || (this$0.onKeyShortcut(paramKeyEvent.getKeyCode(), paramKeyEvent));
  }
  
  public void onContentChanged() {}
  
  public boolean onCreatePanelMenu(int paramInt, Menu paramMenu)
  {
    if ((paramInt == 0) && (!(paramMenu instanceof f))) {
      return false;
    }
    return super.onCreatePanelMenu(paramInt, paramMenu);
  }
  
  public boolean onMenuOpened(int paramInt, Menu paramMenu)
  {
    super.onMenuOpened(paramInt, paramMenu);
    this$0.onMenuOpened(paramInt);
    return true;
  }
  
  public void onPanelClosed(int paramInt, Menu paramMenu)
  {
    super.onPanelClosed(paramInt, paramMenu);
    this$0.onPanelClosed(paramInt);
  }
  
  public boolean onPreparePanel(int paramInt, View paramView, Menu paramMenu)
  {
    f localF;
    if ((paramMenu instanceof f)) {
      localF = (f)paramMenu;
    } else {
      localF = null;
    }
    if ((paramInt == 0) && (localF == null)) {
      return false;
    }
    if (localF != null) {
      localF.setOverrideVisibleItems(true);
    }
    boolean bool = super.onPreparePanel(paramInt, paramView, paramMenu);
    if (localF != null) {
      localF.setOverrideVisibleItems(false);
    }
    return bool;
  }
  
  public void onProvideKeyboardShortcuts(List paramList, Menu paramMenu, int paramInt)
  {
    Object localObject = this$0.getPanelState(0, true);
    if (localObject != null)
    {
      localObject = menu;
      if (localObject != null)
      {
        super.onProvideKeyboardShortcuts(paramList, (Menu)localObject, paramInt);
        return;
      }
    }
    super.onProvideKeyboardShortcuts(paramList, paramMenu, paramInt);
  }
  
  public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback paramCallback)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return null;
    }
    if (this$0.isHandleNativeActionModesEnabled()) {
      return startAsSupportActionMode(paramCallback);
    }
    return super.onWindowStartingActionMode(paramCallback);
  }
  
  public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback paramCallback, int paramInt)
  {
    if ((this$0.isHandleNativeActionModesEnabled()) && (paramInt == 0)) {
      return startAsSupportActionMode(paramCallback);
    }
    return super.onWindowStartingActionMode(paramCallback, paramInt);
  }
  
  final android.view.ActionMode startAsSupportActionMode(ActionMode.Callback paramCallback)
  {
    paramCallback = new SupportActionModeWrapper.CallbackWrapper(this$0.mContext, paramCallback);
    org.org.v4.view.ActionMode localActionMode = this$0.startSupportActionMode(paramCallback);
    if (localActionMode != null) {
      return paramCallback.getActionModeWrapper(localActionMode);
    }
    return null;
  }
}
