package com.lawyer_smartCalendar.activity;

import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Collection4;

public class AddProceedingsTimesActivity_ViewBinding
  implements Unbinder
{
  public AddProceedingsTimesActivity_ViewBinding(AddProceedingsTimesActivity paramAddProceedingsTimesActivity, View paramView)
  {
    input_client_name = ((EditText)Collection4.replace(paramView, 2131296504, "field 'input_client_name'", EditText.class));
    input_case = ((EditText)Collection4.replace(paramView, 2131296493, "field 'input_case'", EditText.class));
    input_subject = ((EditText)Collection4.replace(paramView, 2131296549, "field 'input_subject'", EditText.class));
    input_result = ((EditText)Collection4.replace(paramView, 2131296546, "field 'input_result'", EditText.class));
    input_date = ((EditText)Collection4.replace(paramView, 2131296512, "field 'input_date'", EditText.class));
    input_date_hour = ((EditText)Collection4.replace(paramView, 2131296514, "field 'input_date_hour'", EditText.class));
    input_reminder = ((EditText)Collection4.replace(paramView, 2131296544, "field 'input_reminder'", EditText.class));
    input_reminder_count = ((EditText)Collection4.replace(paramView, 2131296545, "field 'input_reminder_count'", EditText.class));
    btn_add_time = ((com.joanzapata.material.widget.Button)Collection4.replace(paramView, 2131296348, "field 'btn_add_time'", com.rey.material.widget.Button.class));
  }
}
