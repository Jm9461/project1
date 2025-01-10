package asm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

abstract class b
  implements h
{
  protected final i a;
  private final OutputStream b;
  protected final File f;
  
  protected b(i paramI, File paramFile)
  {
    a = paramI;
    f = paramFile;
    b = put(paramFile);
  }
  
  private OutputStream put(File paramFile)
  {
    if (paramFile != null) {
      try
      {
        FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
        return localFileOutputStream;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("could not build OutputStream from this file");
        localStringBuilder.append(paramFile.getName());
        throw new RuntimeException(localStringBuilder.toString(), localFileNotFoundException);
      }
    }
    throw new RuntimeException("file is null !");
  }
  
  public void a()
  {
    a.stop();
  }
  
  public void audioStartThread()
  {
    new Thread(new GodotIO.1(this)).start();
  }
  
  public void c()
  {
    a.a().e(true);
    audioStartThread();
  }
  
  public void e()
  {
    a.a().e(false);
  }
}
