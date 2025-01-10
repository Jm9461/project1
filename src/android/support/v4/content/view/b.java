package android.support.v4.content.view;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class b
{
  private int a;
  private final Shader g;
  private final ColorStateList l;
  
  private b(Shader paramShader, ColorStateList paramColorStateList, int paramInt)
  {
    g = paramShader;
    l = paramColorStateList;
    a = paramInt;
  }
  
  static b a(int paramInt)
  {
    return new b(null, null, paramInt);
  }
  
  static b a(ColorStateList paramColorStateList)
  {
    return new b(null, paramColorStateList, paramColorStateList.getDefaultColor());
  }
  
  public static b a(Resources paramResources, int paramInt, Resources.Theme paramTheme)
  {
    try
    {
      paramResources = create(paramResources, paramInt, paramTheme);
      return paramResources;
    }
    catch (Exception paramResources)
    {
      Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", paramResources);
    }
    return null;
  }
  
  static b a(Shader paramShader)
  {
    return new b(paramShader, null, 0);
  }
  
  private static b create(Resources paramResources, int paramInt, Resources.Theme paramTheme)
  {
    XmlResourceParser localXmlResourceParser = paramResources.getXml(paramInt);
    AttributeSet localAttributeSet = Xml.asAttributeSet(localXmlResourceParser);
    do
    {
      paramInt = localXmlResourceParser.next();
    } while ((paramInt != 2) && (paramInt != 1));
    if (paramInt == 2)
    {
      String str = localXmlResourceParser.getName();
      paramInt = -1;
      int i = str.hashCode();
      if (i != 89650992)
      {
        break label69;
        if (i != 1191572447) {
          break label99;
        }
      }
      label69:
      while (!str.equals("gradient"))
      {
        while (!str.equals("selector")) {}
        paramInt = 0;
        break;
      }
      paramInt = 1;
      label99:
      if (paramInt != 0)
      {
        if (paramInt == 1) {
          return a(Status.inflate(paramResources, localXmlResourceParser, localAttributeSet, paramTheme));
        }
        paramResources = new StringBuilder();
        paramResources.append(localXmlResourceParser.getPositionDescription());
        paramResources.append(": unsupported complex color tag ");
        paramResources.append(str);
        throw new XmlPullParserException(paramResources.toString());
      }
      return a(Handler.inflate(paramResources, localXmlResourceParser, localAttributeSet, paramTheme));
    }
    paramResources = new XmlPullParserException("No start tag found");
    throw paramResources;
  }
  
  public boolean a()
  {
    return (write()) || (a != 0);
  }
  
  public boolean a(int[] paramArrayOfInt)
  {
    if (b())
    {
      ColorStateList localColorStateList = l;
      int i = localColorStateList.getColorForState(paramArrayOfInt, localColorStateList.getDefaultColor());
      if (i != a)
      {
        a = i;
        return true;
      }
    }
    return false;
  }
  
  public boolean b()
  {
    if (g == null)
    {
      ColorStateList localColorStateList = l;
      if ((localColorStateList != null) && (localColorStateList.isStateful())) {
        return true;
      }
    }
    return false;
  }
  
  public Shader e()
  {
    return g;
  }
  
  public int getColor()
  {
    return a;
  }
  
  public void setColor(int paramInt)
  {
    a = paramInt;
  }
  
  public boolean write()
  {
    return g != null;
  }
}
