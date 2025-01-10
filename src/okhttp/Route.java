package okhttp;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

public final class Route
{
  final Address address;
  final InetSocketAddress inetSocketAddress;
  final Proxy proxy;
  
  public Route(Address paramAddress, Proxy paramProxy, InetSocketAddress paramInetSocketAddress)
  {
    if (paramAddress != null)
    {
      if (paramProxy != null)
      {
        if (paramInetSocketAddress != null)
        {
          address = paramAddress;
          proxy = paramProxy;
          inetSocketAddress = paramInetSocketAddress;
          return;
        }
        throw new NullPointerException("inetSocketAddress == null");
      }
      throw new NullPointerException("proxy == null");
    }
    throw new NullPointerException("address == null");
  }
  
  public boolean equals()
  {
    return (address.sslSocketFactory != null) && (proxy.type() == Proxy.Type.HTTP);
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof Route)) && (address.equals(address)) && (proxy.equals(proxy)) && (inetSocketAddress.equals(inetSocketAddress));
  }
  
  public Address getAddress()
  {
    return address;
  }
  
  public InetSocketAddress getId()
  {
    return inetSocketAddress;
  }
  
  public Proxy getProxy()
  {
    return proxy;
  }
  
  public int hashCode()
  {
    return ((17 * 31 + address.hashCode()) * 31 + proxy.hashCode()) * 31 + inetSocketAddress.hashCode();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Route{");
    localStringBuilder.append(inetSocketAddress);
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
}
