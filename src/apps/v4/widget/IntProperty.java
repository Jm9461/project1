package apps.v4.widget;

import b.h.b.c;

public abstract class IntProperty<T>
  extends c<T, Float>
{
  public IntProperty(String paramString)
  {
    super(Float.class, paramString);
  }
  
  public final void set(Object paramObject, Float paramFloat)
  {
    setValue(paramObject, paramFloat.floatValue());
  }
  
  public abstract void setValue(Object paramObject, float paramFloat);
}
