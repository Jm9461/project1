package android.support.v4.app;

import android.graphics.Rect;
import android.view.View;
import java.util.ArrayList;
import org.org.jaxen.util.Label;

final class NumberPicker
  implements Runnable
{
  NumberPicker(ClassWriter paramClassWriter, Label paramLabel, Object paramObject1, h paramH, ArrayList paramArrayList1, View paramView, Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean, ArrayList paramArrayList2, Object paramObject2, Rect paramRect) {}
  
  public void run()
  {
    Object localObject1 = f.b(b, i, s, e);
    if (localObject1 != null)
    {
      a.addAll(((Label)localObject1).values());
      a.add(v);
    }
    f.a(d, j, l, (Label)localObject1, false);
    Object localObject2 = s;
    if (localObject2 != null)
    {
      b.b(localObject2, k, a);
      localObject1 = f.a((Label)localObject1, e, t, l);
      if (localObject1 != null) {
        b.setText((View)localObject1, r);
      }
    }
  }
}
