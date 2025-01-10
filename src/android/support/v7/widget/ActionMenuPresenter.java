package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ActionProvider.SubUiVisibilityListener;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.ActionMenuItemView.b;
import android.support.v7.view.menu.ListPopupWindow;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.MenuView.ItemView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.view.menu.b;
import android.support.v7.view.menu.f;
import android.support.v7.view.menu.l.a;
import android.support.v7.view.menu.w;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import org.org.v4.content.R.attr;
import org.org.v4.content.R.layout;
import org.org.v4.view.ActionBarPolicy;

class ActionMenuPresenter
  extends b
  implements ActionProvider.SubUiVisibilityListener
{
  private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
  ActionButtonSubmenu mActionButtonPopup;
  private int mActionItemWidthLimit;
  private boolean mExpandedActionViewsExclusive;
  private int mMaxItems;
  private boolean mMaxItemsSet;
  private int mMinCellSize;
  int mOpenSubMenuId;
  OverflowMenuButton mOverflowButton;
  OverflowPopup mOverflowPopup;
  private Drawable mPendingOverflowIcon;
  private boolean mPendingOverflowIconSet;
  private ActionMenuPopupCallback mPopupCallback;
  final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback();
  OpenOverflowRunnable mPostedOpenRunnable;
  private boolean mReserveOverflow;
  private boolean mReserveOverflowSet;
  private View mScrapActionButtonView;
  private boolean mStrictWidthLimit;
  private int mWidthLimit;
  private boolean mWidthLimitSet;
  
  public ActionMenuPresenter(Context paramContext)
  {
    super(paramContext, R.layout.abc_action_menu_layout, R.layout.abc_action_menu_item_layout);
  }
  
  private View findViewForItem(MenuItem paramMenuItem)
  {
    ViewGroup localViewGroup = (ViewGroup)mMenuView;
    if (localViewGroup == null) {
      return null;
    }
    int j = localViewGroup.getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = localViewGroup.getChildAt(i);
      if (((localView instanceof MenuView.ItemView)) && (((MenuView.ItemView)localView).getItemData() == paramMenuItem)) {
        return localView;
      }
      i += 1;
    }
    return null;
  }
  
  public void a(f paramF, boolean paramBoolean)
  {
    dismissPopupMenus();
    super.a(paramF, paramBoolean);
  }
  
  public boolean a(SubMenuBuilder paramSubMenuBuilder)
  {
    if (!paramSubMenuBuilder.hasVisibleItems()) {
      return false;
    }
    for (Object localObject = paramSubMenuBuilder; ((SubMenuBuilder)localObject).getParentMenu() != mMenu; localObject = (SubMenuBuilder)((SubMenuBuilder)localObject).getParentMenu()) {}
    localObject = findViewForItem(((SubMenuBuilder)localObject).getItem());
    if (localObject == null) {
      return false;
    }
    mOpenSubMenuId = paramSubMenuBuilder.getItem().getItemId();
    boolean bool2 = false;
    int j = paramSubMenuBuilder.size();
    int i = 0;
    boolean bool1;
    for (;;)
    {
      bool1 = bool2;
      if (i >= j) {
        break;
      }
      MenuItem localMenuItem = paramSubMenuBuilder.getItem(i);
      if ((localMenuItem.isVisible()) && (localMenuItem.getIcon() != null))
      {
        bool1 = true;
        break;
      }
      i += 1;
    }
    mActionButtonPopup = new ActionButtonSubmenu(mContext, paramSubMenuBuilder, (View)localObject);
    mActionButtonPopup.a(bool1);
    mActionButtonPopup.show();
    super.a(paramSubMenuBuilder);
    return true;
  }
  
  public void bindItemView(MenuItemImpl paramMenuItemImpl, MenuView.ItemView paramItemView)
  {
    paramItemView.initialize(paramMenuItemImpl, 0);
    paramMenuItemImpl = (ActionMenuView)mMenuView;
    paramItemView = (ActionMenuItemView)paramItemView;
    paramItemView.setItemInvoker(paramMenuItemImpl);
    if (mPopupCallback == null) {
      mPopupCallback = new ActionMenuPopupCallback();
    }
    paramItemView.setPopupCallback(mPopupCallback);
  }
  
  public boolean dismissPopupMenus()
  {
    return hideOverflowMenu() | hideSubMenus();
  }
  
  public boolean filterLeftoverView(ViewGroup paramViewGroup, int paramInt)
  {
    if (paramViewGroup.getChildAt(paramInt) == mOverflowButton) {
      return false;
    }
    return super.filterLeftoverView(paramViewGroup, paramInt);
  }
  
  public boolean flagActionItems()
  {
    Object localObject1 = mMenu;
    Object localObject2;
    int i3;
    if (localObject1 != null)
    {
      localObject2 = ((f)localObject1).getVisibleItems();
      localObject1 = localObject2;
      i3 = ((ArrayList)localObject2).size();
    }
    else
    {
      localObject1 = null;
      i3 = 0;
    }
    int i = mMaxItems;
    int i2 = mActionItemWidthLimit;
    int i6 = View.MeasureSpec.makeMeasureSpec(0, 0);
    ViewGroup localViewGroup = (ViewGroup)mMenuView;
    int m = 0;
    int n = 0;
    int i5 = 0;
    int k = 0;
    int j = 0;
    int i1;
    while (j < i3)
    {
      localObject2 = (MenuItemImpl)((ArrayList)localObject1).get(j);
      if (((MenuItemImpl)localObject2).requiresActionButton()) {
        m += 1;
      } else if (((MenuItemImpl)localObject2).requestsActionButton()) {
        n += 1;
      } else {
        k = 1;
      }
      i1 = i;
      if (mExpandedActionViewsExclusive)
      {
        i1 = i;
        if (((MenuItemImpl)localObject2).isActionViewExpanded()) {
          i1 = 0;
        }
      }
      j += 1;
      i = i1;
    }
    j = i;
    if (mReserveOverflow) {
      if (k == 0)
      {
        j = i;
        if (m + n <= i) {}
      }
      else
      {
        j = i - 1;
      }
    }
    k = j - m;
    SparseBooleanArray localSparseBooleanArray = mActionButtonGroups;
    localSparseBooleanArray.clear();
    int i4 = 0;
    j = 0;
    if (mStrictWidthLimit)
    {
      i = mMinCellSize;
      j = i2 / i;
      i4 = i + i2 % i / j;
    }
    m = 0;
    i = i5;
    i5 = m;
    for (;;)
    {
      localObject2 = this;
      if (i5 >= i3) {
        break;
      }
      MenuItemImpl localMenuItemImpl = (MenuItemImpl)((ArrayList)localObject1).get(i5);
      View localView;
      if (localMenuItemImpl.requiresActionButton())
      {
        localView = ((ActionMenuPresenter)localObject2).getItemView(localMenuItemImpl, mScrapActionButtonView, localViewGroup);
        if (mScrapActionButtonView == null) {
          mScrapActionButtonView = localView;
        }
        if (mStrictWidthLimit) {
          j -= ActionMenuView.measureChildForCells(localView, i4, j, i6, 0);
        } else {
          localView.measure(i6, i6);
        }
        i1 = localView.getMeasuredWidth();
        n = i2 - i1;
        m = i;
        if (i == 0) {
          m = i1;
        }
        i = localMenuItemImpl.getGroupId();
        if (i != 0) {
          localSparseBooleanArray.put(i, true);
        }
        localMenuItemImpl.setIsActionButton(true);
        i = m;
      }
      else if (localMenuItemImpl.requestsActionButton())
      {
        int i7 = localMenuItemImpl.getGroupId();
        boolean bool = localSparseBooleanArray.get(i7);
        if ((k <= 0) && (!bool)) {
          break label465;
        }
        int i8;
        if ((i2 > 0) && ((!mStrictWidthLimit) || (j > 0))) {
          i8 = 1;
        } else {
          label465:
          i8 = 0;
        }
        m = j;
        n = i2;
        i1 = i;
        int i9 = i8;
        if (i8 != 0)
        {
          localView = ((ActionMenuPresenter)localObject2).getItemView(localMenuItemImpl, mScrapActionButtonView, localViewGroup);
          if (mScrapActionButtonView == null) {
            mScrapActionButtonView = localView;
          }
          if (mStrictWidthLimit)
          {
            m = ActionMenuView.measureChildForCells(localView, i4, j, i6, 0);
            j -= m;
            if (m == 0) {
              i8 = 0;
            }
          }
          else
          {
            localView.measure(i6, i6);
          }
          m = localView.getMeasuredWidth();
          n = i2 - m;
          i1 = i;
          if (i == 0) {
            i1 = m;
          }
          if (mStrictWidthLimit)
          {
            if (n >= 0) {
              i = 1;
            } else {
              i = 0;
            }
            i9 = i8 & i;
            m = j;
          }
          else
          {
            if (n + i1 > 0) {
              i = 1;
            } else {
              i = 0;
            }
            i9 = i8 & i;
            m = j;
          }
        }
        if ((i9 != 0) && (i7 != 0))
        {
          localSparseBooleanArray.put(i7, true);
          i = k;
        }
        else if (bool)
        {
          localSparseBooleanArray.put(i7, false);
          j = 0;
          while (j < i5)
          {
            localObject2 = (MenuItemImpl)((ArrayList)localObject1).get(j);
            i = k;
            if (((MenuItemImpl)localObject2).getGroupId() == i7)
            {
              i = k;
              if (((MenuItemImpl)localObject2).isActionButton()) {
                i = k + 1;
              }
              ((MenuItemImpl)localObject2).setIsActionButton(false);
            }
            j += 1;
            k = i;
          }
          i = k;
        }
        else
        {
          i = k;
        }
        k = i;
        if (i9 != 0) {
          k = i - 1;
        }
        localMenuItemImpl.setIsActionButton(i9);
        j = m;
        i = i1;
      }
      else
      {
        localMenuItemImpl.setIsActionButton(false);
        n = i2;
      }
      i5 += 1;
      i2 = n;
    }
    return true;
  }
  
  public View getItemView(MenuItemImpl paramMenuItemImpl, View paramView, ViewGroup paramViewGroup)
  {
    View localView2 = paramMenuItemImpl.getActionView();
    View localView1 = localView2;
    if ((localView2 == null) || (paramMenuItemImpl.hasCollapsibleActionView())) {
      localView1 = super.getItemView(paramMenuItemImpl, paramView, paramViewGroup);
    }
    int i;
    if (paramMenuItemImpl.isActionViewExpanded()) {
      i = 8;
    } else {
      i = 0;
    }
    localView1.setVisibility(i);
    paramMenuItemImpl = (ActionMenuView)paramViewGroup;
    paramView = localView1.getLayoutParams();
    if (!paramMenuItemImpl.checkLayoutParams(paramView)) {
      localView1.setLayoutParams(paramMenuItemImpl.generateLayoutParams(paramView));
    }
    return localView1;
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup)
  {
    MenuView localMenuView = mMenuView;
    paramViewGroup = super.getMenuView(paramViewGroup);
    if (localMenuView != paramViewGroup) {
      ((ActionMenuView)paramViewGroup).setPresenter(this);
    }
    return paramViewGroup;
  }
  
  public Drawable getOverflowIcon()
  {
    OverflowMenuButton localOverflowMenuButton = mOverflowButton;
    if (localOverflowMenuButton != null) {
      return localOverflowMenuButton.getDrawable();
    }
    if (mPendingOverflowIconSet) {
      return mPendingOverflowIcon;
    }
    return null;
  }
  
  public boolean hideOverflowMenu()
  {
    Object localObject = mPostedOpenRunnable;
    if (localObject != null)
    {
      MenuView localMenuView = mMenuView;
      if (localMenuView != null)
      {
        ((View)localMenuView).removeCallbacks((Runnable)localObject);
        mPostedOpenRunnable = null;
        return true;
      }
    }
    localObject = mOverflowPopup;
    if (localObject != null)
    {
      ((w)localObject).dismiss();
      return true;
    }
    return false;
  }
  
  public boolean hideSubMenus()
  {
    ActionButtonSubmenu localActionButtonSubmenu = mActionButtonPopup;
    if (localActionButtonSubmenu != null)
    {
      localActionButtonSubmenu.dismiss();
      return true;
    }
    return false;
  }
  
  public void initForMenu(Context paramContext, f paramF)
  {
    super.initForMenu(paramContext, paramF);
    paramF = paramContext.getResources();
    paramContext = ActionBarPolicy.get(paramContext);
    if (!mReserveOverflowSet) {
      mReserveOverflow = paramContext.showsOverflowMenuButton();
    }
    if (!mWidthLimitSet) {
      mWidthLimit = paramContext.getEmbeddedMenuWidthLimit();
    }
    if (!mMaxItemsSet) {
      mMaxItems = paramContext.init();
    }
    int i = mWidthLimit;
    if (mReserveOverflow)
    {
      if (mOverflowButton == null)
      {
        mOverflowButton = new OverflowMenuButton(mSystemContext);
        if (mPendingOverflowIconSet)
        {
          mOverflowButton.setImageDrawable(mPendingOverflowIcon);
          mPendingOverflowIcon = null;
          mPendingOverflowIconSet = false;
        }
        int j = View.MeasureSpec.makeMeasureSpec(0, 0);
        mOverflowButton.measure(j, j);
      }
      i -= mOverflowButton.getMeasuredWidth();
    }
    else
    {
      mOverflowButton = null;
    }
    mActionItemWidthLimit = i;
    mMinCellSize = ((int)(getDisplayMetricsdensity * 56.0F));
    mScrapActionButtonView = null;
  }
  
  public boolean isOverflowMenuShowPending()
  {
    return (mPostedOpenRunnable != null) || (isOverflowMenuShowing());
  }
  
  public boolean isOverflowMenuShowing()
  {
    OverflowPopup localOverflowPopup = mOverflowPopup;
    return (localOverflowPopup != null) && (localOverflowPopup.isShowing());
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (!mMaxItemsSet) {
      mMaxItems = ActionBarPolicy.get(mContext).init();
    }
    paramConfiguration = mMenu;
    if (paramConfiguration != null) {
      paramConfiguration.onItemsChanged(true);
    }
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof SavedState)) {
      return;
    }
    int i = openSubMenuId;
    if (i > 0)
    {
      paramParcelable = mMenu.findItem(i);
      if (paramParcelable != null) {
        a((SubMenuBuilder)paramParcelable.getSubMenu());
      }
    }
  }
  
  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState();
    openSubMenuId = mOpenSubMenuId;
    return localSavedState;
  }
  
  public void setExpandedActionViewsExclusive(boolean paramBoolean)
  {
    mExpandedActionViewsExclusive = paramBoolean;
  }
  
  public void setMenuView(ActionMenuView paramActionMenuView)
  {
    mMenuView = paramActionMenuView;
    paramActionMenuView.initialize(mMenu);
  }
  
  public void setOverflowIcon(Drawable paramDrawable)
  {
    OverflowMenuButton localOverflowMenuButton = mOverflowButton;
    if (localOverflowMenuButton != null)
    {
      localOverflowMenuButton.setImageDrawable(paramDrawable);
      return;
    }
    mPendingOverflowIconSet = true;
    mPendingOverflowIcon = paramDrawable;
  }
  
  public void setReserveOverflow(boolean paramBoolean)
  {
    mReserveOverflow = paramBoolean;
    mReserveOverflowSet = true;
  }
  
  public boolean shouldIncludeItem(int paramInt, MenuItemImpl paramMenuItemImpl)
  {
    return paramMenuItemImpl.isActionButton();
  }
  
  public boolean showOverflowMenu()
  {
    if ((mReserveOverflow) && (!isOverflowMenuShowing()))
    {
      f localF = mMenu;
      if ((localF != null) && (mMenuView != null) && (mPostedOpenRunnable == null) && (!localF.getNonActionItems().isEmpty()))
      {
        mPostedOpenRunnable = new OpenOverflowRunnable(new OverflowPopup(mContext, mMenu, mOverflowButton, true));
        ((View)mMenuView).post(mPostedOpenRunnable);
        super.a(null);
        return true;
      }
    }
    return false;
  }
  
  public void updateMenuView(boolean paramBoolean)
  {
    super.updateMenuView(paramBoolean);
    ((View)mMenuView).requestLayout();
    Object localObject1 = mMenu;
    Object localObject2;
    if (localObject1 != null)
    {
      localObject1 = ((f)localObject1).getActionItems();
      j = ((ArrayList)localObject1).size();
      i = 0;
      while (i < j)
      {
        localObject2 = ((MenuItemImpl)((ArrayList)localObject1).get(i)).getSupportActionProvider();
        if (localObject2 != null) {
          ((ActionProvider)localObject2).setSubUiVisibilityListener(this);
        }
        i += 1;
      }
    }
    localObject1 = mMenu;
    if (localObject1 != null) {
      localObject1 = ((f)localObject1).getNonActionItems();
    } else {
      localObject1 = null;
    }
    int j = 0;
    int i = j;
    boolean bool;
    if (mReserveOverflow)
    {
      i = j;
      if (localObject1 != null)
      {
        j = ((ArrayList)localObject1).size();
        i = 0;
        if (j == 1) {
          bool = ((MenuItemImpl)((ArrayList)localObject1).get(0)).isActionViewExpanded() ^ true;
        } else if (j > 0) {
          bool = true;
        }
      }
    }
    if (bool)
    {
      if (mOverflowButton == null) {
        mOverflowButton = new OverflowMenuButton(mSystemContext);
      }
      localObject1 = (ViewGroup)mOverflowButton.getParent();
      if (localObject1 != mMenuView)
      {
        if (localObject1 != null) {
          ((ViewGroup)localObject1).removeView(mOverflowButton);
        }
        localObject1 = (ActionMenuView)mMenuView;
        ((ViewGroup)localObject1).addView(mOverflowButton, ((ActionMenuView)localObject1).generateOverflowButtonLayoutParams());
      }
    }
    else
    {
      localObject1 = mOverflowButton;
      if (localObject1 != null)
      {
        localObject1 = ((View)localObject1).getParent();
        localObject2 = mMenuView;
        if (localObject1 == localObject2) {
          ((ViewGroup)localObject2).removeView(mOverflowButton);
        }
      }
    }
    ((ActionMenuView)mMenuView).setOverflowReserved(mReserveOverflow);
  }
  
  class ActionButtonSubmenu
    extends w
  {
    public ActionButtonSubmenu(Context paramContext, SubMenuBuilder paramSubMenuBuilder, View paramView)
    {
      super(paramSubMenuBuilder, paramView, false, R.attr.actionOverflowMenuStyle);
      if (!((MenuItemImpl)paramSubMenuBuilder.getItem()).isActionButton())
      {
        paramSubMenuBuilder = mOverflowButton;
        paramContext = paramSubMenuBuilder;
        if (paramSubMenuBuilder == null) {
          paramContext = (View)ActionMenuPresenter.getMenuView(ActionMenuPresenter.this);
        }
        a(paramContext);
      }
      a(mPopupPresenterCallback);
    }
    
    protected void onDismiss()
    {
      ActionMenuPresenter localActionMenuPresenter = ActionMenuPresenter.this;
      mActionButtonPopup = null;
      mOpenSubMenuId = 0;
      super.onDismiss();
    }
  }
  
  class ActionMenuPopupCallback
    extends ActionMenuItemView.b
  {
    ActionMenuPopupCallback() {}
    
    public ListPopupWindow getPopup()
    {
      ActionMenuPresenter.ActionButtonSubmenu localActionButtonSubmenu = mActionButtonPopup;
      if (localActionButtonSubmenu != null) {
        return localActionButtonSubmenu.getPopup();
      }
      return null;
    }
  }
  
  class OpenOverflowRunnable
    implements Runnable
  {
    private ActionMenuPresenter.OverflowPopup mPopup;
    
    public OpenOverflowRunnable(ActionMenuPresenter.OverflowPopup paramOverflowPopup)
    {
      mPopup = paramOverflowPopup;
    }
    
    public void run()
    {
      if (ActionMenuPresenter.access$setMOverflowPopup(ActionMenuPresenter.this) != null) {
        ActionMenuPresenter.access$getMMenu(ActionMenuPresenter.this).changeMenuMode();
      }
      View localView = (View)ActionMenuPresenter.access$getMMenuView(ActionMenuPresenter.this);
      if ((localView != null) && (localView.getWindowToken() != null) && (mPopup.a())) {
        mOverflowPopup = mPopup;
      }
      mPostedOpenRunnable = null;
    }
  }
  
  class OverflowMenuButton
    extends AppCompatImageView
    implements ActionMenuView.a
  {
    public OverflowMenuButton(Context paramContext)
    {
      super(null, R.attr.actionOverflowButtonStyle);
      setClickable(true);
      setFocusable(true);
      setVisibility(0);
      setEnabled(true);
      TabLayout.Tab.setText(this, getContentDescription());
      setOnTouchListener(new c.d.a(this, this, ActionMenuPresenter.this));
    }
    
    public boolean needsDividerAfter()
    {
      return false;
    }
    
    public boolean needsDividerBefore()
    {
      return false;
    }
    
    public boolean performClick()
    {
      if (super.performClick()) {
        return true;
      }
      playSoundEffect(0);
      showOverflowMenu();
      return true;
    }
    
    protected boolean setFrame(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      boolean bool = super.setFrame(paramInt1, paramInt2, paramInt3, paramInt4);
      Drawable localDrawable1 = getDrawable();
      Drawable localDrawable2 = getBackground();
      if ((localDrawable1 != null) && (localDrawable2 != null))
      {
        int i = getWidth();
        paramInt2 = getHeight();
        paramInt1 = Math.max(i, paramInt2) / 2;
        int j = getPaddingLeft();
        int k = getPaddingRight();
        paramInt3 = getPaddingTop();
        paramInt4 = getPaddingBottom();
        i = (i + (j - k)) / 2;
        paramInt2 = (paramInt2 + (paramInt3 - paramInt4)) / 2;
        DrawableCompat.setHotspotBounds(localDrawable2, i - paramInt1, paramInt2 - paramInt1, i + paramInt1, paramInt2 + paramInt1);
      }
      return bool;
    }
  }
  
  class OverflowPopup
    extends w
  {
    public OverflowPopup(Context paramContext, f paramF, View paramView, boolean paramBoolean)
    {
      super(paramF, paramView, paramBoolean, R.attr.actionOverflowMenuStyle);
      a(8388613);
      a(mPopupPresenterCallback);
    }
    
    protected void onDismiss()
    {
      if (ActionMenuPresenter.access$setMActionButtonPopup(ActionMenuPresenter.this) != null) {
        ActionMenuPresenter.e(ActionMenuPresenter.this).close();
      }
      mOverflowPopup = null;
      super.onDismiss();
    }
  }
  
  class PopupPresenterCallback
    implements l.a
  {
    PopupPresenterCallback() {}
    
    public void onCloseMenu(f paramF, boolean paramBoolean)
    {
      if ((paramF instanceof SubMenuBuilder)) {
        paramF.getRootMenu().close(false);
      }
      l.a localA = getCallback();
      if (localA != null) {
        localA.onCloseMenu(paramF, paramBoolean);
      }
    }
    
    public boolean onOpenSubMenu(f paramF)
    {
      if (paramF == null) {
        return false;
      }
      mOpenSubMenuId = ((SubMenuBuilder)paramF).getItem().getItemId();
      l.a localA = getCallback();
      if (localA != null) {
        return localA.onOpenSubMenu(paramF);
      }
      return false;
    }
  }
  
  class SavedState
    implements Parcelable
  {
    public static final Parcelable.Creator<c.g> CREATOR = new c.g.a();
    public int openSubMenuId;
    
    SavedState() {}
    
    SavedState()
    {
      openSubMenuId = this$1.readInt();
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(openSubMenuId);
    }
  }
}
