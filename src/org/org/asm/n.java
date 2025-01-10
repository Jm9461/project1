package org.org.asm;

import android.os.Build.VERSION;
import android.view.ViewGroup;

class n
{
  static g a(ViewGroup paramViewGroup)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      return new MethodWriter(paramViewGroup);
    }
    return q.a(paramViewGroup);
  }
  
  static void a(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 18)
    {
      AnnotationWriter.a(paramViewGroup, paramBoolean);
      return;
    }
    DHGEX.init(paramViewGroup, paramBoolean);
  }
}
