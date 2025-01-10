package android.support.constraint;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class ProgressBar
  extends View
{
  public ProgressBar(Context paramContext)
  {
    super(paramContext);
    super.setVisibility(8);
  }
  
  public void draw(Canvas paramCanvas) {}
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(0, 0);
  }
  
  public void setGuidelineBegin(int paramInt)
  {
    ConstraintLayout.a localA = (ConstraintLayout.a)getLayoutParams();
    o = paramInt;
    setLayoutParams(localA);
  }
  
  public void setGuidelineEnd(int paramInt)
  {
    ConstraintLayout.a localA = (ConstraintLayout.a)getLayoutParams();
    j = paramInt;
    setLayoutParams(localA);
  }
  
  public void setGuidelinePercent(float paramFloat)
  {
    ConstraintLayout.a localA = (ConstraintLayout.a)getLayoutParams();
    c = paramFloat;
    setLayoutParams(localA);
  }
  
  public void setVisibility(int paramInt) {}
}
