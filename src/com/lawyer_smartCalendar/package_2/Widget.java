package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.TextView;

public class Widget
  extends RecyclerView.d0
{
  TextView b;
  TextView c;
  TextView f;
  TextView g;
  
  public Widget(b paramB, View paramView)
  {
    super(paramView);
    b = ((TextView)paramView.findViewById(2131296800));
    g = ((TextView)paramView.findViewById(2131296801));
    f = ((TextView)paramView.findViewById(2131296798));
    c = ((TextView)paramView.findViewById(2131296799));
    paramView.setOnClickListener(new h.a.a(this, paramB));
  }
}
