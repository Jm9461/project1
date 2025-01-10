package com.aurelhubert.ahbottomnavigation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.c;
import android.support.design.widget.Snackbar.SnackbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Interpolator;

public class AHBottomNavigationBehavior<V extends View>
  extends VerticalScrollingBehavior<V>
{
  private static final Interpolator DECELERATE_INTERPOLATOR = new LinearOutSlowInInterpolator();
  private int b = -1;
  private boolean c = true;
  private boolean e = false;
  private float j = 0.0F;
  private float l = 0.0F;
  private int mAnchorId;
  private TabLayout mAnchorView;
  private ObjectAnimator mAnimator;
  private ViewPropertyAnimatorCompat mImpl;
  private Snackbar.SnackbarLayout mView;
  private AHBottomNavigation.f updater;
  
  public AHBottomNavigationBehavior() {}
  
  public AHBottomNavigationBehavior(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AHBottomNavigationBehavior_Params);
    mAnchorId = paramContext.getResourceId(R.styleable.AHBottomNavigationBehavior_Params_tabLayoutId, -1);
    paramContext.recycle();
  }
  
  public AHBottomNavigationBehavior(boolean paramBoolean, int paramInt)
  {
    c = paramBoolean;
  }
  
  private void a(View paramView, int paramInt)
  {
    if (!c) {
      return;
    }
    if ((paramInt == -1) && (e))
    {
      e = false;
      show(paramView, 0, false, true);
      return;
    }
    if ((paramInt == 1) && (!e))
    {
      e = true;
      show(paramView, paramView.getHeight(), false, true);
    }
  }
  
  private void fadeIn(final View paramView, int paramInt, boolean paramBoolean)
  {
    ObjectAnimator localObjectAnimator = mAnimator;
    if (localObjectAnimator != null) {
      localObjectAnimator.cancel();
    }
    mAnimator = ObjectAnimator.ofFloat(paramView, View.TRANSLATION_Y, new float[] { paramInt });
    localObjectAnimator = mAnimator;
    long l1;
    if (paramBoolean) {
      l1 = 300L;
    } else {
      l1 = 0L;
    }
    localObjectAnimator.setDuration(l1);
    mAnimator.setInterpolator(DECELERATE_INTERPOLATOR);
    mAnimator.addUpdateListener(new b(paramView));
  }
  
  private TabLayout onCreateView(View paramView)
  {
    int i = mAnchorId;
    if (i == 0) {
      return null;
    }
    return (TabLayout)paramView.findViewById(i);
  }
  
  private void show(View paramView, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((!c) && (!paramBoolean1)) {
      return;
    }
    if (Build.VERSION.SDK_INT < 19)
    {
      fadeIn(paramView, paramInt, paramBoolean2);
      mAnimator.start();
      return;
    }
    updateVisibility(paramView, paramBoolean2);
    paramView = mImpl;
    paramView.translationY(paramInt);
    paramView.start();
  }
  
  private void updateVisibility(View paramView, boolean paramBoolean)
  {
    ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat = mImpl;
    long l1 = 300L;
    if (localViewPropertyAnimatorCompat == null)
    {
      mImpl = ViewCompat.animate(paramView);
      paramView = mImpl;
      if (!paramBoolean) {
        l1 = 0L;
      }
      paramView.setDuration(l1);
      mImpl.setUpdateListener(new a());
      mImpl.setInterpolator(DECELERATE_INTERPOLATOR);
      return;
    }
    if (!paramBoolean) {
      l1 = 0L;
    }
    localViewPropertyAnimatorCompat.setDuration(l1);
    mImpl.cancel();
  }
  
  public void a(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void a(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, int paramInt1, int paramInt2, int[] paramArrayOfInt, int paramInt3) {}
  
  public void a(View paramView, int paramInt, boolean paramBoolean)
  {
    if (!e)
    {
      e = true;
      show(paramView, paramInt, true, paramBoolean);
    }
  }
  
  public void a(boolean paramBoolean, int paramInt)
  {
    c = paramBoolean;
  }
  
  public void b(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.b(paramCoordinatorLayout, paramView1, paramView2, paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt2 < 0)
    {
      a(paramView1, -1);
      return;
    }
    if (paramInt2 > 0) {
      a(paramView1, 1);
    }
  }
  
  public void b(AHBottomNavigation.f paramF)
  {
    updater = paramF;
  }
  
  public void init(View paramView1, View paramView2)
  {
    if ((paramView2 != null) && ((paramView2 instanceof Snackbar.SnackbarLayout)))
    {
      mView = ((Snackbar.SnackbarLayout)paramView2);
      if (b == -1) {
        b = paramView2.getHeight();
      }
      int i = (int)(paramView1.getMeasuredHeight() - paramView1.getTranslationY());
      if (Build.VERSION.SDK_INT < 21) {
        paramView1.bringToFront();
      }
      if ((paramView2.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
      {
        paramView1 = (ViewGroup.MarginLayoutParams)paramView2.getLayoutParams();
        paramView1.setMargins(leftMargin, topMargin, rightMargin, i);
        paramView2.requestLayout();
      }
    }
  }
  
  public boolean onDependentViewChanged(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
  {
    if ((paramView2 != null) && ((paramView2 instanceof Snackbar.SnackbarLayout)))
    {
      init(paramView1, paramView2);
      return true;
    }
    return super.onDependentViewChanged(paramCoordinatorLayout, paramView1, paramView2);
  }
  
  public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt)
  {
    boolean bool = super.onLayoutChild(paramCoordinatorLayout, paramView, paramInt);
    if ((mAnchorView == null) && (mAnchorId != -1)) {
      mAnchorView = onCreateView(paramView);
    }
    return bool;
  }
  
  public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
  {
    return super.onLayoutChild(paramCoordinatorLayout, paramView1, paramView2);
  }
  
  protected boolean onNestedFling(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, float paramFloat1, float paramFloat2, int paramInt)
  {
    return false;
  }
  
  public void onNestedPreScroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
  {
    super.onNestedPreScroll(paramCoordinatorLayout, paramView1, paramView2);
  }
  
  public boolean onStartNestedScroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, View paramView3, int paramInt)
  {
    return (paramInt == 2) || (super.onStartNestedScroll(paramCoordinatorLayout, paramView1, paramView2, paramView3, paramInt));
  }
  
  class a
    implements ViewPropertyAnimatorUpdateListener
  {
    a() {}
    
    public void b(View paramView)
    {
      if (AHBottomNavigationBehavior.access$getUpdater(AHBottomNavigationBehavior.this) != null) {
        AHBottomNavigationBehavior.access$getUpdater(AHBottomNavigationBehavior.this).setHeight((int)(paramView.getMeasuredHeight() - paramView.getTranslationY() + AHBottomNavigationBehavior.b(AHBottomNavigationBehavior.this)));
      }
    }
  }
  
  class b
    implements ValueAnimator.AnimatorUpdateListener
  {
    b(View paramView) {}
    
    public void onAnimationUpdate(ValueAnimator paramValueAnimator)
    {
      if ((AHBottomNavigationBehavior.access$getMView(AHBottomNavigationBehavior.this) != null) && ((AHBottomNavigationBehavior.access$getMView(AHBottomNavigationBehavior.this).getLayoutParams() instanceof ViewGroup.MarginLayoutParams)))
      {
        AHBottomNavigationBehavior.updateProgress(AHBottomNavigationBehavior.this, paramView.getMeasuredHeight() - paramView.getTranslationY());
        paramValueAnimator = (ViewGroup.MarginLayoutParams)AHBottomNavigationBehavior.access$getMView(AHBottomNavigationBehavior.this).getLayoutParams();
        paramValueAnimator.setMargins(leftMargin, topMargin, rightMargin, (int)AHBottomNavigationBehavior.access$getL(AHBottomNavigationBehavior.this));
        AHBottomNavigationBehavior.access$getMView(AHBottomNavigationBehavior.this).requestLayout();
      }
      if (AHBottomNavigationBehavior.access$getUpdater(AHBottomNavigationBehavior.this) != null) {
        AHBottomNavigationBehavior.access$getUpdater(AHBottomNavigationBehavior.this).setHeight((int)(paramView.getMeasuredHeight() - paramView.getTranslationY() + AHBottomNavigationBehavior.b(AHBottomNavigationBehavior.this)));
      }
    }
  }
}
