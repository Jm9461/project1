package com.lawyer_smartCalendar.package_2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.lawyer_smartCalendar.ui.Item;
import com.lawyer_smartCalendar.ui.LogsFragment;
import com.lawyer_smartCalendar.ui.WelcomeFragment;
import com.lawyer_smartCalendar.ui.c;
import com.lawyer_smartCalendar.ui.d;

public class TabPagerAdapter
  extends FragmentStatePagerAdapter
{
  int elemCount;
  
  public TabPagerAdapter(FragmentManager paramFragmentManager, int paramInt)
  {
    super(paramFragmentManager);
    elemCount = paramInt;
  }
  
  public int getCount()
  {
    return elemCount;
  }
  
  public Fragment getItem(int paramInt)
  {
    Object localObject;
    switch (paramInt)
    {
    default: 
      return null;
    case 6: 
      localObject = new Item();
      com.lawyer_smartCalendar.utils.e.i = (Item)localObject;
      return localObject;
    case 5: 
      localObject = new d();
      com.lawyer_smartCalendar.utils.e.e = (d)localObject;
      return localObject;
    case 4: 
      localObject = new LogsFragment();
      com.lawyer_smartCalendar.utils.e.f = (LogsFragment)localObject;
      return localObject;
    case 3: 
      return new c();
    case 2: 
      return new WelcomeFragment();
    case 1: 
      return new WelcomeFragment();
    }
    return new WelcomeFragment();
  }
}
