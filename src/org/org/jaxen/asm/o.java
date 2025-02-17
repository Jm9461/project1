package org.org.jaxen.asm;

import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import java.io.File;

class o
  extends e
{
  o() {}
  
  private File close(ParcelFileDescriptor paramParcelFileDescriptor)
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("/proc/self/fd/");
      ((StringBuilder)localObject).append(paramParcelFileDescriptor.getFd());
      paramParcelFileDescriptor = Os.readlink(((StringBuilder)localObject).toString());
      localObject = Os.stat(paramParcelFileDescriptor);
      int i = st_mode;
      boolean bool = OsConstants.S_ISREG(i);
      if (bool)
      {
        paramParcelFileDescriptor = new File(paramParcelFileDescriptor);
        return paramParcelFileDescriptor;
      }
      return null;
    }
    catch (ErrnoException paramParcelFileDescriptor) {}
    return null;
  }
  
  /* Error */
  public android.graphics.Typeface a(android.content.Context paramContext, android.os.CancellationSignal paramCancellationSignal, org.org.jaxen.ui.Label[] paramArrayOfLabel, int paramInt)
  {
    // Byte code:
    //   0: aload_3
    //   1: arraylength
    //   2: iconst_1
    //   3: if_icmpge +5 -> 8
    //   6: aconst_null
    //   7: areturn
    //   8: aload_0
    //   9: aload_3
    //   10: iload 4
    //   12: invokevirtual 71	org/org/jaxen/asm/e:a	([Lorg/org/jaxen/ui/Label;I)Lorg/org/jaxen/ui/Label;
    //   15: astore_3
    //   16: aload_1
    //   17: invokevirtual 77	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 6
    //   22: aload 6
    //   24: aload_3
    //   25: invokevirtual 83	org/org/jaxen/ui/Label:getValue	()Landroid/net/Uri;
    //   28: ldc 85
    //   30: aload_2
    //   31: invokevirtual 91	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
    //   34: astore_3
    //   35: aload_0
    //   36: aload_3
    //   37: invokespecial 93	org/org/jaxen/asm/o:close	(Landroid/os/ParcelFileDescriptor;)Ljava/io/File;
    //   40: astore_2
    //   41: aload_2
    //   42: ifnull +34 -> 76
    //   45: aload_2
    //   46: invokevirtual 97	java/io/File:canRead	()Z
    //   49: istore 5
    //   51: iload 5
    //   53: ifne +6 -> 59
    //   56: goto +20 -> 76
    //   59: aload_2
    //   60: invokestatic 103	android/graphics/Typeface:createFromFile	(Ljava/io/File;)Landroid/graphics/Typeface;
    //   63: astore_2
    //   64: aload_2
    //   65: astore_1
    //   66: aload_3
    //   67: ifnull +95 -> 162
    //   70: aload_3
    //   71: invokevirtual 105	android/os/ParcelFileDescriptor:close	()V
    //   74: aload_2
    //   75: areturn
    //   76: new 107	java/io/FileInputStream
    //   79: dup
    //   80: aload_3
    //   81: invokevirtual 111	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   84: invokespecial 114	java/io/FileInputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   87: astore 6
    //   89: aload_0
    //   90: aload_1
    //   91: aload 6
    //   93: invokespecial 117	org/org/jaxen/asm/e:a	(Landroid/content/Context;Ljava/io/InputStream;)Landroid/graphics/Typeface;
    //   96: astore_2
    //   97: aload 6
    //   99: invokevirtual 118	java/io/FileInputStream:close	()V
    //   102: aload_2
    //   103: astore_1
    //   104: aload_3
    //   105: ifnull +57 -> 162
    //   108: aload_3
    //   109: invokevirtual 105	android/os/ParcelFileDescriptor:close	()V
    //   112: aload_2
    //   113: areturn
    //   114: astore_1
    //   115: aload_1
    //   116: athrow
    //   117: astore_2
    //   118: aload 6
    //   120: invokevirtual 118	java/io/FileInputStream:close	()V
    //   123: goto +11 -> 134
    //   126: astore 6
    //   128: aload_1
    //   129: aload 6
    //   131: invokevirtual 122	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   134: aload_2
    //   135: athrow
    //   136: astore_1
    //   137: aload_1
    //   138: athrow
    //   139: astore_2
    //   140: aload_3
    //   141: ifnull +16 -> 157
    //   144: aload_3
    //   145: invokevirtual 105	android/os/ParcelFileDescriptor:close	()V
    //   148: goto +9 -> 157
    //   151: astore_3
    //   152: aload_1
    //   153: aload_3
    //   154: invokevirtual 122	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   157: aload_2
    //   158: athrow
    //   159: astore_1
    //   160: aconst_null
    //   161: areturn
    //   162: aload_1
    //   163: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	164	0	this	o
    //   0	164	1	paramContext	android.content.Context
    //   0	164	2	paramCancellationSignal	android.os.CancellationSignal
    //   0	164	3	paramArrayOfLabel	org.org.jaxen.ui.Label[]
    //   0	164	4	paramInt	int
    //   49	3	5	bool	boolean
    //   20	99	6	localObject	Object
    //   126	4	6	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   89	97	114	java/lang/Throwable
    //   115	117	117	java/lang/Throwable
    //   118	123	126	java/lang/Throwable
    //   35	41	136	java/lang/Throwable
    //   45	51	136	java/lang/Throwable
    //   59	64	136	java/lang/Throwable
    //   76	89	136	java/lang/Throwable
    //   97	102	136	java/lang/Throwable
    //   128	134	136	java/lang/Throwable
    //   134	136	136	java/lang/Throwable
    //   137	139	139	java/lang/Throwable
    //   144	148	151	java/lang/Throwable
    //   22	35	159	java/io/IOException
    //   70	74	159	java/io/IOException
    //   108	112	159	java/io/IOException
    //   152	157	159	java/io/IOException
    //   157	159	159	java/io/IOException
  }
}
