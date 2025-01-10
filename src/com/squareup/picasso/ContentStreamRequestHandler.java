package com.squareup.picasso;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import java.io.InputStream;
import okio.Okio;

class ContentStreamRequestHandler
  extends RequestHandler
{
  final Context context;
  
  ContentStreamRequestHandler(Context paramContext)
  {
    context = paramContext;
  }
  
  public boolean canHandleRequest(Request paramRequest)
  {
    return "content".equals(uri.getScheme());
  }
  
  InputStream getInputStream(Request paramRequest)
  {
    return context.getContentResolver().openInputStream(uri);
  }
  
  public RequestHandler.Result load(Request paramRequest, int paramInt)
  {
    return new RequestHandler.Result(Okio.source(getInputStream(paramRequest)), Picasso.LoadedFrom.DISK);
  }
}
