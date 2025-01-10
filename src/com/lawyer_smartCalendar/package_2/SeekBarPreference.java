package com.lawyer_smartCalendar.package_2;

import android.support.v7.widget.RecyclerView.d0;
import android.view.View;
import android.widget.TextView;

public class SeekBarPreference
  extends RecyclerView.d0
{
  TextView mCurrentValue;
  TextView mMaxValue;
  TextView mTextView;
  TextView text;
  
  public SeekBarPreference(Window paramWindow, View paramView)
  {
    super(paramView);
    text = ((TextView)paramView.findViewById(2131296800));
    mTextView = ((TextView)paramView.findViewById(2131296801));
    mCurrentValue = ((TextView)paramView.findViewById(2131296798));
    mMaxValue = ((TextView)paramView.findViewById(2131296799));
    paramView.setOnClickListener(new m.a.a(this, paramWindow));
  }
}
