package com.lawyer_smartCalendar.package_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.a.m.a;
import com.lawyer_smartCalendar.generators.h;
import com.lawyer_smartCalendar.ui.ChatFragment;
import java.util.Calendar;

public class Window
  extends RecyclerView.g<m.a>
{
  private ChatFragment bytesRemaining;
  private Calendar date_time;
  private Context mContext;
  private com.lawyer_smartCalendar.utils.f mCurrentValue;
  private java.util.List<com.lawyer_smartCalendar.d.f> mValues;
  
  public Window(Context paramContext, ChatFragment paramChatFragment, java.util.List paramList)
  {
    setHasStableIds(true);
    mContext = paramContext;
    mValues = paramList;
    bytesRemaining = paramChatFragment;
    mCurrentValue = new com.lawyer_smartCalendar.utils.f(mContext);
    date_time = Calendar.getInstance();
  }
  
  public int a()
  {
    return mValues.size();
  }
  
  public long a(int paramInt)
  {
    return paramInt;
  }
  
  public SeekBarPreference a(ViewGroup paramViewGroup, int paramInt)
  {
    return new SeekBarPreference(this, LayoutInflater.from(paramViewGroup.getContext()).inflate(2131493025, paramViewGroup, false));
  }
  
  public int getItemViewType(int paramInt)
  {
    return paramInt;
  }
  
  public void onCreateView(SeekBarPreference paramSeekBarPreference, int paramInt)
  {
    int j = ((com.lawyer_smartCalendar.data.List)mValues.get(paramInt)).get().length();
    int i = 0;
    if (j > 31)
    {
      localObject1 = text;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((com.lawyer_smartCalendar.data.List)mValues.get(paramInt)).get().substring(0, 30));
      ((StringBuilder)localObject2).append("...");
      ((TextView)localObject1).setText(((StringBuilder)localObject2).toString());
    }
    else
    {
      text.setText(((com.lawyer_smartCalendar.data.List)mValues.get(paramInt)).get());
    }
    text.setTypeface(mCurrentValue.setText());
    Object localObject1 = "";
    Object localObject2 = ((com.lawyer_smartCalendar.data.List)mValues.get(paramInt)).getString();
    j = ((String)localObject2).hashCode();
    if (j != -1199578693)
    {
      break label174;
      if (j != 76517104) {
        break label205;
      }
    }
    label174:
    while (!((String)localObject2).equals("ClientCase"))
    {
      while (!((String)localObject2).equals("Other")) {}
      i = 1;
      break;
    }
    break label207;
    label205:
    i = -1;
    label207:
    if (i != 0)
    {
      if (i == 1)
      {
        mTextView.setBackgroundResource(2131231000);
        localObject1 = "????";
      }
    }
    else
    {
      mTextView.setBackgroundResource(2131230999);
      localObject1 = "????/??????";
    }
    mTextView.setText((CharSequence)localObject1);
    mTextView.setTypeface(mCurrentValue.setText());
    localObject1 = h.get(Long.valueOf(Long.parseLong(((com.lawyer_smartCalendar.data.List)mValues.get(paramInt)).getId())));
    mCurrentValue.setText((CharSequence)localObject1);
    mCurrentValue.setTypeface(mCurrentValue.setText());
    date_time.setTimeInMillis(Long.parseLong(((com.lawyer_smartCalendar.data.List)mValues.get(paramInt)).getId()));
    localObject1 = mMaxValue;
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(date_time.get(11));
    ((StringBuilder)localObject2).append(":");
    ((StringBuilder)localObject2).append(date_time.get(12));
    ((TextView)localObject1).setText(((StringBuilder)localObject2).toString());
    mMaxValue.setTypeface(mCurrentValue.get());
  }
}
