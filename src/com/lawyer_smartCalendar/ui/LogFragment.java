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
import com.lawyer_smartCalendar.activity.AddMeetingActivity;
import com.lawyer_smartCalendar.activity.ClientDetailActivity;
import com.lawyer_smartCalendar.package_2.SettingsActivity;
import com.lawyer_smartCalendar.utils.h;
import java.util.List;

public class LogFragment
  extends Fragment
  implements View.OnClickListener
{
  FloatingActionButton emptyView;
  private RecyclerView recyclerView;
  private int type = 102;
  private TextView view;
  
  public LogFragment() {}
  
  public void onActivityCreated()
  {
    h localH = new h(getActivity());
    localH.setEnabled();
    List localList = localH.start(getActivitya);
    localH.close();
    recyclerView.setAdapter(new SettingsActivity(getActivity(), this, localList));
    if (localList.isEmpty())
    {
      recyclerView.setVisibility(8);
      view.setVisibility(0);
      return;
    }
    recyclerView.setVisibility(0);
    view.setVisibility(8);
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() != 2131296275) {
      return;
    }
    paramView = new Intent(getActivity(), AddMeetingActivity.class);
    paramView.putExtra("frmMode", "add");
    paramView.putExtra("frmStarter", "client");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getActivitya);
    localStringBuilder.append("");
    paramView.putExtra("id", localStringBuilder.toString());
    onItemClick(paramView);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2131492948, paramViewGroup, false);
  }
  
  public void onCreateView(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == -1) {
      onActivityCreated();
    }
  }
  
  public void onCreateView(View paramView, Bundle paramBundle)
  {
    super.onCreateView(paramView, paramBundle);
    new h(getActivity());
    view = ((TextView)paramView.findViewById(2131296870));
    emptyView = ((FloatingActionButton)paramView.findViewById(2131296275));
    emptyView.setOnClickListener(this);
    recyclerView = ((RecyclerView)paramView.findViewById(2131296684));
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    onActivityCreated();
  }
  
  public void onItemClick(Intent paramIntent)
  {
    equals(paramIntent, type);
  }
}
