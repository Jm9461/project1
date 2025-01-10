package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Handle
  extends RecyclerView.d0
{
  TextView a;
  TextView b;
  TextView c;
  LinearLayout d;
  TextView g;
  
  public Handle(f paramF, View paramView)
  {
    super(paramView);
    g = ((TextView)paramView.findViewById(2131296858));
    b = ((TextView)paramView.findViewById(2131296859));
    a = ((TextView)paramView.findViewById(2131296871));
    c = ((TextView)paramView.findViewById(2131296873));
    d = ((LinearLayout)paramView.findViewById(2131296560));
    paramView.setOnClickListener(new e.a.a(this, paramF));
  }
}
