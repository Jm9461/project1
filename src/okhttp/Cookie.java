package okhttp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp.internal.Util;
import okhttp.internal.httpclient.HttpDate;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;

public final class Cookie
{
  private static final Pattern DAY_OF_MONTH_PATTERN = Pattern.compile("(\\d{1,2})[^\\d]*");
  private static final Pattern MONTH_PATTERN;
  private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
  private static final Pattern YEAR_PATTERN = Pattern.compile("(\\d{2,4})[^\\d]*");
  private final boolean b;
  private final long code;
  private final boolean f;
  private final String name;
  private final boolean p;
  private final String path;
  private final boolean r;
  private final String value;
  private final String version;
  
  static
  {
    MONTH_PATTERN = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
  }
  
  private Cookie(String paramString1, String paramString2, long paramLong, String paramString3, String paramString4, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    value = paramString1;
    name = paramString2;
    code = paramLong;
    path = paramString3;
    version = paramString4;
    f = paramBoolean1;
    b = paramBoolean2;
    p = paramBoolean3;
    r = paramBoolean4;
  }
  
  private static int dateCharacterOffset(String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    while (paramInt1 < paramInt2)
    {
      int i = paramString.charAt(paramInt1);
      if (((i >= 32) || (i == 9)) && (i < 127) && ((i < 48) || (i > 57)) && ((i < 97) || (i > 122)) && ((i < 65) || (i > 90)) && (i != 58)) {
        i = 0;
      } else {
        i = 1;
      }
      if (i == (paramBoolean ^ true)) {
        return paramInt1;
      }
      paramInt1 += 1;
    }
    return paramInt2;
  }
  
  private static boolean domainMatch(String paramString1, String paramString2)
  {
    if (paramString1.equals(paramString2)) {
      return true;
    }
    return (paramString1.endsWith(paramString2)) && (paramString1.charAt(paramString1.length() - paramString2.length() - 1) == '.') && (!Util.verifyAsIpAddress(paramString1));
  }
  
  private static String parse(String paramString)
  {
    if (!paramString.endsWith("."))
    {
      String str = paramString;
      if (paramString.startsWith(".")) {
        str = paramString.substring(1);
      }
      paramString = Util.parse(str);
      if (paramString != null) {
        return paramString;
      }
      throw new IllegalArgumentException();
    }
    throw new IllegalArgumentException();
  }
  
