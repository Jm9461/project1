package org.org.android.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.design.internal.g;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import org.org.android.R.attr;
import org.org.android.R.style;
import org.org.android.R.styleable;
import org.org.android.gcm.ResourcesCompat;

public class FloatingActionButton
  extends AppCompatButton
{
  private final ClassWriter a;
  private int c;
  private ColorStateList mBackgroundTint;
  private PorterDuff.Mode mBackgroundTintMode;
  private int mColor;
  private Drawable mIcon;
  private int mIconSize;
  private int mSize;
  
  public FloatingActionButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.materialButtonStyle);
  }
  
  public FloatingActionButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = g.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.MaterialButton, paramInt, R.style.Widget_MaterialComponents_Button, new int[0]);
    c = paramContext.getDimensionPixelSize(R.styleable.MaterialButton_iconPadding, 0);
    mBackgroundTintMode = android.support.design.internal.DrawableCompat.parseTintMode(paramContext.getInt(R.styleable.MaterialButton_iconTintMode, -1), PorterDuff.Mode.SRC_IN);
    mBackgroundTint = ResourcesCompat.getColorStateList(getContext(), paramContext, R.styleable.MaterialButton_iconTint);
    mIcon = ResourcesCompat.getDrawable(getContext(), paramContext, R.styleable.MaterialButton_icon);
    mColor = paramContext.getInteger(R.styleable.MaterialButton_iconGravity, 1);
    mSize = paramContext.getDimensionPixelSize(R.styleable.MaterialButton_iconSize, 0);
    a = new ClassWriter(this);
    a.init(paramContext);
    paramContext.recycle();
    setCompoundDrawablePadding(c);
    setIcon();
  }
  
  private boolean a()
  {
    ClassWriter localClassWriter = a;
    return (localClassWriter != null) && (!localClassWriter.newUTF8());
  }
  
  private boolean isLayoutRtl()
  {
    return ViewCompat.getLayoutDirection(this) == 1;
  }
  
  private void setIcon()
  {
    Object localObject = mIcon;
    if (localObject != null)
    {
      mIcon = ((Drawable)localObject).mutate();
      android.support.v4.graphics.drawable.DrawableCompat.setTintList(mIcon, mBackgroundTint);
      localObject = mBackgroundTintMode;
      if (localObject != null) {
        android.support.v4.graphics.drawable.DrawableCompat.setTintMode(mIcon, (PorterDuff.Mode)localObject);
      }
      int i = mSize;
      if (i == 0) {
        i = mIcon.getIntrinsicWidth();
      }
      int j = mSize;
      if (j == 0) {
        j = mIcon.getIntrinsicHeight();
      }
      localObject = mIcon;
      int k = mIconSize;
      ((Drawable)localObject).setBounds(k, 0, k + i, j);
    }
    android.support.v4.widget.TextView.setCompoundDrawablesRelative(this, mIcon, null, null, null);
  }
  
  public ColorStateList getBackgroundTintList()
  {
    return getSupportBackgroundTintList();
  }
  
  public PorterDuff.Mode getBackgroundTintMode()
  {
    return getSupportBackgroundTintMode();
  }
  
  public int getCornerRadius()
  {
    if (a()) {
      return a.f();
    }
    return 0;
  }
  
  public Drawable getIcon()
  {
    return mIcon;
  }
  
  public int getIconGravity()
  {
    return mColor;
  }
  
  public int getIconPadding()
  {
    return c;
  }
  
  public int getIconSize()
  {
    return mSize;
  }
  
  public ColorStateList getIconTint()
  {
    return mBackgroundTint;
  }
  
  public PorterDuff.Mode getIconTintMode()
  {
    return mBackgroundTintMode;
  }
  
  public ColorStateList getRippleColor()
  {
    if (a()) {
      return a.d();
    }
    return null;
  }
  
  public ColorStateList getStrokeColor()
  {
    if (a()) {
      return a.getColor();
    }
    return null;
  }
  
  public int getStrokeWidth()
  {
    if (a()) {
      return a.get();
    }
    return 0;
  }
  
  public ColorStateList getSupportBackgroundTintList()
  {
    if (a()) {
      return a.getSupportBackgroundTintList();
    }
    return super.getSupportBackgroundTintList();
  }
  
  public PorterDuff.Mode getSupportBackgroundTintMode()
  {
    if (a()) {
      return a.put();
    }
    return super.getSupportBackgroundTintMode();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((Build.VERSION.SDK_INT < 21) && (a())) {
      a.a(paramCanvas);
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (Build.VERSION.SDK_INT == 21)
    {
      ClassWriter localClassWriter = a;
      if (localClassWriter != null) {
        localClassWriter.a(paramInt4 - paramInt2, paramInt3 - paramInt1);
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (mIcon != null)
    {
      if (mColor != 2) {
        return;
      }
      int i = (int)getPaint().measureText(getText().toString());
      paramInt2 = mSize;
      paramInt1 = paramInt2;
      if (paramInt2 == 0) {
        paramInt1 = mIcon.getIntrinsicWidth();
      }
      paramInt2 = (getMeasuredWidth() - i - ViewCompat.getPaddingEnd(this) - paramInt1 - c - ViewCompat.getPaddingStart(this)) / 2;
      paramInt1 = paramInt2;
      if (isLayoutRtl()) {
        paramInt1 = -paramInt2;
      }
      if (mIconSize != paramInt1)
      {
        mIconSize = paramInt1;
        setIcon();
      }
    }
  }
  
  public void setBackground(Drawable paramDrawable)
  {
    setBackgroundDrawable(paramDrawable);
  }
  
  public void setBackgroundColor(int paramInt)
  {
    if (a())
    {
      a.get(paramInt);
      return;
    }
    super.setBackgroundColor(paramInt);
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    if (a())
    {
      if (paramDrawable != getBackground())
      {
        Log.i("MaterialButton", "Setting a custom background is not supported.");
        a.b();
        super.setBackgroundDrawable(paramDrawable);
        return;
      }
      getBackground().setState(paramDrawable.getState());
      return;
    }
    super.setBackgroundDrawable(paramDrawable);
  }
  
  public void setBackgroundResource(int paramInt)
  {
    Drawable localDrawable = null;
    if (paramInt != 0) {
      localDrawable = org.org.v4.gui.helpers.Resources.getDrawable(getContext(), paramInt);
    }
    setBackgroundDrawable(localDrawable);
  }
  
  public void setBackgroundTintList(ColorStateList paramColorStateList)
  {
    setSupportBackgroundTintList(paramColorStateList);
  }
  
  public void setBackgroundTintMode(PorterDuff.Mode paramMode)
  {
    setSupportBackgroundTintMode(paramMode);
  }
  
  public void setCornerRadius(int paramInt)
  {
    if (a()) {
      a.a(paramInt);
    }
  }
  
  public void setCornerRadiusResource(int paramInt)
  {
    if (a()) {
      setCornerRadius(getResources().getDimensionPixelSize(paramInt));
    }
  }
  
  public void setIcon(Drawable paramDrawable)
  {
    if (mIcon != paramDrawable)
    {
      mIcon = paramDrawable;
      setIcon();
    }
  }
  
  public void setIconGravity(int paramInt)
  {
    mColor = paramInt;
  }
  
  public void setIconPadding(int paramInt)
  {
    if (c != paramInt)
    {
      c = paramInt;
      setCompoundDrawablePadding(paramInt);
    }
  }
  
  public void setIconResource(int paramInt)
  {
    Drawable localDrawable = null;
    if (paramInt != 0) {
      localDrawable = org.org.v4.gui.helpers.Resources.getDrawable(getContext(), paramInt);
    }
    setIcon(localDrawable);
  }
  
  public void setIconSize(int paramInt)
  {
    if (paramInt >= 0)
    {
      if (mSize != paramInt)
      {
        mSize = paramInt;
        setIcon();
      }
    }
    else {
      throw new IllegalArgumentException("iconSize cannot be less than 0");
    }
  }
  
  public void setIconTint(ColorStateList paramColorStateList)
  {
    if (mBackgroundTint != paramColorStateList)
    {
      mBackgroundTint = paramColorStateList;
      setIcon();
    }
  }
  
  public void setIconTintMode(PorterDuff.Mode paramMode)
  {
    if (mBackgroundTintMode != paramMode)
    {
      mBackgroundTintMode = paramMode;
      setIcon();
    }
  }
  
  public void setIconTintResource(int paramInt)
  {
    setIconTint(org.org.v4.gui.helpers.Resources.getColorStateList(getContext(), paramInt));
  }
  
  void setInternalBackground(Drawable paramDrawable)
  {
    super.setBackgroundDrawable(paramDrawable);
  }
  
  public void setRippleColor(ColorStateList paramColorStateList)
  {
    if (a()) {
      a.init(paramColorStateList);
    }
  }
  
  public void setRippleColorResource(int paramInt)
  {
    if (a()) {
      setRippleColor(org.org.v4.gui.helpers.Resources.getColorStateList(getContext(), paramInt));
    }
  }
  
  public void setStrokeColor(ColorStateList paramColorStateList)
  {
    if (a()) {
      a.b(paramColorStateList);
    }
  }
  
  public void setStrokeColorResource(int paramInt)
  {
    if (a()) {
      setStrokeColor(org.org.v4.gui.helpers.Resources.getColorStateList(getContext(), paramInt));
    }
  }
  
  public void setStrokeWidth(int paramInt)
  {
    if (a()) {
      a.b(paramInt);
    }
  }
  
  public void setStrokeWidthResource(int paramInt)
  {
    if (a()) {
      setStrokeWidth(getResources().getDimensionPixelSize(paramInt));
    }
  }
  
  public void setSupportBackgroundTintList(ColorStateList paramColorStateList)
  {
    if (a())
    {
      a.a(paramColorStateList);
      return;
    }
    if (a != null) {
      super.setSupportBackgroundTintList(paramColorStateList);
    }
  }
  
  public void setSupportBackgroundTintMode(PorterDuff.Mode paramMode)
  {
    if (a())
    {
      a.a(paramMode);
      return;
    }
    if (a != null) {
      super.setSupportBackgroundTintMode(paramMode);
    }
  }
}
