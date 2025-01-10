package okhttp.internal.okhttp;

import okhttp.CacheControl;
import okhttp.Request;

public final class Response
{
  public final okhttp.Response request;
  public final Request this$0;
  
  Response(Request paramRequest, okhttp.Response paramResponse)
  {
    this$0 = paramRequest;
    request = paramResponse;
  }
  
  public static boolean isCacheable(okhttp.Response paramResponse, Request paramRequest)
  {
    int i = paramResponse.code();
    if ((i != 200) && (i != 410) && (i != 414) && (i != 501) && (i != 203) && (i != 204)) {
      if (i != 307)
      {
        if ((i == 308) || (i == 404) || (i == 405)) {}
      }
      else {
        switch (i)
        {
        default: 
          return false;
        case 302: 
          if ((paramResponse.header("Expires") == null) && (paramResponse.cacheControl().maxAgeSeconds() == -1) && (!paramResponse.cacheControl().isPublic()) && (!paramResponse.cacheControl().isPrivate())) {
            return false;
          }
          break;
        }
      }
    }
    return (!paramResponse.cacheControl().noStore()) && (!paramRequest.cacheControl().noStore());
  }
}
