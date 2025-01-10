package com.squareup.picasso;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;

class Dispatcher
{
  boolean airplaneMode;
  final List<c> batch;
  final Cache cache;
  final Context context;
  final DispatcherThread dispatcherThread = new DispatcherThread();
  final Downloader downloader;
  final Map<Object, a> failedActions;
  final Handler handler;
  final Map<String, c> hunterMap;
  final Handler mainThreadHandler;
  final Map<Object, a> pausedActions;
  final Set<Object> pausedTags;
  final NetworkBroadcastReceiver receiver;
  final boolean scansNetworkChanges;
  final ExecutorService service;
  final Stats stats;
  
  Dispatcher(Context paramContext, ExecutorService paramExecutorService, Handler paramHandler, Downloader paramDownloader, Cache paramCache, Stats paramStats)
  {
    dispatcherThread.start();
    Utils.flushStackLocalLeaks(dispatcherThread.getLooper());
    context = paramContext;
    service = paramExecutorService;
    hunterMap = new LinkedHashMap();
    failedActions = new WeakHashMap();
    pausedActions = new WeakHashMap();
    pausedTags = new LinkedHashSet();
    handler = new DispatcherHandler(dispatcherThread.getLooper(), this);
    downloader = paramDownloader;
    mainThreadHandler = paramHandler;
    cache = paramCache;
    stats = paramStats;
    batch = new ArrayList(4);
    airplaneMode = Utils.isAirplaneModeOn(context);
    scansNetworkChanges = Utils.hasPermission(paramContext, "android.permission.ACCESS_NETWORK_STATE");
    receiver = new NetworkBroadcastReceiver();
    receiver.register();
  }
  
  private void batch(BitmapHunter paramBitmapHunter)
  {
    if (paramBitmapHunter.isCancelled()) {
      return;
    }
    Bitmap localBitmap = result;
    if (localBitmap != null) {
      localBitmap.prepareToDraw();
    }
    batch.add(paramBitmapHunter);
    if (!handler.hasMessages(7)) {
      handler.sendEmptyMessageDelayed(7, 200L);
    }
  }
  
  private void flushFailedActions()
  {
    if (!failedActions.isEmpty())
    {
      Iterator localIterator = failedActions.values().iterator();
      while (localIterator.hasNext())
      {
        Action localAction = (Action)localIterator.next();
        localIterator.remove();
        if (getPicassologgingEnabled) {
          Utils.log("Dispatcher", "replaying", localAction.getRequest().logId());
        }
        performSubmit(localAction, false);
      }
    }
  }
  
