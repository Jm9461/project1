package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.net.NetworkInfo;
import okio.Source;

public abstract class RequestHandler
{
  public RequestHandler() {}
  
  static void calculateInSampleSize(int paramInt1, int paramInt2, int paramInt3, int paramInt4, BitmapFactory.Options paramOptions, Request paramRequest)
  {
    int i = 1;
    if ((paramInt4 > paramInt2) || (paramInt3 > paramInt1)) {
      if (paramInt2 == 0)
      {
        i = (int)Math.floor(paramInt3 / paramInt1);
      }
      else if (paramInt1 == 0)
      {
        i = (int)Math.floor(paramInt4 / paramInt2);
      }
      else
      {
        paramInt2 = (int)Math.floor(paramInt4 / paramInt2);
        paramInt1 = (int)Math.floor(paramInt3 / paramInt1);
        if (centerInside) {
          paramInt1 = Math.max(paramInt2, paramInt1);
        } else {
          paramInt1 = Math.min(paramInt2, paramInt1);
        }
        i = paramInt1;
      }
    }
    inSampleSize = i;
    inJustDecodeBounds = false;
  }
  
  static void calculateInSampleSize(int paramInt1, int paramInt2, BitmapFactory.Options paramOptions, Request paramRequest)
  {
    calculateInSampleSize(paramInt1, paramInt2, outWidth, outHeight, paramOptions, paramRequest);
  }
  
  static BitmapFactory.Options createBitmapOptions(Request paramRequest)
  {
    boolean bool = paramRequest.hasSize();
    int i;
    if (body != null) {
      i = 1;
    } else {
      i = 0;
    }
    BitmapFactory.Options localOptions;
    if ((bool) || (i != 0) || (config))
    {
      localOptions = new BitmapFactory.Options();
      inJustDecodeBounds = bool;
      bool = config;
      inInputShareable = bool;
      inPurgeable = bool;
      if (i != 0)
      {
        inPreferredConfig = body;
        return localOptions;
      }
    }
    else
    {
      return null;
    }
    return localOptions;
  }
  
  static boolean requiresInSampleSize(BitmapFactory.Options paramOptions)
  {
    return (paramOptions != null) && (inJustDecodeBounds);
  }
  
  public abstract boolean canHandleRequest(Request paramRequest);
  
  int getRetryCount()
  {
    return 0;
  }
  
  public abstract Result load(Request paramRequest, int paramInt);
  
  boolean shouldRetry(boolean paramBoolean, NetworkInfo paramNetworkInfo)
  {
    return false;
  }
  
  boolean supportsReplay()
  {
    return false;
  }
  
  public final class Result
  {
    private final int exifOrientation;
    private final Picasso.LoadedFrom loadedFrom;
    private final Source stream;
    
    public Result(Picasso.LoadedFrom paramLoadedFrom)
    {
      this(null, paramLoadedFrom, 0);
    }
    
    Result(Source paramSource, Picasso.LoadedFrom paramLoadedFrom, int paramInt)
    {
      int j = 1;
      int i;
      if (RequestHandler.this != null) {
        i = 1;
      } else {
        i = 0;
      }
      if (paramSource == null) {
        j = 0;
      }
      if (i != j)
      {
        stream = paramSource;
        Utils.checkNotNull(paramLoadedFrom, "loadedFrom == null");
        loadedFrom = ((Picasso.LoadedFrom)paramLoadedFrom);
        exifOrientation = paramInt;
        return;
      }
      throw new AssertionError();
    }
    
    public Result(Picasso.LoadedFrom paramLoadedFrom)
    {
      this((Source)RequestHandler.this, paramLoadedFrom, 0);
    }
    
    public Bitmap getBitmap()
    {
      return RequestHandler.this;
    }
    
    int getExifOrientation()
    {
      return exifOrientation;
    }
    
    public Picasso.LoadedFrom getLoadedFrom()
    {
      return loadedFrom;
    }
    
    public Source getStream()
    {
      return stream;
    }
  }
}
