package okhttp.internal.http;

import e.f0.f.g;
import java.lang.ref.WeakReference;

public final class ConnectionPool
  extends WeakReference<g>
{
  public final Object logger;
  
  ConnectionPool(StreamAllocation paramStreamAllocation, Object paramObject)
  {
    super(paramStreamAllocation);
    logger = paramObject;
  }
}