  private void logBatch(List paramList)
  {
    if (paramList != null)
    {
      if (paramList.isEmpty()) {
        return;
      }
      if (get0getPicassologgingEnabled)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        paramList = paramList.iterator();
        while (paramList.hasNext())
        {
          BitmapHunter localBitmapHunter = (BitmapHunter)paramList.next();
          if (localStringBuilder.length() > 0) {
            localStringBuilder.append(", ");
          }
          localStringBuilder.append(Utils.getLogIdsForHunter(localBitmapHunter));
        }
        Utils.log("Dispatcher", "delivered", localStringBuilder.toString());
      }
    }
  }
  
  private void markForReplay(Action paramAction)
  {
    Object localObject = paramAction.getTarget();
    if (localObject != null)
    {
      cancelled = true;
      failedActions.put(localObject, paramAction);
    }
  }
  
  private void markForReplay(BitmapHunter paramBitmapHunter)
  {
    Action localAction = paramBitmapHunter.getAction();
    if (localAction != null) {
      markForReplay(localAction);
    }
    paramBitmapHunter = paramBitmapHunter.getActions();
    if (paramBitmapHunter != null)
    {
      int i = 0;
      int j = paramBitmapHunter.size();
      while (i < j)
      {
        markForReplay((Action)paramBitmapHunter.get(i));
        i += 1;
      }
    }
  }
  
  void dispatchAirplaneModeChange(boolean paramBoolean)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  void dispatchCancel(Action paramAction)
  {
    Handler localHandler = handler;
    localHandler.sendMessage(localHandler.obtainMessage(2, paramAction));
  }
  
  void dispatchComplete(BitmapHunter paramBitmapHunter)
  {
    Handler localHandler = handler;
    localHandler.sendMessage(localHandler.obtainMessage(4, paramBitmapHunter));
  }
  
  void dispatchFailed(BitmapHunter paramBitmapHunter)
  {
    Handler localHandler = handler;
    localHandler.sendMessage(localHandler.obtainMessage(6, paramBitmapHunter));
  }
  
  void dispatchNetworkStateChange(NetworkInfo paramNetworkInfo)
  {
    Handler localHandler = handler;
    localHandler.sendMessage(localHandler.obtainMessage(9, paramNetworkInfo));
  }
  
  void dispatchRetry(BitmapHunter paramBitmapHunter)
  {
    Handler localHandler = handler;
    localHandler.sendMessageDelayed(localHandler.obtainMessage(5, paramBitmapHunter), 500L);
  }
  
  void dispatchSubmit(Action paramAction)
  {
    Handler localHandler = handler;
    localHandler.sendMessage(localHandler.obtainMessage(1, paramAction));
  }
  
  void performAirplaneModeChange(boolean paramBoolean)
  {
    airplaneMode = paramBoolean;
  }
  
  void performBatchComplete()
  {
    ArrayList localArrayList = new ArrayList(batch);
    batch.clear();
    Handler localHandler = mainThreadHandler;
    localHandler.sendMessage(localHandler.obtainMessage(8, localArrayList));
    logBatch(localArrayList);
  }
  
  void performCancel(Action paramAction)
  {
    String str = paramAction.getKey();
    BitmapHunter localBitmapHunter = (BitmapHunter)hunterMap.get(str);
    if (localBitmapHunter != null)
    {
      localBitmapHunter.detach(paramAction);
      if (localBitmapHunter.cancel())
      {
        hunterMap.remove(str);
        if (getPicassologgingEnabled) {
          Utils.log("Dispatcher", "canceled", paramAction.getRequest().logId());
        }
      }
    }
    if (pausedTags.contains(paramAction.getTag()))
    {
      pausedActions.remove(paramAction.getTarget());
      if (getPicassologgingEnabled) {
        Utils.log("Dispatcher", "canceled", paramAction.getRequest().logId(), "because paused request got canceled");
      }
    }
    paramAction = (Action)failedActions.remove(paramAction.getTarget());
    if ((paramAction != null) && (getPicassologgingEnabled)) {
      Utils.log("Dispatcher", "canceled", paramAction.getRequest().logId(), "from replaying");
    }
  }
  
  void performComplete(BitmapHunter paramBitmapHunter)
  {
    if (MemoryPolicy.shouldWriteToMemoryCache(paramBitmapHunter.getMemoryPolicy())) {
      cache.set(paramBitmapHunter.getKey(), paramBitmapHunter.getResult());
    }
    hunterMap.remove(paramBitmapHunter.getKey());
    batch(paramBitmapHunter);
    if (getPicassologgingEnabled) {
      Utils.log("Dispatcher", "batched", Utils.getLogIdsForHunter(paramBitmapHunter), "for completion");
    }
  }
  
  void performError(BitmapHunter paramBitmapHunter, boolean paramBoolean)
  {
    if (getPicassologgingEnabled)
    {
      String str2 = Utils.getLogIdsForHunter(paramBitmapHunter);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("for error");
      String str1;
      if (paramBoolean) {
        str1 = " (will replay)";
      } else {
        str1 = "";
      }
      localStringBuilder.append(str1);
      Utils.log("Dispatcher", "batched", str2, localStringBuilder.toString());
    }
    hunterMap.remove(paramBitmapHunter.getKey());
    batch(paramBitmapHunter);
  }
  
  void performNetworkStateChange(NetworkInfo paramNetworkInfo)
  {
    ExecutorService localExecutorService = service;
    if ((localExecutorService instanceof PicassoExecutorService)) {
      ((PicassoExecutorService)localExecutorService).adjustThreadCount(paramNetworkInfo);
    }
    if ((paramNetworkInfo != null) && (paramNetworkInfo.isConnected())) {
      flushFailedActions();
    }
  }
  
  void performPauseTag(Object paramObject)
  {
    if (!pausedTags.add(paramObject)) {
      return;
    }
    Iterator localIterator = hunterMap.values().iterator();
    while (localIterator.hasNext())
    {
      BitmapHunter localBitmapHunter = (BitmapHunter)localIterator.next();
      boolean bool = getPicassologgingEnabled;
      Object localObject = localBitmapHunter.getAction();
      List localList = localBitmapHunter.getActions();
      int i;
      if ((localList != null) && (!localList.isEmpty())) {
        i = 1;
      } else {
        i = 0;
      }
      if ((localObject != null) || (i != 0))
      {
        StringBuilder localStringBuilder;
        if ((localObject != null) && (((Action)localObject).getTag().equals(paramObject)))
        {
          localBitmapHunter.detach((Action)localObject);
          pausedActions.put(((Action)localObject).getTarget(), localObject);
          if (bool)
          {
            localObject = request.logId();
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("because tag '");
            localStringBuilder.append(paramObject);
            localStringBuilder.append("' was paused");
            Utils.log("Dispatcher", "paused", (String)localObject, localStringBuilder.toString());
          }
        }
        if (i != 0)
        {
          i = localList.size() - 1;
          while (i >= 0)
          {
            localObject = (Action)localList.get(i);
            if (((Action)localObject).getTag().equals(paramObject))
            {
              localBitmapHunter.detach((Action)localObject);
              pausedActions.put(((Action)localObject).getTarget(), localObject);
              if (bool)
              {
                localObject = request.logId();
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("because tag '");
                localStringBuilder.append(paramObject);
                localStringBuilder.append("' was paused");
                Utils.log("Dispatcher", "paused", (String)localObject, localStringBuilder.toString());
              }
            }
            i -= 1;
          }
        }
        if (localBitmapHunter.cancel())
        {
          localIterator.remove();
          if (bool) {
            Utils.log("Dispatcher", "canceled", Utils.getLogIdsForHunter(localBitmapHunter), "all actions paused");
          }
        }
      }
    }
  }
  
  void performResumeTag(Object paramObject)
  {
    if (!pausedTags.remove(paramObject)) {
      return;
    }
    Object localObject1 = null;
    Iterator localIterator = pausedActions.values().iterator();
    while (localIterator.hasNext())
    {
      Action localAction = (Action)localIterator.next();
      if (localAction.getTag().equals(paramObject))
      {
        Object localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new ArrayList();
        }
        ((List)localObject2).add(localAction);
        localIterator.remove();
        localObject1 = localObject2;
      }
    }
    if (localObject1 != null)
    {
      paramObject = mainThreadHandler;
      paramObject.sendMessage(paramObject.obtainMessage(13, localObject1));
    }
  }
  
  void performRetry(BitmapHunter paramBitmapHunter)
  {
    if (paramBitmapHunter.isCancelled()) {
      return;
    }
    boolean bool1 = service.isShutdown();
    boolean bool2 = false;
    if (bool1)
    {
      performError(paramBitmapHunter, false);
      return;
    }
    NetworkInfo localNetworkInfo = null;
    if (scansNetworkChanges) {
      localNetworkInfo = ((ConnectivityManager)Utils.getService(context, "connectivity")).getActiveNetworkInfo();
    }
    if (paramBitmapHunter.shouldRetry(airplaneMode, localNetworkInfo))
    {
      if (getPicassologgingEnabled) {
        Utils.log("Dispatcher", "retrying", Utils.getLogIdsForHunter(paramBitmapHunter));
      }
      if ((paramBitmapHunter.getException() instanceof Downloader.ResponseException)) {
        networkPolicy |= NO_CACHEindex;
      }
      future = service.submit(paramBitmapHunter);
      return;
    }
    bool1 = bool2;
    if (scansNetworkChanges)
    {
      bool1 = bool2;
      if (paramBitmapHunter.supportsReplay()) {
        bool1 = true;
      }
    }
    performError(paramBitmapHunter, bool1);
    if (bool1) {
      markForReplay(paramBitmapHunter);
    }
  }
  
  void performSubmit(Action paramAction)
  {
    performSubmit(paramAction, true);
  }
  
  void performSubmit(Action paramAction, boolean paramBoolean)
  {
    Object localObject;
    if (pausedTags.contains(paramAction.getTag()))
    {
      pausedActions.put(paramAction.getTarget(), paramAction);
      if (getPicassologgingEnabled)
      {
        localObject = request.logId();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("because tag '");
        localStringBuilder.append(paramAction.getTag());
        localStringBuilder.append("' is paused");
        Utils.log("Dispatcher", "paused", (String)localObject, localStringBuilder.toString());
      }
    }
    else
    {
      localObject = (BitmapHunter)hunterMap.get(paramAction.getKey());
      if (localObject != null)
      {
        ((BitmapHunter)localObject).attach(paramAction);
        return;
      }
      if (service.isShutdown())
      {
        if (getPicassologgingEnabled) {
          Utils.log("Dispatcher", "ignored", request.logId(), "because shut down");
        }
      }
      else
      {
        localObject = BitmapHunter.forRequest(paramAction.getPicasso(), this, cache, stats, paramAction);
        future = service.submit((Runnable)localObject);
        hunterMap.put(paramAction.getKey(), localObject);
        if (paramBoolean) {
          failedActions.remove(paramAction.getTarget());
        }
        if (getPicassologgingEnabled) {
          Utils.log("Dispatcher", "enqueued", request.logId());
        }
      }
    }
  }
  
  class DispatcherHandler
    extends Handler
  {
    private final Dispatcher dispatcher;
    
    DispatcherHandler(Dispatcher paramDispatcher)
    {
      super();
      dispatcher = paramDispatcher;
    }
    
    public void handleMessage(Message paramMessage)
    {
      int i = what;
      boolean bool = false;
      switch (i)
      {
      default: 
        break;
      case 3: 
      case 8: 
        Picasso.HANDLER.post(new i.a.a(this, paramMessage));
        return;
      case 12: 
        paramMessage = obj;
        dispatcher.performResumeTag(paramMessage);
        return;
      case 11: 
        paramMessage = obj;
        dispatcher.performPauseTag(paramMessage);
        return;
      case 10: 
        Dispatcher localDispatcher = dispatcher;
        if (arg1 == 1) {
          bool = true;
        }
        localDispatcher.performAirplaneModeChange(bool);
        return;
      case 9: 
        paramMessage = (NetworkInfo)obj;
        dispatcher.performNetworkStateChange(paramMessage);
        return;
      case 7: 
        dispatcher.performBatchComplete();
        return;
      case 6: 
        paramMessage = (BitmapHunter)obj;
        dispatcher.performError(paramMessage, false);
        return;
      case 5: 
        paramMessage = (BitmapHunter)obj;
        dispatcher.performRetry(paramMessage);
        return;
      case 4: 
        paramMessage = (BitmapHunter)obj;
        dispatcher.performComplete(paramMessage);
        return;
      case 2: 
        paramMessage = (Action)obj;
        dispatcher.performCancel(paramMessage);
        return;
      }
      paramMessage = (Action)obj;
      dispatcher.performSubmit(paramMessage);
    }
  }
  
  class DispatcherThread
    extends HandlerThread
  {
    DispatcherThread()
    {
      super(10);
    }
  }
  
  class NetworkBroadcastReceiver
    extends BroadcastReceiver
  {
    NetworkBroadcastReceiver() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent == null) {
        return;
      }
      String str = paramIntent.getAction();
      if ("android.intent.action.AIRPLANE_MODE".equals(str))
      {
        if (!paramIntent.hasExtra("state")) {
          return;
        }
        dispatchAirplaneModeChange(paramIntent.getBooleanExtra("state", false));
        return;
      }
      if ("android.net.conn.CONNECTIVITY_CHANGE".equals(str))
      {
        paramContext = (ConnectivityManager)Utils.getService(paramContext, "connectivity");
        dispatchNetworkStateChange(paramContext.getActiveNetworkInfo());
      }
    }
    
    void register()
    {
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.AIRPLANE_MODE");
      if (scansNetworkChanges) {
        localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
      }
      context.registerReceiver(this, localIntentFilter);
    }
  }
}
