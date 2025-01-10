package com.squareup.picasso;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

class DeferredRequestCreator
  implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener
{
  Callback callback;
  private final RequestCreator creator;
  final WeakReference<ImageView> target;
  
  DeferredRequestCreator(RequestCreator paramRequestCreator, ImageView paramImageView, Callback paramCallback)
  {
    creator = paramRequestCreator;
    target = new WeakReference(paramImageView);
    callback = paramCallback;
    paramImageView.addOnAttachStateChangeListener(this);
    if (paramImageView.getWindowToken() != null) {
      onViewAttachedToWindow(paramImageView);
    }
  }
  
  void cancel()
  {
    creator.clearTag();
    callback = null;
    Object localObject = (ImageView)target.get();
    if (localObject == null) {
      return;
    }
    target.clear();
    ((View)localObject).removeOnAttachStateChangeListener(this);
    localObject = ((View)localObject).getViewTreeObserver();
    if (((ViewTreeObserver)localObject).isAlive()) {
      ((ViewTreeObserver)localObject).removeOnPreDrawListener(this);
    }
  }
  
  public boolean onPreDraw()
  {
    ImageView localImageView = (ImageView)target.get();
    if (localImageView == null) {
      return true;
    }
    Object localObject = localImageView.getViewTreeObserver();
    if (!((ViewTreeObserver)localObject).isAlive()) {
      return true;
    }
    int i = localImageView.getWidth();
    int j = localImageView.getHeight();
    if (i > 0)
    {
      if (j <= 0) {
        return true;
      }
      localImageView.removeOnAttachStateChangeListener(this);
      ((ViewTreeObserver)localObject).removeOnPreDrawListener(this);
      target.clear();
      localObject = creator;
      ((RequestCreator)localObject).unfit();
      ((RequestCreator)localObject).resize(i, j);
      ((RequestCreator)localObject).into(localImageView, callback);
    }
    return true;
  }
  
  public void onViewAttachedToWindow(View paramView)
  {
    paramView.getViewTreeObserver().addOnPreDrawListener(this);
  }
  
  public void onViewDetachedFromWindow(View paramView)
  {
    paramView.getViewTreeObserver().removeOnPreDrawListener(this);
  }
}
