package okhttp.internal.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp.internal.tls.Resource;

final class ExtensionData
  extends Resource
{
  private final Object a;
  private final Method b;
  
  ExtensionData(Object paramObject, Method paramMethod)
  {
    a = paramObject;
    b = paramMethod;
  }
  
  public List clean(List paramList, String paramString)
  {
    try
    {
      int i = paramList.size();
      Object localObject1 = new X509Certificate[i];
      paramList = paramList.toArray((Object[])localObject1);
      paramList = (X509Certificate[])paramList;
      localObject1 = b;
      Object localObject2 = a;
      paramList = ((Method)localObject1).invoke(localObject2, new Object[] { paramList, "RSA", paramString });
      return (List)paramList;
    }
    catch (IllegalAccessException paramList)
    {
      throw new AssertionError(paramList);
    }
    catch (InvocationTargetException paramList)
    {
      paramString = new SSLPeerUnverifiedException(paramList.getMessage());
      paramString.initCause(paramList);
      throw paramString;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    return paramObject instanceof ExtensionData;
  }
  
  public int hashCode()
  {
    return 0;
  }
}
