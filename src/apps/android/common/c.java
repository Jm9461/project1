package apps.android.common;

import java.lang.reflect.Constructor;

public enum c
{
  private Class clazz;
  
  static
  {
    c = new c("BackEaseInOut", 2, b.e.b.d.b.class);
    a = new c("BounceEaseIn", 3, b.e.b.e.a.class);
    i = new c("BounceEaseOut", 4, b.e.b.e.c.class);
    g = new c("BounceEaseInOut", 5, b.e.b.e.b.class);
    h = new c("CircEaseIn", 6, b.e.b.f.a.class);
    b = new c("CircEaseOut", 7, b.e.b.f.c.class);
    l = new c("CircEaseInOut", 8, b.e.b.f.b.class);
    SV = new c("CubicEaseIn", 9, b.e.b.g.a.class);
    SY = new c("CubicEaseOut", 10, b.e.b.g.c.class);
    SZ = new c("CubicEaseInOut", 11, b.e.b.g.b.class);
    CLOUDS_FEW = new c("ElasticEaseIn", 12, b.e.b.h.a.class);
    CLOUDS_SCATTERED = new c("ElasticEaseOut", 13, b.e.b.h.b.class);
    CLOUDS_BROKEN = new c("ExpoEaseIn", 14, b.e.b.i.a.class);
    CLOUDS_OVERCAST = new c("ExpoEaseOut", 15, b.e.b.i.c.class);
    DRIZZLE_SHOWER = new c("ExpoEaseInOut", 16, b.e.b.i.b.class);
    RAIN_LIGHT = new c("QuadEaseIn", 17, b.e.b.k.a.class);
    RAIN = new c("QuadEaseOut", 18, b.e.b.k.c.class);
    stroke_width = new c("QuadEaseInOut", 19, b.e.b.k.b.class);
    style = new c("QuintEaseIn", 20, b.e.b.l.a.class);
    systemLanguage = new c("QuintEaseOut", 21, b.e.b.l.c.class);
    text_anchor = new c("QuintEaseInOut", 22, b.e.b.l.b.class);
    text_decoration = new c("SineEaseIn", 23, b.e.b.m.a.class);
    transform = new c("SineEaseOut", 24, b.e.b.m.c.class);
    startOffset = new c("SineEaseInOut", 25, b.e.b.m.b.class);
    stop_color = new c("Linear", 26, b.e.b.j.a.class);
  }
  
  private c(Class paramClass)
  {
    clazz = paramClass;
  }
  
  public e getMethod(float paramFloat)
  {
    Object localObject = clazz;
    Class localClass = Float.TYPE;
    try
    {
      localObject = ((Class)localObject).getConstructor(new Class[] { localClass });
      localObject = ((Constructor)localObject).newInstance(new Object[] { Float.valueOf(paramFloat) });
      return (e)localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      throw new Error("Can not init easingMethod instance");
    }
  }
}
