package android.support.design.widget;

import android.support.v4.view.accessibility.ClassWriter;

class Alarm
  implements ClassWriter
{
  Alarm(TimePicker paramTimePicker) {}
  
  public void onTouchExplorationStateChanged(boolean paramBoolean)
  {
    TimePicker.setEnabled(enabled, paramBoolean);
  }
}
