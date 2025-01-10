package okhttp.internal.http;

import e.k;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;
import okhttp.ConnectionSpec;
import okhttp.internal.Internal;

public final class ConnectionSpecSelector
{
  private final List<k> connectionSpecs;
  private boolean isFallback;
  private boolean isFallbackPossible;
  private int nextModeIndex = 0;
  
  public ConnectionSpecSelector(List paramList)
  {
    connectionSpecs = paramList;
  }
  
  private boolean isFallbackPossible(SSLSocket paramSSLSocket)
  {
    int i = nextModeIndex;
    while (i < connectionSpecs.size())
    {
      if (((ConnectionSpec)connectionSpecs.get(i)).isCompatible(paramSSLSocket)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public ConnectionSpec configureSecureSocket(SSLSocket paramSSLSocket)
  {
    Object localObject2 = null;
    int i = nextModeIndex;
    int j = connectionSpecs.size();
    for (;;)
    {
      localObject1 = localObject2;
      if (i >= j) {
        break;
      }
      localObject1 = (ConnectionSpec)connectionSpecs.get(i);
      if (((ConnectionSpec)localObject1).isCompatible(paramSSLSocket))
      {
        nextModeIndex = (i + 1);
        break;
      }
      i += 1;
    }
    if (localObject1 != null)
    {
      isFallbackPossible = isFallbackPossible(paramSSLSocket);
      Internal.instance.apply((ConnectionSpec)localObject1, paramSSLSocket, isFallback);
      return localObject1;
    }
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("Unable to find acceptable protocols. isFallback=");
    ((StringBuilder)localObject1).append(isFallback);
    ((StringBuilder)localObject1).append(", modes=");
    ((StringBuilder)localObject1).append(connectionSpecs);
    ((StringBuilder)localObject1).append(", supported protocols=");
    ((StringBuilder)localObject1).append(Arrays.toString(paramSSLSocket.getEnabledProtocols()));
    paramSSLSocket = new UnknownServiceException(((StringBuilder)localObject1).toString());
    throw paramSSLSocket;
  }
  
  public boolean connectionFailed(IOException paramIOException)
  {
    isFallback = true;
    if (!isFallbackPossible) {
      return false;
    }
    if ((paramIOException instanceof ProtocolException)) {
      return false;
    }
    if ((paramIOException instanceof InterruptedIOException)) {
      return false;
    }
    if (((paramIOException instanceof SSLHandshakeException)) && ((paramIOException.getCause() instanceof CertificateException))) {
      return false;
    }
    if ((paramIOException instanceof SSLPeerUnverifiedException)) {
      return false;
    }
    if (!(paramIOException instanceof SSLHandshakeException)) {
      return (paramIOException instanceof SSLProtocolException);
    }
    return true;
  }
}