  static Cookie parse(long paramLong, HttpUrl paramHttpUrl, String paramString)
  {
    int j = paramString.length();
    int i = Util.delimiterOffset(paramString, 0, j, ';');
    int k = Util.delimiterOffset(paramString, 0, i, '=');
    if (k == i) {
      return null;
    }
    String str2 = Util.trimSubstring(paramString, 0, k);
    if (!str2.isEmpty())
    {
      if (Util.get(str2) != -1) {
        return null;
      }
      String str3 = Util.trimSubstring(paramString, k + 1, i);
      if (Util.get(str3) != -1) {
        return null;
      }
      long l1 = 253402300799999L;
      Object localObject2 = null;
      Object localObject1 = null;
      long l2 = -1L;
      boolean bool4 = false;
      boolean bool5 = false;
      boolean bool3 = true;
      boolean bool1 = false;
      i += 1;
      while (i < j)
      {
        k = Util.delimiterOffset(paramString, i, j, ';');
        int m = Util.delimiterOffset(paramString, i, k, '=');
        String str4 = Util.trimSubstring(paramString, i, m);
        Object localObject3;
        if (m < k) {
          localObject3 = Util.trimSubstring(paramString, m + 1, k);
        } else {
          localObject3 = "";
        }
        long l3;
        boolean bool6;
        Object localObject6;
        long l4;
        boolean bool7;
        boolean bool2;
        if (str4.equalsIgnoreCase("expires"))
        {
          try
          {
            l3 = parseExpires((String)localObject3, 0, ((String)localObject3).length());
            bool1 = true;
            l1 = l3;
          }
          catch (IllegalArgumentException localIllegalArgumentException1)
          {
            for (;;) {}
          }
          bool6 = bool4;
          l3 = l1;
          localObject3 = localObject2;
          localObject6 = localObject1;
          l4 = l2;
          bool7 = bool3;
          bool2 = bool1;
          break label541;
        }
        if (str4.equalsIgnoreCase("max-age"))
        {
          try
          {
            l3 = parseMaxAge(localIllegalArgumentException1);
            bool1 = true;
            l2 = l3;
          }
          catch (NumberFormatException localNumberFormatException)
          {
            Object localObject4;
            for (;;) {}
          }
          bool6 = bool4;
          l3 = l1;
          localObject4 = localObject2;
          localObject6 = localObject1;
          l4 = l2;
          bool7 = bool3;
          bool2 = bool1;
          break label541;
        }
        if (str4.equalsIgnoreCase("domain"))
        {
          try
          {
            String str1 = parse(localNumberFormatException);
            bool7 = false;
            bool6 = bool4;
            l3 = l1;
            localObject6 = localObject1;
            l4 = l2;
            bool2 = bool1;
          }
          catch (IllegalArgumentException localIllegalArgumentException2)
          {
            bool6 = bool4;
            l3 = l1;
            localObject5 = localObject2;
            localObject6 = localObject1;
            l4 = l2;
            bool7 = bool3;
            bool2 = bool1;
          }
        }
        else if (str4.equalsIgnoreCase("path"))
        {
          localObject6 = localObject5;
          bool6 = bool4;
          l3 = l1;
          localObject5 = localObject2;
          l4 = l2;
          bool7 = bool3;
          bool2 = bool1;
        }
        else if (str4.equalsIgnoreCase("secure"))
        {
          bool6 = true;
          l3 = l1;
          localObject5 = localObject2;
          localObject6 = localObject1;
          l4 = l2;
          bool7 = bool3;
          bool2 = bool1;
        }
        else
        {
          bool6 = bool4;
          l3 = l1;
          localObject5 = localObject2;
          localObject6 = localObject1;
          l4 = l2;
          bool7 = bool3;
          bool2 = bool1;
          if (str4.equalsIgnoreCase("httponly"))
          {
            bool5 = true;
            bool2 = bool1;
            bool7 = bool3;
            l4 = l2;
            localObject6 = localObject1;
            localObject5 = localObject2;
            l3 = l1;
            bool6 = bool4;
          }
        }
        label541:
        i = k + 1;
        bool4 = bool6;
        l1 = l3;
        localObject2 = localObject5;
        localObject1 = localObject6;
        l2 = l4;
        bool3 = bool7;
        bool1 = bool2;
      }
      if (l2 == Long.MIN_VALUE)
      {
        paramLong = Long.MIN_VALUE;
      }
      else if (l2 != -1L)
      {
        if (l2 <= 9223372036854775L) {
          l1 = 1000L * l2;
        } else {
          l1 = Long.MAX_VALUE;
        }
        l1 = paramLong + l1;
        if ((l1 >= paramLong) && (l1 <= 253402300799999L)) {
          paramLong = l1;
        } else {
          paramLong = 253402300799999L;
        }
      }
      else
      {
        paramLong = l1;
      }
      Object localObject5 = paramHttpUrl.host();
      if (localObject2 == null)
      {
        paramString = (String)localObject5;
      }
      else
      {
        if (!domainMatch((String)localObject5, (String)localObject2)) {
          return null;
        }
        paramString = (String)localObject2;
      }
      if ((((String)localObject5).length() != paramString.length()) && (PublicSuffixDatabase.createParser().parse(paramString) == null)) {
        return null;
      }
      localObject2 = "/";
      if ((localObject1 != null) && (((String)localObject1).startsWith("/")))
      {
        paramHttpUrl = (HttpUrl)localObject1;
      }
      else
      {
        localObject1 = paramHttpUrl.encodedPath();
        i = ((String)localObject1).lastIndexOf('/');
        paramHttpUrl = (HttpUrl)localObject2;
        if (i != 0) {
          paramHttpUrl = ((String)localObject1).substring(0, i);
        }
      }
      return new Cookie(str2, str3, paramLong, paramString, paramHttpUrl, bool4, bool5, bool3, bool1);
    }
    return null;
  }
  
  public static Cookie parse(HttpUrl paramHttpUrl, String paramString)
  {
    return parse(System.currentTimeMillis(), paramHttpUrl, paramString);
  }
  
  public static List parseAll(HttpUrl paramHttpUrl, Headers paramHeaders)
  {
    List localList = paramHeaders.values("Set-Cookie");
    paramHeaders = null;
    int i = 0;
    int j = localList.size();
    while (i < j)
    {
      Cookie localCookie = parse(paramHttpUrl, (String)localList.get(i));
      if (localCookie != null)
      {
        Object localObject = paramHeaders;
        if (paramHeaders == null) {
          localObject = new ArrayList();
        }
        ((List)localObject).add(localCookie);
        paramHeaders = (Headers)localObject;
      }
      i += 1;
    }
    if (paramHeaders != null) {
      return Collections.unmodifiableList(paramHeaders);
    }
    return Collections.emptyList();
  }
  
