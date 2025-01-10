package org.org.asm;

import android.view.View;
import android.view.ViewGroup;

public class b
{
  private ViewGroup g;
  private Runnable q;
  
  static b a(View paramView)
  {
    return (b)paramView.getTag(R.id.transition_current_scene);
  }
  
  static void a(View paramView, b paramB)
  {
    paramView.setTag(R.id.transition_current_scene, paramB);
  }
  
  public void a()
  {
    if (a(g) == this)
    {
      Runnable localRunnable = q;
      if (localRunnable != null) {
        localRunnable.run();
      }
    }
  }
}
