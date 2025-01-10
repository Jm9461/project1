package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;

public class ViewDragHelper
{
  private static final Interpolator sInterpolator = new BakedBezierInterpolator();
  private int mActivePointerId = -1;
  private final Callback mCallback;
  private View mCapturedView;
  private int mDragState;
  private int[] mEdgeDragsInProgress;
  private int[] mEdgeDragsLocked;
  private int mEdgeSize;
  private int[] mInitialEdgesTouched;
  private float[] mInitialMotionX;
  private float[] mInitialMotionY;
  private float[] mLastMotionX;
  private float[] mLastMotionY;
  private float mMaxVelocity;
  private float mMinVelocity;
  private final ViewGroup mParentView;
  private int mPointersDown;
  private boolean mReleaseInProgress;
  private OverScroller mScroller;
  private final Runnable mSetIdleRunnable = new MonthByWeekFragment.2(this);
  private int mTouchSlop;
  private int mTrackingEdges;
  private VelocityTracker mVelocityTracker;
  
  private ViewDragHelper(Context paramContext, ViewGroup paramViewGroup, Callback paramCallback)
  {
    if (paramViewGroup != null)
    {
      if (paramCallback != null)
      {
        mParentView = paramViewGroup;
        mCallback = paramCallback;
        paramViewGroup = ViewConfiguration.get(paramContext);
        mEdgeSize = ((int)(20.0F * getResourcesgetDisplayMetricsdensity + 0.5F));
        mTouchSlop = paramViewGroup.getScaledTouchSlop();
        mMaxVelocity = paramViewGroup.getScaledMaximumFlingVelocity();
        mMinVelocity = paramViewGroup.getScaledMinimumFlingVelocity();
        mScroller = new OverScroller(paramContext, sInterpolator);
        return;
      }
      throw new IllegalArgumentException("Callback may not be null");
    }
    throw new IllegalArgumentException("Parent view may not be null");
  }
  
  private boolean checkNewEdgeDrag(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    paramFloat1 = Math.abs(paramFloat1);
    paramFloat2 = Math.abs(paramFloat2);
    if (((mInitialEdgesTouched[paramInt1] & paramInt2) == paramInt2) && ((mTrackingEdges & paramInt2) != 0) && ((mEdgeDragsLocked[paramInt1] & paramInt2) != paramInt2) && ((mEdgeDragsInProgress[paramInt1] & paramInt2) != paramInt2))
    {
      int i = mTouchSlop;
      if ((paramFloat1 <= i) && (paramFloat2 <= i)) {
        return false;
      }
      if ((paramFloat1 < 0.5F * paramFloat2) && (mCallback.onEdgeLock(paramInt2)))
      {
        int[] arrayOfInt = mEdgeDragsLocked;
        arrayOfInt[paramInt1] |= paramInt2;
        return false;
      }
      if (((mEdgeDragsInProgress[paramInt1] & paramInt2) == 0) && (paramFloat1 > mTouchSlop)) {
        return true;
      }
    }
    return false;
  }
  
  private boolean checkTouchSlop(View paramView, float paramFloat1, float paramFloat2)
  {
    if (paramView == null) {
      return false;
    }
    int i;
    if (mCallback.getViewHorizontalDragRange(paramView) > 0) {
      i = 1;
    } else {
      i = 0;
    }
    int j;
    if (mCallback.getViewVerticalDragRange(paramView) > 0) {
      j = 1;
    } else {
      j = 0;
    }
    if ((i != 0) && (j != 0))
    {
      i = mTouchSlop;
      if (paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2 > i * i) {
        return true;
      }
    }
    else if (i != 0)
    {
      if (Math.abs(paramFloat1) > mTouchSlop) {
        return true;
      }
    }
    else if ((j != 0) && (Math.abs(paramFloat2) > mTouchSlop))
    {
      return true;
    }
    return false;
  }
  
