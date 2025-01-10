package android.support.design.internal;

import android.content.Context;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.view.menu.f;

public class NavigationSubMenu
  extends SubMenuBuilder
{
  public NavigationSubMenu(Context paramContext, a paramA, MenuItemImpl paramMenuItemImpl)
  {
    super(paramContext, paramA, paramMenuItemImpl);
  }
  
  public void onItemsChanged(boolean paramBoolean)
  {
    super.onItemsChanged(paramBoolean);
    ((f)getParentMenu()).onItemsChanged(paramBoolean);
  }
}
