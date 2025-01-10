package android.support.design.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import org.org.android.R.color;
import org.org.v4.graphics.drawable.DrawableWrapper;

public class ShadowDrawableWrapper
  extends DrawableWrapper
{
  static final double COS_45 = Math.cos(Math.toRadians(45.0D));
  private boolean mAddPaddingForCorners = true;
  final RectF mContentBounds;
  float mCornerRadius;
  final Paint mCornerShadowPaint;
  Path mCornerShadowPath;
  private boolean mDirty = true;
  final Paint mEdgeShadowPaint;
  private boolean mPrintedShadowClipWarning = false;
  float mRawMaxShadowSize;
  float mRawShadowSize;
  private float mRotation;
  private final int mShadowEndColor;
  private final int mShadowMiddleColor;
  float mShadowSize;
  private final int mShadowStartColor;
  
  public ShadowDrawableWrapper(Context paramContext, Drawable paramDrawable, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    super(paramDrawable);
    mShadowStartColor = ContextCompat.getColor(paramContext, R.color.design_fab_shadow_start_color);
    mShadowMiddleColor = ContextCompat.getColor(paramContext, R.color.design_fab_shadow_mid_color);
    mShadowEndColor = ContextCompat.getColor(paramContext, R.color.design_fab_shadow_end_color);
    mCornerShadowPaint = new Paint(5);
    mCornerShadowPaint.setStyle(Paint.Style.FILL);
    mCornerRadius = Math.round(paramFloat1);
    mContentBounds = new RectF();
    mEdgeShadowPaint = new Paint(mCornerShadowPaint);
    mEdgeShadowPaint.setAntiAlias(false);
    setShadowSize(paramFloat2, paramFloat3);
  }
  
  private void buildComponents(Rect paramRect)
  {
    float f1 = mRawMaxShadowSize;
    float f2 = 1.5F * f1;
    mContentBounds.set(left + f1, top + f2, right - f1, bottom - f2);
    paramRect = getWrappedDrawable();
    RectF localRectF = mContentBounds;
    paramRect.setBounds((int)left, (int)top, (int)right, (int)bottom);
    buildShadowCorners();
  }
  
  private void buildShadowCorners()
  {
    float f1 = mCornerRadius;
    Object localObject1 = new RectF(-f1, -f1, f1, f1);
    RectF localRectF = new RectF((RectF)localObject1);
    f1 = mShadowSize;
    localRectF.inset(-f1, -f1);
    Object localObject2 = mCornerShadowPath;
    if (localObject2 == null) {
      mCornerShadowPath = new Path();
    } else {
      ((Path)localObject2).reset();
    }
    mCornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
    mCornerShadowPath.moveTo(-mCornerRadius, 0.0F);
    mCornerShadowPath.rLineTo(-mShadowSize, 0.0F);
    mCornerShadowPath.arcTo(localRectF, 180.0F, 90.0F, false);
    mCornerShadowPath.arcTo((RectF)localObject1, 270.0F, -90.0F, false);
    mCornerShadowPath.close();
    f1 = -top;
    if (f1 > 0.0F)
    {
      f2 = mCornerRadius / f1;
      float f3 = (1.0F - f2) / 2.0F;
      localObject2 = mCornerShadowPaint;
      i = mShadowStartColor;
      j = mShadowMiddleColor;
      k = mShadowEndColor;
      Shader.TileMode localTileMode = Shader.TileMode.CLAMP;
      ((Paint)localObject2).setShader(new RadialGradient(0.0F, 0.0F, f1, new int[] { 0, i, j, k }, new float[] { 0.0F, f2, f2 + f3, 1.0F }, localTileMode));
    }
    localObject2 = mEdgeShadowPaint;
    f1 = top;
    float f2 = top;
    int i = mShadowStartColor;
    int j = mShadowMiddleColor;
    int k = mShadowEndColor;
    localObject1 = Shader.TileMode.CLAMP;
    ((Paint)localObject2).setShader(new LinearGradient(0.0F, f1, 0.0F, f2, new int[] { i, j, k }, new float[] { 0.0F, 0.5F, 1.0F }, (Shader.TileMode)localObject1));
    mEdgeShadowPaint.setAntiAlias(false);
  }
  
  public static float calculateHorizontalPadding(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    float f = paramFloat1;
    if (paramBoolean)
    {
      double d1 = paramFloat1;
      double d2 = COS_45;
      double d3 = paramFloat2;
      Double.isNaN(d3);
      Double.isNaN(d1);
      f = (float)(d1 + (1.0D - d2) * d3);
    }
    return f;
  }
  
  public static float calculateVerticalPadding(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      double d1 = 1.5F * paramFloat1;
      double d2 = COS_45;
      double d3 = paramFloat2;
      Double.isNaN(d3);
      Double.isNaN(d1);
      return (float)(d1 + (1.0D - d2) * d3);
    }
    return 1.5F * paramFloat1;
  }
  
  private void drawShadow(Canvas paramCanvas)
  {
    int k = paramCanvas.save();
    paramCanvas.rotate(mRotation, mContentBounds.centerX(), mContentBounds.centerY());
    float f1 = -mCornerRadius - mShadowSize;
    float f2 = mCornerRadius;
    float f3 = mContentBounds.width();
    int j = 1;
    if (f3 - f2 * 2.0F > 0.0F) {
      i = 1;
    } else {
      i = 0;
    }
    if (mContentBounds.height() - f2 * 2.0F <= 0.0F) {
      j = 0;
    }
    float f5 = mRawShadowSize;
    f3 = f2 / (f2 + (f5 - 0.5F * f5));
    float f4 = f2 / (f2 + (f5 - 0.25F * f5));
    f5 = f2 / (f2 + (f5 - f5 * 1.0F));
    int m = paramCanvas.save();
    RectF localRectF = mContentBounds;
    paramCanvas.translate(left + f2, top + f2);
    paramCanvas.scale(f3, f4);
    paramCanvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
    if (i != 0)
    {
      paramCanvas.scale(1.0F / f3, 1.0F);
      paramCanvas.drawRect(0.0F, f1, mContentBounds.width() - f2 * 2.0F, -mCornerRadius, mEdgeShadowPaint);
    }
    paramCanvas.restoreToCount(m);
    m = paramCanvas.save();
    localRectF = mContentBounds;
    paramCanvas.translate(right - f2, bottom - f2);
    paramCanvas.scale(f3, f5);
    paramCanvas.rotate(180.0F);
    paramCanvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
    if (i != 0)
    {
      paramCanvas.scale(1.0F / f3, 1.0F);
      paramCanvas.drawRect(0.0F, f1, mContentBounds.width() - f2 * 2.0F, -mCornerRadius + mShadowSize, mEdgeShadowPaint);
    }
    paramCanvas.restoreToCount(m);
    int i = paramCanvas.save();
    localRectF = mContentBounds;
    paramCanvas.translate(left + f2, bottom - f2);
    paramCanvas.scale(f3, f5);
    paramCanvas.rotate(270.0F);
    paramCanvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
    if (j != 0)
    {
      paramCanvas.scale(1.0F / f5, 1.0F);
      paramCanvas.drawRect(0.0F, f1, mContentBounds.height() - f2 * 2.0F, -mCornerRadius, mEdgeShadowPaint);
    }
    paramCanvas.restoreToCount(i);
    i = paramCanvas.save();
    localRectF = mContentBounds;
    paramCanvas.translate(right - f2, top + f2);
    paramCanvas.scale(f3, f4);
    paramCanvas.rotate(90.0F);
    paramCanvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
    if (j != 0)
    {
      paramCanvas.scale(1.0F / f4, 1.0F);
      paramCanvas.drawRect(0.0F, f1, mContentBounds.height() - 2.0F * f2, -mCornerRadius, mEdgeShadowPaint);
    }
    paramCanvas.restoreToCount(i);
    paramCanvas.restoreToCount(k);
  }
  
  private static int toEven(float paramFloat)
  {
    int j = Math.round(paramFloat);
    int i = j;
    if (j % 2 == 1) {
      i = j - 1;
    }
    return i;
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (mDirty)
    {
      buildComponents(getBounds());
      mDirty = false;
    }
    drawShadow(paramCanvas);
    super.draw(paramCanvas);
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public boolean getPadding(Rect paramRect)
  {
    int i = (int)Math.ceil(calculateVerticalPadding(mRawMaxShadowSize, mCornerRadius, mAddPaddingForCorners));
    int j = (int)Math.ceil(calculateHorizontalPadding(mRawMaxShadowSize, mCornerRadius, mAddPaddingForCorners));
    paramRect.set(j, i, j, i);
    return true;
  }
  
  public float getShadowSize()
  {
    return mRawShadowSize;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    mDirty = true;
  }
  
  public void setAddPaddingForCorners(boolean paramBoolean)
  {
    mAddPaddingForCorners = paramBoolean;
    invalidateSelf();
  }
  
  public void setAlpha(int paramInt)
  {
    super.setAlpha(paramInt);
    mCornerShadowPaint.setAlpha(paramInt);
    mEdgeShadowPaint.setAlpha(paramInt);
  }
  
  public final void setRotation(float paramFloat)
  {
    if (mRotation != paramFloat)
    {
      mRotation = paramFloat;
      invalidateSelf();
    }
  }
  
  public void setShadowSize(float paramFloat)
  {
    setShadowSize(paramFloat, mRawMaxShadowSize);
  }
  
  public void setShadowSize(float paramFloat1, float paramFloat2)
  {
    if ((paramFloat1 >= 0.0F) && (paramFloat2 >= 0.0F))
    {
      float f1 = toEven(paramFloat1);
      float f2 = toEven(paramFloat2);
      paramFloat1 = f1;
      if (f1 > f2)
      {
        paramFloat2 = f2;
        paramFloat1 = paramFloat2;
        if (!mPrintedShadowClipWarning)
        {
          mPrintedShadowClipWarning = true;
          paramFloat1 = paramFloat2;
        }
      }
      if ((mRawShadowSize == paramFloat1) && (mRawMaxShadowSize == f2)) {
        return;
      }
      mRawShadowSize = paramFloat1;
      mRawMaxShadowSize = f2;
      mShadowSize = Math.round(1.5F * paramFloat1);
      mDirty = true;
      invalidateSelf();
      return;
    }
    throw new IllegalArgumentException("invalid shadow size");
  }
}
