package okhttp;

import java.io.IOException;
import java.util.ArrayList;
import okhttp.internal.httpclient.ArticleFragment.2;
import okhttp.internal.httpclient.HttpConnection;
import okhttp.internal.internal.Platform;
import okhttp.internal.okhttp.Call;

final class ClassWriter
  implements Map
{
  final Request a;
  final okhttp.internal.httpclient.ClassWriter b;
  final OkHttpClient c;
  private boolean h;
  private HttpHost k;
  final boolean m;
  
  private ClassWriter(OkHttpClient paramOkHttpClient, Request paramRequest, boolean paramBoolean)
  {
    c = paramOkHttpClient;
    a = paramRequest;
    m = paramBoolean;
    b = new okhttp.internal.httpclient.ClassWriter(paramOkHttpClient, paramBoolean);
  }
  
  static ClassWriter get(OkHttpClient paramOkHttpClient, Request paramRequest, boolean paramBoolean)
  {
    paramRequest = new ClassWriter(paramOkHttpClient, paramRequest, paramBoolean);
    k = paramOkHttpClient.networkInterceptors().size(paramRequest);
    return paramRequest;
  }
  
  private void put()
  {
    Object localObject = Platform.get().add("response.body().close()");
    b.a(localObject);
  }
  
  String a()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str;
    if (c()) {
      str = "canceled ";
    } else {
      str = "";
    }
    localStringBuilder.append(str);
    if (m) {
      str = "web socket";
    } else {
      str = "call";
    }
    localStringBuilder.append(str);
    localStringBuilder.append(" to ");
    localStringBuilder.append(build());
    return localStringBuilder.toString();
  }
  
  String build()
  {
    return a.url().build();
  }
  
  public boolean c()
  {
    return b.get();
  }
  
  public ClassWriter clone()
  {
    return get(c, a, m);
  }
  
  public Response execute()
  {
    try
    {
      if (!h)
      {
        h = true;
        put();
        k.clear(this);
        Object localObject = c;
        try
        {
          ((OkHttpClient)localObject).getDispatcher().executed(this);
          localObject = get();
          if (localObject != null)
          {
            c.getDispatcher().finished(this);
            return localObject;
          }
          throw new IOException("Canceled");
        }
        catch (Throwable localThrowable1) {}catch (IOException localIOException)
        {
          k.add(this, (IOException)localIOException);
          throw localIOException;
        }
        c.getDispatcher().finished(this);
        throw localIOException;
      }
      throw new IllegalStateException("Already Executed");
    }
    catch (Throwable localThrowable2)
    {
      throw localThrowable2;
    }
  }
  
  Response get()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(c.transform());
    localArrayList.add(b);
    localArrayList.add(new okhttp.internal.httpclient.Preferences.8(c.cookieJar()));
    localArrayList.add(new Call(c.internalCache()));
    localArrayList.add(new okhttp.internal.http.Preferences.8(c));
    if (!m) {
      localArrayList.addAll(c.get());
    }
    localArrayList.add(new ArticleFragment.2(m));
    return new HttpConnection(localArrayList, null, null, null, 0, a, this, k, c.getConnectTimeout(), c.getReadTimeout(), c.getWriteTimeout()).get(a);
  }
}
