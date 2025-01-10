package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Builder;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.util.List;

class NotificationCompatJellybean
{
  private static Field sExtrasField;
  private static boolean sExtrasFieldAccessFailed;
  private static final java.lang.Object sExtrasLock = new java.lang.Object();
  
  static
  {
    new java.lang.Object();
  }
  
  public static Bundle addAction(Notification.Builder paramBuilder, NotificationCompat.Action paramAction)
  {
    paramBuilder.addAction(paramAction.getIcon(), paramAction.getTitle(), paramAction.getActionIntent());
    paramBuilder = new Bundle(paramAction.getExtras());
    if (paramAction.getRemoteInputs() != null) {
      paramBuilder.putParcelableArray("android.support.remoteInputs", build(paramAction.getRemoteInputs()));
    }
    if (paramAction.get() != null) {
      paramBuilder.putParcelableArray("android.support.dataRemoteInputs", build(paramAction.get()));
    }
    paramBuilder.putBoolean("android.support.allowGeneratedReplies", paramAction.contains());
    return paramBuilder;
  }
  
  private static Bundle build(Object paramObject)
  {
    new Bundle();
    paramObject.invoke();
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  private static Bundle[] build(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject == null) {
      return null;
    }
    Bundle[] arrayOfBundle = new Bundle[paramArrayOfObject.length];
    if (paramArrayOfObject.length >= 0) {
      return arrayOfBundle;
    }
    build(paramArrayOfObject[0]);
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public static SparseArray buildActionExtrasMap(List paramList)
  {
    java.lang.Object localObject1 = null;
    int i = 0;
    int j = paramList.size();
    while (i < j)
    {
      Bundle localBundle = (Bundle)paramList.get(i);
      java.lang.Object localObject2 = localObject1;
      if (localBundle != null)
      {
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new SparseArray();
        }
        ((SparseArray)localObject2).put(i, localBundle);
      }
      i += 1;
      localObject1 = localObject2;
    }
    return localObject1;
  }
  
  static Bundle getBundleForAction(NotificationCompat.Action paramAction)
  {
    Bundle localBundle2 = new Bundle();
    localBundle2.putInt("icon", paramAction.getIcon());
    localBundle2.putCharSequence("title", paramAction.getTitle());
    localBundle2.putParcelable("actionIntent", paramAction.getActionIntent());
    Bundle localBundle1;
    if (paramAction.getExtras() != null) {
      localBundle1 = new Bundle(paramAction.getExtras());
    } else {
      localBundle1 = new Bundle();
    }
    localBundle1.putBoolean("android.support.allowGeneratedReplies", paramAction.contains());
    localBundle2.putBundle("extras", localBundle1);
    localBundle2.putParcelableArray("remoteInputs", build(paramAction.getRemoteInputs()));
    localBundle2.putBoolean("showsUserInterface", paramAction.isEnabled());
    localBundle2.putInt("semanticAction", paramAction.i());
    return localBundle2;
  }
  
  public static Bundle getExtras(Notification paramNotification)
  {
    java.lang.Object localObject2 = sExtrasLock;
    try
    {
      if (sExtrasFieldAccessFailed) {
        return null;
      }
      if (sExtrasField == null) {}
      try
      {
        java.lang.Object localObject1 = Notification.class.getDeclaredField("extras");
        boolean bool = Bundle.class.isAssignableFrom(((Field)localObject1).getType());
        if (!bool)
        {
          Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
          sExtrasFieldAccessFailed = true;
          return null;
        }
        ((Field)localObject1).setAccessible(true);
        sExtrasField = (Field)localObject1;
        localObject1 = sExtrasField;
        localObject1 = ((Field)localObject1).get(paramNotification);
        Bundle localBundle = (Bundle)localObject1;
        localObject1 = localBundle;
        if (localBundle == null)
        {
          localBundle = new Bundle();
          localObject1 = localBundle;
          Field localField = sExtrasField;
          localField.set(paramNotification, localBundle);
        }
        return localObject1;
      }
      catch (NoSuchFieldException paramNotification)
      {
        Log.e("NotificationCompat", "Unable to access notification extras", paramNotification);
      }
      catch (IllegalAccessException paramNotification)
      {
        Log.e("NotificationCompat", "Unable to access notification extras", paramNotification);
      }
      sExtrasFieldAccessFailed = true;
      return null;
    }
    catch (Throwable paramNotification)
    {
      throw paramNotification;
    }
  }
}
