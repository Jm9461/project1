package android.support.v7.view.menu;

import android.content.Context;
import android.os.Parcelable;

public abstract interface l
{
  public abstract void a(f paramF, boolean paramBoolean);
  
  public abstract void a(a paramA);
  
  public abstract boolean a(SubMenuBuilder paramSubMenuBuilder);
  
  public abstract boolean collapseItemActionView(f paramF, MenuItemImpl paramMenuItemImpl);
  
  public abstract boolean expandItemActionView(f paramF, MenuItemImpl paramMenuItemImpl);
  
  public abstract boolean flagActionItems();
  
  public abstract int getId();
  
  public abstract void initForMenu(Context paramContext, f paramF);
  
  public abstract void onRestoreInstanceState(Parcelable paramParcelable);
  
  public abstract Parcelable onSaveInstanceState();
  
  public abstract void updateMenuView(boolean paramBoolean);
  
  public abstract interface a
  {
    public abstract void onCloseMenu(f paramF, boolean paramBoolean);
    
    public abstract boolean onOpenSubMenu(f paramF);
  }
}
