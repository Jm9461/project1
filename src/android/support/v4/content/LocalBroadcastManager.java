package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public final class LocalBroadcastManager
{
  private static LocalBroadcastManager mInstance;
  private static final Object mLock = new Object();
  private final HashMap<String, ArrayList<c.c>> mActions = new HashMap();
  private final Context mAppContext;
  private final Handler mHandler;
  private final ArrayList<c.b> mPendingBroadcasts = new ArrayList();
  private final HashMap<BroadcastReceiver, ArrayList<c.c>> mReceivers = new HashMap();
  
  private LocalBroadcastManager(Context paramContext)
  {
    mAppContext = paramContext;
    mHandler = new SlidingDrawer.SlidingHandler(this, paramContext.getMainLooper());
  }
  
  public static LocalBroadcastManager getInstance(Context paramContext)
  {
    Object localObject = mLock;
    try
    {
      if (mInstance == null) {
        mInstance = new LocalBroadcastManager(paramContext.getApplicationContext());
      }
      paramContext = mInstance;
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  /* Error */
  void executePendingBroadcasts()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 38	android/support/v4/content/LocalBroadcastManager:mReceivers	Ljava/util/HashMap;
    //   4: astore 5
    //   6: aload 5
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield 45	android/support/v4/content/LocalBroadcastManager:mPendingBroadcasts	Ljava/util/ArrayList;
    //   13: invokevirtual 77	java/util/ArrayList:size	()I
    //   16: istore_1
    //   17: iload_1
    //   18: ifgt +7 -> 25
    //   21: aload 5
    //   23: monitorexit
    //   24: return
    //   25: iload_1
    //   26: anewarray 6	android/support/v4/content/LocalBroadcastManager$BroadcastRecord
    //   29: astore 4
    //   31: aload_0
    //   32: getfield 45	android/support/v4/content/LocalBroadcastManager:mPendingBroadcasts	Ljava/util/ArrayList;
    //   35: aload 4
    //   37: invokevirtual 81	java/util/ArrayList:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
    //   40: pop
    //   41: aload_0
    //   42: getfield 45	android/support/v4/content/LocalBroadcastManager:mPendingBroadcasts	Ljava/util/ArrayList;
    //   45: invokevirtual 84	java/util/ArrayList:clear	()V
    //   48: aload 5
    //   50: monitorexit
    //   51: iconst_0
    //   52: istore_1
    //   53: iload_1
    //   54: aload 4
    //   56: arraylength
    //   57: if_icmpge -57 -> 0
    //   60: aload 4
    //   62: iload_1
    //   63: aaload
    //   64: astore 5
    //   66: aload 5
    //   68: getfield 87	android/support/v4/content/LocalBroadcastManager$BroadcastRecord:receivers	Ljava/util/ArrayList;
    //   71: invokevirtual 77	java/util/ArrayList:size	()I
    //   74: istore_3
    //   75: iconst_0
    //   76: istore_2
    //   77: iload_2
    //   78: iload_3
    //   79: if_icmpge +49 -> 128
    //   82: aload 5
    //   84: getfield 87	android/support/v4/content/LocalBroadcastManager$BroadcastRecord:receivers	Ljava/util/ArrayList;
    //   87: iload_2
    //   88: invokevirtual 91	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   91: checkcast 8	android/support/v4/content/LocalBroadcastManager$ReceiverRecord
    //   94: astore 6
    //   96: aload 6
    //   98: getfield 94	android/support/v4/content/LocalBroadcastManager$ReceiverRecord:size	Z
    //   101: ifne +20 -> 121
    //   104: aload 6
    //   106: getfield 98	android/support/v4/content/LocalBroadcastManager$ReceiverRecord:receiver	Landroid/content/BroadcastReceiver;
    //   109: aload_0
    //   110: getfield 47	android/support/v4/content/LocalBroadcastManager:mAppContext	Landroid/content/Context;
    //   113: aload 5
    //   115: getfield 102	android/support/v4/content/LocalBroadcastManager$BroadcastRecord:intent	Landroid/content/Intent;
    //   118: invokevirtual 108	android/content/BroadcastReceiver:onReceive	(Landroid/content/Context;Landroid/content/Intent;)V
    //   121: iload_2
    //   122: iconst_1
    //   123: iadd
    //   124: istore_2
    //   125: goto -48 -> 77
    //   128: iload_1
    //   129: iconst_1
    //   130: iadd
    //   131: istore_1
    //   132: goto -79 -> 53
    //   135: astore 4
    //   137: aload 5
    //   139: monitorexit
    //   140: aload 4
    //   142: athrow
    //   143: astore 4
    //   145: goto -8 -> 137
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	148	0	this	LocalBroadcastManager
    //   16	116	1	i	int
    //   76	49	2	j	int
    //   74	6	3	k	int
    //   29	32	4	arrayOfBroadcastRecord	BroadcastRecord[]
    //   135	6	4	localThrowable1	Throwable
    //   143	1	4	localThrowable2	Throwable
    //   4	134	5	localHashMap	HashMap
    //   94	11	6	localReceiverRecord	ReceiverRecord
    // Exception table:
    //   from	to	target	type
    //   9	17	135	java/lang/Throwable
    //   21	24	135	java/lang/Throwable
    //   25	31	135	java/lang/Throwable
    //   31	51	143	java/lang/Throwable
    //   137	140	143	java/lang/Throwable
  }
  
  public void registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter)
  {
    HashMap localHashMap = mReceivers;
    try
    {
      ReceiverRecord localReceiverRecord = new ReceiverRecord(paramIntentFilter, paramBroadcastReceiver);
      Object localObject2 = (ArrayList)mReceivers.get(paramBroadcastReceiver);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject2 = new ArrayList(1);
        localObject1 = localObject2;
        mReceivers.put(paramBroadcastReceiver, localObject2);
      }
      ((ArrayList)localObject1).add(localReceiverRecord);
      int i = 0;
      while (i < paramIntentFilter.countActions())
      {
        localObject2 = paramIntentFilter.getAction(i);
        localObject1 = (ArrayList)mActions.get(localObject2);
        paramBroadcastReceiver = (BroadcastReceiver)localObject1;
        if (localObject1 == null)
        {
          localObject1 = new ArrayList(1);
          paramBroadcastReceiver = (BroadcastReceiver)localObject1;
          mActions.put(localObject2, localObject1);
        }
        paramBroadcastReceiver.add(localReceiverRecord);
        i += 1;
      }
      return;
    }
    catch (Throwable paramBroadcastReceiver)
    {
      throw paramBroadcastReceiver;
    }
  }
  
  public boolean sendBroadcast(Intent paramIntent)
  {
    HashMap localHashMap = mReceivers;
    for (;;)
    {
      int k;
      try
      {
        String str1 = paramIntent.getAction();
        localObject1 = paramIntent.resolveTypeIfNeeded(mAppContext.getContentResolver());
        Object localObject3 = localObject1;
        Uri localUri = paramIntent.getData();
        String str2 = paramIntent.getScheme();
        Set localSet = paramIntent.getCategories();
        if ((paramIntent.getFlags() & 0x8) != 0)
        {
          i = 1;
          if (i != 0)
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Resolving type ");
            ((StringBuilder)localObject2).append((String)localObject1);
            ((StringBuilder)localObject2).append(" scheme ");
            ((StringBuilder)localObject2).append(str2);
            ((StringBuilder)localObject2).append(" of intent ");
            ((StringBuilder)localObject2).append(paramIntent);
            Log.v("LocalBroadcastManager", ((StringBuilder)localObject2).toString());
          }
          ArrayList localArrayList = (ArrayList)mActions.get(paramIntent.getAction());
          if (localArrayList != null)
          {
            if (i == 0) {
              break label529;
            }
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Action list: ");
            ((StringBuilder)localObject1).append(localArrayList);
            Log.v("LocalBroadcastManager", ((StringBuilder)localObject1).toString());
            break label529;
            if (j >= localArrayList.size()) {
              break label625;
            }
            Object localObject4 = (ReceiverRecord)localArrayList.get(j);
            if (i != 0)
            {
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("Matching against filter ");
              ((StringBuilder)localObject1).append(filter);
              Log.v("LocalBroadcastManager", ((StringBuilder)localObject1).toString());
            }
            if (broadcasting)
            {
              if (i == 0) {
                break label537;
              }
              Log.v("LocalBroadcastManager", "  Filter's target already added");
              localObject1 = localObject2;
              break label614;
            }
            k = filter.match(str1, localObject3, str2, localUri, localSet, "LocalBroadcastManager");
            if (k < 0) {
              break label544;
            }
            if (i != 0)
            {
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("  Filter matched!  match=0x");
              ((StringBuilder)localObject1).append(Integer.toHexString(k));
              Log.v("LocalBroadcastManager", ((StringBuilder)localObject1).toString());
            }
            localObject1 = localObject2;
            if (localObject2 == null) {
              localObject1 = new ArrayList();
            }
            ((ArrayList)localObject1).add(localObject4);
            broadcasting = true;
            break label614;
            localObject4 = new StringBuilder();
            ((StringBuilder)localObject4).append("  Filter did not match: ");
            ((StringBuilder)localObject4).append((String)localObject1);
            Log.v("LocalBroadcastManager", ((StringBuilder)localObject4).toString());
            localObject1 = localObject2;
            break label614;
            if (i < ((ArrayList)localObject2).size())
            {
              getbroadcasting = false;
              i += 1;
              continue;
            }
            mPendingBroadcasts.add(new BroadcastRecord(paramIntent, (ArrayList)localObject2));
            if (!mHandler.hasMessages(1)) {
              mHandler.sendEmptyMessage(1);
            }
            return true;
          }
          return false;
        }
      }
      catch (Throwable paramIntent)
      {
        throw paramIntent;
      }
      int i = 0;
      continue;
      label529:
      Object localObject2 = null;
      int j = 0;
      continue;
      label537:
      Object localObject1 = localObject2;
      break label614;
      label544:
      localObject1 = localObject2;
      if (i != 0)
      {
        if (k != -4)
        {
          if (k != -3)
          {
            if (k != -2)
            {
              if (k != -1) {
                localObject1 = "unknown reason";
              } else {
                localObject1 = "type";
              }
            }
            else {
              localObject1 = "data";
            }
          }
          else {
            localObject1 = "action";
          }
        }
        else {
          localObject1 = "category";
        }
      }
      else
      {
        label614:
        j += 1;
        localObject2 = localObject1;
        continue;
        label625:
        if (localObject2 != null) {
          i = 0;
        }
      }
    }
  }
  
  public void unregisterReceiver(BroadcastReceiver paramBroadcastReceiver)
  {
    HashMap localHashMap = mReceivers;
    for (;;)
    {
      int i;
      int j;
      int k;
      try
      {
        ArrayList localArrayList1 = (ArrayList)mReceivers.remove(paramBroadcastReceiver);
        if (localArrayList1 == null) {
          return;
        }
        i = localArrayList1.size() - 1;
        if (i >= 0)
        {
          ReceiverRecord localReceiverRecord1 = (ReceiverRecord)localArrayList1.get(i);
          size = true;
          j = 0;
          if (j >= filter.countActions()) {
            break label206;
          }
          String str = filter.getAction(j);
          ArrayList localArrayList2 = (ArrayList)mActions.get(str);
          if (localArrayList2 == null) {
            break label199;
          }
          k = localArrayList2.size() - 1;
          if (k >= 0)
          {
            ReceiverRecord localReceiverRecord2 = (ReceiverRecord)localArrayList2.get(k);
            if (receiver == paramBroadcastReceiver)
            {
              size = true;
              localArrayList2.remove(k);
            }
          }
          else
          {
            if (localArrayList2.size() > 0) {
              break label199;
            }
            mActions.remove(str);
            break label199;
          }
        }
        else
        {
          return;
        }
      }
      catch (Throwable paramBroadcastReceiver)
      {
        throw paramBroadcastReceiver;
      }
      k -= 1;
      continue;
      label199:
      j += 1;
      continue;
      label206:
      i -= 1;
    }
  }
  
  final class BroadcastRecord
  {
    final ArrayList<c.c> receivers;
    
    BroadcastRecord(ArrayList paramArrayList)
    {
      receivers = paramArrayList;
    }
  }
  
  final class ReceiverRecord
  {
    boolean broadcasting;
    final BroadcastReceiver receiver;
    boolean size;
    
    ReceiverRecord(BroadcastReceiver paramBroadcastReceiver)
    {
      receiver = paramBroadcastReceiver;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(128);
      localStringBuilder.append("Receiver{");
      localStringBuilder.append(receiver);
      localStringBuilder.append(" filter=");
      localStringBuilder.append(LocalBroadcastManager.this);
      if (size) {
        localStringBuilder.append(" DEAD");
      }
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
  }
}
