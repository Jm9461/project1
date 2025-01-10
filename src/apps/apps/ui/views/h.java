package apps.apps.ui.views;

import android.graphics.Canvas;
import android.view.View;

public class h
  implements Item
{
  public h() {}
  
  public void a(Canvas paramCanvas, float paramFloat, View paramView)
  {
    paramCanvas.clipRect(0.0F, (paramView.getBottom() - paramView.getTop()) * (1.0F - paramFloat), paramView.getRight(), paramView.getBottom());
  }
}
