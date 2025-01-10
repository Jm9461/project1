package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.TextView;

public class Switch
  extends RecyclerView.d0
{
  TextView a;
  TextView b;
  TextView d;
  TextView e;
  
  public Switch(Handler paramHandler, View paramView)
  {
    super(paramView);
    d = ((TextView)paramView.findViewById(2131296790));
    a = ((TextView)paramView.findViewById(2131296793));
    e = ((TextView)paramView.findViewById(2131296792));
    b = ((TextView)paramView.findViewById(2131296794));
    paramView.setOnClickListener(new o.a.a(this, paramHandler));
  }
}
