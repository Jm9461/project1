package com.lawyer_smartCalendar.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation.h;
import com.lawyer_smartCalendar.utils.d;

public class c
  extends Fragment
  implements View.OnClickListener
{
  private FragmentTransaction a;
  private AHBottomNavigation i;
  
  public c() {}
  
  public void onClick(View paramView) {}
  
  public void onCreate()
  {
    com.aurelhubert.ahbottomnavigation.Label localLabel1 = new com.aurelhubert.ahbottomnavigation.Label(2131755111, 2131230879, 2131099898);
    com.aurelhubert.ahbottomnavigation.Label localLabel2 = new com.aurelhubert.ahbottomnavigation.Label(2131755112, 2131230877, 2131099898);
    com.aurelhubert.ahbottomnavigation.Label localLabel3 = new com.aurelhubert.ahbottomnavigation.Label(2131755113, 2131230878, 2131099898);
    com.aurelhubert.ahbottomnavigation.Label localLabel4 = new com.aurelhubert.ahbottomnavigation.Label(2131755114, 2131230880, 2131099898);
    com.aurelhubert.ahbottomnavigation.Label localLabel5 = new com.aurelhubert.ahbottomnavigation.Label(2131755115, 2131230876, 2131099898);
    i.a(localLabel1);
    i.a(localLabel2);
    i.a(localLabel3);
    i.a(localLabel4);
    i.a(localLabel5);
    i.setForceTint(false);
    i.setTitleState(AHBottomNavigation.h.i);
    i.setAccentColor(Color.parseColor(d.getValue()));
    i.close(30.0F, 30.0F);
    i.setOnTabSelectedListener(new Label(this));
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2131492985, paramViewGroup, false);
  }
  
  public void onCreateView(View paramView, Bundle paramBundle)
  {
    super.onCreateView(paramView, paramBundle);
    i = ((AHBottomNavigation)paramView.findViewById(2131296329));
    onCreate();
    a = getActivity().getSupportFragmentManager().beginTransaction();
    paramView = new b();
    a.show(2131296450, paramView);
    com.lawyer_smartCalendar.utils.e.a = paramView;
    a.commit();
  }
}
