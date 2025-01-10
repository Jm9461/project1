package com.lawyer_smartCalendar.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Collection4;

public class SettingActivity_ViewBinding
  implements Unbinder
{
  public SettingActivity_ViewBinding(SettingActivity paramSettingActivity, View paramView)
  {
    btn_change_color = ((com.joanzapata.material.widget.Button)Collection4.replace(paramView, 2131296350, "field 'btn_change_color'", com.rey.material.widget.Button.class));
    btn_backup = ((com.joanzapata.material.widget.Button)Collection4.replace(paramView, 2131296349, "field 'btn_backup'", com.rey.material.widget.Button.class));
    btn_restore = ((com.joanzapata.material.widget.Button)Collection4.replace(paramView, 2131296355, "field 'btn_restore'", com.rey.material.widget.Button.class));
  }
}
