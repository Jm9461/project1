package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.a.w.a;
import com.lawyer_smartCalendar.generators.h;
import com.lawyer_smartCalendar.ui.b;
import java.util.Calendar;

public class PagerSlidingTabStrip
  extends RecyclerView.g<w.a>
{
  private java.util.List<com.lawyer_smartCalendar.d.f> a;
  private Context c;
  private b e;
  private com.lawyer_smartCalendar.utils.f f;
  private Calendar v;
  
  public PagerSlidingTabStrip(Context paramContext, b paramB, java.util.List paramList)
  {
    setHasStableIds(true);
    c = paramContext;
    a = paramList;
    e = paramB;
    f = new com.lawyer_smartCalendar.utils.f(c);
    v = Calendar.getInstance();
  }
  
  public int a()
  {
    return a.size();
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public XYPlot a(ViewGroup paramViewGroup, int paramInt)
  {
    return new XYPlot(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131493025, paramViewGroup, false));
  }
  
  public void a(XYPlot paramXYPlot, int paramInt)
  {
    int j = ((com.lawyer_smartCalendar.data.List)a.get(paramInt)).get().length();
    int i = 0;
    if (j > 31)
    {
      localObject1 = f;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((com.lawyer_smartCalendar.data.List)a.get(paramInt)).get().substring(0, 30));
      ((StringBuilder)localObject2).append("...");
      ((TextView)localObject1).setText(((StringBuilder)localObject2).toString());
    }
    else
    {
      f.setText(((com.lawyer_smartCalendar.data.List)a.get(paramInt)).get());
    }
    f.setTypeface(f.setText());
    Object localObject1 = "";
    Object localObject2 = ((com.lawyer_smartCalendar.data.List)a.get(paramInt)).getString();
    j = ((String)localObject2).hashCode();
    if (j != -1199578693) {
      if (j != 76517104)
      {
        break label181;
        break label181;
        if (j != 459619368) {
          break label227;
        }
      }
    }
    label181:
    while (!((String)localObject2).equals("ClientCase"))
    {
      do
      {
        while (!((String)localObject2).equals("DailyActivity")) {}
        i = 2;
        break;
      } while (!((String)localObject2).equals("Other"));
      i = 1;
      break;
    }
    break label229;
    label227:
    i = -1;
    label229:
    if (i != 0)
    {
      if (i != 1)
      {
        if (i == 2)
        {
          g.setBackgroundResource(2131231000);
          localObject1 = "??? ??????";
        }
      }
      else
      {
        g.setBackgroundResource(2131231000);
        localObject1 = "????";
      }
    }
    else
    {
      g.setBackgroundResource(2131230999);
      localObject1 = "????/??????";
    }
    g.setText((CharSequence)localObject1);
    g.setTypeface(f.setText());
    localObject1 = h.get(Long.valueOf(Long.parseLong(((com.lawyer_smartCalendar.data.List)a.get(paramInt)).getId())));
    e.setText((CharSequence)localObject1);
    e.setTypeface(f.setText());
    v.setTimeInMillis(Long.parseLong(((com.lawyer_smartCalendar.data.List)a.get(paramInt)).getId()));
    localObject1 = b;
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(v.get(11));
    ((StringBuilder)localObject2).append(":");
    ((StringBuilder)localObject2).append(v.get(12));
    ((TextView)localObject1).setText(((StringBuilder)localObject2).toString());
    b.setTypeface(f.get());
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
}
