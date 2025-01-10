package android.support.v4.app;

import android.app.Activity;
import android.arch.lifecycle.BaseFragment;
import android.arch.lifecycle.d;
import android.arch.lifecycle.h;
import android.arch.lifecycle.m;
import android.os.Bundle;
import android.support.v4.view.c;
import android.support.v4.view.i;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

public class g
  extends Activity
  implements h, c
{
  private m a;
  
  public g()
  {
    new org.org.jaxen.util.f();
    a = new m(this);
  }
  
  public d a()
  {
    return a;
  }
  
  public boolean b(KeyEvent paramKeyEvent)
  {
    return super.dispatchKeyEvent(paramKeyEvent);
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    View localView = getWindow().getDecorView();
    if ((localView != null) && (i.a(localView, paramKeyEvent))) {
      return true;
    }
    return i.a(this, localView, this, paramKeyEvent);
  }
  
  public boolean dispatchKeyShortcutEvent(KeyEvent paramKeyEvent)
  {
    View localView = getWindow().getDecorView();
    if ((localView != null) && (i.a(localView, paramKeyEvent))) {
      return true;
    }
    return super.dispatchKeyShortcutEvent(paramKeyEvent);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    BaseFragment.showDialog(this);
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    a.d(android.arch.lifecycle.f.a);
    super.onSaveInstanceState(paramBundle);
  }
}
