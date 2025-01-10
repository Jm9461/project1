package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import org.org.jraf.drawable.AnimatedVectorDrawableCompat;
import org.xmlpull.v1.XmlPullParser;

class ServiceController
  implements AppCompatDrawableManager.InflateDelegate
{
  ServiceController() {}
  
  public Drawable createFromXmlInner(Context paramContext, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    try
    {
      paramContext = AnimatedVectorDrawableCompat.createFromXmlInner(paramContext, paramContext.getResources(), paramXmlPullParser, paramAttributeSet, paramTheme);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", paramContext);
    }
    return null;
  }
}
