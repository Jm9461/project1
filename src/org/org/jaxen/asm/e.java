package org.org.jaxen.asm;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.support.v4.content.view.h;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.org.jaxen.ui.Label;

class e
{
  e() {}
  
  private h a(android.support.v4.content.view.x paramX, int paramInt)
  {
    return (h)a(paramX.a(), paramInt, new g(this));
  }
  
  private static Object a(Object[] paramArrayOfObject, int paramInt, x paramX)
  {
    int i;
    if ((paramInt & 0x1) == 0) {
      i = 400;
    } else {
      i = 700;
    }
    int i1;
    if ((paramInt & 0x2) != 0) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    int n = paramArrayOfObject.length;
    int j = Integer.MAX_VALUE;
    Object localObject1 = null;
    paramInt = 0;
    while (paramInt < n)
    {
      Object localObject2 = paramArrayOfObject[paramInt];
      int m = Math.abs(paramX.c(localObject2) - i);
      int k;
      if (paramX.a(localObject2) == i1) {
        k = 0;
      } else {
        k = 1;
      }
      m = m * 2 + k;
      if (localObject1 != null)
      {
        k = j;
        if (j <= m) {}
      }
      else
      {
        localObject1 = localObject2;
        k = m;
      }
      paramInt += 1;
      j = k;
    }
    return localObject1;
  }
  
  public Typeface a(Context paramContext, Resources paramResources, int paramInt1, String paramString, int paramInt2)
  {
    paramContext = a.a(paramContext);
    if (paramContext == null) {
      return null;
    }
    try
    {
      boolean bool = a.b(paramContext, paramResources, paramInt1);
      if (!bool)
      {
        paramContext.delete();
        return null;
      }
      paramResources = Typeface.createFromFile(paramContext.getPath());
      paramContext.delete();
      return paramResources;
    }
    catch (Throwable paramResources)
    {
      paramContext.delete();
      throw paramResources;
    }
    catch (RuntimeException paramResources)
    {
      paramContext.delete();
    }
    return null;
  }
  
  public Typeface a(Context paramContext, CancellationSignal paramCancellationSignal, Label[] paramArrayOfLabel, int paramInt)
  {
    if (paramArrayOfLabel.length < 1) {
      return null;
    }
    Object localObject = a(paramArrayOfLabel, paramInt);
    paramCancellationSignal = null;
    paramArrayOfLabel = null;
    try
    {
      localObject = paramContext.getContentResolver().openInputStream(((Label)localObject).getValue());
      paramCancellationSignal = (CancellationSignal)localObject;
      paramArrayOfLabel = paramCancellationSignal;
      paramContext = a(paramContext, (InputStream)localObject);
      a.close((Closeable)localObject);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      a.close(paramArrayOfLabel);
      throw paramContext;
    }
    catch (IOException paramContext)
    {
      a.close(paramCancellationSignal);
    }
    return null;
  }
  
  public Typeface a(Context paramContext, android.support.v4.content.view.x paramX, Resources paramResources, int paramInt)
  {
    paramX = a(paramX, paramInt);
    if (paramX == null) {
      return null;
    }
    return Type.a(paramContext, paramResources, paramX.e(), paramX.a(), paramInt);
  }
  
  protected Typeface a(Context paramContext, InputStream paramInputStream)
  {
    paramContext = a.a(paramContext);
    if (paramContext == null) {
      return null;
    }
    try
    {
      boolean bool = a.a(paramContext, paramInputStream);
      if (!bool)
      {
        paramContext.delete();
        return null;
      }
      paramInputStream = Typeface.createFromFile(paramContext.getPath());
      paramContext.delete();
      return paramInputStream;
    }
    catch (Throwable paramInputStream)
    {
      paramContext.delete();
      throw paramInputStream;
    }
    catch (RuntimeException paramInputStream)
    {
      paramContext.delete();
    }
    return null;
  }
  
  protected Label a(Label[] paramArrayOfLabel, int paramInt)
  {
    return (Label)a(paramArrayOfLabel, paramInt, new d(this));
  }
}
