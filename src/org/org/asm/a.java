package org.org.asm;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.Property;
import android.view.View;
import java.lang.reflect.Field;

class a
{
  private static final LayoutManager a;
  private static Field field;
  static final Property<View, Float> h;
  private static boolean value;
  
  static
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 22) {
      a = new JRockit131Instantiator();
    } else if (i >= 21) {
      a = new Plot();
    } else if (i >= 19) {
      a = new Frame();
    } else {
      a = new LayoutManager();
    }
    h = new d0.a(Float.class, "translationAlpha");
    new d0.b(Rect.class, "clipBounds");
  }
  
  static Object a(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      return new e(paramView);
    }
    return o.a(paramView);
  }
  
  static void a(View paramView, float paramFloat)
  {
    a.a(paramView, paramFloat);
  }
  
  static void a(View paramView, Matrix paramMatrix)
  {
    a.draw(paramView, paramMatrix);
  }
  
  static float b(View paramView)
  {
    return a.a(paramView);
  }
  
  static void b(View paramView, Matrix paramMatrix)
  {
    a.a(paramView, paramMatrix);
  }
  
  static c c(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      return new GOST3410ParameterSpec(paramView);
    }
    return new PdfSpotColor(paramView.getWindowToken());
  }
  
  private static void set()
  {
    if (!value)
    {
      try
      {
        Field localField = View.class.getDeclaredField("mViewFlags");
        field = localField;
        localField = field;
        localField.setAccessible(true);
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        Log.i("ViewUtils", "fetchViewFlagsField: ");
      }
      value = true;
    }
  }
  
  static void set(View paramView, int paramInt)
  {
    set();
    Field localField = field;
    if (localField != null) {
      try
      {
        int i = localField.getInt(paramView);
        localField = field;
        localField.setInt(paramView, i & 0xFFFFFFF3 | paramInt);
        return;
      }
      catch (IllegalAccessException paramView) {}
    }
  }
  
  static void set(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    a.initialize(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  static void setChecked(View paramView)
  {
    a.init(paramView);
  }
  
  static void setEnabled(View paramView)
  {
    a.onDraw(paramView);
  }
}
