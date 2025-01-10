package com.joanzapata.material.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import apps.muzei.api.asm.ClassVisitor;
import apps.muzei.api.asm.ClassWriter;
import apps.muzei.api.asm.Frame;
import apps.muzei.api.internal.ViewUtil;
import apps.muzei.api.util.RippleDrawable;

public class TextView
  extends AppCompatTextView
  implements ClassVisitor
{
  private b e;
  protected int mCurrentStyle = Integer.MIN_VALUE;
  private a mOnSelectionChangedListener;
  protected int mStyleId;
  
  public TextView(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null, 0, 0);
  }
  
  public TextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0, 0);
  }
  
  public TextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt, 0);
  }
  
  public void applyStyle(int paramInt)
  {
    ViewUtil.applyStyle(this, paramInt);
    applyStyle(getContext(), null, 0, paramInt);
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
  
  protected void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    ViewUtil.applyFont(this, paramAttributeSet, paramInt1, paramInt2);
    applyStyle(paramContext, paramAttributeSet, paramInt1, paramInt2);
    if (!isInEditMode()) {
      mStyleId = ClassWriter.getStyleId(paramContext, paramAttributeSet, paramInt1, paramInt2);
    }
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (mStyleId != 0)
    {
      ClassWriter.a().a(this);
      onThemeChanged(null);
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    b.a(this);
    if (mStyleId != 0) {
      ClassWriter.a().b(this);
    }
  }
  
  protected void onSelectionChanged(int paramInt1, int paramInt2)
  {
    super.onSelectionChanged(paramInt1, paramInt2);
    a localA = mOnSelectionChangedListener;
    if (localA != null) {
      localA.onSelectionChanged(this, paramInt1, paramInt2);
    }
  }
  
  public void onThemeChanged(Frame paramFrame)
  {
    int i = ClassWriter.a().a(mStyleId);
    if (mCurrentStyle != i)
    {
      mCurrentStyle = i;
      applyStyle(mCurrentStyle);
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
  
  public void setOnSelectionChangedListener(a paramA)
  {
    mOnSelectionChangedListener = paramA;
  }
  
  public void setTextAppearance(int paramInt)
  {
    ViewUtil.applyStyle(this, paramInt);
  }
  
  public void setTextAppearance(Context paramContext, int paramInt)
  {
    ViewUtil.applyStyle(this, paramInt);
  }
  
  public abstract interface a
  {
    public abstract void onSelectionChanged(View paramView, int paramInt1, int paramInt2);
  }
}
