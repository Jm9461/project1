package okhttp.internal.internal;

import e.v;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import okhttp.Protocol;
import okhttp.internal.tls.Address;
import okhttp.internal.tls.Element;
import okhttp.internal.tls.Resource;
import okhttp.internal.tls.TrustRootIndex;
import okio.Buffer;

public class Platform
{
  private static final Platform PLATFORM = ;
  private static final Logger log = Logger.getLogger(v.class.getName());
  
  public Platform() {}
  
  static byte[] concatLengthPrefixed(List paramList)
  {
    Buffer localBuffer = new Buffer();
    int i = 0;
    int j = paramList.size();
    while (i < j)
    {
      Protocol localProtocol = (Protocol)paramList.get(i);
      if (localProtocol != Protocol.HTTP_1_0)
      {
        localBuffer.writeByte(localProtocol.toString().length());
        localBuffer.writeUtf8(localProtocol.toString());
      }
      i += 1;
    }
    return localBuffer.readByteArray();
  }
  
  private static Platform detect()
  {
    Object localObject = ByteVector.findPlatform();
    if (localObject != null) {
      return localObject;
    }
    if (invoke())
    {
      localObject = Resources.detect();
      if (localObject != null) {
        return localObject;
      }
    }
    localObject = FeatureDetector.create();
    if (localObject != null) {
      return localObject;
    }
    localObject = BinDecorator.findPlatform();
    if (localObject != null) {
      return localObject;
    }
    return new Platform();
  }
  
  public static List get(List paramList)
  {
    ArrayList localArrayList = new ArrayList(paramList.size());
    int i = 0;
    int j = paramList.size();
    while (i < j)
    {
      Protocol localProtocol = (Protocol)paramList.get(i);
      if (localProtocol != Protocol.HTTP_1_0) {
        localArrayList.add(localProtocol.toString());
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static Platform get()
  {
    return PLATFORM;
  }
  
  public static boolean invoke()
  {
    if ("conscrypt".equals(System.getProperty("okhttp.platform"))) {
      return true;
    }
    return "Conscrypt".equals(java.security.Security.getProviders()[0].getName());
  }
  
  public Object add(String paramString)
  {
    if (log.isLoggable(Level.FINE)) {
      return new Throwable(paramString);
    }
    return null;
  }
  
  public void close(SSLSocket paramSSLSocket) {}
  
  public void connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress, int paramInt)
  {
    paramSocket.connect(paramInetSocketAddress, paramInt);
  }
  
  public SSLContext createDefault()
  {
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      return localSSLContext;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new IllegalStateException("No TLS provider", localNoSuchAlgorithmException);
    }
  }
  
  public TrustRootIndex get(X509TrustManager paramX509TrustManager)
  {
    return new Address(paramX509TrustManager.getAcceptedIssuers());
  }
  
  public void get(String paramString, Object paramObject)
  {
    Object localObject = paramString;
    if (paramObject == null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(" To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);");
      localObject = ((StringBuilder)localObject).toString();
    }
    log(5, (String)localObject, (Throwable)paramObject);
  }
  
  public boolean get(String paramString)
  {
    return true;
  }
  
  public String getPrefix()
  {
    return "OkHttp";
  }
  
  public void log(int paramInt, String paramString, Throwable paramThrowable)
  {
    Level localLevel;
    if (paramInt == 5) {
      localLevel = Level.WARNING;
    } else {
      localLevel = Level.INFO;
    }
    log.log(localLevel, paramString, paramThrowable);
  }
  
  public String read(SSLSocket paramSSLSocket)
  {
    return null;
  }
  
  public Resource read(X509TrustManager paramX509TrustManager)
  {
    return new Element(get(paramX509TrustManager));
  }
  
  public void read(SSLSocket paramSSLSocket, String paramString, List paramList) {}
}
