package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.graphics.drawable.InsetDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;

public final class DrawableCompat
{
  private static Method sGetLayoutDirectionMethod;
  private static boolean sGetLayoutDirectionMethodFetched;
  private static Method sSetLayoutDirectionMethod;
  private static boolean sSetLayoutDirectionMethodFetched;
  
  public static void applyTheme(android.graphics.drawable.Drawable paramDrawable, Resources.Theme paramTheme)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      paramDrawable.applyTheme(paramTheme);
    }
  }
  
  public static boolean canApplyTheme(android.graphics.drawable.Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramDrawable.canApplyTheme();
    }
    return false;
  }
  
  public static int getAlpha(android.graphics.drawable.Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return paramDrawable.getAlpha();
    }
    return 0;
  }
  
  public static ColorFilter getColorFilter(android.graphics.drawable.Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramDrawable.getColorFilter();
    }
    return null;
  }
  
  public static int getLayoutDirection(android.graphics.drawable.Drawable paramDrawable)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 23) {
      return paramDrawable.getLayoutDirection();
    }
    if (i >= 17)
    {
      if (!sGetLayoutDirectionMethodFetched)
      {
        try
        {
          Method localMethod1 = android.graphics.drawable.Drawable.class.getDeclaredMethod("getLayoutDirection", new Class[0]);
          sGetLayoutDirectionMethod = localMethod1;
          localMethod1 = sGetLayoutDirectionMethod;
          localMethod1.setAccessible(true);
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          Log.i("DrawableCompat", "Failed to retrieve getLayoutDirection() method", localNoSuchMethodException);
        }
        sGetLayoutDirectionMethodFetched = true;
      }
      Method localMethod2 = sGetLayoutDirectionMethod;
      if (localMethod2 != null) {
        try
        {
          paramDrawable = localMethod2.invoke(paramDrawable, new Object[0]);
          paramDrawable = (Integer)paramDrawable;
          i = paramDrawable.intValue();
          return i;
        }
        catch (Exception paramDrawable)
        {
          Log.i("DrawableCompat", "Failed to invoke getLayoutDirection() via reflection", paramDrawable);
          sGetLayoutDirectionMethod = null;
        }
      }
    }
    return 0;
  }
  
  public static void inflate(android.graphics.drawable.Drawable paramDrawable, Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramDrawable.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
      return;
    }
    paramDrawable.inflate(paramResources, paramXmlPullParser, paramAttributeSet);
  }
  
  public static boolean isAutoMirrored(android.graphics.drawable.Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return paramDrawable.isAutoMirrored();
    }
    return false;
  }
  
  public static void jumpToCurrentState(android.graphics.drawable.Drawable paramDrawable)
  {
    paramDrawable.jumpToCurrentState();
  }
  
  public static void setHotspot(android.graphics.drawable.Drawable paramDrawable, float paramFloat1, float paramFloat2)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      paramDrawable.setHotspot(paramFloat1, paramFloat2);
    }
  }
  
  public static void setHotspotBounds(android.graphics.drawable.Drawable paramDrawable, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      paramDrawable.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }
  
  public static boolean setLayoutDirection(android.graphics.drawable.Drawable paramDrawable, int paramInt)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 23) {
      return paramDrawable.setLayoutDirection(paramInt);
    }
    if (i >= 17)
    {
      if (!sSetLayoutDirectionMethodFetched)
      {
        Object localObject = Integer.TYPE;
        try
        {
          localObject = android.graphics.drawable.Drawable.class.getDeclaredMethod("setLayoutDirection", new Class[] { localObject });
          sSetLayoutDirectionMethod = (Method)localObject;
          localObject = sSetLayoutDirectionMethod;
          ((Method)localObject).setAccessible(true);
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          Log.i("DrawableCompat", "Failed to retrieve setLayoutDirection(int) method", localNoSuchMethodException);
        }
        sSetLayoutDirectionMethodFetched = true;
      }
      Method localMethod = sSetLayoutDirectionMethod;
      if (localMethod != null) {
        try
        {
          localMethod.invoke(paramDrawable, new Object[] { Integer.valueOf(paramInt) });
          return true;
        }
        catch (Exception paramDrawable)
        {
          Log.i("DrawableCompat", "Failed to invoke setLayoutDirection(int) via reflection", paramDrawable);
          sSetLayoutDirectionMethod = null;
        }
      }
    }
    return false;
  }
  
  public static void setTint(android.graphics.drawable.Drawable paramDrawable, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramDrawable.setTint(paramInt);
      return;
    }
    if ((paramDrawable instanceof DrawableWrapper)) {
      ((DrawableWrapper)paramDrawable).setTint(paramInt);
    }
  }
  
  public static void setTint(android.graphics.drawable.Drawable paramDrawable, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      paramDrawable.setAutoMirrored(paramBoolean);
    }
  }
  
  public static void setTintList(android.graphics.drawable.Drawable paramDrawable, ColorStateList paramColorStateList)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramDrawable.setTintList(paramColorStateList);
      return;
    }
    if ((paramDrawable instanceof DrawableWrapper)) {
      ((DrawableWrapper)paramDrawable).setTintList(paramColorStateList);
    }
  }
  
  public static void setTintMode(android.graphics.drawable.Drawable paramDrawable, PorterDuff.Mode paramMode)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramDrawable.setTintMode(paramMode);
      return;
    }
    if ((paramDrawable instanceof DrawableWrapper)) {
      ((DrawableWrapper)paramDrawable).setTintMode(paramMode);
    }
  }
  
  public static void update(android.graphics.drawable.Drawable paramDrawable)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 23)
    {
      paramDrawable.clearColorFilter();
      return;
    }
    if (i >= 21)
    {
      paramDrawable.clearColorFilter();
      if ((paramDrawable instanceof InsetDrawable))
      {
        update(((InsetDrawable)paramDrawable).getDrawable());
        return;
      }
      if ((paramDrawable instanceof Drawable))
      {
        update(((Drawable)paramDrawable).getWrappedDrawable());
        return;
      }
      if ((paramDrawable instanceof DrawableContainer))
      {
        paramDrawable = (DrawableContainer.DrawableContainerState)((DrawableContainer)paramDrawable).getConstantState();
        if (paramDrawable != null)
        {
          i = 0;
          int j = paramDrawable.getChildCount();
          while (i < j)
          {
            android.graphics.drawable.Drawable localDrawable = paramDrawable.getChild(i);
            if (localDrawable != null) {
              update(localDrawable);
            }
            i += 1;
          }
        }
      }
    }
    else
    {
      paramDrawable.clearColorFilter();
    }
  }
  
  public static android.graphics.drawable.Drawable wrap(android.graphics.drawable.Drawable paramDrawable)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 23) {
      return paramDrawable;
    }
    if (i >= 21)
    {
      if (!(paramDrawable instanceof DrawableWrapper)) {
        return new DrawableWrapperLollipop(paramDrawable);
      }
      return paramDrawable;
    }
    if (!(paramDrawable instanceof DrawableWrapper)) {
      return new DrawableWrapperDonut(paramDrawable);
    }
    return paramDrawable;
  }
}
