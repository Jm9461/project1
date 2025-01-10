package com.shawnlin.numberpicker;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public class Scroller
{
  private static float ALPHA;
  private static float DECELERATION_RATE = (float)(Math.log(0.75D) / Math.log(0.9D));
  private static float END_TENSION;
  private static final float[] SPLINE;
  private static float START_TENSION;
  private static float sViscousFluidNormalize = 1.0F / viscousFluid(1.0F);
  private static float sViscousFluidScale;
  private int a;
  private int b;
  private int c;
  private int d;
  private int e;
  private float f;
  private int g;
  private int h;
  private int i;
  private int j;
  private int k;
  private float mDeceleration;
  private int mDuration;
  private float mDurationReciprocal;
  private boolean mFinished = true;
  private boolean mFlywheel;
  private Interpolator mInterpolator;
  private int mMode;
  private final float mPpi;
  private long mStartTime;
  private float mVelocity;
  private float r;
  
  static
  {
    ALPHA = 800.0F;
    START_TENSION = 0.4F;
    END_TENSION = 1.0F - START_TENSION;
    SPLINE = new float[101];
    float f2 = 0.0F;
    int m = 0;
    if (m <= 100)
    {
      float f4 = m / 100.0F;
      float f1 = 1.0F;
      for (;;)
      {
        float f3 = (f1 - f2) / 2.0F + f2;
        float f5 = 3.0F * f3 * (1.0F - f3);
        float f6 = ((1.0F - f3) * START_TENSION + END_TENSION * f3) * f5 + f3 * f3 * f3;
        if (Math.abs(f6 - f4) < 1.0E-5D)
        {
          SPLINE[m] = (f3 * f3 * f3 + f5);
          m += 1;
          break;
        }
        if (f6 > f4) {
          f1 = f3;
        } else {
          f2 = f3;
        }
      }
    }
    SPLINE[100] = 1.0F;
    sViscousFluidScale = 8.0F;
    sViscousFluidNormalize = 1.0F;
  }
  
  public Scroller(Context paramContext, Interpolator paramInterpolator)
  {
    this(paramContext, paramInterpolator, true);
  }
  
  public Scroller(Context paramContext, Interpolator paramInterpolator, boolean paramBoolean)
  {
    mInterpolator = paramInterpolator;
    mPpi = (getResourcesgetDisplayMetricsdensity * 160.0F);
    mDeceleration = computeDeceleration(ViewConfiguration.getScrollFriction());
    mFlywheel = paramBoolean;
  }
  
  private float computeDeceleration(float paramFloat)
  {
    return mPpi * 386.0878F * paramFloat;
  }
  
  static float viscousFluid(float paramFloat)
  {
    paramFloat *= sViscousFluidScale;
    if (paramFloat < 1.0F) {
      paramFloat -= 1.0F - (float)Math.exp(-paramFloat);
    } else {
      paramFloat = 0.36787945F + (1.0F - 0.36787945F) * (1.0F - (float)Math.exp(1.0F - paramFloat));
    }
    return paramFloat * sViscousFluidNormalize;
  }
  
  public final int computeScrollOffset()
  {
    return k;
  }
  
  public boolean draw()
  {
    if (mFinished) {
      return false;
    }
    int m = (int)(AnimationUtils.currentAnimationTimeMillis() - mStartTime);
    int n = mDuration;
    if (m < n)
    {
      int i1 = mMode;
      float f1;
      Object localObject;
      if (i1 != 0)
      {
        if (i1 == 1)
        {
          f1 = m / n;
          m = (int)(f1 * 100.0F);
          float f2 = m / 100.0F;
          float f3 = (m + 1) / 100.0F;
          localObject = SPLINE;
          float f4 = localObject[m];
          float f5 = localObject[(m + 1)];
          f1 = (f1 - f2) / (f3 - f2) * (f5 - f4) + f4;
          m = a;
          j = (m + Math.round((d - m) * f1));
          j = Math.min(j, g);
          j = Math.max(j, b);
          m = c;
          k = (m + Math.round((e - m) * f1));
          k = Math.min(k, i);
          k = Math.max(k, h);
          if ((j == d) && (k == e)) {
            mFinished = true;
          }
        }
      }
      else
      {
        f1 = m * mDurationReciprocal;
        localObject = mInterpolator;
        if (localObject == null) {
          f1 = viscousFluid(f1);
        } else {
          f1 = ((TimeInterpolator)localObject).getInterpolation(f1);
        }
        j = (a + Math.round(f * f1));
        k = (c + Math.round(r * f1));
      }
      return true;
    }
    j = d;
    k = e;
    mFinished = true;
    return true;
  }
  
  public void fling$69c647f5(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
  {
    int n = paramInt3;
    int i1 = paramInt4;
    int i2 = n;
    int m = i1;
    float f1;
    if (mFlywheel)
    {
      i2 = n;
      m = i1;
      if (!mFinished)
      {
        f1 = getCurrVelocity();
        float f4 = d - a;
        f2 = e - c;
        f3 = (float)Math.sqrt(f4 * f4 + f2 * f2);
        f4 /= f3;
        f2 /= f3;
        f3 = f4 * f1;
        f1 = f2 * f1;
        i2 = n;
        m = i1;
        if (Math.signum(paramInt3) == Math.signum(f3))
        {
          i2 = n;
          m = i1;
          if (Math.signum(paramInt4) == Math.signum(f1))
          {
            i2 = (int)(paramInt3 + f3);
            m = (int)(paramInt4 + f1);
          }
        }
      }
    }
    mMode = 1;
    mFinished = false;
    float f3 = (float)Math.sqrt(i2 * i2 + m * m);
    mVelocity = f3;
    double d1 = Math.log(START_TENSION * f3 / ALPHA);
    double d2 = DECELERATION_RATE;
    Double.isNaN(d2);
    mDuration = ((int)(Math.exp(d1 / (d2 - 1.0D)) * 1000.0D));
    mStartTime = AnimationUtils.currentAnimationTimeMillis();
    a = paramInt1;
    c = paramInt2;
    float f2 = 1.0F;
    if (f3 == 0.0F) {
      f1 = 1.0F;
    } else {
      f1 = i2 / f3;
    }
    if (f3 != 0.0F) {
      f2 = m / f3;
    }
    d2 = ALPHA;
    f3 = DECELERATION_RATE;
    double d3 = f3;
    double d4 = f3;
    Double.isNaN(d4);
    Double.isNaN(d3);
    d1 = Math.exp(d3 / (d4 - 1.0D) * d1);
    Double.isNaN(d2);
    paramInt3 = (int)(d2 * d1);
    b = paramInt5;
    g = paramInt6;
    h = paramInt7;
    i = paramInt8;
    d = (Math.round(paramInt3 * f1) + paramInt1);
    d = Math.min(d, g);
    d = Math.max(d, b);
    e = (Math.round(paramInt3 * f2) + paramInt2);
    e = Math.min(e, i);
    e = Math.max(e, h);
  }
  
  public final void forceFinished(boolean paramBoolean)
  {
    mFinished = paramBoolean;
  }
  
  public float getCurrVelocity()
  {
    return mVelocity - mDeceleration * timePassed() / 2000.0F;
  }
  
  public final int getCurrY()
  {
    return j;
  }
  
  public final int getDuration()
  {
    return d;
  }
  
  public final int getFinalY()
  {
    return e;
  }
  
  public final int getStartY()
  {
    return a;
  }
  
  public final boolean isFinished()
  {
    return mFinished;
  }
  
  public final int startScroll()
  {
    return c;
  }
  
  public void startScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    mMode = 0;
    mFinished = false;
    mDuration = paramInt5;
    mStartTime = AnimationUtils.currentAnimationTimeMillis();
    a = paramInt1;
    c = paramInt2;
    d = (paramInt1 + paramInt3);
    e = (paramInt2 + paramInt4);
    f = paramInt3;
    r = paramInt4;
    mDurationReciprocal = (1.0F / mDuration);
  }
  
  public int timePassed()
  {
    return (int)(AnimationUtils.currentAnimationTimeMillis() - mStartTime);
  }
}
