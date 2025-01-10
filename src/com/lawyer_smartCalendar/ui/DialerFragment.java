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
import com.lawyer_smartCalendar.activity.AddTaxActivity;
import com.lawyer_smartCalendar.package_2.ClassWriter;
import com.lawyer_smartCalendar.utils.h;
import java.util.List;

public class DialerFragment
  extends Fragment
  implements View.OnClickListener
{
  private TextView mEmptyView;
  private FloatingActionButton mFab;
  private RecyclerView mRecyclerView;
  private int type = 102;
  
  public DialerFragment() {}
  
  public void onClick(View paramView)
  {
    if (paramView.getId() != 2131296277) {
      return;
    }
    paramView = new Intent(getActivity(), AddTaxActivity.class);
    paramView.putExtra("frmMode", "add");
    remove(paramView);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2131492978, paramViewGroup, false);
  }
  
  public void onCreateView()
  {
    h localH = new h(getActivity());
    localH.setEnabled();
    List localList = localH.doInBackground();
    localH.close();
    mRecyclerView.setAdapter(new ClassWriter(getActivity(), this, localList));
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
    mFab = ((FloatingActionButton)paramView.findViewById(2131296277));
    mFab.setOnClickListener(this);
    mRecyclerView = ((RecyclerView)paramView.findViewById(2131296684));
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    onCreateView();
  }
  
  public void remove(Intent paramIntent)
  {
    equals(paramIntent, type);
  }
}
