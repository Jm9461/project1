package okhttp;

import e.k;
import e.w;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp.internal.Util;

public final class Address
{
  final CertificatePinner certificatePinner;
  final List<k> connectionSpecs;
  final Dns dns;
  final HostnameVerifier hostnameVerifier;
  final List<w> protocols;
  final Proxy proxy;
  final Authenticator proxyAuthenticator;
  final ProxySelector proxySelector;
  final SocketFactory socketFactory;
  final SSLSocketFactory sslSocketFactory;
  final HttpUrl url;
  
  public Address(String paramString, int paramInt, Dns paramDns, SocketFactory paramSocketFactory, SSLSocketFactory paramSSLSocketFactory, HostnameVerifier paramHostnameVerifier, CertificatePinner paramCertificatePinner, Authenticator paramAuthenticator, Proxy paramProxy, List paramList1, List paramList2, ProxySelector paramProxySelector)
  {
    HttpUrl.Builder localBuilder = new HttpUrl.Builder();
    String str;
    if (paramSSLSocketFactory != null) {
      str = "https";
    } else {
      str = "http";
    }
    localBuilder.scheme(str);
    localBuilder.host(paramString);
    localBuilder.port(paramInt);
    url = localBuilder.build();
    if (paramDns != null)
    {
      dns = paramDns;
      if (paramSocketFactory != null)
      {
        socketFactory = paramSocketFactory;
        if (paramAuthenticator != null)
        {
          proxyAuthenticator = paramAuthenticator;
          if (paramList1 != null)
          {
            protocols = Util.immutableList(paramList1);
            if (paramList2 != null)
            {
              connectionSpecs = Util.immutableList(paramList2);
              if (paramProxySelector != null)
              {
                proxySelector = paramProxySelector;
                proxy = paramProxy;
                sslSocketFactory = paramSSLSocketFactory;
                hostnameVerifier = paramHostnameVerifier;
                certificatePinner = paramCertificatePinner;
                return;
              }
              throw new NullPointerException("proxySelector == null");
            }
            throw new NullPointerException("connectionSpecs == null");
          }
          throw new NullPointerException("protocols == null");
        }
        throw new NullPointerException("proxyAuthenticator == null");
      }
      throw new NullPointerException("socketFactory == null");
    }
    throw new NullPointerException("dns == null");
  }
  
  public Dns dns()
  {
    return dns;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof Address)) && (url.equals(url)) && (equals((Address)paramObject));
  }
  
  boolean equals(Address paramAddress)
  {
    return (dns.equals(dns)) && (proxyAuthenticator.equals(proxyAuthenticator)) && (protocols.equals(protocols)) && (connectionSpecs.equals(connectionSpecs)) && (proxySelector.equals(proxySelector)) && (Util.get(proxy, proxy)) && (Util.get(sslSocketFactory, sslSocketFactory)) && (Util.get(hostnameVerifier, hostnameVerifier)) && (Util.get(certificatePinner, certificatePinner)) && (url().port() == paramAddress.url().port());
  }
  
  public CertificatePinner getCertificatePinner()
  {
    return certificatePinner;
  }
  
  public List getConnectionSpecs()
  {
    return connectionSpecs;
  }
  
  public HostnameVerifier getHostnameVerifier()
  {
    return hostnameVerifier;
  }
  
  public List getProtocols()
  {
    return protocols;
  }
  
  public Proxy getProxy()
  {
    return proxy;
  }
  
  public ProxySelector getProxySelector()
  {
    return proxySelector;
  }
  
  public SocketFactory getSocketFactory()
  {
    return socketFactory;
  }
  
  public SSLSocketFactory getSslSocketFactory()
  {
    return sslSocketFactory;
  }
  
  public int hashCode()
  {
    int n = url.hashCode();
    int i1 = dns.hashCode();
    int i2 = proxyAuthenticator.hashCode();
    int i3 = protocols.hashCode();
    int i4 = connectionSpecs.hashCode();
    int i5 = proxySelector.hashCode();
    Object localObject = proxy;
    int m = 0;
    int i;
    if (localObject != null) {
      i = ((Proxy)localObject).hashCode();
    } else {
      i = 0;
    }
    localObject = sslSocketFactory;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    localObject = hostnameVerifier;
    int k;
    if (localObject != null) {
      k = localObject.hashCode();
    } else {
      k = 0;
    }
    localObject = certificatePinner;
    if (localObject != null) {
      m = ((CertificatePinner)localObject).hashCode();
    }
    return (((((((((17 * 31 + n) * 31 + i1) * 31 + i2) * 31 + i3) * 31 + i4) * 31 + i5) * 31 + i) * 31 + j) * 31 + k) * 31 + m;
  }
  
  public Authenticator proxyAuthenticator()
  {
    return proxyAuthenticator;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Address{");
    localStringBuilder.append(url.host());
    localStringBuilder.append(":");
    localStringBuilder = localStringBuilder.append(url.port());
    if (proxy != null)
    {
      localStringBuilder.append(", proxy=");
      localStringBuilder.append(proxy);
    }
    else
    {
      localStringBuilder.append(", proxySelector=");
      localStringBuilder.append(proxySelector);
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public HttpUrl url()
  {
    return url;
  }
}
