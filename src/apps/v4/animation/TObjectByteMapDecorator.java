package apps.v4.animation;

import android.view.View;
import apps.v4.internal.view.AnimatorProxy;
import apps.v4.widget.IntProperty;
import b.h.b.a;

final class TObjectByteMapDecorator
  extends a<View>
{
  TObjectByteMapDecorator(String paramString)
  {
    super(paramString);
  }
  
  public Float get(View paramView)
  {
    return Float.valueOf(AnimatorProxy.wrap(paramView).getRotationY());
  }
  
  public void setValue(View paramView, float paramFloat)
  {
    AnimatorProxy.wrap(paramView).setRotationY(paramFloat);
  }
}
