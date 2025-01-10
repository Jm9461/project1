package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class o
  extends RecyclerView.d0
{
  public ImageView b;
  public TextView c;
  
  public o(AppCompatDelegateImplV7 paramAppCompatDelegateImplV7, View paramView)
  {
    super(paramView);
    c = ((TextView)paramView.findViewById(2131296785));
    b = ((ImageView)paramView.findViewById(2131296471));
    b.setBackgroundResource(2131230974);
    b.setOnClickListener(new d.a.a(this, paramAppCompatDelegateImplV7));
  }
}
