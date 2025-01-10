package com.lawyer_smartCalendar.ui;

import android.app.Activity;
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
import com.lawyer_smartCalendar.activity.AddCaseActivity;

public class LogsFragment
  extends Fragment
  implements View.OnClickListener
{
  private FloatingActionButton mAdapter;
  private RecyclerView mRecyclerView;
  private TextView swipeRefreshLayout;
  private int version = 110;
  
  public LogsFragment() {}
  
  public void addView()
  {
    getActivity().runOnUiThread(new DayFragment.1(this));
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() != 2131296271) {
      return;
    }
    paramView = new Intent(getActivity(), AddCaseActivity.class);
    paramView.putExtra("frmMode", "add");
    write(paramView);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2131492984, paramViewGroup, false);
  }
  
  public void onCreateView(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onCreateView(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == version) && (paramInt2 == -1)) {
      addView();
    }
  }
  
  public void onCreateView(View paramView, Bundle paramBundle)
  {
    super.onCreateView(paramView, paramBundle);
    swipeRefreshLayout = ((TextView)paramView.findViewById(2131296870));
    mAdapter = ((FloatingActionButton)paramView.findViewById(2131296271));
    mAdapter.setOnClickListener(this);
    mRecyclerView = ((RecyclerView)paramView.findViewById(2131296680));
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    addView();
  }
}
