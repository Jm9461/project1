package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Label
  extends RecyclerView.d0
{
  TextView a;
  ImageView b;
  TextView d;
  TextView e;
  
  public Label(Type paramType, View paramView)
  {
    super(paramView);
    d = ((TextView)paramView.findViewById(2131296790));
    a = ((TextView)paramView.findViewById(2131296384));
    e = ((TextView)paramView.findViewById(2131296791));
    b = ((ImageView)paramView.findViewById(2131296479));
    paramView.setOnClickListener(new l.a.a(this, paramType));
    b.setOnClickListener(new l.a.b(this, paramType));
  }
}
