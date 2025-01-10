package apps.authenticator.wizard.runtime;

import android.content.Context;
import android.text.TextUtils;

public final class a
{
  private String a;
  private boolean b = false;
  private Context c;
  private int f = -1;
  
  public a() {}
  
  public a a(int paramInt)
  {
    if ((paramInt != 0) && (paramInt != 1) && (paramInt != 2) && (paramInt != 4)) {
      throw new RuntimeException("The mode in the SharedPreference can only be set too ContextWrapper.MODE_PRIVATE, ContextWrapper.MODE_WORLD_READABLE, ContextWrapper.MODE_WORLD_WRITEABLE or ContextWrapper.MODE_MULTI_PROCESS");
    }
    f = paramInt;
    return this;
  }
  
  public a a(Context paramContext)
  {
    c = paramContext;
    return this;
  }
  
  public a a(boolean paramBoolean)
  {
    b = paramBoolean;
    return this;
  }
  
  public void a()
  {
    if (c != null)
    {
      if (TextUtils.isEmpty(a)) {
        a = c.getPackageName();
      }
      if (b)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(a);
        localStringBuilder.append("_preferences");
        a = localStringBuilder.toString();
      }
      if (f == -1) {
        f = 0;
      }
      Log.setText(c, a, f);
      return;
    }
    throw new RuntimeException("Context not set, please set context before building the Prefs instance.");
  }
  
  public a set(String paramString)
  {
    a = paramString;
    return this;
  }
}
