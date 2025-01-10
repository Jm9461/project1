package okhttp;

import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import okhttp.internal.Util;

public final class Handshake
{
  private final Language cipherSuite;
  private final List<Certificate> localCertificates;
  private final List<Certificate> peerCertificates;
  private final TlsVersion tlsVersion;
  
  private Handshake(TlsVersion paramTlsVersion, Language paramLanguage, List paramList1, List paramList2)
  {
    tlsVersion = paramTlsVersion;
    cipherSuite = paramLanguage;
    peerCertificates = paramList1;
    localCertificates = paramList2;
  }
  
  public static Handshake get(SSLSession paramSSLSession)
  {
    Object localObject1 = paramSSLSession.getCipherSuite();
    if (localObject1 != null)
    {
      Language localLanguage = Language.get((String)localObject1);
      localObject1 = paramSSLSession.getProtocol();
      if (localObject1 != null)
      {
        TlsVersion localTlsVersion = TlsVersion.forJavaName((String)localObject1);
        Object localObject2;
        try
        {
          localObject1 = paramSSLSession.getPeerCertificates();
        }
        catch (SSLPeerUnverifiedException localSSLPeerUnverifiedException)
        {
          localObject2 = null;
        }
        if (localObject2 != null) {
          localObject2 = Util.immutableList((Object[])localObject2);
        } else {
          localObject2 = Collections.emptyList();
        }
        paramSSLSession = paramSSLSession.getLocalCertificates();
        if (paramSSLSession != null) {
          paramSSLSession = Util.immutableList(paramSSLSession);
        } else {
          paramSSLSession = Collections.emptyList();
        }
        return new Handshake(localTlsVersion, localLanguage, (List)localObject2, paramSSLSession);
      }
      throw new IllegalStateException("tlsVersion == null");
    }
    throw new IllegalStateException("cipherSuite == null");
  }
  
  public static Handshake get(TlsVersion paramTlsVersion, Language paramLanguage, List paramList1, List paramList2)
  {
    if (paramTlsVersion != null)
    {
      if (paramLanguage != null) {
        return new Handshake(paramTlsVersion, paramLanguage, Util.immutableList(paramList1), Util.immutableList(paramList2));
      }
      throw new NullPointerException("cipherSuite == null");
    }
    throw new NullPointerException("tlsVersion == null");
  }
  
  public Language cipherSuite()
  {
    return cipherSuite;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Handshake)) {
      return false;
    }
    paramObject = (Handshake)paramObject;
    return (tlsVersion.equals(tlsVersion)) && (cipherSuite.equals(cipherSuite)) && (peerCertificates.equals(peerCertificates)) && (localCertificates.equals(localCertificates));
  }
  
  public int hashCode()
  {
    return (((17 * 31 + tlsVersion.hashCode()) * 31 + cipherSuite.hashCode()) * 31 + peerCertificates.hashCode()) * 31 + localCertificates.hashCode();
  }
  
  public List localCertificates()
  {
    return localCertificates;
  }
  
  public List peerCertificates()
  {
    return peerCertificates;
  }
  
  public TlsVersion tlsVersion()
  {
    return tlsVersion;
  }
}
