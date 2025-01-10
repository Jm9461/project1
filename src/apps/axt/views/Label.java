package apps.axt.views;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Label
{
  public Label(Context paramContext) {}
  
  private void closeStream(Closeable paramCloseable)
  {
    if (paramCloseable != null) {
      try
      {
        paramCloseable.close();
        return;
      }
      catch (IOException paramCloseable) {}
    }
  }
  
  public boolean copy(String paramString1, String paramString2)
  {
    Object localObject4 = resolve(paramString1);
    if (!((File)localObject4).isFile()) {
      return false;
    }
    Object localObject2 = null;
    paramString1 = null;
    FileChannel localFileChannel1 = null;
    FileOutputStream localFileOutputStream = null;
    Object localObject1 = localFileOutputStream;
    Object localObject3 = localFileChannel1;
    try
    {
      FileInputStream localFileInputStream = new FileInputStream((File)localObject4);
      localObject4 = localFileInputStream;
      paramString1 = (String)localObject4;
      localObject1 = localFileOutputStream;
      localObject2 = localObject4;
      localObject3 = localFileChannel1;
      localFileOutputStream = new FileOutputStream(new File(paramString2));
      paramString2 = localFileOutputStream;
      paramString1 = (String)localObject4;
      localObject1 = paramString2;
      localObject2 = localObject4;
      localObject3 = paramString2;
      localFileChannel1 = localFileInputStream.getChannel();
      paramString1 = (String)localObject4;
      localObject1 = paramString2;
      localObject2 = localObject4;
      localObject3 = paramString2;
      FileChannel localFileChannel2 = localFileOutputStream.getChannel();
      paramString1 = (String)localObject4;
      localObject1 = paramString2;
      localObject2 = localObject4;
      localObject3 = paramString2;
      localFileChannel1.transferTo(0L, localFileChannel1.size(), localFileChannel2);
      closeStream(localFileInputStream);
      closeStream(localFileOutputStream);
      return true;
    }
    catch (Throwable paramString2) {}catch (Exception paramString2)
    {
      paramString1 = localObject2;
      localObject1 = localObject3;
      Log.e("Storage", "Failed copy", paramString2);
      closeStream(localObject2);
      closeStream((Closeable)localObject3);
      return false;
    }
    closeStream(paramString1);
    closeStream((Closeable)localObject1);
    throw paramString2;
  }
  
  public String getName()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }
  
  public boolean remove(String paramString)
  {
    return new File(paramString).delete();
  }
  
  public File resolve(String paramString)
  {
    return new File(paramString);
  }
  
  public boolean update(String paramString)
  {
    Object localObject = new File(paramString);
    if (((File)localObject).exists())
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Directory '");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("' already exists");
      Log.w("Storage", ((StringBuilder)localObject).toString());
      return false;
    }
    return ((File)localObject).mkdirs();
  }
}
