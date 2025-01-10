package okio;

import f.l;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public final class Okio
{
  static final Logger logger = Logger.getLogger(l.class.getName());
  
  private Okio() {}
  
  public static Sink appendingSink(File paramFile)
  {
    if (paramFile != null) {
      return sink(new FileOutputStream(paramFile, true));
    }
    throw new IllegalArgumentException("file == null");
  }
  
  public static BufferedSink buffer(Sink paramSink)
  {
    return new RealBufferedSink(paramSink);
  }
  
  public static BufferedSource buffer(Source paramSource)
  {
    return new RealBufferedSource(paramSource);
  }
  
  static boolean isAndroidGetsocknameError(AssertionError paramAssertionError)
  {
    return (paramAssertionError.getCause() != null) && (paramAssertionError.getMessage() != null) && (paramAssertionError.getMessage().contains("getsockname failed"));
  }
  
  public static Sink sink()
  {
    return new DeflaterSink();
  }
  
  public static Sink sink(File paramFile)
  {
    if (paramFile != null) {
      return sink(new FileOutputStream(paramFile));
    }
    throw new IllegalArgumentException("file == null");
  }
  
  public static Sink sink(OutputStream paramOutputStream)
  {
    return sink(paramOutputStream, new Timeout());
  }
  
  private static Sink sink(OutputStream paramOutputStream, Timeout paramTimeout)
  {
    if (paramOutputStream != null)
    {
      if (paramTimeout != null) {
        return new Okio.1(paramTimeout, paramOutputStream);
      }
      throw new IllegalArgumentException("timeout == null");
    }
    throw new IllegalArgumentException("out == null");
  }
  
  public static Sink sink(Socket paramSocket)
  {
    if (paramSocket != null)
    {
      if (paramSocket.getOutputStream() != null)
      {
        Context localContext = timeout(paramSocket);
        return localContext.sink(sink(paramSocket.getOutputStream(), localContext));
      }
      throw new IOException("socket's output stream == null");
    }
    throw new IllegalArgumentException("socket == null");
  }
  
  public static Source source(File paramFile)
  {
    if (paramFile != null) {
      return source(new FileInputStream(paramFile));
    }
    throw new IllegalArgumentException("file == null");
  }
  
  public static Source source(InputStream paramInputStream)
  {
    return source(paramInputStream, new Timeout());
  }
  
  private static Source source(InputStream paramInputStream, Timeout paramTimeout)
  {
    if (paramInputStream != null)
    {
      if (paramTimeout != null) {
        return new Okio.2(paramTimeout, paramInputStream);
      }
      throw new IllegalArgumentException("timeout == null");
    }
    throw new IllegalArgumentException("in == null");
  }
  
  public static Source source(Socket paramSocket)
  {
    if (paramSocket != null)
    {
      if (paramSocket.getInputStream() != null)
      {
        Context localContext = timeout(paramSocket);
        return localContext.source(source(paramSocket.getInputStream(), localContext));
      }
      throw new IOException("socket's input stream == null");
    }
    throw new IllegalArgumentException("socket == null");
  }
  
  private static Context timeout(Socket paramSocket)
  {
    return new Okio.3(paramSocket);
  }
}
