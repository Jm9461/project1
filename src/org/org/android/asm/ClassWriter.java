package org.org.android.asm;

import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.Property;
import java.util.WeakHashMap;

public class ClassWriter
  extends Property<Drawable, Integer>
{
  public static final Property<Drawable, Integer> K = new ClassWriter();
  private final WeakHashMap<Drawable, Integer> c = new WeakHashMap();
  
  private ClassWriter()
  {
    super(Integer.class, "drawableAlphaCompat");
  }
  
  public void a(Drawable paramDrawable, Integer paramInteger)
  {
    if (Build.VERSION.SDK_INT < 19) {
      c.put(paramDrawable, paramInteger);
    }
    paramDrawable.setAlpha(paramInteger.intValue());
  }
  
  public Integer get(Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return Integer.valueOf(paramDrawable.getAlpha());
    }
    if (c.containsKey(paramDrawable)) {
      return (Integer)c.get(paramDrawable);
    }
    return Integer.valueOf(255);
  }
}
