package apps.objectweb.asm;

import java.util.Timer;

public abstract class b<TData>
{
  private float[] a;
  private boolean b;
  private float[] c;
  private Item g;
  private int l;
  private float[] m;
  private Timer t;
  
  public b() {}
  
  private void close()
  {
    Timer localTimer = t;
    if (localTimer != null)
    {
      localTimer.cancel();
      t.purge();
      t = null;
    }
  }
  
  public void a()
  {
    b = true;
    a = null;
    m = null;
    g = null;
  }
  
  void a(Item paramItem, int paramInt)
  {
    g = paramItem;
    l = paramInt;
    a = new float[paramInt];
    m = new float[paramInt];
    c = new float[paramInt];
  }
  
  public final void a(Object paramObject)
  {
    if (b) {
      return;
    }
    a(paramObject, l, a, m);
    g.a(a, m);
    b();
  }
  
  protected abstract void a(Object paramObject, int paramInt, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2);
  
  protected final void b()
  {
    close();
    g.setRenderMode();
  }
  
  public void onClick() {}
  
  public void onCloseMenu() {}
  
  protected final void onCreate()
  {
    if (t == null)
    {
      t = new Timer("Stop Rendering Timer");
      t.scheduleAtFixedRate(new MainActivity.3(this), 16L, 16L);
    }
    g.load(new EditActivity.4(this));
  }
  
  protected final void run()
  {
    close();
    g.apply();
  }
}
