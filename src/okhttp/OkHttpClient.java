package okhttp;

import e.k;
import e.t;
import e.w;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp.internal.Internal;
import okhttp.internal.Util;
import okhttp.internal.http.RealConnection;
import okhttp.internal.http.RouteDatabase;
import okhttp.internal.http.StreamAllocation;
import okhttp.internal.internal.Platform;
import okhttp.internal.okhttp.InternalCache;
import okhttp.internal.tls.OkHostnameVerifier;
import okhttp.internal.tls.Resource;

public class OkHttpClient
  implements Cloneable, OkResponseCache, Call.Factory
{
  static final java.util.List<k> DEFAULT_CONNECTION_SPECS;
  static final java.util.List<w> DEFAULT_PROTOCOLS = Util.immutableList(new Protocol[] { Protocol.HTTP_2, Protocol.HTTP_1_1 });
  final Authenticator authenticator;
  final Cache cache;
  final CertificatePinner certificatePinner;
  final int connectTimeout;
  final ConnectionPool connectionPool;
  final java.util.List<k> connectionSpecs;
  final CookieJar cookieJar;
  final java.util.List<t> delegate;
  final Dispatcher dispatcher;
  final Dns dns;
  final boolean followRedirects;
  final boolean followSslRedirects;
  final HostnameVerifier hostnameVerifier;
  final InternalCache internalCache;
  final java.util.List<t> key;
  final int length;
  final List networkInterceptors;
  final java.util.List<w> protocols;
  final Proxy proxy;
  final ProxySelector proxySelector;
  final int readTimeout;
  final boolean retryOnConnectionFailure;
  final SocketFactory socketFactory;
  final SSLSocketFactory sslSocketFactory;
  final Authenticator transports;
  final Resource trustRootIndex;
  final int writeTimeout;
  
  static
  {
    DEFAULT_CONNECTION_SPECS = Util.immutableList(new ConnectionSpec[] { ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT });
    Internal.instance = new Internal()
    {
      public void add(Headers.Builder paramAnonymousBuilder, String paramAnonymousString1, String paramAnonymousString2)
      {
        paramAnonymousBuilder.addLenient(paramAnonymousString1, paramAnonymousString2);
      }
      
      public void addLenient(Headers.Builder paramAnonymousBuilder, String paramAnonymousString)
      {
        paramAnonymousBuilder.addLenient(paramAnonymousString);
      }
      
      public void apply(ConnectionSpec paramAnonymousConnectionSpec, SSLSocket paramAnonymousSSLSocket, boolean paramAnonymousBoolean)
      {
        paramAnonymousConnectionSpec.apply(paramAnonymousSSLSocket, paramAnonymousBoolean);
      }
      
      public boolean connectionBecameIdle(ConnectionPool paramAnonymousConnectionPool, RealConnection paramAnonymousRealConnection)
      {
        return paramAnonymousConnectionPool.connectionBecameIdle(paramAnonymousRealConnection);
      }
      
      public int get(a0.a paramAnonymousA)
      {
        return code;
      }
      
      public Socket get(ConnectionPool paramAnonymousConnectionPool, Address paramAnonymousAddress, StreamAllocation paramAnonymousStreamAllocation)
      {
        return paramAnonymousConnectionPool.get(paramAnonymousAddress, paramAnonymousStreamAllocation);
      }
      
      public RealConnection get(ConnectionPool paramAnonymousConnectionPool, Address paramAnonymousAddress, StreamAllocation paramAnonymousStreamAllocation, Route paramAnonymousRoute)
      {
        return paramAnonymousConnectionPool.get(paramAnonymousAddress, paramAnonymousStreamAllocation, paramAnonymousRoute);
      }
      
      public boolean get(Address paramAnonymousAddress1, Address paramAnonymousAddress2)
      {
        return paramAnonymousAddress1.equals(paramAnonymousAddress2);
      }
      
      public void put(ConnectionPool paramAnonymousConnectionPool, RealConnection paramAnonymousRealConnection)
      {
        paramAnonymousConnectionPool.put(paramAnonymousRealConnection);
      }
      
      public RouteDatabase routeDatabase(ConnectionPool paramAnonymousConnectionPool)
      {
        return routeDatabase;
      }
    };
  }
  
  public OkHttpClient()
  {
    this(new Builder());
  }
  
  OkHttpClient(Builder paramBuilder)
  {
    dispatcher = dispatcher;
    proxy = proxy;
    protocols = protocols;
    connectionSpecs = connectionSpecs;
    key = Util.immutableList(interceptors);
    delegate = Util.immutableList(connection);
    networkInterceptors = networkInterceptors;
    proxySelector = proxySelector;
    cookieJar = cookieJar;
    cache = cache;
    internalCache = internalCache;
    socketFactory = socketFactory;
    int i = 0;
    Object localObject = connectionSpecs.iterator();
    while (((Iterator)localObject).hasNext())
    {
      ConnectionSpec localConnectionSpec = (ConnectionSpec)((Iterator)localObject).next();
      if ((i == 0) && (!localConnectionSpec.isTls())) {
        i = 0;
      } else {
        i = 1;
      }
    }
    if ((sslSocketFactory == null) && (i != 0))
    {
      localObject = init();
      sslSocketFactory = getSocketFactory((X509TrustManager)localObject);
      trustRootIndex = Resource.get((X509TrustManager)localObject);
    }
    else
    {
      sslSocketFactory = sslSocketFactory;
      trustRootIndex = trustRootIndex;
    }
    hostnameVerifier = hostnameVerifier;
    certificatePinner = certificatePinner.check(trustRootIndex);
    authenticator = authenticator;
    transports = connectionPool;
    connectionPool = dns;
    dns = SYSTEM;
    retryOnConnectionFailure = retryOnConnectionFailure;
    followSslRedirects = followSslRedirects;
    followRedirects = followRedirects;
    connectTimeout = connectTimeout;
    readTimeout = readTimeout;
    writeTimeout = writeTimeout;
    length = size;
    if (!key.contains(null))
    {
      if (!delegate.contains(null)) {
        return;
      }
      paramBuilder = new StringBuilder();
      paramBuilder.append("Null network interceptor: ");
      paramBuilder.append(delegate);
      throw new IllegalStateException(paramBuilder.toString());
    }
    paramBuilder = new StringBuilder();
    paramBuilder.append("Null interceptor: ");
    paramBuilder.append(key);
    paramBuilder = new IllegalStateException(paramBuilder.toString());
    throw paramBuilder;
  }
  
  private SSLSocketFactory getSocketFactory(X509TrustManager paramX509TrustManager)
  {
    try
    {
      SSLContext localSSLContext = Platform.get().createDefault();
      paramX509TrustManager = (TrustManager[])new TrustManager[] { paramX509TrustManager };
      localSSLContext.init(null, paramX509TrustManager, null);
      paramX509TrustManager = localSSLContext.getSocketFactory();
      return paramX509TrustManager;
    }
    catch (GeneralSecurityException paramX509TrustManager)
    {
      throw Util.close("No System TLS", paramX509TrustManager);
    }
  }
  
  private X509TrustManager init()
  {
    try
    {
      Object localObject = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      ((TrustManagerFactory)localObject).init(null);
      localObject = ((TrustManagerFactory)localObject).getTrustManagers();
      if ((localObject.length == 1) && ((localObject[0] instanceof X509TrustManager))) {
        return (X509TrustManager)localObject[0];
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unexpected default trust managers:");
      localStringBuilder.append(Arrays.toString((Object[])localObject));
      localObject = new IllegalStateException(localStringBuilder.toString());
      throw ((Throwable)localObject);
    }
    catch (GeneralSecurityException localGeneralSecurityException)
    {
      throw Util.close("No System TLS", localGeneralSecurityException);
    }
  }
  
  public Authenticator authenticator()
  {
    return transports;
  }
  
  public CookieJar cookieJar()
  {
    return cookieJar;
  }
  
  public Dns dns()
  {
    return dns;
  }
  
  public boolean followRedirects()
  {
    return followRedirects;
  }
  
  public java.util.List get()
  {
    return delegate;
  }
  
  public Map get(Request paramRequest)
  {
    return ClassWriter.get(this, paramRequest, false);
  }
  
  public Authenticator getAuthenticator()
  {
    return authenticator;
  }
  
  public Cache getCache()
  {
    return cache;
  }
  
  public CertificatePinner getCertificatePinner()
  {
    return certificatePinner;
  }
  
  public int getConnectTimeout()
  {
    return connectTimeout;
  }
  
  public ConnectionPool getConnectionPool()
  {
    return connectionPool;
  }
  
  public java.util.List getConnectionSpecs()
  {
    return connectionSpecs;
  }
  
  public Dispatcher getDispatcher()
  {
    return dispatcher;
  }
  
  public boolean getFollowSslRedirects()
  {
    return followSslRedirects;
  }
  
  public HostnameVerifier getHostnameVerifier()
  {
    return hostnameVerifier;
  }
  
  public java.util.List getProtocols()
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
  
  public int getReadTimeout()
  {
    return readTimeout;
  }
  
  public boolean getRetryOnConnectionFailure()
  {
    return retryOnConnectionFailure;
  }
  
  public SocketFactory getSocketFactory()
  {
    return socketFactory;
  }
  
  public SSLSocketFactory getSslSocketFactory()
  {
    return sslSocketFactory;
  }
  
  public int getWriteTimeout()
  {
    return writeTimeout;
  }
  
  InternalCache internalCache()
  {
    Cache localCache = cache;
    if (localCache != null) {
      return internalCache;
    }
    return internalCache;
  }
  
  public List networkInterceptors()
  {
    return networkInterceptors;
  }
  
  public int open()
  {
    return length;
  }
  
  public java.util.List transform()
  {
    return key;
  }
  
  public final class Builder
  {
    Dns SYSTEM;
    Authenticator authenticator;
    Cache cache;
    CertificatePinner certificatePinner = CertificatePinner.DEFAULT;
    int connectTimeout;
    final java.util.List<t> connection = new ArrayList();
    Authenticator connectionPool;
    java.util.List<k> connectionSpecs = OkHttpClient.DEFAULT_CONNECTION_SPECS;
    CookieJar cookieJar = CookieJar.NO_COOKIES;
    Dispatcher dispatcher = new Dispatcher();
    ConnectionPool dns;
    boolean followRedirects;
    boolean followSslRedirects;
    HostnameVerifier hostnameVerifier = OkHostnameVerifier.INSTANCE;
    final java.util.List<t> interceptors = new ArrayList();
    InternalCache internalCache;
    List networkInterceptors = HttpHost.create(HttpHost.host);
    java.util.List<w> protocols = OkHttpClient.DEFAULT_PROTOCOLS;
    Proxy proxy;
    ProxySelector proxySelector = ProxySelector.getDefault();
    int readTimeout;
    boolean retryOnConnectionFailure;
    int size;
    SocketFactory socketFactory = SocketFactory.getDefault();
    SSLSocketFactory sslSocketFactory;
    Resource trustRootIndex;
    int writeTimeout;
    
    public Builder()
    {
      this$1 = Authenticator.NONE;
      authenticator = this$1;
      connectionPool = this$1;
      dns = new ConnectionPool();
      SYSTEM = Dns.SYSTEM;
      retryOnConnectionFailure = true;
      followSslRedirects = true;
      followRedirects = true;
      connectTimeout = 10000;
      readTimeout = 10000;
      writeTimeout = 10000;
      size = 0;
    }
    
    public OkHttpClient build()
    {
      return new OkHttpClient(this);
    }
    
    public Builder cache(Cache paramCache)
    {
      cache = paramCache;
      internalCache = null;
      return this;
    }
  }
}
