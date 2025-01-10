package android.support.constraint;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.constraint.asm.asm.d;
import android.view.View;

public class MethodWriter
  extends View
{
  private View c;
  private int i;
  private int l;
  
  public void a(ConstraintLayout paramConstraintLayout)
  {
    if ((l == -1) && (!isInEditMode())) {
      setVisibility(i);
    }
    c = paramConstraintLayout.findViewById(l);
    paramConstraintLayout = c;
    if (paramConstraintLayout != null)
    {
      getLayoutParamse = true;
      c.setVisibility(0);
      setVisibility(0);
    }
  }
  
  public void c(ConstraintLayout paramConstraintLayout)
  {
    if (c == null) {
      return;
    }
    paramConstraintLayout = (ConstraintLayout.a)getLayoutParams();
    ConstraintLayout.a localA = (ConstraintLayout.a)c.getLayoutParams();
    a.g(0);
    a.d(a.size());
    a.c(a.getValue());
    a.g(8);
  }
  
  public View getContent()
  {
    return c;
  }
  
  public int getEmptyVisibility()
  {
    return i;
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if (isInEditMode())
    {
      paramCanvas.drawRGB(223, 223, 223);
      Paint localPaint = new Paint();
      localPaint.setARGB(255, 210, 210, 210);
      localPaint.setTextAlign(Paint.Align.CENTER);
      localPaint.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
      Rect localRect = new Rect();
      paramCanvas.getClipBounds(localRect);
      localPaint.setTextSize(localRect.height());
      int j = localRect.height();
      int k = localRect.width();
      localPaint.setTextAlign(Paint.Align.LEFT);
      localPaint.getTextBounds("?", 0, "?".length(), localRect);
      paramCanvas.drawText("?", k / 2.0F - localRect.width() / 2.0F - left, j / 2.0F + localRect.height() / 2.0F - bottom, localPaint);
    }
  }
  
  public void setContentId(int paramInt)
  {
    if (l == paramInt) {
      return;
    }
    View localView = c;
    if (localView != null)
    {
      localView.setVisibility(0);
      c.getLayoutParams()).e = false;
      c = null;
    }
    l = paramInt;
    if (paramInt != -1)
    {
      localView = ((View)getParent()).findViewById(paramInt);
      if (localView != null) {
        localView.setVisibility(8);
      }
    }
  }
  
  public void setEmptyVisibility(int paramInt)
  {
    i = paramInt;
  }
}
