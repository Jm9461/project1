package okhttp.internal.httpclient;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import okhttp.Cookie;
import okhttp.CookieJar;
import okhttp.Headers;
import okhttp.Headers.Builder;
import okhttp.HttpUrl;
import okhttp.Request;
import okhttp.Response;
import okhttp.internal.Util;

public final class OkHeaders
{
  static
  {
    Pattern.compile(" +([^ \"=]*)=(:?\"([^\"]*)\"|([^ \"=]*)) *(:?,|$)");
  }
  
  public static long contentLength(Headers paramHeaders)
  {
    return stringToLong(paramHeaders.get("Content-Length"));
  }
  
  public static long contentLength(Response paramResponse)
  {
    return contentLength(paramResponse.headers());
  }
  
  public static boolean hasBody(Response paramResponse)
  {
    if (paramResponse.request().method().equals("HEAD")) {
      return false;
    }
    int i = paramResponse.code();
    if (((i < 100) || (i >= 200)) && (i != 204) && (i != 304)) {
      return true;
    }
    if (contentLength(paramResponse) == -1L) {
      return "chunked".equalsIgnoreCase(paramResponse.header("Transfer-Encoding"));
    }
    return true;
  }
  
  public static boolean hasVaryAll(Headers paramHeaders)
  {
    return varyFields(paramHeaders).contains("*");
  }
  
  public static boolean hasVaryAll(Response paramResponse)
  {
    return hasVaryAll(paramResponse.headers());
  }
  
  public static int parseSeconds(String paramString, int paramInt)
  {
    try
    {
      long l = Long.parseLong(paramString);
      if (l > 2147483647L) {
        return Integer.MAX_VALUE;
      }
      if (l < 0L) {
        return 0;
      }
      return (int)l;
    }
    catch (NumberFormatException paramString) {}
    return paramInt;
  }
  
  public static void receiveHeaders(CookieJar paramCookieJar, HttpUrl paramHttpUrl, Headers paramHeaders)
  {
    if (paramCookieJar == CookieJar.NO_COOKIES) {
      return;
    }
    paramHeaders = Cookie.parseAll(paramHttpUrl, paramHeaders);
    if (paramHeaders.isEmpty()) {
      return;
    }
    paramCookieJar.saveFromResponse(paramHttpUrl, paramHeaders);
  }
  
  public static int skipUntil(String paramString1, int paramInt, String paramString2)
  {
    while (paramInt < paramString1.length())
    {
      if (paramString2.indexOf(paramString1.charAt(paramInt)) != -1) {
        return paramInt;
      }
      paramInt += 1;
    }
    return paramInt;
  }
  
  public static int skipWhitespace(String paramString, int paramInt)
  {
    while (paramInt < paramString.length())
    {
      int i = paramString.charAt(paramInt);
      if ((i != 32) && (i != 9)) {
        return paramInt;
      }
      paramInt += 1;
    }
    return paramInt;
  }
  
  private static long stringToLong(String paramString)
  {
    if (paramString == null) {
      return -1L;
    }
    try
    {
      long l = Long.parseLong(paramString);
      return l;
    }
    catch (NumberFormatException paramString) {}
    return -1L;
  }
  
  public static Set varyFields(Headers paramHeaders)
  {
    Object localObject2 = Collections.emptySet();
    int i = 0;
    int k = paramHeaders.value();
    while (i < k)
    {
      if ("Vary".equalsIgnoreCase(paramHeaders.name(i)))
      {
        Object localObject3 = paramHeaders.value(i);
        Object localObject1 = localObject2;
        if (((Set)localObject2).isEmpty()) {
          localObject1 = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        }
        localObject3 = ((String)localObject3).split(",");
        int m = localObject3.length;
        int j = 0;
        for (;;)
        {
          localObject2 = localObject1;
          if (j >= m) {
            break;
          }
          localObject2 = localObject3[j].trim();
          ((Set)localObject1).add(localObject2);
          j += 1;
        }
      }
      i += 1;
    }
    return (Set)localObject2;
  }
  
  private static Set varyFields(Response paramResponse)
  {
    return varyFields(paramResponse.headers());
  }
  
  public static Headers varyHeaders(Headers paramHeaders1, Headers paramHeaders2)
  {
    paramHeaders2 = varyFields(paramHeaders2);
    if (paramHeaders2.isEmpty()) {
      return new Headers.Builder().build();
    }
    Headers.Builder localBuilder = new Headers.Builder();
    int i = 0;
    int j = paramHeaders1.value();
    while (i < j)
    {
      String str = paramHeaders1.name(i);
      if (paramHeaders2.contains(str)) {
        localBuilder.add(str, paramHeaders1.value(i));
      }
      i += 1;
    }
    return localBuilder.build();
  }
  
  public static Headers varyHeaders(Response paramResponse)
  {
    return varyHeaders(paramResponse.networkResponse().request().headers(), paramResponse.headers());
  }
  
  public static boolean varyMatches(Response paramResponse, Headers paramHeaders, Request paramRequest)
  {
    paramResponse = varyFields(paramResponse).iterator();
    while (paramResponse.hasNext())
    {
      String str = (String)paramResponse.next();
      if (!Util.get(paramHeaders.values(str), paramRequest.headers(str))) {
        return false;
      }
    }
    return true;
  }
}
