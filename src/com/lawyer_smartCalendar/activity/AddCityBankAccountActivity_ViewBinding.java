package com.lawyer_smartCalendar.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Collection4;

public class AddCityBankAccountActivity_ViewBinding
  implements Unbinder
{
  public AddCityBankAccountActivity_ViewBinding(AddCityBankAccountActivity paramAddCityBankAccountActivity, View paramView)
  {
    input_select_city = ((EditText)Collection4.replace(paramView, 2131296547, "field 'input_select_city'", EditText.class));
    input_select_type = ((EditText)Collection4.replace(paramView, 2131296548, "field 'input_select_type'", EditText.class));
    input_account_number = ((EditText)Collection4.replace(paramView, 2131296487, "field 'input_account_number'", EditText.class));
    btn_add_account_number = ((com.joanzapata.material.widget.Button)Collection4.replace(paramView, 2131296337, "field 'btn_add_account_number'", com.rey.material.widget.Button.class));
    toolbar = ((Toolbar)Collection4.replace(paramView, 2131296838, "field 'toolbar'", Toolbar.class));
  }
}
