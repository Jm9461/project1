package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MethodWriter
  extends RecyclerView.d0
{
  TextView B;
  TextView c;
  TextView d;
  TextView e;
  TextView f;
  TextView g;
  ImageView v;
  
  public MethodWriter(g paramG, View paramView)
  {
    super(paramView);
    c = ((TextView)paramView.findViewById(2131296856));
    d = ((TextView)paramView.findViewById(2131296857));
    f = ((TextView)paramView.findViewById(2131296865));
    e = ((TextView)paramView.findViewById(2131296866));
    B = ((TextView)paramView.findViewById(2131296864));
    g = ((TextView)paramView.findViewById(2131296867));
    v = ((ImageView)paramView.findViewById(2131296479));
    v.setOnClickListener(new a.a.a(this, paramG));
  }
}
