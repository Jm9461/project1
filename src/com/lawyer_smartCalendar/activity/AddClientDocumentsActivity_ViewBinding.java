package com.lawyer_smartCalendar.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Collection4;

public class AddClientDocumentsActivity_ViewBinding
  implements Unbinder
{
  public AddClientDocumentsActivity_ViewBinding(AddClientDocumentsActivity paramAddClientDocumentsActivity, View paramView)
  {
    input_client_name = ((EditText)Collection4.replace(paramView, 2131296504, "field 'input_client_name'", EditText.class));
    input_case = ((EditText)Collection4.replace(paramView, 2131296493, "field 'input_case'", EditText.class));
    input_document_type = ((EditText)Collection4.replace(paramView, 2131296518, "field 'input_document_type'", EditText.class));
    input_date_received = ((EditText)Collection4.replace(paramView, 2131296515, "field 'input_date_received'", EditText.class));
    input_date_delivered = ((EditText)Collection4.replace(paramView, 2131296513, "field 'input_date_delivered'", EditText.class));
    input_document_note = ((EditText)Collection4.replace(paramView, 2131296517, "field 'input_document_note'", EditText.class));
    img_clear_date = ((ImageView)Collection4.replace(paramView, 2131296476, "field 'img_clear_date'", ImageView.class));
    btn_add_document = ((com.joanzapata.material.widget.Button)Collection4.replace(paramView, 2131296342, "field 'btn_add_document'", com.rey.material.widget.Button.class));
    btn_add_image = ((com.joanzapata.material.widget.Button)Collection4.replace(paramView, 2131296344, "field 'btn_add_image'", com.rey.material.widget.Button.class));
    recyclerView = ((RecyclerView)Collection4.replace(paramView, 2131296684, "field 'recyclerView'", RecyclerView.class));
  }
}
