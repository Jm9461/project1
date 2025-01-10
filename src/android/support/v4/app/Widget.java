package android.support.v4.app;

import android.support.v4.view.ViewCompat;
import android.view.View;
import java.util.ArrayList;

class Widget
  implements Runnable
{
  Widget(ClassWriter paramClassWriter, int paramInt, ArrayList paramArrayList1, ArrayList paramArrayList2, ArrayList paramArrayList3, ArrayList paramArrayList4) {}
  
  public void run()
  {
    int k = 0;
    while (k < d)
    {
      ViewCompat.a((View)j.get(k), (String)i.get(k));
      ViewCompat.a((View)b.get(k), (String)a.get(k));
      k += 1;
    }
  }
}
