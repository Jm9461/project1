package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
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
import org.org.v4.util.R.color;
import org.org.v4.util.R.dimen;

class RoundRectDrawableWithShadow
  extends Drawable
{
  private static final double COS_45 = Math.cos(Math.toRadians(45.0D));
  static y0.a sRoundRectHelper;
  private boolean mAddPaddingForCorners = true;
  private final RectF mCardBounds;
  private ColorStateList mColor;
  private float mCornerRadius;
  private Paint mCornerShadowPaint;
  private Path mCornerShadowPath;
  private boolean mDirty = true;
  private Paint mEdgeShadowPaint;
  private final int mInsetShadow;
  private Paint mPaint;
  private boolean mPrintedShadowClipWarning = false;
  private float mRawMaxShadowSize;
  private float mRawShadowSize;
  private final int mShadowEndColor;
  private float mShadowSize;
  private final int mShadowStartColor;
  
  RoundRectDrawableWithShadow(Resources paramResources, ColorStateList paramColorStateList, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    mShadowStartColor = paramResources.getColor(R.color.cardview_shadow_start_color);
    mShadowEndColor = paramResources.getColor(R.color.cardview_shadow_end_color);
    mInsetShadow = paramResources.getDimensionPixelSize(R.dimen.cardview_compat_inset_shadow);
    mPaint = new Paint(5);
    setColor(paramColorStateList);
    mCornerShadowPaint = new Paint(5);
    mCornerShadowPaint.setStyle(Paint.Style.FILL);
    mCornerRadius = ((int)(0.5F + paramFloat1));
    mCardBounds = new RectF();
    mEdgeShadowPaint = new Paint(mCornerShadowPaint);
    mEdgeShadowPaint.setAntiAlias(false);
    setShadowSize(paramFloat2, paramFloat3);
  }
  
  private void buildComponents(Rect paramRect)
  {
    float f1 = mRawMaxShadowSize;
    float f2 = 1.5F * f1;
    mCardBounds.set(left + f1, top + f2, right - f1, bottom - f2);
    buildShadowCorners();
  }
  
  private void buildShadowCorners()
  {
    float f1 = mCornerRadius;
    Object localObject1 = new RectF(-f1, -f1, f1, f1);
    Object localObject2 = new RectF((RectF)localObject1);
    f1 = mShadowSize;
    ((RectF)localObject2).inset(-f1, -f1);
    Path localPath = mCornerShadowPath;
    if (localPath == null) {
      mCornerShadowPath = new Path();
    } else {
      localPath.reset();
    }
    mCornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
    mCornerShadowPath.moveTo(-mCornerRadius, 0.0F);
    mCornerShadowPath.rLineTo(-mShadowSize, 0.0F);
    mCornerShadowPath.arcTo((RectF)localObject2, 180.0F, 90.0F, false);
    mCornerShadowPath.arcTo((RectF)localObject1, 270.0F, -90.0F, false);
    mCornerShadowPath.close();
    f1 = mCornerRadius;
    float f2 = mShadowSize;
    float f3 = f1 / (f1 + f2);
    localObject1 = mCornerShadowPaint;
    int i = mShadowStartColor;
    int j = mShadowEndColor;
    localObject2 = Shader.TileMode.CLAMP;
    ((Paint)localObject1).setShader(new RadialGradient(0.0F, 0.0F, f1 + f2, new int[] { i, i, j }, new float[] { 0.0F, f3, 1.0F }, (Shader.TileMode)localObject2));
    localObject1 = mEdgeShadowPaint;
    f3 = mCornerRadius;
    f1 = -f3;
    f2 = mShadowSize;
    f3 = -f3;
    i = mShadowStartColor;
    j = mShadowEndColor;
    localObject2 = Shader.TileMode.CLAMP;
    ((Paint)localObject1).setShader(new LinearGradient(0.0F, f1 + f2, 0.0F, f3 - f2, new int[] { i, i, j }, new float[] { 0.0F, 0.5F, 1.0F }, (Shader.TileMode)localObject2));
    mEdgeShadowPaint.setAntiAlias(false);
  }
  
  static float calculateHorizontalPadding(float paramFloat1, float paramFloat2, boolean paramBoolean)
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
  
  static float calculateVerticalPadding(float paramFloat1, float paramFloat2, boolean paramBoolean)
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
    float f2 = mCornerRadius;
    float f1 = -f2 - mShadowSize;
    f2 = f2 + mInsetShadow + mRawShadowSize / 2.0F;
    float f3 = mCardBounds.width();
    int j = 1;
    if (f3 - f2 * 2.0F > 0.0F) {
      i = 1;
    } else {
      i = 0;
    }
    if (mCardBounds.height() - f2 * 2.0F <= 0.0F) {
      j = 0;
    }
    int k = paramCanvas.save();
    RectF localRectF = mCardBounds;
    paramCanvas.translate(left + f2, top + f2);
    paramCanvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
    if (i != 0) {
      paramCanvas.drawRect(0.0F, f1, mCardBounds.width() - f2 * 2.0F, -mCornerRadius, mEdgeShadowPaint);
    }
    paramCanvas.restoreToCount(k);
    k = paramCanvas.save();
    localRectF = mCardBounds;
    paramCanvas.translate(right - f2, bottom - f2);
    paramCanvas.rotate(180.0F);
    paramCanvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
    if (i != 0) {
      paramCanvas.drawRect(0.0F, f1, mCardBounds.width() - f2 * 2.0F, -mCornerRadius + mShadowSize, mEdgeShadowPaint);
    }
    paramCanvas.restoreToCount(k);
    int i = paramCanvas.save();
    localRectF = mCardBounds;
    paramCanvas.translate(left + f2, bottom - f2);
    paramCanvas.rotate(270.0F);
    paramCanvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
    if (j != 0) {
      paramCanvas.drawRect(0.0F, f1, mCardBounds.height() - f2 * 2.0F, -mCornerRadius, mEdgeShadowPaint);
    }
    paramCanvas.restoreToCount(i);
    i = paramCanvas.save();
    localRectF = mCardBounds;
    paramCanvas.translate(right - f2, top + f2);
    paramCanvas.rotate(90.0F);
    paramCanvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
    if (j != 0) {
      paramCanvas.drawRect(0.0F, f1, mCardBounds.height() - 2.0F * f2, -mCornerRadius, mEdgeShadowPaint);
    }
    paramCanvas.restoreToCount(i);
  }
  
  private void setColor(ColorStateList paramColorStateList)
  {
    ColorStateList localColorStateList = paramColorStateList;
    if (paramColorStateList == null) {
      localColorStateList = ColorStateList.valueOf(0);
    }
    mColor = localColorStateList;
    mPaint.setColor(mColor.getColorForState(getState(), mColor.getDefaultColor()));
  }
  
  private void setShadowSize(float paramFloat1, float paramFloat2)
  {
    if (paramFloat1 >= 0.0F)
    {
      if (paramFloat2 >= 0.0F)
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
        mShadowSize = ((int)(1.5F * paramFloat1 + mInsetShadow + 0.5F));
        mDirty = true;
        invalidateSelf();
        return;
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Invalid max shadow size ");
      localStringBuilder.append(paramFloat2);
      localStringBuilder.append(". Must be >= 0");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Invalid shadow size ");
    localStringBuilder.append(paramFloat1);
    localStringBuilder.append(". Must be >= 0");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  private int toEven(float paramFloat)
  {
    int j = (int)(0.5F + paramFloat);
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
    paramCanvas.translate(0.0F, mRawShadowSize / 2.0F);
    drawShadow(paramCanvas);
    paramCanvas.translate(0.0F, -mRawShadowSize / 2.0F);
    sRoundRectHelper.drawRoundRect(paramCanvas, mCardBounds, mCornerRadius, mPaint);
  }
  
  float getCornerRadius()
  {
    return mCornerRadius;
  }
  
  void getMaxShadowAndCornerPadding(Rect paramRect)
  {
    getPadding(paramRect);
  }
  
  float getMaxShadowSize()
  {
    return mRawMaxShadowSize;
  }
  
  float getMinHeight()
  {
    float f = mRawMaxShadowSize;
    f = Math.max(f, mCornerRadius + mInsetShadow + f / 2.0F);
    return (mRawMaxShadowSize + mInsetShadow) * 2.0F + f * 2.0F;
  }
  
  float getMinWidth()
  {
    float f = mRawMaxShadowSize;
    f = Math.max(f, mCornerRadius + mInsetShadow + f * 1.5F / 2.0F);
    return (mRawMaxShadowSize * 1.5F + mInsetShadow) * 2.0F + f * 2.0F;
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
  
  float getShadowSize()
  {
    return mRawShadowSize;
  }
  
  public boolean isStateful()
  {
    ColorStateList localColorStateList = mColor;
    return ((localColorStateList != null) && (localColorStateList.isStateful())) || (super.isStateful());
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    super.onBoundsChange(paramRect);
    mDirty = true;
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    ColorStateList localColorStateList = mColor;
    int i = localColorStateList.getColorForState(paramArrayOfInt, localColorStateList.getDefaultColor());
    if (mPaint.getColor() == i) {
      return false;
    }
    mPaint.setColor(i);
    mDirty = true;
    invalidateSelf();
    return true;
  }
  
  void setAddPaddingForCorners(boolean paramBoolean)
  {
    mAddPaddingForCorners = paramBoolean;
    invalidateSelf();
  }
  
  public void setAlpha(int paramInt)
  {
    mPaint.setAlpha(paramInt);
    mCornerShadowPaint.setAlpha(paramInt);
    mEdgeShadowPaint.setAlpha(paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mPaint.setColorFilter(paramColorFilter);
  }
  
  void setCornerRadius(float paramFloat)
  {
    if (paramFloat >= 0.0F)
    {
      paramFloat = (int)(0.5F + paramFloat);
      if (mCornerRadius == paramFloat) {
        return;
      }
      mCornerRadius = paramFloat;
      mDirty = true;
      invalidateSelf();
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Invalid radius ");
    localStringBuilder.append(paramFloat);
    localStringBuilder.append(". Must be >= 0");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  ColorStateList setMaxShadowSize()
  {
    return mColor;
  }
  
  void setMaxShadowSize(float paramFloat)
  {
    setShadowSize(mRawShadowSize, paramFloat);
  }
  
  void setShadowSize(float paramFloat)
  {
    setShadowSize(paramFloat, mRawMaxShadowSize);
  }
  
  void setShadowSize(ColorStateList paramColorStateList)
  {
    setColor(paramColorStateList);
    invalidateSelf();
  }
}
