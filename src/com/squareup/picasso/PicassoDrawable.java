package com.squareup.picasso;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.widget.ImageView;

final class PicassoDrawable
  extends BitmapDrawable
{
  private static final Paint DEBUG_PAINT = new Paint();
  int alpha = 255;
  boolean animating;
  private final boolean debugging;
  private final float density;
  private final Picasso.LoadedFrom loadedFrom;
  Drawable placeholder;
  long startTimeMillis;
  
  PicassoDrawable(Context paramContext, Bitmap paramBitmap, Drawable paramDrawable, Picasso.LoadedFrom paramLoadedFrom, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramContext.getResources(), paramBitmap);
    debugging = paramBoolean2;
    density = getResourcesgetDisplayMetricsdensity;
    loadedFrom = paramLoadedFrom;
    int i;
    if ((paramLoadedFrom != Picasso.LoadedFrom.MEMORY) && (!paramBoolean1)) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0)
    {
      placeholder = paramDrawable;
      animating = true;
      startTimeMillis = SystemClock.uptimeMillis();
    }
  }
  
  private void drawDebugIndicator(Canvas paramCanvas)
  {
    DEBUG_PAINT.setColor(-1);
    paramCanvas.drawPath(getTrianglePath(0, 0, (int)(density * 16.0F)), DEBUG_PAINT);
    DEBUG_PAINT.setColor(loadedFrom.debugColor);
    paramCanvas.drawPath(getTrianglePath(0, 0, (int)(density * 15.0F)), DEBUG_PAINT);
  }
  
  private static Path getTrianglePath(int paramInt1, int paramInt2, int paramInt3)
  {
    Path localPath = new Path();
    localPath.moveTo(paramInt1, paramInt2);
    localPath.lineTo(paramInt1 + paramInt3, paramInt2);
    localPath.lineTo(paramInt1, paramInt2 + paramInt3);
    return localPath;
  }
  
  static void setBitmap(ImageView paramImageView, Context paramContext, Bitmap paramBitmap, Picasso.LoadedFrom paramLoadedFrom, boolean paramBoolean1, boolean paramBoolean2)
  {
    Drawable localDrawable = paramImageView.getDrawable();
    if ((localDrawable instanceof Animatable)) {
      ((Animatable)localDrawable).stop();
    }
    paramImageView.setImageDrawable(new PicassoDrawable(paramContext, paramBitmap, localDrawable, paramLoadedFrom, paramBoolean1, paramBoolean2));
  }
  
  static void setPlaceholder(ImageView paramImageView, Drawable paramDrawable)
  {
    paramImageView.setImageDrawable(paramDrawable);
    if ((paramImageView.getDrawable() instanceof Animatable)) {
      ((Animatable)paramImageView.getDrawable()).start();
    }
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (!animating)
    {
      super.draw(paramCanvas);
    }
    else
    {
      float f = (float)(SystemClock.uptimeMillis() - startTimeMillis) / 200.0F;
      if (f >= 1.0F)
      {
        animating = false;
        placeholder = null;
        super.draw(paramCanvas);
      }
      else
      {
        Drawable localDrawable = placeholder;
        if (localDrawable != null) {
          localDrawable.draw(paramCanvas);
        }
        super.setAlpha((int)(alpha * f));
        super.draw(paramCanvas);
        super.setAlpha(alpha);
      }
    }
    if (debugging) {
      drawDebugIndicator(paramCanvas);
    }
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    Drawable localDrawable = placeholder;
    if (localDrawable != null) {
      localDrawable.setBounds(paramRect);
    }
    super.onBoundsChange(paramRect);
  }
  
  public void setAlpha(int paramInt)
  {
    alpha = paramInt;
    Drawable localDrawable = placeholder;
    if (localDrawable != null) {
      localDrawable.setAlpha(paramInt);
    }
    super.setAlpha(paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    Drawable localDrawable = placeholder;
    if (localDrawable != null) {
      localDrawable.setColorFilter(paramColorFilter);
    }
    super.setColorFilter(paramColorFilter);
  }
}
