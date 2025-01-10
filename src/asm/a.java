package asm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

final class a
  extends b
{
  private final RandomAccessFile f = read(paramFile);
  
  public a(i paramI, File paramFile)
  {
    super(paramI, paramFile);
  }
  
  private void close()
  {
    long l = new FileInputStream(f).getChannel().size();
    RandomAccessFile localRandomAccessFile = f;
    try
    {
      localRandomAccessFile.seek(0L);
      localRandomAccessFile = f;
      i localI = a;
      localRandomAccessFile.write(new l(localI.a(), l).a());
      localRandomAccessFile = f;
      localRandomAccessFile.close();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
  
  private RandomAccessFile read(File paramFile)
  {
    try
    {
      paramFile = new RandomAccessFile(paramFile, "rw");
      return paramFile;
    }
    catch (FileNotFoundException paramFile)
    {
      throw new RuntimeException(paramFile);
    }
  }
  
  public void a()
  {
    super.a();
    try
    {
      close();
      return;
    }
    catch (IOException localIOException) {}
  }
}
