package com.squareup.picasso;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestCreator
{
  private static final AtomicInteger nextId = new AtomicInteger();
  private int context;
  private final Request.Builder data;
  private boolean deferred;
  private Drawable errorDrawable;
  private int errorResId;
  private Drawable icon;
  private int memoryPolicy;
  private int networkPolicy;
  private boolean noFade;
  private final Picasso picasso;
  private boolean setPlaceholder = true;
  private Object tag;
  
  RequestCreator(Picasso paramPicasso, Uri paramUri, int paramInt)
  {
    if (!shutdown)
    {
      picasso = paramPicasso;
      data = new Request.Builder(paramUri, paramInt, defaultBitmapConfig);
      return;
    }
    throw new IllegalStateException("Picasso instance already shut down. Cannot submit new requests.");
  }
  
  private Request createRequest(long paramLong)
  {
    int i = nextId.getAndIncrement();
    Object localObject = data.build();
    id = i;
    started = paramLong;
    boolean bool = picasso.loggingEnabled;
    if (bool) {
      Utils.log("Main", "created", ((Request)localObject).plainId(), ((Request)localObject).toString());
    }
    Request localRequest = picasso.transformRequest((Request)localObject);
    if (localRequest != localObject)
    {
      id = i;
      started = paramLong;
      if (bool)
      {
        localObject = localRequest.logId();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("into ");
        localStringBuilder.append(localRequest);
        Utils.log("Main", "changed", (String)localObject, localStringBuilder.toString());
      }
    }
    return localRequest;
  }
  
  private Drawable getDrawable()
  {
    int i = context;
    if (i != 0)
    {
      int j = Build.VERSION.SDK_INT;
      if (j >= 21) {
        return picasso.context.getDrawable(i);
      }
      if (j >= 16) {
        return picasso.context.getResources().getDrawable(context);
      }
      TypedValue localTypedValue = new TypedValue();
      picasso.context.getResources().getValue(context, localTypedValue, true);
      return picasso.context.getResources().getDrawable(resourceId);
    }
    return icon;
  }
  
  RequestCreator clearTag()
  {
    tag = null;
    return this;
  }
  
  public RequestCreator fit()
  {
    deferred = true;
    return this;
  }
  
  public void into(ImageView paramImageView)
  {
    into(paramImageView, null);
  }
  
  public void into(ImageView paramImageView, Callback paramCallback)
  {
    long l = System.nanoTime();
    Utils.checkMain();
    if (paramImageView != null)
    {
      if (!data.hasImage())
      {
        picasso.cancelRequest(paramImageView);
        if (setPlaceholder) {
          PicassoDrawable.setPlaceholder(paramImageView, getDrawable());
        }
      }
      else
      {
        if (deferred) {
          if (!data.hasSize())
          {
            int i = paramImageView.getWidth();
            int j = paramImageView.getHeight();
            if ((i != 0) && (j != 0))
            {
              data.resize(i, j);
            }
            else
            {
              if (setPlaceholder) {
                PicassoDrawable.setPlaceholder(paramImageView, getDrawable());
              }
              picasso.defer(paramImageView, new DeferredRequestCreator(this, paramImageView, paramCallback));
            }
          }
          else
          {
            throw new IllegalStateException("Fit cannot be used with resize.");
          }
        }
        Object localObject1 = createRequest(l);
        Object localObject2 = Utils.createKey((Request)localObject1);
        if (MemoryPolicy.shouldReadFromMemoryCache(memoryPolicy))
        {
          Bitmap localBitmap = picasso.quickMemoryCacheCheck((String)localObject2);
          if (localBitmap != null)
          {
            picasso.cancelRequest(paramImageView);
            localObject2 = picasso;
            PicassoDrawable.setBitmap(paramImageView, context, localBitmap, Picasso.LoadedFrom.MEMORY, noFade, indicatorsEnabled);
            if (picasso.loggingEnabled)
            {
              paramImageView = ((Request)localObject1).plainId();
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("from ");
              ((StringBuilder)localObject1).append(Picasso.LoadedFrom.MEMORY);
              Utils.log("Main", "completed", paramImageView, ((StringBuilder)localObject1).toString());
            }
            if (paramCallback == null) {
              return;
            }
            paramCallback.onSuccess();
            return;
          }
        }
        if (setPlaceholder) {
          PicassoDrawable.setPlaceholder(paramImageView, getDrawable());
        }
        paramImageView = new ImageViewAction(picasso, paramImageView, (Request)localObject1, memoryPolicy, networkPolicy, errorResId, errorDrawable, (String)localObject2, tag, paramCallback, noFade);
        picasso.enqueueAndSubmit(paramImageView);
      }
    }
    else {
      throw new IllegalArgumentException("Target must not be null.");
    }
  }
  
  public RequestCreator resize(int paramInt1, int paramInt2)
  {
    data.resize(paramInt1, paramInt2);
    return this;
  }
  
  public RequestCreator transform()
  {
    data.centerCrop(17);
    return this;
  }
  
  RequestCreator unfit()
  {
    deferred = false;
    return this;
  }
}
