package com.squareup.picasso;

public abstract interface Callback
{
  public abstract void onError(Exception paramException);
  
  public abstract void onSuccess();
}
