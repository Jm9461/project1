package android.support.design.widget;

import android.graphics.Outline;
import android.graphics.drawable.Drawable;

public class CircularBorderDrawableLollipop
  extends CircularBorderDrawable
{
  public CircularBorderDrawableLollipop() {}
  
  public void getOutline(Outline paramOutline)
  {
    copyBounds(mRect);
    paramOutline.setOval(mRect);
  }
}
