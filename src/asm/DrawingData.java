package asm;

import java.io.OutputStream;

public abstract interface DrawingData
{
  public abstract void addRawData(byte[] paramArrayOfByte, OutputStream paramOutputStream);
}
