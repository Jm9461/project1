package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.a.x.a;
import com.lawyer_smartCalendar.generators.h;
import java.util.Calendar;

public class LineAndPointFormatter
  extends RecyclerView.g<x.a>
{
  private java.util.List<com.lawyer_smartCalendar.d.f> a;
  private com.lawyer_smartCalendar.utils.f b;
  private Calendar c;
  private Context e;
  
  public LineAndPointFormatter(Context paramContext, java.util.List paramList)
  {
    setHasStableIds(true);
    e = paramContext;
    a = paramList;
    b = new com.lawyer_smartCalendar.utils.f(e);
    c = Calendar.getInstance();
  }
  
  public int a()
  {
    return a.size();
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public Main a(ViewGroup paramViewGroup, int paramInt)
  {
    return new Main(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131493025, paramViewGroup, false));
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
  
  public void onCreateView(Main paramMain, int paramInt)
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
    f.setTypeface(b.setText());
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
          a.setBackgroundResource(2131231000);
          localObject1 = "??? ??????";
        }
      }
      else
      {
        a.setBackgroundResource(2131231000);
        localObject1 = "????";
      }
    }
    else
    {
      a.setBackgroundResource(2131230999);
      localObject1 = "????/??????";
    }
    a.setText((CharSequence)localObject1);
    a.setTypeface(b.setText());
    localObject1 = h.get(Long.valueOf(Long.parseLong(((com.lawyer_smartCalendar.data.List)a.get(paramInt)).getId())));
    c.setText((CharSequence)localObject1);
    c.setTypeface(b.setText());
    c.setTimeInMillis(Long.parseLong(((com.lawyer_smartCalendar.data.List)a.get(paramInt)).getId()));
    localObject1 = b;
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(c.get(11));
    ((StringBuilder)localObject2).append(":");
    ((StringBuilder)localObject2).append(c.get(12));
    ((TextView)localObject1).setText(((StringBuilder)localObject2).toString());
    b.setTypeface(b.get());
  }
}
