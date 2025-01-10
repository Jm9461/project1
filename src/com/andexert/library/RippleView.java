package com.andexert.library;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.RelativeLayout;

public class RippleView
  extends RelativeLayout
{
  private int HEIGHT;
  private int WIDTH;
  private boolean animationRunning = false;
  private Handler canvasHandler;
  private int durationEmpty = -1;
  private int frameRate = 10;
  private GestureDetector gestureDetector;
  private Boolean hasToZoom;
  private Boolean isCentered;
  private c onCompletionListener;
  private Bitmap originBitmap;
  private Paint paint;
  private float radiusMax = 0.0F;
  private int rippleAlpha = 90;
  private int rippleColor;
  private int rippleDuration = 400;
  private int ripplePadding;
  private Integer rippleType;
  private final Runnable runnable = new a();
  private ScaleAnimation scaleAnimation;
  private int timer = 0;
  private int timerEmpty = 0;
  private float x = -1.0F;
  private float y = -1.0F;
  private int zoomDuration;
  private float zoomScale;
  
  public RippleView(Context paramContext)
  {
    super(paramContext);
  }
  
  public RippleView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }
  
  public RippleView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }
  
  private void createAnimation(float paramFloat1, float paramFloat2)
  {
    if ((isEnabled()) && (!animationRunning))
    {
      if (hasToZoom.booleanValue()) {
        startAnimation(scaleAnimation);
      }
      radiusMax = Math.max(WIDTH, HEIGHT);
      if (rippleType.intValue() != 2) {
        radiusMax /= 2.0F;
      }
      radiusMax -= ripplePadding;
      if ((!isCentered.booleanValue()) && (rippleType.intValue() != 1))
      {
        x = paramFloat1;
        y = paramFloat2;
      }
      else
      {
        x = (getMeasuredWidth() / 2);
        y = (getMeasuredHeight() / 2);
      }
      animationRunning = true;
      if ((rippleType.intValue() == 1) && (originBitmap == null)) {
        originBitmap = getDrawingCache(true);
      }
      invalidate();
    }
  }
  
  private Bitmap getCircleBitmap(int paramInt)
  {
    Bitmap localBitmap = Bitmap.createBitmap(originBitmap.getWidth(), originBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    float f1 = x;
    int i = (int)(f1 - paramInt);
    float f2 = y;
    Rect localRect = new Rect(i, (int)(f2 - paramInt), (int)(f1 + paramInt), (int)(f2 + paramInt));
    localPaint.setAntiAlias(true);
    localCanvas.drawARGB(0, 0, 0, 0);
    localCanvas.drawCircle(x, y, paramInt, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(originBitmap, localRect, localRect, localPaint);
    return localBitmap;
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    if (isInEditMode()) {
      return;
    }
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RippleView);
    rippleColor = paramAttributeSet.getColor(R.styleable.RippleView_rv_color, getResources().getColor(R.color.rippelColor));
    rippleType = Integer.valueOf(paramAttributeSet.getInt(R.styleable.RippleView_rv_type, 0));
    hasToZoom = Boolean.valueOf(paramAttributeSet.getBoolean(R.styleable.RippleView_rv_zoom, false));
    isCentered = Boolean.valueOf(paramAttributeSet.getBoolean(R.styleable.RippleView_rv_centered, false));
    rippleDuration = paramAttributeSet.getInteger(R.styleable.RippleView_rv_rippleDuration, rippleDuration);
    frameRate = paramAttributeSet.getInteger(R.styleable.RippleView_rv_framerate, frameRate);
    rippleAlpha = paramAttributeSet.getInteger(R.styleable.RippleView_rv_alpha, rippleAlpha);
    ripplePadding = paramAttributeSet.getDimensionPixelSize(R.styleable.RippleView_rv_ripplePadding, 0);
    canvasHandler = new Handler();
    zoomScale = paramAttributeSet.getFloat(R.styleable.RippleView_rv_zoomScale, 1.03F);
    zoomDuration = paramAttributeSet.getInt(R.styleable.RippleView_rv_zoomDuration, 200);
    paramAttributeSet.recycle();
    paint = new Paint();
    paint.setAntiAlias(true);
    paint.setStyle(Paint.Style.FILL);
    paint.setColor(rippleColor);
    paint.setAlpha(rippleAlpha);
    setWillNotDraw(false);
    gestureDetector = new GestureDetector(paramContext, new b());
    setDrawingCacheEnabled(true);
    setClickable(true);
  }
  
  private void update(Boolean paramBoolean)
  {
    if ((getParent() instanceof AdapterView))
    {
      AdapterView localAdapterView = (AdapterView)getParent();
      int i = localAdapterView.getPositionForView(this);
      long l = localAdapterView.getItemIdAtPosition(i);
      if (paramBoolean.booleanValue())
      {
        if (localAdapterView.getOnItemLongClickListener() != null) {
          localAdapterView.getOnItemLongClickListener().onItemLongClick(localAdapterView, this, i, l);
        }
      }
      else if (localAdapterView.getOnItemClickListener() != null) {
        localAdapterView.getOnItemClickListener().onItemClick(localAdapterView, this, i, l);
      }
    }
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    if (animationRunning)
    {
      paramCanvas.save();
      int i = rippleDuration;
      int j = timer;
      int k = frameRate;
      if (i <= j * k)
      {
        animationRunning = false;
        timer = 0;
        durationEmpty = -1;
        timerEmpty = 0;
        if (Build.VERSION.SDK_INT < 23) {
          paramCanvas.restore();
        }
        invalidate();
        paramCanvas = onCompletionListener;
        if (paramCanvas != null) {
          paramCanvas.a(this);
        }
      }
      else
      {
        canvasHandler.postDelayed(runnable, k);
        if (timer == 0) {
          paramCanvas.save();
        }
        paramCanvas.drawCircle(x, y, radiusMax * (timer * frameRate / rippleDuration), paint);
        paint.setColor(Color.parseColor("#ffff4444"));
        float f1;
        if ((rippleType.intValue() == 1) && (originBitmap != null))
        {
          i = timer;
          f1 = i;
          j = frameRate;
          float f2 = j;
          k = rippleDuration;
          if (f1 * f2 / k > 0.4F)
          {
            if (durationEmpty == -1) {
              durationEmpty = (k - i * j);
            }
            timerEmpty += 1;
            Bitmap localBitmap = getCircleBitmap((int)(radiusMax * (timerEmpty * frameRate / durationEmpty)));
            paramCanvas.drawBitmap(localBitmap, 0.0F, 0.0F, paint);
            localBitmap.recycle();
          }
        }
        paint.setColor(rippleColor);
        if (rippleType.intValue() == 1)
        {
          f1 = timer;
          i = frameRate;
          if (f1 * i / rippleDuration > 0.6F)
          {
            paramCanvas = paint;
            j = rippleAlpha;
            paramCanvas.setAlpha((int)(j - j * (timerEmpty * i / durationEmpty)));
          }
          else
          {
            paint.setAlpha(rippleAlpha);
          }
        }
        else
        {
          paramCanvas = paint;
          i = rippleAlpha;
          paramCanvas.setAlpha((int)(i - i * (timer * frameRate / rippleDuration)));
        }
        timer += 1;
      }
    }
  }
  
  public int getFrameRate()
  {
    return frameRate;
  }
  
  public int getRippleAlpha()
  {
    return rippleAlpha;
  }
  
  public int getRippleColor()
  {
    return rippleColor;
  }
  
  public int getRippleDuration()
  {
    return rippleDuration;
  }
  
  public int getRipplePadding()
  {
    return ripplePadding;
  }
  
  public d getRippleType()
  {
    return d.values()[rippleType.intValue()];
  }
  
  public int getZoomDuration()
  {
    return zoomDuration;
  }
  
  public float getZoomScale()
  {
    return zoomScale;
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    onTouchEvent(paramMotionEvent);
    return super.onInterceptTouchEvent(paramMotionEvent);
  }
  
  public void onLongPress(MotionEvent paramMotionEvent)
  {
    createAnimation(paramMotionEvent.getX(), paramMotionEvent.getY());
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    WIDTH = paramInt1;
    HEIGHT = paramInt2;
    float f = zoomScale;
    scaleAnimation = new ScaleAnimation(1.0F, f, 1.0F, f, paramInt1 / 2, paramInt2 / 2);
    scaleAnimation.setDuration(zoomDuration);
    scaleAnimation.setRepeatMode(2);
    scaleAnimation.setRepeatCount(1);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (gestureDetector.onTouchEvent(paramMotionEvent))
    {
      onLongPress(paramMotionEvent);
      update(Boolean.valueOf(false));
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public void setCentered(Boolean paramBoolean)
  {
    isCentered = paramBoolean;
  }
  
  public void setFrameRate(int paramInt)
  {
    frameRate = paramInt;
  }
  
  public void setOnRippleCompleteListener(c paramC)
  {
    onCompletionListener = paramC;
  }
  
  public void setRippleAlpha(int paramInt)
  {
    rippleAlpha = paramInt;
  }
  
  public void setRippleColor(int paramInt)
  {
    rippleColor = getResources().getColor(paramInt);
  }
  
  public void setRippleDuration(int paramInt)
  {
    rippleDuration = paramInt;
  }
  
  public void setRipplePadding(int paramInt)
  {
    ripplePadding = paramInt;
  }
  
  public void setRippleType(d paramD)
  {
    rippleType = Integer.valueOf(paramD.ordinal());
  }
  
  public void setZoomDuration(int paramInt)
  {
    zoomDuration = paramInt;
  }
  
  public void setZoomScale(float paramFloat)
  {
    zoomScale = paramFloat;
  }
  
  public void setZooming(Boolean paramBoolean)
  {
    hasToZoom = paramBoolean;
  }
  
  class a
    implements Runnable
  {
    a() {}
    
    public void run()
    {
      invalidate();
    }
  }
  
  class b
    extends GestureDetector.SimpleOnGestureListener
  {
    b() {}
    
    public void onLongPress(MotionEvent paramMotionEvent)
    {
      super.onLongPress(paramMotionEvent);
      RippleView.this.onLongPress(paramMotionEvent);
      RippleView.init(RippleView.this, Boolean.valueOf(true));
    }
    
    public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent)
    {
      return true;
    }
    
    public boolean onSingleTapUp(MotionEvent paramMotionEvent)
    {
      return true;
    }
  }
  
  public static abstract interface c
  {
    public abstract void a(RippleView paramRippleView);
  }
  
  public static enum d
  {
    ENHANCE_YOUR_CALM(0),  INADEQUATE_SECURITY(1),  INVALID_CREDENTIALS(2);
    
    private d(int paramInt) {}
  }
}
