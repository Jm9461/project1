package okhttp3.internal.publicsuffix;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp.internal.Util;
import okhttp.internal.internal.Platform;

public final class PublicSuffixDatabase
{
  private static final String[] INT_TYPE = { "*" };
  private static final String[] SHORT_TYPE;
  private static final byte[] d = { 42 };
  private static final PublicSuffixDatabase err = new PublicSuffixDatabase();
  private final CountDownLatch M = new CountDownLatch(1);
  private final AtomicBoolean a = new AtomicBoolean(false);
  private byte[] b;
  private byte[] p;
  
  static
  {
    SHORT_TYPE = new String[0];
  }
  
  public PublicSuffixDatabase() {}
  
  private static String a(byte[] paramArrayOfByte, byte[][] paramArrayOfByte1, int paramInt)
  {
    int j = 0;
    int i = paramArrayOfByte.length;
    if (j < i)
    {
      int k = (j + i) / 2;
      while ((k > -1) && (paramArrayOfByte[k] != 10)) {
        k -= 1;
      }
      int i5 = k + 1;
      int i1 = 1;
      while (paramArrayOfByte[(i5 + i1)] != 10) {
        i1 += 1;
      }
      int i6 = i5 + i1 - i5;
      int i2 = paramInt;
      int m = 0;
      k = 0;
      int n = 0;
      for (;;)
      {
        if (n != 0)
        {
          i3 = 46;
          n = 0;
        }
        else
        {
          i3 = paramArrayOfByte1[i2][m] & 0xFF;
        }
        int i7 = i3 - (paramArrayOfByte[(i5 + k)] & 0xFF);
        if (i7 == 0)
        {
          k += 1;
          int i4 = m + 1;
          if (k == i6)
          {
            m = i4;
          }
          else
          {
            i3 = i2;
            m = i4;
            if (paramArrayOfByte1[i2].length != i4) {
              break label349;
            }
            if (i2 != paramArrayOfByte1.length - 1) {
              break label334;
            }
            m = i4;
          }
        }
        if (i7 < 0)
        {
          i = i5 - 1;
          break;
        }
        if (i7 > 0)
        {
          j = i5 + i1 + 1;
          break;
        }
        n = i6 - k;
        m = paramArrayOfByte1[i2].length - m;
        k = i2 + 1;
        while (k < paramArrayOfByte1.length)
        {
          m += paramArrayOfByte1[k].length;
          k += 1;
        }
        if (m < n)
        {
          i = i5 - 1;
          break;
        }
        if (m > n)
        {
          j = i5 + i1 + 1;
          break;
        }
        return new String(paramArrayOfByte, i5, i6, Util.s);
        label334:
        int i3 = i2 + 1;
        n = 1;
        m = -1;
        label349:
        i2 = i3;
      }
    }
    return null;
  }
  
  private String[] a(String[] paramArrayOfString)
  {
    if ((!a.get()) && (a.compareAndSet(false, true)))
    {
      connect();
    }
    else
    {
      CountDownLatch localCountDownLatch = M;
      try
      {
        localCountDownLatch.await();
      }
      catch (InterruptedException localInterruptedException) {}
    }
    try
    {
      if (b != null)
      {
        byte[][] arrayOfByte1 = new byte[paramArrayOfString.length][];
        int i = 0;
        while (i < paramArrayOfString.length)
        {
          arrayOfByte1[i] = paramArrayOfString[i].getBytes(Util.s);
          i += 1;
        }
        Object localObject1 = null;
        i = 0;
        for (;;)
        {
          paramArrayOfString = (String[])localObject1;
          if (i >= arrayOfByte1.length) {
            break;
          }
          paramArrayOfString = a(b, arrayOfByte1, i);
          if (paramArrayOfString != null) {
            break;
          }
          i += 1;
        }
        Object localObject2 = null;
        localObject1 = localObject2;
        if (arrayOfByte1.length > 1)
        {
          arrayOfByte = (byte[][])arrayOfByte1.clone();
          i = 0;
          for (;;)
          {
            localObject1 = localObject2;
            if (i >= arrayOfByte.length - 1) {
              break;
            }
            arrayOfByte[i] = d;
            localObject1 = a(b, arrayOfByte, i);
            if (localObject1 != null) {
              break;
            }
            i += 1;
          }
        }
        byte[][] arrayOfByte = null;
        localObject2 = arrayOfByte;
        if (localObject1 != null)
        {
          i = 0;
          for (;;)
          {
            localObject2 = arrayOfByte;
            if (i >= arrayOfByte1.length - 1) {
              break;
            }
            localObject2 = a(p, arrayOfByte1, i);
            if (localObject2 != null) {
              break;
            }
            i += 1;
          }
        }
        if (localObject2 != null)
        {
          paramArrayOfString = new StringBuilder();
          paramArrayOfString.append("!");
          paramArrayOfString.append((String)localObject2);
          return paramArrayOfString.toString().split("\\.");
        }
        if ((paramArrayOfString == null) && (localObject1 == null)) {
          return INT_TYPE;
        }
        if (paramArrayOfString != null) {
          paramArrayOfString = paramArrayOfString.split("\\.");
        } else {
          paramArrayOfString = SHORT_TYPE;
        }
        if (localObject1 != null) {
          localObject1 = ((String)localObject1).split("\\.");
        } else {
          localObject1 = SHORT_TYPE;
        }
        if (paramArrayOfString.length > localObject1.length) {
          return paramArrayOfString;
        }
        return localObject1;
      }
      throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
    }
    catch (Throwable paramArrayOfString)
    {
      throw paramArrayOfString;
    }
  }
  
