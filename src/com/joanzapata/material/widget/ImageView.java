package com.joanzapata.material.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import apps.muzei.api.asm.ClassVisitor;
import apps.muzei.api.asm.ClassWriter;
import apps.muzei.api.asm.Frame;
import apps.muzei.api.internal.ViewUtil;
import apps.muzei.api.util.RippleDrawable;

public class ImageView
  extends AppCompatImageView
  implements ClassVisitor
{
  protected int a = Integer.MIN_VALUE;
  private b e;
  protected int m;
  
  public ImageView(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null, 0, 0);
  }
  
  public ImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0, 0);
  }
  
  public ImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt, 0);
  }
  
  public void a(Frame paramFrame)
  {
    int i = ClassWriter.a().a(m);
    if (a != i)
    {
      a = i;
      init(a);
    }
  }
  
  protected void applyStyle(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    getRippleManager().onCreate(this, paramContext, paramAttributeSet, paramInt1, paramInt2);
  }
  
  protected b getRippleManager()
  {
    if (e == null) {
      try
      {
        if (e == null) {
          e = new b();
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    return e;
  }
  
  public void init(int paramInt)
  {
    ViewUtil.applyStyle(this, paramInt);
    applyStyle(getContext(), null, 0, paramInt);
  }
  
  protected void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    applyStyle(paramContext, paramAttributeSet, paramInt1, paramInt2);
    if (!isInEditMode()) {
      m = ClassWriter.getStyleId(paramContext, paramAttributeSet, paramInt1, paramInt2);
    }
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (m != 0)
    {
      ClassWriter.a().a(this);
      a(null);
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    b.a(this);
    if (m != 0) {
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
}
