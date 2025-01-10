package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.a.s.a;
import com.lawyer_smartCalendar.d.c;
import java.util.List;

public class i
  extends RecyclerView.g<s.a>
{
  private String c;
  private com.lawyer_smartCalendar.utils.f d;
  private Context j;
  private List<c> l;
  
  public i(Context paramContext, String paramString, List paramList)
  {
    setHasStableIds(true);
    j = paramContext;
    l = paramList;
    c = paramString;
    d = new com.lawyer_smartCalendar.utils.f(j);
  }
  
  public int a()
  {
    return l.size();
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public p a(ViewGroup paramViewGroup, int paramInt)
  {
    return new p(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131492950, paramViewGroup, false));
  }
  
  public void a(p paramP, int paramInt)
  {
    a.setText(((com.lawyer_smartCalendar.data.f)l.get(paramInt)).getValue());
    a.setTypeface(d.setText());
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
}
