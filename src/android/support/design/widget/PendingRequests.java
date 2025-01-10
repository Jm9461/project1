package android.support.design.widget;

import android.os.Handler.Callback;
import android.os.Message;

class PendingRequests
  implements Handler.Callback
{
  PendingRequests(d paramD) {}
  
  public boolean handleMessage(Message paramMessage)
  {
    if (what != 0) {
      return false;
    }
    progress.a((h)obj);
    return true;
  }
}
