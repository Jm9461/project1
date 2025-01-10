package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.design.internal.NavigationMenuPresenter;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.design.internal.a;
import android.support.design.internal.g;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.Artist;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.f;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.org.android.R.style;
import org.org.android.R.styleable;
import org.org.v4.view.SupportMenuInflater;

public class NavigationView
  extends ScrimInsetsFrameLayout
{
  private static final int[] CHECKED_STATE_SET = { 16842912 };
  private static final int[] DISABLED_STATE_SET = { -16842910 };
  private final int mMaxWidth;
  private final a mMenu;
  private MenuInflater mMenuInflater;
  private final NavigationMenuPresenter mPresenter = new NavigationMenuPresenter();
  b mTitle;
  
  public NavigationView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public NavigationView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, org.org.android.R.attr.navigationViewStyle);
  }
  
  public NavigationView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    mMenu = new a(paramContext);
    TintTypedArray localTintTypedArray = g.obtainStyledAttrsFromThemeAttr(paramContext, paramAttributeSet, R.styleable.NavigationView, paramInt, R.style.Widget_Design_NavigationView, new int[0]);
    ViewCompat.setBackgroundDrawable(this, localTintTypedArray.getDrawable(R.styleable.NavigationView_android_background));
    if (localTintTypedArray.hasValue(R.styleable.NavigationView_elevation)) {
      ViewCompat.setElevation(this, localTintTypedArray.getDimensionPixelSize(R.styleable.NavigationView_elevation, 0));
    }
    ViewCompat.setFitsSystemWindows(this, localTintTypedArray.getBoolean(R.styleable.NavigationView_android_fitsSystemWindows, false));
    mMaxWidth = localTintTypedArray.getDimensionPixelSize(R.styleable.NavigationView_android_maxWidth, 0);
    ColorStateList localColorStateList;
    if (localTintTypedArray.hasValue(R.styleable.NavigationView_itemIconTint)) {
      localColorStateList = localTintTypedArray.getColorStateList(R.styleable.NavigationView_itemIconTint);
    } else {
      localColorStateList = createDefaultColorStateList(16842808);
    }
    paramInt = 0;
    int i = 0;
    if (localTintTypedArray.hasValue(R.styleable.NavigationView_itemTextAppearance))
    {
      i = localTintTypedArray.getResourceId(R.styleable.NavigationView_itemTextAppearance, 0);
      paramInt = 1;
    }
    paramAttributeSet = null;
    if (localTintTypedArray.hasValue(R.styleable.NavigationView_itemTextColor)) {
      paramAttributeSet = localTintTypedArray.getColorStateList(R.styleable.NavigationView_itemTextColor);
    }
    Object localObject = paramAttributeSet;
    if (paramInt == 0)
    {
      localObject = paramAttributeSet;
      if (paramAttributeSet == null) {
        localObject = createDefaultColorStateList(16842806);
      }
    }
    paramAttributeSet = localTintTypedArray.getDrawable(R.styleable.NavigationView_itemBackground);
    if (localTintTypedArray.hasValue(R.styleable.NavigationView_itemHorizontalPadding))
    {
      j = localTintTypedArray.getDimensionPixelSize(R.styleable.NavigationView_itemHorizontalPadding, 0);
      mPresenter.b(j);
    }
    int j = localTintTypedArray.getDimensionPixelSize(R.styleable.NavigationView_itemIconPadding, 0);
    mMenu.setCallback(new a());
    mPresenter.e(1);
    mPresenter.initForMenu(paramContext, mMenu);
    mPresenter.a(localColorStateList);
    if (paramInt != 0) {
      mPresenter.setItemTextAppearance(i);
    }
    mPresenter.setItemTextColor((ColorStateList)localObject);
    mPresenter.setItemBackground(paramAttributeSet);
    mPresenter.a(j);
    mMenu.a(mPresenter);
    addView((View)mPresenter.getMenuView(this));
    if (localTintTypedArray.hasValue(R.styleable.NavigationView_menu)) {
      inflateMenu(localTintTypedArray.getResourceId(R.styleable.NavigationView_menu, 0));
    }
    if (localTintTypedArray.hasValue(R.styleable.NavigationView_headerLayout)) {
      inflateHeaderView(localTintTypedArray.getResourceId(R.styleable.NavigationView_headerLayout, 0));
    }
    localTintTypedArray.recycle();
  }
  
  private ColorStateList createDefaultColorStateList(int paramInt)
  {
    Object localObject = new TypedValue();
    if (!getContext().getTheme().resolveAttribute(paramInt, (TypedValue)localObject, true)) {
      return null;
    }
    ColorStateList localColorStateList = org.org.v4.gui.helpers.Resources.getColorStateList(getContext(), resourceId);
    if (!getContext().getTheme().resolveAttribute(org.org.v4.content.R.attr.colorPrimary, (TypedValue)localObject, true)) {
      return null;
    }
    paramInt = data;
    int i = localColorStateList.getDefaultColor();
    localObject = DISABLED_STATE_SET;
    int[] arrayOfInt1 = CHECKED_STATE_SET;
    int[] arrayOfInt2 = View.EMPTY_STATE_SET;
    int j = localColorStateList.getColorForState(DISABLED_STATE_SET, i);
    return new ColorStateList(new int[][] { localObject, arrayOfInt1, arrayOfInt2 }, new int[] { j, paramInt, i });
  }
  
  private MenuInflater getMenuInflater()
  {
    if (mMenuInflater == null) {
      mMenuInflater = new SupportMenuInflater(getContext());
    }
    return mMenuInflater;
  }
  
  public MenuItem getCheckedItem()
  {
    return mPresenter.updateMenuView();
  }
  
  public int getHeaderCount()
  {
    return mPresenter.a();
  }
  
  public Drawable getItemBackground()
  {
    return mPresenter.getItemBackground();
  }
  
  public int getItemHorizontalPadding()
  {
    return mPresenter.g();
  }
  
  public int getItemIconPadding()
  {
    return mPresenter.d();
  }
  
  public ColorStateList getItemIconTintList()
  {
    return mPresenter.b();
  }
  
  public ColorStateList getItemTextColor()
  {
    return mPresenter.c();
  }
  
  public Menu getMenu()
  {
    return mMenu;
  }
  
  public View inflateHeaderView(int paramInt)
  {
    return mPresenter.inflateHeaderView(paramInt);
  }
  
  public void inflateMenu(int paramInt)
  {
    mPresenter.setUpdateSuspended(true);
    getMenuInflater().inflate(paramInt, mMenu);
    mPresenter.setUpdateSuspended(false);
    mPresenter.updateMenuView(false);
  }
  
  protected void onInsetsChanged(WindowInsetsCompat paramWindowInsetsCompat)
  {
    mPresenter.a(paramWindowInsetsCompat);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt1);
    if (i != Integer.MIN_VALUE)
    {
      if (i != 0)
      {
        if (i != 1073741824) {}
      }
      else {
        paramInt1 = View.MeasureSpec.makeMeasureSpec(mMaxWidth, 1073741824);
      }
    }
    else {
      paramInt1 = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(paramInt1), mMaxWidth), 1073741824);
    }
    super.onMeasure(paramInt1, paramInt2);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof c))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (c)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    mMenu.restorePresenterStates(menuState);
  }
  
  protected Parcelable onSaveInstanceState()
  {
    c localC = new c(super.onSaveInstanceState());
    menuState = new Bundle();
    mMenu.savePresenterStates(menuState);
    return localC;
  }
  
  public void setCheckedItem(int paramInt)
  {
    MenuItem localMenuItem = mMenu.findItem(paramInt);
    if (localMenuItem != null) {
      mPresenter.setCheckedItem((MenuItemImpl)localMenuItem);
    }
  }
  
  public void setCheckedItem(MenuItem paramMenuItem)
  {
    paramMenuItem = mMenu.findItem(paramMenuItem.getItemId());
    if (paramMenuItem != null)
    {
      mPresenter.setCheckedItem((MenuItemImpl)paramMenuItem);
      return;
    }
    throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
  }
  
  public void setItemBackground(Drawable paramDrawable)
  {
    mPresenter.setItemBackground(paramDrawable);
  }
  
  public void setItemBackgroundResource(int paramInt)
  {
    setItemBackground(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setItemHorizontalPadding(int paramInt)
  {
    mPresenter.b(paramInt);
  }
  
  public void setItemHorizontalPaddingResource(int paramInt)
  {
    mPresenter.b(getResources().getDimensionPixelSize(paramInt));
  }
  
  public void setItemIconPadding(int paramInt)
  {
    mPresenter.a(paramInt);
  }
  
  public void setItemIconPaddingResource(int paramInt)
  {
    mPresenter.a(getResources().getDimensionPixelSize(paramInt));
  }
  
  public void setItemIconTintList(ColorStateList paramColorStateList)
  {
    mPresenter.a(paramColorStateList);
  }
  
  public void setItemTextAppearance(int paramInt)
  {
    mPresenter.setItemTextAppearance(paramInt);
  }
  
  public void setItemTextColor(ColorStateList paramColorStateList)
  {
    mPresenter.setItemTextColor(paramColorStateList);
  }
  
  public void setNavigationItemSelectedListener(b paramB)
  {
    mTitle = paramB;
  }
  
  class a
    implements MenuBuilder.Callback
  {
    a() {}
    
    public boolean onMenuItemSelected(f paramF, MenuItem paramMenuItem)
    {
      paramF = mTitle;
      return (paramF != null) && (paramF.onMenuItemSelected(paramMenuItem));
    }
    
    public void onMenuModeChange(f paramF) {}
  }
  
  public static abstract interface b
  {
    public abstract boolean onMenuItemSelected(MenuItem paramMenuItem);
  }
  
  public static class c
    extends Artist
  {
    public static final Parcelable.Creator<c> CREATOR = new a();
    public Bundle menuState;
    
    public c(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      menuState = paramParcel.readBundle(paramClassLoader);
    }
    
    public c(Parcelable paramParcelable)
    {
      super();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeBundle(menuState);
    }
    
    static final class a
      implements Parcelable.ClassLoaderCreator<NavigationView.c>
    {
      a() {}
      
      public NavigationView.c createFromParcel(Parcel paramParcel)
      {
        return new NavigationView.c(paramParcel, null);
      }
      
      public NavigationView.c createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
      {
        return new NavigationView.c(paramParcel, paramClassLoader);
      }
      
      public NavigationView.c[] newArray(int paramInt)
      {
        return new NavigationView.c[paramInt];
      }
    }
  }
}
