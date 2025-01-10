package asm;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public final class ByteVector
  implements Label
{
  final byte[] data;
  
  ByteVector(byte[] paramArrayOfByte)
  {
    data = paramArrayOfByte;
  }
  
  public double a()
  {
    short[] arrayOfShort = read();
    int j = 0;
    int m = arrayOfShort.length;
    int i = 0;
    while (i < m)
    {
      int k = j;
      if (arrayOfShort[i] >= j) {
        k = arrayOfShort[i];
      }
      i += 1;
      j = k;
    }
    double d = j;
    Double.isNaN(d);
    return (int)(Math.log10(d / 0.6D) * 20.0D);
  }
  
  public byte[] get()
  {
    return data;
  }
  
  public short[] read()
  {
    byte[] arrayOfByte = data;
    short[] arrayOfShort = new short[arrayOfByte.length / 2];
    ByteBuffer.wrap(arrayOfByte).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(arrayOfShort);
    return arrayOfShort;
  }
}
