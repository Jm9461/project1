package android.support.v7.view.menu;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;

class PopupMenuHelper
  implements View.OnAttachStateChangeListener
{
  PopupMenuHelper(d paramD) {}
  
  public void onViewAttachedToWindow(View paramView) {}
  
  public void onViewDetachedFromWindow(View paramView)
  {
    Object localObject = mAnchorView.mTreeObserver;
    if (localObject != null)
    {
      if (!((ViewTreeObserver)localObject).isAlive()) {
        mAnchorView.mTreeObserver = paramView.getViewTreeObserver();
      }
      localObject = mAnchorView;
      mTreeObserver.removeGlobalOnLayoutListener(w);
    }
    paramView.removeOnAttachStateChangeListener(this);
  }
}
