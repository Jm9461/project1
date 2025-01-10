package android.support.v7.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.view.View;

class g
  implements i
{
  final Toolbar a;
  final Drawable b;
  final CharSequence c;
  
  g(Toolbar paramToolbar)
  {
    a = paramToolbar;
    b = paramToolbar.getNavigationIcon();
    c = paramToolbar.getNavigationContentDescription();
  }
  
  public Drawable a()
  {
    return b;
  }
  
  public void a(int paramInt)
  {
    if (paramInt == 0)
    {
      a.setNavigationContentDescription(c);
      return;
    }
    a.setNavigationContentDescription(paramInt);
  }
  
  public void a(Drawable paramDrawable, int paramInt)
  {
    a.setNavigationIcon(paramDrawable);
    a(paramInt);
  }
  
  public Context b()
  {
    return a.getContext();
  }
  
  public boolean c()
  {
    return true;
  }
}
