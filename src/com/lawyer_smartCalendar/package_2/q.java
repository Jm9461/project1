package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class q
  extends RecyclerView.d0
{
  TextView a;
  TextView b;
  TextView c;
  TextView e;
  LinearLayout f;
  
  public q(t paramT, View paramView)
  {
    super(paramView);
    c = ((TextView)paramView.findViewById(2131296858));
    b = ((TextView)paramView.findViewById(2131296859));
    a = ((TextView)paramView.findViewById(2131296871));
    e = ((TextView)paramView.findViewById(2131296873));
    f = ((LinearLayout)paramView.findViewById(2131296560));
    paramView.setOnClickListener(new f.a.a(this, paramT));
  }
}