  private static long parseExpires(String paramString, int paramInt1, int paramInt2)
  {
    int i7 = dateCharacterOffset(paramString, paramInt1, paramInt2, false);
    int j = -1;
    int n = -1;
    int m = -1;
    int k = -1;
    int i = -1;
    paramInt1 = -1;
    Matcher localMatcher = TIME_PATTERN.matcher(paramString);
    while (i7 < paramInt2)
    {
      int i8 = dateCharacterOffset(paramString, i7 + 1, paramInt2, true);
      localMatcher.region(i7, i8);
      int i6;
      int i1;
      int i2;
      int i3;
      int i4;
      int i5;
      if ((j == -1) && (localMatcher.usePattern(TIME_PATTERN).matches()))
      {
        i6 = Integer.parseInt(localMatcher.group(1));
        i1 = Integer.parseInt(localMatcher.group(2));
        i2 = Integer.parseInt(localMatcher.group(3));
        i3 = k;
        i4 = i;
        i5 = paramInt1;
      }
      else if ((k == -1) && (localMatcher.usePattern(DAY_OF_MONTH_PATTERN).matches()))
      {
        i3 = Integer.parseInt(localMatcher.group(1));
        i1 = n;
        i2 = m;
        i4 = i;
        i5 = paramInt1;
        i6 = j;
      }
      else if ((i == -1) && (localMatcher.usePattern(MONTH_PATTERN).matches()))
      {
        String str = localMatcher.group(1).toLowerCase(Locale.US);
        i4 = MONTH_PATTERN.pattern().indexOf(str) / 4;
        i1 = n;
        i2 = m;
        i3 = k;
        i5 = paramInt1;
        i6 = j;
      }
      else
      {
        i1 = n;
        i2 = m;
        i3 = k;
        i4 = i;
        i5 = paramInt1;
        i6 = j;
        if (paramInt1 == -1)
        {
          i1 = n;
          i2 = m;
          i3 = k;
          i4 = i;
          i5 = paramInt1;
          i6 = j;
          if (localMatcher.usePattern(YEAR_PATTERN).matches())
          {
            i5 = Integer.parseInt(localMatcher.group(1));
            i6 = j;
            i4 = i;
            i3 = k;
            i2 = m;
            i1 = n;
          }
        }
      }
      i7 = dateCharacterOffset(paramString, i8 + 1, paramInt2, false);
      n = i1;
      m = i2;
      k = i3;
      i = i4;
      paramInt1 = i5;
      j = i6;
    }
    paramInt2 = paramInt1;
    if (paramInt1 >= 70)
    {
      paramInt2 = paramInt1;
      if (paramInt1 <= 99) {
        paramInt2 = paramInt1 + 1900;
      }
    }
    paramInt1 = paramInt2;
    if (paramInt2 >= 0)
    {
      paramInt1 = paramInt2;
      if (paramInt2 <= 69) {
        paramInt1 = paramInt2 + 2000;
      }
    }
    if (paramInt1 >= 1601)
    {
      if (i != -1)
      {
        if ((k >= 1) && (k <= 31))
        {
          if ((j >= 0) && (j <= 23))
          {
            if ((n >= 0) && (n <= 59))
            {
              if ((m >= 0) && (m <= 59))
              {
                paramString = new GregorianCalendar(Util.UTC);
                paramString.setLenient(false);
                paramString.set(1, paramInt1);
                paramString.set(2, i - 1);
                paramString.set(5, k);
                paramString.set(11, j);
                paramString.set(12, n);
                paramString.set(13, m);
                paramString.set(14, 0);
                return paramString.getTimeInMillis();
              }
              throw new IllegalArgumentException();
            }
            throw new IllegalArgumentException();
          }
          throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
      }
      throw new IllegalArgumentException();
    }
    paramString = new IllegalArgumentException();
    throw paramString;
  }
  
  private static long parseMaxAge(String paramString)
  {
    try
    {
      long l = Long.parseLong(paramString);
      if (l <= 0L) {
        return Long.MIN_VALUE;
      }
      return l;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      if (paramString.matches("-?\\d+"))
      {
        if (paramString.startsWith("-")) {
          return Long.MIN_VALUE;
        }
        return Long.MAX_VALUE;
      }
      throw localNumberFormatException;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Cookie)) {
      return false;
    }
    paramObject = (Cookie)paramObject;
    return (value.equals(value)) && (name.equals(name)) && (path.equals(path)) && (version.equals(version)) && (code == code) && (f == f) && (b == b) && (r == r) && (p == p);
  }
  
  public int hashCode()
  {
    int i = value.hashCode();
    int j = name.hashCode();
    int k = path.hashCode();
    int m = version.hashCode();
    long l = code;
    return ((((((((17 * 31 + i) * 31 + j) * 31 + k) * 31 + m) * 31 + (int)(l ^ l >>> 32)) * 31 + (f ^ true)) * 31 + (b ^ true)) * 31 + (r ^ true)) * 31 + (p ^ true);
  }
  
  public String name()
  {
    return name;
  }
  
  public String toString()
  {
    return toString(false);
  }
  
  String toString(boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(value);
    localStringBuilder.append('=');
    localStringBuilder.append(name);
    if (r) {
      if (code == Long.MIN_VALUE)
      {
        localStringBuilder.append("; max-age=0");
      }
      else
      {
        localStringBuilder.append("; expires=");
        localStringBuilder.append(HttpDate.format(new Date(code)));
      }
    }
    if (!p)
    {
      localStringBuilder.append("; domain=");
      if (paramBoolean) {
        localStringBuilder.append(".");
      }
      localStringBuilder.append(path);
    }
    localStringBuilder.append("; path=");
    localStringBuilder.append(version);
    if (f) {
      localStringBuilder.append("; secure");
    }
    if (b) {
      localStringBuilder.append("; httponly");
    }
    return localStringBuilder.toString();
  }
  
  public String value()
  {
    return value;
  }
}
