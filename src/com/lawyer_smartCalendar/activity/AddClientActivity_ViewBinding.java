package com.lawyer_smartCalendar.activity;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.Collection4;

public class AddClientActivity_ViewBinding
  implements Unbinder
{
  public AddClientActivity_ViewBinding(AddClientActivity paramAddClientActivity, View paramView)
  {
    input_client_name = ((EditText)Collection4.replace(paramView, 2131296504, "field 'input_client_name'", EditText.class));
    input_client_father_name = ((EditText)Collection4.replace(paramView, 2131296502, "field 'input_client_father_name'", EditText.class));
    input_client_national_code = ((EditText)Collection4.replace(paramView, 2131296505, "field 'input_client_national_code'", EditText.class));
    input_client_phone_number = ((EditText)Collection4.replace(paramView, 2131296506, "field 'input_client_phone_number'", EditText.class));
    input_client_landline_phone = ((EditText)Collection4.replace(paramView, 2131296503, "field 'input_client_landline_phone'", EditText.class));
    input_client_email_address = ((EditText)Collection4.replace(paramView, 2131296501, "field 'input_client_email_address'", EditText.class));
    input_client_address = ((EditText)Collection4.replace(paramView, 2131296500, "field 'input_client_address'", EditText.class));
    btn_add_client = ((com.joanzapata.material.widget.Button)Collection4.replace(paramView, 2131296341, "field 'btn_add_client'", com.rey.material.widget.Button.class));
    radioButton_real = ((RadioButton)Collection4.replace(paramView, 2131296675, "field 'radioButton_real'", RadioButton.class));
    radioButton_legal = ((RadioButton)Collection4.replace(paramView, 2131296676, "field 'radioButton_legal'", RadioButton.class));
    input_layout_national_code = ((TextInputLayout)Collection4.replace(paramView, 2131296526, "field 'input_layout_national_code'", TextInputLayout.class));
    input_layout_father_name = ((TextInputLayout)Collection4.replace(paramView, 2131296525, "field 'input_layout_father_name'", TextInputLayout.class));
    input_layout_phone_number = ((TextInputLayout)Collection4.replace(paramView, 2131296531, "field 'input_layout_phone_number'", TextInputLayout.class));
    input_layout_client_name = ((TextInputLayout)Collection4.replace(paramView, 2131296524, "field 'input_layout_client_name'", TextInputLayout.class));
  }
}