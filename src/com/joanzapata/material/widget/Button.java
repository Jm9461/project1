package com.joanzapata.material.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import apps.muzei.api.asm.ClassVisitor;
import apps.muzei.api.asm.ClassWriter;
import apps.muzei.api.asm.Frame;
import apps.muzei.api.internal.ViewUtil;
import apps.muzei.api.util.RippleDrawable;

public class Button
  extends AppCompatButton
  implements ClassVisitor
{
  private b Text;
  protected int x;
  protected int y = Integer.MIN_VALUE;
  
  public Button(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null, 0, 0);
  }
  
  public Button(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0, 0);
  }
  
  public Button(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt, 0);
  }
  
  protected void applyStyle(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    getRippleManager().onCreate(this, paramContext, paramAttributeSet, paramInt1, paramInt2);
  }
  
  protected b getRippleManager()
  {
    if (Text == null) {
      try
      {
        if (Text == null) {
          Text = new b();
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    return Text;
  }
  
  public void init(int paramInt)
  {
    ViewUtil.applyStyle(this, paramInt);
    applyStyle(getContext(), null, 0, paramInt);
  }
  
  protected void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    ViewUtil.applyFont(this, paramAttributeSet, paramInt1, paramInt2);
    applyStyle(paramContext, paramAttributeSet, paramInt1, paramInt2);
    if (!isInEditMode()) {
      x = ClassWriter.getStyleId(paramContext, paramAttributeSet, paramInt1, paramInt2);
    }
  }
  
  public void init(Frame paramFrame)
  {
    int i = ClassWriter.a().a(x);
    if (y != i)
    {
      y = i;
      init(y);
    }
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (x != 0)
    {
      ClassWriter.a().a(this);
      init(null);
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    b.a(this);
    if (x != 0) {
      ClassWriter.a().b(this);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = super.onTouchEvent(paramMotionEvent);
    return (getRippleManager().onTouchEvent(this, paramMotionEvent)) || (bool);
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    Drawable localDrawable = getBackground();
    if (((localDrawable instanceof RippleDrawable)) && (!(paramDrawable instanceof RippleDrawable)))
    {
      ((RippleDrawable)localDrawable).setBackgroundDrawable(paramDrawable);
      return;
    }
    super.setBackgroundDrawable(paramDrawable);
  }
  
  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    b localB = getRippleManager();
    if (paramOnClickListener == localB)
    {
      super.setOnClickListener(paramOnClickListener);
      return;
    }
    localB.onCreate(paramOnClickListener);
    setOnClickListener(localB);
  }
  
  public void setTextAppearance(int paramInt)
  {
    ViewUtil.applyStyle(this, paramInt);
  }
  
  public void setTextAppearance(Context paramContext, int paramInt)
  {
    ViewUtil.applyStyle(this, paramInt);
  }
}
