package android.support.design.transformation;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.c;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.util.List;
import org.org.android.widgets.AppBarLayout;

public abstract class ExpandableBehavior
  extends CoordinatorLayout.c<View>
{
  private int top = 0;
  
  public ExpandableBehavior() {}
  
  public ExpandableBehavior(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private boolean get(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      int i = top;
      if ((i == 0) || (i == 2)) {
        return true;
      }
    }
    else if (top == 1)
    {
      return true;
    }
    return false;
  }
  
  protected AppBarLayout onLayoutChild(CoordinatorLayout paramCoordinatorLayout, View paramView)
  {
    List localList = paramCoordinatorLayout.getDependencies(paramView);
    int i = 0;
    int j = localList.size();
    while (i < j)
    {
      View localView = (View)localList.get(i);
      if (onDependentViewChanged(paramCoordinatorLayout, paramView, localView)) {
        return (AppBarLayout)localView;
      }
      i += 1;
    }
    return null;
  }
  
  public boolean onLayoutChild(final CoordinatorLayout paramCoordinatorLayout, final View paramView, final int paramInt)
  {
    if (!ViewCompat.get(paramView))
    {
      paramCoordinatorLayout = onLayoutChild(paramCoordinatorLayout, paramView);
      if ((paramCoordinatorLayout != null) && (get(paramCoordinatorLayout.a())))
      {
        if (paramCoordinatorLayout.a()) {
          paramInt = 1;
        } else {
          paramInt = 2;
        }
        top = paramInt;
        paramInt = top;
        paramView.getViewTreeObserver().addOnPreDrawListener(new a(paramView, paramInt, paramCoordinatorLayout));
      }
    }
    return false;
  }
  
  public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
  {
    paramCoordinatorLayout = (AppBarLayout)paramView2;
    if (get(paramCoordinatorLayout.a()))
    {
      int i;
      if (paramCoordinatorLayout.a()) {
        i = 1;
      } else {
        i = 2;
      }
      top = i;
      return setChecked((View)paramCoordinatorLayout, paramView1, paramCoordinatorLayout.a(), true);
    }
    return false;
  }
  
  protected abstract boolean setChecked(View paramView1, View paramView2, boolean paramBoolean1, boolean paramBoolean2);
  
  class a
    implements ViewTreeObserver.OnPreDrawListener
  {
    a(View paramView, int paramInt, AppBarLayout paramAppBarLayout) {}
    
    public boolean onPreDraw()
    {
      paramView.getViewTreeObserver().removeOnPreDrawListener(this);
      if (ExpandableBehavior.c(ExpandableBehavior.this) == paramInt)
      {
        ExpandableBehavior localExpandableBehavior = ExpandableBehavior.this;
        AppBarLayout localAppBarLayout = paramCoordinatorLayout;
        localExpandableBehavior.setChecked((View)localAppBarLayout, paramView, localAppBarLayout.a(), false);
      }
      return false;
    }
  }
}