  private float clampMag(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    float f = Math.abs(paramFloat1);
    if (f < paramFloat2) {
      return 0.0F;
    }
    paramFloat2 = paramFloat1;
    if (f > paramFloat3)
    {
      if (paramFloat1 > 0.0F) {
        return paramFloat3;
      }
      paramFloat2 = -paramFloat3;
    }
    return paramFloat2;
  }
  
  private int clampMag(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = Math.abs(paramInt1);
    if (i < paramInt2) {
      return 0;
    }
    paramInt2 = paramInt1;
    if (i > paramInt3)
    {
      if (paramInt1 > 0) {
        return paramInt3;
      }
      paramInt2 = -paramInt3;
    }
    return paramInt2;
  }
  
  private void clearMotionHistory()
  {
    float[] arrayOfFloat = mInitialMotionX;
    if (arrayOfFloat == null) {
      return;
    }
    Arrays.fill(arrayOfFloat, 0.0F);
    Arrays.fill(mInitialMotionY, 0.0F);
    Arrays.fill(mLastMotionX, 0.0F);
    Arrays.fill(mLastMotionY, 0.0F);
    Arrays.fill(mInitialEdgesTouched, 0);
    Arrays.fill(mEdgeDragsInProgress, 0);
    Arrays.fill(mEdgeDragsLocked, 0);
    mPointersDown = 0;
  }
  
  private void clearMotionHistory(int paramInt)
  {
    if (mInitialMotionX != null)
    {
      if (!isPointerDown(paramInt)) {
        return;
      }
      mInitialMotionX[paramInt] = 0.0F;
      mInitialMotionY[paramInt] = 0.0F;
      mLastMotionX[paramInt] = 0.0F;
      mLastMotionY[paramInt] = 0.0F;
      mInitialEdgesTouched[paramInt] = 0;
      mEdgeDragsInProgress[paramInt] = 0;
      mEdgeDragsLocked[paramInt] = 0;
      mPointersDown &= 1 << paramInt;
    }
  }
  
