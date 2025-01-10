package com.miguelcatalan.materialsearchview.widget;

import android.view.View;

public abstract interface ToolbarWidgetWrapper
{
  public abstract boolean get(View paramView);
  
  public abstract boolean playSound(View paramView);
  
  public abstract boolean setVisible(View paramView);
}
