package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class l
  extends RecyclerView.d0
{
  public TextView a;
  public ImageView c;
  public TextView e;
  public TextView f;
  
  public l(Item paramItem, View paramView)
  {
    super(paramView);
    f = ((TextView)paramView.findViewById(2131296803));
    e = ((TextView)paramView.findViewById(2131296816));
    a = ((TextView)paramView.findViewById(2131296792));
    c = ((ImageView)paramView.findViewById(2131296260));
    c.setOnClickListener(new t.a.a(this, paramItem));
  }
}
