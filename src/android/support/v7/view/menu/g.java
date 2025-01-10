package android.support.v7.view.menu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.os.IBinder;
import android.support.v7.app.e.a;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Adapter;
import org.org.v4.content.R.layout;

class g
  implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, l.a
{
  e a;
  private f b;
  private android.support.v7.app.e c;
  private l.a d;
  
  public g(f paramF)
  {
    b = paramF;
  }
  
  public void a()
  {
    android.support.v7.app.e localE = c;
    if (localE != null) {
      localE.dismiss();
    }
  }
  
  public void a(IBinder paramIBinder)
  {
    Object localObject = b;
    e.a localA = new e.a(((f)localObject).getContext());
    a = new e(localA.getCount(), R.layout.abc_list_menu_item_layout);
    a.a(this);
    b.a(a);
    localA.a(a.a(), this);
    View localView = ((f)localObject).b();
    if (localView != null)
    {
      localA.b(localView);
    }
    else
    {
      localA.a(((f)localObject).p());
      localA.b(((f)localObject).a());
    }
    localA.b(this);
    c = localA.a();
    c.setOnDismissListener(this);
    localObject = c.getWindow().getAttributes();
    type = 1003;
    if (paramIBinder != null) {
      token = paramIBinder;
    }
    flags |= 0x20000;
    c.show();
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    b.a((MenuItemImpl)a.a().getItem(paramInt), 0);
  }
  
  public void onCloseMenu(f paramF, boolean paramBoolean)
  {
    if ((paramBoolean) || (paramF == b)) {
      a();
    }
    l.a localA = d;
    if (localA != null) {
      localA.onCloseMenu(paramF, paramBoolean);
    }
  }
  
  public void onDismiss(DialogInterface paramDialogInterface)
  {
    a.a(b, true);
  }
  
  public boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 82) || (paramInt == 4)) {
      if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.getRepeatCount() == 0))
      {
        paramDialogInterface = c.getWindow();
        if (paramDialogInterface != null)
        {
          paramDialogInterface = paramDialogInterface.getDecorView();
          if (paramDialogInterface != null)
          {
            paramDialogInterface = paramDialogInterface.getKeyDispatcherState();
            if (paramDialogInterface != null)
            {
              paramDialogInterface.startTracking(paramKeyEvent, this);
              return true;
            }
          }
        }
      }
      else if ((paramKeyEvent.getAction() == 1) && (!paramKeyEvent.isCanceled()))
      {
        Object localObject = c.getWindow();
        if (localObject != null)
        {
          localObject = ((Window)localObject).getDecorView();
          if (localObject != null)
          {
            localObject = ((View)localObject).getKeyDispatcherState();
            if ((localObject != null) && (((KeyEvent.DispatcherState)localObject).isTracking(paramKeyEvent)))
            {
              b.close(true);
              paramDialogInterface.dismiss();
              return true;
            }
          }
        }
      }
    }
    return b.performShortcut(paramInt, paramKeyEvent, 0);
  }
  
  public boolean onOpenSubMenu(f paramF)
  {
    l.a localA = d;
    if (localA != null) {
      return localA.onOpenSubMenu(paramF);
    }
    return false;
  }
}
