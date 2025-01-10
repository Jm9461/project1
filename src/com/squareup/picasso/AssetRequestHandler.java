package com.squareup.picasso;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import java.util.List;
import okio.Okio;

class AssetRequestHandler
  extends RequestHandler
{
  private static final int ASSET_PREFIX_LENGTH = "file:///android_asset/".length();
  private AssetManager assetManager;
  private final Context context;
  private final Object lock = new Object();
  
  AssetRequestHandler(Context paramContext)
  {
    context = paramContext;
  }
  
  static String getFilePath(Request paramRequest)
  {
    return uri.toString().substring(ASSET_PREFIX_LENGTH);
  }
  
  public boolean canHandleRequest(Request paramRequest)
  {
    paramRequest = uri;
    return ("file".equals(paramRequest.getScheme())) && (!paramRequest.getPathSegments().isEmpty()) && ("android_asset".equals(paramRequest.getPathSegments().get(0)));
  }
  
  public RequestHandler.Result load(Request paramRequest, int paramInt)
  {
    if (assetManager == null)
    {
      Object localObject = lock;
      try
      {
        if (assetManager == null) {
          assetManager = context.getAssets();
        }
      }
      catch (Throwable paramRequest)
      {
        throw paramRequest;
      }
    }
    return new RequestHandler.Result(Okio.source(assetManager.open(getFilePath(paramRequest))), Picasso.LoadedFrom.DISK);
  }
}
