package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class SwipeDismissBehavior<V extends View>
  extends CoordinatorLayout.c<V>
{
  float mAlphaEndSwipeDistance = 0.5F;
  float mAlphaStartSwipeDistance = 0.0F;
  private final ViewDragHelper.Callback mDragCallback = new a();
  float mDragDismissThreshold = 0.5F;
  private boolean mIgnoreEvents;
  private float mSensitivity = 0.0F;
  private boolean mSensitivitySet;
  int mSwipeDirection = 2;
  ViewDragHelper mViewDragHelper;
  b this$0;
  
  public SwipeDismissBehavior() {}
  
  static float clamp(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return Math.min(Math.max(paramFloat1, paramFloat2), paramFloat3);
  }
  
  private void ensureViewDragHelper(ViewGroup paramViewGroup)
  {
    if (mViewDragHelper == null)
    {
      if (mSensitivitySet) {
        paramViewGroup = ViewDragHelper.create(paramViewGroup, mSensitivity, mDragCallback);
      } else {
        paramViewGroup = ViewDragHelper.create(paramViewGroup, mDragCallback);
      }
      mViewDragHelper = paramViewGroup;
    }
  }
  
  static float fraction(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return (paramFloat3 - paramFloat1) / (paramFloat2 - paramFloat1);
  }
  
  public boolean b(View paramView)
  {
    return true;
  }
  
  public boolean onInterceptTouchEvent(CoordinatorLayout paramCoordinatorLayout, View paramView, MotionEvent paramMotionEvent)
  {
    boolean bool = mIgnoreEvents;
    int i = paramMotionEvent.getActionMasked();
    if (i != 0)
    {
      if ((i == 1) || (i == 3)) {
        mIgnoreEvents = false;
      }
    }
    else
    {
      mIgnoreEvents = paramCoordinatorLayout.isPointInChildBounds(paramView, (int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
      bool = mIgnoreEvents;
    }
    if (bool)
    {
      ensureViewDragHelper(paramCoordinatorLayout);
      return mViewDragHelper.shouldInterceptTouchEvent(paramMotionEvent);
    }
    return false;
  }
  
  public boolean onTouchEvent(CoordinatorLayout paramCoordinatorLayout, View paramView, MotionEvent paramMotionEvent)
  {
    paramCoordinatorLayout = mViewDragHelper;
    if (paramCoordinatorLayout != null)
    {
      paramCoordinatorLayout.processTouchEvent(paramMotionEvent);
      return true;
    }
    return false;
  }
  
  public void setEndAlphaSwipeDistance(float paramFloat)
  {
    mAlphaEndSwipeDistance = clamp(0.0F, paramFloat, 1.0F);
  }
  
  public void setListener(int paramInt)
  {
    mSwipeDirection = paramInt;
  }
  
  public void setStartAlphaSwipeDistance(float paramFloat)
  {
    mAlphaStartSwipeDistance = clamp(0.0F, paramFloat, 1.0F);
  }
  
  class a
    extends ViewDragHelper.Callback
  {
    private int mActivePointerId = -1;
    private int mOriginalCapturedViewLeft;
    
    a() {}
    
    private boolean shouldDismiss(View paramView, float paramFloat)
    {
      int i;
      int j;
      if (paramFloat != 0.0F)
      {
        if (ViewCompat.getLayoutDirection(paramView) == 1) {
          i = 1;
        } else {
          i = 0;
        }
        j = mSwipeDirection;
        if (j == 2) {
          return true;
        }
        if (j == 0)
        {
          if (i != 0 ? paramFloat < 0.0F : paramFloat > 0.0F) {
            return true;
          }
        }
        else if (j == 1)
        {
          if (i != 0 ? paramFloat > 0.0F : paramFloat < 0.0F) {
            return true;
          }
        }
        else {
          return false;
        }
      }
      else
      {
        i = paramView.getLeft();
        j = mOriginalCapturedViewLeft;
        int k = Math.round(paramView.getWidth() * mDragDismissThreshold);
        if (Math.abs(i - j) >= k) {
          return true;
        }
      }
      return false;
    }
    
    public int clampViewPositionHorizontal(View paramView, int paramInt1, int paramInt2)
    {
      if (ViewCompat.getLayoutDirection(paramView) == 1) {
        paramInt2 = 1;
      } else {
        paramInt2 = 0;
      }
      int i = mSwipeDirection;
      if (i == 0)
      {
        if (paramInt2 != 0)
        {
          i = mOriginalCapturedViewLeft - paramView.getWidth();
          paramInt2 = mOriginalCapturedViewLeft;
        }
        else
        {
          i = mOriginalCapturedViewLeft;
          paramInt2 = mOriginalCapturedViewLeft + paramView.getWidth();
        }
      }
      else if (i == 1)
      {
        if (paramInt2 != 0)
        {
          i = mOriginalCapturedViewLeft;
          paramInt2 = mOriginalCapturedViewLeft + paramView.getWidth();
        }
        else
        {
          i = mOriginalCapturedViewLeft - paramView.getWidth();
          paramInt2 = mOriginalCapturedViewLeft;
        }
      }
      else
      {
        i = mOriginalCapturedViewLeft - paramView.getWidth();
        paramInt2 = mOriginalCapturedViewLeft + paramView.getWidth();
      }
      return SwipeDismissBehavior.min(i, paramInt1, paramInt2);
    }
    
    public int clampViewPositionVertical(View paramView, int paramInt1, int paramInt2)
    {
      return paramView.getTop();
    }
    
    public int getViewHorizontalDragRange(View paramView)
    {
      return paramView.getWidth();
    }
    
    public void onViewCaptured(View paramView, int paramInt)
    {
      mActivePointerId = paramInt;
      mOriginalCapturedViewLeft = paramView.getLeft();
      paramView = paramView.getParent();
      if (paramView != null) {
        paramView.requestDisallowInterceptTouchEvent(true);
      }
    }
    
    public void onViewDragStateChanged(int paramInt)
    {
      SwipeDismissBehavior.b localB = this$0;
      if (localB != null) {
        localB.setVisibility(paramInt);
      }
    }
    
    public void onViewPositionChanged(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      float f1 = mOriginalCapturedViewLeft + paramView.getWidth() * mAlphaStartSwipeDistance;
      float f2 = mOriginalCapturedViewLeft + paramView.getWidth() * mAlphaEndSwipeDistance;
      if (paramInt1 <= f1)
      {
        paramView.setAlpha(1.0F);
        return;
      }
      if (paramInt1 >= f2)
      {
        paramView.setAlpha(0.0F);
        return;
      }
      paramView.setAlpha(SwipeDismissBehavior.clamp(0.0F, 1.0F - SwipeDismissBehavior.fraction(f1, f2, paramInt1), 1.0F));
    }
    
    public void onViewReleased(View paramView, float paramFloat1, float paramFloat2)
    {
      mActivePointerId = -1;
      int i = paramView.getWidth();
      boolean bool = false;
      if (shouldDismiss(paramView, paramFloat1))
      {
        int j = paramView.getLeft();
        int k = mOriginalCapturedViewLeft;
        if (j < k) {
          i = k - i;
        } else {
          i = k + i;
        }
        bool = true;
      }
      else
      {
        i = mOriginalCapturedViewLeft;
      }
      if (mViewDragHelper.settleCapturedViewAt(i, paramView.getTop()))
      {
        ViewCompat.postOnAnimation(paramView, new SwipeDismissBehavior.c(SwipeDismissBehavior.this, paramView, bool));
        return;
      }
      if (bool)
      {
        SwipeDismissBehavior.b localB = this$0;
        if (localB != null) {
          localB.put(paramView);
        }
      }
    }
    
    public boolean tryCaptureView(View paramView, int paramInt)
    {
      return (mActivePointerId == -1) && (b(paramView));
    }
  }
  
  public static abstract interface b
  {
    public abstract void put(View paramView);
    
    public abstract void setVisibility(int paramInt);
  }
  
  private class c
    implements Runnable
  {
    private final View c;
    private final boolean d;
    
    c(View paramView, boolean paramBoolean)
    {
      c = paramView;
      d = paramBoolean;
    }
    
    public void run()
    {
      Object localObject = mViewDragHelper;
      if ((localObject != null) && (((ViewDragHelper)localObject).continueSettling(true)))
      {
        ViewCompat.postOnAnimation(c, this);
        return;
      }
      if (d)
      {
        localObject = this$0;
        if (localObject != null) {
          ((SwipeDismissBehavior.b)localObject).put(c);
        }
      }
    }
  }
}
