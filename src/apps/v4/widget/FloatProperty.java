package apps.v4.widget;

import b.h.b.c;

public abstract class FloatProperty<T>
  extends c<T, Integer>
{
  public FloatProperty(String paramString)
  {
    super(Integer.class, paramString);
  }
  
  public final void set(Object paramObject, Integer paramInteger)
  {
    set(paramObject, Integer.valueOf(paramInteger.intValue()));
  }
}
