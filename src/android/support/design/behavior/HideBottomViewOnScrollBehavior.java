package android.support.design.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.c;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import org.org.android.asm.Item;

public class HideBottomViewOnScrollBehavior<V extends View>
  extends CoordinatorLayout.c<V>
{
  private ViewPropertyAnimator a;
  private int d = 2;
  private int x = 0;
  
  public HideBottomViewOnScrollBehavior() {}
  
  public HideBottomViewOnScrollBehavior(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private void close(View paramView, int paramInt, long paramLong, TimeInterpolator paramTimeInterpolator)
  {
    a = paramView.animate().translationY(paramInt).setInterpolator(paramTimeInterpolator).setDuration(paramLong).setListener(new a());
  }
  
  protected void a(View paramView)
  {
    ViewPropertyAnimator localViewPropertyAnimator = a;
    if (localViewPropertyAnimator != null)
    {
      localViewPropertyAnimator.cancel();
      paramView.clearAnimation();
    }
    d = 2;
    close(paramView, 0, 225L, Item.a);
  }
  
  public void b(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((d != 1) && (paramInt2 > 0))
    {
      b(paramView1);
      return;
    }
    if ((d != 2) && (paramInt2 < 0)) {
      a(paramView1);
    }
  }
  
  protected void b(View paramView)
  {
    ViewPropertyAnimator localViewPropertyAnimator = a;
    if (localViewPropertyAnimator != null)
    {
      localViewPropertyAnimator.cancel();
      paramView.clearAnimation();
    }
    d = 1;
    close(paramView, x, 175L, Item.b);
  }
  
  public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt)
  {
    x = paramView.getMeasuredHeight();
    return super.onLayoutChild(paramCoordinatorLayout, paramView, paramInt);
  }
  
  public boolean onStartNestedScroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, View paramView3, int paramInt)
  {
    return paramInt == 2;
  }
  
  class a
    extends AnimatorListenerAdapter
  {
    a() {}
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      HideBottomViewOnScrollBehavior.b(HideBottomViewOnScrollBehavior.this, null);
    }
  }
}
