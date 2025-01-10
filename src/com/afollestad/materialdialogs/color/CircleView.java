package com.afollestad.materialdialogs.color;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import apps.afollestad.materialdialogs.base.DialogUtils;

public class CircleView
  extends FrameLayout
{
  private final Paint a;
  private final Paint b;
  private final int k;
  private boolean mSelected;
  private final int t;
  private final Paint x;
  
  public CircleView(Context paramContext)
  {
    this(paramContext, null, 0);
  }
  
  public CircleView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = getResources();
    k = ((int)TypedValue.applyDimension(1, 3.0F, paramContext.getDisplayMetrics()));
    t = ((int)TypedValue.applyDimension(1, 5.0F, paramContext.getDisplayMetrics()));
    x = new Paint();
    x.setAntiAlias(true);
    x.setColor(-1);
    b = new Paint();
    b.setAntiAlias(true);
    a = new Paint();
    a.setAntiAlias(true);
    update(-12303292);
    setWillNotDraw(false);
  }
  
  private Drawable createSelector(int paramInt)
  {
    ShapeDrawable localShapeDrawable = new ShapeDrawable(new OvalShape());
    localShapeDrawable.getPaint().setColor(translucentColor(shiftColorUp(paramInt)));
    StateListDrawable localStateListDrawable = new StateListDrawable();
    localStateListDrawable.addState(new int[] { 16842919 }, localShapeDrawable);
    return localStateListDrawable;
  }
  
  public static int shiftColor(int paramInt, float paramFloat)
  {
    if (paramFloat == 1.0F) {
      return paramInt;
    }
    float[] arrayOfFloat = new float[3];
    Color.colorToHSV(paramInt, arrayOfFloat);
    arrayOfFloat[2] *= paramFloat;
    return Color.HSVToColor(arrayOfFloat);
  }
  
  public static int shiftColorDown(int paramInt)
  {
    return shiftColor(paramInt, 0.9F);
  }
  
  public static int shiftColorUp(int paramInt)
  {
    return shiftColor(paramInt, 1.1F);
  }
  
  private static int translucentColor(int paramInt)
  {
    return Color.argb(Math.round(Color.alpha(paramInt) * 0.7F), Color.red(paramInt), Color.green(paramInt), Color.blue(paramInt));
  }
  
  private void update(int paramInt)
  {
    b.setColor(paramInt);
    a.setColor(shiftColorDown(paramInt));
    Drawable localDrawable = createSelector(paramInt);
    if (Build.VERSION.SDK_INT >= 21)
    {
      int[] arrayOfInt = { 16842919 };
      paramInt = shiftColorUp(paramInt);
      setForeground((Drawable)new RippleDrawable(new ColorStateList(new int[][] { arrayOfInt }, new int[] { paramInt }), localDrawable, null));
      return;
    }
    setForeground(localDrawable);
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int i = getMeasuredWidth() / 2;
    if (mSelected)
    {
      int j = i - t;
      int m = k;
      paramCanvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, i, a);
      paramCanvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, j, x);
      paramCanvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, j - m, b);
      return;
    }
    paramCanvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, i, b);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt1);
    setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
  }
  
  public void setActivated(boolean paramBoolean)
  {
    throw new IllegalStateException("Cannot use setActivated() on CircleView.");
  }
  
  public void setBackground(Drawable paramDrawable)
  {
    throw new IllegalStateException("Cannot use setBackground() on CircleView.");
  }
  
  public void setBackgroundColor(int paramInt)
  {
    update(paramInt);
    requestLayout();
    invalidate();
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    throw new IllegalStateException("Cannot use setBackgroundDrawable() on CircleView.");
  }
  
  public void setBackgroundResource(int paramInt)
  {
    setBackgroundColor(DialogUtils.getColor(getContext(), paramInt));
  }
  
  public void setSelected(boolean paramBoolean)
  {
    mSelected = paramBoolean;
    requestLayout();
    invalidate();
  }
  
  public void showHint(int paramInt)
  {
    int[] arrayOfInt = new int[2];
    Rect localRect = new Rect();
    getLocationOnScreen(arrayOfInt);
    getWindowVisibleDisplayFrame(localRect);
    Object localObject = getContext();
    int i = getWidth();
    int m = getHeight();
    int n = arrayOfInt[1];
    int i1 = m / 2;
    int j = arrayOfInt[0] + i / 2;
    i = j;
    if (ViewCompat.getLayoutDirection(this) == 0) {
      i = getResourcesgetDisplayMetricswidthPixels - j;
    }
    localObject = Toast.makeText((Context)localObject, String.format("#%06X", new Object[] { Integer.valueOf(0xFFFFFF & paramInt) }), 0);
    if (n + i1 < localRect.height()) {
      ((Toast)localObject).setGravity(8388661, i, arrayOfInt[1] + m - top);
    } else {
      ((Toast)localObject).setGravity(81, 0, m);
    }
    ((Toast)localObject).show();
  }
}
