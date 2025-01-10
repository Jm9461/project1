package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

public final class ViewPropertyAnimatorCompat
{
  Runnable mEndAction = null;
  int mOldLayerType = -1;
  Runnable mStartAction = null;
  private WeakReference<View> mView;
  
  ViewPropertyAnimatorCompat(View paramView)
  {
    mView = new WeakReference(paramView);
  }
  
  private void setListener(View paramView, ViewPropertyAnimatorListener paramViewPropertyAnimatorListener)
  {
    if (paramViewPropertyAnimatorListener != null)
    {
      paramView.animate().setListener(new ViewPropertyAnimatorCompatICS.1(this, paramViewPropertyAnimatorListener, paramView));
      return;
    }
    paramView.animate().setListener(null);
  }
  
  public ViewPropertyAnimatorCompat alpha(float paramFloat)
  {
    View localView = (View)mView.get();
    if (localView != null) {
      localView.animate().alpha(paramFloat);
    }
    return this;
  }
  
  public void cancel()
  {
    View localView = (View)mView.get();
    if (localView != null) {
      localView.animate().cancel();
    }
  }
  
  public long getDuration()
  {
    View localView = (View)mView.get();
    if (localView != null) {
      return localView.animate().getDuration();
    }
    return 0L;
  }
  
  public ViewPropertyAnimatorCompat setDuration(long paramLong)
  {
    View localView = (View)mView.get();
    if (localView != null) {
      localView.animate().setDuration(paramLong);
    }
    return this;
  }
  
  public ViewPropertyAnimatorCompat setInterpolator(Interpolator paramInterpolator)
  {
    View localView = (View)mView.get();
    if (localView != null) {
      localView.animate().setInterpolator(paramInterpolator);
    }
    return this;
  }
  
  public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener paramViewPropertyAnimatorListener)
  {
    View localView = (View)mView.get();
    if (localView != null)
    {
      if (Build.VERSION.SDK_INT >= 16)
      {
        setListener(localView, paramViewPropertyAnimatorListener);
        return this;
      }
      localView.setTag(2113929216, paramViewPropertyAnimatorListener);
      setListener(localView, new ICSViewPropertyAnimatorCompatImpl.MyVpaListener());
    }
    return this;
  }
  
  public ViewPropertyAnimatorCompat setStartDelay(long paramLong)
  {
    View localView = (View)mView.get();
    if (localView != null) {
      localView.animate().setStartDelay(paramLong);
    }
    return this;
  }
  
  public ViewPropertyAnimatorCompat setUpdateListener(ViewPropertyAnimatorUpdateListener paramViewPropertyAnimatorUpdateListener)
  {
    View localView = (View)mView.get();
    if ((localView != null) && (Build.VERSION.SDK_INT >= 19))
    {
      HoneycombMr1AnimatorCompatProvider.HoneycombValueAnimatorCompat.1 local1 = null;
      if (paramViewPropertyAnimatorUpdateListener != null) {
        local1 = new HoneycombMr1AnimatorCompatProvider.HoneycombValueAnimatorCompat.1(this, paramViewPropertyAnimatorUpdateListener, localView);
      }
      localView.animate().setUpdateListener(local1);
    }
    return this;
  }
  
  public void start()
  {
    View localView = (View)mView.get();
    if (localView != null) {
      localView.animate().start();
    }
  }
  
  public ViewPropertyAnimatorCompat translationY(float paramFloat)
  {
    View localView = (View)mView.get();
    if (localView != null) {
      localView.animate().translationY(paramFloat);
    }
    return this;
  }
  
  class ICSViewPropertyAnimatorCompatImpl$MyVpaListener
    implements ViewPropertyAnimatorListener
  {
    boolean mAnimEndCalled;
    
    ICSViewPropertyAnimatorCompatImpl$MyVpaListener() {}
    
    public void onAnimationCancel(View paramView)
    {
      Object localObject = paramView.getTag(2113929216);
      ViewPropertyAnimatorListener localViewPropertyAnimatorListener = null;
      if ((localObject instanceof ViewPropertyAnimatorListener)) {
        localViewPropertyAnimatorListener = (ViewPropertyAnimatorListener)localObject;
      }
      if (localViewPropertyAnimatorListener != null) {
        localViewPropertyAnimatorListener.onAnimationCancel(paramView);
      }
    }
    
    public void onAnimationEnd(View paramView)
    {
      int i = mOldLayerType;
      if (i > -1)
      {
        paramView.setLayerType(i, null);
        mOldLayerType = -1;
      }
      if ((Build.VERSION.SDK_INT >= 16) || (!mAnimEndCalled))
      {
        Object localObject1 = ViewPropertyAnimatorCompat.this;
        if (mEndAction != null)
        {
          localObject2 = mEndAction;
          mEndAction = null;
          ((Runnable)localObject2).run();
        }
        Object localObject2 = paramView.getTag(2113929216);
        localObject1 = null;
        if ((localObject2 instanceof ViewPropertyAnimatorListener)) {
          localObject1 = (ViewPropertyAnimatorListener)localObject2;
        }
        if (localObject1 != null) {
          ((ViewPropertyAnimatorListener)localObject1).onAnimationEnd(paramView);
        }
        mAnimEndCalled = true;
      }
    }
    
    public void onAnimationStart(View paramView)
    {
      mAnimEndCalled = false;
      if (mOldLayerType > -1) {
        paramView.setLayerType(2, null);
      }
      Object localObject1 = ViewPropertyAnimatorCompat.this;
      if (mStartAction != null)
      {
        localObject2 = mStartAction;
        mStartAction = null;
        ((Runnable)localObject2).run();
      }
      Object localObject2 = paramView.getTag(2113929216);
      localObject1 = null;
      if ((localObject2 instanceof ViewPropertyAnimatorListener)) {
        localObject1 = (ViewPropertyAnimatorListener)localObject2;
      }
      if (localObject1 != null) {
        ((ViewPropertyAnimatorListener)localObject1).onAnimationStart(paramView);
      }
    }
  }
}
