package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.view.menu.f;
import android.support.v7.view.menu.l;
import android.support.v7.view.menu.l.a;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.d0;
import android.support.v7.widget.RecyclerView.g;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.org.android.R.dimen;
import org.org.android.R.layout;

public class NavigationMenuPresenter
  implements l
{
  int a;
  int b;
  private NavigationMenuView c;
  LinearLayout d;
  ColorStateList e;
  int f;
  private int g;
  NavigationMenuAdapter h;
  LayoutInflater i;
  private int j;
  private l.a k;
  ColorStateList l;
  Drawable mItemBackground;
  f mMenu;
  int mTextAppearance;
  boolean mTextAppearanceSet;
  final View.OnClickListener q = new FilenameDialog.1(this);
  
  public NavigationMenuPresenter() {}
  
  public int a()
  {
    return d.getChildCount();
  }
  
  public void a(int paramInt)
  {
    f = paramInt;
    updateMenuView(false);
  }
  
  public void a(ColorStateList paramColorStateList)
  {
    e = paramColorStateList;
    updateMenuView(false);
  }
  
  public void a(WindowInsetsCompat paramWindowInsetsCompat)
  {
    int m = paramWindowInsetsCompat.getSystemWindowInsetTop();
    if (j != m)
    {
      j = m;
      if (d.getChildCount() == 0)
      {
        NavigationMenuView localNavigationMenuView = c;
        localNavigationMenuView.setPadding(0, j, 0, localNavigationMenuView.getPaddingBottom());
      }
    }
    ViewCompat.dispatchApplyWindowInsets(d, paramWindowInsetsCompat);
  }
  
  public void a(f paramF, boolean paramBoolean)
  {
    l.a localA = k;
    if (localA != null) {
      localA.onCloseMenu(paramF, paramBoolean);
    }
  }
  
  public void a(View paramView)
  {
    d.addView(paramView);
    paramView = c;
    paramView.setPadding(0, 0, 0, paramView.getPaddingBottom());
  }
  
  public boolean a(SubMenuBuilder paramSubMenuBuilder)
  {
    return false;
  }
  
  public ColorStateList b()
  {
    return e;
  }
  
  public void b(int paramInt)
  {
    b = paramInt;
    updateMenuView(false);
  }
  
  public ColorStateList c()
  {
    return l;
  }
  
  public boolean collapseItemActionView(f paramF, MenuItemImpl paramMenuItemImpl)
  {
    return false;
  }
  
  public int d()
  {
    return f;
  }
  
  public void e(int paramInt)
  {
    g = paramInt;
  }
  
  public boolean expandItemActionView(f paramF, MenuItemImpl paramMenuItemImpl)
  {
    return false;
  }
  
  public boolean flagActionItems()
  {
    return false;
  }
  
  public int g()
  {
    return b;
  }
  
  public int getId()
  {
    return g;
  }
  
  public Drawable getItemBackground()
  {
    return mItemBackground;
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup)
  {
    if (c == null)
    {
      c = ((NavigationMenuView)i.inflate(R.layout.design_navigation_menu, paramViewGroup, false));
      if (h == null) {
        h = new NavigationMenuAdapter();
      }
      d = ((LinearLayout)i.inflate(R.layout.design_navigation_item_header, c, false));
      c.setAdapter(h);
    }
    return c;
  }
  
  public View inflateHeaderView(int paramInt)
  {
    View localView = i.inflate(paramInt, d, false);
    a(localView);
    return localView;
  }
  
  public void initForMenu(Context paramContext, f paramF)
  {
    i = LayoutInflater.from(paramContext);
    mMenu = paramF;
    a = paramContext.getResources().getDimensionPixelOffset(R.dimen.design_navigation_separator_vertical_padding);
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if ((paramParcelable instanceof Bundle))
    {
      paramParcelable = (Bundle)paramParcelable;
      Object localObject = paramParcelable.getSparseParcelableArray("android:menu:list");
      if (localObject != null) {
        c.restoreHierarchyState((SparseArray)localObject);
      }
      localObject = paramParcelable.getBundle("android:menu:adapter");
      if (localObject != null) {
        h.restoreInstanceState((Bundle)localObject);
      }
      paramParcelable = paramParcelable.getSparseParcelableArray("android:menu:header");
      if (paramParcelable != null) {
        d.restoreHierarchyState(paramParcelable);
      }
    }
  }
  
  public Parcelable onSaveInstanceState()
  {
    Bundle localBundle = new Bundle();
    if (c != null)
    {
      localObject = new SparseArray();
      c.saveHierarchyState((SparseArray)localObject);
      localBundle.putSparseParcelableArray("android:menu:list", (SparseArray)localObject);
    }
    Object localObject = h;
    if (localObject != null) {
      localBundle.putBundle("android:menu:adapter", ((NavigationMenuAdapter)localObject).createInstanceState());
    }
    if (d != null)
    {
      localObject = new SparseArray();
      d.saveHierarchyState((SparseArray)localObject);
      localBundle.putSparseParcelableArray("android:menu:header", (SparseArray)localObject);
    }
    return localBundle;
  }
  
  public void setCheckedItem(MenuItemImpl paramMenuItemImpl)
  {
    h.setCheckedItem(paramMenuItemImpl);
  }
  
  public void setItemBackground(Drawable paramDrawable)
  {
    mItemBackground = paramDrawable;
    updateMenuView(false);
  }
  
  public void setItemTextAppearance(int paramInt)
  {
    mTextAppearance = paramInt;
    mTextAppearanceSet = true;
    updateMenuView(false);
  }
  
  public void setItemTextColor(ColorStateList paramColorStateList)
  {
    l = paramColorStateList;
    updateMenuView(false);
  }
  
  public void setUpdateSuspended(boolean paramBoolean)
  {
    NavigationMenuAdapter localNavigationMenuAdapter = h;
    if (localNavigationMenuAdapter != null) {
      localNavigationMenuAdapter.setUpdateSuspended(paramBoolean);
    }
  }
  
  public MenuItemImpl updateMenuView()
  {
    return h.getItem();
  }
  
  public void updateMenuView(boolean paramBoolean)
  {
    NavigationMenuAdapter localNavigationMenuAdapter = h;
    if (localNavigationMenuAdapter != null) {
      localNavigationMenuAdapter.update();
    }
  }
  
  class NavigationMenuAdapter
    extends RecyclerView.g<c.k>
  {
    private MenuItemImpl mCheckedItem;
    private final ArrayList<c.e> mItems = new ArrayList();
    private boolean mUpdateSuspended;
    
    NavigationMenuAdapter()
    {
      prepareMenuItems();
    }
    
    private void appendTransparentIconIfMissing(int paramInt1, int paramInt2)
    {
      while (paramInt1 < paramInt2)
      {
        mItems.get(paramInt1)).mSelected = true;
        paramInt1 += 1;
      }
    }
    
    private void prepareMenuItems()
    {
      if (mUpdateSuspended) {
        return;
      }
      mUpdateSuspended = true;
      mItems.clear();
      mItems.add(new NavigationMenuPresenter.NavigationMenuHeaderItem());
      int n = -1;
      int j = 0;
      boolean bool2 = false;
      int m = 0;
      int i2 = mMenu.getVisibleItems().size();
      for (;;)
      {
        boolean bool1 = false;
        if (m >= i2) {
          break;
        }
        Object localObject1 = (MenuItemImpl)mMenu.getVisibleItems().get(m);
        if (((MenuItemImpl)localObject1).isChecked()) {
          setCheckedItem((MenuItemImpl)localObject1);
        }
        if (((MenuItemImpl)localObject1).isCheckable()) {
          ((MenuItemImpl)localObject1).setExclusiveCheckable(false);
        }
        Object localObject2;
        int i;
        int k;
        if (((MenuItemImpl)localObject1).hasSubMenu())
        {
          localObject2 = ((MenuItemImpl)localObject1).getSubMenu();
          if (((Menu)localObject2).hasVisibleItems())
          {
            if (m != 0) {
              mItems.add(new NavigationMenuPresenter.NavigationMenuSeparatorItem(a, 0));
            }
            mItems.add(new NavigationMenuPresenter.NavigationMenuTextItem((MenuItemImpl)localObject1));
            i = 0;
            int i3 = mItems.size();
            int i1 = 0;
            int i4 = ((Menu)localObject2).size();
            while (i1 < i4)
            {
              MenuItemImpl localMenuItemImpl = (MenuItemImpl)((Menu)localObject2).getItem(i1);
              k = i;
              if (localMenuItemImpl.isVisible())
              {
                k = i;
                if (i == 0)
                {
                  k = i;
                  if (localMenuItemImpl.getIcon() != null) {
                    k = 1;
                  }
                }
                if (localMenuItemImpl.isCheckable()) {
                  localMenuItemImpl.setExclusiveCheckable(false);
                }
                if (((MenuItemImpl)localObject1).isChecked()) {
                  setCheckedItem((MenuItemImpl)localObject1);
                }
                mItems.add(new NavigationMenuPresenter.NavigationMenuTextItem(localMenuItemImpl));
              }
              i1 += 1;
              i = k;
            }
            if (i != 0) {
              appendTransparentIconIfMissing(i3, mItems.size());
            }
          }
          bool1 = bool2;
          i = j;
        }
        else
        {
          k = ((MenuItemImpl)localObject1).getGroupId();
          if (k != n)
          {
            j = mItems.size();
            i = j;
            if (((MenuItemImpl)localObject1).getIcon() != null) {
              bool1 = true;
            }
            bool2 = bool1;
            bool1 = bool2;
            if (m != 0)
            {
              i = j + 1;
              localObject2 = mItems;
              j = a;
              ((ArrayList)localObject2).add(new NavigationMenuPresenter.NavigationMenuSeparatorItem(j, j));
              bool1 = bool2;
            }
          }
          else
          {
            bool1 = bool2;
            i = j;
            if (!bool2)
            {
              bool1 = bool2;
              i = j;
              if (((MenuItemImpl)localObject1).getIcon() != null)
              {
                bool1 = true;
                appendTransparentIconIfMissing(j, mItems.size());
                i = j;
              }
            }
          }
          localObject1 = new NavigationMenuPresenter.NavigationMenuTextItem((MenuItemImpl)localObject1);
          mSelected = bool1;
          mItems.add(localObject1);
          n = k;
        }
        m += 1;
        bool2 = bool1;
        j = i;
      }
      mUpdateSuspended = false;
    }
    
    public int a()
    {
      return mItems.size();
    }
    
    public long a(int paramInt)
    {
      return paramInt;
    }
    
    public c a(ViewGroup paramViewGroup, int paramInt)
    {
      if (paramInt != 0)
      {
        if (paramInt != 1)
        {
          if (paramInt != 2)
          {
            if (paramInt != 3) {
              return null;
            }
            return new AtomicBoolean(d);
          }
          return new i(i, paramViewGroup);
        }
        return new Type(i, paramViewGroup);
      }
      NavigationMenuPresenter localNavigationMenuPresenter = NavigationMenuPresenter.this;
      return new o(i, paramViewGroup, q);
    }
    
    public Bundle createInstanceState()
    {
      Bundle localBundle = new Bundle();
      Object localObject = mCheckedItem;
      if (localObject != null) {
        localBundle.putInt("android:menu:checked", ((MenuItemImpl)localObject).getItemId());
      }
      SparseArray localSparseArray = new SparseArray();
      int i = 0;
      int j = mItems.size();
      while (i < j)
      {
        localObject = (NavigationMenuPresenter.NavigationMenuItem)mItems.get(i);
        if ((localObject instanceof NavigationMenuPresenter.NavigationMenuTextItem))
        {
          MenuItemImpl localMenuItemImpl = ((NavigationMenuPresenter.NavigationMenuTextItem)localObject).getMenuItem();
          if (localMenuItemImpl != null) {
            localObject = localMenuItemImpl.getActionView();
          } else {
            localObject = null;
          }
          if (localObject != null)
          {
            ParcelableSparseArray localParcelableSparseArray = new ParcelableSparseArray();
            ((View)localObject).saveHierarchyState(localParcelableSparseArray);
            localSparseArray.put(localMenuItemImpl.getItemId(), localParcelableSparseArray);
          }
        }
        i += 1;
      }
      localBundle.putSparseParcelableArray("android:menu:action_views", localSparseArray);
      return localBundle;
    }
    
    public MenuItemImpl getItem()
    {
      return mCheckedItem;
    }
    
    public int getItemViewType(int paramInt)
    {
      NavigationMenuPresenter.NavigationMenuItem localNavigationMenuItem = (NavigationMenuPresenter.NavigationMenuItem)mItems.get(paramInt);
      if ((localNavigationMenuItem instanceof NavigationMenuPresenter.NavigationMenuSeparatorItem)) {
        return 2;
      }
      if ((localNavigationMenuItem instanceof NavigationMenuPresenter.NavigationMenuHeaderItem)) {
        return 3;
      }
      if ((localNavigationMenuItem instanceof NavigationMenuPresenter.NavigationMenuTextItem))
      {
        if (((NavigationMenuPresenter.NavigationMenuTextItem)localNavigationMenuItem).getMenuItem().hasSubMenu()) {
          return 1;
        }
        return 0;
      }
      throw new RuntimeException("Unknown item type.");
    }
    
    public void onBindViewHolder(c paramC, int paramInt)
    {
      int i = getItemViewType(paramInt);
      if (i != 0)
      {
        if (i != 1)
        {
          if (i != 2) {
            return;
          }
          localObject = (NavigationMenuPresenter.NavigationMenuSeparatorItem)mItems.get(paramInt);
          itemView.setPadding(0, ((NavigationMenuPresenter.NavigationMenuSeparatorItem)localObject).getPaddingTop(), 0, ((NavigationMenuPresenter.NavigationMenuSeparatorItem)localObject).getPaddingBottom());
          return;
        }
        ((TextView)itemView).setText(((NavigationMenuPresenter.NavigationMenuTextItem)mItems.get(paramInt)).getMenuItem().getTitle());
        return;
      }
      Object localObject = (NavigationMenuItemView)itemView;
      ((NavigationMenuItemView)localObject).setIconTintList(e);
      paramC = NavigationMenuPresenter.this;
      if (mTextAppearanceSet) {
        ((NavigationMenuItemView)localObject).setTextAppearance(mTextAppearance);
      }
      paramC = l;
      if (paramC != null) {
        ((NavigationMenuItemView)localObject).setTextColor(paramC);
      }
      paramC = mItemBackground;
      if (paramC != null) {
        paramC = paramC.getConstantState().newDrawable();
      } else {
        paramC = null;
      }
      ViewCompat.setBackgroundDrawable((View)localObject, paramC);
      paramC = (NavigationMenuPresenter.NavigationMenuTextItem)mItems.get(paramInt);
      ((NavigationMenuItemView)localObject).setNeedsEmptyIcon(mSelected);
      ((NavigationMenuItemView)localObject).setHorizontalPadding(b);
      ((NavigationMenuItemView)localObject).setIconPadding(f);
      ((NavigationMenuItemView)localObject).initialize(paramC.getMenuItem(), 0);
    }
    
    public void onViewRecycled(c paramC)
    {
      if ((paramC instanceof o)) {
        ((NavigationMenuItemView)itemView).recycle();
      }
    }
    
    public void restoreInstanceState(Bundle paramBundle)
    {
      int j = paramBundle.getInt("android:menu:checked", 0);
      int i;
      Object localObject1;
      if (j != 0)
      {
        mUpdateSuspended = true;
        i = 0;
        int k = mItems.size();
        while (i < k)
        {
          localObject1 = (NavigationMenuPresenter.NavigationMenuItem)mItems.get(i);
          if ((localObject1 instanceof NavigationMenuPresenter.NavigationMenuTextItem))
          {
            localObject1 = ((NavigationMenuPresenter.NavigationMenuTextItem)localObject1).getMenuItem();
            if ((localObject1 != null) && (((MenuItemImpl)localObject1).getItemId() == j))
            {
              setCheckedItem((MenuItemImpl)localObject1);
              break;
            }
          }
          i += 1;
        }
        mUpdateSuspended = false;
        prepareMenuItems();
      }
      paramBundle = paramBundle.getSparseParcelableArray("android:menu:action_views");
      if (paramBundle != null)
      {
        i = 0;
        j = mItems.size();
        while (i < j)
        {
          localObject1 = (NavigationMenuPresenter.NavigationMenuItem)mItems.get(i);
          if ((localObject1 instanceof NavigationMenuPresenter.NavigationMenuTextItem))
          {
            Object localObject2 = ((NavigationMenuPresenter.NavigationMenuTextItem)localObject1).getMenuItem();
            if (localObject2 != null)
            {
              localObject1 = ((MenuItemImpl)localObject2).getActionView();
              if (localObject1 != null)
              {
                localObject2 = (ParcelableSparseArray)paramBundle.get(((MenuItemImpl)localObject2).getItemId());
                if (localObject2 != null) {
                  ((View)localObject1).restoreHierarchyState((SparseArray)localObject2);
                }
              }
            }
          }
          i += 1;
        }
      }
    }
    
    public void setCheckedItem(MenuItemImpl paramMenuItemImpl)
    {
      if (mCheckedItem != paramMenuItemImpl)
      {
        if (!paramMenuItemImpl.isCheckable()) {
          return;
        }
        MenuItemImpl localMenuItemImpl = mCheckedItem;
        if (localMenuItemImpl != null) {
          localMenuItemImpl.setChecked(false);
        }
        mCheckedItem = paramMenuItemImpl;
        paramMenuItemImpl.setChecked(true);
      }
    }
    
    public void setUpdateSuspended(boolean paramBoolean)
    {
      mUpdateSuspended = paramBoolean;
    }
    
    public void update()
    {
      prepareMenuItems();
      clear();
    }
  }
  
  class NavigationMenuHeaderItem
    implements NavigationMenuPresenter.NavigationMenuItem
  {
    NavigationMenuHeaderItem() {}
  }
  
  abstract interface NavigationMenuItem {}
  
  class NavigationMenuSeparatorItem
    implements NavigationMenuPresenter.NavigationMenuItem
  {
    private final int mPaddingBottom;
    private final int mPaddingTop;
    
    public NavigationMenuSeparatorItem(int paramInt)
    {
      mPaddingTop = this$1;
      mPaddingBottom = paramInt;
    }
    
    public int getPaddingBottom()
    {
      return mPaddingBottom;
    }
    
    public int getPaddingTop()
    {
      return mPaddingTop;
    }
  }
  
  class NavigationMenuTextItem
    implements NavigationMenuPresenter.NavigationMenuItem
  {
    boolean mSelected;
    
    NavigationMenuTextItem() {}
    
    public MenuItemImpl getMenuItem()
    {
      return NavigationMenuPresenter.this;
    }
  }
}
