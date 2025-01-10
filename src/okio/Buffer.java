package okio;

import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;

public final class Buffer
  implements BufferedSource, BufferedSink, Cloneable, ByteChannel
{
  private static final byte[] DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  Segment head;
  long size;
  
  public Buffer() {}
  
  public Buffer buffer()
  {
    return this;
  }
  
  public void clear()
  {
    long l = size;
    try
    {
      skip(l);
      return;
    }
    catch (EOFException localEOFException)
    {
      throw new AssertionError(localEOFException);
    }
  }
  
  public Buffer clone()
  {
    Buffer localBuffer = new Buffer();
    if (size == 0L) {
      return localBuffer;
    }
    head = head.write();
    Segment localSegment = head;
    prev = localSegment;
    next = localSegment;
    for (localSegment = head.next; localSegment != head; localSegment = next) {
      head.prev.push(localSegment.write());
    }
    size = size;
    return localBuffer;
  }
  
  public void close() {}
  
  public long completeSegmentByteCount()
  {
    long l2 = size;
    if (l2 == 0L) {
      return 0L;
    }
    Segment localSegment = head.prev;
    int i = limit;
    long l1 = l2;
    if (i < 8192)
    {
      l1 = l2;
      if (owner) {
        l1 = l2 - (i - pos);
      }
    }
    return l1;
  }
  
  public Buffer copyTo(Buffer paramBuffer, long paramLong1, long paramLong2)
  {
    if (paramBuffer != null)
    {
      Util.checkOffsetAndCount(size, paramLong1, paramLong2);
      if (paramLong2 == 0L) {
        return this;
      }
      size += paramLong2;
      Segment localSegment2;
      long l1;
      long l2;
      for (Segment localSegment1 = head;; localSegment1 = next)
      {
        int i = limit;
        int j = pos;
        localSegment2 = localSegment1;
        l1 = paramLong1;
        l2 = paramLong2;
        if (paramLong1 < i - j) {
          break;
        }
        paramLong1 -= i - j;
      }
      while (l2 > 0L)
      {
        localSegment1 = localSegment2.write();
        pos = ((int)(pos + l1));
        limit = Math.min(pos + (int)l2, limit);
        Segment localSegment3 = head;
        if (localSegment3 == null)
        {
          prev = localSegment1;
          next = localSegment1;
          head = localSegment1;
        }
        else
        {
          prev.push(localSegment1);
        }
        l2 -= limit - pos;
        l1 = 0L;
        localSegment2 = next;
      }
      return this;
    }
    paramBuffer = new IllegalArgumentException("out == null");
    throw paramBuffer;
  }
  
  public Buffer emitCompleteSegments()
  {
    return this;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof Buffer)) {
      return false;
    }
    paramObject = (Buffer)paramObject;
    long l1 = size;
    if (l1 != size) {
      return false;
    }
    if (l1 == 0L) {
      return true;
    }
    Object localObject2 = head;
    paramObject = head;
    int j = pos;
    int i = pos;
    l1 = 0L;
    while (l1 < size)
    {
      long l2 = Math.min(limit - j, limit - i);
      int k = 0;
      while (k < l2)
      {
        if (data[j] != data[i]) {
          return false;
        }
        k += 1;
        j += 1;
        i += 1;
      }
      Object localObject1 = localObject2;
      k = j;
      if (j == limit)
      {
        localObject1 = next;
        k = pos;
      }
      Object localObject3 = paramObject;
      int m = i;
      if (i == limit)
      {
        localObject3 = next;
        m = pos;
      }
      l1 += l2;
      localObject2 = localObject1;
      paramObject = localObject3;
      j = k;
      i = m;
    }
    return true;
  }
  
  public boolean exhausted()
  {
    return size == 0L;
  }
  
  public void flush() {}
  
  String get(long paramLong)
  {
    if ((paramLong > 0L) && (getByte(paramLong - 1L) == 13))
    {
      str = readUtf8(paramLong - 1L);
      skip(2L);
      return str;
    }
    String str = readUtf8(paramLong);
    skip(1L);
    return str;
  }
  
  public byte getByte(long paramLong)
  {
    Util.checkOffsetAndCount(size, paramLong, 1L);
    long l = size;
    int j;
    int i;
    if (l - paramLong > paramLong) {
      for (localSegment = head;; localSegment = next)
      {
        j = limit;
        i = pos;
        j -= i;
        if (paramLong < j) {
          return data[(i + (int)paramLong)];
        }
        paramLong -= j;
      }
    }
    paramLong -= l;
    for (Segment localSegment = head.prev;; localSegment = prev)
    {
      i = limit;
      j = pos;
      paramLong += i - j;
      if (paramLong >= 0L) {
        return data[(j + (int)paramLong)];
      }
    }
  }
  
  public int hashCode()
  {
    Object localObject = head;
    if (localObject == null) {
      return 0;
    }
    int i = 1;
    int j;
    Segment localSegment;
    do
    {
      int k = pos;
      int m = limit;
      j = i;
      while (k < m)
      {
        j = j * 31 + data[k];
        k += 1;
      }
      localSegment = next;
      localObject = localSegment;
      i = j;
    } while (localSegment != head);
    return j;
  }
  
  public InputStream inputStream()
  {
    return new Buffer.2(this);
  }
  
  public boolean isOpen()
  {
    return true;
  }
  
  public int read(ByteBuffer paramByteBuffer)
  {
    Segment localSegment = head;
    if (localSegment == null) {
      return -1;
    }
    int i = Math.min(paramByteBuffer.remaining(), limit - pos);
    paramByteBuffer.put(data, pos, i);
    pos += i;
    size -= i;
    if (pos == limit)
    {
      head = localSegment.pop();
      SegmentPool.recycle(localSegment);
    }
    return i;
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    Util.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
    Segment localSegment = head;
    if (localSegment == null) {
      return -1;
    }
    paramInt2 = Math.min(paramInt2, limit - pos);
    System.arraycopy(data, pos, paramArrayOfByte, paramInt1, paramInt2);
    pos += paramInt2;
    size -= paramInt2;
    if (pos == limit)
    {
      head = localSegment.pop();
      SegmentPool.recycle(localSegment);
    }
    return paramInt2;
  }
  
  public long read(byte paramByte)
  {
    return read(paramByte, 0L, Long.MAX_VALUE);
  }
  
  public long read(byte paramByte, long paramLong1, long paramLong2)
  {
    if ((paramLong1 >= 0L) && (paramLong2 >= paramLong1))
    {
      long l2 = paramLong2;
      if (paramLong2 > size) {
        l2 = size;
      }
      if (paramLong1 == l2) {
        return -1L;
      }
      Object localObject2 = head;
      if (localObject2 == null) {
        return -1L;
      }
      long l1;
      long l3;
      if (size - paramLong1 < paramLong1) {
        for (l1 = size;; l1 -= limit - pos)
        {
          paramLong2 = l1;
          localObject1 = localObject2;
          l3 = paramLong1;
          if (l1 <= paramLong1) {
            break;
          }
          localObject2 = prev;
        }
      }
      for (paramLong2 = 0L;; paramLong2 = l1)
      {
        l1 = limit - pos + paramLong2;
        localObject1 = localObject2;
        l3 = paramLong1;
        if (l1 >= paramLong1) {
          break;
        }
        localObject2 = next;
      }
      while (paramLong2 < l2)
      {
        localObject2 = data;
        int j = (int)Math.min(limit, pos + l2 - paramLong2);
        int i = (int)(pos + l3 - paramLong2);
        while (i < j)
        {
          if (localObject2[i] == paramByte) {
            return i - pos + paramLong2;
          }
          i += 1;
        }
        paramLong2 += limit - pos;
        l3 = paramLong2;
        localObject1 = next;
      }
      return -1L;
    }
    Object localObject1 = new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", new Object[] { Long.valueOf(size), Long.valueOf(paramLong1), Long.valueOf(paramLong2) }));
    throw ((Throwable)localObject1);
  }
  
  public long read(Buffer paramBuffer, long paramLong)
  {
    if (paramBuffer != null)
    {
      if (paramLong >= 0L)
      {
        long l2 = size;
        if (l2 == 0L) {
          return -1L;
        }
        long l1 = paramLong;
        if (paramLong > l2) {
          l1 = size;
        }
        paramBuffer.write(this, l1);
        return l1;
      }
      paramBuffer = new StringBuilder();
      paramBuffer.append("byteCount < 0: ");
      paramBuffer.append(paramLong);
      throw new IllegalArgumentException(paramBuffer.toString());
    }
    throw new IllegalArgumentException("sink == null");
  }
  
  public String read(long paramLong)
  {
    if (paramLong >= 0L)
    {
      long l1 = Long.MAX_VALUE;
      if (paramLong != Long.MAX_VALUE) {
        l1 = paramLong + 1L;
      }
      long l2 = read((byte)10, 0L, l1);
      if (l2 != -1L) {
        return get(l2);
      }
      if ((l1 < size()) && (getByte(l1 - 1L) == 13) && (getByte(l1) == 10)) {
        return get(l1);
      }
      localObject = new Buffer();
      copyTo((Buffer)localObject, 0L, Math.min(32L, size()));
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("\\n not found: limit=");
      localStringBuilder.append(Math.min(size(), paramLong));
      localStringBuilder.append(" content=");
      localStringBuilder.append(((Buffer)localObject).read().hex());
      localStringBuilder.append('?');
      throw new EOFException(localStringBuilder.toString());
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("limit < 0: ");
    ((StringBuilder)localObject).append(paramLong);
    throw new IllegalArgumentException(((StringBuilder)localObject).toString());
  }
  
  public ByteString read()
  {
    return new ByteString(readByteArray());
  }
  
  public boolean read(long paramLong, ByteString paramByteString)
  {
    return write(paramLong, paramByteString, 0, paramByteString.size());
  }
  
  public byte readByte()
  {
    long l = size;
    if (l != 0L)
    {
      Segment localSegment = head;
      int i = pos;
      int j = limit;
      byte[] arrayOfByte = data;
      int k = i + 1;
      byte b = arrayOfByte[i];
      size = (l - 1L);
      if (k == j)
      {
        head = localSegment.pop();
        SegmentPool.recycle(localSegment);
        return b;
      }
      pos = k;
      return b;
    }
    throw new IllegalStateException("size == 0");
  }
  
  public byte[] readByteArray()
  {
    long l = size;
    try
    {
      byte[] arrayOfByte = readByteArray(l);
      return arrayOfByte;
    }
    catch (EOFException localEOFException)
    {
      throw new AssertionError(localEOFException);
    }
  }
  
  public byte[] readByteArray(long paramLong)
  {
    Util.checkOffsetAndCount(size, 0L, paramLong);
    if (paramLong <= 2147483647L)
    {
      localObject = new byte[(int)paramLong];
      readFully((byte[])localObject);
      return localObject;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("byteCount > Integer.MAX_VALUE: ");
    ((StringBuilder)localObject).append(paramLong);
    throw new IllegalArgumentException(((StringBuilder)localObject).toString());
  }
  
  public ByteString readByteString(long paramLong)
  {
    return new ByteString(readByteArray(paramLong));
  }
  
  public long readDecimalLong()
  {
    if (size != 0L)
    {
      long l2 = 0L;
      int k = 0;
      int m = 0;
      int i = 0;
      long l1 = -7L;
      for (;;)
      {
        localObject1 = head;
        Object localObject2 = data;
        int j = pos;
        int i1 = limit;
        int n;
        for (;;)
        {
          n = i;
          if (j >= i1) {
            break label293;
          }
          n = localObject2[j];
          if ((n >= 48) && (n <= 57))
          {
            int i2 = 48 - n;
            if ((l2 >= -922337203685477580L) && ((l2 != -922337203685477580L) || (i2 >= l1)))
            {
              l2 = l2 * 10L + i2;
            }
            else
            {
              localObject1 = new Buffer();
              ((Buffer)localObject1).writeDecimalLong(l2);
              localObject1 = ((Buffer)localObject1).writeByte(n);
              if (m == 0) {
                ((Buffer)localObject1).readByte();
              }
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("Number too large: ");
              ((StringBuilder)localObject2).append(((Buffer)localObject1).readUtf8());
              throw new NumberFormatException(((StringBuilder)localObject2).toString());
            }
          }
          else
          {
            if ((n != 45) || (k != 0)) {
              break;
            }
            m = 1;
            l1 -= 1L;
          }
          j += 1;
          k += 1;
        }
        if (k != 0)
        {
          n = 1;
        }
        else
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Expected leading [0-9] or '-' character but was 0x");
          ((StringBuilder)localObject1).append(Integer.toHexString(n));
          throw new NumberFormatException(((StringBuilder)localObject1).toString());
        }
        label293:
        if (j == i1)
        {
          head = ((Segment)localObject1).pop();
          SegmentPool.recycle((Segment)localObject1);
        }
        else
        {
          pos = j;
        }
        if ((n != 0) || (head == null)) {
          break;
        }
        i = n;
      }
      size -= k;
      if (m != 0) {
        return l2;
      }
      return -l2;
    }
    Object localObject1 = new IllegalStateException("size == 0");
    throw ((Throwable)localObject1);
  }
  
  public void readFully(byte[] paramArrayOfByte)
  {
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      int j = read(paramArrayOfByte, i, paramArrayOfByte.length - i);
      if (j != -1) {
        i += j;
      } else {
        throw new EOFException();
      }
    }
  }
  
  public long readHexadecimalUnsignedLong()
  {
    if (size != 0L)
    {
      long l1 = 0L;
      int i = 0;
      int j = 0;
      int k;
      long l2;
      label239:
      label291:
      do
      {
        localObject1 = head;
        Object localObject2 = data;
        int m = pos;
        int i1 = limit;
        k = i;
        l2 = l1;
        int n;
        for (;;)
        {
          n = j;
          if (m >= i1) {
            break label291;
          }
          n = localObject2[m];
          if ((n >= 48) && (n <= 57))
          {
            i = n - 48;
          }
          else if ((n >= 97) && (n <= 102))
          {
            i = n - 97 + 10;
          }
          else
          {
            if ((n < 65) || (n > 70)) {
              break label239;
            }
            i = n - 65 + 10;
          }
          if ((0xF000000000000000 & l2) != 0L) {
            break;
          }
          l2 = l2 << 4 | i;
          m += 1;
          k += 1;
        }
        localObject1 = new Buffer();
        ((Buffer)localObject1).writeHexadecimalUnsignedLong(l2);
        localObject1 = ((Buffer)localObject1).writeByte(n);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Number too large: ");
        ((StringBuilder)localObject2).append(((Buffer)localObject1).readUtf8());
        throw new NumberFormatException(((StringBuilder)localObject2).toString());
        if (k != 0)
        {
          n = 1;
        }
        else
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Expected leading [0-9a-fA-F] character but was 0x");
          ((StringBuilder)localObject1).append(Integer.toHexString(n));
          throw new NumberFormatException(((StringBuilder)localObject1).toString());
        }
        if (m == i1)
        {
          head = ((Segment)localObject1).pop();
          SegmentPool.recycle((Segment)localObject1);
        }
        else
        {
          pos = m;
        }
        if (n != 0) {
          break;
        }
        l1 = l2;
        i = k;
        j = n;
      } while (head != null);
      size -= k;
      return l2;
    }
    Object localObject1 = new IllegalStateException("size == 0");
    throw ((Throwable)localObject1);
  }
  
  public int readInt()
  {
    long l = size;
    if (l >= 4L)
    {
      localObject = head;
      int j = pos;
      int i = limit;
      if (i - j < 4) {
        return (readByte() & 0xFF) << 24 | (readByte() & 0xFF) << 16 | (readByte() & 0xFF) << 8 | readByte() & 0xFF;
      }
      byte[] arrayOfByte = data;
      int k = j + 1;
      j = arrayOfByte[j];
      int n = k + 1;
      k = arrayOfByte[k];
      int m = n + 1;
      int i1 = arrayOfByte[n];
      n = m + 1;
      j = (j & 0xFF) << 24 | (k & 0xFF) << 16 | (i1 & 0xFF) << 8 | arrayOfByte[m] & 0xFF;
      size = (l - 4L);
      if (n == i)
      {
        head = ((Segment)localObject).pop();
        SegmentPool.recycle((Segment)localObject);
        return j;
      }
      pos = n;
      return j;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("size < 4: ");
    ((StringBuilder)localObject).append(size);
    throw new IllegalStateException(((StringBuilder)localObject).toString());
  }
  
  public int readIntLe()
  {
    return Util.reverseBytesInt(readInt());
  }
  
  public short readShort()
  {
    long l = size;
    if (l >= 2L)
    {
      localObject = head;
      int k = pos;
      int i = limit;
      if (i - k < 2) {
        return (short)((readByte() & 0xFF) << 8 | readByte() & 0xFF);
      }
      byte[] arrayOfByte = data;
      int j = k + 1;
      k = arrayOfByte[k];
      int m = j + 1;
      j = arrayOfByte[j];
      size = (l - 2L);
      if (m == i)
      {
        head = ((Segment)localObject).pop();
        SegmentPool.recycle((Segment)localObject);
      }
      else
      {
        pos = m;
      }
      return (short)((k & 0xFF) << 8 | j & 0xFF);
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("size < 2: ");
    ((StringBuilder)localObject).append(size);
    throw new IllegalStateException(((StringBuilder)localObject).toString());
  }
  
  public short readShortLe()
  {
    return Util.reverseBytesShort(readShort());
  }
  
  public String readString(long paramLong, Charset paramCharset)
  {
    Util.checkOffsetAndCount(size, 0L, paramLong);
    if (paramCharset != null)
    {
      if (paramLong <= 2147483647L)
      {
        if (paramLong == 0L) {
          return "";
        }
        Segment localSegment = head;
        int i = pos;
        if (i + paramLong > limit) {
          return new String(readByteArray(paramLong), paramCharset);
        }
        paramCharset = new String(data, i, (int)paramLong, paramCharset);
        pos = ((int)(pos + paramLong));
        size -= paramLong;
        if (pos == limit)
        {
          head = localSegment.pop();
          SegmentPool.recycle(localSegment);
          return paramCharset;
        }
      }
      else
      {
        paramCharset = new StringBuilder();
        paramCharset.append("byteCount > Integer.MAX_VALUE: ");
        paramCharset.append(paramLong);
        throw new IllegalArgumentException(paramCharset.toString());
      }
    }
    else {
      throw new IllegalArgumentException("charset == null");
    }
    return paramCharset;
  }
  
  public String readUtf8()
  {
    long l = size;
    Object localObject = Util.UTF_8;
    try
    {
      localObject = readString(l, (Charset)localObject);
      return localObject;
    }
    catch (EOFException localEOFException)
    {
      throw new AssertionError(localEOFException);
    }
  }
  
  public String readUtf8(long paramLong)
  {
    return readString(paramLong, Util.UTF_8);
  }
  
  public String readUtf8LineStrict()
  {
    return read(Long.MAX_VALUE);
  }
  
  public void require(long paramLong)
  {
    if (size >= paramLong) {
      return;
    }
    throw new EOFException();
  }
  
  public long size()
  {
    return size;
  }
  
  public void skip(long paramLong)
  {
    while (paramLong > 0L)
    {
      Segment localSegment = head;
      if (localSegment != null)
      {
        int i = (int)Math.min(paramLong, limit - pos);
        size -= i;
        long l = paramLong - i;
        localSegment = head;
        pos += i;
        paramLong = l;
        if (pos == limit)
        {
          localSegment = head;
          head = localSegment.pop();
          SegmentPool.recycle(localSegment);
          paramLong = l;
        }
      }
      else
      {
        throw new EOFException();
      }
    }
  }
  
  public ByteString snapshot()
  {
    long l = size;
    if (l <= 2147483647L) {
      return snapshot((int)l);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("size > Integer.MAX_VALUE: ");
    localStringBuilder.append(size);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  public ByteString snapshot(int paramInt)
  {
    if (paramInt == 0) {
      return ByteString.EMPTY;
    }
    return new SegmentedByteString(this, paramInt);
  }
  
  public Timeout timeout()
  {
    return Timeout.NONE;
  }
  
  public String toString()
  {
    return snapshot().toString();
  }
  
  Segment writableSegment(int paramInt)
  {
    Segment localSegment;
    if ((paramInt >= 1) && (paramInt <= 8192))
    {
      localSegment = head;
      if (localSegment == null)
      {
        head = SegmentPool.take();
        localSegment = head;
        prev = localSegment;
        next = localSegment;
        return localSegment;
      }
      localSegment = prev;
      if ((limit + paramInt > 8192) || (!owner)) {
        return localSegment.push(SegmentPool.take());
      }
    }
    else
    {
      throw new IllegalArgumentException();
    }
    return localSegment;
  }
  
  public int write(ByteBuffer paramByteBuffer)
  {
    if (paramByteBuffer != null)
    {
      int j = paramByteBuffer.remaining();
      int i = j;
      while (i > 0)
      {
        Segment localSegment = writableSegment(1);
        int k = Math.min(i, 8192 - limit);
        paramByteBuffer.get(data, limit, k);
        i -= k;
        limit += k;
      }
      size += j;
      return j;
    }
    paramByteBuffer = new IllegalArgumentException("source == null");
    throw paramByteBuffer;
  }
  
  public Buffer write(ByteString paramByteString)
  {
    if (paramByteString != null)
    {
      paramByteString.write(this);
      return this;
    }
    throw new IllegalArgumentException("byteString == null");
  }
  
  public Buffer write(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null)
    {
      write(paramArrayOfByte, 0, paramArrayOfByte.length);
      return this;
    }
    throw new IllegalArgumentException("source == null");
  }
  
  public Buffer write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte != null)
    {
      Util.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
      int i = paramInt1 + paramInt2;
      while (paramInt1 < i)
      {
        Segment localSegment = writableSegment(1);
        int j = Math.min(i - paramInt1, 8192 - limit);
        System.arraycopy(paramArrayOfByte, paramInt1, data, limit, j);
        paramInt1 += j;
        limit += j;
      }
      size += paramInt2;
      return this;
    }
    paramArrayOfByte = new IllegalArgumentException("source == null");
    throw paramArrayOfByte;
  }
  
  public void write(Buffer paramBuffer, long paramLong)
  {
    if (paramBuffer != null)
    {
      if (paramBuffer != this)
      {
        Util.checkOffsetAndCount(size, 0L, paramLong);
        while (paramLong > 0L)
        {
          Segment localSegment1 = head;
          if (paramLong < limit - pos)
          {
            localSegment1 = head;
            if (localSegment1 != null) {
              localSegment1 = prev;
            } else {
              localSegment1 = null;
            }
            if ((localSegment1 != null) && (owner))
            {
              l = limit;
              int i;
              if (shared) {
                i = 0;
              } else {
                i = pos;
              }
              if (l + paramLong - i <= 8192L)
              {
                head.writeTo(localSegment1, (int)paramLong);
                size -= paramLong;
                size += paramLong;
                return;
              }
            }
            head = head.split((int)paramLong);
          }
          localSegment1 = head;
          long l = limit - pos;
          head = localSegment1.pop();
          Segment localSegment2 = head;
          if (localSegment2 == null)
          {
            head = localSegment1;
            localSegment1 = head;
            prev = localSegment1;
            next = localSegment1;
          }
          else
          {
            prev.push(localSegment1).compact();
          }
          size -= l;
          size += l;
          paramLong -= l;
        }
        return;
      }
      throw new IllegalArgumentException("source == this");
    }
    paramBuffer = new IllegalArgumentException("source == null");
    throw paramBuffer;
  }
  
  public boolean write(long paramLong, ByteString paramByteString, int paramInt1, int paramInt2)
  {
    if ((paramLong >= 0L) && (paramInt1 >= 0) && (paramInt2 >= 0) && (size - paramLong >= paramInt2))
    {
      if (paramByteString.size() - paramInt1 < paramInt2) {
        return false;
      }
      int i = 0;
      while (i < paramInt2)
      {
        if (getByte(i + paramLong) != paramByteString.read(paramInt1 + i)) {
          return false;
        }
        i += 1;
      }
      return true;
    }
    return false;
  }
  
  public long writeAll(Source paramSource)
  {
    if (paramSource != null)
    {
      long l2;
      for (long l1 = 0L;; l1 += l2)
      {
        l2 = paramSource.read(this, 8192L);
        if (l2 == -1L) {
          break;
        }
      }
      return l1;
    }
    paramSource = new IllegalArgumentException("source == null");
    throw paramSource;
  }
  
  public Buffer writeByte(int paramInt)
  {
    Segment localSegment = writableSegment(1);
    byte[] arrayOfByte = data;
    int i = limit;
    limit = (i + 1);
    arrayOfByte[i] = ((byte)paramInt);
    size += 1L;
    return this;
  }
  
  public Buffer writeDecimalLong(long paramLong)
  {
    if (paramLong == 0L)
    {
      writeByte(48);
      return this;
    }
    int j = 0;
    long l = paramLong;
    if (paramLong < 0L)
    {
      l = -paramLong;
      if (l < 0L)
      {
        writeUtf8("-9223372036854775808");
        return this;
      }
      j = 1;
    }
    if (l < 100000000L)
    {
      if (l < 10000L)
      {
        if (l < 100L)
        {
          if (l < 10L) {
            i = 1;
          } else {
            i = 2;
          }
        }
        else if (l < 1000L) {
          i = 3;
        } else {
          i = 4;
        }
      }
      else if (l < 1000000L)
      {
        if (l < 100000L) {
          i = 5;
        } else {
          i = 6;
        }
      }
      else if (l < 10000000L) {
        i = 7;
      } else {
        i = 8;
      }
    }
    else if (l < 1000000000000L)
    {
      if (l < 10000000000L)
      {
        if (l < 1000000000L) {
          i = 9;
        } else {
          i = 10;
        }
      }
      else if (l < 100000000000L) {
        i = 11;
      } else {
        i = 12;
      }
    }
    else if (l < 1000000000000000L)
    {
      if (l < 10000000000000L) {
        i = 13;
      } else if (l < 100000000000000L) {
        i = 14;
      } else {
        i = 15;
      }
    }
    else if (l < 100000000000000000L)
    {
      if (l < 10000000000000000L) {
        i = 16;
      } else {
        i = 17;
      }
    }
    else if (l < 1000000000000000000L) {
      i = 18;
    } else {
      i = 19;
    }
    int k = i;
    if (j != 0) {
      k = i + 1;
    }
    Segment localSegment = writableSegment(k);
    byte[] arrayOfByte = data;
    int i = limit + k;
    while (l != 0L)
    {
      int m = (int)(l % 10L);
      i -= 1;
      arrayOfByte[i] = DIGITS[m];
      l /= 10L;
    }
    if (j != 0) {
      arrayOfByte[(i - 1)] = 45;
    }
    limit += k;
    size += k;
    return this;
  }
  
  public Buffer writeHexadecimalUnsignedLong(long paramLong)
  {
    if (paramLong == 0L)
    {
      writeByte(48);
      return this;
    }
    int j = Long.numberOfTrailingZeros(Long.highestOneBit(paramLong)) / 4 + 1;
    Segment localSegment = writableSegment(j);
    byte[] arrayOfByte = data;
    int i = limit + j - 1;
    int k = limit;
    while (i >= k)
    {
      arrayOfByte[i] = DIGITS[((int)(0xF & paramLong))];
      paramLong >>>= 4;
      i -= 1;
    }
    limit += j;
    size += j;
    return this;
  }
  
  public Buffer writeInt(int paramInt)
  {
    Segment localSegment = writableSegment(4);
    byte[] arrayOfByte = data;
    int j = limit;
    int i = j + 1;
    arrayOfByte[j] = ((byte)(paramInt >>> 24 & 0xFF));
    j = i + 1;
    arrayOfByte[i] = ((byte)(paramInt >>> 16 & 0xFF));
    i = j + 1;
    arrayOfByte[j] = ((byte)(paramInt >>> 8 & 0xFF));
    arrayOfByte[i] = ((byte)(paramInt & 0xFF));
    limit = (i + 1);
    size += 4L;
    return this;
  }
  
  public Buffer writeShort(int paramInt)
  {
    Segment localSegment = writableSegment(2);
    byte[] arrayOfByte = data;
    int i = limit;
    int j = i + 1;
    arrayOfByte[i] = ((byte)(paramInt >>> 8 & 0xFF));
    arrayOfByte[j] = ((byte)(paramInt & 0xFF));
    limit = (j + 1);
    size += 2L;
    return this;
  }
  
  public Buffer writeString(String paramString, int paramInt1, int paramInt2, Charset paramCharset)
  {
    if (paramString != null)
    {
      if (paramInt1 >= 0)
      {
        if (paramInt2 >= paramInt1)
        {
          if (paramInt2 <= paramString.length())
          {
            if (paramCharset != null)
            {
              if (paramCharset.equals(Util.UTF_8))
              {
                writeUtf8(paramString, paramInt1, paramInt2);
                return this;
              }
              paramString = paramString.substring(paramInt1, paramInt2).getBytes(paramCharset);
              write(paramString, 0, paramString.length);
              return this;
            }
            throw new IllegalArgumentException("charset == null");
          }
          paramCharset = new StringBuilder();
          paramCharset.append("endIndex > string.length: ");
          paramCharset.append(paramInt2);
          paramCharset.append(" > ");
          paramCharset.append(paramString.length());
          throw new IllegalArgumentException(paramCharset.toString());
        }
        paramString = new StringBuilder();
        paramString.append("endIndex < beginIndex: ");
        paramString.append(paramInt2);
        paramString.append(" < ");
        paramString.append(paramInt1);
        throw new IllegalArgumentException(paramString.toString());
      }
      paramString = new StringBuilder();
      paramString.append("beginIndex < 0: ");
      paramString.append(paramInt1);
      throw new IllegalAccessError(paramString.toString());
    }
    throw new IllegalArgumentException("string == null");
  }
  
  public Buffer writeUtf8(String paramString)
  {
    writeUtf8(paramString, 0, paramString.length());
    return this;
  }
  
  public Buffer writeUtf8(String paramString, int paramInt1, int paramInt2)
  {
    if (paramString != null)
    {
      if (paramInt1 >= 0)
      {
        if (paramInt2 >= paramInt1)
        {
          if (paramInt2 <= paramString.length())
          {
            while (paramInt1 < paramInt2)
            {
              int k = paramString.charAt(paramInt1);
              int i;
              if (k < 128)
              {
                localObject = writableSegment(1);
                byte[] arrayOfByte = data;
                int j = limit - paramInt1;
                int m = Math.min(paramInt2, 8192 - j);
                i = paramInt1 + 1;
                arrayOfByte[(paramInt1 + j)] = ((byte)k);
                paramInt1 = i;
                while (paramInt1 < m)
                {
                  i = paramString.charAt(paramInt1);
                  if (i >= 128) {
                    break;
                  }
                  arrayOfByte[(paramInt1 + j)] = ((byte)i);
                  paramInt1 += 1;
                }
                i = limit;
                j = paramInt1 + j - i;
                limit = (i + j);
                size += j;
              }
              else if (k < 2048)
              {
                writeByte(k >> 6 | 0xC0);
                writeByte(0x80 | k & 0x3F);
                paramInt1 += 1;
              }
              else if ((k >= 55296) && (k <= 57343))
              {
                if (paramInt1 + 1 < paramInt2) {
                  i = paramString.charAt(paramInt1 + 1);
                } else {
                  i = 0;
                }
                if ((k <= 56319) && (i >= 56320) && (i <= 57343))
                {
                  i = ((0xFFFF27FF & k) << 10 | 0xFFFF23FF & i) + 65536;
                  writeByte(i >> 18 | 0xF0);
                  writeByte(i >> 12 & 0x3F | 0x80);
                  writeByte(i >> 6 & 0x3F | 0x80);
                  writeByte(0x80 | i & 0x3F);
                  paramInt1 += 2;
                }
                else
                {
                  writeByte(63);
                  paramInt1 += 1;
                }
              }
              else
              {
                writeByte(k >> 12 | 0xE0);
                writeByte(k >> 6 & 0x3F | 0x80);
                writeByte(0x80 | k & 0x3F);
                paramInt1 += 1;
              }
            }
            return this;
          }
          Object localObject = new StringBuilder();
          ((StringBuilder)localObject).append("endIndex > string.length: ");
          ((StringBuilder)localObject).append(paramInt2);
          ((StringBuilder)localObject).append(" > ");
          ((StringBuilder)localObject).append(paramString.length());
          throw new IllegalArgumentException(((StringBuilder)localObject).toString());
        }
        paramString = new StringBuilder();
        paramString.append("endIndex < beginIndex: ");
        paramString.append(paramInt2);
        paramString.append(" < ");
        paramString.append(paramInt1);
        throw new IllegalArgumentException(paramString.toString());
      }
      paramString = new StringBuilder();
      paramString.append("beginIndex < 0: ");
      paramString.append(paramInt1);
      throw new IllegalArgumentException(paramString.toString());
    }
    paramString = new IllegalArgumentException("string == null");
    throw paramString;
  }
  
  public Buffer writeUtf8CodePoint(int paramInt)
  {
    if (paramInt < 128)
    {
      writeByte(paramInt);
      return this;
    }
    if (paramInt < 2048)
    {
      writeByte(paramInt >> 6 | 0xC0);
      writeByte(0x80 | paramInt & 0x3F);
      return this;
    }
    if (paramInt < 65536)
    {
      if ((paramInt >= 55296) && (paramInt <= 57343))
      {
        writeByte(63);
        return this;
      }
      writeByte(paramInt >> 12 | 0xE0);
      writeByte(paramInt >> 6 & 0x3F | 0x80);
      writeByte(0x80 | paramInt & 0x3F);
      return this;
    }
    if (paramInt <= 1114111)
    {
      writeByte(paramInt >> 18 | 0xF0);
      writeByte(paramInt >> 12 & 0x3F | 0x80);
      writeByte(paramInt >> 6 & 0x3F | 0x80);
      writeByte(0x80 | paramInt & 0x3F);
      return this;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Unexpected code point: ");
    localStringBuilder.append(Integer.toHexString(paramInt));
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
}
