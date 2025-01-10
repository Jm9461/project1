package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.d.g;
import com.lawyer_smartCalendar.utils.f;
import com.mohamadamin.persianmaterialdatetimepicker.views.Calendar;
import java.text.DecimalFormat;
import java.util.List;

public class Plot
  extends RecyclerView.g<com.lawyer_smartCalendar.a.c0.a>
{
  private List<g> a;
  private com.lawyer_smartCalendar.utils.h b;
  private f c;
  private DecimalFormat d;
  private Context e;
  private com.lawyer_smartCalendar.ui.h g;
  
  public Plot(Context paramContext, com.lawyer_smartCalendar.ui.h paramH, List paramList)
  {
    setHasStableIds(true);
    e = paramContext;
    a = paramList;
    c = new f(e);
    b = new com.lawyer_smartCalendar.utils.h(e);
    g = paramH;
    d = new DecimalFormat("#,###,###,###,###,###,###");
  }
  
  public int a()
  {
    return a.size();
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public c0.a a(ViewGroup paramViewGroup, int paramInt)
  {
    return new c0.a(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131493034, paramViewGroup, false));
  }
  
  public void a(c0.a paramA, int paramInt)
  {
    b.setEnabled();
    Object localObject1 = b;
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(((com.lawyer_smartCalendar.data.d)a.get(paramInt)).b());
    ((StringBuilder)localObject2).append("");
    localObject1 = ((com.lawyer_smartCalendar.utils.h)localObject1).a(((StringBuilder)localObject2).toString());
    localObject1 = b.get(((com.lawyer_smartCalendar.data.h)localObject1).c());
    b.close();
    localObject2 = f;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("???? : ");
    localStringBuilder.append((String)localObject1);
    ((TextView)localObject2).setText(localStringBuilder.toString());
    localObject1 = new Calendar();
    ((Calendar)localObject1).setTimeInMillis(Long.parseLong(((com.lawyer_smartCalendar.data.d)a.get(paramInt)).getId()));
    localObject2 = e;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("????? : ");
    localStringBuilder.append(((Calendar)localObject1).format());
    ((TextView)localObject2).setText(localStringBuilder.toString());
    localObject1 = "";
    localObject2 = ((com.lawyer_smartCalendar.data.d)a.get(paramInt)).getString();
    switch (((String)localObject2).hashCode())
    {
    default: 
      break;
    }
    for (;;)
    {
      break;
      if (((String)localObject2).equals("installment"))
      {
        i = 3;
        break label340;
        if (((String)localObject2).equals("bankReceipt"))
        {
          i = 1;
          break label340;
          if (((String)localObject2).equals("cardToCart"))
          {
            i = 2;
            break label340;
            if (((String)localObject2).equals("cash"))
            {
              i = 0;
              break label340;
            }
          }
        }
      }
    }
    int i = -1;
    label340:
    if (i != 0)
    {
      if (i != 1)
      {
        if (i != 2)
        {
          if (i == 3) {
            localObject1 = "??????";
          }
        }
        else {
          localObject1 = "???? ?? ????";
        }
      }
      else {
        localObject1 = "??? ?????";
      }
    }
    else {
      localObject1 = "????";
    }
    a.setText((CharSequence)localObject1);
    localObject1 = d.format(Long.parseLong(((com.lawyer_smartCalendar.data.d)a.get(paramInt)).getValue()));
    localObject2 = d;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("???? : ");
    localStringBuilder.append((String)localObject1);
    localStringBuilder.append(" ????? ");
    ((TextView)localObject2).setText(localStringBuilder.toString());
    d.setTypeface(c.get());
    a.setTypeface(c.get());
    a.setTextColor(Color.parseColor(com.lawyer_smartCalendar.utils.d.getValue()));
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
}
