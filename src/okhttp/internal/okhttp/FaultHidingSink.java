package okhttp.internal.okhttp;

import java.io.IOException;
import okio.Buffer;
import okio.ForwardingSink;
import okio.Sink;

class FaultHidingSink
  extends ForwardingSink
{
  private boolean hasErrors;
  
  FaultHidingSink(Sink paramSink)
  {
    super(paramSink);
  }
  
  public void close()
  {
    if (hasErrors) {
      return;
    }
    try
    {
      super.close();
      return;
    }
    catch (IOException localIOException)
    {
      hasErrors = true;
      onException(localIOException);
    }
  }
  
  public void flush()
  {
    if (hasErrors) {
      return;
    }
    try
    {
      super.flush();
      return;
    }
    catch (IOException localIOException)
    {
      hasErrors = true;
      onException(localIOException);
    }
  }
  
  protected void onException(IOException paramIOException)
  {
    throw new NullPointerException("Null throw statement replaced by Soot");
  }
  
  public void write(Buffer paramBuffer, long paramLong)
  {
    if (hasErrors)
    {
      paramBuffer.skip(paramLong);
      return;
    }
    try
    {
      super.write(paramBuffer, paramLong);
      return;
    }
    catch (IOException paramBuffer)
    {
      hasErrors = true;
      onException(paramBuffer);
    }
  }
}
