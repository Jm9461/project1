package android.support.v4.content.view;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.Xml;
import org.org.mult.Mixed.1;
import org.org.mult.class_1;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class Handler
{
  private static int a(int paramInt, float paramFloat)
  {
    return 0xFFFFFF & paramInt | Math.round(Color.alpha(paramInt) * paramFloat) << 24;
  }
  
  private static ColorStateList a(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    int i = paramXmlPullParser.getDepth() + 1;
    Object localObject1 = new int[20][];
    int[] arrayOfInt = new int[localObject1.length];
    int j = 0;
    for (;;)
    {
      int k = paramXmlPullParser.next();
      if (k == 1) {
        break;
      }
      int m = paramXmlPullParser.getDepth();
      if ((m < i) && (k == 3)) {
        break;
      }
      if ((k == 2) && (m <= i) && (paramXmlPullParser.getName().equals("item")))
      {
        Object localObject2 = obtainAttributes(paramResources, paramTheme, paramAttributeSet, Mixed.1.ColorStateListItem);
        int i2 = ((TypedArray)localObject2).getColor(Mixed.1.ColorStateListItem_android_color, -65281);
        float f = 1.0F;
        if (((TypedArray)localObject2).hasValue(Mixed.1.ColorStateListItem_android_alpha)) {
          f = ((TypedArray)localObject2).getFloat(Mixed.1.ColorStateListItem_android_alpha, 1.0F);
        } else if (((TypedArray)localObject2).hasValue(Mixed.1.ColorStateListItem_alpha)) {
          f = ((TypedArray)localObject2).getFloat(Mixed.1.ColorStateListItem_alpha, 1.0F);
        }
        ((TypedArray)localObject2).recycle();
        int i3 = paramAttributeSet.getAttributeCount();
        localObject2 = new int[i3];
        m = 0;
        k = 0;
        while (k < i3)
        {
          int i1 = paramAttributeSet.getAttributeNameResource(k);
          int n = m;
          if (i1 != 16843173)
          {
            n = m;
            if (i1 != 16843551)
            {
              n = m;
              if (i1 != class_1.alpha)
              {
                if (paramAttributeSet.getAttributeBooleanValue(k, false)) {
                  n = i1;
                } else {
                  n = -i1;
                }
                localObject2[m] = n;
                n = m + 1;
              }
            }
          }
          k += 1;
          m = n;
        }
        localObject2 = StateSet.trimStateSet((int[])localObject2, m);
        k = a(i2, f);
        if ((j != 0) && (localObject2.length == 0)) {}
        arrayOfInt = k.a(arrayOfInt, j, k);
        localObject1 = (int[][])k.a((Object[])localObject1, j, localObject2);
        j += 1;
      }
    }
    paramResources = new int[j];
    paramXmlPullParser = new int[j][];
    System.arraycopy(arrayOfInt, 0, paramResources, 0, j);
    System.arraycopy(localObject1, 0, paramXmlPullParser, 0, j);
    return new ColorStateList(paramXmlPullParser, paramResources);
  }
  
  public static ColorStateList create(Resources paramResources, XmlPullParser paramXmlPullParser, Resources.Theme paramTheme)
  {
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    int i;
    do
    {
      i = paramXmlPullParser.next();
    } while ((i != 2) && (i != 1));
    if (i == 2) {
      return inflate(paramResources, paramXmlPullParser, localAttributeSet, paramTheme);
    }
    paramResources = new XmlPullParserException("No start tag found");
    throw paramResources;
  }
  
  public static ColorStateList inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    String str = paramXmlPullParser.getName();
    if (str.equals("selector")) {
      return a(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    }
    paramResources = new StringBuilder();
    paramResources.append(paramXmlPullParser.getPositionDescription());
    paramResources.append(": invalid color state list tag ");
    paramResources.append(str);
    throw new XmlPullParserException(paramResources.toString());
  }
  
  private static TypedArray obtainAttributes(Resources paramResources, Resources.Theme paramTheme, AttributeSet paramAttributeSet, int[] paramArrayOfInt)
  {
    if (paramTheme == null) {
      return paramResources.obtainAttributes(paramAttributeSet, paramArrayOfInt);
    }
    return paramTheme.obtainStyledAttributes(paramAttributeSet, paramArrayOfInt, 0, 0);
  }
}
