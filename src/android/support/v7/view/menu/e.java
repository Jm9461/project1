package android.support.v7.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import org.org.v4.content.R.layout;

public class e
  implements l, AdapterView.OnItemClickListener
{
  Context a;
  LayoutInflater b;
  private l.a d;
  int e;
  int f;
  private int g;
  ListMenuPresenter.MenuAdapter mAdapter;
  int mExpandedIndex;
  f mMenu;
  ExpandedMenuView mMenuView;
  
  public e(int paramInt1, int paramInt2)
  {
    f = paramInt1;
    e = paramInt2;
  }
  
  public e(Context paramContext, int paramInt)
  {
    this(paramInt, 0);
    a = paramContext;
    b = LayoutInflater.from(a);
  }
  
  public ListAdapter a()
  {
    if (mAdapter == null) {
      mAdapter = new ListMenuPresenter.MenuAdapter(this);
    }
    return mAdapter;
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
    if (!paramSubMenuBuilder.hasVisibleItems()) {
      return false;
    }
    new g(paramSubMenuBuilder).a(null);
    l.a localA = d;
    if (localA != null) {
      localA.onOpenSubMenu(paramSubMenuBuilder);
    }
    return true;
  }
  
  public void b(Bundle paramBundle)
  {
    paramBundle = paramBundle.getSparseParcelableArray("android:menu:list");
    if (paramBundle != null) {
      mMenuView.restoreHierarchyState(paramBundle);
    }
  }
  
  public boolean collapseItemActionView(f paramF, MenuItemImpl paramMenuItemImpl)
  {
    return false;
  }
  
  public boolean expandItemActionView(f paramF, MenuItemImpl paramMenuItemImpl)
  {
    return false;
  }
  
  public boolean flagActionItems()
  {
    return false;
  }
  
  public int getId()
  {
    return g;
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup)
  {
    if (mMenuView == null)
    {
      mMenuView = ((ExpandedMenuView)b.inflate(R.layout.abc_expanded_menu_layout, paramViewGroup, false));
      if (mAdapter == null) {
        mAdapter = new ListMenuPresenter.MenuAdapter(this);
      }
      mMenuView.setAdapter(mAdapter);
      mMenuView.setOnItemClickListener(this);
    }
    return mMenuView;
  }
  
  public void initForMenu(Context paramContext, f paramF)
  {
    int i = e;
    if (i != 0)
    {
      a = new ContextThemeWrapper(paramContext, i);
      b = LayoutInflater.from(a);
    }
    else if (a != null)
    {
      a = paramContext;
      if (b == null) {
        b = LayoutInflater.from(a);
      }
    }
    mMenu = paramF;
    paramContext = mAdapter;
    if (paramContext != null) {
      paramContext.notifyDataSetChanged();
    }
  }
  
  public void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    mMenu.performItemAction(mAdapter.getItem(paramInt), this, 0);
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    b((Bundle)paramParcelable);
  }
  
  public Parcelable onSaveInstanceState()
  {
    if (mMenuView == null) {
      return null;
    }
    Bundle localBundle = new Bundle();
    onSaveInstanceState(localBundle);
    return localBundle;
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    SparseArray localSparseArray = new SparseArray();
    ExpandedMenuView localExpandedMenuView = mMenuView;
    if (localExpandedMenuView != null) {
      localExpandedMenuView.saveHierarchyState(localSparseArray);
    }
    paramBundle.putSparseParcelableArray("android:menu:list", localSparseArray);
  }
  
  public void updateMenuView(boolean paramBoolean)
  {
    ListMenuPresenter.MenuAdapter localMenuAdapter = mAdapter;
    if (localMenuAdapter != null) {
      localMenuAdapter.notifyDataSetChanged();
    }
  }
}
