package spongycastle.mirrajabi.persiancalendar.crypto.engines;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.j;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import spongycastle.mirrajabi.persiancalendar.R.id;
import spongycastle.mirrajabi.persiancalendar.R.layout;
import spongycastle.mirrajabi.persiancalendar.crypto.ec.MainActivity.1;
import spongycastle.mirrajabi.persiancalendar.crypto.f;

public class TitlePageIndicator
  extends Fragment
  implements ViewPager.j
{
  private f c;
  private int container;
  private ViewPager mViewPager;
  
  public TitlePageIndicator() {}
  
  private void initUI()
  {
    mViewPager.setAdapter(new MainActivity.1(getChildFragmentManager()));
    mViewPager.setCurrentItem(2500);
    mViewPager.addOnPageChangeListener(this);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(R.layout.fragment_calendar, paramViewGroup, false);
    c = f.a(getContext());
    container = 0;
    mViewPager = ((ViewPager)paramLayoutInflater.findViewById(R.id.calendar_pager));
    c.a(new b(this));
    initUI();
    return paramLayoutInflater;
  }
  
  public void onDraw(int paramInt)
  {
    ViewPager localViewPager = mViewPager;
    localViewPager.setCurrentItem(localViewPager.getCurrentItem() + paramInt, true);
  }
  
  public void onPageScrollStateChanged(int paramInt) {}
  
  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {}
  
  public void onPageSelected(int paramInt)
  {
    container = (paramInt - 2500);
    Intent localIntent = new Intent("BROADCAST_INTENT_TO_MONTH_FRAGMENT");
    localIntent.putExtra("BROADCAST_FIELD_TO_MONTH_FRAGMENT", container);
    localIntent.putExtra("BROADCAST_FIELD_SELECT_DAY", -1);
    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(localIntent);
  }
  
  public int setTextColor()
  {
    return container;
  }
}
