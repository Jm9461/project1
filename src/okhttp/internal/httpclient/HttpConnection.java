package okhttp.internal.httpclient;

import e.t;
import java.util.List;
import okhttp.Connection;
import okhttp.HttpHost;
import okhttp.Map;
import okhttp.Request;
import okhttp.Response;
import okhttp.URI;
import okhttp.internal.http.RealConnection;
import okhttp.internal.http.StreamAllocation;

public final class HttpConnection
  implements Connection
{
  private final List<t> buffer;
  private final int connection;
  private final Map data;
  private final StreamAllocation key;
  private final int length;
  private final HttpHost method;
  private final RealConnection name;
  private final int offset;
  private final Request res;
  private int result;
  private final HttpStream source;
  private final int stream;
  
  public HttpConnection(List paramList, StreamAllocation paramStreamAllocation, HttpStream paramHttpStream, RealConnection paramRealConnection, int paramInt1, Request paramRequest, Map paramMap, HttpHost paramHttpHost, int paramInt2, int paramInt3, int paramInt4)
  {
    buffer = paramList;
    name = paramRealConnection;
    key = paramStreamAllocation;
    source = paramHttpStream;
    offset = paramInt1;
    res = paramRequest;
    data = paramMap;
    method = paramHttpHost;
    connection = paramInt2;
    stream = paramInt3;
    length = paramInt4;
  }
  
  public int close()
  {
    return connection;
  }
  
  public HttpHost create()
  {
    return method;
  }
  
  public Request execute()
  {
    return res;
  }
  
  public int flush()
  {
    return length;
  }
  
  public Response get(Request paramRequest)
  {
    return get(paramRequest, key, source, name);
  }
  
  public Response get(Request paramRequest, StreamAllocation paramStreamAllocation, HttpStream paramHttpStream, RealConnection paramRealConnection)
  {
    if (offset < buffer.size())
    {
      result += 1;
      if ((source != null) && (!name.toString(paramRequest.url())))
      {
        paramRequest = new StringBuilder();
        paramRequest.append("network interceptor ");
        paramRequest.append(buffer.get(offset - 1));
        paramRequest.append(" must retain the same host and port");
        throw new IllegalStateException(paramRequest.toString());
      }
      if ((source != null) && (result > 1))
      {
        paramRequest = new StringBuilder();
        paramRequest.append("network interceptor ");
        paramRequest.append(buffer.get(offset - 1));
        paramRequest.append(" must call proceed() exactly once");
        throw new IllegalStateException(paramRequest.toString());
      }
      paramStreamAllocation = new HttpConnection(buffer, paramStreamAllocation, paramHttpStream, paramRealConnection, offset + 1, paramRequest, data, method, connection, stream, length);
      paramRequest = (okhttp.Object)buffer.get(offset);
      paramRealConnection = paramRequest.doInBackground(paramStreamAllocation);
      if ((paramHttpStream != null) && (offset + 1 < buffer.size()) && (result != 1))
      {
        paramStreamAllocation = new StringBuilder();
        paramStreamAllocation.append("network interceptor ");
        paramStreamAllocation.append(paramRequest);
        paramStreamAllocation.append(" must call proceed() exactly once");
        throw new IllegalStateException(paramStreamAllocation.toString());
      }
      if (paramRealConnection != null)
      {
        if (paramRealConnection.body() != null) {
          return paramRealConnection;
        }
        paramStreamAllocation = new StringBuilder();
        paramStreamAllocation.append("interceptor ");
        paramStreamAllocation.append(paramRequest);
        paramStreamAllocation.append(" returned a response with no body");
        throw new IllegalStateException(paramStreamAllocation.toString());
      }
      paramStreamAllocation = new StringBuilder();
      paramStreamAllocation.append("interceptor ");
      paramStreamAllocation.append(paramRequest);
      paramStreamAllocation.append(" returned null");
      throw new NullPointerException(paramStreamAllocation.toString());
    }
    throw new AssertionError();
  }
  
  public StreamAllocation get()
  {
    return key;
  }
  
  public URI getAddress()
  {
    return name;
  }
  
  public Map getBytes()
  {
    return data;
  }
  
  public int getProtocol()
  {
    return stream;
  }
  
  public HttpStream rawSource()
  {
    return source;
  }
}
