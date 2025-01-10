package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.d.i;
import com.lawyer_smartCalendar.data.Log;
import com.lawyer_smartCalendar.generators.h;
import com.lawyer_smartCalendar.ui.Item;
import com.lawyer_smartCalendar.utils.f;
import java.util.Calendar;
import java.util.List;

public class Frame
  extends RecyclerView.g<com.lawyer_smartCalendar.a.d0.a>
{
  private Calendar a;
  private f b;
  private List<i> c;
  private Context e;
  private Item g;
  
  public Frame(Context paramContext, Item paramItem, List paramList)
  {
    setHasStableIds(true);
    e = paramContext;
    c = paramList;
    g = paramItem;
    b = new f(e);
    a = Calendar.getInstance();
  }
  
  public int a()
  {
    return c.size();
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public d0.a a(ViewGroup paramViewGroup, int paramInt)
  {
    return new d0.a(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131493037, paramViewGroup, false));
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
  
  public void onCreateView(d0.a paramA, int paramInt)
  {
    int j = ((Log)c.get(paramInt)).getID().length();
    int i = 0;
    if (j > 31)
    {
      localObject1 = f;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((Log)c.get(paramInt)).getID().substring(0, 30));
      ((StringBuilder)localObject2).append("...");
      ((TextView)localObject1).setText(((StringBuilder)localObject2).toString());
    }
    else
    {
      f.setText(((Log)c.get(paramInt)).getID());
    }
    f.setTypeface(b.setText());
    Object localObject1 = "";
    Object localObject2 = ((Log)c.get(paramInt)).get();
    j = ((String)localObject2).hashCode();
    if (j != -1688280549)
    {
      break label174;
      if (j != 1705285671) {
        break label205;
      }
    }
    label174:
    while (!((String)localObject2).equals("Meeting"))
    {
      while (!((String)localObject2).equals("ProceedingsTimes")) {}
      break;
    }
    i = 1;
    break label207;
    label205:
    i = -1;
    label207:
    if (i != 0)
    {
      if (i == 1)
      {
        a.setBackgroundResource(2131231000);
        localObject1 = "???? ??????";
      }
    }
    else
    {
      a.setBackgroundResource(2131230999);
      localObject1 = "???? ??????";
    }
    a.setText((CharSequence)localObject1);
    a.setTypeface(b.setText());
    localObject1 = h.get(Long.valueOf(Long.parseLong(((Log)c.get(paramInt)).getString())));
    b.setText((CharSequence)localObject1);
    b.setTypeface(b.setText());
    a.setTimeInMillis(Long.parseLong(((Log)c.get(paramInt)).getString()));
    localObject1 = d;
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(a.get(11));
    ((StringBuilder)localObject2).append(":");
    ((StringBuilder)localObject2).append(a.get(12));
    ((TextView)localObject1).setText(((StringBuilder)localObject2).toString());
    d.setTypeface(b.get());
  }
}
