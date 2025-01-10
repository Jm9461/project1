package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.a.g.a;
import com.lawyer_smartCalendar.d.d;
import com.lawyer_smartCalendar.data.Label;
import com.lawyer_smartCalendar.ui.HistoryFragment;
import com.lawyer_smartCalendar.utils.f;
import java.util.Calendar;
import java.util.List;

public class ActionMenuItemView
  extends RecyclerView.g<g.a>
{
  private HistoryFragment b;
  private Context c;
  private f d;
  private List<d> l;
  
  public ActionMenuItemView(Context paramContext, HistoryFragment paramHistoryFragment, List paramList)
  {
    setHasStableIds(true);
    c = paramContext;
    l = paramList;
    b = paramHistoryFragment;
    d = new f(c);
    new com.lawyer_smartCalendar.utils.h(c);
    Calendar.getInstance();
  }
  
  public int a()
  {
    return l.size();
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public AppCompatDelegateImplV7.PanelFeatureState a(ViewGroup paramViewGroup, int paramInt)
  {
    return new AppCompatDelegateImplV7.PanelFeatureState(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131492968, paramViewGroup, false));
  }
  
  public void a(AppCompatDelegateImplV7.PanelFeatureState paramPanelFeatureState, int paramInt)
  {
    String str1 = "";
    String str2 = ((Label)l.get(paramInt)).getColor();
    switch (str2.hashCode())
    {
    default: 
      break;
    }
    for (;;)
    {
      break;
      if (str2.equals("SejliDocument"))
      {
        i = 2;
        break label136;
        if (str2.equals("Other"))
        {
          i = 3;
          break label136;
          if (str2.equals("Check"))
          {
            i = 0;
            break label136;
            if (str2.equals("MarriageCertificate"))
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
            g.setBackgroundResource(2131230846);
            str1 = "???? ?????";
          }
        }
        else
        {
          g.setBackgroundResource(2131230848);
          str1 = "????? ????";
        }
      }
      else
      {
        g.setBackgroundResource(2131230849);
        str1 = "??? ??????";
      }
    }
    else
    {
      g.setBackgroundResource(2131230847);
      str1 = "??";
    }
    g.setText(str1);
    if (!((Label)l.get(paramInt)).getName().equals("")) {
      str1 = com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((Label)l.get(paramInt)).getName())));
    } else {
      str1 = "-";
    }
    if (!((Label)l.get(paramInt)).getText().equals("")) {
      str2 = com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((Label)l.get(paramInt)).getText())));
    } else {
      str2 = "-";
    }
    TextView localTextView = e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("????? ??????: ");
    localStringBuilder.append(str1);
    localTextView.setText(localStringBuilder.toString());
    b.setText("????? ?????");
    d.setText(str2);
    e.setTypeface(d.setText());
    g.setTypeface(d.setText());
    b.setTypeface(d.setText());
    d.setTypeface(d.get());
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
}
