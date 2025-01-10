package com.aurelhubert.ahbottomnavigation;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ThemeManager
{
  public static void access$100(View paramView, int paramInt1, int paramInt2)
  {
    ValueAnimator localValueAnimator = ValueAnimator.ofFloat(new float[] { paramInt1, paramInt2 });
    localValueAnimator.setDuration(150L);
    localValueAnimator.addUpdateListener(new HoneycombMr1AnimatorCompatProvider.HoneycombValueAnimatorCompat.1(paramView));
    localValueAnimator.start();
  }
  
  public static Drawable get(Drawable paramDrawable, int paramInt, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      paramDrawable.clearColorFilter();
      paramDrawable.setColorFilter(paramInt, PorterDuff.Mode.SRC_IN);
      paramDrawable.invalidateSelf();
      return paramDrawable;
    }
    paramDrawable = DrawableCompat.wrap(paramDrawable).mutate();
    DrawableCompat.setTint(paramDrawable, paramInt);
    return paramDrawable;
  }
  
  public static void init(View paramView, int paramInt1, int paramInt2)
  {
    ValueAnimator localValueAnimator = ValueAnimator.ofFloat(new float[] { paramInt1, paramInt2 });
    localValueAnimator.setDuration(150L);
    localValueAnimator.addUpdateListener(new MainActivity.5(paramView));
    localValueAnimator.start();
  }
  
  public static void setColor(Context paramContext, Drawable paramDrawable, ImageView paramImageView, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    paramContext = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
    paramContext.setDuration(150L);
    paramContext.addUpdateListener(new ValueAnimatorCompatImplHoneycombMr1.1(paramImageView, paramDrawable, paramBoolean));
    paramContext.start();
  }
  
  public static void setColor(View paramView, float paramFloat1, float paramFloat2)
  {
    ValueAnimator localValueAnimator = ValueAnimator.ofFloat(new float[] { paramFloat1, paramFloat2 });
    localValueAnimator.setDuration(150L);
    localValueAnimator.addUpdateListener(new ImageViewTouchBase.2(paramView));
    localValueAnimator.start();
  }
  
  public static void setColor(View paramView, int paramInt1, int paramInt2)
  {
    ValueAnimator localValueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
    localValueAnimator.setDuration(150L);
    localValueAnimator.addUpdateListener(new BaseDismissAnimation.5(paramView));
    localValueAnimator.start();
  }
  
  public static void setColor(TextView paramTextView, int paramInt1, int paramInt2)
  {
    ValueAnimator localValueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
    localValueAnimator.setDuration(150L);
    localValueAnimator.addUpdateListener(new ViewPropertyAnimatorCompatKK.1(paramTextView));
    localValueAnimator.start();
  }
  
  public static void start(TextView paramTextView, float paramFloat1, float paramFloat2)
  {
    ValueAnimator localValueAnimator = ValueAnimator.ofFloat(new float[] { paramFloat1, paramFloat2 });
    localValueAnimator.setDuration(150L);
    localValueAnimator.addUpdateListener(new ViewPropertyAnimatorPreHC.AnimatorEventListener(paramTextView));
    localValueAnimator.start();
  }
  
  public static void zoomTo(View paramView, float paramFloat1, float paramFloat2)
  {
    ValueAnimator localValueAnimator = ValueAnimator.ofFloat(new float[] { paramFloat1, paramFloat2 });
    localValueAnimator.setDuration(150L);
    localValueAnimator.addUpdateListener(new ImageViewTouchBase.4(paramView));
    localValueAnimator.start();
  }
}
