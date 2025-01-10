package org.org.asm;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

class e
  implements Object
{
  private final ViewOverlay j;
  
  e(View paramView)
  {
    j = paramView.getOverlay();
  }
  
  public void a(Drawable paramDrawable)
  {
    j.add(paramDrawable);
  }
  
  public void b(Drawable paramDrawable)
  {
    j.remove(paramDrawable);
  }
}
