package android.support.design.internal;

import android.content.Context;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.f;
import android.view.SubMenu;

public class a
  extends f
{
  public a(Context paramContext)
  {
    super(paramContext);
  }
  
  public SubMenu addSubMenu(int paramInt1, int paramInt2, int paramInt3, CharSequence paramCharSequence)
  {
    paramCharSequence = (MenuItemImpl)addInternal(paramInt1, paramInt2, paramInt3, paramCharSequence);
    NavigationSubMenu localNavigationSubMenu = new NavigationSubMenu(getContext(), this, paramCharSequence);
    paramCharSequence.setSubMenu(localNavigationSubMenu);
    return localNavigationSubMenu;
  }
}
