package okhttp.internal.tls;

import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.security.auth.x500.X500Principal;

public final class Address
  implements TrustRootIndex
{
  private final Map<X500Principal, Set<X509Certificate>> data = new LinkedHashMap();
  
  public Address(X509Certificate... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      X509Certificate localX509Certificate = paramVarArgs[i];
      X500Principal localX500Principal = localX509Certificate.getSubjectX500Principal();
      Object localObject2 = (Set)data.get(localX500Principal);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject2 = new LinkedHashSet(1);
        localObject1 = localObject2;
        data.put(localX500Principal, localObject2);
      }
      ((Set)localObject1).add(localX509Certificate);
      i += 1;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    return ((paramObject instanceof Address)) && (data.equals(data));
  }
  
  public X509Certificate findByIssuerAndSignature(X509Certificate paramX509Certificate)
  {
    Object localObject = paramX509Certificate.getIssuerX500Principal();
    localObject = (Set)data.get(localObject);
    if (localObject == null) {
      return null;
    }
    localObject = ((Set)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      X509Certificate localX509Certificate = (X509Certificate)((Iterator)localObject).next();
      PublicKey localPublicKey = localX509Certificate.getPublicKey();
      try
      {
        paramX509Certificate.verify(localPublicKey);
        return localX509Certificate;
      }
      catch (Exception localException) {}
    }
    return null;
  }
  
  public int hashCode()
  {
    return data.hashCode();
  }
}
