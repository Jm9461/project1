package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;

public abstract interface CordovaInterfaceImpl
{
  public abstract boolean onActivityResult(Activity paramActivity, int paramInt1, int paramInt2, Intent paramIntent);
  
  public abstract boolean requestPermissions(Activity paramActivity, String[] paramArrayOfString, int paramInt);
}
