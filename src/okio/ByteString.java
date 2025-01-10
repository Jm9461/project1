package okio;

import f.f;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class ByteString
  implements Serializable, Comparable<f>
{
  public static final ByteString EMPTY = of(new byte[0]);
  static final char[] HEX_DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  final byte[] data;
  transient int hashCode;
  transient String utf8;
  
  ByteString(byte[] paramArrayOfByte)
  {
    data = paramArrayOfByte;
  }
  
  public static ByteString decodeBase64(String paramString)
  {
    if (paramString != null)
    {
      paramString = Base64.decode(paramString);
      if (paramString != null) {
        return new ByteString(paramString);
      }
      return null;
    }
    throw new IllegalArgumentException("base64 == null");
  }
  
  public static ByteString decodeHex(String paramString)
  {
    if (paramString != null)
    {
      if (paramString.length() % 2 == 0)
      {
        localObject = new byte[paramString.length() / 2];
        int i = 0;
        while (i < localObject.length)
        {
          localObject[i] = ((byte)((decodeHexDigit(paramString.charAt(i * 2)) << 4) + decodeHexDigit(paramString.charAt(i * 2 + 1))));
          i += 1;
        }
        return of((byte[])localObject);
      }
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Unexpected hex string: ");
      ((StringBuilder)localObject).append(paramString);
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    paramString = new IllegalArgumentException("hex == null");
    throw paramString;
  }
  
  private static int decodeHexDigit(char paramChar)
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
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Unexpected hex digit: ");
    localStringBuilder.append(paramChar);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  private ByteString digest(String paramString)
  {
    try
    {
      paramString = MessageDigest.getInstance(paramString);
      byte[] arrayOfByte = data;
      paramString = of(paramString.digest(arrayOfByte));
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      throw new AssertionError(paramString);
    }
  }
  
  public static ByteString encodeUtf8(String paramString)
  {
    if (paramString != null)
    {
      ByteString localByteString = new ByteString(paramString.getBytes(Util.UTF_8));
      utf8 = paramString;
      return localByteString;
    }
    throw new IllegalArgumentException("s == null");
  }
  
  static int escape(String paramString, int paramInt)
  {
    int i = 0;
    int j = 0;
    int k = paramString.length();
    while (i < k)
    {
      if (j == paramInt) {
        return i;
      }
      int m = paramString.codePointAt(i);
      if (((Character.isISOControl(m)) && (m != 10) && (m != 13)) || (m == 65533)) {
        return -1;
      }
      j += 1;
      i += Character.charCount(m);
    }
    return paramString.length();
  }
  
  public static ByteString of(byte... paramVarArgs)
  {
    if (paramVarArgs != null) {
      return new ByteString((byte[])paramVarArgs.clone());
    }
    throw new IllegalArgumentException("data == null");
  }
  
  public String base64()
  {
    return Base64.encode(data);
  }
  
  public int compareTo(ByteString paramByteString)
  {
    int j = size();
    int k = paramByteString.size();
    int i = 0;
    int m = Math.min(j, k);
    while (i < m)
    {
      int n = read(i) & 0xFF;
      int i1 = paramByteString.read(i) & 0xFF;
      if (n == i1)
      {
        i += 1;
      }
      else
      {
        if (n < i1) {
          return -1;
        }
        return 1;
      }
    }
    if (j == k) {
      return 0;
    }
    if (j < k) {
      return -1;
    }
    return 1;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if ((paramObject instanceof ByteString))
    {
      int i = ((ByteString)paramObject).size();
      byte[] arrayOfByte = data;
      if ((i == arrayOfByte.length) && (((ByteString)paramObject).write(0, arrayOfByte, 0, arrayOfByte.length))) {
        return true;
      }
    }
    return false;
  }
  
  public int hashCode()
  {
    int i = hashCode;
    if (i != 0) {
      return i;
    }
    i = Arrays.hashCode(data);
    hashCode = i;
    return i;
  }
  
  public String hex()
  {
    byte[] arrayOfByte = data;
    char[] arrayOfChar1 = new char[arrayOfByte.length * 2];
    int j = 0;
    int k = arrayOfByte.length;
    int i = 0;
    while (i < k)
    {
      int m = arrayOfByte[i];
      int n = j + 1;
      char[] arrayOfChar2 = HEX_DIGITS;
      arrayOfChar1[j] = arrayOfChar2[(m >> 4 & 0xF)];
      j = n + 1;
      arrayOfChar1[n] = arrayOfChar2[(m & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar1);
  }
  
  public ByteString md5()
  {
    return digest("MD5");
  }
  
  public byte read(int paramInt)
  {
    return data[paramInt];
  }
  
  public ByteString sha256()
  {
    return digest("SHA-1");
  }
  
  public int size()
  {
    return data.length;
  }
  
  public ByteString substring()
  {
    return digest("SHA-256");
  }
  
  public ByteString substring(int paramInt1, int paramInt2)
  {
    if (paramInt1 >= 0)
    {
      Object localObject = data;
      if (paramInt2 <= localObject.length)
      {
        int i = paramInt2 - paramInt1;
        if (i >= 0)
        {
          if ((paramInt1 == 0) && (paramInt2 == localObject.length)) {
            return this;
          }
          localObject = new byte[i];
          System.arraycopy(data, paramInt1, localObject, 0, i);
          return new ByteString((byte[])localObject);
        }
        throw new IllegalArgumentException("endIndex < beginIndex");
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("endIndex > length(");
      ((StringBuilder)localObject).append(data.length);
      ((StringBuilder)localObject).append(")");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    throw new IllegalArgumentException("beginIndex < 0");
  }
  
  public ByteString toAsciiLowercase()
  {
    int i = 0;
    for (;;)
    {
      byte[] arrayOfByte = data;
      if (i >= arrayOfByte.length) {
        break;
      }
      int k = arrayOfByte[i];
      if ((k >= 65) && (k <= 90))
      {
        arrayOfByte = (byte[])arrayOfByte.clone();
        int j = i + 1;
        arrayOfByte[i] = ((byte)(k + 32));
        i = j;
        while (i < arrayOfByte.length)
        {
          j = arrayOfByte[i];
          if ((j >= 65) && (j <= 90)) {
            arrayOfByte[i] = ((byte)(j + 32));
          }
          i += 1;
        }
        return new ByteString(arrayOfByte);
      }
      i += 1;
    }
    return this;
  }
  
  public byte[] toByteArray()
  {
    return (byte[])data.clone();
  }
  
  public String toString()
  {
    if (data.length == 0) {
      return "[size=0]";
    }
    Object localObject2 = utf8();
    int i = escape((String)localObject2, 64);
    if (i == -1)
    {
      if (data.length <= 64)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("[hex=");
        ((StringBuilder)localObject1).append(hex());
        ((StringBuilder)localObject1).append("]");
        return ((StringBuilder)localObject1).toString();
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("[size=");
      ((StringBuilder)localObject1).append(data.length);
      ((StringBuilder)localObject1).append(" hex=");
      ((StringBuilder)localObject1).append(substring(0, 64).hex());
      ((StringBuilder)localObject1).append("?]");
      return ((StringBuilder)localObject1).toString();
    }
    Object localObject1 = ((String)localObject2).substring(0, i).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
    if (i < ((String)localObject2).length())
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("[size=");
      ((StringBuilder)localObject2).append(data.length);
      ((StringBuilder)localObject2).append(" text=");
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("?]");
      return ((StringBuilder)localObject2).toString();
    }
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("[text=");
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append("]");
    return ((StringBuilder)localObject2).toString();
  }
  
  public String utf8()
  {
    String str = utf8;
    if (str != null) {
      return str;
    }
    str = new String(data, Util.UTF_8);
    utf8 = str;
    return str;
  }
  
  void write(Buffer paramBuffer)
  {
    byte[] arrayOfByte = data;
    paramBuffer.write(arrayOfByte, 0, arrayOfByte.length);
  }
  
  public boolean write(int paramInt1, ByteString paramByteString, int paramInt2, int paramInt3)
  {
    return paramByteString.write(paramInt2, data, paramInt1, paramInt3);
  }
  
  public boolean write(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    if (paramInt1 >= 0)
    {
      byte[] arrayOfByte = data;
      if ((paramInt1 <= arrayOfByte.length - paramInt3) && (paramInt2 >= 0) && (paramInt2 <= paramArrayOfByte.length - paramInt3) && (Util.add(arrayOfByte, paramInt1, paramArrayOfByte, paramInt2, paramInt3))) {
        return true;
      }
    }
    return false;
  }
  
  public final boolean write(ByteString paramByteString)
  {
    return write(0, paramByteString, 0, paramByteString.size());
  }
}
