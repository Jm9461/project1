package org.org.jaxen.ui;

import a.b.g.e.b.g;
import android.graphics.Typeface;
import java.util.concurrent.Callable;
import org.org.jaxen.util.LruCache;

final class Context
  implements Callable<b.g>
{
  Context(android.content.Context paramContext, h paramH, int paramInt, String paramString) {}
  
  public e call()
  {
    e localE = b.a(i, e, p);
    Typeface localTypeface = b;
    if (localTypeface != null) {
      b.g.put(version, localTypeface);
    }
    return localE;
  }
}
