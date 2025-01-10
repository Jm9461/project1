package android.support.v4.app;

import android.view.View;
import java.util.ArrayList;
import java.util.Collection;

final class a
  implements Runnable
{
  a(Object paramObject1, ClassWriter paramClassWriter, View paramView, Fragment paramFragment, ArrayList paramArrayList1, ArrayList paramArrayList2, ArrayList paramArrayList3, Object paramObject2) {}
  
  public void run()
  {
    Object localObject = s;
    if (localObject != null)
    {
      b.a(localObject, c);
      localObject = f.a(b, s, i, h, c);
      r.addAll((Collection)localObject);
    }
    if (q != null)
    {
      if (t != null)
      {
        localObject = new ArrayList();
        ((ArrayList)localObject).add(c);
        b.a(t, q, (ArrayList)localObject);
      }
      q.clear();
      q.add(c);
    }
  }
}
