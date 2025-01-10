package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.a.e.a;
import com.lawyer_smartCalendar.d.b;
import java.util.List;

public class f
  extends RecyclerView.g<e.a>
{
  private com.lawyer_smartCalendar.utils.h b;
  private com.lawyer_smartCalendar.utils.f c;
  private List<b> l;
  private Context m;
  
  public f(Context paramContext, List paramList)
  {
    setHasStableIds(true);
    m = paramContext;
    l = paramList;
    c = new com.lawyer_smartCalendar.utils.f(m);
    b = new com.lawyer_smartCalendar.utils.h(m);
  }
  
  public int a()
  {
    return l.size();
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public Handle a(ViewGroup paramViewGroup, int paramInt)
  {
    return new Handle(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131492941, paramViewGroup, false));
  }
  
  public void a(Handle paramHandle, int paramInt)
  {
    String str = "";
    Object localObject = ((com.lawyer_smartCalendar.data.h)l.get(paramInt)).getString();
    int i = ((String)localObject).hashCode();
    int j = 0;
    switch (i)
    {
    default: 
      break;
    }
    for (;;)
    {
      break;
      if (((String)localObject).equals("Executive"))
      {
        i = 1;
        break label144;
        if (((String)localObject).equals("Bank"))
        {
          i = 3;
          break label144;
          if (((String)localObject).equals("Challenge"))
          {
            i = 2;
            break label144;
            if (((String)localObject).equals("Normal"))
            {
              i = 0;
              break label144;
            }
          }
        }
      }
    }
    i = -1;
    label144:
    if (i != 0)
    {
      if (i != 1)
      {
        if (i != 2)
        {
          if (i == 3) {
            str = "?????";
          }
        }
        else {
          str = "???????/??????";
        }
      }
      else {
        str = "??????";
      }
    }
    else {
      str = "????";
    }
    c.setText(str);
    c.setTypeface(c.setText());
    b.setEnabled();
    str = b.get(((com.lawyer_smartCalendar.data.h)l.get(paramInt)).c());
    b.close();
    localObject = g;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("????: ");
    localStringBuilder.append(str);
    ((TextView)localObject).setText(localStringBuilder.toString());
    str = "";
    localObject = ((com.lawyer_smartCalendar.data.h)l.get(paramInt)).getText().toString();
    i = ((String)localObject).hashCode();
    if (i != -1808122976)
    {
      break label337;
      if (i != -609016686) {
        break label371;
      }
    }
    label337:
    while (!((String)localObject).equals("Stream"))
    {
      while (!((String)localObject).equals("Finished")) {}
      i = 1;
      break;
    }
    i = j;
    break label373;
    label371:
    i = -1;
    label373:
    if (i != 0)
    {
      if (i == 1)
      {
        str = "??????";
        d.setBackgroundResource(2131230827);
      }
    }
    else
    {
      str = "??????";
      d.setBackgroundResource(2131230826);
      a.setTextColor(-1);
    }
    a.setText(str);
    b.setText(com.lawyer_smartCalendar.generators.h.get(Long.valueOf(Long.parseLong(((com.lawyer_smartCalendar.data.h)l.get(paramInt)).getIcon()))));
    g.setTypeface(c.setText());
    b.setTypeface(c.setText());
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
}
