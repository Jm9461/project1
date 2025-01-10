package com.lawyer_smartCalendar.package_2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.lawyer_smartCalendar.ui.AboutFragment;
import com.lawyer_smartCalendar.ui.ChatFragment;
import com.lawyer_smartCalendar.ui.LogFragment;
import com.lawyer_smartCalendar.ui.SeekBarPreference;

public class Album
  extends FragmentStatePagerAdapter
{
  int count;
  
  public Album(FragmentManager paramFragmentManager, int paramInt)
  {
    super(paramFragmentManager);
    count = paramInt;
  }
  
  public int getCount()
  {
    return count;
  }
  
  public Fragment getItem(int paramInt)
  {
    if (paramInt != 0)
    {
      if (paramInt != 1)
      {
        if (paramInt != 2)
        {
          if (paramInt != 3) {
            return null;
          }
          return new SeekBarPreference();
        }
        return new AboutFragment();
      }
      return new LogFragment();
    }
    return new ChatFragment();
  }
}
