package android.support.v7.view.menu;

import a.b.g.b.a.b;
import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.org.jaxen.dom.field.SupportMenuItem;
import org.org.jaxen.dom.field.SupportSubMenu;
import org.org.jaxen.util.Label;

abstract class c<T>
  extends d<T>
{
  final Context a;
  private Map<b, MenuItem> c;
  private Map<a.b.g.b.a.c, SubMenu> d;
  
  c(Context paramContext, Object paramObject)
  {
    super(paramObject);
    a = paramContext;
  }
  
  final MenuItem a(MenuItem paramMenuItem)
  {
    MenuItem localMenuItem = paramMenuItem;
    if ((paramMenuItem instanceof SupportMenuItem))
    {
      SupportMenuItem localSupportMenuItem = (SupportMenuItem)paramMenuItem;
      if (c == null) {
        c = new Label();
      }
      paramMenuItem = (MenuItem)c.get(paramMenuItem);
      localMenuItem = paramMenuItem;
      if (paramMenuItem == null)
      {
        localMenuItem = n.a(a, localSupportMenuItem);
        c.put(localSupportMenuItem, localMenuItem);
      }
    }
    return localMenuItem;
  }
  
  final SubMenu a(SubMenu paramSubMenu)
  {
    SubMenu localSubMenu = paramSubMenu;
    if ((paramSubMenu instanceof SupportSubMenu))
    {
      SupportSubMenu localSupportSubMenu = (SupportSubMenu)paramSubMenu;
      if (d == null) {
        d = new Label();
      }
      paramSubMenu = (SubMenu)d.get(localSupportSubMenu);
      localSubMenu = paramSubMenu;
      if (paramSubMenu == null)
      {
        localSubMenu = n.a(a, localSupportSubMenu);
        d.put(localSupportSubMenu, localSubMenu);
      }
    }
    return localSubMenu;
  }
  
  final void a(int paramInt)
  {
    Object localObject = c;
    if (localObject == null) {
      return;
    }
    localObject = ((Map)localObject).keySet().iterator();
    while (((Iterator)localObject).hasNext()) {
      if (paramInt == ((MenuItem)((Iterator)localObject).next()).getGroupId()) {
        ((Iterator)localObject).remove();
      }
    }
  }
  
  final void b()
  {
    Map localMap = c;
    if (localMap != null) {
      localMap.clear();
    }
    localMap = d;
    if (localMap != null) {
      localMap.clear();
    }
  }
  
  final void b(int paramInt)
  {
    Object localObject = c;
    if (localObject == null) {
      return;
    }
    localObject = ((Map)localObject).keySet().iterator();
    while (((Iterator)localObject).hasNext()) {
      if (paramInt == ((MenuItem)((Iterator)localObject).next()).getItemId()) {
        ((Iterator)localObject).remove();
      }
    }
  }
}
