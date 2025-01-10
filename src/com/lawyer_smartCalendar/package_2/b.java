package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.a.h.a;
import com.lawyer_smartCalendar.generators.h;
import com.lawyer_smartCalendar.ui.AbstractFilePickerFragment;
import java.util.Calendar;

public class b
  extends RecyclerView.g<h.a>
{
  private AbstractFilePickerFragment a;
  private com.lawyer_smartCalendar.utils.f b;
  private Calendar c;
  private Context j;
  private java.util.List<com.lawyer_smartCalendar.d.f> l;
  
  public b(Context paramContext, AbstractFilePickerFragment paramAbstractFilePickerFragment, java.util.List paramList)
  {
    setHasStableIds(true);
    j = paramContext;
    l = paramList;
    a = paramAbstractFilePickerFragment;
    b = new com.lawyer_smartCalendar.utils.f(j);
    c = Calendar.getInstance();
  }
  
  public int a()
  {
    return l.size();
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public Widget a(ViewGroup paramViewGroup, int paramInt)
  {
    return new Widget(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131493025, paramViewGroup, false));
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
  
  public void onCreateView(Widget paramWidget, int paramInt)
  {
    int k = ((com.lawyer_smartCalendar.data.List)l.get(paramInt)).get().length();
    int i = 0;
    if (k > 31)
    {
      localObject1 = b;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((com.lawyer_smartCalendar.data.List)l.get(paramInt)).get().substring(0, 30));
      ((StringBuilder)localObject2).append("...");
      ((TextView)localObject1).setText(((StringBuilder)localObject2).toString());
    }
    else
    {
      b.setText(((com.lawyer_smartCalendar.data.List)l.get(paramInt)).get());
    }
    b.setTypeface(b.setText());
    Object localObject1 = "";
    Object localObject2 = ((com.lawyer_smartCalendar.data.List)l.get(paramInt)).getString();
    k = ((String)localObject2).hashCode();
    if (k != -1199578693)
    {
      break label174;
      if (k != 76517104) {
        break label205;
      }
    }
    label174:
    while (!((String)localObject2).equals("ClientCase"))
    {
      while (!((String)localObject2).equals("Other")) {}
      i = 1;
      break;
    }
    break label207;
    label205:
    i = -1;
    label207:
    if (i != 0)
    {
      if (i == 1)
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
    g.setTypeface(b.setText());
    localObject1 = h.get(Long.valueOf(Long.parseLong(((com.lawyer_smartCalendar.data.List)l.get(paramInt)).getId())));
    f.setText((CharSequence)localObject1);
    f.setTypeface(b.setText());
    c.setTimeInMillis(Long.parseLong(((com.lawyer_smartCalendar.data.List)l.get(paramInt)).getId()));
    localObject1 = c;
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(c.get(11));
    ((StringBuilder)localObject2).append(":");
    ((StringBuilder)localObject2).append(c.get(12));
    ((TextView)localObject1).setText(((StringBuilder)localObject2).toString());
    c.setTypeface(b.get());
  }
}
