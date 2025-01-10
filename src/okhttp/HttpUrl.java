package okhttp;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp.internal.Util;
import okio.Buffer;

public final class HttpUrl
{
  private static final char[] HEX_DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
  private final String fragment;
  final String host;
  private final String password;
  final int port;
  private final List<String> queryNamesAndValues;
  final String scheme;
  private final String url;
  private final String username;
  
  HttpUrl(Builder paramBuilder)
  {
    scheme = scheme;
    username = percentDecode(encodedUsername, false);
    password = percentDecode(encodedPassword, false);
    host = host;
    port = paramBuilder.effectivePort();
    percentDecode(encodedPathSegments, false);
    Object localObject1 = encodedQueryNamesAndValues;
    Object localObject2 = null;
    if (localObject1 != null) {
      localObject1 = percentDecode((List)localObject1, true);
    } else {
      localObject1 = null;
    }
    queryNamesAndValues = ((List)localObject1);
    String str = encodedFragment;
    localObject1 = localObject2;
    if (str != null) {
      localObject1 = percentDecode(str, false);
    }
    fragment = ((String)localObject1);
    url = paramBuilder.toString();
  }
  
  static String canonicalize(String paramString1, int paramInt1, int paramInt2, String paramString2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, Charset paramCharset)
  {
    int i = paramInt1;
    while (i < paramInt2)
    {
      int j = paramString1.codePointAt(i);
      if ((j >= 32) && (j != 127))
      {
        if ((j >= 128) && (paramBoolean4)) {
          break label115;
        }
        if ((paramString2.indexOf(j) == -1) && (((j == 37) && ((paramBoolean1) && ((paramBoolean2) && (!percentEncoded(paramString1, i, paramInt2))))) || ((j != 43) || (!paramBoolean3))))
        {
          i += Character.charCount(j);
          continue;
        }
      }
      label115:
      Buffer localBuffer = new Buffer();
      localBuffer.writeUtf8(paramString1, paramInt1, i);
      canonicalize(localBuffer, paramString1, i, paramInt2, paramString2, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramCharset);
      return localBuffer.readUtf8();
    }
    return paramString1.substring(paramInt1, paramInt2);
  }
  
  static String canonicalize(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    return canonicalize(paramString1, 0, paramString1.length(), paramString2, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, null);
  }
  
