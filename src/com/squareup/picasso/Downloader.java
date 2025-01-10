package com.squareup.picasso;

import okhttp.Request;
import okhttp.Response;

public abstract interface Downloader
{
  public abstract Response execute(Request paramRequest);
}
