package okhttp.internal.spdy;

import f.f;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp.Connection;
import okhttp.Headers;
import okhttp.Headers.Builder;
import okhttp.HttpHost;
import okhttp.HttpUrl;
import okhttp.OkHttpClient;
import okhttp.Protocol;
import okhttp.Request;
import okhttp.Response;
import okhttp.ResponseBody;
import okhttp.a0.a;
import okhttp.internal.Internal;
import okhttp.internal.Util;
import okhttp.internal.http.StreamAllocation;
import okhttp.internal.httpclient.HttpStream;
import okhttp.internal.httpclient.OkHeaders;
import okhttp.internal.httpclient.RequestLine;
import okhttp.internal.httpclient.ResponseBody.1;
import okhttp.internal.httpclient.StatusLine;
import okio.ByteString;
import okio.Okio;
import okio.Sink;
import okio.Timeout;

public final class Http2xStream
  implements HttpStream
{
  private static final ByteString ENCODING;
  private static final List<f> HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableList(new ByteString[] { TARGET_SCHEME, TARGET_AUTHORITY, TE, ENCODING, RESPONSE_STATUS, UPGRADE, TARGET_METHOD, TARGET_PATH, Label.TARGET_METHOD, Label.TARGET_PATH, Label.TARGET_SCHEME, Label.TARGET_AUTHORITY });
  private static final List<f> HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableList(new ByteString[] { TARGET_SCHEME, TARGET_AUTHORITY, TE, ENCODING, RESPONSE_STATUS, UPGRADE, TARGET_METHOD, TARGET_PATH });
  private static final ByteString RESPONSE_STATUS;
  private static final ByteString TARGET_AUTHORITY;
  private static final ByteString TARGET_METHOD;
  private static final ByteString TARGET_PATH;
  private static final ByteString TARGET_SCHEME = ByteString.encodeUtf8("connection");
  private static final ByteString TE;
  private static final ByteString UPGRADE;
  private final Connection connection;
  private final SpdyConnection framedConnection;
  private SpdyStream stream;
  final StreamAllocation streamAllocation;
  
  static
  {
    TARGET_AUTHORITY = ByteString.encodeUtf8("host");
    TE = ByteString.encodeUtf8("keep-alive");
    ENCODING = ByteString.encodeUtf8("proxy-connection");
    UPGRADE = ByteString.encodeUtf8("transfer-encoding");
    RESPONSE_STATUS = ByteString.encodeUtf8("te");
    TARGET_METHOD = ByteString.encodeUtf8("encoding");
    TARGET_PATH = ByteString.encodeUtf8("upgrade");
  }
  
  public Http2xStream(OkHttpClient paramOkHttpClient, Connection paramConnection, StreamAllocation paramStreamAllocation, SpdyConnection paramSpdyConnection)
  {
    connection = paramConnection;
    streamAllocation = paramStreamAllocation;
    framedConnection = paramSpdyConnection;
  }
  
  public static List http2HeadersList(Request paramRequest)
  {
    Headers localHeaders = paramRequest.headers();
    ArrayList localArrayList = new ArrayList(localHeaders.value() + 4);
    localArrayList.add(new Label(Label.TARGET_METHOD, paramRequest.method()));
    localArrayList.add(new Label(Label.TARGET_PATH, RequestLine.requestPath(paramRequest.url())));
    String str = paramRequest.header("Host");
    if (str != null) {
      localArrayList.add(new Label(Label.TARGET_AUTHORITY, str));
    }
    localArrayList.add(new Label(Label.TARGET_SCHEME, paramRequest.url().scheme()));
    int i = 0;
    int j = localHeaders.value();
    while (i < j)
    {
      paramRequest = ByteString.encodeUtf8(localHeaders.name(i).toLowerCase(Locale.US));
      if (!HTTP_2_SKIPPED_REQUEST_HEADERS.contains(paramRequest)) {
        localArrayList.add(new Label(paramRequest, localHeaders.value(i)));
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static a0.a readHttp2HeadersList(List paramList)
  {
    Object localObject3 = null;
    Object localObject2 = new Headers.Builder();
    int i = 0;
    int j = paramList.size();
    while (i < j)
    {
      Object localObject1 = (Label)paramList.get(i);
      Object localObject4;
      if (localObject1 == null)
      {
        localObject1 = localObject3;
        localObject4 = localObject2;
        if (localObject3 != null)
        {
          localObject1 = localObject3;
          localObject4 = localObject2;
          if (code == 100)
          {
            localObject1 = null;
            localObject4 = new Headers.Builder();
          }
        }
      }
      else
      {
        ByteString localByteString = c;
        String str = g.utf8();
        if (localByteString.equals(Label.RESPONSE_STATUS))
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("HTTP/1.1 ");
          ((StringBuilder)localObject1).append(str);
          localObject1 = StatusLine.parse(((StringBuilder)localObject1).toString());
          localObject4 = localObject2;
        }
        else
        {
          localObject1 = localObject3;
          localObject4 = localObject2;
          if (!HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(localByteString))
          {
            Internal.instance.add((Headers.Builder)localObject2, localByteString.utf8(), str);
            localObject4 = localObject2;
            localObject1 = localObject3;
          }
        }
      }
      i += 1;
      localObject3 = localObject1;
      localObject2 = localObject4;
    }
    if (localObject3 != null)
    {
      paramList = new a0.a();
      paramList.protocol(Protocol.HTTP_2);
      paramList.code(code);
      paramList.message(message);
      paramList.headers(((Headers.Builder)localObject2).build());
      return paramList;
    }
    paramList = new ProtocolException("Expected ':status' header not present");
    throw paramList;
  }
  
  public Sink createRequestBody(Request paramRequest, long paramLong)
  {
    return stream.getSink();
  }
  
  public void finishRequest()
  {
    stream.getSink().close();
  }
  
  public ResponseBody openResponseBody(Response paramResponse)
  {
    StreamAllocation localStreamAllocation = streamAllocation;
    this$0.toString(buffer);
    return new ResponseBody.1(paramResponse.header("Content-Type"), OkHeaders.contentLength(paramResponse), Okio.buffer(new Base64InputStream(this, stream.getSource())));
  }
  
  public a0.a readResponse(boolean paramBoolean)
  {
    a0.a localA = readHttp2HeadersList(stream.read());
    if ((paramBoolean) && (Internal.instance.get(localA) == 100)) {
      return null;
    }
    return localA;
  }
  
  public void writeRequestBody()
  {
    framedConnection.flush();
  }
  
  public void writeRequestHeaders(Request paramRequest)
  {
    if (stream != null) {
      return;
    }
    boolean bool;
    if (paramRequest.body() != null) {
      bool = true;
    } else {
      bool = false;
    }
    paramRequest = http2HeadersList(paramRequest);
    stream = framedConnection.newStream(paramRequest, bool);
    stream.readTimeout().timeout(connection.getProtocol(), TimeUnit.MILLISECONDS);
    stream.writeTimeout().timeout(connection.flush(), TimeUnit.MILLISECONDS);
  }
}
