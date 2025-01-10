package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import android.util.TypedValue;
import java.io.File;

public class ContextCompat
{
  private static final Object cache = new Object();
  private static TypedValue mTypedValue;
  
  public static int checkSelfPermission(Context paramContext, String paramString)
  {
    if (paramString != null) {
      return paramContext.checkPermission(paramString, Process.myPid(), Process.myUid());
    }
    throw new IllegalArgumentException("permission is null");
  }
  
  public static int getColor(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return paramContext.getColor(paramInt);
    }
    return paramContext.getResources().getColor(paramInt);
  }
  
  public static ColorStateList getColorStateList(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return paramContext.getColorStateList(paramInt);
    }
    return paramContext.getResources().getColorStateList(paramInt);
  }
  
  /* Error */
  public static android.graphics.drawable.Drawable getDrawable(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: getstatic 48	android/os/Build$VERSION:SDK_INT	I
    //   3: istore_2
    //   4: iload_2
    //   5: bipush 21
    //   7: if_icmplt +9 -> 16
    //   10: aload_0
    //   11: iload_1
    //   12: invokevirtual 71	android/content/Context:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   15: areturn
    //   16: iload_2
    //   17: bipush 16
    //   19: if_icmplt +12 -> 31
    //   22: aload_0
    //   23: invokevirtual 55	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   26: iload_1
    //   27: invokevirtual 72	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   30: areturn
    //   31: getstatic 15	android/support/v4/content/ContextCompat:cache	Ljava/lang/Object;
    //   34: astore_3
    //   35: aload_3
    //   36: monitorenter
    //   37: getstatic 74	android/support/v4/content/ContextCompat:mTypedValue	Landroid/util/TypedValue;
    //   40: ifnonnull +13 -> 53
    //   43: new 76	android/util/TypedValue
    //   46: dup
    //   47: invokespecial 77	android/util/TypedValue:<init>	()V
    //   50: putstatic 74	android/support/v4/content/ContextCompat:mTypedValue	Landroid/util/TypedValue;
    //   53: aload_0
    //   54: invokevirtual 55	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   57: iload_1
    //   58: getstatic 74	android/support/v4/content/ContextCompat:mTypedValue	Landroid/util/TypedValue;
    //   61: iconst_1
    //   62: invokevirtual 81	android/content/res/Resources:getValue	(ILandroid/util/TypedValue;Z)V
    //   65: getstatic 74	android/support/v4/content/ContextCompat:mTypedValue	Landroid/util/TypedValue;
    //   68: getfield 84	android/util/TypedValue:resourceId	I
    //   71: istore_1
    //   72: aload_3
    //   73: monitorexit
    //   74: aload_0
    //   75: invokevirtual 55	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   78: iload_1
    //   79: invokevirtual 72	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   82: areturn
    //   83: astore_0
    //   84: aload_3
    //   85: monitorexit
    //   86: aload_0
    //   87: athrow
    //   88: astore_0
    //   89: goto -5 -> 84
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	paramContext	Context
    //   0	92	1	paramInt	int
    //   3	17	2	i	int
    //   34	51	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   37	53	83	java/lang/Throwable
    //   53	72	83	java/lang/Throwable
    //   72	74	88	java/lang/Throwable
    //   84	86	88	java/lang/Throwable
  }
  
  public static File[] getExternalCacheDirs(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return paramContext.getExternalCacheDirs();
    }
    return new File[] { paramContext.getExternalCacheDir() };
  }
  
  public static File[] getExternalFilesDirs(Context paramContext, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return paramContext.getExternalFilesDirs(paramString);
    }
    return new File[] { paramContext.getExternalFilesDir(paramString) };
  }
  
  public static boolean startActivities(Context paramContext, Intent[] paramArrayOfIntent, Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      paramContext.startActivities(paramArrayOfIntent, paramBundle);
    } else {
      paramContext.startActivities(paramArrayOfIntent);
    }
    return true;
  }
}
