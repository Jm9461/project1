package com.mohamadamin.persianmaterialdatetimepicker;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Build.VERSION;
import android.view.View;
import com.mohamadamin.persianmaterialdatetimepicker.views.Label;

public class Utils
{
  public static int getDaysInMonth(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 6) {
      return 31;
    }
    if (paramInt1 < 11) {
      return 30;
    }
    if (Label.a(paramInt2)) {
      return 30;
    }
    return 29;
  }
  
  public static ObjectAnimator getPulseAnimator(View paramView, float paramFloat1, float paramFloat2)
  {
    Keyframe localKeyframe1 = Keyframe.ofFloat(0.0F, 1.0F);
    Keyframe localKeyframe2 = Keyframe.ofFloat(0.275F, paramFloat1);
    Keyframe localKeyframe3 = Keyframe.ofFloat(0.69F, paramFloat2);
    Keyframe localKeyframe4 = Keyframe.ofFloat(1.0F, 1.0F);
    paramView = ObjectAnimator.ofPropertyValuesHolder(paramView, new PropertyValuesHolder[] { PropertyValuesHolder.ofKeyframe("scaleX", new Keyframe[] { localKeyframe1, localKeyframe2, localKeyframe3, localKeyframe4 }), PropertyValuesHolder.ofKeyframe("scaleY", new Keyframe[] { localKeyframe1, localKeyframe2, localKeyframe3, localKeyframe4 }) });
    paramView.setDuration(544L);
    return paramView;
  }
  
  public static boolean isJellybeanOrLater()
  {
    return Build.VERSION.SDK_INT >= 16;
  }
  
  public static void tryAccessibilityAnnounce(View paramView, CharSequence paramCharSequence)
  {
    if ((isJellybeanOrLater()) && (paramView != null) && (paramCharSequence != null)) {
      paramView.announceForAccessibility(paramCharSequence);
    }
  }
}
