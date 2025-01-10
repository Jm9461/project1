package android.support.v7.app;

import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.view.View;

class c
  implements ViewPropertyAnimatorUpdateListener
{
  c(WindowDecorActionBar paramWindowDecorActionBar) {}
  
  public void b(View paramView)
  {
    ((View)d.mContainerView.getParent()).invalidate();
  }
}
