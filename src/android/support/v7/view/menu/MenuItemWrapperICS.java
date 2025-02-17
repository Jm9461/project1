package android.support.v7.view.menu;

import a.b.g.b.a.b;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.lang.reflect.Method;
import org.org.jaxen.dom.field.SupportMenuItem;

public class MenuItemWrapperICS
  extends c<b>
  implements MenuItem
{
  private Method mSetExclusiveCheckableMethod;
  
  MenuItemWrapperICS(Context paramContext, SupportMenuItem paramSupportMenuItem)
  {
    super(paramContext, paramSupportMenuItem);
  }
  
  public boolean collapseActionView()
  {
    return ((SupportMenuItem)mWrappedObject).collapseActionView();
  }
  
  ActionProviderWrapper createActionProviderWrapper(android.view.ActionProvider paramActionProvider)
  {
    return new ActionProviderWrapper(a, paramActionProvider);
  }
  
  public boolean expandActionView()
  {
    return ((SupportMenuItem)mWrappedObject).expandActionView();
  }
  
  public android.view.ActionProvider getActionProvider()
  {
    android.support.v4.view.ActionProvider localActionProvider = ((SupportMenuItem)mWrappedObject).getSupportActionProvider();
    if ((localActionProvider instanceof ActionProviderWrapper)) {
      return mInner;
    }
    return null;
  }
  
  public View getActionView()
  {
    View localView2 = ((SupportMenuItem)mWrappedObject).getActionView();
    View localView1 = localView2;
    if ((localView2 instanceof CollapsibleActionViewWrapper)) {
      localView1 = ((CollapsibleActionViewWrapper)localView2).getWrappedView();
    }
    return localView1;
  }
  
  public int getAlphabeticModifiers()
  {
    return ((SupportMenuItem)mWrappedObject).getAlphabeticModifiers();
  }
  
  public char getAlphabeticShortcut()
  {
    return ((SupportMenuItem)mWrappedObject).getAlphabeticShortcut();
  }
  
  public CharSequence getContentDescription()
  {
    return ((SupportMenuItem)mWrappedObject).getContentDescription();
  }
  
  public int getGroupId()
  {
    return ((SupportMenuItem)mWrappedObject).getGroupId();
  }
  
  public Drawable getIcon()
  {
    return ((SupportMenuItem)mWrappedObject).getIcon();
  }
  
  public ColorStateList getIconTintList()
  {
    return ((SupportMenuItem)mWrappedObject).getIconTintList();
  }
  
  public PorterDuff.Mode getIconTintMode()
  {
    return ((SupportMenuItem)mWrappedObject).getIconTintMode();
  }
  
  public Intent getIntent()
  {
    return ((SupportMenuItem)mWrappedObject).getIntent();
  }
  
  public int getItemId()
  {
    return ((SupportMenuItem)mWrappedObject).getItemId();
  }
  
  public ContextMenu.ContextMenuInfo getMenuInfo()
  {
    return ((SupportMenuItem)mWrappedObject).getMenuInfo();
  }
  
  public int getNumericModifiers()
  {
    return ((SupportMenuItem)mWrappedObject).getNumericModifiers();
  }
  
  public char getNumericShortcut()
  {
    return ((SupportMenuItem)mWrappedObject).getNumericShortcut();
  }
  
  public int getOrder()
  {
    return ((SupportMenuItem)mWrappedObject).getOrder();
  }
  
  public SubMenu getSubMenu()
  {
    return a(((SupportMenuItem)mWrappedObject).getSubMenu());
  }
  
  public CharSequence getTitle()
  {
    return ((SupportMenuItem)mWrappedObject).getTitle();
  }
  
  public CharSequence getTitleCondensed()
  {
    return ((SupportMenuItem)mWrappedObject).getTitleCondensed();
  }
  
  public CharSequence getTooltipText()
  {
    return ((SupportMenuItem)mWrappedObject).getTooltipText();
  }
  
  public boolean hasSubMenu()
  {
    return ((SupportMenuItem)mWrappedObject).hasSubMenu();
  }
  
  public boolean isActionViewExpanded()
  {
    return ((SupportMenuItem)mWrappedObject).isActionViewExpanded();
  }
  
  public boolean isCheckable()
  {
    return ((SupportMenuItem)mWrappedObject).isCheckable();
  }
  
  public boolean isChecked()
  {
    return ((SupportMenuItem)mWrappedObject).isChecked();
  }
  
  public boolean isEnabled()
  {
    return ((SupportMenuItem)mWrappedObject).isEnabled();
  }
  
  public boolean isVisible()
  {
    return ((SupportMenuItem)mWrappedObject).isVisible();
  }
  
  public MenuItem setActionProvider(android.view.ActionProvider paramActionProvider)
  {
    SupportMenuItem localSupportMenuItem = (SupportMenuItem)mWrappedObject;
    if (paramActionProvider != null) {
      paramActionProvider = createActionProviderWrapper(paramActionProvider);
    } else {
      paramActionProvider = null;
    }
    localSupportMenuItem.setSupportActionProvider(paramActionProvider);
    return this;
  }
  
  public MenuItem setActionView(int paramInt)
  {
    ((SupportMenuItem)mWrappedObject).setActionView(paramInt);
    View localView = ((SupportMenuItem)mWrappedObject).getActionView();
    if ((localView instanceof android.view.CollapsibleActionView)) {
      ((SupportMenuItem)mWrappedObject).setActionView(new CollapsibleActionViewWrapper(localView));
    }
    return this;
  }
  
  public MenuItem setActionView(View paramView)
  {
    Object localObject = paramView;
    if ((paramView instanceof android.view.CollapsibleActionView)) {
      localObject = new CollapsibleActionViewWrapper(paramView);
    }
    ((SupportMenuItem)mWrappedObject).setActionView((View)localObject);
    return this;
  }
  
  public MenuItem setAlphabeticShortcut(char paramChar)
  {
    ((SupportMenuItem)mWrappedObject).setAlphabeticShortcut(paramChar);
    return this;
  }
  
  public MenuItem setAlphabeticShortcut(char paramChar, int paramInt)
  {
    ((SupportMenuItem)mWrappedObject).setAlphabeticShortcut(paramChar, paramInt);
    return this;
  }
  
  public MenuItem setCheckable(boolean paramBoolean)
  {
    ((SupportMenuItem)mWrappedObject).setCheckable(paramBoolean);
    return this;
  }
  
  public MenuItem setChecked(boolean paramBoolean)
  {
    ((SupportMenuItem)mWrappedObject).setChecked(paramBoolean);
    return this;
  }
  
  public MenuItem setContentDescription(CharSequence paramCharSequence)
  {
    ((SupportMenuItem)mWrappedObject).setContentDescription(paramCharSequence);
    return this;
  }
  
  public MenuItem setEnabled(boolean paramBoolean)
  {
    ((SupportMenuItem)mWrappedObject).setEnabled(paramBoolean);
    return this;
  }
  
  public void setExclusiveCheckable(boolean paramBoolean)
  {
    Object localObject1;
    if (mSetExclusiveCheckableMethod == null) {
      localObject1 = (SupportMenuItem)mWrappedObject;
    }
    try
    {
      localObject1 = localObject1.getClass();
      Object localObject2 = Boolean.TYPE;
      localObject1 = ((Class)localObject1).getDeclaredMethod("setExclusiveCheckable", new Class[] { localObject2 });
      mSetExclusiveCheckableMethod = ((Method)localObject1);
      localObject1 = mSetExclusiveCheckableMethod;
      localObject2 = mWrappedObject;
      ((Method)localObject1).invoke(localObject2, new Object[] { Boolean.valueOf(paramBoolean) });
      return;
    }
    catch (Exception localException)
    {
      Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", localException);
    }
  }
  
  public MenuItem setIcon(int paramInt)
  {
    ((SupportMenuItem)mWrappedObject).setIcon(paramInt);
    return this;
  }
  
  public MenuItem setIcon(Drawable paramDrawable)
  {
    ((SupportMenuItem)mWrappedObject).setIcon(paramDrawable);
    return this;
  }
  
  public MenuItem setIconTintList(ColorStateList paramColorStateList)
  {
    ((SupportMenuItem)mWrappedObject).setIconTintList(paramColorStateList);
    return this;
  }
  
  public MenuItem setIconTintMode(PorterDuff.Mode paramMode)
  {
    ((SupportMenuItem)mWrappedObject).setIconTintMode(paramMode);
    return this;
  }
  
  public MenuItem setIntent(Intent paramIntent)
  {
    ((SupportMenuItem)mWrappedObject).setIntent(paramIntent);
    return this;
  }
  
  public MenuItem setNumericShortcut(char paramChar)
  {
    ((SupportMenuItem)mWrappedObject).setNumericShortcut(paramChar);
    return this;
  }
  
  public MenuItem setNumericShortcut(char paramChar, int paramInt)
  {
    ((SupportMenuItem)mWrappedObject).setNumericShortcut(paramChar, paramInt);
    return this;
  }
  
  public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener paramOnActionExpandListener)
  {
    SupportMenuItem localSupportMenuItem = (SupportMenuItem)mWrappedObject;
    if (paramOnActionExpandListener != null) {
      paramOnActionExpandListener = new OnActionExpandListenerWrapper(paramOnActionExpandListener);
    } else {
      paramOnActionExpandListener = null;
    }
    localSupportMenuItem.setOnActionExpandListener(paramOnActionExpandListener);
    return this;
  }
  
  public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener paramOnMenuItemClickListener)
  {
    SupportMenuItem localSupportMenuItem = (SupportMenuItem)mWrappedObject;
    if (paramOnMenuItemClickListener != null) {
      paramOnMenuItemClickListener = new OnMenuItemClickListenerWrapper(paramOnMenuItemClickListener);
    } else {
      paramOnMenuItemClickListener = null;
    }
    localSupportMenuItem.setOnMenuItemClickListener(paramOnMenuItemClickListener);
    return this;
  }
  
  public MenuItem setShortcut(char paramChar1, char paramChar2)
  {
    ((SupportMenuItem)mWrappedObject).setShortcut(paramChar1, paramChar2);
    return this;
  }
  
  public MenuItem setShortcut(char paramChar1, char paramChar2, int paramInt1, int paramInt2)
  {
    ((SupportMenuItem)mWrappedObject).setShortcut(paramChar1, paramChar2, paramInt1, paramInt2);
    return this;
  }
  
  public void setShowAsAction(int paramInt)
  {
    ((SupportMenuItem)mWrappedObject).setShowAsAction(paramInt);
  }
  
  public MenuItem setShowAsActionFlags(int paramInt)
  {
    ((SupportMenuItem)mWrappedObject).setShowAsActionFlags(paramInt);
    return this;
  }
  
  public MenuItem setTitle(int paramInt)
  {
    ((SupportMenuItem)mWrappedObject).setTitle(paramInt);
    return this;
  }
  
  public MenuItem setTitle(CharSequence paramCharSequence)
  {
    ((SupportMenuItem)mWrappedObject).setTitle(paramCharSequence);
    return this;
  }
  
  public MenuItem setTitleCondensed(CharSequence paramCharSequence)
  {
    ((SupportMenuItem)mWrappedObject).setTitleCondensed(paramCharSequence);
    return this;
  }
  
  public MenuItem setTooltipText(CharSequence paramCharSequence)
  {
    ((SupportMenuItem)mWrappedObject).setTooltipText(paramCharSequence);
    return this;
  }
  
  public MenuItem setVisible(boolean paramBoolean)
  {
    return ((SupportMenuItem)mWrappedObject).setVisible(paramBoolean);
  }
  
  class ActionProviderWrapper
    extends android.support.v4.view.ActionProvider
  {
    final android.view.ActionProvider mInner;
    
    public ActionProviderWrapper(Context paramContext, android.view.ActionProvider paramActionProvider)
    {
      super();
      mInner = paramActionProvider;
    }
    
    public boolean hasSubMenu()
    {
      return mInner.hasSubMenu();
    }
    
    public View onCreateActionView()
    {
      return mInner.onCreateActionView();
    }
    
    public boolean onPerformDefaultAction()
    {
      return mInner.onPerformDefaultAction();
    }
    
    public void onPrepareSubMenu(SubMenu paramSubMenu)
    {
      mInner.onPrepareSubMenu(a(paramSubMenu));
    }
  }
  
  class CollapsibleActionViewWrapper
    extends FrameLayout
    implements org.org.v4.view.CollapsibleActionView
  {
    final android.view.CollapsibleActionView mWrappedView;
    
    CollapsibleActionViewWrapper()
    {
      super();
      mWrappedView = ((android.view.CollapsibleActionView)this$1);
      addView(this$1);
    }
    
    View getWrappedView()
    {
      return (View)mWrappedView;
    }
    
    public void onActionViewCollapsed()
    {
      mWrappedView.onActionViewCollapsed();
    }
    
    public void onActionViewExpanded()
    {
      mWrappedView.onActionViewExpanded();
    }
  }
  
  class OnActionExpandListenerWrapper
    extends d<MenuItem.OnActionExpandListener>
    implements MenuItem.OnActionExpandListener
  {
    OnActionExpandListenerWrapper(MenuItem.OnActionExpandListener paramOnActionExpandListener)
    {
      super();
    }
    
    public boolean onMenuItemActionCollapse(MenuItem paramMenuItem)
    {
      return ((MenuItem.OnActionExpandListener)mWrappedObject).onMenuItemActionCollapse(a(paramMenuItem));
    }
    
    public boolean onMenuItemActionExpand(MenuItem paramMenuItem)
    {
      return ((MenuItem.OnActionExpandListener)mWrappedObject).onMenuItemActionExpand(a(paramMenuItem));
    }
  }
  
  class OnMenuItemClickListenerWrapper
    extends d<MenuItem.OnMenuItemClickListener>
    implements MenuItem.OnMenuItemClickListener
  {
    OnMenuItemClickListenerWrapper(MenuItem.OnMenuItemClickListener paramOnMenuItemClickListener)
    {
      super();
    }
    
    public boolean onMenuItemClick(MenuItem paramMenuItem)
    {
      return ((MenuItem.OnMenuItemClickListener)mWrappedObject).onMenuItemClick(a(paramMenuItem));
    }
  }
}
