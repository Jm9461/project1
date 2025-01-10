package org.org.jaxen.asm;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.os.CancellationSignal;
import android.support.v4.content.view.x;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import org.org.jaxen.ui.Label;

public class ClassWriter
  extends o
{
  protected final Method a;
  protected final Method b;
  protected final Constructor c;
  protected final Method d;
  protected final Method e;
  protected final Method f;
  protected final Class type;
  
  public ClassWriter()
  {
    try
    {
      localObject4 = get();
      Object localObject1 = localObject4;
      localObject3 = getConstructor((Class)localObject4);
      localMethod1 = a((Class)localObject4);
      localMethod2 = find((Class)localObject4);
      localMethod3 = findGetListenerMethod((Class)localObject4);
      localMethod4 = getMethod((Class)localObject4);
      localObject4 = get((Class)localObject4);
    }
    catch (NoSuchMethodException localNoSuchMethodException) {}catch (ClassNotFoundException localClassNotFoundException) {}
    Object localObject3 = new StringBuilder();
    ((StringBuilder)localObject3).append("Unable to collect necessary methods for class ");
    ((StringBuilder)localObject3).append(localClassNotFoundException.getClass().getName());
    Log.e("TypefaceCompatApi26Impl", ((StringBuilder)localObject3).toString(), localClassNotFoundException);
    Object localObject4 = null;
    Object localObject2 = null;
    localObject3 = null;
    Method localMethod1 = null;
    Method localMethod2 = null;
    Method localMethod3 = null;
    Method localMethod4 = null;
    type = localObject2;
    c = ((Constructor)localObject3);
    b = localMethod1;
    d = localMethod2;
    e = localMethod3;
    f = localMethod4;
    a = ((Method)localObject4);
  }
  
  private Object a()
  {
    Object localObject = c;
    try
    {
      localObject = ((Constructor)localObject).newInstance(new Object[0]);
      return localObject;
    }
    catch (InvocationTargetException localInvocationTargetException) {}catch (InstantiationException localInstantiationException) {}catch (IllegalAccessException localIllegalAccessException) {}
    throw new RuntimeException(localIllegalAccessException);
  }
  
  private boolean a(Context paramContext, Object paramObject, String paramString, int paramInt1, int paramInt2, int paramInt3, FontVariationAxis[] paramArrayOfFontVariationAxis)
  {
    Method localMethod = b;
    try
    {
      paramContext = paramContext.getAssets();
      paramContext = localMethod.invoke(paramObject, new Object[] { paramContext, paramString, Integer.valueOf(0), Boolean.valueOf(false), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), paramArrayOfFontVariationAxis });
      paramContext = (Boolean)paramContext;
      boolean bool = paramContext.booleanValue();
      return bool;
    }
    catch (InvocationTargetException paramContext) {}catch (IllegalAccessException paramContext) {}
    throw new RuntimeException(paramContext);
  }
  
  private boolean a(Object paramObject)
  {
    Method localMethod = e;
    try
    {
      paramObject = localMethod.invoke(paramObject, new Object[0]);
      paramObject = (Boolean)paramObject;
      boolean bool = paramObject.booleanValue();
      return bool;
    }
    catch (InvocationTargetException paramObject) {}catch (IllegalAccessException paramObject) {}
    throw new RuntimeException(paramObject);
  }
  
  private boolean a(Object paramObject, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3)
  {
    Method localMethod = d;
    try
    {
      paramObject = localMethod.invoke(paramObject, new Object[] { paramByteBuffer, Integer.valueOf(paramInt1), null, Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) });
      paramObject = (Boolean)paramObject;
      boolean bool = paramObject.booleanValue();
      return bool;
    }
    catch (InvocationTargetException paramObject) {}catch (IllegalAccessException paramObject) {}
    throw new RuntimeException(paramObject);
  }
  
  private boolean b()
  {
    if (b == null) {
      Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
    }
    return b != null;
  }
  
  private void invoke(Object paramObject)
  {
    Method localMethod = f;
    try
    {
      localMethod.invoke(paramObject, new Object[0]);
      return;
    }
    catch (InvocationTargetException paramObject) {}catch (IllegalAccessException paramObject) {}
    throw new RuntimeException(paramObject);
  }
  
  public Typeface a(Context paramContext, Resources paramResources, int paramInt1, String paramString, int paramInt2)
  {
    if (!b()) {
      return super.a(paramContext, paramResources, paramInt1, paramString, paramInt2);
    }
    paramResources = a();
    if (!a(paramContext, paramResources, paramString, 0, -1, -1, null))
    {
      invoke(paramResources);
      return null;
    }
    if (!a(paramResources)) {
      return null;
    }
    return get(paramResources);
  }
  
  public Typeface a(Context paramContext, CancellationSignal paramCancellationSignal, Label[] paramArrayOfLabel, int paramInt)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public Typeface a(Context paramContext, x paramX, Resources paramResources, int paramInt)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  protected Method a(Class paramClass)
  {
    Class localClass1 = Integer.TYPE;
    Class localClass2 = Boolean.TYPE;
    Class localClass3 = Integer.TYPE;
    return paramClass.getMethod("addFontFromAssetManager", new Class[] { AssetManager.class, String.class, localClass1, localClass2, localClass3, localClass3, localClass3, [Landroid.graphics.fonts.FontVariationAxis.class });
  }
  
  protected Method find(Class paramClass)
  {
    Class localClass = Integer.TYPE;
    return paramClass.getMethod("addFontFromBuffer", new Class[] { ByteBuffer.class, localClass, [Landroid.graphics.fonts.FontVariationAxis.class, localClass, localClass });
  }
  
  protected Method findGetListenerMethod(Class paramClass)
  {
    return paramClass.getMethod("freeze", new Class[0]);
  }
  
  protected Typeface get(Object paramObject)
  {
    Object localObject = type;
    try
    {
      localObject = Array.newInstance((Class)localObject, 1);
      Array.set(localObject, 0, paramObject);
      paramObject = a;
      paramObject = paramObject.invoke(null, new Object[] { localObject, Integer.valueOf(-1), Integer.valueOf(-1) });
      return (Typeface)paramObject;
    }
    catch (InvocationTargetException paramObject) {}catch (IllegalAccessException paramObject) {}
    throw new RuntimeException(paramObject);
  }
  
  protected Class get()
  {
    return Class.forName("android.graphics.FontFamily");
  }
  
  protected Method get(Class paramClass)
  {
    paramClass = Array.newInstance(paramClass, 1).getClass();
    Class localClass = Integer.TYPE;
    paramClass = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", new Class[] { paramClass, localClass, localClass });
    paramClass.setAccessible(true);
    return paramClass;
  }
  
  protected Constructor getConstructor(Class paramClass)
  {
    return paramClass.getConstructor(new Class[0]);
  }
  
  protected Method getMethod(Class paramClass)
  {
    return paramClass.getMethod("abortCreation", new Class[0]);
  }
}
