package com.mohamadamin.persianmaterialdatetimepicker;

import a.b.g.g.n;
import android.content.Context;
import android.graphics.Typeface;
import org.org.jaxen.util.f;

public class TypefaceHelper
{
  private static final n<String, Typeface> cache = new f();
  
  public static Typeface get(Context paramContext, String paramString)
  {
    f localF = cache;
    try
    {
      if (!cache.containsKey(paramString))
      {
        paramContext = Typeface.createFromAsset(paramContext.getAssets(), String.format("fonts/%s.ttf", new Object[] { paramString }));
        cache.put(paramString, paramContext);
        return paramContext;
      }
      paramContext = (Typeface)cache.get(paramString);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
}
