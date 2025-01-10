package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import org.org.jaxen.dom.field.SupportSubMenu;

class SubMenuWrapperICS
  extends MenuWrapperICS
  implements SubMenu
{
  SubMenuWrapperICS(Context paramContext, SupportSubMenu paramSupportSubMenu)
  {
    super(paramContext, paramSupportSubMenu);
  }
  
  public void clearHeader()
  {
    getWrappedObject().clearHeader();
  }
  
  public MenuItem getItem()
  {
    return a(getWrappedObject().getItem());
  }
  
  public SupportSubMenu getWrappedObject()
  {
    return (SupportSubMenu)mWrappedObject;
  }
  
  public SubMenu setHeaderIcon(int paramInt)
  {
    getWrappedObject().setHeaderIcon(paramInt);
    return this;
  }
  
  public SubMenu setHeaderIcon(Drawable paramDrawable)
  {
    getWrappedObject().setHeaderIcon(paramDrawable);
    return this;
  }
  
  public SubMenu setHeaderTitle(int paramInt)
  {
    getWrappedObject().setHeaderTitle(paramInt);
    return this;
  }
  
  public SubMenu setHeaderTitle(CharSequence paramCharSequence)
  {
    getWrappedObject().setHeaderTitle(paramCharSequence);
    return this;
  }
  
  public SubMenu setHeaderView(View paramView)
  {
    getWrappedObject().setHeaderView(paramView);
    return this;
  }
  
  public SubMenu setIcon(int paramInt)
  {
    getWrappedObject().setIcon(paramInt);
    return this;
  }
  
  public SubMenu setIcon(Drawable paramDrawable)
  {
    getWrappedObject().setIcon(paramDrawable);
    return this;
  }
}
