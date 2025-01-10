package apps.muzei.api.util;

import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Interpolator;
import apps.muzei.api.internal.ContextCompat;

public class RippleDrawable
  extends Drawable
  implements Animatable, View.OnTouchListener
{
  private static final float[] color = { 0.0F, 0.99F, 1.0F };
  private float a;
  private int b;
  private float bottom;
  private int c;
  private int d;
  private int f;
  private Interpolator g;
  private long h;
  private long l;
  private int m;
  private int mAlpha = 255;
  private Path mBackground;
  private Drawable mBackgroundDrawable;
  private int mColor;
  private Paint mFillPaint;
  private Event mMask;
  private Matrix mMatrix;
  private Paint mPaint;
  private RectF mRect;
  private boolean mRunning = false;
  private RadialGradient mScaleFactor;
  private RadialGradient mState;
  private final Runnable mUpdater = new MonthByWeekFragment.2(this);
  private int n;
  private int p;
  private Interpolator r;
  private PointF this$0;
  private float x;
  private int y = 0;
  
  private RippleDrawable(Drawable paramDrawable, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, Interpolator paramInterpolator1, Interpolator paramInterpolator2, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, int paramInt15, int paramInt16, int paramInt17)
  {
    setBackgroundDrawable(paramDrawable);
    d = paramInt1;
    mColor = paramInt2;
    c = paramInt3;
    setVisible(paramInt4);
    p = paramInt5;
    m = paramInt6;
    f = paramInt7;
    b = paramInt8;
    if ((c == 0) && (m <= 0)) {
      c = -1;
    }
    r = paramInterpolator1;
    g = paramInterpolator2;
    setMask(paramInt9, paramInt10, paramInt11, paramInt12, paramInt13, paramInt14, paramInt15, paramInt16, paramInt17);
    mPaint = new Paint(1);
    mPaint.setStyle(Paint.Style.FILL);
    mFillPaint = new Paint(1);
    mFillPaint.setStyle(Paint.Style.FILL);
    mBackground = new Path();
    mRect = new RectF();
    this$0 = new PointF();
    mMatrix = new Matrix();
    paramInt1 = b;
    paramDrawable = color;
    paramInterpolator1 = Shader.TileMode.CLAMP;
    mScaleFactor = new RadialGradient(0.0F, 0.0F, 16.0F, new int[] { paramInt1, paramInt1, 0 }, paramDrawable, paramInterpolator1);
    if (c == 1)
    {
      paramInt1 = ContextCompat.getColor(b, 0.0F);
      paramInt2 = b;
      paramDrawable = color;
      paramInterpolator1 = Shader.TileMode.CLAMP;
      mState = new RadialGradient(0.0F, 0.0F, 16.0F, new int[] { 0, paramInt1, paramInt2 }, paramDrawable, paramInterpolator1);
    }
  }
  
  private void draw()
  {
    int j = y;
    int i = 4;
    float f1;
    float f2;
    PointF localPointF;
    if (j != 4)
    {
      f1 = Math.min(1.0F, (float)(SystemClock.uptimeMillis() - h) / d);
      x = (r.getInterpolation(f1) * Color.alpha(mColor) / 255.0F);
      f2 = Math.min(1.0F, (float)(SystemClock.uptimeMillis() - h) / f);
      a = r.getInterpolation(f2);
      localPointF = this$0;
      draw(x, y, m * r.getInterpolation(f2));
      if ((f1 == 1.0F) && (f2 == 1.0F))
      {
        h = SystemClock.uptimeMillis();
        if (y == 1) {
          i = 2;
        }
        update(i);
      }
    }
    else
    {
      f1 = Math.min(1.0F, (float)(SystemClock.uptimeMillis() - h) / d);
      x = ((1.0F - g.getInterpolation(f1)) * Color.alpha(mColor) / 255.0F);
      f2 = Math.min(1.0F, (float)(SystemClock.uptimeMillis() - h) / f);
      a = (1.0F - g.getInterpolation(f2));
      localPointF = this$0;
      draw(x, y, m * (g.getInterpolation(f2) * 0.5F + 1.0F));
      if ((f1 == 1.0F) && (f2 == 1.0F)) {
        update(0);
      }
    }
    if (isRunning()) {
      scheduleSelf(mUpdater, SystemClock.uptimeMillis() + 16L);
    }
    invalidateSelf();
  }
  
  private boolean draw(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    Object localObject = this$0;
    if ((x == paramFloat1) && (y == paramFloat2) && (bottom == paramFloat3)) {
      return false;
    }
    this$0.set(paramFloat1, paramFloat2);
    bottom = paramFloat3;
    paramFloat3 = bottom / 16.0F;
    mMatrix.reset();
    mMatrix.postTranslate(paramFloat1, paramFloat2);
    mMatrix.postScale(paramFloat3, paramFloat3, paramFloat1, paramFloat2);
    mScaleFactor.setLocalMatrix(mMatrix);
    localObject = mState;
    if (localObject != null) {
      ((Shader)localObject).setLocalMatrix(mMatrix);
    }
    return true;
  }
  
  private void drawWave(Canvas paramCanvas)
  {
    int i = y;
    if (i != 0)
    {
      if (i == 4)
      {
        if (bottom == 0.0F)
        {
          mPaint.setColor(b);
          paramCanvas.drawPath(mBackground, mPaint);
          return;
        }
        mFillPaint.setShader(mState);
        paramCanvas.drawPath(mBackground, mFillPaint);
        return;
      }
      if (bottom > 0.0F)
      {
        mFillPaint.setShader(mScaleFactor);
        paramCanvas.drawPath(mBackground, mFillPaint);
      }
    }
  }
  
  private void onDraw(Canvas paramCanvas)
  {
    if (y != 0)
    {
      if (x > 0.0F)
      {
        mPaint.setColor(mColor);
        mPaint.setAlpha(Math.round(mAlpha * x));
        paramCanvas.drawPath(mBackground, mPaint);
      }
      if (bottom > 0.0F)
      {
        float f1 = a;
        if (f1 > 0.0F)
        {
          mFillPaint.setAlpha(Math.round(mAlpha * f1));
          mFillPaint.setShader(mScaleFactor);
          paramCanvas.drawPath(mBackground, mFillPaint);
        }
      }
    }
  }
  
  private void resetAnimation()
  {
    h = SystemClock.uptimeMillis();
  }
  
  private int update(float paramFloat1, float paramFloat2)
  {
    float f1;
    if (paramFloat1 < mRect.centerX()) {
      f1 = mRect.right;
    } else {
      f1 = mRect.left;
    }
    float f2;
    if (paramFloat2 < mRect.centerY()) {
      f2 = mRect.bottom;
    } else {
      f2 = mRect.top;
    }
    return (int)Math.round(Math.sqrt(Math.pow(f1 - paramFloat1, 2.0D) + Math.pow(f2 - paramFloat2, 2.0D)));
  }
  
  private void update()
  {
    float f1 = Math.min(1.0F, (float)(SystemClock.uptimeMillis() - h) / f);
    PointF localPointF;
    if (y != 4)
    {
      localPointF = this$0;
      draw(x, y, m * r.getInterpolation(f1));
      if (f1 == 1.0F)
      {
        h = SystemClock.uptimeMillis();
        if (y == 1)
        {
          update(2);
        }
        else
        {
          localPointF = this$0;
          draw(x, y, 0.0F);
          update(4);
        }
      }
    }
    else
    {
      localPointF = this$0;
      draw(x, y, m * g.getInterpolation(f1));
      if (f1 == 1.0F) {
        update(0);
      }
    }
    if (isRunning()) {
      scheduleSelf(mUpdater, SystemClock.uptimeMillis() + 16L);
    }
    invalidateSelf();
  }
  
  private void update(int paramInt)
  {
    int i = y;
    if (i != paramInt)
    {
      if ((i == 0) && (paramInt != 1)) {
        return;
      }
      y = paramInt;
      paramInt = y;
      if ((paramInt != 0) && (paramInt != 2))
      {
        start();
        return;
      }
      stop();
    }
  }
  
  public long d()
  {
    int i = n;
    if (i != 0) {
      if (i != 1)
      {
        if (i != 2) {
          return -1L;
        }
        i = y;
        if (i == 3) {
          return Math.max(d, f) * 2 - (SystemClock.uptimeMillis() - h);
        }
        if (i == 4) {
          return Math.max(d, f) - (SystemClock.uptimeMillis() - h);
        }
      }
      else if (y == 3)
      {
        return Math.max(d, f) - (SystemClock.uptimeMillis() - h);
      }
    }
    return -1L;
  }
  
  public void draw(Canvas paramCanvas)
  {
    Drawable localDrawable = mBackgroundDrawable;
    if (localDrawable != null) {
      localDrawable.draw(paramCanvas);
    }
    int i = c;
    if ((i != -1) && (i != 0))
    {
      if (i != 1) {
        return;
      }
      drawWave(paramCanvas);
      return;
    }
    onDraw(paramCanvas);
  }
  
  public Drawable getBackgroundDrawable()
  {
    return mBackgroundDrawable;
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public boolean isRunning()
  {
    int i = y;
    return (i != 0) && (i != 2) && (mRunning);
  }
  
  public boolean isStateful()
  {
    Drawable localDrawable = mBackgroundDrawable;
    return (localDrawable != null) && (localDrawable.isStateful());
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    Object localObject = mBackgroundDrawable;
    if (localObject != null) {
      ((Drawable)localObject).setBounds(paramRect);
    }
    localObject = mRect;
    int i = left;
    Event localEvent = mMask;
    ((RectF)localObject).set(i + left, top + top, right - right, bottom - bottom);
    mBackground.reset();
    paramRect = mMask;
    i = type;
    if (i != 0)
    {
      if (i != 1) {
        return;
      }
      mBackground.addOval(mRect, Path.Direction.CW);
      return;
    }
    mBackground.addRoundRect(mRect, cornerRadius, Path.Direction.CW);
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    Drawable localDrawable = mBackgroundDrawable;
    return (localDrawable != null) && (localDrawable.setState(paramArrayOfInt));
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    if (i != 0)
    {
      if (i != 1)
      {
        if (i == 2) {
          break label145;
        }
        if (i != 3) {
          return true;
        }
      }
      else if ((l > 0L) && (y == 0))
      {
        i = c;
        if ((i == 1) || (i == -1)) {
          m = update(paramMotionEvent.getX(), paramMotionEvent.getY());
        }
        update(1);
      }
      l = 0L;
      i = y;
      if (i == 0) {
        break label280;
      }
      if (i == 2)
      {
        i = c;
        if ((i == 1) || (i == -1))
        {
          paramView = this$0;
          draw(x, y, 0.0F);
        }
        update(4);
        return true;
      }
      update(3);
      return true;
    }
    label145:
    i = y;
    if ((i != 0) && (i != 4))
    {
      if ((c == 0) && (draw(paramMotionEvent.getX(), paramMotionEvent.getY(), bottom)))
      {
        invalidateSelf();
        return true;
      }
    }
    else
    {
      long l1 = SystemClock.uptimeMillis();
      if (l == 0L) {
        l = l1;
      }
      draw(paramMotionEvent.getX(), paramMotionEvent.getY(), 0.0F);
      if (l <= l1 - p)
      {
        i = c;
        if ((i == 1) || (i == -1)) {
          m = update(paramMotionEvent.getX(), paramMotionEvent.getY());
        }
        update(1);
      }
    }
    label280:
    return true;
  }
  
  public void scheduleSelf(Runnable paramRunnable, long paramLong)
  {
    mRunning = true;
    super.scheduleSelf(paramRunnable, paramLong);
  }
  
  public void setAlpha(int paramInt)
  {
    mAlpha = paramInt;
    Drawable localDrawable = mBackgroundDrawable;
    if (localDrawable != null) {
      localDrawable.setAlpha(paramInt);
    }
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    mBackgroundDrawable = paramDrawable;
    paramDrawable = mBackgroundDrawable;
    if (paramDrawable != null) {
      paramDrawable.setBounds(getBounds());
    }
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    Drawable localDrawable = mBackgroundDrawable;
    if (localDrawable != null) {
      localDrawable.setColorFilter(paramColorFilter);
    }
  }
  
  public void setMask(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9)
  {
    mMask = new Event(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9);
  }
  
  public void setVisible()
  {
    update(0);
  }
  
  public void setVisible(int paramInt)
  {
    n = paramInt;
  }
  
  public void start()
  {
    if (isRunning()) {
      return;
    }
    resetAnimation();
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