  private int computeAxisDuration(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 == 0) {
      return 0;
    }
    int i = mParentView.getWidth();
    int j = i / 2;
    float f3 = Math.min(1.0F, Math.abs(paramInt1) / i);
    float f1 = j;
    float f2 = j;
    f3 = distanceInfluenceForSnapDuration(f3);
    paramInt2 = Math.abs(paramInt2);
    if (paramInt2 > 0) {
      paramInt1 = Math.round(Math.abs((f1 + f2 * f3) / paramInt2) * 1000.0F) * 4;
    } else {
      paramInt1 = (int)((1.0F + Math.abs(paramInt1) / paramInt3) * 256.0F);
    }
    return Math.min(paramInt1, 600);
  }
  
  private int computeSettleDuration(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt3 = clampMag(paramInt3, (int)mMinVelocity, (int)mMaxVelocity);
    paramInt4 = clampMag(paramInt4, (int)mMinVelocity, (int)mMaxVelocity);
    int i = Math.abs(paramInt1);
    int j = Math.abs(paramInt2);
    int k = Math.abs(paramInt3);
    int m = Math.abs(paramInt4);
    int n = k + m;
    int i1 = i + j;
    float f1;
    float f2;
    if (paramInt3 != 0)
    {
      f1 = k;
      f2 = n;
    }
    else
    {
      f1 = i;
      f2 = i1;
    }
    float f3 = f1 / f2;
    if (paramInt4 != 0)
    {
      f1 = m;
      f2 = n;
    }
    else
    {
      f1 = j;
      f2 = i1;
    }
    f1 /= f2;
    paramInt1 = computeAxisDuration(paramInt1, paramInt3, mCallback.getViewHorizontalDragRange(paramView));
    paramInt2 = computeAxisDuration(paramInt2, paramInt4, mCallback.getViewVerticalDragRange(paramView));
    return (int)(paramInt1 * f3 + paramInt2 * f1);
  }
  
  public static ViewDragHelper create(ViewGroup paramViewGroup, float paramFloat, Callback paramCallback)
  {
    paramViewGroup = create(paramViewGroup, paramCallback);
    mTouchSlop = ((int)(mTouchSlop * (1.0F / paramFloat)));
    return paramViewGroup;
  }
  
  public static ViewDragHelper create(ViewGroup paramViewGroup, Callback paramCallback)
  {
    return new ViewDragHelper(paramViewGroup.getContext(), paramViewGroup, paramCallback);
  }
  
  private void dispatchViewReleased(float paramFloat1, float paramFloat2)
  {
    mReleaseInProgress = true;
    mCallback.onViewReleased(mCapturedView, paramFloat1, paramFloat2);
    mReleaseInProgress = false;
    if (mDragState == 1) {
      setDragState(0);
    }
  }
  
  private float distanceInfluenceForSnapDuration(float paramFloat)
  {
    return (float)Math.sin((paramFloat - 0.5F) * 0.47123894F);
  }
  
  private void dragTo(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j = paramInt1;
    int i = paramInt2;
    int k = mCapturedView.getLeft();
    int m = mCapturedView.getTop();
    if (paramInt3 != 0)
    {
      paramInt1 = mCallback.clampViewPositionHorizontal(mCapturedView, paramInt1, paramInt3);
      j = paramInt1;
      ViewCompat.offsetLeftAndRight(mCapturedView, paramInt1 - k);
    }
    paramInt1 = i;
    if (paramInt4 != 0)
    {
      paramInt2 = mCallback.clampViewPositionVertical(mCapturedView, paramInt2, paramInt4);
      paramInt1 = paramInt2;
      ViewCompat.offsetTopAndBottom(mCapturedView, paramInt2 - m);
    }
    if ((paramInt3 != 0) || (paramInt4 != 0)) {
      mCallback.onViewPositionChanged(mCapturedView, j, paramInt1, j - k, paramInt1 - m);
    }
  }
  
  private void ensureMotionHistorySizeForId(int paramInt)
  {
    float[] arrayOfFloat1 = mInitialMotionX;
    if ((arrayOfFloat1 == null) || (arrayOfFloat1.length <= paramInt))
    {
      arrayOfFloat1 = new float[paramInt + 1];
      float[] arrayOfFloat2 = new float[paramInt + 1];
      float[] arrayOfFloat3 = new float[paramInt + 1];
      float[] arrayOfFloat4 = new float[paramInt + 1];
      int[] arrayOfInt1 = new int[paramInt + 1];
      int[] arrayOfInt2 = new int[paramInt + 1];
      int[] arrayOfInt3 = new int[paramInt + 1];
      Object localObject = mInitialMotionX;
      if (localObject != null)
      {
        System.arraycopy(localObject, 0, arrayOfFloat1, 0, localObject.length);
        localObject = mInitialMotionY;
        System.arraycopy(localObject, 0, arrayOfFloat2, 0, localObject.length);
        localObject = mLastMotionX;
        System.arraycopy(localObject, 0, arrayOfFloat3, 0, localObject.length);
        localObject = mLastMotionY;
        System.arraycopy(localObject, 0, arrayOfFloat4, 0, localObject.length);
        localObject = mInitialEdgesTouched;
        System.arraycopy(localObject, 0, arrayOfInt1, 0, localObject.length);
        localObject = mEdgeDragsInProgress;
        System.arraycopy(localObject, 0, arrayOfInt2, 0, localObject.length);
        localObject = mEdgeDragsLocked;
        System.arraycopy(localObject, 0, arrayOfInt3, 0, localObject.length);
      }
      mInitialMotionX = arrayOfFloat1;
      mInitialMotionY = arrayOfFloat2;
      mLastMotionX = arrayOfFloat3;
      mLastMotionY = arrayOfFloat4;
      mInitialEdgesTouched = arrayOfInt1;
      mEdgeDragsInProgress = arrayOfInt2;
      mEdgeDragsLocked = arrayOfInt3;
    }
  }
  
  private boolean forceSettleCapturedViewAt(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = mCapturedView.getLeft();
    int j = mCapturedView.getTop();
    paramInt1 -= i;
    paramInt2 -= j;
    if ((paramInt1 == 0) && (paramInt2 == 0))
    {
      mScroller.abortAnimation();
      setDragState(0);
      return false;
    }
    paramInt3 = computeSettleDuration(mCapturedView, paramInt1, paramInt2, paramInt3, paramInt4);
    mScroller.startScroll(i, j, paramInt1, paramInt2, paramInt3);
    setDragState(2);
    return true;
  }
  
  private int getEdgesTouched(int paramInt1, int paramInt2)
  {
    int j = 0;
    if (paramInt1 < mParentView.getLeft() + mEdgeSize) {
      j = 0x0 | 0x1;
    }
    int i = j;
    if (paramInt2 < mParentView.getTop() + mEdgeSize) {
      i = j | 0x4;
    }
    j = i;
    if (paramInt1 > mParentView.getRight() - mEdgeSize) {
      j = i | 0x2;
    }
    paramInt1 = j;
    if (paramInt2 > mParentView.getBottom() - mEdgeSize) {
      paramInt1 = j | 0x8;
    }
    return paramInt1;
  }
  
  private boolean isValidPointerForActionMove(int paramInt)
  {
    if (!isPointerDown(paramInt))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Ignoring pointerId=");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(" because ACTION_DOWN was not received ");
      localStringBuilder.append("for this pointer before ACTION_MOVE. It likely happened because ");
      localStringBuilder.append(" ViewDragHelper did not receive all the events in the event stream.");
      Log.e("ViewDragHelper", localStringBuilder.toString());
      return false;
    }
    return true;
  }
  
  private void releaseViewForPointerUp()
  {
    mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
    dispatchViewReleased(clampMag(mVelocityTracker.getXVelocity(mActivePointerId), mMinVelocity, mMaxVelocity), clampMag(mVelocityTracker.getYVelocity(mActivePointerId), mMinVelocity, mMaxVelocity));
  }
  
  private void reportNewEdgeDrags(float paramFloat1, float paramFloat2, int paramInt)
  {
    int j = 0;
    if (checkNewEdgeDrag(paramFloat1, paramFloat2, paramInt, 1)) {
      j = 0x0 | 0x1;
    }
    int i = j;
    if (checkNewEdgeDrag(paramFloat2, paramFloat1, paramInt, 4)) {
      i = j | 0x4;
    }
    j = i;
    if (checkNewEdgeDrag(paramFloat1, paramFloat2, paramInt, 2)) {
      j = i | 0x2;
    }
    i = j;
    if (checkNewEdgeDrag(paramFloat2, paramFloat1, paramInt, 8)) {
      i = j | 0x8;
    }
    if (i != 0)
    {
      int[] arrayOfInt = mEdgeDragsInProgress;
      arrayOfInt[paramInt] |= i;
      mCallback.onEdgeDragStarted(i, paramInt);
    }
  }
  
  private void saveInitialMotion(float paramFloat1, float paramFloat2, int paramInt)
  {
    ensureMotionHistorySizeForId(paramInt);
    float[] arrayOfFloat = mInitialMotionX;
    mLastMotionX[paramInt] = paramFloat1;
    arrayOfFloat[paramInt] = paramFloat1;
    arrayOfFloat = mInitialMotionY;
    mLastMotionY[paramInt] = paramFloat2;
    arrayOfFloat[paramInt] = paramFloat2;
    mInitialEdgesTouched[paramInt] = getEdgesTouched((int)paramFloat1, (int)paramFloat2);
    mPointersDown |= 1 << paramInt;
  }
  
  private void saveLastMotion(MotionEvent paramMotionEvent)
  {
    int j = paramMotionEvent.getPointerCount();
    int i = 0;
    while (i < j)
    {
      int k = paramMotionEvent.getPointerId(i);
      if (isValidPointerForActionMove(k))
      {
        float f1 = paramMotionEvent.getX(i);
        float f2 = paramMotionEvent.getY(i);
        mLastMotionX[k] = f1;
        mLastMotionY[k] = f2;
      }
      i += 1;
    }
  }
  
  public void cancel()
  {
    mActivePointerId = -1;
    clearMotionHistory();
    VelocityTracker localVelocityTracker = mVelocityTracker;
    if (localVelocityTracker != null)
    {
      localVelocityTracker.recycle();
      mVelocityTracker = null;
    }
  }
  
  public void captureChildView(View paramView, int paramInt)
  {
    if (paramView.getParent() == mParentView)
    {
      mCapturedView = paramView;
      mActivePointerId = paramInt;
      mCallback.onViewCaptured(paramView, paramInt);
      setDragState(1);
      return;
    }
    paramView = new StringBuilder();
    paramView.append("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (");
    paramView.append(mParentView);
    paramView.append(")");
    throw new IllegalArgumentException(paramView.toString());
  }
  
  public boolean checkTouchSlop(int paramInt)
  {
    int j = mInitialMotionX.length;
    int i = 0;
    while (i < j)
    {
      if (checkTouchSlop(paramInt, i)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public boolean checkTouchSlop(int paramInt1, int paramInt2)
  {
    if (!isPointerDown(paramInt2)) {
      return false;
    }
    int i;
    if ((paramInt1 & 0x1) == 1) {
      i = 1;
    } else {
      i = 0;
    }
    if ((paramInt1 & 0x2) == 2) {
      paramInt1 = 1;
    } else {
      paramInt1 = 0;
    }
    float f1 = mLastMotionX[paramInt2] - mInitialMotionX[paramInt2];
    float f2 = mLastMotionY[paramInt2] - mInitialMotionY[paramInt2];
    if ((i != 0) && (paramInt1 != 0))
    {
      paramInt1 = mTouchSlop;
      if (f1 * f1 + f2 * f2 > paramInt1 * paramInt1) {
        return true;
      }
    }
    else if (i != 0)
    {
      if (Math.abs(f1) > mTouchSlop) {
        return true;
      }
    }
    else if ((paramInt1 != 0) && (Math.abs(f2) > mTouchSlop))
    {
      return true;
    }
    return false;
  }
  
  public boolean continueSettling(boolean paramBoolean)
  {
    if (mDragState == 2)
    {
      boolean bool3 = mScroller.computeScrollOffset();
      boolean bool1 = bool3;
      int i = mScroller.getCurrX();
      int j = mScroller.getCurrY();
      int k = i - mCapturedView.getLeft();
      int m = j - mCapturedView.getTop();
      if (k != 0) {
        ViewCompat.offsetLeftAndRight(mCapturedView, k);
      }
      if (m != 0) {
        ViewCompat.offsetTopAndBottom(mCapturedView, m);
      }
      if ((k != 0) || (m != 0)) {
        mCallback.onViewPositionChanged(mCapturedView, i, j, k, m);
      }
      boolean bool2 = bool1;
      if (bool3)
      {
        bool2 = bool1;
        if (i == mScroller.getFinalX())
        {
          bool2 = bool1;
          if (j == mScroller.getFinalY())
          {
            mScroller.abortAnimation();
            bool2 = false;
          }
        }
      }
      if (!bool2) {
        if (paramBoolean) {
          mParentView.post(mSetIdleRunnable);
        } else {
          setDragState(0);
        }
      }
    }
    return mDragState == 2;
  }
  
  public View findTopChildUnder(int paramInt1, int paramInt2)
  {
    int i = mParentView.getChildCount() - 1;
    while (i >= 0)
    {
      Object localObject = mParentView;
      mCallback.getOrderedChildIndex(i);
      localObject = ((ViewGroup)localObject).getChildAt(i);
      if ((paramInt1 >= ((View)localObject).getLeft()) && (paramInt1 < ((View)localObject).getRight()) && (paramInt2 >= ((View)localObject).getTop()) && (paramInt2 < ((View)localObject).getBottom())) {
        return localObject;
      }
      i -= 1;
    }
    return null;
  }
  
  public View getCapturedView()
  {
    return mCapturedView;
  }
  
  public int getEdgeSize()
  {
    return mEdgeSize;
  }
  
  public int getTouchSlop()
  {
    return mTouchSlop;
  }
  
  public int getViewDragState()
  {
    return mDragState;
  }
  
  public boolean isCapturedViewUnder(int paramInt1, int paramInt2)
  {
    return isViewUnder(mCapturedView, paramInt1, paramInt2);
  }
  
  public boolean isPointerDown(int paramInt)
  {
    return (mPointersDown & 1 << paramInt) != 0;
  }
  
  public boolean isViewUnder(View paramView, int paramInt1, int paramInt2)
  {
    if (paramView == null) {
      return false;
    }
    return (paramInt1 >= paramView.getLeft()) && (paramInt1 < paramView.getRight()) && (paramInt2 >= paramView.getTop()) && (paramInt2 < paramView.getBottom());
  }
  
  public void processTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionMasked();
    int j = paramMotionEvent.getActionIndex();
    if (i == 0) {
      cancel();
    }
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
    mVelocityTracker.addMovement(paramMotionEvent);
    if (i != 0)
    {
      if (i != 1)
      {
        Object localObject;
        if (i != 2)
        {
          if (i != 3)
          {
            if (i != 5)
            {
              if (i != 6) {
                return;
              }
              int m = paramMotionEvent.getPointerId(j);
              if ((mDragState == 1) && (m == mActivePointerId))
              {
                k = -1;
                int n = paramMotionEvent.getPointerCount();
                i = 0;
                for (;;)
                {
                  j = k;
                  if (i >= n) {
                    break;
                  }
                  j = paramMotionEvent.getPointerId(i);
                  if (j != mActivePointerId)
                  {
                    f1 = paramMotionEvent.getX(i);
                    f2 = paramMotionEvent.getY(i);
                    localObject = findTopChildUnder((int)f1, (int)f2);
                    View localView = mCapturedView;
                    if ((localObject == localView) && (tryCaptureViewForDrag(localView, j)))
                    {
                      j = mActivePointerId;
                      break;
                    }
                  }
                  i += 1;
                }
                if (j == -1) {
                  releaseViewForPointerUp();
                }
              }
              clearMotionHistory(m);
              return;
            }
            i = paramMotionEvent.getPointerId(j);
            f1 = paramMotionEvent.getX(j);
            f2 = paramMotionEvent.getY(j);
            saveInitialMotion(f1, f2, i);
            if (mDragState == 0)
            {
              tryCaptureViewForDrag(findTopChildUnder((int)f1, (int)f2), i);
              j = mInitialEdgesTouched[i];
              k = mTrackingEdges;
              if ((j & k) != 0) {
                mCallback.onEdgeTouched(k & j, i);
              }
            }
            else if (isCapturedViewUnder((int)f1, (int)f2))
            {
              tryCaptureViewForDrag(mCapturedView, i);
            }
            return;
          }
          if (mDragState == 1) {
            dispatchViewReleased(0.0F, 0.0F);
          }
          cancel();
          return;
        }
        if (mDragState == 1)
        {
          if (!isValidPointerForActionMove(mActivePointerId)) {
            return;
          }
          i = paramMotionEvent.findPointerIndex(mActivePointerId);
          f1 = paramMotionEvent.getX(i);
          f2 = paramMotionEvent.getY(i);
          localObject = mLastMotionX;
          j = mActivePointerId;
          i = (int)(f1 - localObject[j]);
          j = (int)(f2 - mLastMotionY[j]);
          dragTo(mCapturedView.getLeft() + i, mCapturedView.getTop() + j, i, j);
          saveLastMotion(paramMotionEvent);
          return;
        }
        j = paramMotionEvent.getPointerCount();
        i = 0;
        while (i < j)
        {
          k = paramMotionEvent.getPointerId(i);
          if (isValidPointerForActionMove(k))
          {
            f1 = paramMotionEvent.getX(i);
            f2 = paramMotionEvent.getY(i);
            float f3 = f1 - mInitialMotionX[k];
            float f4 = f2 - mInitialMotionY[k];
            reportNewEdgeDrags(f3, f4, k);
            if (mDragState == 1) {
              break;
            }
            localObject = findTopChildUnder((int)f1, (int)f2);
            if ((checkTouchSlop((View)localObject, f3, f4)) && (tryCaptureViewForDrag((View)localObject, k))) {
              break;
            }
          }
          i += 1;
        }
        saveLastMotion(paramMotionEvent);
        return;
      }
      if (mDragState == 1) {
        releaseViewForPointerUp();
      }
      cancel();
      return;
    }
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    i = paramMotionEvent.getPointerId(0);
    paramMotionEvent = findTopChildUnder((int)f1, (int)f2);
    saveInitialMotion(f1, f2, i);
    tryCaptureViewForDrag(paramMotionEvent, i);
    j = mInitialEdgesTouched[i];
    int k = mTrackingEdges;
    if ((j & k) != 0) {
      mCallback.onEdgeTouched(k & j, i);
    }
  }
  
  void setDragState(int paramInt)
  {
    mParentView.removeCallbacks(mSetIdleRunnable);
    if (mDragState != paramInt)
    {
      mDragState = paramInt;
      mCallback.onViewDragStateChanged(paramInt);
      if (mDragState == 0) {
        mCapturedView = null;
      }
    }
  }
  
  public void setEdgeTrackingEnabled(int paramInt)
  {
    mTrackingEdges = paramInt;
  }
  
  public void setMinVelocity(float paramFloat)
  {
    mMinVelocity = paramFloat;
  }
  
  public boolean settleCapturedViewAt(int paramInt1, int paramInt2)
  {
    if (mReleaseInProgress) {
      return forceSettleCapturedViewAt(paramInt1, paramInt2, (int)mVelocityTracker.getXVelocity(mActivePointerId), (int)mVelocityTracker.getYVelocity(mActivePointerId));
    }
    throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
  }
  
  public boolean shouldInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionMasked();
    int j = paramMotionEvent.getActionIndex();
    if (i == 0) {
      cancel();
    }
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
    mVelocityTracker.addMovement(paramMotionEvent);
    float f1;
    float f2;
    int k;
    if (i != 0)
    {
      if (i != 1) {
        if (i != 2)
        {
          if (i != 3)
          {
            if (i != 5)
            {
              if (i != 6) {
                break label630;
              }
              clearMotionHistory(paramMotionEvent.getPointerId(j));
              break label630;
            }
            i = paramMotionEvent.getPointerId(j);
            f1 = paramMotionEvent.getX(j);
            f2 = paramMotionEvent.getY(j);
            saveInitialMotion(f1, f2, i);
            j = mDragState;
            if (j == 0)
            {
              j = mInitialEdgesTouched[i];
              k = mTrackingEdges;
              if ((j & k) != 0) {
                mCallback.onEdgeTouched(k & j, i);
              }
            }
            else if (j == 2)
            {
              paramMotionEvent = findTopChildUnder((int)f1, (int)f2);
              if (paramMotionEvent == mCapturedView) {
                tryCaptureViewForDrag(paramMotionEvent, i);
              }
              break label630;
            }
            break label630;
          }
        }
        else
        {
          if (mInitialMotionX != null)
          {
            if (mInitialMotionY == null) {
              break label630;
            }
            k = paramMotionEvent.getPointerCount();
            i = 0;
            while (i < k)
            {
              int m = paramMotionEvent.getPointerId(i);
              if (isValidPointerForActionMove(m))
              {
                f1 = paramMotionEvent.getX(i);
                f2 = paramMotionEvent.getY(i);
                float f3 = f1 - mInitialMotionX[m];
                float f4 = f2 - mInitialMotionY[m];
                View localView = findTopChildUnder((int)f1, (int)f2);
                if ((localView != null) && (checkTouchSlop(localView, f3, f4))) {
                  j = 1;
                } else {
                  j = 0;
                }
                if (j != 0)
                {
                  int n = localView.getLeft();
                  int i1 = (int)f3;
                  i1 = mCallback.clampViewPositionHorizontal(localView, i1 + n, (int)f3);
                  int i2 = localView.getTop();
                  int i3 = (int)f4;
                  i3 = mCallback.clampViewPositionVertical(localView, i3 + i2, (int)f4);
                  int i4 = mCallback.getViewHorizontalDragRange(localView);
                  int i5 = mCallback.getViewVerticalDragRange(localView);
                  if (((i4 == 0) || ((i4 > 0) && (i1 == n))) && ((i5 == 0) || ((i5 > 0) && (i3 == i2)))) {
                    break;
                  }
                }
                else
                {
                  reportNewEdgeDrags(f3, f4, m);
                  if ((mDragState == 1) || ((j != 0) && (tryCaptureViewForDrag(localView, m)))) {
                    break;
                  }
                }
              }
              i += 1;
            }
            saveLastMotion(paramMotionEvent);
            break label630;
          }
          break label630;
        }
      }
      cancel();
    }
    else
    {
      f1 = paramMotionEvent.getX();
      f2 = paramMotionEvent.getY();
      i = paramMotionEvent.getPointerId(0);
      saveInitialMotion(f1, f2, i);
      paramMotionEvent = findTopChildUnder((int)f1, (int)f2);
      if ((paramMotionEvent == mCapturedView) && (mDragState == 2)) {
        tryCaptureViewForDrag(paramMotionEvent, i);
      }
      j = mInitialEdgesTouched[i];
      k = mTrackingEdges;
      if ((j & k) != 0) {
        mCallback.onEdgeTouched(k & j, i);
      }
    }
    label630:
    return mDragState == 1;
  }
  
  public boolean smoothSlideViewTo(View paramView, int paramInt1, int paramInt2)
  {
    mCapturedView = paramView;
    mActivePointerId = -1;
    boolean bool = forceSettleCapturedViewAt(paramInt1, paramInt2, 0, 0);
    if ((!bool) && (mDragState == 0) && (mCapturedView != null)) {
      mCapturedView = null;
    }
    return bool;
  }
  
  boolean tryCaptureViewForDrag(View paramView, int paramInt)
  {
    if ((paramView == mCapturedView) && (mActivePointerId == paramInt)) {
      return true;
    }
    if ((paramView != null) && (mCallback.tryCaptureView(paramView, paramInt)))
    {
      mActivePointerId = paramInt;
      captureChildView(paramView, paramInt);
      return true;
    }
    return false;
  }
  
  public abstract class Callback
  {
    public Callback() {}
    
    public abstract int clampViewPositionHorizontal(View paramView, int paramInt1, int paramInt2);
    
    public abstract int clampViewPositionVertical(View paramView, int paramInt1, int paramInt2);
    
    public int getOrderedChildIndex(int paramInt)
    {
      return paramInt;
    }
    
    public int getViewHorizontalDragRange(View paramView)
    {
      return 0;
    }
    
    public int getViewVerticalDragRange(View paramView)
    {
      return 0;
    }
    
    public void onEdgeDragStarted(int paramInt1, int paramInt2) {}
    
    public boolean onEdgeLock(int paramInt)
    {
      return false;
    }
    
    public void onEdgeTouched(int paramInt1, int paramInt2) {}
    
    public void onViewCaptured(View paramView, int paramInt) {}
    
    public abstract void onViewDragStateChanged(int paramInt);
    
    public abstract void onViewPositionChanged(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
    
    public abstract void onViewReleased(View paramView, float paramFloat1, float paramFloat2);
    
    public abstract boolean tryCaptureView(View paramView, int paramInt);
  }
}
