package apps.api.a;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class h
{
  private final Set<String> m = new HashSet(1);
  
  public h() {}
  
  public final void a(String[] paramArrayOfString)
  {
    try
    {
      Collections.addAll(m, paramArrayOfString);
      return;
    }
    catch (Throwable paramArrayOfString)
    {
      throw paramArrayOfString;
    }
  }
  
  public final boolean a(String paramString, int paramInt)
  {
    if (paramInt == 0) {}
    try
    {
      m.remove(paramString);
      if (m.isEmpty())
      {
        l();
        return true;
      }
      return false;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
    b(paramString);
    return true;
  }
  
  public abstract void b(String paramString);
  
  public abstract void l();
}
