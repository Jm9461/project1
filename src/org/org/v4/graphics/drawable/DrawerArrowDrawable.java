package org.org.v4.graphics.drawable;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import org.org.v4.content.R.attr;
import org.org.v4.content.R.style;
import org.org.v4.content.R.styleable;

public class DrawerArrowDrawable
  extends Drawable
{
  private static final float ARROW_HEAD_ANGLE = (float)Math.toRadians(45.0D);
  private float mArrowHeadLength;
  private float mArrowShaftLength;
  private float mBarGap;
  private float mBarLength;
  private int mDirection = 2;
  private float mMaxCutForBarSize;
  private final Paint mPaint = new Paint();
  private final Path mPath = new Path();
  private float mProgress;
  private final int mSize;
  private boolean mSpin;
  private boolean mVerticalMirror = false;
  
  public DrawerArrowDrawable(Context paramContext)
  {
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeJoin(Paint.Join.MITER);
    mPaint.setStrokeCap(Paint.Cap.BUTT);
    mPaint.setAntiAlias(true);
    paramContext = paramContext.getTheme().obtainStyledAttributes(null, R.styleable.DrawerArrowToggle, R.attr.drawerArrowStyle, R.style.Base_Widget_AppCompat_DrawerArrowToggle);
    setColor(paramContext.getColor(R.styleable.DrawerArrowToggle_color, 0));
    setBarThickness(paramContext.getDimension(R.styleable.DrawerArrowToggle_thickness, 0.0F));
    setSpinEnabled(paramContext.getBoolean(R.styleable.DrawerArrowToggle_spinBars, true));
    setGapSize(Math.round(paramContext.getDimension(R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0F)));
    mSize = paramContext.getDimensionPixelSize(R.styleable.DrawerArrowToggle_drawableSize, 0);
    mBarLength = Math.round(paramContext.getDimension(R.styleable.DrawerArrowToggle_barLength, 0.0F));
    mArrowHeadLength = Math.round(paramContext.getDimension(R.styleable.DrawerArrowToggle_arrowHeadLength, 0.0F));
    mArrowShaftLength = paramContext.getDimension(R.styleable.DrawerArrowToggle_arrowShaftLength, 0.0F);
    paramContext.recycle();
  }
  
  private static float lerp(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return (paramFloat2 - paramFloat1) * paramFloat3 + paramFloat1;
  }
  
  public void draw(Canvas paramCanvas)
  {
    Rect localRect = getBounds();
    int k = mDirection;
    int i;
    if (k != 0)
    {
      if (k != 1)
      {
        int j = 0;
        i = 0;
        if (k != 3)
        {
          if (DrawableCompat.getLayoutDirection(this) == 1) {
            i = 1;
          }
        }
        else
        {
          i = j;
          if (DrawableCompat.getLayoutDirection(this) == 0) {
            i = 1;
          }
        }
      }
      else
      {
        i = 1;
      }
    }
    else {
      i = 0;
    }
    float f1 = mArrowHeadLength;
    f1 = (float)Math.sqrt(f1 * f1 * 2.0F);
    float f5 = lerp(mBarLength, f1, mProgress);
    float f3 = lerp(mBarLength, mArrowShaftLength, mProgress);
    float f4 = Math.round(lerp(0.0F, mMaxCutForBarSize, mProgress));
    float f6 = lerp(0.0F, ARROW_HEAD_ANGLE, mProgress);
    if (i != 0) {
      f1 = 0.0F;
    } else {
      f1 = -180.0F;
    }
    if (i != 0) {
      f2 = 180.0F;
    } else {
      f2 = 0.0F;
    }
    f1 = lerp(f1, f2, mProgress);
    double d1 = f5;
    double d2 = Math.cos(f6);
    Double.isNaN(d1);
    float f2 = (float)Math.round(d1 * d2);
    d1 = f5;
    d2 = Math.sin(f6);
    Double.isNaN(d1);
    f5 = (float)Math.round(d1 * d2);
    mPath.rewind();
    f6 = lerp(mBarGap + mPaint.getStrokeWidth(), -mMaxCutForBarSize, mProgress);
    float f7 = -f3 / 2.0F;
    mPath.moveTo(f7 + f4, 0.0F);
    mPath.rLineTo(f3 - f4 * 2.0F, 0.0F);
    mPath.moveTo(f7, f6);
    mPath.rLineTo(f2, f5);
    mPath.moveTo(f7, -f6);
    mPath.rLineTo(f2, -f5);
    mPath.close();
    paramCanvas.save();
    f2 = mPaint.getStrokeWidth();
    f4 = localRect.height();
    f3 = mBarGap;
    f4 = (int)(f4 - 3.0F * f2 - 2.0F * f3) / 4 * 2;
    paramCanvas.translate(localRect.centerX(), f4 + (1.5F * f2 + f3));
    if (mSpin)
    {
      if ((mVerticalMirror ^ i)) {
        i = -1;
      } else {
        i = 1;
      }
      paramCanvas.rotate(i * f1);
    }
    else if (i != 0)
    {
      paramCanvas.rotate(180.0F);
    }
    paramCanvas.drawPath(mPath, mPaint);
    paramCanvas.restore();
  }
  
  public int getIntrinsicHeight()
  {
    return mSize;
  }
  
  public int getIntrinsicWidth()
  {
    return mSize;
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public void setAlpha(int paramInt)
  {
    if (paramInt != mPaint.getAlpha())
    {
      mPaint.setAlpha(paramInt);
      invalidateSelf();
    }
  }
  
  public void setBarThickness(float paramFloat)
  {
    if (mPaint.getStrokeWidth() != paramFloat)
    {
      mPaint.setStrokeWidth(paramFloat);
      double d1 = paramFloat / 2.0F;
      double d2 = Math.cos(ARROW_HEAD_ANGLE);
      Double.isNaN(d1);
      mMaxCutForBarSize = ((float)(d1 * d2));
      invalidateSelf();
    }
  }
  
  public void setColor(int paramInt)
  {
    if (paramInt != mPaint.getColor())
    {
      mPaint.setColor(paramInt);
      invalidateSelf();
    }
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mPaint.setColorFilter(paramColorFilter);
    invalidateSelf();
  }
  
  public void setGapSize(float paramFloat)
  {
    if (paramFloat != mBarGap)
    {
      mBarGap = paramFloat;
      invalidateSelf();
    }
  }
  
  public void setProgress(float paramFloat)
  {
    if (mProgress != paramFloat)
    {
      mProgress = paramFloat;
      invalidateSelf();
    }
  }
  
  public void setSpinEnabled(boolean paramBoolean)
  {
    if (mSpin != paramBoolean)
    {
      mSpin = paramBoolean;
      invalidateSelf();
    }
  }
  
  public void setVerticalMirror(boolean paramBoolean)
  {
    if (mVerticalMirror != paramBoolean)
    {
      mVerticalMirror = paramBoolean;
      invalidateSelf();
    }
  }
}
