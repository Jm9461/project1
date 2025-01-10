package com.squareup.picasso;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import android.provider.Settings.Global;
import android.provider.Settings.System;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import okio.BufferedSource;
import okio.ByteString;

final class Utils
{
  static final StringBuilder MAIN_THREAD_KEY_BUILDER = new StringBuilder();
  private static final ByteString TARGET_PATH = ByteString.encodeUtf8("RIFF");
  private static final ByteString TARGET_SCHEME = ByteString.encodeUtf8("WEBP");
  
  static long calculateDiskCacheSize(File paramFile)
  {
    long l2 = 5242880L;
    long l1;
    try
    {
      paramFile = new StatFs(paramFile.getAbsolutePath());
      int i;
      if (Build.VERSION.SDK_INT < 18)
      {
        i = paramFile.getBlockCount();
        l1 = i;
      }
      else
      {
        l1 = paramFile.getBlockCountLong();
      }
      if (Build.VERSION.SDK_INT < 18)
      {
        i = paramFile.getBlockSize();
        l2 = i;
      }
      else
      {
        long l3 = paramFile.getBlockSizeLong();
        l2 = l3;
      }
      l1 = l1 * l2 / 50L;
    }
    catch (IllegalArgumentException paramFile)
    {
      l1 = l2;
    }
    return Math.max(Math.min(l1, 52428800L), 5242880L);
  }
  
  static int calculateMemoryCacheSize(Context paramContext)
  {
    ActivityManager localActivityManager = (ActivityManager)getService(paramContext, "activity");
    int i;
    if ((getApplicationInfoflags & 0x100000) != 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      i = localActivityManager.getLargeMemoryClass();
    } else {
      i = localActivityManager.getMemoryClass();
    }
    return (int)(i * 1048576L / 7L);
  }
  
  static void checkMain()
  {
    if (isMain()) {
      return;
    }
    throw new IllegalStateException("Method call should happen from the main thread.");
  }
  
  static Object checkNotNull(Object paramObject, String paramString)
  {
    if (paramObject != null) {
      return paramObject;
    }
    throw new NullPointerException(paramString);
  }
  
  static File createDefaultCacheDir(Context paramContext)
  {
    paramContext = new File(paramContext.getApplicationContext().getCacheDir(), "picasso-cache");
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    return paramContext;
  }
  
  static String createKey(Request paramRequest)
  {
    paramRequest = createKey(paramRequest, MAIN_THREAD_KEY_BUILDER);
    MAIN_THREAD_KEY_BUILDER.setLength(0);
    return paramRequest;
  }
  
  static String createKey(Request paramRequest, StringBuilder paramStringBuilder)
  {
    Object localObject = stableKey;
    if (localObject != null)
    {
      paramStringBuilder.ensureCapacity(((String)localObject).length() + 50);
      paramStringBuilder.append(stableKey);
    }
    else
    {
      localObject = uri;
      if (localObject != null)
      {
        localObject = ((Uri)localObject).toString();
        paramStringBuilder.ensureCapacity(((String)localObject).length() + 50);
        paramStringBuilder.append((String)localObject);
      }
      else
      {
        paramStringBuilder.ensureCapacity(50);
        paramStringBuilder.append(resourceId);
      }
    }
    paramStringBuilder.append('\n');
    if (rotationDegrees != 0.0F)
    {
      paramStringBuilder.append("rotation:");
      paramStringBuilder.append(rotationDegrees);
      if (hasRotationPivot)
      {
        paramStringBuilder.append('@');
        paramStringBuilder.append(rotationPivotX);
        paramStringBuilder.append('x');
        paramStringBuilder.append(rotationPivotY);
      }
      paramStringBuilder.append('\n');
    }
    if (paramRequest.hasSize())
    {
      paramStringBuilder.append("resize:");
      paramStringBuilder.append(targetWidth);
      paramStringBuilder.append('x');
      paramStringBuilder.append(targetHeight);
      paramStringBuilder.append('\n');
    }
    if (centerCrop)
    {
      paramStringBuilder.append("centerCrop:");
      paramStringBuilder.append(data);
      paramStringBuilder.append('\n');
    }
    else if (centerInside)
    {
      paramStringBuilder.append("centerInside");
      paramStringBuilder.append('\n');
    }
    localObject = transformations;
    if (localObject != null)
    {
      int i = 0;
      int j = ((List)localObject).size();
      while (i < j)
      {
        paramStringBuilder.append(((Transformation)transformations.get(i)).key());
        paramStringBuilder.append('\n');
        i += 1;
      }
    }
    return paramStringBuilder.toString();
  }
  
  static void flushStackLocalLeaks(Looper paramLooper)
  {
    paramLooper = new d0.a(paramLooper);
    paramLooper.sendMessageDelayed(paramLooper.obtainMessage(), 1000L);
  }
  
