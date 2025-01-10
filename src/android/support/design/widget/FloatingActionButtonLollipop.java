package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.AnimatorSet.Builder;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.List;
import org.org.android.utils.ThemeUtils;

class FloatingActionButtonLollipop
  extends FloatingActionButtonImpl
{
  private InsetDrawable mInsetDrawable;
  
  FloatingActionButtonLollipop(VisibilityAwareImageButton paramVisibilityAwareImageButton, ShadowViewDelegate paramShadowViewDelegate)
  {
    super(paramVisibilityAwareImageButton, paramShadowViewDelegate);
  }
  
  private Animator hide(float paramFloat1, float paramFloat2)
  {
    AnimatorSet localAnimatorSet = new AnimatorSet();
    localAnimatorSet.play(ObjectAnimator.ofFloat(this$0, "elevation", new float[] { paramFloat1 }).setDuration(0L)).with(ObjectAnimator.ofFloat(this$0, View.TRANSLATION_Z, new float[] { paramFloat2 }).setDuration(100L));
    localAnimatorSet.setInterpolator(FloatingActionButtonImpl.a);
    return localAnimatorSet;
  }
  
  public float getElevation()
  {
    return this$0.getElevation();
  }
  
  void getPadding(Rect paramRect)
  {
    if (mShadowViewDelegate.setBackgroundDrawable())
    {
      float f1 = mShadowViewDelegate.getRadius();
      float f2 = getElevation() + mPressedTranslationZ;
      int i = (int)Math.ceil(ShadowDrawableWrapper.calculateHorizontalPadding(f2, f1, false));
      int j = (int)Math.ceil(ShadowDrawableWrapper.calculateVerticalPadding(f2, f1, false));
      paramRect.set(i, j, i, j);
      return;
    }
    paramRect.set(0, 0, 0, 0);
  }
  
  GradientDrawable init()
  {
    return new TransitionDrawable();
  }
  
  void init(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (Build.VERSION.SDK_INT == 21)
    {
      this$0.refreshDrawableState();
    }
    else
    {
      StateListAnimator localStateListAnimator = new StateListAnimator();
      localStateListAnimator.addState(FloatingActionButtonImpl.p, hide(paramFloat1, paramFloat3));
      localStateListAnimator.addState(FloatingActionButtonImpl.r, hide(paramFloat1, paramFloat2));
      localStateListAnimator.addState(FloatingActionButtonImpl.t, hide(paramFloat1, paramFloat2));
      localStateListAnimator.addState(FloatingActionButtonImpl.q, hide(paramFloat1, paramFloat2));
      AnimatorSet localAnimatorSet = new AnimatorSet();
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(ObjectAnimator.ofFloat(this$0, "elevation", new float[] { paramFloat1 }).setDuration(0L));
      int i = Build.VERSION.SDK_INT;
      if ((i >= 22) && (i <= 24))
      {
        VisibilityAwareImageButton localVisibilityAwareImageButton = this$0;
        localArrayList.add(ObjectAnimator.ofFloat(localVisibilityAwareImageButton, View.TRANSLATION_Z, new float[] { localVisibilityAwareImageButton.getTranslationZ() }).setDuration(100L));
      }
      localArrayList.add(ObjectAnimator.ofFloat(this$0, View.TRANSLATION_Z, new float[] { 0.0F }).setDuration(100L));
      localAnimatorSet.playSequentially((Animator[])localArrayList.toArray(new Animator[0]));
      localAnimatorSet.setInterpolator(FloatingActionButtonImpl.a);
      localStateListAnimator.addState(FloatingActionButtonImpl.s, localAnimatorSet);
      localStateListAnimator.addState(FloatingActionButtonImpl.i, hide(0.0F, 0.0F));
      this$0.setStateListAnimator(localStateListAnimator);
    }
    if (mShadowViewDelegate.setBackgroundDrawable()) {
      updatePadding();
    }
  }
  
  CircularBorderDrawable newCircularDrawable()
  {
    return new CircularBorderDrawableLollipop();
  }
  
  void onCompatShadowChanged()
  {
    updatePadding();
  }
  
  void onPaddingUpdated(Rect paramRect)
  {
    if (mShadowViewDelegate.setBackgroundDrawable())
    {
      mInsetDrawable = new InsetDrawable(mRippleDrawable, left, top, right, bottom);
      mShadowViewDelegate.setBackgroundDrawable(mInsetDrawable);
      return;
    }
    mShadowViewDelegate.setBackgroundDrawable(mRippleDrawable);
  }
  
  boolean requirePreDrawListener()
  {
    return false;
  }
  
  void setBackgroundDrawable(ColorStateList paramColorStateList1, PorterDuff.Mode paramMode, ColorStateList paramColorStateList2, int paramInt)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a6 = a5\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:552)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:1)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:166)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:331)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:387)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:90)\n\t... 17 more\n");
  }
  
  void setPressedTranslationZ() {}
  
  void setRippleColor(ColorStateList paramColorStateList)
  {
    Drawable localDrawable = mRippleDrawable;
    if ((localDrawable instanceof RippleDrawable))
    {
      ((RippleDrawable)localDrawable).setColor(ThemeUtils.createDefaultColorStateList(paramColorStateList));
      return;
    }
    super.setRippleColor(paramColorStateList);
  }
  
  void show(int[] paramArrayOfInt)
  {
    if (Build.VERSION.SDK_INT == 21)
    {
      if (this$0.isEnabled())
      {
        this$0.setElevation(mElevation);
        if (this$0.isPressed())
        {
          this$0.setTranslationZ(mPressedTranslationZ);
          return;
        }
        if ((!this$0.isFocused()) && (!this$0.isHovered()))
        {
          this$0.setTranslationZ(0.0F);
          return;
        }
        this$0.setTranslationZ(mView);
        return;
      }
      this$0.setElevation(0.0F);
      this$0.setTranslationZ(0.0F);
    }
  }
}
