package apps.api.a;

import android.app.Activity;
import android.os.Build.VERSION;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import b.c.a.a;
import b.c.a.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class f
{
  private static final f d = new f();
  private final Set<String> a = new HashSet(1);
  private final List<b> f = new ArrayList(1);
  
  static
  {
    a.class.getSimpleName();
  }
  
  private f() {}
  
  public static f a()
  {
    return d;
  }
  
  private void a(String[] paramArrayOfString, h paramH)
  {
    if (paramH == null) {
      return;
    }
    try
    {
      paramH.a(paramArrayOfString);
      f.add(paramH);
      return;
    }
    catch (Throwable paramArrayOfString)
    {
      throw paramArrayOfString;
    }
  }
  
  public void a(Activity paramActivity, String[] paramArrayOfString, h paramH)
  {
    if (paramActivity == null) {
      return;
    }
    for (;;)
    {
      int i;
      try
      {
        a(paramArrayOfString, paramH);
        int j;
        Object localObject;
        if (Build.VERSION.SDK_INT < 23)
        {
          j = paramArrayOfString.length;
          i = 0;
          if (i >= j) {
            break label228;
          }
          localObject = paramArrayOfString[i];
          if (paramH != null) {
            if (ContextCompat.checkSelfPermission(paramActivity, (String)localObject) != 0) {
              paramH.a((String)localObject, -1);
            } else {
              paramH.a((String)localObject, 0);
            }
          }
        }
        else
        {
          localObject = new ArrayList(1);
          j = paramArrayOfString.length;
          i = 0;
          if (i < j)
          {
            String str = paramArrayOfString[i];
            if (ContextCompat.checkSelfPermission(paramActivity, str) != 0)
            {
              if (a.contains(str)) {
                break label231;
              }
              ((List)localObject).add(str);
              break label231;
            }
            if (paramH == null) {
              break label231;
            }
            paramH.a(str, 0);
            break label231;
          }
          if (!((List)localObject).isEmpty())
          {
            paramArrayOfString = (String[])((List)localObject).toArray(new String[((List)localObject).size()]);
            a.addAll((Collection)localObject);
            ActivityCompat.requestPermissions(paramActivity, paramArrayOfString, 1);
          }
          return;
        }
      }
      catch (Throwable paramActivity)
      {
        throw paramActivity;
      }
      i += 1;
      continue;
      label228:
      continue;
      label231:
      i += 1;
    }
  }
  
  public void a(String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    for (;;)
    {
      int j;
      try
      {
        j = paramArrayOfString.length;
        i = j;
        if (paramArrayOfInt.length >= j) {
          break label117;
        }
        i = paramArrayOfInt.length;
      }
      catch (Throwable paramArrayOfString)
      {
        int i;
        Iterator localIterator;
        throw paramArrayOfString;
      }
      if (j < i)
      {
        localIterator = f.iterator();
        if (localIterator.hasNext())
        {
          if (((h)localIterator.next()).a(paramArrayOfString[j], paramArrayOfInt[j])) {
            localIterator.remove();
          }
          a.remove(paramArrayOfString[j]);
        }
        else
        {
          j += 1;
        }
      }
      else
      {
        return;
        label117:
        j = 0;
      }
    }
  }
}
