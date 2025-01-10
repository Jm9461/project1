package com.lawyer_smartCalendar.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class WelcomeFragment
  extends Fragment
  implements View.OnClickListener
{
  public WelcomeFragment() {}
  
  public void onClick(View paramView)
  {
    paramView.getId();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2131493047, paramViewGroup, false);
  }
  
  public void onCreateView(View paramView, Bundle paramBundle)
  {
    super.onCreateView(paramView, paramBundle);
  }
}
