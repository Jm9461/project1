package commons.markushi.ode;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import commons.markushi.ode.nonstiff.a;

public class RevealColorView
  extends ViewGroup
{
  private ShapeDrawable busy;
  private View button;
  
  public RevealColorView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public RevealColorView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public RevealColorView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    if (isInEditMode()) {
      return;
    }
    button = new View(paramContext);
    addView(button);
    busy = new ShapeDrawable(new OvalShape());
    a.a(button, busy);
    button.setVisibility(4);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    View localView = button;
    localView.layout(paramInt1, paramInt2, localView.getMeasuredWidth() + paramInt1, button.getMeasuredHeight() + paramInt2);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    setMeasuredDimension(paramInt1, paramInt2);
    paramInt1 = View.MeasureSpec.makeMeasureSpec((int)((float)Math.sqrt(paramInt1 * paramInt1 + paramInt2 * paramInt2) * 2.0F / 8.0F), 1073741824);
    button.measure(paramInt1, paramInt1);
  }
}
