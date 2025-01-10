package org.org.android.ui.general;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;
import org.org.android.ui.Label;
import org.org.android.ui.List;
import org.org.android.ui.c;

public class OverlayList
  extends CardView
  implements List
{
  private final c d;
  
  public void add()
  {
    d.put();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public void clear()
  {
    d.cancel();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public void draw(Canvas paramCanvas)
  {
    c localC = d;
    if (localC == null)
    {
      super.draw(paramCanvas);
      return;
    }
    localC.a(paramCanvas);
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public Drawable getCircularRevealOverlayDrawable()
  {
    d.get();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public int getCircularRevealScrimColor()
  {
    d.b();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public Label getRevealInfo()
  {
    d.a();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public boolean isOpaque()
  {
    c localC = d;
    if (localC == null) {
      return super.isOpaque();
    }
    localC.invoke();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public void setCircularRevealOverlayDrawable(Drawable paramDrawable)
  {
    d.a(paramDrawable);
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public void setCircularRevealScrimColor(int paramInt)
  {
    d.b(paramInt);
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public void setRevealInfo(Label paramLabel)
  {
    d.b(paramLabel);
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
}
