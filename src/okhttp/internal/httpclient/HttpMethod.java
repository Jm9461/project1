package okhttp.internal.httpclient;

public final class HttpMethod
{
  public static boolean get(String paramString)
  {
    return (!paramString.equals("GET")) && (!paramString.equals("HEAD"));
  }
  
  public static boolean invalidatesCache(String paramString)
  {
    return (paramString.equals("POST")) || (paramString.equals("PATCH")) || (paramString.equals("PUT")) || (paramString.equals("DELETE")) || (paramString.equals("MOVE"));
  }
  
  public static boolean permitsRequestBody(String paramString)
  {
    return paramString.equals("PROPFIND") ^ true;
  }
  
  public static boolean redirectsToGet(String paramString)
  {
    return paramString.equals("PROPFIND");
  }
  
  public static boolean requiresRequestBody(String paramString)
  {
    return (paramString.equals("POST")) || (paramString.equals("PUT")) || (paramString.equals("PATCH")) || (paramString.equals("PROPPATCH")) || (paramString.equals("REPORT"));
  }
}
