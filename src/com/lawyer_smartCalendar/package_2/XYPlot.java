package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.TextView;

public class XYPlot
  extends RecyclerView.d0
{
  TextView b;
  TextView e;
  TextView f;
  TextView g;
  
  public XYPlot(PagerSlidingTabStrip paramPagerSlidingTabStrip, View paramView)
  {
    super(paramView);
    f = ((TextView)paramView.findViewById(2131296800));
    g = ((TextView)paramView.findViewById(2131296801));
    e = ((TextView)paramView.findViewById(2131296798));
    b = ((TextView)paramView.findViewById(2131296799));
    paramView.setOnClickListener(new w.a.a(this, paramPagerSlidingTabStrip));
  }
}