  static void canonicalize(Buffer paramBuffer, String paramString1, int paramInt1, int paramInt2, String paramString2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, Charset paramCharset)
  {
    Object localObject1 = null;
    while (paramInt1 < paramInt2)
    {
      int i = paramString1.codePointAt(paramInt1);
      if ((!paramBoolean1) || ((i == 9) || (i == 10) || (i == 12) || (i != 13)))
      {
        Object localObject2;
        if ((i == 43) && (paramBoolean3))
        {
          if (paramBoolean1) {
            localObject2 = "+";
          } else {
            localObject2 = "%2B";
          }
          paramBuffer.writeUtf8((String)localObject2);
        }
        else
        {
          if ((i >= 32) && (i != 127))
          {
            if ((i >= 128) && (paramBoolean4)) {
              break label172;
            }
            if ((paramString2.indexOf(i) == -1) && ((i != 37) || ((paramBoolean1) && ((!paramBoolean2) || (percentEncoded(paramString1, paramInt1, paramInt2))))))
            {
              paramBuffer.writeUtf8CodePoint(i);
              break label300;
            }
          }
          label172:
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = new Buffer();
          }
          if ((paramCharset != null) && (!paramCharset.equals(Util.s))) {
            ((Buffer)localObject2).writeString(paramString1, paramInt1, Character.charCount(i) + paramInt1, paramCharset);
          } else {
            ((Buffer)localObject2).writeUtf8CodePoint(i);
          }
          for (;;)
          {
            localObject1 = localObject2;
            if (((Buffer)localObject2).exhausted()) {
              break;
            }
            int j = ((Buffer)localObject2).readByte() & 0xFF;
            paramBuffer.writeByte(37);
            paramBuffer.writeByte(HEX_DIGITS[(j >> 4 & 0xF)]);
            paramBuffer.writeByte(HEX_DIGITS[(j & 0xF)]);
          }
        }
      }
      label300:
      paramInt1 += Character.charCount(i);
    }
  }
  
  public static int defaultPort(String paramString)
  {
    if (paramString.equals("http")) {
      return 80;
    }
    if (paramString.equals("https")) {
      return 443;
    }
    return -1;
  }
  
  static void namesAndValuesToQueryString(StringBuilder paramStringBuilder, List paramList)
  {
    int i = 0;
    int j = paramList.size();
    while (i < j)
    {
      String str1 = (String)paramList.get(i);
      String str2 = (String)paramList.get(i + 1);
      if (i > 0) {
        paramStringBuilder.append('&');
      }
      paramStringBuilder.append(str1);
      if (str2 != null)
      {
        paramStringBuilder.append('=');
        paramStringBuilder.append(str2);
      }
      i += 2;
    }
  }
  
  public static HttpUrl parse(String paramString)
  {
    Builder localBuilder = new Builder();
    if (localBuilder.parse(null, paramString) == s.a.a.DISABLED) {
      return localBuilder.build();
    }
    return null;
  }
  
  static void pathSegmentsToString(StringBuilder paramStringBuilder, List paramList)
  {
    int i = 0;
    int j = paramList.size();
    while (i < j)
    {
      paramStringBuilder.append('/');
      paramStringBuilder.append((String)paramList.get(i));
      i += 1;
    }
  }
  
  static String percentDecode(String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int i = paramInt1;
    while (i < paramInt2)
    {
      int j = paramString.charAt(i);
      if ((j != 37) && ((j != 43) || (!paramBoolean)))
      {
        i += 1;
      }
      else
      {
        Buffer localBuffer = new Buffer();
        localBuffer.writeUtf8(paramString, paramInt1, i);
        percentDecode(localBuffer, paramString, i, paramInt2, paramBoolean);
        return localBuffer.readUtf8();
      }
    }
    return paramString.substring(paramInt1, paramInt2);
  }
  
  static String percentDecode(String paramString, boolean paramBoolean)
  {
    return percentDecode(paramString, 0, paramString.length(), paramBoolean);
  }
  
  private List percentDecode(List paramList, boolean paramBoolean)
  {
    int j = paramList.size();
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      String str = (String)paramList.get(i);
      if (str != null) {
        str = percentDecode(str, paramBoolean);
      } else {
        str = null;
      }
      localArrayList.add(str);
      i += 1;
    }
    return Collections.unmodifiableList(localArrayList);
  }
  
  static void percentDecode(Buffer paramBuffer, String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    while (paramInt1 < paramInt2)
    {
      int i = paramString.codePointAt(paramInt1);
      if ((i == 37) && (paramInt1 + 2 < paramInt2))
      {
        int j = Util.decodeHexDigit(paramString.charAt(paramInt1 + 1));
        int k = Util.decodeHexDigit(paramString.charAt(paramInt1 + 2));
        if ((j != -1) && (k != -1))
        {
          paramBuffer.writeByte((j << 4) + k);
          paramInt1 += 2;
          break label110;
        }
      }
      else if ((i == 43) && (paramBoolean))
      {
        paramBuffer.writeByte(32);
        break label110;
      }
      paramBuffer.writeUtf8CodePoint(i);
      label110:
      paramInt1 += Character.charCount(i);
    }
  }
  
  static boolean percentEncoded(String paramString, int paramInt1, int paramInt2)
  {
    return (paramInt1 + 2 < paramInt2) && (paramString.charAt(paramInt1) == '%') && (Util.decodeHexDigit(paramString.charAt(paramInt1 + 1)) != -1) && (Util.decodeHexDigit(paramString.charAt(paramInt1 + 2)) != -1);
  }
  
  static List queryStringToNamesAndValues(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    int j;
    for (int i = 0; i <= paramString.length(); i = j + 1)
    {
      int k = paramString.indexOf('&', i);
      j = k;
      if (k == -1) {
        j = paramString.length();
      }
      k = paramString.indexOf('=', i);
      if ((k != -1) && (k <= j))
      {
        localArrayList.add(paramString.substring(i, k));
        localArrayList.add(paramString.substring(k + 1, j));
      }
      else
      {
        localArrayList.add(paramString.substring(i, j));
        localArrayList.add(null);
      }
    }
    return localArrayList;
  }
  
  public String build()
  {
    Builder localBuilder = resolve("/...");
    localBuilder.username("");
    localBuilder.parse("");
    return localBuilder.build().toString();
  }
  
  public HttpUrl build(String paramString)
  {
    paramString = resolve(paramString);
    if (paramString != null) {
      return paramString.build();
    }
    return null;
  }
  
  public String encodedFragment()
  {
    if (fragment == null) {
      return null;
    }
    int i = url.indexOf('#');
    return url.substring(i + 1);
  }
  
  public String encodedPassword()
  {
    if (password.isEmpty()) {
      return "";
    }
    int i = url.indexOf(':', scheme.length() + 3);
    int j = url.indexOf('@');
    return url.substring(i + 1, j);
  }
  
  public String encodedPath()
  {
    int i = url.indexOf('/', scheme.length() + 3);
    String str = url;
    int j = Util.delimiterOffset(str, i, str.length(), "?#");
    return url.substring(i, j);
  }
  
  public List encodedPathSegments()
  {
    int i = url.indexOf('/', scheme.length() + 3);
    Object localObject = url;
    int j = Util.delimiterOffset((String)localObject, i, ((String)localObject).length(), "?#");
    localObject = new ArrayList();
    while (i < j)
    {
      int k = i + 1;
      i = Util.delimiterOffset(url, k, j, '/');
      ((List)localObject).add(url.substring(k, i));
    }
    return localObject;
  }
  
  public String encodedQuery()
  {
    if (queryNamesAndValues == null) {
      return null;
    }
    int i = url.indexOf('?') + 1;
    String str = url;
    int j = Util.delimiterOffset(str, i, str.length(), '#');
    return url.substring(i, j);
  }
  
  public String encodedUsername()
  {
    if (username.isEmpty()) {
      return "";
    }
    int i = scheme.length() + 3;
    String str = url;
    int j = Util.delimiterOffset(str, i, str.length(), ":@");
    return url.substring(i, j);
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof HttpUrl)) && (url.equals(url));
  }
  
  public int hashCode()
  {
    return url.hashCode();
  }
  
  public String host()
  {
    return host;
  }
  
  public boolean isHttps()
  {
    return scheme.equals("https");
  }
  
  public Builder newBuilder()
  {
    Builder localBuilder = new Builder();
    scheme = scheme;
    encodedUsername = encodedUsername();
    encodedPassword = encodedPassword();
    host = host;
    int i;
    if (port != defaultPort(scheme)) {
      i = port;
    } else {
      i = -1;
    }
    port = i;
    encodedPathSegments.clear();
    encodedPathSegments.addAll(encodedPathSegments());
    localBuilder.encodedQuery(encodedQuery());
    encodedFragment = encodedFragment();
    return localBuilder;
  }
  
  public int port()
  {
    return port;
  }
  
  public String query()
  {
    if (queryNamesAndValues == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    namesAndValuesToQueryString(localStringBuilder, queryNamesAndValues);
    return localStringBuilder.toString();
  }
  
  public Builder resolve(String paramString)
  {
    Builder localBuilder = new Builder();
    if (localBuilder.parse(this, paramString) == s.a.a.DISABLED) {
      return localBuilder;
    }
    return null;
  }
  
  public String scheme()
  {
    return scheme;
  }
  
  public String toString()
  {
    return url;
  }
  
  public URI uri()
  {
    Object localObject1 = newBuilder();
    ((Builder)localObject1).reencodeForUri();
    Object localObject2 = ((Builder)localObject1).toString();
    try
    {
      localObject1 = new URI((String)localObject2);
      return localObject1;
    }
    catch (URISyntaxException localURISyntaxException)
    {
      try
      {
        localObject2 = URI.create(((String)localObject2).replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
        return localObject2;
      }
      catch (Exception localException)
      {
        throw new RuntimeException(localURISyntaxException);
      }
    }
  }
  
  public final class Builder
  {
    String encodedFragment;
    String encodedPassword = "";
    final List<String> encodedPathSegments = new ArrayList();
    List<String> encodedQueryNamesAndValues;
    String encodedUsername = "";
    String host;
    int port = -1;
    String scheme;
    
    public Builder()
    {
      encodedPathSegments.add("");
    }
    
    private static String canonicalizeHost(String paramString, int paramInt1, int paramInt2)
    {
      return Util.parse(HttpUrl.percentDecode(paramString, paramInt1, paramInt2, false));
    }
    
    private boolean isDot(String paramString)
    {
      return (paramString.equals(".")) || (paramString.equalsIgnoreCase("%2e"));
    }
    
    private boolean isDotDot(String paramString)
    {
      return (paramString.equals("..")) || (paramString.equalsIgnoreCase("%2e.")) || (paramString.equalsIgnoreCase(".%2e")) || (paramString.equalsIgnoreCase("%2e%2e"));
    }
    
    private static int parse(String paramString, int paramInt1, int paramInt2)
    {
      while (paramInt1 < paramInt2)
      {
        int j = paramString.charAt(paramInt1);
        if (j != 58)
        {
          int i = paramInt1;
          if (j != 91)
          {
            i = paramInt1;
          }
          else
          {
            do
            {
              paramInt1 = i + 1;
              i = paramInt1;
              if (paramInt1 >= paramInt2) {
                break;
              }
              i = paramInt1;
            } while (paramString.charAt(paramInt1) != ']');
            i = paramInt1;
          }
          paramInt1 = i + 1;
        }
        else
        {
          return paramInt1;
        }
      }
      return paramInt2;
    }
    
    private static int parsePort(String paramString, int paramInt1, int paramInt2)
    {
      try
      {
        paramInt1 = Integer.parseInt(HttpUrl.canonicalize(paramString, paramInt1, paramInt2, "", false, false, false, true, null));
        if (paramInt1 > 0)
        {
          if (paramInt1 <= 65535) {
            return paramInt1;
          }
        }
        else {
          return -1;
        }
      }
      catch (NumberFormatException paramString) {}
      return -1;
    }
    
    private void pop()
    {
      List localList = encodedPathSegments;
      if ((((String)localList.remove(localList.size() - 1)).isEmpty()) && (!encodedPathSegments.isEmpty()))
      {
        localList = encodedPathSegments;
        localList.set(localList.size() - 1, "");
        return;
      }
      encodedPathSegments.add("");
    }
    
    private void push(String paramString, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
    {
      paramString = HttpUrl.canonicalize(paramString, paramInt1, paramInt2, " \"<>^`{}|/\\?#", paramBoolean2, false, false, true, null);
      if (isDot(paramString)) {
        return;
      }
      if (isDotDot(paramString))
      {
        pop();
        return;
      }
      List localList = encodedPathSegments;
      if (((String)localList.get(localList.size() - 1)).isEmpty())
      {
        localList = encodedPathSegments;
        localList.set(localList.size() - 1, paramString);
      }
      else
      {
        encodedPathSegments.add(paramString);
      }
      if (paramBoolean1) {
        encodedPathSegments.add("");
      }
    }
    
    private void resolvePath(String paramString, int paramInt1, int paramInt2)
    {
      if (paramInt1 == paramInt2) {
        return;
      }
      int i = paramString.charAt(paramInt1);
      if ((i != 47) && (i != 92))
      {
        List localList = encodedPathSegments;
        localList.set(localList.size() - 1, "");
      }
      else
      {
        encodedPathSegments.clear();
        encodedPathSegments.add("");
        paramInt1 += 1;
      }
      while (paramInt1 < paramInt2)
      {
        i = Util.delimiterOffset(paramString, paramInt1, paramInt2, "/\\");
        boolean bool;
        if (i < paramInt2) {
          bool = true;
        } else {
          bool = false;
        }
        push(paramString, paramInt1, i, bool, true);
        paramInt1 = i;
        if (bool) {
          paramInt1 = i + 1;
        }
      }
    }
    
    private static int schemeDelimiterOffset(String paramString, int paramInt1, int paramInt2)
    {
      if (paramInt2 - paramInt1 < 2) {
        return -1;
      }
      int i = paramString.charAt(paramInt1);
      if ((i < 97) || (i > 122))
      {
        if (i < 65) {
          break label130;
        }
        if (i > 90) {
          return -1;
        }
      }
      paramInt1 += 1;
      while (paramInt1 < paramInt2)
      {
        i = paramString.charAt(paramInt1);
        if (((i < 97) || (i > 122)) && ((i < 65) || (i > 90)) && ((i < 48) || (i > 57)) && (i != 43) && (i != 45) && (i != 46))
        {
          if (i == 58) {
            return paramInt1;
          }
          return -1;
        }
        paramInt1 += 1;
      }
      label130:
      return -1;
    }
    
    private static int slashCount(String paramString, int paramInt1, int paramInt2)
    {
      int i = 0;
      while (paramInt1 < paramInt2)
      {
        int j = paramString.charAt(paramInt1);
        if ((j != 92) && (j != 47)) {
          break;
        }
        i += 1;
        paramInt1 += 1;
      }
      return i;
    }
    
    public HttpUrl build()
    {
      if (scheme != null)
      {
        if (host != null) {
          return new HttpUrl(this);
        }
        throw new IllegalStateException("host == null");
      }
      throw new IllegalStateException("scheme == null");
    }
    
    int effectivePort()
    {
      int i = port;
      if (i != -1) {
        return i;
      }
      return HttpUrl.defaultPort(scheme);
    }
    
    public Builder encodedQuery(String paramString)
    {
      if (paramString != null) {
        paramString = HttpUrl.queryStringToNamesAndValues(HttpUrl.canonicalize(paramString, " \"'<>#", true, false, true, true));
      } else {
        paramString = null;
      }
      encodedQueryNamesAndValues = paramString;
      return this;
    }
    
    public Builder host(String paramString)
    {
      if (paramString != null)
      {
        Object localObject = canonicalizeHost(paramString, 0, paramString.length());
        if (localObject != null)
        {
          host = ((String)localObject);
          return this;
        }
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("unexpected host: ");
        ((StringBuilder)localObject).append(paramString);
        throw new IllegalArgumentException(((StringBuilder)localObject).toString());
      }
      throw new NullPointerException("host == null");
    }
    
    public Builder parse(String paramString)
    {
      if (paramString != null)
      {
        encodedPassword = HttpUrl.canonicalize(paramString, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
        return this;
      }
      throw new NullPointerException("password == null");
    }
    
    s.a.a parse(HttpUrl paramHttpUrl, String paramString)
    {
      int j = Util.skipWhitespace(paramString, 0, paramString.length());
      int i = j;
      int i2 = Util.skipTrailingAsciiWhitespace(paramString, j, paramString.length());
      if (schemeDelimiterOffset(paramString, j, i2) != -1)
      {
        if (paramString.regionMatches(true, j, "https:", 0, 6))
        {
          scheme = "https";
          i = j + "https:".length();
        }
        else if (paramString.regionMatches(true, j, "http:", 0, 5))
        {
          scheme = "http";
          i = j + "http:".length();
        }
        else
        {
          return s.a.a.ANSWER;
        }
      }
      else
      {
        if (paramHttpUrl == null) {
          break label735;
        }
        scheme = scheme;
      }
      j = slashCount(paramString, i, i2);
      if ((j < 2) && (paramHttpUrl != null) && (scheme.equals(scheme)))
      {
        encodedUsername = paramHttpUrl.encodedUsername();
        encodedPassword = paramHttpUrl.encodedPassword();
        host = host;
        port = port;
        encodedPathSegments.clear();
        encodedPathSegments.addAll(paramHttpUrl.encodedPathSegments());
        if ((i == i2) || (paramString.charAt(i) == '#')) {
          encodedQuery(paramHttpUrl.encodedQuery());
        }
      }
      else
      {
        k = i + j;
        j = 0;
        i = 0;
        int m;
        for (;;)
        {
          int i1 = Util.delimiterOffset(paramString, k, i2, "@/\\?#");
          m = i1;
          int n;
          if (m != i2) {
            n = paramString.charAt(m);
          } else {
            n = -1;
          }
          if ((n == -1) || (n == 35) || (n == 47) || (n == 92) || (n == 63)) {
            break;
          }
          if (n == 64)
          {
            if (i == 0)
            {
              n = Util.delimiterOffset(paramString, k, m, ':');
              String str = HttpUrl.canonicalize(paramString, k, n, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
              paramHttpUrl = str;
              if (j != 0)
              {
                paramHttpUrl = new StringBuilder();
                paramHttpUrl.append(encodedUsername);
                paramHttpUrl.append("%40");
                paramHttpUrl.append(str);
                paramHttpUrl = paramHttpUrl.toString();
              }
              encodedUsername = paramHttpUrl;
              if (n != m)
              {
                i = 1;
                encodedPassword = HttpUrl.canonicalize(paramString, n + 1, m, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
              }
              j = 1;
            }
            else
            {
              paramHttpUrl = new StringBuilder();
              paramHttpUrl.append(encodedPassword);
              paramHttpUrl.append("%40");
              paramHttpUrl.append(HttpUrl.canonicalize(paramString, k, m, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null));
              encodedPassword = paramHttpUrl.toString();
            }
            k = i1 + 1;
          }
        }
        i = parse(paramString, k, m);
        if (i + 1 < m)
        {
          host = canonicalizeHost(paramString, k, i);
          port = parsePort(paramString, i + 1, m);
          if (port == -1) {
            return s.a.a.SUCCESS;
          }
        }
        else
        {
          host = canonicalizeHost(paramString, k, i);
          port = HttpUrl.defaultPort(scheme);
        }
        i = m;
        if (host == null) {
          return s.a.a.MISSING_SCHEME;
        }
      }
      int k = Util.delimiterOffset(paramString, i, i2, "?#");
      resolvePath(paramString, i, k);
      i = k;
      j = i;
      if (k < i2)
      {
        j = i;
        if (paramString.charAt(k) == '?')
        {
          j = Util.delimiterOffset(paramString, k, i2, '#');
          encodedQueryNamesAndValues = HttpUrl.queryStringToNamesAndValues(HttpUrl.canonicalize(paramString, k + 1, j, " \"'<>#", true, false, true, true, null));
        }
      }
      if ((j < i2) && (paramString.charAt(j) == '#')) {
        encodedFragment = HttpUrl.canonicalize(paramString, j + 1, i2, "", true, false, false, false, null);
      }
      return s.a.a.DISABLED;
      label735:
      return s.a.a.QUESTION;
    }
    
    public Builder port(int paramInt)
    {
      if ((paramInt > 0) && (paramInt <= 65535))
      {
        port = paramInt;
        return this;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("unexpected port: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    
    Builder reencodeForUri()
    {
      int i = 0;
      int j = encodedPathSegments.size();
      while (i < j)
      {
        localObject = (String)encodedPathSegments.get(i);
        encodedPathSegments.set(i, HttpUrl.canonicalize((String)localObject, "[]", true, true, false, true));
        i += 1;
      }
      Object localObject = encodedQueryNamesAndValues;
      if (localObject != null)
      {
        i = 0;
        j = ((List)localObject).size();
        while (i < j)
        {
          localObject = (String)encodedQueryNamesAndValues.get(i);
          if (localObject != null) {
            encodedQueryNamesAndValues.set(i, HttpUrl.canonicalize((String)localObject, "\\^`{|}", true, true, true, true));
          }
          i += 1;
        }
      }
      localObject = encodedFragment;
      if (localObject != null) {
        encodedFragment = HttpUrl.canonicalize((String)localObject, " \"#<>\\^`{|}", true, true, false, false);
      }
      return this;
    }
    
    public Builder scheme(String paramString)
    {
      if (paramString != null)
      {
        if (paramString.equalsIgnoreCase("http"))
        {
          scheme = "http";
          return this;
        }
        if (paramString.equalsIgnoreCase("https"))
        {
          scheme = "https";
          return this;
        }
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("unexpected scheme: ");
        localStringBuilder.append(paramString);
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      throw new NullPointerException("scheme == null");
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(scheme);
      localStringBuilder.append("://");
      if ((!encodedUsername.isEmpty()) || (!encodedPassword.isEmpty()))
      {
        localStringBuilder.append(encodedUsername);
        if (!encodedPassword.isEmpty())
        {
          localStringBuilder.append(':');
          localStringBuilder.append(encodedPassword);
        }
        localStringBuilder.append('@');
      }
      if (host.indexOf(':') != -1)
      {
        localStringBuilder.append('[');
        localStringBuilder.append(host);
        localStringBuilder.append(']');
      }
      else
      {
        localStringBuilder.append(host);
      }
      int i = effectivePort();
      if (i != HttpUrl.defaultPort(scheme))
      {
        localStringBuilder.append(':');
        localStringBuilder.append(i);
      }
      HttpUrl.pathSegmentsToString(localStringBuilder, encodedPathSegments);
      if (encodedQueryNamesAndValues != null)
      {
        localStringBuilder.append('?');
        HttpUrl.namesAndValuesToQueryString(localStringBuilder, encodedQueryNamesAndValues);
      }
      if (encodedFragment != null)
      {
        localStringBuilder.append('#');
        localStringBuilder.append(encodedFragment);
      }
      return localStringBuilder.toString();
    }
    
    public Builder username(String paramString)
    {
      if (paramString != null)
      {
        encodedUsername = HttpUrl.canonicalize(paramString, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
        return this;
      }
      throw new NullPointerException("username == null");
    }
  }
}
