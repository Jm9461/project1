package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView.ItemView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.TabLayout.Tab;
import android.support.v7.widget.o0.a;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import org.org.android.R.dimen;
import org.org.android.R.drawable;
import org.org.android.R.id;
import org.org.android.R.layout;
import org.org.v4.content.R.attr;

public class NavigationMenuItemView
  extends ForegroundLinearLayout
  implements MenuView.ItemView
{
  private static final int[] CHECKED_STATE_SET = { 16842912 };
  private FrameLayout mActionArea;
  private boolean mForceShowIcon;
  private Drawable mIcon;
  private final int mIconSize;
  private ColorStateList mIconTintList;
  private MenuItemImpl mItemData;
  private boolean mLogo;
  private final CheckedTextView mTextView;
  boolean s;
  private final AccessibilityDelegateCompat this$0 = new a();
  
  public NavigationMenuItemView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public NavigationMenuItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public NavigationMenuItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setOrientation(0);
    LayoutInflater.from(paramContext).inflate(R.layout.design_navigation_menu_item, this, true);
    mIconSize = paramContext.getResources().getDimensionPixelSize(R.dimen.design_navigation_icon_size);
    mTextView = ((CheckedTextView)findViewById(R.id.design_menu_item_text));
    mTextView.setDuplicateParentStateEnabled(true);
    ViewCompat.setAccessibilityDelegate(mTextView, this$0);
  }
  
  private StateListDrawable createDefaultBackground()
  {
    TypedValue localTypedValue = new TypedValue();
    if (getContext().getTheme().resolveAttribute(R.attr.colorControlHighlight, localTypedValue, true))
    {
      StateListDrawable localStateListDrawable = new StateListDrawable();
      localStateListDrawable.addState(CHECKED_STATE_SET, new ColorDrawable(data));
      localStateListDrawable.addState(View.EMPTY_STATE_SET, new ColorDrawable(0));
      return localStateListDrawable;
    }
    return null;
  }
  
  private void onBindViewHolder()
  {
    Object localObject;
    if (setIcon())
    {
      mTextView.setVisibility(8);
      localObject = mActionArea;
      if (localObject != null)
      {
        localObject = (o0.a)((View)localObject).getLayoutParams();
        width = -1;
        mActionArea.setLayoutParams((ViewGroup.LayoutParams)localObject);
      }
    }
    else
    {
      mTextView.setVisibility(0);
      localObject = mActionArea;
      if (localObject != null)
      {
        localObject = (o0.a)((View)localObject).getLayoutParams();
        width = -2;
        mActionArea.setLayoutParams((ViewGroup.LayoutParams)localObject);
      }
    }
  }
  
  private void setActionView(View paramView)
  {
    if (paramView != null)
    {
      if (mActionArea == null) {
        mActionArea = ((FrameLayout)((ViewStub)findViewById(R.id.design_menu_item_action_area_stub)).inflate());
      }
      mActionArea.removeAllViews();
      mActionArea.addView(paramView);
    }
  }
  
  private boolean setIcon()
  {
    return (mItemData.getTitle() == null) && (mItemData.getIcon() == null) && (mItemData.getActionView() != null);
  }
  
  public MenuItemImpl getItemData()
  {
    return mItemData;
  }
  
  public void initialize(MenuItemImpl paramMenuItemImpl, int paramInt)
  {
    mItemData = paramMenuItemImpl;
    if (paramMenuItemImpl.isVisible()) {
      paramInt = 0;
    } else {
      paramInt = 8;
    }
    setVisibility(paramInt);
    if (getBackground() == null) {
      ViewCompat.setBackgroundDrawable(this, createDefaultBackground());
    }
    setCheckable(paramMenuItemImpl.isCheckable());
    setChecked(paramMenuItemImpl.isChecked());
    setEnabled(paramMenuItemImpl.isEnabled());
    setTitle(paramMenuItemImpl.getTitle());
    setIcon(paramMenuItemImpl.getIcon());
    setActionView(paramMenuItemImpl.getActionView());
    setContentDescription(paramMenuItemImpl.getContentDescription());
    TabLayout.Tab.setText(this, paramMenuItemImpl.getTooltipText());
    onBindViewHolder();
  }
  
  protected int[] onCreateDrawableState(int paramInt)
  {
    int[] arrayOfInt = super.onCreateDrawableState(paramInt + 1);
    MenuItemImpl localMenuItemImpl = mItemData;
    if ((localMenuItemImpl != null) && (localMenuItemImpl.isCheckable()) && (mItemData.isChecked())) {
      View.mergeDrawableStates(arrayOfInt, CHECKED_STATE_SET);
    }
    return arrayOfInt;
  }
  
  public boolean prefersCondensedTitle()
  {
    return false;
  }
  
  public void recycle()
  {
    FrameLayout localFrameLayout = mActionArea;
    if (localFrameLayout != null) {
      localFrameLayout.removeAllViews();
    }
    mTextView.setCompoundDrawables(null, null, null, null);
  }
  
  public void setCheckable(boolean paramBoolean)
  {
    refreshDrawableState();
    if (s != paramBoolean)
    {
      s = paramBoolean;
      this$0.sendAccessibilityEvent(mTextView, 2048);
    }
  }
  
  public void setChecked(boolean paramBoolean)
  {
    refreshDrawableState();
    mTextView.setChecked(paramBoolean);
  }
  
  public void setHorizontalPadding(int paramInt)
  {
    setPadding(paramInt, 0, paramInt, 0);
  }
  
  public void setIcon(Drawable paramDrawable)
  {
    int i;
    if (paramDrawable != null)
    {
      Object localObject = paramDrawable;
      if (mForceShowIcon)
      {
        localObject = paramDrawable.getConstantState();
        if (localObject != null) {
          paramDrawable = ((Drawable.ConstantState)localObject).newDrawable();
        }
        paramDrawable = DrawableCompat.wrap(paramDrawable).mutate();
        localObject = paramDrawable;
        DrawableCompat.setTintList(paramDrawable, mIconTintList);
      }
      i = mIconSize;
      ((Drawable)localObject).setBounds(0, 0, i, i);
      paramDrawable = (Drawable)localObject;
    }
    else if (mLogo)
    {
      if (mIcon == null)
      {
        mIcon = android.support.v4.content.view.Resources.getDrawable(getResources(), R.drawable.navigation_empty_icon, getContext().getTheme());
        paramDrawable = mIcon;
        if (paramDrawable != null)
        {
          i = mIconSize;
          paramDrawable.setBounds(0, 0, i, i);
        }
      }
      paramDrawable = mIcon;
    }
    android.support.v4.widget.TextView.setCompoundDrawablesRelative(mTextView, paramDrawable, null, null, null);
  }
  
  public void setIconPadding(int paramInt)
  {
    mTextView.setCompoundDrawablePadding(paramInt);
  }
  
  void setIconTintList(ColorStateList paramColorStateList)
  {
    mIconTintList = paramColorStateList;
    boolean bool;
    if (mIconTintList != null) {
      bool = true;
    } else {
      bool = false;
    }
    mForceShowIcon = bool;
    paramColorStateList = mItemData;
    if (paramColorStateList != null) {
      setIcon(paramColorStateList.getIcon());
    }
  }
  
  public void setNeedsEmptyIcon(boolean paramBoolean)
  {
    mLogo = paramBoolean;
  }
  
  public void setTextAppearance(int paramInt)
  {
    android.support.v4.widget.TextView.setTextAppearance(mTextView, paramInt);
  }
  
  public void setTextColor(ColorStateList paramColorStateList)
  {
    mTextView.setTextColor(paramColorStateList);
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    mTextView.setText(paramCharSequence);
  }
  
  class a
    extends AccessibilityDelegateCompat
  {
    a() {}
    
    public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
      paramAccessibilityNodeInfoCompat.setContentInvalid(s);
    }
  }
}
