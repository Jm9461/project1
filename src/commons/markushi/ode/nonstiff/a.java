package commons.markushi.ode.nonstiff;

import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;

public class a
{
  public static void a(View paramView, Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramView.setBackground(paramDrawable);
      return;
    }
    paramView.setBackgroundDrawable(paramDrawable);
  }
}
