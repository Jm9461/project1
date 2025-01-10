package com.squareup.picasso;

import android.net.NetworkInfo;
import android.net.Uri;
import okhttp.CacheControl;
import okhttp.CacheControl.Builder;
import okhttp.Request.Builder;
import okhttp.Response;
import okhttp.ResponseBody;

class NetworkRequestHandler
  extends RequestHandler
{
  private final Downloader downloader;
  private final Stats stats;
  
  NetworkRequestHandler(Downloader paramDownloader, Stats paramStats)
  {
    downloader = paramDownloader;
    stats = paramStats;
  }
  
  private static okhttp.Request get(Request paramRequest, int paramInt)
  {
    Object localObject = null;
    if (paramInt != 0) {
      if (NetworkPolicy.equals(paramInt))
      {
        localObject = CacheControl.FORCE_CACHE;
      }
      else
      {
        localObject = new CacheControl.Builder();
        if (!NetworkPolicy.shouldReadFromDiskCache(paramInt)) {
          ((CacheControl.Builder)localObject).noCache();
        }
        if (!NetworkPolicy.shouldWriteToDiskCache(paramInt)) {
          ((CacheControl.Builder)localObject).noStore();
        }
        localObject = ((CacheControl.Builder)localObject).build();
      }
    }
    paramRequest = new Request.Builder().url(uri.toString());
    if (localObject != null) {
      paramRequest.cacheControl((CacheControl)localObject);
    }
    return paramRequest.build();
  }
  
  public boolean canHandleRequest(Request paramRequest)
  {
    paramRequest = uri.getScheme();
    return ("http".equals(paramRequest)) || ("https".equals(paramRequest));
  }
  
  int getRetryCount()
  {
    return 2;
  }
  
  public RequestHandler.Result load(Request paramRequest, int paramInt)
  {
    Object localObject = get(paramRequest, paramInt);
    Response localResponse = downloader.execute((okhttp.Request)localObject);
    localObject = localResponse.body();
    if (localResponse.isSuccessful())
    {
      if (localResponse.cacheResponse() == null) {
        paramRequest = Picasso.LoadedFrom.NETWORK;
      } else {
        paramRequest = Picasso.LoadedFrom.DISK;
      }
      if ((paramRequest == Picasso.LoadedFrom.DISK) && (((ResponseBody)localObject).contentLength() == 0L))
      {
        ((ResponseBody)localObject).close();
        throw new Downloader.ResponseException("Received response with 0 content-length header.");
      }
      if ((paramRequest == Picasso.LoadedFrom.NETWORK) && (((ResponseBody)localObject).contentLength() > 0L)) {
        stats.dispatchDownloadFinished(((ResponseBody)localObject).contentLength());
      }
      return new RequestHandler.Result(((ResponseBody)localObject).source(), paramRequest);
    }
    ((ResponseBody)localObject).close();
    throw new Main(localResponse.code(), networkPolicy);
  }
  
  boolean shouldRetry(boolean paramBoolean, NetworkInfo paramNetworkInfo)
  {
    return (paramNetworkInfo == null) || (paramNetworkInfo.isConnected());
  }
  
  boolean supportsReplay()
  {
    return true;
  }
}
