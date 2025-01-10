package okhttp.internal.internal;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import org.conscrypt.Conscrypt;
import org.conscrypt.OpenSSLProvider;

public class Resources
  extends Platform
{
  private Resources() {}
  
  public static Platform detect()
  {
    try
    {
      Class.forName("org.conscrypt.ConscryptEngineSocket");
      boolean bool = Conscrypt.isAvailable();
      if (!bool) {
        return null;
      }
      Conscrypt.setUseEngineSocketByDefault(true);
      Resources localResources = new Resources();
      return localResources;
    }
    catch (ClassNotFoundException localClassNotFoundException) {}
    return null;
  }
  
  private Provider getProvider()
  {
    return (Provider)new OpenSSLProvider();
  }
  
  public SSLContext createDefault()
  {
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("TLS", getProvider());
      return localSSLContext;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new IllegalStateException("No TLS provider", localNoSuchAlgorithmException);
    }
  }
  
  public String read(SSLSocket paramSSLSocket)
  {
    if (Conscrypt.isConscrypt(paramSSLSocket)) {
      return Conscrypt.getApplicationProtocol(paramSSLSocket);
    }
    return super.read(paramSSLSocket);
  }
  
  public void read(SSLSocket paramSSLSocket, String paramString, List paramList)
  {
    if (Conscrypt.isConscrypt(paramSSLSocket))
    {
      if (paramString != null)
      {
        Conscrypt.setUseSessionTickets(paramSSLSocket, true);
        Conscrypt.setHostname(paramSSLSocket, paramString);
      }
      Conscrypt.setApplicationProtocols(paramSSLSocket, (String[])Platform.get(paramList).toArray(new String[0]));
      return;
    }
    super.read(paramSSLSocket, paramString, paramList);
  }
}
