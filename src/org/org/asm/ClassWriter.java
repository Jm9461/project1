package org.org.asm;

import android.graphics.PointF;
import android.view.View;

class ClassWriter
{
  private int b;
  private int d;
  private int h;
  private View v;
  private int w;
  private int x;
  private int y;
  
  ClassWriter(View paramView)
  {
    v = paramView;
  }
  
  private void a()
  {
    a.set(v, w, h, y, x);
    b = 0;
    d = 0;
  }
  
  void a(PointF paramPointF)
  {
    y = Math.round(x);
    x = Math.round(y);
    d += 1;
    if (b == d) {
      a();
    }
  }
  
  void b(PointF paramPointF)
  {
    w = Math.round(x);
    h = Math.round(y);
    b += 1;
    if (b == d) {
      a();
    }
  }
}
