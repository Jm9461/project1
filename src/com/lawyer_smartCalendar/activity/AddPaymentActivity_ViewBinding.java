package com.lawyer_smartCalendar.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import butterknife.Unbinder;
import butterknife.internal.Collection4;

public class AddPaymentActivity_ViewBinding
  implements Unbinder
{
  public AddPaymentActivity_ViewBinding(AddPaymentActivity paramAddPaymentActivity, View paramView)
  {
    input_date = ((EditText)Collection4.replace(paramView, 2131296512, "field 'input_date'", EditText.class));
    input_client_name = ((EditText)Collection4.replace(paramView, 2131296504, "field 'input_client_name'", EditText.class));
    input_case = ((EditText)Collection4.replace(paramView, 2131296493, "field 'input_case'", EditText.class));
    input_pay_type = ((EditText)Collection4.replace(paramView, 2131296543, "field 'input_pay_type'", EditText.class));
    input_honorariumTotal = ((EditText)Collection4.replace(paramView, 2131296519, "field 'input_honorariumTotal'", EditText.class));
    input_detail = ((EditText)Collection4.replace(paramView, 2131296516, "field 'input_detail'", EditText.class));
    btn_add_installment = ((com.joanzapata.material.widget.Button)Collection4.replace(paramView, 2131296345, "field 'btn_add_installment'", com.rey.material.widget.Button.class));
    layout_ScrollView = ((ScrollView)Collection4.replace(paramView, 2131296558, "field 'layout_ScrollView'", ScrollView.class));
    btn_add_honorarium = ((com.joanzapata.material.widget.Button)Collection4.replace(paramView, 2131296343, "field 'btn_add_honorarium'", com.rey.material.widget.Button.class));
    recyclerView = ((RecyclerView)Collection4.replace(paramView, 2131296684, "field 'recyclerView'", RecyclerView.class));
  }
}
