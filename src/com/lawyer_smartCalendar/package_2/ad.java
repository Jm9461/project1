package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.TextView;

public class ad
  extends RecyclerView.d0
{
  TextView d;
  TextView e;
  
  public ad(NavigationMenuPresenter paramNavigationMenuPresenter, View paramView)
  {
    super(paramView);
    e = ((TextView)paramView.findViewById(2131296788));
    d = ((TextView)paramView.findViewById(2131296787));
    paramView.setOnClickListener(new r.a.a(this, paramNavigationMenuPresenter));
  }
}
