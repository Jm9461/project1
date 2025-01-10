package android.support.design.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;

abstract class HeaderBehavior<V extends View>
  extends x<V>
{
  private int mActivePointerId = -1;
  private Runnable mFlingRunnable;
  private boolean mIsBeingDragged;
  private int mLastMotionY;
  OverScroller mScroller;
  private int mTouchSlop = -1;
  private VelocityTracker mVelocityTracker;
  
  public HeaderBehavior() {}
  
  public HeaderBehavior(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private void ensureVelocityTracker()
  {
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
  }
  
  abstract boolean canDragView(View paramView);
  
  final boolean fling(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt1, int paramInt2, float paramFloat)
  {
    Runnable localRunnable = mFlingRunnable;
    if (localRunnable != null)
    {
      paramView.removeCallbacks(localRunnable);
      mFlingRunnable = null;
    }
    if (mScroller == null) {
      mScroller = new OverScroller(paramView.getContext());
    }
    mScroller.fling(0, getTopAndBottomOffset(), 0, Math.round(paramFloat), 0, 0, paramInt1, paramInt2);
    if (mScroller.computeScrollOffset())
    {
      mFlingRunnable = new FlingRunnable(paramCoordinatorLayout, paramView);
      ViewCompat.postOnAnimation(paramView, mFlingRunnable);
      return true;
    }
    onFlingFinished(paramCoordinatorLayout, paramView);
    return false;
  }
  
  abstract int getMaxDragOffset(View paramView);
  
  abstract int getScrollRangeForDragFling(View paramView);
  
  abstract int getTopBottomOffsetForScrollingSibling();
  
  abstract void onFlingFinished(CoordinatorLayout paramCoordinatorLayout, View paramView);
  
  public boolean onInterceptTouchEvent(CoordinatorLayout paramCoordinatorLayout, View paramView, MotionEvent paramMotionEvent)
  {
    if (mTouchSlop < 0) {
      mTouchSlop = ViewConfiguration.get(paramCoordinatorLayout.getContext()).getScaledTouchSlop();
    }
    if ((paramMotionEvent.getAction() == 2) && (mIsBeingDragged)) {
      return true;
    }
    int i = paramMotionEvent.getActionMasked();
    if (i != 0)
    {
      if (i != 1) {
        if (i != 2)
        {
          if (i != 3) {
            break label231;
          }
        }
        else
        {
          i = mActivePointerId;
          if (i == -1) {
            break label231;
          }
          i = paramMotionEvent.findPointerIndex(i);
          if (i == -1) {
            break label231;
          }
          i = (int)paramMotionEvent.getY(i);
          if (Math.abs(i - mLastMotionY) <= mTouchSlop) {
            break label231;
          }
          mIsBeingDragged = true;
          mLastMotionY = i;
          break label231;
        }
      }
      mIsBeingDragged = false;
      mActivePointerId = -1;
      paramCoordinatorLayout = mVelocityTracker;
      if (paramCoordinatorLayout != null)
      {
        paramCoordinatorLayout.recycle();
        mVelocityTracker = null;
      }
    }
    else
    {
      mIsBeingDragged = false;
      i = (int)paramMotionEvent.getX();
      int j = (int)paramMotionEvent.getY();
      if ((canDragView(paramView)) && (paramCoordinatorLayout.isPointInChildBounds(paramView, i, j)))
      {
        mLastMotionY = j;
        mActivePointerId = paramMotionEvent.getPointerId(0);
        ensureVelocityTracker();
      }
    }
    label231:
    paramCoordinatorLayout = mVelocityTracker;
    if (paramCoordinatorLayout != null) {
      paramCoordinatorLayout.addMovement(paramMotionEvent);
    }
    return mIsBeingDragged;
  }
  
  public boolean onTouchEvent(CoordinatorLayout paramCoordinatorLayout, View paramView, MotionEvent paramMotionEvent)
  {
    if (mTouchSlop < 0) {
      mTouchSlop = ViewConfiguration.get(paramCoordinatorLayout.getContext()).getScaledTouchSlop();
    }
    int i = paramMotionEvent.getActionMasked();
    int j;
    if (i != 0)
    {
      if (i != 1)
      {
        if (i != 2)
        {
          if (i != 3) {
            break label322;
          }
        }
        else
        {
          i = paramMotionEvent.findPointerIndex(mActivePointerId);
          if (i == -1) {
            return false;
          }
          int k = (int)paramMotionEvent.getY(i);
          j = mLastMotionY - k;
          i = j;
          if (!mIsBeingDragged)
          {
            int m = Math.abs(j);
            int n = mTouchSlop;
            i = j;
            if (m > n)
            {
              mIsBeingDragged = true;
              if (j > 0) {
                i = j - n;
              } else {
                i = j + n;
              }
            }
          }
          if (!mIsBeingDragged) {
            break label322;
          }
          mLastMotionY = k;
          scroll(paramCoordinatorLayout, paramView, i, getMaxDragOffset(paramView), 0);
          break label322;
        }
      }
      else
      {
        VelocityTracker localVelocityTracker = mVelocityTracker;
        if (localVelocityTracker != null)
        {
          localVelocityTracker.addMovement(paramMotionEvent);
          mVelocityTracker.computeCurrentVelocity(1000);
          float f = mVelocityTracker.getYVelocity(mActivePointerId);
          fling(paramCoordinatorLayout, paramView, -getScrollRangeForDragFling(paramView), 0, f);
        }
      }
      mIsBeingDragged = false;
      mActivePointerId = -1;
      paramCoordinatorLayout = mVelocityTracker;
      if (paramCoordinatorLayout != null)
      {
        paramCoordinatorLayout.recycle();
        mVelocityTracker = null;
      }
    }
    else
    {
      i = (int)paramMotionEvent.getX();
      j = (int)paramMotionEvent.getY();
      if ((!paramCoordinatorLayout.isPointInChildBounds(paramView, i, j)) || (!canDragView(paramView))) {
        break label338;
      }
      mLastMotionY = j;
      mActivePointerId = paramMotionEvent.getPointerId(0);
      ensureVelocityTracker();
    }
    label322:
    paramCoordinatorLayout = mVelocityTracker;
    if (paramCoordinatorLayout != null)
    {
      paramCoordinatorLayout.addMovement(paramMotionEvent);
      return true;
      label338:
      return false;
    }
    return true;
  }
  
  final int scroll(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    return setHeaderTopBottomOffset(paramCoordinatorLayout, paramView, getTopBottomOffsetForScrollingSibling() - paramInt1, paramInt2, paramInt3);
  }
  
  int setHeaderTopBottomOffset(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt)
  {
    return setHeaderTopBottomOffset(paramCoordinatorLayout, paramView, paramInt, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
  
  abstract int setHeaderTopBottomOffset(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt1, int paramInt2, int paramInt3);
  
  class FlingRunnable
    implements Runnable
  {
    private final V mLayout;
    private final CoordinatorLayout mParent;
    
    FlingRunnable(CoordinatorLayout paramCoordinatorLayout, View paramView)
    {
      mParent = paramCoordinatorLayout;
      mLayout = paramView;
    }
    
    public void run()
    {
      if (mLayout != null)
      {
        Object localObject = mScroller;
        if (localObject != null)
        {
          if (((OverScroller)localObject).computeScrollOffset())
          {
            localObject = HeaderBehavior.this;
            ((HeaderBehavior)localObject).setHeaderTopBottomOffset(mParent, mLayout, mScroller.getCurrY());
            ViewCompat.postOnAnimation(mLayout, this);
            return;
          }
          onFlingFinished(mParent, mLayout);
        }
      }
    }
  }
}
