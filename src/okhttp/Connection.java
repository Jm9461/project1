package okhttp;

public abstract interface Connection
{
  public abstract int close();
  
  public abstract Request execute();
  
  public abstract int flush();
  
  public abstract Response get(Request paramRequest);
  
  public abstract int getProtocol();
}
