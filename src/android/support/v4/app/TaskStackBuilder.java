package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

public final class TaskStackBuilder
  implements Iterable<Intent>
{
  private final ArrayList<Intent> mIntents = new ArrayList();
  private final Context mSourceContext;
  
  private TaskStackBuilder(Context paramContext)
  {
    mSourceContext = paramContext;
  }
  
  public static TaskStackBuilder create(Context paramContext)
  {
    return new TaskStackBuilder(paramContext);
  }
  
  public TaskStackBuilder addNextIntent(Intent paramIntent)
  {
    mIntents.add(paramIntent);
    return this;
  }
  
  public TaskStackBuilder addParentStack(Activity paramActivity)
  {
    Object localObject1 = null;
    if ((paramActivity instanceof f0.a)) {
      localObject1 = ((f0.a)paramActivity).getSupportParentActivityIntent();
    }
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = NavUtils.getParentActivityIntent(paramActivity);
    }
    if (localObject2 != null)
    {
      localObject1 = ((Intent)localObject2).getComponent();
      paramActivity = (Activity)localObject1;
      if (localObject1 == null) {
        paramActivity = ((Intent)localObject2).resolveActivity(mSourceContext.getPackageManager());
      }
      addParentStack(paramActivity);
      addNextIntent((Intent)localObject2);
    }
    return this;
  }
  
  public TaskStackBuilder addParentStack(ComponentName paramComponentName)
  {
    int i = mIntents.size();
    Object localObject = mSourceContext;
    try
    {
      for (paramComponentName = NavUtils.getParentActivityIntent((Context)localObject, paramComponentName); paramComponentName != null; paramComponentName = NavUtils.getParentActivityIntent((Context)localObject, paramComponentName.getComponent()))
      {
        localObject = mIntents;
        ((ArrayList)localObject).add(i, paramComponentName);
        localObject = mSourceContext;
      }
      return this;
    }
    catch (PackageManager.NameNotFoundException paramComponentName)
    {
      Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
      paramComponentName = new IllegalArgumentException(paramComponentName);
      throw paramComponentName;
    }
  }
  
  public Iterator iterator()
  {
    return mIntents.iterator();
  }
  
  public void startActivities()
  {
    startActivities(null);
  }
  
  public void startActivities(Bundle paramBundle)
  {
    if (!mIntents.isEmpty())
    {
      Object localObject = mIntents;
      localObject = (Intent[])((ArrayList)localObject).toArray(new Intent[((ArrayList)localObject).size()]);
      localObject[0] = new Intent(localObject[0]).addFlags(268484608);
      if (!ContextCompat.startActivities(mSourceContext, (Intent[])localObject, paramBundle))
      {
        paramBundle = new Intent(localObject[(localObject.length - 1)]);
        paramBundle.addFlags(268435456);
        mSourceContext.startActivity(paramBundle);
      }
    }
    else
    {
      throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
    }
  }
}
