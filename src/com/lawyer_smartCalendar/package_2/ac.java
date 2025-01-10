package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.TextView;
import com.andexert.library.RippleView;

public class ac
  extends RecyclerView.d0
{
  TextView b;
  
  public ac(w paramW, View paramView)
  {
    super(paramView);
    b = ((TextView)paramView.findViewById(2131296863));
    w.a(paramW, (RippleView)paramView.findViewById(2131296693));
    w.getView(paramW).setOnRippleCompleteListener(new v.a.a(this, paramW));
  }
}
