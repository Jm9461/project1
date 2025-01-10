package android.support.v4.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface.OnKeyListener;
import android.os.Build.VERSION;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.View;
import android.view.Window;
import android.view.Window.Callback;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class i
{
  private static Field b = null;
  private static boolean c;
  private static Method d;
  private static boolean e = false;
  
  static
  {
    d = null;
    c = false;
  }
  
  private static boolean a(ActionBar paramActionBar, KeyEvent paramKeyEvent)
  {
    if (!e)
    {
      try
      {
        Object localObject = paramActionBar.getClass();
        localObject = ((Class)localObject).getMethod("onMenuKeyEvent", new Class[] { KeyEvent.class });
        d = (Method)localObject;
      }
      catch (NoSuchMethodException localNoSuchMethodException) {}
      e = true;
    }
    Method localMethod = d;
    if (localMethod != null) {
      try
      {
        paramActionBar = localMethod.invoke(paramActionBar, new Object[] { paramKeyEvent });
        paramActionBar = (Boolean)paramActionBar;
        boolean bool = paramActionBar.booleanValue();
        return bool;
      }
      catch (InvocationTargetException paramActionBar)
      {
        return false;
      }
      catch (IllegalAccessException paramActionBar) {}
    }
    return false;
  }
  
  private static boolean a(Activity paramActivity, KeyEvent paramKeyEvent)
  {
    paramActivity.onUserInteraction();
    Object localObject = paramActivity.getWindow();
    if (((Window)localObject).hasFeature(8))
    {
      ActionBar localActionBar = paramActivity.getActionBar();
      if ((paramKeyEvent.getKeyCode() == 82) && (localActionBar != null) && (a(localActionBar, paramKeyEvent))) {
        return true;
      }
    }
    if (((Window)localObject).superDispatchKeyEvent(paramKeyEvent)) {
      return true;
    }
    localObject = ((Window)localObject).getDecorView();
    if (ViewCompat.b((View)localObject, paramKeyEvent)) {
      return true;
    }
    if (localObject != null) {
      localObject = ((View)localObject).getKeyDispatcherState();
    } else {
      localObject = null;
    }
    return paramKeyEvent.dispatch(paramActivity, (KeyEvent.DispatcherState)localObject, paramActivity);
  }
  
  private static boolean a(Dialog paramDialog, KeyEvent paramKeyEvent)
  {
    Object localObject = add(paramDialog);
    if ((localObject != null) && (((DialogInterface.OnKeyListener)localObject).onKey(paramDialog, paramKeyEvent.getKeyCode(), paramKeyEvent))) {
      return true;
    }
    localObject = paramDialog.getWindow();
    if (((Window)localObject).superDispatchKeyEvent(paramKeyEvent)) {
      return true;
    }
    localObject = ((Window)localObject).getDecorView();
    if (ViewCompat.b((View)localObject, paramKeyEvent)) {
      return true;
    }
    if (localObject != null) {
      localObject = ((View)localObject).getKeyDispatcherState();
    } else {
      localObject = null;
    }
    return paramKeyEvent.dispatch(paramDialog, (KeyEvent.DispatcherState)localObject, paramDialog);
  }
  
  public static boolean a(c paramC, View paramView, Window.Callback paramCallback, KeyEvent paramKeyEvent)
  {
    if (paramC == null) {
      return false;
    }
    if (Build.VERSION.SDK_INT >= 28) {
      return paramC.b(paramKeyEvent);
    }
    if ((paramCallback instanceof Activity)) {
      return a((Activity)paramCallback, paramKeyEvent);
    }
    if ((paramCallback instanceof Dialog)) {
      return a((Dialog)paramCallback, paramKeyEvent);
    }
    return ((paramView != null) && (ViewCompat.b(paramView, paramKeyEvent))) || (paramC.b(paramKeyEvent));
  }
  
  public static boolean a(View paramView, KeyEvent paramKeyEvent)
  {
    return ViewCompat.a(paramView, paramKeyEvent);
  }
  
  private static DialogInterface.OnKeyListener add(Dialog paramDialog)
  {
    if (!c)
    {
      try
      {
        Field localField1 = Dialog.class.getDeclaredField("mOnKeyListener");
        b = localField1;
        localField1 = b;
        localField1.setAccessible(true);
      }
      catch (NoSuchFieldException localNoSuchFieldException) {}
      c = true;
    }
    Field localField2 = b;
    if (localField2 != null) {
      try
      {
        paramDialog = localField2.get(paramDialog);
        return (DialogInterface.OnKeyListener)paramDialog;
      }
      catch (IllegalAccessException paramDialog) {}
    }
    return null;
  }
}
