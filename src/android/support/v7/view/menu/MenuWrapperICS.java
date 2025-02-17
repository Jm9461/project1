package android.support.v7.view.menu;

import a.b.g.b.a.a;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import org.org.jaxen.dom.field.SupportMenu;

class MenuWrapperICS
  extends c<a>
  implements Menu
{
  MenuWrapperICS(Context paramContext, SupportMenu paramSupportMenu)
  {
    super(paramContext, paramSupportMenu);
  }
  
  public MenuItem add(int paramInt)
  {
    return a(((SupportMenu)mWrappedObject).add(paramInt));
  }
  
  public MenuItem add(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return a(((SupportMenu)mWrappedObject).add(paramInt1, paramInt2, paramInt3, paramInt4));
  }
  
  public MenuItem add(int paramInt1, int paramInt2, int paramInt3, CharSequence paramCharSequence)
  {
    return a(((SupportMenu)mWrappedObject).add(paramInt1, paramInt2, paramInt3, paramCharSequence));
  }
  
  public MenuItem add(CharSequence paramCharSequence)
  {
    return a(((SupportMenu)mWrappedObject).add(paramCharSequence));
  }
  
  public int addIntentOptions(int paramInt1, int paramInt2, int paramInt3, ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt4, MenuItem[] paramArrayOfMenuItem)
  {
    MenuItem[] arrayOfMenuItem = null;
    if (paramArrayOfMenuItem != null) {
      arrayOfMenuItem = new MenuItem[paramArrayOfMenuItem.length];
    }
    paramInt2 = ((SupportMenu)mWrappedObject).addIntentOptions(paramInt1, paramInt2, paramInt3, paramComponentName, paramArrayOfIntent, paramIntent, paramInt4, arrayOfMenuItem);
    if (arrayOfMenuItem != null)
    {
      paramInt1 = 0;
      paramInt3 = arrayOfMenuItem.length;
      while (paramInt1 < paramInt3)
      {
        paramArrayOfMenuItem[paramInt1] = a(arrayOfMenuItem[paramInt1]);
        paramInt1 += 1;
      }
    }
    return paramInt2;
  }
  
  public SubMenu addSubMenu(int paramInt)
  {
    return a(((SupportMenu)mWrappedObject).addSubMenu(paramInt));
  }
  
  public SubMenu addSubMenu(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return a(((SupportMenu)mWrappedObject).addSubMenu(paramInt1, paramInt2, paramInt3, paramInt4));
  }
  
  public SubMenu addSubMenu(int paramInt1, int paramInt2, int paramInt3, CharSequence paramCharSequence)
  {
    return a(((SupportMenu)mWrappedObject).addSubMenu(paramInt1, paramInt2, paramInt3, paramCharSequence));
  }
  
  public SubMenu addSubMenu(CharSequence paramCharSequence)
  {
    return a(((SupportMenu)mWrappedObject).addSubMenu(paramCharSequence));
  }
  
  public void clear()
  {
    b();
    ((SupportMenu)mWrappedObject).clear();
  }
  
  public void close()
  {
    ((SupportMenu)mWrappedObject).close();
  }
  
  public MenuItem findItem(int paramInt)
  {
    return a(((SupportMenu)mWrappedObject).findItem(paramInt));
  }
  
  public MenuItem getItem(int paramInt)
  {
    return a(((SupportMenu)mWrappedObject).getItem(paramInt));
  }
  
  public boolean hasVisibleItems()
  {
    return ((SupportMenu)mWrappedObject).hasVisibleItems();
  }
  
  public boolean isShortcutKey(int paramInt, KeyEvent paramKeyEvent)
  {
    return ((SupportMenu)mWrappedObject).isShortcutKey(paramInt, paramKeyEvent);
  }
  
  public boolean performIdentifierAction(int paramInt1, int paramInt2)
  {
    return ((SupportMenu)mWrappedObject).performIdentifierAction(paramInt1, paramInt2);
  }
  
  public boolean performShortcut(int paramInt1, KeyEvent paramKeyEvent, int paramInt2)
  {
    return ((SupportMenu)mWrappedObject).performShortcut(paramInt1, paramKeyEvent, paramInt2);
  }
  
  public void removeGroup(int paramInt)
  {
    a(paramInt);
    ((SupportMenu)mWrappedObject).removeGroup(paramInt);
  }
  
  public void removeItem(int paramInt)
  {
    b(paramInt);
    ((SupportMenu)mWrappedObject).removeItem(paramInt);
  }
  
  public void setGroupCheckable(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    ((SupportMenu)mWrappedObject).setGroupCheckable(paramInt, paramBoolean1, paramBoolean2);
  }
  
  public void setGroupEnabled(int paramInt, boolean paramBoolean)
  {
    ((SupportMenu)mWrappedObject).setGroupEnabled(paramInt, paramBoolean);
  }
  
  public void setGroupVisible(int paramInt, boolean paramBoolean)
  {
    ((SupportMenu)mWrappedObject).setGroupVisible(paramInt, paramBoolean);
  }
  
  public void setQwertyMode(boolean paramBoolean)
  {
    ((SupportMenu)mWrappedObject).setQwertyMode(paramBoolean);
  }
  
  public int size()
  {
    return ((SupportMenu)mWrappedObject).size();
  }
}
