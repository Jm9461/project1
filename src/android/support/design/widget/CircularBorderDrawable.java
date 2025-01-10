package android.support.design.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import org.org.jaxen.asm.ColorUtils;

public class CircularBorderDrawable
  extends Drawable
{
  private ColorStateList mBorderTint;
  float mBorderWidth;
  private int mBottomInnerStrokeColor;
  private int mBottomOuterStrokeColor;
  private int mCurrentBorderTintColor;
  private boolean mInvalidateShader = true;
  final Paint mPaint = new Paint(1);
  final Rect mRect = new Rect();
  final RectF mRectF = new RectF();
  private float mRotation;
  final ColorDrawable.ColorState mState = new ColorDrawable.ColorState(this, null);
  private int mTopInnerStrokeColor;
  private int mTopOuterStrokeColor;
  
  public CircularBorderDrawable()
  {
    mPaint.setStyle(Paint.Style.STROKE);
  }
  
  private Shader createGradientShader()
  {
    Object localObject = mRect;
    copyBounds((Rect)localObject);
    float f1 = mBorderWidth / ((Rect)localObject).height();
    int i = ColorUtils.compositeColors(mBottomOuterStrokeColor, mCurrentBorderTintColor);
    int j = ColorUtils.compositeColors(mBottomInnerStrokeColor, mCurrentBorderTintColor);
    int k = ColorUtils.compositeColors(ColorUtils.setAlphaComponent(mBottomInnerStrokeColor, 0), mCurrentBorderTintColor);
    int m = ColorUtils.compositeColors(ColorUtils.setAlphaComponent(mTopInnerStrokeColor, 0), mCurrentBorderTintColor);
    int n = ColorUtils.compositeColors(mTopInnerStrokeColor, mCurrentBorderTintColor);
    int i1 = ColorUtils.compositeColors(mTopOuterStrokeColor, mCurrentBorderTintColor);
    float f2 = top;
    float f3 = bottom;
    localObject = Shader.TileMode.CLAMP;
    return new LinearGradient(0.0F, f2, 0.0F, f3, new int[] { i, j, k, m, n, i1 }, new float[] { 0.0F, f1, 0.5F, 0.5F, 1.0F - f1, 1.0F }, (Shader.TileMode)localObject);
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (mInvalidateShader)
    {
      mPaint.setShader(createGradientShader());
      mInvalidateShader = false;
    }
    float f = mPaint.getStrokeWidth() / 2.0F;
    RectF localRectF = mRectF;
    copyBounds(mRect);
    localRectF.set(mRect);
    left += f;
    top += f;
    right -= f;
    bottom -= f;
    paramCanvas.save();
    paramCanvas.rotate(mRotation, localRectF.centerX(), localRectF.centerY());
    paramCanvas.drawOval(localRectF, mPaint);
    paramCanvas.restore();
  }
  
  public Drawable.ConstantState getConstantState()
  {
    return mState;
  }
  
  public int getOpacity()
  {
    if (mBorderWidth > 0.0F) {
      return -3;
    }
    return -2;
  }
  
  public boolean getPadding(Rect paramRect)
  {
    int i = Math.round(mBorderWidth);
    paramRect.set(i, i, i, i);
    return true;
  }
  
  public boolean isStateful()
  {
    ColorStateList localColorStateList = mBorderTint;
    return ((localColorStateList != null) && (localColorStateList.isStateful())) || (super.isStateful());
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    mInvalidateShader = true;
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    ColorStateList localColorStateList = mBorderTint;
    if (localColorStateList != null)
    {
      int i = localColorStateList.getColorForState(paramArrayOfInt, mCurrentBorderTintColor);
      if (i != mCurrentBorderTintColor)
      {
        mInvalidateShader = true;
        mCurrentBorderTintColor = i;
      }
    }
    if (mInvalidateShader) {
      invalidateSelf();
    }
    return mInvalidateShader;
  }
  
  public void setAlpha(int paramInt)
  {
    mPaint.setAlpha(paramInt);
    invalidateSelf();
  }
  
  public void setBorderTint(ColorStateList paramColorStateList)
  {
    if (paramColorStateList != null) {
      mCurrentBorderTintColor = paramColorStateList.getColorForState(getState(), mCurrentBorderTintColor);
    }
    mBorderTint = paramColorStateList;
    mInvalidateShader = true;
    invalidateSelf();
  }
  
  public void setBorderWidth(float paramFloat)
  {
    if (mBorderWidth != paramFloat)
    {
      mBorderWidth = paramFloat;
      mPaint.setStrokeWidth(1.3333F * paramFloat);
      mInvalidateShader = true;
      invalidateSelf();
    }
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mPaint.setColorFilter(paramColorFilter);
    invalidateSelf();
  }
  
  public void setGradientColors(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mBottomOuterStrokeColor = paramInt1;
    mBottomInnerStrokeColor = paramInt2;
    mTopOuterStrokeColor = paramInt3;
    mTopInnerStrokeColor = paramInt4;
  }
  
  public final void setRotation(float paramFloat)
  {
    if (paramFloat != mRotation)
    {
      mRotation = paramFloat;
      invalidateSelf();
    }
  }
}
