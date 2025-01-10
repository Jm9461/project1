package com.mohamadamin.persianmaterialdatetimepicker.time;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import com.mohamadamin.persianmaterialdatetimepicker.R.color;
import com.mohamadamin.persianmaterialdatetimepicker.R.string;

public class RadialSelectorView
  extends View
{
  private float mAmPmCircleRadiusMultiplier;
  private float mAnimationRadiusMultiplier;
  private int mCircleRadius;
  private float mCircleRadiusMultiplier;
  private boolean mDrawValuesReady;
  private boolean mForceDrawDot;
  private boolean mHasInnerCircle;
  private float mInnerNumbersRadiusMultiplier;
  private RadialTextsView.InvalidateUpdateListener mInvalidateUpdateListener;
  private boolean mIs24HourMode;
  private boolean mIsInitialized = false;
  private int mLineLength;
  private float mNumbersRadiusMultiplier;
  private float mOuterNumbersRadiusMultiplier;
  private final Paint mPaint = new Paint();
  private int mSelectionAlpha;
  private int mSelectionDegrees;
  private double mSelectionRadians;
  private int mSelectionRadius;
  private float mSelectionRadiusMultiplier;
  private float mTransitionEndRadiusMultiplier;
  private float mTransitionMidRadiusMultiplier;
  private int mXCenter;
  private int mYCenter;
  
  public RadialSelectorView(Context paramContext)
  {
    super(paramContext);
  }
  
  public int getDegreesFromCoords(float paramFloat1, float paramFloat2, boolean paramBoolean, Boolean[] paramArrayOfBoolean)
  {
    if (!mDrawValuesReady) {
      return -1;
    }
    int i = mYCenter;
    float f1 = i;
    float f2 = i;
    i = mXCenter;
    double d1 = Math.sqrt((paramFloat2 - f1) * (paramFloat2 - f2) + (paramFloat1 - i) * (paramFloat1 - i));
    boolean bool = mHasInnerCircle;
    int j = 1;
    if (bool)
    {
      if (paramBoolean)
      {
        d2 = (int)(mCircleRadius * mInnerNumbersRadiusMultiplier);
        Double.isNaN(d2);
        i = (int)Math.abs(d1 - d2);
        d2 = (int)(mCircleRadius * mOuterNumbersRadiusMultiplier);
        Double.isNaN(d2);
        if (i <= (int)Math.abs(d1 - d2)) {
          paramBoolean = true;
        } else {
          paramBoolean = false;
        }
        paramArrayOfBoolean[0] = Boolean.valueOf(paramBoolean);
      }
      else
      {
        int n = mCircleRadius;
        f2 = n;
        f1 = mInnerNumbersRadiusMultiplier;
        i = (int)(f2 * f1);
        k = mSelectionRadius;
        f2 = n;
        float f3 = mOuterNumbersRadiusMultiplier;
        int m = (int)(f2 * f3);
        n = (int)(n * ((f3 + f1) / 2.0F));
        if ((d1 >= i - k) && (d1 <= n))
        {
          paramArrayOfBoolean[0] = Boolean.valueOf(true);
        }
        else
        {
          if (d1 > m + k) {
            break label287;
          }
          if (d1 < n) {
            break label472;
          }
          paramArrayOfBoolean[0] = Boolean.valueOf(false);
        }
        break label333;
        label287:
        return -1;
      }
    }
    else if (!paramBoolean)
    {
      d2 = mLineLength;
      Double.isNaN(d2);
      if ((int)Math.abs(d1 - d2) > (int)(mCircleRadius * (1.0F - mNumbersRadiusMultiplier))) {
        return -1;
      }
    }
    label333:
    double d2 = Math.abs(paramFloat2 - mYCenter);
    Double.isNaN(d2);
    int k = (int)(180.0D * Math.asin(d2 / d1) / 3.141592653589793D);
    if (paramFloat1 > mXCenter) {
      i = 1;
    } else {
      i = 0;
    }
    if (paramFloat2 >= mYCenter) {
      j = 0;
    }
    if ((i != 0) && (j != 0)) {
      return 90 - k;
    }
    if ((i != 0) && (j == 0)) {
      return k + 90;
    }
    if ((i == 0) && (j == 0)) {
      return 270 - k;
    }
    if ((i == 0) && (j != 0))
    {
      return k + 270;
      label472:
      return -1;
    }
    return k;
  }
  
  public ObjectAnimator getDisappearAnimator()
  {
    if ((mIsInitialized) && (mDrawValuesReady))
    {
      ObjectAnimator localObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[] { PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", new Keyframe[] { Keyframe.ofFloat(0.0F, 1.0F), Keyframe.ofFloat(0.2F, mTransitionMidRadiusMultiplier), Keyframe.ofFloat(1.0F, mTransitionEndRadiusMultiplier) }), PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[] { Keyframe.ofFloat(0.0F, 1.0F), Keyframe.ofFloat(1.0F, 0.0F) }) }).setDuration('?');
      localObjectAnimator.addUpdateListener(mInvalidateUpdateListener);
      return localObjectAnimator;
    }
    Log.e("RadialSelectorView", "RadialSelectorView was not ready for animation.");
    return null;
  }
  
  public ObjectAnimator getReappearAnimator()
  {
    if ((mIsInitialized) && (mDrawValuesReady))
    {
      int i = (int)(500.0F * (1.0F + 0.25F));
      float f = 500.0F * 0.25F / i;
      ObjectAnimator localObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[] { PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", new Keyframe[] { Keyframe.ofFloat(0.0F, mTransitionEndRadiusMultiplier), Keyframe.ofFloat(f, mTransitionEndRadiusMultiplier), Keyframe.ofFloat(1.0F - (1.0F - f) * 0.2F, mTransitionMidRadiusMultiplier), Keyframe.ofFloat(1.0F, 1.0F) }), PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[] { Keyframe.ofFloat(0.0F, 0.0F), Keyframe.ofFloat(f, 0.0F), Keyframe.ofFloat(1.0F, 1.0F) }) }).setDuration(i);
      localObjectAnimator.addUpdateListener(mInvalidateUpdateListener);
      return localObjectAnimator;
    }
    Log.e("RadialSelectorView", "RadialSelectorView was not ready for animation.");
    return null;
  }
  
  public boolean hasOverlappingRendering()
  {
    return false;
  }
  
  public void initialize(Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt, boolean paramBoolean4)
  {
    if (mIsInitialized)
    {
      Log.e("RadialSelectorView", "This RadialSelectorView may only be initialized once.");
      return;
    }
    paramContext = paramContext.getResources();
    int i = paramContext.getColor(R.color.mdtp_accent_color);
    mPaint.setColor(i);
    mPaint.setAntiAlias(true);
    mSelectionAlpha = 255;
    mIs24HourMode = paramBoolean1;
    if (paramBoolean1)
    {
      mCircleRadiusMultiplier = Float.parseFloat(paramContext.getString(R.string.mdtp_circle_radius_multiplier_24HourMode));
    }
    else
    {
      mCircleRadiusMultiplier = Float.parseFloat(paramContext.getString(R.string.mdtp_circle_radius_multiplier));
      mAmPmCircleRadiusMultiplier = Float.parseFloat(paramContext.getString(R.string.mdtp_ampm_circle_radius_multiplier));
    }
    mHasInnerCircle = paramBoolean2;
    if (paramBoolean2)
    {
      mInnerNumbersRadiusMultiplier = Float.parseFloat(paramContext.getString(R.string.mdtp_numbers_radius_multiplier_inner));
      mOuterNumbersRadiusMultiplier = Float.parseFloat(paramContext.getString(R.string.mdtp_numbers_radius_multiplier_outer));
    }
    else
    {
      mNumbersRadiusMultiplier = Float.parseFloat(paramContext.getString(R.string.mdtp_numbers_radius_multiplier_normal));
    }
    mSelectionRadiusMultiplier = Float.parseFloat(paramContext.getString(R.string.mdtp_selection_radius_multiplier));
    mAnimationRadiusMultiplier = 1.0F;
    int j = -1;
    if (paramBoolean3) {
      i = -1;
    } else {
      i = 1;
    }
    mTransitionMidRadiusMultiplier = (i * 0.05F + 1.0F);
    i = j;
    if (paramBoolean3) {
      i = 1;
    }
    mTransitionEndRadiusMultiplier = (i * 0.3F + 1.0F);
    mInvalidateUpdateListener = new RadialTextsView.InvalidateUpdateListener(this, null);
    setSelection(paramInt, paramBoolean4, false);
    mIsInitialized = true;
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if (getWidth() != 0)
    {
      if (!mIsInitialized) {
        return;
      }
      boolean bool2 = mDrawValuesReady;
      boolean bool1 = true;
      if (!bool2)
      {
        mXCenter = (getWidth() / 2);
        mYCenter = (getHeight() / 2);
        mCircleRadius = ((int)(Math.min(mXCenter, mYCenter) * mCircleRadiusMultiplier));
        if (!mIs24HourMode)
        {
          j = (int)(mCircleRadius * mAmPmCircleRadiusMultiplier);
          d1 = mYCenter;
          d2 = j;
          Double.isNaN(d2);
          Double.isNaN(d1);
          mYCenter = ((int)(d1 - d2 * 0.75D));
        }
        mSelectionRadius = ((int)(mCircleRadius * mSelectionRadiusMultiplier));
        mDrawValuesReady = true;
      }
      mLineLength = ((int)(mCircleRadius * mNumbersRadiusMultiplier * mAnimationRadiusMultiplier));
      int j = mXCenter;
      double d1 = mLineLength;
      double d2 = Math.sin(mSelectionRadians);
      Double.isNaN(d1);
      j += (int)(d1 * d2);
      int k = mYCenter;
      d1 = mLineLength;
      d2 = Math.cos(mSelectionRadians);
      Double.isNaN(d1);
      k -= (int)(d1 * d2);
      mPaint.setAlpha(mSelectionAlpha);
      paramCanvas.drawCircle(j, k, mSelectionRadius, mPaint);
      bool2 = mForceDrawDot;
      if (mSelectionDegrees % 30 == 0) {
        bool1 = false;
      }
      int i;
      if ((bool1 | bool2))
      {
        mPaint.setAlpha(255);
        paramCanvas.drawCircle(j, k, mSelectionRadius * 2 / 7, mPaint);
        bool1 = j;
        j = k;
      }
      else
      {
        j = mLineLength - mSelectionRadius;
        i = mXCenter;
        d1 = j;
        d2 = Math.sin(mSelectionRadians);
        Double.isNaN(d1);
        i += (int)(d1 * d2);
        k = mYCenter;
        d1 = j;
        d2 = Math.cos(mSelectionRadians);
        Double.isNaN(d1);
        j = k - (int)(d1 * d2);
      }
      mPaint.setAlpha(255);
      mPaint.setStrokeWidth(1.0F);
      paramCanvas.drawLine(mXCenter, mYCenter, i, j, mPaint);
    }
  }
  
  public void setAnimationRadiusMultiplier(float paramFloat)
  {
    mAnimationRadiusMultiplier = paramFloat;
  }
  
  public void setSelection(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    mSelectionDegrees = paramInt;
    double d = paramInt;
    Double.isNaN(d);
    mSelectionRadians = (d * 3.141592653589793D / 180.0D);
    mForceDrawDot = paramBoolean2;
    if (mHasInnerCircle)
    {
      if (paramBoolean1)
      {
        mNumbersRadiusMultiplier = mInnerNumbersRadiusMultiplier;
        return;
      }
      mNumbersRadiusMultiplier = mOuterNumbersRadiusMultiplier;
    }
  }
  
  void setTheme(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getResources();
    int i;
    if (paramBoolean)
    {
      i = paramContext.getColor(R.color.mdtp_red);
      mSelectionAlpha = 255;
    }
    else
    {
      i = paramContext.getColor(R.color.mdtp_accent_color);
      mSelectionAlpha = 255;
    }
    mPaint.setColor(i);
  }
}
