package com.lawyer_smartCalendar.activity;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.Collection4;

public class FirstProfileActivity_ViewBinding
  implements Unbinder
{
  public FirstProfileActivity_ViewBinding(FirstProfileActivity paramFirstProfileActivity, View paramView)
  {
    editText_name = ((EditText)Collection4.replace(paramView, 2131296428, "field 'editText_name'", EditText.class));
    editText_phone = ((EditText)Collection4.replace(paramView, 2131296429, "field 'editText_phone'", EditText.class));
    editText_landline_phone = ((EditText)Collection4.replace(paramView, 2131296427, "field 'editText_landline_phone'", EditText.class));
    editText_email = ((EditText)Collection4.replace(paramView, 2131296426, "field 'editText_email'", EditText.class));
    editText_address = ((EditText)Collection4.replace(paramView, 2131296425, "field 'editText_address'", EditText.class));
    img_add_avatar = ((FloatingActionButton)Collection4.replace(paramView, 2131296474, "field 'img_add_avatar'", FloatingActionButton.class));
    radio_lagalBase_one = ((RadioButton)Collection4.replace(paramView, 2131296675, "field 'radio_lagalBase_one'", RadioButton.class));
    radio_lagalBase_two = ((RadioButton)Collection4.replace(paramView, 2131296676, "field 'radio_lagalBase_two'", RadioButton.class));
    btn_edit_profile = ((com.joanzapata.material.widget.Button)Collection4.replace(paramView, 2131296352, "field 'btn_edit_profile'", com.rey.material.widget.Button.class));
    img_lawyer_pic = ((ImageView)Collection4.replace(paramView, 2131296478, "field 'img_lawyer_pic'", ImageView.class));
  }
}
