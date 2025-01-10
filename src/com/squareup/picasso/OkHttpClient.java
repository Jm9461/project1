package com.squareup.picasso;

import android.content.Context;
import java.io.File;
import okhttp.Map;
import okhttp.OkHttpClient.Builder;
import okhttp.OkResponseCache;
import okhttp.Request;
import okhttp.Response;

public final class OkHttpClient
  implements Downloader
{
  final OkResponseCache responseCache;
  
  public OkHttpClient(Context paramContext)
  {
    this(Utils.createDefaultCacheDir(paramContext));
  }
  
  public OkHttpClient(File paramFile)
  {
    this(paramFile, Utils.calculateDiskCacheSize(paramFile));
  }
  
  public OkHttpClient(File paramFile, long paramLong)
  {
    this(localBuilder.build());
  }
  
  public OkHttpClient(okhttp.OkHttpClient paramOkHttpClient)
  {
    responseCache = paramOkHttpClient;
    paramOkHttpClient.getCache();
  }
  
  public Response execute(Request paramRequest)
  {
    return responseCache.get(paramRequest).execute();
  }
}
