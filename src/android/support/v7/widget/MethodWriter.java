package android.support.v7.widget;

import android.graphics.Rect;
import android.view.View;

public abstract class MethodWriter
{
  private int a = Integer.MIN_VALUE;
  protected final RecyclerView.o b;
  final Rect x = new Rect();
  
  private MethodWriter(RecyclerView.o paramO)
  {
    b = paramO;
  }
  
  public static MethodWriter a(RecyclerView.o paramO, int paramInt)
  {
    if (paramInt != 0)
    {
      if (paramInt == 1) {
        return b(paramO);
      }
      throw new IllegalArgumentException("invalid orientation");
    }
    return c(paramO);
  }
  
  public static MethodWriter b(RecyclerView.o paramO)
  {
    return new t0.b(paramO);
  }
  
  public static MethodWriter c(RecyclerView.o paramO)
  {
    return new t0.a(paramO);
  }
  
  public abstract int a();
  
  public abstract int a(View paramView);
  
  public abstract void a(int paramInt);
  
  public abstract int b();
  
  public abstract int b(View paramView);
  
  public abstract int c(View paramView);
  
  public int d()
  {
    if (Integer.MIN_VALUE == a) {
      return 0;
    }
    return get() - a;
  }
  
  public abstract int e();
  
  public abstract int f();
  
  public abstract int f(View paramView);
  
  public abstract int get();
  
  public abstract int getDecoratedMeasurementInOther(View paramView);
  
  public abstract int getMode();
  
  public abstract int remove();
  
  public abstract int remove(View paramView);
  
  public void visitLabel()
  {
    a = get();
  }
}
