package okhttp.internal.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import okhttp.internal.Util;

final class FeatureDetector
  extends Platform
{
  final Method b;
  final Method c;
  
  FeatureDetector(Method paramMethod1, Method paramMethod2)
  {
    c = paramMethod1;
    b = paramMethod2;
  }
  
  public static FeatureDetector create()
  {
    try
    {
      Object localObject = SSLParameters.class.getMethod("setApplicationProtocols", new Class[] { [Ljava.lang.String.class });
      Method localMethod = SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]);
      localObject = new FeatureDetector((Method)localObject, localMethod);
      return localObject;
    }
    catch (NoSuchMethodException localNoSuchMethodException) {}
    return null;
  }
  
  public String read(SSLSocket paramSSLSocket)
  {
    Method localMethod = b;
    try
    {
      paramSSLSocket = localMethod.invoke(paramSSLSocket, new Object[0]);
      paramSSLSocket = (String)paramSSLSocket;
      if (paramSSLSocket != null)
      {
        boolean bool = paramSSLSocket.equals("");
        if (!bool) {
          return paramSSLSocket;
        }
      }
      return null;
    }
    catch (InvocationTargetException paramSSLSocket) {}catch (IllegalAccessException paramSSLSocket) {}
    throw Util.close("unable to get selected protocols", (Exception)paramSSLSocket);
  }
  
  public void read(SSLSocket paramSSLSocket, String paramString, List paramList)
  {
    try
    {
      paramString = paramSSLSocket.getSSLParameters();
      Object localObject = Platform.get(paramList);
      paramList = c;
      int i = ((List)localObject).size();
      String[] arrayOfString = new String[i];
      localObject = ((List)localObject).toArray(arrayOfString);
      paramList.invoke(paramString, new Object[] { localObject });
      paramSSLSocket.setSSLParameters(paramString);
      return;
    }
    catch (InvocationTargetException paramSSLSocket) {}catch (IllegalAccessException paramSSLSocket) {}
    throw Util.close("unable to set ssl parameters", (Exception)paramSSLSocket);
  }
}
