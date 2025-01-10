package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public final class InflaterSource
  implements Source
{
  private int bufferBytesHeldByInflater;
  private boolean closed;
  private final Inflater inflater;
  private final BufferedSource source;
  
  InflaterSource(BufferedSource paramBufferedSource, Inflater paramInflater)
  {
    if (paramBufferedSource != null)
    {
      if (paramInflater != null)
      {
        source = paramBufferedSource;
        inflater = paramInflater;
        return;
      }
      throw new IllegalArgumentException("inflater == null");
    }
    throw new IllegalArgumentException("source == null");
  }
  
  private void releaseInflatedBytes()
  {
    int i = bufferBytesHeldByInflater;
    if (i == 0) {
      return;
    }
    i -= inflater.getRemaining();
    bufferBytesHeldByInflater -= i;
    source.skip(i);
  }
  
  public void close()
  {
    if (closed) {
      return;
    }
    inflater.end();
    closed = true;
    source.close();
  }
  
  public long read(Buffer paramBuffer, long paramLong)
  {
    if (paramLong >= 0L)
    {
      if (!closed)
      {
        if (paramLong == 0L) {
          return 0L;
        }
        for (;;)
        {
          boolean bool1 = refill();
          try
          {
            Segment localSegment = paramBuffer.writableSegment(1);
            long l = 8192 - limit;
            l = Math.min(paramLong, l);
            int i = (int)l;
            Object localObject = inflater;
            byte[] arrayOfByte = data;
            int j = limit;
            i = ((Inflater)localObject).inflate(arrayOfByte, j, i);
            if (i > 0)
            {
              limit += i;
              size += i;
              return i;
            }
            localObject = inflater;
            boolean bool2 = ((Inflater)localObject).finished();
            if (!bool2)
            {
              localObject = inflater;
              bool2 = ((Inflater)localObject).needsDictionary();
              if (!bool2)
              {
                if (!bool1) {
                  continue;
                }
                paramBuffer = new EOFException("source exhausted prematurely");
                throw paramBuffer;
              }
            }
            releaseInflatedBytes();
            if (pos == limit)
            {
              localObject = localSegment.pop();
              head = ((Segment)localObject);
              SegmentPool.recycle(localSegment);
            }
            return -1L;
          }
          catch (DataFormatException paramBuffer)
          {
            throw new IOException(paramBuffer);
          }
        }
      }
      throw new IllegalStateException("closed");
    }
    paramBuffer = new StringBuilder();
    paramBuffer.append("byteCount < 0: ");
    paramBuffer.append(paramLong);
    paramBuffer = new IllegalArgumentException(paramBuffer.toString());
    throw paramBuffer;
  }
  
  public boolean refill()
  {
    if (!inflater.needsInput()) {
      return false;
    }
    releaseInflatedBytes();
    if (inflater.getRemaining() == 0)
    {
      if (source.exhausted()) {
        return true;
      }
      Segment localSegment = source.buffer().head;
      int i = limit;
      int j = pos;
      bufferBytesHeldByInflater = (i - j);
      inflater.setInput(data, j, bufferBytesHeldByInflater);
      return false;
    }
    throw new IllegalStateException("?");
  }
  
  public Timeout timeout()
  {
    return source.timeout();
  }
}
