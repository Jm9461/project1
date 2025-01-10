package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.TextView;

public class Segment
  extends RecyclerView.d0
{
  TextView b;
  TextView g;
  TextView t1;
  TextView t2;
  
  public Segment(e paramE, View paramView)
  {
    super(paramView);
    b = ((TextView)paramView.findViewById(2131296808));
    g = ((TextView)paramView.findViewById(2131296809));
    t1 = ((TextView)paramView.findViewById(2131296806));
    t2 = ((TextView)paramView.findViewById(2131296807));
    paramView.setOnClickListener(new i.a.a(this, paramE));
  }
}
