package ch.acra.widget;

import android.view.View;
import java.lang.ref.WeakReference;

public class Target
{
  public final int name;
  public final int port;
  public final WeakReference<View> target;
  
  public Target(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, WeakReference paramWeakReference)
  {
    name = paramInt1;
    port = paramInt2;
    target = paramWeakReference;
  }
  
  public View getTarget()
  {
    return (View)target.get();
  }
}
