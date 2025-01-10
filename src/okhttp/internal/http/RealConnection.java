package okhttp.internal.http;

import e.f0.f.g;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import okhttp.Address;
import okhttp.Authenticator;
import okhttp.CertificatePinner;
import okhttp.Connection;
import okhttp.ConnectionPool;
import okhttp.Handshake;
import okhttp.HttpHost;
import okhttp.HttpUrl;
import okhttp.Map;
import okhttp.OkHttpClient;
import okhttp.Protocol;
import okhttp.Request.Builder;
import okhttp.Response;
import okhttp.Route;
import okhttp.URI;
import okhttp.a0.a;
import okhttp.internal.Internal;
import okhttp.internal.Util;
import okhttp.internal.allocations.Http1xStream;
import okhttp.internal.httpclient.HttpStream;
import okhttp.internal.httpclient.OkHeaders;
import okhttp.internal.spdy.ErrorCode;
import okhttp.internal.spdy.FramedConnection.Builder;
import okhttp.internal.spdy.FramedConnection.Listener;
import okhttp.internal.spdy.Http2xStream;
import okhttp.internal.spdy.SpdyConnection;
import okhttp.internal.spdy.SpdyStream;
import okhttp.internal.tls.OkHostnameVerifier;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class RealConnection
  extends FramedConnection.Listener
  implements URI
{
  public int address = 1;
  public final List<Reference<g>> allocations = new ArrayList();
  private SpdyConnection connection;
  private Handshake handshake;
  public long idleAtNanos = Long.MAX_VALUE;
  public boolean noNewStreams;
  private Protocol protocol;
  private Socket rawSocket;
  private final Route route;
  private BufferedSink sink;
  private Socket socket;
  private BufferedSource source;
  public int successCount;
  private final ConnectionPool this$0;
  
  public RealConnection(ConnectionPool paramConnectionPool, Route paramRoute)
  {
    this$0 = paramConnectionPool;
    route = paramRoute;
  }
  
  private void connect(int paramInt1, int paramInt2, int paramInt3, Map paramMap, HttpHost paramHttpHost)
  {
    okhttp.Request localRequest2 = createTunnelRequest();
    okhttp.Request localRequest1 = localRequest2;
    HttpUrl localHttpUrl = localRequest2.url();
    int i = 0;
    while (i < 21)
    {
      connect(paramInt1, paramInt2, paramMap, paramHttpHost);
      localRequest2 = createTunnel(paramInt2, paramInt3, localRequest1, localHttpUrl);
      localRequest1 = localRequest2;
      if (localRequest2 == null) {
        return;
      }
      Util.closeQuietly(rawSocket);
      rawSocket = null;
      sink = null;
      source = null;
      paramHttpHost.append(paramMap, route.getId(), route.getProxy(), null);
      i += 1;
    }
  }
  
  /* Error */
  private void connect(int paramInt1, int paramInt2, Map paramMap, HttpHost paramHttpHost)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 53	okhttp/internal/http/RealConnection:route	Lokhttp/Route;
    //   4: invokevirtual 95	okhttp/Route:getProxy	()Ljava/net/Proxy;
    //   7: astore 6
    //   9: aload_0
    //   10: getfield 53	okhttp/internal/http/RealConnection:route	Lokhttp/Route;
    //   13: invokevirtual 109	okhttp/Route:getAddress	()Lokhttp/Address;
    //   16: astore 5
    //   18: aload 6
    //   20: invokevirtual 115	java/net/Proxy:type	()Ljava/net/Proxy$Type;
    //   23: getstatic 121	java/net/Proxy$Type:DIRECT	Ljava/net/Proxy$Type;
    //   26: if_acmpeq +31 -> 57
    //   29: aload 6
    //   31: invokevirtual 115	java/net/Proxy:type	()Ljava/net/Proxy$Type;
    //   34: getstatic 124	java/net/Proxy$Type:HTTP	Ljava/net/Proxy$Type;
    //   37: if_acmpne +6 -> 43
    //   40: goto +17 -> 57
    //   43: new 126	java/net/Socket
    //   46: dup
    //   47: aload 6
    //   49: invokespecial 129	java/net/Socket:<init>	(Ljava/net/Proxy;)V
    //   52: astore 5
    //   54: goto +13 -> 67
    //   57: aload 5
    //   59: invokevirtual 135	okhttp/Address:getSocketFactory	()Ljavax/net/SocketFactory;
    //   62: invokevirtual 141	javax/net/SocketFactory:createSocket	()Ljava/net/Socket;
    //   65: astore 5
    //   67: aload_0
    //   68: aload 5
    //   70: putfield 75	okhttp/internal/http/RealConnection:rawSocket	Ljava/net/Socket;
    //   73: aload 4
    //   75: aload_3
    //   76: aload_0
    //   77: getfield 53	okhttp/internal/http/RealConnection:route	Lokhttp/Route;
    //   80: invokevirtual 91	okhttp/Route:getId	()Ljava/net/InetSocketAddress;
    //   83: aload 6
    //   85: invokevirtual 144	okhttp/HttpHost:connect	(Lokhttp/Map;Ljava/net/InetSocketAddress;Ljava/net/Proxy;)V
    //   88: aload_0
    //   89: getfield 75	okhttp/internal/http/RealConnection:rawSocket	Ljava/net/Socket;
    //   92: iload_2
    //   93: invokevirtual 148	java/net/Socket:setSoTimeout	(I)V
    //   96: invokestatic 154	okhttp/internal/internal/Platform:get	()Lokhttp/internal/internal/Platform;
    //   99: astore_3
    //   100: aload_0
    //   101: getfield 75	okhttp/internal/http/RealConnection:rawSocket	Ljava/net/Socket;
    //   104: astore 4
    //   106: aload_0
    //   107: getfield 53	okhttp/internal/http/RealConnection:route	Lokhttp/Route;
    //   110: astore 5
    //   112: aload_3
    //   113: aload 4
    //   115: aload 5
    //   117: invokevirtual 91	okhttp/Route:getId	()Ljava/net/InetSocketAddress;
    //   120: iload_1
    //   121: invokevirtual 158	okhttp/internal/internal/Platform:connectSocket	(Ljava/net/Socket;Ljava/net/InetSocketAddress;I)V
    //   124: aload_0
    //   125: aload_0
    //   126: getfield 75	okhttp/internal/http/RealConnection:rawSocket	Ljava/net/Socket;
    //   129: invokestatic 163	okio/Okio:source	(Ljava/net/Socket;)Lokio/Source;
    //   132: invokestatic 167	okio/Okio:buffer	(Lokio/Source;)Lokio/BufferedSource;
    //   135: putfield 85	okhttp/internal/http/RealConnection:source	Lokio/BufferedSource;
    //   138: aload_0
    //   139: aload_0
    //   140: getfield 75	okhttp/internal/http/RealConnection:rawSocket	Ljava/net/Socket;
    //   143: invokestatic 170	okio/Okio:sink	(Ljava/net/Socket;)Lokio/Sink;
    //   146: invokestatic 173	okio/Okio:buffer	(Lokio/Sink;)Lokio/BufferedSink;
    //   149: putfield 83	okhttp/internal/http/RealConnection:sink	Lokio/BufferedSink;
    //   152: return
    //   153: astore_3
    //   154: ldc -81
    //   156: aload_3
    //   157: invokevirtual 181	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   160: invokevirtual 187	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   163: ifne +4 -> 167
    //   166: return
    //   167: new 189	java/io/IOException
    //   170: dup
    //   171: aload_3
    //   172: invokespecial 192	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   175: athrow
    //   176: astore_3
    //   177: new 194	java/lang/StringBuilder
    //   180: dup
    //   181: invokespecial 195	java/lang/StringBuilder:<init>	()V
    //   184: astore 4
    //   186: aload 4
    //   188: ldc -59
    //   190: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: pop
    //   194: aload 4
    //   196: aload_0
    //   197: getfield 53	okhttp/internal/http/RealConnection:route	Lokhttp/Route;
    //   200: invokevirtual 91	okhttp/Route:getId	()Ljava/net/InetSocketAddress;
    //   203: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   206: pop
    //   207: new 103	java/net/ConnectException
    //   210: dup
    //   211: aload 4
    //   213: invokevirtual 206	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   216: invokespecial 209	java/net/ConnectException:<init>	(Ljava/lang/String;)V
    //   219: astore 4
    //   221: aload 4
    //   223: aload_3
    //   224: invokevirtual 213	java/net/ConnectException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   227: pop
    //   228: aload 4
    //   230: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	231	0	this	RealConnection
    //   0	231	1	paramInt1	int
    //   0	231	2	paramInt2	int
    //   0	231	3	paramMap	Map
    //   0	231	4	paramHttpHost	HttpHost
    //   16	100	5	localObject	Object
    //   7	77	6	localProxy	Proxy
    // Exception table:
    //   from	to	target	type
    //   124	152	153	java/lang/NullPointerException
    //   96	100	176	java/net/ConnectException
    //   112	124	176	java/net/ConnectException
  }
  
  private void connect(ConnectionSpecSelector paramConnectionSpecSelector, int paramInt, Map paramMap, HttpHost paramHttpHost)
  {
    if (route.getAddress().getSslSocketFactory() == null)
    {
      protocol = Protocol.HTTP_1_1;
      socket = rawSocket;
      return;
    }
    paramHttpHost.create(paramMap);
    connectTls(paramConnectionSpecSelector);
    paramHttpHost.create(paramMap, handshake);
    if (protocol == Protocol.HTTP_2)
    {
      socket.setSoTimeout(0);
      paramConnectionSpecSelector = new FramedConnection.Builder(true);
      paramConnectionSpecSelector.socket(socket, route.getAddress().url().host(), source, sink);
      paramConnectionSpecSelector.listener(this);
      paramConnectionSpecSelector.socket(paramInt);
      connection = paramConnectionSpecSelector.build();
      connection.connect();
    }
  }
  
  /* Error */
  private void connectTls(ConnectionSpecSelector paramConnectionSpecSelector)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 53	okhttp/internal/http/RealConnection:route	Lokhttp/Route;
    //   4: invokevirtual 109	okhttp/Route:getAddress	()Lokhttp/Address;
    //   7: astore 7
    //   9: aload 7
    //   11: invokevirtual 218	okhttp/Address:getSslSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   14: astore 6
    //   16: aconst_null
    //   17: astore 4
    //   19: aconst_null
    //   20: astore 5
    //   22: aload 5
    //   24: astore_3
    //   25: aload_0
    //   26: getfield 75	okhttp/internal/http/RealConnection:rawSocket	Ljava/net/Socket;
    //   29: astore 8
    //   31: aload 5
    //   33: astore_3
    //   34: aload 6
    //   36: aload 8
    //   38: aload 7
    //   40: invokevirtual 249	okhttp/Address:url	()Lokhttp/HttpUrl;
    //   43: invokevirtual 254	okhttp/HttpUrl:host	()Ljava/lang/String;
    //   46: aload 7
    //   48: invokevirtual 249	okhttp/Address:url	()Lokhttp/HttpUrl;
    //   51: invokevirtual 282	okhttp/HttpUrl:port	()I
    //   54: iconst_1
    //   55: invokevirtual 287	javax/net/ssl/SSLSocketFactory:createSocket	(Ljava/net/Socket;Ljava/lang/String;IZ)Ljava/net/Socket;
    //   58: astore 6
    //   60: aload 5
    //   62: astore_3
    //   63: aload 6
    //   65: checkcast 289	javax/net/ssl/SSLSocket
    //   68: astore 6
    //   70: aload 6
    //   72: astore 5
    //   74: aload 5
    //   76: astore_3
    //   77: aload 5
    //   79: astore 4
    //   81: aload_1
    //   82: aload 6
    //   84: invokevirtual 295	okhttp/internal/http/ConnectionSpecSelector:configureSecureSocket	(Ljavax/net/ssl/SSLSocket;)Lokhttp/ConnectionSpec;
    //   87: astore_1
    //   88: aload 5
    //   90: astore_3
    //   91: aload 5
    //   93: astore 4
    //   95: aload_1
    //   96: invokevirtual 301	okhttp/ConnectionSpec:supportsTlsExtensions	()Z
    //   99: istore_2
    //   100: iload_2
    //   101: ifeq +31 -> 132
    //   104: aload 5
    //   106: astore_3
    //   107: aload 5
    //   109: astore 4
    //   111: invokestatic 154	okhttp/internal/internal/Platform:get	()Lokhttp/internal/internal/Platform;
    //   114: aload 6
    //   116: aload 7
    //   118: invokevirtual 249	okhttp/Address:url	()Lokhttp/HttpUrl;
    //   121: invokevirtual 254	okhttp/HttpUrl:host	()Ljava/lang/String;
    //   124: aload 7
    //   126: invokevirtual 305	okhttp/Address:getProtocols	()Ljava/util/List;
    //   129: invokevirtual 309	okhttp/internal/internal/Platform:read	(Ljavax/net/ssl/SSLSocket;Ljava/lang/String;Ljava/util/List;)V
    //   132: aload 5
    //   134: astore_3
    //   135: aload 5
    //   137: astore 4
    //   139: aload 6
    //   141: invokevirtual 312	javax/net/ssl/SSLSocket:startHandshake	()V
    //   144: aload 5
    //   146: astore_3
    //   147: aload 5
    //   149: astore 4
    //   151: aload 6
    //   153: invokevirtual 316	javax/net/ssl/SSLSocket:getSession	()Ljavax/net/ssl/SSLSession;
    //   156: astore 9
    //   158: aload 5
    //   160: astore_3
    //   161: aload 5
    //   163: astore 4
    //   165: aload_0
    //   166: aload 9
    //   168: invokespecial 319	okhttp/internal/http/RealConnection:get	(Ljavax/net/ssl/SSLSession;)Z
    //   171: istore_2
    //   172: iload_2
    //   173: ifeq +463 -> 636
    //   176: aload 5
    //   178: astore_3
    //   179: aload 5
    //   181: astore 4
    //   183: aload 9
    //   185: invokestatic 324	okhttp/Handshake:get	(Ljavax/net/ssl/SSLSession;)Lokhttp/Handshake;
    //   188: astore 8
    //   190: aload 5
    //   192: astore_3
    //   193: aload 5
    //   195: astore 4
    //   197: aload 7
    //   199: invokevirtual 328	okhttp/Address:getHostnameVerifier	()Ljavax/net/ssl/HostnameVerifier;
    //   202: aload 7
    //   204: invokevirtual 249	okhttp/Address:url	()Lokhttp/HttpUrl;
    //   207: invokevirtual 254	okhttp/HttpUrl:host	()Ljava/lang/String;
    //   210: aload 9
    //   212: invokeinterface 334 3 0
    //   217: istore_2
    //   218: iload_2
    //   219: ifeq +208 -> 427
    //   222: aload 5
    //   224: astore_3
    //   225: aload 5
    //   227: astore 4
    //   229: aload 7
    //   231: invokevirtual 338	okhttp/Address:getCertificatePinner	()Lokhttp/CertificatePinner;
    //   234: aload 7
    //   236: invokevirtual 249	okhttp/Address:url	()Lokhttp/HttpUrl;
    //   239: invokevirtual 254	okhttp/HttpUrl:host	()Ljava/lang/String;
    //   242: aload 8
    //   244: invokevirtual 341	okhttp/Handshake:peerCertificates	()Ljava/util/List;
    //   247: invokevirtual 347	okhttp/CertificatePinner:check	(Ljava/lang/String;Ljava/util/List;)V
    //   250: aload 5
    //   252: astore_3
    //   253: aload 5
    //   255: astore 4
    //   257: aload_1
    //   258: invokevirtual 301	okhttp/ConnectionSpec:supportsTlsExtensions	()Z
    //   261: istore_2
    //   262: iload_2
    //   263: ifeq +22 -> 285
    //   266: aload 5
    //   268: astore_3
    //   269: aload 5
    //   271: astore 4
    //   273: invokestatic 154	okhttp/internal/internal/Platform:get	()Lokhttp/internal/internal/Platform;
    //   276: aload 6
    //   278: invokevirtual 350	okhttp/internal/internal/Platform:read	(Ljavax/net/ssl/SSLSocket;)Ljava/lang/String;
    //   281: astore_1
    //   282: goto +5 -> 287
    //   285: aconst_null
    //   286: astore_1
    //   287: aload 5
    //   289: astore_3
    //   290: aload_0
    //   291: aload 6
    //   293: putfield 227	okhttp/internal/http/RealConnection:socket	Ljava/net/Socket;
    //   296: aload 5
    //   298: astore_3
    //   299: aload_0
    //   300: getfield 227	okhttp/internal/http/RealConnection:socket	Ljava/net/Socket;
    //   303: astore 7
    //   305: aload 5
    //   307: astore_3
    //   308: aload 5
    //   310: astore 4
    //   312: aload 7
    //   314: invokestatic 163	okio/Okio:source	(Ljava/net/Socket;)Lokio/Source;
    //   317: invokestatic 167	okio/Okio:buffer	(Lokio/Source;)Lokio/BufferedSource;
    //   320: astore 7
    //   322: aload 5
    //   324: astore_3
    //   325: aload_0
    //   326: aload 7
    //   328: putfield 85	okhttp/internal/http/RealConnection:source	Lokio/BufferedSource;
    //   331: aload 5
    //   333: astore_3
    //   334: aload_0
    //   335: getfield 227	okhttp/internal/http/RealConnection:socket	Ljava/net/Socket;
    //   338: astore 7
    //   340: aload 5
    //   342: astore_3
    //   343: aload 5
    //   345: astore 4
    //   347: aload 7
    //   349: invokestatic 170	okio/Okio:sink	(Ljava/net/Socket;)Lokio/Sink;
    //   352: invokestatic 173	okio/Okio:buffer	(Lokio/Sink;)Lokio/BufferedSink;
    //   355: astore 7
    //   357: aload 5
    //   359: astore_3
    //   360: aload_0
    //   361: aload 7
    //   363: putfield 83	okhttp/internal/http/RealConnection:sink	Lokio/BufferedSink;
    //   366: aload 5
    //   368: astore_3
    //   369: aload_0
    //   370: aload 8
    //   372: putfield 237	okhttp/internal/http/RealConnection:handshake	Lokhttp/Handshake;
    //   375: aload_1
    //   376: ifnull +18 -> 394
    //   379: aload 5
    //   381: astore_3
    //   382: aload 5
    //   384: astore 4
    //   386: aload_1
    //   387: invokestatic 353	okhttp/Protocol:get	(Ljava/lang/String;)Lokhttp/Protocol;
    //   390: astore_1
    //   391: goto +14 -> 405
    //   394: aload 5
    //   396: astore_3
    //   397: aload 5
    //   399: astore 4
    //   401: getstatic 223	okhttp/Protocol:HTTP_1_1	Lokhttp/Protocol;
    //   404: astore_1
    //   405: aload 5
    //   407: astore_3
    //   408: aload_0
    //   409: aload_1
    //   410: putfield 225	okhttp/internal/http/RealConnection:protocol	Lokhttp/Protocol;
    //   413: aload 6
    //   415: ifnull +11 -> 426
    //   418: invokestatic 154	okhttp/internal/internal/Platform:get	()Lokhttp/internal/internal/Platform;
    //   421: aload 6
    //   423: invokevirtual 357	okhttp/internal/internal/Platform:close	(Ljavax/net/ssl/SSLSocket;)V
    //   426: return
    //   427: aload 5
    //   429: astore_3
    //   430: aload 5
    //   432: astore 4
    //   434: aload 8
    //   436: invokevirtual 341	okhttp/Handshake:peerCertificates	()Ljava/util/List;
    //   439: iconst_0
    //   440: invokeinterface 362 2 0
    //   445: astore_1
    //   446: aload 5
    //   448: astore_3
    //   449: aload_1
    //   450: checkcast 364	java/security/cert/X509Certificate
    //   453: astore_1
    //   454: aload 5
    //   456: astore_3
    //   457: aload 5
    //   459: astore 4
    //   461: new 194	java/lang/StringBuilder
    //   464: dup
    //   465: invokespecial 195	java/lang/StringBuilder:<init>	()V
    //   468: astore 6
    //   470: aload 5
    //   472: astore_3
    //   473: aload 5
    //   475: astore 4
    //   477: aload 6
    //   479: ldc_w 366
    //   482: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   485: pop
    //   486: aload 5
    //   488: astore_3
    //   489: aload 5
    //   491: astore 4
    //   493: aload 6
    //   495: aload 7
    //   497: invokevirtual 249	okhttp/Address:url	()Lokhttp/HttpUrl;
    //   500: invokevirtual 254	okhttp/HttpUrl:host	()Ljava/lang/String;
    //   503: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   506: pop
    //   507: aload 5
    //   509: astore_3
    //   510: aload 5
    //   512: astore 4
    //   514: aload 6
    //   516: ldc_w 368
    //   519: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   522: pop
    //   523: aload 5
    //   525: astore_3
    //   526: aload 5
    //   528: astore 4
    //   530: aload 6
    //   532: aload_1
    //   533: invokestatic 372	okhttp/CertificatePinner:pin	(Ljava/security/cert/Certificate;)Ljava/lang/String;
    //   536: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   539: pop
    //   540: aload 5
    //   542: astore_3
    //   543: aload 5
    //   545: astore 4
    //   547: aload 6
    //   549: ldc_w 374
    //   552: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   555: pop
    //   556: aload 5
    //   558: astore_3
    //   559: aload 5
    //   561: astore 4
    //   563: aload 6
    //   565: aload_1
    //   566: invokevirtual 378	java/security/cert/X509Certificate:getSubjectDN	()Ljava/security/Principal;
    //   569: invokeinterface 383 1 0
    //   574: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   577: pop
    //   578: aload 5
    //   580: astore_3
    //   581: aload 5
    //   583: astore 4
    //   585: aload 6
    //   587: ldc_w 385
    //   590: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   593: pop
    //   594: aload 5
    //   596: astore_3
    //   597: aload 5
    //   599: astore 4
    //   601: aload 6
    //   603: aload_1
    //   604: invokestatic 391	okhttp/internal/tls/OkHostnameVerifier:allSubjectAltNames	(Ljava/security/cert/X509Certificate;)Ljava/util/List;
    //   607: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   610: pop
    //   611: aload 5
    //   613: astore_3
    //   614: aload 5
    //   616: astore 4
    //   618: new 393	javax/net/ssl/SSLPeerUnverifiedException
    //   621: dup
    //   622: aload 6
    //   624: invokevirtual 206	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   627: invokespecial 394	javax/net/ssl/SSLPeerUnverifiedException:<init>	(Ljava/lang/String;)V
    //   630: astore_1
    //   631: aload 5
    //   633: astore_3
    //   634: aload_1
    //   635: athrow
    //   636: aload 5
    //   638: astore_3
    //   639: aload 5
    //   641: astore 4
    //   643: new 189	java/io/IOException
    //   646: dup
    //   647: ldc_w 396
    //   650: invokespecial 397	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   653: astore_1
    //   654: aload 5
    //   656: astore_3
    //   657: aload_1
    //   658: athrow
    //   659: astore_1
    //   660: goto +33 -> 693
    //   663: astore_1
    //   664: aload 4
    //   666: astore_3
    //   667: aload_1
    //   668: invokestatic 401	okhttp/internal/Util:isAndroidGetsocknameError	(Ljava/lang/AssertionError;)Z
    //   671: istore_2
    //   672: iload_2
    //   673: ifeq +15 -> 688
    //   676: aload 4
    //   678: astore_3
    //   679: new 189	java/io/IOException
    //   682: dup
    //   683: aload_1
    //   684: invokespecial 192	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   687: athrow
    //   688: aload 4
    //   690: astore_3
    //   691: aload_1
    //   692: athrow
    //   693: aload_3
    //   694: ifnull +10 -> 704
    //   697: invokestatic 154	okhttp/internal/internal/Platform:get	()Lokhttp/internal/internal/Platform;
    //   700: aload_3
    //   701: invokevirtual 357	okhttp/internal/internal/Platform:close	(Ljavax/net/ssl/SSLSocket;)V
    //   704: aload_3
    //   705: invokestatic 81	okhttp/internal/Util:closeQuietly	(Ljava/net/Socket;)V
    //   708: aload_1
    //   709: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	710	0	this	RealConnection
    //   0	710	1	paramConnectionSpecSelector	ConnectionSpecSelector
    //   99	574	2	bool	boolean
    //   24	681	3	localObject1	Object
    //   17	672	4	localObject2	Object
    //   20	635	5	localObject3	Object
    //   14	609	6	localObject4	Object
    //   7	489	7	localObject5	Object
    //   29	406	8	localObject6	Object
    //   156	55	9	localSSLSession	SSLSession
    // Exception table:
    //   from	to	target	type
    //   25	31	659	java/lang/Throwable
    //   34	60	659	java/lang/Throwable
    //   63	70	659	java/lang/Throwable
    //   81	88	659	java/lang/Throwable
    //   95	100	659	java/lang/Throwable
    //   111	132	659	java/lang/Throwable
    //   139	144	659	java/lang/Throwable
    //   151	158	659	java/lang/Throwable
    //   165	172	659	java/lang/Throwable
    //   183	190	659	java/lang/Throwable
    //   197	218	659	java/lang/Throwable
    //   229	250	659	java/lang/Throwable
    //   257	262	659	java/lang/Throwable
    //   273	282	659	java/lang/Throwable
    //   290	296	659	java/lang/Throwable
    //   299	305	659	java/lang/Throwable
    //   312	322	659	java/lang/Throwable
    //   325	331	659	java/lang/Throwable
    //   334	340	659	java/lang/Throwable
    //   347	357	659	java/lang/Throwable
    //   360	366	659	java/lang/Throwable
    //   369	375	659	java/lang/Throwable
    //   386	391	659	java/lang/Throwable
    //   401	405	659	java/lang/Throwable
    //   408	413	659	java/lang/Throwable
    //   434	446	659	java/lang/Throwable
    //   449	454	659	java/lang/Throwable
    //   461	470	659	java/lang/Throwable
    //   477	486	659	java/lang/Throwable
    //   493	507	659	java/lang/Throwable
    //   514	523	659	java/lang/Throwable
    //   530	540	659	java/lang/Throwable
    //   547	556	659	java/lang/Throwable
    //   563	578	659	java/lang/Throwable
    //   585	594	659	java/lang/Throwable
    //   601	611	659	java/lang/Throwable
    //   618	631	659	java/lang/Throwable
    //   634	636	659	java/lang/Throwable
    //   643	654	659	java/lang/Throwable
    //   657	659	659	java/lang/Throwable
    //   667	672	659	java/lang/Throwable
    //   679	688	659	java/lang/Throwable
    //   691	693	659	java/lang/Throwable
    //   34	60	663	java/lang/AssertionError
    //   81	88	663	java/lang/AssertionError
    //   95	100	663	java/lang/AssertionError
    //   111	132	663	java/lang/AssertionError
    //   139	144	663	java/lang/AssertionError
    //   151	158	663	java/lang/AssertionError
    //   165	172	663	java/lang/AssertionError
    //   183	190	663	java/lang/AssertionError
    //   197	218	663	java/lang/AssertionError
    //   229	250	663	java/lang/AssertionError
    //   257	262	663	java/lang/AssertionError
    //   273	282	663	java/lang/AssertionError
    //   312	322	663	java/lang/AssertionError
    //   347	357	663	java/lang/AssertionError
    //   386	391	663	java/lang/AssertionError
    //   401	405	663	java/lang/AssertionError
    //   434	446	663	java/lang/AssertionError
    //   461	470	663	java/lang/AssertionError
    //   477	486	663	java/lang/AssertionError
    //   493	507	663	java/lang/AssertionError
    //   514	523	663	java/lang/AssertionError
    //   530	540	663	java/lang/AssertionError
    //   547	556	663	java/lang/AssertionError
    //   563	578	663	java/lang/AssertionError
    //   585	594	663	java/lang/AssertionError
    //   601	611	663	java/lang/AssertionError
    //   618	631	663	java/lang/AssertionError
    //   643	654	663	java/lang/AssertionError
  }
  
  private okhttp.Request createTunnel(int paramInt1, int paramInt2, okhttp.Request paramRequest, HttpUrl paramHttpUrl)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("CONNECT ");
    ((StringBuilder)localObject1).append(Util.hostHeader(paramHttpUrl, true));
    ((StringBuilder)localObject1).append(" HTTP/1.1");
    localObject1 = ((StringBuilder)localObject1).toString();
    Object localObject2;
    do
    {
      paramHttpUrl = new Http1xStream(null, null, source, sink);
      source.timeout().timeout(paramInt1, TimeUnit.MILLISECONDS);
      sink.timeout().timeout(paramInt2, TimeUnit.MILLISECONDS);
      paramHttpUrl.writeRequest(paramRequest.headers(), (String)localObject1);
      paramHttpUrl.finishRequest();
      localObject2 = paramHttpUrl.readResponse(false);
      ((a0.a)localObject2).request(paramRequest);
      localObject2 = ((a0.a)localObject2).build();
      long l2 = OkHeaders.contentLength((Response)localObject2);
      long l1 = l2;
      if (l2 == -1L) {
        l1 = 0L;
      }
      paramRequest = paramHttpUrl.newFixedLengthSource(l1);
      Util.skipAll(paramRequest, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
      paramRequest.close();
      int i = ((Response)localObject2).code();
      if (i == 200) {
        break label310;
      }
      if (i != 407) {
        break label272;
      }
      paramHttpUrl = route.getAddress().proxyAuthenticator().authenticate(route, (Response)localObject2);
      paramRequest = paramHttpUrl;
      if (paramHttpUrl == null) {
        break;
      }
    } while (!"close".equalsIgnoreCase(((Response)localObject2).header("Connection")));
    return paramHttpUrl;
    throw new IOException("Failed to authenticate with proxy");
    label272:
    paramRequest = new StringBuilder();
    paramRequest.append("Unexpected response code for CONNECT: ");
    paramRequest.append(((Response)localObject2).code());
    throw new IOException(paramRequest.toString());
    label310:
    if ((source.buffer().exhausted()) && (sink.buffer().exhausted())) {
      return null;
    }
    paramRequest = new IOException("TLS tunnel buffered too many bytes!");
    throw paramRequest;
  }
  
  private okhttp.Request createTunnelRequest()
  {
    Request.Builder localBuilder = new Request.Builder();
    localBuilder.url(route.getAddress().url());
    localBuilder.header("Host", Util.hostHeader(route.getAddress().url(), true));
    localBuilder.header("Proxy-Connection", "Keep-Alive");
    localBuilder.header("User-Agent", okhttp.internal.Request.userAgent());
    return localBuilder.build();
  }
  
  private boolean get(SSLSession paramSSLSession)
  {
    return (!"NONE".equals(paramSSLSession.getProtocol())) && (!"SSL_NULL_WITH_NULL_NULL".equals(paramSSLSession.getCipherSuite()));
  }
  
  /* Error */
  public void connect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, Map paramMap, HttpHost paramHttpHost)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 225	okhttp/internal/http/RealConnection:protocol	Lokhttp/Protocol;
    //   4: ifnonnull +475 -> 479
    //   7: aload_0
    //   8: getfield 53	okhttp/internal/http/RealConnection:route	Lokhttp/Route;
    //   11: invokevirtual 109	okhttp/Route:getAddress	()Lokhttp/Address;
    //   14: invokevirtual 565	okhttp/Address:getConnectionSpecs	()Ljava/util/List;
    //   17: astore 9
    //   19: new 291	okhttp/internal/http/ConnectionSpecSelector
    //   22: dup
    //   23: aload 9
    //   25: invokespecial 568	okhttp/internal/http/ConnectionSpecSelector:<init>	(Ljava/util/List;)V
    //   28: astore 11
    //   30: aload_0
    //   31: getfield 53	okhttp/internal/http/RealConnection:route	Lokhttp/Route;
    //   34: invokevirtual 109	okhttp/Route:getAddress	()Lokhttp/Address;
    //   37: invokevirtual 218	okhttp/Address:getSslSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   40: ifnonnull +118 -> 158
    //   43: aload 9
    //   45: getstatic 572	okhttp/ConnectionSpec:CLEARTEXT	Lokhttp/ConnectionSpec;
    //   48: invokeinterface 575 2 0
    //   53: ifeq +87 -> 140
    //   56: aload_0
    //   57: getfield 53	okhttp/internal/http/RealConnection:route	Lokhttp/Route;
    //   60: invokevirtual 109	okhttp/Route:getAddress	()Lokhttp/Address;
    //   63: invokevirtual 249	okhttp/Address:url	()Lokhttp/HttpUrl;
    //   66: invokevirtual 254	okhttp/HttpUrl:host	()Ljava/lang/String;
    //   69: astore 9
    //   71: invokestatic 154	okhttp/internal/internal/Platform:get	()Lokhttp/internal/internal/Platform;
    //   74: aload 9
    //   76: invokevirtual 577	okhttp/internal/internal/Platform:get	(Ljava/lang/String;)Z
    //   79: ifeq +6 -> 85
    //   82: goto +76 -> 158
    //   85: new 194	java/lang/StringBuilder
    //   88: dup
    //   89: invokespecial 195	java/lang/StringBuilder:<init>	()V
    //   92: astore 6
    //   94: aload 6
    //   96: ldc_w 579
    //   99: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: pop
    //   103: aload 6
    //   105: aload 9
    //   107: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: pop
    //   111: aload 6
    //   113: ldc_w 581
    //   116: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: pop
    //   120: new 583	okhttp/internal/http/RouteException
    //   123: dup
    //   124: new 585	java/net/UnknownServiceException
    //   127: dup
    //   128: aload 6
    //   130: invokevirtual 206	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   133: invokespecial 586	java/net/UnknownServiceException:<init>	(Ljava/lang/String;)V
    //   136: invokespecial 589	okhttp/internal/http/RouteException:<init>	(Ljava/io/IOException;)V
    //   139: athrow
    //   140: new 583	okhttp/internal/http/RouteException
    //   143: dup
    //   144: new 585	java/net/UnknownServiceException
    //   147: dup
    //   148: ldc_w 591
    //   151: invokespecial 586	java/net/UnknownServiceException:<init>	(Ljava/lang/String;)V
    //   154: invokespecial 589	okhttp/internal/http/RouteException:<init>	(Ljava/io/IOException;)V
    //   157: athrow
    //   158: aconst_null
    //   159: astore 10
    //   161: aload_0
    //   162: getfield 53	okhttp/internal/http/RealConnection:route	Lokhttp/Route;
    //   165: astore 9
    //   167: aload 9
    //   169: invokevirtual 593	okhttp/Route:equals	()Z
    //   172: istore 8
    //   174: iload 8
    //   176: ifeq +27 -> 203
    //   179: aload_0
    //   180: iload_1
    //   181: iload_2
    //   182: iload_3
    //   183: aload 6
    //   185: aload 7
    //   187: invokespecial 595	okhttp/internal/http/RealConnection:connect	(IIILokhttp/Map;Lokhttp/HttpHost;)V
    //   190: aload_0
    //   191: getfield 75	okhttp/internal/http/RealConnection:rawSocket	Ljava/net/Socket;
    //   194: ifnonnull +6 -> 200
    //   197: goto +73 -> 270
    //   200: goto +13 -> 213
    //   203: aload_0
    //   204: iload_1
    //   205: iload_2
    //   206: aload 6
    //   208: aload 7
    //   210: invokespecial 69	okhttp/internal/http/RealConnection:connect	(IILokhttp/Map;Lokhttp/HttpHost;)V
    //   213: aload_0
    //   214: aload 11
    //   216: iload 4
    //   218: aload 6
    //   220: aload 7
    //   222: invokespecial 597	okhttp/internal/http/RealConnection:connect	(Lokhttp/internal/http/ConnectionSpecSelector;ILokhttp/Map;Lokhttp/HttpHost;)V
    //   225: aload_0
    //   226: getfield 53	okhttp/internal/http/RealConnection:route	Lokhttp/Route;
    //   229: astore 9
    //   231: aload 9
    //   233: invokevirtual 91	okhttp/Route:getId	()Ljava/net/InetSocketAddress;
    //   236: astore 9
    //   238: aload_0
    //   239: getfield 53	okhttp/internal/http/RealConnection:route	Lokhttp/Route;
    //   242: astore 12
    //   244: aload 12
    //   246: invokevirtual 95	okhttp/Route:getProxy	()Ljava/net/Proxy;
    //   249: astore 12
    //   251: aload_0
    //   252: getfield 225	okhttp/internal/http/RealConnection:protocol	Lokhttp/Protocol;
    //   255: astore 13
    //   257: aload 7
    //   259: aload 6
    //   261: aload 9
    //   263: aload 12
    //   265: aload 13
    //   267: invokevirtual 101	okhttp/HttpHost:append	(Lokhttp/Map;Ljava/net/InetSocketAddress;Ljava/net/Proxy;Lokhttp/Protocol;)V
    //   270: aload_0
    //   271: getfield 53	okhttp/internal/http/RealConnection:route	Lokhttp/Route;
    //   274: invokevirtual 593	okhttp/Route:equals	()Z
    //   277: ifeq +31 -> 308
    //   280: aload_0
    //   281: getfield 75	okhttp/internal/http/RealConnection:rawSocket	Ljava/net/Socket;
    //   284: ifnull +6 -> 290
    //   287: goto +21 -> 308
    //   290: new 583	okhttp/internal/http/RouteException
    //   293: dup
    //   294: new 599	java/net/ProtocolException
    //   297: dup
    //   298: ldc_w 601
    //   301: invokespecial 602	java/net/ProtocolException:<init>	(Ljava/lang/String;)V
    //   304: invokespecial 589	okhttp/internal/http/RouteException:<init>	(Ljava/io/IOException;)V
    //   307: athrow
    //   308: aload_0
    //   309: getfield 270	okhttp/internal/http/RealConnection:connection	Lokhttp/internal/spdy/SpdyConnection;
    //   312: ifnull +185 -> 497
    //   315: aload_0
    //   316: getfield 51	okhttp/internal/http/RealConnection:this$0	Lokhttp/ConnectionPool;
    //   319: astore 6
    //   321: aload 6
    //   323: monitorenter
    //   324: aload_0
    //   325: aload_0
    //   326: getfield 270	okhttp/internal/http/RealConnection:connection	Lokhttp/internal/spdy/SpdyConnection;
    //   329: invokevirtual 604	okhttp/internal/spdy/SpdyConnection:read	()I
    //   332: putfield 40	okhttp/internal/http/RealConnection:address	I
    //   335: aload 6
    //   337: monitorexit
    //   338: return
    //   339: astore 7
    //   341: aload 6
    //   343: monitorexit
    //   344: aload 7
    //   346: athrow
    //   347: astore 9
    //   349: goto +10 -> 359
    //   352: astore 9
    //   354: goto +5 -> 359
    //   357: astore 9
    //   359: aload_0
    //   360: getfield 227	okhttp/internal/http/RealConnection:socket	Ljava/net/Socket;
    //   363: invokestatic 81	okhttp/internal/Util:closeQuietly	(Ljava/net/Socket;)V
    //   366: aload_0
    //   367: getfield 75	okhttp/internal/http/RealConnection:rawSocket	Ljava/net/Socket;
    //   370: invokestatic 81	okhttp/internal/Util:closeQuietly	(Ljava/net/Socket;)V
    //   373: aload_0
    //   374: aconst_null
    //   375: putfield 227	okhttp/internal/http/RealConnection:socket	Ljava/net/Socket;
    //   378: aload_0
    //   379: aconst_null
    //   380: putfield 75	okhttp/internal/http/RealConnection:rawSocket	Ljava/net/Socket;
    //   383: aload_0
    //   384: aconst_null
    //   385: putfield 85	okhttp/internal/http/RealConnection:source	Lokio/BufferedSource;
    //   388: aload_0
    //   389: aconst_null
    //   390: putfield 83	okhttp/internal/http/RealConnection:sink	Lokio/BufferedSink;
    //   393: aload_0
    //   394: aconst_null
    //   395: putfield 237	okhttp/internal/http/RealConnection:handshake	Lokhttp/Handshake;
    //   398: aload_0
    //   399: aconst_null
    //   400: putfield 225	okhttp/internal/http/RealConnection:protocol	Lokhttp/Protocol;
    //   403: aload_0
    //   404: aconst_null
    //   405: putfield 270	okhttp/internal/http/RealConnection:connection	Lokhttp/internal/spdy/SpdyConnection;
    //   408: aload 7
    //   410: aload 6
    //   412: aload_0
    //   413: getfield 53	okhttp/internal/http/RealConnection:route	Lokhttp/Route;
    //   416: invokevirtual 91	okhttp/Route:getId	()Ljava/net/InetSocketAddress;
    //   419: aload_0
    //   420: getfield 53	okhttp/internal/http/RealConnection:route	Lokhttp/Route;
    //   423: invokevirtual 95	okhttp/Route:getProxy	()Ljava/net/Proxy;
    //   426: aconst_null
    //   427: aload 9
    //   429: invokevirtual 607	okhttp/HttpHost:create	(Lokhttp/Map;Ljava/net/InetSocketAddress;Ljava/net/Proxy;Lokhttp/Protocol;Ljava/io/IOException;)V
    //   432: aload 10
    //   434: ifnonnull +17 -> 451
    //   437: new 583	okhttp/internal/http/RouteException
    //   440: dup
    //   441: aload 9
    //   443: invokespecial 589	okhttp/internal/http/RouteException:<init>	(Ljava/io/IOException;)V
    //   446: astore 10
    //   448: goto +10 -> 458
    //   451: aload 10
    //   453: aload 9
    //   455: invokevirtual 610	okhttp/internal/http/RouteException:addConnectException	(Ljava/io/IOException;)V
    //   458: iload 5
    //   460: ifeq +16 -> 476
    //   463: aload 11
    //   465: aload 9
    //   467: invokevirtual 614	okhttp/internal/http/ConnectionSpecSelector:connectionFailed	(Ljava/io/IOException;)Z
    //   470: ifeq +6 -> 476
    //   473: goto -312 -> 161
    //   476: aload 10
    //   478: athrow
    //   479: new 616	java/lang/IllegalStateException
    //   482: dup
    //   483: ldc_w 618
    //   486: invokespecial 619	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   489: astore 6
    //   491: goto +3 -> 494
    //   494: aload 6
    //   496: athrow
    //   497: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	498	0	this	RealConnection
    //   0	498	1	paramInt1	int
    //   0	498	2	paramInt2	int
    //   0	498	3	paramInt3	int
    //   0	498	4	paramInt4	int
    //   0	498	5	paramBoolean	boolean
    //   0	498	6	paramMap	Map
    //   0	498	7	paramHttpHost	HttpHost
    //   172	3	8	bool	boolean
    //   17	245	9	localObject1	Object
    //   347	1	9	localIOException1	IOException
    //   352	1	9	localIOException2	IOException
    //   357	109	9	localIOException3	IOException
    //   159	318	10	localRouteException	RouteException
    //   28	436	11	localConnectionSpecSelector	ConnectionSpecSelector
    //   242	22	12	localObject2	Object
    //   255	11	13	localProtocol	Protocol
    // Exception table:
    //   from	to	target	type
    //   324	338	339	java/lang/Throwable
    //   341	344	339	java/lang/Throwable
    //   213	225	347	java/io/IOException
    //   231	238	347	java/io/IOException
    //   244	251	347	java/io/IOException
    //   257	270	347	java/io/IOException
    //   203	213	352	java/io/IOException
    //   167	174	357	java/io/IOException
    //   179	190	357	java/io/IOException
  }
  
  public void connect(SpdyConnection paramSpdyConnection)
  {
    ConnectionPool localConnectionPool = this$0;
    try
    {
      address = paramSpdyConnection.read();
      return;
    }
    catch (Throwable paramSpdyConnection)
    {
      throw paramSpdyConnection;
    }
  }
  
  public boolean connect()
  {
    return connection != null;
  }
  
  public boolean get(Address paramAddress, Route paramRoute)
  {
    if (allocations.size() < address)
    {
      if (noNewStreams) {
        return false;
      }
      if (!Internal.instance.get(route.getAddress(), paramAddress)) {
        return false;
      }
      if (paramAddress.url().host().equals(getRoute().getAddress().url().host())) {
        return true;
      }
      if (connection == null) {
        return false;
      }
      if (paramRoute == null) {
        return false;
      }
      if (paramRoute.getProxy().type() != Proxy.Type.DIRECT) {
        return false;
      }
      if (route.getProxy().type() != Proxy.Type.DIRECT) {
        return false;
      }
      if (!route.getId().equals(paramRoute.getId())) {
        return false;
      }
      if (paramRoute.getAddress().getHostnameVerifier() != OkHostnameVerifier.INSTANCE) {
        return false;
      }
      if (!toString(paramAddress.url())) {
        return false;
      }
      try
      {
        paramAddress.getCertificatePinner().check(paramAddress.url().host(), handshake().peerCertificates());
        return true;
      }
      catch (SSLPeerUnverifiedException paramAddress) {}
    }
    return false;
  }
  
  public Route getRoute()
  {
    return route;
  }
  
  public Handshake handshake()
  {
    return handshake;
  }
  
  public boolean isHealthy(boolean paramBoolean)
  {
    if ((!socket.isClosed()) && (!socket.isInputShutdown()))
    {
      if (socket.isOutputShutdown()) {
        return false;
      }
      Object localObject = connection;
      if (localObject != null) {
        return ((SpdyConnection)localObject).shutdown() ^ true;
      }
      if (!paramBoolean) {
        break label131;
      }
      localObject = socket;
      try
      {
        int i = ((Socket)localObject).getSoTimeout();
        try
        {
          socket.setSoTimeout(1);
          paramBoolean = source.exhausted();
          if (paramBoolean)
          {
            localObject = socket;
            ((Socket)localObject).setSoTimeout(i);
            return false;
          }
          localObject = socket;
          ((Socket)localObject).setSoTimeout(i);
          return true;
        }
        catch (Throwable localThrowable)
        {
          Socket localSocket = socket;
          localSocket.setSoTimeout(i);
          throw localThrowable;
        }
        return false;
      }
      catch (IOException localIOException)
      {
        return false;
      }
      catch (SocketTimeoutException localSocketTimeoutException)
      {
        return true;
      }
    }
    label131:
    return true;
  }
  
  public HttpStream newStream(OkHttpClient paramOkHttpClient, Connection paramConnection, StreamAllocation paramStreamAllocation)
  {
    SpdyConnection localSpdyConnection = connection;
    if (localSpdyConnection != null) {
      return new Http2xStream(paramOkHttpClient, paramConnection, paramStreamAllocation, localSpdyConnection);
    }
    socket.setSoTimeout(paramConnection.getProtocol());
    source.timeout().timeout(paramConnection.getProtocol(), TimeUnit.MILLISECONDS);
    sink.timeout().timeout(paramConnection.flush(), TimeUnit.MILLISECONDS);
    return new Http1xStream(paramOkHttpClient, paramStreamAllocation, source, sink);
  }
  
  public void receive(SpdyStream paramSpdyStream)
  {
    paramSpdyStream.close(ErrorCode.REFUSED_STREAM);
  }
  
  public Socket socket()
  {
    return socket;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Connection{");
    localStringBuilder.append(route.getAddress().url().host());
    localStringBuilder.append(":");
    localStringBuilder.append(route.getAddress().url().port());
    localStringBuilder.append(", proxy=");
    localStringBuilder.append(route.getProxy());
    localStringBuilder.append(" hostAddress=");
    localStringBuilder.append(route.getId());
    localStringBuilder.append(" cipherSuite=");
    Object localObject = handshake;
    if (localObject != null) {
      localObject = ((Handshake)localObject).cipherSuite();
    } else {
      localObject = "none";
    }
    localStringBuilder.append(localObject);
    localStringBuilder.append(" protocol=");
    localStringBuilder.append(protocol);
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
  
  public boolean toString(HttpUrl paramHttpUrl)
  {
    if (paramHttpUrl.port() != route.getAddress().url().port()) {
      return false;
    }
    if (!paramHttpUrl.host().equals(route.getAddress().url().host())) {
      return (handshake != null) && (OkHostnameVerifier.INSTANCE.verify(paramHttpUrl.host(), (X509Certificate)handshake.peerCertificates().get(0)));
    }
    return true;
  }
}
