package android.support.v4.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.Editable;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ClassWriter
  implements ActionMode.Callback
{
  private final TextView a;
  private Class b;
  private Method c;
  private boolean d;
  private boolean f;
  private final ActionMode.Callback mWrapped;
  
  ClassWriter(ActionMode.Callback paramCallback, TextView paramTextView)
  {
    mWrapped = paramCallback;
    a = paramTextView;
    f = false;
  }
  
  private Intent a(ResolveInfo paramResolveInfo, TextView paramTextView)
  {
    return getIntent().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", a(paramTextView) ^ true).setClassName(activityInfo.packageName, activityInfo.name);
  }
  
  private void a(Menu paramMenu)
  {
    Object localObject3 = a.getContext();
    PackageManager localPackageManager = ((Context)localObject3).getPackageManager();
    Object localObject4;
    if (!f)
    {
      f = true;
      try
      {
        Object localObject1 = Class.forName("com.android.internal.view.menu.MenuBuilder");
        b = ((Class)localObject1);
        localObject1 = b;
        localObject4 = Integer.TYPE;
        localObject1 = ((Class)localObject1).getDeclaredMethod("removeItemAt", new Class[] { localObject4 });
        c = ((Method)localObject1);
        d = true;
      }
      catch (NoSuchMethodException localNoSuchMethodException) {}catch (ClassNotFoundException localClassNotFoundException) {}
      b = null;
      c = null;
      d = false;
    }
    Object localObject2;
    if (d) {
      localObject2 = b;
    }
    try
    {
      boolean bool = ((Class)localObject2).isInstance(paramMenu);
      if (bool)
      {
        localObject2 = c;
      }
      else
      {
        localObject2 = paramMenu.getClass();
        localObject4 = Integer.TYPE;
        localObject2 = ((Class)localObject2).getDeclaredMethod("removeItemAt", new Class[] { localObject4 });
      }
      int i = paramMenu.size();
      i -= 1;
      while (i >= 0)
      {
        localObject4 = paramMenu.getItem(i);
        Intent localIntent = ((MenuItem)localObject4).getIntent();
        if (localIntent != null)
        {
          bool = "android.intent.action.PROCESS_TEXT".equals(((MenuItem)localObject4).getIntent().getAction());
          if (bool) {
            ((Method)localObject2).invoke(paramMenu, new Object[] { Integer.valueOf(i) });
          }
        }
        i -= 1;
      }
      localObject2 = get((Context)localObject3, localPackageManager);
      i = 0;
      while (i < ((List)localObject2).size())
      {
        localObject3 = (ResolveInfo)((List)localObject2).get(i);
        paramMenu.add(0, 0, i + 100, ((ResolveInfo)localObject3).loadLabel(localPackageManager)).setIntent(a((ResolveInfo)localObject3, a)).setShowAsAction(1);
        i += 1;
      }
      return;
    }
    catch (InvocationTargetException paramMenu) {}catch (IllegalAccessException paramMenu) {}catch (NoSuchMethodException paramMenu) {}
  }
  
  private boolean a(TextView paramTextView)
  {
    return ((paramTextView instanceof Editable)) && (paramTextView.onCheckIsTextEditor()) && (paramTextView.isEnabled());
  }
  
  private boolean auth(ResolveInfo paramResolveInfo, Context paramContext)
  {
    if (paramContext.getPackageName().equals(activityInfo.packageName)) {
      return true;
    }
    if (!activityInfo.exported) {
      return false;
    }
    paramResolveInfo = activityInfo.permission;
    return (paramResolveInfo == null) || (paramContext.checkSelfPermission(paramResolveInfo) == 0);
  }
  
  private List get(Context paramContext, PackageManager paramPackageManager)
  {
    ArrayList localArrayList = new ArrayList();
    if (!(paramContext instanceof Activity)) {
      return localArrayList;
    }
    paramPackageManager = paramPackageManager.queryIntentActivities(getIntent(), 0).iterator();
    while (paramPackageManager.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramPackageManager.next();
      if (auth(localResolveInfo, paramContext)) {
        localArrayList.add(localResolveInfo);
      }
    }
    return localArrayList;
  }
  
  private Intent getIntent()
  {
    return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
  }
  
  public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
  {
    return mWrapped.onActionItemClicked(paramActionMode, paramMenuItem);
  }
  
  public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    return mWrapped.onCreateActionMode(paramActionMode, paramMenu);
  }
  
  public void onDestroyActionMode(ActionMode paramActionMode)
  {
    mWrapped.onDestroyActionMode(paramActionMode);
  }
  
  public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
  {
    a(paramMenu);
    return mWrapped.onPrepareActionMode(paramActionMode, paramMenu);
  }
}
