package com.lawyer_smartCalendar.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Collection4;

public class AddNoteActivity_ViewBinding
  implements Unbinder
{
  public AddNoteActivity_ViewBinding(AddNoteActivity paramAddNoteActivity, View paramView)
  {
    TextInputLayout_client = ((LinearLayout)Collection4.replace(paramView, 2131296268, "field 'TextInputLayout_client'", LinearLayout.class));
    TextInputLayout_case = ((LinearLayout)Collection4.replace(paramView, 2131296267, "field 'TextInputLayout_case'", LinearLayout.class));
    input_note_title = ((EditText)Collection4.replace(paramView, 2131296536, "field 'input_note_title'", EditText.class));
    input_client_name = ((EditText)Collection4.replace(paramView, 2131296504, "field 'input_client_name'", EditText.class));
    input_case = ((EditText)Collection4.replace(paramView, 2131296493, "field 'input_case'", EditText.class));
    input_note_type = ((EditText)Collection4.replace(paramView, 2131296537, "field 'input_note_type'", EditText.class));
    input_note_date = ((EditText)Collection4.replace(paramView, 2131296535, "field 'input_note_date'", EditText.class));
    input_date_hour = ((EditText)Collection4.replace(paramView, 2131296514, "field 'input_date_hour'", EditText.class));
    input_note_body = ((EditText)Collection4.replace(paramView, 2131296534, "field 'input_note_body'", EditText.class));
    btn_add_note = ((com.joanzapata.material.widget.Button)Collection4.replace(paramView, 2131296346, "field 'btn_add_note'", com.rey.material.widget.Button.class));
    btn_add_audio = ((com.joanzapata.material.widget.Button)Collection4.replace(paramView, 2131296339, "field 'btn_add_audio'", com.rey.material.widget.Button.class));
    recyclerView = ((RecyclerView)Collection4.replace(paramView, 2131296684, "field 'recyclerView'", RecyclerView.class));
  }
}
