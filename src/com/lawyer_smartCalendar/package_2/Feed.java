package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.ImageView;

public class Feed
  extends RecyclerView.d0
{
  public ImageView image;
  
  public Feed(MainScreen paramMainScreen, View paramView)
  {
    super(paramView);
    paramView.setOnClickListener(new q.a.a(this, paramMainScreen));
    image = ((ImageView)paramView.findViewById(2131296472));
  }
}
