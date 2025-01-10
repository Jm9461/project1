package okhttp.internal.httpclient;

import okhttp.Request;
import okhttp.Response;
import okhttp.ResponseBody;
import okhttp.a0.a;
import okio.Sink;

public abstract interface HttpStream
{
  public abstract Sink createRequestBody(Request paramRequest, long paramLong);
  
  public abstract void finishRequest();
  
  public abstract ResponseBody openResponseBody(Response paramResponse);
  
  public abstract a0.a readResponse(boolean paramBoolean);
  
  public abstract void writeRequestBody();
  
  public abstract void writeRequestHeaders(Request paramRequest);
}
