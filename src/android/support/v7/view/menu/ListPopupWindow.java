package android.support.v7.view.menu;

import android.widget.ListView;

public abstract interface ListPopupWindow
{
  public abstract ListView c();
  
  public abstract void dismiss();
  
  public abstract boolean isShowing();
  
  public abstract void show();
}
