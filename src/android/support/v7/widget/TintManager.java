package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import java.lang.ref.WeakReference;

public class TintManager
  extends Resources
{
  private static boolean c = false;
  private final WeakReference<Context> mContextRef;
  
  public TintManager(Context paramContext, Resources paramResources)
  {
    super(paramResources.getAssets(), paramResources.getDisplayMetrics(), paramResources.getConfiguration());
    mContextRef = new WeakReference(paramContext);
  }
  
  public static boolean get()
  {
    return (value()) && (Build.VERSION.SDK_INT <= 20);
  }
  
  public static boolean value()
  {
    return c;
  }
  
  public Drawable getDrawable(int paramInt)
  {
    Context localContext = (Context)mContextRef.get();
    if (localContext != null) {
      return AppCompatDrawableManager.get().getDrawable(localContext, this, paramInt);
    }
    return super.getDrawable(paramInt);
  }
  
  final Drawable superGetDrawable(int paramInt)
  {
    return super.getDrawable(paramInt);
  }
}
