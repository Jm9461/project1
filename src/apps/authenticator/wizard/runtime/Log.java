package apps.authenticator.wizard.runtime;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class Log
{
  private static SharedPreferences mPrefs;
  
  public static int get(String paramString, int paramInt)
  {
    return getInstance().getInt(paramString, paramInt);
  }
  
  public static boolean get(String paramString, boolean paramBoolean)
  {
    return getInstance().getBoolean(paramString, paramBoolean);
  }
  
  public static String getFilename(String paramString1, String paramString2)
  {
    return getInstance().getString(paramString1, paramString2);
  }
  
  public static SharedPreferences getInstance()
  {
    SharedPreferences localSharedPreferences = mPrefs;
    if (localSharedPreferences != null) {
      return localSharedPreferences;
    }
    throw new RuntimeException("Prefs class not correctly instantiated. Please call Builder.setContext().build() in the Application class onCreate.");
  }
  
  private static void init(Context paramContext, String paramString, int paramInt)
  {
    mPrefs = paramContext.getSharedPreferences(paramString, paramInt);
  }
  
  public static void initialize(String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = getInstance().edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.apply();
  }
  
  public static void send(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = getInstance().edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.apply();
  }
  
  public static void write(String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = getInstance().edit();
    localEditor.putBoolean(paramString, paramBoolean);
    localEditor.apply();
  }
}
