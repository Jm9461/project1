package com.squareup.picasso;

import android.graphics.Bitmap;

public abstract interface Transformation
{
  public abstract String key();
  
  public abstract Bitmap transform(Bitmap paramBitmap);
}
