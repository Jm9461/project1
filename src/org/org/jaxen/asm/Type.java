package org.org.jaxen.asm;

import a.b.g.g.g;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import android.os.Handler;
import android.support.v4.content.view.f;
import android.support.v4.content.view.x;
import org.org.jaxen.ui.b;
import org.org.jaxen.util.LruCache;

public class Type
{
  private static final e a;
  private static final g<String, Typeface> c = new LruCache(16);
  
  static
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 28) {
      a = new SystemProperty();
    } else if (i >= 26) {
      a = new ClassWriter();
    } else if ((i >= 24) && (Frame.set())) {
      a = new Frame();
    } else if (Build.VERSION.SDK_INT >= 21) {
      a = new o();
    } else {
      a = new e();
    }
  }
  
  public static Typeface a(Context paramContext, Resources paramResources, int paramInt1, String paramString, int paramInt2)
  {
    paramContext = a.a(paramContext, paramResources, paramInt1, paramString, paramInt2);
    if (paramContext != null)
    {
      paramResources = create(paramResources, paramInt1, paramInt2);
      c.put(paramResources, paramContext);
    }
    return paramContext;
  }
  
  public static Typeface a(Context paramContext, CancellationSignal paramCancellationSignal, org.org.jaxen.ui.Label[] paramArrayOfLabel, int paramInt)
  {
    return a.a(paramContext, paramCancellationSignal, paramArrayOfLabel, paramInt);
  }
  
  public static Typeface a(Context paramContext, android.support.v4.content.view.Object paramObject, Resources paramResources, int paramInt1, int paramInt2, android.support.v4.content.view.Label paramLabel, Handler paramHandler, boolean paramBoolean)
  {
    if ((paramObject instanceof f))
    {
      paramObject = (f)paramObject;
      boolean bool;
      if (paramBoolean ? paramObject.d() == 0 : paramLabel == null) {
        bool = true;
      } else {
        bool = false;
      }
      int i;
      if (paramBoolean) {
        i = paramObject.e();
      } else {
        i = -1;
      }
      paramObject = b.a(paramContext, paramObject.c(), paramLabel, paramHandler, bool, i, paramInt2);
    }
    else
    {
      Typeface localTypeface = a.a(paramContext, (x)paramObject, paramResources, paramInt2);
      paramContext = localTypeface;
      paramObject = paramContext;
      if (paramLabel != null) {
        if (localTypeface != null)
        {
          paramLabel.a(localTypeface, paramHandler);
          paramObject = paramContext;
        }
        else
        {
          paramLabel.a(-3, paramHandler);
          paramObject = paramContext;
        }
      }
    }
    if (paramObject != null) {
      c.put(create(paramResources, paramInt1, paramInt2), paramObject);
    }
    return paramObject;
  }
  
  private static String create(Resources paramResources, int paramInt1, int paramInt2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramResources.getResourcePackageName(paramInt1));
    localStringBuilder.append("-");
    localStringBuilder.append(paramInt1);
    localStringBuilder.append("-");
    localStringBuilder.append(paramInt2);
    return localStringBuilder.toString();
  }
  
  public static Typeface get(Resources paramResources, int paramInt1, int paramInt2)
  {
    return (Typeface)c.get(create(paramResources, paramInt1, paramInt2));
  }
}
