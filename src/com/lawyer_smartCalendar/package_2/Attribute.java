package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Attribute
  extends RecyclerView.d0
{
  TextView a;
  TextView b;
  ImageView state;
  TextView type;
  
  public Attribute(d paramD, View paramView)
  {
    super(paramView);
    type = ((TextView)paramView.findViewById(2131296790));
    a = ((TextView)paramView.findViewById(2131296384));
    b = ((TextView)paramView.findViewById(2131296791));
    state = ((ImageView)paramView.findViewById(2131296479));
    paramView.setOnClickListener(new k.a.a(this, paramD));
    state.setOnClickListener(new k.a.b(this, paramD));
  }
}
