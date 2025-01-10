package okhttp.internal.httpclient;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp.Address;
import okhttp.Authenticator;
import okhttp.CertificatePinner;
import okhttp.HttpUrl;
import okhttp.OkHttpClient;
import okhttp.Request;
import okhttp.Request.Builder;
import okhttp.Response;
import okhttp.Route;
import okhttp.internal.http.StreamAllocation;

public final class ClassWriter
  implements okhttp.Object
{
  private volatile boolean c;
  private final OkHttpClient client;
  private final boolean f;
  private Object t;
  
  public ClassWriter(OkHttpClient paramOkHttpClient, boolean paramBoolean)
  {
    client = paramOkHttpClient;
    f = paramBoolean;
  }
  
  private boolean authenticate(Response paramResponse, HttpUrl paramHttpUrl)
  {
    paramResponse = paramResponse.request().url();
    return (paramResponse.host().equals(paramHttpUrl.host())) && (paramResponse.port() == paramHttpUrl.port()) && (paramResponse.scheme().equals(paramHttpUrl.scheme()));
  }
  
  private Request followUpRequest(Response paramResponse, Route paramRoute)
  {
    if (paramResponse != null)
    {
      int i = paramResponse.code();
      String str = paramResponse.request().method();
      Proxy localProxy = null;
      if ((i != 307) && (i != 308)) {
        if (i != 401) {
          if (i != 503) {
            if (i != 407) {
              if (i == 408) {}
            }
          }
        }
      }
      switch (i)
      {
      default: 
        return null;
        if (!client.followRedirects()) {
          return null;
        }
        paramResponse.request().body();
        if ((paramResponse.priorResponse() != null) && (paramResponse.priorResponse().code() == 408)) {
          return null;
        }
        if (get(paramResponse, 0) > 0) {
          return null;
        }
        return paramResponse.request();
        if (paramRoute != null) {
          localProxy = paramRoute.getProxy();
        } else {
          localProxy = client.getProxy();
        }
        if (localProxy.type() == Proxy.Type.HTTP) {
          return client.getAuthenticator().authenticate(paramRoute, paramResponse);
        }
        throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
        if ((paramResponse.priorResponse() != null) && (paramResponse.priorResponse().code() == 503)) {
          return null;
        }
        if (get(paramResponse, Integer.MAX_VALUE) == 0) {
          return paramResponse.request();
        }
        return null;
        return client.authenticator().authenticate(paramRoute, paramResponse);
        if ((!str.equals("GET")) && (!str.equals("HEAD"))) {
          return null;
        }
        break;
      }
      if (!client.getFollowSslRedirects()) {
        return null;
      }
      paramRoute = paramResponse.header("Location");
      if (paramRoute == null) {
        return null;
      }
      HttpUrl localHttpUrl = paramResponse.request().url().build(paramRoute);
      if (localHttpUrl == null) {
        return null;
      }
      if ((!localHttpUrl.scheme().equals(paramResponse.request().url().scheme())) && (!client.getRetryOnConnectionFailure())) {
        return null;
      }
      Request.Builder localBuilder = paramResponse.request().newBuilder();
      if (HttpMethod.get(str))
      {
        boolean bool = HttpMethod.redirectsToGet(str);
        if (HttpMethod.permitsRequestBody(str))
        {
          localBuilder.method("GET", null);
        }
        else
        {
          paramRoute = localProxy;
          if (bool) {
            paramRoute = paramResponse.request().body();
          }
          localBuilder.method(str, paramRoute);
        }
        if (!bool)
        {
          localBuilder.removeHeader("Transfer-Encoding");
          localBuilder.removeHeader("Content-Length");
          localBuilder.removeHeader("Content-Type");
        }
      }
      if (!authenticate(paramResponse, localHttpUrl)) {
        localBuilder.removeHeader("Authorization");
      }
      localBuilder.url(localHttpUrl);
      return localBuilder.build();
    }
    throw new IllegalStateException();
  }
  
  private int get(Response paramResponse, int paramInt)
  {
    paramResponse = paramResponse.header("Retry-After");
    if (paramResponse == null) {
      return paramInt;
    }
    if (paramResponse.matches("\\d+")) {
      return Integer.valueOf(paramResponse).intValue();
    }
    return Integer.MAX_VALUE;
  }
  
  private Address get(HttpUrl paramHttpUrl)
  {
    SSLSocketFactory localSSLSocketFactory = null;
    HostnameVerifier localHostnameVerifier = null;
    CertificatePinner localCertificatePinner = null;
    if (paramHttpUrl.isHttps())
    {
      localSSLSocketFactory = client.getSslSocketFactory();
      localHostnameVerifier = client.getHostnameVerifier();
      localCertificatePinner = client.getCertificatePinner();
    }
    return new Address(paramHttpUrl.host(), paramHttpUrl.port(), client.dns(), client.getSocketFactory(), localSSLSocketFactory, localHostnameVerifier, localCertificatePinner, client.getAuthenticator(), client.getProxy(), client.getProtocols(), client.getConnectionSpecs(), client.getProxySelector());
  }
  
  private boolean get(IOException paramIOException, StreamAllocation paramStreamAllocation, boolean paramBoolean, Request paramRequest)
  {
    paramStreamAllocation.connect(paramIOException);
    if (!client.followRedirects()) {
      return false;
    }
    if (paramBoolean) {
      paramRequest.body();
    }
    if (!get(paramIOException, paramBoolean)) {
      return false;
    }
    return paramStreamAllocation.connect();
  }
  
  private boolean get(IOException paramIOException, boolean paramBoolean)
  {
    if ((paramIOException instanceof ProtocolException)) {
      return false;
    }
    if ((paramIOException instanceof InterruptedIOException))
    {
      if (((paramIOException instanceof SocketTimeoutException)) && (!paramBoolean)) {
        return true;
      }
    }
    else
    {
      if (((paramIOException instanceof SSLHandshakeException)) && ((paramIOException.getCause() instanceof CertificateException))) {
        return false;
      }
      return !(paramIOException instanceof SSLPeerUnverifiedException);
    }
    return false;
  }
  
  public void a(Object paramObject)
  {
    t = paramObject;
  }
  
  /* Error */
  public Response doInBackground(okhttp.Connection paramConnection)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface 282 1 0
    //   6: astore 7
    //   8: aload_1
    //   9: checkcast 284	okhttp/internal/httpclient/HttpConnection
    //   12: astore 8
    //   14: aload 8
    //   16: invokevirtual 288	okhttp/internal/httpclient/HttpConnection:getBytes	()Lokhttp/Map;
    //   19: astore 9
    //   21: aload 8
    //   23: invokevirtual 292	okhttp/internal/httpclient/HttpConnection:create	()Lokhttp/HttpHost;
    //   26: astore 10
    //   28: new 242	okhttp/internal/http/StreamAllocation
    //   31: dup
    //   32: aload_0
    //   33: getfield 20	okhttp/internal/httpclient/ClassWriter:client	Lokhttp/OkHttpClient;
    //   36: invokevirtual 296	okhttp/OkHttpClient:getConnectionPool	()Lokhttp/ConnectionPool;
    //   39: aload_0
    //   40: aload 7
    //   42: invokevirtual 37	okhttp/Request:url	()Lokhttp/HttpUrl;
    //   45: invokespecial 298	okhttp/internal/httpclient/ClassWriter:get	(Lokhttp/HttpUrl;)Lokhttp/Address;
    //   48: aload 9
    //   50: aload 10
    //   52: aload_0
    //   53: getfield 271	okhttp/internal/httpclient/ClassWriter:t	Ljava/lang/Object;
    //   56: invokespecial 301	okhttp/internal/http/StreamAllocation:<init>	(Lokhttp/ConnectionPool;Lokhttp/Address;Lokhttp/Map;Lokhttp/HttpHost;Ljava/lang/Object;)V
    //   59: astore 5
    //   61: iconst_0
    //   62: istore_2
    //   63: aconst_null
    //   64: astore 6
    //   66: aload 7
    //   68: astore_1
    //   69: aload_0
    //   70: getfield 303	okhttp/internal/httpclient/ClassWriter:c	Z
    //   73: ifne +361 -> 434
    //   76: iconst_0
    //   77: istore_3
    //   78: aload 8
    //   80: aload_1
    //   81: aload 5
    //   83: aconst_null
    //   84: aconst_null
    //   85: invokevirtual 306	okhttp/internal/httpclient/HttpConnection:get	(Lokhttp/Request;Lokhttp/internal/http/StreamAllocation;Lokhttp/internal/httpclient/HttpStream;Lokhttp/internal/http/RealConnection;)Lokhttp/Response;
    //   88: astore 7
    //   90: aload 7
    //   92: astore_1
    //   93: goto +3 -> 96
    //   96: aload 6
    //   98: ifnull +38 -> 136
    //   101: aload 7
    //   103: invokevirtual 309	okhttp/Response:get	()Lokhttp/a0$a;
    //   106: astore_1
    //   107: aload 6
    //   109: invokevirtual 309	okhttp/Response:get	()Lokhttp/a0$a;
    //   112: astore 6
    //   114: aload 6
    //   116: aconst_null
    //   117: invokevirtual 314	okhttp/a0$a:body	(Lokhttp/ResponseBody;)Lokhttp/a0$a;
    //   120: pop
    //   121: aload_1
    //   122: aload 6
    //   124: invokevirtual 316	okhttp/a0$a:build	()Lokhttp/Response;
    //   127: invokevirtual 319	okhttp/a0$a:authenticate	(Lokhttp/Response;)Lokhttp/a0$a;
    //   130: pop
    //   131: aload_1
    //   132: invokevirtual 316	okhttp/a0$a:build	()Lokhttp/Response;
    //   135: astore_1
    //   136: aload_0
    //   137: aload_1
    //   138: aload 5
    //   140: invokevirtual 323	okhttp/internal/http/StreamAllocation:route	()Lokhttp/Route;
    //   143: invokespecial 325	okhttp/internal/httpclient/ClassWriter:followUpRequest	(Lokhttp/Response;Lokhttp/Route;)Lokhttp/Request;
    //   146: astore 7
    //   148: aload 7
    //   150: ifnonnull +17 -> 167
    //   153: aload_0
    //   154: getfield 22	okhttp/internal/httpclient/ClassWriter:f	Z
    //   157: ifne +298 -> 455
    //   160: aload 5
    //   162: invokevirtual 328	okhttp/internal/http/StreamAllocation:release	()V
    //   165: aload_1
    //   166: areturn
    //   167: aload_1
    //   168: invokevirtual 331	okhttp/Response:body	()Lokhttp/ResponseBody;
    //   171: invokestatic 337	okhttp/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   174: iload_2
    //   175: iconst_1
    //   176: iadd
    //   177: istore_2
    //   178: iload_2
    //   179: bipush 20
    //   181: if_icmpgt +127 -> 308
    //   184: aload 7
    //   186: invokevirtual 74	okhttp/Request:body	()Lokhttp/RequestBody;
    //   189: pop
    //   190: aload_0
    //   191: aload_1
    //   192: aload 7
    //   194: invokevirtual 37	okhttp/Request:url	()Lokhttp/HttpUrl;
    //   197: invokespecial 173	okhttp/internal/httpclient/ClassWriter:authenticate	(Lokhttp/Response;Lokhttp/HttpUrl;)Z
    //   200: ifne +44 -> 244
    //   203: aload 5
    //   205: invokevirtual 328	okhttp/internal/http/StreamAllocation:release	()V
    //   208: new 242	okhttp/internal/http/StreamAllocation
    //   211: dup
    //   212: aload_0
    //   213: getfield 20	okhttp/internal/httpclient/ClassWriter:client	Lokhttp/OkHttpClient;
    //   216: invokevirtual 296	okhttp/OkHttpClient:getConnectionPool	()Lokhttp/ConnectionPool;
    //   219: aload_0
    //   220: aload 7
    //   222: invokevirtual 37	okhttp/Request:url	()Lokhttp/HttpUrl;
    //   225: invokespecial 298	okhttp/internal/httpclient/ClassWriter:get	(Lokhttp/HttpUrl;)Lokhttp/Address;
    //   228: aload 9
    //   230: aload 10
    //   232: aload_0
    //   233: getfield 271	okhttp/internal/httpclient/ClassWriter:t	Ljava/lang/Object;
    //   236: invokespecial 301	okhttp/internal/http/StreamAllocation:<init>	(Lokhttp/ConnectionPool;Lokhttp/Address;Lokhttp/Map;Lokhttp/HttpHost;Ljava/lang/Object;)V
    //   239: astore 5
    //   241: goto +11 -> 252
    //   244: aload 5
    //   246: invokevirtual 341	okhttp/internal/http/StreamAllocation:cancel	()Lokhttp/internal/httpclient/HttpStream;
    //   249: ifnonnull +12 -> 261
    //   252: aload_1
    //   253: astore 6
    //   255: aload 7
    //   257: astore_1
    //   258: goto -189 -> 69
    //   261: new 343	java/lang/StringBuilder
    //   264: dup
    //   265: invokespecial 344	java/lang/StringBuilder:<init>	()V
    //   268: astore 5
    //   270: aload 5
    //   272: ldc_w 346
    //   275: invokevirtual 350	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   278: pop
    //   279: aload 5
    //   281: aload_1
    //   282: invokevirtual 353	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   285: pop
    //   286: aload 5
    //   288: ldc_w 355
    //   291: invokevirtual 350	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   294: pop
    //   295: new 182	java/lang/IllegalStateException
    //   298: dup
    //   299: aload 5
    //   301: invokevirtual 358	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   304: invokespecial 359	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   307: athrow
    //   308: aload 5
    //   310: invokevirtual 328	okhttp/internal/http/StreamAllocation:release	()V
    //   313: new 343	java/lang/StringBuilder
    //   316: dup
    //   317: invokespecial 344	java/lang/StringBuilder:<init>	()V
    //   320: astore_1
    //   321: aload_1
    //   322: ldc_w 361
    //   325: invokevirtual 350	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: pop
    //   329: aload_1
    //   330: iload_2
    //   331: invokevirtual 364	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   334: pop
    //   335: new 112	java/net/ProtocolException
    //   338: dup
    //   339: aload_1
    //   340: invokevirtual 358	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   343: invokespecial 117	java/net/ProtocolException:<init>	(Ljava/lang/String;)V
    //   346: athrow
    //   347: astore_1
    //   348: goto +73 -> 421
    //   351: astore 7
    //   353: aload 7
    //   355: instanceof 366
    //   358: istore 4
    //   360: iload 4
    //   362: ifne +5 -> 367
    //   365: iconst_1
    //   366: istore_3
    //   367: aload_0
    //   368: aload 7
    //   370: aload 5
    //   372: iload_3
    //   373: aload_1
    //   374: invokespecial 368	okhttp/internal/httpclient/ClassWriter:get	(Ljava/io/IOException;Lokhttp/internal/http/StreamAllocation;ZLokhttp/Request;)Z
    //   377: istore_3
    //   378: iload_3
    //   379: ifeq +6 -> 385
    //   382: goto +29 -> 411
    //   385: aload 7
    //   387: athrow
    //   388: astore 7
    //   390: aload_0
    //   391: aload 7
    //   393: invokevirtual 372	okhttp/internal/http/RouteException:getLastConnectException	()Ljava/io/IOException;
    //   396: aload 5
    //   398: iconst_0
    //   399: aload_1
    //   400: invokespecial 368	okhttp/internal/httpclient/ClassWriter:get	(Ljava/io/IOException;Lokhttp/internal/http/StreamAllocation;ZLokhttp/Request;)Z
    //   403: istore_3
    //   404: iload_3
    //   405: ifeq +9 -> 414
    //   408: goto +3 -> 411
    //   411: goto -342 -> 69
    //   414: aload 7
    //   416: invokevirtual 372	okhttp/internal/http/RouteException:getLastConnectException	()Ljava/io/IOException;
    //   419: athrow
    //   420: astore_1
    //   421: aload 5
    //   423: aconst_null
    //   424: invokevirtual 246	okhttp/internal/http/StreamAllocation:connect	(Ljava/io/IOException;)V
    //   427: aload 5
    //   429: invokevirtual 328	okhttp/internal/http/StreamAllocation:release	()V
    //   432: aload_1
    //   433: athrow
    //   434: aload 5
    //   436: invokevirtual 328	okhttp/internal/http/StreamAllocation:release	()V
    //   439: new 259	java/io/IOException
    //   442: dup
    //   443: ldc_w 374
    //   446: invokespecial 375	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   449: astore_1
    //   450: goto +3 -> 453
    //   453: aload_1
    //   454: athrow
    //   455: aload_1
    //   456: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	457	0	this	ClassWriter
    //   0	457	1	paramConnection	okhttp.Connection
    //   62	269	2	i	int
    //   77	328	3	bool1	boolean
    //   358	3	4	bool2	boolean
    //   59	376	5	localObject1	Object
    //   64	190	6	localObject2	Object
    //   6	250	7	localObject3	Object
    //   351	35	7	localIOException	IOException
    //   388	27	7	localRouteException	okhttp.internal.http.RouteException
    //   12	67	8	localHttpConnection	HttpConnection
    //   19	210	9	localMap	okhttp.Map
    //   26	205	10	localHttpHost	okhttp.HttpHost
    // Exception table:
    //   from	to	target	type
    //   78	90	347	java/lang/Throwable
    //   78	90	351	java/io/IOException
    //   78	90	388	okhttp/internal/http/RouteException
    //   353	360	420	java/lang/Throwable
    //   367	378	420	java/lang/Throwable
    //   385	388	420	java/lang/Throwable
    //   390	404	420	java/lang/Throwable
    //   414	420	420	java/lang/Throwable
  }
  
  public boolean get()
  {
    return c;
  }
}
