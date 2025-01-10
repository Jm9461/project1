package android.support.v7.widget;

import android.content.Context;
import android.support.v7.view.menu.f;
import android.support.v7.view.menu.w;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import org.org.v4.content.R.attr;
import org.org.v4.view.SupportMenuInflater;

public class PopupMenu
{
  private final Context mActionModeContext;
  final w mAdapter;
  u0.c mContext;
  private final f mMenu;
  u0.d mMenuItemClickListener;
  
  public PopupMenu(Context paramContext, View paramView)
  {
    this(paramContext, paramView, 0);
  }
  
  public PopupMenu(Context paramContext, View paramView, int paramInt)
  {
    this(paramContext, paramView, paramInt, R.attr.popupMenuStyle, 0);
  }
  
  public PopupMenu(Context paramContext, View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    mActionModeContext = paramContext;
    mMenu = new f(paramContext);
    mMenu.setCallback(new u0.a(this));
    mAdapter = new w(paramContext, mMenu, paramView, false, paramInt2, paramInt3);
    mAdapter.a(paramInt1);
    mAdapter.a(new u0.b(this));
  }
  
  public Menu getMenu()
  {
    return mMenu;
  }
  
  public MenuInflater getMenuInflater()
  {
    return new SupportMenuInflater(mActionModeContext);
  }
  
  public void setOnMenuItemClickListener(u0.d paramD)
  {
    mMenuItemClickListener = paramD;
  }
  
  public void show()
  {
    mAdapter.show();
  }
}
