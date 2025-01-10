package okhttp.internal.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import okio.Okio;
import okio.Sink;
import okio.Source;

public abstract interface FileSystem
{
  public static final FileSystem SYSTEM = new FileSystem()
  {
    public Sink appendingSink(File paramAnonymousFile)
    {
      try
      {
        Sink localSink = Okio.appendingSink(paramAnonymousFile);
        return localSink;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        paramAnonymousFile.getParentFile().mkdirs();
      }
      return Okio.appendingSink(paramAnonymousFile);
    }
    
    public void delete(File paramAnonymousFile)
    {
      if (!paramAnonymousFile.delete())
      {
        if (!paramAnonymousFile.exists()) {
          return;
        }
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("failed to delete ");
        localStringBuilder.append(paramAnonymousFile);
        throw new IOException(localStringBuilder.toString());
      }
    }
    
    public void deleteContents(File paramAnonymousFile)
    {
      Object localObject = paramAnonymousFile.listFiles();
      if (localObject != null)
      {
        int j = localObject.length;
        int i = 0;
        for (;;)
        {
          if (i >= j) {
            return;
          }
          paramAnonymousFile = localObject[i];
          if (paramAnonymousFile.isDirectory()) {
            deleteContents(paramAnonymousFile);
          }
          if (!paramAnonymousFile.delete()) {
            break;
          }
          i += 1;
        }
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("failed to delete ");
        ((StringBuilder)localObject).append(paramAnonymousFile);
        throw new IOException(((StringBuilder)localObject).toString());
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("not a readable directory: ");
      ((StringBuilder)localObject).append(paramAnonymousFile);
      paramAnonymousFile = new IOException(((StringBuilder)localObject).toString());
      throw paramAnonymousFile;
    }
    
    public boolean exists(File paramAnonymousFile)
    {
      return paramAnonymousFile.exists();
    }
    
    public void rename(File paramAnonymousFile1, File paramAnonymousFile2)
    {
      delete(paramAnonymousFile2);
      if (paramAnonymousFile1.renameTo(paramAnonymousFile2)) {
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("failed to rename ");
      localStringBuilder.append(paramAnonymousFile1);
      localStringBuilder.append(" to ");
      localStringBuilder.append(paramAnonymousFile2);
      throw new IOException(localStringBuilder.toString());
    }
    
    public Sink sink(File paramAnonymousFile)
    {
      try
      {
        Sink localSink = Okio.sink(paramAnonymousFile);
        return localSink;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        paramAnonymousFile.getParentFile().mkdirs();
      }
      return Okio.sink(paramAnonymousFile);
    }
    
    public long size(File paramAnonymousFile)
    {
      return paramAnonymousFile.length();
    }
    
    public Source source(File paramAnonymousFile)
    {
      return Okio.source(paramAnonymousFile);
    }
  };
  
  public abstract Sink appendingSink(File paramFile);
  
  public abstract void delete(File paramFile);
  
  public abstract void deleteContents(File paramFile);
  
  public abstract boolean exists(File paramFile);
  
  public abstract void rename(File paramFile1, File paramFile2);
  
  public abstract Sink sink(File paramFile);
  
  public abstract long size(File paramFile);
  
  public abstract Source source(File paramFile);
}
