package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class FitWindowsFrameLayout
  extends FrameLayout
  implements FitWindowsViewGroup
{
  private k0.a mListener;
  
  public FitWindowsFrameLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  public FitWindowsFrameLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected boolean fitSystemWindows(Rect paramRect)
  {
    k0.a localA = mListener;
    if (localA != null) {
      localA.onFitSystemWindows(paramRect);
    }
    return super.fitSystemWindows(paramRect);
  }
  
  public void setOnFitSystemWindowsListener(k0.a paramA)
  {
    mListener = paramA;
  }
}
