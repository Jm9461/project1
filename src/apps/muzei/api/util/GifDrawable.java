package apps.muzei.api.util;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

public class GifDrawable
  extends Drawable
  implements Animatable
{
  public static int bottom = 1;
  public static int y = 0;
  private Interpolator a;
  private ColorStateList mBorderTint;
  private boolean mClockwise = true;
  private int mCurrentBorderTintColor;
  private int mId;
  private Paint mPaint;
  private Path mPath;
  private boolean mRunning = false;
  private long mStartTime;
  private int mState;
  private final Runnable mUpdater = new EventInfoFragment.1(this);
  private float mX;
  private int state;
  
  public GifDrawable(int paramInt1, int paramInt2, ColorStateList paramColorStateList, int paramInt3, Interpolator paramInterpolator, boolean paramBoolean)
  {
    mId = paramInt2;
    mState = paramInt3;
    state = paramInt1;
    a = paramInterpolator;
    if (a == null) {
      a = new DecelerateInterpolator();
    }
    mClockwise = paramBoolean;
    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mPaint.setStyle(Paint.Style.FILL);
    mPath = new Path();
    setColor(paramColorStateList);
  }
  
  private void startAnimation()
  {
    mStartTime = SystemClock.uptimeMillis();
    mX = 0.0F;
  }
  
  private void update()
  {
    mX = Math.min(1.0F, (float)(SystemClock.uptimeMillis() - mStartTime) / mState);
    if (mX == 1.0F) {
      mRunning = false;
    }
    if (isRunning()) {
      scheduleSelf(mUpdater, SystemClock.uptimeMillis() + 16L);
    }
    invalidateSelf();
  }
  
  public void draw(Canvas paramCanvas)
  {
    int i = paramCanvas.save();
    Rect localRect = getBounds();
    if (!isRunning())
    {
      if (state == bottom) {
        paramCanvas.rotate(180.0F, localRect.exactCenterX(), localRect.exactCenterY());
      }
    }
    else
    {
      float f = a.getInterpolation(mX);
      if (mClockwise)
      {
        if (state == bottom) {
          f = 180.0F * f;
        } else {
          f = 180.0F * (1.0F + f);
        }
      }
      else if (state == bottom) {
        f *= -180.0F;
      } else {
        f = (1.0F + f) * -180.0F;
      }
      paramCanvas.rotate(f, localRect.exactCenterX(), localRect.exactCenterY());
    }
    mPaint.setColor(mCurrentBorderTintColor);
    paramCanvas.drawPath(mPath, mPaint);
    paramCanvas.restoreToCount(i);
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public boolean isRunning()
  {
    return mRunning;
  }
  
  public boolean isStateful()
  {
    return true;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    float f1 = paramRect.exactCenterX();
    float f2 = paramRect.exactCenterY();
    mPath.reset();
    mPath.moveTo(f1, mId / 2.0F + f2);
    paramRect = mPath;
    int i = mId;
    paramRect.lineTo(f1 - i, f2 - i / 2.0F);
    paramRect = mPath;
    i = mId;
    paramRect.lineTo(i + f1, f2 - i / 2.0F);
    mPath.close();
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    int i = mBorderTint.getColorForState(paramArrayOfInt, mCurrentBorderTintColor);
    if (mCurrentBorderTintColor != i)
    {
      mCurrentBorderTintColor = i;
      return true;
    }
    return false;
  }
  
  public void recycle(int paramInt)
  {
    mState = paramInt;
  }
  
  public void recycle(Interpolator paramInterpolator)
  {
    a = paramInterpolator;
  }
  
  public void scheduleSelf(Runnable paramRunnable, long paramLong)
  {
    mRunning = true;
    super.scheduleSelf(paramRunnable, paramLong);
  }
  
  public void setAlpha(int paramInt)
  {
    mPaint.setAlpha(paramInt);
  }
  
  public void setColor(ColorStateList paramColorStateList)
  {
    mBorderTint = paramColorStateList;
    onStateChange(getState());
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mPaint.setColorFilter(paramColorFilter);
  }
  
  public void start()
  {
    startAnimation();
    scheduleSelf(mUpdater, SystemClock.uptimeMillis() + 16L);
    invalidateSelf();
  }
  
  public void start(int paramInt)
  {
    if (mId != paramInt)
    {
      mId = paramInt;
      invalidateSelf();
    }
  }
  
  public void start(int paramInt, boolean paramBoolean)
  {
    if (state != paramInt)
    {
      state = paramInt;
      if ((paramBoolean) && (mState > 0))
      {
        start();
        return;
      }
      invalidateSelf();
    }
  }
  
  public void start(boolean paramBoolean)
  {
    mClockwise = paramBoolean;
  }
  
  public void stop()
  {
    mRunning = false;
    unscheduleSelf(mUpdater);
    invalidateSelf();
  }
}
