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
import com.lawyer_smartCalendar.activity.AddClientActivity;

public class d
  extends Fragment
  implements View.OnClickListener
{
  private boolean b = false;
  private TextView c;
  private int f = 103;
  private int g = 104;
  private RecyclerView mRecyclerView;
  private FloatingActionButton refresh;
  
  public d() {}
  
  public void a(Intent paramIntent)
  {
    equals(paramIntent, g);
  }
  
  public void finish()
  {
    getActivity().runOnUiThread(new MainActivity.13(this));
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() != 2131296272) {
      return;
    }
    paramView = new Intent(getActivity(), AddClientActivity.class);
    paramView.putExtra("frmMode", "add");
    equals(paramView, f);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2131492983, paramViewGroup, false);
  }
  
  public void onCreateView(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == -1) {
      finish();
    }
  }
  
  public void onCreateView(View paramView, Bundle paramBundle)
  {
    super.onCreateView(paramView, paramBundle);
    c = ((TextView)paramView.findViewById(2131296870));
    refresh = ((FloatingActionButton)paramView.findViewById(2131296272));
    refresh.setOnClickListener(this);
    mRecyclerView = ((RecyclerView)paramView.findViewById(2131296682));
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    finish();
  }
}
