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
import com.lawyer_smartCalendar.activity.AddNoteActivity;
import com.lawyer_smartCalendar.activity.ClientDetailActivity;
import com.lawyer_smartCalendar.package_2.Window;
import com.lawyer_smartCalendar.utils.h;
import java.util.List;

public class ChatFragment
  extends Fragment
  implements View.OnClickListener
{
  private TextView mEmptyView;
  private FloatingActionButton mFab;
  private RecyclerView mListView;
  private int message = 102;
  
  public ChatFragment() {}
  
  public void onClick(View paramView)
  {
    if (paramView.getId() != 2131296276) {
      return;
    }
    paramView = new Intent(getActivity(), AddNoteActivity.class);
    paramView.putExtra("frmMode", "add");
    paramView.putExtra("frmStarter", "client");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getActivitya);
    localStringBuilder.append("");
    paramView.putExtra("id", localStringBuilder.toString());
    update(paramView);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2131492947, paramViewGroup, false);
  }
  
  public void onCreateView()
  {
    h localH = new h(getActivity());
    localH.setEnabled();
    List localList = localH.doInBackground(getActivitya);
    localH.close();
    mListView.setAdapter(new Window(getActivity(), this, localList));
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
    mFab = ((FloatingActionButton)paramView.findViewById(2131296276));
    mFab.setOnClickListener(this);
    mListView = ((RecyclerView)paramView.findViewById(2131296683));
    mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
    onCreateView();
  }
  
  public void update(Intent paramIntent)
  {
    equals(paramIntent, message);
  }
}
