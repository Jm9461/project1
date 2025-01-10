package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Media
  extends RecyclerView.d0
{
  public TextView path;
  public TextView size;
  public TextView state;
  public ImageView status;
  
  public Media(ByteVector paramByteVector, View paramView)
  {
    super(paramView);
    size = ((TextView)paramView.findViewById(2131296803));
    path = ((TextView)paramView.findViewById(2131296816));
    state = ((TextView)paramView.findViewById(2131296792));
    status = ((ImageView)paramView.findViewById(2131296260));
    status.setOnClickListener(new u.a.a(this, paramByteVector));
    status.setVisibility(8);
  }
}
