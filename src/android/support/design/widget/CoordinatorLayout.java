package android.support.design.widget;

import a.b.g.g.j;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.Artist;
import android.support.v4.view.CoordinatorLayout.LayoutParams;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.ViewGroupUtilsHoneycomb;
import android.support.v4.widget.f;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.AbsSavedState;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.org.jaxen.util.Pools.Pool;
import org.org.jaxen.util.Pools.SynchronizedPool;
import org.org.jaxen.util.X509LDAPCertStoreParameters;
import org.org.net.IpAddress;
import org.org.net.SocketAddress;
import org.org.net.TransactionOutputChanges;

public class CoordinatorLayout
  extends ViewGroup
  implements CoordinatorLayout.LayoutParams
{
  static final Class<?>[] CONSTRUCTOR_PARAMS;
  static final Comparator<View> TOP_SORTED_CHILDREN_COMPARATOR;
  static final String WIDGET_PACKAGE_NAME;
  private static final j<Rect> lock = new Pools.SynchronizedPool(12);
  static final ThreadLocal<Map<String, Constructor<c>>> sConstructors;
  private final f<View> c = new f();
  private final List<View> data = new ArrayList();
  private final int[] h = new int[2];
  private View mBehaviorTouchView;
  private boolean mDisallowInterceptReset;
  private boolean mDrawStatusBarBackground;
  private boolean mIsAttachedToWindow;
  private int[] mKeylines;
  private WindowInsetsCompat mLastInsets;
  private boolean mNeedsPreDrawListener;
  private final NestedScrollingParentHelper mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
  private View mNestedScrollingTarget;
  ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
  private g mOnPreDrawListener;
  private Paint mScrimPaint;
  private Drawable mStatusBarBackground;
  private OnApplyWindowInsetsListener mSubDecor;
  private final List<View> mTempList1 = new ArrayList();
  private final List<View> x = new ArrayList();
  
  static
  {
    Object localObject = CoordinatorLayout.class.getPackage();
    if (localObject != null) {
      localObject = ((Package)localObject).getName();
    } else {
      localObject = null;
    }
    WIDGET_PACKAGE_NAME = (String)localObject;
    if (Build.VERSION.SDK_INT >= 21) {
      TOP_SORTED_CHILDREN_COMPARATOR = new i();
    } else {
      TOP_SORTED_CHILDREN_COMPARATOR = null;
    }
    CONSTRUCTOR_PARAMS = new Class[] { Context.class, AttributeSet.class };
    sConstructors = new ThreadLocal();
  }
  
  public CoordinatorLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CoordinatorLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, SocketAddress.coordinatorLayoutStyle);
  }
  
  public CoordinatorLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    if (paramInt == 0) {
      paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, IpAddress.CoordinatorLayout, 0, TransactionOutputChanges.Widget_Support_CoordinatorLayout);
    } else {
      paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, IpAddress.CoordinatorLayout, paramInt, 0);
    }
    paramInt = paramAttributeSet.getResourceId(IpAddress.CoordinatorLayout_keylines, 0);
    if (paramInt != 0)
    {
      paramContext = paramContext.getResources();
      mKeylines = paramContext.getIntArray(paramInt);
      float f = getDisplayMetricsdensity;
      int i = mKeylines.length;
      paramInt = 0;
      while (paramInt < i)
      {
        paramContext = mKeylines;
        paramContext[paramInt] = ((int)(paramContext[paramInt] * f));
        paramInt += 1;
      }
    }
    mStatusBarBackground = paramAttributeSet.getDrawable(IpAddress.CoordinatorLayout_statusBarBackground);
    paramAttributeSet.recycle();
    ensureSubDecor();
    super.setOnHierarchyChangeListener(new e());
  }
  
  private static int constrain(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 < paramInt2) {
      return paramInt2;
    }
    if (paramInt1 > paramInt3) {
      return paramInt3;
    }
    return paramInt1;
  }
  
  private static void decode(Rect paramRect)
  {
    paramRect.setEmpty();
    lock.release(paramRect);
  }
  
  private WindowInsetsCompat dispatchApplyWindowInsetsToBehaviors(WindowInsetsCompat paramWindowInsetsCompat)
  {
    if (paramWindowInsetsCompat.isConsumed()) {
      return paramWindowInsetsCompat;
    }
    int i = 0;
    int j = getChildCount();
    for (WindowInsetsCompat localWindowInsetsCompat = paramWindowInsetsCompat; i < j; localWindowInsetsCompat = paramWindowInsetsCompat)
    {
      View localView = getChildAt(i);
      paramWindowInsetsCompat = localWindowInsetsCompat;
      if (ViewCompat.getFitsSystemWindows(localView))
      {
        c localC = ((f)localView.getLayoutParams()).getBehavior();
        paramWindowInsetsCompat = localWindowInsetsCompat;
        if (localC != null)
        {
          localWindowInsetsCompat = localC.onApplyWindowInsets(this, localView, localWindowInsetsCompat);
          paramWindowInsetsCompat = localWindowInsetsCompat;
          if (localWindowInsetsCompat.isConsumed()) {
            return localWindowInsetsCompat;
          }
        }
      }
      i += 1;
    }
    return localWindowInsetsCompat;
  }
  
  private void ensureSubDecor()
  {
    if (Build.VERSION.SDK_INT < 21) {
      return;
    }
    if (ViewCompat.getFitsSystemWindows(this))
    {
      if (mSubDecor == null) {
        mSubDecor = new a();
      }
      ViewCompat.setOnApplyWindowInsetsListener(this, mSubDecor);
      setSystemUiVisibility(1280);
      return;
    }
    ViewCompat.setOnApplyWindowInsetsListener(this, null);
  }
  
  private static Rect get()
  {
    Rect localRect2 = (Rect)lock.acquire();
    Rect localRect1 = localRect2;
    if (localRect2 == null) {
      localRect1 = new Rect();
    }
    return localRect1;
  }
  
  private void getDesiredAnchoredChildRect(f paramF, Rect paramRect, int paramInt1, int paramInt2)
  {
    int j = getWidth();
    int i = getHeight();
    j = Math.max(getPaddingLeft() + leftMargin, Math.min(left, j - getPaddingRight() - paramInt1 - rightMargin));
    i = Math.max(getPaddingTop() + topMargin, Math.min(top, i - getPaddingBottom() - paramInt2 - bottomMargin));
    paramRect.set(j, i, j + paramInt1, i + paramInt2);
  }
  
  private void getDesiredAnchoredChildRect(View paramView, int paramInt1, Rect paramRect1, Rect paramRect2, f paramF, int paramInt2, int paramInt3)
  {
    int i = GravityCompat.getAbsoluteGravity(resolveAnchoredChildGravity(gravity), paramInt1);
    paramInt1 = GravityCompat.getAbsoluteGravity(resolveGravity(anchorGravity), paramInt1);
    int k = i & 0x7;
    int j = i & 0x70;
    int m = paramInt1 & 0x7;
    i = paramInt1 & 0x70;
    if (m != 1)
    {
      if (m != 5) {
        paramInt1 = left;
      } else {
        paramInt1 = right;
      }
    }
    else {
      paramInt1 = left + paramRect1.width() / 2;
    }
    if (i != 16)
    {
      if (i != 80) {
        i = top;
      } else {
        i = bottom;
      }
    }
    else {
      i = top + paramRect1.height() / 2;
    }
    if (k != 1)
    {
      if (k != 5) {
        paramInt1 -= paramInt2;
      }
    }
    else {
      paramInt1 -= paramInt2 / 2;
    }
    if (j != 16)
    {
      if (j != 80) {
        i -= paramInt3;
      }
    }
    else {
      i -= paramInt3 / 2;
    }
    paramRect2.set(paramInt1, i, paramInt1 + paramInt2, i + paramInt3);
  }
  
  private int getKeyline(int paramInt)
  {
    Object localObject = mKeylines;
    if (localObject == null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("No keylines defined for ");
      ((StringBuilder)localObject).append(this);
      ((StringBuilder)localObject).append(" - attempted index lookup ");
      ((StringBuilder)localObject).append(paramInt);
      Log.e("CoordinatorLayout", ((StringBuilder)localObject).toString());
      return 0;
    }
    if ((paramInt >= 0) && (paramInt < localObject.length)) {
      return localObject[paramInt];
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Keyline index ");
    ((StringBuilder)localObject).append(paramInt);
    ((StringBuilder)localObject).append(" out of range for ");
    ((StringBuilder)localObject).append(this);
    Log.e("CoordinatorLayout", ((StringBuilder)localObject).toString());
    return 0;
  }
  
  private void getTopSortedChildren(List paramList)
  {
    paramList.clear();
    boolean bool = isChildrenDrawingOrderEnabled();
    int k = getChildCount();
    int i = k - 1;
    while (i >= 0)
    {
      int j;
      if (bool) {
        j = getChildDrawingOrder(k, i);
      } else {
        j = i;
      }
      paramList.add(getChildAt(j));
      i -= 1;
    }
    Comparator localComparator = TOP_SORTED_CHILDREN_COMPARATOR;
    if (localComparator != null) {
      Collections.sort(paramList, localComparator);
    }
  }
  
  private boolean hasDependencies(View paramView)
  {
    return c.b(paramView);
  }
  
  private void layoutChild(View paramView, int paramInt)
  {
    f localF = (f)paramView.getLayoutParams();
    Rect localRect1 = get();
    localRect1.set(getPaddingLeft() + leftMargin, getPaddingTop() + topMargin, getWidth() - getPaddingRight() - rightMargin, getHeight() - getPaddingBottom() - bottomMargin);
    if ((mLastInsets != null) && (ViewCompat.getFitsSystemWindows(this)) && (!ViewCompat.getFitsSystemWindows(paramView)))
    {
      left += mLastInsets.getSystemWindowInsetLeft();
      top += mLastInsets.getSystemWindowInsetTop();
      right -= mLastInsets.getSystemWindowInsetRight();
      bottom -= mLastInsets.getSystemWindowInsetBottom();
    }
    Rect localRect2 = get();
    GravityCompat.apply(resolveGravity(gravity), paramView.getMeasuredWidth(), paramView.getMeasuredHeight(), localRect1, localRect2, paramInt);
    paramView.layout(left, top, right, bottom);
    decode(localRect1);
    decode(localRect2);
  }
  
  private void layoutChildWithAnchor(View paramView1, View paramView2, int paramInt)
  {
    Rect localRect1 = get();
    Rect localRect2 = get();
    try
    {
      getDescendantRect(paramView2, localRect1);
      getDesiredAnchoredChildRect(paramView1, paramInt, localRect1, localRect2);
      paramView1.layout(left, top, right, bottom);
      decode(localRect1);
      decode(localRect2);
      return;
    }
    catch (Throwable paramView1)
    {
      decode(localRect1);
      decode(localRect2);
      throw paramView1;
    }
  }
  
  private void layoutChildWithKeyline(View paramView, int paramInt1, int paramInt2)
  {
    f localF = (f)paramView.getLayoutParams();
    int i = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(gravity), paramInt2);
    int i2 = i & 0x7;
    int i1 = i & 0x70;
    int n = getWidth();
    int m = getHeight();
    int j = paramView.getMeasuredWidth();
    int k = paramView.getMeasuredHeight();
    i = paramInt1;
    if (paramInt2 == 1) {
      i = n - paramInt1;
    }
    paramInt1 = getKeyline(i) - j;
    paramInt2 = 0;
    if (i2 != 1)
    {
      if (i2 == 5) {
        paramInt1 += j;
      }
    }
    else {
      paramInt1 += j / 2;
    }
    if (i1 != 16)
    {
      if (i1 == 80) {
        paramInt2 = 0 + k;
      }
    }
    else {
      paramInt2 = 0 + k / 2;
    }
    paramInt1 = Math.max(getPaddingLeft() + leftMargin, Math.min(paramInt1, n - getPaddingRight() - j - rightMargin));
    paramInt2 = Math.max(getPaddingTop() + topMargin, Math.min(paramInt2, m - getPaddingBottom() - k - bottomMargin));
    paramView.layout(paramInt1, paramInt2, paramInt1 + j, paramInt2 + k);
  }
  
  private void onMeasureChild(View paramView, int paramInt)
  {
    f localF = (f)paramView.getLayoutParams();
    int i = width;
    if (i != paramInt)
    {
      ViewCompat.offsetTopAndBottom(paramView, paramInt - i);
      width = paramInt;
    }
  }
  
  static c parseBehavior(Context paramContext, AttributeSet paramAttributeSet, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    Object localObject1;
    if (paramString.startsWith("."))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(paramContext.getPackageName());
      ((StringBuilder)localObject1).append(paramString);
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    else if (paramString.indexOf('.') >= 0)
    {
      localObject1 = paramString;
    }
    else
    {
      localObject1 = paramString;
      if (!TextUtils.isEmpty(WIDGET_PACKAGE_NAME))
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(WIDGET_PACKAGE_NAME);
        ((StringBuilder)localObject1).append('.');
        ((StringBuilder)localObject1).append(paramString);
        localObject1 = ((StringBuilder)localObject1).toString();
      }
    }
    paramString = sConstructors;
    try
    {
      paramString = paramString.get();
      Object localObject2 = (Map)paramString;
      paramString = (String)localObject2;
      if (localObject2 == null)
      {
        localObject2 = new HashMap();
        paramString = (String)localObject2;
        localObject3 = sConstructors;
        ((ThreadLocal)localObject3).set(localObject2);
      }
      localObject2 = (Map)paramString;
      localObject2 = ((Map)localObject2).get(localObject1);
      Object localObject3 = (Constructor)localObject2;
      localObject2 = localObject3;
      if (localObject3 == null)
      {
        localObject2 = paramContext.getClassLoader().loadClass((String)localObject1);
        localObject3 = CONSTRUCTOR_PARAMS;
        localObject3 = ((Class)localObject2).getConstructor((Class[])localObject3);
        localObject2 = localObject3;
        ((Constructor)localObject3).setAccessible(true);
        paramString = (Map)paramString;
        paramString.put(localObject1, localObject3);
      }
      paramContext = ((Constructor)localObject2).newInstance(new Object[] { paramContext, paramAttributeSet });
      return (c)paramContext;
    }
    catch (Exception paramContext)
    {
      paramAttributeSet = new StringBuilder();
      paramAttributeSet.append("Could not inflate Behavior subclass ");
      paramAttributeSet.append((String)localObject1);
      throw new RuntimeException(paramAttributeSet.toString(), paramContext);
    }
  }
  
  private void performIntercept(boolean paramBoolean)
  {
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = getChildAt(i);
      c localC = ((f)localView.getLayoutParams()).getBehavior();
      if (localC != null)
      {
        long l = SystemClock.uptimeMillis();
        MotionEvent localMotionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
        if (paramBoolean) {
          localC.onInterceptTouchEvent(this, localView, localMotionEvent);
        } else {
          localC.onTouchEvent(this, localView, localMotionEvent);
        }
        localMotionEvent.recycle();
      }
      i += 1;
    }
    i = 0;
    while (i < j)
    {
      ((f)getChildAt(i).getLayoutParams()).isBlockingInteractionBelow();
      i += 1;
    }
    mBehaviorTouchView = null;
    mDisallowInterceptReset = false;
  }
  
  private boolean performIntercept(MotionEvent paramMotionEvent, int paramInt)
  {
    boolean bool1 = false;
    int i = 0;
    Object localObject1 = null;
    int n = paramMotionEvent.getActionMasked();
    List localList = mTempList1;
    getTopSortedChildren(localList);
    int i1 = localList.size();
    int j = 0;
    boolean bool2;
    for (;;)
    {
      bool2 = bool1;
      if (j >= i1) {
        break;
      }
      View localView = (View)localList.get(j);
      Object localObject2 = (f)localView.getLayoutParams();
      c localC = ((f)localObject2).getBehavior();
      int k = 1;
      boolean bool3;
      if (((bool1) || (i != 0)) && (n != 0))
      {
        bool3 = bool1;
        k = i;
        localObject2 = localObject1;
        if (localC != null)
        {
          localObject2 = localObject1;
          if (localObject1 == null)
          {
            long l = SystemClock.uptimeMillis();
            localObject2 = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
          }
          if (paramInt != 0)
          {
            if (paramInt == 1) {
              localC.onTouchEvent(this, localView, (MotionEvent)localObject2);
            }
          }
          else {
            localC.onInterceptTouchEvent(this, localView, (MotionEvent)localObject2);
          }
          bool3 = bool1;
          k = i;
        }
      }
      else
      {
        bool2 = bool1;
        if (!bool1)
        {
          bool2 = bool1;
          if (localC != null)
          {
            if (paramInt != 0)
            {
              if (paramInt == 1) {
                bool1 = localC.onTouchEvent(this, localView, paramMotionEvent);
              }
            }
            else {
              bool1 = localC.onInterceptTouchEvent(this, localView, paramMotionEvent);
            }
            bool2 = bool1;
            if (bool1)
            {
              mBehaviorTouchView = localView;
              bool2 = bool1;
            }
          }
        }
        bool3 = ((f)localObject2).didBlockInteraction();
        bool1 = ((f)localObject2).isBlockingInteractionBelow(this, localView);
        if ((bool1) && (!bool3)) {
          i = k;
        } else {
          i = 0;
        }
        int m = i;
        bool3 = bool2;
        k = m;
        localObject2 = localObject1;
        if (bool1)
        {
          bool3 = bool2;
          k = m;
          localObject2 = localObject1;
          if (i == 0) {
            break;
          }
        }
      }
      j += 1;
      bool1 = bool3;
      i = k;
      localObject1 = localObject2;
    }
    localList.clear();
    return bool2;
  }
  
  private void prepareChildren()
  {
    x.clear();
    c.b();
    int i = 0;
    int k = getChildCount();
    while (i < k)
    {
      View localView1 = getChildAt(i);
      f localF = getResolvedLayoutParams(localView1);
      localF.findAnchorView(this, localView1);
      c.setEnabled(localView1);
      int j = 0;
      while (j < k)
      {
        if (j != i)
        {
          View localView2 = getChildAt(j);
          if (localF.isDirty(this, localView1, localView2))
          {
            if (!c.add(localView2)) {
              c.setEnabled(localView2);
            }
            c.add(localView2, localView1);
          }
        }
        j += 1;
      }
      i += 1;
    }
    x.addAll(c.a());
    Collections.reverse(x);
  }
  
  private void reset(View paramView, int paramInt)
  {
    f localF = (f)paramView.getLayoutParams();
    int i = flags;
    if (i != paramInt)
    {
      ViewCompat.offsetLeftAndRight(paramView, paramInt - i);
      flags = paramInt;
    }
  }
  
  private static int resolveAnchoredChildGravity(int paramInt)
  {
    if (paramInt == 0) {
      return 17;
    }
    return paramInt;
  }
  
  private static int resolveGravity(int paramInt)
  {
    int i = paramInt;
    if ((paramInt & 0x7) == 0) {
      i = paramInt | 0x800003;
    }
    paramInt = i;
    if ((i & 0x70) == 0) {
      paramInt = i | 0x30;
    }
    return paramInt;
  }
  
  private static int resolveKeylineGravity(int paramInt)
  {
    if (paramInt == 0) {
      return 8388661;
    }
    return paramInt;
  }
  
  private void show(View paramView, Rect paramRect, int paramInt)
  {
    if (!ViewCompat.get(paramView)) {
      return;
    }
    if (paramView.getWidth() > 0)
    {
      if (paramView.getHeight() <= 0) {
        return;
      }
      f localF = (f)paramView.getLayoutParams();
      c localC = localF.getBehavior();
      Rect localRect1 = get();
      Rect localRect2 = get();
      localRect2.set(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom());
      if ((localC != null) && (localC.onDependentViewChanged(this, paramView, localRect1)))
      {
        if (!localRect2.contains(localRect1))
        {
          paramView = new StringBuilder();
          paramView.append("Rect should be within the child's bounds. Rect:");
          paramView.append(localRect1.toShortString());
          paramView.append(" | Bounds:");
          paramView.append(localRect2.toShortString());
          throw new IllegalArgumentException(paramView.toString());
        }
      }
      else {
        localRect1.set(localRect2);
      }
      decode(localRect2);
      if (localRect1.isEmpty())
      {
        decode(localRect1);
        return;
      }
      int j = GravityCompat.getAbsoluteGravity(x, paramInt);
      int i = 0;
      paramInt = i;
      int k;
      int m;
      if ((j & 0x30) == 48)
      {
        k = top - topMargin - width;
        m = top;
        paramInt = i;
        if (k < m)
        {
          onMeasureChild(paramView, m - k);
          paramInt = 1;
        }
      }
      i = paramInt;
      if ((j & 0x50) == 80)
      {
        k = getHeight() - bottom - bottomMargin + width;
        m = bottom;
        i = paramInt;
        if (k < m)
        {
          onMeasureChild(paramView, k - m);
          i = 1;
        }
      }
      if (i == 0) {
        onMeasureChild(paramView, 0);
      }
      i = 0;
      paramInt = i;
      if ((j & 0x3) == 3)
      {
        k = left - leftMargin - flags;
        m = left;
        paramInt = i;
        if (k < m)
        {
          reset(paramView, m - k);
          paramInt = 1;
        }
      }
      i = paramInt;
      if ((j & 0x5) == 5)
      {
        j = getWidth() - right - rightMargin + flags;
        k = right;
        i = paramInt;
        if (j < k)
        {
          reset(paramView, j - k);
          i = 1;
        }
      }
      if (i == 0) {
        reset(paramView, 0);
      }
      decode(localRect1);
    }
  }
  
  public boolean a(View paramView1, View paramView2, int paramInt1, int paramInt2)
  {
    int j = getChildCount();
    boolean bool1 = false;
    int i = 0;
    while (i < j)
    {
      View localView = getChildAt(i);
      if (localView.getVisibility() != 8)
      {
        f localF = (f)localView.getLayoutParams();
        c localC = localF.getBehavior();
        if (localC != null)
        {
          boolean bool2 = localC.onStartNestedScroll(this, localView, paramView1, paramView2, paramInt1, paramInt2);
          localF.a(paramInt2, bool2);
          bool1 |= bool2;
        }
        else
        {
          localF.a(paramInt2, false);
        }
      }
      i += 1;
    }
    return bool1;
  }
  
  void addPreDrawListener()
  {
    if (mIsAttachedToWindow)
    {
      if (mOnPreDrawListener == null) {
        mOnPreDrawListener = new g();
      }
      getViewTreeObserver().addOnPreDrawListener(mOnPreDrawListener);
    }
    mNeedsPreDrawListener = true;
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return ((paramLayoutParams instanceof f)) && (super.checkLayoutParams(paramLayoutParams));
  }
  
  public void dispatchDependentViewsChanged(View paramView)
  {
    List localList = c.get(paramView);
    if ((localList != null) && (!localList.isEmpty()))
    {
      int i = 0;
      while (i < localList.size())
      {
        View localView = (View)localList.get(i);
        c localC = ((f)localView.getLayoutParams()).getBehavior();
        if (localC != null) {
          localC.onLayoutChild(this, localView, paramView);
        }
        i += 1;
      }
    }
  }
  
  void draw(View paramView, Rect paramRect)
  {
    ((f)paramView.getLayoutParams()).setRect(paramRect);
  }
  
  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    f localF = (f)paramView.getLayoutParams();
    c localC = mBehavior;
    if (localC != null)
    {
      float f = localC.getScrimOpacity(this, paramView);
      if (f > 0.0F)
      {
        if (mScrimPaint == null) {
          mScrimPaint = new Paint();
        }
        mScrimPaint.setColor(mBehavior.getScrimColor(this, paramView));
        mScrimPaint.setAlpha(constrain(Math.round(255.0F * f), 0, 255));
        int i = paramCanvas.save();
        if (paramView.isOpaque()) {
          paramCanvas.clipRect(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom(), Region.Op.DIFFERENCE);
        }
        paramCanvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), mScrimPaint);
        paramCanvas.restoreToCount(i);
      }
    }
    return super.drawChild(paramCanvas, paramView, paramLong);
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    int[] arrayOfInt = getDrawableState();
    boolean bool2 = false;
    Drawable localDrawable = mStatusBarBackground;
    boolean bool1 = bool2;
    if (localDrawable != null)
    {
      bool1 = bool2;
      if (localDrawable.isStateful()) {
        bool1 = false | localDrawable.setState(arrayOfInt);
      }
    }
    if (bool1) {
      invalidate();
    }
  }
  
  void ensurePreDrawListener()
  {
    int m = 0;
    int j = getChildCount();
    int i = 0;
    int k;
    for (;;)
    {
      k = m;
      if (i >= j) {
        break;
      }
      if (hasDependencies(getChildAt(i)))
      {
        k = 1;
        break;
      }
      i += 1;
    }
    if (k != mNeedsPreDrawListener)
    {
      if (k != 0)
      {
        addPreDrawListener();
        return;
      }
      removePreDrawListener();
    }
  }
  
  protected f generateDefaultLayoutParams()
  {
    return new f(-2, -2);
  }
  
  public f generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new f(getContext(), paramAttributeSet);
  }
  
  protected f generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof f)) {
      return new f((f)paramLayoutParams);
    }
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
      return new f((ViewGroup.MarginLayoutParams)paramLayoutParams);
    }
    return new f(paramLayoutParams);
  }
  
  public List get(View paramView)
  {
    paramView = c.get(paramView);
    data.clear();
    if (paramView != null) {
      data.addAll(paramView);
    }
    return data;
  }
  
  void getChildRect(View paramView, boolean paramBoolean, Rect paramRect)
  {
    if ((!paramView.isLayoutRequested()) && (paramView.getVisibility() != 8))
    {
      if (paramBoolean)
      {
        getDescendantRect(paramView, paramRect);
        return;
      }
      paramRect.set(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom());
      return;
    }
    paramRect.setEmpty();
  }
  
  public List getDependencies(View paramView)
  {
    paramView = c.a(paramView);
    data.clear();
    if (paramView != null) {
      data.addAll(paramView);
    }
    return data;
  }
  
  final List getDependencySortedChildren()
  {
    prepareChildren();
    return Collections.unmodifiableList(x);
  }
  
  void getDescendantRect(View paramView, Rect paramRect)
  {
    ViewGroupUtilsHoneycomb.getDescendantRect(this, paramView, paramRect);
  }
  
  void getDesiredAnchoredChildRect(View paramView, int paramInt, Rect paramRect1, Rect paramRect2)
  {
    f localF = (f)paramView.getLayoutParams();
    int i = paramView.getMeasuredWidth();
    int j = paramView.getMeasuredHeight();
    getDesiredAnchoredChildRect(paramView, paramInt, paramRect1, paramRect2, localF, i, j);
    getDesiredAnchoredChildRect(localF, paramRect2, i, j);
  }
  
  public final WindowInsetsCompat getLastWindowInsets()
  {
    return mLastInsets;
  }
  
  public int getNestedScrollAxes()
  {
    return mNestedScrollingParentHelper.getNestedScrollAxes();
  }
  
  f getResolvedLayoutParams(View paramView)
  {
    f localF = (f)paramView.getLayoutParams();
    if (!mBehaviorResolved)
    {
      if ((paramView instanceof b))
      {
        paramView = ((b)paramView).getBehavior();
        if (paramView == null) {
          Log.e("CoordinatorLayout", "Attached behavior class is null");
        }
        localF.a(paramView);
        mBehaviorResolved = true;
        return localF;
      }
      Object localObject = paramView.getClass();
      paramView = null;
      View localView;
      for (;;)
      {
        localView = paramView;
        if (localObject == null) {
          break;
        }
        d localD = (d)((Class)localObject).getAnnotation(d.class);
        paramView = localD;
        localView = paramView;
        if (localD != null) {
          break;
        }
        localObject = ((Class)localObject).getSuperclass();
      }
      if (localView != null) {
        try
        {
          paramView = localView.value();
          paramView = paramView.getDeclaredConstructor(new Class[0]);
          paramView = paramView.newInstance(new Object[0]);
          paramView = (c)paramView;
          localF.a(paramView);
        }
        catch (Exception paramView)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Default behavior class ");
          ((StringBuilder)localObject).append(localView.value().getName());
          ((StringBuilder)localObject).append(" could not be instantiated. Did you forget");
          ((StringBuilder)localObject).append(" a default constructor?");
          Log.e("CoordinatorLayout", ((StringBuilder)localObject).toString(), paramView);
        }
      }
      mBehaviorResolved = true;
    }
    return localF;
  }
  
  public Drawable getStatusBarBackground()
  {
    return mStatusBarBackground;
  }
  
  protected int getSuggestedMinimumHeight()
  {
    return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
  }
  
  protected int getSuggestedMinimumWidth()
  {
    return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
  }
  
  public boolean isPointInChildBounds(View paramView, int paramInt1, int paramInt2)
  {
    Rect localRect = get();
    getDescendantRect(paramView, localRect);
    try
    {
      boolean bool = localRect.contains(paramInt1, paramInt2);
      decode(localRect);
      return bool;
    }
    catch (Throwable paramView)
    {
      decode(localRect);
      throw paramView;
    }
  }
  
  void offsetChildToAnchor(View paramView, int paramInt)
  {
    f localF = (f)paramView.getLayoutParams();
    if (mAnchorView != null)
    {
      Rect localRect1 = get();
      Rect localRect2 = get();
      Rect localRect3 = get();
      getDescendantRect(mAnchorView, localRect1);
      int i = 0;
      getChildRect(paramView, false, localRect2);
      int j = paramView.getMeasuredWidth();
      int k = paramView.getMeasuredHeight();
      getDesiredAnchoredChildRect(paramView, paramInt, localRect1, localRect3, localF, j, k);
      if (left == left)
      {
        paramInt = i;
        if (top == top) {}
      }
      else
      {
        paramInt = 1;
      }
      getDesiredAnchoredChildRect(localF, localRect3, j, k);
      i = left - left;
      j = top - top;
      if (i != 0) {
        ViewCompat.offsetLeftAndRight(paramView, i);
      }
      if (j != 0) {
        ViewCompat.offsetTopAndBottom(paramView, j);
      }
      if (paramInt != 0)
      {
        c localC = localF.getBehavior();
        if (localC != null) {
          localC.onLayoutChild(this, paramView, mAnchorView);
        }
      }
      decode(localRect1);
      decode(localRect2);
      decode(localRect3);
    }
  }
  
  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    performIntercept(false);
    if (mNeedsPreDrawListener)
    {
      if (mOnPreDrawListener == null) {
        mOnPreDrawListener = new g();
      }
      getViewTreeObserver().addOnPreDrawListener(mOnPreDrawListener);
    }
    if ((mLastInsets == null) && (ViewCompat.getFitsSystemWindows(this))) {
      ViewCompat.requestApplyInsets(this);
    }
    mIsAttachedToWindow = true;
  }
  
  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    performIntercept(false);
    if ((mNeedsPreDrawListener) && (mOnPreDrawListener != null)) {
      getViewTreeObserver().removeOnPreDrawListener(mOnPreDrawListener);
    }
    View localView = mNestedScrollingTarget;
    if (localView != null) {
      onStopNestedScroll(localView);
    }
    mIsAttachedToWindow = false;
  }
  
  final void onDraw(int paramInt)
  {
    int k = ViewCompat.getLayoutDirection(this);
    int m = x.size();
    Rect localRect1 = get();
    Rect localRect2 = get();
    Rect localRect3 = get();
    int i = 0;
    while (i < m)
    {
      View localView = (View)x.get(i);
      Object localObject1 = (f)localView.getLayoutParams();
      if ((paramInt != 0) || (localView.getVisibility() != 8))
      {
        int j = 0;
        Object localObject2;
        while (j < i)
        {
          localObject2 = (View)x.get(j);
          if (mAnchorDirectChild == localObject2) {
            offsetChildToAnchor(localView, k);
          }
          j += 1;
        }
        getChildRect(localView, true, localRect2);
        if ((height != 0) && (!localRect2.isEmpty()))
        {
          j = GravityCompat.getAbsoluteGravity(height, k);
          int n = j & 0x70;
          if (n != 48)
          {
            if (n == 80) {
              bottom = Math.max(bottom, getHeight() - top);
            }
          }
          else {
            top = Math.max(top, bottom);
          }
          j &= 0x7;
          if (j != 3)
          {
            if (j == 5) {
              right = Math.max(right, getWidth() - left);
            }
          }
          else {
            left = Math.max(left, right);
          }
        }
        if ((x != 0) && (localView.getVisibility() == 0)) {
          show(localView, localRect1, k);
        }
        if (paramInt != 2)
        {
          onDraw(localView, localRect3);
          if (!localRect3.equals(localRect2)) {
            draw(localView, localRect2);
          }
        }
        else
        {
          j = i + 1;
          while (j < m)
          {
            localObject1 = (View)x.get(j);
            localObject2 = (f)((View)localObject1).getLayoutParams();
            c localC = ((f)localObject2).getBehavior();
            if ((localC != null) && (localC.onDependentViewChanged(this, (View)localObject1, localView))) {
              if ((paramInt == 0) && (((f)localObject2).a()))
              {
                ((f)localObject2).get();
              }
              else
              {
                boolean bool;
                if (paramInt != 2)
                {
                  bool = localC.onLayoutChild(this, (View)localObject1, localView);
                }
                else
                {
                  localC.onNestedPreScroll(this, (View)localObject1, localView);
                  bool = true;
                }
                if (paramInt == 1) {
                  ((f)localObject2).renderItem(bool);
                }
              }
            }
            j += 1;
          }
        }
      }
      i += 1;
    }
    decode(localRect1);
    decode(localRect2);
    decode(localRect3);
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((mDrawStatusBarBackground) && (mStatusBarBackground != null))
    {
      WindowInsetsCompat localWindowInsetsCompat = mLastInsets;
      int i;
      if (localWindowInsetsCompat != null) {
        i = localWindowInsetsCompat.getSystemWindowInsetTop();
      } else {
        i = 0;
      }
      if (i > 0)
      {
        mStatusBarBackground.setBounds(0, 0, getWidth(), i);
        mStatusBarBackground.draw(paramCanvas);
      }
    }
  }
  
  public void onDraw(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt, int paramInt3)
  {
    int i2 = getChildCount();
    int i1 = 0;
    int j = 0;
    int m = 0;
    int k = 0;
    while (k < i2)
    {
      Object localObject1 = getChildAt(k);
      int i;
      int n;
      if (((View)localObject1).getVisibility() == 8)
      {
        i = i1;
        n = j;
      }
      else
      {
        Object localObject2 = (f)((View)localObject1).getLayoutParams();
        if (!((f)localObject2).get(paramInt3))
        {
          i = i1;
          n = j;
        }
        else
        {
          localObject2 = ((f)localObject2).getBehavior();
          i = i1;
          n = j;
          if (localObject2 != null)
          {
            int[] arrayOfInt = h;
            arrayOfInt[1] = 0;
            arrayOfInt[0] = 0;
            ((c)localObject2).onNestedPreScroll(this, (View)localObject1, paramView, paramInt1, paramInt2, arrayOfInt, paramInt3);
            localObject1 = h;
            if (paramInt1 > 0) {
              i = Math.max(i1, localObject1[0]);
            } else {
              i = Math.min(i1, localObject1[0]);
            }
            localObject1 = h;
            if (paramInt2 > 0) {
              j = Math.max(j, localObject1[1]);
            } else {
              j = Math.min(j, localObject1[1]);
            }
            m = 1;
            n = j;
          }
        }
      }
      k += 1;
      i1 = i;
      j = n;
    }
    paramArrayOfInt[0] = i1;
    paramArrayOfInt[1] = j;
    if (m != 0) {
      onDraw(1);
    }
  }
  
  void onDraw(View paramView, Rect paramRect)
  {
    paramRect.set(((f)paramView.getLayoutParams()).getRect());
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionMasked();
    if (i == 0) {
      performIntercept(true);
    }
    boolean bool = performIntercept(paramMotionEvent, 0);
    if ((i == 1) || (i == 3)) {
      performIntercept(true);
    }
    return bool;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt2 = ViewCompat.getLayoutDirection(this);
    paramInt3 = x.size();
    paramInt1 = 0;
    while (paramInt1 < paramInt3)
    {
      View localView = (View)x.get(paramInt1);
      if (localView.getVisibility() != 8)
      {
        c localC = ((f)localView.getLayoutParams()).getBehavior();
        if ((localC == null) || (!localC.onLayoutChild(this, localView, paramInt2))) {
          onLayoutChild(localView, paramInt2);
        }
      }
      paramInt1 += 1;
    }
  }
  
  public void onLayoutChild(View paramView, int paramInt)
  {
    f localF = (f)paramView.getLayoutParams();
    if (!localF.checkAnchorChanged())
    {
      View localView = mAnchorView;
      if (localView != null)
      {
        layoutChildWithAnchor(paramView, localView, paramInt);
        return;
      }
      int i = keyline;
      if (i >= 0)
      {
        layoutChildWithKeyline(paramView, i, paramInt);
        return;
      }
      layoutChild(paramView, paramInt);
      return;
    }
    throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    prepareChildren();
    ensurePreDrawListener();
    int i5 = getPaddingLeft();
    int i6 = getPaddingTop();
    int i7 = getPaddingRight();
    int i8 = getPaddingBottom();
    int i9 = ViewCompat.getLayoutDirection(this);
    int k = 1;
    int j;
    if (i9 == 1) {
      j = 1;
    } else {
      j = 0;
    }
    int i10 = View.MeasureSpec.getMode(paramInt1);
    int i11 = View.MeasureSpec.getSize(paramInt1);
    int i12 = View.MeasureSpec.getMode(paramInt2);
    int i13 = View.MeasureSpec.getSize(paramInt2);
    int i2 = getSuggestedMinimumWidth();
    int i1 = getSuggestedMinimumHeight();
    if ((mLastInsets == null) || (!ViewCompat.getFitsSystemWindows(this))) {
      k = 0;
    }
    int i14 = x.size();
    int m = 0;
    int n = 0;
    while (m < i14)
    {
      View localView = (View)x.get(m);
      if (localView.getVisibility() != 8)
      {
        f localF = (f)localView.getLayoutParams();
        int i3 = 0;
        int i4 = keyline;
        int i = i3;
        if (i4 >= 0)
        {
          i = i3;
          if (i10 != 0)
          {
            i = getKeyline(i4);
            i4 = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(gravity), i9) & 0x7;
            if (((i4 == 3) && (j == 0)) || ((i4 == 5) && (j != 0))) {
              i = Math.max(0, i11 - i7 - i);
            } else if (((i4 == 5) && (j == 0)) || ((i4 == 3) && (j != 0))) {
              i = Math.max(0, i - i5);
            } else {
              i = i3;
            }
          }
        }
        if ((k != 0) && (!ViewCompat.getFitsSystemWindows(localView)))
        {
          i3 = mLastInsets.getSystemWindowInsetLeft();
          int i16 = mLastInsets.getSystemWindowInsetRight();
          i4 = mLastInsets.getSystemWindowInsetTop();
          int i15 = mLastInsets.getSystemWindowInsetBottom();
          i3 = View.MeasureSpec.makeMeasureSpec(i11 - (i3 + i16), i10);
          i4 = View.MeasureSpec.makeMeasureSpec(i13 - (i4 + i15), i12);
        }
        else
        {
          i3 = paramInt1;
          i4 = paramInt2;
        }
        c localC = localF.getBehavior();
        if (localC != null) {
          if (localC.onMeasureChild(this, localView, i3, i, i4, 0)) {
            break label435;
          }
        }
        onMeasureChild(localView, i3, i, i4, 0);
        label435:
        i2 = Math.max(i2, i5 + i7 + localView.getMeasuredWidth() + leftMargin + rightMargin);
        i1 = Math.max(i1, i6 + i8 + localView.getMeasuredHeight() + topMargin + bottomMargin);
        n = View.combineMeasuredStates(n, localView.getMeasuredState());
      }
      m += 1;
    }
    setMeasuredDimension(View.resolveSizeAndState(i2, paramInt1, 0xFF000000 & n), View.resolveSizeAndState(i1, paramInt2, n << 16));
  }
  
  public void onMeasureChild(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    measureChildWithMargins(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public boolean onNestedFling(View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    int j = getChildCount();
    boolean bool1 = false;
    int i = 0;
    while (i < j)
    {
      View localView = getChildAt(i);
      boolean bool2;
      if (localView.getVisibility() == 8)
      {
        bool2 = bool1;
      }
      else
      {
        Object localObject = (f)localView.getLayoutParams();
        if (!((f)localObject).get(0))
        {
          bool2 = bool1;
        }
        else
        {
          localObject = ((f)localObject).getBehavior();
          bool2 = bool1;
          if (localObject != null) {
            bool2 = ((c)localObject).onNestedFling(this, localView, paramView, paramFloat1, paramFloat2, paramBoolean) | bool1;
          }
        }
      }
      i += 1;
      bool1 = bool2;
    }
    if (bool1) {
      onDraw(1);
    }
    return bool1;
  }
  
  public boolean onNestedPreFling(View paramView, float paramFloat1, float paramFloat2)
  {
    boolean bool1 = false;
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = getChildAt(i);
      boolean bool2;
      if (localView.getVisibility() == 8)
      {
        bool2 = bool1;
      }
      else
      {
        Object localObject = (f)localView.getLayoutParams();
        if (!((f)localObject).get(0))
        {
          bool2 = bool1;
        }
        else
        {
          localObject = ((f)localObject).getBehavior();
          bool2 = bool1;
          if (localObject != null) {
            bool2 = bool1 | ((c)localObject).onNestedPreFling(this, localView, paramView, paramFloat1, paramFloat2);
          }
        }
      }
      i += 1;
      bool1 = bool2;
    }
    return bool1;
  }
  
  public void onNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    onDraw(paramView, paramInt1, paramInt2, paramArrayOfInt, 0);
  }
  
  public void onNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    onSaveInstanceState(paramView, paramInt1, paramInt2, paramInt3, paramInt4, 0);
  }
  
  public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt)
  {
    performIntercept(paramView1, paramView2, paramInt, 0);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof h))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (h)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    paramParcelable = behaviorStates;
    int i = 0;
    int j = getChildCount();
    while (i < j)
    {
      View localView = getChildAt(i);
      int k = localView.getId();
      c localC = getResolvedLayoutParams(localView).getBehavior();
      if ((k != -1) && (localC != null))
      {
        Parcelable localParcelable = (Parcelable)paramParcelable.get(k);
        if (localParcelable != null) {
          localC.onRestoreInstanceState(this, localView, localParcelable);
        }
      }
      i += 1;
    }
  }
  
  protected Parcelable onSaveInstanceState()
  {
    h localH = new h(super.onSaveInstanceState());
    SparseArray localSparseArray = new SparseArray();
    int i = 0;
    int j = getChildCount();
    while (i < j)
    {
      Object localObject = getChildAt(i);
      int k = ((View)localObject).getId();
      c localC = ((f)((View)localObject).getLayoutParams()).getBehavior();
      if ((k != -1) && (localC != null))
      {
        localObject = localC.onSaveInstanceState(this, (View)localObject);
        if (localObject != null) {
          localSparseArray.append(k, localObject);
        }
      }
      i += 1;
    }
    behaviorStates = localSparseArray;
    return localH;
  }
  
  public void onSaveInstanceState(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    int k = getChildCount();
    int j = 0;
    int i = 0;
    while (i < k)
    {
      View localView = getChildAt(i);
      if (localView.getVisibility() != 8)
      {
        Object localObject = (f)localView.getLayoutParams();
        if (((f)localObject).get(paramInt5))
        {
          localObject = ((f)localObject).getBehavior();
          if (localObject != null)
          {
            ((c)localObject).onNestedScroll(this, localView, paramView, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
            j = 1;
          }
        }
      }
      i += 1;
    }
    if (j != 0) {
      onDraw(1);
    }
  }
  
  public boolean onStartNestedScroll(View paramView1, View paramView2, int paramInt)
  {
    return a(paramView1, paramView2, paramInt, 0);
  }
  
  public void onStopNestedScroll(View paramView)
  {
    performIntercept(paramView, 0);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool4 = false;
    boolean bool2 = false;
    Object localObject = null;
    int i = paramMotionEvent.getActionMasked();
    boolean bool1;
    boolean bool3;
    if (mBehaviorTouchView == null)
    {
      boolean bool5 = performIntercept(paramMotionEvent, 1);
      bool2 = bool5;
      bool1 = bool4;
      bool3 = bool2;
      if (!bool5) {}
    }
    else
    {
      c localC = ((f)mBehaviorTouchView.getLayoutParams()).getBehavior();
      bool1 = bool4;
      bool3 = bool2;
      if (localC != null)
      {
        bool1 = localC.onTouchEvent(this, mBehaviorTouchView, paramMotionEvent);
        bool3 = bool2;
      }
    }
    if (mBehaviorTouchView == null)
    {
      bool2 = bool1 | super.onTouchEvent(paramMotionEvent);
      paramMotionEvent = localObject;
    }
    else
    {
      bool2 = bool1;
      paramMotionEvent = localObject;
      if (bool3)
      {
        long l = SystemClock.uptimeMillis();
        paramMotionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
        super.onTouchEvent(paramMotionEvent);
        bool2 = bool1;
      }
    }
    if (paramMotionEvent != null) {
      paramMotionEvent.recycle();
    }
    if ((i == 1) || (i == 3)) {
      performIntercept(false);
    }
    return bool2;
  }
  
  public void performIntercept(View paramView, int paramInt)
  {
    mNestedScrollingParentHelper.onNestedScrollAccepted(paramView, paramInt);
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = getChildAt(i);
      f localF = (f)localView.getLayoutParams();
      if (localF.get(paramInt))
      {
        c localC = localF.getBehavior();
        if (localC != null) {
          localC.onStopNestedScroll(this, localView, paramView, paramInt);
        }
        localF.add(paramInt);
        localF.get();
      }
      i += 1;
    }
    mNestedScrollingTarget = null;
  }
  
  public void performIntercept(View paramView1, View paramView2, int paramInt1, int paramInt2)
  {
    mNestedScrollingParentHelper.onNestedScrollAccepted(paramView1, paramView2, paramInt1, paramInt2);
    mNestedScrollingTarget = paramView2;
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = getChildAt(i);
      Object localObject = (f)localView.getLayoutParams();
      if (((f)localObject).get(paramInt2))
      {
        localObject = ((f)localObject).getBehavior();
        if (localObject != null) {
          ((c)localObject).onNestedPreScroll(this, localView, paramView1, paramView2, paramInt1, paramInt2);
        }
      }
      i += 1;
    }
  }
  
  void removePreDrawListener()
  {
    if ((mIsAttachedToWindow) && (mOnPreDrawListener != null)) {
      getViewTreeObserver().removeOnPreDrawListener(mOnPreDrawListener);
    }
    mNeedsPreDrawListener = false;
  }
  
  public boolean requestChildRectangleOnScreen(View paramView, Rect paramRect, boolean paramBoolean)
  {
    c localC = ((f)paramView.getLayoutParams()).getBehavior();
    if ((localC != null) && (localC.onLayoutChild(this, paramView, paramRect, paramBoolean))) {
      return true;
    }
    return super.requestChildRectangleOnScreen(paramView, paramRect, paramBoolean);
  }
  
  public void requestDisallowInterceptTouchEvent(boolean paramBoolean)
  {
    super.requestDisallowInterceptTouchEvent(paramBoolean);
    if ((paramBoolean) && (!mDisallowInterceptReset))
    {
      performIntercept(false);
      mDisallowInterceptReset = true;
    }
  }
  
  public void setFitsSystemWindows(boolean paramBoolean)
  {
    super.setFitsSystemWindows(paramBoolean);
    ensureSubDecor();
  }
  
  public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener paramOnHierarchyChangeListener)
  {
    mOnHierarchyChangeListener = paramOnHierarchyChangeListener;
  }
  
  public void setStatusBarBackground(Drawable paramDrawable)
  {
    Drawable localDrawable2 = mStatusBarBackground;
    if (localDrawable2 != paramDrawable)
    {
      Drawable localDrawable1 = null;
      if (localDrawable2 != null) {
        localDrawable2.setCallback(null);
      }
      if (paramDrawable != null) {
        localDrawable1 = paramDrawable.mutate();
      }
      mStatusBarBackground = localDrawable1;
      paramDrawable = mStatusBarBackground;
      if (paramDrawable != null)
      {
        if (paramDrawable.isStateful()) {
          mStatusBarBackground.setState(getDrawableState());
        }
        DrawableCompat.setLayoutDirection(mStatusBarBackground, ViewCompat.getLayoutDirection(this));
        paramDrawable = mStatusBarBackground;
        boolean bool;
        if (getVisibility() == 0) {
          bool = true;
        } else {
          bool = false;
        }
        paramDrawable.setVisible(bool, false);
        mStatusBarBackground.setCallback(this);
      }
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  public void setStatusBarBackgroundColor(int paramInt)
  {
    setStatusBarBackground(new ColorDrawable(paramInt));
  }
  
  public void setStatusBarBackgroundResource(int paramInt)
  {
    Drawable localDrawable;
    if (paramInt != 0) {
      localDrawable = ContextCompat.getDrawable(getContext(), paramInt);
    } else {
      localDrawable = null;
    }
    setStatusBarBackground(localDrawable);
  }
  
  public void setVisibility(int paramInt)
  {
    super.setVisibility(paramInt);
    boolean bool;
    if (paramInt == 0) {
      bool = true;
    } else {
      bool = false;
    }
    Drawable localDrawable = mStatusBarBackground;
    if ((localDrawable != null) && (localDrawable.isVisible() != bool)) {
      mStatusBarBackground.setVisible(bool, false);
    }
  }
  
  final WindowInsetsCompat setWindowInsets(WindowInsetsCompat paramWindowInsetsCompat)
  {
    WindowInsetsCompat localWindowInsetsCompat = paramWindowInsetsCompat;
    if (!X509LDAPCertStoreParameters.apply(mLastInsets, paramWindowInsetsCompat))
    {
      mLastInsets = paramWindowInsetsCompat;
      boolean bool2 = true;
      boolean bool1;
      if ((paramWindowInsetsCompat != null) && (paramWindowInsetsCompat.getSystemWindowInsetTop() > 0)) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      mDrawStatusBarBackground = bool1;
      if ((!mDrawStatusBarBackground) && (getBackground() == null)) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
      setWillNotDraw(bool1);
      localWindowInsetsCompat = dispatchApplyWindowInsetsToBehaviors(paramWindowInsetsCompat);
      requestLayout();
    }
    return localWindowInsetsCompat;
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    return (super.verifyDrawable(paramDrawable)) || (paramDrawable == mStatusBarBackground);
  }
  
  class a
    implements OnApplyWindowInsetsListener
  {
    a() {}
    
    public WindowInsetsCompat onApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat)
    {
      return setWindowInsets(paramWindowInsetsCompat);
    }
  }
  
  public static abstract interface b
  {
    public abstract CoordinatorLayout.c getBehavior();
  }
  
  public static abstract class c<V extends View>
  {
    public c() {}
    
    public c(Context paramContext, AttributeSet paramAttributeSet) {}
    
    public void a(CoordinatorLayout.f paramF) {}
    
    public void b(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
    
    public void b(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, int paramInt1, int paramInt2, int[] paramArrayOfInt) {}
    
    public boolean blocksInteractionBelow(CoordinatorLayout paramCoordinatorLayout, View paramView)
    {
      return getScrimOpacity(paramCoordinatorLayout, paramView) > 0.0F;
    }
    
    public int getScrimColor(CoordinatorLayout paramCoordinatorLayout, View paramView)
    {
      return -16777216;
    }
    
    public float getScrimOpacity(CoordinatorLayout paramCoordinatorLayout, View paramView)
    {
      return 0.0F;
    }
    
    public WindowInsetsCompat onApplyWindowInsets(CoordinatorLayout paramCoordinatorLayout, View paramView, WindowInsetsCompat paramWindowInsetsCompat)
    {
      return paramWindowInsetsCompat;
    }
    
    public boolean onDependentViewChanged(CoordinatorLayout paramCoordinatorLayout, View paramView, Rect paramRect)
    {
      return false;
    }
    
    public boolean onDependentViewChanged(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
    {
      return false;
    }
    
    public boolean onInterceptTouchEvent(CoordinatorLayout paramCoordinatorLayout, View paramView, MotionEvent paramMotionEvent)
    {
      return false;
    }
    
    public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt)
    {
      return false;
    }
    
    public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, View paramView, Rect paramRect, boolean paramBoolean)
    {
      return false;
    }
    
    public boolean onLayoutChild(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
    {
      return false;
    }
    
    public boolean onMeasureChild(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      return false;
    }
    
    public boolean onNestedFling(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, float paramFloat1, float paramFloat2, boolean paramBoolean)
    {
      return false;
    }
    
    public boolean onNestedPreFling(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, float paramFloat1, float paramFloat2)
    {
      return false;
    }
    
    public void onNestedPreScroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2) {}
    
    public void onNestedPreScroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, int paramInt1, int paramInt2, int[] paramArrayOfInt, int paramInt3)
    {
      if (paramInt3 == 0) {
        b(paramCoordinatorLayout, paramView1, paramView2, paramInt1, paramInt2, paramArrayOfInt);
      }
    }
    
    public void onNestedPreScroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, View paramView3, int paramInt1, int paramInt2)
    {
      if (paramInt2 == 0) {
        scroll(paramCoordinatorLayout, paramView1, paramView2, paramView3, paramInt1);
      }
    }
    
    public void onNestedScroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
    {
      if (paramInt5 == 0) {
        b(paramCoordinatorLayout, paramView1, paramView2, paramInt1, paramInt2, paramInt3, paramInt4);
      }
    }
    
    public void onRestoreInstanceState(CoordinatorLayout paramCoordinatorLayout, View paramView, Parcelable paramParcelable) {}
    
    public Parcelable onSaveInstanceState(CoordinatorLayout paramCoordinatorLayout, View paramView)
    {
      return AbsSavedState.EMPTY_STATE;
    }
    
    public void onSaveInstanceState() {}
    
    public boolean onStartNestedScroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, View paramView3, int paramInt)
    {
      return false;
    }
    
    public boolean onStartNestedScroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, View paramView3, int paramInt1, int paramInt2)
    {
      if (paramInt2 == 0) {
        return onStartNestedScroll(paramCoordinatorLayout, paramView1, paramView2, paramView3, paramInt1);
      }
      return false;
    }
    
    public void onStopNestedScroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2) {}
    
    public void onStopNestedScroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, int paramInt)
    {
      if (paramInt == 0) {
        onStopNestedScroll(paramCoordinatorLayout, paramView1, paramView2);
      }
    }
    
    public boolean onTouchEvent(CoordinatorLayout paramCoordinatorLayout, View paramView, MotionEvent paramMotionEvent)
    {
      return false;
    }
    
    public void scroll(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2, View paramView3, int paramInt) {}
  }
  
  @Deprecated
  @Retention(RetentionPolicy.RUNTIME)
  public static @interface d
  {
    Class value();
  }
  
  private class e
    implements ViewGroup.OnHierarchyChangeListener
  {
    e() {}
    
    public void onChildViewAdded(View paramView1, View paramView2)
    {
      ViewGroup.OnHierarchyChangeListener localOnHierarchyChangeListener = mOnHierarchyChangeListener;
      if (localOnHierarchyChangeListener != null) {
        localOnHierarchyChangeListener.onChildViewAdded(paramView1, paramView2);
      }
    }
    
    public void onChildViewRemoved(View paramView1, View paramView2)
    {
      onDraw(2);
      ViewGroup.OnHierarchyChangeListener localOnHierarchyChangeListener = mOnHierarchyChangeListener;
      if (localOnHierarchyChangeListener != null) {
        localOnHierarchyChangeListener.onChildViewRemoved(paramView1, paramView2);
      }
    }
  }
  
  public static class f
    extends ViewGroup.MarginLayoutParams
  {
    private boolean a;
    public int anchorGravity = 0;
    private boolean c;
    int flags;
    public int gravity = 0;
    public int height = 0;
    public int keyline = -1;
    View mAnchorDirectChild;
    int mAnchorId = -1;
    View mAnchorView;
    CoordinatorLayout.c mBehavior;
    boolean mBehaviorResolved = false;
    private boolean mDidBlockInteraction;
    final Rect mRect = new Rect();
    int width;
    public int x = 0;
    private boolean y;
    
    public f(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    f(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, IpAddress.CoordinatorLayout_Layout);
      gravity = localTypedArray.getInteger(IpAddress.CoordinatorLayout_Layout_android_layout_gravity, 0);
      mAnchorId = localTypedArray.getResourceId(IpAddress.CoordinatorLayout_Layout_layout_anchor, -1);
      anchorGravity = localTypedArray.getInteger(IpAddress.CoordinatorLayout_Layout_layout_anchorGravity, 0);
      keyline = localTypedArray.getInteger(IpAddress.CoordinatorLayout_Layout_layout_keyline, -1);
      height = localTypedArray.getInt(IpAddress.CoordinatorLayout_Layout_layout_insetEdge, 0);
      x = localTypedArray.getInt(IpAddress.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
      mBehaviorResolved = localTypedArray.hasValue(IpAddress.CoordinatorLayout_Layout_layout_behavior);
      if (mBehaviorResolved) {
        mBehavior = CoordinatorLayout.parseBehavior(paramContext, paramAttributeSet, localTypedArray.getString(IpAddress.CoordinatorLayout_Layout_layout_behavior));
      }
      localTypedArray.recycle();
      paramContext = mBehavior;
      if (paramContext != null) {
        paramContext.a(this);
      }
    }
    
    public f(f paramF)
    {
      super();
    }
    
    public f(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public f(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
    
    private boolean onLayoutChild(View paramView, int paramInt)
    {
      int i = GravityCompat.getAbsoluteGravity(getLayoutParamsheight, paramInt);
      return (i != 0) && ((GravityCompat.getAbsoluteGravity(x, paramInt) & i) == i);
    }
    
    private void resolveAnchorView(View paramView, CoordinatorLayout paramCoordinatorLayout)
    {
      mAnchorView = paramCoordinatorLayout.findViewById(mAnchorId);
      Object localObject = mAnchorView;
      if (localObject != null)
      {
        if (localObject == paramCoordinatorLayout)
        {
          if (paramCoordinatorLayout.isInEditMode())
          {
            mAnchorDirectChild = null;
            mAnchorView = null;
            return;
          }
          throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
        }
        View localView = mAnchorView;
        for (localObject = ((View)localObject).getParent(); (localObject != paramCoordinatorLayout) && (localObject != null); localObject = ((ViewParent)localObject).getParent())
        {
          if (localObject == paramView)
          {
            if (paramCoordinatorLayout.isInEditMode())
            {
              mAnchorDirectChild = null;
              mAnchorView = null;
              return;
            }
            throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
          }
          if ((localObject instanceof View)) {
            localView = (View)localObject;
          }
        }
        mAnchorDirectChild = localView;
        return;
      }
      if (paramCoordinatorLayout.isInEditMode())
      {
        mAnchorDirectChild = null;
        mAnchorView = null;
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Could not find CoordinatorLayout descendant view with id ");
      ((StringBuilder)localObject).append(paramCoordinatorLayout.getResources().getResourceName(mAnchorId));
      ((StringBuilder)localObject).append(" to anchor view ");
      ((StringBuilder)localObject).append(paramView);
      paramView = new IllegalStateException(((StringBuilder)localObject).toString());
      throw paramView;
    }
    
    private boolean verifyAnchorView(View paramView, CoordinatorLayout paramCoordinatorLayout)
    {
      if (mAnchorView.getId() != mAnchorId) {
        return false;
      }
      View localView = mAnchorView;
      ViewParent localViewParent = mAnchorView.getParent();
      while (localViewParent != paramCoordinatorLayout) {
        if ((localViewParent != null) && (localViewParent != paramView))
        {
          if ((localViewParent instanceof View)) {
            localView = (View)localViewParent;
          }
          localViewParent = localViewParent.getParent();
        }
        else
        {
          mAnchorDirectChild = null;
          mAnchorView = null;
          return false;
        }
      }
      mAnchorDirectChild = localView;
      return true;
    }
    
    void a(int paramInt, boolean paramBoolean)
    {
      if (paramInt != 0)
      {
        if (paramInt != 1) {
          return;
        }
        a = paramBoolean;
        return;
      }
      c = paramBoolean;
    }
    
    public void a(CoordinatorLayout.c paramC)
    {
      CoordinatorLayout.c localC = mBehavior;
      if (localC != paramC)
      {
        if (localC != null) {
          localC.onSaveInstanceState();
        }
        mBehavior = paramC;
        mBehaviorResolved = true;
        if (paramC != null) {
          paramC.a(this);
        }
      }
    }
    
    boolean a()
    {
      return y;
    }
    
    void add(int paramInt)
    {
      a(paramInt, false);
    }
    
    boolean checkAnchorChanged()
    {
      return (mAnchorView == null) && (mAnchorId != -1);
    }
    
    boolean didBlockInteraction()
    {
      if (mBehavior == null) {
        mDidBlockInteraction = false;
      }
      return mDidBlockInteraction;
    }
    
    View findAnchorView(CoordinatorLayout paramCoordinatorLayout, View paramView)
    {
      if (mAnchorId == -1)
      {
        mAnchorDirectChild = null;
        mAnchorView = null;
        return null;
      }
      if ((mAnchorView == null) || (!verifyAnchorView(paramView, paramCoordinatorLayout))) {
        resolveAnchorView(paramView, paramCoordinatorLayout);
      }
      return mAnchorView;
    }
    
    void get()
    {
      y = false;
    }
    
    boolean get(int paramInt)
    {
      if (paramInt != 0)
      {
        if (paramInt != 1) {
          return false;
        }
        return a;
      }
      return c;
    }
    
    public int getAnchorId()
    {
      return mAnchorId;
    }
    
    public CoordinatorLayout.c getBehavior()
    {
      return mBehavior;
    }
    
    Rect getRect()
    {
      return mRect;
    }
    
    void isBlockingInteractionBelow()
    {
      mDidBlockInteraction = false;
    }
    
    boolean isBlockingInteractionBelow(CoordinatorLayout paramCoordinatorLayout, View paramView)
    {
      boolean bool2 = mDidBlockInteraction;
      if (bool2) {
        return true;
      }
      CoordinatorLayout.c localC = mBehavior;
      if (localC != null) {
        bool1 = localC.blocksInteractionBelow(paramCoordinatorLayout, paramView);
      } else {
        bool1 = false;
      }
      boolean bool1 = bool2 | bool1;
      mDidBlockInteraction = bool1;
      return bool1;
    }
    
    boolean isDirty(CoordinatorLayout paramCoordinatorLayout, View paramView1, View paramView2)
    {
      if ((paramView2 != mAnchorDirectChild) && (!onLayoutChild(paramView2, ViewCompat.getLayoutDirection(paramCoordinatorLayout))))
      {
        CoordinatorLayout.c localC = mBehavior;
        if ((localC == null) || (!localC.onDependentViewChanged(paramCoordinatorLayout, paramView1, paramView2))) {
          return false;
        }
      }
      return true;
    }
    
    void renderItem(boolean paramBoolean)
    {
      y = paramBoolean;
    }
    
    void setRect(Rect paramRect)
    {
      mRect.set(paramRect);
    }
  }
  
  class g
    implements ViewTreeObserver.OnPreDrawListener
  {
    g() {}
    
    public boolean onPreDraw()
    {
      onDraw(0);
      return true;
    }
  }
  
  protected static class h
    extends Artist
  {
    public static final Parcelable.Creator<h> CREATOR = new a();
    SparseArray<Parcelable> behaviorStates;
    
    public h(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      int j = paramParcel.readInt();
      int[] arrayOfInt = new int[j];
      paramParcel.readIntArray(arrayOfInt);
      paramParcel = paramParcel.readParcelableArray(paramClassLoader);
      behaviorStates = new SparseArray(j);
      int i = 0;
      while (i < j)
      {
        behaviorStates.append(arrayOfInt[i], paramParcel[i]);
        i += 1;
      }
    }
    
    public h(Parcelable paramParcelable)
    {
      super();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      Object localObject = behaviorStates;
      int i;
      if (localObject != null) {
        i = ((SparseArray)localObject).size();
      } else {
        i = 0;
      }
      paramParcel.writeInt(i);
      localObject = new int[i];
      Parcelable[] arrayOfParcelable = new Parcelable[i];
      int j = 0;
      while (j < i)
      {
        localObject[j] = behaviorStates.keyAt(j);
        arrayOfParcelable[j] = ((Parcelable)behaviorStates.valueAt(j));
        j += 1;
      }
      paramParcel.writeIntArray((int[])localObject);
      paramParcel.writeParcelableArray(arrayOfParcelable, paramInt);
    }
    
    static final class a
      implements Parcelable.ClassLoaderCreator<CoordinatorLayout.h>
    {
      a() {}
      
      public CoordinatorLayout.h createFromParcel(Parcel paramParcel)
      {
        return new CoordinatorLayout.h(paramParcel, null);
      }
      
      public CoordinatorLayout.h createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
      {
        return new CoordinatorLayout.h(paramParcel, paramClassLoader);
      }
      
      public CoordinatorLayout.h[] newArray(int paramInt)
      {
        return new CoordinatorLayout.h[paramInt];
      }
    }
  }
  
  static class i
    implements Comparator<View>
  {
    i() {}
    
    public int compare(View paramView1, View paramView2)
    {
      float f1 = ViewCompat.init(paramView1);
      float f2 = ViewCompat.init(paramView2);
      if (f1 > f2) {
        return -1;
      }
      if (f1 < f2) {
        return 1;
      }
      return 0;
    }
  }
}
