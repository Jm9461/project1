package spongycastle.mirrajabi.persiancalendar.crypto.ec;

import android.os.Build.VERSION;
import android.support.v7.widget.RecyclerView.d0;
import android.support.v7.widget.RecyclerView.g;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import java.util.List;
import spongycastle.mirrajabi.persiancalendar.R.id;
import spongycastle.mirrajabi.persiancalendar.crypto.engines.HistoryFragment;

public class d
  extends RecyclerView.d0
  implements View.OnClickListener, View.OnLongClickListener
{
  TextView b;
  View g;
  View u;
  View v;
  
  public d(h paramH, View paramView)
  {
    super(paramView);
    b = ((TextView)paramView.findViewById(R.id.count));
    g = paramView.findViewById(R.id.today);
    v = paramView.findViewById(R.id.select_day);
    u = paramView.findViewById(R.id.event);
    paramView.setOnClickListener(this);
    paramView.setOnLongClickListener(this);
  }
  
  public void onClick(View paramView)
  {
    int i = getText();
    i += 6 - i % 7 * 2;
    if (h.c(a) < i - 6 - h.b(a)) {
      return;
    }
    if (i - 7 - h.b(a) >= 0)
    {
      h.a(a).remove(((spongycastle.mirrajabi.persiancalendar.crypto.asm.d)h.d(a).get(i - 7 - h.b(a))).b());
      h.a(a, i);
      a.clear();
    }
  }
  
  public boolean onLongClick(View paramView)
  {
    int i = getText();
    i += 6 - i % 7 * 2;
    if (h.c(a) < i - 6 - h.b(a)) {
      return false;
    }
    if (Build.VERSION.SDK_INT >= 14)
    {
      paramView = a;
      try
      {
        paramView = h.a(paramView);
        Object localObject = a;
        localObject = h.d((h)localObject);
        h localH = a;
        int j = h.b(localH);
        localObject = ((List)localObject).get(i - 7 - j);
        localObject = (spongycastle.mirrajabi.persiancalendar.crypto.asm.d)localObject;
        paramView.a(((spongycastle.mirrajabi.persiancalendar.crypto.asm.d)localObject).b());
        return false;
      }
      catch (Exception paramView) {}
    }
    return false;
  }
}
