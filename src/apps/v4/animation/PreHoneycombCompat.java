package apps.v4.animation;

import android.view.View;
import apps.v4.internal.view.AnimatorProxy;
import apps.v4.widget.FloatProperty;
import apps.v4.widget.IntProperty;
import b.h.b.a;
import b.h.b.b;
import b.h.b.c;

final class PreHoneycombCompat
{
  static c<View, Float> ALPHA = new TObjectDoubleMapDecorator("y");
  static c<View, Float> PIVOT_X;
  static c<View, Float> PIVOT_Y;
  static c<View, Float> ROTATION;
  static c<View, Float> ROTATION_X;
  static c<View, Float> ROTATION_Y;
  static c<View, Float> SCALE_X;
  static c<View, Integer> SCALE_Y;
  static c<View, Float> SCROLL_X = new TDoubleDoubleMapDecorator("alpha");
  static c<View, Float> SCROLL_Y = new TDoubleShortMapDecorator("pivotX");
  static c<View, Float> TRANSLATION_X;
  static c<View, Float> TRANSLATION_Y;
  static c<View, Integer> X;
  static c<View, Float> Y;
  
  static
  {
    PIVOT_X = new IntProperty("pivotY")
    {
      public Float get(View paramAnonymousView)
      {
        return Float.valueOf(AnimatorProxy.wrap(paramAnonymousView).getPivotX());
      }
      
      public void setValue(View paramAnonymousView, float paramAnonymousFloat)
      {
        AnimatorProxy.wrap(paramAnonymousView).setPivotX(paramAnonymousFloat);
      }
    };
    PIVOT_Y = new TIntShortMapDecorator("translationX");
    TRANSLATION_X = new TIntFloatMapDecorator("translationY");
    TRANSLATION_Y = new TByteShortMapDecorator("rotation");
    ROTATION = new TObjectByteMapDecorator("rotationX");
    ROTATION_X = new TShortFloatMapDecorator("rotationY");
    ROTATION_Y = new IntProperty("scaleX")
    {
      public Float get(View paramAnonymousView)
      {
        return Float.valueOf(AnimatorProxy.wrap(paramAnonymousView).getRotation());
      }
      
      public void setValue(View paramAnonymousView, float paramAnonymousFloat)
      {
        AnimatorProxy.wrap(paramAnonymousView).setRotation(paramAnonymousFloat);
      }
    };
    SCALE_X = new IntProperty("scaleY")
    {
      public Float get(View paramAnonymousView)
      {
        return Float.valueOf(AnimatorProxy.wrap(paramAnonymousView).getRotationX());
      }
      
      public void setValue(View paramAnonymousView, float paramAnonymousFloat)
      {
        AnimatorProxy.wrap(paramAnonymousView).setRotationX(paramAnonymousFloat);
      }
    };
    SCALE_Y = new FloatProperty("scrollX")
    {
      public Integer get(View paramAnonymousView)
      {
        return Integer.valueOf(AnimatorProxy.wrap(paramAnonymousView).getScrollX());
      }
    };
    X = new FloatProperty("scrollY")
    {
      public Integer get(View paramAnonymousView)
      {
        return Integer.valueOf(AnimatorProxy.wrap(paramAnonymousView).getScrollY());
      }
    };
    Y = new QueryResult("x");
  }
}
