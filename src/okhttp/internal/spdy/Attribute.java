package okhttp.internal.spdy;

import f.f;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import okio.ByteString;

final class Attribute
{
  static final Label[] a = { new Label(Label.TARGET_AUTHORITY, ""), new Label(Label.TARGET_METHOD, "GET"), new Label(Label.TARGET_METHOD, "POST"), new Label(Label.TARGET_PATH, "/"), new Label(Label.TARGET_PATH, "/index.html"), new Label(Label.TARGET_SCHEME, "http"), new Label(Label.TARGET_SCHEME, "https"), new Label(Label.RESPONSE_STATUS, "200"), new Label(Label.RESPONSE_STATUS, "204"), new Label(Label.RESPONSE_STATUS, "206"), new Label(Label.RESPONSE_STATUS, "304"), new Label(Label.RESPONSE_STATUS, "400"), new Label(Label.RESPONSE_STATUS, "404"), new Label(Label.RESPONSE_STATUS, "500"), new Label("accept-charset", ""), new Label("accept-encoding", "gzip, deflate"), new Label("accept-language", ""), new Label("accept-ranges", ""), new Label("accept", ""), new Label("access-control-allow-origin", ""), new Label("age", ""), new Label("allow", ""), new Label("authorization", ""), new Label("cache-control", ""), new Label("content-disposition", ""), new Label("content-encoding", ""), new Label("content-language", ""), new Label("content-length", ""), new Label("content-location", ""), new Label("content-range", ""), new Label("content-type", ""), new Label("cookie", ""), new Label("date", ""), new Label("etag", ""), new Label("expect", ""), new Label("expires", ""), new Label("from", ""), new Label("host", ""), new Label("if-match", ""), new Label("if-modified-since", ""), new Label("if-none-match", ""), new Label("if-range", ""), new Label("if-unmodified-since", ""), new Label("last-modified", ""), new Label("link", ""), new Label("location", ""), new Label("max-forwards", ""), new Label("proxy-authenticate", ""), new Label("proxy-authorization", ""), new Label("range", ""), new Label("referer", ""), new Label("refresh", ""), new Label("retry-after", ""), new Label("server", ""), new Label("set-cookie", ""), new Label("strict-transport-security", ""), new Label("transfer-encoding", ""), new Label("user-agent", ""), new Label("vary", ""), new Label("via", ""), new Label("www-authenticate", "") };
  static final Map<f, Integer> b = draw();
  
  static ByteString decode(ByteString paramByteString)
  {
    int i = 0;
    int j = paramByteString.size();
    while (i < j)
    {
      int k = paramByteString.read(i);
      if ((k >= 65) && (k <= 90))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("PROTOCOL_ERROR response malformed: mixed case name: ");
        localStringBuilder.append(paramByteString.utf8());
        throw new IOException(localStringBuilder.toString());
      }
      i += 1;
    }
    return paramByteString;
  }
  
  private static Map draw()
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap(a.length);
    int i = 0;
    for (;;)
    {
      Label[] arrayOfLabel = a;
      if (i >= arrayOfLabel.length) {
        break;
      }
      if (!localLinkedHashMap.containsKey(c)) {
        localLinkedHashMap.put(ac, Integer.valueOf(i));
      }
      i += 1;
    }
    return Collections.unmodifiableMap(localLinkedHashMap);
  }
}
