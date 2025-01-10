package apps.afollestad.materialdialogs.base;

import a.b.g.g.n;
import android.content.Context;
import android.graphics.Typeface;
import org.org.jaxen.util.f;

public class DeltaBaseCache
{
  private static final n<String, Typeface> table = new f();
  
  public static Typeface get(Context paramContext, String paramString)
  {
    f localF1 = table;
    try
    {
      boolean bool = table.containsKey(paramString);
      if (!bool) {
        try
        {
          paramContext = Typeface.createFromAsset(paramContext.getAssets(), String.format("fonts/%s", new Object[] { paramString }));
          f localF2 = table;
          localF2.put(paramString, paramContext);
          return paramContext;
        }
        catch (RuntimeException paramContext)
        {
          return null;
        }
      }
      paramContext = (Typeface)table.get(paramString);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
}
