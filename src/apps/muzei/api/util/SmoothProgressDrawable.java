package apps.muzei.api.util;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import apps.muzei.api.internal.ViewUtil;

public class SmoothProgressDrawable
  extends Drawable
  implements Animatable
{
  private float bottom;
  private int mAlpha;
  private ColorStateList mColor;
  private int mDuration;
  private boolean mEnable = true;
  private boolean mFlat = false;
  private int mHeight;
  private Paint mPaint;
  private Path mPath;
  private boolean mRunning = false;
  private long mStartTimeMillis;
  private int mState;
  private PathEffect mStrokeSize;
  private final Runnable mUpdater = new DayFragment.1(this);
  private boolean mVisible = true;
  private int right;
  private int top;
  
  public SmoothProgressDrawable(int paramInt1, int paramInt2, int paramInt3, ColorStateList paramColorStateList, int paramInt4)
  {
    mHeight = paramInt1;
    right = paramInt2;
    top = paramInt3;
    mDuration = paramInt4;
    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(mHeight);
    mPaint.setStrokeCap(Paint.Cap.ROUND);
    mPaint.setStrokeJoin(Paint.Join.ROUND);
    mPath = new Path();
    mVisible = false;
    setColor(paramColorStateList);
    mVisible = true;
  }
  
  public SmoothProgressDrawable(int paramInt1, ColorStateList paramColorStateList, int paramInt2)
  {
    this(paramInt1, 0, 0, paramColorStateList, paramInt2);
  }
  
  private void draw()
  {
    bottom = Math.min(1.0F, (float)(SystemClock.uptimeMillis() - mStartTimeMillis) / mDuration);
    if (bottom == 1.0F) {
      mRunning = false;
    }
    if (isRunning()) {
      scheduleSelf(mUpdater, SystemClock.uptimeMillis() + 16L);
    }
    invalidateSelf();
  }
  
  private PathEffect getPathEffect()
  {
    if (mStrokeSize == null) {
      mStrokeSize = new DashPathEffect(new float[] { 1.04522054E9F, mHeight * 2 }, 0.0F);
    }
    return mStrokeSize;
  }
  
  private void setProgress()
  {
    mStartTimeMillis = SystemClock.uptimeMillis();
    bottom = 0.0F;
  }
  
  public void draw(Canvas paramCanvas)
  {
    int i = mHeight;
    SmoothProgressDrawable localSmoothProgressDrawable = this;
    if (i == 0) {
      return;
    }
    Object localObject2 = localSmoothProgressDrawable.getBounds();
    float f1 = bottom - mHeight / 2;
    boolean bool = localSmoothProgressDrawable.isRunning();
    Object localObject1 = null;
    if (!bool)
    {
      mPath.reset();
      mPath.moveTo(left + right, f1);
      mPath.lineTo(right - top, f1);
      localObject2 = mPaint;
      if (!mEnable) {
        localObject1 = localSmoothProgressDrawable.getPathEffect();
      }
      ((Paint)localObject2).setPathEffect((PathEffect)localObject1);
      mPaint.setColor(mAlpha);
      paramCanvas.drawPath(mPath, mPaint);
      return;
    }
    i = right;
    int j = left;
    int k = top;
    int m = right;
    float f3 = (i + j - k + m) / 2.0F;
    float f4 = bottom;
    float f2 = (1.0F - f4) * f3 + (j + m) * f4;
    f3 = (1.0F - f4) * f3 + (i + k) * f4;
    mPaint.setPathEffect(null);
    if (bottom < 1.0F)
    {
      localObject1 = mPaint;
      i = mState;
      ((Paint)localObject1).setColor(i);
      mPath.reset();
      mPath.moveTo(left + right, f1);
      mPath.lineTo(f2, f1);
      mPath.moveTo(right - top, f1);
      mPath.lineTo(f3, f1);
      paramCanvas.drawPath(mPath, mPaint);
    }
    localSmoothProgressDrawable = this;
    mPaint.setColor(mAlpha);
    mPath.reset();
    mPath.moveTo(f2, f1);
    mPath.lineTo(f3, f1);
    paramCanvas.drawPath(mPath, mPaint);
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
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    mEnable = ViewUtil.hasState(paramArrayOfInt, 16842910);
    int j = mColor.getColorForState(paramArrayOfInt, mAlpha);
    if (mAlpha != j)
    {
      if ((!mFlat) && (mVisible) && (mEnable) && (mDuration > 0))
      {
        int i;
        if (isRunning()) {
          i = mState;
        } else {
          i = mAlpha;
        }
        mState = i;
        mAlpha = j;
        start();
      }
      else
      {
        mState = j;
        mAlpha = j;
      }
      return true;
    }
    if (!isRunning()) {
      mState = j;
    }
    return false;
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
  
  public void setColor(int paramInt)
  {
    mDuration = paramInt;
  }
  
  public void setColor(ColorStateList paramColorStateList)
  {
    mColor = paramColorStateList;
    onStateChange(getState());
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mPaint.setColorFilter(paramColorFilter);
  }
  
  public void setDividerHeight(int paramInt)
  {
    if (mHeight != paramInt)
    {
      mHeight = paramInt;
      mPaint.setStrokeWidth(mHeight);
      invalidateSelf();
    }
  }
  
  public void start()
  {
    setProgress();
    scheduleSelf(mUpdater, SystemClock.uptimeMillis() + 16L);
    invalidateSelf();
  }
  
  public void stop()
  {
    mRunning = false;
    unscheduleSelf(mUpdater);
    invalidateSelf();
  }
}
