package lombok.objectweb.asm;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class a
{
  private Context a;
  
  private a(Context paramContext)
  {
    a = paramContext;
  }
  
  public a setEnabled(boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(a).edit().putBoolean("pl.aprilapps.public_temp", paramBoolean).commit();
    return this;
  }
  
  public a setValue(String paramString)
  {
    PreferenceManager.getDefaultSharedPreferences(a).edit().putString("pl.aprilapps.folder_name", paramString).commit();
    return this;
  }
}
