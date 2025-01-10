package org.org.jaxen.asm;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class a
{
  public static File a(Context paramContext)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(".font");
    ((StringBuilder)localObject).append(Process.myPid());
    ((StringBuilder)localObject).append("-");
    ((StringBuilder)localObject).append(Process.myTid());
    ((StringBuilder)localObject).append("-");
    localObject = ((StringBuilder)localObject).toString();
    int i = 0;
    while (i < 100)
    {
      File localFile = paramContext.getCacheDir();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject);
      localStringBuilder.append(i);
      localFile = new File(localFile, localStringBuilder.toString());
      try
      {
        boolean bool = localFile.createNewFile();
        if (bool) {
          return localFile;
        }
      }
      catch (IOException localIOException) {}
      i += 1;
    }
    return null;
  }
  
  /* Error */
  public static ByteBuffer a(Context paramContext, Resources paramResources, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 59	org/org/jaxen/asm/a:a	(Landroid/content/Context;)Ljava/io/File;
    //   4: astore 4
    //   6: aconst_null
    //   7: astore_0
    //   8: aload 4
    //   10: ifnonnull +5 -> 15
    //   13: aconst_null
    //   14: areturn
    //   15: aload 4
    //   17: aload_1
    //   18: iload_2
    //   19: invokestatic 63	org/org/jaxen/asm/a:b	(Ljava/io/File;Landroid/content/res/Resources;I)Z
    //   22: istore_3
    //   23: iload_3
    //   24: ifne +11 -> 35
    //   27: aload 4
    //   29: invokevirtual 66	java/io/File:delete	()Z
    //   32: pop
    //   33: aload_0
    //   34: areturn
    //   35: aload 4
    //   37: invokestatic 70	org/org/jaxen/asm/a:read	(Ljava/io/File;)Ljava/nio/ByteBuffer;
    //   40: astore_0
    //   41: goto -14 -> 27
    //   44: astore_0
    //   45: aload 4
    //   47: invokevirtual 66	java/io/File:delete	()Z
    //   50: pop
    //   51: goto +3 -> 54
    //   54: aload_0
    //   55: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	56	0	paramContext	Context
    //   0	56	1	paramResources	Resources
    //   0	56	2	paramInt	int
    //   22	2	3	bool	boolean
    //   4	42	4	localFile	File
    // Exception table:
    //   from	to	target	type
    //   15	23	44	java/lang/Throwable
    //   35	41	44	java/lang/Throwable
  }
  
  public static boolean a(File paramFile, InputStream paramInputStream)
  {
    File localFile2 = null;
    File localFile1 = null;
    StrictMode.ThreadPolicy localThreadPolicy = StrictMode.allowThreadDiskWrites();
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(paramFile, false);
      paramFile = localFileOutputStream;
      byte[] arrayOfByte = new byte['?'];
      for (;;)
      {
        localFile1 = paramFile;
        localFile2 = paramFile;
        int i = paramInputStream.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localFile1 = paramFile;
        localFile2 = paramFile;
        localFileOutputStream.write(arrayOfByte, 0, i);
      }
      close(localFileOutputStream);
      StrictMode.setThreadPolicy(localThreadPolicy);
      return true;
    }
    catch (Throwable paramFile) {}catch (IOException paramFile)
    {
      localFile1 = localFile2;
      paramInputStream = new StringBuilder();
      localFile1 = localFile2;
      paramInputStream.append("Error copying resource contents to temp file: ");
      localFile1 = localFile2;
      paramInputStream.append(paramFile.getMessage());
      localFile1 = localFile2;
      Log.e("TypefaceCompatUtil", paramInputStream.toString());
      close(localFile2);
      StrictMode.setThreadPolicy(localThreadPolicy);
      return false;
    }
    close(localFile1);
    StrictMode.setThreadPolicy(localThreadPolicy);
    throw paramFile;
  }
  
  public static boolean b(File paramFile, Resources paramResources, int paramInt)
  {
    Resources localResources = null;
    try
    {
      paramResources = paramResources.openRawResource(paramInt);
      localResources = paramResources;
      boolean bool = a(paramFile, paramResources);
      close(paramResources);
      return bool;
    }
    catch (Throwable paramFile)
    {
      close(localResources);
      throw paramFile;
    }
  }
  
  public static void close(Closeable paramCloseable)
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
  
  /* Error */
  public static ByteBuffer read(Context paramContext, android.os.CancellationSignal paramCancellationSignal, android.net.Uri paramUri)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 129	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: astore_0
    //   5: aload_0
    //   6: aload_2
    //   7: ldc -125
    //   9: aload_1
    //   10: invokevirtual 137	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
    //   13: astore_0
    //   14: aload_0
    //   15: ifnonnull +13 -> 28
    //   18: aload_0
    //   19: ifnull +100 -> 119
    //   22: aload_0
    //   23: invokevirtual 140	android/os/ParcelFileDescriptor:close	()V
    //   26: aconst_null
    //   27: areturn
    //   28: new 142	java/io/FileInputStream
    //   31: dup
    //   32: aload_0
    //   33: invokevirtual 146	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   36: invokespecial 149	java/io/FileInputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   39: astore_1
    //   40: aload_1
    //   41: invokevirtual 153	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   44: astore_2
    //   45: aload_2
    //   46: invokevirtual 159	java/nio/channels/FileChannel:size	()J
    //   49: lstore_3
    //   50: aload_2
    //   51: getstatic 165	java/nio/channels/FileChannel$MapMode:READ_ONLY	Ljava/nio/channels/FileChannel$MapMode;
    //   54: lconst_0
    //   55: lload_3
    //   56: invokevirtual 169	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   59: astore_2
    //   60: aload_1
    //   61: invokevirtual 170	java/io/FileInputStream:close	()V
    //   64: aload_0
    //   65: ifnull +56 -> 121
    //   68: aload_0
    //   69: invokevirtual 140	android/os/ParcelFileDescriptor:close	()V
    //   72: aload_2
    //   73: areturn
    //   74: astore_2
    //   75: aload_2
    //   76: athrow
    //   77: astore 5
    //   79: aload_1
    //   80: invokevirtual 170	java/io/FileInputStream:close	()V
    //   83: goto +9 -> 92
    //   86: astore_1
    //   87: aload_2
    //   88: aload_1
    //   89: invokevirtual 174	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   92: aload 5
    //   94: athrow
    //   95: astore_1
    //   96: aload_1
    //   97: athrow
    //   98: astore_2
    //   99: aload_0
    //   100: ifnull +16 -> 116
    //   103: aload_0
    //   104: invokevirtual 140	android/os/ParcelFileDescriptor:close	()V
    //   107: goto +9 -> 116
    //   110: astore_0
    //   111: aload_1
    //   112: aload_0
    //   113: invokevirtual 174	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   116: aload_2
    //   117: athrow
    //   118: astore_0
    //   119: aconst_null
    //   120: areturn
    //   121: aload_2
    //   122: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	123	0	paramContext	Context
    //   0	123	1	paramCancellationSignal	android.os.CancellationSignal
    //   0	123	2	paramUri	android.net.Uri
    //   49	7	3	l	long
    //   77	16	5	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   40	60	74	java/lang/Throwable
    //   75	77	77	java/lang/Throwable
    //   79	83	86	java/lang/Throwable
    //   28	40	95	java/lang/Throwable
    //   60	64	95	java/lang/Throwable
    //   87	92	95	java/lang/Throwable
    //   92	95	95	java/lang/Throwable
    //   96	98	98	java/lang/Throwable
    //   103	107	110	java/lang/Throwable
    //   5	14	118	java/io/IOException
    //   22	26	118	java/io/IOException
    //   68	72	118	java/io/IOException
    //   111	116	118	java/io/IOException
    //   116	118	118	java/io/IOException
  }
  
  private static ByteBuffer read(File paramFile)
  {
    try
    {
      paramFile = new FileInputStream(paramFile);
      try
      {
        Object localObject = paramFile.getChannel();
        long l = ((FileChannel)localObject).size();
        localObject = ((FileChannel)localObject).map(FileChannel.MapMode.READ_ONLY, 0L, l);
        paramFile.close();
        return localObject;
      }
      catch (Throwable localThrowable1)
      {
        try
        {
          throw localThrowable1;
        }
        catch (Throwable localThrowable2)
        {
          try
          {
            paramFile.close();
          }
          catch (Throwable paramFile)
          {
            localThrowable1.addSuppressed(paramFile);
          }
          throw localThrowable2;
        }
      }
      return null;
    }
    catch (IOException paramFile) {}
  }
}
