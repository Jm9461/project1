package okhttp.internal.okhttp;

import e.f0.e.d.d;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp.internal.Util;
import okhttp.internal.internal.Platform;
import okhttp.internal.io.FileSystem;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class DiskLruCache
  implements Closeable, Flushable
{
  static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
  private final int appVersion;
  private final Runnable cleanupRunnable = new MonthByWeekFragment.2(this);
  boolean closed;
  final File directory;
  private final Executor executor;
  final FileSystem fileSystem;
  boolean hasJournalErrors;
  boolean initialized;
  private final File journalFile;
  private final File journalFileBackup;
  private final File journalFileTmp;
  BufferedSink journalWriter;
  final LinkedHashMap<String, d.d> lruEntries = new LinkedHashMap(0, 0.75F, true);
  private long maxSize;
  boolean mostRecentTrimFailed;
  private long nextSequenceNumber = 0L;
  boolean readable;
  int redundantOpCount;
  private long size = 0L;
  final int valueCount;
  
  DiskLruCache(FileSystem paramFileSystem, File paramFile, int paramInt1, int paramInt2, long paramLong, Executor paramExecutor)
  {
    fileSystem = paramFileSystem;
    directory = paramFile;
    appVersion = paramInt1;
    journalFile = new File(paramFile, "journal");
    journalFileTmp = new File(paramFile, "journal.tmp");
    journalFileBackup = new File(paramFile, "journal.bkp");
    valueCount = paramInt2;
    maxSize = paramLong;
    executor = paramExecutor;
  }
  
  private void checkNotClosed()
  {
    try
    {
      boolean bool = isClosed();
      if (!bool) {
        return;
      }
      throw new IllegalStateException("cache is closed");
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public static DiskLruCache create(FileSystem paramFileSystem, File paramFile, int paramInt1, int paramInt2, long paramLong)
  {
    if (paramLong > 0L)
    {
      if (paramInt2 > 0) {
        return new DiskLruCache(paramFileSystem, paramFile, paramInt1, paramInt2, paramLong, new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp DiskLruCache", true)));
      }
      throw new IllegalArgumentException("valueCount <= 0");
    }
    throw new IllegalArgumentException("maxSize <= 0");
  }
  
  private BufferedSink newJournalWriter()
  {
    return Okio.buffer(new DiskLruCache.2(this, fileSystem.appendingSink(journalFile)));
  }
  
  private void processJournal()
  {
    fileSystem.delete(journalFileTmp);
    Iterator localIterator = lruEntries.values().iterator();
    while (localIterator.hasNext())
    {
      Entry localEntry = (Entry)localIterator.next();
      int i;
      if (currentEditor == null)
      {
        i = 0;
        while (i < valueCount)
        {
          size += lengths[i];
          i += 1;
        }
      }
      else
      {
        currentEditor = null;
        i = 0;
        while (i < valueCount)
        {
          fileSystem.delete(cleanFiles[i]);
          fileSystem.delete(size[i]);
          i += 1;
        }
        localIterator.remove();
      }
    }
  }
  
  private void readJournal()
  {
    BufferedSource localBufferedSource = Okio.buffer(fileSystem.source(journalFile));
    try
    {
      String str1 = localBufferedSource.readUtf8LineStrict();
      String str2 = localBufferedSource.readUtf8LineStrict();
      Object localObject = localBufferedSource.readUtf8LineStrict();
      String str3 = localBufferedSource.readUtf8LineStrict();
      String str4 = localBufferedSource.readUtf8LineStrict();
      boolean bool = "libcore.io.DiskLruCache".equals(str1);
      if (bool)
      {
        bool = "1".equals(str2);
        if (bool)
        {
          bool = Integer.toString(appVersion).equals(localObject);
          if (bool)
          {
            bool = Integer.toString(valueCount).equals(str3);
            if (bool)
            {
              bool = "".equals(str4);
              if (bool)
              {
                int i = 0;
                try
                {
                  for (;;)
                  {
                    readJournalLine(localBufferedSource.readUtf8LineStrict());
                    i += 1;
                  }
                  int j;
                  localObject = new StringBuilder();
                }
                catch (EOFException localEOFException)
                {
                  j = lruEntries.size();
                  redundantOpCount = (i - j);
                  bool = localBufferedSource.exhausted();
                  if (!bool) {
                    rebuildJournal();
                  } else {
                    journalWriter = newJournalWriter();
                  }
                  Util.closeQuietly(localBufferedSource);
                  return;
                }
              }
            }
          }
        }
      }
      ((StringBuilder)localObject).append("unexpected journal header: [");
      ((StringBuilder)localObject).append(localEOFException);
      ((StringBuilder)localObject).append(", ");
      ((StringBuilder)localObject).append(str2);
      ((StringBuilder)localObject).append(", ");
      ((StringBuilder)localObject).append(str3);
      ((StringBuilder)localObject).append(", ");
      ((StringBuilder)localObject).append(str4);
      ((StringBuilder)localObject).append("]");
      throw new IOException(((StringBuilder)localObject).toString());
    }
    catch (Throwable localThrowable)
    {
      Util.closeQuietly(localBufferedSource);
      throw localThrowable;
    }
  }
  
  private void readJournalLine(String paramString)
  {
    int i = paramString.indexOf(' ');
    if (i != -1)
    {
      int j = i + 1;
      int k = paramString.indexOf(' ', j);
      if (k == -1)
      {
        localObject3 = paramString.substring(j);
        localObject2 = localObject3;
        localObject1 = localObject2;
        if (i == "REMOVE".length())
        {
          localObject1 = localObject2;
          if (paramString.startsWith("REMOVE")) {
            lruEntries.remove(localObject3);
          }
        }
      }
      else
      {
        localObject1 = paramString.substring(j, k);
      }
      Object localObject3 = (Entry)lruEntries.get(localObject1);
      Object localObject2 = localObject3;
      if (localObject3 == null)
      {
        localObject3 = new Entry((String)localObject1);
        localObject2 = localObject3;
        lruEntries.put(localObject1, localObject3);
      }
      if ((k != -1) && (i == "CLEAN".length()) && (paramString.startsWith("CLEAN")))
      {
        paramString = paramString.substring(k + 1).split(" ");
        readable = true;
        currentEditor = null;
        localObject2.remove(paramString);
        return;
      }
      if ((k == -1) && (i == "DIRTY".length()) && (paramString.startsWith("DIRTY")))
      {
        currentEditor = new Editor(localObject2);
        return;
      }
      if ((k == -1) && (i == "READ".length()) && (paramString.startsWith("READ"))) {
        return;
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("unexpected journal line: ");
      ((StringBuilder)localObject1).append(paramString);
      throw new IOException(((StringBuilder)localObject1).toString());
    }
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("unexpected journal line: ");
    ((StringBuilder)localObject1).append(paramString);
    throw new IOException(((StringBuilder)localObject1).toString());
  }
  
  private void validateKey(String paramString)
  {
    if (LEGAL_KEY_PATTERN.matcher(paramString).matches()) {
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("keys must match regex [a-z0-9_-]{1,120}: \"");
    localStringBuilder.append(paramString);
    localStringBuilder.append("\"");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  public void close()
  {
    for (;;)
    {
      int i;
      try
      {
        if ((initialized) && (!closed))
        {
          Entry[] arrayOfEntry = (Entry[])lruEntries.values().toArray(new Entry[lruEntries.size()]);
          int j = arrayOfEntry.length;
          i = 0;
          if (i < j)
          {
            Entry localEntry = arrayOfEntry[i];
            if (currentEditor != null) {
              currentEditor.abort();
            }
          }
          else
          {
            trimToSize();
            journalWriter.close();
            journalWriter = null;
            closed = true;
          }
        }
        else
        {
          closed = true;
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      i += 1;
    }
  }
  
  void completeEdit(Editor paramEditor, boolean paramBoolean)
  {
    for (;;)
    {
      int j;
      try
      {
        Entry localEntry = entry;
        if (currentEditor == paramEditor)
        {
          int k = 0;
          j = k;
          if (paramBoolean)
          {
            j = k;
            if (!readable)
            {
              int i = 0;
              j = k;
              if (i < valueCount)
              {
                if (written[i] != 0)
                {
                  if (!fileSystem.exists(size[i]))
                  {
                    paramEditor.abort();
                    return;
                  }
                  i += 1;
                  continue;
                }
                paramEditor.abort();
                paramEditor = new StringBuilder();
                paramEditor.append("Newly created entry didn't create value for index ");
                paramEditor.append(i);
                throw new IllegalStateException(paramEditor.toString());
              }
            }
          }
          long l1;
          if (j < valueCount)
          {
            paramEditor = size[j];
            if (paramBoolean)
            {
              if (fileSystem.exists(paramEditor))
              {
                File localFile = cleanFiles[j];
                fileSystem.rename(paramEditor, localFile);
                l1 = lengths[j];
                long l2 = fileSystem.size(localFile);
                lengths[j] = l2;
                size = (size - l1 + l2);
              }
            }
            else {
              fileSystem.delete(paramEditor);
            }
          }
          else
          {
            redundantOpCount += 1;
            currentEditor = null;
            if ((readable | paramBoolean))
            {
              readable = true;
              journalWriter.writeUtf8("CLEAN").writeByte(32);
              journalWriter.writeUtf8(key);
              localEntry.writeLengths(journalWriter);
              journalWriter.writeByte(10);
              if (paramBoolean)
              {
                l1 = nextSequenceNumber;
                nextSequenceNumber = (1L + l1);
                sequenceNumber = l1;
              }
            }
            else
            {
              lruEntries.remove(key);
              journalWriter.writeUtf8("REMOVE").writeByte(32);
              journalWriter.writeUtf8(key);
              journalWriter.writeByte(10);
            }
            journalWriter.flush();
            if ((size > maxSize) || (remove())) {
              executor.execute(cleanupRunnable);
            }
          }
        }
        else
        {
          throw new IllegalStateException();
        }
      }
      catch (Throwable paramEditor)
      {
        throw paramEditor;
      }
      j += 1;
    }
  }
  
  public void delete()
  {
    close();
    fileSystem.deleteContents(directory);
  }
  
  public Editor edit(String paramString)
  {
    return edit(paramString, -1L);
  }
  
  Editor edit(String paramString, long paramLong)
  {
    try
    {
      initialize();
      checkNotClosed();
      validateKey(paramString);
      Entry localEntry = (Entry)lruEntries.get(paramString);
      if (paramLong != -1L) {
        if (localEntry != null)
        {
          long l = sequenceNumber;
          if (l == paramLong) {}
        }
        else
        {
          return null;
        }
      }
      Object localObject;
      if (localEntry != null)
      {
        localObject = currentEditor;
        if (localObject != null) {
          return null;
        }
      }
      if ((!mostRecentTrimFailed) && (!readable))
      {
        journalWriter.writeUtf8("DIRTY").writeByte(32).writeUtf8(paramString).writeByte(10);
        journalWriter.flush();
        boolean bool = hasJournalErrors;
        if (bool) {
          return null;
        }
        localObject = localEntry;
        if (localEntry == null)
        {
          localEntry = new Entry(paramString);
          localObject = localEntry;
          lruEntries.put(paramString, localEntry);
        }
        paramString = new Editor((Entry)localObject);
        currentEditor = paramString;
        return paramString;
      }
      executor.execute(cleanupRunnable);
      return null;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public void flush()
  {
    try
    {
      boolean bool = initialized;
      if (!bool) {
        return;
      }
      checkNotClosed();
      trimToSize();
      journalWriter.flush();
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public Snapshot get(String paramString)
  {
    try
    {
      initialize();
      checkNotClosed();
      validateKey(paramString);
      Object localObject = (Entry)lruEntries.get(paramString);
      if ((localObject != null) && (readable))
      {
        localObject = ((Entry)localObject).get();
        if (localObject == null) {
          return null;
        }
        redundantOpCount += 1;
        journalWriter.writeUtf8("READ").writeByte(32).writeUtf8(paramString).writeByte(10);
        if (remove()) {
          executor.execute(cleanupRunnable);
        }
        return localObject;
      }
      return null;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public void initialize()
  {
    try
    {
      boolean bool = initialized;
      if (bool) {
        return;
      }
      if (fileSystem.exists(journalFileBackup)) {
        if (fileSystem.exists(journalFile)) {
          fileSystem.delete(journalFileBackup);
        } else {
          fileSystem.rename(journalFileBackup, journalFile);
        }
      }
      bool = fileSystem.exists(journalFile);
      if (bool) {
        try
        {
          readJournal();
          processJournal();
          initialized = true;
          return;
        }
        catch (IOException localIOException)
        {
          Platform localPlatform = Platform.get();
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("DiskLruCache ");
          localStringBuilder.append(directory);
          localStringBuilder.append(" is corrupt: ");
          localStringBuilder.append(localIOException.getMessage());
          localStringBuilder.append(", removing");
          localPlatform.log(5, localStringBuilder.toString(), localIOException);
          try
          {
            delete();
            closed = false;
          }
          catch (Throwable localThrowable1)
          {
            closed = false;
            throw localThrowable1;
          }
        }
      }
      rebuildJournal();
      initialized = true;
      return;
    }
    catch (Throwable localThrowable2)
    {
      throw localThrowable2;
    }
  }
  
  public boolean isClosed()
  {
    try
    {
      boolean bool = closed;
      return bool;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  /* Error */
  void rebuildJournal()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: astore_1
    //   4: aload_0
    //   5: getfield 272	okhttp/internal/okhttp/DiskLruCache:journalWriter	Lokio/BufferedSink;
    //   8: astore_3
    //   9: aload_0
    //   10: astore_2
    //   11: aload_3
    //   12: ifnull +18 -> 30
    //   15: aload_2
    //   16: astore_1
    //   17: aload_2
    //   18: getfield 272	okhttp/internal/okhttp/DiskLruCache:journalWriter	Lokio/BufferedSink;
    //   21: astore_3
    //   22: aload_2
    //   23: astore_1
    //   24: aload_3
    //   25: invokeinterface 386 1 0
    //   30: aload_0
    //   31: astore_2
    //   32: aload_2
    //   33: astore_1
    //   34: aload_2
    //   35: getfield 84	okhttp/internal/okhttp/DiskLruCache:fileSystem	Lokhttp/internal/io/FileSystem;
    //   38: aload_2
    //   39: getfield 101	okhttp/internal/okhttp/DiskLruCache:journalFileTmp	Ljava/io/File;
    //   42: invokeinterface 502 2 0
    //   47: invokestatic 179	okio/Okio:buffer	(Lokio/Sink;)Lokio/BufferedSink;
    //   50: astore_3
    //   51: aload_3
    //   52: ldc -18
    //   54: invokeinterface 418 2 0
    //   59: bipush 10
    //   61: invokeinterface 422 2 0
    //   66: pop
    //   67: aload_3
    //   68: ldc -10
    //   70: invokeinterface 418 2 0
    //   75: bipush 10
    //   77: invokeinterface 422 2 0
    //   82: pop
    //   83: aload_3
    //   84: aload_2
    //   85: getfield 88	okhttp/internal/okhttp/DiskLruCache:appVersion	I
    //   88: i2l
    //   89: invokeinterface 506 3 0
    //   94: bipush 10
    //   96: invokeinterface 422 2 0
    //   101: pop
    //   102: aload_3
    //   103: aload_2
    //   104: getfield 107	okhttp/internal/okhttp/DiskLruCache:valueCount	I
    //   107: i2l
    //   108: invokeinterface 506 3 0
    //   113: bipush 10
    //   115: invokeinterface 422 2 0
    //   120: pop
    //   121: aload_3
    //   122: bipush 10
    //   124: invokeinterface 422 2 0
    //   129: pop
    //   130: aload_2
    //   131: getfield 75	okhttp/internal/okhttp/DiskLruCache:lruEntries	Ljava/util/LinkedHashMap;
    //   134: invokevirtual 188	java/util/LinkedHashMap:values	()Ljava/util/Collection;
    //   137: invokeinterface 194 1 0
    //   142: astore_1
    //   143: aload_1
    //   144: invokeinterface 199 1 0
    //   149: ifeq +110 -> 259
    //   152: aload_1
    //   153: invokeinterface 203 1 0
    //   158: checkcast 12	okhttp/internal/okhttp/DiskLruCache$Entry
    //   161: astore 4
    //   163: aload 4
    //   165: getfield 207	okhttp/internal/okhttp/DiskLruCache$Entry:currentEditor	Lokhttp/internal/okhttp/DiskLruCache$Editor;
    //   168: ifnull +44 -> 212
    //   171: aload_3
    //   172: ldc_w 344
    //   175: invokeinterface 418 2 0
    //   180: bipush 32
    //   182: invokeinterface 422 2 0
    //   187: pop
    //   188: aload_3
    //   189: aload 4
    //   191: getfield 426	okhttp/internal/okhttp/DiskLruCache$Entry:key	Ljava/lang/String;
    //   194: invokeinterface 418 2 0
    //   199: pop
    //   200: aload_3
    //   201: bipush 10
    //   203: invokeinterface 422 2 0
    //   208: pop
    //   209: goto -66 -> 143
    //   212: aload_3
    //   213: ldc_w 331
    //   216: invokeinterface 418 2 0
    //   221: bipush 32
    //   223: invokeinterface 422 2 0
    //   228: pop
    //   229: aload_3
    //   230: aload 4
    //   232: getfield 426	okhttp/internal/okhttp/DiskLruCache$Entry:key	Ljava/lang/String;
    //   235: invokeinterface 418 2 0
    //   240: pop
    //   241: aload 4
    //   243: aload_3
    //   244: invokevirtual 430	okhttp/internal/okhttp/DiskLruCache$Entry:writeLengths	(Lokio/BufferedSink;)V
    //   247: aload_3
    //   248: bipush 10
    //   250: invokeinterface 422 2 0
    //   255: pop
    //   256: goto -113 -> 143
    //   259: aload_2
    //   260: astore_1
    //   261: aload_3
    //   262: invokeinterface 386 1 0
    //   267: aload_2
    //   268: astore_1
    //   269: aload_2
    //   270: getfield 84	okhttp/internal/okhttp/DiskLruCache:fileSystem	Lokhttp/internal/io/FileSystem;
    //   273: aload_2
    //   274: getfield 97	okhttp/internal/okhttp/DiskLruCache:journalFile	Ljava/io/File;
    //   277: invokeinterface 400 2 0
    //   282: ifeq +22 -> 304
    //   285: aload_2
    //   286: astore_1
    //   287: aload_2
    //   288: getfield 84	okhttp/internal/okhttp/DiskLruCache:fileSystem	Lokhttp/internal/io/FileSystem;
    //   291: aload_2
    //   292: getfield 97	okhttp/internal/okhttp/DiskLruCache:journalFile	Ljava/io/File;
    //   295: aload_2
    //   296: getfield 105	okhttp/internal/okhttp/DiskLruCache:journalFileBackup	Ljava/io/File;
    //   299: invokeinterface 409 3 0
    //   304: aload_2
    //   305: astore_1
    //   306: aload_2
    //   307: getfield 84	okhttp/internal/okhttp/DiskLruCache:fileSystem	Lokhttp/internal/io/FileSystem;
    //   310: aload_2
    //   311: getfield 101	okhttp/internal/okhttp/DiskLruCache:journalFileTmp	Ljava/io/File;
    //   314: aload_2
    //   315: getfield 97	okhttp/internal/okhttp/DiskLruCache:journalFile	Ljava/io/File;
    //   318: invokeinterface 409 3 0
    //   323: aload_2
    //   324: astore_1
    //   325: aload_2
    //   326: getfield 84	okhttp/internal/okhttp/DiskLruCache:fileSystem	Lokhttp/internal/io/FileSystem;
    //   329: aload_2
    //   330: getfield 105	okhttp/internal/okhttp/DiskLruCache:journalFileBackup	Ljava/io/File;
    //   333: invokeinterface 184 2 0
    //   338: aload_2
    //   339: astore_1
    //   340: aload_2
    //   341: aload_2
    //   342: invokespecial 270	okhttp/internal/okhttp/DiskLruCache:newJournalWriter	()Lokio/BufferedSink;
    //   345: putfield 272	okhttp/internal/okhttp/DiskLruCache:journalWriter	Lokio/BufferedSink;
    //   348: aload_2
    //   349: astore_1
    //   350: aload_2
    //   351: iconst_0
    //   352: putfield 468	okhttp/internal/okhttp/DiskLruCache:hasJournalErrors	Z
    //   355: aload_2
    //   356: astore_1
    //   357: aload_2
    //   358: iconst_0
    //   359: putfield 466	okhttp/internal/okhttp/DiskLruCache:readable	Z
    //   362: aload_2
    //   363: monitorexit
    //   364: return
    //   365: astore 4
    //   367: aload_2
    //   368: astore_1
    //   369: aload_3
    //   370: invokeinterface 386 1 0
    //   375: aload_2
    //   376: astore_1
    //   377: aload 4
    //   379: athrow
    //   380: astore_2
    //   381: aload_1
    //   382: monitorexit
    //   383: goto +3 -> 386
    //   386: aload_2
    //   387: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	388	0	this	DiskLruCache
    //   3	379	1	localObject	Object
    //   10	366	2	localDiskLruCache	DiskLruCache
    //   380	7	2	localThrowable1	Throwable
    //   8	362	3	localBufferedSink	BufferedSink
    //   161	81	4	localEntry	Entry
    //   365	13	4	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   51	143	365	java/lang/Throwable
    //   143	209	365	java/lang/Throwable
    //   212	256	365	java/lang/Throwable
    //   4	9	380	java/lang/Throwable
    //   17	22	380	java/lang/Throwable
    //   24	30	380	java/lang/Throwable
    //   34	51	380	java/lang/Throwable
    //   261	267	380	java/lang/Throwable
    //   269	285	380	java/lang/Throwable
    //   287	304	380	java/lang/Throwable
    //   306	323	380	java/lang/Throwable
    //   325	338	380	java/lang/Throwable
    //   340	348	380	java/lang/Throwable
    //   350	355	380	java/lang/Throwable
    //   357	362	380	java/lang/Throwable
    //   369	375	380	java/lang/Throwable
    //   377	380	380	java/lang/Throwable
  }
  
  boolean remove()
  {
    int i = redundantOpCount;
    return (i >= 2000) && (i >= lruEntries.size());
  }
  
  public boolean remove(String paramString)
  {
    try
    {
      initialize();
      checkNotClosed();
      validateKey(paramString);
      paramString = (Entry)lruEntries.get(paramString);
      if (paramString == null) {
        return false;
      }
      boolean bool = remove(paramString);
      if ((bool) && (size <= maxSize)) {
        mostRecentTrimFailed = false;
      }
      return bool;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  boolean remove(Entry paramEntry)
  {
    Object localObject = currentEditor;
    if (localObject != null) {
      ((Editor)localObject).remove();
    }
    int i = 0;
    while (i < valueCount)
    {
      fileSystem.delete(cleanFiles[i]);
      long l = size;
      localObject = lengths;
      size = (l - localObject[i]);
      localObject[i] = 0L;
      i += 1;
    }
    redundantOpCount += 1;
    journalWriter.writeUtf8("REMOVE").writeByte(32).writeUtf8(key).writeByte(10);
    lruEntries.remove(key);
    if (remove()) {
      executor.execute(cleanupRunnable);
    }
    return true;
  }
  
  void trimToSize()
  {
    while (size > maxSize) {
      remove((Entry)lruEntries.values().iterator().next());
    }
    mostRecentTrimFailed = false;
  }
  
  public final class Editor
  {
    private boolean done;
    final DiskLruCache.Entry entry;
    final boolean[] written;
    
    Editor(DiskLruCache.Entry paramEntry)
    {
      entry = paramEntry;
      if (readable) {
        this$1 = null;
      } else {
        this$1 = new boolean[valueCount];
      }
      written = DiskLruCache.this;
    }
    
    public void abort()
    {
      DiskLruCache localDiskLruCache = DiskLruCache.this;
      try
      {
        if (!done)
        {
          if (entry.currentEditor == this) {
            completeEdit(this, false);
          }
          done = true;
          return;
        }
        throw new IllegalStateException();
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    public void commit()
    {
      DiskLruCache localDiskLruCache = DiskLruCache.this;
      try
      {
        if (!done)
        {
          if (entry.currentEditor == this) {
            completeEdit(this, true);
          }
          done = true;
          return;
        }
        throw new IllegalStateException();
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    public Sink newSink(int paramInt)
    {
      DiskLruCache localDiskLruCache = DiskLruCache.this;
      try
      {
        if (!done)
        {
          if (entry.currentEditor != this)
          {
            localObject = Okio.sink();
            return localObject;
          }
          if (!entry.readable) {
            written[paramInt] = true;
          }
          Object localObject = entry.size[paramInt];
          FileSystem localFileSystem = fileSystem;
          try
          {
            localObject = localFileSystem.sink((File)localObject);
            localObject = new d.c.a(this, (Sink)localObject);
            return localObject;
          }
          catch (FileNotFoundException localFileNotFoundException)
          {
            Sink localSink = Okio.sink();
            return localSink;
          }
        }
        throw new IllegalStateException();
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    void remove()
    {
      if (entry.currentEditor == this)
      {
        int i = 0;
        for (;;)
        {
          Object localObject = DiskLruCache.this;
          if (i >= valueCount) {
            break;
          }
          localObject = fileSystem;
          File localFile = entry.size[i];
          try
          {
            ((FileSystem)localObject).delete(localFile);
          }
          catch (IOException localIOException) {}
          i += 1;
        }
        entry.currentEditor = null;
      }
    }
  }
  
  final class Entry
  {
    final File[] cleanFiles;
    DiskLruCache.Editor currentEditor;
    final String key;
    final long[] lengths;
    boolean readable;
    long sequenceNumber;
    final File[] size;
    
    Entry(String paramString)
    {
      key = paramString;
      int i = valueCount;
      lengths = new long[i];
      cleanFiles = new File[i];
      size = new File[i];
      paramString = new StringBuilder(paramString).append('.');
      int j = paramString.length();
      i = 0;
      while (i < valueCount)
      {
        paramString.append(i);
        cleanFiles[i] = new File(directory, paramString.toString());
        paramString.append(".tmp");
        size[i] = new File(directory, paramString.toString());
        paramString.setLength(j);
        i += 1;
      }
    }
    
    private IOException invalidLengths(String[] paramArrayOfString)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("unexpected journal line: ");
      localStringBuilder.append(Arrays.toString(paramArrayOfString));
      throw new IOException(localStringBuilder.toString());
    }
    
    DiskLruCache.Snapshot get()
    {
      Object localObject1;
      int i;
      if (Thread.holdsLock(DiskLruCache.this))
      {
        localObject1 = new Source[valueCount];
        Object localObject2 = (long[])lengths.clone();
        i = 0;
        for (;;)
        {
          Object localObject3;
          Object localObject4;
          if (i < valueCount)
          {
            localObject3 = fileSystem;
            localObject4 = cleanFiles[i];
          }
          try
          {
            localObject3 = ((FileSystem)localObject3).source((File)localObject4);
            localObject1[i] = localObject3;
            i += 1;
          }
          catch (FileNotFoundException localFileNotFoundException)
          {
            long l;
            i = 0;
            while ((i < valueCount) && (localObject1[i] != null))
            {
              Util.closeQuietly(localObject1[i]);
              i += 1;
            }
            localObject1 = DiskLruCache.this;
            try
            {
              ((DiskLruCache)localObject1).remove(this);
            }
            catch (IOException localIOException) {}
            return null;
          }
        }
        localObject3 = DiskLruCache.this;
        localObject4 = key;
        l = sequenceNumber;
        localObject2 = new DiskLruCache.Snapshot((DiskLruCache)localObject3, (String)localObject4, l, (Source[])localObject1, (long[])localObject2);
        return localObject2;
      }
      AssertionError localAssertionError = new AssertionError();
      throw localAssertionError;
    }
    
    void remove(String[] paramArrayOfString)
    {
      if (paramArrayOfString.length == valueCount)
      {
        int i = 0;
        for (;;)
        {
          long[] arrayOfLong;
          String str;
          if (i < paramArrayOfString.length)
          {
            arrayOfLong = lengths;
            str = paramArrayOfString[i];
          }
          try
          {
            long l = Long.parseLong(str);
            arrayOfLong[i] = l;
            i += 1;
          }
          catch (NumberFormatException localNumberFormatException)
          {
            invalidLengths(paramArrayOfString);
            throw new NullPointerException("Null throw statement replaced by Soot");
          }
        }
        return;
      }
      invalidLengths(paramArrayOfString);
      throw new NullPointerException("Null throw statement replaced by Soot");
    }
    
    void writeLengths(BufferedSink paramBufferedSink)
    {
      long[] arrayOfLong = lengths;
      int j = arrayOfLong.length;
      int i = 0;
      while (i < j)
      {
        long l = arrayOfLong[i];
        paramBufferedSink.writeByte(32).writeDecimalLong(l);
        i += 1;
      }
    }
  }
  
  public final class Snapshot
    implements Closeable
  {
    private final String key;
    private final long sequenceNumber;
    private final Source[] sources;
    
    Snapshot(String paramString, long paramLong, Source[] paramArrayOfSource, long[] paramArrayOfLong)
    {
      key = paramString;
      sequenceNumber = paramLong;
      sources = paramArrayOfSource;
    }
    
    public void close()
    {
      Source[] arrayOfSource = sources;
      int j = arrayOfSource.length;
      int i = 0;
      while (i < j)
      {
        Util.closeQuietly(arrayOfSource[i]);
        i += 1;
      }
    }
    
    public DiskLruCache.Editor edit()
    {
      return edit(key, sequenceNumber);
    }
    
    public Source getSource(int paramInt)
    {
      return sources[paramInt];
    }
  }
}
