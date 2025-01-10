package android.support.design.widget;

import android.view.MotionEvent;
import android.view.View;

public class a
{
  private Number b;
  
  public a(SwipeDismissBehavior paramSwipeDismissBehavior)
  {
    paramSwipeDismissBehavior.setStartAlphaSwipeDistance(0.1F);
    paramSwipeDismissBehavior.setEndAlphaSwipeDistance(0.6F);
    paramSwipeDismissBehavior.setListener(0);
  }
  
  public void a(CoordinatorLayout paramCoordinatorLayout, View paramView, MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionMasked();
    if (i != 0)
    {
      if ((i != 1) && (i != 3)) {
        return;
      }
      d.a().b(b);
      return;
    }
    if (paramCoordinatorLayout.isPointInChildBounds(paramView, (int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())) {
      d.a().a(b);
    }
  }
  
  public boolean a(View paramView)
  {
    return paramView instanceof TimePicker;
  }
}
