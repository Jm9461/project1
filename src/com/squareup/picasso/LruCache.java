package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;

public final class LruCache
  implements Cache
{
  final android.util.LruCache<String, m.b> cache;
  
  public LruCache(int paramInt)
  {
    cache = new LruBitmapCache(this, paramInt);
  }
  
  public LruCache(Context paramContext)
  {
    this(Utils.calculateMemoryCacheSize(paramContext));
  }
  
  public Bitmap get(String paramString)
  {
    paramString = (BitmapInfo)cache.get(paramString);
    if (paramString != null) {
      return bitmap;
    }
    return null;
  }
  
  public int maxSize()
  {
    return cache.maxSize();
  }
  
  public void set(String paramString, Bitmap paramBitmap)
  {
    if ((paramString != null) && (paramBitmap != null))
    {
      int i = Utils.getBitmapBytes(paramBitmap);
      if (i > maxSize())
      {
        cache.remove(paramString);
        return;
      }
      cache.put(paramString, new BitmapInfo(paramBitmap, i));
      return;
    }
    throw new NullPointerException("key == null || bitmap == null");
  }
  
  public int size()
  {
    return cache.size();
  }
}