  static int getBitmapBytes(Bitmap paramBitmap)
  {
    int i;
    if (Build.VERSION.SDK_INT >= 19) {
      i = paramBitmap.getAllocationByteCount();
    } else {
      i = paramBitmap.getByteCount();
    }
    if (i >= 0) {
      return i;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Negative size: ");
    localStringBuilder.append(paramBitmap);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  static String getLogIdsForHunter(BitmapHunter paramBitmapHunter)
  {
    return getLogIdsForHunter(paramBitmapHunter, "");
  }
  
  static String getLogIdsForHunter(BitmapHunter paramBitmapHunter, String paramString)
  {
    paramString = new StringBuilder(paramString);
    Action localAction = paramBitmapHunter.getAction();
    if (localAction != null) {
      paramString.append(request.logId());
    }
    paramBitmapHunter = paramBitmapHunter.getActions();
    if (paramBitmapHunter != null)
    {
      int i = 0;
      int j = paramBitmapHunter.size();
      while (i < j)
      {
        if ((i > 0) || (localAction != null)) {
          paramString.append(", ");
        }
        paramString.append(getrequest.logId());
        i += 1;
      }
    }
    return paramString.toString();
  }
  
  static int getResourceId(Resources paramResources, Request paramRequest)
  {
    if (resourceId == 0)
    {
      Object localObject = uri;
      if (localObject != null)
      {
        localObject = ((Uri)localObject).getAuthority();
        if (localObject != null)
        {
          List localList = uri.getPathSegments();
          if ((localList != null) && (!localList.isEmpty()))
          {
            if (localList.size() == 1) {
              try
              {
                paramResources = localList.get(0);
                paramResources = (String)paramResources;
                int i = Integer.parseInt(paramResources);
                return i;
              }
              catch (NumberFormatException paramResources)
              {
                paramResources = new StringBuilder();
                paramResources.append("Last path segment is not a resource ID: ");
                paramResources.append(uri);
                throw new FileNotFoundException(paramResources.toString());
              }
            }
            if (localList.size() == 2)
            {
              paramRequest = (String)localList.get(0);
              return paramResources.getIdentifier((String)localList.get(1), paramRequest, (String)localObject);
            }
            paramResources = new StringBuilder();
            paramResources.append("More than two path segments: ");
            paramResources.append(uri);
            throw new FileNotFoundException(paramResources.toString());
          }
          paramResources = new StringBuilder();
          paramResources.append("No path segments: ");
          paramResources.append(uri);
          throw new FileNotFoundException(paramResources.toString());
        }
        paramResources = new StringBuilder();
        paramResources.append("No package provided: ");
        paramResources.append(uri);
        throw new FileNotFoundException(paramResources.toString());
      }
    }
    return resourceId;
  }
  
  static Resources getResources(Context paramContext, Request paramRequest)
  {
    if (resourceId == 0)
    {
      Object localObject = uri;
      if (localObject != null)
      {
        localObject = ((Uri)localObject).getAuthority();
        if (localObject != null) {
          try
          {
            paramContext = paramContext.getPackageManager().getResourcesForApplication((String)localObject);
            return paramContext;
          }
          catch (PackageManager.NameNotFoundException paramContext)
          {
            paramContext = new StringBuilder();
            paramContext.append("Unable to obtain resources for package: ");
            paramContext.append(uri);
            throw new FileNotFoundException(paramContext.toString());
          }
        }
        paramContext = new StringBuilder();
        paramContext.append("No package provided: ");
        paramContext.append(uri);
        throw new FileNotFoundException(paramContext.toString());
      }
    }
    return paramContext.getResources();
  }
  
  static Object getService(Context paramContext, String paramString)
  {
    return paramContext.getSystemService(paramString);
  }
  
  static boolean hasPermission(Context paramContext, String paramString)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }
  
  static boolean isAirplaneModeOn(Context paramContext)
  {
    paramContext = paramContext.getContentResolver();
    if (Build.VERSION.SDK_INT < 17) {}
    try
    {
      i = Settings.System.getInt(paramContext, "airplane_mode_on", 0);
      if (i == 0) {
        break label47;
      }
      return true;
    }
    catch (SecurityException paramContext)
    {
      int i;
      return false;
    }
    catch (NullPointerException paramContext) {}
    i = Settings.Global.getInt(paramContext, "airplane_mode_on", 0);
    return i != 0;
  }
  
  static boolean isMain()
  {
    return Looper.getMainLooper().getThread() == Thread.currentThread();
  }
  
  static boolean isWebPFile(BufferedSource paramBufferedSource)
  {
    return (paramBufferedSource.read(0L, TARGET_PATH)) && (paramBufferedSource.read(8L, TARGET_SCHEME));
  }
  
  static void log(String paramString1, String paramString2, String paramString3)
  {
    log(paramString1, paramString2, paramString3, "");
  }
  
  static void log(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Log.d("Picasso", String.format("%1$-11s %2$-12s %3$s %4$s", new Object[] { paramString1, paramString2, paramString3, paramString4 }));
  }
}
