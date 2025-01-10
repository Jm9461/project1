package org.org.jraf.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.XmlResourceParser;
import android.os.Build.VERSION;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Xml;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ArgbEvaluator
{
  private static Interpolator createFromXml(Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser)
  {
    paramResources = null;
    int i = paramXmlPullParser.getDepth();
    for (;;)
    {
      int j = paramXmlPullParser.next();
      if (((j == 3) && (paramXmlPullParser.getDepth() <= i)) || (j == 1)) {
        return paramResources;
      }
      if (j == 2)
      {
        paramResources = Xml.asAttributeSet(paramXmlPullParser);
        paramTheme = paramXmlPullParser.getName();
        if (paramTheme.equals("linearInterpolator"))
        {
          paramResources = new LinearInterpolator();
        }
        else if (paramTheme.equals("accelerateInterpolator"))
        {
          paramResources = new AccelerateInterpolator(paramContext, paramResources);
        }
        else if (paramTheme.equals("decelerateInterpolator"))
        {
          paramResources = new DecelerateInterpolator(paramContext, paramResources);
        }
        else if (paramTheme.equals("accelerateDecelerateInterpolator"))
        {
          paramResources = new AccelerateDecelerateInterpolator();
        }
        else if (paramTheme.equals("cycleInterpolator"))
        {
          paramResources = new CycleInterpolator(paramContext, paramResources);
        }
        else if (paramTheme.equals("anticipateInterpolator"))
        {
          paramResources = new AnticipateInterpolator(paramContext, paramResources);
        }
        else if (paramTheme.equals("overshootInterpolator"))
        {
          paramResources = new OvershootInterpolator(paramContext, paramResources);
        }
        else if (paramTheme.equals("anticipateOvershootInterpolator"))
        {
          paramResources = new AnticipateOvershootInterpolator(paramContext, paramResources);
        }
        else if (paramTheme.equals("bounceInterpolator"))
        {
          paramResources = new BounceInterpolator();
        }
        else
        {
          if (!paramTheme.equals("pathInterpolator")) {
            break;
          }
          paramResources = new PathInterpolatorDonut(paramContext, paramResources, paramXmlPullParser);
        }
      }
    }
    paramContext = new StringBuilder();
    paramContext.append("Unknown interpolator name: ");
    paramContext.append(paramXmlPullParser.getName());
    throw new RuntimeException(paramContext.toString());
    return paramResources;
  }
  
  public static Interpolator loadAnimator(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return AnimationUtils.loadInterpolator(paramContext, paramInt);
    }
    Object localObject3 = null;
    Object localObject2 = null;
    Object localObject1 = null;
    if (paramInt == 17563663)
    {
      try
      {
        paramContext = new FastOutLinearInInterpolator();
        return paramContext;
      }
      catch (Throwable paramContext)
      {
        break label253;
      }
      catch (IOException paramContext)
      {
        break label128;
      }
      catch (XmlPullParserException paramContext) {}
    }
    else
    {
      if (paramInt == 17563661)
      {
        paramContext = new FastOutSlowInInterpolator();
        return paramContext;
      }
      if (paramInt == 17563662)
      {
        paramContext = new LinearOutSlowInInterpolator();
        return paramContext;
      }
      XmlResourceParser localXmlResourceParser = paramContext.getResources().getAnimation(paramInt);
      localObject2 = localXmlResourceParser;
      localObject1 = localObject2;
      localObject3 = localObject2;
      paramContext = createFromXml(paramContext, paramContext.getResources(), paramContext.getTheme(), localXmlResourceParser);
      if (localXmlResourceParser == null) {
        return paramContext;
      }
      localXmlResourceParser.close();
      return paramContext;
    }
    label128:
    localObject1 = localObject3;
    localObject2 = new StringBuilder();
    localObject1 = localObject3;
    ((StringBuilder)localObject2).append("Can't load animation resource ID #0x");
    localObject1 = localObject3;
    ((StringBuilder)localObject2).append(Integer.toHexString(paramInt));
    localObject1 = localObject3;
    localObject2 = new Resources.NotFoundException(((StringBuilder)localObject2).toString());
    localObject1 = localObject3;
    ((Exception)localObject2).initCause(paramContext);
    localObject1 = localObject3;
    throw ((Throwable)localObject2);
    localObject1 = localObject2;
    localObject3 = new StringBuilder();
    localObject1 = localObject2;
    ((StringBuilder)localObject3).append("Can't load animation resource ID #0x");
    localObject1 = localObject2;
    ((StringBuilder)localObject3).append(Integer.toHexString(paramInt));
    localObject1 = localObject2;
    localObject3 = new Resources.NotFoundException(((StringBuilder)localObject3).toString());
    localObject1 = localObject2;
    ((Exception)localObject3).initCause(paramContext);
    localObject1 = localObject2;
    throw ((Throwable)localObject3);
    label253:
    if (localObject1 != null) {
      localObject1.close();
    }
    throw paramContext;
    return paramContext;
  }
}
