package android.support.design.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.List;
import org.org.jaxen.expr.MathUtils;

abstract class HeaderScrollingViewBehavior
  extends x<View>
{
  private int mOverlayTop;
  final Rect mTempRect1 = new Rect();
  final Rect mTempRect2 = new Rect();
  private int mVerticalLayoutGap = 0;
  
  public HeaderScrollingViewBehavior() {}
  
  public HeaderScrollingViewBehavior(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private static int resolveGravity(int paramInt)
  {
    if (paramInt == 0) {
      return 8388659;
    }
    return paramInt;
  }
  
  abstract View findFirstDependency(List paramList);
  
  final int getOverlapPixelsForOffset(View paramView)
  {
    if (mOverlayTop == 0) {
      return 0;
    }
    float f = getOverlapRatioForOffset(paramView);
    int i = mOverlayTop;
    return MathUtils.constrain((int)(f * i), 0, i);
  }
  
  abstract float getOverlapRatioForOffset(View paramView);
  
  public final int getOverlayTop()
  {
    return mOverlayTop;
  }
  
  int getScrollRange(View paramView)
  {
    return paramView.getMeasuredHeight();
  }
  
  final int getVerticalLayoutGap()
  {
    return mVerticalLayoutGap;
  }
  
  protected void layoutChild(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt)
  {
    View localView = findFirstDependency(paramCoordinatorLayout.getDependencies(paramView));
    if (localView != null)
    {
      CoordinatorLayout.f localF = (CoordinatorLayout.f)paramView.getLayoutParams();
      Rect localRect = mTempRect1;
      localRect.set(paramCoordinatorLayout.getPaddingLeft() + leftMargin, localView.getBottom() + topMargin, paramCoordinatorLayout.getWidth() - paramCoordinatorLayout.getPaddingRight() - rightMargin, paramCoordinatorLayout.getHeight() + localView.getBottom() - paramCoordinatorLayout.getPaddingBottom() - bottomMargin);
      WindowInsetsCompat localWindowInsetsCompat = paramCoordinatorLayout.getLastWindowInsets();
      if ((localWindowInsetsCompat != null) && (ViewCompat.getFitsSystemWindows(paramCoordinatorLayout)) && (!ViewCompat.getFitsSystemWindows(paramView)))
      {
        left += localWindowInsetsCompat.getSystemWindowInsetLeft();
        right -= localWindowInsetsCompat.getSystemWindowInsetRight();
      }
      paramCoordinatorLayout = mTempRect2;
      GravityCompat.apply(resolveGravity(gravity), paramView.getMeasuredWidth(), paramView.getMeasuredHeight(), localRect, paramCoordinatorLayout, paramInt);
      paramInt = getOverlapPixelsForOffset(localView);
      paramView.layout(left, top - paramInt, right, bottom - paramInt);
      mVerticalLayoutGap = (top - localView.getBottom());
      return;
    }
    super.layoutChild(paramCoordinatorLayout, paramView, paramInt);
    mVerticalLayoutGap = 0;
  }
  
  public boolean onMeasureChild(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j = getLayoutParamsheight;
    if ((j != -1) && (j != -2)) {
      break label159;
    }
    View localView = findFirstDependency(paramCoordinatorLayout.getDependencies(paramView));
    if (localView != null)
    {
      if ((ViewCompat.getFitsSystemWindows(localView)) && (!ViewCompat.getFitsSystemWindows(paramView)))
      {
        ViewCompat.setFitsSystemWindows(paramView, true);
        if (ViewCompat.getFitsSystemWindows(paramView))
        {
          paramView.requestLayout();
          return true;
        }
      }
      int i = View.MeasureSpec.getSize(paramInt3);
      paramInt3 = i;
      if (i == 0) {
        paramInt3 = paramCoordinatorLayout.getHeight();
      }
      int k = localView.getMeasuredHeight();
      int m = getScrollRange(localView);
      if (j == -1) {
        i = 1073741824;
      } else {
        i = Integer.MIN_VALUE;
      }
      paramCoordinatorLayout.onMeasureChild(paramView, paramInt1, paramInt2, View.MeasureSpec.makeMeasureSpec(paramInt3 - k + m, i), paramInt4);
      return true;
    }
    label159:
    return false;
  }
  
  public final void setOverlayTop(int paramInt)
  {
    mOverlayTop = paramInt;
  }
}
