package spongycastle.mirrajabi.persiancalendar.crypto.ec;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ir.mirrajabi.persiancalendar.f.c.b.a;
import java.util.List;
import spongycastle.mirrajabi.persiancalendar.R.layout;
import spongycastle.mirrajabi.persiancalendar.crypto.engines.HistoryFragment;
import spongycastle.mirrajabi.persiancalendar.crypto.f;

public class h
  extends RecyclerView.g<b.a>
{
  private Context a;
  private f b;
  private HistoryFragment e;
  private List<ir.mirrajabi.persiancalendar.f.g.d> f;
  private int h = -1;
  private final int i;
  private final int j;
  
  public h(Context paramContext, HistoryFragment paramHistoryFragment, List paramList)
  {
    j = ((spongycastle.mirrajabi.persiancalendar.crypto.asm.d)paramList.get(0)).a();
    i = paramList.size();
    e = paramHistoryFragment;
    a = paramContext;
    f = paramList;
    b = f.a(paramContext);
  }
  
  private boolean get(int paramInt)
  {
    return paramInt < 7;
  }
  
  public int a()
  {
    return 49;
  }
  
  public d a(ViewGroup paramViewGroup, int paramInt)
  {
    return new d(this, LayoutInflater.from(a).inflate(R.layout.item_day, paramViewGroup, false));
  }
  
  public void b(d paramD, int paramInt)
  {
    g.setBackgroundResource(b.clear());
    v.setBackgroundResource(b.size());
    u.setBackgroundColor(b.k());
    paramInt += 6 - paramInt % 7 * 2;
    if (i < paramInt - 6 - j) {
      return;
    }
    if (!get(paramInt))
    {
      if (paramInt - 7 - j >= 0)
      {
        Object localObject = b;
        List localList = f;
        ((TextView)localObject).setText(((spongycastle.mirrajabi.persiancalendar.crypto.asm.d)localList.get(paramInt - 7 - ((spongycastle.mirrajabi.persiancalendar.crypto.asm.d)localList.get(0)).a())).getId());
        b.setVisibility(0);
        b.setTextSize(0, b.j());
        if (((spongycastle.mirrajabi.persiancalendar.crypto.asm.d)f.get(paramInt - 7 - j)).l()) {
          b.setTextColor(b.c());
        } else {
          b.setTextColor(b.d());
        }
        localObject = (spongycastle.mirrajabi.persiancalendar.crypto.asm.d)f.get(paramInt - 7 - j);
        if (((spongycastle.mirrajabi.persiancalendar.crypto.asm.d)localObject).d())
        {
          if ((((spongycastle.mirrajabi.persiancalendar.crypto.asm.d)localObject).g()) && (b.b())) {
            u.setVisibility(0);
          } else if ((!((spongycastle.mirrajabi.persiancalendar.crypto.asm.d)localObject).g()) && (b.m())) {
            u.setVisibility(0);
          } else {
            u.setVisibility(8);
          }
        }
        else {
          u.setVisibility(8);
        }
        if (((spongycastle.mirrajabi.persiancalendar.crypto.asm.d)f.get(paramInt - 7 - j)).c()) {
          g.setVisibility(0);
        } else {
          g.setVisibility(8);
        }
        if (paramInt == h)
        {
          v.setVisibility(0);
          if (((spongycastle.mirrajabi.persiancalendar.crypto.asm.d)f.get(paramInt - 7 - j)).l()) {
            b.setTextColor(b.e());
          } else {
            b.setTextColor(b.f());
          }
        }
        else
        {
          v.setVisibility(8);
        }
      }
      else
      {
        g.setVisibility(8);
        v.setVisibility(8);
        b.setVisibility(8);
        u.setVisibility(8);
      }
      b.a(b);
      return;
    }
    b.setText(spongycastle.mirrajabi.persiancalendar.crypto.i.s[paramInt]);
    b.setTextColor(b.h());
    b.setTextSize(0, b.i());
    b.setTypeface(b.get());
    g.setVisibility(8);
    v.setVisibility(8);
    u.setVisibility(8);
    b.setVisibility(0);
    b.b(b);
  }
  
  public void e()
  {
    h = -1;
    clear();
  }
  
  public void e(int paramInt)
  {
    h = (paramInt + 6 + j);
    clear();
  }
  
  public int getItemViewType(int paramInt)
  {
    if (get(paramInt)) {
      return 0;
    }
    return 1;
  }
}
