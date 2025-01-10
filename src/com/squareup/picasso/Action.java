package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

abstract class Action<T>
{
  boolean cancelled;
  final Drawable errorDrawable;
  final int errorResId;
  final String key;
  final int memoryPolicy;
  final int networkPolicy;
  final boolean noFade;
  final Picasso picasso;
  final Request request;
  final Object tag;
  final WeakReference<T> target;
  boolean willReplay;
  
  Action(Picasso paramPicasso, Object paramObject1, Request paramRequest, int paramInt1, int paramInt2, int paramInt3, Drawable paramDrawable, String paramString, Object paramObject2, boolean paramBoolean)
  {
    picasso = paramPicasso;
    request = paramRequest;
    if (paramObject1 == null) {
      paramPicasso = null;
    } else {
      paramPicasso = new RequestWeakReference(paramObject1, referenceQueue);
    }
    target = paramPicasso;
    memoryPolicy = paramInt1;
    networkPolicy = paramInt2;
    noFade = paramBoolean;
    errorResId = paramInt3;
    errorDrawable = paramDrawable;
    key = paramString;
    if (paramObject2 != null) {
      paramPicasso = paramObject2;
    } else {
      paramPicasso = this;
    }
    tag = paramPicasso;
  }
  
  void cancel()
  {
    willReplay = true;
  }
  
  abstract void complete(Bitmap paramBitmap, Picasso.LoadedFrom paramLoadedFrom);
  
  abstract void error(Exception paramException);
  
  String getKey()
  {
    return key;
  }
  
  int getMemoryPolicy()
  {
    return memoryPolicy;
  }
  
  int getNetworkPolicy()
  {
    return networkPolicy;
  }
  
  Picasso getPicasso()
  {
    return picasso;
  }
  
  Picasso.Priority getPriority()
  {
    return request.priority;
  }
  
  Request getRequest()
  {
    return request;
  }
  
  Object getTag()
  {
    return tag;
  }
  
  Object getTarget()
  {
    WeakReference localWeakReference = target;
    if (localWeakReference == null) {
      return null;
    }
    return localWeakReference.get();
  }
  
  boolean isCancelled()
  {
    return cancelled;
  }
  
  boolean willReplay()
  {
    return willReplay;
  }
  
  class RequestWeakReference<M>
    extends WeakReference<M>
  {
    RequestWeakReference(Object paramObject, ReferenceQueue paramReferenceQueue)
    {
      super(paramReferenceQueue);
    }
  }
}
