package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.a.i.a;
import com.lawyer_smartCalendar.d.i;
import com.lawyer_smartCalendar.data.Log;
import com.lawyer_smartCalendar.generators.h;
import com.lawyer_smartCalendar.ui.SearchFragment;
import com.lawyer_smartCalendar.utils.f;
import java.util.Calendar;
import java.util.List;

public class e
  extends RecyclerView.g<i.a>
{
  private List<i> a;
  private f b;
  private Calendar c;
  private Context e;
  private SearchFragment f;
  
  public e(Context paramContext, SearchFragment paramSearchFragment, List paramList)
  {
    setHasStableIds(true);
    e = paramContext;
    a = paramList;
    f = paramSearchFragment;
    b = new f(e);
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
  
  public Segment a(ViewGroup paramViewGroup, int paramInt)
  {
    return new Segment(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131493037, paramViewGroup, false));
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
  
  public void onCreateView(Segment paramSegment, int paramInt)
  {
    int j = ((Log)a.get(paramInt)).getID().length();
    int i = 0;
    if (j > 31)
    {
      localObject1 = b;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((Log)a.get(paramInt)).getID().substring(0, 30));
      ((StringBuilder)localObject2).append("...");
      ((TextView)localObject1).setText(((StringBuilder)localObject2).toString());
    }
    else
    {
      b.setText(((Log)a.get(paramInt)).getID());
    }
    b.setTypeface(b.setText());
    Object localObject1 = "";
    Object localObject2 = ((Log)a.get(paramInt)).get();
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
        g.setBackgroundResource(2131231000);
        localObject1 = "???? ??????";
      }
    }
    else
    {
      g.setBackgroundResource(2131230999);
      localObject1 = "???? ??????";
    }
    g.setText((CharSequence)localObject1);
    g.setTypeface(b.setText());
    localObject1 = h.get(Long.valueOf(Long.parseLong(((Log)a.get(paramInt)).getString())));
    t1.setText((CharSequence)localObject1);
    t1.setTypeface(b.setText());
    c.setTimeInMillis(Long.parseLong(((Log)a.get(paramInt)).getString()));
    localObject1 = t2;
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(c.get(11));
    ((StringBuilder)localObject2).append(":");
    ((StringBuilder)localObject2).append(c.get(12));
    ((TextView)localObject1).setText(((StringBuilder)localObject2).toString());
    t2.setTypeface(b.get());
  }
}
