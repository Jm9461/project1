package asm;

final class l
{
  private final f b;
  private final long d;
  
  l(f paramF, long paramLong)
  {
    b = paramF;
    d = paramLong;
  }
  
  private byte[] encryptBlock(long paramLong1, long paramLong2, long paramLong3, int paramInt, long paramLong4, byte paramByte)
  {
    return new byte[] { 82, 73, 70, 70, (byte)(int)(paramLong2 & 0xFF), (byte)(int)(paramLong2 >> 8 & 0xFF), (byte)(int)(paramLong2 >> 16 & 0xFF), (byte)(int)(paramLong2 >> 24 & 0xFF), 87, 65, 86, 69, 102, 109, 116, 32, 16, 0, 0, 0, 1, 0, (byte)paramInt, 0, (byte)(int)(paramLong3 & 0xFF), (byte)(int)(paramLong3 >> 8 & 0xFF), (byte)(int)(paramLong3 >> 16 & 0xFF), (byte)(int)(paramLong3 >> 24 & 0xFF), (byte)(int)(paramLong4 & 0xFF), (byte)(int)(paramLong4 >> 8 & 0xFF), (byte)(int)(paramLong4 >> 16 & 0xFF), (byte)(int)(paramLong4 >> 24 & 0xFF), (byte)(paramByte / 8 * paramInt), 0, paramByte, 0, 100, 97, 116, 97, (byte)(int)(paramLong1 & 0xFF), (byte)(int)(paramLong1 >> 8 & 0xFF), (byte)(int)(paramLong1 >> 16 & 0xFF), (byte)(int)(paramLong1 >> 24 & 0xFF) };
  }
  
  public byte[] a()
  {
    long l1 = b.l();
    int i;
    if (b.a() == 16) {
      i = 1;
    } else {
      i = 2;
    }
    byte b1 = b.c();
    long l2 = d;
    return encryptBlock(l2, 36L + l2, l1, i, b1 * l1 * i / 8L, b1);
  }
}
