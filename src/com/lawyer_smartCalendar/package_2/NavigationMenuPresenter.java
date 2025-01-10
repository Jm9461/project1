package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.a.r.a;
import com.lawyer_smartCalendar.d.b;
import com.lawyer_smartCalendar.utils.f;
import java.util.List;

public class NavigationMenuPresenter
  extends RecyclerView.g<r.a>
{
  private f f;
  private String i;
  private Context j;
  private List<b> l;
  
  public NavigationMenuPresenter(Context paramContext, String paramString, List paramList)
  {
    setHasStableIds(true);
    j = paramContext;
    l = paramList;
    i = paramString;
    f = new f(j);
  }
  
  public int a()
  {
    return l.size();
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public ad a(ViewGroup paramViewGroup, int paramInt)
  {
    return new ad(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131492942, paramViewGroup, false));
  }
  
  public void a(ad paramAd, int paramInt)
  {
    Object localObject1 = "";
    Object localObject2 = ((com.lawyer_smartCalendar.data.h)l.get(paramInt)).getString();
    switch (((String)localObject2).hashCode())
    {
    default: 
      break;
    }
    for (;;)
    {
      break;
      if (((String)localObject2).equals("Executive"))
      {
        k = 1;
        break label136;
        if (((String)localObject2).equals("Bank"))
        {
          k = 3;
          break label136;
          if (((String)localObject2).equals("Challenge"))
          {
            k = 2;
            break label136;
            if (((String)localObject2).equals("Normal"))
            {
              k = 0;
              break label136;
            }
          }
        }
      }
    }
    int k = -1;
    label136:
    if (k != 0)
    {
      if (k != 1)
      {
        if (k != 2)
        {
          if (k == 3) {
            localObject1 = "?????";
          }
        }
        else {
          localObject1 = "???????/??????";
        }
      }
      else {
        localObject1 = "??????";
      }
    }
    else {
      localObject1 = "????";
    }
    e.setText((CharSequence)localObject1);
    localObject1 = d;
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("????? ????? ??????: ");
    ((StringBuilder)localObject2).append(com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((com.lawyer_smartCalendar.data.h)l.get(paramInt)).getIcon()))));
    ((TextView)localObject1).setText(((StringBuilder)localObject2).toString());
    e.setTypeface(f.setText());
    d.setTypeface(f.load());
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
}
