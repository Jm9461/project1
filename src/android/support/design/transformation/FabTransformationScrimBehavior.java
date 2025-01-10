package android.support.design.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.c;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import org.org.android.asm.Label;
import org.org.android.asm.Plot.a;

public class FabTransformationScrimBehavior
  extends ExpandableTransformationBehavior
{
  private final Label labels = new Label(0L, 150L);
  private final Label this$0 = new Label(75L, 150L);
  
  public FabTransformationScrimBehavior() {}
  
  public FabTransformationScrimBehavior(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private void show(View paramView, boolean paramBoolean1, boolean paramBoolean2, List paramList1, List paramList2)
  {
    if (paramBoolean1) {
      paramList2 = this$0;
    } else {
      paramList2 = labels;
    }
    if (paramBoolean1)
    {
      if (!paramBoolean2) {
        paramView.setAlpha(0.0F);
      }
      paramView = ObjectAnimator.ofFloat(paramView, View.ALPHA, new float[] { 1.0F });
    }
    else
    {
      paramView = ObjectAnimator.ofFloat(paramView, View.ALPHA, new float[] { 0.0F });
    }
    paramList2.add(paramView);
    paramList1.add(paramView);
  }
  
  protected AnimatorSet a(View paramView1, final View paramView2, final boolean paramBoolean1, boolean paramBoolean2)
  {
    paramView1 = new ArrayList();
    show(paramView2, paramBoolean1, paramBoolean2, paramView1, new ArrayList());
    AnimatorSet localAnimatorSet = new AnimatorSet();
    Plot.a.run(localAnimatorSet, paramView1);
    localAnimatorSet.addListener(new a(paramBoolean1, paramView2));
    return localAnimatorSet;
  }
  
  public boolean onDependentViewChanged(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
  {
    return paramView2 instanceof FloatingActionButton;
  }
  
  public boolean onTouchEvent(CoordinatorLayout paramCoordinatorLayout, View paramView, MotionEvent paramMotionEvent)
  {
    return super.onTouchEvent(paramCoordinatorLayout, paramView, paramMotionEvent);
  }
  
  class a
    extends AnimatorListenerAdapter
  {
    a(boolean paramBoolean, View paramView) {}
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      if (!paramBoolean1) {
        paramView2.setVisibility(4);
      }
    }
    
    public void onAnimationStart(Animator paramAnimator)
    {
      if (paramBoolean1) {
        paramView2.setVisibility(0);
      }
    }
  }
}
