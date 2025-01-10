package com.aurelhubert.ahbottomnavigation;

import android.content.Context;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.c;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;

public abstract class VerticalScrollingBehavior<V extends View>
  extends CoordinatorLayout.c<V>
{
  private int e = 0;
  private int i = 0;
  private int k = 0;
  private int w = 0;
  
  public VerticalScrollingBehavior() {}
  
  public VerticalScrollingBehavior(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public abstract void a(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt1, int paramInt2, int paramInt3);
  
  public abstract void a(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, int paramInt1, int paramInt2, int[] paramArrayOfInt, int paramInt3);
  
  public void b(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.b(paramCoordinatorLayout, paramView1, paramView2, paramInt1, paramInt2, paramInt3, paramInt4);
    if ((paramInt4 > 0) && (w < 0))
    {
      w = 0;
      e = 1;
    }
    else if ((paramInt4 < 0) && (w > 0))
    {
      w = 0;
      e = -1;
    }
    w += paramInt4;
    a(paramCoordinatorLayout, paramView1, e, paramInt2, w);
  }
  
  public void b(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    super.b(paramCoordinatorLayout, paramView1, paramView2, paramInt1, paramInt2, paramArrayOfInt);
    if ((paramInt2 > 0) && (k < 0))
    {
      k = 0;
      i = 1;
    }
    else if ((paramInt2 < 0) && (k > 0))
    {
      k = 0;
      i = -1;
    }
    k += paramInt2;
    a(paramCoordinatorLayout, paramView1, paramView2, paramInt1, paramInt2, paramArrayOfInt, i);
  }
  
  public WindowInsetsCompat onApplyWindowInsets(CoordinatorLayout paramCoordinatorLayout, View paramView, WindowInsetsCompat paramWindowInsetsCompat)
  {
    super.onApplyWindowInsets(paramCoordinatorLayout, paramView, paramWindowInsetsCompat);
    return paramWindowInsetsCompat;
  }
  
  protected abstract boolean onNestedFling(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, float paramFloat1, float paramFloat2, int paramInt);
  
  public boolean onNestedFling(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    super.onNestedFling(paramCoordinatorLayout, paramView1, paramView2, paramFloat1, paramFloat2, paramBoolean);
    int j;
    if (paramFloat2 > 0.0F) {
      j = 1;
    } else {
      j = -1;
    }
    i = j;
    return onNestedFling(paramCoordinatorLayout, paramView1, paramView2, paramFloat1, paramFloat2, i);
  }
  
  public boolean onNestedPreFling(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, float paramFloat1, float paramFloat2)
  {
    return super.onNestedPreFling(paramCoordinatorLayout, paramView1, paramView2, paramFloat1, paramFloat2);
  }
  
  public Parcelable onSaveInstanceState(CoordinatorLayout paramCoordinatorLayout, View paramView)
  {
    return super.onSaveInstanceState(paramCoordinatorLayout, paramView);
  }
  
  public boolean onStartNestedScroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, View paramView3, int paramInt)
  {
    return (paramInt & 0x2) != 0;
  }
  
  public void onStopNestedScroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
  {
    super.onStopNestedScroll(paramCoordinatorLayout, paramView1, paramView2);
  }
  
  public void scroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, View paramView3, int paramInt)
  {
    super.scroll(paramCoordinatorLayout, paramView1, paramView2, paramView3, paramInt);
  }
}
