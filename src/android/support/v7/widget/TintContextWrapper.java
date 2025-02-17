package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class TintContextWrapper
  extends ContextWrapper
{
  private static final Object LINE_SEPARATOR = new Object();
  private static ArrayList<WeakReference<g1>> sCache;
  private final Resources mResources;
  private final Resources.Theme mTheme;
  
  private TintContextWrapper(Context paramContext)
  {
    super(paramContext);
    if (TintManager.get())
    {
      mResources = new TintManager(this, paramContext.getResources());
      mTheme = mResources.newTheme();
      mTheme.setTo(paramContext.getTheme());
      return;
    }
    mResources = new TintResources(this, paramContext.getResources());
    mTheme = null;
  }
  
  private static boolean get(Context paramContext)
  {
    if ((!(paramContext instanceof TintContextWrapper)) && (!(paramContext.getResources() instanceof TintResources)))
    {
      if ((paramContext.getResources() instanceof TintManager)) {
        return false;
      }
      if ((Build.VERSION.SDK_INT < 21) || (TintManager.get())) {
        return true;
      }
    }
    return false;
  }
  
  public static Context wrap(Context paramContext)
  {
    Object localObject2;
    if (get(paramContext)) {
      localObject2 = LINE_SEPARATOR;
    }
    for (;;)
    {
      int i;
      try
      {
        if (sCache == null)
        {
          sCache = new ArrayList();
        }
        else
        {
          i = sCache.size() - 1;
          if (i >= 0)
          {
            localObject1 = (WeakReference)sCache.get(i);
            if ((localObject1 != null) && (((WeakReference)localObject1).get() != null)) {
              break label175;
            }
            sCache.remove(i);
            break label175;
          }
          i = sCache.size() - 1;
          if (i >= 0)
          {
            localObject1 = (WeakReference)sCache.get(i);
            if (localObject1 == null) {
              break label182;
            }
            localObject1 = (TintContextWrapper)((WeakReference)localObject1).get();
            if ((localObject1 != null) && (((ContextWrapper)localObject1).getBaseContext() == paramContext)) {
              return localObject1;
            }
            i -= 1;
            continue;
          }
        }
        paramContext = new TintContextWrapper(paramContext);
        sCache.add(new WeakReference(paramContext));
        return paramContext;
      }
      catch (Throwable paramContext)
      {
        throw paramContext;
      }
      return paramContext;
      label175:
      i -= 1;
      continue;
      label182:
      Object localObject1 = null;
    }
  }
  
  public AssetManager getAssets()
  {
    return mResources.getAssets();
  }
  
  public Resources getResources()
  {
    return mResources;
  }
  
  public Resources.Theme getTheme()
  {
    Resources.Theme localTheme2 = mTheme;
    Resources.Theme localTheme1 = localTheme2;
    if (localTheme2 == null) {
      localTheme1 = super.getTheme();
    }
    return localTheme1;
  }
  
  public void setTheme(int paramInt)
  {
    Resources.Theme localTheme = mTheme;
    if (localTheme == null)
    {
      super.setTheme(paramInt);
      return;
    }
    localTheme.applyStyle(paramInt, true);
  }
}
