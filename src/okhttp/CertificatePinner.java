package okhttp;

import e.g.b;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp.internal.Util;
import okhttp.internal.tls.Resource;
import okio.ByteString;

public final class CertificatePinner
{
  public static final CertificatePinner DEFAULT = new RequestConfig.Builder().build();
  private final Set<g.b> array;
  private final Resource hash;
  
  CertificatePinner(Set paramSet, Resource paramResource)
  {
    array = paramSet;
    hash = paramResource;
  }
  
  public static String pin(Certificate paramCertificate)
  {
    if ((paramCertificate instanceof X509Certificate))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("sha256/");
      localStringBuilder.append(sha1((X509Certificate)paramCertificate).base64());
      return localStringBuilder.toString();
    }
    throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
  }
  
  static ByteString sha1(X509Certificate paramX509Certificate)
  {
    return ByteString.of(paramX509Certificate.getPublicKey().getEncoded()).substring();
  }
  
  static ByteString sha256(X509Certificate paramX509Certificate)
  {
    return ByteString.of(paramX509Certificate.getPublicKey().getEncoded()).sha256();
  }
  
  CertificatePinner check(Resource paramResource)
  {
    if (Util.get(hash, paramResource)) {
      return this;
    }
    return new CertificatePinner(array, paramResource);
  }
  
  public void check(String paramString, List paramList)
  {
    List localList2 = findMatchingPins(paramString);
    if (localList2.isEmpty()) {
      return;
    }
    Object localObject1 = hash;
    List localList1 = paramList;
    if (localObject1 != null) {
      localList1 = ((Resource)localObject1).clean(paramList, paramString);
    }
    int i = 0;
    int k = localList1.size();
    while (i < k)
    {
      X509Certificate localX509Certificate = (X509Certificate)localList1.get(i);
      localObject1 = null;
      paramList = null;
      j = 0;
      int m = localList2.size();
      while (j < m)
      {
        Pin localPin = (Pin)localList2.get(j);
        Object localObject2;
        if (hashAlgorithm.equals("sha256/"))
        {
          localObject2 = paramList;
          if (paramList == null) {
            localObject2 = sha1(localX509Certificate);
          }
          paramList = (List)localObject2;
          if (!hash.equals(localObject2)) {}
        }
        else
        {
          if (!hashAlgorithm.equals("sha1/")) {
            break label209;
          }
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = sha256(localX509Certificate);
          }
          localObject1 = localObject2;
          if (hash.equals(localObject2)) {
            return;
          }
        }
        j += 1;
        continue;
        label209:
        paramString = new StringBuilder();
        paramString.append("unsupported hashAlgorithm: ");
        paramString.append(hashAlgorithm);
        throw new AssertionError(paramString.toString());
      }
      i += 1;
    }
    paramList = new StringBuilder();
    paramList.append("Certificate pinning failure!");
    paramList = paramList.append("\n  Peer certificate chain:");
    i = 0;
    int j = localList1.size();
    while (i < j)
    {
      localObject1 = (X509Certificate)localList1.get(i);
      paramList.append("\n    ");
      paramList.append(pin((Certificate)localObject1));
      paramList.append(": ");
      paramList.append(((X509Certificate)localObject1).getSubjectDN().getName());
      i += 1;
    }
    paramList.append("\n  Pinned certificates for ");
    paramList.append(paramString);
    paramList.append(":");
    i = 0;
    j = localList2.size();
    while (i < j)
    {
      paramString = (Pin)localList2.get(i);
      paramList.append("\n    ");
      paramList.append(paramString);
      i += 1;
    }
    paramString = new SSLPeerUnverifiedException(paramList.toString());
    throw paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    return ((paramObject instanceof CertificatePinner)) && (Util.get(hash, hash)) && (array.equals(array));
  }
  
  List findMatchingPins(String paramString)
  {
    Object localObject1 = Collections.emptyList();
    Iterator localIterator = array.iterator();
    while (localIterator.hasNext())
    {
      Pin localPin = (Pin)localIterator.next();
      if (localPin.matches(paramString))
      {
        Object localObject2 = localObject1;
        if (((List)localObject1).isEmpty()) {
          localObject2 = new ArrayList();
        }
        ((List)localObject2).add(localPin);
        localObject1 = localObject2;
      }
    }
    return (List)localObject1;
  }
  
  public int hashCode()
  {
    Resource localResource = hash;
    int i;
    if (localResource != null) {
      i = localResource.hashCode();
    } else {
      i = 0;
    }
    return i * 31 + array.hashCode();
  }
  
  final class Pin
  {
    final String date;
    final ByteString hash;
    final String hashAlgorithm;
    final String pattern;
    
    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof Pin)) && (pattern.equals(pattern)) && (hashAlgorithm.equals(hashAlgorithm)) && (hash.equals(hash));
    }
    
    public int hashCode()
    {
      return ((17 * 31 + pattern.hashCode()) * 31 + hashAlgorithm.hashCode()) * 31 + hash.hashCode();
    }
    
    boolean matches(String paramString)
    {
      if (pattern.startsWith("*."))
      {
        int i = paramString.indexOf('.');
        if (paramString.length() - i - 1 == date.length())
        {
          String str = date;
          if (paramString.regionMatches(false, i + 1, str, 0, str.length())) {
            return true;
          }
        }
        return false;
      }
      return paramString.equals(date);
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(hashAlgorithm);
      localStringBuilder.append(hash.base64());
      return localStringBuilder.toString();
    }
  }
}
