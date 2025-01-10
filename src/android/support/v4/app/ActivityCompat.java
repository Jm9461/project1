package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;

public class ActivityCompat
  extends ContextCompat
{
  private static CordovaInterfaceImpl IMPL;
  
  public static void finishAffinity(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramActivity.finishAffinity();
      return;
    }
    paramActivity.finish();
  }
  
  public static void requestPermissions(Activity paramActivity, String[] paramArrayOfString, int paramInt)
  {
    CordovaInterfaceImpl localCordovaInterfaceImpl = IMPL;
    if ((localCordovaInterfaceImpl != null) && (localCordovaInterfaceImpl.requestPermissions(paramActivity, paramArrayOfString, paramInt))) {
      return;
    }
    if (Build.VERSION.SDK_INT >= 23)
    {
      if ((paramActivity instanceof ActivityCompatApi23.RequestPermissionsRequestCodeValidator)) {
        ((ActivityCompatApi23.RequestPermissionsRequestCodeValidator)paramActivity).validateRequestPermissionsRequestCode(paramInt);
      }
      paramActivity.requestPermissions(paramArrayOfString, paramInt);
      return;
    }
    if ((paramActivity instanceof OnRequestPermissionsResultCallback)) {
      new Handler(Looper.getMainLooper()).post(new ActivityCompat.1(paramArrayOfString, paramActivity, paramInt));
    }
  }
  
  public static CordovaInterfaceImpl setFullScreen()
  {
    return IMPL;
  }
  
  public static void startActivityForResult(Activity paramActivity, Intent paramIntent, int paramInt, Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramActivity.startActivityForResult(paramIntent, paramInt, paramBundle);
      return;
    }
    paramActivity.startActivityForResult(paramIntent, paramInt);
  }
  
  public abstract interface OnRequestPermissionsResultCallback
  {
    public abstract void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt);
  }
}
