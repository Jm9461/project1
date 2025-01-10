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
import com.lawyer_smartCalendar.activity.AddProceedingsTimesActivity;
import com.lawyer_smartCalendar.activity.CaseDetailActivity;
import com.lawyer_smartCalendar.package_2.e;
import com.lawyer_smartCalendar.utils.h;
import java.util.List;

public class SearchFragment
  extends Fragment
  implements View.OnClickListener
{
  private FloatingActionButton mAdapter;
  private TextView mEmptyView;
  private int mGroup = 102;
  private RecyclerView mRecyclerView;
  
  public SearchFragment() {}
  
  public void loadData(Intent paramIntent)
  {
    equals(paramIntent, mGroup);
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() != 2131296276) {
      return;
    }
    paramView = new Intent(getActivity(), AddProceedingsTimesActivity.class);
    paramView.putExtra("frmMode", "add");
    paramView.putExtra("frmStarter", "case");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getActivityc);
    localStringBuilder.append("");
    paramView.putExtra("id", localStringBuilder.toString());
    loadData(paramView);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2131492940, paramViewGroup, false);
  }
  
  public void onCreateView()
  {
    h localH = new h(getActivity());
    localH.setEnabled();
    List localList = localH.get(getActivityc);
    localH.close();
    mRecyclerView.setAdapter(new e(getActivity(), this, localList));
    if (localList.isEmpty())
    {
      mRecyclerView.setVisibility(8);
      mEmptyView.setVisibility(0);
      return;
    }
    mRecyclerView.setVisibility(0);
    mEmptyView.setVisibility(8);
  }
  
  public void onCreateView(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == -1) {
      onCreateView();
    }
  }
  
  public void onCreateView(View paramView, Bundle paramBundle)
  {
    super.onCreateView(paramView, paramBundle);
    new h(getActivity());
    mEmptyView = ((TextView)paramView.findViewById(2131296870));
    mAdapter = ((FloatingActionButton)paramView.findViewById(2131296276));
    mAdapter.setOnClickListener(this);
    mRecyclerView = ((RecyclerView)paramView.findViewById(2131296684));
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    onCreateView();
  }
}
