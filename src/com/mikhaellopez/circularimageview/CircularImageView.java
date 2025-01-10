package com.mikhaellopez.circularimageview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class CircularImageView
  extends ImageView
{
  private static final ImageView.ScaleType SCALE_TYPE = ImageView.ScaleType.CENTER_CROP;
  private Paint b;
  private Bitmap bitmap;
  private float j;
  private int m = -16777216;
  private Drawable scale;
  private int width;
  private Paint x;
  private float y;
  
  public CircularImageView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CircularImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public CircularImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt);
  }
  
  private Bitmap getBitmap(Drawable paramDrawable)
  {
    if (paramDrawable == null) {
      return null;
    }
    if ((paramDrawable instanceof BitmapDrawable)) {
      return ((BitmapDrawable)paramDrawable).getBitmap();
    }
    int i = paramDrawable.getIntrinsicWidth();
    int k = paramDrawable.getIntrinsicHeight();
    if (i > 0)
    {
      if (k <= 0) {
        return null;
      }
      try
      {
        Bitmap localBitmap = Bitmap.createBitmap(i, k, Bitmap.Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap);
        paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
        paramDrawable.draw(localCanvas);
        return localBitmap;
      }
      catch (OutOfMemoryError paramDrawable)
      {
        Log.e(getClass().toString(), "Encountered OutOfMemoryError while generating bitmap!");
      }
    }
    return null;
  }
  
  private void init(float paramFloat, int paramInt)
  {
    j = paramFloat;
    m = paramInt;
    if (Build.VERSION.SDK_INT >= 11) {
      setLayerType(1, b);
    }
    b.setShadowLayer(paramFloat, 0.0F, paramFloat / 2.0F, paramInt);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    x = new Paint();
    x.setAntiAlias(true);
    b = new Paint();
    b.setAntiAlias(true);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, IpAddress.CircularImageView, paramInt, 0);
    if (paramContext.getBoolean(IpAddress.CircularImageView_civ_border, true))
    {
      float f = getContextgetResourcesgetDisplayMetricsdensity;
      setBorderWidth(paramContext.getDimension(IpAddress.CircularImageView_civ_border_width, f * 4.0F));
      setBorderColor(paramContext.getColor(IpAddress.CircularImageView_civ_border_color, -1));
    }
    if (paramContext.getBoolean(IpAddress.CircularImageView_civ_shadow, false))
    {
      j = 8.0F;
      init(paramContext.getFloat(IpAddress.CircularImageView_civ_shadow_radius, j), paramContext.getColor(IpAddress.CircularImageView_civ_shadow_color, m));
    }
  }
  
  private int measure(int paramInt)
  {
    int i = View.MeasureSpec.getMode(paramInt);
    paramInt = View.MeasureSpec.getSize(paramInt);
    if ((i != 1073741824) && (i != Integer.MIN_VALUE)) {
      paramInt = width;
    }
    return paramInt + 2;
  }
  
  private int measureHeight(int paramInt)
  {
    int i = View.MeasureSpec.getMode(paramInt);
    paramInt = View.MeasureSpec.getSize(paramInt);
    if (i == 1073741824) {
      return paramInt;
    }
    if (i == Integer.MIN_VALUE) {
      return paramInt;
    }
    return width;
  }
  
  private Bitmap transform(Bitmap paramBitmap)
  {
    if (paramBitmap.getWidth() >= paramBitmap.getHeight()) {
      return Bitmap.createBitmap(paramBitmap, paramBitmap.getWidth() / 2 - paramBitmap.getHeight() / 2, 0, paramBitmap.getHeight(), paramBitmap.getHeight());
    }
    return Bitmap.createBitmap(paramBitmap, 0, paramBitmap.getHeight() / 2 - paramBitmap.getWidth() / 2, paramBitmap.getWidth(), paramBitmap.getWidth());
  }
  
  private void transform()
  {
    Object localObject1 = bitmap;
    if (localObject1 == null) {
      return;
    }
    bitmap = transform((Bitmap)localObject1);
    localObject1 = bitmap;
    Object localObject2 = Shader.TileMode.CLAMP;
    localObject1 = new BitmapShader((Bitmap)localObject1, (Shader.TileMode)localObject2, (Shader.TileMode)localObject2);
    localObject2 = new Matrix();
    ((Matrix)localObject2).setScale(width / bitmap.getWidth(), width / bitmap.getHeight());
    ((Shader)localObject1).setLocalMatrix((Matrix)localObject2);
    x.setShader((Shader)localObject1);
  }
  
  private void zoomOut()
  {
    if (scale == getDrawable()) {
      return;
    }
    scale = getDrawable();
    bitmap = getBitmap(scale);
    transform();
  }
  
  public ImageView.ScaleType getScaleType()
  {
    return SCALE_TYPE;
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    zoomOut();
    if (bitmap == null) {
      return;
    }
    if (!isInEditMode())
    {
      width = paramCanvas.getWidth();
      if (paramCanvas.getHeight() < width) {
        width = paramCanvas.getHeight();
      }
    }
    float f2 = width;
    float f1 = y;
    int i = (int)(f2 - f1 * 2.0F) / 2;
    f2 = i;
    float f3 = i;
    float f4 = i;
    float f5 = j;
    paramCanvas.drawCircle(f2 + f1, f3 + f1, f4 + f1 - (f5 + f5 / 2.0F), b);
    f1 = i;
    f2 = y;
    f3 = i;
    f4 = i;
    f5 = j;
    paramCanvas.drawCircle(f1 + f2, f3 + f2, f4 - (f5 + f5 / 2.0F), x);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(measureHeight(paramInt1), measure(paramInt2));
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    width = paramInt1;
    if (paramInt2 < width) {
      width = paramInt2;
    }
    if (bitmap != null) {
      transform();
    }
  }
  
  public void setBorderColor(int paramInt)
  {
    Paint localPaint = b;
    if (localPaint != null) {
      localPaint.setColor(paramInt);
    }
    invalidate();
  }
  
  public void setBorderWidth(float paramFloat)
  {
    y = paramFloat;
    requestLayout();
    invalidate();
  }
  
  public void setScaleType(ImageView.ScaleType paramScaleType)
  {
    if (paramScaleType == SCALE_TYPE) {
      return;
    }
    throw new IllegalArgumentException(String.format("ScaleType %s not supported. ScaleType.CENTER_CROP is used by default. So you don't need to use ScaleType.", new Object[] { paramScaleType }));
  }
  
  public void setShadowColor(int paramInt)
  {
    init(j, paramInt);
    invalidate();
  }
  
  public void setShadowRadius(float paramFloat)
  {
    init(paramFloat, m);
    invalidate();
  }
}
