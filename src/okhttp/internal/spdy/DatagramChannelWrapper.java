package okhttp.internal.spdy;

import java.io.IOException;

public final class DatagramChannelWrapper
  extends IOException
{
  public final ErrorCode address;
  
  public DatagramChannelWrapper(ErrorCode paramErrorCode)
  {
    super(localStringBuilder.toString());
    address = paramErrorCode;
  }
}
