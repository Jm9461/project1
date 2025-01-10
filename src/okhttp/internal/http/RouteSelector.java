package okhttp.internal.http;

import e.c0;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp.Address;
import okhttp.Dns;
import okhttp.HttpHost;
import okhttp.HttpUrl;
import okhttp.Map;
import okhttp.Route;
import okhttp.internal.Util;

public final class RouteSelector
{
  private final Address address;
  private final List<c0> data = new ArrayList();
  private List<InetSocketAddress> inetSocketAddresses = Collections.emptyList();
  private final Map network;
  private int nextProxyIndex;
  private List<Proxy> proxies = Collections.emptyList();
  private final RouteDatabase routeDatabase;
  private final HttpHost uri;
  
  public RouteSelector(Address paramAddress, RouteDatabase paramRouteDatabase, Map paramMap, HttpHost paramHttpHost)
  {
    address = paramAddress;
    routeDatabase = paramRouteDatabase;
    network = paramMap;
    uri = paramHttpHost;
    resetNextProxy(paramAddress.url(), paramAddress.getProxy());
  }
  
  static String getHostString(InetSocketAddress paramInetSocketAddress)
  {
    InetAddress localInetAddress = paramInetSocketAddress.getAddress();
    if (localInetAddress == null) {
      return paramInetSocketAddress.getHostName();
    }
    return localInetAddress.getHostAddress();
  }
  
  private boolean hasNextProxy()
  {
    return nextProxyIndex < proxies.size();
  }
  
  private Proxy nextProxy()
  {
    if (hasNextProxy())
    {
      localObject = proxies;
      int i = nextProxyIndex;
      nextProxyIndex = (i + 1);
      localObject = (Proxy)((List)localObject).get(i);
      resetNextInetSocketAddress((Proxy)localObject);
      return localObject;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("No route to ");
    ((StringBuilder)localObject).append(address.url().host());
    ((StringBuilder)localObject).append("; exhausted proxy configurations: ");
    ((StringBuilder)localObject).append(proxies);
    throw new SocketException(((StringBuilder)localObject).toString());
  }
  
  private void resetNextInetSocketAddress(Proxy paramProxy)
  {
    inetSocketAddresses = new ArrayList();
    Object localObject;
    int i;
    if ((paramProxy.type() != Proxy.Type.DIRECT) && (paramProxy.type() != Proxy.Type.SOCKS))
    {
      localObject = paramProxy.address();
      if ((localObject instanceof InetSocketAddress))
      {
        InetSocketAddress localInetSocketAddress = (InetSocketAddress)localObject;
        localObject = getHostString(localInetSocketAddress);
        i = localInetSocketAddress.getPort();
      }
      else
      {
        paramProxy = new StringBuilder();
        paramProxy.append("Proxy.address() is not an InetSocketAddress: ");
        paramProxy.append(localObject.getClass());
        throw new IllegalArgumentException(paramProxy.toString());
      }
    }
    else
    {
      localObject = address.url().host();
      i = address.url().port();
    }
    if ((i >= 1) && (i <= 65535))
    {
      if (paramProxy.type() == Proxy.Type.SOCKS)
      {
        inetSocketAddresses.add(InetSocketAddress.createUnresolved((String)localObject, i));
        return;
      }
      uri.append(network, (String)localObject);
      paramProxy = address.dns().lookup((String)localObject);
      if (!paramProxy.isEmpty())
      {
        uri.append(network, (String)localObject, paramProxy);
        int j = 0;
        int k = paramProxy.size();
        while (j < k)
        {
          localObject = (InetAddress)paramProxy.get(j);
          inetSocketAddresses.add(new InetSocketAddress((InetAddress)localObject, i));
          j += 1;
        }
        return;
      }
      paramProxy = new StringBuilder();
      paramProxy.append(address.dns());
      paramProxy.append(" returned no addresses for ");
      paramProxy.append((String)localObject);
      throw new UnknownHostException(paramProxy.toString());
    }
    paramProxy = new StringBuilder();
    paramProxy.append("No route to ");
    paramProxy.append((String)localObject);
    paramProxy.append(":");
    paramProxy.append(i);
    paramProxy.append("; port is out of range");
    paramProxy = new SocketException(paramProxy.toString());
    throw paramProxy;
  }
  
  private void resetNextProxy(HttpUrl paramHttpUrl, Proxy paramProxy)
  {
    if (paramProxy != null)
    {
      proxies = Collections.singletonList(paramProxy);
    }
    else
    {
      paramHttpUrl = address.getProxySelector().select(paramHttpUrl.uri());
      if ((paramHttpUrl != null) && (!paramHttpUrl.isEmpty())) {
        paramHttpUrl = Util.immutableList(paramHttpUrl);
      } else {
        paramHttpUrl = Util.immutableList(new Proxy[] { Proxy.NO_PROXY });
      }
      proxies = paramHttpUrl;
    }
    nextProxyIndex = 0;
  }
  
  public void connectFailed(Route paramRoute, IOException paramIOException)
  {
    if ((paramRoute.getProxy().type() != Proxy.Type.DIRECT) && (address.getProxySelector() != null)) {
      address.getProxySelector().connectFailed(address.url().uri(), paramRoute.getProxy().address(), paramIOException);
    }
    routeDatabase.failed(paramRoute);
  }
  
  public boolean hasNext()
  {
    return (hasNextProxy()) || (!data.isEmpty());
  }
  
  public Iterator next()
  {
    if (hasNext())
    {
      localObject = new ArrayList();
      while (hasNextProxy())
      {
        Proxy localProxy = nextProxy();
        int i = 0;
        int j = inetSocketAddresses.size();
        while (i < j)
        {
          Route localRoute = new Route(address, localProxy, (InetSocketAddress)inetSocketAddresses.get(i));
          if (routeDatabase.shouldPostpone(localRoute)) {
            data.add(localRoute);
          } else {
            ((List)localObject).add(localRoute);
          }
          i += 1;
        }
        if (!((List)localObject).isEmpty()) {
          break;
        }
      }
      if (((List)localObject).isEmpty())
      {
        ((List)localObject).addAll(data);
        data.clear();
      }
      return new Iterator((List)localObject);
    }
    Object localObject = new NoSuchElementException();
    throw ((Throwable)localObject);
  }
}
