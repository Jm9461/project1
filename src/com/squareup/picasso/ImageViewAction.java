package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

class ImageViewAction
  extends a<ImageView>
{
  Callback callback;
  
  ImageViewAction(Picasso paramPicasso, ImageView paramImageView, Request paramRequest, int paramInt1, int paramInt2, int paramInt3, Drawable paramDrawable, String paramString, Object paramObject, Callback paramCallback, boolean paramBoolean)
  {
    super(paramPicasso, paramImageView, paramRequest, paramInt1, paramInt2, paramInt3, paramDrawable, paramString, paramObject, paramBoolean);
    callback = paramCallback;
  }
  
  void cancel()
  {
    super.cancel();
    if (callback != null) {
      callback = null;
    }
  }
  
  public void complete(Bitmap paramBitmap, Picasso.LoadedFrom paramLoadedFrom)
  {
    if (paramBitmap != null)
    {
      ImageView localImageView = (ImageView)target.get();
      if (localImageView == null) {
        return;
      }
      Picasso localPicasso = picasso;
      Context localContext = context;
      boolean bool = indicatorsEnabled;
      PicassoDrawable.setBitmap(localImageView, localContext, paramBitmap, paramLoadedFrom, noFade, bool);
      paramBitmap = callback;
      if (paramBitmap != null) {
        paramBitmap.onSuccess();
      }
    }
    else
    {
      throw new AssertionError(String.format("Attempted to complete action with no result!\n%s", new Object[] { this }));
    }
  }
  
  public void error(Exception paramException)
  {
    Object localObject = (ImageView)target.get();
    if (localObject == null) {
      return;
    }
    Drawable localDrawable = ((ImageView)localObject).getDrawable();
    if ((localDrawable instanceof Animatable)) {
      ((Animatable)localDrawable).stop();
    }
    int i = errorResId;
    if (i != 0)
    {
      ((ImageView)localObject).setImageResource(i);
    }
    else
    {
      localDrawable = errorDrawable;
      if (localDrawable != null) {
        ((ImageView)localObject).setImageDrawable(localDrawable);
      }
    }
    localObject = callback;
    if (localObject != null) {
      ((Callback)localObject).onError(paramException);
    }
  }
}
