package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.ImageView;
import com.joanzapata.material.widget.Button;

public class SearchResult
  extends RecyclerView.d0
{
  public Button start;
  public ImageView type;
  
  public SearchResult(aa paramAa, View paramView)
  {
    super(paramView);
    paramView.setOnClickListener(new p.a.a(this, paramAa));
    type = ((ImageView)paramView.findViewById(2131296472));
    start = ((Button)paramView.findViewById(2131296351));
    start.setOnClickListener(new p.a.b(this, paramAa));
  }
}
