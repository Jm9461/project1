package android.support.v4.content.view;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public final class Resources
{
  private static Typeface add(Context paramContext, android.content.res.Resources paramResources, TypedValue paramTypedValue, int paramInt1, int paramInt2, Label paramLabel, Handler paramHandler, boolean paramBoolean)
  {
    java.lang.Object localObject = string;
    if (localObject != null)
    {
      paramTypedValue = ((CharSequence)localObject).toString();
      if (!paramTypedValue.startsWith("res/"))
      {
        if (paramLabel != null)
        {
          paramLabel.a(-3, paramHandler);
          return null;
        }
      }
      else
      {
        localObject = org.org.jaxen.asm.Type.get(paramResources, paramInt1, paramInt2);
        if (localObject != null)
        {
          if (paramLabel == null) {
            break label385;
          }
          paramLabel.a((Typeface)localObject, paramHandler);
          return localObject;
        }
        try
        {
          boolean bool = paramTypedValue.toLowerCase().endsWith(".xml");
          if (bool) {
            try
            {
              localObject = Type.createFromXml(paramResources.getXml(paramInt1), paramResources);
              if (localObject == null)
              {
                try
                {
                  Log.e("ResourcesCompat", "Failed to find font-family tag");
                  if (paramLabel == null) {
                    break label388;
                  }
                  paramLabel.a(-3, paramHandler);
                  return null;
                }
                catch (IOException paramContext)
                {
                  break label233;
                }
                catch (XmlPullParserException paramContext) {}
              }
              else
              {
                try
                {
                  paramContext = org.org.jaxen.asm.Type.a(paramContext, (Object)localObject, paramResources, paramInt1, paramInt2, paramLabel, paramHandler, paramBoolean);
                  return paramContext;
                }
                catch (IOException paramContext)
                {
                  break label233;
                }
                catch (XmlPullParserException paramContext) {}
                try
                {
                  paramContext = org.org.jaxen.asm.Type.a(paramContext, paramResources, paramInt1, paramTypedValue, paramInt2);
                  if (paramLabel != null)
                  {
                    if (paramContext != null) {}
                    try
                    {
                      paramLabel.a(paramContext, paramHandler);
                      return paramContext;
                    }
                    catch (IOException paramContext)
                    {
                      break label233;
                    }
                    catch (XmlPullParserException paramContext) {}
                    paramLabel.a(-3, paramHandler);
                    return paramContext;
                  }
                  else
                  {
                    return paramContext;
                  }
                }
                catch (IOException paramContext) {}catch (XmlPullParserException paramContext) {}
              }
            }
            catch (IOException paramContext) {}catch (XmlPullParserException paramContext) {}
          }
          label233:
          paramResources = new StringBuilder();
        }
        catch (IOException paramContext)
        {
          paramResources = new StringBuilder();
          paramResources.append("Failed to read xml resource ");
          paramResources.append(paramTypedValue);
          Log.e("ResourcesCompat", paramResources.toString(), paramContext);
        }
        catch (XmlPullParserException paramContext) {}
        paramResources.append("Failed to parse xml resource ");
        paramResources.append(paramTypedValue);
        Log.e("ResourcesCompat", paramResources.toString(), paramContext);
        if (paramLabel == null) {
          break label388;
        }
        paramLabel.a(-3, paramHandler);
        return null;
      }
    }
    else
    {
      paramContext = new StringBuilder();
      paramContext.append("Resource \"");
      paramContext.append(paramResources.getResourceName(paramInt1));
      paramContext.append("\" (");
      paramContext.append(Integer.toHexString(paramInt1));
      paramContext.append(") is not a Font: ");
      paramContext.append(paramTypedValue);
      throw new Resources.NotFoundException(paramContext.toString());
    }
    return null;
    label385:
    return localObject;
    label388:
    return null;
  }
  
  public static Typeface get(Context paramContext, int paramInt1, TypedValue paramTypedValue, int paramInt2, Label paramLabel)
  {
    if (paramContext.isRestricted()) {
      return null;
    }
    return load(paramContext, paramInt1, paramTypedValue, paramInt2, paramLabel, null, true);
  }
  
  public static Drawable getDrawable(android.content.res.Resources paramResources, int paramInt, Resources.Theme paramTheme)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramResources.getDrawable(paramInt, paramTheme);
    }
    return paramResources.getDrawable(paramInt);
  }
  
  private static Typeface load(Context paramContext, int paramInt1, TypedValue paramTypedValue, int paramInt2, Label paramLabel, Handler paramHandler, boolean paramBoolean)
  {
    android.content.res.Resources localResources = paramContext.getResources();
    localResources.getValue(paramInt1, paramTypedValue, true);
    paramContext = add(paramContext, localResources, paramTypedValue, paramInt1, paramInt2, paramLabel, paramHandler, paramBoolean);
    if (paramContext == null)
    {
      if (paramLabel != null) {
        return paramContext;
      }
      paramContext = new StringBuilder();
      paramContext.append("Font resource ID #0x");
      paramContext.append(Integer.toHexString(paramInt1));
      paramContext.append(" could not be retrieved.");
      throw new Resources.NotFoundException(paramContext.toString());
    }
    return paramContext;
  }
}
