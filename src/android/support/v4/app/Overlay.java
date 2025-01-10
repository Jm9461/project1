package android.support.v4.app;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;

class Overlay
  extends AnimationSet
  implements Runnable
{
  private boolean mActive = true;
  private boolean mAdded;
  private boolean mEnabled;
  private final ViewGroup mLocale;
  private final View mName;
  
  Overlay(Animation paramAnimation, ViewGroup paramViewGroup, View paramView)
  {
    super(false);
    mLocale = paramViewGroup;
    mName = paramView;
    addAnimation(paramAnimation);
    mLocale.post(this);
  }
  
  public boolean getTransformation(long paramLong, Transformation paramTransformation)
  {
    mActive = true;
    if (mAdded) {
      return true ^ mEnabled;
    }
    if (!super.getTransformation(paramLong, paramTransformation))
    {
      mAdded = true;
      RecyclerView.Adapter.a(mLocale, this);
    }
    return true;
  }
  
  public boolean getTransformation(long paramLong, Transformation paramTransformation, float paramFloat)
  {
    mActive = true;
    if (mAdded) {
      return true ^ mEnabled;
    }
    if (!super.getTransformation(paramLong, paramTransformation, paramFloat))
    {
      mAdded = true;
      RecyclerView.Adapter.a(mLocale, this);
    }
    return true;
  }
  
  public void run()
  {
    if ((!mAdded) && (mActive))
    {
      mActive = false;
      mLocale.post(this);
      return;
    }
    mLocale.endViewTransition(mName);
    mEnabled = true;
  }
}
