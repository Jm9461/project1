package android.support.v7.view.menu;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import org.org.jaxen.dom.field.SupportMenu;
import org.org.jaxen.dom.field.SupportMenuItem;
import org.org.jaxen.dom.field.SupportSubMenu;

public final class n
{
  public static MenuItem a(Context paramContext, SupportMenuItem paramSupportMenuItem)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return new MenuItemWrapperJB(paramContext, paramSupportMenuItem);
    }
    return new MenuItemWrapperICS(paramContext, paramSupportMenuItem);
  }
  
  public static SubMenu a(Context paramContext, SupportSubMenu paramSupportSubMenu)
  {
    return new SubMenuWrapperICS(paramContext, paramSupportSubMenu);
  }
  
  public static Menu wrapSupportMenu(Context paramContext, SupportMenu paramSupportMenu)
  {
    return new MenuWrapperICS(paramContext, paramSupportMenu);
  }
}
