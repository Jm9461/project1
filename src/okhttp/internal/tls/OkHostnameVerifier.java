package okhttp.internal.tls;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import okhttp.internal.Util;

public final class OkHostnameVerifier
  implements HostnameVerifier
{
  public static final OkHostnameVerifier INSTANCE = new OkHostnameVerifier();
  
  private OkHostnameVerifier() {}
  
  public static List allSubjectAltNames(X509Certificate paramX509Certificate)
  {
    List localList = getSubjectAltNames(paramX509Certificate, 7);
    paramX509Certificate = getSubjectAltNames(paramX509Certificate, 2);
    ArrayList localArrayList = new ArrayList(localList.size() + paramX509Certificate.size());
    localArrayList.addAll(localList);
    localArrayList.addAll(paramX509Certificate);
    return localArrayList;
  }
  
  private static List getSubjectAltNames(X509Certificate paramX509Certificate, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramX509Certificate = paramX509Certificate.getSubjectAlternativeNames();
      if (paramX509Certificate == null)
      {
        paramX509Certificate = Collections.emptyList();
        return paramX509Certificate;
      }
      paramX509Certificate = paramX509Certificate.iterator();
      for (;;)
      {
        boolean bool = paramX509Certificate.hasNext();
        if (!bool) {
          break;
        }
        Object localObject1 = paramX509Certificate.next();
        localObject1 = (List)localObject1;
        if (localObject1 != null)
        {
          int i = ((List)localObject1).size();
          if (i >= 2)
          {
            Object localObject2 = ((List)localObject1).get(0);
            localObject2 = (Integer)localObject2;
            if (localObject2 != null)
            {
              i = ((Integer)localObject2).intValue();
              if (i == paramInt)
              {
                localObject1 = ((List)localObject1).get(1);
                localObject1 = (String)localObject1;
                if (localObject1 != null) {
                  localArrayList.add(localObject1);
                }
              }
            }
          }
        }
      }
      return localArrayList;
    }
    catch (CertificateParsingException paramX509Certificate) {}
    return Collections.emptyList();
  }
  
  private boolean verifyHostName(String paramString, X509Certificate paramX509Certificate)
  {
    paramString = paramString.toLowerCase(Locale.US);
    paramX509Certificate = getSubjectAltNames(paramX509Certificate, 2).iterator();
    while (paramX509Certificate.hasNext()) {
      if (verifyHostName(paramString, (String)paramX509Certificate.next())) {
        return true;
      }
    }
    return false;
  }
  
  private boolean verifyIpAddress(String paramString, X509Certificate paramX509Certificate)
  {
    paramX509Certificate = getSubjectAltNames(paramX509Certificate, 7);
    int i = 0;
    int j = paramX509Certificate.size();
    while (i < j)
    {
      if (paramString.equalsIgnoreCase((String)paramX509Certificate.get(i))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public boolean verify(String paramString, X509Certificate paramX509Certificate)
  {
    if (Util.verifyAsIpAddress(paramString)) {
      return verifyIpAddress(paramString, paramX509Certificate);
    }
    return verifyHostName(paramString, paramX509Certificate);
  }
  
  public boolean verify(String paramString, SSLSession paramSSLSession)
  {
    try
    {
      paramSSLSession = paramSSLSession.getPeerCertificates();
      paramSSLSession = (X509Certificate)paramSSLSession[0];
      boolean bool = verify(paramString, paramSSLSession);
      return bool;
    }
    catch (SSLException paramString) {}
    return false;
  }
  
  public boolean verifyHostName(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (paramString1.length() != 0) && (!paramString1.startsWith(".")))
    {
      if (paramString1.endsWith("..")) {
        return false;
      }
      if ((paramString2 != null) && (paramString2.length() != 0) && (!paramString2.startsWith(".")))
      {
        if (paramString2.endsWith("..")) {
          return false;
        }
        Object localObject = paramString1;
        if (!paramString1.endsWith("."))
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(paramString1);
          ((StringBuilder)localObject).append('.');
          localObject = ((StringBuilder)localObject).toString();
        }
        paramString1 = paramString2;
        if (!paramString2.endsWith("."))
        {
          paramString1 = new StringBuilder();
          paramString1.append(paramString2);
          paramString1.append('.');
          paramString1 = paramString1.toString();
        }
        paramString1 = paramString1.toLowerCase(Locale.US);
        if (!paramString1.contains("*")) {
          return ((String)localObject).equals(paramString1);
        }
        if (paramString1.startsWith("*."))
        {
          if (paramString1.indexOf('*', 1) != -1) {
            return false;
          }
          if (((String)localObject).length() < paramString1.length()) {
            return false;
          }
          if ("*.".equals(paramString1)) {
            return false;
          }
          paramString1 = paramString1.substring(1);
          if (!((String)localObject).endsWith(paramString1)) {
            return false;
          }
          int i = ((String)localObject).length() - paramString1.length();
          if (i > 0)
          {
            if (((String)localObject).lastIndexOf('.', i - 1) == -1) {
              break label265;
            }
            return false;
          }
          return true;
        }
      }
    }
    return false;
    label265:
    return true;
  }
}
