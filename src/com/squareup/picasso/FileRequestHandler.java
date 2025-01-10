package com.squareup.picasso;

import android.content.Context;
import android.net.Uri;
import okio.Okio;
import org.org.adapters.ClassWriter;

class FileRequestHandler
  extends ContentStreamRequestHandler
{
  FileRequestHandler(Context paramContext)
  {
    super(paramContext);
  }
  
  static int getFileExifRotation(Uri paramUri)
  {
    return new ClassWriter(paramUri.getPath()).a("Orientation", 1);
  }
  
  public boolean canHandleRequest(Request paramRequest)
  {
    return "file".equals(uri.getScheme());
  }
  
  public RequestHandler.Result load(Request paramRequest, int paramInt)
  {
    return new RequestHandler.Result(null, Okio.source(getInputStream(paramRequest)), Picasso.LoadedFrom.DISK, getFileExifRotation(uri));
  }
}
