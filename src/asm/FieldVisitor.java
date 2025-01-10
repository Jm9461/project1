package asm;

import java.io.OutputStream;

public final class FieldVisitor
  implements DrawingData
{
  public FieldVisitor() {}
  
  public void addRawData(byte[] paramArrayOfByte, OutputStream paramOutputStream)
  {
    paramOutputStream.write(paramArrayOfByte);
  }
}
