package com.lawyer_smartCalendar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.clans.extract.FloatingActionButton;
import com.lawyer_smartCalendar.activity.AddPaymentActivity;
import com.lawyer_smartCalendar.package_2.Plot;
import java.util.List;

public class h
  extends Fragment
  implements View.OnClickListener
{
  private RecyclerView a;
  private TextView c;
  private FloatingActionButton l;
  private int t = 102;
  
  public h() {}
  
  public void a(Intent paramIntent)
  {
    equals(paramIntent, t);
  }
  
  public void c()
  {
    com.lawyer_smartCalendar.utils.h localH = new com.lawyer_smartCalendar.utils.h(getActivity());
    localH.setEnabled();
    List localList = localH.next();
    localH.close();
    a.setAdapter(new Plot(getActivity(), this, localList));
    if (localList.isEmpty())
    {
      a.setVisibility(8);
      c.setVisibility(0);
      return;
    }
    a.setVisibility(0);
    c.setVisibility(8);
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() != 2131296274) {
      return;
    }
    paramView = new Intent(getActivity(), AddPaymentActivity.class);
    paramView.putExtra("frmMode", "add");
    a(paramView);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2131492976, paramViewGroup, false);
  }
  
  public void onCreateView(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == -1) {
      c();
    }
  }
  
  public void onCreateView(View paramView, Bundle paramBundle)
  {
    super.onCreateView(paramView, paramBundle);
    new com.lawyer_smartCalendar.utils.h(getActivity());
    c = ((TextView)paramView.findViewById(2131296870));
    l = ((FloatingActionButton)paramView.findViewById(2131296274));
    l.setOnClickListener(this);
    a = ((RecyclerView)paramView.findViewById(2131296684));
    a.setLayoutManager(new LinearLayoutManager(getActivity()));
    c();
  }
}
