package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewPager
  extends ViewGroup
{
  private static final Comparator<f> COMPARATOR = new a();
  static final int[] LAYOUT_ATTRS = { 16842931 };
  private static final Interpolator sInterpolator = new b();
  private static final n sPositionComparator = new n();
  private int 0;
  private int mActivePointerId = -1;
  PagerAdapter mAdapter;
  private int mBottomPageBounds;
  private boolean mCalledSuper;
  private int mChildHeightMeasureSpec;
  private int mCloseEnough;
  int mCurItem;
  private int mDecorChildCount;
  private int mDefaultGutterSize;
  private int mDrawingOrder;
  private ArrayList<View> mDrawingOrderedChildren;
  private final Runnable mEndScrollRunnable = new c();
  private int mExpectedAdapterCount;
  private boolean mFakeDragging;
  private boolean mFirstLayout = true;
  private float mFirstOffset = -3.4028235E38F;
  private int mFlingDistance;
  private List<i> mGroups;
  private int mGutterSize;
  private boolean mInLayout;
  private float mInitialMotionX;
  private float mInitialMotionY;
  private j mInternalPageChangeListener;
  private boolean mIsBeingDragged;
  private boolean mIsScrollStarted;
  private boolean mIsUnableToDrag;
  private final ArrayList<f> mItems = new ArrayList();
  private float mLastMotionX;
  private float mLastMotionY;
  private float mLastOffset = Float.MAX_VALUE;
  private EdgeEffect mLeftEdge;
  private Drawable mMarginDrawable;
  private int mMaximumVelocity;
  private int mMinimumVelocity;
  private l mObserver;
  private int mOffscreenPageLimit = 1;
  private j mOnPageChangeListener;
  private List<j> mOnPageChangeListeners;
  private int mPageMargin;
  private k mPageTransformer;
  private boolean mPopulatePending;
  private Parcelable mRestoredAdapterState = null;
  private ClassLoader mRestoredClassLoader = null;
  private int mRestoredCurItem = -1;
  private EdgeEffect mRightEdge;
  private int mScrollState = 0;
  private Scroller mScroller;
  private boolean mScrollingCacheEnabled;
  private final f mTempItem = new f();
  private final Rect mTempRect = new Rect();
  private int mTopPageBounds;
  private int mTouchSlop;
  private VelocityTracker mVelocityTracker;
  
  public ViewPager(Context paramContext)
  {
    super(paramContext);
    initViewPager();
  }
  
  public ViewPager(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initViewPager();
  }
  
  private static boolean add(View paramView)
  {
    return paramView.getClass().getAnnotation(e.class) != null;
  }
  
  private void calculatePageOffsets(f paramF1, int paramInt, f paramF2)
  {
    Object localObject1 = mAdapter;
    Object localObject2 = this;
    int m = ((PagerAdapter)localObject1).getCount();
    int i = ((ViewPager)localObject2).getClientWidth();
    float f2;
    if (i > 0) {
      f2 = mPageMargin / i;
    } else {
      f2 = 0.0F;
    }
    localObject1 = localObject2;
    if (paramF2 != null)
    {
      i = position;
      j = position;
      if (i < j)
      {
        j = 0;
        f1 = offset + widthFactor + f2;
        i += 1;
        for (;;)
        {
          localObject1 = localObject2;
          if (i > position) {
            break;
          }
          localObject1 = localObject2;
          if (j >= mItems.size()) {
            break;
          }
          for (paramF2 = (f)mItems.get(j);; paramF2 = (f)mItems.get(j))
          {
            k = i;
            f3 = f1;
            localObject1 = localObject2;
            if (i <= position) {
              break;
            }
            k = i;
            f3 = f1;
            localObject1 = localObject2;
            if (j >= mItems.size() - 1) {
              break;
            }
            j += 1;
          }
          while (k < position)
          {
            localObject2 = mAdapter;
            f3 += ((PagerAdapter)localObject2).getPageWidth(k) + f2;
            k += 1;
          }
          offset = f3;
          f1 = f3 + (widthFactor + f2);
          i = k + 1;
          localObject2 = localObject1;
        }
      }
      localObject1 = localObject2;
      if (i > j)
      {
        j = mItems.size() - 1;
        f1 = offset;
        i -= 1;
        for (;;)
        {
          localObject1 = localObject2;
          if (i < position) {
            break;
          }
          localObject1 = localObject2;
          if (j < 0) {
            break;
          }
          for (paramF2 = (f)mItems.get(j);; paramF2 = (f)mItems.get(j))
          {
            k = i;
            f3 = f1;
            localObject1 = localObject2;
            if (i >= position) {
              break;
            }
            k = i;
            f3 = f1;
            localObject1 = localObject2;
            if (j <= 0) {
              break;
            }
            j -= 1;
          }
          while (k > position)
          {
            localObject2 = mAdapter;
            f3 -= ((PagerAdapter)localObject2).getPageWidth(k) + f2;
            k -= 1;
          }
          f1 = f3 - (widthFactor + f2);
          offset = f1;
          i = k - 1;
          localObject2 = localObject1;
        }
      }
    }
    int k = mItems.size();
    float f3 = offset;
    int j = position;
    i = j - 1;
    if (j == 0) {
      f1 = offset;
    } else {
      f1 = -3.4028235E38F;
    }
    mFirstOffset = f1;
    if (position == m - 1) {
      f1 = offset + widthFactor - 1.0F;
    } else {
      f1 = Float.MAX_VALUE;
    }
    mLastOffset = f1;
    j = paramInt - 1;
    float f1 = f3;
    while (j >= 0)
    {
      paramF2 = (f)mItems.get(j);
      int n;
      for (;;)
      {
        n = position;
        if (i <= n) {
          break;
        }
        localObject2 = mAdapter;
        f1 -= ((PagerAdapter)localObject2).getPageWidth(i) + f2;
        i -= 1;
      }
      f1 -= widthFactor + f2;
      offset = f1;
      if (n == 0) {
        mFirstOffset = f1;
      }
      j -= 1;
      i -= 1;
    }
    f1 = offset + widthFactor + f2;
    j = position + 1;
    i = paramInt + 1;
    paramF1 = (f)localObject1;
    paramInt = j;
    while (i < k)
    {
      paramF2 = (f)mItems.get(i);
      for (;;)
      {
        j = position;
        if (paramInt >= j) {
          break;
        }
        localObject1 = mAdapter;
        f1 += ((PagerAdapter)localObject1).getPageWidth(paramInt) + f2;
        paramInt += 1;
      }
      if (j == m - 1) {
        mLastOffset = (widthFactor + f1 - 1.0F);
      }
      offset = f1;
      f1 += widthFactor + f2;
      i += 1;
      paramInt += 1;
    }
  }
  
  private void completeScroll(boolean paramBoolean)
  {
    if (mScrollState == 2) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0)
    {
      setScrollingCacheEnabled(false);
      if ((true ^ mScroller.isFinished()))
      {
        mScroller.abortAnimation();
        j = getScrollX();
        k = getScrollY();
        int m = mScroller.getCurrX();
        int n = mScroller.getCurrY();
        if ((j != m) || (k != n))
        {
          scrollTo(m, n);
          if (m != j) {
            pageScrolled(m);
          }
        }
      }
    }
    mPopulatePending = false;
    int k = 0;
    int j = i;
    int i = k;
    while (i < mItems.size())
    {
      f localF = (f)mItems.get(i);
      if (scrolling)
      {
        j = 1;
        scrolling = false;
      }
      i += 1;
    }
    if (j != 0)
    {
      if (paramBoolean)
      {
        ViewCompat.postOnAnimation(this, mEndScrollRunnable);
        return;
      }
      mEndScrollRunnable.run();
    }
  }
  
  private int determineTargetPage(int paramInt1, float paramFloat, int paramInt2, int paramInt3)
  {
    if ((Math.abs(paramInt3) > mFlingDistance) && (Math.abs(paramInt2) > mMinimumVelocity))
    {
      if (paramInt2 <= 0) {
        paramInt1 += 1;
      }
    }
    else
    {
      float f;
      if (paramInt1 >= mCurItem) {
        f = 0.4F;
      } else {
        f = 0.6F;
      }
      paramInt1 += (int)(paramFloat + f);
    }
    paramInt2 = paramInt1;
    if (mItems.size() > 0)
    {
      f localF = (f)mItems.get(0);
      Object localObject = mItems;
      localObject = (f)((ArrayList)localObject).get(((ArrayList)localObject).size() - 1);
      paramInt2 = Math.max(position, Math.min(paramInt1, position));
    }
    return paramInt2;
  }
  
  private void dispatchOnPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    Object localObject = mOnPageChangeListener;
    if (localObject != null) {
      ((j)localObject).onPageScrolled(paramInt1, paramFloat, paramInt2);
    }
    localObject = mOnPageChangeListeners;
    if (localObject != null)
    {
      int i = 0;
      int j = ((List)localObject).size();
      while (i < j)
      {
        localObject = (j)mOnPageChangeListeners.get(i);
        if (localObject != null) {
          ((j)localObject).onPageScrolled(paramInt1, paramFloat, paramInt2);
        }
        i += 1;
      }
    }
    localObject = mInternalPageChangeListener;
    if (localObject != null) {
      ((j)localObject).onPageScrolled(paramInt1, paramFloat, paramInt2);
    }
  }
  
  private void dispatchOnPageSelected(int paramInt)
  {
    Object localObject = mOnPageChangeListener;
    if (localObject != null) {
      ((j)localObject).onPageSelected(paramInt);
    }
    localObject = mOnPageChangeListeners;
    if (localObject != null)
    {
      int i = 0;
      int j = ((List)localObject).size();
      while (i < j)
      {
        localObject = (j)mOnPageChangeListeners.get(i);
        if (localObject != null) {
          ((j)localObject).onPageSelected(paramInt);
        }
        i += 1;
      }
    }
    localObject = mInternalPageChangeListener;
    if (localObject != null) {
      ((j)localObject).onPageSelected(paramInt);
    }
  }
  
  private void dispatchOnScrollStateChanged(int paramInt)
  {
    Object localObject = mOnPageChangeListener;
    if (localObject != null) {
      ((j)localObject).onPageScrollStateChanged(paramInt);
    }
    localObject = mOnPageChangeListeners;
    if (localObject != null)
    {
      int i = 0;
      int j = ((List)localObject).size();
      while (i < j)
      {
        localObject = (j)mOnPageChangeListeners.get(i);
        if (localObject != null) {
          ((j)localObject).onPageScrollStateChanged(paramInt);
        }
        i += 1;
      }
    }
    localObject = mInternalPageChangeListener;
    if (localObject != null) {
      ((j)localObject).onPageScrollStateChanged(paramInt);
    }
  }
  
  private void enableLayers(boolean paramBoolean)
  {
    int k = getChildCount();
    int i = 0;
    while (i < k)
    {
      int j;
      if (paramBoolean) {
        j = 0;
      } else {
        j = 0;
      }
      getChildAt(i).setLayerType(j, null);
      i += 1;
    }
  }
  
  private void endDrag()
  {
    mIsBeingDragged = false;
    mIsUnableToDrag = false;
    VelocityTracker localVelocityTracker = mVelocityTracker;
    if (localVelocityTracker != null)
    {
      localVelocityTracker.recycle();
      mVelocityTracker = null;
    }
  }
  
  private Rect getChildRectInPagerCoordinates(Rect paramRect, View paramView)
  {
    Rect localRect = paramRect;
    if (paramRect == null) {
      localRect = new Rect();
    }
    if (paramView == null)
    {
      localRect.set(0, 0, 0, 0);
      return localRect;
    }
    left = paramView.getLeft();
    right = paramView.getRight();
    top = paramView.getTop();
    bottom = paramView.getBottom();
    for (paramRect = paramView.getParent(); ((paramRect instanceof ViewGroup)) && (paramRect != this); paramRect = paramRect.getParent())
    {
      paramRect = (ViewGroup)paramRect;
      left += paramRect.getLeft();
      right += paramRect.getRight();
      top += paramRect.getTop();
      bottom += paramRect.getBottom();
    }
    return localRect;
  }
  
  private int getClientWidth()
  {
    return getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
  }
  
  private f infoForCurrentScrollPosition()
  {
    int i = getClientWidth();
    float f2 = 0.0F;
    float f1;
    if (i > 0) {
      f1 = getScrollX() / i;
    } else {
      f1 = 0.0F;
    }
    if (i > 0) {
      f2 = mPageMargin / i;
    }
    int k = -1;
    float f3 = 0.0F;
    float f4 = 0.0F;
    int j = 1;
    Object localObject = null;
    i = 0;
    while (i < mItems.size())
    {
      f localF2 = (f)mItems.get(i);
      int m = i;
      f localF1 = localF2;
      if (j == 0)
      {
        m = i;
        localF1 = localF2;
        if (position != k + 1)
        {
          localF1 = mTempItem;
          offset = (f3 + f4 + f2);
          position = (k + 1);
          widthFactor = mAdapter.getPageWidth(position);
          m = i - 1;
        }
      }
      f3 = offset;
      f4 = widthFactor;
      if ((j == 0) && (f1 < f3)) {
        return localObject;
      }
      if (f1 >= f4 + f3 + f2)
      {
        if (m == mItems.size() - 1) {
          return localF1;
        }
        j = 0;
        k = position;
        f4 = widthFactor;
        i = m + 1;
        localObject = localF1;
      }
      else
      {
        return localF1;
      }
    }
    return localObject;
  }
  
  private boolean isGutterDrag(float paramFloat1, float paramFloat2)
  {
    return ((paramFloat1 < mGutterSize) && (paramFloat2 > 0.0F)) || ((paramFloat1 > getWidth() - mGutterSize) && (paramFloat2 < 0.0F));
  }
  
  private void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionIndex();
    if (paramMotionEvent.getPointerId(i) == mActivePointerId)
    {
      if (i == 0) {
        i = 1;
      } else {
        i = 0;
      }
      mLastMotionX = paramMotionEvent.getX(i);
      mActivePointerId = paramMotionEvent.getPointerId(i);
      paramMotionEvent = mVelocityTracker;
      if (paramMotionEvent != null) {
        paramMotionEvent.clear();
      }
    }
  }
  
  private boolean onTouchEvent()
  {
    mActivePointerId = -1;
    endDrag();
    mLeftEdge.onRelease();
    mRightEdge.onRelease();
    return (mLeftEdge.isFinished()) || (mRightEdge.isFinished());
  }
  
  private boolean pageScrolled(int paramInt)
  {
    if (mItems.size() == 0)
    {
      if (mFirstLayout) {
        return false;
      }
      mCalledSuper = false;
      onPageScrolled(0, 0.0F, 0);
      if (mCalledSuper) {
        return false;
      }
      throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }
    f localF = infoForCurrentScrollPosition();
    int j = getClientWidth();
    int k = mPageMargin;
    float f = k / j;
    int i = position;
    f = (paramInt / j - offset) / (widthFactor + f);
    paramInt = (int)((j + k) * f);
    mCalledSuper = false;
    onPageScrolled(i, f, paramInt);
    if (mCalledSuper) {
      return true;
    }
    throw new IllegalStateException("onPageScrolled did not call superclass implementation");
  }
  
  private boolean performDrag(float paramFloat)
  {
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool1 = false;
    float f1 = mLastMotionX;
    mLastMotionX = paramFloat;
    float f2 = getScrollX() + (f1 - paramFloat);
    int k = getClientWidth();
    paramFloat = k * mFirstOffset;
    f1 = k * mLastOffset;
    int i = 1;
    int j = 1;
    f localF = (f)mItems.get(0);
    Object localObject = mItems;
    localObject = (f)((ArrayList)localObject).get(((ArrayList)localObject).size() - 1);
    if (position != 0)
    {
      i = 0;
      paramFloat = offset * k;
    }
    if (position != mAdapter.getCount() - 1)
    {
      j = 0;
      f1 = offset * k;
    }
    if (f2 < paramFloat)
    {
      if (i != 0)
      {
        mLeftEdge.onPull(Math.abs(paramFloat - f2) / k);
        bool1 = true;
      }
    }
    else
    {
      bool1 = bool3;
      paramFloat = f2;
      if (f2 > f1)
      {
        bool1 = bool2;
        if (j != 0)
        {
          mRightEdge.onPull(Math.abs(f2 - f1) / k);
          bool1 = true;
        }
        paramFloat = f1;
      }
    }
    mLastMotionX += paramFloat - (int)paramFloat;
    scrollTo((int)paramFloat, getScrollY());
    pageScrolled((int)paramFloat);
    return bool1;
  }
  
  private void recomputeScrollPosition(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    float f;
    if ((paramInt2 > 0) && (!mItems.isEmpty()))
    {
      if (!mScroller.isFinished())
      {
        mScroller.setFinalX(getCurrentItem() * getClientWidth());
        return;
      }
      int i = getPaddingLeft();
      int j = getPaddingRight();
      int k = getPaddingLeft();
      int m = getPaddingRight();
      f = getScrollX() / (paramInt2 - k - m + paramInt4);
      scrollTo((int)((paramInt1 - i - j + paramInt3) * f), getScrollY());
      return;
    }
    f localF = infoForPosition(mCurItem);
    if (localF != null) {
      f = Math.min(offset, mLastOffset);
    } else {
      f = 0.0F;
    }
    paramInt1 = (int)((paramInt1 - getPaddingLeft() - getPaddingRight()) * f);
    if (paramInt1 != getScrollX())
    {
      completeScroll(false);
      scrollTo(paramInt1, getScrollY());
    }
  }
  
  private void requestParentDisallowInterceptTouchEvent(boolean paramBoolean)
  {
    ViewParent localViewParent = getParent();
    if (localViewParent != null) {
      localViewParent.requestDisallowInterceptTouchEvent(paramBoolean);
    }
  }
  
  private void scrollToItem(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2)
  {
    f localF = infoForPosition(paramInt1);
    int i = 0;
    if (localF != null) {
      i = (int)(getClientWidth() * Math.max(mFirstOffset, Math.min(offset, mLastOffset)));
    }
    if (paramBoolean1)
    {
      smoothScrollTo(i, 0, paramInt2);
      if (paramBoolean2) {
        dispatchOnPageSelected(paramInt1);
      }
    }
    else
    {
      if (paramBoolean2) {
        dispatchOnPageSelected(paramInt1);
      }
      completeScroll(false);
      scrollTo(i, 0);
      pageScrolled(i);
    }
  }
  
  private void setAdapter()
  {
    int j;
    for (int i = 0; i < getChildCount(); i = j + 1)
    {
      j = i;
      if (!getChildAtgetLayoutParamsisDecor)
      {
        removeViewAt(i);
        j = i - 1;
      }
    }
  }
  
  private void setScrollingCacheEnabled(boolean paramBoolean)
  {
    if (mScrollingCacheEnabled != paramBoolean) {
      mScrollingCacheEnabled = paramBoolean;
    }
  }
  
  private void sortChildDrawingOrder()
  {
    if (mDrawingOrder != 0)
    {
      Object localObject = mDrawingOrderedChildren;
      if (localObject == null) {
        mDrawingOrderedChildren = new ArrayList();
      } else {
        ((ArrayList)localObject).clear();
      }
      int j = getChildCount();
      int i = 0;
      while (i < j)
      {
        localObject = getChildAt(i);
        mDrawingOrderedChildren.add(localObject);
        i += 1;
      }
      Collections.sort(mDrawingOrderedChildren, sPositionComparator);
    }
  }
  
  public void a(j paramJ)
  {
    List localList = mOnPageChangeListeners;
    if (localList != null) {
      localList.remove(paramJ);
    }
  }
  
  public void addFocusables(ArrayList paramArrayList, int paramInt1, int paramInt2)
  {
    int j = paramArrayList.size();
    int k = getDescendantFocusability();
    if (k != 393216)
    {
      int i = 0;
      while (i < getChildCount())
      {
        View localView = getChildAt(i);
        if (localView.getVisibility() == 0)
        {
          f localF = infoForChild(localView);
          if ((localF != null) && (position == mCurItem)) {
            localView.addFocusables(paramArrayList, paramInt1, paramInt2);
          }
        }
        i += 1;
      }
    }
    if ((k != 262144) || (j == paramArrayList.size()))
    {
      if (!isFocusable()) {
        return;
      }
      if (((paramInt2 & 0x1) == 1) && (isInTouchMode()) && (!isFocusableInTouchMode())) {
        return;
      }
      if (paramArrayList != null) {
        paramArrayList.add(this);
      }
    }
  }
  
  f addNewItem(int paramInt1, int paramInt2)
  {
    f localF = new f();
    position = paramInt1;
    object = mAdapter.instantiateItem(this, paramInt1);
    widthFactor = mAdapter.getPageWidth(paramInt1);
    if ((paramInt2 >= 0) && (paramInt2 < mItems.size()))
    {
      mItems.add(paramInt2, localF);
      return localF;
    }
    mItems.add(localF);
    return localF;
  }
  
  public void addOnPageChangeListener(j paramJ)
  {
    if (mOnPageChangeListeners == null) {
      mOnPageChangeListeners = new ArrayList();
    }
    mOnPageChangeListeners.add(paramJ);
  }
  
  public void addTouchables(ArrayList paramArrayList)
  {
    int i = 0;
    while (i < getChildCount())
    {
      View localView = getChildAt(i);
      if (localView.getVisibility() == 0)
      {
        f localF = infoForChild(localView);
        if ((localF != null) && (position == mCurItem)) {
          localView.addTouchables(paramArrayList);
        }
      }
      i += 1;
    }
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    ViewGroup.LayoutParams localLayoutParams = paramLayoutParams;
    if (!checkLayoutParams(paramLayoutParams)) {
      localLayoutParams = generateLayoutParams(paramLayoutParams);
    }
    paramLayoutParams = (g)localLayoutParams;
    isDecor |= add(paramView);
    if (mInLayout)
    {
      if ((paramLayoutParams != null) && (isDecor)) {
        throw new IllegalStateException("Cannot add pager decor view during layout");
      }
      needsMeasure = true;
      addViewInLayout(paramView, paramInt, localLayoutParams);
      return;
    }
    super.addView(paramView, paramInt, localLayoutParams);
  }
  
  public boolean arrowScroll(int paramInt)
  {
    View localView = findFocus();
    Object localObject2 = localView;
    Object localObject1;
    int j;
    int i;
    if (localView == this)
    {
      localObject1 = null;
    }
    else
    {
      localObject1 = localObject2;
      if (localView != null)
      {
        j = 0;
        for (localObject1 = localView.getParent();; localObject1 = ((ViewParent)localObject1).getParent())
        {
          i = j;
          if (!(localObject1 instanceof ViewGroup)) {
            break;
          }
          if (localObject1 == this)
          {
            i = 1;
            break;
          }
        }
        localObject1 = localObject2;
        if (i == 0)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(localView.getClass().getSimpleName());
          for (localObject1 = localView.getParent(); (localObject1 instanceof ViewGroup); localObject1 = ((ViewParent)localObject1).getParent())
          {
            ((StringBuilder)localObject2).append(" => ");
            ((StringBuilder)localObject2).append(localObject1.getClass().getSimpleName());
          }
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("arrowScroll tried to find focus based on non-child current focused view ");
          ((StringBuilder)localObject1).append(((StringBuilder)localObject2).toString());
          Log.e("ViewPager", ((StringBuilder)localObject1).toString());
          localObject1 = null;
        }
      }
    }
    boolean bool2 = false;
    boolean bool1 = false;
    localObject2 = FocusFinder.getInstance().findNextFocus(this, (View)localObject1, paramInt);
    if ((localObject2 != null) && (localObject2 != localObject1)) {
      if (paramInt == 17)
      {
        i = getChildRectInPagerCoordinatesmTempRect, (View)localObject2).left;
        j = getChildRectInPagerCoordinatesmTempRect, (View)localObject1).left;
        if ((localObject1 != null) && (i >= j)) {
          bool1 = pageLeft();
        } else {
          bool1 = ((View)localObject2).requestFocus();
        }
      }
    }
    for (;;)
    {
      break;
      if (paramInt == 66)
      {
        i = getChildRectInPagerCoordinatesmTempRect, (View)localObject2).left;
        j = getChildRectInPagerCoordinatesmTempRect, (View)localObject1).left;
        if ((localObject1 != null) && (i <= j)) {
          bool1 = pageRight();
        } else {
          bool1 = ((View)localObject2).requestFocus();
        }
        break;
        if ((paramInt != 17) && (paramInt != 1))
        {
          if (paramInt != 66)
          {
            bool1 = bool2;
            if (paramInt != 2) {
              break;
            }
          }
          else
          {
            bool1 = pageRight();
          }
        }
        else {
          bool1 = pageLeft();
        }
      }
    }
    if (bool1) {
      playSoundEffect(SoundEffectConstants.getContantForFocusDirection(paramInt));
    }
    return bool1;
  }
  
  protected boolean canScroll(View paramView, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramView instanceof ViewGroup))
    {
      ViewGroup localViewGroup = (ViewGroup)paramView;
      int j = paramView.getScrollX();
      int k = paramView.getScrollY();
      int i = localViewGroup.getChildCount() - 1;
      while (i >= 0)
      {
        View localView = localViewGroup.getChildAt(i);
        if ((paramInt2 + j >= localView.getLeft()) && (paramInt2 + j < localView.getRight()) && (paramInt3 + k >= localView.getTop()) && (paramInt3 + k < localView.getBottom()) && (canScroll(localView, true, paramInt1, paramInt2 + j - localView.getLeft(), paramInt3 + k - localView.getTop()))) {
          return true;
        }
        i -= 1;
      }
    }
    return (paramBoolean) && (paramView.canScrollHorizontally(-paramInt1));
  }
  
  public boolean canScrollHorizontally(int paramInt)
  {
    if (mAdapter == null) {
      return false;
    }
    int i = getClientWidth();
    int j = getScrollX();
    if (paramInt < 0)
    {
      if (j > (int)(i * mFirstOffset)) {
        return true;
      }
    }
    else if ((paramInt > 0) && (j < (int)(i * mLastOffset))) {
      return true;
    }
    return false;
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return ((paramLayoutParams instanceof g)) && (super.checkLayoutParams(paramLayoutParams));
  }
  
  public void computeScroll()
  {
    mIsScrollStarted = true;
    if ((!mScroller.isFinished()) && (mScroller.computeScrollOffset()))
    {
      int i = getScrollX();
      int j = getScrollY();
      int k = mScroller.getCurrX();
      int m = mScroller.getCurrY();
      if ((i != k) || (j != m))
      {
        scrollTo(k, m);
        if (!pageScrolled(k))
        {
          mScroller.abortAnimation();
          scrollTo(0, m);
        }
      }
      ViewCompat.postInvalidateOnAnimation(this);
      return;
    }
    completeScroll(true);
  }
  
  void dataSetChanged()
  {
    int i4 = mAdapter.getCount();
    mExpectedAdapterCount = i4;
    int i;
    if ((mItems.size() < mOffscreenPageLimit * 2 + 1) && (mItems.size() < i4)) {
      i = 1;
    } else {
      i = 0;
    }
    int j = mCurItem;
    int k = 0;
    int n = 0;
    Object localObject;
    while (n < mItems.size())
    {
      localObject = (f)mItems.get(n);
      int i3 = mAdapter.getItemPosition(object);
      int i1;
      int m;
      int i2;
      if (i3 == -1)
      {
        i1 = n;
        m = j;
        i2 = k;
      }
      else if (i3 == -2)
      {
        mItems.remove(n);
        i3 = n - 1;
        n = k;
        if (k == 0)
        {
          mAdapter.destroyItem(this);
          n = 1;
        }
        mAdapter.destroyItem(this, position, object);
        i = 1;
        k = mCurItem;
        i1 = i3;
        m = j;
        i2 = n;
        if (k == position)
        {
          m = Math.max(0, Math.min(k, i4 - 1));
          i = 1;
          i1 = i3;
          i2 = n;
        }
      }
      else
      {
        int i5 = position;
        i1 = n;
        m = j;
        i2 = k;
        if (i5 != i3)
        {
          if (i5 == mCurItem) {
            j = i3;
          }
          position = i3;
          i = 1;
          i2 = k;
          m = j;
          i1 = n;
        }
      }
      n = i1 + 1;
      j = m;
      k = i2;
    }
    if (k != 0) {
      mAdapter.finishUpdate(this);
    }
    Collections.sort(mItems, COMPARATOR);
    if (i != 0)
    {
      k = getChildCount();
      i = 0;
      while (i < k)
      {
        localObject = (g)getChildAt(i).getLayoutParams();
        if (!isDecor) {
          widthFactor = 0.0F;
        }
        i += 1;
      }
      setCurrentItemInternal(j, false, true);
      requestLayout();
    }
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return (super.dispatchKeyEvent(paramKeyEvent)) || (executeKeyEvent(paramKeyEvent));
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if (paramAccessibilityEvent.getEventType() == 4096) {
      return super.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent);
    }
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = getChildAt(i);
      if (localView.getVisibility() == 0)
      {
        f localF = infoForChild(localView);
        if ((localF != null) && (position == mCurItem) && (localView.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent))) {
          return true;
        }
      }
      i += 1;
    }
    return false;
  }
  
  float distanceInfluenceForSnapDuration(float paramFloat)
  {
    return (float)Math.sin((paramFloat - 0.5F) * 0.47123894F);
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    int k = 0;
    int i = 0;
    int m = getOverScrollMode();
    if (m != 0) {
      if (m == 1)
      {
        PagerAdapter localPagerAdapter = mAdapter;
        if ((localPagerAdapter != null) && (localPagerAdapter.getCount() > 1)) {}
      }
      else
      {
        mLeftEdge.finish();
        mRightEdge.finish();
        break label256;
      }
    }
    int j;
    if (!mLeftEdge.isFinished())
    {
      k = paramCanvas.save();
      i = getHeight() - getPaddingTop() - getPaddingBottom();
      m = getWidth();
      paramCanvas.rotate(270.0F);
      paramCanvas.translate(-i + getPaddingTop(), mFirstOffset * m);
      mLeftEdge.setSize(i, m);
      j = false | mLeftEdge.draw(paramCanvas);
      paramCanvas.restoreToCount(k);
    }
    k = j;
    boolean bool;
    if (!mRightEdge.isFinished())
    {
      m = paramCanvas.save();
      k = getWidth();
      int n = getHeight();
      int i1 = getPaddingTop();
      int i2 = getPaddingBottom();
      paramCanvas.rotate(90.0F);
      paramCanvas.translate(-getPaddingTop(), -(mLastOffset + 1.0F) * k);
      mRightEdge.setSize(n - i1 - i2, k);
      bool = j | mRightEdge.draw(paramCanvas);
      paramCanvas.restoreToCount(m);
    }
    label256:
    if (bool) {
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    Drawable localDrawable = mMarginDrawable;
    if ((localDrawable != null) && (localDrawable.isStateful())) {
      localDrawable.setState(getDrawableState());
    }
  }
  
  public boolean executeKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 0)
    {
      int i = paramKeyEvent.getKeyCode();
      if (i != 21)
      {
        if (i != 22)
        {
          if (i != 61) {
            return false;
          }
          if (paramKeyEvent.hasNoModifiers()) {
            return arrowScroll(2);
          }
          if (paramKeyEvent.hasModifiers(1)) {
            return arrowScroll(1);
          }
        }
        else
        {
          if (paramKeyEvent.hasModifiers(2)) {
            return pageRight();
          }
          return arrowScroll(66);
        }
      }
      else
      {
        if (paramKeyEvent.hasModifiers(2)) {
          return pageLeft();
        }
        return arrowScroll(17);
      }
    }
    return false;
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new g();
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new g(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return generateDefaultLayoutParams();
  }
  
  public PagerAdapter getAdapter()
  {
    return mAdapter;
  }
  
  protected int getChildDrawingOrder(int paramInt1, int paramInt2)
  {
    if (mDrawingOrder == 2) {
      paramInt2 = paramInt1 - 1 - paramInt2;
    }
    return mDrawingOrderedChildren.get(paramInt2)).getLayoutParams()).childIndex;
  }
  
  public int getCurrentItem()
  {
    return mCurItem;
  }
  
  public int getOffscreenPageLimit()
  {
    return mOffscreenPageLimit;
  }
  
  public int getPageMargin()
  {
    return mPageMargin;
  }
  
  f infoForAnyChild(View paramView)
  {
    for (;;)
    {
      ViewParent localViewParent = paramView.getParent();
      if (localViewParent == this) {
        break label34;
      }
      if ((localViewParent == null) || (!(localViewParent instanceof View))) {
        break;
      }
      paramView = (View)localViewParent;
    }
    return null;
    label34:
    return infoForChild(paramView);
  }
  
  f infoForChild(View paramView)
  {
    int i = 0;
    while (i < mItems.size())
    {
      f localF = (f)mItems.get(i);
      if (mAdapter.isViewFromObject(paramView, object)) {
        return localF;
      }
      i += 1;
    }
    return null;
  }
  
  f infoForPosition(int paramInt)
  {
    int i = 0;
    while (i < mItems.size())
    {
      f localF = (f)mItems.get(i);
      if (position == paramInt) {
        return localF;
      }
      i += 1;
    }
    return null;
  }
  
  void initViewPager()
  {
    setWillNotDraw(false);
    setDescendantFocusability(262144);
    setFocusable(true);
    Context localContext = getContext();
    mScroller = new Scroller(localContext, sInterpolator);
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(localContext);
    float f = getResourcesgetDisplayMetricsdensity;
    mTouchSlop = localViewConfiguration.getScaledPagingTouchSlop();
    mMinimumVelocity = ((int)(400.0F * f));
    mMaximumVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
    mLeftEdge = new EdgeEffect(localContext);
    mRightEdge = new EdgeEffect(localContext);
    mFlingDistance = ((int)(25.0F * f));
    mCloseEnough = ((int)(2.0F * f));
    mDefaultGutterSize = ((int)(16.0F * f));
    ViewCompat.setAccessibilityDelegate(this, new h());
    if (ViewCompat.getImportantForAccessibility(this) == 0) {
      ViewCompat.add(this, 1);
    }
    ViewCompat.setOnApplyWindowInsetsListener(this, new d());
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    mFirstLayout = true;
  }
  
  protected void onDetachedFromWindow()
  {
    removeCallbacks(mEndScrollRunnable);
    Scroller localScroller = mScroller;
    if ((localScroller != null) && (!localScroller.isFinished())) {
      mScroller.abortAnimation();
    }
    super.onDetachedFromWindow();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((mPageMargin > 0) && (mMarginDrawable != null) && (mItems.size() > 0) && (mAdapter != null))
    {
      int k = getScrollX();
      int m = getWidth();
      float f3 = mPageMargin / m;
      int j = 0;
      Object localObject = (f)mItems.get(0);
      float f1 = offset;
      int n = mItems.size();
      int i = position;
      int i1 = mItems.get(n - 1)).position;
      while (i < i1)
      {
        while ((i > position) && (j < n))
        {
          localObject = mItems;
          j += 1;
          localObject = (f)((ArrayList)localObject).get(j);
        }
        float f4;
        float f2;
        if (i == position)
        {
          f1 = offset;
          f4 = widthFactor;
          f2 = (f1 + f4) * m;
          f1 = f1 + f4 + f3;
        }
        else
        {
          f4 = mAdapter.getPageWidth(i);
          f2 = m * (f1 + f4);
          f1 += f4 + f3;
        }
        if (mPageMargin + f2 > k)
        {
          mMarginDrawable.setBounds(Math.round(f2), mTopPageBounds, Math.round(mPageMargin + f2), mBottomPageBounds);
          mMarginDrawable.draw(paramCanvas);
        }
        if (f2 > k + m) {
          return;
        }
        i += 1;
      }
    }
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction() & 0xFF;
    if ((i != 3) && (i != 1))
    {
      if (i != 0)
      {
        if (mIsBeingDragged) {
          return true;
        }
        if (mIsUnableToDrag) {
          return false;
        }
      }
      float f1;
      if (i != 0)
      {
        if (i != 2)
        {
          if (i == 6) {
            onSecondaryPointerUp(paramMotionEvent);
          }
        }
        else
        {
          i = mActivePointerId;
          if (i != -1)
          {
            i = paramMotionEvent.findPointerIndex(i);
            float f2 = paramMotionEvent.getX(i);
            f1 = f2 - mLastMotionX;
            float f4 = Math.abs(f1);
            float f3 = paramMotionEvent.getY(i);
            float f5 = Math.abs(f3 - mInitialMotionY);
            if ((f1 != 0.0F) && (!isGutterDrag(mLastMotionX, f1)) && (canScroll(this, false, (int)f1, (int)f2, (int)f3)))
            {
              mLastMotionX = f2;
              mLastMotionY = f3;
              mIsUnableToDrag = true;
              return false;
            }
            if ((f4 > mTouchSlop) && (0.5F * f4 > f5))
            {
              mIsBeingDragged = true;
              requestParentDisallowInterceptTouchEvent(true);
              setScrollState(1);
              if (f1 > 0.0F) {
                f1 = mInitialMotionX + mTouchSlop;
              } else {
                f1 = mInitialMotionX - mTouchSlop;
              }
              mLastMotionX = f1;
              mLastMotionY = f3;
              setScrollingCacheEnabled(true);
            }
            else if (f5 > mTouchSlop)
            {
              mIsUnableToDrag = true;
            }
            if ((mIsBeingDragged) && (performDrag(f2))) {
              ViewCompat.postInvalidateOnAnimation(this);
            }
          }
        }
      }
      else
      {
        f1 = paramMotionEvent.getX();
        mInitialMotionX = f1;
        mLastMotionX = f1;
        f1 = paramMotionEvent.getY();
        mInitialMotionY = f1;
        mLastMotionY = f1;
        mActivePointerId = paramMotionEvent.getPointerId(0);
        mIsUnableToDrag = false;
        mIsScrollStarted = true;
        mScroller.computeScrollOffset();
        if ((mScrollState == 2) && (Math.abs(mScroller.getFinalX() - mScroller.getCurrX()) > mCloseEnough))
        {
          mScroller.abortAnimation();
          mPopulatePending = false;
          populate();
          mIsBeingDragged = true;
          requestParentDisallowInterceptTouchEvent(true);
          setScrollState(1);
        }
        else
        {
          completeScroll(false);
          mIsBeingDragged = false;
        }
      }
      if (mVelocityTracker == null) {
        mVelocityTracker = VelocityTracker.obtain();
      }
      mVelocityTracker.addMovement(paramMotionEvent);
      return mIsBeingDragged;
    }
    onTouchEvent();
    return false;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i3 = getChildCount();
    int i5 = paramInt3 - paramInt1;
    int i4 = paramInt4 - paramInt2;
    paramInt1 = getPaddingLeft();
    paramInt2 = getPaddingTop();
    int j = getPaddingRight();
    paramInt4 = getPaddingBottom();
    int i6 = getScrollX();
    int k = 0;
    int m = 0;
    View localView;
    g localG;
    while (m < i3)
    {
      localView = getChildAt(m);
      i = paramInt1;
      paramInt3 = j;
      int n = paramInt2;
      int i1 = k;
      int i2 = paramInt4;
      if (localView.getVisibility() != 8)
      {
        localG = (g)localView.getLayoutParams();
        i = paramInt1;
        paramInt3 = j;
        n = paramInt2;
        i1 = k;
        i2 = paramInt4;
        if (isDecor)
        {
          i = gravity;
          paramInt3 = i & 0x7;
          n = i & 0x70;
          if (paramInt3 != 1)
          {
            if (paramInt3 != 3)
            {
              if (paramInt3 != 5)
              {
                paramInt3 = paramInt1;
                i = paramInt1;
              }
              else
              {
                paramInt3 = i5 - j - localView.getMeasuredWidth();
                j += localView.getMeasuredWidth();
                i = paramInt1;
              }
            }
            else
            {
              paramInt3 = paramInt1;
              i = paramInt1 + localView.getMeasuredWidth();
            }
          }
          else
          {
            paramInt3 = Math.max((i5 - localView.getMeasuredWidth()) / 2, paramInt1);
            i = paramInt1;
          }
          if (n != 16)
          {
            if (n != 48)
            {
              if (n != 80)
              {
                paramInt1 = paramInt2;
              }
              else
              {
                paramInt1 = i4 - paramInt4 - localView.getMeasuredHeight();
                paramInt4 += localView.getMeasuredHeight();
              }
            }
            else
            {
              paramInt1 = paramInt2;
              paramInt2 += localView.getMeasuredHeight();
            }
          }
          else {
            paramInt1 = Math.max((i4 - localView.getMeasuredHeight()) / 2, paramInt2);
          }
          paramInt3 += i6;
          localView.layout(paramInt3, paramInt1, localView.getMeasuredWidth() + paramInt3, paramInt1 + localView.getMeasuredHeight());
          i1 = k + 1;
          paramInt3 = j;
          n = paramInt2;
          i2 = paramInt4;
        }
      }
      m += 1;
      paramInt1 = i;
      j = paramInt3;
      paramInt2 = n;
      k = i1;
      paramInt4 = i2;
    }
    int i = i5 - paramInt1 - j;
    paramInt3 = 0;
    while (paramInt3 < i3)
    {
      localView = getChildAt(paramInt3);
      if (localView.getVisibility() != 8)
      {
        localG = (g)localView.getLayoutParams();
        if (!isDecor)
        {
          f localF = infoForChild(localView);
          if (localF != null)
          {
            j = paramInt1 + (int)(i * offset);
            if (needsMeasure)
            {
              needsMeasure = false;
              localView.measure(View.MeasureSpec.makeMeasureSpec((int)(i * widthFactor), 1073741824), View.MeasureSpec.makeMeasureSpec(i4 - paramInt2 - paramInt4, 1073741824));
            }
            localView.layout(j, paramInt2, localView.getMeasuredWidth() + j, localView.getMeasuredHeight() + paramInt2);
          }
        }
        else {}
      }
      paramInt3 += 1;
    }
    mTopPageBounds = paramInt2;
    mBottomPageBounds = (i4 - paramInt4);
    mDecorChildCount = k;
    if (mFirstLayout) {
      scrollToItem(mCurItem, false, 0, false);
    }
    mFirstLayout = false;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(View.getDefaultSize(0, paramInt1), View.getDefaultSize(0, paramInt2));
    paramInt1 = getMeasuredWidth();
    mGutterSize = Math.min(paramInt1 / 10, mDefaultGutterSize);
    paramInt1 = paramInt1 - getPaddingLeft() - getPaddingRight();
    paramInt2 = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
    int i4 = getChildCount();
    int m = 0;
    View localView;
    g localG;
    while (m < i4)
    {
      localView = getChildAt(m);
      i = paramInt1;
      int j = paramInt2;
      if (localView.getVisibility() != 8)
      {
        localG = (g)localView.getLayoutParams();
        if ((localG != null) && (isDecor))
        {
          i = gravity;
          int k = i & 0x7;
          int n = i & 0x70;
          j = Integer.MIN_VALUE;
          i = Integer.MIN_VALUE;
          if ((n != 48) && (n != 80)) {
            n = 0;
          } else {
            n = 1;
          }
          int i1;
          if ((k != 3) && (k != 5)) {
            i1 = 0;
          } else {
            i1 = 1;
          }
          if (n != 0)
          {
            k = 1073741824;
          }
          else
          {
            k = j;
            if (i1 != 0)
            {
              i = 1073741824;
              k = j;
            }
          }
          int i2 = paramInt2;
          j = width;
          if (j != -2)
          {
            k = 1073741824;
            if (j != -1) {
              j = width;
            } else {
              j = paramInt1;
            }
          }
          else
          {
            j = paramInt1;
          }
          int i5 = height;
          int i3 = i2;
          if (i5 != -2) {
            if (i5 != -1)
            {
              i3 = height;
              i = 1073741824;
            }
            else
            {
              i = 1073741824;
              i3 = i2;
            }
          }
          localView.measure(View.MeasureSpec.makeMeasureSpec(j, k), View.MeasureSpec.makeMeasureSpec(i3, i));
          if (n != 0)
          {
            j = paramInt2 - localView.getMeasuredHeight();
            i = paramInt1;
          }
          else
          {
            i = paramInt1;
            j = paramInt2;
            if (i1 != 0)
            {
              i = paramInt1 - localView.getMeasuredWidth();
              j = paramInt2;
            }
          }
        }
        else
        {
          i = paramInt1;
          j = paramInt2;
        }
      }
      m += 1;
      paramInt1 = i;
      paramInt2 = j;
    }
    View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824);
    mChildHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
    mInLayout = true;
    populate();
    mInLayout = false;
    int i = getChildCount();
    paramInt2 = 0;
    while (paramInt2 < i)
    {
      localView = getChildAt(paramInt2);
      if (localView.getVisibility() != 8)
      {
        localG = (g)localView.getLayoutParams();
        if ((localG == null) || (!isDecor)) {
          localView.measure(View.MeasureSpec.makeMeasureSpec((int)(paramInt1 * widthFactor), 1073741824), mChildHeightMeasureSpec);
        }
      }
      paramInt2 += 1;
    }
  }
  
  protected void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    int i;
    View localView;
    if (mDecorChildCount > 0)
    {
      int i1 = getScrollX();
      i = getPaddingLeft();
      int k = getPaddingRight();
      int i2 = getWidth();
      int i3 = getChildCount();
      int m = 0;
      while (m < i3)
      {
        localView = getChildAt(m);
        g localG = (g)localView.getLayoutParams();
        int j;
        int n;
        if (!isDecor)
        {
          j = i;
          n = k;
        }
        else
        {
          j = gravity & 0x7;
          if (j != 1)
          {
            if (j != 3)
            {
              if (j != 5)
              {
                j = i;
              }
              else
              {
                j = i2 - k - localView.getMeasuredWidth();
                k += localView.getMeasuredWidth();
              }
            }
            else
            {
              j = i;
              i += localView.getWidth();
            }
          }
          else {
            j = Math.max((i2 - localView.getMeasuredWidth()) / 2, i);
          }
          int i4 = j + i1 - localView.getLeft();
          j = i;
          n = k;
          if (i4 != 0)
          {
            localView.offsetLeftAndRight(i4);
            n = k;
            j = i;
          }
        }
        m += 1;
        i = j;
        k = n;
      }
    }
    dispatchOnPageScrolled(paramInt1, paramFloat, paramInt2);
    if (mPageTransformer != null)
    {
      paramInt2 = getScrollX();
      i = getChildCount();
      paramInt1 = 0;
      while (paramInt1 < i)
      {
        localView = getChildAt(paramInt1);
        if (!getLayoutParamsisDecor)
        {
          paramFloat = (localView.getLeft() - paramInt2) / getClientWidth();
          mPageTransformer.transformPage(localView, paramFloat);
        }
        paramInt1 += 1;
      }
    }
    mCalledSuper = true;
  }
  
  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect)
  {
    int j = getChildCount();
    int i;
    int k;
    if ((paramInt & 0x2) != 0)
    {
      i = 0;
      k = 1;
    }
    else
    {
      i = j - 1;
      k = -1;
      j = -1;
    }
    while (i != j)
    {
      View localView = getChildAt(i);
      if (localView.getVisibility() == 0)
      {
        f localF = infoForChild(localView);
        if ((localF != null) && (position == mCurItem) && (localView.requestFocus(paramInt, paramRect))) {
          return true;
        }
      }
      i += k;
    }
    return false;
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof m))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (m)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    PagerAdapter localPagerAdapter = mAdapter;
    if (localPagerAdapter != null)
    {
      localPagerAdapter.restoreState(adapterState, loader);
      setCurrentItemInternal(position, false, true);
      return;
    }
    mRestoredCurItem = position;
    mRestoredAdapterState = adapterState;
    mRestoredClassLoader = loader;
  }
  
  public Parcelable onSaveInstanceState()
  {
    m localM = new m(super.onSaveInstanceState());
    position = mCurItem;
    PagerAdapter localPagerAdapter = mAdapter;
    if (localPagerAdapter != null) {
      adapterState = localPagerAdapter.saveState();
    }
    return localM;
  }
  
  public void onSaveInstanceState(i paramI)
  {
    List localList = mGroups;
    if (localList != null) {
      localList.remove(paramI);
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3)
    {
      paramInt2 = mPageMargin;
      recomputeScrollPosition(paramInt1, paramInt3, paramInt2, paramInt2);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (mFakeDragging) {
      return true;
    }
    if ((paramMotionEvent.getAction() == 0) && (paramMotionEvent.getEdgeFlags() != 0)) {
      return false;
    }
    Object localObject = mAdapter;
    if (localObject != null)
    {
      if (((PagerAdapter)localObject).getCount() == 0) {
        return false;
      }
      if (mVelocityTracker == null) {
        mVelocityTracker = VelocityTracker.obtain();
      }
      mVelocityTracker.addMovement(paramMotionEvent);
      int i = paramMotionEvent.getAction();
      boolean bool = false;
      i &= 0xFF;
      float f1;
      if (i != 0)
      {
        if (i != 1)
        {
          if (i != 2)
          {
            if (i != 3)
            {
              if (i != 5)
              {
                if (i == 6)
                {
                  onSecondaryPointerUp(paramMotionEvent);
                  mLastMotionX = paramMotionEvent.getX(paramMotionEvent.findPointerIndex(mActivePointerId));
                }
              }
              else
              {
                i = paramMotionEvent.getActionIndex();
                mLastMotionX = paramMotionEvent.getX(i);
                mActivePointerId = paramMotionEvent.getPointerId(i);
              }
            }
            else if (mIsBeingDragged)
            {
              scrollToItem(mCurItem, true, 0, false);
              bool = onTouchEvent();
            }
          }
          else
          {
            if (!mIsBeingDragged)
            {
              i = paramMotionEvent.findPointerIndex(mActivePointerId);
              if (i == -1)
              {
                bool = onTouchEvent();
                break label608;
              }
              f1 = paramMotionEvent.getX(i);
              float f3 = Math.abs(f1 - mLastMotionX);
              float f2 = paramMotionEvent.getY(i);
              float f4 = Math.abs(f2 - mLastMotionY);
              if ((f3 > mTouchSlop) && (f3 > f4))
              {
                mIsBeingDragged = true;
                requestParentDisallowInterceptTouchEvent(true);
                f3 = mInitialMotionX;
                if (f1 - f3 > 0.0F) {
                  f1 = f3 + mTouchSlop;
                } else {
                  f1 = f3 - mTouchSlop;
                }
                mLastMotionX = f1;
                mLastMotionY = f2;
                setScrollState(1);
                setScrollingCacheEnabled(true);
                localObject = getParent();
                if (localObject != null) {
                  ((ViewParent)localObject).requestDisallowInterceptTouchEvent(true);
                }
              }
            }
            if (mIsBeingDragged) {
              bool = false | performDrag(paramMotionEvent.getX(paramMotionEvent.findPointerIndex(mActivePointerId)));
            }
          }
        }
        else if (mIsBeingDragged)
        {
          localObject = mVelocityTracker;
          ((VelocityTracker)localObject).computeCurrentVelocity(1000, mMaximumVelocity);
          i = (int)((VelocityTracker)localObject).getXVelocity(mActivePointerId);
          mPopulatePending = true;
          int j = getClientWidth();
          int k = getScrollX();
          localObject = infoForCurrentScrollPosition();
          f1 = mPageMargin / j;
          setCurrentItemInternal(determineTargetPage(position, (k / j - offset) / (widthFactor + f1), i, (int)(paramMotionEvent.getX(paramMotionEvent.findPointerIndex(mActivePointerId)) - mInitialMotionX)), true, true, i);
          bool = onTouchEvent();
        }
      }
      else
      {
        mScroller.abortAnimation();
        mPopulatePending = false;
        populate();
        f1 = paramMotionEvent.getX();
        mInitialMotionX = f1;
        mLastMotionX = f1;
        f1 = paramMotionEvent.getY();
        mInitialMotionY = f1;
        mLastMotionY = f1;
        mActivePointerId = paramMotionEvent.getPointerId(0);
      }
      label608:
      if (bool)
      {
        ViewCompat.postInvalidateOnAnimation(this);
        return true;
      }
    }
    else
    {
      return false;
    }
    return true;
  }
  
  boolean pageLeft()
  {
    int i = mCurItem;
    if (i > 0)
    {
      setCurrentItem(i - 1, true);
      return true;
    }
    return false;
  }
  
  boolean pageRight()
  {
    PagerAdapter localPagerAdapter = mAdapter;
    if ((localPagerAdapter != null) && (mCurItem < localPagerAdapter.getCount() - 1))
    {
      setCurrentItem(mCurItem + 1, true);
      return true;
    }
    return false;
  }
  
  void populate()
  {
    populate(mCurItem);
  }
  
  void populate(int paramInt)
  {
    int i = mCurItem;
    Object localObject3;
    if (i != paramInt)
    {
      localObject3 = infoForPosition(i);
      mCurItem = paramInt;
    }
    else
    {
      localObject3 = null;
    }
    if (mAdapter == null)
    {
      sortChildDrawingOrder();
      return;
    }
    if (mPopulatePending)
    {
      sortChildDrawingOrder();
      return;
    }
    if (getWindowToken() == null) {
      return;
    }
    mAdapter.destroyItem(this);
    paramInt = mOffscreenPageLimit;
    int i2 = Math.max(0, mCurItem - paramInt);
    int n = mAdapter.getCount();
    int i1 = Math.min(n - 1, mCurItem + paramInt);
    Object localObject1;
    if (n == mExpectedAdapterCount)
    {
      Object localObject4 = null;
      paramInt = 0;
      Object localObject5;
      int j;
      for (;;)
      {
        localObject1 = localObject4;
        if (paramInt >= mItems.size()) {
          break;
        }
        localObject5 = (f)mItems.get(paramInt);
        i = position;
        j = mCurItem;
        if (i >= j)
        {
          localObject1 = localObject4;
          if (i != j) {
            break;
          }
          localObject1 = localObject5;
          break;
        }
        paramInt += 1;
      }
      localObject4 = localObject1;
      if (localObject1 == null)
      {
        localObject4 = localObject1;
        if (n > 0) {
          localObject4 = addNewItem(mCurItem, paramInt);
        }
      }
      if (localObject4 != null)
      {
        float f3 = 0.0F;
        int m = paramInt - 1;
        if (m >= 0) {
          localObject1 = (f)mItems.get(m);
        } else {
          localObject1 = null;
        }
        int i3 = getClientWidth();
        float f2;
        if (i3 <= 0)
        {
          f2 = 0.0F;
        }
        else
        {
          f1 = widthFactor;
          f2 = getPaddingLeft() / i3 + (2.0F - f1);
        }
        int k = mCurItem - 1;
        localObject5 = localObject1;
        j = paramInt;
        while (k >= 0)
        {
          if ((f3 >= f2) && (k < i2))
          {
            if (localObject5 == null) {
              break;
            }
            paramInt = j;
            localObject1 = localObject5;
            i = m;
            f1 = f3;
            if (k == position)
            {
              paramInt = j;
              localObject1 = localObject5;
              i = m;
              f1 = f3;
              if (!scrolling)
              {
                mItems.remove(m);
                mAdapter.destroyItem(this, k, object);
                i = m - 1;
                paramInt = j - 1;
                if (i >= 0) {
                  localObject1 = (f)mItems.get(i);
                } else {
                  localObject1 = null;
                }
                f1 = f3;
              }
            }
          }
          else if ((localObject5 != null) && (k == position))
          {
            f1 = f3 + widthFactor;
            i = m - 1;
            if (i >= 0) {
              localObject1 = (f)mItems.get(i);
            } else {
              localObject1 = null;
            }
            paramInt = j;
          }
          else
          {
            f1 = f3 + addNewItem1widthFactor;
            paramInt = j + 1;
            if (m >= 0)
            {
              localObject1 = (f)mItems.get(m);
              i = m;
            }
            else
            {
              localObject1 = null;
              i = m;
            }
          }
          k -= 1;
          j = paramInt;
          localObject5 = localObject1;
          m = i;
          f3 = f1;
        }
        float f1 = widthFactor;
        paramInt = j + 1;
        if (f1 < 2.0F)
        {
          if (paramInt < mItems.size()) {
            localObject1 = (f)mItems.get(paramInt);
          } else {
            localObject1 = null;
          }
          if (i3 <= 0) {
            f2 = 0.0F;
          } else {
            f2 = getPaddingRight() / i3 + 2.0F;
          }
          i = mCurItem + 1;
          while (i < n)
          {
            if ((f1 >= f2) && (i > i1))
            {
              if (localObject1 == null) {
                break;
              }
              if ((i == position) && (!scrolling))
              {
                mItems.remove(paramInt);
                mAdapter.destroyItem(this, i, object);
                if (paramInt < mItems.size()) {
                  localObject1 = (f)mItems.get(paramInt);
                } else {
                  localObject1 = null;
                }
              }
            }
            else if ((localObject1 != null) && (i == position))
            {
              f1 += widthFactor;
              paramInt += 1;
              if (paramInt < mItems.size()) {
                localObject1 = (f)mItems.get(paramInt);
              } else {
                localObject1 = null;
              }
            }
            else
            {
              localObject1 = addNewItem(i, paramInt);
              paramInt += 1;
              f1 += widthFactor;
              if (paramInt < mItems.size()) {
                localObject1 = (f)mItems.get(paramInt);
              } else {
                localObject1 = null;
              }
            }
            i += 1;
          }
        }
        calculatePageOffsets((f)localObject4, j, (f)localObject3);
        mAdapter.setPrimaryItem(this, mCurItem, object);
      }
      mAdapter.finishUpdate(this);
      i = getChildCount();
      paramInt = 0;
      while (paramInt < i)
      {
        localObject3 = getChildAt(paramInt);
        localObject1 = (g)((View)localObject3).getLayoutParams();
        childIndex = paramInt;
        if ((!isDecor) && (widthFactor == 0.0F))
        {
          localObject3 = infoForChild((View)localObject3);
          if (localObject3 != null)
          {
            widthFactor = widthFactor;
            position = position;
          }
        }
        paramInt += 1;
      }
      sortChildDrawingOrder();
      if (hasFocus())
      {
        localObject1 = findFocus();
        if (localObject1 != null) {
          localObject1 = infoForAnyChild((View)localObject1);
        } else {
          localObject1 = null;
        }
        if ((localObject1 != null) && (position == mCurItem)) {
          return;
        }
        paramInt = 0;
        while (paramInt < getChildCount())
        {
          localObject1 = getChildAt(paramInt);
          localObject3 = infoForChild((View)localObject1);
          if ((localObject3 != null) && (position == mCurItem) && (((View)localObject1).requestFocus(2))) {
            return;
          }
          paramInt += 1;
        }
      }
    }
    else
    {
      try
      {
        localObject1 = getResources().getResourceName(getId());
      }
      catch (Resources.NotFoundException localNotFoundException)
      {
        localObject2 = Integer.toHexString(getId());
      }
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ");
      ((StringBuilder)localObject3).append(mExpectedAdapterCount);
      ((StringBuilder)localObject3).append(", found: ");
      ((StringBuilder)localObject3).append(n);
      ((StringBuilder)localObject3).append(" Pager id: ");
      ((StringBuilder)localObject3).append((String)localObject2);
      ((StringBuilder)localObject3).append(" Pager class: ");
      ((StringBuilder)localObject3).append(getClass());
      ((StringBuilder)localObject3).append(" Problematic adapter: ");
      ((StringBuilder)localObject3).append(mAdapter.getClass());
      Object localObject2 = new IllegalStateException(((StringBuilder)localObject3).toString());
      throw ((Throwable)localObject2);
    }
  }
  
  public void removeView(View paramView)
  {
    if (mInLayout)
    {
      removeViewInLayout(paramView);
      return;
    }
    super.removeView(paramView);
  }
  
  public void setAdapter(PagerAdapter paramPagerAdapter)
  {
    Object localObject = mAdapter;
    int i;
    if (localObject != null)
    {
      ((PagerAdapter)localObject).setViewPagerObserver(null);
      mAdapter.destroyItem(this);
      i = 0;
      while (i < mItems.size())
      {
        localObject = (f)mItems.get(i);
        mAdapter.destroyItem(this, position, object);
        i += 1;
      }
      mAdapter.finishUpdate(this);
      mItems.clear();
      setAdapter();
      mCurItem = 0;
      scrollTo(0, 0);
    }
    localObject = mAdapter;
    mAdapter = paramPagerAdapter;
    mExpectedAdapterCount = 0;
    if (mAdapter != null)
    {
      if (mObserver == null) {
        mObserver = new l();
      }
      mAdapter.setViewPagerObserver(mObserver);
      mPopulatePending = false;
      boolean bool = mFirstLayout;
      mFirstLayout = true;
      mExpectedAdapterCount = mAdapter.getCount();
      if (mRestoredCurItem >= 0)
      {
        mAdapter.restoreState(mRestoredAdapterState, mRestoredClassLoader);
        setCurrentItemInternal(mRestoredCurItem, false, true);
        mRestoredCurItem = -1;
        mRestoredAdapterState = null;
        mRestoredClassLoader = null;
      }
      else if (!bool)
      {
        populate();
      }
      else
      {
        requestLayout();
      }
    }
    List localList = mGroups;
    if ((localList != null) && (!localList.isEmpty()))
    {
      i = 0;
      int j = mGroups.size();
      while (i < j)
      {
        ((i)mGroups.get(i)).add(this, (PagerAdapter)localObject, paramPagerAdapter);
        i += 1;
      }
    }
  }
  
  public void setAdapter(i paramI)
  {
    if (mGroups == null) {
      mGroups = new ArrayList();
    }
    mGroups.add(paramI);
  }
  
  public void setCurrentItem(int paramInt)
  {
    mPopulatePending = false;
    setCurrentItemInternal(paramInt, mFirstLayout ^ true, false);
  }
  
  public void setCurrentItem(int paramInt, boolean paramBoolean)
  {
    mPopulatePending = false;
    setCurrentItemInternal(paramInt, paramBoolean, false);
  }
  
  void setCurrentItemInternal(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    setCurrentItemInternal(paramInt, paramBoolean1, paramBoolean2, 0);
  }
  
  void setCurrentItemInternal(int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2)
  {
    PagerAdapter localPagerAdapter = mAdapter;
    if ((localPagerAdapter != null) && (localPagerAdapter.getCount() > 0))
    {
      if ((!paramBoolean2) && (mCurItem == paramInt1) && (mItems.size() != 0))
      {
        setScrollingCacheEnabled(false);
        return;
      }
      paramBoolean2 = true;
      int i;
      if (paramInt1 < 0)
      {
        i = 0;
      }
      else
      {
        i = paramInt1;
        if (paramInt1 >= mAdapter.getCount()) {
          i = mAdapter.getCount() - 1;
        }
      }
      paramInt1 = mOffscreenPageLimit;
      int j = mCurItem;
      if ((i > j + paramInt1) || (i < j - paramInt1))
      {
        paramInt1 = 0;
        while (paramInt1 < mItems.size())
        {
          mItems.get(paramInt1)).scrolling = true;
          paramInt1 += 1;
        }
      }
      if (mCurItem == i) {
        paramBoolean2 = false;
      }
      if (mFirstLayout)
      {
        mCurItem = i;
        if (paramBoolean2) {
          dispatchOnPageSelected(i);
        }
        requestLayout();
        return;
      }
      populate(i);
      scrollToItem(i, paramBoolean1, paramInt2, paramBoolean2);
      return;
    }
    setScrollingCacheEnabled(false);
  }
  
  public void setOffscreenPageLimit(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 1)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Requested offscreen page limit ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(" too small; defaulting to ");
      localStringBuilder.append(1);
      Log.w("ViewPager", localStringBuilder.toString());
      i = 1;
    }
    if (i != mOffscreenPageLimit)
    {
      mOffscreenPageLimit = i;
      populate();
    }
  }
  
  public void setOnPageChangeListener(j paramJ)
  {
    mOnPageChangeListener = paramJ;
  }
  
  public void setPageMargin(int paramInt)
  {
    int i = mPageMargin;
    mPageMargin = paramInt;
    int j = getWidth();
    recomputeScrollPosition(j, j, paramInt, i);
    requestLayout();
  }
  
  public void setPageMarginDrawable(int paramInt)
  {
    setPageMarginDrawable(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setPageMarginDrawable(Drawable paramDrawable)
  {
    mMarginDrawable = paramDrawable;
    if (paramDrawable != null) {
      refreshDrawableState();
    }
    boolean bool;
    if (paramDrawable == null) {
      bool = true;
    } else {
      bool = false;
    }
    setWillNotDraw(bool);
    invalidate();
  }
  
  void setScrollState(int paramInt)
  {
    if (mScrollState == paramInt) {
      return;
    }
    mScrollState = paramInt;
    if (mPageTransformer != null)
    {
      boolean bool;
      if (paramInt != 0) {
        bool = true;
      } else {
        bool = false;
      }
      enableLayers(bool);
    }
    dispatchOnScrollStateChanged(paramInt);
  }
  
  void smoothScrollTo(int paramInt1, int paramInt2, int paramInt3)
  {
    if (getChildCount() == 0)
    {
      setScrollingCacheEnabled(false);
      return;
    }
    Scroller localScroller = mScroller;
    int i;
    if ((localScroller != null) && (!localScroller.isFinished())) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0)
    {
      if (mIsScrollStarted) {
        i = mScroller.getCurrX();
      } else {
        i = mScroller.getStartX();
      }
      mScroller.abortAnimation();
      setScrollingCacheEnabled(false);
    }
    else
    {
      i = getScrollX();
    }
    int j = getScrollY();
    int k = paramInt1 - i;
    paramInt2 -= j;
    if ((k == 0) && (paramInt2 == 0))
    {
      completeScroll(false);
      populate();
      setScrollState(0);
      return;
    }
    setScrollingCacheEnabled(true);
    setScrollState(2);
    paramInt1 = getClientWidth();
    int m = paramInt1 / 2;
    float f3 = Math.min(1.0F, Math.abs(k) * 1.0F / paramInt1);
    float f1 = m;
    float f2 = m;
    f3 = distanceInfluenceForSnapDuration(f3);
    paramInt3 = Math.abs(paramInt3);
    if (paramInt3 > 0)
    {
      paramInt1 = Math.round(Math.abs((f1 + f2 * f3) / paramInt3) * 1000.0F) * 4;
    }
    else
    {
      f1 = paramInt1;
      f2 = mAdapter.getPageWidth(mCurItem);
      paramInt1 = (int)((1.0F + Math.abs(k) / (mPageMargin + f1 * f2)) * 100.0F);
    }
    paramInt1 = Math.min(paramInt1, 600);
    mIsScrollStarted = false;
    mScroller.startScroll(i, j, k, paramInt2, paramInt1);
    ViewCompat.postInvalidateOnAnimation(this);
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    return (super.verifyDrawable(paramDrawable)) || (paramDrawable == mMarginDrawable);
  }
  
  static final class a
    implements Comparator<ViewPager.f>
  {
    a() {}
    
    public int replace(ViewPager.f paramF1, ViewPager.f paramF2)
    {
      return position - position;
    }
  }
  
  static final class b
    implements Interpolator
  {
    b() {}
    
    public float getInterpolation(float paramFloat)
    {
      paramFloat -= 1.0F;
      return paramFloat * paramFloat * paramFloat * paramFloat * paramFloat + 1.0F;
    }
  }
  
  class c
    implements Runnable
  {
    c() {}
    
    public void run()
    {
      setScrollState(0);
      populate();
    }
  }
  
  class d
    implements OnApplyWindowInsetsListener
  {
    private final Rect mTempRect = new Rect();
    
    d() {}
    
    public WindowInsetsCompat onApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat)
    {
      paramView = ViewCompat.onApplyWindowInsets(paramView, paramWindowInsetsCompat);
      if (paramView.isConsumed()) {
        return paramView;
      }
      paramWindowInsetsCompat = mTempRect;
      left = paramView.getSystemWindowInsetLeft();
      top = paramView.getSystemWindowInsetTop();
      right = paramView.getSystemWindowInsetRight();
      bottom = paramView.getSystemWindowInsetBottom();
      int i = 0;
      int j = getChildCount();
      while (i < j)
      {
        WindowInsetsCompat localWindowInsetsCompat = ViewCompat.dispatchApplyWindowInsets(getChildAt(i), paramView);
        left = Math.min(localWindowInsetsCompat.getSystemWindowInsetLeft(), left);
        top = Math.min(localWindowInsetsCompat.getSystemWindowInsetTop(), top);
        right = Math.min(localWindowInsetsCompat.getSystemWindowInsetRight(), right);
        bottom = Math.min(localWindowInsetsCompat.getSystemWindowInsetBottom(), bottom);
        i += 1;
      }
      return paramView.replaceSystemWindowInsets(left, top, right, bottom);
    }
  }
  
  @Inherited
  @Retention(RetentionPolicy.RUNTIME)
  @Target({java.lang.annotation.ElementType.TYPE})
  public static @interface e {}
  
  static class f
  {
    Object object;
    float offset;
    int position;
    boolean scrolling;
    float widthFactor;
    
    f() {}
  }
  
  public static class g
    extends ViewGroup.LayoutParams
  {
    int childIndex;
    public int gravity;
    public boolean isDecor;
    boolean needsMeasure;
    int position;
    float widthFactor = 0.0F;
    
    public g()
    {
      super(-1);
    }
    
    public g(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, ViewPager.LAYOUT_ATTRS);
      gravity = paramContext.getInteger(0, 48);
      paramContext.recycle();
    }
  }
  
  class h
    extends AccessibilityDelegateCompat
  {
    h() {}
    
    private boolean canScroll()
    {
      PagerAdapter localPagerAdapter = mAdapter;
      return (localPagerAdapter != null) && (localPagerAdapter.getCount() > 1);
    }
    
    public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
      paramAccessibilityEvent.setClassName(ViewPager.class.getName());
      paramAccessibilityEvent.setScrollable(canScroll());
      if (paramAccessibilityEvent.getEventType() == 4096)
      {
        paramView = mAdapter;
        if (paramView != null)
        {
          paramAccessibilityEvent.setItemCount(paramView.getCount());
          paramAccessibilityEvent.setFromIndex(mCurItem);
          paramAccessibilityEvent.setToIndex(mCurItem);
        }
      }
    }
    
    public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
      paramAccessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
      paramAccessibilityNodeInfoCompat.setScrollable(canScroll());
      if (canScrollHorizontally(1)) {
        paramAccessibilityNodeInfoCompat.addAction(4096);
      }
      if (canScrollHorizontally(-1)) {
        paramAccessibilityNodeInfoCompat.addAction(8192);
      }
    }
    
    public boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle)
    {
      if (super.performAccessibilityAction(paramView, paramInt, paramBundle)) {
        return true;
      }
      if (paramInt != 4096)
      {
        if (paramInt != 8192) {
          return false;
        }
        if (canScrollHorizontally(-1))
        {
          paramView = ViewPager.this;
          paramView.setCurrentItem(mCurItem - 1);
          return true;
        }
        return false;
      }
      if (canScrollHorizontally(1))
      {
        paramView = ViewPager.this;
        paramView.setCurrentItem(mCurItem + 1);
        return true;
      }
      return false;
    }
  }
  
  public static abstract interface i
  {
    public abstract void add(ViewPager paramViewPager, PagerAdapter paramPagerAdapter1, PagerAdapter paramPagerAdapter2);
  }
  
  public static abstract interface j
  {
    public abstract void onPageScrollStateChanged(int paramInt);
    
    public abstract void onPageScrolled(int paramInt1, float paramFloat, int paramInt2);
    
    public abstract void onPageSelected(int paramInt);
  }
  
  public static abstract interface k
  {
    public abstract void transformPage(View paramView, float paramFloat);
  }
  
  private class l
    extends DataSetObserver
  {
    l() {}
    
    public void onChanged()
    {
      dataSetChanged();
    }
    
    public void onInvalidated()
    {
      dataSetChanged();
    }
  }
  
  public static class m
    extends Artist
  {
    public static final Parcelable.Creator<m> CREATOR = new a();
    Parcelable adapterState;
    ClassLoader loader;
    int position;
    
    m(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      ClassLoader localClassLoader = paramClassLoader;
      if (paramClassLoader == null) {
        localClassLoader = getClass().getClassLoader();
      }
      position = paramParcel.readInt();
      adapterState = paramParcel.readParcelable(localClassLoader);
      loader = localClassLoader;
    }
    
    public m(Parcelable paramParcelable)
    {
      super();
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("FragmentPager.SavedState{");
      localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      localStringBuilder.append(" position=");
      localStringBuilder.append(position);
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(position);
      paramParcel.writeParcelable(adapterState, paramInt);
    }
    
    static final class a
      implements Parcelable.ClassLoaderCreator<ViewPager.m>
    {
      a() {}
      
      public ViewPager.m createFromParcel(Parcel paramParcel)
      {
        return new ViewPager.m(paramParcel, null);
      }
      
      public ViewPager.m createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
      {
        return new ViewPager.m(paramParcel, paramClassLoader);
      }
      
      public ViewPager.m[] newArray(int paramInt)
      {
        return new ViewPager.m[paramInt];
      }
    }
  }
  
  static class n
    implements Comparator<View>
  {
    n() {}
    
    public int compare(View paramView1, View paramView2)
    {
      paramView1 = (ViewPager.g)paramView1.getLayoutParams();
      paramView2 = (ViewPager.g)paramView2.getLayoutParams();
      boolean bool = isDecor;
      if (bool != isDecor)
      {
        if (bool) {
          return 1;
        }
        return -1;
      }
      return position - position;
    }
  }
}
