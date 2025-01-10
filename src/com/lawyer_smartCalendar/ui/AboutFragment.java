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
import com.lawyer_smartCalendar.activity.ClientDetailActivity;

public class AboutFragment
  extends Fragment
  implements View.OnClickListener
{
  private TextView mCurrentPage;
  FloatingActionButton mFab;
  private RecyclerView mRecyclerView;
  private int version = 110;
  
  public AboutFragment() {}
  
  public void init(Intent paramIntent)
  {
    equals(paramIntent, 102);
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() != 2131296271) {
      return;
    }
    paramView = new Intent(getActivity(), AddCaseActivity.class);
    paramView.putExtra("frmMode", "add");
    paramView.putExtra("frmStarter", "client");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getActivitya);
    localStringBuilder.append("");
    paramView.putExtra("id", localStringBuilder.toString());
    init(paramView);
  }
  
  public void onCreate()
  {
    getActivity().runOnUiThread(new TestActivity.1(this));
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2131492944, paramViewGroup, false);
  }
  
  public void onCreateView(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onCreateView(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == version) && (paramInt2 == -1)) {
      onCreate();
    }
  }
  
  public void onCreateView(View paramView, Bundle paramBundle)
  {
    super.onCreateView(paramView, paramBundle);
    mRecyclerView = ((RecyclerView)paramView.findViewById(2131296680));
    mFab = ((FloatingActionButton)paramView.findViewById(2131296271));
    mFab.setOnClickListener(this);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mCurrentPage = ((TextView)paramView.findViewById(2131296870));
    onCreate();
  }
}
