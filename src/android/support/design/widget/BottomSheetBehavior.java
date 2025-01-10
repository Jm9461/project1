package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.v4.view.Artist;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.org.android.R.dimen;
import org.org.android.R.styleable;
import org.org.jaxen.expr.MathUtils;

public class BottomSheetBehavior<V extends View>
  extends CoordinatorLayout.c<V>
{
  private int bottom;
  private Map<View, Integer> c;
  int left;
  int mActivePointerId;
  private c mCallback;
  private final ViewDragHelper.Callback mDragCallback = new b();
  private boolean mHideable = true;
  private boolean mIgnoreEvents;
  private int mInitialY;
  private int mLastNestedScrollDy;
  int mMaxOffset;
  private float mMaximumVelocity;
  int mMinOffset;
  private boolean mNestedScrolled;
  WeakReference<View> mNestedScrollingChildRef;
  private boolean mNoCrossFade;
  private int mParentHeight;
  private int mPeekHeight;
  int mState;
  boolean mTouchingScrollingChild;
  private VelocityTracker mVelocityTracker;
  ViewDragHelper mViewDragHelper;
  WeakReference<V> mViewRef;
  int state = 4;
  boolean this$0;
  private boolean top;
  
  public BottomSheetBehavior() {}
  
  public BottomSheetBehavior(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.BottomSheetBehavior_Layout);
    TypedValue localTypedValue = paramAttributeSet.peekValue(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
    if (localTypedValue != null)
    {
      int i = data;
      if (i == -1)
      {
        onLayoutChild(i);
        break label82;
      }
    }
    onLayoutChild(paramAttributeSet.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
    label82:
    put(paramAttributeSet.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
    setState(paramAttributeSet.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
    setHideable(paramAttributeSet.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
    paramAttributeSet.recycle();
    mMaximumVelocity = ViewConfiguration.get(paramContext).getScaledMaximumFlingVelocity();
  }
  
  private void a(boolean paramBoolean)
  {
    Object localObject = mViewRef;
    if (localObject == null) {
      return;
    }
    localObject = ((View)((WeakReference)localObject).get()).getParent();
    if (!(localObject instanceof CoordinatorLayout)) {
      return;
    }
    localObject = (CoordinatorLayout)localObject;
    int j = ((ViewGroup)localObject).getChildCount();
    if ((Build.VERSION.SDK_INT >= 16) && (paramBoolean)) {
      if (c == null) {
        c = new HashMap(j);
      } else {
        return;
      }
    }
    int i = 0;
    while (i < j)
    {
      View localView = ((ViewGroup)localObject).getChildAt(i);
      if (localView != mViewRef.get()) {
        if (!paramBoolean)
        {
          Map localMap = c;
          if ((localMap != null) && (localMap.containsKey(localView))) {
            ViewCompat.add(localView, ((Integer)c.get(localView)).intValue());
          }
        }
        else
        {
          if (Build.VERSION.SDK_INT >= 16) {
            c.put(localView, Integer.valueOf(localView.getImportantForAccessibility()));
          }
          ViewCompat.add(localView, 4);
        }
      }
      i += 1;
    }
    if (!paramBoolean) {
      c = null;
    }
  }
  
  public static BottomSheetBehavior from(View paramView)
  {
    paramView = paramView.getLayoutParams();
    if ((paramView instanceof CoordinatorLayout.f))
    {
      paramView = ((CoordinatorLayout.f)paramView).getBehavior();
      if ((paramView instanceof BottomSheetBehavior)) {
        return (BottomSheetBehavior)paramView;
      }
      throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
    }
    throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
  }
  
  private int get()
  {
    if (mHideable) {
      return mMinOffset;
    }
    return 0;
  }
  
  private float getYVelocity()
  {
    VelocityTracker localVelocityTracker = mVelocityTracker;
    if (localVelocityTracker == null) {
      return 0.0F;
    }
    localVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
    return mVelocityTracker.getYVelocity(mActivePointerId);
  }
  
  private void onLayoutChild()
  {
    if (mHideable)
    {
      mMaxOffset = Math.max(mState - mParentHeight, mMinOffset);
      return;
    }
    mMaxOffset = (mState - mParentHeight);
  }
  
  private void reset()
  {
    mActivePointerId = -1;
    VelocityTracker localVelocityTracker = mVelocityTracker;
    if (localVelocityTracker != null)
    {
      localVelocityTracker.recycle();
      mVelocityTracker = null;
    }
  }
  
  void dispatchOnSlide(int paramInt)
  {
    View localView = (View)mViewRef.get();
    if (localView != null)
    {
      c localC = mCallback;
      if (localC != null)
      {
        int i = mMaxOffset;
        if (paramInt > i)
        {
          localC.onSlide(localView, (i - paramInt) / (mState - i));
          return;
        }
        localC.onSlide(localView, (i - paramInt) / (i - get()));
      }
    }
  }
  
  View findScrollingChild(View paramView)
  {
    if (ViewCompat.isNestedScrollingEnabled(paramView)) {
      return paramView;
    }
    if ((paramView instanceof ViewGroup))
    {
      paramView = (ViewGroup)paramView;
      int i = 0;
      int j = paramView.getChildCount();
      while (i < j)
      {
        View localView = findScrollingChild(paramView.getChildAt(i));
        if (localView != null) {
          return localView;
        }
        i += 1;
      }
    }
    return null;
  }
  
  public final int getState()
  {
    return state;
  }
  
  public boolean onInterceptTouchEvent(CoordinatorLayout paramCoordinatorLayout, View paramView, MotionEvent paramMotionEvent)
  {
    if (!paramView.isShown())
    {
      mIgnoreEvents = true;
      return false;
    }
    int i = paramMotionEvent.getActionMasked();
    if (i == 0) {
      reset();
    }
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
    mVelocityTracker.addMovement(paramMotionEvent);
    Object localObject2 = null;
    if (i != 0)
    {
      if ((i == 1) || (i == 3))
      {
        mTouchingScrollingChild = false;
        mActivePointerId = -1;
        if (mIgnoreEvents)
        {
          mIgnoreEvents = false;
          return false;
        }
      }
    }
    else
    {
      int j = (int)paramMotionEvent.getX();
      mInitialY = ((int)paramMotionEvent.getY());
      localObject1 = mNestedScrollingChildRef;
      if (localObject1 != null) {
        localObject1 = (View)((WeakReference)localObject1).get();
      } else {
        localObject1 = null;
      }
      if ((localObject1 != null) && (paramCoordinatorLayout.isPointInChildBounds((View)localObject1, j, mInitialY)))
      {
        mActivePointerId = paramMotionEvent.getPointerId(paramMotionEvent.getActionIndex());
        mTouchingScrollingChild = true;
      }
      boolean bool;
      if ((mActivePointerId == -1) && (!paramCoordinatorLayout.isPointInChildBounds(paramView, j, mInitialY))) {
        bool = true;
      } else {
        bool = false;
      }
      mIgnoreEvents = bool;
    }
    if (!mIgnoreEvents)
    {
      paramView = mViewDragHelper;
      if ((paramView != null) && (paramView.shouldInterceptTouchEvent(paramMotionEvent))) {
        return true;
      }
    }
    Object localObject1 = mNestedScrollingChildRef;
    paramView = localObject2;
    if (localObject1 != null) {
      paramView = (View)((WeakReference)localObject1).get();
    }
    return (i == 2) && (paramView != null) && (!mIgnoreEvents) && (state != 1) && (!paramCoordinatorLayout.isPointInChildBounds(paramView, (int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())) && (mViewDragHelper != null) && (Math.abs(mInitialY - paramMotionEvent.getY()) > mViewDragHelper.getTouchSlop());
  }
  
  public final void onLayoutChild(int paramInt)
  {
    int i = 0;
    if (paramInt == -1)
    {
      if (!top)
      {
        top = true;
        i = 1;
      }
    }
    else if ((top) || (mPeekHeight != paramInt))
    {
      top = false;
      mPeekHeight = Math.max(0, paramInt);
      mMaxOffset = (mState - paramInt);
      i = 1;
    }
    if ((i != 0) && (state == 4))
    {
      Object localObject = mViewRef;
      if (localObject != null)
      {
        localObject = (View)((WeakReference)localObject).get();
        if (localObject != null) {
          ((View)localObject).requestLayout();
        }
      }
    }
  }
  
  public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt)
  {
    if ((ViewCompat.getFitsSystemWindows(paramCoordinatorLayout)) && (!ViewCompat.getFitsSystemWindows(paramView))) {
      paramView.setFitsSystemWindows(true);
    }
    int i = paramView.getTop();
    paramCoordinatorLayout.onLayoutChild(paramView, paramInt);
    mState = paramCoordinatorLayout.getHeight();
    if (top)
    {
      if (bottom == 0) {
        bottom = paramCoordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
      }
      mParentHeight = Math.max(bottom, mState - paramCoordinatorLayout.getWidth() * 9 / 16);
    }
    else
    {
      mParentHeight = mPeekHeight;
    }
    mMinOffset = Math.max(0, mState - paramView.getHeight());
    left = (mState / 2);
    onLayoutChild();
    paramInt = state;
    if (paramInt == 3)
    {
      ViewCompat.offsetTopAndBottom(paramView, get());
    }
    else if (paramInt == 6)
    {
      ViewCompat.offsetTopAndBottom(paramView, left);
    }
    else if ((this$0) && (paramInt == 5))
    {
      ViewCompat.offsetTopAndBottom(paramView, mState);
    }
    else
    {
      paramInt = state;
      if (paramInt == 4) {
        ViewCompat.offsetTopAndBottom(paramView, mMaxOffset);
      } else if ((paramInt == 1) || (paramInt == 2)) {
        ViewCompat.offsetTopAndBottom(paramView, i - paramView.getTop());
      }
    }
    if (mViewDragHelper == null) {
      mViewDragHelper = ViewDragHelper.create(paramCoordinatorLayout, mDragCallback);
    }
    mViewRef = new WeakReference(paramView);
    mNestedScrollingChildRef = new WeakReference(findScrollingChild(paramView));
    return true;
  }
  
  public boolean onNestedPreFling(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, float paramFloat1, float paramFloat2)
  {
    return (paramView2 == mNestedScrollingChildRef.get()) && ((state != 3) || (super.onNestedPreFling(paramCoordinatorLayout, paramView1, paramView2, paramFloat1, paramFloat2)));
  }
  
  public void onNestedPreScroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, int paramInt1, int paramInt2, int[] paramArrayOfInt, int paramInt3)
  {
    if (paramInt3 == 1) {
      return;
    }
    if (paramView2 != (View)mNestedScrollingChildRef.get()) {
      return;
    }
    paramInt1 = paramView1.getTop();
    paramInt3 = paramInt1 - paramInt2;
    if (paramInt2 > 0)
    {
      if (paramInt3 < get())
      {
        paramArrayOfInt[1] = (paramInt1 - get());
        ViewCompat.offsetTopAndBottom(paramView1, -paramArrayOfInt[1]);
        setState(3);
      }
      else
      {
        paramArrayOfInt[1] = paramInt2;
        ViewCompat.offsetTopAndBottom(paramView1, -paramInt2);
        setState(1);
      }
    }
    else if ((paramInt2 < 0) && (!paramView2.canScrollVertically(-1)))
    {
      int i = mMaxOffset;
      if ((paramInt3 > i) && (!this$0))
      {
        paramArrayOfInt[1] = (paramInt1 - i);
        ViewCompat.offsetTopAndBottom(paramView1, -paramArrayOfInt[1]);
        setState(4);
      }
      else
      {
        paramArrayOfInt[1] = paramInt2;
        ViewCompat.offsetTopAndBottom(paramView1, -paramInt2);
        setState(1);
      }
    }
    dispatchOnSlide(paramView1.getTop());
    mLastNestedScrollDy = paramInt2;
    mNestedScrolled = true;
  }
  
  public void onRestoreInstanceState(c paramC)
  {
    mCallback = paramC;
  }
  
  public void onRestoreInstanceState(CoordinatorLayout paramCoordinatorLayout, View paramView, Parcelable paramParcelable)
  {
    paramParcelable = (d)paramParcelable;
    super.onRestoreInstanceState(paramCoordinatorLayout, paramView, paramParcelable.getSuperState());
    int i = proximity;
    if ((i != 1) && (i != 2))
    {
      state = i;
      return;
    }
    state = 4;
  }
  
  public Parcelable onSaveInstanceState(CoordinatorLayout paramCoordinatorLayout, View paramView)
  {
    return new d(super.onSaveInstanceState(paramCoordinatorLayout, paramView), state);
  }
  
  public boolean onStartNestedScroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, View paramView3, int paramInt1, int paramInt2)
  {
    mLastNestedScrollDy = 0;
    mNestedScrolled = false;
    return (paramInt1 & 0x2) != 0;
  }
  
  public void onStopNestedScroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, int paramInt)
  {
    if (paramView1.getTop() == get())
    {
      setState(3);
      return;
    }
    if (paramView2 == mNestedScrollingChildRef.get())
    {
      if (!mNestedScrolled) {
        return;
      }
      int i;
      if (mLastNestedScrollDy > 0)
      {
        i = get();
        paramInt = 3;
      }
      else if ((this$0) && (shouldHide(paramView1, getYVelocity())))
      {
        i = mState;
        paramInt = 5;
      }
      else if (mLastNestedScrollDy == 0)
      {
        paramInt = paramView1.getTop();
        if (mHideable)
        {
          if (Math.abs(paramInt - mMinOffset) < Math.abs(paramInt - mMaxOffset))
          {
            i = mMinOffset;
            paramInt = 3;
          }
          else
          {
            i = mMaxOffset;
            paramInt = 4;
          }
        }
        else
        {
          i = left;
          if (paramInt < i)
          {
            if (paramInt < Math.abs(paramInt - mMaxOffset))
            {
              i = 0;
              paramInt = 3;
            }
            else
            {
              i = left;
              paramInt = 6;
            }
          }
          else if (Math.abs(paramInt - i) < Math.abs(paramInt - mMaxOffset))
          {
            i = left;
            paramInt = 6;
          }
          else
          {
            paramInt = 4;
            i = mMaxOffset;
          }
        }
      }
      else
      {
        i = mMaxOffset;
        paramInt = 4;
      }
      if (mViewDragHelper.smoothSlideViewTo(paramView1, paramView1.getLeft(), i))
      {
        setState(2);
        ViewCompat.postOnAnimation(paramView1, new e(paramView1, paramInt));
      }
      else
      {
        setState(paramInt);
      }
      mNestedScrolled = false;
    }
  }
  
  public boolean onTouchEvent(CoordinatorLayout paramCoordinatorLayout, View paramView, MotionEvent paramMotionEvent)
  {
    if (!paramView.isShown()) {
      return false;
    }
    int i = paramMotionEvent.getActionMasked();
    if ((state == 1) && (i == 0)) {
      return true;
    }
    paramCoordinatorLayout = mViewDragHelper;
    if (paramCoordinatorLayout != null) {
      paramCoordinatorLayout.processTouchEvent(paramMotionEvent);
    }
    if (i == 0) {
      reset();
    }
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
    mVelocityTracker.addMovement(paramMotionEvent);
    if ((i == 2) && (!mIgnoreEvents) && (Math.abs(mInitialY - paramMotionEvent.getY()) > mViewDragHelper.getTouchSlop())) {
      mViewDragHelper.captureChildView(paramView, paramMotionEvent.getPointerId(paramMotionEvent.getActionIndex()));
    }
    return mIgnoreEvents ^ true;
  }
  
  public void put(boolean paramBoolean)
  {
    this$0 = paramBoolean;
  }
  
  public final void reset(final int paramInt)
  {
    if (paramInt == state) {
      return;
    }
    Object localObject = mViewRef;
    if (localObject == null)
    {
      if ((paramInt == 4) || (paramInt == 3) || (paramInt == 6) || ((this$0) && (paramInt == 5))) {
        state = paramInt;
      }
    }
    else
    {
      localObject = (View)((WeakReference)localObject).get();
      if (localObject == null) {
        return;
      }
      ViewParent localViewParent = ((View)localObject).getParent();
      if ((localViewParent != null) && (localViewParent.isLayoutRequested()) && (ViewCompat.setText((View)localObject)))
      {
        ((View)localObject).post(new a((View)localObject, paramInt));
        return;
      }
      setState((View)localObject, paramInt);
    }
  }
  
  public void setHideable(boolean paramBoolean)
  {
    mNoCrossFade = paramBoolean;
  }
  
  void setState(int paramInt)
  {
    if (state == paramInt) {
      return;
    }
    state = paramInt;
    if ((paramInt != 6) && (paramInt != 3))
    {
      if ((paramInt == 5) || (paramInt == 4)) {
        a(false);
      }
    }
    else {
      a(true);
    }
    View localView = (View)mViewRef.get();
    if (localView != null)
    {
      c localC = mCallback;
      if (localC != null) {
        localC.clear(localView, paramInt);
      }
    }
  }
  
  void setState(View paramView, int paramInt)
  {
    int i;
    int j;
    if (paramInt == 4)
    {
      i = mMaxOffset;
      j = paramInt;
    }
    else if (paramInt == 6)
    {
      int k = left;
      i = k;
      j = paramInt;
      if (mHideable)
      {
        i = k;
        j = paramInt;
        if (k <= mMinOffset)
        {
          j = 3;
          i = mMinOffset;
        }
      }
    }
    else if (paramInt == 3)
    {
      i = get();
      j = paramInt;
    }
    else
    {
      if ((!this$0) || (paramInt != 5)) {
        break label147;
      }
      i = mState;
      j = paramInt;
    }
    if (mViewDragHelper.smoothSlideViewTo(paramView, paramView.getLeft(), i))
    {
      setState(2);
      ViewCompat.postOnAnimation(paramView, new e(paramView, j));
      return;
    }
    setState(j);
    return;
    label147:
    paramView = new StringBuilder();
    paramView.append("Illegal state argument: ");
    paramView.append(paramInt);
    throw new IllegalArgumentException(paramView.toString());
  }
  
  public void setState(boolean paramBoolean)
  {
    if (mHideable == paramBoolean) {
      return;
    }
    mHideable = paramBoolean;
    if (mViewRef != null) {
      onLayoutChild();
    }
    int i;
    if ((mHideable) && (state == 6)) {
      i = 3;
    } else {
      i = state;
    }
    setState(i);
  }
  
  boolean shouldHide(View paramView, float paramFloat)
  {
    if (mNoCrossFade) {
      return true;
    }
    if (paramView.getTop() < mMaxOffset) {
      return false;
    }
    return Math.abs(paramView.getTop() + 0.1F * paramFloat - mMaxOffset) / mPeekHeight > 0.5F;
  }
  
  class a
    implements Runnable
  {
    a(View paramView, int paramInt) {}
    
    public void run()
    {
      setState(c, paramInt);
    }
  }
  
  class b
    extends ViewDragHelper.Callback
  {
    b() {}
    
    public int clampViewPositionHorizontal(View paramView, int paramInt1, int paramInt2)
    {
      return paramView.getLeft();
    }
    
    public int clampViewPositionVertical(View paramView, int paramInt1, int paramInt2)
    {
      int i = BottomSheetBehavior.access$getMMinOffset(BottomSheetBehavior.this);
      paramView = BottomSheetBehavior.this;
      if (this$0) {
        paramInt2 = mState;
      } else {
        paramInt2 = mMaxOffset;
      }
      return MathUtils.constrain(paramInt1, i, paramInt2);
    }
    
    public int getViewVerticalDragRange(View paramView)
    {
      paramView = BottomSheetBehavior.this;
      if (this$0) {
        return mState;
      }
      return mMaxOffset;
    }
    
    public void onViewDragStateChanged(int paramInt)
    {
      if (paramInt == 1) {
        setState(1);
      }
    }
    
    public void onViewPositionChanged(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      dispatchOnSlide(paramInt2);
    }
    
    public void onViewReleased(View paramView, float paramFloat1, float paramFloat2)
    {
      int j;
      int i;
      BottomSheetBehavior localBottomSheetBehavior;
      if (paramFloat2 < 0.0F)
      {
        if (BottomSheetBehavior.access$getMHideable(BottomSheetBehavior.this))
        {
          j = mMinOffset;
          i = 3;
        }
        else
        {
          i = paramView.getTop();
          localBottomSheetBehavior = BottomSheetBehavior.this;
          if (i > left)
          {
            j = left;
            i = 6;
          }
          else
          {
            i = 3;
            j = 0;
          }
        }
      }
      else
      {
        localBottomSheetBehavior = BottomSheetBehavior.this;
        if ((this$0) && (localBottomSheetBehavior.shouldHide(paramView, paramFloat2)) && ((paramView.getTop() > mMaxOffset) || (Math.abs(paramFloat1) < Math.abs(paramFloat2))))
        {
          j = mState;
          i = 5;
        }
        else if ((paramFloat2 != 0.0F) && (Math.abs(paramFloat1) <= Math.abs(paramFloat2)))
        {
          j = mMaxOffset;
          i = 4;
        }
        else
        {
          i = paramView.getTop();
          if (BottomSheetBehavior.access$getMHideable(BottomSheetBehavior.this))
          {
            if (Math.abs(i - mMinOffset) < Math.abs(i - mMaxOffset))
            {
              j = mMinOffset;
              i = 3;
            }
            else
            {
              j = mMaxOffset;
              i = 4;
            }
          }
          else
          {
            localBottomSheetBehavior = BottomSheetBehavior.this;
            j = left;
            if (i < j)
            {
              if (i < Math.abs(i - mMaxOffset))
              {
                j = 0;
                i = 3;
              }
              else
              {
                j = left;
                i = 6;
              }
            }
            else if (Math.abs(i - j) < Math.abs(i - mMaxOffset))
            {
              j = left;
              i = 6;
            }
            else
            {
              localBottomSheetBehavior = BottomSheetBehavior.this;
              i = 4;
              j = mMaxOffset;
            }
          }
        }
      }
      if (mViewDragHelper.settleCapturedViewAt(paramView.getLeft(), j))
      {
        setState(2);
        ViewCompat.postOnAnimation(paramView, new BottomSheetBehavior.e(BottomSheetBehavior.this, paramView, i));
        return;
      }
      setState(i);
    }
    
    public boolean tryCaptureView(View paramView, int paramInt)
    {
      Object localObject = BottomSheetBehavior.this;
      int i = state;
      if (i == 1) {
        return false;
      }
      if (mTouchingScrollingChild) {
        return false;
      }
      if ((i == 3) && (mActivePointerId == paramInt))
      {
        localObject = (View)mNestedScrollingChildRef.get();
        if ((localObject != null) && (((View)localObject).canScrollVertically(-1))) {
          return false;
        }
      }
      localObject = mViewRef;
      return (localObject != null) && (((WeakReference)localObject).get() == paramView);
    }
  }
  
  public static abstract class c
  {
    public c() {}
    
    public abstract void clear(View paramView, int paramInt);
    
    public abstract void onSlide(View paramView, float paramFloat);
  }
  
  protected static class d
    extends Artist
  {
    public static final Parcelable.Creator<d> CREATOR = new a();
    final int proximity;
    
    public d(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      proximity = paramParcel.readInt();
    }
    
    public d(Parcelable paramParcelable, int paramInt)
    {
      super();
      proximity = paramInt;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(proximity);
    }
    
    static final class a
      implements Parcelable.ClassLoaderCreator<BottomSheetBehavior.d>
    {
      a() {}
      
      public BottomSheetBehavior.d createFromParcel(Parcel paramParcel)
      {
        return new BottomSheetBehavior.d(paramParcel, null);
      }
      
      public BottomSheetBehavior.d createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
      {
        return new BottomSheetBehavior.d(paramParcel, paramClassLoader);
      }
      
      public BottomSheetBehavior.d[] newArray(int paramInt)
      {
        return new BottomSheetBehavior.d[paramInt];
      }
    }
  }
  
  private class e
    implements Runnable
  {
    private final View mLayout;
    private final int val$state;
    
    e(View paramView, int paramInt)
    {
      mLayout = paramView;
      val$state = paramInt;
    }
    
    public void run()
    {
      ViewDragHelper localViewDragHelper = mViewDragHelper;
      if ((localViewDragHelper != null) && (localViewDragHelper.continueSettling(true)))
      {
        ViewCompat.postOnAnimation(mLayout, this);
        return;
      }
      setState(val$state);
    }
  }
}
