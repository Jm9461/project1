package com.lawyer_smartCalendar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.clans.extract.FloatingActionButton;
import com.lawyer_smartCalendar.activity.AddClientDocumentsActivity;
import com.lawyer_smartCalendar.package_2.Handler;
import com.lawyer_smartCalendar.utils.h;
import java.util.List;

public class Fragment
  extends android.support.v4.app.Fragment
  implements View.OnClickListener
{
  private TextView mContainer;
  private int mContainerId = 102;
  private RecyclerView mRecyclerView;
  private FloatingActionButton mView;
  
  public Fragment() {}
  
  public void dump(Intent paramIntent)
  {
    equals(paramIntent, mContainerId);
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() != 2131296273) {
      return;
    }
    paramView = new Intent(getActivity(), AddClientDocumentsActivity.class);
    paramView.putExtra("frmMode", "add");
    dump(paramView);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2131492975, paramViewGroup, false);
  }
  
  public void onCreateView()
  {
    h localH = new h(getActivity());
    localH.setEnabled();
    List localList = localH.a();
    localH.close();
    mRecyclerView.setAdapter(new Handler(getActivity(), this, localList));
    if (localList.isEmpty())
    {
      mRecyclerView.setVisibility(8);
      mContainer.setVisibility(0);
      return;
    }
    mRecyclerView.setVisibility(0);
    mContainer.setVisibility(8);
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
    mContainer = ((TextView)paramView.findViewById(2131296870));
    mView = ((FloatingActionButton)paramView.findViewById(2131296273));
    mView.setOnClickListener(this);
    mRecyclerView = ((RecyclerView)paramView.findViewById(2131296684));
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    onCreateView();
  }
}
