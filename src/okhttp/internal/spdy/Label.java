package okhttp.internal.spdy;

import okhttp.internal.Util;
import okio.ByteString;

public final class Label
{
  public static final ByteString RESPONSE_STATUS;
  public static final ByteString TARGET_AUTHORITY = ByteString.encodeUtf8(":authority");
  public static final ByteString TARGET_METHOD;
  public static final ByteString TARGET_PATH;
  public static final ByteString TARGET_SCHEME;
  public static final ByteString b = ByteString.encodeUtf8(":");
  public final ByteString c;
  final int f;
  public final ByteString g;
  
  static
  {
    RESPONSE_STATUS = ByteString.encodeUtf8(":status");
    TARGET_METHOD = ByteString.encodeUtf8(":method");
    TARGET_PATH = ByteString.encodeUtf8(":path");
    TARGET_SCHEME = ByteString.encodeUtf8(":scheme");
  }
  
  public Label(String paramString1, String paramString2)
  {
    this(ByteString.encodeUtf8(paramString1), ByteString.encodeUtf8(paramString2));
  }
  
  public Label(ByteString paramByteString, String paramString)
  {
    this(paramByteString, ByteString.encodeUtf8(paramString));
  }
  
  public Label(ByteString paramByteString1, ByteString paramByteString2)
  {
    c = paramByteString1;
    g = paramByteString2;
    f = (paramByteString1.size() + 32 + paramByteString2.size());
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Label))
    {
      paramObject = (Label)paramObject;
      if ((c.equals(c)) && (g.equals(g))) {
        return true;
      }
    }
    return false;
  }
  
  public int hashCode()
  {
    return (17 * 31 + c.hashCode()) * 31 + g.hashCode();
  }
  
  public String toString()
  {
    return Util.format("%s: %s", new Object[] { c.utf8(), g.utf8() });
  }
}
