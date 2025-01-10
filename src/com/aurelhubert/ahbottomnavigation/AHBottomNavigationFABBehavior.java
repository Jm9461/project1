package com.aurelhubert.ahbottomnavigation;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.c;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar.SnackbarLayout;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;

public class AHBottomNavigationFABBehavior
  extends CoordinatorLayout.c<FloatingActionButton>
{
  private long mTime;
  
  private void apply(FloatingActionButton paramFloatingActionButton, View paramView)
  {
    View localView = paramView;
    int i;
    if (paramFloatingActionButton != null)
    {
      localView = paramView;
      if (paramView != null)
      {
        boolean bool = paramView instanceof Snackbar.SnackbarLayout;
        localView = paramView;
        if (bool)
        {
          mTime = System.currentTimeMillis();
          i = getLayoutParamsbottomMargin;
          paramFloatingActionButton.setY(paramView.getY() - i);
          return;
        }
      }
    }
    if ((paramFloatingActionButton != null) && (localView != null) && ((localView instanceof AHBottomNavigation)))
    {
      if (System.currentTimeMillis() - mTime < 30L) {
        return;
      }
      i = getLayoutParamsbottomMargin;
      paramFloatingActionButton.setY(localView.getY() - i);
    }
  }
  
  public boolean onDependentViewChanged(CoordinatorLayout paramCoordinatorLayout, FloatingActionButton paramFloatingActionButton, View paramView)
  {
    if ((paramView != null) && ((paramView instanceof Snackbar.SnackbarLayout))) {
      return true;
    }
    if ((paramView != null) && ((paramView instanceof AHBottomNavigation))) {
      return true;
    }
    return super.onDependentViewChanged(paramCoordinatorLayout, paramFloatingActionButton, paramView);
  }
  
  public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, FloatingActionButton paramFloatingActionButton, View paramView)
  {
    apply(paramFloatingActionButton, paramView);
    return super.onLayoutChild(paramCoordinatorLayout, paramFloatingActionButton, paramView);
  }
}
