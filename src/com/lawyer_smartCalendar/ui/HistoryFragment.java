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
import com.lawyer_smartCalendar.activity.AddClientDocumentsActivity;
import com.lawyer_smartCalendar.activity.CaseDetailActivity;
import com.lawyer_smartCalendar.package_2.ActionMenuItemView;
import com.lawyer_smartCalendar.utils.h;
import java.util.List;

public class HistoryFragment
  extends Fragment
  implements View.OnClickListener
{
  private FloatingActionButton mAdapter;
  private TextView mEmptyView;
  private RecyclerView mListView;
  private int type = 102;
  
  public HistoryFragment() {}
  
  public void onClick(View paramView)
  {
    if (paramView.getId() != 2131296273) {
      return;
    }
    paramView = new Intent(getActivity(), AddClientDocumentsActivity.class);
    paramView.putExtra("frmMode", "add");
    paramView.putExtra("frmStarter", "case");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getActivityc);
    localStringBuilder.append("");
    paramView.putExtra("id", localStringBuilder.toString());
    remove(paramView);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2131492937, paramViewGroup, false);
  }
  
  public void onCreateView()
  {
    h localH = new h(getActivity());
    localH.setEnabled();
    List localList = localH.read(getActivityc);
    localH.close();
    mListView.setAdapter(new ActionMenuItemView(getActivity(), this, localList));
    if (localList.isEmpty())
    {
      mListView.setVisibility(8);
      mEmptyView.setVisibility(0);
      return;
    }
    mListView.setVisibility(0);
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
    mAdapter = ((FloatingActionButton)paramView.findViewById(2131296273));
    mAdapter.setOnClickListener(this);
    mListView = ((RecyclerView)paramView.findViewById(2131296683));
    mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
    onCreateView();
  }
  
  public void remove(Intent paramIntent)
  {
    equals(paramIntent, type);
  }
}
