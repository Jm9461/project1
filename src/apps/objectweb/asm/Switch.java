package apps.objectweb.asm;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

class Switch
{
  int a;
  float b;
  int c;
  float f;
  float[][] i;
  int k;
  boolean p;
  float s;
  float[] y;
  
  private Switch(f paramF)
  {
    f = f.b(paramF);
    f = Label.set(f, 10.0F, 1920.0F);
    c = f.a(paramF);
    c = Label.a(c, 1, 16);
    i = paramF.b();
    s = f.d(paramF);
    s = Label.set(s, 10.0F, 200.0F);
    s /= fgetResourcesgetDisplayMetricswidthPixels;
    b = f.e(paramF);
    b = Label.set(b, 20.0F, 1080.0F);
    p = f.add(paramF);
    y = paramF.getValue();
    a = f.i(paramF);
    k = f.getItem(paramF);
    Label.a(k, 1, 36);
    a = Label.a(a, 1, 4);
    if (i.length >= a) {
      return;
    }
    throw new IllegalArgumentException("You specified more layers than colors.");
  }
}
