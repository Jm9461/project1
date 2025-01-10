package com.squareup.picasso;

import android.graphics.Bitmap.Config;
import android.net.Uri;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class Request
{
  private static final long TOO_LONG_LOG = TimeUnit.SECONDS.toNanos(5L);
  public final Bitmap.Config body;
  public final boolean centerCrop;
  public final boolean centerInside;
  public final boolean config;
  public final int data;
  public final boolean hasRotationPivot;
  int id;
  int networkPolicy;
  public final boolean onlyScaleDown;
  public final Picasso.Priority priority;
  public final int resourceId;
  public final float rotationDegrees;
  public final float rotationPivotX;
  public final float rotationPivotY;
  public final String stableKey;
  long started;
  public final int targetHeight;
  public final int targetWidth;
  public final List<c0> transformations;
  public final Uri uri;
  
  private Request(Uri paramUri, int paramInt1, String paramString, List paramList, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, int paramInt4, boolean paramBoolean3, float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean4, boolean paramBoolean5, Bitmap.Config paramConfig, Picasso.Priority paramPriority)
  {
    uri = paramUri;
    resourceId = paramInt1;
    stableKey = paramString;
    if (paramList == null) {
      transformations = null;
    } else {
      transformations = Collections.unmodifiableList(paramList);
    }
    targetWidth = paramInt2;
    targetHeight = paramInt3;
    centerCrop = paramBoolean1;
    centerInside = paramBoolean2;
    data = paramInt4;
    onlyScaleDown = paramBoolean3;
    rotationDegrees = paramFloat1;
    rotationPivotX = paramFloat2;
    rotationPivotY = paramFloat3;
    hasRotationPivot = paramBoolean4;
    config = paramBoolean5;
    body = paramConfig;
    priority = paramPriority;
  }
  
  String getName()
  {
    Uri localUri = uri;
    if (localUri != null) {
      return String.valueOf(localUri.getPath());
    }
    return Integer.toHexString(resourceId);
  }
  
  boolean hasCustomTransformations()
  {
    return transformations != null;
  }
  
  public boolean hasSize()
  {
    return (targetWidth != 0) || (targetHeight != 0);
  }
  
  String logId()
  {
    long l = System.nanoTime() - started;
    if (l > TOO_LONG_LOG)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(plainId());
      localStringBuilder.append('+');
      localStringBuilder.append(TimeUnit.NANOSECONDS.toSeconds(l));
      localStringBuilder.append('s');
      return localStringBuilder.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(plainId());
    localStringBuilder.append('+');
    localStringBuilder.append(TimeUnit.NANOSECONDS.toMillis(l));
    localStringBuilder.append("ms");
    return localStringBuilder.toString();
  }
  
  boolean needsMatrixTransform()
  {
    return (hasSize()) || (rotationDegrees != 0.0F);
  }
  
  boolean needsTransformation()
  {
    return (needsMatrixTransform()) || (hasCustomTransformations());
  }
  
  String plainId()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[R");
    localStringBuilder.append(id);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Request{");
    int i = resourceId;
    if (i > 0) {
      localStringBuilder.append(i);
    } else {
      localStringBuilder.append(uri);
    }
    Object localObject = transformations;
    if ((localObject != null) && (!((List)localObject).isEmpty()))
    {
      localObject = transformations.iterator();
      while (((Iterator)localObject).hasNext())
      {
        Transformation localTransformation = (Transformation)((Iterator)localObject).next();
        localStringBuilder.append(' ');
        localStringBuilder.append(localTransformation.key());
      }
    }
    if (stableKey != null)
    {
      localStringBuilder.append(" stableKey(");
      localStringBuilder.append(stableKey);
      localStringBuilder.append(')');
    }
    if (targetWidth > 0)
    {
      localStringBuilder.append(" resize(");
      localStringBuilder.append(targetWidth);
      localStringBuilder.append(',');
      localStringBuilder.append(targetHeight);
      localStringBuilder.append(')');
    }
    if (centerCrop) {
      localStringBuilder.append(" centerCrop");
    }
    if (centerInside) {
      localStringBuilder.append(" centerInside");
    }
    if (rotationDegrees != 0.0F)
    {
      localStringBuilder.append(" rotation(");
      localStringBuilder.append(rotationDegrees);
      if (hasRotationPivot)
      {
        localStringBuilder.append(" @ ");
        localStringBuilder.append(rotationPivotX);
        localStringBuilder.append(',');
        localStringBuilder.append(rotationPivotY);
      }
      localStringBuilder.append(')');
    }
    if (config) {
      localStringBuilder.append(" purgeable");
    }
    if (body != null)
    {
      localStringBuilder.append(' ');
      localStringBuilder.append(body);
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
  
  public final class Builder
  {
    private float body;
    private boolean centerCrop;
    private boolean centerInside;
    private Bitmap.Config config;
    private boolean hasRotationPivot;
    private boolean headers;
    private Picasso.Priority priority;
    private int resourceId;
    private float rotationDegrees;
    private float rotationPivotX;
    private boolean rotationPivotY;
    private int source;
    private String stableKey;
    private int targetHeight;
    private int targetWidth;
    private List<c0> transformations;
    
    Builder(int paramInt, Bitmap.Config paramConfig)
    {
      resourceId = paramInt;
      config = paramConfig;
    }
    
    public Request build()
    {
      if ((centerInside) && (centerCrop)) {
        throw new IllegalStateException("Center crop and center inside can not be used together.");
      }
      if ((centerCrop) && (targetWidth == 0) && (targetHeight == 0)) {
        throw new IllegalStateException("Center crop requires calling resize with positive width and height.");
      }
      if ((centerInside) && (targetWidth == 0) && (targetHeight == 0)) {
        throw new IllegalStateException("Center inside requires calling resize with positive width and height.");
      }
      if (priority == null) {
        priority = Picasso.Priority.NORMAL;
      }
      return new Request(Request.this, resourceId, stableKey, transformations, targetWidth, targetHeight, centerCrop, centerInside, source, headers, body, rotationDegrees, rotationPivotX, rotationPivotY, hasRotationPivot, config, priority, null);
    }
    
    public Builder centerCrop(int paramInt)
    {
      if (!centerInside)
      {
        centerCrop = true;
        source = paramInt;
        return this;
      }
      throw new IllegalStateException("Center crop can not be used after calling centerInside");
    }
    
    boolean hasImage()
    {
      return (Request.this != null) || (resourceId != 0);
    }
    
    boolean hasSize()
    {
      return (targetWidth != 0) || (targetHeight != 0);
    }
    
    public Builder resize(int paramInt1, int paramInt2)
    {
      if (paramInt1 >= 0)
      {
        if (paramInt2 >= 0)
        {
          if ((paramInt2 == 0) && (paramInt1 == 0)) {
            throw new IllegalArgumentException("At least one dimension has to be positive number.");
          }
          targetWidth = paramInt1;
          targetHeight = paramInt2;
          return this;
        }
        throw new IllegalArgumentException("Height must be positive number or 0.");
      }
      throw new IllegalArgumentException("Width must be positive number or 0.");
    }
  }
}
