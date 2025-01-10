package okhttp.internal.spdy;

import java.io.IOException;
import okhttp.internal.Util;
import okio.ByteString;

public final class Http2
{
  static final String[] BINARY;
  static final String[] FLAGS;
  private static final String[] TYPES;
  static final ByteString source = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
  
  static
  {
    TYPES = new String[] { "DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION" };
    FLAGS = new String[64];
    BINARY = new String['?'];
    int i = 0;
    for (;;)
    {
      localObject1 = BINARY;
      if (i >= localObject1.length) {
        break;
      }
      localObject1[i] = Util.format("%8s", new Object[] { Integer.toBinaryString(i) }).replace(' ', '0');
      i += 1;
    }
    Object localObject2 = FLAGS;
    localObject2[0] = "";
    localObject2[1] = "END_STREAM";
    Object localObject1 = new int[1];
    localObject1[0] = 1;
    localObject2[8] = "PADDED";
    int j = localObject1.length;
    i = 0;
    Object localObject3;
    while (i < j)
    {
      k = localObject1[i];
      localObject2 = FLAGS;
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(FLAGS[k]);
      ((StringBuilder)localObject3).append("|PADDED");
      localObject2[(k | 0x8)] = ((StringBuilder)localObject3).toString();
      i += 1;
    }
    localObject2 = FLAGS;
    localObject2[4] = "END_HEADERS";
    localObject2[32] = "PRIORITY";
    localObject2[36] = "END_HEADERS|PRIORITY";
    localObject2 = new int[3];
    Object tmp269_267 = localObject2;
    tmp269_267[0] = 4;
    Object tmp273_269 = tmp269_267;
    tmp273_269[1] = 32;
    Object tmp278_273 = tmp273_269;
    tmp278_273[2] = 36;
    tmp278_273;
    int k = localObject2.length;
    i = 0;
    while (i < k)
    {
      int m = localObject2[i];
      int n = localObject1.length;
      j = 0;
      while (j < n)
      {
        int i1 = localObject1[j];
        localObject3 = FLAGS;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(FLAGS[i1]);
        localStringBuilder.append('|');
        localStringBuilder.append(FLAGS[m]);
        localObject3[(i1 | m)] = localStringBuilder.toString();
        localObject3 = FLAGS;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(FLAGS[i1]);
        localStringBuilder.append('|');
        localStringBuilder.append(FLAGS[m]);
        localStringBuilder.append("|PADDED");
        localObject3[(i1 | m | 0x8)] = localStringBuilder.toString();
        j += 1;
      }
      i += 1;
    }
    i = 0;
    for (;;)
    {
      localObject1 = FLAGS;
      if (i >= localObject1.length) {
        break;
      }
      if (localObject1[i] == null) {
        localObject1[i] = BINARY[i];
      }
      i += 1;
    }
  }
  
  private Http2() {}
  
  static IOException access$getIoException(String paramString, Object... paramVarArgs)
  {
    throw new IOException(Util.format(paramString, paramVarArgs));
  }
  
  static String formatFlags(byte paramByte1, byte paramByte2)
  {
    if (paramByte2 == 0) {
      return "";
    }
    if ((paramByte1 != 2) && (paramByte1 != 3)) {
      if ((paramByte1 != 4) && (paramByte1 != 6))
      {
        if ((paramByte1 != 7) && (paramByte1 != 8))
        {
          Object localObject1 = FLAGS;
          if (paramByte2 < localObject1.length) {
            localObject1 = localObject1[paramByte2];
          } else {
            localObject1 = BINARY[paramByte2];
          }
          if ((paramByte1 == 5) && ((paramByte2 & 0x4) != 0)) {
            return ((String)localObject1).replace("HEADERS", "PUSH_PROMISE");
          }
          localObject2 = localObject1;
          if (paramByte1 == 0)
          {
            localObject2 = localObject1;
            if ((paramByte2 & 0x20) == 0) {
              return localObject2;
            }
            localObject2 = ((String)localObject1).replace("PRIORITY", "COMPRESSED");
          }
          return localObject2;
        }
      }
      else
      {
        if (paramByte2 == 1) {
          return "ACK";
        }
        return BINARY[paramByte2];
      }
    }
    Object localObject2 = BINARY[paramByte2];
    return localObject2;
  }
  
  static String formatHeader(boolean paramBoolean, int paramInt1, int paramInt2, byte paramByte1, byte paramByte2)
  {
    Object localObject = TYPES;
    if (paramByte1 < localObject.length) {
      localObject = localObject[paramByte1];
    } else {
      localObject = Util.format("0x%02x", new Object[] { Byte.valueOf(paramByte1) });
    }
    String str2 = formatFlags(paramByte1, paramByte2);
    String str1;
    if (paramBoolean) {
      str1 = "<<";
    } else {
      str1 = ">>";
    }
    return Util.format("%s 0x%08x %5d %-13s %s", new Object[] { str1, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), localObject, str2 });
  }
}
