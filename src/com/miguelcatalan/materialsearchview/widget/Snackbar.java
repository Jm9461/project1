package com.miguelcatalan.materialsearchview.widget;

import android.animation.Animator;
import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;

public class Snackbar
{
  public static int mType = 400;
  
  public static void show(View paramView, int paramInt, ToolbarWidgetWrapper paramToolbarWidgetWrapper)
  {
    paramView.setVisibility(0);
    paramView.setAlpha(0.0F);
    ScrollingTabContainerView.VisibilityAnimListener localVisibilityAnimListener = null;
    if (paramToolbarWidgetWrapper != null) {
      localVisibilityAnimListener = new ScrollingTabContainerView.VisibilityAnimListener(paramToolbarWidgetWrapper);
    }
    paramView = ViewCompat.animate(paramView);
    paramView.alpha(1.0F);
    paramView.setDuration(paramInt);
    paramView.setListener(localVisibilityAnimListener);
  }
  
  public static void show(View paramView, ToolbarWidgetWrapper paramToolbarWidgetWrapper)
  {
    Animator localAnimator = ViewAnimationUtils.createCircularReveal(paramView, paramView.getWidth() - (int)TypedValue.applyDimension(1, 24.0F, paramView.getResources().getDisplayMetrics()), paramView.getHeight() / 2, 0.0F, Math.max(paramView.getWidth(), paramView.getHeight()));
    paramView.setVisibility(0);
    localAnimator.addListener(new ValueAnimatorCompatImplHoneycombMr1.2(paramToolbarWidgetWrapper, paramView));
    localAnimator.start();
  }
}
