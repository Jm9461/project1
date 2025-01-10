package org.org.asm;

import android.graphics.PointF;
import android.util.Property;
import android.view.View;

final class RefList
  extends Property<View, PointF>
{
  RefList(Class paramClass, String paramString)
  {
    super(paramClass, paramString);
  }
  
  public void a(View paramView, PointF paramPointF)
  {
    a.set(paramView, Math.round(x), Math.round(y), paramView.getRight(), paramView.getBottom());
  }
  
  public PointF put(View paramView)
  {
    return null;
  }
}
