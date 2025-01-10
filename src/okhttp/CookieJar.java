package okhttp;

import java.util.Collections;
import java.util.List;

public abstract interface CookieJar
{
  public static final CookieJar NO_COOKIES = new CookieJar()
  {
    public List loadForRequest(HttpUrl paramAnonymousHttpUrl)
    {
      return Collections.emptyList();
    }
    
    public void saveFromResponse(HttpUrl paramAnonymousHttpUrl, List paramAnonymousList) {}
  };
  
  public abstract List loadForRequest(HttpUrl paramHttpUrl);
  
  public abstract void saveFromResponse(HttpUrl paramHttpUrl, List paramList);
}
