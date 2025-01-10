package apps.v4.animation;

import android.view.View;
import apps.v4.internal.view.AnimatorProxy;
import apps.v4.widget.IntProperty;
import b.h.b.a;

final class TDoubleDoubleMapDecorator
  extends a<View>
{
  TDoubleDoubleMapDecorator(String paramString)
  {
    super(paramString);
  }
  
  public Float get(View paramView)
  {
    return Float.valueOf(AnimatorProxy.wrap(paramView).getAlpha());
  }
  
  public void setValue(View paramView, float paramFloat)
  {
    AnimatorProxy.wrap(paramView).setAlpha(paramFloat);
  }
}
