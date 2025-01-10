package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.support.v4.graphics.drawable.IconCompat;
import androidx.versionedparcelable.h;

public class IconCompatParcelizer
{
  public IconCompatParcelizer() {}
  
  public static IconCompat read(h paramH)
  {
    IconCompat localIconCompat = new IconCompat();
    c = paramH.add(c, 1);
    a = paramH.a(a, 2);
    x = paramH.d(x, 3);
    b = paramH.add(b, 4);
    p = paramH.add(p, 5);
    g = ((ColorStateList)paramH.d(g, 6));
    s = paramH.a(s, 7);
    localIconCompat.init();
    return localIconCompat;
  }
  
  public static void write(IconCompat paramIconCompat, h paramH)
  {
    paramH.a(true, true);
    paramIconCompat.encode(paramH.processBytes());
    paramH.a(c, 1);
    paramH.c(a, 2);
    paramH.a(x, 3);
    paramH.a(b, 4);
    paramH.a(p, 5);
    paramH.a(g, 6);
    paramH.b(s, 7);
  }
}
