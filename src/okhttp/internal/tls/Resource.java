package okhttp.internal.tls;

import java.util.List;
import javax.net.ssl.X509TrustManager;
import okhttp.internal.internal.Platform;

public abstract class Resource
{
  public Resource() {}
  
  public static Resource get(X509TrustManager paramX509TrustManager)
  {
    return Platform.get().read(paramX509TrustManager);
  }
  
  public abstract List clean(List paramList, String paramString);
}
