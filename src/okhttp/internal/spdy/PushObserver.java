package okhttp.internal.spdy;

import java.util.List;
import okio.BufferedSource;

public abstract interface PushObserver
{
  public static final PushObserver CANCEL = new PushObserver()
  {
    public boolean onData(int paramAnonymousInt1, BufferedSource paramAnonymousBufferedSource, int paramAnonymousInt2, boolean paramAnonymousBoolean)
    {
      paramAnonymousBufferedSource.skip(paramAnonymousInt2);
      return true;
    }
    
    public boolean onHeaders(int paramAnonymousInt, List paramAnonymousList, boolean paramAnonymousBoolean)
    {
      return true;
    }
    
    public boolean onRequest(int paramAnonymousInt, List paramAnonymousList)
    {
      return true;
    }
    
    public void onReset(int paramAnonymousInt, ErrorCode paramAnonymousErrorCode) {}
  };
  
  public abstract boolean onData(int paramInt1, BufferedSource paramBufferedSource, int paramInt2, boolean paramBoolean);
  
  public abstract boolean onHeaders(int paramInt, List paramList, boolean paramBoolean);
  
  public abstract boolean onRequest(int paramInt, List paramList);
  
  public abstract void onReset(int paramInt, ErrorCode paramErrorCode);
}
