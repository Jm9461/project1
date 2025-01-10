package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Handler;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

class BitmapHunter
  implements Runnable
{
  private static final Object DECODE_LOCK = new Object();
  private static final RequestHandler ERRORING_HANDLER = new RequestHandler()
  {
    public boolean canHandleRequest(Request paramAnonymousRequest)
    {
      return true;
    }
    
    public RequestHandler.Result load(Request paramAnonymousRequest, int paramAnonymousInt)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unrecognized type of request: ");
      localStringBuilder.append(paramAnonymousRequest);
      throw new IllegalStateException(localStringBuilder.toString());
    }
  };
  private static final ThreadLocal<StringBuilder> NAME_BUILDER = new ThreadLocal()
  {
    protected StringBuilder initialValue()
    {
      return new StringBuilder("Picasso-");
    }
  };
  private static final AtomicInteger SEQUENCE_GENERATOR = new AtomicInteger();
  Action action;
  List<a> actions;
  final Cache cache;
  final Request data;
  final Dispatcher dispatcher;
  Exception exception;
  int exifRotation;
  Future<?> future;
  final String key;
  Picasso.LoadedFrom loadedFrom;
  final int memoryPolicy;
  int networkPolicy;
  final Picasso picasso;
  Picasso.Priority priority;
  final RequestHandler requestHandler;
  Bitmap result;
  int retryCount;
  final int sequence = SEQUENCE_GENERATOR.incrementAndGet();
  final Stats stats;
  
  BitmapHunter(Picasso paramPicasso, Dispatcher paramDispatcher, Cache paramCache, Stats paramStats, Action paramAction, RequestHandler paramRequestHandler)
  {
    picasso = paramPicasso;
    dispatcher = paramDispatcher;
    cache = paramCache;
    stats = paramStats;
    action = paramAction;
    key = paramAction.getKey();
    data = paramAction.getRequest();
    priority = paramAction.getPriority();
    memoryPolicy = paramAction.getMemoryPolicy();
    networkPolicy = paramAction.getNetworkPolicy();
    requestHandler = paramRequestHandler;
    retryCount = paramRequestHandler.getRetryCount();
  }
  
  static Bitmap applyCustomTransformations(List paramList, Bitmap paramBitmap)
  {
    int i = 0;
    int j = paramList.size();
    while (i < j)
    {
      Transformation localTransformation = (Transformation)paramList.get(i);
      try
      {
        Bitmap localBitmap = localTransformation.transform(paramBitmap);
        if (localBitmap == null)
        {
          paramBitmap = new StringBuilder();
          paramBitmap.append("Transformation ");
          paramBitmap.append(localTransformation.key());
          paramBitmap.append(" returned null after ");
          paramBitmap.append(i);
          paramBitmap = paramBitmap.append(" previous transformation(s).\n\nTransformation list:\n");
          paramList = paramList.iterator();
          while (paramList.hasNext())
          {
            paramBitmap.append(((Transformation)paramList.next()).key());
            paramBitmap.append('\n');
          }
          Picasso.HANDLER.post(new BitmapHunter.4(paramBitmap));
          return null;
        }
        if ((localBitmap == paramBitmap) && (paramBitmap.isRecycled()))
        {
          Picasso.HANDLER.post(new BitmapHunter.6(localTransformation));
          return null;
        }
        if ((localBitmap != paramBitmap) && (!paramBitmap.isRecycled()))
        {
          Picasso.HANDLER.post(new BitmapHunter.5(localTransformation));
          return null;
        }
        paramBitmap = localBitmap;
        i += 1;
      }
      catch (RuntimeException paramList)
      {
        Picasso.HANDLER.post(new BitmapHunter.3(localTransformation, paramList));
        return null;
      }
    }
    return paramBitmap;
  }
  
  private Picasso.Priority computeNewPriority()
  {
    Object localObject1 = Picasso.Priority.LOW;
    Object localObject2 = actions;
    int j = 0;
    int i;
    if ((localObject2 != null) && (!((List)localObject2).isEmpty())) {
      i = 1;
    } else {
      i = 0;
    }
    if ((action != null) || (i != 0)) {
      j = 1;
    }
    if (j == 0) {
      return localObject1;
    }
    localObject2 = action;
    if (localObject2 != null) {
      localObject1 = ((Action)localObject2).getPriority();
    }
    localObject2 = localObject1;
    if (i != 0)
    {
      i = 0;
      j = actions.size();
      for (;;)
      {
        localObject2 = localObject1;
        if (i >= j) {
          break;
        }
        Picasso.Priority localPriority = ((Action)actions.get(i)).getPriority();
        localObject2 = localObject1;
        if (localPriority.ordinal() > ((Enum)localObject1).ordinal()) {
          localObject2 = localPriority;
        }
        i += 1;
        localObject1 = localObject2;
      }
    }
    return localObject2;
  }
  
  static int decode(int paramInt)
  {
    if ((paramInt != 2) && (paramInt != 7) && (paramInt != 4) && (paramInt != 5)) {
      return 1;
    }
    return -1;
  }
  
  static Bitmap decodeStream(Source paramSource, Request paramRequest)
  {
    paramSource = Okio.buffer(paramSource);
    boolean bool1 = Utils.isWebPFile(paramSource);
    int i;
    if ((config) && (Build.VERSION.SDK_INT < 21)) {
      i = 1;
    } else {
      i = 0;
    }
    BitmapFactory.Options localOptions = RequestHandler.createBitmapOptions(paramRequest);
    boolean bool2 = RequestHandler.requiresInSampleSize(localOptions);
    if ((!bool1) && (i == 0))
    {
      Object localObject = paramSource.inputStream();
      paramSource = (Source)localObject;
      if (bool2)
      {
        localObject = new MarkableInputStream((InputStream)localObject);
        paramSource = (Source)localObject;
        ((MarkableInputStream)localObject).reset(false);
        long l = ((MarkableInputStream)localObject).savePosition(1024);
        BitmapFactory.decodeStream((InputStream)localObject, null, localOptions);
        RequestHandler.calculateInSampleSize(targetWidth, targetHeight, localOptions, paramRequest);
        ((MarkableInputStream)localObject).reset(l);
        ((MarkableInputStream)localObject).reset(true);
      }
      paramSource = BitmapFactory.decodeStream((InputStream)paramSource, null, localOptions);
      if (paramSource != null) {
        return paramSource;
      }
      throw new IOException("Failed to decode stream.");
    }
    paramSource = paramSource.readByteArray();
    if (bool2)
    {
      BitmapFactory.decodeByteArray(paramSource, 0, paramSource.length, localOptions);
      RequestHandler.calculateInSampleSize(targetWidth, targetHeight, localOptions, paramRequest);
    }
    return BitmapFactory.decodeByteArray(paramSource, 0, paramSource.length, localOptions);
  }
  
  static BitmapHunter forRequest(Picasso paramPicasso, Dispatcher paramDispatcher, Cache paramCache, Stats paramStats, Action paramAction)
  {
    Request localRequest = paramAction.getRequest();
    List localList = paramPicasso.getRequestHandlers();
    int i = 0;
    int j = localList.size();
    while (i < j)
    {
      RequestHandler localRequestHandler = (RequestHandler)localList.get(i);
      if (localRequestHandler.canHandleRequest(localRequest)) {
        return new BitmapHunter(paramPicasso, paramDispatcher, paramCache, paramStats, paramAction, localRequestHandler);
      }
      i += 1;
    }
    return new BitmapHunter(paramPicasso, paramDispatcher, paramCache, paramStats, paramAction, ERRORING_HANDLER);
  }
  
  static int getExifOrientation(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 0;
    case 7: 
    case 8: 
      return 270;
    case 5: 
    case 6: 
      return 90;
    }
    return 180;
  }
  
  private static boolean shouldResize(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return (!paramBoolean) || ((paramInt3 != 0) && (paramInt1 > paramInt3)) || ((paramInt4 != 0) && (paramInt2 > paramInt4));
  }
  
  static Bitmap transformResult(Request paramRequest, Bitmap paramBitmap, int paramInt)
  {
    int m = paramBitmap.getWidth();
    int k = paramBitmap.getHeight();
    boolean bool = onlyScaleDown;
    Matrix localMatrix1 = new Matrix();
    int j;
    int i;
    int n;
    if ((!paramRequest.needsMatrixTransform()) && (paramInt == 0))
    {
      paramInt = 0;
      j = 0;
      i = m;
      n = k;
    }
    else
    {
      float f1 = rotationDegrees;
      float f2;
      if (f1 != 0.0F)
      {
        double d1 = Math.cos(Math.toRadians(f1));
        double d2 = Math.sin(Math.toRadians(f1));
        double d3;
        double d4;
        double d5;
        double d6;
        double d7;
        if (hasRotationPivot)
        {
          localMatrix1.setRotate(f1, rotationPivotX, rotationPivotY);
          f1 = rotationPivotX;
          d3 = f1;
          Double.isNaN(d3);
          f2 = rotationPivotY;
          d4 = f2;
          Double.isNaN(d4);
          d3 = d3 * (1.0D - d1) + d4 * d2;
          d4 = f2;
          Double.isNaN(d4);
          d5 = f1;
          Double.isNaN(d5);
          d4 = d4 * (1.0D - d1) - d5 * d2;
          i = targetWidth;
          d5 = i;
          Double.isNaN(d5);
          d6 = d5 * d1 + d3;
          d5 = i;
          Double.isNaN(d5);
          d5 = d5 * d2 + d4;
          d7 = i;
          Double.isNaN(d7);
          j = targetHeight;
          double d8 = j;
          Double.isNaN(d8);
          d7 = d7 * d1 + d3 - d8 * d2;
          d8 = i;
          Double.isNaN(d8);
          double d9 = j;
          Double.isNaN(d9);
          d8 = d8 * d2 + d4 + d9 * d1;
          d9 = j;
          Double.isNaN(d9);
          d2 = d3 - d9 * d2;
          d9 = j;
          Double.isNaN(d9);
          d9 = d9 * d1 + d4;
          d1 = Math.max(d2, Math.max(d7, Math.max(d3, d6)));
          d3 = Math.min(d2, Math.min(d7, Math.min(d3, d6)));
          d2 = Math.max(d9, Math.max(d8, Math.max(d4, d5)));
          d4 = Math.min(d9, Math.min(d8, Math.min(d4, d5)));
          d1 = Math.floor(d1 - d3);
          d2 = Math.floor(d2 - d4);
          i = (int)d1;
          j = (int)d2;
        }
        else
        {
          localMatrix1.setRotate(f1);
          i = targetWidth;
          d3 = i;
          Double.isNaN(d3);
          d4 = d3 * d1;
          d3 = i;
          Double.isNaN(d3);
          d3 *= d2;
          d5 = i;
          Double.isNaN(d5);
          j = targetHeight;
          d6 = j;
          Double.isNaN(d6);
          d5 = d5 * d1 - d6 * d2;
          d6 = i;
          Double.isNaN(d6);
          d7 = j;
          Double.isNaN(d7);
          d6 = d6 * d2 + d7 * d1;
          d7 = j;
          Double.isNaN(d7);
          d2 = -(d7 * d2);
          d7 = j;
          Double.isNaN(d7);
          d7 *= d1;
          d1 = Math.max(d2, Math.max(d5, Math.max(0.0D, d4)));
          d2 = Math.min(d2, Math.min(d5, Math.min(0.0D, d4)));
          d4 = Math.max(d7, Math.max(d6, Math.max(0.0D, d3)));
          d3 = Math.min(d7, Math.min(d6, Math.min(0.0D, d3)));
          i = (int)Math.floor(d1 - d2);
          j = (int)Math.floor(d4 - d3);
        }
      }
      else
      {
        i = targetWidth;
        j = targetHeight;
      }
      int i4 = m;
      int i3 = k;
      Matrix localMatrix2 = localMatrix1;
      int i7 = 0;
      int i5 = 0;
      int i8 = 0;
      int i6 = 0;
      int i1 = i;
      int i2 = j;
      if (paramInt != 0)
      {
        i1 = getExifOrientation(paramInt);
        int i9 = decode(paramInt);
        n = i;
        paramInt = j;
        if (i1 != 0)
        {
          localMatrix2.preRotate(i1);
          if (i1 != 90)
          {
            n = i;
            paramInt = j;
            if (i1 != 270) {}
          }
          else
          {
            paramInt = i;
            n = j;
          }
        }
        i1 = n;
        i2 = paramInt;
        if (i9 != 1)
        {
          localMatrix2.postScale(i9, 1.0F);
          i2 = paramInt;
          i1 = n;
        }
      }
      float f3;
      if (centerCrop)
      {
        if (i1 != 0) {
          f1 = i1 / i4;
        } else {
          f1 = i2 / i3;
        }
        n = i4;
        if (i2 != 0)
        {
          f2 = i2;
          f3 = i3;
        }
        else
        {
          f2 = i1;
          f3 = n;
        }
        f2 /= f3;
        if (f1 > f2)
        {
          k = (int)Math.ceil(i3 * (f2 / f1));
          paramInt = data;
          if ((paramInt & 0x30) == 48) {
            i = 0;
          } else if ((paramInt & 0x50) == 80) {
            i = i3 - k;
          } else {
            i = (i3 - k) / 2;
          }
          f2 = i2 / k;
          paramInt = i6;
          j = i;
          i = m;
        }
        else if (f1 < f2)
        {
          i = (int)Math.ceil(n * (f1 / f2));
          paramInt = data;
          if ((paramInt & 0x3) == 3) {
            paramInt = 0;
          } else if ((paramInt & 0x5) == 5) {
            paramInt = n - i;
          } else {
            paramInt = (n - i) / 2;
          }
          f1 = i1 / i;
          j = i5;
        }
        else
        {
          f1 = f2;
          f3 = f2;
          paramInt = 0;
          i = n;
          f2 = f1;
          f1 = f3;
          j = i5;
        }
        if (shouldResize(bool, n, i3, i1, i2)) {
          localMatrix2.preScale(f1, f2);
        }
        n = k;
      }
      else
      {
        if (centerInside)
        {
          if (i1 != 0)
          {
            f1 = i1;
            f2 = i4;
          }
          else
          {
            f1 = i2;
            f2 = i3;
          }
          f3 = f1 / f2;
          if (i2 != 0)
          {
            f1 = i2;
            f2 = i3;
          }
          else
          {
            f1 = i1;
            f2 = i4;
          }
          f1 /= f2;
          if (f3 < f1) {
            f1 = f3;
          }
          if (shouldResize(bool, i4, i3, i1, i2)) {
            localMatrix2.preScale(f1, f1);
          }
        }
        else
        {
          if ((i1 != 0) || (i2 != 0)) {
            break label1322;
          }
        }
        label1322:
        while ((i1 == i4) && (i2 == i3))
        {
          paramInt = i8;
          j = i7;
          i = m;
          n = k;
          break;
        }
        if (i1 != 0)
        {
          f1 = i1;
          f2 = i4;
        }
        else
        {
          f1 = i2;
          f2 = i3;
        }
        f3 = f1 / f2;
        if (i2 != 0)
        {
          f1 = i2;
          f2 = i3;
        }
        else
        {
          f1 = i1;
          f2 = i4;
        }
        f1 /= f2;
        paramInt = i8;
        j = i7;
        i = m;
        n = k;
        if (shouldResize(bool, i4, i3, i1, i2))
        {
          localMatrix2.preScale(f3, f1);
          n = k;
          i = m;
          j = i7;
          paramInt = i8;
        }
      }
    }
    paramRequest = Bitmap.createBitmap(paramBitmap, paramInt, j, i, n, localMatrix1, true);
    if (paramRequest != paramBitmap)
    {
      paramBitmap.recycle();
      return paramRequest;
    }
    return paramBitmap;
  }
  
  static void updateThreadName(Request paramRequest)
  {
    paramRequest = paramRequest.getName();
    StringBuilder localStringBuilder = (StringBuilder)NAME_BUILDER.get();
    localStringBuilder.ensureCapacity("Picasso-".length() + paramRequest.length());
    localStringBuilder.replace("Picasso-".length(), localStringBuilder.length(), paramRequest);
    Thread.currentThread().setName(localStringBuilder.toString());
  }
  
  void attach(Action paramAction)
  {
    boolean bool = picasso.loggingEnabled;
    Request localRequest = request;
    if (action == null)
    {
      action = paramAction;
      if (bool)
      {
        paramAction = actions;
        if ((paramAction != null) && (!paramAction.isEmpty()))
        {
          Utils.log("Hunter", "joined", localRequest.logId(), Utils.getLogIdsForHunter(this, "to "));
          return;
        }
        Utils.log("Hunter", "joined", localRequest.logId(), "to empty hunter");
      }
    }
    else
    {
      if (actions == null) {
        actions = new ArrayList(3);
      }
      actions.add(paramAction);
      if (bool) {
        Utils.log("Hunter", "joined", localRequest.logId(), Utils.getLogIdsForHunter(this, "to "));
      }
      paramAction = paramAction.getPriority();
      if (paramAction.ordinal() > priority.ordinal()) {
        priority = paramAction;
      }
    }
  }
  
  boolean cancel()
  {
    if (action == null)
    {
      Object localObject = actions;
      if ((localObject == null) || (((List)localObject).isEmpty()))
      {
        localObject = future;
        if ((localObject != null) && (((Future)localObject).cancel(false))) {
          return true;
        }
      }
    }
    return false;
  }
  
  void detach(Action paramAction)
  {
    boolean bool = false;
    if (action == paramAction)
    {
      action = null;
      bool = true;
    }
    else
    {
      List localList = actions;
      if (localList != null) {
        bool = localList.remove(paramAction);
      }
    }
    if ((bool) && (paramAction.getPriority() == priority)) {
      priority = computeNewPriority();
    }
    if (picasso.loggingEnabled) {
      Utils.log("Hunter", "removed", request.logId(), Utils.getLogIdsForHunter(this, "from "));
    }
  }
  
  Action getAction()
  {
    return action;
  }
  
  List getActions()
  {
    return actions;
  }
  
  Request getData()
  {
    return data;
  }
  
  Exception getException()
  {
    return exception;
  }
  
  String getKey()
  {
    return key;
  }
  
  Picasso.LoadedFrom getLoadedFrom()
  {
    return loadedFrom;
  }
  
  int getMemoryPolicy()
  {
    return memoryPolicy;
  }
  
  Picasso getPicasso()
  {
    return picasso;
  }
  
  Picasso.Priority getPriority()
  {
    return priority;
  }
  
  Bitmap getResult()
  {
    return result;
  }
  
  Bitmap hunt()
  {
    Object localObject1 = null;
    Object localObject3;
    if (MemoryPolicy.shouldReadFromMemoryCache(memoryPolicy))
    {
      localObject3 = cache.get(key);
      localObject1 = localObject3;
      if (localObject3 != null)
      {
        stats.dispatchCacheHit();
        loadedFrom = Picasso.LoadedFrom.MEMORY;
        if (!picasso.loggingEnabled) {
          break label408;
        }
        Utils.log("Hunter", "decoded", data.logId(), "from cache");
        return localObject3;
      }
    }
    int i;
    if (retryCount == 0) {
      i = OFFLINEindex;
    } else {
      i = networkPolicy;
    }
    networkPolicy = i;
    Object localObject5 = requestHandler.load(data, networkPolicy);
    if (localObject5 != null)
    {
      loadedFrom = ((RequestHandler.Result)localObject5).getLoadedFrom();
      exifRotation = ((RequestHandler.Result)localObject5).getExifOrientation();
      localObject3 = ((RequestHandler.Result)localObject5).getBitmap();
      localObject1 = localObject3;
      if (localObject3 == null)
      {
        localObject3 = ((RequestHandler.Result)localObject5).getStream();
        try
        {
          localObject1 = decodeStream((Source)localObject3, data);
          try
          {
            ((Source)localObject3).close();
          }
          catch (IOException localIOException1) {}
          localObject4 = localThrowable1;
        }
        catch (Throwable localThrowable1)
        {
          try
          {
            localIOException1.close();
          }
          catch (IOException localIOException2) {}
          throw localThrowable1;
        }
      }
    }
    Object localObject4;
    if (localThrowable1 != null)
    {
      if (picasso.loggingEnabled) {
        Utils.log("Hunter", "decoded", data.logId());
      }
      stats.dispatchBitmapDecoded(localThrowable1);
      if (!data.needsTransformation())
      {
        localObject4 = localThrowable1;
        if (exifRotation == 0) {}
      }
      else
      {
        localObject5 = DECODE_LOCK;
        try
        {
          if (!data.needsMatrixTransform())
          {
            localObject4 = localThrowable1;
            if (exifRotation == 0) {}
          }
          else
          {
            localObject2 = transformResult(data, localThrowable1, exifRotation);
            localObject4 = localObject2;
            if (picasso.loggingEnabled)
            {
              Utils.log("Hunter", "transformed", data.logId());
              localObject4 = localObject2;
            }
          }
          Object localObject2 = localObject4;
          if (data.hasCustomTransformations())
          {
            localObject4 = applyCustomTransformations(data.transformations, (Bitmap)localObject4);
            localObject2 = localObject4;
            if (picasso.loggingEnabled)
            {
              Utils.log("Hunter", "transformed", data.logId(), "from custom transformations");
              localObject2 = localObject4;
            }
          }
          localObject4 = localObject2;
          if (localObject2 == null) {
            return localObject4;
          }
          stats.dispatchBitmapTransformed((Bitmap)localObject2);
          return localObject2;
        }
        catch (Throwable localThrowable2)
        {
          throw localThrowable2;
        }
        label408:
        return localObject4;
      }
    }
    return localObject4;
  }
  
  boolean isCancelled()
  {
    Future localFuture = future;
    return (localFuture != null) && (localFuture.isCancelled());
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 104	com/squareup/picasso/BitmapHunter:data	Lcom/squareup/picasso/Request;
    //   4: astore_3
    //   5: aload_3
    //   6: invokestatic 648	com/squareup/picasso/BitmapHunter:updateThreadName	(Lcom/squareup/picasso/Request;)V
    //   9: aload_0
    //   10: getfield 82	com/squareup/picasso/BitmapHunter:picasso	Lcom/squareup/picasso/Picasso;
    //   13: getfield 480	com/squareup/picasso/Picasso:loggingEnabled	Z
    //   16: istore_2
    //   17: iload_2
    //   18: ifeq +16 -> 34
    //   21: ldc_w 485
    //   24: ldc_w 650
    //   27: aload_0
    //   28: invokestatic 653	com/squareup/picasso/Utils:getLogIdsForHunter	(Lcom/squareup/picasso/BitmapHunter;)Ljava/lang/String;
    //   31: invokestatic 612	com/squareup/picasso/Utils:log	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   34: aload_0
    //   35: invokevirtual 655	com/squareup/picasso/BitmapHunter:hunt	()Landroid/graphics/Bitmap;
    //   38: astore_3
    //   39: aload_0
    //   40: aload_3
    //   41: putfield 546	com/squareup/picasso/BitmapHunter:result	Landroid/graphics/Bitmap;
    //   44: aload_0
    //   45: getfield 546	com/squareup/picasso/BitmapHunter:result	Landroid/graphics/Bitmap;
    //   48: astore_3
    //   49: aload_3
    //   50: ifnonnull +16 -> 66
    //   53: aload_0
    //   54: getfield 84	com/squareup/picasso/BitmapHunter:dispatcher	Lcom/squareup/picasso/Dispatcher;
    //   57: astore_3
    //   58: aload_3
    //   59: aload_0
    //   60: invokevirtual 661	com/squareup/picasso/Dispatcher:dispatchFailed	(Lcom/squareup/picasso/BitmapHunter;)V
    //   63: goto +108 -> 171
    //   66: aload_0
    //   67: getfield 84	com/squareup/picasso/BitmapHunter:dispatcher	Lcom/squareup/picasso/Dispatcher;
    //   70: astore_3
    //   71: aload_3
    //   72: aload_0
    //   73: invokevirtual 664	com/squareup/picasso/Dispatcher:dispatchComplete	(Lcom/squareup/picasso/BitmapHunter;)V
    //   76: goto +95 -> 171
    //   79: astore_3
    //   80: goto +142 -> 222
    //   83: astore_3
    //   84: aload_0
    //   85: aload_3
    //   86: putfield 536	com/squareup/picasso/BitmapHunter:exception	Ljava/lang/Exception;
    //   89: aload_0
    //   90: getfield 84	com/squareup/picasso/BitmapHunter:dispatcher	Lcom/squareup/picasso/Dispatcher;
    //   93: aload_0
    //   94: invokevirtual 661	com/squareup/picasso/Dispatcher:dispatchFailed	(Lcom/squareup/picasso/BitmapHunter;)V
    //   97: goto +74 -> 171
    //   100: astore_3
    //   101: new 666	java/io/StringWriter
    //   104: dup
    //   105: invokespecial 667	java/io/StringWriter:<init>	()V
    //   108: astore 4
    //   110: aload_0
    //   111: getfield 88	com/squareup/picasso/BitmapHunter:stats	Lcom/squareup/picasso/Stats;
    //   114: invokevirtual 671	com/squareup/picasso/Stats:createSnapshot	()Lcom/squareup/picasso/StatsSnapshot;
    //   117: new 673	java/io/PrintWriter
    //   120: dup
    //   121: aload 4
    //   123: invokespecial 676	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
    //   126: invokevirtual 682	com/squareup/picasso/StatsSnapshot:dump	(Ljava/io/PrintWriter;)V
    //   129: aload_0
    //   130: new 133	java/lang/RuntimeException
    //   133: dup
    //   134: aload 4
    //   136: invokevirtual 683	java/io/StringWriter:toString	()Ljava/lang/String;
    //   139: aload_3
    //   140: invokespecial 686	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   143: putfield 536	com/squareup/picasso/BitmapHunter:exception	Ljava/lang/Exception;
    //   146: aload_0
    //   147: getfield 84	com/squareup/picasso/BitmapHunter:dispatcher	Lcom/squareup/picasso/Dispatcher;
    //   150: aload_0
    //   151: invokevirtual 661	com/squareup/picasso/Dispatcher:dispatchFailed	(Lcom/squareup/picasso/BitmapHunter;)V
    //   154: goto +17 -> 171
    //   157: astore_3
    //   158: aload_0
    //   159: aload_3
    //   160: putfield 536	com/squareup/picasso/BitmapHunter:exception	Ljava/lang/Exception;
    //   163: aload_0
    //   164: getfield 84	com/squareup/picasso/BitmapHunter:dispatcher	Lcom/squareup/picasso/Dispatcher;
    //   167: aload_0
    //   168: invokevirtual 689	com/squareup/picasso/Dispatcher:dispatchRetry	(Lcom/squareup/picasso/BitmapHunter;)V
    //   171: invokestatic 469	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   174: ldc_w 691
    //   177: invokevirtual 475	java/lang/Thread:setName	(Ljava/lang/String;)V
    //   180: return
    //   181: astore_3
    //   182: aload_3
    //   183: getfield 694	com/squareup/picasso/Main:i	I
    //   186: invokestatic 697	com/squareup/picasso/NetworkPolicy:equals	(I)Z
    //   189: istore_2
    //   190: iload_2
    //   191: ifeq +15 -> 206
    //   194: aload_3
    //   195: getfield 700	com/squareup/picasso/Main:b	I
    //   198: istore_1
    //   199: iload_1
    //   200: sipush 504
    //   203: if_icmpeq +8 -> 211
    //   206: aload_0
    //   207: aload_3
    //   208: putfield 536	com/squareup/picasso/BitmapHunter:exception	Ljava/lang/Exception;
    //   211: aload_0
    //   212: getfield 84	com/squareup/picasso/BitmapHunter:dispatcher	Lcom/squareup/picasso/Dispatcher;
    //   215: aload_0
    //   216: invokevirtual 661	com/squareup/picasso/Dispatcher:dispatchFailed	(Lcom/squareup/picasso/BitmapHunter;)V
    //   219: goto -48 -> 171
    //   222: invokestatic 469	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   225: ldc_w 691
    //   228: invokevirtual 475	java/lang/Thread:setName	(Ljava/lang/String;)V
    //   231: goto +3 -> 234
    //   234: aload_3
    //   235: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	236	0	this	BitmapHunter
    //   198	6	1	i	int
    //   16	175	2	bool	boolean
    //   4	68	3	localObject	Object
    //   79	1	3	localThrowable	Throwable
    //   83	3	3	localException	Exception
    //   100	40	3	localOutOfMemoryError	OutOfMemoryError
    //   157	3	3	localIOException	IOException
    //   181	54	3	localMain	Main
    //   108	27	4	localStringWriter	java.io.StringWriter
    // Exception table:
    //   from	to	target	type
    //   0	5	79	java/lang/Throwable
    //   5	9	79	java/lang/Throwable
    //   9	17	79	java/lang/Throwable
    //   21	34	79	java/lang/Throwable
    //   34	39	79	java/lang/Throwable
    //   39	49	79	java/lang/Throwable
    //   58	63	79	java/lang/Throwable
    //   71	76	79	java/lang/Throwable
    //   84	97	79	java/lang/Throwable
    //   101	154	79	java/lang/Throwable
    //   158	171	79	java/lang/Throwable
    //   182	190	79	java/lang/Throwable
    //   194	199	79	java/lang/Throwable
    //   206	211	79	java/lang/Throwable
    //   211	219	79	java/lang/Throwable
    //   5	9	83	java/lang/Exception
    //   21	34	83	java/lang/Exception
    //   34	39	83	java/lang/Exception
    //   58	63	83	java/lang/Exception
    //   71	76	83	java/lang/Exception
    //   5	9	100	java/lang/OutOfMemoryError
    //   21	34	100	java/lang/OutOfMemoryError
    //   34	39	100	java/lang/OutOfMemoryError
    //   58	63	100	java/lang/OutOfMemoryError
    //   71	76	100	java/lang/OutOfMemoryError
    //   5	9	157	java/io/IOException
    //   21	34	157	java/io/IOException
    //   34	39	157	java/io/IOException
    //   58	63	157	java/io/IOException
    //   71	76	157	java/io/IOException
    //   5	9	181	com/squareup/picasso/Main
    //   21	34	181	com/squareup/picasso/Main
    //   34	39	181	com/squareup/picasso/Main
    //   58	63	181	com/squareup/picasso/Main
    //   71	76	181	com/squareup/picasso/Main
  }
  
  boolean shouldRetry(boolean paramBoolean, NetworkInfo paramNetworkInfo)
  {
    int i;
    if (retryCount > 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0) {
      return false;
    }
    retryCount -= 1;
    return requestHandler.shouldRetry(paramBoolean, paramNetworkInfo);
  }
  
  boolean supportsReplay()
  {
    return requestHandler.supportsReplay();
  }
}
