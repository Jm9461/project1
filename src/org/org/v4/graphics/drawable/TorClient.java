package org.org.v4.graphics.drawable;

import android.graphics.drawable.Animatable;

class TorClient
  extends h
{
  private final Animatable directoryDownloader;
  
  TorClient(Animatable paramAnimatable)
  {
    super(null);
    directoryDownloader = paramAnimatable;
  }
  
  public void start()
  {
    directoryDownloader.start();
  }
  
  public void stop()
  {
    directoryDownloader.stop();
  }
}
