package apps.muzei.api.util;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import apps.muzei.api.internal.ViewUtil;

public class CircleDrawable
  extends Drawable
  implements Animatable
{
  private PointF a;
  private RadialGradient bottom;
  private int color;
  private float height;
  private Paint mPaint;
  private boolean mPressed;
  private int mRadius;
  private RectF mRect;
  private boolean mRunning;
  private int mState;
  private final Runnable mUpdater;
  private int mX;
  private long mY;
  private Matrix matrix;
  private float padding;
  private Paint paint;
  private int right;
  private RadialGradient shader;
  private Path this$0;
  private int verticalOffset;
  private float width;
  private int x;
  private int y;
  
  private boolean draw(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    Object localObject = a;
    if ((x == paramFloat1) && (y == paramFloat2) && (height == paramFloat3)) {
      return false;
    }
    a.set(paramFloat1, paramFloat2);
    height = paramFloat3;
    paramFloat3 = height / 16.0F;
    matrix.reset();
    matrix.postTranslate(paramFloat1, paramFloat2);
    matrix.postScale(paramFloat3, paramFloat3, paramFloat1, paramFloat2);
    bottom.setLocalMatrix(matrix);
    localObject = shader;
    if (localObject != null) {
      ((Shader)localObject).setLocalMatrix(matrix);
    }
    return true;
  }
  
  private void drawBackground(Canvas paramCanvas)
  {
    if (x != 0)
    {
      if (padding > 0.0F)
      {
        mPaint.setColor(color);
        mPaint.setAlpha(Math.round(right * padding));
        paramCanvas.drawPath(this$0, mPaint);
      }
      if (height > 0.0F)
      {
        float f = width;
        if (f > 0.0F)
        {
          paint.setAlpha(Math.round(right * f));
          paint.setShader(bottom);
          paramCanvas.drawPath(this$0, paint);
        }
      }
    }
  }
  
  private void setColor(Canvas paramCanvas)
  {
    int i = x;
    if (i != 0)
    {
      if (i == 4)
      {
        if (height == 0.0F)
        {
          mPaint.setColor(mState);
          paramCanvas.drawPath(this$0, mPaint);
          return;
        }
        paint.setShader(shader);
        paramCanvas.drawPath(this$0, paint);
        return;
      }
      if (height > 0.0F)
      {
        paint.setShader(bottom);
        paramCanvas.drawPath(this$0, paint);
      }
    }
  }
  
  private void start(int paramInt)
  {
    if (x != paramInt)
    {
      x = paramInt;
      paramInt = x;
      if (paramInt != 0)
      {
        if (paramInt != 2)
        {
          start();
          return;
        }
        stop();
        return;
      }
      stop();
    }
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
    mY = SystemClock.uptimeMillis();
  }
  
  public long draw()
  {
    int i = verticalOffset;
    if (i != 0) {
      if (i != 1)
      {
        if (i != 2) {
          return -1L;
        }
        i = x;
        if (i == 3) {
          return Math.max(mRadius, mX) * 2 - (SystemClock.uptimeMillis() - mY);
        }
        if (i == 4) {
          return Math.max(mRadius, mX) - (SystemClock.uptimeMillis() - mY);
        }
      }
      else if (x == 3)
      {
        return Math.max(mRadius, mX) - (SystemClock.uptimeMillis() - mY);
      }
    }
    return -1L;
  }
  
  public void draw(Canvas paramCanvas)
  {
    int i = y;
    if ((i != -1) && (i != 0))
    {
      if (i != 1) {
        return;
      }
      setColor(paramCanvas);
      return;
    }
    drawBackground(paramCanvas);
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
    mRect.set(left, top, right, bottom);
    this$0.reset();
    this$0.addRect(mRect, Path.Direction.CW);
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    boolean bool = ViewUtil.hasState(paramArrayOfInt, 16842919);
    if (mPressed != bool)
    {
      mPressed = bool;
      if (mPressed)
      {
        paramArrayOfInt = getBounds();
        i = x;
        if ((i != 0) && (i != 4))
        {
          if (y == 0) {
            draw(paramArrayOfInt.exactCenterX(), paramArrayOfInt.exactCenterY(), height);
          }
        }
        else
        {
          i = y;
          if ((i == 1) || (i == -1)) {
            update(paramArrayOfInt.exactCenterX(), paramArrayOfInt.exactCenterY());
          }
          draw(paramArrayOfInt.exactCenterX(), paramArrayOfInt.exactCenterY(), 0.0F);
          start(1);
        }
        return true;
      }
      int i = x;
      if (i != 0)
      {
        if (i == 2)
        {
          i = y;
          if ((i == 1) || (i == -1))
          {
            paramArrayOfInt = a;
            draw(x, y, 0.0F);
          }
          start(4);
          return true;
        }
        start(3);
        return true;
      }
    }
    else
    {
      return false;
    }
    return true;
  }
  
  public void scheduleSelf(Runnable paramRunnable, long paramLong)
  {
    mRunning = true;
    super.scheduleSelf(paramRunnable, paramLong);
  }
  
  public void setAlpha(int paramInt)
  {
    right = paramInt;
  }
  
  public void setColor()
  {
    start(0);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mPaint.setColorFilter(paramColorFilter);
    paint.setColorFilter(paramColorFilter);
  }
  
  public void start()
  {
    if (isRunning()) {
      return;
    }
    update();
    scheduleSelf(mUpdater, SystemClock.uptimeMillis() + 16L);
    invalidateSelf();
  }
  
  public void stop()
  {
    if (!isRunning()) {
      return;
    }
    mRunning = false;
    unscheduleSelf(mUpdater);
    invalidateSelf();
  }
}
