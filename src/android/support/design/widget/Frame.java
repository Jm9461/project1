package android.support.design.widget;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.design.internal.g;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import org.org.android.R.attr;
import org.org.android.R.integer;

class Frame
{
  private static final int[] a = { 16843848 };
  
  static void a(View paramView, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    Context localContext = paramView.getContext();
    paramAttributeSet = g.obtainStyledAttributes(localContext, paramAttributeSet, a, paramInt1, paramInt2, new int[0]);
    try
    {
      boolean bool = paramAttributeSet.hasValue(0);
      if (bool) {
        paramView.setStateListAnimator(AnimatorInflater.loadStateListAnimator(localContext, paramAttributeSet.getResourceId(0, 0)));
      }
      paramAttributeSet.recycle();
      return;
    }
    catch (Throwable paramView)
    {
      paramAttributeSet.recycle();
      throw paramView;
    }
  }
  
  static void init(View paramView, float paramFloat)
  {
    int i = paramView.getResources().getInteger(R.integer.app_bar_elevation_anim_duration);
    StateListAnimator localStateListAnimator = new StateListAnimator();
    int j = R.attr.state_liftable;
    int k = -R.attr.state_lifted;
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView, "elevation", new float[] { 0.0F }).setDuration(i);
    localStateListAnimator.addState(new int[] { 16842766, j, k }, localObjectAnimator);
    localObjectAnimator = ObjectAnimator.ofFloat(paramView, "elevation", new float[] { paramFloat }).setDuration(i);
    localStateListAnimator.addState(new int[] { 16842766 }, localObjectAnimator);
    localObjectAnimator = ObjectAnimator.ofFloat(paramView, "elevation", new float[] { 0.0F }).setDuration(0L);
    localStateListAnimator.addState(new int[0], localObjectAnimator);
    paramView.setStateListAnimator(localStateListAnimator);
  }
  
  static void setBoundsViewOutlineProvider(View paramView)
  {
    paramView.setOutlineProvider(ViewOutlineProvider.BOUNDS);
  }
}
