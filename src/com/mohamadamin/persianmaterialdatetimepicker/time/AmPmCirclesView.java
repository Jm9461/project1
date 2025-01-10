package com.mohamadamin.persianmaterialdatetimepicker.time;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import com.mohamadamin.persianmaterialdatetimepicker.R.color;
import com.mohamadamin.persianmaterialdatetimepicker.R.string;

public class AmPmCirclesView
  extends View
{
  private int mAmOrPm;
  private int mAmOrPmPressed;
  private int mAmPmCircleRadius;
  private float mAmPmCircleRadiusMultiplier;
  private int mAmPmDisabledTextColor;
  private int mAmPmTextColor;
  private int mAmPmYCenter;
  private String mAmText;
  private int mAmXCenter;
  private float mCircleRadiusMultiplier;
  private boolean mDrawValuesReady;
  private boolean mIsInitialized = false;
  private final Paint mPaint = new Paint();
  private String mPmText;
  private int mPmXCenter;
  private int mSelectedAlpha;
  private int mSelectedColor;
  private int mTouchedColor;
  private int mUnselectedColor;
  
  public AmPmCirclesView(Context paramContext)
  {
    super(paramContext);
  }
  
  public int getIsTouchingAmOrPm(float paramFloat1, float paramFloat2)
  {
    if (!mDrawValuesReady) {
      return -1;
    }
    int i = mAmPmYCenter;
    i = (int)((paramFloat2 - i) * (paramFloat2 - i));
    int j = mAmXCenter;
    if ((int)Math.sqrt((paramFloat1 - j) * (paramFloat1 - j) + i) <= mAmPmCircleRadius) {
      return 0;
    }
    j = mPmXCenter;
    if ((int)Math.sqrt((paramFloat1 - j) * (paramFloat1 - j) + i) <= mAmPmCircleRadius) {
      return 1;
    }
    return -1;
  }
  
  public void initialize(Context paramContext, int paramInt)
  {
    if (mIsInitialized)
    {
      Log.e("AmPmCirclesView", "AmPmCirclesView may only be initialized once.");
      return;
    }
    paramContext = paramContext.getResources();
    mUnselectedColor = paramContext.getColor(R.color.mdtp_white);
    mSelectedColor = paramContext.getColor(R.color.mdtp_accent_color);
    mTouchedColor = paramContext.getColor(R.color.mdtp_accent_color_dark);
    mAmPmTextColor = paramContext.getColor(R.color.mdtp_ampm_text_color);
    mAmPmDisabledTextColor = paramContext.getColor(R.color.mdtp_white);
    mSelectedAlpha = 255;
    Typeface localTypeface = Typeface.create(paramContext.getString(R.string.mdtp_sans_serif), 0);
    mPaint.setTypeface(localTypeface);
    mPaint.setAntiAlias(true);
    mPaint.setTextAlign(Paint.Align.CENTER);
    mCircleRadiusMultiplier = Float.parseFloat(paramContext.getString(R.string.mdtp_circle_radius_multiplier));
    mAmPmCircleRadiusMultiplier = Float.parseFloat(paramContext.getString(R.string.mdtp_ampm_circle_radius_multiplier));
    mAmText = "?????????";
    mPmText = "????????";
    setAmOrPm(paramInt);
    mAmOrPmPressed = -1;
    mIsInitialized = true;
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if (getWidth() != 0)
    {
      if (!mIsInitialized) {
        return;
      }
      int j;
      if (!mDrawValuesReady)
      {
        i = getWidth() / 2;
        k = getHeight() / 2;
        j = (int)(Math.min(i, k) * mCircleRadiusMultiplier);
        mAmPmCircleRadius = ((int)(j * mAmPmCircleRadiusMultiplier));
        double d1 = k;
        m = mAmPmCircleRadius;
        double d2 = m;
        Double.isNaN(d2);
        Double.isNaN(d1);
        k = (int)(d1 + d2 * 0.75D);
        m = m * 3 / 4;
        mPaint.setTextSize(m);
        m = mAmPmCircleRadius;
        mAmPmYCenter = (k - m / 2 + j);
        mAmXCenter = (i - j + m);
        mPmXCenter = (i + j - m);
        mDrawValuesReady = true;
      }
      int i2 = mUnselectedColor;
      int i3 = 255;
      int i4 = mAmPmTextColor;
      int m = mUnselectedColor;
      int k = 255;
      int n = mAmPmTextColor;
      int i5 = mAmOrPm;
      int i1;
      if (i5 == 0)
      {
        i = mSelectedColor;
        j = mSelectedAlpha;
        i1 = mAmPmDisabledTextColor;
      }
      else
      {
        i1 = i4;
        i = i2;
        j = i3;
        if (i5 == 1)
        {
          m = mSelectedColor;
          k = mSelectedAlpha;
          n = mAmPmDisabledTextColor;
          j = i3;
          i = i2;
          i1 = i4;
        }
      }
      i4 = mAmOrPmPressed;
      if (i4 == 0)
      {
        i2 = mTouchedColor;
        i3 = mSelectedAlpha;
      }
      else
      {
        i2 = i;
        i3 = j;
        if (i4 == 1)
        {
          m = mTouchedColor;
          k = mSelectedAlpha;
          i3 = j;
          i2 = i;
        }
      }
      mPaint.setColor(i2);
      mPaint.setAlpha(i3);
      paramCanvas.drawCircle(mAmXCenter, mAmPmYCenter, mAmPmCircleRadius, mPaint);
      mPaint.setColor(m);
      mPaint.setAlpha(k);
      paramCanvas.drawCircle(mPmXCenter, mAmPmYCenter, mAmPmCircleRadius, mPaint);
      mPaint.setColor(i1);
      int i = mAmPmYCenter - (int)(mPaint.descent() + mPaint.ascent()) / 2;
      paramCanvas.drawText(mAmText, mAmXCenter, i, mPaint);
      mPaint.setColor(n);
      paramCanvas.drawText(mPmText, mPmXCenter, i, mPaint);
    }
  }
  
  public void setAmOrPm(int paramInt)
  {
    mAmOrPm = paramInt;
  }
  
  public void setAmOrPmPressed(int paramInt)
  {
    mAmOrPmPressed = paramInt;
  }
  
  void setTheme(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getResources();
    if (paramBoolean)
    {
      mUnselectedColor = paramContext.getColor(R.color.mdtp_circle_background_dark_theme);
      mSelectedColor = paramContext.getColor(R.color.mdtp_red);
      mAmPmTextColor = paramContext.getColor(R.color.mdtp_white);
      mSelectedAlpha = 255;
      return;
    }
    mUnselectedColor = paramContext.getColor(R.color.mdtp_white);
    mSelectedColor = paramContext.getColor(R.color.mdtp_accent_color);
    mAmPmTextColor = paramContext.getColor(R.color.mdtp_ampm_text_color);
    mSelectedAlpha = 255;
  }
}
