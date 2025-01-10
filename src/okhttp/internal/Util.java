package okhttp.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp.HttpUrl;
import okhttp.RequestBody;
import okhttp.ResponseBody;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

public final class Util
{
  public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
  public static final String[] EMPTY_STRING_ARRAY = new String[0];
  public static final TimeZone UTC;
  private static final Pattern VERIFY_AS_IP_ADDRESS = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");
  public static final Comparator<String> cipherSuites;
  public static final ResponseBody log = ResponseBody.create(null, EMPTY_BYTE_ARRAY);
  public static final Charset s;
  
  static
  {
    RequestBody.create(null, EMPTY_BYTE_ARRAY);
    ByteString.decodeHex("efbbbf");
    ByteString.decodeHex("feff");
    ByteString.decodeHex("fffe");
    ByteString.decodeHex("0000ffff");
    ByteString.decodeHex("ffff0000");
    s = Charset.forName("UTF-8");
    Charset.forName("ISO-8859-1");
    Charset.forName("UTF-16BE");
    Charset.forName("UTF-16LE");
    Charset.forName("UTF-32BE");
    Charset.forName("UTF-32LE");
    UTC = TimeZone.getTimeZone("GMT");
    cipherSuites = new Version.BuildAwareOrder();
  }
  
  public static void checkOffsetAndCount(long paramLong1, long paramLong2, long paramLong3)
  {
    if (((paramLong2 | paramLong3) >= 0L) && (paramLong2 <= paramLong1) && (paramLong1 - paramLong2 >= paramLong3)) {
      return;
    }
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public static AssertionError close(String paramString, Exception paramException)
  {
    paramString = new AssertionError(paramString);
    try
    {
      paramString.initCause(paramException);
      return paramString;
    }
    catch (IllegalStateException paramException) {}
    return paramString;
  }
  
  public static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null) {
      try
      {
        paramCloseable.close();
        return;
      }
      catch (Exception paramCloseable) {}catch (RuntimeException paramCloseable)
      {
        throw paramCloseable;
      }
    }
  }
  
  public static void closeQuietly(Socket paramSocket)
  {
    if (paramSocket != null) {
      try
      {
        paramSocket.close();
        return;
      }
      catch (Exception paramSocket) {}catch (RuntimeException paramSocket)
      {
        throw paramSocket;
      }
      catch (AssertionError paramSocket)
      {
        if (isAndroidGetsocknameError(paramSocket)) {
          return;
        }
        throw paramSocket;
      }
    }
  }
  
  public static String[] concat(String[] paramArrayOfString, String paramString)
  {
    String[] arrayOfString = new String[paramArrayOfString.length + 1];
    System.arraycopy(paramArrayOfString, 0, arrayOfString, 0, paramArrayOfString.length);
    arrayOfString[(arrayOfString.length - 1)] = paramString;
    return arrayOfString;
  }
  
  private static boolean containsInvalidHostnameAsciiCodes(String paramString)
  {
    int i = 0;
    while (i < paramString.length())
    {
      int j = paramString.charAt(i);
      if (j > 31)
      {
        if (j >= 127) {
          return true;
        }
        if (" #%/:?@[\\]".indexOf(j) != -1) {
          return true;
        }
        i += 1;
      }
      else
      {
        return true;
      }
    }
    return false;
  }
  
  public static int decodeHexDigit(char paramChar)
  {
    if ((paramChar >= '0') && (paramChar <= '9')) {
      return paramChar - '0';
    }
    if ((paramChar >= 'a') && (paramChar <= 'f')) {
      return paramChar - 'a' + 10;
    }
    if ((paramChar >= 'A') && (paramChar <= 'F')) {
      return paramChar - 'A' + 10;
    }
    return -1;
  }
  
  private static boolean decodeIpv4Suffix(String paramString, int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3)
  {
    int j = paramInt3;
    int i = paramInt1;
    while (i < paramInt2)
    {
      if (j == paramArrayOfByte.length) {
        return false;
      }
      paramInt1 = i;
      if (j != paramInt3)
      {
        if (paramString.charAt(i) != '.') {
          return false;
        }
        paramInt1 = i + 1;
      }
      int k = 0;
      i = paramInt1;
      while (i < paramInt2)
      {
        int m = paramString.charAt(i);
        if ((m < 48) || (m > 57)) {
          break;
        }
        if ((k == 0) && (paramInt1 != i)) {
          return false;
        }
        k = k * 10 + m - 48;
        if (k > 255) {
          return false;
        }
        i += 1;
      }
      if (i - paramInt1 == 0) {
        return false;
      }
      paramArrayOfByte[j] = ((byte)k);
      j += 1;
    }
    return j == paramInt3 + 4;
  }
  
  private static InetAddress decodeIpv6(String paramString, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[16];
    int i = 0;
    int j = -1;
    int i1 = -1;
    int m;
    for (int k = paramInt1;; k = paramInt1)
    {
      paramInt1 = i;
      m = j;
      if (k >= paramInt2) {
        break label309;
      }
      if (i == arrayOfByte.length) {
        return null;
      }
      int n;
      if ((k + 2 <= paramInt2) && (paramString.regionMatches(k, "::", 0, 2)))
      {
        if (j != -1) {
          return null;
        }
        k += 2;
        j = i + 2;
        i = j;
        n = j;
        m = i;
        paramInt1 = k;
        if (k == paramInt2)
        {
          paramInt1 = j;
          m = i;
          break label309;
        }
      }
      else
      {
        n = i;
        m = j;
        paramInt1 = k;
        if (i != 0) {
          if (paramString.regionMatches(k, ":", 0, 1))
          {
            paramInt1 = k + 1;
            n = i;
            m = j;
          }
          else
          {
            if (paramString.regionMatches(k, ".", 0, 1))
            {
              if (!decodeIpv4Suffix(paramString, i1, paramInt2, arrayOfByte, i - 2)) {
                return null;
              }
              paramInt1 = i + 2;
              m = j;
              break label309;
            }
            return null;
          }
        }
      }
      i = 0;
      k = paramInt1;
      while (paramInt1 < paramInt2)
      {
        j = decodeHexDigit(paramString.charAt(paramInt1));
        if (j == -1) {
          break;
        }
        i = (i << 4) + j;
        paramInt1 += 1;
      }
      j = paramInt1 - k;
      if (j == 0) {
        break;
      }
      if (j > 4) {
        return null;
      }
      i1 = n + 1;
      arrayOfByte[n] = ((byte)(i >>> 8 & 0xFF));
      j = i1 + 1;
      arrayOfByte[i1] = ((byte)(i & 0xFF));
      i = j;
      j = m;
      i1 = k;
    }
    return null;
    label309:
    if (paramInt1 != arrayOfByte.length)
    {
      if (m == -1) {
        return null;
      }
      System.arraycopy(arrayOfByte, m, arrayOfByte, arrayOfByte.length - (paramInt1 - m), paramInt1 - m);
      Arrays.fill(arrayOfByte, m, arrayOfByte.length - paramInt1 + m, (byte)0);
    }
    try
    {
      paramString = InetAddress.getByAddress(arrayOfByte);
      return paramString;
    }
    catch (UnknownHostException paramString)
    {
      paramString = new AssertionError();
      throw paramString;
    }
  }
  
  public static int delimiterOffset(String paramString, int paramInt1, int paramInt2, char paramChar)
  {
    while (paramInt1 < paramInt2)
    {
      if (paramString.charAt(paramInt1) == paramChar) {
        return paramInt1;
      }
      paramInt1 += 1;
    }
    return paramInt2;
  }
  
  public static int delimiterOffset(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    while (paramInt1 < paramInt2)
    {
      if (paramString2.indexOf(paramString1.charAt(paramInt1)) != -1) {
        return paramInt1;
      }
      paramInt1 += 1;
    }
    return paramInt2;
  }
  
  public static boolean discard(Source paramSource, int paramInt, TimeUnit paramTimeUnit)
  {
    try
    {
      boolean bool = skipAll(paramSource, paramInt, paramTimeUnit);
      return bool;
    }
    catch (IOException paramSource) {}
    return false;
  }
  
  private static String encode(byte[] paramArrayOfByte)
  {
    int m = -1;
    int k = 0;
    int i = 0;
    int j;
    while (i < paramArrayOfByte.length)
    {
      int i1;
      for (j = i;; j = i1 + 2)
      {
        i1 = j;
        if ((i1 >= 16) || (paramArrayOfByte[i1] != 0) || (paramArrayOfByte[(i1 + 1)] != 0)) {
          break;
        }
      }
      int i2 = i1 - i;
      int n = m;
      j = k;
      if (i2 > k)
      {
        n = m;
        j = k;
        if (i2 >= 4)
        {
          j = i2;
          n = i;
        }
      }
      i = i1 + 2;
      m = n;
      k = j;
    }
    Buffer localBuffer = new Buffer();
    i = 0;
    while (i < paramArrayOfByte.length) {
      if (i == m)
      {
        localBuffer.writeByte(58);
        j = i + k;
        i = j;
        if (j == 16)
        {
          localBuffer.writeByte(58);
          i = j;
        }
      }
      else
      {
        if (i > 0) {
          localBuffer.writeByte(58);
        }
        localBuffer.writeHexadecimalUnsignedLong((paramArrayOfByte[i] & 0xFF) << 8 | paramArrayOfByte[(i + 1)] & 0xFF);
        i += 2;
      }
    }
    return localBuffer.readUtf8();
  }
  
  public static boolean equals(Comparator paramComparator, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    if ((paramArrayOfString1 != null) && (paramArrayOfString2 != null) && (paramArrayOfString1.length != 0))
    {
      if (paramArrayOfString2.length == 0) {
        return false;
      }
      int k = paramArrayOfString1.length;
      int i = 0;
      while (i < k)
      {
        String str = paramArrayOfString1[i];
        int m = paramArrayOfString2.length;
        int j = 0;
        while (j < m)
        {
          if (paramComparator.compare(str, paramArrayOfString2[j]) == 0) {
            return true;
          }
          j += 1;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static String format(String paramString, Object... paramVarArgs)
  {
    return String.format(Locale.US, paramString, paramVarArgs);
  }
  
  public static int get(String paramString)
  {
    int i = 0;
    int j = paramString.length();
    while (i < j)
    {
      int k = paramString.charAt(i);
      if (k > 31)
      {
        if (k >= 127) {
          return i;
        }
        i += 1;
      }
      else
      {
        return i;
      }
    }
    return -1;
  }
  
  public static boolean get(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }
  
  public static String hostHeader(HttpUrl paramHttpUrl, boolean paramBoolean)
  {
    Object localObject;
    if (paramHttpUrl.host().contains(":"))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("[");
      ((StringBuilder)localObject).append(paramHttpUrl.host());
      ((StringBuilder)localObject).append("]");
      localObject = ((StringBuilder)localObject).toString();
    }
    else
    {
      localObject = paramHttpUrl.host();
    }
    if ((!paramBoolean) && (paramHttpUrl.port() == HttpUrl.defaultPort(paramHttpUrl.scheme()))) {
      return localObject;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(":");
    localStringBuilder.append(paramHttpUrl.port());
    return localStringBuilder.toString();
  }
  
  public static List immutableList(List paramList)
  {
    return Collections.unmodifiableList(new ArrayList(paramList));
  }
  
  public static List immutableList(Object... paramVarArgs)
  {
    return Collections.unmodifiableList(Arrays.asList((Object[])paramVarArgs.clone()));
  }
  
  public static String[] intersect(Comparator paramComparator, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    ArrayList localArrayList = new ArrayList();
    int k = paramArrayOfString1.length;
    int i = 0;
    while (i < k)
    {
      String str = paramArrayOfString1[i];
      int m = paramArrayOfString2.length;
      int j = 0;
      while (j < m)
      {
        if (paramComparator.compare(str, paramArrayOfString2[j]) == 0)
        {
          localArrayList.add(str);
          break;
        }
        j += 1;
      }
      i += 1;
    }
    return (String[])localArrayList.toArray(new String[localArrayList.size()]);
  }
  
  public static boolean isAndroidGetsocknameError(AssertionError paramAssertionError)
  {
    return (paramAssertionError.getCause() != null) && (paramAssertionError.getMessage() != null) && (paramAssertionError.getMessage().contains("getsockname failed"));
  }
  
  public static int match(Comparator paramComparator, String[] paramArrayOfString, String paramString)
  {
    int i = 0;
    int j = paramArrayOfString.length;
    while (i < j)
    {
      if (paramComparator.compare(paramArrayOfString[i], paramString) == 0) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public static String parse(String paramString)
  {
    Object localObject;
    if (paramString.contains(":"))
    {
      if ((paramString.startsWith("[")) && (paramString.endsWith("]"))) {
        localObject = decodeIpv6(paramString, 1, paramString.length() - 1);
      } else {
        localObject = decodeIpv6(paramString, 0, paramString.length());
      }
      if (localObject == null) {
        return null;
      }
      localObject = ((InetAddress)localObject).getAddress();
      if (localObject.length == 16) {
        return encode((byte[])localObject);
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Invalid IPv6 address: '");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("'");
      throw new AssertionError(((StringBuilder)localObject).toString());
    }
    try
    {
      paramString = IDN.toASCII(paramString);
      localObject = Locale.US;
      paramString = paramString.toLowerCase((Locale)localObject);
      boolean bool = paramString.isEmpty();
      if (bool) {
        return null;
      }
      bool = containsInvalidHostnameAsciiCodes(paramString);
      if (bool) {
        return null;
      }
      return paramString;
    }
    catch (IllegalArgumentException paramString) {}
    return null;
  }
  
  public static boolean skipAll(Source paramSource, int paramInt, TimeUnit paramTimeUnit)
  {
    long l2 = System.nanoTime();
    long l1;
    if (paramSource.timeout().hasDeadline()) {
      l1 = paramSource.timeout().deadlineNanoTime() - l2;
    } else {
      l1 = Long.MAX_VALUE;
    }
    paramSource.timeout().deadlineNanoTime(Math.min(l1, paramTimeUnit.toNanos(paramInt)) + l2);
    try
    {
      paramTimeUnit = new Buffer();
      for (;;)
      {
        long l3 = paramSource.read(paramTimeUnit, 8192L);
        if (l3 == -1L) {
          break;
        }
        paramTimeUnit.clear();
      }
      if (l1 == Long.MAX_VALUE)
      {
        paramSource.timeout().clearDeadline();
        return true;
      }
      paramSource.timeout().deadlineNanoTime(l2 + l1);
      return true;
    }
    catch (Throwable paramTimeUnit)
    {
      if (l1 == Long.MAX_VALUE) {
        paramSource.timeout().clearDeadline();
      } else {
        paramSource.timeout().deadlineNanoTime(l2 + l1);
      }
      throw paramTimeUnit;
    }
    catch (InterruptedIOException paramTimeUnit)
    {
      if (l1 == Long.MAX_VALUE)
      {
        paramSource.timeout().clearDeadline();
        return false;
      }
      paramSource.timeout().deadlineNanoTime(l2 + l1);
    }
    return false;
  }
  
  public static int skipTrailingAsciiWhitespace(String paramString, int paramInt1, int paramInt2)
  {
    paramInt2 -= 1;
    while (paramInt2 >= paramInt1)
    {
      int i = paramString.charAt(paramInt2);
      if ((i != 9) && (i != 10) && (i != 12) && (i != 13) && (i != 32)) {
        return paramInt2 + 1;
      }
      paramInt2 -= 1;
    }
    return paramInt1;
  }
  
  public static int skipWhitespace(String paramString, int paramInt1, int paramInt2)
  {
    while (paramInt1 < paramInt2)
    {
      int i = paramString.charAt(paramInt1);
      if ((i != 9) && (i != 10) && (i != 12) && (i != 13) && (i != 32)) {
        return paramInt1;
      }
      paramInt1 += 1;
    }
    return paramInt2;
  }
  
  public static ThreadFactory threadFactory(String paramString, boolean paramBoolean)
  {
    return new Util.1(paramString, paramBoolean);
  }
  
  public static String trimSubstring(String paramString, int paramInt1, int paramInt2)
  {
    paramInt1 = skipWhitespace(paramString, paramInt1, paramInt2);
    return paramString.substring(paramInt1, skipTrailingAsciiWhitespace(paramString, paramInt1, paramInt2));
  }
  
  public static boolean verifyAsIpAddress(String paramString)
  {
    return VERIFY_AS_IP_ADDRESS.matcher(paramString).matches();
  }
}
