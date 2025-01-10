package apps.v4.animation;

import android.view.View;
import apps.v4.internal.view.AnimatorProxy;
import apps.v4.widget.IntProperty;
import b.h.b.a;

final class TIntFloatMapDecorator
  extends a<View>
{
  TIntFloatMapDecorator(String paramString)
  {
    super(paramString);
  }
  
  public Float get(View paramView)
  {
    return Float.valueOf(AnimatorProxy.wrap(paramView).getTranslationY());
  }
  
  public void setTranslationY(View paramView, float paramFloat)
  {
    AnimatorProxy.wrap(paramView).setTranslationY(paramFloat);
  }
}
