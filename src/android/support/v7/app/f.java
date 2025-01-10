package android.support.v7.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

final class f
{
  private TwilightManager b;
  private boolean c;
  private BroadcastReceiver v;
  private IntentFilter x;
  
  f(AppCompatDelegateImplV7 paramAppCompatDelegateImplV7, TwilightManager paramTwilightManager)
  {
    b = paramTwilightManager;
    c = paramTwilightManager.isNight();
  }
  
  void a()
  {
    BroadcastReceiver localBroadcastReceiver = v;
    if (localBroadcastReceiver != null)
    {
      a.mContext.unregisterReceiver(localBroadcastReceiver);
      v = null;
    }
  }
  
  void add()
  {
    a();
    if (v == null) {
      v = new h.l.a(this);
    }
    if (x == null)
    {
      x = new IntentFilter();
      x.addAction("android.intent.action.TIME_SET");
      x.addAction("android.intent.action.TIMEZONE_CHANGED");
      x.addAction("android.intent.action.TIME_TICK");
    }
    a.mContext.registerReceiver(v, x);
  }
  
  int b()
  {
    c = b.isNight();
    if (c) {
      return 2;
    }
    return 1;
  }
  
  void d()
  {
    boolean bool = b.isNight();
    if (bool != c)
    {
      c = bool;
      a.a();
    }
  }
}
