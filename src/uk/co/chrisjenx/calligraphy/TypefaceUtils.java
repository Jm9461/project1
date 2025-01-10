package uk.co.chrisjenx.calligraphy;

import android.graphics.Typeface;
import java.util.HashMap;
import java.util.Map;

public final class TypefaceUtils
{
  private static final Map<String, Typeface> sCachedFonts = new HashMap();
  private static final Map<Typeface, CalligraphyTypefaceSpan> sCachedSpans = new HashMap();
  
  private TypefaceUtils() {}
  
  public static CalligraphyTypefaceSpan getSpan(Typeface paramTypeface)
  {
    if (paramTypeface == null) {
      return null;
    }
    Map localMap = sCachedSpans;
    try
    {
      if (!sCachedSpans.containsKey(paramTypeface))
      {
        CalligraphyTypefaceSpan localCalligraphyTypefaceSpan = new CalligraphyTypefaceSpan(paramTypeface);
        sCachedSpans.put(paramTypeface, localCalligraphyTypefaceSpan);
        return localCalligraphyTypefaceSpan;
      }
      paramTypeface = (CalligraphyTypefaceSpan)sCachedSpans.get(paramTypeface);
      return paramTypeface;
    }
    catch (Throwable paramTypeface)
    {
      throw paramTypeface;
    }
  }
  
  public static boolean isLoaded(Typeface paramTypeface)
  {
    return (paramTypeface != null) && (sCachedFonts.containsValue(paramTypeface));
  }
  
  /* Error */
  public static Typeface load(android.content.res.AssetManager paramAssetManager, String paramString)
  {
    // Byte code:
    //   0: getstatic 18	uk/co/chrisjenx/calligraphy/TypefaceUtils:sCachedFonts	Ljava/util/Map;
    //   3: astore_3
    //   4: aload_3
    //   5: monitorenter
    //   6: getstatic 18	uk/co/chrisjenx/calligraphy/TypefaceUtils:sCachedFonts	Ljava/util/Map;
    //   9: astore 4
    //   11: aload 4
    //   13: aload_1
    //   14: invokeinterface 32 2 0
    //   19: istore_2
    //   20: iload_2
    //   21: ifne +28 -> 49
    //   24: aload_0
    //   25: aload_1
    //   26: invokestatic 59	android/graphics/Typeface:createFromAsset	(Landroid/content/res/AssetManager;Ljava/lang/String;)Landroid/graphics/Typeface;
    //   29: astore_0
    //   30: getstatic 18	uk/co/chrisjenx/calligraphy/TypefaceUtils:sCachedFonts	Ljava/util/Map;
    //   33: astore 4
    //   35: aload 4
    //   37: aload_1
    //   38: aload_0
    //   39: invokeinterface 41 3 0
    //   44: pop
    //   45: aload_3
    //   46: monitorexit
    //   47: aload_0
    //   48: areturn
    //   49: getstatic 18	uk/co/chrisjenx/calligraphy/TypefaceUtils:sCachedFonts	Ljava/util/Map;
    //   52: aload_1
    //   53: invokeinterface 45 2 0
    //   58: checkcast 56	android/graphics/Typeface
    //   61: astore_0
    //   62: aload_3
    //   63: monitorexit
    //   64: aload_0
    //   65: areturn
    //   66: astore_0
    //   67: goto +63 -> 130
    //   70: astore_0
    //   71: new 61	java/lang/StringBuilder
    //   74: dup
    //   75: invokespecial 62	java/lang/StringBuilder:<init>	()V
    //   78: astore 4
    //   80: aload 4
    //   82: ldc 64
    //   84: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: pop
    //   88: aload 4
    //   90: aload_1
    //   91: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: pop
    //   95: aload 4
    //   97: ldc 70
    //   99: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: pop
    //   103: ldc 72
    //   105: aload 4
    //   107: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   110: aload_0
    //   111: invokestatic 82	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   114: pop
    //   115: getstatic 18	uk/co/chrisjenx/calligraphy/TypefaceUtils:sCachedFonts	Ljava/util/Map;
    //   118: aload_1
    //   119: aconst_null
    //   120: invokeinterface 41 3 0
    //   125: pop
    //   126: aload_3
    //   127: monitorexit
    //   128: aconst_null
    //   129: areturn
    //   130: aload_3
    //   131: monitorexit
    //   132: aload_0
    //   133: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	134	0	paramAssetManager	android.content.res.AssetManager
    //   0	134	1	paramString	String
    //   19	2	2	bool	boolean
    //   3	128	3	localMap	Map
    //   9	97	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   6	11	66	java/lang/Throwable
    //   11	20	66	java/lang/Throwable
    //   24	30	66	java/lang/Throwable
    //   35	45	66	java/lang/Throwable
    //   45	47	66	java/lang/Throwable
    //   49	64	66	java/lang/Throwable
    //   71	128	66	java/lang/Throwable
    //   130	132	66	java/lang/Throwable
    //   11	20	70	java/lang/Exception
    //   24	30	70	java/lang/Exception
    //   35	45	70	java/lang/Exception
  }
}
