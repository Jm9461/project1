package org.org.android.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.view.View;
import org.org.android.R.styleable;
import org.org.android.gcm.ResourcesCompat;
import org.org.android.utils.ThemeUtils;

class ClassWriter
{
  private static final boolean c;
  private final FloatingActionButton a;
  private GradientDrawable b;
  private boolean d = false;
  private GradientDrawable e;
  private final Paint f = new Paint(1);
  private int g;
  private Drawable h;
  private int i;
  private GradientDrawable j;
  private GradientDrawable k;
  private ColorStateList l;
  private PorterDuff.Mode m;
  private Drawable n;
  private int p;
  private ColorStateList q;
  private int r;
  private GradientDrawable s;
  private final Rect t = new Rect();
  private final RectF v = new RectF();
  private ColorStateList w;
  private int x;
  private int y;
  
  static
  {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 21) {
      bool = true;
    } else {
      bool = false;
    }
    c = bool;
  }
  
  public ClassWriter(FloatingActionButton paramFloatingActionButton)
  {
    a = paramFloatingActionButton;
  }
  
  private Drawable a()
  {
    k = new GradientDrawable();
    k.setCornerRadius(i + 1.0E-5F);
    k.setColor(-1);
    h = android.support.v4.graphics.drawable.DrawableCompat.wrap(k);
    android.support.v4.graphics.drawable.DrawableCompat.setTintList(h, l);
    PorterDuff.Mode localMode = m;
    if (localMode != null) {
      android.support.v4.graphics.drawable.DrawableCompat.setTintMode(h, localMode);
    }
    j = new GradientDrawable();
    j.setCornerRadius(i + 1.0E-5F);
    j.setColor(-1);
    n = android.support.v4.graphics.drawable.DrawableCompat.wrap(j);
    android.support.v4.graphics.drawable.DrawableCompat.setTintList(n, q);
    return a(new LayerDrawable(new Drawable[] { h, n }));
  }
  
  private InsetDrawable a(Drawable paramDrawable)
  {
    return new InsetDrawable(paramDrawable, g, r, y, p);
  }
  
  private void c()
  {
    if ((c) && (e != null))
    {
      a.setInternalBackground(init());
      return;
    }
    if (!c) {
      a.invalidate();
    }
  }
  
  private GradientDrawable draw()
  {
    if ((c) && (a.getBackground() != null)) {
      return (GradientDrawable)((LayerDrawable)((InsetDrawable)((RippleDrawable)a.getBackground()).getDrawable(0)).getDrawable()).getDrawable(0);
    }
    return null;
  }
  
  private GradientDrawable getDrawable()
  {
    if ((c) && (a.getBackground() != null)) {
      return (GradientDrawable)((LayerDrawable)((InsetDrawable)((RippleDrawable)a.getBackground()).getDrawable(0)).getDrawable()).getDrawable(1);
    }
    return null;
  }
  
  private Drawable init()
  {
    s = new GradientDrawable();
    s.setCornerRadius(i + 1.0E-5F);
    s.setColor(-1);
    setIcon();
    e = new GradientDrawable();
    e.setCornerRadius(i + 1.0E-5F);
    e.setColor(0);
    e.setStroke(x, w);
    InsetDrawable localInsetDrawable = a(new LayerDrawable(new Drawable[] { s, e }));
    b = new GradientDrawable();
    b.setCornerRadius(i + 1.0E-5F);
    b.setColor(-1);
    return (Drawable)new TransitionDrawable(ThemeUtils.createDefaultColorStateList(q), localInsetDrawable, b);
  }
  
  private void setIcon()
  {
    Object localObject = s;
    if (localObject != null)
    {
      android.support.v4.graphics.drawable.DrawableCompat.setTintList((Drawable)localObject, l);
      localObject = m;
      if (localObject != null) {
        android.support.v4.graphics.drawable.DrawableCompat.setTintMode(s, (PorterDuff.Mode)localObject);
      }
    }
  }
  
  void a(int paramInt)
  {
    if (i != paramInt)
    {
      i = paramInt;
      if ((c) && (s != null) && (e != null) && (b != null))
      {
        if (Build.VERSION.SDK_INT == 21)
        {
          draw().setCornerRadius(paramInt + 1.0E-5F);
          getDrawable().setCornerRadius(paramInt + 1.0E-5F);
        }
        s.setCornerRadius(paramInt + 1.0E-5F);
        e.setCornerRadius(paramInt + 1.0E-5F);
        b.setCornerRadius(paramInt + 1.0E-5F);
        return;
      }
      if (!c)
      {
        GradientDrawable localGradientDrawable = k;
        if ((localGradientDrawable != null) && (j != null))
        {
          localGradientDrawable.setCornerRadius(paramInt + 1.0E-5F);
          j.setCornerRadius(paramInt + 1.0E-5F);
          a.invalidate();
        }
      }
    }
  }
  
  void a(int paramInt1, int paramInt2)
  {
    GradientDrawable localGradientDrawable = b;
    if (localGradientDrawable != null) {
      localGradientDrawable.setBounds(g, r, paramInt2 - y, paramInt1 - p);
    }
  }
  
  void a(ColorStateList paramColorStateList)
  {
    if (l != paramColorStateList)
    {
      l = paramColorStateList;
      if (c)
      {
        setIcon();
        return;
      }
      paramColorStateList = h;
      if (paramColorStateList != null) {
        android.support.v4.graphics.drawable.DrawableCompat.setTintList(paramColorStateList, l);
      }
    }
  }
  
  void a(Canvas paramCanvas)
  {
    if ((paramCanvas != null) && (w != null) && (x > 0))
    {
      t.set(a.getBackground().getBounds());
      RectF localRectF = v;
      Rect localRect = t;
      float f1 = left;
      int i1 = x;
      localRectF.set(f1 + i1 / 2.0F + g, top + i1 / 2.0F + r, right - i1 / 2.0F - y, bottom - i1 / 2.0F - p);
      f1 = i - x / 2.0F;
      paramCanvas.drawRoundRect(v, f1, f1, f);
    }
  }
  
  void a(PorterDuff.Mode paramMode)
  {
    if (m != paramMode)
    {
      m = paramMode;
      if (c)
      {
        setIcon();
        return;
      }
      paramMode = h;
      if (paramMode != null)
      {
        PorterDuff.Mode localMode = m;
        if (localMode != null) {
          android.support.v4.graphics.drawable.DrawableCompat.setTintMode(paramMode, localMode);
        }
      }
    }
  }
  
  void b()
  {
    d = true;
    a.setSupportBackgroundTintList(l);
    a.setSupportBackgroundTintMode(m);
  }
  
  void b(int paramInt)
  {
    if (x != paramInt)
    {
      x = paramInt;
      f.setStrokeWidth(paramInt);
      c();
    }
  }
  
  void b(ColorStateList paramColorStateList)
  {
    if (w != paramColorStateList)
    {
      w = paramColorStateList;
      Paint localPaint = f;
      int i1 = 0;
      if (paramColorStateList != null) {
        i1 = paramColorStateList.getColorForState(a.getDrawableState(), 0);
      }
      localPaint.setColor(i1);
      c();
    }
  }
  
  ColorStateList d()
  {
    return q;
  }
  
  int f()
  {
    return i;
  }
  
  int get()
  {
    return x;
  }
  
  void get(int paramInt)
  {
    GradientDrawable localGradientDrawable;
    if (c)
    {
      localGradientDrawable = s;
      if (localGradientDrawable != null)
      {
        localGradientDrawable.setColor(paramInt);
        return;
      }
    }
    if (!c)
    {
      localGradientDrawable = k;
      if (localGradientDrawable != null) {
        localGradientDrawable.setColor(paramInt);
      }
    }
  }
  
  ColorStateList getColor()
  {
    return w;
  }
  
  ColorStateList getSupportBackgroundTintList()
  {
    return l;
  }
  
  void init(ColorStateList paramColorStateList)
  {
    if (q != paramColorStateList)
    {
      q = paramColorStateList;
      if ((c) && ((a.getBackground() instanceof RippleDrawable)))
      {
        ((RippleDrawable)a.getBackground()).setColor(paramColorStateList);
        return;
      }
      if (!c)
      {
        Drawable localDrawable = n;
        if (localDrawable != null) {
          android.support.v4.graphics.drawable.DrawableCompat.setTintList(localDrawable, paramColorStateList);
        }
      }
    }
  }
  
  public void init(TypedArray paramTypedArray)
  {
    int i2 = R.styleable.MaterialButton_android_insetLeft;
    int i1 = 0;
    g = paramTypedArray.getDimensionPixelOffset(i2, 0);
    y = paramTypedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetRight, 0);
    r = paramTypedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetTop, 0);
    p = paramTypedArray.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetBottom, 0);
    i = paramTypedArray.getDimensionPixelSize(R.styleable.MaterialButton_cornerRadius, 0);
    x = paramTypedArray.getDimensionPixelSize(R.styleable.MaterialButton_strokeWidth, 0);
    m = android.support.design.internal.DrawableCompat.parseTintMode(paramTypedArray.getInt(R.styleable.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
    l = ResourcesCompat.getColorStateList(a.getContext(), paramTypedArray, R.styleable.MaterialButton_backgroundTint);
    w = ResourcesCompat.getColorStateList(a.getContext(), paramTypedArray, R.styleable.MaterialButton_strokeColor);
    q = ResourcesCompat.getColorStateList(a.getContext(), paramTypedArray, R.styleable.MaterialButton_rippleColor);
    f.setStyle(Paint.Style.STROKE);
    f.setStrokeWidth(x);
    paramTypedArray = f;
    Object localObject = w;
    if (localObject != null) {
      i1 = ((ColorStateList)localObject).getColorForState(a.getDrawableState(), 0);
    }
    paramTypedArray.setColor(i1);
    i1 = ViewCompat.getPaddingStart(a);
    i2 = a.getPaddingTop();
    int i3 = ViewCompat.getPaddingEnd(a);
    int i4 = a.getPaddingBottom();
    localObject = a;
    if (c) {
      paramTypedArray = init();
    } else {
      paramTypedArray = a();
    }
    ((FloatingActionButton)localObject).setInternalBackground(paramTypedArray);
    ViewCompat.setPaddingRelative(a, g + i1, r + i2, y + i3, p + i4);
  }
  
  boolean newUTF8()
  {
    return d;
  }
  
  PorterDuff.Mode put()
  {
    return m;
  }
}
