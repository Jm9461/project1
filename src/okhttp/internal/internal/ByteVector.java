package okhttp.internal.internal;

import android.os.Build.VERSION;
import android.util.Log;
import e.f0.k.e;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import okhttp.internal.Util;
import okhttp.internal.tls.Resource;
import okhttp.internal.tls.TrustRootIndex;

class ByteVector
  extends Platform
{
  private final e<Socket> a;
  private final e<Socket> b;
  private final e<Socket> c;
  private final Type m = Type.a();
  private final e<Socket> n;
  
  ByteVector(Class paramClass, OptionalMethod paramOptionalMethod1, OptionalMethod paramOptionalMethod2, OptionalMethod paramOptionalMethod3, OptionalMethod paramOptionalMethod4)
  {
    b = paramOptionalMethod1;
    n = paramOptionalMethod2;
    c = paramOptionalMethod3;
    a = paramOptionalMethod4;
  }
  
  private boolean apply(String paramString, Class paramClass, Object paramObject)
  {
    try
    {
      paramClass = paramClass.getMethod("isCleartextTrafficPermitted", new Class[0]);
      paramClass = paramClass.invoke(paramObject, new Object[0]);
      paramClass = (Boolean)paramClass;
      boolean bool = paramClass.booleanValue();
      return bool;
    }
    catch (NoSuchMethodException paramClass) {}
    return super.get(paramString);
  }
  
  public static Platform findPlatform()
  {
    try
    {
      Class localClass = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
    }
    catch (ClassNotFoundException localClassNotFoundException1) {}
    try
    {
      Object localObject1 = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
      Object localObject2 = Boolean.TYPE;
      OptionalMethod localOptionalMethod2 = new OptionalMethod(null, "setUseSessionTickets", new Class[] { localObject2 });
      OptionalMethod localOptionalMethod3 = new OptionalMethod(null, "setHostname", new Class[] { String.class });
      boolean bool = validate();
      OptionalMethod localOptionalMethod1;
      if (bool)
      {
        localObject2 = new OptionalMethod([B.class, "getAlpnSelectedProtocol", new Class[0]);
        localOptionalMethod1 = new OptionalMethod(null, "setAlpnProtocols", new Class[] { [B.class });
      }
      else
      {
        localObject2 = null;
        localOptionalMethod1 = null;
      }
      localObject1 = new ByteVector((Class)localObject1, localOptionalMethod2, localOptionalMethod3, (OptionalMethod)localObject2, localOptionalMethod1);
      return localObject1;
    }
    catch (ClassNotFoundException localClassNotFoundException2) {}
    return null;
  }
  
  private boolean get(String paramString, Class paramClass, Object paramObject)
  {
    try
    {
      Object localObject = paramClass.getMethod("isCleartextTrafficPermitted", new Class[] { String.class });
      localObject = ((Method)localObject).invoke(paramObject, new Object[] { paramString });
      localObject = (Boolean)localObject;
      boolean bool = ((Boolean)localObject).booleanValue();
      return bool;
    }
    catch (NoSuchMethodException localNoSuchMethodException) {}
    return apply(paramString, paramClass, paramObject);
  }
  
  private static boolean validate()
  {
    if (Security.getProvider("GMSCore_OpenSSL") != null) {
      return true;
    }
    try
    {
      Class.forName("android.net.Network");
      return true;
    }
    catch (ClassNotFoundException localClassNotFoundException) {}
    return false;
  }
  
  public Object add(String paramString)
  {
    return m.a(paramString);
  }
  
  public void connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress, int paramInt)
  {
    try
    {
      paramSocket.connect(paramInetSocketAddress, paramInt);
      return;
    }
    catch (ClassCastException paramSocket)
    {
      if (Build.VERSION.SDK_INT == 26)
      {
        paramInetSocketAddress = new IOException("Exception in connect");
        paramInetSocketAddress.initCause(paramSocket);
        throw paramInetSocketAddress;
      }
      throw paramSocket;
    }
    catch (SecurityException paramSocket)
    {
      paramInetSocketAddress = new IOException("Exception in connect");
      paramInetSocketAddress.initCause(paramSocket);
      throw paramInetSocketAddress;
    }
    catch (AssertionError paramSocket)
    {
      if (Util.isAndroidGetsocknameError(paramSocket)) {
        throw new IOException(paramSocket);
      }
      throw paramSocket;
    }
  }
  
  public TrustRootIndex get(X509TrustManager paramX509TrustManager)
  {
    try
    {
      Object localObject = paramX509TrustManager.getClass();
      localObject = ((Class)localObject).getDeclaredMethod("findTrustAnchorByIssuerAndSignature", new Class[] { X509Certificate.class });
      ((Method)localObject).setAccessible(true);
      localObject = new ECCurve(paramX509TrustManager, (Method)localObject);
      return localObject;
    }
    catch (NoSuchMethodException localNoSuchMethodException) {}
    return super.get(paramX509TrustManager);
  }
  
  public void get(String paramString, Object paramObject)
  {
    if (!m.a(paramObject)) {
      log(5, paramString, null);
    }
  }
  
  public boolean get(String paramString)
  {
    try
    {
      try
      {
        Class localClass = Class.forName("android.security.NetworkSecurityPolicy");
        Method localMethod = localClass.getMethod("getInstance", new Class[0]);
        boolean bool = get(paramString, localClass, localMethod.invoke(null, new Object[0]));
        return bool;
      }
      catch (InvocationTargetException paramString) {}catch (IllegalArgumentException paramString) {}catch (IllegalAccessException paramString) {}
      throw Util.close("unable to determine cleartext support", (Exception)paramString);
    }
    catch (NoSuchMethodException localNoSuchMethodException) {}catch (ClassNotFoundException localClassNotFoundException) {}
    return super.get(paramString);
  }
  
  public void log(int paramInt, String paramString, Throwable paramThrowable)
  {
    int i = 5;
    if (paramInt != 5) {
      i = 3;
    }
    Object localObject = paramString;
    if (paramThrowable != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append('\n');
      ((StringBuilder)localObject).append(Log.getStackTraceString(paramThrowable));
      localObject = ((StringBuilder)localObject).toString();
    }
    paramInt = 0;
    int k = ((String)localObject).length();
    while (paramInt < k)
    {
      int j = ((String)localObject).indexOf('\n', paramInt);
      if (j == -1) {
        j = k;
      }
      int i1;
      do
      {
        i1 = Math.min(j, paramInt + 4000);
        Log.println(i, "OkHttp", ((String)localObject).substring(paramInt, i1));
        paramInt = i1;
      } while (i1 < j);
      paramInt = i1 + 1;
    }
  }
  
  public String read(SSLSocket paramSSLSocket)
  {
    OptionalMethod localOptionalMethod = c;
    if (localOptionalMethod == null) {
      return null;
    }
    if (!localOptionalMethod.invoke(paramSSLSocket)) {
      return null;
    }
    paramSSLSocket = (byte[])c.invokeWithoutCheckedException(paramSSLSocket, new Object[0]);
    if (paramSSLSocket != null) {
      return new String(paramSSLSocket, Util.s);
    }
    return null;
  }
  
  public Resource read(X509TrustManager paramX509TrustManager)
  {
    try
    {
      Object localObject1 = Class.forName("android.net.http.X509TrustManagerExtensions");
      Object localObject2 = ((Class)localObject1).getConstructor(new Class[] { X509TrustManager.class });
      localObject2 = ((Constructor)localObject2).newInstance(new Object[] { paramX509TrustManager });
      localObject1 = ((Class)localObject1).getMethod("checkServerTrusted", new Class[] { [Ljava.security.cert.X509Certificate.class, String.class, String.class });
      localObject1 = new ExtensionData(localObject2, (Method)localObject1);
      return localObject1;
    }
    catch (Exception localException) {}
    return super.read(paramX509TrustManager);
  }
  
  public void read(SSLSocket paramSSLSocket, String paramString, List paramList)
  {
    if (paramString != null)
    {
      b.invokeOptionalWithoutCheckedException(paramSSLSocket, new Object[] { Boolean.valueOf(true) });
      n.invokeOptionalWithoutCheckedException(paramSSLSocket, new Object[] { paramString });
    }
    paramString = a;
    if ((paramString != null) && (paramString.invoke(paramSSLSocket)))
    {
      paramString = Platform.concatLengthPrefixed(paramList);
      a.invokeWithoutCheckedException(paramSSLSocket, new Object[] { paramString });
    }
  }
}
