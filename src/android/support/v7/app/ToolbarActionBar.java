package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.menu.f;
import android.support.v7.view.menu.l.a;
import android.support.v7.widget.DecorToolbar;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.f;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window.Callback;
import java.util.ArrayList;
import org.org.v4.view.WindowCallbackWrapper;

class ToolbarActionBar
  extends ActionBar
{
  DecorToolbar mDecorToolbar;
  private boolean mLastMenuVisibility;
  private boolean mMenuCallbackSet;
  private final Toolbar.f mMenuClicker = new MainActivity.2(this);
  private final Runnable mMenuInvalidator = new EventInfoFragment.1(this);
  private ArrayList<a.b> mMenuVisibilityListeners = new ArrayList();
  boolean mOptionsMenuInvalidated;
  Window.Callback mWindowCallback;
  
  ToolbarActionBar(Toolbar paramToolbar, CharSequence paramCharSequence, Window.Callback paramCallback)
  {
    mDecorToolbar = new ToolbarWidgetWrapper(paramToolbar, false);
    mWindowCallback = new ToolbarCallbackWrapper(paramCallback);
    mDecorToolbar.setWindowCallback(mWindowCallback);
    paramToolbar.setOnMenuItemClickListener(mMenuClicker);
    mDecorToolbar.setWindowTitle(paramCharSequence);
  }
  
  private Menu getMenu()
  {
    if (!mMenuCallbackSet)
    {
      mDecorToolbar.setMenuCallbacks(new ActionMenuPresenterCallback(), new ActionMenuView.MenuBuilderCallback(this));
      mMenuCallbackSet = true;
    }
    return mDecorToolbar.getMenu();
  }
  
  public boolean collapseActionView()
  {
    if (mDecorToolbar.hasExpandedActionView())
    {
      mDecorToolbar.collapseActionView();
      return true;
    }
    return false;
  }
  
  public void dispatchMenuVisibilityChanged(boolean paramBoolean)
  {
    if (paramBoolean == mLastMenuVisibility) {
      return;
    }
    mLastMenuVisibility = paramBoolean;
    int j = mMenuVisibilityListeners.size();
    int i = 0;
    while (i < j)
    {
      ((ActionBar.OnMenuVisibilityListener)mMenuVisibilityListeners.get(i)).onMenuVisibilityChanged(paramBoolean);
      i += 1;
    }
  }
  
  public int getDisplayOptions()
  {
    return mDecorToolbar.getDisplayOptions();
  }
  
  public Context getThemedContext()
  {
    return mDecorToolbar.getContext();
  }
  
  public Window.Callback getWrappedWindowCallback()
  {
    return mWindowCallback;
  }
  
  public boolean invalidateOptionsMenu()
  {
    mDecorToolbar.getViewGroup().removeCallbacks(mMenuInvalidator);
    ViewCompat.postOnAnimation(mDecorToolbar.getViewGroup(), mMenuInvalidator);
    return true;
  }
  
  public boolean isShowing()
  {
    return mDecorToolbar.hideOverflowMenu();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  void onDestroy()
  {
    mDecorToolbar.getViewGroup().removeCallbacks(mMenuInvalidator);
  }
  
  public boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent)
  {
    Menu localMenu = getMenu();
    if (localMenu != null)
    {
      if (paramKeyEvent != null) {
        i = paramKeyEvent.getDeviceId();
      } else {
        i = -1;
      }
      int i = KeyCharacterMap.load(i).getKeyboardType();
      boolean bool = true;
      if (i == 1) {
        bool = false;
      }
      localMenu.setQwertyMode(bool);
      return localMenu.performShortcut(paramInt, paramKeyEvent, 0);
    }
    return false;
  }
  
  public boolean onKeyShortcut(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 1) {
      openOptionsMenu();
    }
    return true;
  }
  
  public boolean openOptionsMenu()
  {
    return mDecorToolbar.showOverflowMenu();
  }
  
  void populateOptionsMenu()
  {
    Menu localMenu = getMenu();
    f localF;
    if ((localMenu instanceof f)) {
      localF = (f)localMenu;
    } else {
      localF = null;
    }
    if (localF != null) {
      localF.stopDispatchingItemsChanged();
    }
    try
    {
      localMenu.clear();
      boolean bool = mWindowCallback.onCreatePanelMenu(0, localMenu);
      if (bool)
      {
        bool = mWindowCallback.onPreparePanel(0, null, localMenu);
        if (bool) {}
      }
      else
      {
        localMenu.clear();
      }
      if (localF != null)
      {
        localF.startDispatchingItemsChanged();
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      if (localF != null) {
        localF.startDispatchingItemsChanged();
      }
      throw localThrowable;
    }
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    mDecorToolbar.setBackgroundDrawable(paramDrawable);
  }
  
  public void setDefaultDisplayHomeAsUpEnabled(boolean paramBoolean) {}
  
  public void setDisplayHomeAsUpEnabled(boolean paramBoolean)
  {
    int i;
    if (paramBoolean) {
      i = 4;
    } else {
      i = 0;
    }
    setDisplayOptions(i, 4);
  }
  
  public void setDisplayOptions(int paramInt1, int paramInt2)
  {
    int i = mDecorToolbar.getDisplayOptions();
    mDecorToolbar.setDisplayOptions(paramInt1 & paramInt2 | paramInt2 & i);
  }
  
  public void setDisplayShowTitleEnabled(boolean paramBoolean)
  {
    int i;
    if (paramBoolean) {
      i = 8;
    } else {
      i = 0;
    }
    setDisplayOptions(i, 8);
  }
  
  public void setElevation(float paramFloat)
  {
    ViewCompat.setElevation(mDecorToolbar.getViewGroup(), paramFloat);
  }
  
  public void setHomeActionContentDescription(int paramInt)
  {
    mDecorToolbar.setNavigationContentDescription(paramInt);
  }
  
  public void setHomeAsUpIndicator(Drawable paramDrawable)
  {
    mDecorToolbar.setNavigationIcon(paramDrawable);
  }
  
  public void setHomeButtonEnabled(boolean paramBoolean) {}
  
  public void setShowHideAnimationEnabled(boolean paramBoolean) {}
  
  public void setTitle(CharSequence paramCharSequence)
  {
    mDecorToolbar.setTitle(paramCharSequence);
  }
  
  public void setWindowTitle(CharSequence paramCharSequence)
  {
    mDecorToolbar.setWindowTitle(paramCharSequence);
  }
  
  final class ActionMenuPresenterCallback
    implements l.a
  {
    private boolean mClosingActionMenu;
    
    ActionMenuPresenterCallback() {}
    
    public void onCloseMenu(f paramF, boolean paramBoolean)
    {
      if (mClosingActionMenu) {
        return;
      }
      mClosingActionMenu = true;
      mDecorToolbar.dismissPopupMenus();
      Window.Callback localCallback = mWindowCallback;
      if (localCallback != null) {
        localCallback.onPanelClosed(108, paramF);
      }
      mClosingActionMenu = false;
    }
    
    public boolean onOpenSubMenu(f paramF)
    {
      Window.Callback localCallback = mWindowCallback;
      if (localCallback != null)
      {
        localCallback.onMenuOpened(108, paramF);
        return true;
      }
      return false;
    }
  }
  
  class ToolbarCallbackWrapper
    extends WindowCallbackWrapper
  {
    public ToolbarCallbackWrapper(Window.Callback paramCallback)
    {
      super();
    }
    
    public View onCreatePanelView(int paramInt)
    {
      if (paramInt == 0) {
        return new View(mDecorToolbar.getContext());
      }
      return super.onCreatePanelView(paramInt);
    }
    
    public boolean onPreparePanel(int paramInt, View paramView, Menu paramMenu)
    {
      boolean bool = super.onPreparePanel(paramInt, paramView, paramMenu);
      if (bool)
      {
        paramView = ToolbarActionBar.this;
        if (!mOptionsMenuInvalidated)
        {
          mDecorToolbar.setMenuPrepared();
          mOptionsMenuInvalidated = true;
        }
      }
      return bool;
    }
  }
}
