package com.mohamadamin.persianmaterialdatetimepicker.time;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.util.Log;
import com.mohamadamin.persianmaterialdatetimepicker.R.color;
import com.mohamadamin.persianmaterialdatetimepicker.R.string;

public class RadialTextsView
  extends android.view.View
{
  private float mAmPmCircleRadiusMultiplier;
  private float mAnimationRadiusMultiplier;
  private float mCircleRadius;
  private float mCircleRadiusMultiplier;
  ObjectAnimator mDisappearAnimator;
  private boolean mDrawValuesReady;
  private boolean mHasInnerCircle;
  private float mInnerNumbersRadiusMultiplier;
  private float[] mInnerTextGridHeights;
  private float[] mInnerTextGridWidths;
  private float mInnerTextSize;
  private float mInnerTextSizeMultiplier;
  private String[] mInnerTexts;
  private RadialSelectorView.InvalidateUpdateListener mInvalidateUpdateListener;
  private boolean mIs24HourMode;
  private boolean mIsInitialized = false;
  private float mNumbersRadiusMultiplier;
  private final Paint mPaint = new Paint();
  ObjectAnimator mReappearAnimator;
  private final Paint mSelectedPaint = new Paint();
  private float[] mTextGridHeights;
  private boolean mTextGridValuesDirty;
  private float[] mTextGridWidths;
  private float mTextSize;
  private float mTextSizeMultiplier;
  private String[] mTexts;
  private float mTransitionEndRadiusMultiplier;
  private float mTransitionMidRadiusMultiplier;
  private Typeface mTypefaceLight;
  private Typeface mTypefaceRegular;
  private int mXCenter;
  private int mYCenter;
  private int selection = -1;
  
  public RadialTextsView(Context paramContext)
  {
    super(paramContext);
  }
  
  private void calculateGridSizes(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    float f1 = (float)Math.sqrt(3.0D) * paramFloat1 / 2.0F;
    float f2 = paramFloat1 / 2.0F;
    mPaint.setTextSize(paramFloat4);
    mSelectedPaint.setTextSize(paramFloat4);
    paramFloat3 -= (mPaint.descent() + mPaint.ascent()) / 2.0F;
    paramArrayOfFloat1[0] = (paramFloat3 - paramFloat1);
    paramArrayOfFloat2[0] = (paramFloat2 - paramFloat1);
    paramArrayOfFloat1[1] = (paramFloat3 - f1);
    paramArrayOfFloat2[1] = (paramFloat2 - f1);
    paramArrayOfFloat1[2] = (paramFloat3 - f2);
    paramArrayOfFloat2[2] = (paramFloat2 - f2);
    paramArrayOfFloat1[3] = paramFloat3;
    paramArrayOfFloat2[3] = paramFloat2;
    paramArrayOfFloat1[4] = (paramFloat3 + f2);
    paramArrayOfFloat2[4] = (paramFloat2 + f2);
    paramArrayOfFloat1[5] = (paramFloat3 + f1);
    paramArrayOfFloat2[5] = (paramFloat2 + f1);
    paramArrayOfFloat1[6] = (paramFloat3 + paramFloat1);
    paramArrayOfFloat2[6] = (paramFloat2 + paramFloat1);
  }
  
  private void drawTexts(Canvas paramCanvas, float paramFloat, Typeface paramTypeface, String[] paramArrayOfString, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    mPaint.setTextSize(paramFloat);
    mPaint.setTypeface(paramTypeface);
    com.mohamadamin.persianmaterialdatetimepicker.views.View.update(paramArrayOfString);
    String str = paramArrayOfString[0];
    paramFloat = paramArrayOfFloat1[3];
    float f = paramArrayOfFloat2[0];
    if (Integer.parseInt(paramArrayOfString[0]) == selection) {
      paramTypeface = mSelectedPaint;
    } else {
      paramTypeface = mPaint;
    }
    paramCanvas.drawText(str, paramFloat, f, paramTypeface);
    str = paramArrayOfString[1];
    paramFloat = paramArrayOfFloat1[4];
    f = paramArrayOfFloat2[1];
    if (Integer.parseInt(paramArrayOfString[1]) == selection) {
      paramTypeface = mSelectedPaint;
    } else {
      paramTypeface = mPaint;
    }
    paramCanvas.drawText(str, paramFloat, f, paramTypeface);
    str = paramArrayOfString[2];
    paramFloat = paramArrayOfFloat1[5];
    f = paramArrayOfFloat2[2];
    if (Integer.parseInt(paramArrayOfString[2]) == selection) {
      paramTypeface = mSelectedPaint;
    } else {
      paramTypeface = mPaint;
    }
    paramCanvas.drawText(str, paramFloat, f, paramTypeface);
    str = paramArrayOfString[3];
    paramFloat = paramArrayOfFloat1[6];
    f = paramArrayOfFloat2[3];
    if (Integer.parseInt(paramArrayOfString[3]) == selection) {
      paramTypeface = mSelectedPaint;
    } else {
      paramTypeface = mPaint;
    }
    paramCanvas.drawText(str, paramFloat, f, paramTypeface);
    str = paramArrayOfString[4];
    paramFloat = paramArrayOfFloat1[5];
    f = paramArrayOfFloat2[4];
    if (Integer.parseInt(paramArrayOfString[4]) == selection) {
      paramTypeface = mSelectedPaint;
    } else {
      paramTypeface = mPaint;
    }
    paramCanvas.drawText(str, paramFloat, f, paramTypeface);
    str = paramArrayOfString[5];
    paramFloat = paramArrayOfFloat1[4];
    f = paramArrayOfFloat2[5];
    if (Integer.parseInt(paramArrayOfString[5]) == selection) {
      paramTypeface = mSelectedPaint;
    } else {
      paramTypeface = mPaint;
    }
    paramCanvas.drawText(str, paramFloat, f, paramTypeface);
    str = paramArrayOfString[6];
    paramFloat = paramArrayOfFloat1[3];
    f = paramArrayOfFloat2[6];
    if (Integer.parseInt(paramArrayOfString[6]) == selection) {
      paramTypeface = mSelectedPaint;
    } else {
      paramTypeface = mPaint;
    }
    paramCanvas.drawText(str, paramFloat, f, paramTypeface);
    str = paramArrayOfString[7];
    paramFloat = paramArrayOfFloat1[2];
    f = paramArrayOfFloat2[5];
    if (Integer.parseInt(paramArrayOfString[7]) == selection) {
      paramTypeface = mSelectedPaint;
    } else {
      paramTypeface = mPaint;
    }
    paramCanvas.drawText(str, paramFloat, f, paramTypeface);
    str = paramArrayOfString[8];
    paramFloat = paramArrayOfFloat1[1];
    f = paramArrayOfFloat2[4];
    if (Integer.parseInt(paramArrayOfString[8]) == selection) {
      paramTypeface = mSelectedPaint;
    } else {
      paramTypeface = mPaint;
    }
    paramCanvas.drawText(str, paramFloat, f, paramTypeface);
    str = paramArrayOfString[9];
    paramFloat = paramArrayOfFloat1[0];
    f = paramArrayOfFloat2[3];
    if (Integer.parseInt(paramArrayOfString[9]) == selection) {
      paramTypeface = mSelectedPaint;
    } else {
      paramTypeface = mPaint;
    }
    paramCanvas.drawText(str, paramFloat, f, paramTypeface);
    str = paramArrayOfString[10];
    paramFloat = paramArrayOfFloat1[1];
    f = paramArrayOfFloat2[2];
    if (Integer.parseInt(paramArrayOfString[10]) == selection) {
      paramTypeface = mSelectedPaint;
    } else {
      paramTypeface = mPaint;
    }
    paramCanvas.drawText(str, paramFloat, f, paramTypeface);
    str = paramArrayOfString[11];
    paramFloat = paramArrayOfFloat1[2];
    f = paramArrayOfFloat2[1];
    if (Integer.parseInt(paramArrayOfString[11]) == selection) {
      paramTypeface = mSelectedPaint;
    } else {
      paramTypeface = mPaint;
    }
    paramCanvas.drawText(str, paramFloat, f, paramTypeface);
  }
  
  private void renderAnimations()
  {
    mDisappearAnimator = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[] { PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", new Keyframe[] { Keyframe.ofFloat(0.0F, 1.0F), Keyframe.ofFloat(0.2F, mTransitionMidRadiusMultiplier), Keyframe.ofFloat(1.0F, mTransitionEndRadiusMultiplier) }), PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[] { Keyframe.ofFloat(0.0F, 1.0F), Keyframe.ofFloat(1.0F, 0.0F) }) }).setDuration('?');
    mDisappearAnimator.addUpdateListener(mInvalidateUpdateListener);
    int i = (int)(500.0F * (1.0F + 0.25F));
    float f = 500.0F * 0.25F / i;
    mReappearAnimator = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[] { PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", new Keyframe[] { Keyframe.ofFloat(0.0F, mTransitionEndRadiusMultiplier), Keyframe.ofFloat(f, mTransitionEndRadiusMultiplier), Keyframe.ofFloat(1.0F - (1.0F - f) * 0.2F, mTransitionMidRadiusMultiplier), Keyframe.ofFloat(1.0F, 1.0F) }), PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[] { Keyframe.ofFloat(0.0F, 0.0F), Keyframe.ofFloat(f, 0.0F), Keyframe.ofFloat(1.0F, 1.0F) }) }).setDuration(i);
    mReappearAnimator.addUpdateListener(mInvalidateUpdateListener);
  }
  
  public ObjectAnimator getDisappearAnimator()
  {
    if ((mIsInitialized) && (mDrawValuesReady))
    {
      ObjectAnimator localObjectAnimator = mDisappearAnimator;
      if (localObjectAnimator != null) {
        return localObjectAnimator;
      }
    }
    Log.e("RadialTextsView", "RadialTextView was not ready for animation.");
    return null;
  }
  
  public ObjectAnimator getReappearAnimator()
  {
    if ((mIsInitialized) && (mDrawValuesReady))
    {
      ObjectAnimator localObjectAnimator = mReappearAnimator;
      if (localObjectAnimator != null) {
        return localObjectAnimator;
      }
    }
    Log.e("RadialTextsView", "RadialTextView was not ready for animation.");
    return null;
  }
  
  public boolean hasOverlappingRendering()
  {
    return false;
  }
  
  public void initialize(Resources paramResources, String[] paramArrayOfString1, String[] paramArrayOfString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (mIsInitialized)
    {
      Log.e("RadialTextsView", "This RadialTextsView may only be initialized once.");
      return;
    }
    int i = paramResources.getColor(R.color.mdtp_numbers_text_color);
    mPaint.setColor(i);
    String str = paramResources.getString(R.string.mdtp_radial_numbers_typeface);
    boolean bool = false;
    mTypefaceLight = Typeface.create(str, 0);
    mTypefaceRegular = Typeface.create(paramResources.getString(R.string.mdtp_sans_serif), 0);
    mPaint.setAntiAlias(true);
    mPaint.setTextAlign(Paint.Align.CENTER);
    i = paramResources.getColor(R.color.mdtp_white);
    mSelectedPaint.setColor(i);
    mSelectedPaint.setAntiAlias(true);
    mSelectedPaint.setTextAlign(Paint.Align.CENTER);
    mTexts = paramArrayOfString1;
    mInnerTexts = paramArrayOfString2;
    mIs24HourMode = paramBoolean1;
    if (paramArrayOfString2 != null) {
      bool = true;
    }
    mHasInnerCircle = bool;
    if (paramBoolean1)
    {
      mCircleRadiusMultiplier = Float.parseFloat(paramResources.getString(R.string.mdtp_circle_radius_multiplier_24HourMode));
    }
    else
    {
      mCircleRadiusMultiplier = Float.parseFloat(paramResources.getString(R.string.mdtp_circle_radius_multiplier));
      mAmPmCircleRadiusMultiplier = Float.parseFloat(paramResources.getString(R.string.mdtp_ampm_circle_radius_multiplier));
    }
    mTextGridHeights = new float[7];
    mTextGridWidths = new float[7];
    if (mHasInnerCircle)
    {
      mNumbersRadiusMultiplier = Float.parseFloat(paramResources.getString(R.string.mdtp_numbers_radius_multiplier_outer));
      mTextSizeMultiplier = Float.parseFloat(paramResources.getString(R.string.mdtp_text_size_multiplier_outer));
      mInnerNumbersRadiusMultiplier = Float.parseFloat(paramResources.getString(R.string.mdtp_numbers_radius_multiplier_inner));
      mInnerTextSizeMultiplier = Float.parseFloat(paramResources.getString(R.string.mdtp_text_size_multiplier_inner));
      mInnerTextGridHeights = new float[7];
      mInnerTextGridWidths = new float[7];
    }
    else
    {
      mNumbersRadiusMultiplier = Float.parseFloat(paramResources.getString(R.string.mdtp_numbers_radius_multiplier_normal));
      mTextSizeMultiplier = Float.parseFloat(paramResources.getString(R.string.mdtp_text_size_multiplier_normal));
    }
    mAnimationRadiusMultiplier = 1.0F;
    int j = -1;
    if (paramBoolean2) {
      i = -1;
    } else {
      i = 1;
    }
    mTransitionMidRadiusMultiplier = (i * 0.05F + 1.0F);
    i = j;
    if (paramBoolean2) {
      i = 1;
    }
    mTransitionEndRadiusMultiplier = (i * 0.3F + 1.0F);
    mInvalidateUpdateListener = new RadialSelectorView.InvalidateUpdateListener(this, null);
    mTextGridValuesDirty = true;
    mIsInitialized = true;
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if (getWidth() != 0)
    {
      if (!mIsInitialized) {
        return;
      }
      if (!mDrawValuesReady)
      {
        mXCenter = (getWidth() / 2);
        mYCenter = (getHeight() / 2);
        mCircleRadius = (Math.min(mXCenter, mYCenter) * mCircleRadiusMultiplier);
        if (!mIs24HourMode)
        {
          f1 = mCircleRadius;
          float f2 = mAmPmCircleRadiusMultiplier;
          double d1 = mYCenter;
          double d2 = f1 * f2;
          Double.isNaN(d2);
          Double.isNaN(d1);
          mYCenter = ((int)(d1 - d2 * 0.75D));
        }
        float f1 = mCircleRadius;
        mTextSize = (mTextSizeMultiplier * f1);
        if (mHasInnerCircle) {
          mInnerTextSize = (f1 * mInnerTextSizeMultiplier);
        }
        renderAnimations();
        mTextGridValuesDirty = true;
        mDrawValuesReady = true;
      }
      if (mTextGridValuesDirty)
      {
        calculateGridSizes(mCircleRadius * mNumbersRadiusMultiplier * mAnimationRadiusMultiplier, mXCenter, mYCenter, mTextSize, mTextGridHeights, mTextGridWidths);
        if (mHasInnerCircle) {
          calculateGridSizes(mCircleRadius * mInnerNumbersRadiusMultiplier * mAnimationRadiusMultiplier, mXCenter, mYCenter, mInnerTextSize, mInnerTextGridHeights, mInnerTextGridWidths);
        }
        mTextGridValuesDirty = false;
      }
      drawTexts(paramCanvas, mTextSize, mTypefaceLight, mTexts, mTextGridWidths, mTextGridHeights);
      if (mHasInnerCircle) {
        drawTexts(paramCanvas, mInnerTextSize, mTypefaceRegular, mInnerTexts, mInnerTextGridWidths, mInnerTextGridHeights);
      }
    }
  }
  
  public void setAnimationRadiusMultiplier(float paramFloat)
  {
    mAnimationRadiusMultiplier = paramFloat;
    mTextGridValuesDirty = true;
  }
  
  protected void setSelection(int paramInt)
  {
    selection = paramInt;
  }
  
  void setTheme(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getResources();
    int i;
    if (paramBoolean) {
      i = paramContext.getColor(R.color.mdtp_white);
    } else {
      i = paramContext.getColor(R.color.mdtp_numbers_text_color);
    }
    mPaint.setColor(i);
  }
}
