package com.mohamadamin.persianmaterialdatetimepicker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.SystemClock;
import android.os.Vibrator;
import android.provider.Settings.System;

public class HapticFeedbackController
{
  private final ContentObserver mContentObserver;
  private final Context mContext;
  private boolean mIsGloballyEnabled;
  private long mLastVibrate;
  private Vibrator mVibrator;
  
  public HapticFeedbackController(Context paramContext)
  {
    mContext = paramContext;
    mContentObserver = new Loader.ForceLoadContentObserver(this, null);
  }
  
  private static boolean checkGlobalSetting(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "haptic_feedback_enabled", 0) == 1;
  }
  
  public void start()
  {
    mVibrator = ((Vibrator)mContext.getSystemService("vibrator"));
    mIsGloballyEnabled = checkGlobalSetting(mContext);
    Uri localUri = Settings.System.getUriFor("haptic_feedback_enabled");
    mContext.getContentResolver().registerContentObserver(localUri, false, mContentObserver);
  }
  
  public void stop()
  {
    mVibrator = null;
    mContext.getContentResolver().unregisterContentObserver(mContentObserver);
  }
  
  public void tryVibrate()
  {
    if ((mVibrator != null) && (mIsGloballyEnabled))
    {
      long l = SystemClock.uptimeMillis();
      if (l - mLastVibrate >= 125L)
      {
        mVibrator.vibrate(5L);
        mLastVibrate = l;
      }
    }
  }
}
