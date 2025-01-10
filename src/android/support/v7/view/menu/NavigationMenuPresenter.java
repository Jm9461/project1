package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow.OnDismissListener;

abstract class NavigationMenuPresenter
  implements ListPopupWindow, l, AdapterView.OnItemClickListener
{
  private Rect j;
  
  NavigationMenuPresenter() {}
  
  protected static e.a a(ListAdapter paramListAdapter)
  {
    if ((paramListAdapter instanceof HeaderViewListAdapter)) {
      return (e.a)((HeaderViewListAdapter)paramListAdapter).getWrappedAdapter();
    }
    return (e.a)paramListAdapter;
  }
  
  protected static int measureContentWidth(ListAdapter paramListAdapter, ViewGroup paramViewGroup, Context paramContext, int paramInt)
  {
    int k = 0;
    Object localObject2 = null;
    int n = 0;
    int i2 = View.MeasureSpec.makeMeasureSpec(0, 0);
    int i3 = View.MeasureSpec.makeMeasureSpec(0, 0);
    int i4 = paramListAdapter.getCount();
    int i = 0;
    Object localObject1 = paramViewGroup;
    paramViewGroup = (ViewGroup)localObject2;
    while (i < i4)
    {
      int i1 = paramListAdapter.getItemViewType(i);
      int m = n;
      if (i1 != n)
      {
        m = i1;
        paramViewGroup = null;
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = new FrameLayout(paramContext);
      }
      localObject1 = paramListAdapter.getView(i, paramViewGroup, (ViewGroup)localObject2);
      paramViewGroup = (ViewGroup)localObject1;
      ((View)localObject1).measure(i2, i3);
      i1 = ((View)localObject1).getMeasuredWidth();
      if (i1 >= paramInt) {
        return paramInt;
      }
      n = k;
      if (i1 > k) {
        n = i1;
      }
      i += 1;
      k = n;
      n = m;
      localObject1 = localObject2;
    }
    return k;
  }
  
  protected static boolean onSubMenuSelected(f paramF)
  {
    int k = paramF.size();
    int i = 0;
    while (i < k)
    {
      MenuItem localMenuItem = paramF.getItem(i);
      if ((localMenuItem.isVisible()) && (localMenuItem.getIcon() != null)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public abstract void a(int paramInt);
  
  public abstract void a(View paramView);
  
  public abstract void a(PopupWindow.OnDismissListener paramOnDismissListener);
  
  public abstract void a(boolean paramBoolean);
  
  public abstract void c(int paramInt);
  
  public abstract void c(f paramF);
  
  public abstract void c(boolean paramBoolean);
  
  public boolean collapseItemActionView(f paramF, MenuItemImpl paramMenuItemImpl)
  {
    return false;
  }
  
  public abstract void d(int paramInt);
  
  public void d(Rect paramRect)
  {
    j = paramRect;
  }
  
  public Rect e()
  {
    return j;
  }
  
  public boolean expandItemActionView(f paramF, MenuItemImpl paramMenuItemImpl)
  {
    return false;
  }
  
  protected boolean g()
  {
    return true;
  }
  
  public int getId()
  {
    return 0;
  }
  
  public void initForMenu(Context paramContext, f paramF) {}
  
  public void onItemClick(AdapterView paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramView = (ListAdapter)paramAdapterView.getAdapter();
    paramAdapterView = ab;
    paramView = (MenuItem)paramView.getItem(paramInt);
    if (g()) {
      paramInt = 0;
    } else {
      paramInt = 4;
    }
    paramAdapterView.performItemAction(paramView, this, paramInt);
  }
}
