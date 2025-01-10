package okhttp.internal.internal;

import e.f0.k.f;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.net.ssl.SSLSocket;
import okhttp.internal.Util;

class BinDecorator
  extends Platform
{
  private final Class<?> _bin;
  private final Class<?> _blockOff;
  private final Method a;
  private final Method b;
  private final Method c;
  
  BinDecorator(Method paramMethod1, Method paramMethod2, Method paramMethod3, Class paramClass1, Class paramClass2)
  {
    c = paramMethod1;
    b = paramMethod2;
    a = paramMethod3;
    _bin = paramClass1;
    _blockOff = paramClass2;
  }
  
  public static Platform findPlatform()
  {
    try
    {
      Object localObject1 = Class.forName("org.eclipse.jetty.alpn.ALPN");
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("org.eclipse.jetty.alpn.ALPN");
      ((StringBuilder)localObject2).append("$Provider");
      Object localObject4 = Class.forName(((StringBuilder)localObject2).toString());
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("org.eclipse.jetty.alpn.ALPN");
      ((StringBuilder)localObject2).append("$ClientProvider");
      localObject2 = Class.forName(((StringBuilder)localObject2).toString());
      Object localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("org.eclipse.jetty.alpn.ALPN");
      ((StringBuilder)localObject3).append("$ServerProvider");
      localObject3 = Class.forName(((StringBuilder)localObject3).toString());
      localObject4 = ((Class)localObject1).getMethod("put", new Class[] { SSLSocket.class, localObject4 });
      Method localMethod = ((Class)localObject1).getMethod("get", new Class[] { SSLSocket.class });
      localObject1 = ((Class)localObject1).getMethod("remove", new Class[] { SSLSocket.class });
      localObject1 = new BinDecorator((Method)localObject4, localMethod, (Method)localObject1, (Class)localObject2, (Class)localObject3);
      return localObject1;
    }
    catch (NoSuchMethodException localNoSuchMethodException) {}catch (ClassNotFoundException localClassNotFoundException) {}
    return null;
  }
  
  public void close(SSLSocket paramSSLSocket)
  {
    Method localMethod = a;
    try
    {
      localMethod.invoke(null, new Object[] { paramSSLSocket });
      return;
    }
    catch (InvocationTargetException paramSSLSocket) {}catch (IllegalAccessException paramSSLSocket) {}
    throw Util.close("unable to remove alpn", (Exception)paramSSLSocket);
  }
  
  public String read(SSLSocket paramSSLSocket)
  {
    Method localMethod = b;
    try
    {
      paramSSLSocket = Proxy.getInvocationHandler(localMethod.invoke(null, new Object[] { paramSSLSocket }));
      paramSSLSocket = (Platform.JettyNegoProvider)paramSSLSocket;
      if ((!b) && (i == null))
      {
        Platform.get().log(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", null);
        return null;
      }
      if (b) {
        return null;
      }
      return i;
    }
    catch (IllegalAccessException paramSSLSocket) {}catch (InvocationTargetException paramSSLSocket) {}
    throw Util.close("unable to get selected protocol", (Exception)paramSSLSocket);
  }
  
  public void read(SSLSocket paramSSLSocket, String paramString, List paramList)
  {
    Object localObject = Platform.get(paramList);
    try
    {
      paramString = f.class.getClassLoader();
      paramList = _bin;
      Class localClass = _blockOff;
      localObject = new Platform.JettyNegoProvider((List)localObject);
      paramString = Proxy.newProxyInstance(paramString, new Class[] { paramList, localClass }, (InvocationHandler)localObject);
      paramList = c;
      paramList.invoke(null, new Object[] { paramSSLSocket, paramString });
      return;
    }
    catch (IllegalAccessException paramSSLSocket) {}catch (InvocationTargetException paramSSLSocket) {}
    throw Util.close("unable to set alpn", (Exception)paramSSLSocket);
  }
}
