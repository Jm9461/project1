package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class c
  extends RecyclerView.d0
{
  public ImageView b;
  public ImageView c;
  public TextView f;
  
  public c(h paramH, View paramView)
  {
    super(paramView);
    f = ((TextView)paramView.findViewById(2131296785));
    b = ((ImageView)paramView.findViewById(2131296470));
    c = ((ImageView)paramView.findViewById(2131296471));
    c.setBackgroundResource(2131230974);
    b.setOnClickListener(new c.a.a(this, paramH));
    c.setOnClickListener(new c.a.b(this, paramH));
  }
}
