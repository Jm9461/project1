package org.org.asm;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

class Item<T>
  extends Property<T, Float>
{
  private final PathMeasure a;
  private final float b;
  private final Property<T, PointF> c;
  private final float[] d = new float[2];
  private final PointF j = new PointF();
  private float k;
  
  Item(Property paramProperty, Path paramPath)
  {
    super(Float.class, paramProperty.getName());
    c = paramProperty;
    a = new PathMeasure(paramPath, false);
    b = a.getLength();
  }
  
  public void a(Object paramObject, Float paramFloat)
  {
    k = paramFloat.floatValue();
    a.getPosTan(b * paramFloat.floatValue(), d, null);
    paramFloat = j;
    float[] arrayOfFloat = d;
    x = arrayOfFloat[0];
    y = arrayOfFloat[1];
    c.set(paramObject, paramFloat);
  }
  
  public Float get(Object paramObject)
  {
    return Float.valueOf(k);
  }
}
