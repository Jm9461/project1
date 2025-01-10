package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

public final class GzipSource
  implements Source
{
  private final CRC32 crc = new CRC32();
  private final Inflater inflater;
  private final InflaterSource inflaterSource;
  private int section = 0;
  private final BufferedSource source;
  
  public GzipSource(Source paramSource)
  {
    if (paramSource != null)
    {
      inflater = new Inflater(true);
      source = Okio.buffer(paramSource);
      inflaterSource = new InflaterSource(source, inflater);
      return;
    }
    throw new IllegalArgumentException("source == null");
  }
  
  private void checkEqual(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt2 == paramInt1) {
      return;
    }
    throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", new Object[] { paramString, Integer.valueOf(paramInt2), Integer.valueOf(paramInt1) }));
  }
  
  private void consumeHeader()
  {
    source.require(10L);
    int j = source.buffer().getByte(3L);
    int i;
    if ((j >> 1 & 0x1) == 1) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      updateCrc(source.buffer(), 0L, 10L);
    }
    checkEqual("ID1ID2", 8075, source.readShort());
    source.skip(8L);
    if ((j >> 2 & 0x1) == 1)
    {
      source.require(2L);
      if (i != 0) {
        updateCrc(source.buffer(), 0L, 2L);
      }
      int k = source.buffer().readShortLe();
      source.require(k);
      if (i != 0) {
        updateCrc(source.buffer(), 0L, k);
      }
      source.skip(k);
    }
    long l;
    if ((j >> 3 & 0x1) == 1)
    {
      l = source.read((byte)0);
      if (l != -1L)
      {
        if (i != 0) {
          updateCrc(source.buffer(), 0L, l + 1L);
        }
        source.skip(l + 1L);
      }
      else
      {
        throw new EOFException();
      }
    }
    if ((j >> 4 & 0x1) == 1)
    {
      l = source.read((byte)0);
      if (l != -1L)
      {
        if (i != 0) {
          updateCrc(source.buffer(), 0L, l + 1L);
        }
        source.skip(1L + l);
      }
      else
      {
        throw new EOFException();
      }
    }
    if (i != 0)
    {
      checkEqual("FHCRC", source.readShortLe(), (short)(int)crc.getValue());
      crc.reset();
    }
  }
  
  private void consumeTrailer()
  {
    checkEqual("CRC", source.readIntLe(), (int)crc.getValue());
    checkEqual("ISIZE", source.readIntLe(), (int)inflater.getBytesWritten());
  }
  
  private void updateCrc(Buffer paramBuffer, long paramLong1, long paramLong2)
  {
    int i;
    int j;
    Object localObject;
    long l1;
    long l2;
    for (paramBuffer = head;; paramBuffer = next)
    {
      i = limit;
      j = pos;
      localObject = paramBuffer;
      l1 = paramLong1;
      l2 = paramLong2;
      if (paramLong1 < i - j) {
        break;
      }
      paramLong1 -= i - j;
    }
    while (l2 > 0L)
    {
      i = (int)(pos + l1);
      j = (int)Math.min(limit - i, l2);
      crc.update(data, i, j);
      l2 -= j;
      l1 = 0L;
      localObject = next;
    }
  }
  
  public void close()
  {
    inflaterSource.close();
  }
  
  public long read(Buffer paramBuffer, long paramLong)
  {
    if (paramLong >= 0L)
    {
      if (paramLong == 0L) {
        return 0L;
      }
      if (section == 0)
      {
        consumeHeader();
        section = 1;
      }
      if (section == 1)
      {
        long l = size;
        paramLong = inflaterSource.read(paramBuffer, paramLong);
        if (paramLong != -1L)
        {
          updateCrc(paramBuffer, l, paramLong);
          return paramLong;
        }
        section = 2;
      }
      if (section == 2)
      {
        consumeTrailer();
        section = 3;
        if (source.exhausted()) {
          return -1L;
        }
        throw new IOException("gzip finished without exhausting source");
      }
    }
    else
    {
      paramBuffer = new StringBuilder();
      paramBuffer.append("byteCount < 0: ");
      paramBuffer.append(paramLong);
      throw new IllegalArgumentException(paramBuffer.toString());
    }
    return -1L;
  }
  
  public Timeout timeout()
  {
    return source.timeout();
  }
}
