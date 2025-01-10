package apps.android.google.api;

import android.view.View;
import android.view.animation.Interpolator;
import apps.v4.animation.Animator;
import apps.v4.animation.Animator.AnimatorListener;
import apps.v4.animation.AnimatorSet;
import apps.v4.internal.ViewHelper;

public abstract class Object
{
  private AnimatorSet a = new AnimatorSet();
  private long n = 1000L;
  
  public Object() {}
  
  public Object add(long paramLong)
  {
    n = paramLong;
    return this;
  }
  
  public void close()
  {
    a.setDuration(n);
    a.start();
  }
  
  public AnimatorSet getItem()
  {
    return a;
  }
  
  public long length()
  {
    return n;
  }
  
  protected abstract void prepare(View paramView);
  
  public void put(View paramView)
  {
    setValue(paramView);
    prepare(paramView);
    close();
  }
  
  public Object remove(long paramLong)
  {
    getItem().setStartDelay(paramLong);
    return this;
  }
  
  public Object remove(Interpolator paramInterpolator)
  {
    a.setInterpolator(paramInterpolator);
    return this;
  }
  
  public Object remove(Animator.AnimatorListener paramAnimatorListener)
  {
    a.addListener(paramAnimatorListener);
    return this;
  }
  
  public void setValue(View paramView)
  {
    ViewHelper.setAlpha(paramView, 1.0F);
    ViewHelper.setScaleX(paramView, 1.0F);
    ViewHelper.setScaleY(paramView, 1.0F);
    ViewHelper.setTranslationX(paramView, 0.0F);
    ViewHelper.setTranslationY(paramView, 0.0F);
    ViewHelper.setRotation(paramView, 0.0F);
    ViewHelper.setRotationY(paramView, 0.0F);
    ViewHelper.setRotationX(paramView, 0.0F);
    ViewHelper.setPivotY(paramView, paramView.getMeasuredWidth() / 2.0F);
    ViewHelper.setPivotX(paramView, paramView.getMeasuredHeight() / 2.0F);
  }
}
