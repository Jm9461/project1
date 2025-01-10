package org.org.asm;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

public class ClassReader
  extends XYPlot
{
  public ClassReader(int paramInt)
  {
    d(paramInt);
  }
  
  private static float a(Label paramLabel, float paramFloat)
  {
    float f = paramFloat;
    if (paramLabel != null)
    {
      paramLabel = (Float)c.get("android:fade:transitionAlpha");
      f = paramFloat;
      if (paramLabel != null) {
        f = paramLabel.floatValue();
      }
    }
    return f;
  }
  
  private Animator a(View paramView, float paramFloat1, float paramFloat2)
  {
    if (paramFloat1 == paramFloat2) {
      return null;
    }
    a.a(paramView, paramFloat1);
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView, a.h, new float[] { paramFloat2 });
    localObjectAnimator.addListener(new AbsActionBarView.VisibilityAnimListener(paramView));
    a(new j(this, paramView));
    return localObjectAnimator;
  }
  
  public Animator a(ViewGroup paramViewGroup, View paramView, Label paramLabel1, Label paramLabel2)
  {
    a.setEnabled(paramView);
    return a(paramView, a(paramLabel1, 1.0F), 0.0F);
  }
  
  public void a(Label paramLabel)
  {
    super.a(paramLabel);
    c.put("android:fade:transitionAlpha", Float.valueOf(a.b(a)));
  }
  
  public Animator b(ViewGroup paramViewGroup, View paramView, Label paramLabel1, Label paramLabel2)
  {
    float f2 = a(paramLabel1, 0.0F);
    float f1 = f2;
    if (f2 == 1.0F) {
      f1 = 0.0F;
    }
    return a(paramView, f1, 1.0F);
  }
}
