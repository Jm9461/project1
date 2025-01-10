package android.support.v7.view.menu;

import android.os.Handler;
import android.os.SystemClock;
import android.view.MenuItem;
import java.util.List;

class p
  implements android.support.v7.widget.Object
{
  p(d paramD) {}
  
  public void a(f paramF, MenuItem paramMenuItem)
  {
    a.f.removeCallbacksAndMessages(null);
    int k = -1;
    int i = 0;
    int m = a.a.size();
    int j;
    for (;;)
    {
      j = k;
      if (i >= m) {
        break;
      }
      if (paramF == a.a.get(i)).c)
      {
        j = i;
        break;
      }
      i += 1;
    }
    if (j == -1) {
      return;
    }
    i = j + 1;
    h localH;
    if (i < a.a.size()) {
      localH = (h)a.a.get(i);
    } else {
      localH = null;
    }
    paramMenuItem = new e.c.a(this, localH, paramMenuItem, paramF);
    long l = SystemClock.uptimeMillis();
    a.f.postAtTime(paramMenuItem, paramF, l + 200L);
  }
  
  public void b(f paramF, MenuItem paramMenuItem)
  {
    a.f.removeCallbacksAndMessages(paramF);
  }
}
