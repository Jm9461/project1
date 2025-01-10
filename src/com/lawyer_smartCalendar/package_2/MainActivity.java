package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.TextView;

public class MainActivity
  extends RecyclerView.d0
{
  TextView a;
  TextView b;
  TextView c;
  TextView header;
  
  public MainActivity(SettingsActivity paramSettingsActivity, View paramView)
  {
    super(paramView);
    header = ((TextView)paramView.findViewById(2131296808));
    a = ((TextView)paramView.findViewById(2131296809));
    c = ((TextView)paramView.findViewById(2131296806));
    b = ((TextView)paramView.findViewById(2131296807));
    paramView.setOnClickListener(new n.a.a(this, paramSettingsActivity));
  }
}
