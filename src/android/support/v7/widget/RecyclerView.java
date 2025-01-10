package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.view.Artist;
import android.support.v4.view.Frame;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.Type;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewCompat.ViewCompatImpl;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityRecord;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.org.jaxen.pattern.TraceCompat;
import org.org.jaxen.util.ClassReader;
import org.org.v4.net.IpAddress;
import org.org.v4.net.SocketAddress;

public class RecyclerView
  extends ViewGroup
  implements ScrollingView, ViewCompat.ViewCompatImpl
{
  static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC;
  private static final int[] CameraPreference = { 16843830 };
  static final boolean FORCE_INVALIDATE_DISPLAY_LIST;
  private static final boolean L;
  private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
  private static final boolean M;
  private static final int[] NESTED_SCROLLING_ATTRS = { 16842987 };
  static final boolean c;
  static final Interpolator mInterpolator = new c();
  static final boolean s;
  private r DEBUG;
  w bitmap;
  m0.b d;
  private boolean header = true;
  boolean isPaddingMiddle;
  final int[] itemView;
  RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
  private final AccessibilityManager mAccessibilityManager;
  private s mActiveOnItemTouchListener;
  g mAdapter;
  f mAdapterHelper;
  boolean mAdapterUpdateDuringMeasure;
  private EdgeEffect mBottomGlow;
  private j mChildDrawingOrderCallback;
  ChildHelper mChildHelper;
  boolean mClipToPadding;
  boolean mDataSetHasChangedAfterLayout = false;
  private int mEatRequestLayout = 0;
  private int mEatenAccessibilityChangeFlags;
  boolean mFirstLayoutComplete;
  boolean mHasFixedSize;
  private boolean mIgnoreMotionEventTillDown;
  private int mInitialTouchX;
  private int mInitialTouchY;
  private final Rect mInsets = new Rect();
  boolean mIsAttached;
  l mItemAnimator = new DefaultItemAnimator();
  private RecyclerView.l.b mItemAnimatorListener;
  private Runnable mItemAnimatorRunner;
  final ArrayList<n> mItemDecorations = new ArrayList();
  boolean mItemsAddedOrRemoved;
  boolean mItemsChanged;
  private int mLastTouchX;
  private int mLastTouchY;
  o mLayout;
  boolean mLayoutFrozen;
  private int mLayoutOrScrollCounter = 0;
  boolean mLayoutRequestEaten;
  private EdgeEffect mLeftGlow;
  private final int mMaxFlingVelocity;
  private final int mMinFlingVelocity;
  private final int[] mMinMaxLayoutPositions;
  private final int[] mNestedOffsets;
  private final x mObserver = new x();
  private List<q> mOnChildAttachStateListeners;
  private final ArrayList<s> mOnItemTouchListeners = new ArrayList();
  private y mPendingSavedState;
  final RectF mPosition = new RectF();
  boolean mPostedAnimatorRunner;
  final v mRecycler = new v();
  private EdgeEffect mRightGlow;
  boolean mRunningLayoutOrScroll = false;
  final int[] mScrollConsumed;
  private float mScrollFactor = Float.MIN_VALUE;
  private t mScrollListener;
  private List<t> mScrollListeners;
  private final int[] mScrollOffset;
  private int mScrollPointerId = -1;
  private int mScrollState = 0;
  private NestedScrollingChildHelper mScrollingChildHelper;
  final a0 mState;
  final Rect mTempRect = new Rect();
  private EdgeEffect mTopGlow;
  private int mTouchSlop;
  final Runnable mUpdateChildViewsRunnable = new a();
  private VelocityTracker mVelocityTracker;
  final c0 mViewFlinger = new c0();
  private final q1.b mViewInfoProcessCallback;
  final ViewInfoStore mViewInfoStore = new ViewInfoStore();
  private k o = new k();
  a r;
  private float response = Float.MIN_VALUE;
  private int t = 0;
  final List<d0> this$0;
  
  static
  {
    int i = Build.VERSION.SDK_INT;
    boolean bool;
    if ((i != 18) && (i != 19) && (i != 20)) {
      bool = false;
    } else {
      bool = true;
    }
    FORCE_INVALIDATE_DISPLAY_LIST = bool;
    if (Build.VERSION.SDK_INT >= 23) {
      bool = true;
    } else {
      bool = false;
    }
    ALLOW_SIZE_IN_UNSPECIFIED_SPEC = bool;
    if (Build.VERSION.SDK_INT >= 16) {
      bool = true;
    } else {
      bool = false;
    }
    s = bool;
    if (Build.VERSION.SDK_INT >= 21) {
      bool = true;
    } else {
      bool = false;
    }
    c = bool;
    if (Build.VERSION.SDK_INT <= 15) {
      bool = true;
    } else {
      bool = false;
    }
    L = bool;
    if (Build.VERSION.SDK_INT <= 15) {
      bool = true;
    } else {
      bool = false;
    }
    M = bool;
    Class localClass = Integer.TYPE;
    LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[] { Context.class, AttributeSet.class, localClass, localClass };
  }
  
  public RecyclerView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public RecyclerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public RecyclerView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    if (c) {
      localObject = new m0.b();
    } else {
      localObject = null;
    }
    d = ((m0.b)localObject);
    mState = new a0();
    mItemsAddedOrRemoved = false;
    mItemsChanged = false;
    mItemAnimatorListener = new m();
    mPostedAnimatorRunner = false;
    mMinMaxLayoutPositions = new int[2];
    mScrollOffset = new int[2];
    mScrollConsumed = new int[2];
    mNestedOffsets = new int[2];
    itemView = new int[2];
    this$0 = new ArrayList();
    mItemAnimatorRunner = new b();
    mViewInfoProcessCallback = new d();
    if (paramAttributeSet != null)
    {
      localObject = paramContext.obtainStyledAttributes(paramAttributeSet, NESTED_SCROLLING_ATTRS, paramInt, 0);
      mClipToPadding = ((TypedArray)localObject).getBoolean(0, true);
      ((TypedArray)localObject).recycle();
    }
    else
    {
      mClipToPadding = true;
    }
    setScrollContainer(true);
    setFocusableInTouchMode(true);
    Object localObject = ViewConfiguration.get(paramContext);
    mTouchSlop = ((ViewConfiguration)localObject).getScaledTouchSlop();
    mScrollFactor = Frame.init((ViewConfiguration)localObject, paramContext);
    response = Frame.a((ViewConfiguration)localObject, paramContext);
    mMinFlingVelocity = ((ViewConfiguration)localObject).getScaledMinimumFlingVelocity();
    mMaxFlingVelocity = ((ViewConfiguration)localObject).getScaledMaximumFlingVelocity();
    if (getOverScrollMode() == 2) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    setWillNotDraw(bool1);
    mItemAnimator.setListener(mItemAnimatorListener);
    initAdapterManager();
    initChildrenHelper();
    smoothScrollBy();
    if (ViewCompat.getImportantForAccessibility(this) == 0) {
      ViewCompat.add(this, 1);
    }
    mAccessibilityManager = ((AccessibilityManager)getContext().getSystemService("accessibility"));
    setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
    boolean bool2 = true;
    boolean bool1 = true;
    if (paramAttributeSet != null)
    {
      localObject = paramContext.obtainStyledAttributes(paramAttributeSet, IpAddress.RecyclerView, paramInt, 0);
      String str = ((TypedArray)localObject).getString(IpAddress.RecyclerView_layoutManager);
      if (((TypedArray)localObject).getInt(IpAddress.RecyclerView_android_descendantFocusability, -1) == -1) {
        setDescendantFocusability(262144);
      }
      isPaddingMiddle = ((TypedArray)localObject).getBoolean(IpAddress.RecyclerView_fastScrollEnabled, false);
      if (isPaddingMiddle) {
        init((StateListDrawable)((TypedArray)localObject).getDrawable(IpAddress.RecyclerView_fastScrollVerticalThumbDrawable), ((TypedArray)localObject).getDrawable(IpAddress.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable)((TypedArray)localObject).getDrawable(IpAddress.RecyclerView_fastScrollHorizontalThumbDrawable), ((TypedArray)localObject).getDrawable(IpAddress.RecyclerView_fastScrollHorizontalTrackDrawable));
      }
      ((TypedArray)localObject).recycle();
      createLayoutManager(paramContext, str, paramAttributeSet, paramInt, 0);
      if (Build.VERSION.SDK_INT >= 21)
      {
        paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, CameraPreference, paramInt, 0);
        bool1 = paramContext.getBoolean(0, true);
        paramContext.recycle();
      }
    }
    else
    {
      setDescendantFocusability(262144);
      bool1 = bool2;
    }
    setNestedScrollingEnabled(bool1);
  }
  
  static RecyclerView a(View paramView)
  {
    if (!(paramView instanceof ViewGroup)) {
      return null;
    }
    if ((paramView instanceof RecyclerView)) {
      return (RecyclerView)paramView;
    }
    paramView = (ViewGroup)paramView;
    int j = paramView.getChildCount();
    int i = 0;
    while (i < j)
    {
      RecyclerView localRecyclerView = a(paramView.getChildAt(i));
      if (localRecyclerView != null) {
        return localRecyclerView;
      }
      i += 1;
    }
    return null;
  }
  
  private void a()
  {
    if ((header) && (mAdapter != null) && (hasFocus()) && (getDescendantFocusability() != 393216))
    {
      if ((getDescendantFocusability() == 131072) && (isFocused())) {
        return;
      }
      if (!isFocused())
      {
        localObject1 = getFocusedChild();
        if ((M) && ((((View)localObject1).getParent() == null) || (!((View)localObject1).hasFocus())))
        {
          if (mChildHelper.getChildCount() == 0) {
            requestFocus();
          }
        }
        else if (!mChildHelper.isHidden((View)localObject1)) {
          return;
        }
      }
      Object localObject2 = null;
      Object localObject1 = localObject2;
      if (mState.id != -1L)
      {
        localObject1 = localObject2;
        if (mAdapter.hasStableIds()) {
          localObject1 = findViewHolderForItemId(mState.id);
        }
      }
      localObject2 = null;
      if ((localObject1 != null) && (!mChildHelper.isHidden(itemView)) && (itemView.hasFocusable()))
      {
        localObject1 = itemView;
      }
      else
      {
        localObject1 = localObject2;
        if (mChildHelper.getChildCount() > 0) {
          localObject1 = draw();
        }
      }
      if (localObject1 != null)
      {
        int i = mState.editor;
        localObject2 = localObject1;
        if (i != -1L)
        {
          View localView = ((View)localObject1).findViewById(i);
          localObject2 = localObject1;
          if (localView != null)
          {
            localObject2 = localObject1;
            if (localView.isFocusable()) {
              localObject2 = localView;
            }
          }
        }
        localObject2.requestFocus();
      }
    }
  }
  
  private void addAnimatingView(d0 paramD0)
  {
    View localView = itemView;
    int i;
    if (localView.getParent() == this) {
      i = 1;
    } else {
      i = 0;
    }
    mRecycler.unscrapView(getChildViewHolder(localView));
    if (paramD0.isTmpDetached())
    {
      mChildHelper.attachViewToParent(localView, -1, localView.getLayoutParams(), true);
      return;
    }
    if (i == 0)
    {
      mChildHelper.addView(localView, true);
      return;
    }
    mChildHelper.hide(localView);
  }
  
  private void animateChange(d0 paramD01, d0 paramD02, RecyclerView.l.c paramC1, RecyclerView.l.c paramC2, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramD01.setIsRecyclable(false);
    if (paramBoolean1) {
      addAnimatingView(paramD01);
    }
    if (paramD01 != paramD02)
    {
      if (paramBoolean2) {
        addAnimatingView(paramD02);
      }
      mShadowedHolder = paramD02;
      addAnimatingView(paramD01);
      mRecycler.unscrapView(paramD01);
      paramD02.setIsRecyclable(false);
      mShadowingHolder = paramD01;
    }
    if (mItemAnimator.animateChange(paramD01, paramD02, paramC1, paramC2)) {
      postAnimationRunner();
    }
  }
  
  private boolean arrowScroll(View paramView1, View paramView2, int paramInt)
  {
    if (paramView2 != null)
    {
      if (paramView2 == this) {
        return false;
      }
      if (findContainingItemView(paramView2) == null) {
        return false;
      }
      if (paramView1 == null) {
        return true;
      }
      if (findContainingItemView(paramView1) == null) {
        return true;
      }
      mTempRect.set(0, 0, paramView1.getWidth(), paramView1.getHeight());
      mInsets.set(0, 0, paramView2.getWidth(), paramView2.getHeight());
      offsetDescendantRectToMyCoords(paramView1, mTempRect);
      offsetDescendantRectToMyCoords(paramView2, mInsets);
      int k;
      if (mLayout.getLayoutDirection() == 1) {
        k = -1;
      } else {
        k = 1;
      }
      int j = 0;
      paramView1 = mTempRect;
      int i = left;
      int m = mInsets.left;
      if (((i < m) || (right <= m)) && (mTempRect.right < mInsets.right))
      {
        i = 1;
      }
      else
      {
        paramView1 = mTempRect;
        i = right;
        m = mInsets.right;
        if (i <= m)
        {
          i = j;
          if (left < m) {}
        }
        else
        {
          i = j;
          if (mTempRect.left > mInsets.left) {
            i = -1;
          }
        }
      }
      m = 0;
      paramView1 = mTempRect;
      j = top;
      int n = mInsets.top;
      if (((j < n) || (bottom <= n)) && (mTempRect.bottom < mInsets.bottom))
      {
        j = 1;
      }
      else
      {
        paramView1 = mTempRect;
        j = bottom;
        n = mInsets.bottom;
        if (j <= n)
        {
          j = m;
          if (top < n) {}
        }
        else
        {
          j = m;
          if (mTempRect.top > mInsets.top) {
            j = -1;
          }
        }
      }
      if (paramInt != 1)
      {
        if (paramInt != 2)
        {
          if (paramInt != 17)
          {
            if (paramInt != 33)
            {
              if (paramInt != 66)
              {
                if (paramInt == 130)
                {
                  if (j > 0) {
                    return true;
                  }
                }
                else
                {
                  paramView1 = new StringBuilder();
                  paramView1.append("Invalid direction: ");
                  paramView1.append(paramInt);
                  paramView1.append(append());
                  throw new IllegalArgumentException(paramView1.toString());
                }
              }
              else if (i > 0) {
                return true;
              }
            }
            else if (j < 0) {
              return true;
            }
          }
          else if (i < 0) {
            return true;
          }
        }
        else if ((j > 0) || ((j == 0) && (i * k >= 0))) {
          return true;
        }
      }
      else if ((j < 0) || ((j == 0) && (i * k <= 0))) {
        return true;
      }
    }
    return false;
  }
  
  private void cancelTouch()
  {
    resetTouch();
    setScrollState(0);
  }
  
  private int clear(View paramView)
  {
    int i = paramView.getId();
    while ((!paramView.isFocused()) && ((paramView instanceof ViewGroup)) && (paramView.hasFocus()))
    {
      View localView2 = ((ViewGroup)paramView).getFocusedChild();
      View localView1 = localView2;
      paramView = localView1;
      if (localView2.getId() != -1)
      {
        i = localView2.getId();
        paramView = localView1;
      }
    }
    return i;
  }
  
  private void clear()
  {
    a0 localA0 = mState;
    id = -1L;
    type = -1;
    editor = -1;
  }
  
  private void createLayoutManager(Context paramContext, String paramString, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    if (paramString != null)
    {
      paramString = paramString.trim();
      if (!paramString.isEmpty())
      {
        String str = getFullClassName(paramContext, paramString);
        try
        {
          boolean bool = isInEditMode();
          if (bool) {
            paramString = getClass().getClassLoader();
          } else {
            paramString = paramContext.getClassLoader();
          }
          Class localClass = paramString.loadClass(str).asSubclass(o.class);
          Object localObject = null;
          paramString = LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
          try
          {
            paramString = localClass.getConstructor(paramString);
            paramContext = new Object[] { paramContext, paramAttributeSet, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) };
          }
          catch (NoSuchMethodException paramContext) {}
          try
          {
            paramString = localClass.getConstructor(new Class[0]);
            paramContext = localObject;
            paramString.setAccessible(true);
            paramContext = paramString.newInstance(paramContext);
            paramContext = (o)paramContext;
            setLayoutManager(paramContext);
            return;
          }
          catch (NoSuchMethodException paramString)
          {
            paramString.initCause(paramContext);
            paramContext = new StringBuilder();
            paramContext.append(paramAttributeSet.getPositionDescription());
            paramContext.append(": Error creating LayoutManager ");
            paramContext.append(str);
            paramContext = new IllegalStateException(paramContext.toString(), paramString);
            throw paramContext;
          }
          return;
        }
        catch (ClassCastException paramContext)
        {
          paramString = new StringBuilder();
          paramString.append(paramAttributeSet.getPositionDescription());
          paramString.append(": Class is not a LayoutManager ");
          paramString.append(str);
          throw new IllegalStateException(paramString.toString(), paramContext);
        }
        catch (IllegalAccessException paramContext)
        {
          paramString = new StringBuilder();
          paramString.append(paramAttributeSet.getPositionDescription());
          paramString.append(": Cannot access non-public constructor ");
          paramString.append(str);
          throw new IllegalStateException(paramString.toString(), paramContext);
        }
        catch (InstantiationException paramContext)
        {
          paramString = new StringBuilder();
          paramString.append(paramAttributeSet.getPositionDescription());
          paramString.append(": Could not instantiate the LayoutManager: ");
          paramString.append(str);
          throw new IllegalStateException(paramString.toString(), paramContext);
        }
        catch (InvocationTargetException paramContext)
        {
          paramString = new StringBuilder();
          paramString.append(paramAttributeSet.getPositionDescription());
          paramString.append(": Could not instantiate the LayoutManager: ");
          paramString.append(str);
          throw new IllegalStateException(paramString.toString(), paramContext);
        }
        catch (ClassNotFoundException paramContext)
        {
          paramString = new StringBuilder();
          paramString.append(paramAttributeSet.getPositionDescription());
          paramString.append(": Unable to find LayoutManager ");
          paramString.append(str);
          throw new IllegalStateException(paramString.toString(), paramContext);
        }
      }
    }
  }
  
  private boolean didChildRangeChange(int paramInt1, int paramInt2)
  {
    findMinMaxChildLayoutPositions(mMinMaxLayoutPositions);
    int[] arrayOfInt = mMinMaxLayoutPositions;
    return (arrayOfInt[0] != paramInt1) || (arrayOfInt[1] != paramInt2);
  }
  
  private void dispatchContentChangedIfNecessary()
  {
    int i = mEatenAccessibilityChangeFlags;
    mEatenAccessibilityChangeFlags = 0;
    if ((i != 0) && (isAccessibilityEnabled()))
    {
      AccessibilityEvent localAccessibilityEvent = AccessibilityEvent.obtain();
      localAccessibilityEvent.setEventType(2048);
      AccessibilityEventCompat.setContentChangeTypes(localAccessibilityEvent, i);
      sendAccessibilityEventUnchecked(localAccessibilityEvent);
    }
  }
  
  private void dispatchLayoutStep1()
  {
    Object localObject = mState;
    boolean bool = true;
    ((a0)localObject).assertLayoutStep(1);
    onInterceptTouchEvent(mState);
    mState.mIsMeasuring = false;
    eatRequestLayout();
    mViewInfoStore.clear();
    onEnterLayoutOrScroll();
    processAdapterUpdatesAndSetAnimationFlags();
    onCreate();
    localObject = mState;
    if ((!mRunSimpleAnimations) || (!mItemsChanged)) {
      bool = false;
    }
    mTrackOldChangeHolders = bool;
    mItemsChanged = false;
    mItemsAddedOrRemoved = false;
    localObject = mState;
    mInPreLayout = mRunPredictiveAnimations;
    mItemCount = mAdapter.a();
    findMinMaxChildLayoutPositions(mMinMaxLayoutPositions);
    int j;
    int i;
    RecyclerView.l.c localC;
    if (mState.mRunSimpleAnimations)
    {
      j = mChildHelper.getChildCount();
      i = 0;
      while (i < j)
      {
        localObject = getChildViewHolderInt(mChildHelper.getChildAt(i));
        if ((!((d0)localObject).shouldIgnore()) && ((!((d0)localObject).isInvalid()) || (mAdapter.hasStableIds())))
        {
          localC = mItemAnimator.recordPreLayoutInformation(mState, (d0)localObject, l.buildAdapterChangeFlagsForAnimations((d0)localObject), ((d0)localObject).getUnmodifiedPayloads());
          mViewInfoStore.addToPreLayout((d0)localObject, localC);
          if ((mState.mTrackOldChangeHolders) && (((d0)localObject).isUpdated()) && (!((d0)localObject).isRemoved()) && (!((d0)localObject).shouldIgnore()) && (!((d0)localObject).isInvalid()))
          {
            long l = getChangedHolderKey((d0)localObject);
            mViewInfoStore.addToOldChangeHolders(l, (d0)localObject);
          }
        }
        i += 1;
      }
    }
    if (mState.mRunPredictiveAnimations)
    {
      saveOldPositions();
      localObject = mState;
      bool = mStructureChanged;
      mStructureChanged = false;
      mLayout.a(mRecycler, (a0)localObject);
      mState.mStructureChanged = bool;
      i = 0;
      while (i < mChildHelper.getChildCount())
      {
        localObject = getChildViewHolderInt(mChildHelper.getChildAt(i));
        if ((!((d0)localObject).shouldIgnore()) && (!mViewInfoStore.isInPreLayout((d0)localObject)))
        {
          int k = l.buildAdapterChangeFlagsForAnimations((d0)localObject);
          j = k;
          bool = ((d0)localObject).hasAnyOfTheFlags(8192);
          if (!bool) {
            j = k | 0x1000;
          }
          localC = mItemAnimator.recordPreLayoutInformation(mState, (d0)localObject, j, ((d0)localObject).getUnmodifiedPayloads());
          if (bool) {
            recordAnimationInfoIfBouncedHiddenView((d0)localObject, localC);
          } else {
            mViewInfoStore.addToAppearedInPreLayoutHolders((d0)localObject, localC);
          }
        }
        i += 1;
      }
      clearOldPositions();
    }
    else
    {
      clearOldPositions();
    }
    resumeRequestLayout();
    resumeRequestLayout(false);
    mState.mLayoutStep = 2;
  }
  
  private void dispatchLayoutStep2()
  {
    eatRequestLayout();
    onEnterLayoutOrScroll();
    mState.assertLayoutStep(6);
    mAdapterHelper.c();
    mState.mItemCount = mAdapter.a();
    a0 localA0 = mState;
    mDeletedInvisibleItemCountSincePreviousLayout = 0;
    mInPreLayout = false;
    mLayout.a(mRecycler, localA0);
    localA0 = mState;
    mStructureChanged = false;
    mPendingSavedState = null;
    boolean bool;
    if ((mRunSimpleAnimations) && (mItemAnimator != null)) {
      bool = true;
    } else {
      bool = false;
    }
    mRunSimpleAnimations = bool;
    mState.mLayoutStep = 4;
    resumeRequestLayout();
    resumeRequestLayout(false);
  }
  
  private void dispatchLayoutStep3()
  {
    mState.assertLayoutStep(4);
    eatRequestLayout();
    onEnterLayoutOrScroll();
    Object localObject = mState;
    mLayoutStep = 1;
    if (mRunSimpleAnimations)
    {
      int i = mChildHelper.getChildCount() - 1;
      while (i >= 0)
      {
        localObject = getChildViewHolderInt(mChildHelper.getChildAt(i));
        if (!((d0)localObject).shouldIgnore())
        {
          long l = getChangedHolderKey((d0)localObject);
          RecyclerView.l.c localC2 = mItemAnimator.recordPostLayoutInformation(mState, (d0)localObject);
          d0 localD0 = mViewInfoStore.getFromOldChangeHolders(l);
          if ((localD0 != null) && (!localD0.shouldIgnore()))
          {
            boolean bool1 = mViewInfoStore.isDisappearing(localD0);
            boolean bool2 = mViewInfoStore.isDisappearing((d0)localObject);
            if ((bool1) && (localD0 == localObject))
            {
              mViewInfoStore.addToPostLayout((d0)localObject, localC2);
            }
            else
            {
              RecyclerView.l.c localC1 = mViewInfoStore.popFromPreLayout(localD0);
              mViewInfoStore.addToPostLayout((d0)localObject, localC2);
              localC2 = mViewInfoStore.popFromPostLayout((d0)localObject);
              if (localC1 == null) {
                handleMissingPreInfoForChangeError(l, (d0)localObject, localD0);
              } else {
                animateChange(localD0, (d0)localObject, localC1, localC2, bool1, bool2);
              }
            }
          }
          else
          {
            mViewInfoStore.addToPostLayout((d0)localObject, localC2);
          }
        }
        i -= 1;
      }
      mViewInfoStore.a(mViewInfoProcessCallback);
    }
    mLayout.removeAndRecycleScrapInt(mRecycler);
    localObject = mState;
    mPreviousLayoutItemCount = mItemCount;
    mDataSetHasChangedAfterLayout = false;
    mRunningLayoutOrScroll = false;
    mRunSimpleAnimations = false;
    mRunPredictiveAnimations = false;
    mLayout.mDataSetHasChangedAfterLayout = false;
    localObject = mRecycler.mChangedScrap;
    if (localObject != null) {
      ((ArrayList)localObject).clear();
    }
    localObject = mLayout;
    if (l)
    {
      j = 0;
      l = false;
      mRecycler.b();
    }
    mLayout.onLayoutChildren(mState);
    resumeRequestLayout();
    resumeRequestLayout(false);
    mViewInfoStore.clear();
    localObject = mMinMaxLayoutPositions;
    if (didChildRangeChange(localObject[0], localObject[1])) {
      dispatchOnScrolled(0, 0);
    }
    a();
    clear();
  }
  
  private boolean dispatchOnItemTouch(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    s localS = mActiveOnItemTouchListener;
    if (localS != null) {
      if (i == 0)
      {
        mActiveOnItemTouchListener = null;
      }
      else
      {
        localS.a(this, paramMotionEvent);
        if ((i != 3) && (i != 1)) {
          break label115;
        }
        mActiveOnItemTouchListener = null;
        return true;
      }
    }
    if (i != 0)
    {
      int j = mOnItemTouchListeners.size();
      i = 0;
      while (i < j)
      {
        localS = (s)mOnItemTouchListeners.get(i);
        if (localS.onInterceptTouchEvent(this, paramMotionEvent))
        {
          mActiveOnItemTouchListener = localS;
          return true;
        }
        i += 1;
      }
    }
    return false;
    label115:
    return true;
  }
  
  private boolean dispatchOnItemTouchIntercept(MotionEvent paramMotionEvent)
  {
    int j = paramMotionEvent.getAction();
    if ((j == 3) || (j == 0)) {
      mActiveOnItemTouchListener = null;
    }
    int k = mOnItemTouchListeners.size();
    int i = 0;
    while (i < k)
    {
      s localS = (s)mOnItemTouchListeners.get(i);
      if ((localS.onInterceptTouchEvent(this, paramMotionEvent)) && (j != 3))
      {
        mActiveOnItemTouchListener = localS;
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private View draw()
  {
    int i = mState.type;
    if (i == -1) {
      i = 0;
    }
    int k = mState.size();
    int j = i;
    d0 localD0;
    while (j < k)
    {
      localD0 = findViewHolderForAdapterPosition(j);
      if (localD0 == null) {
        break;
      }
      if (itemView.hasFocusable()) {
        return itemView;
      }
      j += 1;
    }
    i = Math.min(k, i) - 1;
    while (i >= 0)
    {
      localD0 = findViewHolderForAdapterPosition(i);
      if (localD0 == null) {
        return null;
      }
      if (itemView.hasFocusable()) {
        return itemView;
      }
      i -= 1;
    }
    return null;
  }
  
  private void draw(View paramView1, View paramView2)
  {
    if (paramView2 != null) {
      localObject = paramView2;
    } else {
      localObject = paramView1;
    }
    mTempRect.set(0, 0, ((View)localObject).getWidth(), ((View)localObject).getHeight());
    Object localObject = ((View)localObject).getLayoutParams();
    if ((localObject instanceof p))
    {
      localObject = (p)localObject;
      if (!mInsetsDirty)
      {
        localObject = mDecorInsets;
        localRect = mTempRect;
        left -= left;
        right += right;
        top -= top;
        bottom += bottom;
      }
    }
    if (paramView2 != null)
    {
      offsetDescendantRectToMyCoords(paramView2, mTempRect);
      offsetRectIntoDescendantCoords(paramView1, mTempRect);
    }
    localObject = mLayout;
    Rect localRect = mTempRect;
    boolean bool2 = mFirstLayoutComplete;
    boolean bool1;
    if (paramView2 == null) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    ((o)localObject).draw(this, paramView1, localRect, bool2 ^ true, bool1);
  }
  
  private void findMinMaxChildLayoutPositions(int[] paramArrayOfInt)
  {
    int i2 = mChildHelper.getChildCount();
    if (i2 == 0)
    {
      paramArrayOfInt[0] = -1;
      paramArrayOfInt[1] = -1;
      return;
    }
    int j = Integer.MAX_VALUE;
    int m = Integer.MIN_VALUE;
    int k = 0;
    while (k < i2)
    {
      d0 localD0 = getChildViewHolderInt(mChildHelper.getChildAt(k));
      int i1;
      if (localD0.shouldIgnore())
      {
        i1 = m;
      }
      else
      {
        int n = localD0.getLayoutPosition();
        int i = j;
        if (n < j) {
          i = n;
        }
        j = i;
        i1 = m;
        if (n > m)
        {
          i1 = n;
          j = i;
        }
      }
      k += 1;
      m = i1;
    }
    paramArrayOfInt[0] = j;
    paramArrayOfInt[1] = m;
  }
  
  static d0 getChildViewHolderInt(View paramView)
  {
    if (paramView == null) {
      return null;
    }
    return getLayoutParamsmViewHolder;
  }
  
  private String getFullClassName(Context paramContext, String paramString)
  {
    if (paramString.charAt(0) == '.')
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.getPackageName());
      localStringBuilder.append(paramString);
      return localStringBuilder.toString();
    }
    if (paramString.contains(".")) {
      return paramString;
    }
    paramContext = new StringBuilder();
    paramContext.append(RecyclerView.class.getPackage().getName());
    paramContext.append('.');
    paramContext.append(paramString);
    return paramContext.toString();
  }
  
  private NestedScrollingChildHelper getScrollingChildHelper()
  {
    if (mScrollingChildHelper == null) {
      mScrollingChildHelper = new NestedScrollingChildHelper(this);
    }
    return mScrollingChildHelper;
  }
  
  private void handleMissingPreInfoForChangeError(long paramLong, d0 paramD01, d0 paramD02)
  {
    int j = mChildHelper.getChildCount();
    int i = 0;
    while (i < j)
    {
      localObject = getChildViewHolderInt(mChildHelper.getChildAt(i));
      if ((localObject != paramD01) && (getChangedHolderKey((d0)localObject) == paramLong))
      {
        paramD02 = mAdapter;
        if ((paramD02 != null) && (paramD02.hasStableIds()))
        {
          paramD02 = new StringBuilder();
          paramD02.append("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:");
          paramD02.append(localObject);
          paramD02.append(" \n View Holder 2:");
          paramD02.append(paramD01);
          paramD02.append(append());
          throw new IllegalStateException(paramD02.toString());
        }
        paramD02 = new StringBuilder();
        paramD02.append("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:");
        paramD02.append(localObject);
        paramD02.append(" \n View Holder 2:");
        paramD02.append(paramD01);
        paramD02.append(append());
        throw new IllegalStateException(paramD02.toString());
      }
      i += 1;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Problem while matching changed view holders with the newones. The pre-layout information for the change holder ");
    ((StringBuilder)localObject).append(paramD02);
    ((StringBuilder)localObject).append(" cannot be found but it is necessary for ");
    ((StringBuilder)localObject).append(paramD01);
    ((StringBuilder)localObject).append(append());
    Log.e("RecyclerView", ((StringBuilder)localObject).toString());
  }
  
  private boolean hasUpdatedView()
  {
    int j = mChildHelper.getChildCount();
    int i = 0;
    while (i < j)
    {
      d0 localD0 = getChildViewHolderInt(mChildHelper.getChildAt(i));
      if ((localD0 != null) && (!localD0.shouldIgnore()) && (localD0.isUpdated())) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private void initChildrenHelper()
  {
    mChildHelper = new ChildHelper(new e());
  }
  
  static void next(d0 paramD0)
  {
    Object localObject = l;
    if (localObject != null)
    {
      localObject = (View)((WeakReference)localObject).get();
      while (localObject != null)
      {
        if (localObject == itemView) {
          return;
        }
        localObject = ((View)localObject).getParent();
        if ((localObject instanceof View)) {
          localObject = (View)localObject;
        } else {
          localObject = null;
        }
      }
      l = null;
    }
  }
  
  private void onCreate()
  {
    a0 localA0 = null;
    Object localObject = localA0;
    if (header)
    {
      localObject = localA0;
      if (hasFocus())
      {
        localObject = localA0;
        if (mAdapter != null) {
          localObject = getFocusedChild();
        }
      }
    }
    if (localObject == null) {
      localObject = null;
    } else {
      localObject = findContainingViewHolder((View)localObject);
    }
    if (localObject == null)
    {
      clear();
      return;
    }
    localA0 = mState;
    long l;
    if (mAdapter.hasStableIds()) {
      l = ((d0)localObject).getItemId();
    } else {
      l = -1L;
    }
    id = l;
    localA0 = mState;
    int i;
    if (mDataSetHasChangedAfterLayout) {
      i = -1;
    } else if (((d0)localObject).isRemoved()) {
      i = mOldPosition;
    } else {
      i = ((d0)localObject).getText();
    }
    type = i;
    mState.editor = clear(itemView);
  }
  
  static void onLayoutChild(View paramView, Rect paramRect)
  {
    p localP = (p)paramView.getLayoutParams();
    Rect localRect = mDecorInsets;
    paramRect.set(paramView.getLeft() - left - leftMargin, paramView.getTop() - top - topMargin, paramView.getRight() + right + rightMargin, paramView.getBottom() + bottom + bottomMargin);
  }
  
  private void onPointerUp(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionIndex();
    if (paramMotionEvent.getPointerId(i) == mScrollPointerId)
    {
      if (i == 0) {
        i = 1;
      } else {
        i = 0;
      }
      mScrollPointerId = paramMotionEvent.getPointerId(i);
      int j = (int)(paramMotionEvent.getX(i) + 0.5F);
      mLastTouchY = j;
      mInitialTouchY = j;
      i = (int)(paramMotionEvent.getY(i) + 0.5F);
      mLastTouchX = i;
      mInitialTouchX = i;
    }
  }
  
  private boolean predictiveItemAnimationsEnabled()
  {
    return (mItemAnimator != null) && (mLayout.supportsPredictiveItemAnimations());
  }
  
  private void processAdapterUpdatesAndSetAnimationFlags()
  {
    if (mDataSetHasChangedAfterLayout)
    {
      mAdapterHelper.e();
      if (mRunningLayoutOrScroll) {
        mLayout.onItemsChanged(this);
      }
    }
    if (predictiveItemAnimationsEnabled()) {
      mAdapterHelper.a();
    } else {
      mAdapterHelper.c();
    }
    boolean bool1 = mItemsAddedOrRemoved;
    boolean bool2 = false;
    int i;
    if ((!bool1) && (!mItemsChanged)) {
      i = 0;
    } else {
      i = 1;
    }
    a0 localA0 = mState;
    if ((mFirstLayoutComplete) && (mItemAnimator != null) && ((mDataSetHasChangedAfterLayout) || (i != 0) || (mLayout.mDataSetHasChangedAfterLayout)) && ((!mDataSetHasChangedAfterLayout) || (mAdapter.hasStableIds()))) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    mRunSimpleAnimations = bool1;
    localA0 = mState;
    bool1 = bool2;
    if (mRunSimpleAnimations)
    {
      bool1 = bool2;
      if (i != 0)
      {
        bool1 = bool2;
        if (!mDataSetHasChangedAfterLayout)
        {
          bool1 = bool2;
          if (predictiveItemAnimationsEnabled()) {
            bool1 = true;
          }
        }
      }
    }
    mRunPredictiveAnimations = bool1;
  }
  
  private void pullGlows(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    int i = 0;
    if (paramFloat2 < 0.0F)
    {
      ensureLeftGlow();
      EdgeEffectCompat.draw(mLeftGlow, -paramFloat2 / getWidth(), 1.0F - paramFloat3 / getHeight());
      i = 1;
    }
    else if (paramFloat2 > 0.0F)
    {
      ensureRightGlow();
      EdgeEffectCompat.draw(mRightGlow, paramFloat2 / getWidth(), paramFloat3 / getHeight());
      i = 1;
    }
    if (paramFloat4 < 0.0F)
    {
      ensureTopGlow();
      EdgeEffectCompat.draw(mTopGlow, -paramFloat4 / getHeight(), paramFloat1 / getWidth());
      i = 1;
    }
    else if (paramFloat4 > 0.0F)
    {
      ensureBottomGlow();
      EdgeEffectCompat.draw(mBottomGlow, paramFloat4 / getHeight(), 1.0F - paramFloat1 / getWidth());
      i = 1;
    }
    if ((i != 0) || (paramFloat2 != 0.0F) || (paramFloat4 != 0.0F)) {
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  private void releaseGlows()
  {
    boolean bool2 = false;
    EdgeEffect localEdgeEffect = mLeftGlow;
    if (localEdgeEffect != null)
    {
      localEdgeEffect.onRelease();
      bool2 = mLeftGlow.isFinished();
    }
    localEdgeEffect = mTopGlow;
    boolean bool1 = bool2;
    if (localEdgeEffect != null)
    {
      localEdgeEffect.onRelease();
      bool1 = bool2 | mTopGlow.isFinished();
    }
    localEdgeEffect = mRightGlow;
    bool2 = bool1;
    if (localEdgeEffect != null)
    {
      localEdgeEffect.onRelease();
      bool2 = bool1 | mRightGlow.isFinished();
    }
    localEdgeEffect = mBottomGlow;
    bool1 = bool2;
    if (localEdgeEffect != null)
    {
      localEdgeEffect.onRelease();
      bool1 = bool2 | mBottomGlow.isFinished();
    }
    if (bool1) {
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  private void resetTouch()
  {
    VelocityTracker localVelocityTracker = mVelocityTracker;
    if (localVelocityTracker != null) {
      localVelocityTracker.clear();
    }
    stopNestedScroll(0);
    releaseGlows();
  }
  
  private void setAdapterInternal(g paramG, boolean paramBoolean1, boolean paramBoolean2)
  {
    g localG = mAdapter;
    if (localG != null)
    {
      localG.unregisterAdapterDataObserver(mObserver);
      mAdapter.onDetachedFromRecyclerView(this);
    }
    if ((!paramBoolean1) || (paramBoolean2)) {
      setAdapterInternal();
    }
    mAdapterHelper.e();
    localG = mAdapter;
    mAdapter = paramG;
    if (paramG != null)
    {
      paramG.registerAdapterDataObserver(mObserver);
      paramG.onAttachedToRecyclerView(this);
    }
    paramG = mLayout;
    if (paramG != null) {
      paramG.onAdapterChanged(localG, mAdapter);
    }
    mRecycler.onAdapterChanged(localG, mAdapter, paramBoolean1);
    mState.mStructureChanged = true;
  }
  
  private void smoothScrollBy()
  {
    if (ViewCompat.create(this) == 0) {
      ViewCompat.postInvalidateOnAnimation(this, 8);
    }
  }
  
  private void stopScrollersInternal()
  {
    mViewFlinger.stop();
    o localO = mLayout;
    if (localO != null) {
      localO.onLayoutChildren();
    }
  }
  
  void a(String paramString)
  {
    if (isComputingLayout())
    {
      if (paramString == null)
      {
        paramString = new StringBuilder();
        paramString.append("Cannot call this method while RecyclerView is computing a layout or scrolling");
        paramString.append(append());
        throw new IllegalStateException(paramString.toString());
      }
      throw new IllegalStateException(paramString);
    }
    if (t > 0)
    {
      paramString = new StringBuilder();
      paramString.append("");
      paramString.append(append());
      Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(paramString.toString()));
    }
  }
  
  void a(boolean paramBoolean)
  {
    mLayoutOrScrollCounter -= 1;
    if (mLayoutOrScrollCounter < 1)
    {
      mLayoutOrScrollCounter = 0;
      if (paramBoolean)
      {
        dispatchContentChangedIfNecessary();
        next();
      }
    }
  }
  
  boolean a(d0 paramD0, int paramInt)
  {
    if (isComputingLayout())
    {
      j = paramInt;
      this$0.add(paramD0);
      return false;
    }
    ViewCompat.add(itemView, paramInt);
    return true;
  }
  
  void absorbGlows(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
    {
      ensureLeftGlow();
      mLeftGlow.onAbsorb(-paramInt1);
    }
    else if (paramInt1 > 0)
    {
      ensureRightGlow();
      mRightGlow.onAbsorb(paramInt1);
    }
    if (paramInt2 < 0)
    {
      ensureTopGlow();
      mTopGlow.onAbsorb(-paramInt2);
    }
    else if (paramInt2 > 0)
    {
      ensureBottomGlow();
      mBottomGlow.onAbsorb(paramInt2);
    }
    if ((paramInt1 != 0) || (paramInt2 != 0)) {
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  public void addFocusables(ArrayList paramArrayList, int paramInt1, int paramInt2)
  {
    o localO = mLayout;
    if ((localO == null) || (!localO.onAddFocusables(this, paramArrayList, paramInt1, paramInt2))) {
      super.addFocusables(paramArrayList, paramInt1, paramInt2);
    }
  }
  
  public void addItemDecoration(n paramN, int paramInt)
  {
    o localO = mLayout;
    if (localO != null) {
      localO.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
    }
    if (mItemDecorations.isEmpty()) {
      setWillNotDraw(false);
    }
    if (paramInt < 0) {
      mItemDecorations.add(paramN);
    } else {
      mItemDecorations.add(paramInt, paramN);
    }
    markItemDecorInsetsDirty();
    requestLayout();
  }
  
  public void addOnItemTouchListener(s paramS)
  {
    mOnItemTouchListeners.add(paramS);
  }
  
  public void addOnScrollListener(t paramT)
  {
    if (mScrollListeners == null) {
      mScrollListeners = new ArrayList();
    }
    mScrollListeners.add(paramT);
  }
  
  void animateAppearance(d0 paramD0, RecyclerView.l.c paramC1, RecyclerView.l.c paramC2)
  {
    paramD0.setIsRecyclable(false);
    if (mItemAnimator.animateAppearance(paramD0, paramC1, paramC2)) {
      postAnimationRunner();
    }
  }
  
  void animateDisappearance(d0 paramD0, RecyclerView.l.c paramC1, RecyclerView.l.c paramC2)
  {
    addAnimatingView(paramD0);
    paramD0.setIsRecyclable(false);
    if (mItemAnimator.animateDisappearance(paramD0, paramC1, paramC2)) {
      postAnimationRunner();
    }
  }
  
  String append()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(" ");
    localStringBuilder.append(super.toString());
    localStringBuilder.append(", adapter:");
    localStringBuilder.append(mAdapter);
    localStringBuilder.append(", layout:");
    localStringBuilder.append(mLayout);
    localStringBuilder.append(", context:");
    localStringBuilder.append(getContext());
    return localStringBuilder.toString();
  }
  
  boolean canReuseUpdatedViewHolder(d0 paramD0)
  {
    l localL = mItemAnimator;
    return (localL == null) || (localL.canReuseUpdatedViewHolder(paramD0, paramD0.getUnmodifiedPayloads()));
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return ((paramLayoutParams instanceof p)) && (mLayout.checkLayoutParams((p)paramLayoutParams));
  }
  
  void clearOldPositions()
  {
    int j = mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      d0 localD0 = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
      if (!localD0.shouldIgnore()) {
        localD0.clearOldPosition();
      }
      i += 1;
    }
    mRecycler.clearOldPositions();
  }
  
  public int computeHorizontalScrollExtent()
  {
    o localO = mLayout;
    if (localO == null) {
      return 0;
    }
    if (localO.canScrollHorizontally()) {
      return mLayout.computeHorizontalScrollExtent(mState);
    }
    return 0;
  }
  
  public int computeHorizontalScrollOffset()
  {
    o localO = mLayout;
    if (localO == null) {
      return 0;
    }
    if (localO.canScrollHorizontally()) {
      return mLayout.computeHorizontalScrollOffset(mState);
    }
    return 0;
  }
  
  public int computeHorizontalScrollRange()
  {
    o localO = mLayout;
    if (localO == null) {
      return 0;
    }
    if (localO.canScrollHorizontally()) {
      return mLayout.computeHorizontalScrollRange(mState);
    }
    return 0;
  }
  
  public int computeVerticalScrollExtent()
  {
    o localO = mLayout;
    if (localO == null) {
      return 0;
    }
    if (localO.canScrollVertically()) {
      return mLayout.computeVerticalScrollExtent(mState);
    }
    return 0;
  }
  
  public int computeVerticalScrollOffset()
  {
    o localO = mLayout;
    if (localO == null) {
      return 0;
    }
    if (localO.canScrollVertically()) {
      return mLayout.computeVerticalScrollOffset(mState);
    }
    return 0;
  }
  
  public int computeVerticalScrollRange()
  {
    o localO = mLayout;
    if (localO == null) {
      return 0;
    }
    if (localO.canScrollVertically()) {
      return mLayout.computeVerticalScrollRange(mState);
    }
    return 0;
  }
  
  void considerReleasingGlowsOnScroll(int paramInt1, int paramInt2)
  {
    boolean bool2 = false;
    EdgeEffect localEdgeEffect = mLeftGlow;
    boolean bool1 = bool2;
    if (localEdgeEffect != null)
    {
      bool1 = bool2;
      if (!localEdgeEffect.isFinished())
      {
        bool1 = bool2;
        if (paramInt1 > 0)
        {
          mLeftGlow.onRelease();
          bool1 = mLeftGlow.isFinished();
        }
      }
    }
    localEdgeEffect = mRightGlow;
    bool2 = bool1;
    if (localEdgeEffect != null)
    {
      bool2 = bool1;
      if (!localEdgeEffect.isFinished())
      {
        bool2 = bool1;
        if (paramInt1 < 0)
        {
          mRightGlow.onRelease();
          bool2 = bool1 | mRightGlow.isFinished();
        }
      }
    }
    localEdgeEffect = mTopGlow;
    bool1 = bool2;
    if (localEdgeEffect != null)
    {
      bool1 = bool2;
      if (!localEdgeEffect.isFinished())
      {
        bool1 = bool2;
        if (paramInt2 > 0)
        {
          mTopGlow.onRelease();
          bool1 = bool2 | mTopGlow.isFinished();
        }
      }
    }
    localEdgeEffect = mBottomGlow;
    bool2 = bool1;
    if (localEdgeEffect != null)
    {
      bool2 = bool1;
      if (!localEdgeEffect.isFinished())
      {
        bool2 = bool1;
        if (paramInt2 < 0)
        {
          mBottomGlow.onRelease();
          bool2 = bool1 | mBottomGlow.isFinished();
        }
      }
    }
    if (bool2) {
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  void consumePendingUpdateOperations()
  {
    if ((mFirstLayoutComplete) && (!mDataSetHasChangedAfterLayout))
    {
      if (!mAdapterHelper.add()) {
        return;
      }
      if ((mAdapterHelper.add(4)) && (!mAdapterHelper.add(11)))
      {
        TraceCompat.beginSection("RV PartialInvalidate");
        eatRequestLayout();
        onEnterLayoutOrScroll();
        mAdapterHelper.a();
        if (!mLayoutRequestEaten) {
          if (hasUpdatedView()) {
            dispatchLayout();
          } else {
            mAdapterHelper.b();
          }
        }
        resumeRequestLayout(true);
        resumeRequestLayout();
        TraceCompat.endSection();
        return;
      }
      if (mAdapterHelper.add())
      {
        TraceCompat.beginSection("RV FullInvalidate");
        dispatchLayout();
        TraceCompat.endSection();
      }
    }
    else
    {
      TraceCompat.beginSection("RV FullInvalidate");
      dispatchLayout();
      TraceCompat.endSection();
    }
  }
  
  void defaultOnMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(o.chooseSize(paramInt1, getPaddingLeft() + getPaddingRight(), ViewCompat.getMinimumWidth(this)), o.chooseSize(paramInt2, getPaddingTop() + getPaddingBottom(), ViewCompat.getMinimumHeight(this)));
  }
  
  void dispatchChildAttached(View paramView)
  {
    Object localObject = getChildViewHolderInt(paramView);
    onChildAttachedToWindow(paramView);
    g localG = mAdapter;
    if ((localG != null) && (localObject != null)) {
      localG.onViewAttachedToWindow((d0)localObject);
    }
    localObject = mOnChildAttachStateListeners;
    if (localObject != null)
    {
      int i = ((List)localObject).size() - 1;
      while (i >= 0)
      {
        ((q)mOnChildAttachStateListeners.get(i)).onChildViewAttachedToWindow(paramView);
        i -= 1;
      }
    }
  }
  
  void dispatchChildDetached(View paramView)
  {
    Object localObject = getChildViewHolderInt(paramView);
    onChildDetachedFromWindow(paramView);
    g localG = mAdapter;
    if ((localG != null) && (localObject != null)) {
      localG.onViewDetachedFromWindow((d0)localObject);
    }
    localObject = mOnChildAttachStateListeners;
    if (localObject != null)
    {
      int i = ((List)localObject).size() - 1;
      while (i >= 0)
      {
        ((q)mOnChildAttachStateListeners.get(i)).onChildViewDetachedFromWindow(paramView);
        i -= 1;
      }
    }
  }
  
  void dispatchLayout()
  {
    if (mAdapter == null)
    {
      Log.e("RecyclerView", "No adapter attached; skipping layout");
      return;
    }
    if (mLayout == null)
    {
      Log.e("RecyclerView", "No layout manager attached; skipping layout");
      return;
    }
    a0 localA0 = mState;
    mIsMeasuring = false;
    if (mLayoutStep == 1)
    {
      dispatchLayoutStep1();
      mLayout.setExactMeasureSpecsFrom(this);
      dispatchLayoutStep2();
    }
    else if ((!mAdapterHelper.size()) && (mLayout.getWidth() == getWidth()) && (mLayout.getHeight() == getHeight()))
    {
      mLayout.setExactMeasureSpecsFrom(this);
    }
    else
    {
      mLayout.setExactMeasureSpecsFrom(this);
      dispatchLayoutStep2();
    }
    dispatchLayoutStep3();
  }
  
  public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    return getScrollingChildHelper().dispatchNestedFling(paramFloat1, paramFloat2, paramBoolean);
  }
  
  public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2)
  {
    return getScrollingChildHelper().dispatchNestedPreFling(paramFloat1, paramFloat2);
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    return getScrollingChildHelper().dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2);
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt3)
  {
    return getScrollingChildHelper().dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2, paramInt3);
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
  {
    return getScrollingChildHelper().dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt);
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, int paramInt5)
  {
    return getScrollingChildHelper().dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt, paramInt5);
  }
  
  void dispatchOnScrollStateChanged(int paramInt)
  {
    Object localObject = mLayout;
    if (localObject != null) {
      ((o)localObject).onScrollStateChanged(paramInt);
    }
    onScrollStateChanged(paramInt);
    localObject = mScrollListener;
    if (localObject != null) {
      ((t)localObject).onScrollStateChanged(this, paramInt);
    }
    localObject = mScrollListeners;
    if (localObject != null)
    {
      int i = ((List)localObject).size() - 1;
      while (i >= 0)
      {
        ((t)mScrollListeners.get(i)).onScrollStateChanged(this, paramInt);
        i -= 1;
      }
    }
  }
  
  void dispatchOnScrolled(int paramInt1, int paramInt2)
  {
    t += 1;
    int i = getScrollX();
    int j = getScrollY();
    onScrollChanged(i, j, i, j);
    onScrolled(paramInt1, paramInt2);
    Object localObject = mScrollListener;
    if (localObject != null) {
      ((t)localObject).a(this, paramInt1, paramInt2);
    }
    localObject = mScrollListeners;
    if (localObject != null)
    {
      i = ((List)localObject).size() - 1;
      while (i >= 0)
      {
        ((t)mScrollListeners.get(i)).a(this, paramInt1, paramInt2);
        i -= 1;
      }
    }
    t -= 1;
  }
  
  protected void dispatchRestoreInstanceState(SparseArray paramSparseArray)
  {
    dispatchThawSelfOnly(paramSparseArray);
  }
  
  protected void dispatchSaveInstanceState(SparseArray paramSparseArray)
  {
    dispatchFreezeSelfOnly(paramSparseArray);
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    int j = mItemDecorations.size();
    int i = 0;
    while (i < j)
    {
      ((n)mItemDecorations.get(i)).a(paramCanvas, this, mState);
      i += 1;
    }
    i = 0;
    EdgeEffect localEdgeEffect = mLeftGlow;
    int k = 1;
    j = i;
    int m;
    if (localEdgeEffect != null)
    {
      j = i;
      if (!localEdgeEffect.isFinished())
      {
        m = paramCanvas.save();
        if (mClipToPadding) {
          i = getPaddingBottom();
        } else {
          i = 0;
        }
        paramCanvas.rotate(270.0F);
        paramCanvas.translate(-getHeight() + i, 0.0F);
        localEdgeEffect = mLeftGlow;
        if ((localEdgeEffect != null) && (localEdgeEffect.draw(paramCanvas))) {
          j = 1;
        } else {
          j = 0;
        }
        paramCanvas.restoreToCount(m);
      }
    }
    localEdgeEffect = mTopGlow;
    i = j;
    if (localEdgeEffect != null)
    {
      i = j;
      if (!localEdgeEffect.isFinished())
      {
        m = paramCanvas.save();
        if (mClipToPadding) {
          paramCanvas.translate(getPaddingLeft(), getPaddingTop());
        }
        localEdgeEffect = mTopGlow;
        if ((localEdgeEffect != null) && (localEdgeEffect.draw(paramCanvas))) {
          i = 1;
        } else {
          i = 0;
        }
        i = j | i;
        paramCanvas.restoreToCount(m);
      }
    }
    localEdgeEffect = mRightGlow;
    j = i;
    if (localEdgeEffect != null)
    {
      j = i;
      if (!localEdgeEffect.isFinished())
      {
        m = paramCanvas.save();
        int n = getWidth();
        if (mClipToPadding) {
          j = getPaddingTop();
        } else {
          j = 0;
        }
        paramCanvas.rotate(90.0F);
        paramCanvas.translate(-j, -n);
        localEdgeEffect = mRightGlow;
        if ((localEdgeEffect != null) && (localEdgeEffect.draw(paramCanvas))) {
          j = 1;
        } else {
          j = 0;
        }
        j = i | j;
        paramCanvas.restoreToCount(m);
      }
    }
    localEdgeEffect = mBottomGlow;
    i = j;
    if (localEdgeEffect != null)
    {
      i = j;
      if (!localEdgeEffect.isFinished())
      {
        m = paramCanvas.save();
        paramCanvas.rotate(180.0F);
        if (mClipToPadding) {
          paramCanvas.translate(-getWidth() + getPaddingRight(), -getHeight() + getPaddingBottom());
        } else {
          paramCanvas.translate(-getWidth(), -getHeight());
        }
        localEdgeEffect = mBottomGlow;
        if ((localEdgeEffect != null) && (localEdgeEffect.draw(paramCanvas))) {
          i = k;
        } else {
          i = 0;
        }
        i = j | i;
        paramCanvas.restoreToCount(m);
      }
    }
    j = i;
    if (i == 0)
    {
      j = i;
      if (mItemAnimator != null)
      {
        j = i;
        if (mItemDecorations.size() > 0)
        {
          j = i;
          if (mItemAnimator.isRunning()) {
            j = 1;
          }
        }
      }
    }
    if (j != 0) {
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  public boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    return super.drawChild(paramCanvas, paramView, paramLong);
  }
  
  void eatRequestLayout()
  {
    mEatRequestLayout += 1;
    if ((mEatRequestLayout == 1) && (!mLayoutFrozen)) {
      mLayoutRequestEaten = false;
    }
  }
  
  void ensureBottomGlow()
  {
    if (mBottomGlow != null) {
      return;
    }
    mBottomGlow = o.a(this, 3);
    if (mClipToPadding)
    {
      mBottomGlow.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
      return;
    }
    mBottomGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
  }
  
  void ensureLeftGlow()
  {
    if (mLeftGlow != null) {
      return;
    }
    mLeftGlow = o.a(this, 0);
    if (mClipToPadding)
    {
      mLeftGlow.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
      return;
    }
    mLeftGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
  }
  
  void ensureRightGlow()
  {
    if (mRightGlow != null) {
      return;
    }
    mRightGlow = o.a(this, 2);
    if (mClipToPadding)
    {
      mRightGlow.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
      return;
    }
    mRightGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
  }
  
  void ensureTopGlow()
  {
    if (mTopGlow != null) {
      return;
    }
    mTopGlow = o.a(this, 1);
    if (mClipToPadding)
    {
      mTopGlow.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
      return;
    }
    mTopGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
  }
  
  public View findContainingItemView(View paramView)
  {
    ViewParent localViewParent = paramView.getParent();
    View localView = paramView;
    for (paramView = localViewParent; (paramView != null) && (paramView != this) && ((paramView instanceof View)); paramView = localView.getParent()) {
      localView = (View)paramView;
    }
    if (paramView == this) {
      return localView;
    }
    return null;
  }
  
  public d0 findContainingViewHolder(View paramView)
  {
    paramView = findContainingItemView(paramView);
    if (paramView == null) {
      return null;
    }
    return getChildViewHolder(paramView);
  }
  
  public d0 findViewHolderForAdapterPosition(int paramInt)
  {
    if (mDataSetHasChangedAfterLayout) {
      return null;
    }
    int j = mChildHelper.getUnfilteredChildCount();
    Object localObject1 = null;
    int i = 0;
    while (i < j)
    {
      d0 localD0 = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
      Object localObject2 = localObject1;
      if (localD0 != null)
      {
        localObject2 = localObject1;
        if (!localD0.isRemoved())
        {
          localObject2 = localObject1;
          if (getAdapterPositionFor(localD0) == paramInt) {
            if (mChildHelper.isHidden(itemView)) {
              localObject2 = localD0;
            } else {
              return localD0;
            }
          }
        }
      }
      i += 1;
      localObject1 = localObject2;
    }
    return localObject1;
  }
  
  public d0 findViewHolderForItemId(long paramLong)
  {
    Object localObject1 = mAdapter;
    if ((localObject1 != null) && (((g)localObject1).hasStableIds()))
    {
      int j = mChildHelper.getUnfilteredChildCount();
      localObject1 = null;
      int i = 0;
      while (i < j)
      {
        d0 localD0 = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
        Object localObject2 = localObject1;
        if (localD0 != null)
        {
          localObject2 = localObject1;
          if (!localD0.isRemoved())
          {
            localObject2 = localObject1;
            if (localD0.getItemId() == paramLong) {
              if (mChildHelper.isHidden(itemView)) {
                localObject2 = localD0;
              } else {
                return localD0;
              }
            }
          }
        }
        i += 1;
        localObject1 = localObject2;
      }
      return localObject1;
    }
    return null;
  }
  
  d0 findViewHolderForPosition(int paramInt, boolean paramBoolean)
  {
    int j = mChildHelper.getUnfilteredChildCount();
    Object localObject1 = null;
    int i = 0;
    while (i < j)
    {
      d0 localD0 = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
      Object localObject2 = localObject1;
      if (localD0 != null)
      {
        localObject2 = localObject1;
        if (!localD0.isRemoved())
        {
          if (paramBoolean)
          {
            if (mPosition != paramInt)
            {
              localObject2 = localObject1;
              break label115;
            }
          }
          else if (localD0.getLayoutPosition() != paramInt)
          {
            localObject2 = localObject1;
            break label115;
          }
          if (mChildHelper.isHidden(itemView)) {
            localObject2 = localD0;
          } else {
            return localD0;
          }
        }
      }
      label115:
      i += 1;
      localObject1 = localObject2;
    }
    return localObject1;
  }
  
  public boolean fling(int paramInt1, int paramInt2)
  {
    Object localObject = mLayout;
    if (localObject == null)
    {
      Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
      return false;
    }
    if (mLayoutFrozen) {
      return false;
    }
    boolean bool2 = ((o)localObject).canScrollHorizontally();
    boolean bool3 = mLayout.canScrollVertically();
    int i;
    if (bool2)
    {
      i = paramInt1;
      if (Math.abs(paramInt1) >= mMinFlingVelocity) {}
    }
    else
    {
      i = 0;
    }
    int j;
    if (bool3)
    {
      j = paramInt2;
      if (Math.abs(paramInt2) >= mMinFlingVelocity) {}
    }
    else
    {
      j = 0;
    }
    if ((i == 0) && (j == 0)) {
      return false;
    }
    if (!dispatchNestedPreFling(i, j))
    {
      boolean bool1;
      if ((!bool2) && (!bool3)) {
        bool1 = false;
      } else {
        bool1 = true;
      }
      dispatchNestedFling(i, j, bool1);
      localObject = DEBUG;
      if ((localObject != null) && (((r)localObject).set(i, j))) {
        return true;
      }
      if (bool1)
      {
        paramInt1 = 0;
        if (bool2) {
          paramInt1 = 0x0 | 0x1;
        }
        paramInt2 = paramInt1;
        if (bool3) {
          paramInt2 = paramInt1 | 0x2;
        }
        startNestedScroll(paramInt2, 1);
        paramInt1 = mMaxFlingVelocity;
        paramInt1 = Math.max(-paramInt1, Math.min(i, paramInt1));
        paramInt2 = mMaxFlingVelocity;
        paramInt2 = Math.max(-paramInt2, Math.min(j, paramInt2));
        mViewFlinger.fling(paramInt1, paramInt2);
        return true;
      }
    }
    return false;
  }
  
  public View focusSearch(View paramView, int paramInt)
  {
    Object localObject = mLayout.onFocusSearchFailed(paramView, paramInt);
    if (localObject != null) {
      return localObject;
    }
    localObject = mAdapter;
    int n = 1;
    int i;
    if ((localObject != null) && (mLayout != null) && (!isComputingLayout()) && (!mLayoutFrozen)) {
      i = 1;
    } else {
      i = 0;
    }
    localObject = FocusFinder.getInstance();
    int j;
    if ((i != 0) && ((paramInt == 2) || (paramInt == 1)))
    {
      int k = 0;
      i = paramInt;
      if (mLayout.canScrollVertically())
      {
        if (paramInt == 2) {
          j = 130;
        } else {
          j = 33;
        }
        if (((FocusFinder)localObject).findNextFocus(this, paramView, j) == null) {
          m = 1;
        } else {
          m = 0;
        }
        k = m;
        i = paramInt;
        if (L)
        {
          i = j;
          k = m;
        }
      }
      int m = k;
      j = i;
      if (k == 0)
      {
        m = k;
        j = i;
        if (mLayout.canScrollHorizontally())
        {
          if (mLayout.getLayoutDirection() == 1) {
            paramInt = 1;
          } else {
            paramInt = 0;
          }
          if (i == 2) {
            j = 1;
          } else {
            j = 0;
          }
          if ((j ^ paramInt) != 0) {
            paramInt = 66;
          } else {
            paramInt = 17;
          }
          if (((FocusFinder)localObject).findNextFocus(this, paramView, paramInt) == null) {
            j = n;
          } else {
            j = 0;
          }
          k = j;
          m = k;
          j = i;
          if (L)
          {
            j = paramInt;
            m = k;
          }
        }
      }
      if (m != 0)
      {
        consumePendingUpdateOperations();
        if (findContainingItemView(paramView) == null) {
          return null;
        }
        eatRequestLayout();
        mLayout.a(paramView, j, mRecycler, mState);
        resumeRequestLayout(false);
      }
      localObject = ((FocusFinder)localObject).findNextFocus(this, paramView, j);
    }
    else
    {
      View localView2 = ((FocusFinder)localObject).findNextFocus(this, paramView, paramInt);
      View localView1 = localView2;
      localObject = localView1;
      j = paramInt;
      if (localView2 == null)
      {
        localObject = localView1;
        j = paramInt;
        if (i != 0)
        {
          consumePendingUpdateOperations();
          if (findContainingItemView(paramView) == null) {
            return null;
          }
          eatRequestLayout();
          localObject = mLayout.a(paramView, paramInt, mRecycler, mState);
          resumeRequestLayout(false);
          j = paramInt;
        }
      }
    }
    if ((localObject != null) && (!((View)localObject).hasFocusable()))
    {
      if (getFocusedChild() == null) {
        return super.focusSearch(paramView, j);
      }
      draw((View)localObject, null);
      return paramView;
    }
    if (arrowScroll(paramView, (View)localObject, j)) {
      return localObject;
    }
    return super.focusSearch(paramView, j);
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    Object localObject = mLayout;
    if (localObject != null) {
      return ((o)localObject).generateDefaultLayoutParams();
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("RecyclerView has no LayoutManager");
    ((StringBuilder)localObject).append(append());
    throw new IllegalStateException(((StringBuilder)localObject).toString());
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    o localO = mLayout;
    if (localO != null) {
      return localO.generateLayoutParams(getContext(), paramAttributeSet);
    }
    paramAttributeSet = new StringBuilder();
    paramAttributeSet.append("RecyclerView has no LayoutManager");
    paramAttributeSet.append(append());
    throw new IllegalStateException(paramAttributeSet.toString());
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    o localO = mLayout;
    if (localO != null) {
      return localO.generateLayoutParams(paramLayoutParams);
    }
    paramLayoutParams = new StringBuilder();
    paramLayoutParams.append("RecyclerView has no LayoutManager");
    paramLayoutParams.append(append());
    throw new IllegalStateException(paramLayoutParams.toString());
  }
  
  public g getAdapter()
  {
    return mAdapter;
  }
  
  int getAdapterPositionFor(d0 paramD0)
  {
    if ((!paramD0.hasAnyOfTheFlags(524)) && (paramD0.isBound())) {
      return mAdapterHelper.a(mPosition);
    }
    return -1;
  }
  
  public int getBaseline()
  {
    o localO = mLayout;
    if (localO != null) {
      return localO.getBaseline();
    }
    return super.getBaseline();
  }
  
  long getChangedHolderKey(d0 paramD0)
  {
    if (mAdapter.hasStableIds()) {
      return paramD0.getItemId();
    }
    return mPosition;
  }
  
  protected int getChildDrawingOrder(int paramInt1, int paramInt2)
  {
    j localJ = mChildDrawingOrderCallback;
    if (localJ == null) {
      return super.getChildDrawingOrder(paramInt1, paramInt2);
    }
    return localJ.onGetChildDrawingOrder(paramInt1, paramInt2);
  }
  
  public d0 getChildViewHolder(View paramView)
  {
    Object localObject = paramView.getParent();
    if ((localObject != null) && (localObject != this))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("View ");
      ((StringBuilder)localObject).append(paramView);
      ((StringBuilder)localObject).append(" is not a direct child of ");
      ((StringBuilder)localObject).append(this);
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    return getChildViewHolderInt(paramView);
  }
  
  public boolean getClipToPadding()
  {
    return mClipToPadding;
  }
  
  public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate()
  {
    return mAccessibilityDelegate;
  }
  
  public k getEdgeEffectFactory()
  {
    return o;
  }
  
  public l getItemAnimator()
  {
    return mItemAnimator;
  }
  
  Rect getItemDecorInsetsForChild(View paramView)
  {
    p localP = (p)paramView.getLayoutParams();
    if (!mInsetsDirty) {
      return mDecorInsets;
    }
    if ((mState.get()) && ((localP.isItemChanged()) || (localP.isViewInvalid()))) {
      return mDecorInsets;
    }
    Rect localRect1 = mDecorInsets;
    localRect1.set(0, 0, 0, 0);
    int j = mItemDecorations.size();
    int i = 0;
    while (i < j)
    {
      mTempRect.set(0, 0, 0, 0);
      ((n)mItemDecorations.get(i)).getItemOffsets(mTempRect, paramView, this, mState);
      int k = left;
      Rect localRect2 = mTempRect;
      left = (k + left);
      top += top;
      right += right;
      bottom += bottom;
      i += 1;
    }
    mInsetsDirty = false;
    return localRect1;
  }
  
  public int getItemDecorationCount()
  {
    return mItemDecorations.size();
  }
  
  public o getLayoutManager()
  {
    return mLayout;
  }
  
  public int getMaxFlingVelocity()
  {
    return mMaxFlingVelocity;
  }
  
  public int getMinFlingVelocity()
  {
    return mMinFlingVelocity;
  }
  
  long getNanoTime()
  {
    if (c) {
      return System.nanoTime();
    }
    return 0L;
  }
  
  public r getOnFlingListener()
  {
    return DEBUG;
  }
  
  public boolean getPreserveFocusAfterLayout()
  {
    return header;
  }
  
  public u getRecycledViewPool()
  {
    return mRecycler.getRecycledViewPool();
  }
  
  public int getScrollState()
  {
    return mScrollState;
  }
  
  public boolean hasNestedScrollingParent()
  {
    return getScrollingChildHelper().hasNestedScrollingParent();
  }
  
  public boolean hasNestedScrollingParent(int paramInt)
  {
    return getScrollingChildHelper().hasNestedScrollingParent(paramInt);
  }
  
  public boolean hasPendingAdapterUpdates()
  {
    return (!mFirstLayoutComplete) || (mDataSetHasChangedAfterLayout) || (mAdapterHelper.add());
  }
  
  void init(StateListDrawable paramStateListDrawable1, Drawable paramDrawable1, StateListDrawable paramStateListDrawable2, Drawable paramDrawable2)
  {
    if ((paramStateListDrawable1 != null) && (paramDrawable1 != null) && (paramStateListDrawable2 != null) && (paramDrawable2 != null))
    {
      Resources localResources = getContext().getResources();
      new i(this, paramStateListDrawable1, paramDrawable1, paramStateListDrawable2, paramDrawable2, localResources.getDimensionPixelSize(SocketAddress.fastscroll_default_thickness), localResources.getDimensionPixelSize(SocketAddress.fastscroll_minimum_range), localResources.getDimensionPixelOffset(SocketAddress.fastscroll_margin));
      return;
    }
    paramStateListDrawable1 = new StringBuilder();
    paramStateListDrawable1.append("Trying to set fast scroller without both required drawables.");
    paramStateListDrawable1.append(append());
    throw new IllegalArgumentException(paramStateListDrawable1.toString());
  }
  
  void initAdapterManager()
  {
    mAdapterHelper = new f(new f());
  }
  
  void invalidateGlows()
  {
    mBottomGlow = null;
    mTopGlow = null;
    mRightGlow = null;
    mLeftGlow = null;
  }
  
  boolean isAccessibilityEnabled()
  {
    AccessibilityManager localAccessibilityManager = mAccessibilityManager;
    return (localAccessibilityManager != null) && (localAccessibilityManager.isEnabled());
  }
  
  public boolean isAttachedToWindow()
  {
    return mIsAttached;
  }
  
  public boolean isComputingLayout()
  {
    return mLayoutOrScrollCounter > 0;
  }
  
  public boolean isNestedScrollingEnabled()
  {
    return getScrollingChildHelper().isNestedScrollingEnabled();
  }
  
  void markItemDecorInsetsDirty()
  {
    int j = mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      mChildHelper.getUnfilteredChildAt(i).getLayoutParams()).mInsetsDirty = true;
      i += 1;
    }
    mRecycler.markItemDecorInsetsDirty();
  }
  
  void markKnownViewsInvalid()
  {
    int j = mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      d0 localD0 = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
      if ((localD0 != null) && (!localD0.shouldIgnore())) {
        localD0.addFlags(6);
      }
      i += 1;
    }
    markItemDecorInsetsDirty();
    mRecycler.markKnownViewsInvalid();
  }
  
  void next()
  {
    int i = this$0.size() - 1;
    while (i >= 0)
    {
      d0 localD0 = (d0)this$0.get(i);
      if ((itemView.getParent() == this) && (!localD0.shouldIgnore()))
      {
        int j = j;
        if (j != -1)
        {
          ViewCompat.add(itemView, j);
          j = -1;
        }
      }
      i -= 1;
    }
    this$0.clear();
  }
  
  public void offsetChildrenHorizontal(int paramInt)
  {
    int j = mChildHelper.getChildCount();
    int i = 0;
    while (i < j)
    {
      mChildHelper.getChildAt(i).offsetLeftAndRight(paramInt);
      i += 1;
    }
  }
  
  public void offsetChildrenVertical(int paramInt)
  {
    int j = mChildHelper.getChildCount();
    int i = 0;
    while (i < j)
    {
      mChildHelper.getChildAt(i).offsetTopAndBottom(paramInt);
      i += 1;
    }
  }
  
  void offsetPositionRecordsForMove(int paramInt1, int paramInt2)
  {
    int n = mChildHelper.getUnfilteredChildCount();
    int i;
    int j;
    int k;
    if (paramInt1 < paramInt2)
    {
      i = paramInt1;
      j = paramInt2;
      k = -1;
    }
    else
    {
      i = paramInt2;
      j = paramInt1;
      k = 1;
    }
    int m = 0;
    while (m < n)
    {
      d0 localD0 = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(m));
      if (localD0 != null)
      {
        int i1 = mPosition;
        if ((i1 >= i) && (i1 <= j))
        {
          if (i1 == paramInt1) {
            localD0.offsetPosition(paramInt2 - paramInt1, false);
          } else {
            localD0.offsetPosition(k, false);
          }
          mState.mStructureChanged = true;
        }
      }
      m += 1;
    }
    mRecycler.offsetPositionRecordsForMove(paramInt1, paramInt2);
    requestLayout();
  }
  
  void offsetPositionRecordsForRemove(int paramInt1, int paramInt2)
  {
    int j = mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      d0 localD0 = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
      if ((localD0 != null) && (!localD0.shouldIgnore()) && (mPosition >= paramInt1))
      {
        localD0.offsetPosition(paramInt2, false);
        mState.mStructureChanged = true;
      }
      i += 1;
    }
    mRecycler.offsetPositionRecordsForInsert(paramInt1, paramInt2);
    requestLayout();
  }
  
  void offsetPositionRecordsForRemove(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int j = mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      d0 localD0 = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
      if ((localD0 != null) && (!localD0.shouldIgnore()))
      {
        int k = mPosition;
        if (k >= paramInt1 + paramInt2)
        {
          localD0.offsetPosition(-paramInt2, paramBoolean);
          mState.mStructureChanged = true;
        }
        else if (k >= paramInt1)
        {
          localD0.flagRemovedAndOffsetPosition(paramInt1 - 1, -paramInt2, paramBoolean);
          mState.mStructureChanged = true;
        }
      }
      i += 1;
    }
    mRecycler.offsetPositionRecordsForRemove(paramInt1, paramInt2, paramBoolean);
    requestLayout();
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    mLayoutOrScrollCounter = 0;
    boolean bool = true;
    mIsAttached = true;
    if ((!mFirstLayoutComplete) || (isLayoutRequested())) {
      bool = false;
    }
    mFirstLayoutComplete = bool;
    Object localObject = mLayout;
    if (localObject != null) {
      ((o)localObject).dispatchAttachedToWindow(this);
    }
    mPostedAnimatorRunner = false;
    if (c)
    {
      r = ((a)a.t.get());
      if (r == null)
      {
        r = new a();
        localObject = ViewCompat.onCreateView(this);
        float f2 = 60.0F;
        float f1 = f2;
        if (!isInEditMode())
        {
          f1 = f2;
          if (localObject != null)
          {
            float f3 = ((Display)localObject).getRefreshRate();
            f1 = f2;
            if (f3 >= 30.0F) {
              f1 = f3;
            }
          }
        }
        localObject = r;
        n = ((1.0E9F / f1));
        a.t.set(localObject);
      }
      r.a(this);
    }
  }
  
  public void onChildAttachedToWindow(View paramView) {}
  
  public void onChildDetachedFromWindow(View paramView) {}
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    Object localObject = mItemAnimator;
    if (localObject != null) {
      ((l)localObject).endAnimations();
    }
    stopScroll();
    mIsAttached = false;
    localObject = mLayout;
    if (localObject != null) {
      ((o)localObject).onDetachedFromWindow(this, mRecycler);
    }
    this$0.clear();
    removeCallbacks(mItemAnimatorRunner);
    mViewInfoStore.onDetach();
    if (c)
    {
      localObject = r;
      if (localObject != null)
      {
        ((a)localObject).close(this);
        r = null;
      }
    }
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int j = mItemDecorations.size();
    int i = 0;
    while (i < j)
    {
      ((n)mItemDecorations.get(i)).onDraw(paramCanvas, this, mState);
      i += 1;
    }
  }
  
  void onEnterLayoutOrScroll()
  {
    mLayoutOrScrollCounter += 1;
  }
  
  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    if (mLayout == null) {
      return false;
    }
    if (mLayoutFrozen) {
      return false;
    }
    if (paramMotionEvent.getAction() == 8)
    {
      float f1;
      float f2;
      if ((paramMotionEvent.getSource() & 0x2) != 0)
      {
        if (mLayout.canScrollVertically()) {
          f1 = -paramMotionEvent.getAxisValue(9);
        } else {
          f1 = 0.0F;
        }
        if (mLayout.canScrollHorizontally()) {
          f2 = paramMotionEvent.getAxisValue(10);
        } else {
          f2 = 0.0F;
        }
      }
      else if ((paramMotionEvent.getSource() & 0x400000) != 0)
      {
        f2 = paramMotionEvent.getAxisValue(26);
        if (mLayout.canScrollVertically())
        {
          f1 = -f2;
          f2 = 0.0F;
        }
        else if (mLayout.canScrollHorizontally())
        {
          f1 = 0.0F;
        }
        else
        {
          f2 = 0.0F;
          f1 = 0.0F;
        }
      }
      else
      {
        f1 = 0.0F;
        f2 = 0.0F;
      }
      if ((f1 != 0.0F) || (f2 != 0.0F)) {
        scrollByInternal((int)(mScrollFactor * f2), (int)(response * f1), paramMotionEvent);
      }
    }
    return false;
  }
  
  final void onInterceptTouchEvent(a0 paramA0)
  {
    if (getScrollState() == 2)
    {
      OverScroller localOverScroller = mViewFlinger.mScroller;
      mCoordOffset = (localOverScroller.getFinalX() - localOverScroller.getCurrX());
      mDragPoint = (localOverScroller.getFinalY() - localOverScroller.getCurrY());
      return;
    }
    mCoordOffset = 0;
    mDragPoint = 0;
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (mLayoutFrozen) {
      return false;
    }
    if (dispatchOnItemTouchIntercept(paramMotionEvent))
    {
      cancelTouch();
      return true;
    }
    o localO = mLayout;
    if (localO == null) {
      return false;
    }
    boolean bool1 = localO.canScrollHorizontally();
    boolean bool2 = mLayout.canScrollVertically();
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
    mVelocityTracker.addMovement(paramMotionEvent);
    int j = paramMotionEvent.getActionMasked();
    int i = paramMotionEvent.getActionIndex();
    if (j != 0)
    {
      if (j != 1)
      {
        if (j != 2)
        {
          if (j != 3)
          {
            if (j != 5)
            {
              if (j == 6) {
                onPointerUp(paramMotionEvent);
              }
            }
            else
            {
              mScrollPointerId = paramMotionEvent.getPointerId(i);
              j = (int)(paramMotionEvent.getX(i) + 0.5F);
              mLastTouchY = j;
              mInitialTouchY = j;
              i = (int)(paramMotionEvent.getY(i) + 0.5F);
              mLastTouchX = i;
              mInitialTouchX = i;
            }
          }
          else {
            cancelTouch();
          }
        }
        else
        {
          i = paramMotionEvent.findPointerIndex(mScrollPointerId);
          if (i < 0)
          {
            paramMotionEvent = new StringBuilder();
            paramMotionEvent.append("Error processing scroll; pointer index for id ");
            paramMotionEvent.append(mScrollPointerId);
            paramMotionEvent.append(" not found. Did any MotionEvents get skipped?");
            Log.e("RecyclerView", paramMotionEvent.toString());
            return false;
          }
          int n = (int)(paramMotionEvent.getX(i) + 0.5F);
          int k = (int)(paramMotionEvent.getY(i) + 0.5F);
          if (mScrollState != 1)
          {
            int i1 = mInitialTouchY;
            int m = mInitialTouchX;
            j = 0;
            i = j;
            if (bool1)
            {
              i = j;
              if (Math.abs(n - i1) > mTouchSlop)
              {
                mLastTouchY = n;
                i = 1;
              }
            }
            j = i;
            if (bool2)
            {
              j = i;
              if (Math.abs(k - m) > mTouchSlop)
              {
                mLastTouchX = k;
                j = 1;
              }
            }
            if (j != 0) {
              setScrollState(1);
            }
          }
        }
      }
      else
      {
        mVelocityTracker.clear();
        stopNestedScroll(0);
      }
    }
    else
    {
      if (mIgnoreMotionEventTillDown) {
        mIgnoreMotionEventTillDown = false;
      }
      mScrollPointerId = paramMotionEvent.getPointerId(0);
      i = (int)(paramMotionEvent.getX() + 0.5F);
      mLastTouchY = i;
      mInitialTouchY = i;
      i = (int)(paramMotionEvent.getY() + 0.5F);
      mLastTouchX = i;
      mInitialTouchX = i;
      if (mScrollState == 2)
      {
        getParent().requestDisallowInterceptTouchEvent(true);
        setScrollState(1);
      }
      paramMotionEvent = mNestedOffsets;
      paramMotionEvent[1] = 0;
      paramMotionEvent[0] = 0;
      i = 0;
      if (bool1) {
        i = 0x0 | 0x1;
      }
      j = i;
      if (bool2) {
        j = i | 0x2;
      }
      startNestedScroll(j, 0);
    }
    return mScrollState == 1;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    TraceCompat.beginSection("RV OnLayout");
    dispatchLayout();
    TraceCompat.endSection();
    mFirstLayoutComplete = true;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    Object localObject = mLayout;
    if (localObject == null)
    {
      defaultOnMeasure(paramInt1, paramInt2);
      return;
    }
    boolean bool = ((o)localObject).setOrientation();
    int j = 0;
    if (bool)
    {
      int k = View.MeasureSpec.getMode(paramInt1);
      int m = View.MeasureSpec.getMode(paramInt2);
      mLayout.onMeasure(mRecycler, mState, paramInt1, paramInt2);
      int i = j;
      if (k == 1073741824)
      {
        i = j;
        if (m == 1073741824) {
          i = 1;
        }
      }
      if (i == 0)
      {
        if (mAdapter == null) {
          return;
        }
        if (mState.mLayoutStep == 1) {
          dispatchLayoutStep1();
        }
        mLayout.setMeasureSpecs(paramInt1, paramInt2);
        mState.mIsMeasuring = true;
        dispatchLayoutStep2();
        mLayout.draw(paramInt1, paramInt2);
        if (mLayout.shouldMeasureTwice())
        {
          mLayout.setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
          mState.mIsMeasuring = true;
          dispatchLayoutStep2();
          mLayout.draw(paramInt1, paramInt2);
        }
      }
      return;
    }
    if (mHasFixedSize)
    {
      mLayout.onMeasure(mRecycler, mState, paramInt1, paramInt2);
      return;
    }
    if (mAdapterUpdateDuringMeasure)
    {
      eatRequestLayout();
      onEnterLayoutOrScroll();
      processAdapterUpdatesAndSetAnimationFlags();
      resumeRequestLayout();
      localObject = mState;
      if (mRunPredictiveAnimations)
      {
        mInPreLayout = true;
      }
      else
      {
        mAdapterHelper.c();
        mState.mInPreLayout = false;
      }
      mAdapterUpdateDuringMeasure = false;
      resumeRequestLayout(false);
    }
    else if (mState.mRunPredictiveAnimations)
    {
      setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
      return;
    }
    localObject = mAdapter;
    if (localObject != null) {
      mState.mItemCount = ((g)localObject).a();
    } else {
      mState.mItemCount = 0;
    }
    eatRequestLayout();
    mLayout.onMeasure(mRecycler, mState, paramInt1, paramInt2);
    resumeRequestLayout(false);
    mState.mInPreLayout = false;
  }
  
  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect)
  {
    if (isComputingLayout()) {
      return false;
    }
    return super.onRequestFocusInDescendants(paramInt, paramRect);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof y))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    mPendingSavedState = ((y)paramParcelable);
    super.onRestoreInstanceState(mPendingSavedState.getSuperState());
    paramParcelable = mLayout;
    if (paramParcelable != null)
    {
      Parcelable localParcelable = mPendingSavedState.mLayoutState;
      if (localParcelable != null) {
        paramParcelable.onRestoreInstanceState(localParcelable);
      }
    }
  }
  
  protected Parcelable onSaveInstanceState()
  {
    y localY = new y(super.onSaveInstanceState());
    Object localObject = mPendingSavedState;
    if (localObject != null)
    {
      localY.access$1900((y)localObject);
      return localY;
    }
    localObject = mLayout;
    if (localObject != null)
    {
      mLayoutState = ((o)localObject).a();
      return localY;
    }
    mLayoutState = null;
    return localY;
  }
  
  public void onScrollStateChanged(int paramInt) {}
  
  public void onScrolled(int paramInt1, int paramInt2) {}
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if ((paramInt1 != paramInt3) || (paramInt2 != paramInt4)) {
      invalidateGlows();
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool1 = mLayoutFrozen;
    int i2 = 0;
    if (!bool1)
    {
      if (mIgnoreMotionEventTillDown) {
        return false;
      }
      if (dispatchOnItemTouch(paramMotionEvent))
      {
        cancelTouch();
        return true;
      }
      Object localObject = mLayout;
      if (localObject == null) {
        return false;
      }
      bool1 = ((o)localObject).canScrollHorizontally();
      boolean bool2 = mLayout.canScrollVertically();
      if (mVelocityTracker == null) {
        mVelocityTracker = VelocityTracker.obtain();
      }
      int i1 = 0;
      localObject = MotionEvent.obtain(paramMotionEvent);
      int j = paramMotionEvent.getActionMasked();
      int i = paramMotionEvent.getActionIndex();
      if (j == 0)
      {
        arrayOfInt = mNestedOffsets;
        arrayOfInt[1] = 0;
        arrayOfInt[0] = 0;
      }
      int[] arrayOfInt = mNestedOffsets;
      ((MotionEvent)localObject).offsetLocation(arrayOfInt[0], arrayOfInt[1]);
      if (j != 0)
      {
        if (j != 1)
        {
          if (j != 2)
          {
            if (j != 3)
            {
              if (j != 5)
              {
                if (j != 6)
                {
                  i = i1;
                }
                else
                {
                  onPointerUp(paramMotionEvent);
                  i = i1;
                }
              }
              else
              {
                mScrollPointerId = paramMotionEvent.getPointerId(i);
                j = (int)(paramMotionEvent.getX(i) + 0.5F);
                mLastTouchY = j;
                mInitialTouchY = j;
                i = (int)(paramMotionEvent.getY(i) + 0.5F);
                mLastTouchX = i;
                mInitialTouchX = i;
                i = i1;
              }
            }
            else
            {
              cancelTouch();
              i = i1;
            }
          }
          else
          {
            i = paramMotionEvent.findPointerIndex(mScrollPointerId);
            if (i < 0)
            {
              paramMotionEvent = new StringBuilder();
              paramMotionEvent.append("Error processing scroll; pointer index for id ");
              paramMotionEvent.append(mScrollPointerId);
              paramMotionEvent.append(" not found. Did any MotionEvents get skipped?");
              Log.e("RecyclerView", paramMotionEvent.toString());
              return false;
            }
            int i3 = (int)(paramMotionEvent.getX(i) + 0.5F);
            int i4 = (int)(paramMotionEvent.getY(i) + 0.5F);
            int m = mLastTouchY - i3;
            int k = mLastTouchX - i4;
            j = m;
            i = k;
            if (dispatchNestedPreScroll(m, k, mScrollConsumed, mScrollOffset, 0))
            {
              paramMotionEvent = mScrollConsumed;
              j = m - paramMotionEvent[0];
              i = k - paramMotionEvent[1];
              paramMotionEvent = mScrollOffset;
              ((MotionEvent)localObject).offsetLocation(paramMotionEvent[0], paramMotionEvent[1]);
              paramMotionEvent = mNestedOffsets;
              k = paramMotionEvent[0];
              arrayOfInt = mScrollOffset;
              paramMotionEvent[0] = (k + arrayOfInt[0]);
              paramMotionEvent[1] += arrayOfInt[1];
            }
            k = j;
            m = i;
            if (mScrollState != 1)
            {
              int n = 0;
              k = j;
              m = n;
              int i5;
              int i6;
              if (bool1)
              {
                i5 = Math.abs(j);
                i6 = mTouchSlop;
                k = j;
                m = n;
                if (i5 > i6)
                {
                  if (j > 0) {
                    k = j - i6;
                  } else {
                    k = j + i6;
                  }
                  m = 1;
                }
              }
              j = i;
              n = m;
              if (bool2)
              {
                i5 = Math.abs(i);
                i6 = mTouchSlop;
                j = i;
                n = m;
                if (i5 > i6)
                {
                  if (i > 0) {
                    j = i - i6;
                  } else {
                    j = i + i6;
                  }
                  n = 1;
                }
              }
              if (n != 0) {
                setScrollState(1);
              }
              m = j;
            }
            if (mScrollState == 1)
            {
              paramMotionEvent = mScrollOffset;
              mLastTouchY = (i3 - paramMotionEvent[0]);
              mLastTouchX = (i4 - paramMotionEvent[1]);
              if (bool1) {
                i = k;
              } else {
                i = 0;
              }
              j = i2;
              if (bool2) {
                j = m;
              }
              if (scrollByInternal(i, j, (MotionEvent)localObject)) {
                getParent().requestDisallowInterceptTouchEvent(true);
              }
              if ((r != null) && ((k != 0) || (m != 0))) {
                r.a(this, k, m);
              }
            }
            i = i1;
          }
        }
        else
        {
          mVelocityTracker.addMovement((MotionEvent)localObject);
          i = 1;
          mVelocityTracker.computeCurrentVelocity(1000, mMaxFlingVelocity);
          float f1;
          if (bool1) {
            f1 = -mVelocityTracker.getXVelocity(mScrollPointerId);
          } else {
            f1 = 0.0F;
          }
          float f2;
          if (bool2) {
            f2 = -mVelocityTracker.getYVelocity(mScrollPointerId);
          } else {
            f2 = 0.0F;
          }
          if (((f1 == 0.0F) && (f2 == 0.0F)) || (!fling((int)f1, (int)f2))) {
            setScrollState(0);
          }
          resetTouch();
        }
      }
      else
      {
        mScrollPointerId = paramMotionEvent.getPointerId(0);
        i = (int)(paramMotionEvent.getX() + 0.5F);
        mLastTouchY = i;
        mInitialTouchY = i;
        i = (int)(paramMotionEvent.getY() + 0.5F);
        mLastTouchX = i;
        mInitialTouchX = i;
        i = 0;
        if (bool1) {
          i = 0x0 | 0x1;
        }
        j = i;
        if (bool2) {
          j = i | 0x2;
        }
        startNestedScroll(j, 0);
        i = i1;
      }
      if (i == 0) {
        mVelocityTracker.addMovement((MotionEvent)localObject);
      }
      ((MotionEvent)localObject).recycle();
      return true;
    }
    return false;
  }
  
  void postAnimationRunner()
  {
    if ((!mPostedAnimatorRunner) && (mIsAttached))
    {
      ViewCompat.postOnAnimation(this, mItemAnimatorRunner);
      mPostedAnimatorRunner = true;
    }
  }
  
  void recordAnimationInfoIfBouncedHiddenView(d0 paramD0, RecyclerView.l.c paramC)
  {
    paramD0.setFlags(0, 8192);
    if ((mState.mTrackOldChangeHolders) && (paramD0.isUpdated()) && (!paramD0.isRemoved()) && (!paramD0.shouldIgnore()))
    {
      long l = getChangedHolderKey(paramD0);
      mViewInfoStore.addToOldChangeHolders(l, paramD0);
    }
    mViewInfoStore.addToPreLayout(paramD0, paramC);
  }
  
  boolean removeAnimatingView(View paramView)
  {
    eatRequestLayout();
    boolean bool = mChildHelper.removeViewIfHidden(paramView);
    if (bool)
    {
      paramView = getChildViewHolderInt(paramView);
      mRecycler.unscrapView(paramView);
      mRecycler.recycleViewHolderInternal(paramView);
    }
    resumeRequestLayout(bool ^ true);
    return bool;
  }
  
  protected void removeDetachedView(View paramView, boolean paramBoolean)
  {
    d0 localD0 = getChildViewHolderInt(paramView);
    if (localD0 != null) {
      if (localD0.isTmpDetached())
      {
        localD0.clearTmpDetachFlag();
      }
      else if (!localD0.shouldIgnore())
      {
        paramView = new StringBuilder();
        paramView.append("Called removeDetachedView with a view which is not flagged as tmp detached.");
        paramView.append(localD0);
        paramView.append(append());
        throw new IllegalArgumentException(paramView.toString());
      }
    }
    paramView.clearAnimation();
    dispatchChildDetached(paramView);
    super.removeDetachedView(paramView, paramBoolean);
  }
  
  public void removeItemDecoration(n paramN)
  {
    o localO = mLayout;
    if (localO != null) {
      localO.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
    }
    mItemDecorations.remove(paramN);
    if (mItemDecorations.isEmpty())
    {
      boolean bool;
      if (getOverScrollMode() == 2) {
        bool = true;
      } else {
        bool = false;
      }
      setWillNotDraw(bool);
    }
    markItemDecorInsetsDirty();
    requestLayout();
  }
  
  public void removeOnItemTouchListener(s paramS)
  {
    mOnItemTouchListeners.remove(paramS);
    if (mActiveOnItemTouchListener == paramS) {
      mActiveOnItemTouchListener = null;
    }
  }
  
  public void removeOnScrollListener(t paramT)
  {
    List localList = mScrollListeners;
    if (localList != null) {
      localList.remove(paramT);
    }
  }
  
  void repositionShadowingViews()
  {
    int j = mChildHelper.getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = mChildHelper.getChildAt(i);
      Object localObject = getChildViewHolder(localView);
      if (localObject != null)
      {
        localObject = mShadowingHolder;
        if (localObject != null)
        {
          localObject = itemView;
          int k = localView.getLeft();
          int m = localView.getTop();
          if ((k != ((View)localObject).getLeft()) || (m != ((View)localObject).getTop())) {
            ((View)localObject).layout(k, m, ((View)localObject).getWidth() + k, ((View)localObject).getHeight() + m);
          }
        }
      }
      i += 1;
    }
  }
  
  public void requestChildFocus(View paramView1, View paramView2)
  {
    if ((!mLayout.onRequestChildFocus(this, mState, paramView1, paramView2)) && (paramView2 != null)) {
      draw(paramView1, paramView2);
    }
    super.requestChildFocus(paramView1, paramView2);
  }
  
  public boolean requestChildRectangleOnScreen(View paramView, Rect paramRect, boolean paramBoolean)
  {
    return mLayout.requestChildRectangleOnScreen(this, paramView, paramRect, paramBoolean);
  }
  
  public void requestDisallowInterceptTouchEvent(boolean paramBoolean)
  {
    int j = mOnItemTouchListeners.size();
    int i = 0;
    while (i < j)
    {
      ((s)mOnItemTouchListeners.get(i)).onRequestDisallowInterceptTouchEvent(paramBoolean);
      i += 1;
    }
    super.requestDisallowInterceptTouchEvent(paramBoolean);
  }
  
  public void requestLayout()
  {
    if ((mEatRequestLayout == 0) && (!mLayoutFrozen))
    {
      super.requestLayout();
      return;
    }
    mLayoutRequestEaten = true;
  }
  
  void resumeRequestLayout()
  {
    a(true);
  }
  
  void resumeRequestLayout(boolean paramBoolean)
  {
    if (mEatRequestLayout < 1) {
      mEatRequestLayout = 1;
    }
    if ((!paramBoolean) && (!mLayoutFrozen)) {
      mLayoutRequestEaten = false;
    }
    if (mEatRequestLayout == 1)
    {
      if ((paramBoolean) && (mLayoutRequestEaten) && (!mLayoutFrozen) && (mLayout != null) && (mAdapter != null)) {
        dispatchLayout();
      }
      if (!mLayoutFrozen) {
        mLayoutRequestEaten = false;
      }
    }
    mEatRequestLayout -= 1;
  }
  
  void saveOldPositions()
  {
    int j = mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      d0 localD0 = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
      if (!localD0.shouldIgnore()) {
        localD0.saveOldPosition();
      }
      i += 1;
    }
  }
  
  public void scrollBy(int paramInt1, int paramInt2)
  {
    o localO = mLayout;
    if (localO == null)
    {
      Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
      return;
    }
    if (mLayoutFrozen) {
      return;
    }
    boolean bool1 = localO.canScrollHorizontally();
    boolean bool2 = mLayout.canScrollVertically();
    if ((bool1) || (bool2))
    {
      int i = 0;
      if (!bool1) {
        paramInt1 = 0;
      }
      if (bool2) {
        i = paramInt2;
      }
      scrollByInternal(paramInt1, i, null);
    }
  }
  
  void scrollByInternal(int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    eatRequestLayout();
    onEnterLayoutOrScroll();
    TraceCompat.beginSection("RV Scroll");
    onInterceptTouchEvent(mState);
    int i = 0;
    int j = 0;
    if (paramInt1 != 0) {
      i = mLayout.scrollHorizontallyBy(paramInt1, mRecycler, mState);
    }
    paramInt1 = j;
    if (paramInt2 != 0) {
      paramInt1 = mLayout.scrollVerticallyBy(paramInt2, mRecycler, mState);
    }
    TraceCompat.endSection();
    repositionShadowingViews();
    resumeRequestLayout();
    resumeRequestLayout(false);
    if (paramArrayOfInt != null)
    {
      paramArrayOfInt[0] = i;
      paramArrayOfInt[1] = paramInt1;
    }
  }
  
  boolean scrollByInternal(int paramInt1, int paramInt2, MotionEvent paramMotionEvent)
  {
    consumePendingUpdateOperations();
    int[] arrayOfInt;
    int i;
    int j;
    int k;
    int m;
    if (mAdapter != null)
    {
      scrollByInternal(paramInt1, paramInt2, itemView);
      arrayOfInt = itemView;
      i = arrayOfInt[0];
      j = arrayOfInt[1];
      k = paramInt1 - i;
      m = paramInt2 - j;
    }
    else
    {
      k = 0;
      m = 0;
      i = 0;
      j = 0;
    }
    if (!mItemDecorations.isEmpty()) {
      invalidate();
    }
    if (dispatchNestedScroll(i, j, k, m, mScrollOffset, 0))
    {
      paramInt1 = mLastTouchY;
      arrayOfInt = mScrollOffset;
      mLastTouchY = (paramInt1 - arrayOfInt[0]);
      mLastTouchX -= arrayOfInt[1];
      if (paramMotionEvent != null) {
        paramMotionEvent.offsetLocation(arrayOfInt[0], arrayOfInt[1]);
      }
      paramMotionEvent = mNestedOffsets;
      paramInt1 = paramMotionEvent[0];
      arrayOfInt = mScrollOffset;
      paramMotionEvent[0] = (paramInt1 + arrayOfInt[0]);
      paramMotionEvent[1] += arrayOfInt[1];
    }
    else if (getOverScrollMode() != 2)
    {
      if ((paramMotionEvent != null) && (!Type.a(paramMotionEvent, 8194))) {
        pullGlows(paramMotionEvent.getX(), k, paramMotionEvent.getY(), m);
      }
      considerReleasingGlowsOnScroll(paramInt1, paramInt2);
    }
    if (i == 0) {
      if (j == 0) {
        break label257;
      }
    }
    dispatchOnScrolled(i, j);
    label257:
    if (!awakenScrollBars()) {
      invalidate();
    }
    return (i != 0) || (j != 0);
  }
  
  public void scrollTo(int paramInt1, int paramInt2)
  {
    Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
  }
  
  public void scrollToPosition(int paramInt)
  {
    if (mLayoutFrozen) {
      return;
    }
    stopScroll();
    o localO = mLayout;
    if (localO == null)
    {
      Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
      return;
    }
    localO.e(paramInt);
    awakenScrollBars();
  }
  
  public void sendAccessibilityEventUnchecked(AccessibilityEvent paramAccessibilityEvent)
  {
    if (shouldDeferAccessibilityEvent(paramAccessibilityEvent)) {
      return;
    }
    super.sendAccessibilityEventUnchecked(paramAccessibilityEvent);
  }
  
  public void setAccessibilityDelegateCompat(RecyclerViewAccessibilityDelegate paramRecyclerViewAccessibilityDelegate)
  {
    mAccessibilityDelegate = paramRecyclerViewAccessibilityDelegate;
    ViewCompat.setAccessibilityDelegate(this, mAccessibilityDelegate);
  }
  
  public void setAdapter(g paramG)
  {
    setLayoutFrozen(false);
    setAdapterInternal(paramG, false, true);
    setAdapterInternal(false);
    requestLayout();
  }
  
  public void setAdapter(n paramN)
  {
    addItemDecoration(paramN, -1);
  }
  
  void setAdapterInternal()
  {
    Object localObject = mItemAnimator;
    if (localObject != null) {
      ((l)localObject).endAnimations();
    }
    localObject = mLayout;
    if (localObject != null)
    {
      ((o)localObject).removeAndRecycleAllViews(mRecycler);
      mLayout.removeAndRecycleScrapInt(mRecycler);
    }
    mRecycler.clear();
  }
  
  void setAdapterInternal(boolean paramBoolean)
  {
    mRunningLayoutOrScroll |= paramBoolean;
    mDataSetHasChangedAfterLayout = true;
    markKnownViewsInvalid();
  }
  
  public void setChildDrawingOrderCallback(j paramJ)
  {
    if (paramJ == mChildDrawingOrderCallback) {
      return;
    }
    mChildDrawingOrderCallback = paramJ;
    boolean bool;
    if (mChildDrawingOrderCallback != null) {
      bool = true;
    } else {
      bool = false;
    }
    setChildrenDrawingOrderEnabled(bool);
  }
  
  public void setClipToPadding(boolean paramBoolean)
  {
    if (paramBoolean != mClipToPadding) {
      invalidateGlows();
    }
    mClipToPadding = paramBoolean;
    super.setClipToPadding(paramBoolean);
    if (mFirstLayoutComplete) {
      requestLayout();
    }
  }
  
  public void setEdgeEffectFactory(k paramK)
  {
    ClassReader.a(paramK);
    o = paramK;
    invalidateGlows();
  }
  
  public void setHasFixedSize(boolean paramBoolean)
  {
    mHasFixedSize = paramBoolean;
  }
  
  public void setItemAnimator(l paramL)
  {
    l localL = mItemAnimator;
    if (localL != null)
    {
      localL.endAnimations();
      mItemAnimator.setListener(null);
    }
    mItemAnimator = paramL;
    paramL = mItemAnimator;
    if (paramL != null) {
      paramL.setListener(mItemAnimatorListener);
    }
  }
  
  public void setItemViewCacheSize(int paramInt)
  {
    mRecycler.b(paramInt);
  }
  
  public void setLayoutFrozen(boolean paramBoolean)
  {
    if (paramBoolean != mLayoutFrozen)
    {
      a("Do not setLayoutFrozen in layout or scroll");
      if (!paramBoolean)
      {
        mLayoutFrozen = false;
        if ((mLayoutRequestEaten) && (mLayout != null) && (mAdapter != null)) {
          requestLayout();
        }
        mLayoutRequestEaten = false;
        return;
      }
      long l = SystemClock.uptimeMillis();
      onTouchEvent(MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0));
      mLayoutFrozen = true;
      mIgnoreMotionEventTillDown = true;
      stopScroll();
    }
  }
  
  public void setLayoutManager(o paramO)
  {
    if (paramO == mLayout) {
      return;
    }
    stopScroll();
    Object localObject;
    if (mLayout != null)
    {
      localObject = mItemAnimator;
      if (localObject != null) {
        ((l)localObject).endAnimations();
      }
      mLayout.removeAndRecycleAllViews(mRecycler);
      mLayout.removeAndRecycleScrapInt(mRecycler);
      mRecycler.clear();
      if (mIsAttached) {
        mLayout.onDetachedFromWindow(this, mRecycler);
      }
      mLayout.setRecyclerView(null);
      mLayout = null;
    }
    else
    {
      mRecycler.clear();
    }
    mChildHelper.removeAllViewsUnfiltered();
    mLayout = paramO;
    if (paramO != null) {
      if (mRecyclerView == null)
      {
        mLayout.setRecyclerView(this);
        if (mIsAttached) {
          mLayout.dispatchAttachedToWindow(this);
        }
      }
      else
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("LayoutManager ");
        ((StringBuilder)localObject).append(paramO);
        ((StringBuilder)localObject).append(" is already attached to a RecyclerView:");
        ((StringBuilder)localObject).append(mRecyclerView.append());
        throw new IllegalArgumentException(((StringBuilder)localObject).toString());
      }
    }
    mRecycler.b();
    requestLayout();
  }
  
  public void setNestedScrollingEnabled(boolean paramBoolean)
  {
    getScrollingChildHelper().setNestedScrollingEnabled(paramBoolean);
  }
  
  public void setOnFlingListener(r paramR)
  {
    DEBUG = paramR;
  }
  
  public void setOnScrollListener(t paramT)
  {
    mScrollListener = paramT;
  }
  
  public void setPreserveFocusAfterLayout(boolean paramBoolean)
  {
    header = paramBoolean;
  }
  
  public void setRecycledViewPool(u paramU)
  {
    mRecycler.setRecycledViewPool(paramU);
  }
  
  public void setRecyclerListener(w paramW)
  {
    bitmap = paramW;
  }
  
  void setScrollState(int paramInt)
  {
    if (paramInt == mScrollState) {
      return;
    }
    mScrollState = paramInt;
    if (paramInt != 2) {
      stopScrollersInternal();
    }
    dispatchOnScrollStateChanged(paramInt);
  }
  
  public void setScrollingTouchSlop(int paramInt)
  {
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(getContext());
    if (paramInt != 0) {
      if (paramInt != 1)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("setScrollingTouchSlop(): bad argument constant ");
        localStringBuilder.append(paramInt);
        localStringBuilder.append("; using default value");
        Log.w("RecyclerView", localStringBuilder.toString());
      }
      else
      {
        mTouchSlop = localViewConfiguration.getScaledPagingTouchSlop();
        return;
      }
    }
    mTouchSlop = localViewConfiguration.getScaledTouchSlop();
  }
  
  public void setViewCacheExtension(b0 paramB0)
  {
    mRecycler.setViewCacheExtension(paramB0);
  }
  
  boolean shouldDeferAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if (isComputingLayout())
    {
      int i = 0;
      if (paramAccessibilityEvent != null) {
        i = AccessibilityEventCompat.getContentChangeTypes(paramAccessibilityEvent);
      }
      int j = i;
      if (i == 0) {
        j = 0;
      }
      mEatenAccessibilityChangeFlags |= j;
      return true;
    }
    return false;
  }
  
  public void smoothScrollBy(int paramInt1, int paramInt2)
  {
    smoothScrollBy(paramInt1, paramInt2, null);
  }
  
  public void smoothScrollBy(int paramInt1, int paramInt2, Interpolator paramInterpolator)
  {
    o localO = mLayout;
    if (localO == null)
    {
      Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
      return;
    }
    if (mLayoutFrozen) {
      return;
    }
    if (!localO.canScrollHorizontally()) {
      paramInt1 = 0;
    }
    if (!mLayout.canScrollVertically()) {
      paramInt2 = 0;
    }
    if ((paramInt1 != 0) || (paramInt2 != 0)) {
      mViewFlinger.smoothScrollBy(paramInt1, paramInt2, paramInterpolator);
    }
  }
  
  public boolean startNestedScroll(int paramInt)
  {
    return getScrollingChildHelper().startNestedScroll(paramInt);
  }
  
  public boolean startNestedScroll(int paramInt1, int paramInt2)
  {
    return getScrollingChildHelper().startNestedScroll(paramInt1, paramInt2);
  }
  
  public void stopNestedScroll()
  {
    getScrollingChildHelper().stopNestedScroll();
  }
  
  public void stopNestedScroll(int paramInt)
  {
    getScrollingChildHelper().stopNestedScroll(paramInt);
  }
  
  public void stopScroll()
  {
    setScrollState(0);
    stopScrollersInternal();
  }
  
  void viewRangeUpdate(int paramInt1, int paramInt2, Object paramObject)
  {
    int j = mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = mChildHelper.getUnfilteredChildAt(i);
      d0 localD0 = getChildViewHolderInt(localView);
      if ((localD0 != null) && (!localD0.shouldIgnore()))
      {
        int k = mPosition;
        if ((k >= paramInt1) && (k < paramInt1 + paramInt2))
        {
          localD0.addFlags(2);
          localD0.addChangePayload(paramObject);
          getLayoutParamsmInsetsDirty = true;
        }
      }
      i += 1;
    }
    mRecycler.viewRangeUpdate(paramInt1, paramInt2);
  }
  
  class a
    implements Runnable
  {
    a() {}
    
    public void run()
    {
      RecyclerView localRecyclerView = RecyclerView.this;
      if (mFirstLayoutComplete)
      {
        if (localRecyclerView.isLayoutRequested()) {
          return;
        }
        localRecyclerView = RecyclerView.this;
        if (!mIsAttached)
        {
          localRecyclerView.requestLayout();
          return;
        }
        if (mLayoutFrozen)
        {
          mLayoutRequestEaten = true;
          return;
        }
        localRecyclerView.consumePendingUpdateOperations();
      }
    }
  }
  
  public static class a0
  {
    int editor;
    long id;
    int mCoordOffset;
    private SparseArray<Object> mData;
    int mDeletedInvisibleItemCountSincePreviousLayout = 0;
    int mDragPoint;
    boolean mInPreLayout = false;
    boolean mIsMeasuring = false;
    int mItemCount = 0;
    int mLayoutStep = 1;
    int mPreviousLayoutItemCount = 0;
    boolean mRunPredictiveAnimations = false;
    boolean mRunSimpleAnimations = false;
    boolean mStructureChanged = false;
    int mTargetPosition = -1;
    boolean mTrackOldChangeHolders = false;
    int type;
    
    public a0() {}
    
    void a(RecyclerView.g paramG)
    {
      mLayoutStep = 1;
      mItemCount = paramG.a();
      mInPreLayout = false;
      mTrackOldChangeHolders = false;
      mIsMeasuring = false;
    }
    
    void assertLayoutStep(int paramInt)
    {
      if ((mLayoutStep & paramInt) != 0) {
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Layout state should be one of ");
      localStringBuilder.append(Integer.toBinaryString(paramInt));
      localStringBuilder.append(" but it is ");
      localStringBuilder.append(Integer.toBinaryString(mLayoutStep));
      throw new IllegalStateException(localStringBuilder.toString());
    }
    
    public boolean get()
    {
      return mInPreLayout;
    }
    
    public int getTargetScrollPosition()
    {
      return mTargetPosition;
    }
    
    public boolean has()
    {
      return mTargetPosition != -1;
    }
    
    public int size()
    {
      if (mInPreLayout) {
        return mPreviousLayoutItemCount - mDeletedInvisibleItemCountSincePreviousLayout;
      }
      return mItemCount;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("State{mTargetPosition=");
      localStringBuilder.append(mTargetPosition);
      localStringBuilder.append(", mData=");
      localStringBuilder.append(mData);
      localStringBuilder.append(", mItemCount=");
      localStringBuilder.append(mItemCount);
      localStringBuilder.append(", mIsMeasuring=");
      localStringBuilder.append(mIsMeasuring);
      localStringBuilder.append(", mPreviousLayoutItemCount=");
      localStringBuilder.append(mPreviousLayoutItemCount);
      localStringBuilder.append(", mDeletedInvisibleItemCountSincePreviousLayout=");
      localStringBuilder.append(mDeletedInvisibleItemCountSincePreviousLayout);
      localStringBuilder.append(", mStructureChanged=");
      localStringBuilder.append(mStructureChanged);
      localStringBuilder.append(", mInPreLayout=");
      localStringBuilder.append(mInPreLayout);
      localStringBuilder.append(", mRunSimpleAnimations=");
      localStringBuilder.append(mRunSimpleAnimations);
      localStringBuilder.append(", mRunPredictiveAnimations=");
      localStringBuilder.append(mRunPredictiveAnimations);
      localStringBuilder.append('}');
      return localStringBuilder.toString();
    }
    
    public boolean willRunPredictiveAnimations()
    {
      return mRunPredictiveAnimations;
    }
  }
  
  class b
    implements Runnable
  {
    b() {}
    
    public void run()
    {
      RecyclerView.l localL = mItemAnimator;
      if (localL != null) {
        localL.runPendingAnimations();
      }
      mPostedAnimatorRunner = false;
    }
  }
  
  public static abstract class b0
  {
    public abstract View getViewForPositionAndType(RecyclerView.v paramV, int paramInt1, int paramInt2);
  }
  
  static final class c
    implements Interpolator
  {
    c() {}
    
    public float getInterpolation(float paramFloat)
    {
      paramFloat -= 1.0F;
      return paramFloat * paramFloat * paramFloat * paramFloat * paramFloat + 1.0F;
    }
  }
  
  class c0
    implements Runnable
  {
    private boolean h = false;
    private boolean i = false;
    Interpolator mInterpolator = RecyclerView.mInterpolator;
    private int mLastFlingX;
    private int mLastFlingY;
    OverScroller mScroller = new OverScroller(getContext(), RecyclerView.mInterpolator);
    
    c0() {}
    
    private void b()
    {
      i = false;
      h = true;
    }
    
    private int computeScrollDuration(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      int k = Math.abs(paramInt1);
      int m = Math.abs(paramInt2);
      int j;
      if (k > m) {
        j = 1;
      } else {
        j = 0;
      }
      paramInt3 = (int)Math.sqrt(paramInt3 * paramInt3 + paramInt4 * paramInt4);
      paramInt2 = (int)Math.sqrt(paramInt1 * paramInt1 + paramInt2 * paramInt2);
      RecyclerView localRecyclerView = RecyclerView.this;
      if (j != 0) {
        paramInt1 = localRecyclerView.getWidth();
      } else {
        paramInt1 = localRecyclerView.getHeight();
      }
      paramInt4 = paramInt1 / 2;
      float f3 = Math.min(1.0F, paramInt2 * 1.0F / paramInt1);
      float f1 = paramInt4;
      float f2 = paramInt4;
      f3 = distanceInfluenceForSnapDuration(f3);
      if (paramInt3 > 0)
      {
        paramInt1 = Math.round(Math.abs((f1 + f2 * f3) / paramInt3) * 1000.0F) * 4;
      }
      else
      {
        if (j != 0) {
          paramInt2 = k;
        } else {
          paramInt2 = m;
        }
        paramInt1 = (int)((paramInt2 / paramInt1 + 1.0F) * 300.0F);
      }
      return Math.min(paramInt1, 2000);
    }
    
    private void d()
    {
      h = false;
      if (i) {
        postOnAnimation();
      }
    }
    
    private float distanceInfluenceForSnapDuration(float paramFloat)
    {
      return (float)Math.sin((paramFloat - 0.5F) * 0.47123894F);
    }
    
    public void fling(int paramInt1, int paramInt2)
    {
      setScrollState(2);
      mLastFlingY = 0;
      mLastFlingX = 0;
      mScroller.fling(0, 0, paramInt1, paramInt2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
      postOnAnimation();
    }
    
    void postOnAnimation()
    {
      if (h)
      {
        i = true;
        return;
      }
      removeCallbacks(this);
      ViewCompat.postOnAnimation(RecyclerView.this, this);
    }
    
    public void run()
    {
      if (mLayout == null)
      {
        stop();
        return;
      }
      b();
      consumePendingUpdateOperations();
      Object localObject1 = mScroller;
      RecyclerView.z localZ = mLayout.itemView;
      if (((OverScroller)localObject1).computeScrollOffset())
      {
        Object localObject2 = mScrollConsumed;
        int i6 = ((OverScroller)localObject1).getCurrX();
        int i7 = ((OverScroller)localObject1).getCurrY();
        int i1 = i6 - mLastFlingX;
        int j = i7 - mLastFlingY;
        int n = 0;
        mLastFlingX = i6;
        mLastFlingY = i7;
        int m = i1;
        int k = j;
        if (dispatchNestedPreScroll(i1, j, (int[])localObject2, null, 1))
        {
          m = i1 - localObject2[0];
          k = j - localObject2[1];
        }
        localObject2 = RecyclerView.this;
        int i2;
        int i3;
        if (mAdapter != null)
        {
          ((RecyclerView)localObject2).scrollByInternal(m, k, itemView);
          localObject2 = itemView;
          n = localObject2[0];
          i2 = localObject2[1];
          i3 = m - n;
          i1 = k - i2;
          if ((localZ != null) && (!localZ.put()) && (localZ.isRunning()))
          {
            j = mState.size();
            if (j == 0)
            {
              localZ.setId();
            }
            else if (localZ.measure() >= j)
            {
              localZ.measure(j - 1);
              localZ.measure(m - i3, k - i1);
            }
            else
            {
              localZ.measure(m - i3, k - i1);
            }
          }
        }
        else
        {
          i2 = 0;
          i3 = 0;
          i1 = 0;
        }
        if (!mItemDecorations.isEmpty()) {
          invalidate();
        }
        if (getOverScrollMode() != 2) {
          considerReleasingGlowsOnScroll(m, k);
        }
        if (!dispatchNestedScroll(n, i2, i3, i1, null, 1))
        {
          if ((i3 == 0) && (i1 == 0)) {
            break label535;
          }
          int i4 = (int)((OverScroller)localObject1).getCurrVelocity();
          int i5;
          if (i3 != i6)
          {
            if (i3 < 0) {
              j = -i4;
            } else if (i3 > 0) {
              j = i4;
            } else {
              j = 0;
            }
            i5 = j;
          }
          else
          {
            i5 = 0;
          }
          if (i1 != i7)
          {
            if (i1 < 0) {
              j = -i4;
            } else if (i1 > 0) {
              j = i4;
            } else {
              j = 0;
            }
          }
          else {
            j = 0;
          }
          if (getOverScrollMode() != 2) {
            absorbGlows(i5, j);
          }
          if (((i5 != 0) || (i3 == i6) || (((OverScroller)localObject1).getFinalX() == 0)) && ((j != 0) || (i1 == i7) || (((OverScroller)localObject1).getFinalY() == 0))) {
            ((OverScroller)localObject1).abortAnimation();
          }
        }
        label535:
        if ((n != 0) || (i2 != 0)) {
          dispatchOnScrolled(n, i2);
        }
        if (!RecyclerView.access$getAwakenScrollBars(RecyclerView.this)) {
          invalidate();
        }
        if ((k != 0) && (mLayout.canScrollVertically()) && (i2 == k)) {
          j = 1;
        } else {
          j = 0;
        }
        if ((m != 0) && (mLayout.canScrollHorizontally()) && (n == m)) {
          n = 1;
        } else {
          n = 0;
        }
        if (((m != 0) || (k != 0)) && (n == 0) && (j == 0)) {
          j = 0;
        } else {
          j = 1;
        }
        if ((!((OverScroller)localObject1).isFinished()) && ((j != 0) || (hasNestedScrollingParent(1))))
        {
          postOnAnimation();
          localObject1 = RecyclerView.this;
          localObject2 = r;
          if (localObject2 != null) {
            ((a)localObject2).a((RecyclerView)localObject1, m, k);
          }
        }
        else
        {
          setScrollState(0);
          if (RecyclerView.c) {
            d.a();
          }
          stopNestedScroll(1);
        }
      }
      if (localZ != null)
      {
        if (localZ.put()) {
          localZ.measure(0, 0);
        }
        if (!i) {
          localZ.setId();
        }
      }
      d();
    }
    
    public void smoothScrollBy(int paramInt1, int paramInt2, int paramInt3, Interpolator paramInterpolator)
    {
      if (mInterpolator != paramInterpolator)
      {
        mInterpolator = paramInterpolator;
        mScroller = new OverScroller(getContext(), paramInterpolator);
      }
      setScrollState(2);
      mLastFlingY = 0;
      mLastFlingX = 0;
      mScroller.startScroll(0, 0, paramInt1, paramInt2, paramInt3);
      if (Build.VERSION.SDK_INT < 23) {
        mScroller.computeScrollOffset();
      }
      postOnAnimation();
    }
    
    public void smoothScrollBy(int paramInt1, int paramInt2, Interpolator paramInterpolator)
    {
      int j = computeScrollDuration(paramInt1, paramInt2, 0, 0);
      Interpolator localInterpolator = paramInterpolator;
      if (paramInterpolator == null) {
        localInterpolator = RecyclerView.mInterpolator;
      }
      smoothScrollBy(paramInt1, paramInt2, j, localInterpolator);
    }
    
    public void stop()
    {
      removeCallbacks(this);
      mScroller.abortAnimation();
    }
  }
  
  class d
    implements q1.b
  {
    d() {}
    
    public void a(RecyclerView.d0 paramD0, RecyclerView.l.c paramC1, RecyclerView.l.c paramC2)
    {
      animateAppearance(paramD0, paramC1, paramC2);
    }
    
    public void animateChange(RecyclerView.d0 paramD0, RecyclerView.l.c paramC1, RecyclerView.l.c paramC2)
    {
      paramD0.setIsRecyclable(false);
      RecyclerView localRecyclerView = RecyclerView.this;
      if (mDataSetHasChangedAfterLayout)
      {
        if (mItemAnimator.animateChange(paramD0, paramD0, paramC1, paramC2)) {
          postAnimationRunner();
        }
      }
      else if (mItemAnimator.animateChange(paramD0, paramC1, paramC2)) {
        postAnimationRunner();
      }
    }
    
    public void drawRect(RecyclerView.d0 paramD0, RecyclerView.l.c paramC1, RecyclerView.l.c paramC2)
    {
      mRecycler.unscrapView(paramD0);
      animateDisappearance(paramD0, paramC1, paramC2);
    }
    
    public void run(RecyclerView.d0 paramD0)
    {
      RecyclerView localRecyclerView = RecyclerView.this;
      mLayout.b(itemView, mRecycler);
    }
  }
  
  public static abstract class d0
  {
    private static final List<Object> FULLUPDATE_PAYLOADS = ;
    private int c = 0;
    public final View itemView;
    int j = -1;
    int k = -1;
    WeakReference<RecyclerView> l;
    int mFlags;
    boolean mInChangeScrap = false;
    private int mIsRecyclableCount = 0;
    long mItemId = -1L;
    int mOldPosition = -1;
    RecyclerView mOwnerRecyclerView;
    List<Object> mPayloads = null;
    int mPosition = -1;
    int mPreLayoutPosition = -1;
    RecyclerView.v mScrapContainer = null;
    d0 mShadowedHolder = null;
    d0 mShadowingHolder = null;
    List<Object> mUnmodifiedPayloads = null;
    
    public d0(View paramView)
    {
      if (paramView != null)
      {
        itemView = paramView;
        return;
      }
      throw new IllegalArgumentException("itemView may not be null");
    }
    
    private void createPayloadsIfNeeded()
    {
      if (mPayloads == null)
      {
        mPayloads = new ArrayList();
        mUnmodifiedPayloads = Collections.unmodifiableList(mPayloads);
      }
    }
    
    void a(RecyclerView paramRecyclerView)
    {
      paramRecyclerView.a(this, c);
      c = 0;
    }
    
    void addChangePayload(Object paramObject)
    {
      if (paramObject == null)
      {
        addFlags(1024);
        return;
      }
      if ((0x400 & mFlags) == 0)
      {
        createPayloadsIfNeeded();
        mPayloads.add(paramObject);
      }
    }
    
    void addFlags(int paramInt)
    {
      mFlags |= paramInt;
    }
    
    void b(RecyclerView paramRecyclerView)
    {
      int i = j;
      if (i != -1) {
        c = i;
      } else {
        c = ViewCompat.getImportantForAccessibility(itemView);
      }
      paramRecyclerView.a(this, 4);
    }
    
    public final int c()
    {
      return k;
    }
    
    void clearOldPosition()
    {
      mOldPosition = -1;
      mPreLayoutPosition = -1;
    }
    
    void clearPayload()
    {
      List localList = mPayloads;
      if (localList != null) {
        localList.clear();
      }
      mFlags &= 0xFBFF;
    }
    
    void clearReturnedFromScrapFlag()
    {
      mFlags &= 0xFFFFFFDF;
    }
    
    void clearTmpDetachFlag()
    {
      mFlags &= 0xFEFF;
    }
    
    boolean doesTransientStatePreventRecycling()
    {
      return ((mFlags & 0x10) == 0) && (ViewCompat.hasTransientState(itemView));
    }
    
    void flagRemovedAndOffsetPosition(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      addFlags(8);
      offsetPosition(paramInt2, paramBoolean);
      mPosition = paramInt1;
    }
    
    public final long getItemId()
    {
      return mItemId;
    }
    
    public final int getLayoutPosition()
    {
      int m = mPreLayoutPosition;
      int i = m;
      if (m == -1) {
        i = mPosition;
      }
      return i;
    }
    
    public final int getOldPosition()
    {
      return mOldPosition;
    }
    
    public final int getText()
    {
      RecyclerView localRecyclerView = mOwnerRecyclerView;
      if (localRecyclerView == null) {
        return -1;
      }
      return localRecyclerView.getAdapterPositionFor(this);
    }
    
    List getUnmodifiedPayloads()
    {
      if ((mFlags & 0x400) == 0)
      {
        List localList = mPayloads;
        if ((localList != null) && (localList.size() != 0)) {
          return mUnmodifiedPayloads;
        }
        return FULLUPDATE_PAYLOADS;
      }
      return FULLUPDATE_PAYLOADS;
    }
    
    boolean hasAnyOfTheFlags(int paramInt)
    {
      return (mFlags & paramInt) != 0;
    }
    
    boolean isAdapterPositionUnknown()
    {
      return ((mFlags & 0x200) != 0) || (isInvalid());
    }
    
    boolean isBound()
    {
      return (mFlags & 0x1) != 0;
    }
    
    boolean isInvalid()
    {
      return (mFlags & 0x4) != 0;
    }
    
    public final boolean isRecyclable()
    {
      return ((mFlags & 0x10) == 0) && (!ViewCompat.hasTransientState(itemView));
    }
    
    boolean isRemoved()
    {
      return (mFlags & 0x8) != 0;
    }
    
    boolean isScrap()
    {
      return mScrapContainer != null;
    }
    
    boolean isTmpDetached()
    {
      return (mFlags & 0x100) != 0;
    }
    
    boolean isUpdated()
    {
      return (mFlags & 0x2) != 0;
    }
    
    boolean needsUpdate()
    {
      return (mFlags & 0x2) != 0;
    }
    
    void offsetPosition(int paramInt, boolean paramBoolean)
    {
      if (mOldPosition == -1) {
        mOldPosition = mPosition;
      }
      if (mPreLayoutPosition == -1) {
        mPreLayoutPosition = mPosition;
      }
      if (paramBoolean) {
        mPreLayoutPosition += paramInt;
      }
      mPosition += paramInt;
      if (itemView.getLayoutParams() != null) {
        itemView.getLayoutParams()).mInsetsDirty = true;
      }
    }
    
    void resetInternal()
    {
      mFlags = 0;
      mPosition = -1;
      mOldPosition = -1;
      mItemId = -1L;
      mPreLayoutPosition = -1;
      mIsRecyclableCount = 0;
      mShadowedHolder = null;
      mShadowingHolder = null;
      clearPayload();
      c = 0;
      j = -1;
      RecyclerView.next(this);
    }
    
    void saveOldPosition()
    {
      if (mOldPosition == -1) {
        mOldPosition = mPosition;
      }
    }
    
    void setFlags(int paramInt1, int paramInt2)
    {
      mFlags = (mFlags & paramInt2 | paramInt1 & paramInt2);
    }
    
    public final void setIsRecyclable(boolean paramBoolean)
    {
      int i = mIsRecyclableCount;
      if (paramBoolean) {
        i -= 1;
      } else {
        i += 1;
      }
      mIsRecyclableCount = i;
      i = mIsRecyclableCount;
      if (i < 0)
      {
        mIsRecyclableCount = 0;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for ");
        localStringBuilder.append(this);
        Log.e("View", localStringBuilder.toString());
        return;
      }
      if ((!paramBoolean) && (i == 1))
      {
        mFlags |= 0x10;
        return;
      }
      if ((paramBoolean) && (mIsRecyclableCount == 0)) {
        mFlags &= 0xFFFFFFEF;
      }
    }
    
    void setScrapContainer(RecyclerView.v paramV, boolean paramBoolean)
    {
      mScrapContainer = paramV;
      mInChangeScrap = paramBoolean;
    }
    
    boolean shouldBeKeptAsChild()
    {
      return (mFlags & 0x10) != 0;
    }
    
    boolean shouldIgnore()
    {
      return (mFlags & 0x80) != 0;
    }
    
    public String toString()
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("ViewHolder{");
      ((StringBuilder)localObject).append(Integer.toHexString(hashCode()));
      ((StringBuilder)localObject).append(" position=");
      ((StringBuilder)localObject).append(mPosition);
      ((StringBuilder)localObject).append(" id=");
      ((StringBuilder)localObject).append(mItemId);
      ((StringBuilder)localObject).append(", oldPos=");
      ((StringBuilder)localObject).append(mOldPosition);
      ((StringBuilder)localObject).append(", pLpos:");
      ((StringBuilder)localObject).append(mPreLayoutPosition);
      StringBuilder localStringBuilder = new StringBuilder(((StringBuilder)localObject).toString());
      if (isScrap())
      {
        localStringBuilder.append(" scrap ");
        if (mInChangeScrap) {
          localObject = "[changeScrap]";
        } else {
          localObject = "[attachedScrap]";
        }
        localStringBuilder.append((String)localObject);
      }
      if (isInvalid()) {
        localStringBuilder.append(" invalid");
      }
      if (!isBound()) {
        localStringBuilder.append(" unbound");
      }
      if (needsUpdate()) {
        localStringBuilder.append(" update");
      }
      if (isRemoved()) {
        localStringBuilder.append(" removed");
      }
      if (shouldIgnore()) {
        localStringBuilder.append(" ignored");
      }
      if (isTmpDetached()) {
        localStringBuilder.append(" tmpDetached");
      }
      if (!isRecyclable())
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(" not recyclable(");
        ((StringBuilder)localObject).append(mIsRecyclableCount);
        ((StringBuilder)localObject).append(")");
        localStringBuilder.append(((StringBuilder)localObject).toString());
      }
      if (isAdapterPositionUnknown()) {
        localStringBuilder.append(" undefined adapter position");
      }
      if (itemView.getParent() == null) {
        localStringBuilder.append(" no parent");
      }
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
    
    void unScrap()
    {
      mScrapContainer.unscrapView(this);
    }
    
    boolean wasReturnedFromScrap()
    {
      return (mFlags & 0x20) != 0;
    }
  }
  
  class e
    implements d0.b
  {
    e() {}
    
    public void addView(View paramView, int paramInt)
    {
      RecyclerView.this.addView(paramView, paramInt);
      dispatchChildAttached(paramView);
    }
    
    public void attachViewToParent(View paramView)
    {
      paramView = RecyclerView.getChildViewHolderInt(paramView);
      if (paramView != null) {
        paramView.b(RecyclerView.this);
      }
    }
    
    public void attachViewToParent(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
    {
      RecyclerView.d0 localD0 = RecyclerView.getChildViewHolderInt(paramView);
      if (localD0 != null)
      {
        if ((!localD0.isTmpDetached()) && (!localD0.shouldIgnore()))
        {
          paramView = new StringBuilder();
          paramView.append("Called attach on a child which is not detached: ");
          paramView.append(localD0);
          paramView.append(append());
          throw new IllegalArgumentException(paramView.toString());
        }
        localD0.clearTmpDetachFlag();
      }
      RecyclerView.access$getAttachViewToParent(RecyclerView.this, paramView, paramInt, paramLayoutParams);
    }
    
    public void detachViewFromParent(int paramInt)
    {
      Object localObject = getChildAt(paramInt);
      if (localObject != null)
      {
        localObject = RecyclerView.getChildViewHolderInt((View)localObject);
        if (localObject != null)
        {
          if ((((RecyclerView.d0)localObject).isTmpDetached()) && (!((RecyclerView.d0)localObject).shouldIgnore()))
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("called detach on an already detached child ");
            localStringBuilder.append(localObject);
            localStringBuilder.append(append());
            throw new IllegalArgumentException(localStringBuilder.toString());
          }
          ((RecyclerView.d0)localObject).addFlags(256);
        }
      }
      RecyclerView.access$getDetachViewFromParent(RecyclerView.this, paramInt);
    }
    
    public View getChildAt(int paramInt)
    {
      return RecyclerView.this.getChildAt(paramInt);
    }
    
    public int getChildCount()
    {
      return RecyclerView.this.getChildCount();
    }
    
    public RecyclerView.d0 getChildViewHolder(View paramView)
    {
      return RecyclerView.getChildViewHolderInt(paramView);
    }
    
    public int indexOfChild(View paramView)
    {
      return RecyclerView.this.indexOfChild(paramView);
    }
    
    public void onLeftHiddenState(View paramView)
    {
      paramView = RecyclerView.getChildViewHolderInt(paramView);
      if (paramView != null) {
        paramView.a(RecyclerView.this);
      }
    }
    
    public void removeAllViews()
    {
      int j = getChildCount();
      int i = 0;
      while (i < j)
      {
        View localView = getChildAt(i);
        dispatchChildDetached(localView);
        localView.clearAnimation();
        i += 1;
      }
      RecyclerView.this.removeAllViews();
    }
    
    public void removeViewAt(int paramInt)
    {
      View localView = RecyclerView.this.getChildAt(paramInt);
      if (localView != null)
      {
        dispatchChildDetached(localView);
        localView.clearAnimation();
      }
      RecyclerView.this.removeViewAt(paramInt);
    }
  }
  
  class f
    implements g
  {
    f() {}
    
    public RecyclerView.d0 a(int paramInt)
    {
      RecyclerView.d0 localD0 = findViewHolderForPosition(paramInt, true);
      if (localD0 == null) {
        return null;
      }
      if (mChildHelper.isHidden(itemView)) {
        return null;
      }
      return localD0;
    }
    
    public void a(int paramInt1, int paramInt2)
    {
      offsetPositionRecordsForMove(paramInt1, paramInt2);
      mItemsAddedOrRemoved = true;
    }
    
    public void a(int paramInt1, int paramInt2, Object paramObject)
    {
      viewRangeUpdate(paramInt1, paramInt2, paramObject);
      mItemsChanged = true;
    }
    
    public void a(Label paramLabel)
    {
      c(paramLabel);
    }
    
    public void b(int paramInt1, int paramInt2)
    {
      offsetPositionRecordsForRemove(paramInt1, paramInt2);
      mItemsAddedOrRemoved = true;
    }
    
    public void b(Label paramLabel)
    {
      c(paramLabel);
    }
    
    void c(Label paramLabel)
    {
      int i = d;
      if (i != 1)
      {
        if (i != 2)
        {
          if (i != 4)
          {
            if (i != 8) {
              return;
            }
            localRecyclerView = RecyclerView.this;
            mLayout.onItemsMoved(localRecyclerView, a, c, 1);
            return;
          }
          localRecyclerView = RecyclerView.this;
          mLayout.onItemsUpdated(localRecyclerView, a, c, b);
          return;
        }
        localRecyclerView = RecyclerView.this;
        mLayout.onItemsRemoved(localRecyclerView, a, c);
        return;
      }
      RecyclerView localRecyclerView = RecyclerView.this;
      mLayout.onItemsAdded(localRecyclerView, a, c);
    }
    
    public void offsetPositionsForRemovingInvisible(int paramInt1, int paramInt2)
    {
      offsetPositionRecordsForRemove(paramInt1, paramInt2, true);
      Object localObject = RecyclerView.this;
      mItemsAddedOrRemoved = true;
      localObject = mState;
      mDeletedInvisibleItemCountSincePreviousLayout += paramInt2;
    }
    
    public void subtract(int paramInt1, int paramInt2)
    {
      offsetPositionRecordsForRemove(paramInt1, paramInt2, false);
      mItemsAddedOrRemoved = true;
    }
  }
  
  public static abstract class g<VH extends RecyclerView.d0>
  {
    private boolean mHasStableIds = false;
    private final RecyclerView.h mObservable = new RecyclerView.h();
    
    public g() {}
    
    public abstract int a();
    
    public long a(int paramInt)
    {
      return -1L;
    }
    
    public abstract RecyclerView.d0 a(ViewGroup paramViewGroup, int paramInt);
    
    public abstract void a(RecyclerView.d0 paramD0, int paramInt);
    
    public final void bindViewHolder(RecyclerView.d0 paramD0, int paramInt)
    {
      mPosition = paramInt;
      if (hasStableIds()) {
        mItemId = a(paramInt);
      }
      paramD0.setFlags(1, 519);
      TraceCompat.beginSection("RV OnBindView");
      onBindViewHolder(paramD0, paramInt, paramD0.getUnmodifiedPayloads());
      paramD0.clearPayload();
      paramD0 = itemView.getLayoutParams();
      if ((paramD0 instanceof RecyclerView.p)) {
        mInsetsDirty = true;
      }
      TraceCompat.endSection();
    }
    
    public final void clear()
    {
      mObservable.notifyItemRangeInserted();
    }
    
    public int getItemViewType(int paramInt)
    {
      return 0;
    }
    
    public final boolean hasObservers()
    {
      return mObservable.hasObservers();
    }
    
    public final boolean hasStableIds()
    {
      return mHasStableIds;
    }
    
    public final void notifyItemChanged(int paramInt)
    {
      mObservable.notifyItemRangeChanged(paramInt, 1);
    }
    
    public void onAttachedToRecyclerView(RecyclerView paramRecyclerView) {}
    
    public void onBindViewHolder(RecyclerView.d0 paramD0, int paramInt, List paramList)
    {
      a(paramD0, paramInt);
    }
    
    public final RecyclerView.d0 onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
    {
      try
      {
        TraceCompat.beginSection("RV CreateView");
        paramViewGroup = a(paramViewGroup, paramInt);
        ViewParent localViewParent = itemView.getParent();
        if (localViewParent == null)
        {
          k = paramInt;
          TraceCompat.endSection();
          return paramViewGroup;
        }
        throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
      }
      catch (Throwable paramViewGroup)
      {
        TraceCompat.endSection();
        throw paramViewGroup;
      }
    }
    
    public void onDetachedFromRecyclerView(RecyclerView paramRecyclerView) {}
    
    public boolean onFailedToRecycleView(RecyclerView.d0 paramD0)
    {
      return false;
    }
    
    public void onViewAttachedToWindow(RecyclerView.d0 paramD0) {}
    
    public void onViewDetachedFromWindow(RecyclerView.d0 paramD0) {}
    
    public void onViewRecycled(RecyclerView.d0 paramD0) {}
    
    public void registerAdapterDataObserver(RecyclerView.i paramI)
    {
      mObservable.registerObserver(paramI);
    }
    
    public void setHasStableIds(boolean paramBoolean)
    {
      if (!hasObservers())
      {
        mHasStableIds = paramBoolean;
        return;
      }
      throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
    }
    
    public void unregisterAdapterDataObserver(RecyclerView.i paramI)
    {
      mObservable.unregisterObserver(paramI);
    }
  }
  
  static class h
    extends Observable<RecyclerView.i>
  {
    h() {}
    
    public boolean hasObservers()
    {
      return mObservers.isEmpty() ^ true;
    }
    
    public void notifyItemRangeChanged(int paramInt1, int paramInt2)
    {
      notifyItemRangeChanged(paramInt1, paramInt2, null);
    }
    
    public void notifyItemRangeChanged(int paramInt1, int paramInt2, Object paramObject)
    {
      int i = mObservers.size() - 1;
      while (i >= 0)
      {
        ((RecyclerView.i)mObservers.get(i)).onItemRangeChanged(paramInt1, paramInt2, paramObject);
        i -= 1;
      }
    }
    
    public void notifyItemRangeInserted()
    {
      int i = mObservers.size() - 1;
      while (i >= 0)
      {
        ((RecyclerView.i)mObservers.get(i)).onItemRangeInserted();
        i -= 1;
      }
    }
  }
  
  public static abstract class i
  {
    public i() {}
    
    public void notifyItemRangeChanged(int paramInt1, int paramInt2) {}
    
    public void onItemRangeChanged(int paramInt1, int paramInt2, Object paramObject)
    {
      notifyItemRangeChanged(paramInt1, paramInt2);
    }
    
    public void onItemRangeInserted() {}
  }
  
  public static abstract interface j
  {
    public abstract int onGetChildDrawingOrder(int paramInt1, int paramInt2);
  }
  
  public static class k
  {
    public k() {}
    
    protected EdgeEffect a(RecyclerView paramRecyclerView, int paramInt)
    {
      return new EdgeEffect(paramRecyclerView.getContext());
    }
  }
  
  public static abstract class l
  {
    private long animationDuration = 120L;
    private b j = null;
    private ArrayList<a> m = new ArrayList();
    private long mChangeDuration = 250L;
    private long mMoveDuration = 250L;
    private long mRemoveDuration = 120L;
    
    public l() {}
    
    static int buildAdapterChangeFlagsForAnimations(RecyclerView.d0 paramD0)
    {
      int k = mFlags & 0xE;
      if (paramD0.isInvalid()) {
        return 4;
      }
      int i = k;
      if ((k & 0x4) == 0)
      {
        int n = paramD0.getOldPosition();
        int i1 = paramD0.getText();
        i = k;
        if (n != -1)
        {
          i = k;
          if (i1 != -1)
          {
            i = k;
            if (n != i1) {
              i = k | 0x800;
            }
          }
        }
      }
      return i;
    }
    
    public void addLast(RecyclerView.d0 paramD0) {}
    
    public abstract boolean animateAppearance(RecyclerView.d0 paramD0, c paramC1, c paramC2);
    
    public abstract boolean animateChange(RecyclerView.d0 paramD01, RecyclerView.d0 paramD02, c paramC1, c paramC2);
    
    public abstract boolean animateChange(RecyclerView.d0 paramD0, c paramC1, c paramC2);
    
    public abstract boolean animateDisappearance(RecyclerView.d0 paramD0, c paramC1, c paramC2);
    
    public abstract boolean canReuseUpdatedViewHolder(RecyclerView.d0 paramD0);
    
    public boolean canReuseUpdatedViewHolder(RecyclerView.d0 paramD0, List paramList)
    {
      return canReuseUpdatedViewHolder(paramD0);
    }
    
    public final void clear()
    {
      int k = m.size();
      int i = 0;
      while (i < k)
      {
        ((a)m.get(i)).setStage();
        i += 1;
      }
      m.clear();
    }
    
    public abstract void endAnimation(RecyclerView.d0 paramD0);
    
    public abstract void endAnimations();
    
    public long getAnimationDuration()
    {
      return animationDuration;
    }
    
    public long getChangeDuration()
    {
      return mChangeDuration;
    }
    
    public long getMoveDuration()
    {
      return mMoveDuration;
    }
    
    public long getRemoveDuration()
    {
      return mRemoveDuration;
    }
    
    public final void i(RecyclerView.d0 paramD0)
    {
      addLast(paramD0);
      b localB = j;
      if (localB != null) {
        localB.onAnimationFinished(paramD0);
      }
    }
    
    public abstract boolean isRunning();
    
    public c recordPostLayoutInformation(RecyclerView.a0 paramA0, RecyclerView.d0 paramD0)
    {
      paramA0 = setFrom();
      paramA0.setFrom(paramD0);
      return paramA0;
    }
    
    public c recordPreLayoutInformation(RecyclerView.a0 paramA0, RecyclerView.d0 paramD0, int paramInt, List paramList)
    {
      paramA0 = setFrom();
      paramA0.setFrom(paramD0);
      return paramA0;
    }
    
    public abstract void runPendingAnimations();
    
    public c setFrom()
    {
      return new c();
    }
    
    void setListener(b paramB)
    {
      j = paramB;
    }
    
    public static abstract interface a
    {
      public abstract void setStage();
    }
    
    static abstract interface b
    {
      public abstract void onAnimationFinished(RecyclerView.d0 paramD0);
    }
    
    public static class c
    {
      public int left;
      public int top;
      
      public c() {}
      
      public c setFrom(RecyclerView.d0 paramD0)
      {
        setFrom(paramD0, 0);
        return this;
      }
      
      public c setFrom(RecyclerView.d0 paramD0, int paramInt)
      {
        paramD0 = itemView;
        left = paramD0.getLeft();
        top = paramD0.getTop();
        paramD0.getRight();
        paramD0.getBottom();
        return this;
      }
    }
  }
  
  private class m
    implements RecyclerView.l.b
  {
    m() {}
    
    public void onAnimationFinished(RecyclerView.d0 paramD0)
    {
      paramD0.setIsRecyclable(true);
      if ((mShadowedHolder != null) && (mShadowingHolder == null)) {
        mShadowedHolder = null;
      }
      mShadowingHolder = null;
      if ((!paramD0.shouldBeKeptAsChild()) && (!removeAnimatingView(itemView)) && (paramD0.isTmpDetached())) {
        removeDetachedView(itemView, false);
      }
    }
  }
  
  public static abstract class n
  {
    public n() {}
    
    public void a(Canvas paramCanvas, RecyclerView paramRecyclerView) {}
    
    public void a(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.a0 paramA0)
    {
      a(paramCanvas, paramRecyclerView);
    }
    
    public void getItemOffsets(Rect paramRect, int paramInt, RecyclerView paramRecyclerView)
    {
      paramRect.set(0, 0, 0, 0);
    }
    
    public void getItemOffsets(Rect paramRect, View paramView, RecyclerView paramRecyclerView, RecyclerView.a0 paramA0)
    {
      getItemOffsets(paramRect, ((RecyclerView.p)paramView.getLayoutParams()).a(), paramRecyclerView);
    }
    
    public void onChildDraw(Canvas paramCanvas, RecyclerView paramRecyclerView) {}
    
    public void onDraw(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.a0 paramA0)
    {
      onChildDraw(paramCanvas, paramRecyclerView);
    }
  }
  
  public static abstract class o
  {
    XYPlot a = new XYPlot(normal);
    private final p1.b d = new b();
    RecyclerView.z itemView;
    int j;
    boolean l;
    boolean mAutoMeasure = false;
    ChildHelper mChildHelper;
    boolean mDataSetHasChangedAfterLayout = false;
    private int mHeight;
    private int mHeightMode;
    boolean mIsAttachedToWindow = false;
    private boolean mMeasurementCacheEnabled = true;
    RecyclerView mRecyclerView;
    private int mWidth;
    private int mWidthMode;
    private final p1.b normal = new a();
    private boolean showIcons = true;
    XYPlot u = new XYPlot(d);
    
    public o() {}
    
    private void addViewInt(View paramView, int paramInt, boolean paramBoolean)
    {
      Object localObject = RecyclerView.getChildViewHolderInt(paramView);
      if ((!paramBoolean) && (!((RecyclerView.d0)localObject).isRemoved())) {
        mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout((RecyclerView.d0)localObject);
      } else {
        mRecyclerView.mViewInfoStore.addToDisappearedInLayout((RecyclerView.d0)localObject);
      }
      RecyclerView.p localP = (RecyclerView.p)paramView.getLayoutParams();
      if ((!((RecyclerView.d0)localObject).wasReturnedFromScrap()) && (!((RecyclerView.d0)localObject).isScrap()))
      {
        if (paramView.getParent() == mRecyclerView)
        {
          int k = mChildHelper.indexOfChild(paramView);
          int i = paramInt;
          if (paramInt == -1) {
            i = mChildHelper.getChildCount();
          }
          if (k != -1)
          {
            if (k != i) {
              mRecyclerView.mLayout.moveView(k, i);
            }
          }
          else
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:");
            ((StringBuilder)localObject).append(mRecyclerView.indexOfChild(paramView));
            ((StringBuilder)localObject).append(mRecyclerView.append());
            throw new IllegalStateException(((StringBuilder)localObject).toString());
          }
        }
        else
        {
          mChildHelper.addView(paramView, paramInt, false);
          mInsetsDirty = true;
          RecyclerView.z localZ = itemView;
          if ((localZ != null) && (localZ.isRunning())) {
            itemView.attachViewToParent(paramView);
          }
        }
      }
      else
      {
        if (((RecyclerView.d0)localObject).isScrap()) {
          ((RecyclerView.d0)localObject).unScrap();
        } else {
          ((RecyclerView.d0)localObject).clearReturnedFromScrapFlag();
        }
        mChildHelper.attachViewToParent(paramView, paramInt, paramView.getLayoutParams(), false);
      }
      if (mPendingInvalidate)
      {
        itemView.invalidate();
        mPendingInvalidate = false;
      }
    }
    
    public static int chooseSize(int paramInt1, int paramInt2, int paramInt3)
    {
      int i = View.MeasureSpec.getMode(paramInt1);
      paramInt1 = View.MeasureSpec.getSize(paramInt1);
      if (i != Integer.MIN_VALUE)
      {
        if (i != 1073741824) {
          paramInt1 = Math.max(paramInt2, paramInt3);
        }
        return paramInt1;
      }
      return Math.min(paramInt1, Math.max(paramInt2, paramInt3));
    }
    
    private void detachViewInternal(int paramInt, View paramView)
    {
      mChildHelper.detachViewFromParent(paramInt);
    }
    
    private boolean draw(RecyclerView paramRecyclerView, int paramInt1, int paramInt2)
    {
      paramRecyclerView = paramRecyclerView.getFocusedChild();
      if (paramRecyclerView == null) {
        return false;
      }
      int i = getPaddingLeft();
      int k = getPaddingTop();
      int m = getWidth();
      int n = getPaddingRight();
      int i1 = getHeight();
      int i2 = getPaddingBottom();
      Rect localRect = mRecyclerView.mTempRect;
      draw(paramRecyclerView, localRect);
      if ((left - paramInt1 < m - n) && (right - paramInt1 > i) && (top - paramInt2 < i1 - i2)) {
        return bottom - paramInt2 > k;
      }
      return false;
    }
    
    private int[] draw(RecyclerView paramRecyclerView, View paramView, Rect paramRect, boolean paramBoolean)
    {
      int i3 = getPaddingLeft();
      int n = getPaddingTop();
      int i4 = getWidth() - getPaddingRight();
      int i2 = getHeight();
      int i7 = getPaddingBottom();
      int i5 = paramView.getLeft() + left - paramView.getScrollX();
      int i1 = paramView.getTop() + top - paramView.getScrollY();
      int i6 = paramRect.width() + i5;
      int i8 = paramRect.height();
      int i = Math.min(0, i5 - i3);
      int k = Math.min(0, i1 - n);
      int m = Math.max(0, i6 - i4);
      i2 = Math.max(0, i8 + i1 - (i2 - i7));
      if (getLayoutDirection() == 1)
      {
        if (m != 0) {
          i = m;
        } else {
          i = Math.max(i, i6 - i4);
        }
      }
      else if (i == 0) {
        i = Math.min(i5 - i3, m);
      }
      if (k == 0) {
        k = Math.min(i1 - n, i2);
      }
      return new int[] { i, k };
    }
    
    public static int getChildMeasureSpec(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
    {
      int i = Math.max(0, paramInt1 - paramInt3);
      int k = 0;
      paramInt3 = 0;
      int m = 0;
      paramInt1 = 0;
      if (paramBoolean)
      {
        if (paramInt4 >= 0)
        {
          paramInt3 = paramInt4;
          paramInt1 = 1073741824;
        }
        else if (paramInt4 == -1)
        {
          if (paramInt2 != Integer.MIN_VALUE) {
            if (paramInt2 != 0)
            {
              if (paramInt2 != 1073741824) {
                break label72;
              }
            }
            else
            {
              paramInt3 = 0;
              paramInt1 = 0;
              break label72;
            }
          }
          paramInt3 = i;
          paramInt1 = paramInt2;
        }
        else
        {
          label72:
          paramInt1 = m;
          paramInt3 = k;
          if (paramInt4 == -2)
          {
            paramInt3 = 0;
            paramInt1 = 0;
          }
        }
      }
      else if (paramInt4 >= 0)
      {
        paramInt3 = paramInt4;
        paramInt1 = 1073741824;
      }
      else if (paramInt4 == -1)
      {
        paramInt3 = i;
        paramInt1 = paramInt2;
      }
      else
      {
        paramInt1 = m;
        paramInt3 = k;
        if (paramInt4 == -2)
        {
          paramInt3 = i;
          if ((paramInt2 != Integer.MIN_VALUE) && (paramInt2 != 1073741824)) {
            paramInt1 = 0;
          } else {
            paramInt1 = Integer.MIN_VALUE;
          }
        }
      }
      return View.MeasureSpec.makeMeasureSpec(paramInt3, paramInt1);
    }
    
    public static d getProperties(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
    {
      d localD = new d();
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, IpAddress.RecyclerView, paramInt1, paramInt2);
      orientation = paramContext.getInt(IpAddress.RecyclerView_android_orientation, 1);
      spanCount = paramContext.getInt(IpAddress.RecyclerView_spanCount, 1);
      reverseLayout = paramContext.getBoolean(IpAddress.RecyclerView_reverseLayout, false);
      stackFromEnd = paramContext.getBoolean(IpAddress.RecyclerView_stackFromEnd, false);
      paramContext.recycle();
      return localD;
    }
    
    private static boolean isMeasurementUpToDate(int paramInt1, int paramInt2, int paramInt3)
    {
      int i = View.MeasureSpec.getMode(paramInt2);
      paramInt2 = View.MeasureSpec.getSize(paramInt2);
      if ((paramInt3 > 0) && (paramInt1 != paramInt3)) {
        return false;
      }
      if (i != Integer.MIN_VALUE)
      {
        if (i != 0)
        {
          if (i != 1073741824) {
            return false;
          }
          if (paramInt2 == paramInt1) {
            return true;
          }
        }
        else
        {
          return true;
        }
      }
      else if (paramInt2 >= paramInt1) {
        return true;
      }
      return false;
    }
    
    private void scrapOrRecycleView(RecyclerView.v paramV, int paramInt, View paramView)
    {
      RecyclerView.d0 localD0 = RecyclerView.getChildViewHolderInt(paramView);
      if (localD0.shouldIgnore()) {
        return;
      }
      if ((localD0.isInvalid()) && (!localD0.isRemoved()) && (!mRecyclerView.mAdapter.hasStableIds()))
      {
        removeViewAt(paramInt);
        paramV.recycleViewHolderInternal(localD0);
        return;
      }
      detachViewAt(paramInt);
      paramV.scrapView(paramView);
      mRecyclerView.mViewInfoStore.onViewDetached(localD0);
    }
    
    public int a(View paramView)
    {
      return ((RecyclerView.p)paramView.getLayoutParams()).a();
    }
    
    public Parcelable a()
    {
      return null;
    }
    
    public View a(View paramView, int paramInt, RecyclerView.v paramV, RecyclerView.a0 paramA0)
    {
      return null;
    }
    
    public void a(int paramInt1, int paramInt2, RecyclerView.a0 paramA0, c paramC) {}
    
    public void a(RecyclerView.v paramV, RecyclerView.a0 paramA0)
    {
      Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
    }
    
    public void a(RecyclerView paramRecyclerView) {}
    
    public void a(RecyclerView paramRecyclerView, RecyclerView.v paramV)
    {
      a(paramRecyclerView);
    }
    
    public boolean a(View paramView, boolean paramBoolean1, boolean paramBoolean2)
    {
      if ((a.a(paramView, 24579)) && (u.a(paramView, 24579))) {
        paramBoolean2 = true;
      } else {
        paramBoolean2 = false;
      }
      if (paramBoolean1) {
        return paramBoolean2;
      }
      return !paramBoolean2;
    }
    
    public void addDisappearingView(View paramView)
    {
      addDisappearingView(paramView, -1);
    }
    
    public void addDisappearingView(View paramView, int paramInt)
    {
      addViewInt(paramView, paramInt, true);
    }
    
    public void addView(View paramView)
    {
      addView(paramView, -1);
    }
    
    public void addView(View paramView, int paramInt)
    {
      addViewInt(paramView, paramInt, false);
    }
    
    public void assertNotInLayoutOrScroll(String paramString)
    {
      RecyclerView localRecyclerView = mRecyclerView;
      if (localRecyclerView != null) {
        localRecyclerView.a(paramString);
      }
    }
    
    public void attachView(View paramView, int paramInt)
    {
      attachView(paramView, paramInt, (RecyclerView.p)paramView.getLayoutParams());
    }
    
    public void attachView(View paramView, int paramInt, RecyclerView.p paramP)
    {
      RecyclerView.d0 localD0 = RecyclerView.getChildViewHolderInt(paramView);
      if (localD0.isRemoved()) {
        mRecyclerView.mViewInfoStore.addToDisappearedInLayout(localD0);
      } else {
        mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(localD0);
      }
      mChildHelper.attachViewToParent(paramView, paramInt, paramP, localD0.isRemoved());
    }
    
    public int b(View paramView)
    {
      return paramView.getBottom() + getBottomDecorationHeight(paramView);
    }
    
    public void b(int paramInt, c paramC) {}
    
    public void b(int paramInt, RecyclerView.v paramV)
    {
      View localView = getChildAt(paramInt);
      removeViewAt(paramInt);
      paramV.recycleView(localView);
    }
    
    public void b(View paramView, RecyclerView.v paramV)
    {
      removeView(paramView);
      paramV.recycleView(paramView);
    }
    
    public void calculateItemDecorationsForChild(View paramView, Rect paramRect)
    {
      RecyclerView localRecyclerView = mRecyclerView;
      if (localRecyclerView == null)
      {
        paramRect.set(0, 0, 0, 0);
        return;
      }
      paramRect.set(localRecyclerView.getItemDecorInsetsForChild(paramView));
    }
    
    public boolean canScroll(RecyclerView.v paramV, RecyclerView.a0 paramA0)
    {
      return false;
    }
    
    public boolean canScrollHorizontally()
    {
      return false;
    }
    
    public boolean canScrollVertically()
    {
      return false;
    }
    
    public boolean checkLayoutParams(RecyclerView.p paramP)
    {
      return paramP != null;
    }
    
    public int computeHorizontalScrollExtent(RecyclerView.a0 paramA0)
    {
      return 0;
    }
    
    public int computeHorizontalScrollOffset(RecyclerView.a0 paramA0)
    {
      return 0;
    }
    
    public int computeHorizontalScrollRange(RecyclerView.a0 paramA0)
    {
      return 0;
    }
    
    public int computeVerticalScrollExtent(RecyclerView.a0 paramA0)
    {
      return 0;
    }
    
    public int computeVerticalScrollOffset(RecyclerView.a0 paramA0)
    {
      return 0;
    }
    
    public int computeVerticalScrollRange(RecyclerView.a0 paramA0)
    {
      return 0;
    }
    
    public void detachAndScrapAttachedViews(RecyclerView.v paramV)
    {
      int i = getChildCount() - 1;
      while (i >= 0)
      {
        scrapOrRecycleView(paramV, i, getChildAt(i));
        i -= 1;
      }
    }
    
    public void detachViewAt(int paramInt)
    {
      detachViewInternal(paramInt, getChildAt(paramInt));
    }
    
    void dispatchAttachedToWindow(RecyclerView paramRecyclerView)
    {
      mIsAttachedToWindow = true;
      onAttachedToWindow(paramRecyclerView);
    }
    
    void draw(int paramInt1, int paramInt2)
    {
      int i5 = getChildCount();
      if (i5 == 0)
      {
        mRecyclerView.defaultOnMeasure(paramInt1, paramInt2);
        return;
      }
      int i3 = Integer.MAX_VALUE;
      int m = Integer.MAX_VALUE;
      int i1 = Integer.MIN_VALUE;
      int k = Integer.MIN_VALUE;
      int i = 0;
      while (i < i5)
      {
        View localView = getChildAt(i);
        Rect localRect = mRecyclerView.mTempRect;
        draw(localView, localRect);
        int n = i3;
        if (left < i3) {
          n = left;
        }
        int i2 = i1;
        if (right > i1) {
          i2 = right;
        }
        i1 = m;
        if (top < m) {
          i1 = top;
        }
        int i4 = k;
        if (bottom > k) {
          i4 = bottom;
        }
        i += 1;
        i3 = n;
        m = i1;
        i1 = i2;
        k = i4;
      }
      mRecyclerView.mTempRect.set(i3, m, i1, k);
      setMeasuredDimension(mRecyclerView.mTempRect, paramInt1, paramInt2);
    }
    
    public void draw(View paramView, Rect paramRect)
    {
      RecyclerView.onLayoutChild(paramView, paramRect);
    }
    
    public void draw(View paramView, boolean paramBoolean, Rect paramRect)
    {
      Object localObject;
      if (paramBoolean)
      {
        localObject = getLayoutParamsmDecorInsets;
        paramRect.set(-left, -top, paramView.getWidth() + right, paramView.getHeight() + bottom);
      }
      else
      {
        paramRect.set(0, 0, paramView.getWidth(), paramView.getHeight());
      }
      if (mRecyclerView != null)
      {
        localObject = paramView.getMatrix();
        if ((localObject != null) && (!((Matrix)localObject).isIdentity()))
        {
          RectF localRectF = mRecyclerView.mPosition;
          localRectF.set(paramRect);
          ((Matrix)localObject).mapRect(localRectF);
          paramRect.set((int)Math.floor(left), (int)Math.floor(top), (int)Math.ceil(right), (int)Math.ceil(bottom));
        }
      }
      paramRect.offset(paramView.getLeft(), paramView.getTop());
    }
    
    public boolean draw(RecyclerView paramRecyclerView, View paramView, Rect paramRect, boolean paramBoolean1, boolean paramBoolean2)
    {
      paramView = draw(paramRecyclerView, paramView, paramRect, paramBoolean1);
      int i = paramView[0];
      int k = paramView[1];
      if ((!paramBoolean2) || (draw(paramRecyclerView, i, k)))
      {
        if (i == 0) {
          if (k == 0) {
            break label77;
          }
        }
      }
      else {
        return false;
      }
      if (paramBoolean1)
      {
        paramRecyclerView.scrollBy(i, k);
        return true;
      }
      paramRecyclerView.smoothScrollBy(i, k);
      return true;
      label77:
      return false;
    }
    
    public void e(int paramInt) {}
    
    public boolean endChangeAnimationIfNecessary()
    {
      RecyclerView.z localZ = itemView;
      return (localZ != null) && (localZ.isRunning());
    }
    
    public View findContainingItemView(View paramView)
    {
      RecyclerView localRecyclerView = mRecyclerView;
      if (localRecyclerView == null) {
        return null;
      }
      paramView = localRecyclerView.findContainingItemView(paramView);
      if (paramView == null) {
        return null;
      }
      if (mChildHelper.isHidden(paramView)) {
        return null;
      }
      return paramView;
    }
    
    public View findViewByPosition(int paramInt)
    {
      int k = getChildCount();
      int i = 0;
      View localView;
      while (i < k)
      {
        localView = getChildAt(i);
        RecyclerView.d0 localD0 = RecyclerView.getChildViewHolderInt(localView);
        if ((localD0 != null) && (localD0.getLayoutPosition() == paramInt) && (!localD0.shouldIgnore()))
        {
          if (mRecyclerView.mState.get()) {
            break label84;
          }
          if (!localD0.isRemoved()) {
            return localView;
          }
        }
        i += 1;
      }
      return null;
      label84:
      return localView;
    }
    
    public abstract RecyclerView.p generateDefaultLayoutParams();
    
    public RecyclerView.p generateLayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      return new RecyclerView.p(paramContext, paramAttributeSet);
    }
    
    public RecyclerView.p generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      if ((paramLayoutParams instanceof RecyclerView.p)) {
        return new RecyclerView.p((RecyclerView.p)paramLayoutParams);
      }
      if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
        return new RecyclerView.p((ViewGroup.MarginLayoutParams)paramLayoutParams);
      }
      return new RecyclerView.p(paramLayoutParams);
    }
    
    public int get(View paramView)
    {
      return paramView.getLeft() - getLeftDecorationWidth(paramView);
    }
    
    public int getBaseline()
    {
      return -1;
    }
    
    public int getBottomDecorationHeight(View paramView)
    {
      return getLayoutParamsmDecorInsets.bottom;
    }
    
    public View getChildAt(int paramInt)
    {
      ChildHelper localChildHelper = mChildHelper;
      if (localChildHelper != null) {
        return localChildHelper.getChildAt(paramInt);
      }
      return null;
    }
    
    public int getChildCount()
    {
      ChildHelper localChildHelper = mChildHelper;
      if (localChildHelper != null) {
        return localChildHelper.getChildCount();
      }
      return 0;
    }
    
    public int getColumnCountForAccessibility(RecyclerView.v paramV, RecyclerView.a0 paramA0)
    {
      paramV = mRecyclerView;
      if (paramV != null)
      {
        if (mAdapter == null) {
          return 1;
        }
        if (canScrollHorizontally()) {
          return mRecyclerView.mAdapter.a();
        }
      }
      return 1;
    }
    
    public int getDecoratedMeasuredHeight(View paramView)
    {
      Rect localRect = getLayoutParamsmDecorInsets;
      return paramView.getMeasuredHeight() + top + bottom;
    }
    
    public int getDecoratedMeasuredWidth(View paramView)
    {
      Rect localRect = getLayoutParamsmDecorInsets;
      return paramView.getMeasuredWidth() + left + right;
    }
    
    public View getFocusedChild()
    {
      Object localObject = mRecyclerView;
      if (localObject == null) {
        return null;
      }
      localObject = ((ViewGroup)localObject).getFocusedChild();
      if (localObject != null)
      {
        if (mChildHelper.isHidden((View)localObject)) {
          return null;
        }
        return localObject;
      }
      return null;
    }
    
    public int getHeight()
    {
      return mHeight;
    }
    
    public int getHeightMode()
    {
      return mHeightMode;
    }
    
    public int getLayoutDirection()
    {
      return ViewCompat.getLayoutDirection(mRecyclerView);
    }
    
    public int getLeftDecorationWidth(View paramView)
    {
      return getLayoutParamsmDecorInsets.left;
    }
    
    public int getMinimumHeight()
    {
      return ViewCompat.getMinimumHeight(mRecyclerView);
    }
    
    public int getMinimumWidth()
    {
      return ViewCompat.getMinimumWidth(mRecyclerView);
    }
    
    public int getPaddingBottom()
    {
      RecyclerView localRecyclerView = mRecyclerView;
      if (localRecyclerView != null) {
        return localRecyclerView.getPaddingBottom();
      }
      return 0;
    }
    
    public int getPaddingBottom(View paramView)
    {
      return paramView.getRight() + getRightDecorationWidth(paramView);
    }
    
    public int getPaddingLeft()
    {
      RecyclerView localRecyclerView = mRecyclerView;
      if (localRecyclerView != null) {
        return localRecyclerView.getPaddingLeft();
      }
      return 0;
    }
    
    public int getPaddingRight()
    {
      RecyclerView localRecyclerView = mRecyclerView;
      if (localRecyclerView != null) {
        return localRecyclerView.getPaddingRight();
      }
      return 0;
    }
    
    public int getPaddingTop()
    {
      RecyclerView localRecyclerView = mRecyclerView;
      if (localRecyclerView != null) {
        return localRecyclerView.getPaddingTop();
      }
      return 0;
    }
    
    public int getRightDecorationWidth(View paramView)
    {
      return getLayoutParamsmDecorInsets.right;
    }
    
    public int getRowCountForAccessibility(RecyclerView.v paramV, RecyclerView.a0 paramA0)
    {
      paramV = mRecyclerView;
      if (paramV != null)
      {
        if (mAdapter == null) {
          return 1;
        }
        if (canScrollVertically()) {
          return mRecyclerView.mAdapter.a();
        }
      }
      return 1;
    }
    
    public int getTopDecorationHeight(View paramView)
    {
      return getLayoutParamsmDecorInsets.top;
    }
    
    public int getWidth()
    {
      return mWidth;
    }
    
    public int getWidthMode()
    {
      return mWidthMode;
    }
    
    boolean hasFlexibleChildInBothOrientations()
    {
      int k = getChildCount();
      int i = 0;
      while (i < k)
      {
        ViewGroup.LayoutParams localLayoutParams = getChildAt(i).getLayoutParams();
        if ((width < 0) && (height < 0)) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    
    public boolean isAttachedToWindow()
    {
      return mIsAttachedToWindow;
    }
    
    public void measureChildWithDecorationsAndMargin(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      RecyclerView.p localP = (RecyclerView.p)paramView.getLayoutParams();
      Rect localRect = mDecorInsets;
      paramView.layout(left + paramInt1 + leftMargin, top + paramInt2 + topMargin, paramInt3 - right - rightMargin, paramInt4 - bottom - bottomMargin);
    }
    
    public void measureChildWithMargins(View paramView, int paramInt1, int paramInt2)
    {
      RecyclerView.p localP = (RecyclerView.p)paramView.getLayoutParams();
      Rect localRect = mRecyclerView.getItemDecorInsetsForChild(paramView);
      int m = left;
      int n = right;
      int i = top;
      int k = bottom;
      paramInt1 = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + leftMargin + rightMargin + (paramInt1 + (m + n)), width, canScrollHorizontally());
      paramInt2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + topMargin + bottomMargin + (paramInt2 + (i + k)), height, canScrollVertically());
      if (shouldMeasureChild(paramView, paramInt1, paramInt2, localP)) {
        paramView.measure(paramInt1, paramInt2);
      }
    }
    
    public void moveView(int paramInt1, int paramInt2)
    {
      Object localObject = getChildAt(paramInt1);
      if (localObject != null)
      {
        detachViewAt(paramInt1);
        attachView((View)localObject, paramInt2);
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Cannot move a child from non-existing index:");
      ((StringBuilder)localObject).append(paramInt1);
      ((StringBuilder)localObject).append(mRecyclerView.toString());
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    
    public void onAdapterChanged(RecyclerView.g paramG1, RecyclerView.g paramG2) {}
    
    public boolean onAddFocusables(RecyclerView paramRecyclerView, ArrayList paramArrayList, int paramInt1, int paramInt2)
    {
      return false;
    }
    
    public void onAttachedToWindow(RecyclerView paramRecyclerView) {}
    
    void onDetachedFromWindow(RecyclerView paramRecyclerView, RecyclerView.v paramV)
    {
      mIsAttachedToWindow = false;
      a(paramRecyclerView, paramV);
    }
    
    public View onFocusSearchFailed(View paramView, int paramInt)
    {
      return null;
    }
    
    public void onInitializeAccessibilityEvent(RecyclerView.v paramV, RecyclerView.a0 paramA0, AccessibilityEvent paramAccessibilityEvent)
    {
      paramV = mRecyclerView;
      if (paramV != null)
      {
        if (paramAccessibilityEvent == null) {
          return;
        }
        boolean bool2 = true;
        boolean bool1 = bool2;
        if (!paramV.canScrollVertically(1))
        {
          bool1 = bool2;
          if (!mRecyclerView.canScrollVertically(-1))
          {
            bool1 = bool2;
            if (!mRecyclerView.canScrollHorizontally(-1)) {
              if (mRecyclerView.canScrollHorizontally(1)) {
                bool1 = bool2;
              } else {
                bool1 = false;
              }
            }
          }
        }
        paramAccessibilityEvent.setScrollable(bool1);
        paramV = mRecyclerView.mAdapter;
        if (paramV != null) {
          paramAccessibilityEvent.setItemCount(paramV.a());
        }
      }
    }
    
    public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
    {
      RecyclerView localRecyclerView = mRecyclerView;
      onInitializeAccessibilityEvent(mRecycler, mState, paramAccessibilityEvent);
    }
    
    void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      RecyclerView localRecyclerView = mRecyclerView;
      onInitializeAccessibilityNodeInfo(mRecycler, mState, paramAccessibilityNodeInfoCompat);
    }
    
    public void onInitializeAccessibilityNodeInfo(RecyclerView.v paramV, RecyclerView.a0 paramA0, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      if ((mRecyclerView.canScrollVertically(-1)) || (mRecyclerView.canScrollHorizontally(-1)))
      {
        paramAccessibilityNodeInfoCompat.addAction(8192);
        paramAccessibilityNodeInfoCompat.setScrollable(true);
      }
      if ((mRecyclerView.canScrollVertically(1)) || (mRecyclerView.canScrollHorizontally(1)))
      {
        paramAccessibilityNodeInfoCompat.addAction(4096);
        paramAccessibilityNodeInfoCompat.setScrollable(true);
      }
      paramAccessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCountForAccessibility(paramV, paramA0), getColumnCountForAccessibility(paramV, paramA0), canScroll(paramV, paramA0), toInteger(paramV, paramA0)));
    }
    
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.v paramV, RecyclerView.a0 paramA0, View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      int i;
      if (canScrollVertically()) {
        i = a(paramView);
      } else {
        i = 0;
      }
      int k;
      if (canScrollHorizontally()) {
        k = a(paramView);
      } else {
        k = 0;
      }
      paramAccessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(i, 1, k, 1, false, false));
    }
    
    void onInitializeAccessibilityNodeInfoForItem(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      Object localObject = RecyclerView.getChildViewHolderInt(paramView);
      if ((localObject != null) && (!((RecyclerView.d0)localObject).isRemoved()) && (!mChildHelper.isHidden(itemView)))
      {
        localObject = mRecyclerView;
        onInitializeAccessibilityNodeInfoForItem(mRecycler, mState, paramView, paramAccessibilityNodeInfoCompat);
      }
    }
    
    public void onItemsAdded(RecyclerView paramRecyclerView, int paramInt1, int paramInt2) {}
    
    public void onItemsChanged(RecyclerView paramRecyclerView) {}
    
    public void onItemsMoved(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onItemsRemoved(RecyclerView paramRecyclerView, int paramInt1, int paramInt2) {}
    
    public void onItemsUpdated(RecyclerView paramRecyclerView, int paramInt1, int paramInt2) {}
    
    public void onItemsUpdated(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, Object paramObject)
    {
      onItemsUpdated(paramRecyclerView, paramInt1, paramInt2);
    }
    
    void onLayoutChildren()
    {
      RecyclerView.z localZ = itemView;
      if (localZ != null) {
        localZ.setId();
      }
    }
    
    public void onLayoutChildren(RecyclerView.a0 paramA0) {}
    
    public void onMeasure(RecyclerView.v paramV, RecyclerView.a0 paramA0, int paramInt1, int paramInt2)
    {
      mRecyclerView.defaultOnMeasure(paramInt1, paramInt2);
    }
    
    public boolean onRequestChildFocus(RecyclerView paramRecyclerView, RecyclerView.a0 paramA0, View paramView1, View paramView2)
    {
      return onRequestChildFocus(paramRecyclerView, paramView1, paramView2);
    }
    
    public boolean onRequestChildFocus(RecyclerView paramRecyclerView, View paramView1, View paramView2)
    {
      return (endChangeAnimationIfNecessary()) || (paramRecyclerView.isComputingLayout());
    }
    
    public void onRestoreInstanceState(Parcelable paramParcelable) {}
    
    public void onResultReceived()
    {
      mDataSetHasChangedAfterLayout = true;
    }
    
    public void onScrollStateChanged(int paramInt) {}
    
    boolean performAccessibilityAction(int paramInt, Bundle paramBundle)
    {
      RecyclerView localRecyclerView = mRecyclerView;
      return performAccessibilityAction(mRecycler, mState, paramInt, paramBundle);
    }
    
    public boolean performAccessibilityAction(RecyclerView.v paramV, RecyclerView.a0 paramA0, int paramInt, Bundle paramBundle)
    {
      paramV = mRecyclerView;
      if (paramV == null) {
        return false;
      }
      int m = 0;
      int n = 0;
      int i = 0;
      int k = 0;
      if (paramInt != 4096)
      {
        if (paramInt != 8192)
        {
          paramInt = n;
        }
        else
        {
          if (paramV.canScrollVertically(-1)) {
            i = -(getHeight() - getPaddingTop() - getPaddingBottom());
          }
          paramInt = i;
          if (mRecyclerView.canScrollHorizontally(-1))
          {
            k = -(getWidth() - getPaddingLeft() - getPaddingRight());
            paramInt = i;
          }
        }
      }
      else
      {
        i = m;
        if (paramV.canScrollVertically(1)) {
          i = getHeight() - getPaddingTop() - getPaddingBottom();
        }
        paramInt = i;
        if (mRecyclerView.canScrollHorizontally(1))
        {
          k = getWidth() - getPaddingLeft() - getPaddingRight();
          paramInt = i;
        }
      }
      if ((paramInt == 0) && (k == 0)) {
        return false;
      }
      mRecyclerView.smoothScrollBy(k, paramInt);
      return true;
    }
    
    public boolean performAccessibilityAction(RecyclerView.v paramV, RecyclerView.a0 paramA0, View paramView, int paramInt, Bundle paramBundle)
    {
      return false;
    }
    
    boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle)
    {
      RecyclerView localRecyclerView = mRecyclerView;
      return performAccessibilityAction(mRecycler, mState, paramView, paramInt, paramBundle);
    }
    
    public void removeAndRecycleAllViews(RecyclerView.v paramV)
    {
      int i = getChildCount() - 1;
      while (i >= 0)
      {
        if (!RecyclerView.getChildViewHolderInt(getChildAt(i)).shouldIgnore()) {
          b(i, paramV);
        }
        i -= 1;
      }
    }
    
    void removeAndRecycleScrapInt(RecyclerView.v paramV)
    {
      int k = paramV.getScrapCount();
      int i = k - 1;
      while (i >= 0)
      {
        View localView = paramV.getScrapViewAt(i);
        RecyclerView.d0 localD0 = RecyclerView.getChildViewHolderInt(localView);
        if (!localD0.shouldIgnore())
        {
          localD0.setIsRecyclable(false);
          if (localD0.isTmpDetached()) {
            mRecyclerView.removeDetachedView(localView, false);
          }
          RecyclerView.l localL = mRecyclerView.mItemAnimator;
          if (localL != null) {
            localL.endAnimation(localD0);
          }
          localD0.setIsRecyclable(true);
          paramV.quickRecycleScrapView(localView);
        }
        i -= 1;
      }
      paramV.clearScrap();
      if (k > 0) {
        mRecyclerView.invalidate();
      }
    }
    
    public boolean removeCallbacks(Runnable paramRunnable)
    {
      RecyclerView localRecyclerView = mRecyclerView;
      if (localRecyclerView != null) {
        return localRecyclerView.removeCallbacks(paramRunnable);
      }
      return false;
    }
    
    public void removeView(View paramView)
    {
      mChildHelper.removeView(paramView);
    }
    
    public void removeViewAt(int paramInt)
    {
      if (getChildAt(paramInt) != null) {
        mChildHelper.removeViewAt(paramInt);
      }
    }
    
    public final boolean requestChildRectangleOnScreen()
    {
      return showIcons;
    }
    
    public boolean requestChildRectangleOnScreen(RecyclerView paramRecyclerView, View paramView, Rect paramRect, boolean paramBoolean)
    {
      return draw(paramRecyclerView, paramView, paramRect, paramBoolean, false);
    }
    
    public void requestLayout()
    {
      RecyclerView localRecyclerView = mRecyclerView;
      if (localRecyclerView != null) {
        localRecyclerView.requestLayout();
      }
    }
    
    public int scrollHorizontallyBy(int paramInt, RecyclerView.v paramV, RecyclerView.a0 paramA0)
    {
      return 0;
    }
    
    public int scrollVerticallyBy(int paramInt, RecyclerView.v paramV, RecyclerView.a0 paramA0)
    {
      return 0;
    }
    
    void setExactMeasureSpecsFrom(RecyclerView paramRecyclerView)
    {
      setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(paramRecyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(paramRecyclerView.getHeight(), 1073741824));
    }
    
    void setMeasureSpecs(int paramInt1, int paramInt2)
    {
      mWidth = View.MeasureSpec.getSize(paramInt1);
      mWidthMode = View.MeasureSpec.getMode(paramInt1);
      if ((mWidthMode == 0) && (!RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC)) {
        mWidth = 0;
      }
      mHeight = View.MeasureSpec.getSize(paramInt2);
      mHeightMode = View.MeasureSpec.getMode(paramInt2);
      if ((mHeightMode == 0) && (!RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC)) {
        mHeight = 0;
      }
    }
    
    public void setMeasuredDimension(int paramInt1, int paramInt2)
    {
      RecyclerView.access$getSetMeasuredDimension(mRecyclerView, paramInt1, paramInt2);
    }
    
    public void setMeasuredDimension(Rect paramRect, int paramInt1, int paramInt2)
    {
      int i = paramRect.width();
      int k = getPaddingLeft();
      int m = getPaddingRight();
      int n = paramRect.height();
      int i1 = getPaddingTop();
      int i2 = getPaddingBottom();
      setMeasuredDimension(chooseSize(paramInt1, i + k + m, getMinimumWidth()), chooseSize(paramInt2, n + i1 + i2, getMinimumHeight()));
    }
    
    public boolean setOrientation()
    {
      return mAutoMeasure;
    }
    
    void setRecyclerView(RecyclerView paramRecyclerView)
    {
      if (paramRecyclerView == null)
      {
        mRecyclerView = null;
        mChildHelper = null;
        mWidth = 0;
        mHeight = 0;
      }
      else
      {
        mRecyclerView = paramRecyclerView;
        mChildHelper = mChildHelper;
        mWidth = paramRecyclerView.getWidth();
        mHeight = paramRecyclerView.getHeight();
      }
      mWidthMode = 1073741824;
      mHeightMode = 1073741824;
    }
    
    public boolean shouldIgnore()
    {
      RecyclerView localRecyclerView = mRecyclerView;
      return (localRecyclerView != null) && (mClipToPadding);
    }
    
    boolean shouldMeasureChild(View paramView, int paramInt1, int paramInt2, RecyclerView.p paramP)
    {
      return (paramView.isLayoutRequested()) || (!mMeasurementCacheEnabled) || (!isMeasurementUpToDate(paramView.getWidth(), paramInt1, width)) || (!isMeasurementUpToDate(paramView.getHeight(), paramInt2, height));
    }
    
    boolean shouldMeasureTwice()
    {
      return false;
    }
    
    boolean shouldReMeasureChild(View paramView, int paramInt1, int paramInt2, RecyclerView.p paramP)
    {
      return (!mMeasurementCacheEnabled) || (!isMeasurementUpToDate(paramView.getMeasuredWidth(), paramInt1, width)) || (!isMeasurementUpToDate(paramView.getMeasuredHeight(), paramInt2, height));
    }
    
    public int show(View paramView)
    {
      return paramView.getTop() - getTopDecorationHeight(paramView);
    }
    
    public boolean supportsPredictiveItemAnimations()
    {
      return false;
    }
    
    public int toInteger(RecyclerView.v paramV, RecyclerView.a0 paramA0)
    {
      return 0;
    }
    
    public void visitFrame(int paramInt)
    {
      RecyclerView localRecyclerView = mRecyclerView;
      if (localRecyclerView != null) {
        localRecyclerView.offsetChildrenHorizontal(paramInt);
      }
    }
    
    public void visitMaxs(int paramInt)
    {
      RecyclerView localRecyclerView = mRecyclerView;
      if (localRecyclerView != null) {
        localRecyclerView.offsetChildrenVertical(paramInt);
      }
    }
    
    class a
      implements p1.b
    {
      a() {}
      
      public int a()
      {
        return getWidth() - getPaddingRight();
      }
      
      public int a(View paramView)
      {
        RecyclerView.p localP = (RecyclerView.p)paramView.getLayoutParams();
        return getPaddingBottom(paramView) + rightMargin;
      }
      
      public int b()
      {
        return getPaddingLeft();
      }
      
      public int b(View paramView)
      {
        RecyclerView.p localP = (RecyclerView.p)paramView.getLayoutParams();
        return get(paramView) - leftMargin;
      }
      
      public View c(int paramInt)
      {
        return getChildAt(paramInt);
      }
    }
    
    class b
      implements p1.b
    {
      b() {}
      
      public int a()
      {
        return getHeight() - getPaddingBottom();
      }
      
      public int a(View paramView)
      {
        RecyclerView.p localP = (RecyclerView.p)paramView.getLayoutParams();
        return RecyclerView.o.this.b(paramView) + bottomMargin;
      }
      
      public int b()
      {
        return getPaddingTop();
      }
      
      public int b(View paramView)
      {
        RecyclerView.p localP = (RecyclerView.p)paramView.getLayoutParams();
        return show(paramView) - topMargin;
      }
      
      public View c(int paramInt)
      {
        return getChildAt(paramInt);
      }
    }
    
    public static abstract interface c
    {
      public abstract void a(int paramInt1, int paramInt2);
    }
    
    public static class d
    {
      public int orientation;
      public boolean reverseLayout;
      public int spanCount;
      public boolean stackFromEnd;
      
      public d() {}
    }
  }
  
  public static class p
    extends ViewGroup.MarginLayoutParams
  {
    final Rect mDecorInsets = new Rect();
    boolean mInsetsDirty = true;
    boolean mPendingInvalidate = false;
    RecyclerView.d0 mViewHolder;
    
    public p(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public p(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public p(p paramP)
    {
      super();
    }
    
    public p(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public p(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
    
    public int a()
    {
      return mViewHolder.getLayoutPosition();
    }
    
    public boolean isItemChanged()
    {
      return mViewHolder.isUpdated();
    }
    
    public boolean isViewInvalid()
    {
      return mViewHolder.isInvalid();
    }
    
    public boolean next()
    {
      return mViewHolder.isRemoved();
    }
  }
  
  public static abstract interface q
  {
    public abstract void onChildViewAttachedToWindow(View paramView);
    
    public abstract void onChildViewDetachedFromWindow(View paramView);
  }
  
  public static abstract class r
  {
    public abstract boolean set(int paramInt1, int paramInt2);
  }
  
  public static abstract interface s
  {
    public abstract void a(RecyclerView paramRecyclerView, MotionEvent paramMotionEvent);
    
    public abstract boolean onInterceptTouchEvent(RecyclerView paramRecyclerView, MotionEvent paramMotionEvent);
    
    public abstract void onRequestDisallowInterceptTouchEvent(boolean paramBoolean);
  }
  
  public static abstract class t
  {
    public t() {}
    
    public void a(RecyclerView paramRecyclerView, int paramInt1, int paramInt2) {}
    
    public void onScrollStateChanged(RecyclerView paramRecyclerView, int paramInt) {}
  }
  
  public static class u
  {
    SparseArray<a> data = new SparseArray();
    private int mAttachCount = 0;
    
    public u() {}
    
    private a append(int paramInt)
    {
      a localA2 = (a)data.get(paramInt);
      a localA1 = localA2;
      if (localA2 == null)
      {
        localA1 = new a();
        data.put(paramInt, localA1);
      }
      return localA1;
    }
    
    long a(long paramLong1, long paramLong2)
    {
      if (paramLong1 == 0L) {
        return paramLong2;
      }
      return paramLong1 / 4L * 3L + paramLong2 / 4L;
    }
    
    void a(int paramInt, long paramLong)
    {
      a localA = append(paramInt);
      b = a(b, paramLong);
    }
    
    public void a(RecyclerView.d0 paramD0)
    {
      int i = paramD0.c();
      ArrayList localArrayList = appendm;
      if (data.get(i)).k <= localArrayList.size()) {
        return;
      }
      paramD0.resetInternal();
      localArrayList.add(paramD0);
    }
    
    boolean a(int paramInt, long paramLong1, long paramLong2)
    {
      long l = appendb;
      return (l == 0L) || (paramLong1 + l < paramLong2);
    }
    
    void attach()
    {
      mAttachCount += 1;
    }
    
    void b(int paramInt, long paramLong)
    {
      a localA = append(paramInt);
      c = a(c, paramLong);
    }
    
    public void clear()
    {
      int i = 0;
      while (i < data.size())
      {
        data.valueAt(i)).m.clear();
        i += 1;
      }
    }
    
    void detach()
    {
      mAttachCount -= 1;
    }
    
    boolean get(int paramInt, long paramLong1, long paramLong2)
    {
      long l = appendc;
      return (l == 0L) || (paramLong1 + l < paramLong2);
    }
    
    public RecyclerView.d0 getRecycledView(int paramInt)
    {
      Object localObject = (a)data.get(paramInt);
      if ((localObject != null) && (!m.isEmpty()))
      {
        localObject = m;
        return (RecyclerView.d0)((ArrayList)localObject).remove(((ArrayList)localObject).size() - 1);
      }
      return null;
    }
    
    void onAdapterChanged(RecyclerView.g paramG1, RecyclerView.g paramG2, boolean paramBoolean)
    {
      if (paramG1 != null) {
        detach();
      }
      if ((!paramBoolean) && (mAttachCount == 0)) {
        clear();
      }
      if (paramG2 != null) {
        attach();
      }
    }
    
    static class a
    {
      long b = 0L;
      long c = 0L;
      int k = 5;
      final ArrayList<RecyclerView.d0> m = new ArrayList();
      
      a() {}
    }
  }
  
  public final class v
  {
    private int d = 2;
    int e = 2;
    final ArrayList<RecyclerView.d0> mAttachedScrap = new ArrayList();
    final ArrayList<RecyclerView.d0> mCachedViews = new ArrayList();
    ArrayList<RecyclerView.d0> mChangedScrap = null;
    RecyclerView.u mRecyclerPool;
    private RecyclerView.b0 mViewCacheExtension;
    private final List<RecyclerView.d0> values = Collections.unmodifiableList(mAttachedScrap);
    
    public v() {}
    
    private void attachAccessibilityDelegate(RecyclerView.d0 paramD0)
    {
      if (isAccessibilityEnabled())
      {
        View localView = itemView;
        if (ViewCompat.getImportantForAccessibility(localView) == 0) {
          ViewCompat.add(localView, 1);
        }
        if (!ViewCompat.hasAccessibilityDelegate(localView))
        {
          paramD0.addFlags(16384);
          ViewCompat.setAccessibilityDelegate(localView, mAccessibilityDelegate.getItemDelegate());
        }
      }
    }
    
    private boolean b(RecyclerView.d0 paramD0, int paramInt1, int paramInt2, long paramLong)
    {
      mOwnerRecyclerView = RecyclerView.this;
      int i = paramD0.c();
      long l = getNanoTime();
      if ((paramLong != Long.MAX_VALUE) && (!mRecyclerPool.a(i, l, paramLong))) {
        return false;
      }
      mAdapter.bindViewHolder(paramD0, paramInt1);
      paramLong = getNanoTime();
      mRecyclerPool.a(paramD0.c(), paramLong - l);
      attachAccessibilityDelegate(paramD0);
      if (mState.get()) {
        mPreLayoutPosition = paramInt2;
      }
      return true;
    }
    
    private void invalidateDisplayListInt(RecyclerView.d0 paramD0)
    {
      paramD0 = itemView;
      if ((paramD0 instanceof ViewGroup)) {
        invalidateDisplayListInt((ViewGroup)paramD0, false);
      }
    }
    
    private void invalidateDisplayListInt(ViewGroup paramViewGroup, boolean paramBoolean)
    {
      int i = paramViewGroup.getChildCount() - 1;
      while (i >= 0)
      {
        View localView = paramViewGroup.getChildAt(i);
        if ((localView instanceof ViewGroup)) {
          invalidateDisplayListInt((ViewGroup)localView, true);
        }
        i -= 1;
      }
      if (!paramBoolean) {
        return;
      }
      if (paramViewGroup.getVisibility() == 4)
      {
        paramViewGroup.setVisibility(0);
        paramViewGroup.setVisibility(4);
        return;
      }
      i = paramViewGroup.getVisibility();
      paramViewGroup.setVisibility(4);
      paramViewGroup.setVisibility(i);
    }
    
    void b()
    {
      RecyclerView.o localO = mLayout;
      if (localO != null) {
        i = j;
      } else {
        i = 0;
      }
      e = (d + i);
      int i = mCachedViews.size() - 1;
      while ((i >= 0) && (mCachedViews.size() > e))
      {
        recycleCachedViewAt(i);
        i -= 1;
      }
    }
    
    public void b(int paramInt)
    {
      d = paramInt;
      b();
    }
    
    void b(RecyclerView.d0 paramD0, boolean paramBoolean)
    {
      RecyclerView.next(paramD0);
      if (paramD0.hasAnyOfTheFlags(16384))
      {
        paramD0.setFlags(0, 16384);
        ViewCompat.setAccessibilityDelegate(itemView, null);
      }
      if (paramBoolean) {
        onDetachedFromWindow(paramD0);
      }
      mOwnerRecyclerView = null;
      getRecycledViewPool().a(paramD0);
    }
    
    public void clear()
    {
      mAttachedScrap.clear();
      draw();
    }
    
    void clearOldPositions()
    {
      int j = mCachedViews.size();
      int i = 0;
      while (i < j)
      {
        ((RecyclerView.d0)mCachedViews.get(i)).clearOldPosition();
        i += 1;
      }
      j = mAttachedScrap.size();
      i = 0;
      while (i < j)
      {
        ((RecyclerView.d0)mAttachedScrap.get(i)).clearOldPosition();
        i += 1;
      }
      ArrayList localArrayList = mChangedScrap;
      if (localArrayList != null)
      {
        j = localArrayList.size();
        i = 0;
        while (i < j)
        {
          ((RecyclerView.d0)mChangedScrap.get(i)).clearOldPosition();
          i += 1;
        }
      }
    }
    
    void clearScrap()
    {
      mAttachedScrap.clear();
      ArrayList localArrayList = mChangedScrap;
      if (localArrayList != null) {
        localArrayList.clear();
      }
    }
    
    public int convertPreLayoutPositionToPostLayout(int paramInt)
    {
      if ((paramInt >= 0) && (paramInt < mState.size()))
      {
        if (!mState.get()) {
          return paramInt;
        }
        return mAdapterHelper.findPositionOffset(paramInt);
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("invalid position ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(". State ");
      localStringBuilder.append("item count is ");
      localStringBuilder.append(mState.size());
      localStringBuilder.append(append());
      throw new IndexOutOfBoundsException(localStringBuilder.toString());
    }
    
    void draw()
    {
      int i = mCachedViews.size() - 1;
      while (i >= 0)
      {
        recycleCachedViewAt(i);
        i -= 1;
      }
      mCachedViews.clear();
      if (RecyclerView.c) {
        d.a();
      }
    }
    
    public View get(int paramInt)
    {
      return next(paramInt, false);
    }
    
    RecyclerView.d0 getChangedScrapViewForPosition(int paramInt)
    {
      Object localObject = mChangedScrap;
      v localV = this;
      if (localObject != null)
      {
        int j = ((ArrayList)localObject).size();
        if (j == 0) {
          return null;
        }
        int i = 0;
        while (i < j)
        {
          localObject = mChangedScrap;
          localObject = (RecyclerView.d0)((ArrayList)localObject).get(i);
          if ((!((RecyclerView.d0)localObject).wasReturnedFromScrap()) && (((RecyclerView.d0)localObject).getLayoutPosition() == paramInt))
          {
            ((RecyclerView.d0)localObject).addFlags(32);
            return localObject;
          }
          i += 1;
        }
        if (this$0.mAdapter.hasStableIds())
        {
          paramInt = this$0.mAdapterHelper.findPositionOffset(paramInt);
          if ((paramInt > 0) && (paramInt < this$0.mAdapter.a()))
          {
            long l = this$0.mAdapter.a(paramInt);
            paramInt = 0;
            while (paramInt < j)
            {
              localObject = mChangedScrap;
              localObject = (RecyclerView.d0)((ArrayList)localObject).get(paramInt);
              if ((!((RecyclerView.d0)localObject).wasReturnedFromScrap()) && (((RecyclerView.d0)localObject).getItemId() == l))
              {
                ((RecyclerView.d0)localObject).addFlags(32);
                return localObject;
              }
              paramInt += 1;
            }
          }
        }
      }
      return null;
    }
    
    RecyclerView.u getRecycledViewPool()
    {
      if (mRecyclerPool == null) {
        mRecyclerPool = new RecyclerView.u();
      }
      return mRecyclerPool;
    }
    
    int getScrapCount()
    {
      return mAttachedScrap.size();
    }
    
    View getScrapViewAt(int paramInt)
    {
      return mAttachedScrap.get(paramInt)).itemView;
    }
    
    RecyclerView.d0 getScrapViewForId(long paramLong, int paramInt, boolean paramBoolean)
    {
      Object localObject2 = mAttachedScrap;
      Object localObject1 = this;
      int i = ((ArrayList)localObject2).size() - 1;
      Object localObject3;
      while (i >= 0)
      {
        localObject3 = mAttachedScrap;
        localObject2 = localObject1;
        localObject3 = (RecyclerView.d0)((ArrayList)localObject3).get(i);
        if ((((RecyclerView.d0)localObject3).getItemId() == paramLong) && (!((RecyclerView.d0)localObject3).wasReturnedFromScrap()))
        {
          if (paramInt == ((RecyclerView.d0)localObject3).c())
          {
            ((RecyclerView.d0)localObject3).addFlags(32);
            localObject1 = localObject3;
            if (!((RecyclerView.d0)localObject3).isRemoved()) {
              break label289;
            }
            localObject1 = localObject3;
            if (this$0.mState.get()) {
              break label289;
            }
            ((RecyclerView.d0)localObject3).setFlags(2, 14);
            return localObject3;
          }
          if (!paramBoolean)
          {
            ArrayList localArrayList = mAttachedScrap;
            localArrayList.remove(i);
            this$0.removeDetachedView(itemView, false);
            ((v)localObject2).quickRecycleScrapView(itemView);
          }
        }
        i -= 1;
      }
      localObject2 = mCachedViews;
      i = ((ArrayList)localObject2).size() - 1;
      while (i >= 0)
      {
        localObject3 = mCachedViews;
        localObject2 = localObject1;
        localObject3 = (RecyclerView.d0)((ArrayList)localObject3).get(i);
        if (((RecyclerView.d0)localObject3).getItemId() == paramLong)
        {
          if (paramInt == ((RecyclerView.d0)localObject3).c())
          {
            localObject1 = localObject3;
            if (paramBoolean) {
              break label289;
            }
            mCachedViews.remove(i);
            return localObject3;
          }
          if (!paramBoolean)
          {
            ((v)localObject2).recycleCachedViewAt(i);
            return null;
          }
        }
        i -= 1;
        localObject1 = localObject2;
      }
      return null;
      label289:
      return localObject1;
    }
    
    RecyclerView.d0 getScrapViewForPosition(int paramInt, boolean paramBoolean)
    {
      Object localObject1 = mAttachedScrap;
      v localV = this;
      int j = ((ArrayList)localObject1).size();
      int i = 0;
      while (i < j)
      {
        localObject1 = mAttachedScrap;
        localObject1 = (RecyclerView.d0)((ArrayList)localObject1).get(i);
        if ((!((RecyclerView.d0)localObject1).wasReturnedFromScrap()) && (((RecyclerView.d0)localObject1).getLayoutPosition() == paramInt) && (!((RecyclerView.d0)localObject1).isInvalid()) && ((this$0.mState.mInPreLayout) || (!((RecyclerView.d0)localObject1).isRemoved())))
        {
          ((RecyclerView.d0)localObject1).addFlags(32);
          return localObject1;
        }
        i += 1;
      }
      if (!paramBoolean)
      {
        Object localObject2 = this$0.mChildHelper.findHiddenNonRemovedView(paramInt);
        if (localObject2 != null)
        {
          localObject1 = RecyclerView.getChildViewHolderInt((View)localObject2);
          this$0.mChildHelper.unhide((View)localObject2);
          paramInt = this$0.mChildHelper.indexOfChild((View)localObject2);
          if (paramInt != -1)
          {
            this$0.mChildHelper.detachViewFromParent(paramInt);
            localV.scrapView((View)localObject2);
            ((RecyclerView.d0)localObject1).addFlags(8224);
            return localObject1;
          }
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("layout index should not be -1 after unhiding a view:");
          ((StringBuilder)localObject2).append(localObject1);
          ((StringBuilder)localObject2).append(this$0.append());
          throw new IllegalStateException(((StringBuilder)localObject2).toString());
        }
      }
      localObject1 = mCachedViews;
      j = ((ArrayList)localObject1).size();
      i = 0;
      while (i < j)
      {
        localObject1 = mCachedViews;
        localObject1 = (RecyclerView.d0)((ArrayList)localObject1).get(i);
        if ((!((RecyclerView.d0)localObject1).isInvalid()) && (((RecyclerView.d0)localObject1).getLayoutPosition() == paramInt))
        {
          if (paramBoolean) {
            break label334;
          }
          mCachedViews.remove(i);
          return localObject1;
        }
        i += 1;
      }
      return null;
      label334:
      return localObject1;
    }
    
    public List getValue()
    {
      return values;
    }
    
    RecyclerView.d0 getViewForPosition(int paramInt, boolean paramBoolean, long paramLong)
    {
      if ((paramInt >= 0) && (paramInt < mState.size()))
      {
        int j = 0;
        Object localObject2 = null;
        boolean bool2 = mState.get();
        boolean bool1 = true;
        if (bool2)
        {
          localObject1 = getChangedScrapViewForPosition(paramInt);
          localObject2 = localObject1;
          if (localObject1 != null) {
            j = 1;
          } else {
            j = 0;
          }
        }
        int i = j;
        localObject1 = localObject2;
        Object localObject3;
        if (localObject2 == null)
        {
          localObject3 = getScrapViewForPosition(paramInt, paramBoolean);
          localObject2 = localObject3;
          i = j;
          localObject1 = localObject2;
          if (localObject3 != null) {
            if (!validateViewHolderForOffsetPosition((RecyclerView.d0)localObject3))
            {
              if (!paramBoolean)
              {
                ((RecyclerView.d0)localObject3).addFlags(4);
                if (((RecyclerView.d0)localObject3).isScrap())
                {
                  removeDetachedView(itemView, false);
                  ((RecyclerView.d0)localObject3).unScrap();
                }
                else if (((RecyclerView.d0)localObject3).wasReturnedFromScrap())
                {
                  ((RecyclerView.d0)localObject3).clearReturnedFromScrapFlag();
                }
                recycleViewHolderInternal((RecyclerView.d0)localObject3);
              }
              localObject1 = null;
              i = j;
            }
            else
            {
              i = 1;
              localObject1 = localObject2;
            }
          }
        }
        if (localObject1 == null)
        {
          int m = mAdapterHelper.findPositionOffset(paramInt);
          if ((m >= 0) && (m < mAdapter.a()))
          {
            int k = mAdapter.getItemViewType(m);
            j = i;
            if (mAdapter.hasStableIds())
            {
              localObject3 = getScrapViewForId(mAdapter.a(m), k, paramBoolean);
              localObject2 = localObject3;
              j = i;
              localObject1 = localObject2;
              if (localObject3 != null)
              {
                mPosition = m;
                j = 1;
                localObject1 = localObject2;
              }
            }
            localObject2 = localObject1;
            if (localObject1 == null)
            {
              localObject3 = mViewCacheExtension;
              localObject2 = localObject1;
              if (localObject3 != null)
              {
                localObject3 = ((RecyclerView.b0)localObject3).getViewForPositionAndType(this, paramInt, k);
                localObject2 = localObject1;
                if (localObject3 != null)
                {
                  localObject1 = getChildViewHolder((View)localObject3);
                  localObject2 = localObject1;
                  if (localObject1 != null)
                  {
                    if (((RecyclerView.d0)localObject1).shouldIgnore())
                    {
                      localObject1 = new StringBuilder();
                      ((StringBuilder)localObject1).append("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view.");
                      ((StringBuilder)localObject1).append(append());
                      throw new IllegalArgumentException(((StringBuilder)localObject1).toString());
                    }
                  }
                  else
                  {
                    localObject1 = new StringBuilder();
                    ((StringBuilder)localObject1).append("getViewForPositionAndType returned a view which does not have a ViewHolder");
                    ((StringBuilder)localObject1).append(append());
                    throw new IllegalArgumentException(((StringBuilder)localObject1).toString());
                  }
                }
              }
            }
            localObject1 = localObject2;
            if (localObject2 == null)
            {
              localObject3 = getRecycledViewPool().getRecycledView(k);
              localObject2 = localObject3;
              localObject1 = localObject2;
              if (localObject3 != null)
              {
                ((RecyclerView.d0)localObject3).resetInternal();
                localObject1 = localObject2;
                if (RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST)
                {
                  invalidateDisplayListInt((RecyclerView.d0)localObject3);
                  localObject1 = localObject2;
                }
              }
            }
            if (localObject1 == null)
            {
              long l1 = getNanoTime();
              if ((paramLong != Long.MAX_VALUE) && (!mRecyclerPool.get(k, l1, paramLong))) {
                return null;
              }
              localObject1 = RecyclerView.this;
              localObject1 = mAdapter.onCreateViewHolder((ViewGroup)localObject1, k);
              if (RecyclerView.c)
              {
                localObject2 = RecyclerView.a(itemView);
                if (localObject2 != null) {
                  l = new WeakReference(localObject2);
                }
              }
              long l2 = getNanoTime();
              mRecyclerPool.b(k, l2 - l1);
              i = j;
            }
            else
            {
              i = j;
            }
          }
          else
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Inconsistency detected. Invalid item position ");
            ((StringBuilder)localObject1).append(paramInt);
            ((StringBuilder)localObject1).append("(offset:");
            ((StringBuilder)localObject1).append(m);
            ((StringBuilder)localObject1).append(").");
            ((StringBuilder)localObject1).append("state:");
            ((StringBuilder)localObject1).append(mState.size());
            ((StringBuilder)localObject1).append(append());
            throw new IndexOutOfBoundsException(((StringBuilder)localObject1).toString());
          }
        }
        if ((i != 0) && (!mState.get()) && (((RecyclerView.d0)localObject1).hasAnyOfTheFlags(8192)))
        {
          ((RecyclerView.d0)localObject1).setFlags(0, 8192);
          if (mState.mRunSimpleAnimations)
          {
            j = RecyclerView.l.buildAdapterChangeFlagsForAnimations((RecyclerView.d0)localObject1);
            localObject2 = RecyclerView.this;
            localObject2 = mItemAnimator.recordPreLayoutInformation(mState, (RecyclerView.d0)localObject1, j | 0x1000, ((RecyclerView.d0)localObject1).getUnmodifiedPayloads());
            recordAnimationInfoIfBouncedHiddenView((RecyclerView.d0)localObject1, (RecyclerView.l.c)localObject2);
          }
        }
        paramBoolean = false;
        if ((mState.get()) && (((RecyclerView.d0)localObject1).isBound())) {
          mPreLayoutPosition = paramInt;
        } else if ((!((RecyclerView.d0)localObject1).isBound()) || (((RecyclerView.d0)localObject1).needsUpdate()) || (((RecyclerView.d0)localObject1).isInvalid())) {
          paramBoolean = b((RecyclerView.d0)localObject1, mAdapterHelper.findPositionOffset(paramInt), paramInt, paramLong);
        }
        localObject2 = itemView.getLayoutParams();
        if (localObject2 == null)
        {
          localObject2 = (RecyclerView.p)generateDefaultLayoutParams();
          itemView.setLayoutParams((ViewGroup.LayoutParams)localObject2);
        }
        else if (!checkLayoutParams((ViewGroup.LayoutParams)localObject2))
        {
          localObject2 = (RecyclerView.p)generateLayoutParams((ViewGroup.LayoutParams)localObject2);
          itemView.setLayoutParams((ViewGroup.LayoutParams)localObject2);
        }
        else
        {
          localObject2 = (RecyclerView.p)localObject2;
        }
        mViewHolder = ((RecyclerView.d0)localObject1);
        if ((i != 0) && (paramBoolean)) {
          paramBoolean = bool1;
        } else {
          paramBoolean = false;
        }
        mPendingInvalidate = paramBoolean;
        return localObject1;
      }
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Invalid item position ");
      ((StringBuilder)localObject1).append(paramInt);
      ((StringBuilder)localObject1).append("(");
      ((StringBuilder)localObject1).append(paramInt);
      ((StringBuilder)localObject1).append("). Item count:");
      ((StringBuilder)localObject1).append(mState.size());
      ((StringBuilder)localObject1).append(append());
      throw new IndexOutOfBoundsException(((StringBuilder)localObject1).toString());
    }
    
    void markItemDecorInsetsDirty()
    {
      int j = mCachedViews.size();
      int i = 0;
      while (i < j)
      {
        RecyclerView.p localP = (RecyclerView.p)mCachedViews.get(i)).itemView.getLayoutParams();
        if (localP != null) {
          mInsetsDirty = true;
        }
        i += 1;
      }
    }
    
    void markKnownViewsInvalid()
    {
      int j = mCachedViews.size();
      int i = 0;
      while (i < j)
      {
        localObject = (RecyclerView.d0)mCachedViews.get(i);
        if (localObject != null)
        {
          ((RecyclerView.d0)localObject).addFlags(6);
          ((RecyclerView.d0)localObject).addChangePayload(null);
        }
        i += 1;
      }
      Object localObject = mAdapter;
      if ((localObject == null) || (!((RecyclerView.g)localObject).hasStableIds())) {
        draw();
      }
    }
    
    View next(int paramInt, boolean paramBoolean)
    {
      return getViewForPositionMAX_VALUEitemView;
    }
    
    void offsetPositionRecordsForInsert(int paramInt1, int paramInt2)
    {
      int j = mCachedViews.size();
      int i = 0;
      while (i < j)
      {
        RecyclerView.d0 localD0 = (RecyclerView.d0)mCachedViews.get(i);
        if ((localD0 != null) && (mPosition >= paramInt1)) {
          localD0.offsetPosition(paramInt2, true);
        }
        i += 1;
      }
    }
    
    void offsetPositionRecordsForMove(int paramInt1, int paramInt2)
    {
      int i;
      int j;
      int k;
      if (paramInt1 < paramInt2)
      {
        i = paramInt1;
        j = paramInt2;
        k = -1;
      }
      else
      {
        i = paramInt2;
        j = paramInt1;
        k = 1;
      }
      int n = mCachedViews.size();
      int m = 0;
      while (m < n)
      {
        RecyclerView.d0 localD0 = (RecyclerView.d0)mCachedViews.get(m);
        if (localD0 != null)
        {
          int i1 = mPosition;
          if ((i1 >= i) && (i1 <= j)) {
            if (i1 == paramInt1) {
              localD0.offsetPosition(paramInt2 - paramInt1, false);
            } else {
              localD0.offsetPosition(k, false);
            }
          }
        }
        m += 1;
      }
    }
    
    void offsetPositionRecordsForRemove(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      int i = mCachedViews.size() - 1;
      while (i >= 0)
      {
        RecyclerView.d0 localD0 = (RecyclerView.d0)mCachedViews.get(i);
        if (localD0 != null)
        {
          int j = mPosition;
          if (j >= paramInt1 + paramInt2)
          {
            localD0.offsetPosition(-paramInt2, paramBoolean);
          }
          else if (j >= paramInt1)
          {
            localD0.addFlags(8);
            recycleCachedViewAt(i);
          }
        }
        i -= 1;
      }
    }
    
    void onAdapterChanged(RecyclerView.g paramG1, RecyclerView.g paramG2, boolean paramBoolean)
    {
      clear();
      getRecycledViewPool().onAdapterChanged(paramG1, paramG2, paramBoolean);
    }
    
    void onDetachedFromWindow(RecyclerView.d0 paramD0)
    {
      Object localObject = bitmap;
      if (localObject != null) {
        ((RecyclerView.w)localObject).andNot(paramD0);
      }
      localObject = mAdapter;
      if (localObject != null) {
        ((RecyclerView.g)localObject).onViewRecycled(paramD0);
      }
      localObject = RecyclerView.this;
      if (mState != null) {
        mViewInfoStore.clear(paramD0);
      }
    }
    
    void quickRecycleScrapView(View paramView)
    {
      paramView = RecyclerView.getChildViewHolderInt(paramView);
      mScrapContainer = null;
      mInChangeScrap = false;
      paramView.clearReturnedFromScrapFlag();
      recycleViewHolderInternal(paramView);
    }
    
    void recycleCachedViewAt(int paramInt)
    {
      b((RecyclerView.d0)mCachedViews.get(paramInt), true);
      mCachedViews.remove(paramInt);
    }
    
    public void recycleView(View paramView)
    {
      RecyclerView.d0 localD0 = RecyclerView.getChildViewHolderInt(paramView);
      if (localD0.isTmpDetached()) {
        removeDetachedView(paramView, false);
      }
      if (localD0.isScrap()) {
        localD0.unScrap();
      } else if (localD0.wasReturnedFromScrap()) {
        localD0.clearReturnedFromScrapFlag();
      }
      recycleViewHolderInternal(localD0);
    }
    
    void recycleViewHolderInternal(RecyclerView.d0 paramD0)
    {
      boolean bool2 = paramD0.isScrap();
      boolean bool1 = false;
      Object localObject;
      if ((!bool2) && (itemView.getParent() == null))
      {
        if (!paramD0.isTmpDetached())
        {
          if (!paramD0.shouldIgnore())
          {
            bool1 = paramD0.doesTransientStatePreventRecycling();
            localObject = mAdapter;
            int i;
            if ((localObject != null) && (bool1) && (((RecyclerView.g)localObject).onFailedToRecycleView(paramD0))) {
              i = 1;
            } else {
              i = 0;
            }
            int k = 0;
            int n = 0;
            int m = 0;
            int j;
            if (i == 0)
            {
              j = m;
              if (!paramD0.isRecyclable()) {}
            }
            else
            {
              i = n;
              if (e > 0)
              {
                i = n;
                if (!paramD0.hasAnyOfTheFlags(526))
                {
                  k = mCachedViews.size();
                  j = k;
                  i = j;
                  if (k >= e)
                  {
                    i = j;
                    if (k > 0)
                    {
                      recycleCachedViewAt(0);
                      i = k - 1;
                    }
                  }
                  j = i;
                  k = j;
                  if (RecyclerView.c)
                  {
                    k = j;
                    if (i > 0)
                    {
                      k = j;
                      if (!d.a(mPosition))
                      {
                        i -= 1;
                        while (i >= 0)
                        {
                          j = mCachedViews.get(i)).mPosition;
                          if (!d.a(j)) {
                            break;
                          }
                          i -= 1;
                        }
                        k = i + 1;
                      }
                    }
                  }
                  mCachedViews.add(k, paramD0);
                  i = 1;
                }
              }
              j = m;
              k = i;
              if (i == 0)
              {
                b(paramD0, true);
                j = 1;
                k = i;
              }
            }
            mViewInfoStore.clear(paramD0);
            if ((k == 0) && (j == 0) && (bool1)) {
              mOwnerRecyclerView = null;
            }
          }
          else
          {
            paramD0 = new StringBuilder();
            paramD0.append("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.");
            paramD0.append(append());
            throw new IllegalArgumentException(paramD0.toString());
          }
        }
        else
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Tmp detached view should be removed from RecyclerView before it can be recycled: ");
          ((StringBuilder)localObject).append(paramD0);
          ((StringBuilder)localObject).append(append());
          throw new IllegalArgumentException(((StringBuilder)localObject).toString());
        }
      }
      else
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Scrapped or attached views may not be recycled. isScrap:");
        ((StringBuilder)localObject).append(paramD0.isScrap());
        ((StringBuilder)localObject).append(" isAttached:");
        if (itemView.getParent() != null) {
          bool1 = true;
        }
        ((StringBuilder)localObject).append(bool1);
        ((StringBuilder)localObject).append(append());
        paramD0 = new IllegalArgumentException(((StringBuilder)localObject).toString());
        throw paramD0;
      }
    }
    
    void scrapView(View paramView)
    {
      paramView = RecyclerView.getChildViewHolderInt(paramView);
      if ((!paramView.hasAnyOfTheFlags(12)) && (paramView.isUpdated()) && (!canReuseUpdatedViewHolder(paramView)))
      {
        if (mChangedScrap == null) {
          mChangedScrap = new ArrayList();
        }
        paramView.setScrapContainer(this, true);
        mChangedScrap.add(paramView);
        return;
      }
      if ((paramView.isInvalid()) && (!paramView.isRemoved()) && (!mAdapter.hasStableIds()))
      {
        paramView = new StringBuilder();
        paramView.append("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
        paramView.append(append());
        throw new IllegalArgumentException(paramView.toString());
      }
      paramView.setScrapContainer(this, false);
      mAttachedScrap.add(paramView);
    }
    
    void setRecycledViewPool(RecyclerView.u paramU)
    {
      RecyclerView.u localU = mRecyclerPool;
      if (localU != null) {
        localU.detach();
      }
      mRecyclerPool = paramU;
      if ((mRecyclerPool != null) && (getAdapter() != null)) {
        mRecyclerPool.attach();
      }
    }
    
    void setViewCacheExtension(RecyclerView.b0 paramB0)
    {
      mViewCacheExtension = paramB0;
    }
    
    void unscrapView(RecyclerView.d0 paramD0)
    {
      if (mInChangeScrap) {
        mChangedScrap.remove(paramD0);
      } else {
        mAttachedScrap.remove(paramD0);
      }
      mScrapContainer = null;
      mInChangeScrap = false;
      paramD0.clearReturnedFromScrapFlag();
    }
    
    boolean validateViewHolderForOffsetPosition(RecyclerView.d0 paramD0)
    {
      if (paramD0.isRemoved()) {
        return mState.get();
      }
      int i = mPosition;
      if ((i >= 0) && (i < mAdapter.a()))
      {
        if ((!mState.get()) && (mAdapter.getItemViewType(mPosition) != paramD0.c())) {
          return false;
        }
        if (mAdapter.hasStableIds())
        {
          if (paramD0.getItemId() == mAdapter.a(mPosition)) {
            return true;
          }
        }
        else {
          return true;
        }
      }
      else
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Inconsistency detected. Invalid view holder adapter position");
        localStringBuilder.append(paramD0);
        localStringBuilder.append(append());
        throw new IndexOutOfBoundsException(localStringBuilder.toString());
      }
      return false;
    }
    
    void viewRangeUpdate(int paramInt1, int paramInt2)
    {
      int i = mCachedViews.size() - 1;
      while (i >= 0)
      {
        RecyclerView.d0 localD0 = (RecyclerView.d0)mCachedViews.get(i);
        if (localD0 != null)
        {
          int j = mPosition;
          if ((j >= paramInt1) && (j < paramInt1 + paramInt2))
          {
            localD0.addFlags(2);
            recycleCachedViewAt(i);
          }
        }
        i -= 1;
      }
    }
  }
  
  public static abstract interface w
  {
    public abstract void andNot(RecyclerView.d0 paramD0);
  }
  
  private class x
    extends RecyclerView.i
  {
    x() {}
    
    public void onItemRangeChanged(int paramInt1, int paramInt2, Object paramObject)
    {
      a(null);
      if (mAdapterHelper.a(paramInt1, paramInt2, paramObject)) {
        triggerUpdateProcessor();
      }
    }
    
    public void onItemRangeInserted()
    {
      a(null);
      RecyclerView localRecyclerView = RecyclerView.this;
      mState.mStructureChanged = true;
      localRecyclerView.setAdapterInternal(true);
      if (!mAdapterHelper.add()) {
        requestLayout();
      }
    }
    
    void triggerUpdateProcessor()
    {
      if (RecyclerView.s)
      {
        localRecyclerView = RecyclerView.this;
        if ((mHasFixedSize) && (mIsAttached))
        {
          ViewCompat.postOnAnimation(localRecyclerView, mUpdateChildViewsRunnable);
          return;
        }
      }
      RecyclerView localRecyclerView = RecyclerView.this;
      mAdapterUpdateDuringMeasure = true;
      localRecyclerView.requestLayout();
    }
  }
  
  public static class y
    extends Artist
  {
    public static final Parcelable.Creator<y> CREATOR = new a();
    Parcelable mLayoutState;
    
    y(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      if (paramClassLoader == null) {
        paramClassLoader = RecyclerView.o.class.getClassLoader();
      }
      mLayoutState = paramParcel.readParcelable(paramClassLoader);
    }
    
    y(Parcelable paramParcelable)
    {
      super();
    }
    
    void access$1900(y paramY)
    {
      mLayoutState = mLayoutState;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeParcelable(mLayoutState, 0);
    }
    
    static final class a
      implements Parcelable.ClassLoaderCreator<RecyclerView.y>
    {
      a() {}
      
      public RecyclerView.y createFromParcel(Parcel paramParcel)
      {
        return new RecyclerView.y(paramParcel, null);
      }
      
      public RecyclerView.y createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
      {
        return new RecyclerView.y(paramParcel, paramClassLoader);
      }
      
      public RecyclerView.y[] newArray(int paramInt)
      {
        return new RecyclerView.y[paramInt];
      }
    }
  }
  
  public static abstract class z
  {
    protected abstract void attachViewToParent(View paramView);
    
    public abstract boolean isRunning();
    
    public abstract int measure();
    
    public abstract void measure(int paramInt);
    
    abstract void measure(int paramInt1, int paramInt2);
    
    public abstract boolean put();
    
    protected final void setId()
    {
      throw new NullPointerException("Null throw statement replaced by Soot");
    }
    
    public static abstract interface a {}
  }
}
