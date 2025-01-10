package android.support.v7.view.menu;

import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import java.util.Iterator;
import java.util.List;

class v
  implements ViewTreeObserver.OnGlobalLayoutListener
{
  v(d paramD) {}
  
  public void onGlobalLayout()
  {
    if ((a.isShowing()) && (a.a.size() > 0) && (!a.a.get(0)).this$0.isModal()))
    {
      Object localObject = a.y;
      if ((localObject != null) && (((View)localObject).isShown())) {
        localObject = a.a.iterator();
      }
      while (((Iterator)localObject).hasNext())
      {
        nextthis$0.show();
        continue;
        a.dismiss();
      }
    }
  }
}
