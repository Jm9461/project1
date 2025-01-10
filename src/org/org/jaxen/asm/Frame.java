package org.org.jaxen.asm;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.support.v4.content.view.h;
import android.support.v4.content.view.x;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import org.org.jaxen.ui.Label;
import org.org.jaxen.util.f;

class Frame
  extends e
{
  private static final Method a;
  private static final Constructor c;
  private static final Method d;
  private static final Class g;
  
  /* Error */
  static
  {
    // Byte code:
    //   0: ldc 19
    //   2: invokestatic 25	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   5: astore_3
    //   6: aload_3
    //   7: astore_0
    //   8: aload_3
    //   9: iconst_0
    //   10: anewarray 21	java/lang/Class
    //   13: invokevirtual 29	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   16: astore_1
    //   17: getstatic 34	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   20: astore_2
    //   21: getstatic 34	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   24: astore 4
    //   26: getstatic 37	java/lang/Boolean:TYPE	Ljava/lang/Class;
    //   29: astore 5
    //   31: aload_3
    //   32: ldc 39
    //   34: iconst_5
    //   35: anewarray 21	java/lang/Class
    //   38: dup
    //   39: iconst_0
    //   40: ldc 41
    //   42: aastore
    //   43: dup
    //   44: iconst_1
    //   45: aload_2
    //   46: aastore
    //   47: dup
    //   48: iconst_2
    //   49: ldc 43
    //   51: aastore
    //   52: dup
    //   53: iconst_3
    //   54: aload 4
    //   56: aastore
    //   57: dup
    //   58: iconst_4
    //   59: aload 5
    //   61: aastore
    //   62: invokevirtual 47	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   65: astore_2
    //   66: aload_3
    //   67: iconst_1
    //   68: invokestatic 53	java/lang/reflect/Array:newInstance	(Ljava/lang/Class;I)Ljava/lang/Object;
    //   71: astore_3
    //   72: aload_3
    //   73: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   76: astore_3
    //   77: ldc 61
    //   79: ldc 63
    //   81: iconst_1
    //   82: anewarray 21	java/lang/Class
    //   85: dup
    //   86: iconst_0
    //   87: aload_3
    //   88: aastore
    //   89: invokevirtual 47	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   92: astore_3
    //   93: goto +57 -> 150
    //   96: astore_0
    //   97: goto +28 -> 125
    //   100: astore_0
    //   101: goto +24 -> 125
    //   104: astore_0
    //   105: goto +20 -> 125
    //   108: astore_0
    //   109: goto +16 -> 125
    //   112: astore_0
    //   113: goto +12 -> 125
    //   116: astore_0
    //   117: goto +8 -> 125
    //   120: astore_0
    //   121: goto +4 -> 125
    //   124: astore_0
    //   125: ldc 65
    //   127: aload_0
    //   128: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   131: invokevirtual 69	java/lang/Class:getName	()Ljava/lang/String;
    //   134: aload_0
    //   135: checkcast 71	java/lang/Throwable
    //   138: invokestatic 77	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   141: pop
    //   142: aconst_null
    //   143: astore_0
    //   144: aconst_null
    //   145: astore_1
    //   146: aconst_null
    //   147: astore_2
    //   148: aconst_null
    //   149: astore_3
    //   150: aload_1
    //   151: putstatic 79	org/org/jaxen/asm/Frame:c	Ljava/lang/reflect/Constructor;
    //   154: aload_0
    //   155: putstatic 81	org/org/jaxen/asm/Frame:g	Ljava/lang/Class;
    //   158: aload_2
    //   159: putstatic 83	org/org/jaxen/asm/Frame:d	Ljava/lang/reflect/Method;
    //   162: aload_3
    //   163: putstatic 85	org/org/jaxen/asm/Frame:a	Ljava/lang/reflect/Method;
    //   166: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   7	1	0	localObject1	Object
    //   96	1	0	localNoSuchMethodException1	NoSuchMethodException
    //   100	1	0	localClassNotFoundException1	ClassNotFoundException
    //   104	1	0	localNoSuchMethodException2	NoSuchMethodException
    //   108	1	0	localClassNotFoundException2	ClassNotFoundException
    //   112	1	0	localNoSuchMethodException3	NoSuchMethodException
    //   116	1	0	localClassNotFoundException3	ClassNotFoundException
    //   120	1	0	localNoSuchMethodException4	NoSuchMethodException
    //   124	11	0	localClassNotFoundException4	ClassNotFoundException
    //   143	12	0	localObject2	Object
    //   16	135	1	localConstructor	Constructor
    //   20	139	2	localObject3	Object
    //   5	158	3	localObject4	Object
    //   24	31	4	localClass1	Class
    //   29	31	5	localClass2	Class
    // Exception table:
    //   from	to	target	type
    //   66	72	96	java/lang/NoSuchMethodException
    //   72	77	96	java/lang/NoSuchMethodException
    //   77	93	96	java/lang/NoSuchMethodException
    //   66	72	100	java/lang/ClassNotFoundException
    //   72	77	100	java/lang/ClassNotFoundException
    //   77	93	100	java/lang/ClassNotFoundException
    //   31	66	104	java/lang/NoSuchMethodException
    //   31	66	108	java/lang/ClassNotFoundException
    //   8	17	112	java/lang/NoSuchMethodException
    //   8	17	116	java/lang/ClassNotFoundException
    //   0	6	120	java/lang/NoSuchMethodException
    //   0	6	124	java/lang/ClassNotFoundException
  }
  
  Frame() {}
  
  private static boolean a(Object paramObject, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    Method localMethod = d;
    try
    {
      paramObject = localMethod.invoke(paramObject, new Object[] { paramByteBuffer, Integer.valueOf(paramInt1), null, Integer.valueOf(paramInt2), Boolean.valueOf(paramBoolean) });
      paramObject = (Boolean)paramObject;
      paramBoolean = paramObject.booleanValue();
      return paramBoolean;
    }
    catch (InvocationTargetException paramObject) {}catch (IllegalAccessException paramObject) {}
    throw new RuntimeException(paramObject);
  }
  
  private static Typeface get(Object paramObject)
  {
    Object localObject = g;
    try
    {
      localObject = Array.newInstance((Class)localObject, 1);
      Array.set(localObject, 0, paramObject);
      paramObject = a;
      paramObject = paramObject.invoke(null, new Object[] { localObject });
      return (Typeface)paramObject;
    }
    catch (InvocationTargetException paramObject) {}catch (IllegalAccessException paramObject) {}
    throw new RuntimeException(paramObject);
  }
  
  private static Object get()
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
  
  public static boolean set()
  {
    if (d == null) {
      Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
    }
    return d != null;
  }
  
  public Typeface a(Context paramContext, CancellationSignal paramCancellationSignal, Label[] paramArrayOfLabel, int paramInt)
  {
    Object localObject = get();
    f localF = new f();
    int j = paramArrayOfLabel.length;
    int i = 0;
    while (i < j)
    {
      Label localLabel = paramArrayOfLabel[i];
      Uri localUri = localLabel.getValue();
      ByteBuffer localByteBuffer2 = (ByteBuffer)localF.get(localUri);
      ByteBuffer localByteBuffer1 = localByteBuffer2;
      if (localByteBuffer2 == null)
      {
        localByteBuffer2 = a.read(paramContext, paramCancellationSignal, localUri);
        localByteBuffer1 = localByteBuffer2;
        localF.put(localUri, localByteBuffer2);
      }
      if (!a(localObject, localByteBuffer1, localLabel.d(), localLabel.b(), localLabel.a())) {
        return null;
      }
      i += 1;
    }
    return Typeface.create(get(localObject), paramInt);
  }
  
  public Typeface a(Context paramContext, x paramX, Resources paramResources, int paramInt)
  {
    Object localObject1 = get();
    paramX = paramX.a();
    int i = paramX.length;
    paramInt = 0;
    while (paramInt < i)
    {
      Object localObject2 = paramX[paramInt];
      ByteBuffer localByteBuffer = a.a(paramContext, paramResources, localObject2.e());
      if (localByteBuffer == null) {
        return null;
      }
      if (!a(localObject1, localByteBuffer, localObject2.g(), localObject2.b(), localObject2.c())) {
        return null;
      }
      paramInt += 1;
    }
    return get(localObject1);
  }
}
