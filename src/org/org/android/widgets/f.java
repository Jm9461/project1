package org.org.android.widgets;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewParent;

public final class f
{
  private final View a;
  private boolean b = false;
  private int c = 0;
  
  public f(AppBarLayout paramAppBarLayout)
  {
    a = ((View)paramAppBarLayout);
  }
  
  private void b()
  {
    ViewParent localViewParent = a.getParent();
    if ((localViewParent instanceof CoordinatorLayout)) {
      ((CoordinatorLayout)localViewParent).dispatchDependentViewsChanged(a);
    }
  }
  
  public boolean a()
  {
    return b;
  }
  
  public int c()
  {
    return c;
  }
  
  public void c(int paramInt)
  {
    c = paramInt;
  }
  
  public Bundle d()
  {
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("expanded", b);
    localBundle.putInt("expandedComponentIdHint", c);
    return localBundle;
  }
  
  public void d(Bundle paramBundle)
  {
    b = paramBundle.getBoolean("expanded", false);
    c = paramBundle.getInt("expandedComponentIdHint", 0);
    if (b) {
      b();
    }
  }
}
