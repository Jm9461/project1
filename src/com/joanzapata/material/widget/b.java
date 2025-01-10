package com.joanzapata.material.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import apps.muzei.api.R.styleable;
import apps.muzei.api.internal.ViewUtil;
import apps.muzei.api.util.CircleDrawable;
import apps.muzei.api.util.RippleDrawable;
import apps.muzei.api.util.f;

public final class b
  implements View.OnClickListener
{
  private View.OnClickListener a;
  private boolean b = false;
  
  public b() {}
  
  public static void a(View paramView)
  {
    Drawable localDrawable = paramView.getBackground();
    if ((localDrawable instanceof RippleDrawable)) {
      ((RippleDrawable)localDrawable).setVisible();
    } else if ((localDrawable instanceof CircleDrawable)) {
      ((CircleDrawable)localDrawable).setColor();
    }
    if ((paramView instanceof ViewGroup))
    {
      paramView = (ViewGroup)paramView;
      int i = 0;
      int j = paramView.getChildCount();
      while (i < j)
      {
        a(paramView.getChildAt(i));
        i += 1;
      }
    }
  }
  
  private void c(View paramView)
  {
    View.OnClickListener localOnClickListener = a;
    if (localOnClickListener != null) {
      localOnClickListener.onClick(paramView);
    }
  }
  
  private Drawable getBackground(View paramView)
  {
    Drawable localDrawable = paramView.getBackground();
    if (localDrawable == null) {
      return null;
    }
    paramView = localDrawable;
    if ((localDrawable instanceof RippleDrawable)) {
      paramView = ((RippleDrawable)localDrawable).getBackgroundDrawable();
    }
    return paramView;
  }
  
  public void onClick(View paramView)
  {
    Drawable localDrawable = paramView.getBackground();
    long l2 = 0L;
    long l1 = l2;
    if (localDrawable != null) {
      if ((localDrawable instanceof RippleDrawable))
      {
        l1 = ((RippleDrawable)localDrawable).d();
      }
      else
      {
        l1 = l2;
        if ((localDrawable instanceof CircleDrawable)) {
          l1 = ((CircleDrawable)localDrawable).draw();
        }
      }
    }
    if ((l1 > 0L) && (paramView.getHandler() != null) && (!b))
    {
      b = true;
      paramView.getHandler().postDelayed(new NumberPicker.BeginSoftInputOnLongPressCommand(this, paramView), l1);
      return;
    }
    c(paramView);
  }
  
  public void onCreate(View.OnClickListener paramOnClickListener)
  {
    a = paramOnClickListener;
  }
  
  public void onCreate(View paramView, Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    if (paramView.isInEditMode()) {
      return;
    }
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RippleView, paramInt1, paramInt2);
    int i = localTypedArray.getResourceId(R.styleable.RippleView_rd_style, 0);
    RippleDrawable localRippleDrawable = null;
    if (i != 0)
    {
      paramContext = new f(paramContext, i);
      paramContext.a(getBackground(paramView));
      localRippleDrawable = paramContext.a();
    }
    else if (localTypedArray.getBoolean(R.styleable.RippleView_rd_enable, false))
    {
      paramContext = new f(paramContext, paramAttributeSet, paramInt1, paramInt2);
      paramContext.a(getBackground(paramView));
      localRippleDrawable = paramContext.a();
    }
    localTypedArray.recycle();
    if (localRippleDrawable != null) {
      ViewUtil.setBackground(paramView, localRippleDrawable);
    }
  }
  
  public boolean onTouchEvent(View paramView, MotionEvent paramMotionEvent)
  {
    Drawable localDrawable = paramView.getBackground();
    return (localDrawable != null) && ((localDrawable instanceof RippleDrawable)) && (((RippleDrawable)localDrawable).onTouch(paramView, paramMotionEvent));
  }
}
