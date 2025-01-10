package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.TextView;

public class Main
  extends RecyclerView.d0
{
  TextView a;
  TextView b;
  TextView c;
  TextView f;
  
  public Main(LineAndPointFormatter paramLineAndPointFormatter, View paramView)
  {
    super(paramView);
    f = ((TextView)paramView.findViewById(2131296800));
    a = ((TextView)paramView.findViewById(2131296801));
    c = ((TextView)paramView.findViewById(2131296798));
    b = ((TextView)paramView.findViewById(2131296799));
    paramView.setOnClickListener(new x.a.a(this, paramLineAndPointFormatter));
  }
}
