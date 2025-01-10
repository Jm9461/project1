package okhttp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

public abstract class HttpHost
{
  public static final HttpHost host = new ResponseSource();
  
  public HttpHost() {}
  
  static List create(HttpHost paramHttpHost)
  {
    return new OverlayList(paramHttpHost);
  }
  
  public void add(Map paramMap) {}
  
  public void add(Map paramMap, IOException paramIOException) {}
  
  public void append(Map paramMap) {}
  
  public void append(Map paramMap, String paramString) {}
  
  public void append(Map paramMap, String paramString, java.util.List paramList) {}
  
  public void append(Map paramMap, InetSocketAddress paramInetSocketAddress, Proxy paramProxy, Protocol paramProtocol) {}
  
  public void clear(Map paramMap) {}
  
  public void connect(Map paramMap, InetSocketAddress paramInetSocketAddress, Proxy paramProxy) {}
  
  public void create(Map paramMap) {}
  
  public void create(Map paramMap, InetSocketAddress paramInetSocketAddress, Proxy paramProxy, Protocol paramProtocol, IOException paramIOException) {}
  
  public void create(Map paramMap, Handshake paramHandshake) {}
  
  public void delete(Map paramMap) {}
  
  public void init(Map paramMap) {}
  
  public void init(Map paramMap, long paramLong) {}
  
  public void init(Map paramMap, Request paramRequest) {}
  
  public void put(Map paramMap, Response paramResponse) {}
  
  public void put(Map paramMap, URI paramURI) {}
  
  public void toString(Map paramMap) {}
  
  public void toString(Map paramMap, long paramLong) {}
  
  public void toString(Map paramMap, URI paramURI) {}
}
