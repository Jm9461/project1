package android.support.v7.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public abstract class b
  implements l
{
  private l.a d;
  protected Context mContext;
  private int mId;
  private int mItemLayoutRes;
  protected f mMenu;
  private int mMenuLayoutRes;
  protected MenuView mMenuView;
  protected Context mSystemContext;
  protected LayoutInflater mSystemInflater;
  
  public b(Context paramContext, int paramInt1, int paramInt2)
  {
    mSystemContext = paramContext;
    mSystemInflater = LayoutInflater.from(paramContext);
    mMenuLayoutRes = paramInt1;
    mItemLayoutRes = paramInt2;
  }
  
  public void a(f paramF, boolean paramBoolean)
  {
    l.a localA = d;
    if (localA != null) {
      localA.onCloseMenu(paramF, paramBoolean);
    }
  }
  
  public void a(l.a paramA)
  {
    d = paramA;
  }
  
  public boolean a(SubMenuBuilder paramSubMenuBuilder)
  {
    l.a localA = d;
    if (localA != null) {
      return localA.onOpenSubMenu(paramSubMenuBuilder);
    }
    return false;
  }
  
  protected void addItemView(View paramView, int paramInt)
  {
    ViewGroup localViewGroup = (ViewGroup)paramView.getParent();
    if (localViewGroup != null) {
      localViewGroup.removeView(paramView);
    }
    ((ViewGroup)mMenuView).addView(paramView, paramInt);
  }
  
  public abstract void bindItemView(MenuItemImpl paramMenuItemImpl, MenuView.ItemView paramItemView);
  
  public boolean collapseItemActionView(f paramF, MenuItemImpl paramMenuItemImpl)
  {
    return false;
  }
  
  public MenuView.ItemView createItemView(ViewGroup paramViewGroup)
  {
    return (MenuView.ItemView)mSystemInflater.inflate(mItemLayoutRes, paramViewGroup, false);
  }
  
  public boolean expandItemActionView(f paramF, MenuItemImpl paramMenuItemImpl)
  {
    return false;
  }
  
  protected boolean filterLeftoverView(ViewGroup paramViewGroup, int paramInt)
  {
    paramViewGroup.removeViewAt(paramInt);
    return true;
  }
  
  public l.a getCallback()
  {
    return d;
  }
  
  public int getId()
  {
    return mId;
  }
  
  public View getItemView(MenuItemImpl paramMenuItemImpl, View paramView, ViewGroup paramViewGroup)
  {
    if ((paramView instanceof MenuView.ItemView)) {
      paramView = (MenuView.ItemView)paramView;
    } else {
      paramView = createItemView(paramViewGroup);
    }
    bindItemView(paramMenuItemImpl, paramView);
    return (View)paramView;
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup)
  {
    if (mMenuView == null)
    {
      mMenuView = ((MenuView)mSystemInflater.inflate(mMenuLayoutRes, paramViewGroup, false));
      mMenuView.initialize(mMenu);
      updateMenuView(true);
    }
    return mMenuView;
  }
  
  public void initForMenu(Context paramContext, f paramF)
  {
    mContext = paramContext;
    LayoutInflater.from(mContext);
    mMenu = paramF;
  }
  
  public void setId(int paramInt)
  {
    mId = paramInt;
  }
  
  public abstract boolean shouldIncludeItem(int paramInt, MenuItemImpl paramMenuItemImpl);
  
  public void updateMenuView(boolean paramBoolean)
  {
    ViewGroup localViewGroup = (ViewGroup)mMenuView;
    if (localViewGroup == null) {
      return;
    }
    int j = 0;
    int i = 0;
    Object localObject = mMenu;
    if (localObject != null)
    {
      ((f)localObject).flagActionItems();
      ArrayList localArrayList = mMenu.getVisibleItems();
      int m = localArrayList.size();
      int k = 0;
      for (;;)
      {
        j = i;
        if (k >= m) {
          break;
        }
        MenuItemImpl localMenuItemImpl = (MenuItemImpl)localArrayList.get(k);
        j = i;
        if (shouldIncludeItem(i, localMenuItemImpl))
        {
          View localView1 = localViewGroup.getChildAt(i);
          if ((localView1 instanceof MenuView.ItemView)) {
            localObject = ((MenuView.ItemView)localView1).getItemData();
          } else {
            localObject = null;
          }
          View localView2 = getItemView(localMenuItemImpl, localView1, localViewGroup);
          if (localMenuItemImpl != localObject)
          {
            localView2.setPressed(false);
            localView2.jumpDrawablesToCurrentState();
          }
          if (localView2 != localView1) {
            addItemView(localView2, i);
          }
          j = i + 1;
        }
        k += 1;
        i = j;
      }
    }
    while (j < localViewGroup.getChildCount()) {
      if (!filterLeftoverView(localViewGroup, j)) {
        j += 1;
      }
    }
  }
}
