package android.support.design.widget;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.design.internal.g;
import android.support.v4.view.Artist;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.lang.ref.WeakReference;
import java.util.List;
import org.org.android.R.attr;
import org.org.android.R.style;
import org.org.android.R.styleable;
import org.org.android.asm.Item;
import org.org.jaxen.expr.MathUtils;
import org.org.jaxen.util.X509LDAPCertStoreParameters;

@CoordinatorLayout.d(Behavior.class)
public class AppBarLayout
  extends LinearLayout
{
  private boolean E;
  private boolean b;
  private boolean g;
  private int mDownPreScrollRange = -1;
  private int mDownScrollRange = -1;
  private boolean mHaveChildWithInterpolator;
  private WindowInsetsCompat mLastInsets;
  private List<b> mListeners;
  private int mPendingAction = 0;
  private int[] mState;
  private int mTotalScrollRange = -1;
  private boolean writable;
  
  public AppBarLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AppBarLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setOrientation(1);
    if (Build.VERSION.SDK_INT >= 21)
    {
      Frame.setBoundsViewOutlineProvider(this);
      Frame.a(this, paramAttributeSet, 0, R.style.Widget_Design_AppBarLayout);
    }
    paramContext = g.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.AppBarLayout, 0, R.style.Widget_Design_AppBarLayout, new int[0]);
    ViewCompat.setBackgroundDrawable(this, paramContext.getDrawable(R.styleable.AppBarLayout_android_background));
    if (paramContext.hasValue(R.styleable.AppBarLayout_expanded)) {
      setExpanded(paramContext.getBoolean(R.styleable.AppBarLayout_expanded, false), false, false);
    }
    if ((Build.VERSION.SDK_INT >= 21) && (paramContext.hasValue(R.styleable.AppBarLayout_elevation))) {
      Frame.init(this, paramContext.getDimensionPixelSize(R.styleable.AppBarLayout_elevation, 0));
    }
    if (Build.VERSION.SDK_INT >= 26)
    {
      if (paramContext.hasValue(R.styleable.AppBarLayout_android_keyboardNavigationCluster)) {
        setKeyboardNavigationCluster(paramContext.getBoolean(R.styleable.AppBarLayout_android_keyboardNavigationCluster, false));
      }
      if (paramContext.hasValue(R.styleable.AppBarLayout_android_touchscreenBlocksFocus)) {
        setTouchscreenBlocksFocus(paramContext.getBoolean(R.styleable.AppBarLayout_android_touchscreenBlocksFocus, false));
      }
    }
    g = paramContext.getBoolean(R.styleable.AppBarLayout_liftOnScroll, false);
    paramContext.recycle();
    ViewCompat.setOnApplyWindowInsetsListener(this, new a());
  }
  
  private boolean handleMeasuredStateTooSmall()
  {
    int i = 0;
    int j = getChildCount();
    while (i < j)
    {
      if (((c)getChildAt(i).getLayoutParams()).isLoggable()) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private void invalidateScrollRanges()
  {
    mTotalScrollRange = -1;
    mDownPreScrollRange = -1;
    mDownScrollRange = -1;
  }
  
  private boolean open(boolean paramBoolean)
  {
    if (writable != paramBoolean)
    {
      writable = paramBoolean;
      refreshDrawableState();
      return true;
    }
    return false;
  }
  
  private void setExpanded(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    int i;
    if (paramBoolean1) {
      i = 1;
    } else {
      i = 2;
    }
    int k = 0;
    int j;
    if (paramBoolean2) {
      j = 4;
    } else {
      j = 0;
    }
    if (paramBoolean3) {
      k = 8;
    }
    mPendingAction = (i | j | k);
    requestLayout();
  }
  
  WindowInsetsCompat access$000(WindowInsetsCompat paramWindowInsetsCompat)
  {
    WindowInsetsCompat localWindowInsetsCompat = null;
    if (ViewCompat.getFitsSystemWindows(this)) {
      localWindowInsetsCompat = paramWindowInsetsCompat;
    }
    if (!X509LDAPCertStoreParameters.apply(mLastInsets, localWindowInsetsCompat))
    {
      mLastInsets = localWindowInsetsCompat;
      invalidateScrollRanges();
    }
    return paramWindowInsetsCompat;
  }
  
  boolean c(boolean paramBoolean)
  {
    if (E != paramBoolean)
    {
      E = paramBoolean;
      refreshDrawableState();
      return true;
    }
    return false;
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof c;
  }
  
  public boolean e()
  {
    return g;
  }
  
  protected c generateDefaultLayoutParams()
  {
    return new c(-1, -2);
  }
  
  public c generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new c(getContext(), paramAttributeSet);
  }
  
  protected c generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((Build.VERSION.SDK_INT >= 19) && ((paramLayoutParams instanceof LinearLayout.LayoutParams))) {
      return new c((LinearLayout.LayoutParams)paramLayoutParams);
    }
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
      return new c((ViewGroup.MarginLayoutParams)paramLayoutParams);
    }
    return new c(paramLayoutParams);
  }
  
  int getDownNestedPreScrollRange()
  {
    int i = mDownPreScrollRange;
    if (i != -1) {
      return i;
    }
    int k = 0;
    int j = getChildCount() - 1;
    while (j >= 0)
    {
      View localView = getChildAt(j);
      c localC = (c)localView.getLayoutParams();
      i = localView.getMeasuredHeight();
      int m = mScrollFlags;
      if ((m & 0x5) == 5)
      {
        k += topMargin + bottomMargin;
        if ((m & 0x8) != 0) {
          i = k + ViewCompat.getMinimumHeight(localView);
        } else if ((m & 0x2) != 0) {
          i = k + (i - ViewCompat.getMinimumHeight(localView));
        } else {
          i = k + (i - getTopInset());
        }
      }
      else
      {
        i = k;
        if (k > 0) {
          break;
        }
      }
      j -= 1;
      k = i;
    }
    i = Math.max(0, k);
    mDownPreScrollRange = i;
    return i;
  }
  
  int getDownNestedScrollRange()
  {
    int i = mDownScrollRange;
    if (i != -1) {
      return i;
    }
    i = 0;
    int j = 0;
    int m = getChildCount();
    int k;
    for (;;)
    {
      k = i;
      if (j >= m) {
        break;
      }
      View localView = getChildAt(j);
      c localC = (c)localView.getLayoutParams();
      int i1 = localView.getMeasuredHeight();
      int i2 = topMargin;
      int i3 = bottomMargin;
      int n = mScrollFlags;
      k = i;
      if ((n & 0x1) == 0) {
        break;
      }
      i += i1 + (i2 + i3);
      if ((n & 0x2) != 0)
      {
        k = i - (ViewCompat.getMinimumHeight(localView) + getTopInset());
        break;
      }
      j += 1;
    }
    i = Math.max(0, k);
    mDownScrollRange = i;
    return i;
  }
  
  public final int getMinimumHeightForVisibleOverlappingContent()
  {
    int j = getTopInset();
    int i = ViewCompat.getMinimumHeight(this);
    if (i != 0) {
      return i * 2 + j;
    }
    i = getChildCount();
    if (i >= 1) {
      i = ViewCompat.getMinimumHeight(getChildAt(i - 1));
    } else {
      i = 0;
    }
    if (i != 0) {
      return i * 2 + j;
    }
    return getHeight() / 3;
  }
  
  int getPendingAction()
  {
    return mPendingAction;
  }
  
  public float getTargetElevation()
  {
    return 0.0F;
  }
  
  final int getTopInset()
  {
    WindowInsetsCompat localWindowInsetsCompat = mLastInsets;
    if (localWindowInsetsCompat != null) {
      return localWindowInsetsCompat.getSystemWindowInsetTop();
    }
    return 0;
  }
  
  public final int getTotalScrollRange()
  {
    int i = mTotalScrollRange;
    if (i != -1) {
      return i;
    }
    i = 0;
    int j = 0;
    int m = getChildCount();
    int k;
    for (;;)
    {
      k = i;
      if (j >= m) {
        break;
      }
      View localView = getChildAt(j);
      c localC = (c)localView.getLayoutParams();
      int i1 = localView.getMeasuredHeight();
      int n = mScrollFlags;
      k = i;
      if ((n & 0x1) == 0) {
        break;
      }
      i += topMargin + i1 + bottomMargin;
      if ((n & 0x2) != 0)
      {
        k = i - ViewCompat.getMinimumHeight(localView);
        break;
      }
      j += 1;
    }
    i = Math.max(0, k - getTopInset());
    mTotalScrollRange = i;
    return i;
  }
  
  int getUpNestedPreScrollRange()
  {
    return getTotalScrollRange();
  }
  
  boolean hasChildWithInterpolator()
  {
    return mHaveChildWithInterpolator;
  }
  
  boolean hasScrollableChildren()
  {
    return getTotalScrollRange() != 0;
  }
  
  void notifyListeners(int paramInt)
  {
    Object localObject = mListeners;
    if (localObject != null)
    {
      int i = 0;
      int j = ((List)localObject).size();
      while (i < j)
      {
        localObject = (b)mListeners.get(i);
        if (localObject != null) {
          ((b)localObject).onInput(this, paramInt);
        }
        i += 1;
      }
    }
  }
  
  protected int[] onCreateDrawableState(int paramInt)
  {
    if (mState == null) {
      mState = new int[4];
    }
    int[] arrayOfInt1 = mState;
    int[] arrayOfInt2 = super.onCreateDrawableState(arrayOfInt1.length + paramInt);
    if (writable) {
      paramInt = R.attr.state_liftable;
    } else {
      paramInt = -R.attr.state_liftable;
    }
    arrayOfInt1[0] = paramInt;
    if ((writable) && (E)) {
      paramInt = R.attr.state_lifted;
    } else {
      paramInt = -R.attr.state_lifted;
    }
    arrayOfInt1[1] = paramInt;
    if (writable) {
      paramInt = R.attr.state_collapsible;
    } else {
      paramInt = -R.attr.state_collapsible;
    }
    arrayOfInt1[2] = paramInt;
    if ((writable) && (E)) {
      paramInt = R.attr.state_collapsed;
    } else {
      paramInt = -R.attr.state_collapsed;
    }
    arrayOfInt1[3] = paramInt;
    return View.mergeDrawableStates(arrayOfInt2, arrayOfInt1);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    invalidateScrollRanges();
    paramBoolean = false;
    mHaveChildWithInterpolator = false;
    paramInt1 = 0;
    paramInt2 = getChildCount();
    while (paramInt1 < paramInt2)
    {
      if (((c)getChildAt(paramInt1).getLayoutParams()).getScrollInterpolator() != null)
      {
        mHaveChildWithInterpolator = true;
        break;
      }
      paramInt1 += 1;
    }
    if (!b)
    {
      if ((g) || (handleMeasuredStateTooSmall())) {
        paramBoolean = true;
      }
      open(paramBoolean);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    invalidateScrollRanges();
  }
  
  void resetPendingAction()
  {
    mPendingAction = 0;
  }
  
  public void setExpanded(boolean paramBoolean)
  {
    setExpanded(paramBoolean, ViewCompat.get(this));
  }
  
  public void setExpanded(boolean paramBoolean1, boolean paramBoolean2)
  {
    setExpanded(paramBoolean1, paramBoolean2, true);
  }
  
  public void setLiftOnScroll(boolean paramBoolean)
  {
    g = paramBoolean;
  }
  
  public void setOrientation(int paramInt)
  {
    if (paramInt == 1)
    {
      super.setOrientation(paramInt);
      return;
    }
    throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
  }
  
  public void setTargetElevation(float paramFloat)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      Frame.init(this, paramFloat);
    }
  }
  
  protected static class BaseBehavior<T extends AppBarLayout>
    extends n<T>
  {
    private ValueAnimator mAnimator;
    private int mLastNestedScrollDy;
    private WeakReference<View> mLastNestedScrollingChildRef;
    private int mOffsetToChildIndexOnLayout = -1;
    private boolean mOffsetToChildIndexOnLayoutIsMinHeight;
    private float mOffsetToChildIndexOnLayoutPerc;
    private b mOnDragCallback;
    private int o;
    
    public BaseBehavior() {}
    
    public BaseBehavior(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    private void a(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, int paramInt1, int paramInt2, boolean paramBoolean)
    {
      View localView = getChildOnOffset(paramAppBarLayout, paramInt1);
      if (localView != null)
      {
        int i = ((AppBarLayout.c)localView.getLayoutParams()).getHeight();
        boolean bool2 = false;
        boolean bool3 = false;
        boolean bool1 = bool2;
        if ((i & 0x1) != 0)
        {
          int j = ViewCompat.getMinimumHeight(localView);
          if ((paramInt2 > 0) && ((i & 0xC) != 0))
          {
            if (-paramInt1 >= localView.getBottom() - j - paramAppBarLayout.getTopInset()) {
              bool1 = true;
            } else {
              bool1 = false;
            }
          }
          else
          {
            bool1 = bool2;
            if ((i & 0x2) != 0) {
              if (-paramInt1 >= localView.getBottom() - j - paramAppBarLayout.getTopInset()) {
                bool1 = true;
              } else {
                bool1 = false;
              }
            }
          }
        }
        bool2 = bool1;
        if (paramAppBarLayout.e())
        {
          localView = unwrap(paramCoordinatorLayout);
          bool2 = bool1;
          if (localView != null)
          {
            bool1 = bool3;
            if (localView.getScrollY() > 0) {
              bool1 = true;
            }
            bool2 = bool1;
          }
        }
        bool1 = paramAppBarLayout.c(bool2);
        if ((Build.VERSION.SDK_INT >= 11) && ((paramBoolean) || ((bool1) && (performIntercept(paramCoordinatorLayout, paramAppBarLayout))))) {
          paramAppBarLayout.jumpDrawablesToCurrentState();
        }
      }
    }
    
    private void animateOffsetTo(final CoordinatorLayout paramCoordinatorLayout, final AppBarLayout paramAppBarLayout, int paramInt1, int paramInt2)
    {
      int i = getTopBottomOffsetForScrollingSibling();
      if (i == paramInt1)
      {
        paramCoordinatorLayout = mAnimator;
        if ((paramCoordinatorLayout != null) && (paramCoordinatorLayout.isRunning())) {
          mAnimator.cancel();
        }
      }
      else
      {
        ValueAnimator localValueAnimator = mAnimator;
        if (localValueAnimator == null)
        {
          mAnimator = new ValueAnimator();
          mAnimator.setInterpolator(Item.DECELERATE_INTERPOLATOR);
          mAnimator.addUpdateListener(new a(paramCoordinatorLayout, paramAppBarLayout));
        }
        else
        {
          localValueAnimator.cancel();
        }
        mAnimator.setDuration(Math.min(paramInt2, 600));
        mAnimator.setIntValues(new int[] { i, paramInt1 });
        mAnimator.start();
      }
    }
    
    private void fling(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, int paramInt, float paramFloat)
    {
      int i = Math.abs(getTopBottomOffsetForScrollingSibling() - paramInt);
      paramFloat = Math.abs(paramFloat);
      if (paramFloat > 0.0F) {
        i = Math.round(i / paramFloat * 1000.0F) * 3;
      } else {
        i = (int)((1.0F + i / paramAppBarLayout.getHeight()) * 150.0F);
      }
      animateOffsetTo(paramCoordinatorLayout, paramAppBarLayout, paramInt, i);
    }
    
    private static boolean get(int paramInt1, int paramInt2)
    {
      return (paramInt1 & paramInt2) == paramInt2;
    }
    
    private static View getChildOnOffset(AppBarLayout paramAppBarLayout, int paramInt)
    {
      int i = Math.abs(paramInt);
      paramInt = 0;
      int j = paramAppBarLayout.getChildCount();
      while (paramInt < j)
      {
        View localView = paramAppBarLayout.getChildAt(paramInt);
        if ((i >= localView.getTop()) && (i <= localView.getBottom())) {
          return localView;
        }
        paramInt += 1;
      }
      return null;
    }
    
    private int interpolateOffset(AppBarLayout paramAppBarLayout, int paramInt)
    {
      int k = Math.abs(paramInt);
      int i = 0;
      int j = paramAppBarLayout.getChildCount();
      while (i < j)
      {
        View localView = paramAppBarLayout.getChildAt(i);
        AppBarLayout.c localC = (AppBarLayout.c)localView.getLayoutParams();
        Interpolator localInterpolator = localC.getScrollInterpolator();
        if ((k >= localView.getTop()) && (k <= localView.getBottom()))
        {
          if (localInterpolator == null) {
            break;
          }
          i = 0;
          int m = localC.getHeight();
          if ((m & 0x1) != 0)
          {
            j = 0 + (localView.getHeight() + topMargin + bottomMargin);
            i = j;
            if ((m & 0x2) != 0) {
              i = j - ViewCompat.getMinimumHeight(localView);
            }
          }
          j = i;
          if (ViewCompat.getFitsSystemWindows(localView)) {
            j = i - paramAppBarLayout.getTopInset();
          }
          i = paramInt;
          if (j > 0)
          {
            i = localView.getTop();
            i = Math.round(j * localInterpolator.getInterpolation((k - i) / j));
            i = Integer.signum(paramInt) * (localView.getTop() + i);
          }
          return i;
        }
        i += 1;
      }
      return paramInt;
    }
    
    private int onDrawOver(AppBarLayout paramAppBarLayout, int paramInt)
    {
      int i = 0;
      int i1 = paramAppBarLayout.getChildCount();
      while (i < i1)
      {
        Object localObject = paramAppBarLayout.getChildAt(i);
        int n = ((View)localObject).getTop();
        int k = n;
        int m = ((View)localObject).getBottom();
        int j = m;
        localObject = (AppBarLayout.c)((View)localObject).getLayoutParams();
        if (get(((AppBarLayout.c)localObject).getHeight(), 32))
        {
          k = n - topMargin;
          j = m + bottomMargin;
        }
        if ((k <= -paramInt) && (j >= -paramInt)) {
          return i;
        }
        i += 1;
      }
      return -1;
    }
    
    private void onLayoutChild(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout)
    {
      int n = getTopBottomOffsetForScrollingSibling();
      int k = onDrawOver(paramAppBarLayout, n);
      if (k >= 0)
      {
        View localView = paramAppBarLayout.getChildAt(k);
        AppBarLayout.c localC = (AppBarLayout.c)localView.getLayoutParams();
        int i1 = localC.getHeight();
        if ((i1 & 0x11) == 17)
        {
          int m = -localView.getTop();
          int i = -localView.getBottom();
          int j = i;
          if (k == paramAppBarLayout.getChildCount() - 1) {
            j = i + paramAppBarLayout.getTopInset();
          }
          if (get(i1, 2))
          {
            i = j + ViewCompat.getMinimumHeight(localView);
            k = m;
          }
          else
          {
            k = m;
            i = j;
            if (get(i1, 5))
            {
              i = ViewCompat.getMinimumHeight(localView) + j;
              if (n < i)
              {
                k = i;
                i = j;
              }
              else
              {
                k = m;
              }
            }
          }
          m = k;
          j = i;
          if (get(i1, 32))
          {
            m = k + topMargin;
            j = i - bottomMargin;
          }
          if (n >= (j + m) / 2) {
            j = m;
          }
          fling(paramCoordinatorLayout, paramAppBarLayout, MathUtils.constrain(j, -paramAppBarLayout.getTotalScrollRange(), 0), 0.0F);
        }
      }
    }
    
    private void onNestedPreScroll(int paramInt1, AppBarLayout paramAppBarLayout, View paramView, int paramInt2)
    {
      if (paramInt2 == 1)
      {
        paramInt2 = getTopBottomOffsetForScrollingSibling();
        if (((paramInt1 < 0) && (paramInt2 == 0)) || ((paramInt1 > 0) && (paramInt2 == -paramAppBarLayout.getDownNestedScrollRange()))) {
          ViewCompat.setNestedScrollingEnabled(paramView, 1);
        }
      }
    }
    
    private boolean onStartNestedScroll(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, View paramView)
    {
      return (paramAppBarLayout.hasScrollableChildren()) && (paramCoordinatorLayout.getHeight() - paramView.getHeight() <= paramAppBarLayout.getHeight());
    }
    
    private boolean performIntercept(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout)
    {
      paramCoordinatorLayout = paramCoordinatorLayout.get(paramAppBarLayout);
      int i = 0;
      int j = paramCoordinatorLayout.size();
      while (i < j)
      {
        paramAppBarLayout = ((CoordinatorLayout.f)((View)paramCoordinatorLayout.get(i)).getLayoutParams()).getBehavior();
        if ((paramAppBarLayout instanceof AppBarLayout.ScrollingViewBehavior))
        {
          if (((AppBarLayout.ScrollingViewBehavior)paramAppBarLayout).getOverlayTop() == 0) {
            break;
          }
          return true;
        }
        i += 1;
      }
      return false;
    }
    
    private View unwrap(CoordinatorLayout paramCoordinatorLayout)
    {
      int i = 0;
      int j = paramCoordinatorLayout.getChildCount();
      while (i < j)
      {
        View localView = paramCoordinatorLayout.getChildAt(i);
        if ((localView instanceof NestedScrollingChild)) {
          return localView;
        }
        i += 1;
      }
      return null;
    }
    
    boolean canDragView(AppBarLayout paramAppBarLayout)
    {
      b localB = mOnDragCallback;
      if (localB != null) {
        return localB.canDrag(paramAppBarLayout);
      }
      paramAppBarLayout = mLastNestedScrollingChildRef;
      if (paramAppBarLayout != null)
      {
        paramAppBarLayout = (View)paramAppBarLayout.get();
        return (paramAppBarLayout != null) && (paramAppBarLayout.isShown()) && (!paramAppBarLayout.canScrollVertically(-1));
      }
      return true;
    }
    
    int getMaxDragOffset(AppBarLayout paramAppBarLayout)
    {
      return -paramAppBarLayout.getDownNestedScrollRange();
    }
    
    int getScrollRangeForDragFling(AppBarLayout paramAppBarLayout)
    {
      return paramAppBarLayout.getTotalScrollRange();
    }
    
    int getTopBottomOffsetForScrollingSibling()
    {
      return getTopAndBottomOffset() + o;
    }
    
    void onFlingFinished(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout)
    {
      onLayoutChild(paramCoordinatorLayout, paramAppBarLayout);
    }
    
    public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, int paramInt)
    {
      boolean bool = super.onLayoutChild(paramCoordinatorLayout, paramAppBarLayout, paramInt);
      int i = paramAppBarLayout.getPendingAction();
      paramInt = mOffsetToChildIndexOnLayout;
      if ((paramInt >= 0) && ((i & 0x8) == 0))
      {
        View localView = paramAppBarLayout.getChildAt(paramInt);
        paramInt = -localView.getBottom();
        if (mOffsetToChildIndexOnLayoutIsMinHeight) {
          paramInt += ViewCompat.getMinimumHeight(localView) + paramAppBarLayout.getTopInset();
        } else {
          paramInt += Math.round(localView.getHeight() * mOffsetToChildIndexOnLayoutPerc);
        }
        setHeaderTopBottomOffset(paramCoordinatorLayout, paramAppBarLayout, paramInt);
      }
      else if (i != 0)
      {
        if ((i & 0x4) != 0) {
          paramInt = 1;
        } else {
          paramInt = 0;
        }
        if ((i & 0x2) != 0)
        {
          i = -paramAppBarLayout.getUpNestedPreScrollRange();
          if (paramInt != 0) {
            fling(paramCoordinatorLayout, paramAppBarLayout, i, 0.0F);
          } else {
            setHeaderTopBottomOffset(paramCoordinatorLayout, paramAppBarLayout, i);
          }
        }
        else if ((i & 0x1) != 0)
        {
          if (paramInt != 0) {
            fling(paramCoordinatorLayout, paramAppBarLayout, 0, 0.0F);
          } else {
            setHeaderTopBottomOffset(paramCoordinatorLayout, paramAppBarLayout, 0);
          }
        }
      }
      paramAppBarLayout.resetPendingAction();
      mOffsetToChildIndexOnLayout = -1;
      setTopAndBottomOffset(MathUtils.constrain(getTopAndBottomOffset(), -paramAppBarLayout.getTotalScrollRange(), 0));
      a(paramCoordinatorLayout, paramAppBarLayout, getTopAndBottomOffset(), 0, true);
      paramAppBarLayout.notifyListeners(getTopAndBottomOffset());
      return bool;
    }
    
    public boolean onMeasureChild(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      if (getLayoutParamsheight == -2)
      {
        paramCoordinatorLayout.onMeasureChild(paramAppBarLayout, paramInt1, paramInt2, View.MeasureSpec.makeMeasureSpec(0, 0), paramInt4);
        return true;
      }
      return super.onMeasureChild(paramCoordinatorLayout, paramAppBarLayout, paramInt1, paramInt2, paramInt3, paramInt4);
    }
    
    public void onNestedPreScroll(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt, int paramInt3)
    {
      if (paramInt2 != 0)
      {
        int i;
        if (paramInt2 < 0)
        {
          i = -paramAppBarLayout.getTotalScrollRange();
          paramInt1 = paramAppBarLayout.getDownNestedPreScrollRange() + i;
        }
        else
        {
          i = -paramAppBarLayout.getUpNestedPreScrollRange();
          paramInt1 = 0;
        }
        if (i != paramInt1)
        {
          paramArrayOfInt[1] = scroll(paramCoordinatorLayout, paramAppBarLayout, paramInt2, i, paramInt1);
          onNestedPreScroll(paramInt2, paramAppBarLayout, paramView, paramInt3);
        }
      }
    }
    
    public void onNestedScroll(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
    {
      if (paramInt4 < 0)
      {
        scroll(paramCoordinatorLayout, paramAppBarLayout, paramInt4, -paramAppBarLayout.getDownNestedScrollRange(), 0);
        onNestedPreScroll(paramInt4, paramAppBarLayout, paramView, paramInt5);
      }
      if (paramAppBarLayout.e())
      {
        boolean bool;
        if (paramView.getScrollY() > 0) {
          bool = true;
        } else {
          bool = false;
        }
        paramAppBarLayout.c(bool);
      }
    }
    
    public void onRestoreInstanceState(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, Parcelable paramParcelable)
    {
      if ((paramParcelable instanceof c))
      {
        paramParcelable = (c)paramParcelable;
        super.onRestoreInstanceState(paramCoordinatorLayout, paramAppBarLayout, paramParcelable.getSuperState());
        mOffsetToChildIndexOnLayout = firstVisibleChildIndex;
        mOffsetToChildIndexOnLayoutPerc = firstVisibileChildPercentageShown;
        mOffsetToChildIndexOnLayoutIsMinHeight = firstVisibileChildAtMinimumHeight;
        return;
      }
      super.onRestoreInstanceState(paramCoordinatorLayout, paramAppBarLayout, paramParcelable);
      mOffsetToChildIndexOnLayout = -1;
    }
    
    public Parcelable onSaveInstanceState(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout)
    {
      Object localObject = super.onSaveInstanceState(paramCoordinatorLayout, paramAppBarLayout);
      int j = getTopAndBottomOffset();
      int i = 0;
      int k = paramAppBarLayout.getChildCount();
      while (i < k)
      {
        paramCoordinatorLayout = paramAppBarLayout.getChildAt(i);
        int m = paramCoordinatorLayout.getBottom() + j;
        if ((paramCoordinatorLayout.getTop() + j <= 0) && (m >= 0))
        {
          localObject = new c((Parcelable)localObject);
          firstVisibleChildIndex = i;
          boolean bool;
          if (m == ViewCompat.getMinimumHeight(paramCoordinatorLayout) + paramAppBarLayout.getTopInset()) {
            bool = true;
          } else {
            bool = false;
          }
          firstVisibileChildAtMinimumHeight = bool;
          firstVisibileChildPercentageShown = (m / paramCoordinatorLayout.getHeight());
          return localObject;
        }
        i += 1;
      }
      return localObject;
    }
    
    public boolean onStartNestedScroll(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, View paramView1, View paramView2, int paramInt1, int paramInt2)
    {
      boolean bool;
      if (((paramInt1 & 0x2) != 0) && ((paramAppBarLayout.e()) || (onStartNestedScroll(paramCoordinatorLayout, paramAppBarLayout, paramView1)))) {
        bool = true;
      } else {
        bool = false;
      }
      if (bool)
      {
        paramCoordinatorLayout = mAnimator;
        if (paramCoordinatorLayout != null) {
          paramCoordinatorLayout.cancel();
        }
      }
      mLastNestedScrollingChildRef = null;
      mLastNestedScrollDy = paramInt2;
      return bool;
    }
    
    public void onStopNestedScroll(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, View paramView, int paramInt)
    {
      if ((mLastNestedScrollDy == 0) || (paramInt == 1)) {
        onLayoutChild(paramCoordinatorLayout, paramAppBarLayout);
      }
      mLastNestedScrollingChildRef = new WeakReference(paramView);
    }
    
    int setHeaderTopBottomOffset(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout, int paramInt1, int paramInt2, int paramInt3)
    {
      int i = getTopBottomOffsetForScrollingSibling();
      if ((paramInt2 != 0) && (i >= paramInt2) && (i <= paramInt3))
      {
        paramInt2 = MathUtils.constrain(paramInt1, paramInt2, paramInt3);
        if (i != paramInt2)
        {
          if (paramAppBarLayout.hasChildWithInterpolator()) {
            paramInt1 = interpolateOffset(paramAppBarLayout, paramInt2);
          } else {
            paramInt1 = paramInt2;
          }
          boolean bool = setTopAndBottomOffset(paramInt1);
          o = (paramInt2 - paramInt1);
          if ((!bool) && (paramAppBarLayout.hasChildWithInterpolator())) {
            paramCoordinatorLayout.dispatchDependentViewsChanged(paramAppBarLayout);
          }
          paramAppBarLayout.notifyListeners(getTopAndBottomOffset());
          if (paramInt2 < i) {
            paramInt1 = -1;
          } else {
            paramInt1 = 1;
          }
          a(paramCoordinatorLayout, paramAppBarLayout, paramInt2, paramInt1, false);
          return i - paramInt2;
        }
      }
      else
      {
        o = 0;
      }
      return 0;
    }
    
    class a
      implements ValueAnimator.AnimatorUpdateListener
    {
      a(CoordinatorLayout paramCoordinatorLayout, AppBarLayout paramAppBarLayout) {}
      
      public void onAnimationUpdate(ValueAnimator paramValueAnimator)
      {
        setHeaderTopBottomOffset(paramCoordinatorLayout, paramAppBarLayout, ((Integer)paramValueAnimator.getAnimatedValue()).intValue());
      }
    }
    
    public static abstract class b<T extends AppBarLayout>
    {
      public abstract boolean canDrag(AppBarLayout paramAppBarLayout);
    }
    
    protected static class c
      extends Artist
    {
      public static final Parcelable.Creator<c> CREATOR = new a();
      boolean firstVisibileChildAtMinimumHeight;
      float firstVisibileChildPercentageShown;
      int firstVisibleChildIndex;
      
      public c(Parcel paramParcel, ClassLoader paramClassLoader)
      {
        super(paramClassLoader);
        firstVisibleChildIndex = paramParcel.readInt();
        firstVisibileChildPercentageShown = paramParcel.readFloat();
        boolean bool;
        if (paramParcel.readByte() != 0) {
          bool = true;
        } else {
          bool = false;
        }
        firstVisibileChildAtMinimumHeight = bool;
      }
      
      public c(Parcelable paramParcelable)
      {
        super();
      }
      
      public void writeToParcel(Parcel paramParcel, int paramInt)
      {
        super.writeToParcel(paramParcel, paramInt);
        paramParcel.writeInt(firstVisibleChildIndex);
        paramParcel.writeFloat(firstVisibileChildPercentageShown);
        paramParcel.writeByte((byte)firstVisibileChildAtMinimumHeight);
      }
      
      static final class a
        implements Parcelable.ClassLoaderCreator<AppBarLayout.BaseBehavior.c>
      {
        a() {}
        
        public AppBarLayout.BaseBehavior.c createFromParcel(Parcel paramParcel)
        {
          return new AppBarLayout.BaseBehavior.c(paramParcel, null);
        }
        
        public AppBarLayout.BaseBehavior.c createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
        {
          return new AppBarLayout.BaseBehavior.c(paramParcel, paramClassLoader);
        }
        
        public AppBarLayout.BaseBehavior.c[] newArray(int paramInt)
        {
          return new AppBarLayout.BaseBehavior.c[paramInt];
        }
      }
    }
  }
  
  public static class Behavior
    extends AppBarLayout.BaseBehavior<AppBarLayout>
  {
    public Behavior() {}
    
    public Behavior(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
  }
  
  public static class ScrollingViewBehavior
    extends HeaderScrollingViewBehavior
  {
    public ScrollingViewBehavior() {}
    
    public ScrollingViewBehavior(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ScrollingViewBehavior_Layout);
      setOverlayTop(paramContext.getDimensionPixelSize(R.styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
      paramContext.recycle();
    }
    
    private static int getAppBarLayoutOffset(AppBarLayout paramAppBarLayout)
    {
      paramAppBarLayout = ((CoordinatorLayout.f)paramAppBarLayout.getLayoutParams()).getBehavior();
      if ((paramAppBarLayout instanceof AppBarLayout.BaseBehavior)) {
        return ((AppBarLayout.BaseBehavior)paramAppBarLayout).getTopBottomOffsetForScrollingSibling();
      }
      return 0;
    }
    
    private void offsetChildAsNeeded(View paramView1, View paramView2)
    {
      Object localObject = ((CoordinatorLayout.f)paramView2.getLayoutParams()).getBehavior();
      if ((localObject instanceof AppBarLayout.BaseBehavior))
      {
        localObject = (AppBarLayout.BaseBehavior)localObject;
        ViewCompat.offsetTopAndBottom(paramView1, paramView2.getBottom() - paramView1.getTop() + AppBarLayout.BaseBehavior.c((AppBarLayout.BaseBehavior)localObject) + getVerticalLayoutGap() - getOverlapPixelsForOffset(paramView2));
      }
    }
    
    private void onNestedPreScroll(View paramView1, View paramView2)
    {
      if ((paramView2 instanceof AppBarLayout))
      {
        paramView2 = (AppBarLayout)paramView2;
        if (paramView2.e())
        {
          boolean bool;
          if (paramView1.getScrollY() > 0) {
            bool = true;
          } else {
            bool = false;
          }
          paramView2.c(bool);
        }
      }
    }
    
    AppBarLayout findFirstDependency(List paramList)
    {
      int i = 0;
      int j = paramList.size();
      while (i < j)
      {
        View localView = (View)paramList.get(i);
        if ((localView instanceof AppBarLayout)) {
          return (AppBarLayout)localView;
        }
        i += 1;
      }
      return null;
    }
    
    float getOverlapRatioForOffset(View paramView)
    {
      if ((paramView instanceof AppBarLayout))
      {
        paramView = (AppBarLayout)paramView;
        int j = paramView.getTotalScrollRange();
        int k = paramView.getDownNestedPreScrollRange();
        int i = getAppBarLayoutOffset(paramView);
        if ((k != 0) && (j + i <= k)) {
          return 0.0F;
        }
        j -= k;
        if (j != 0) {
          return i / j + 1.0F;
        }
      }
      return 0.0F;
    }
    
    int getScrollRange(View paramView)
    {
      if ((paramView instanceof AppBarLayout)) {
        return ((AppBarLayout)paramView).getTotalScrollRange();
      }
      return super.getScrollRange(paramView);
    }
    
    public boolean onDependentViewChanged(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
    {
      return paramView2 instanceof AppBarLayout;
    }
    
    public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, View paramView, Rect paramRect, boolean paramBoolean)
    {
      AppBarLayout localAppBarLayout = findFirstDependency(paramCoordinatorLayout.getDependencies(paramView));
      if (localAppBarLayout != null)
      {
        paramRect.offset(paramView.getLeft(), paramView.getTop());
        paramView = mTempRect1;
        paramView.set(0, 0, paramCoordinatorLayout.getWidth(), paramCoordinatorLayout.getHeight());
        if (!paramView.contains(paramRect))
        {
          localAppBarLayout.setExpanded(false, paramBoolean ^ true);
          return true;
        }
      }
      return false;
    }
    
    public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
    {
      offsetChildAsNeeded(paramView1, paramView2);
      onNestedPreScroll(paramView1, paramView2);
      return false;
    }
  }
  
  class a
    implements OnApplyWindowInsetsListener
  {
    a() {}
    
    public WindowInsetsCompat onApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat)
    {
      return access$000(paramWindowInsetsCompat);
    }
  }
  
  public static abstract interface b<T extends AppBarLayout>
  {
    public abstract void onInput(AppBarLayout paramAppBarLayout, int paramInt);
  }
  
  public static class c
    extends LinearLayout.LayoutParams
  {
    int mScrollFlags = 1;
    Interpolator mScrollInterpolator;
    
    public c(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public c(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AppBarLayout_Layout);
      mScrollFlags = paramAttributeSet.getInt(R.styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
      if (paramAttributeSet.hasValue(R.styleable.AppBarLayout_Layout_layout_scrollInterpolator)) {
        mScrollInterpolator = AnimationUtils.loadInterpolator(paramContext, paramAttributeSet.getResourceId(R.styleable.AppBarLayout_Layout_layout_scrollInterpolator, 0));
      }
      paramAttributeSet.recycle();
    }
    
    public c(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public c(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
    
    public c(LinearLayout.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public int getHeight()
    {
      return mScrollFlags;
    }
    
    public Interpolator getScrollInterpolator()
    {
      return mScrollInterpolator;
    }
    
    boolean isLoggable()
    {
      int i = mScrollFlags;
      return ((i & 0x1) == 1) && ((i & 0xA) != 0);
    }
  }
}
