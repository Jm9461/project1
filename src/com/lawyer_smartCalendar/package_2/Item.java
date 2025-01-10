package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.a.t.a;
import com.lawyer_smartCalendar.data.b;
import com.lawyer_smartCalendar.utils.d;
import com.mohamadamin.persianmaterialdatetimepicker.views.Calendar;
import java.text.DecimalFormat;
import java.util.List;

public class Item
  extends RecyclerView.g<t.a>
{
  private List<com.lawyer_smartCalendar.d.h> a;
  private Context c;
  DecimalFormat d;
  
  public Item(Context paramContext, List paramList)
  {
    c = paramContext;
    a = paramList;
    new com.lawyer_smartCalendar.utils.h(paramContext);
    d = new DecimalFormat("#,###,###,###,###,###,###");
  }
  
  public int a()
  {
    return a.size();
  }
  
  public l a(ViewGroup paramViewGroup, int paramInt)
  {
    return new l(this, LayoutInflater.from(c).inflate(2131492979, paramViewGroup, false));
  }
  
  public void a(l paramL, int paramInt)
  {
    Object localObject = new Calendar();
    ((Calendar)localObject).setTimeInMillis(Long.parseLong(((b)a.get(paramInt)).a()));
    TextView localTextView = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("????? : ");
    localStringBuilder.append(((Calendar)localObject).format());
    localTextView.setText(localStringBuilder.toString());
    a.setTextColor(d.a());
    localObject = d.format(Long.parseLong(((b)a.get(paramInt)).b()));
    localTextView = e;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("???? : ");
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(" ?????");
    localTextView.setText(localStringBuilder.toString());
    paramL = f;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("????? ??/???? : ");
    ((StringBuilder)localObject).append(((b)a.get(paramInt)).c());
    paramL.setText(((StringBuilder)localObject).toString());
  }
}
