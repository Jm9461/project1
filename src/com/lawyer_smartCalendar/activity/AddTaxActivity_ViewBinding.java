package com.lawyer_smartCalendar.activity;

import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Collection4;

public class AddTaxActivity_ViewBinding
  implements Unbinder
{
  public AddTaxActivity_ViewBinding(AddTaxActivity paramAddTaxActivity, View paramView)
  {
    input_year = ((EditText)Collection4.replace(paramView, 2131296552, "field 'input_year'", EditText.class));
    input_Income = ((EditText)Collection4.replace(paramView, 2131296486, "field 'input_Income'", EditText.class));
    input_tax = ((EditText)Collection4.replace(paramView, 2131296550, "field 'input_tax'", EditText.class));
    input_net_income = ((EditText)Collection4.replace(paramView, 2131296532, "field 'input_net_income'", EditText.class));
    input_comment = ((EditText)Collection4.replace(paramView, 2131296508, "field 'input_comment'", EditText.class));
    btn_add_tax = ((com.joanzapata.material.widget.Button)Collection4.replace(paramView, 2131296347, "field 'btn_add_tax'", com.rey.material.widget.Button.class));
  }
}
