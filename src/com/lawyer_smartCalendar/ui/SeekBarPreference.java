package com.lawyer_smartCalendar.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lawyer_smartCalendar.activity.ClientDetailActivity;
import com.lawyer_smartCalendar.utils.h;

public class SeekBarPreference
  extends Fragment
  implements View.OnClickListener
{
  private TextView a;
  private h c;
  private TextView context;
  private TextView date;
  private TextView description;
  private TextView header;
  private TextView location;
  private TextView name;
  private TextView text;
  private TextView time;
  private TextView title;
  private TextView type;
  private TextView url;
  private TextView userName;
  private TextView value;
  private TextView view;
  
  public SeekBarPreference() {}
  
  public void onClick(View paramView)
  {
    paramView.getId();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2131492945, paramViewGroup, false);
  }
  
  public void onCreateView(View paramView, Bundle paramBundle)
  {
    super.onCreateView(paramView, paramBundle);
    c = new h(getActivity());
    context = ((TextView)paramView.findViewById(2131296893));
    value = ((TextView)paramView.findViewById(2131296894));
    description = ((TextView)paramView.findViewById(2131296895));
    location = ((TextView)paramView.findViewById(2131296897));
    date = ((TextView)paramView.findViewById(2131296898));
    time = ((TextView)paramView.findViewById(2131296875));
    type = ((TextView)paramView.findViewById(2131296876));
    header = ((TextView)paramView.findViewById(2131296877));
    a = ((TextView)paramView.findViewById(2131296922));
    view = ((TextView)paramView.findViewById(2131296923));
    name = ((TextView)paramView.findViewById(2131296924));
    title = ((TextView)paramView.findViewById(2131296926));
    userName = ((TextView)paramView.findViewById(2131296927));
    url = ((TextView)paramView.findViewById(2131296904));
    text = ((TextView)paramView.findViewById(2131296905));
    paramView = new com.lawyer_smartCalendar.utils.f(getActivity());
    context.setTypeface(paramView.get());
    value.setTypeface(paramView.get());
    description.setTypeface(paramView.get());
    location.setTypeface(paramView.get());
    date.setTypeface(paramView.get());
    time.setTypeface(paramView.get());
    type.setTypeface(paramView.get());
    header.setTypeface(paramView.get());
    a.setTypeface(paramView.setText());
    view.setTypeface(paramView.setText());
    name.setTypeface(paramView.setText());
    title.setTypeface(paramView.setText());
    userName.setTypeface(paramView.setText());
    url.setTypeface(paramView.setText());
    text.setTypeface(paramView.setText());
    c.setEnabled();
    paramView = c;
    paramBundle = new StringBuilder();
    paramBundle.append(getActivitya);
    paramBundle.append("");
    paramView = paramView.set(paramBundle.toString());
    c.close();
    if (!paramView.getValue().equals("")) {
      a.setText(paramView.getValue());
    }
    if (!paramView.d().equals("")) {
      view.setText(paramView.d());
    }
    if (!paramView.get().equals("")) {
      name.setText(paramView.get());
    }
    if (!paramView.getText().equals("")) {
      title.setText(paramView.getText());
    }
    if (!paramView.a().equals("")) {
      userName.setText(paramView.a());
    }
    if (!paramView.e().equals("")) {
      url.setText(paramView.e());
    }
    if (!paramView.getTitle().equals("")) {
      text.setText(paramView.getTitle());
    }
    if (paramView.f().equals("legal"))
    {
      value.setVisibility(8);
      description.setVisibility(8);
      location.setVisibility(8);
      view.setVisibility(8);
      name.setVisibility(8);
      title.setVisibility(8);
      ((ViewGroup)((ViewGroup)view.getParent()).getParent()).removeView((ViewGroup)view.getParent());
      ((ViewGroup)((ViewGroup)name.getParent()).getParent()).removeView((ViewGroup)name.getParent());
      ((ViewGroup)((ViewGroup)title.getParent()).getParent()).removeView((ViewGroup)title.getParent());
    }
  }
}
