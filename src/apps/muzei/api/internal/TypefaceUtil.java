package apps.muzei.api.internal;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;

public class TypefaceUtil
{
  private static final HashMap<String, Typeface> sCachedFonts = new HashMap();
  
  public static Typeface load(Context paramContext, String paramString, int paramInt)
  {
    if ((paramString != null) && (paramString.startsWith("asset:")))
    {
      HashMap localHashMap1 = sCachedFonts;
      try
      {
        HashMap localHashMap2 = sCachedFonts;
        boolean bool;
        return Typeface.create(paramString, paramInt);
      }
      catch (Throwable paramContext)
      {
        try
        {
          bool = localHashMap2.containsKey(paramString);
          if (!bool)
          {
            paramContext = paramContext.getAssets();
            paramContext = Typeface.createFromAsset(paramContext, paramString.substring("asset:".length()));
            localHashMap2 = sCachedFonts;
            localHashMap2.put(paramString, paramContext);
            return paramContext;
          }
          paramContext = (Typeface)sCachedFonts.get(paramString);
          return paramContext;
        }
        catch (Exception paramContext)
        {
          paramContext = Typeface.DEFAULT;
          return paramContext;
        }
        paramContext = paramContext;
        throw paramContext;
      }
    }
  }
}
