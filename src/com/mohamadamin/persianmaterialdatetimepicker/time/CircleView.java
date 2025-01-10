package com.mohamadamin.persianmaterialdatetimepicker.time;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import com.mohamadamin.persianmaterialdatetimepicker.R.color;
import com.mohamadamin.persianmaterialdatetimepicker.R.string;

public class CircleView
  extends View
{
  private float mAmPmCircleRadiusMultiplier;
  private int mCircleColor;
  private int mCircleRadius;
  private float mCircleRadiusMultiplier;
  private int mDotColor;
  private boolean mDrawValuesReady;
  private boolean mIs24HourMode;
  private boolean mIsInitialized;
  private final Paint mPaint = new Paint();
  private int mXCenter;
  private int mYCenter;
  
  public CircleView(Context paramContext)
  {
    super(paramContext);
    paramContext = paramContext.getResources();
    mCircleColor = paramContext.getColor(R.color.mdtp_circle_color);
    mDotColor = paramContext.getColor(R.color.mdtp_numbers_text_color);
    mPaint.setAntiAlias(true);
    mIsInitialized = false;
  }
  
  public void initialize(Context paramContext, boolean paramBoolean)
  {
    if (mIsInitialized)
    {
      Log.e("CircleView", "CircleView may only be initialized once.");
      return;
    }
    paramContext = paramContext.getResources();
    mIs24HourMode = paramBoolean;
    if (paramBoolean)
    {
      mCircleRadiusMultiplier = Float.parseFloat(paramContext.getString(R.string.mdtp_circle_radius_multiplier_24HourMode));
    }
    else
    {
      mCircleRadiusMultiplier = Float.parseFloat(paramContext.getString(R.string.mdtp_circle_radius_multiplier));
      mAmPmCircleRadiusMultiplier = Float.parseFloat(paramContext.getString(R.string.mdtp_ampm_circle_radius_multiplier));
    }
    mIsInitialized = true;
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if (getWidth() != 0)
    {
      if (!mIsInitialized) {
        return;
      }
      CircleView localCircleView = this;
      if (!mDrawValuesReady)
      {
        mXCenter = (getWidth() / 2);
        mYCenter = (getHeight() / 2);
        int i = mXCenter;
        localCircleView = this;
        mCircleRadius = ((int)(Math.min(i, mYCenter) * mCircleRadiusMultiplier));
        if (!mIs24HourMode)
        {
          i = mCircleRadius;
          i = (int)(i * mAmPmCircleRadiusMultiplier);
          double d1 = mYCenter;
          double d2 = i;
          Double.isNaN(d2);
          Double.isNaN(d1);
          mYCenter = ((int)(d1 - d2 * 0.75D));
        }
        localCircleView = this;
        mDrawValuesReady = true;
      }
      mPaint.setColor(mCircleColor);
      paramCanvas.drawCircle(mXCenter, mYCenter, mCircleRadius, mPaint);
      mPaint.setColor(mDotColor);
      paramCanvas.drawCircle(mXCenter, mYCenter, 4.0F, mPaint);
    }
  }
  
  void setTheme(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getResources();
    if (paramBoolean)
    {
      mCircleColor = paramContext.getColor(R.color.mdtp_circle_background_dark_theme);
      mDotColor = paramContext.getColor(R.color.mdtp_white);
      return;
    }
    mCircleColor = paramContext.getColor(R.color.mdtp_circle_color);
    mDotColor = paramContext.getColor(R.color.mdtp_numbers_text_color);
  }
}
