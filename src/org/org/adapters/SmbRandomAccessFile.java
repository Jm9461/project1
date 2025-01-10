package org.org.adapters;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

class SmbRandomAccessFile
  extends InputStream
  implements DataInput
{
  private static final ByteOrder buffer = ByteOrder.BIG_ENDIAN;
  private static final ByteOrder tmp = ByteOrder.LITTLE_ENDIAN;
  private ByteOrder data = ByteOrder.BIG_ENDIAN;
  private DataInputStream in;
  private int position;
  private final int size;
  
  public SmbRandomAccessFile(InputStream paramInputStream)
  {
    in = new DataInputStream(paramInputStream);
    size = in.available();
    position = 0;
    in.mark(size);
  }
  
  public SmbRandomAccessFile(byte[] paramArrayOfByte)
  {
    this(new ByteArrayInputStream(paramArrayOfByte));
  }
  
  public int available()
  {
    return in.available();
  }
  
  public long length()
  {
    return readInt() & 0xFFFFFFFF;
  }
  
  public int read()
  {
    position += 1;
    return in.read();
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    paramInt1 = in.read(paramArrayOfByte, paramInt1, paramInt2);
    position += paramInt1;
    return paramInt1;
  }
  
  public boolean readBoolean()
  {
    position += 1;
    return in.readBoolean();
  }
  
  public byte readByte()
  {
    position += 1;
    if (position <= size)
    {
      int i = in.read();
      if (i >= 0) {
        return (byte)i;
      }
      throw new EOFException();
    }
    throw new EOFException();
  }
  
  public char readChar()
  {
    position += 2;
    return in.readChar();
  }
  
  public double readDouble()
  {
    return Double.longBitsToDouble(readLong());
  }
  
  public float readFloat()
  {
    return Float.intBitsToFloat(readInt());
  }
  
  public void readFully(byte[] paramArrayOfByte)
  {
    position += paramArrayOfByte.length;
    if (position <= size)
    {
      if (in.read(paramArrayOfByte, 0, paramArrayOfByte.length) == paramArrayOfByte.length) {
        return;
      }
      throw new IOException("Couldn't read up to the length of buffer");
    }
    throw new EOFException();
  }
  
  public void readFully(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    position += paramInt2;
    if (position <= size)
    {
      if (in.read(paramArrayOfByte, paramInt1, paramInt2) == paramInt2) {
        return;
      }
      throw new IOException("Couldn't read up to the length of buffer");
    }
    throw new EOFException();
  }
  
  public int readInt()
  {
    position += 4;
    if (position <= size)
    {
      int i = in.read();
      int j = in.read();
      int k = in.read();
      int m = in.read();
      if ((i | j | k | m) >= 0)
      {
        Object localObject = data;
        if (localObject == tmp) {
          return (m << 24) + (k << 16) + (j << 8) + i;
        }
        if (localObject == buffer) {
          return (i << 24) + (j << 16) + (k << 8) + m;
        }
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Invalid byte order: ");
        ((StringBuilder)localObject).append(data);
        throw new IOException(((StringBuilder)localObject).toString());
      }
      throw new EOFException();
    }
    throw new EOFException();
  }
  
  public String readLine()
  {
    Log.d("ExifInterface", "Currently unsupported");
    return null;
  }
  
  public long readLong()
  {
    position += 8;
    if (position <= size)
    {
      int i = in.read();
      int j = in.read();
      int k = in.read();
      int m = in.read();
      int n = in.read();
      int i1 = in.read();
      int i2 = in.read();
      int i3 = in.read();
      if ((i | j | k | m | n | i1 | i2 | i3) >= 0)
      {
        Object localObject = data;
        if (localObject == tmp) {
          return (i3 << 56) + (i2 << 48) + (i1 << 40) + (n << 32) + (m << 24) + (k << 16) + (j << 8) + i;
        }
        if (localObject == buffer) {
          return (i << 56) + (j << 48) + (k << 40) + (m << 32) + (n << 24) + (i1 << 16) + (i2 << 8) + i3;
        }
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Invalid byte order: ");
        ((StringBuilder)localObject).append(data);
        throw new IOException(((StringBuilder)localObject).toString());
      }
      throw new EOFException();
    }
    throw new EOFException();
  }
  
  public short readShort()
  {
    position += 2;
    if (position <= size)
    {
      int i = in.read();
      int j = in.read();
      if ((i | j) >= 0)
      {
        Object localObject = data;
        if (localObject == tmp) {
          return (short)((j << 8) + i);
        }
        if (localObject == buffer) {
          return (short)((i << 8) + j);
        }
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Invalid byte order: ");
        ((StringBuilder)localObject).append(data);
        throw new IOException(((StringBuilder)localObject).toString());
      }
      throw new EOFException();
    }
    throw new EOFException();
  }
  
  public String readUTF()
  {
    position += 2;
    return in.readUTF();
  }
  
  public int readUnsignedByte()
  {
    position += 1;
    return in.readUnsignedByte();
  }
  
  public int readUnsignedShort()
  {
    position += 2;
    if (position <= size)
    {
      int i = in.read();
      int j = in.read();
      if ((i | j) >= 0)
      {
        Object localObject = data;
        if (localObject == tmp) {
          return (j << 8) + i;
        }
        if (localObject == buffer) {
          return (i << 8) + j;
        }
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Invalid byte order: ");
        ((StringBuilder)localObject).append(data);
        throw new IOException(((StringBuilder)localObject).toString());
      }
      throw new EOFException();
    }
    throw new EOFException();
  }
  
  public int skipBytes(int paramInt)
  {
    int i = Math.min(paramInt, size - position);
    paramInt = 0;
    while (paramInt < i) {
      paramInt += in.skipBytes(i - paramInt);
    }
    position += paramInt;
    return paramInt;
  }
  
  public int write()
  {
    return position;
  }
  
  public void write(long paramLong)
  {
    int i = position;
    if (i > paramLong)
    {
      position = 0;
      in.reset();
      in.mark(size);
    }
    else
    {
      paramLong -= i;
    }
    if (skipBytes((int)paramLong) == (int)paramLong) {
      return;
    }
    throw new IOException("Couldn't seek up to the byteCount");
  }
  
  public void write(ByteOrder paramByteOrder)
  {
    data = paramByteOrder;
  }
}
