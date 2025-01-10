package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.a.n.a;
import com.lawyer_smartCalendar.d.i;
import com.lawyer_smartCalendar.data.Log;
import com.lawyer_smartCalendar.generators.h;
import com.lawyer_smartCalendar.ui.LogFragment;
import com.lawyer_smartCalendar.utils.f;
import java.util.Calendar;
import java.util.List;

public class SettingsActivity
  extends RecyclerView.g<n.a>
{
  private Context a;
  private List<i> b;
  private f c;
  private LogFragment d;
  private Calendar g;
  
  public SettingsActivity(Context paramContext, LogFragment paramLogFragment, List paramList)
  {
    setHasStableIds(true);
    a = paramContext;
    b = paramList;
    d = paramLogFragment;
    c = new f(a);
    g = Calendar.getInstance();
  }
  
  public int a()
  {
    return b.size();
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public MainActivity a(ViewGroup paramViewGroup, int paramInt)
  {
    return new MainActivity(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131493037, paramViewGroup, false));
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
  
  public void onCreateView(MainActivity paramMainActivity, int paramInt)
  {
    int j = ((Log)b.get(paramInt)).getID().length();
    int i = 0;
    if (j > 31)
    {
      localObject1 = header;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((Log)b.get(paramInt)).getID().substring(0, 30));
      ((StringBuilder)localObject2).append("...");
      ((TextView)localObject1).setText(((StringBuilder)localObject2).toString());
    }
    else
    {
      header.setText(((Log)b.get(paramInt)).getID());
    }
    header.setTypeface(c.setText());
    Object localObject1 = "";
    Object localObject2 = ((Log)b.get(paramInt)).get();
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
    a.setTypeface(c.setText());
    localObject1 = h.get(Long.valueOf(Long.parseLong(((Log)b.get(paramInt)).getString())));
    c.setText((CharSequence)localObject1);
    c.setTypeface(c.setText());
    g.setTimeInMillis(Long.parseLong(((Log)b.get(paramInt)).getString()));
    localObject1 = b;
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(g.get(11));
    ((StringBuilder)localObject2).append(":");
    ((StringBuilder)localObject2).append(g.get(12));
    ((TextView)localObject1).setText(((StringBuilder)localObject2).toString());
    b.setTypeface(c.get());
  }
}