  private void connect()
  {
    int i = 0;
    for (;;)
    {
      try
      {
        try
        {
          read();
          if (i == 0) {
            break;
          }
          Thread.currentThread().interrupt();
          return;
        }
        catch (Throwable localThrowable) {}catch (IOException localIOException)
        {
          Platform.get().log(5, "Failed to read public suffix list", localIOException);
          if (i == 0) {
            break;
          }
        }
        Thread.currentThread().interrupt();
        return;
      }
      catch (InterruptedIOException localInterruptedIOException)
      {
        i = 1;
      }
      if (i != 0) {
        Thread.currentThread().interrupt();
      }
      throw localIOException;
    }
  }
  
  public static PublicSuffixDatabase createParser()
  {
    return err;
  }
  
  /* Error */
  private void read()
  {
    // Byte code:
    //   0: ldc 2
    //   2: ldc -103
    //   4: invokevirtual 159	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   7: astore_1
    //   8: aload_1
    //   9: ifnonnull +4 -> 13
    //   12: return
    //   13: new 161	okio/GzipSource
    //   16: dup
    //   17: aload_1
    //   18: invokestatic 167	okio/Okio:source	(Ljava/io/InputStream;)Lokio/Source;
    //   21: invokespecial 170	okio/GzipSource:<init>	(Lokio/Source;)V
    //   24: invokestatic 174	okio/Okio:buffer	(Lokio/Source;)Lokio/BufferedSource;
    //   27: astore_1
    //   28: aload_1
    //   29: invokeinterface 180 1 0
    //   34: newarray byte
    //   36: astore_2
    //   37: aload_1
    //   38: aload_2
    //   39: invokeinterface 184 2 0
    //   44: aload_1
    //   45: invokeinterface 180 1 0
    //   50: newarray byte
    //   52: astore_3
    //   53: aload_1
    //   54: aload_3
    //   55: invokeinterface 184 2 0
    //   60: aload_1
    //   61: invokestatic 188	okhttp/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   64: aload_0
    //   65: monitorenter
    //   66: aload_0
    //   67: aload_2
    //   68: putfield 81	okhttp3/internal/publicsuffix/PublicSuffixDatabase:b	[B
    //   71: aload_0
    //   72: aload_3
    //   73: putfield 96	okhttp3/internal/publicsuffix/PublicSuffixDatabase:p	[B
    //   76: aload_0
    //   77: monitorexit
    //   78: aload_0
    //   79: getfield 50	okhttp3/internal/publicsuffix/PublicSuffixDatabase:M	Ljava/util/concurrent/CountDownLatch;
    //   82: invokevirtual 191	java/util/concurrent/CountDownLatch:countDown	()V
    //   85: return
    //   86: astore_1
    //   87: aload_0
    //   88: monitorexit
    //   89: aload_1
    //   90: athrow
    //   91: astore_2
    //   92: aload_1
    //   93: invokestatic 188	okhttp/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   96: aload_2
    //   97: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	98	0	this	PublicSuffixDatabase
    //   7	54	1	localObject	Object
    //   86	7	1	localThrowable1	Throwable
    //   36	32	2	arrayOfByte1	byte[]
    //   91	6	2	localThrowable2	Throwable
    //   52	21	3	arrayOfByte2	byte[]
    // Exception table:
    //   from	to	target	type
    //   66	78	86	java/lang/Throwable
    //   87	89	86	java/lang/Throwable
    //   28	60	91	java/lang/Throwable
  }
  
  public String parse(String paramString)
  {
    if (paramString != null)
    {
      Object localObject = IDN.toUnicode(paramString).split("\\.");
      String[] arrayOfString = a((String[])localObject);
      if ((localObject.length == arrayOfString.length) && (arrayOfString[0].charAt(0) != '!')) {
        return null;
      }
      int i;
      if (arrayOfString[0].charAt(0) == '!') {
        i = localObject.length - arrayOfString.length;
      } else {
        i = localObject.length - (arrayOfString.length + 1);
      }
      localObject = new StringBuilder();
      paramString = paramString.split("\\.");
      while (i < paramString.length)
      {
        ((StringBuilder)localObject).append(paramString[i]);
        ((StringBuilder)localObject).append('.');
        i += 1;
      }
      ((StringBuilder)localObject).deleteCharAt(((StringBuilder)localObject).length() - 1);
      return ((StringBuilder)localObject).toString();
    }
    paramString = new NullPointerException("domain == null");
    throw paramString;
  }
}
