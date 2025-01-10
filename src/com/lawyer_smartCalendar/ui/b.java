package com.lawyer_smartCalendar.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.github.clans.extract.FloatingActionButton;
import com.lawyer_smartCalendar.activity.AddNoteActivity;
import com.lawyer_smartCalendar.package_2.PagerSlidingTabStrip;
import com.lawyer_smartCalendar.utils.d;
import com.lawyer_smartCalendar.utils.h;
import info.hoang8f.android.segmented.SegmentedGroup;
import java.util.List;

public class b
  extends Fragment
  implements View.OnClickListener
{
  private int a = 102;
  public String c = "DailyActivity";
  private TextView mEmptyView;
  private FloatingActionButton mFab;
  private RecyclerView mRecyclerView;
  
  public b() {}
  
  public void a(Intent paramIntent)
  {
    equals(paramIntent, a);
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() != 2131296276) {
      return;
    }
    paramView = new Intent(getActivity(), AddNoteActivity.class);
    paramView.putExtra("frmMode", "add");
    a(paramView);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2131492977, paramViewGroup, false);
  }
  
  public void onCreateView()
  {
    h localH = new h(getActivity());
    localH.setEnabled();
    List localList = localH.match(c);
    localH.close();
    mRecyclerView.setAdapter(new PagerSlidingTabStrip(getActivity(), this, localList));
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
    mFab = ((FloatingActionButton)paramView.findViewById(2131296276));
    mFab.setOnClickListener(this);
    mRecyclerView = ((RecyclerView)paramView.findViewById(2131296684));
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    paramView = (SegmentedGroup)paramView.findViewById(2131296724);
    paramView.setTintColor(Color.parseColor(d.getValue()));
    paramView.setOnCheckedChangeListener(new SettingsActivity.1(this));
    onCreateView();
  }
}
