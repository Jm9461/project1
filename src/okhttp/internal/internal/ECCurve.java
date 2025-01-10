package okhttp.internal.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import okhttp.internal.Util;
import okhttp.internal.tls.TrustRootIndex;

final class ECCurve
  implements TrustRootIndex
{
  private final X509TrustManager a;
  private final Method b;
  
  ECCurve(X509TrustManager paramX509TrustManager, Method paramMethod)
  {
    b = paramMethod;
    a = paramX509TrustManager;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof ECCurve)) {
      return false;
    }
    paramObject = (ECCurve)paramObject;
    return (a.equals(a)) && (b.equals(b));
  }
  
  public X509Certificate findByIssuerAndSignature(X509Certificate paramX509Certificate)
  {
    Method localMethod = b;
    X509TrustManager localX509TrustManager = a;
    try
    {
      paramX509Certificate = localMethod.invoke(localX509TrustManager, new Object[] { paramX509Certificate });
      paramX509Certificate = (TrustAnchor)paramX509Certificate;
      if (paramX509Certificate != null)
      {
        paramX509Certificate = paramX509Certificate.getTrustedCert();
        return paramX509Certificate;
      }
      return null;
    }
    catch (InvocationTargetException paramX509Certificate)
    {
      return null;
    }
    catch (IllegalAccessException paramX509Certificate)
    {
      throw Util.close("unable to get issues and signature", paramX509Certificate);
    }
  }
  
  public int hashCode()
  {
    return a.hashCode() + b.hashCode() * 31;
  }
}
