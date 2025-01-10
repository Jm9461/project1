package com.lawyer_smartCalendar.activity;

import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Collection4;

public class ContactActivity_ViewBinding
  implements Unbinder
{
  public ContactActivity_ViewBinding(ContactActivity paramContactActivity, View paramView)
  {
    contact_gmail = ((RelativeLayout)Collection4.replace(paramView, 2131296389, "field 'contact_gmail'", RelativeLayout.class));
    contact_instagram = ((RelativeLayout)Collection4.replace(paramView, 2131296390, "field 'contact_instagram'", RelativeLayout.class));
    contact_telegram = ((RelativeLayout)Collection4.replace(paramView, 2131296391, "field 'contact_telegram'", RelativeLayout.class));
  }
}
