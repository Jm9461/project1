package okhttp;

import java.io.IOException;

public abstract interface ByteVector
{
  public abstract void add(Map paramMap, IOException paramIOException);
  
  public abstract void put(Map paramMap, Response paramResponse);
}
