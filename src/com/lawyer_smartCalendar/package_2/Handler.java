package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.a.o.a;
import com.lawyer_smartCalendar.d.d;
import com.lawyer_smartCalendar.data.Label;
import com.lawyer_smartCalendar.ui.Fragment;
import com.lawyer_smartCalendar.utils.f;
import java.util.Calendar;
import java.util.List;

public class Handler
  extends RecyclerView.g<o.a>
{
  private List<d> a;
  private f d;
  private Fragment e;
  private Context type;
  
  public Handler(Context paramContext, Fragment paramFragment, List paramList)
  {
    setHasStableIds(true);
    type = paramContext;
    a = paramList;
    e = paramFragment;
    d = new f(type);
    new com.lawyer_smartCalendar.utils.h(type);
    Calendar.getInstance();
  }
  
  public int a()
  {
    return a.size();
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public Switch a(ViewGroup paramViewGroup, int paramInt)
  {
    return new Switch(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131492968, paramViewGroup, false));
  }
  
  public void a(Switch paramSwitch, int paramInt)
  {
    Object localObject1 = "";
    Object localObject2 = ((Label)a.get(paramInt)).getColor();
    switch (((String)localObject2).hashCode())
    {
    default: 
      break;
    }
    for (;;)
    {
      break;
      if (((String)localObject2).equals("SejliDocument"))
      {
        i = 2;
        break label136;
        if (((String)localObject2).equals("Other"))
        {
          i = 3;
          break label136;
          if (((String)localObject2).equals("Check"))
          {
            i = 0;
            break label136;
            if (((String)localObject2).equals("MarriageCertificate"))
            {
              i = 1;
              break label136;
            }
          }
        }
      }
    }
    int i = -1;
    label136:
    if (i != 0)
    {
      if (i != 1)
      {
        if (i != 2)
        {
          if (i == 3)
          {
            a.setBackgroundResource(2131230846);
            localObject1 = "???? ?????";
          }
        }
        else
        {
          a.setBackgroundResource(2131230848);
          localObject1 = "????? ????";
        }
      }
      else
      {
        a.setBackgroundResource(2131230849);
        localObject1 = "??? ??????";
      }
    }
    else
    {
      a.setBackgroundResource(2131230847);
      localObject1 = "??";
    }
    a.setText((CharSequence)localObject1);
    if (!((Label)a.get(paramInt)).getName().equals("")) {
      localObject1 = com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((Label)a.get(paramInt)).getName())));
    } else {
      localObject1 = "-";
    }
    localObject2 = d;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("????? ??????: ");
    localStringBuilder.append((String)localObject1);
    ((TextView)localObject2).setText(localStringBuilder.toString());
    e.setText("????? ?????");
    localObject1 = "-";
    localObject2 = a;
    try
    {
      localObject2 = ((List)localObject2).get(paramInt);
      localObject2 = (Label)localObject2;
      boolean bool = ((Label)localObject2).getText().equals("");
      if (!bool)
      {
        localObject2 = a;
        localObject2 = ((List)localObject2).get(paramInt);
        localObject2 = (Label)localObject2;
        localObject2 = com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((Label)localObject2).getText())));
        localObject1 = localObject2;
      }
      else
      {
        localObject1 = "-";
      }
    }
    catch (Exception localException) {}
    b.setText((CharSequence)localObject1);
    d.setTypeface(d.setText());
    a.setTypeface(d.setText());
    e.setTypeface(d.setText());
    b.setTypeface(d.get());
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
}
