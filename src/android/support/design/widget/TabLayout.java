package android.support.design.widget;

import a.b.g.g.j;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.support.design.internal.g;
import android.support.v4.view.ByteVector;
import android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.e;
import android.support.v4.view.ViewPager.i;
import android.support.v4.view.ViewPager.j;
import android.support.v7.app.a.c;
import android.support.v7.widget.TabLayout.Tab;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityRecord;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import org.org.android.R.attr;
import org.org.android.R.dimen;
import org.org.android.R.layout;
import org.org.android.R.style;
import org.org.android.asm.Item;
import org.org.android.gcm.ResourcesCompat;
import org.org.jaxen.util.Pools.Pool;
import org.org.jaxen.util.Pools.SimplePool;
import org.org.jaxen.util.Pools.SynchronizedPool;

@ViewPager.e
public class TabLayout
  extends HorizontalScrollView
{
  private static final j<g> sTabPool = new Pools.SynchronizedPool(16);
  ViewPager a;
  private b b;
  Drawable image;
  private final ArrayList<c> items = new ArrayList();
  int lastPos;
  ColorStateList m;
  ColorStateList mBackgroundTint;
  private int mContentInsetStart;
  int mImpl;
  int mMode;
  private c mOnTabSelectedListener;
  private PagerAdapter mPagerAdapter;
  private DataSetObserver mPagerAdapterObserver;
  float mPosition;
  private final int mRequestedTabMaxWidth;
  private final int mRequestedTabMinWidth;
  private ValueAnimator mScrollAnimator;
  private final int mScrollableTabMinWidth;
  private g mSelectedTab;
  boolean mStartPlaying;
  final int mTabBackgroundResId;
  int mTabGravity;
  int mTabMaxWidth = Integer.MAX_VALUE;
  int mTabPaddingBottom;
  int mTabPaddingEnd;
  int mTabPaddingStart;
  int mTabPaddingTop;
  private final f mTabStrip;
  int mTabTextAppearance;
  ColorStateList mTabTextColors;
  float mTabTextSize;
  private final j<i> mTabViewPool = new Pools.SimplePool(12);
  private final ArrayList<g> mTabs = new ArrayList();
  boolean mView;
  private final RectF mViewPager = new RectF();
  private h q;
  private boolean r;
  PorterDuff.Mode str;
  boolean this$0;
  private c x;
  
  public TabLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public TabLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.tabStyle);
  }
  
  public TabLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setHorizontalScrollBarEnabled(false);
    mTabStrip = new f(paramContext);
    super.addView(mTabStrip, 0, new FrameLayout.LayoutParams(-2, -1));
    TypedArray localTypedArray = g.obtainStyledAttributes(paramContext, paramAttributeSet, org.org.android.R.styleable.TabLayout, paramInt, R.style.Widget_Design_TabLayout, new int[] { org.org.android.R.styleable.TabLayout_tabTextAppearance });
    mTabStrip.setSelectedIndicatorHeight(localTypedArray.getDimensionPixelSize(org.org.android.R.styleable.TabLayout_tabIndicatorHeight, -1));
    mTabStrip.setSelectedIndicatorColor(localTypedArray.getColor(org.org.android.R.styleable.TabLayout_tabIndicatorColor, 0));
    setSelectedTabIndicator(ResourcesCompat.getDrawable(paramContext, localTypedArray, org.org.android.R.styleable.TabLayout_tabIndicator));
    setSelectedTabIndicatorGravity(localTypedArray.getInt(org.org.android.R.styleable.TabLayout_tabIndicatorGravity, 0));
    setTabIndicatorFullWidth(localTypedArray.getBoolean(org.org.android.R.styleable.TabLayout_tabIndicatorFullWidth, true));
    paramInt = localTypedArray.getDimensionPixelSize(org.org.android.R.styleable.TabLayout_tabPadding, 0);
    mTabPaddingBottom = paramInt;
    mTabPaddingEnd = paramInt;
    mTabPaddingTop = paramInt;
    mTabPaddingStart = paramInt;
    mTabPaddingStart = localTypedArray.getDimensionPixelSize(org.org.android.R.styleable.TabLayout_tabPaddingStart, mTabPaddingStart);
    mTabPaddingTop = localTypedArray.getDimensionPixelSize(org.org.android.R.styleable.TabLayout_tabPaddingTop, mTabPaddingTop);
    mTabPaddingEnd = localTypedArray.getDimensionPixelSize(org.org.android.R.styleable.TabLayout_tabPaddingEnd, mTabPaddingEnd);
    mTabPaddingBottom = localTypedArray.getDimensionPixelSize(org.org.android.R.styleable.TabLayout_tabPaddingBottom, mTabPaddingBottom);
    mTabTextAppearance = localTypedArray.getResourceId(org.org.android.R.styleable.TabLayout_tabTextAppearance, R.style.TextAppearance_Design_Tab);
    paramAttributeSet = paramContext.obtainStyledAttributes(mTabTextAppearance, org.org.v4.content.R.styleable.TextAppearance);
    try
    {
      mTabTextSize = paramAttributeSet.getDimensionPixelSize(org.org.v4.content.R.styleable.TextAppearance_android_textSize, 0);
      mTabTextColors = ResourcesCompat.getColorStateList(paramContext, paramAttributeSet, org.org.v4.content.R.styleable.TextAppearance_android_textColor);
      paramAttributeSet.recycle();
      if (localTypedArray.hasValue(org.org.android.R.styleable.TabLayout_tabTextColor)) {
        mTabTextColors = ResourcesCompat.getColorStateList(paramContext, localTypedArray, org.org.android.R.styleable.TabLayout_tabTextColor);
      }
      if (localTypedArray.hasValue(org.org.android.R.styleable.TabLayout_tabSelectedTextColor))
      {
        paramInt = localTypedArray.getColor(org.org.android.R.styleable.TabLayout_tabSelectedTextColor, 0);
        mTabTextColors = createColorStateList(mTabTextColors.getDefaultColor(), paramInt);
      }
      m = ResourcesCompat.getColorStateList(paramContext, localTypedArray, org.org.android.R.styleable.TabLayout_tabIconTint);
      str = android.support.design.internal.DrawableCompat.parseTintMode(localTypedArray.getInt(org.org.android.R.styleable.TabLayout_tabIconTintMode, -1), null);
      mBackgroundTint = ResourcesCompat.getColorStateList(paramContext, localTypedArray, org.org.android.R.styleable.TabLayout_tabRippleColor);
      mImpl = localTypedArray.getInt(org.org.android.R.styleable.TabLayout_tabIndicatorAnimationDuration, 300);
      mRequestedTabMinWidth = localTypedArray.getDimensionPixelSize(org.org.android.R.styleable.TabLayout_tabMinWidth, -1);
      mRequestedTabMaxWidth = localTypedArray.getDimensionPixelSize(org.org.android.R.styleable.TabLayout_tabMaxWidth, -1);
      mTabBackgroundResId = localTypedArray.getResourceId(org.org.android.R.styleable.TabLayout_tabBackground, 0);
      mContentInsetStart = localTypedArray.getDimensionPixelSize(org.org.android.R.styleable.TabLayout_tabContentStart, 0);
      mMode = localTypedArray.getInt(org.org.android.R.styleable.TabLayout_tabMode, 1);
      mTabGravity = localTypedArray.getInt(org.org.android.R.styleable.TabLayout_tabGravity, 0);
      this$0 = localTypedArray.getBoolean(org.org.android.R.styleable.TabLayout_tabInlineLabel, false);
      mView = localTypedArray.getBoolean(org.org.android.R.styleable.TabLayout_tabUnboundedRipple, false);
      localTypedArray.recycle();
      paramContext = getResources();
      mPosition = paramContext.getDimensionPixelSize(R.dimen.design_tab_text_size_2line);
      mScrollableTabMinWidth = paramContext.getDimensionPixelSize(R.dimen.design_tab_scrollable_min_width);
      applyModeAndGravity();
      return;
    }
    catch (Throwable paramContext)
    {
      paramAttributeSet.recycle();
      throw paramContext;
    }
  }
  
  private void a(ViewPager paramViewPager, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject = a;
    if (localObject != null)
    {
      h localH = q;
      if (localH != null) {
        ((ViewPager)localObject).a(localH);
      }
      localObject = b;
      if (localObject != null) {
        a.onSaveInstanceState((ViewPager.i)localObject);
      }
    }
    localObject = x;
    if (localObject != null)
    {
      b((c)localObject);
      x = null;
    }
    if (paramViewPager != null)
    {
      a = paramViewPager;
      if (q == null) {
        q = new h(this);
      }
      q.a();
      paramViewPager.addOnPageChangeListener(q);
      x = new j(paramViewPager);
      a(x);
      localObject = paramViewPager.getAdapter();
      if (localObject != null) {
        setPagerAdapter((PagerAdapter)localObject, paramBoolean1);
      }
      if (b == null) {
        b = new b();
      }
      b.b(paramBoolean1);
      paramViewPager.setAdapter(b);
      setScrollPosition(paramViewPager.getCurrentItem(), 0.0F, true);
    }
    else
    {
      a = null;
      setPagerAdapter(null, false);
    }
    r = paramBoolean2;
  }
  
  private void addAll(g paramG)
  {
    int i = items.size() - 1;
    while (i >= 0)
    {
      ((c)items.get(i)).onPostExecute(paramG);
      i -= 1;
    }
  }
  
  private void addTabFromItemView(TabItem paramTabItem)
  {
    g localG = newTab();
    Object localObject = mText;
    if (localObject != null) {
      localG.setText((CharSequence)localObject);
    }
    localObject = mIcon;
    if (localObject != null) {
      localG.setIcon((Drawable)localObject);
    }
    int i = mCustomLayout;
    if (i != 0) {
      localG.setCustomView(i);
    }
    if (!TextUtils.isEmpty(paramTabItem.getContentDescription())) {
      localG.setContentDescription(paramTabItem.getContentDescription());
    }
    addTab(localG);
  }
  
  private void addTabView(g paramG)
  {
    i localI = mView;
    mTabStrip.addView(localI, paramG.getPosition(), createLayoutParamsForTabs());
  }
  
  private void addViewInternal(View paramView)
  {
    if ((paramView instanceof TabItem))
    {
      addTabFromItemView((TabItem)paramView);
      return;
    }
    throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
  }
  
  private void animateToTab()
  {
    if (mScrollAnimator == null)
    {
      mScrollAnimator = new ValueAnimator();
      mScrollAnimator.setInterpolator(Item.c);
      mScrollAnimator.setDuration(mImpl);
      mScrollAnimator.addUpdateListener(new a());
    }
  }
  
  private void animateToTab(int paramInt)
  {
    if (paramInt == -1) {
      return;
    }
    if ((getWindowToken() != null) && (ViewCompat.get(this)) && (!mTabStrip.childrenNeedLayout()))
    {
      int i = getScrollX();
      int j = calculateScrollXForTab(paramInt, 0.0F);
      if (i != j)
      {
        animateToTab();
        mScrollAnimator.setIntValues(new int[] { i, j });
        mScrollAnimator.start();
      }
      mTabStrip.update(paramInt, mImpl);
      return;
    }
    setScrollPosition(paramInt, 0.0F, true);
  }
  
  private void applyModeAndGravity()
  {
    int i = 0;
    if (mMode == 0) {
      i = Math.max(0, mContentInsetStart - mTabPaddingStart);
    }
    ViewCompat.setPaddingRelative(mTabStrip, i, 0, 0, 0);
    i = mMode;
    if (i != 0)
    {
      if (i == 1) {
        mTabStrip.setGravity(1);
      }
    }
    else {
      mTabStrip.setGravity(8388611);
    }
    updateTabViews(true);
  }
  
  private int calculateScrollXForTab(int paramInt, float paramFloat)
  {
    int j = mMode;
    int i = 0;
    if (j == 0)
    {
      View localView2 = mTabStrip.getChildAt(paramInt);
      View localView1;
      if (paramInt + 1 < mTabStrip.getChildCount()) {
        localView1 = mTabStrip.getChildAt(paramInt + 1);
      } else {
        localView1 = null;
      }
      if (localView2 != null) {
        paramInt = localView2.getWidth();
      } else {
        paramInt = 0;
      }
      if (localView1 != null) {
        i = localView1.getWidth();
      }
      j = localView2.getLeft() + paramInt / 2 - getWidth() / 2;
      paramInt = (int)((paramInt + i) * 0.5F * paramFloat);
      if (ViewCompat.getLayoutDirection(this) == 0) {
        return j + paramInt;
      }
      return j - paramInt;
    }
    return 0;
  }
  
  private void clear(g paramG)
  {
    int i = items.size() - 1;
    while (i >= 0)
    {
      ((c)items.get(i)).setStage(paramG);
      i -= 1;
    }
  }
  
  private void configureTab(g paramG, int paramInt)
  {
    paramG.setPosition(paramInt);
    mTabs.add(paramInt, paramG);
    int i = mTabs.size();
    paramInt += 1;
    while (paramInt < i)
    {
      ((g)mTabs.get(paramInt)).setPosition(paramInt);
      paramInt += 1;
    }
  }
  
  private static ColorStateList createColorStateList(int paramInt1, int paramInt2)
  {
    int[][] arrayOfInt = new int[2][];
    int[] arrayOfInt1 = new int[2];
    arrayOfInt[0] = View.SELECTED_STATE_SET;
    arrayOfInt1[0] = paramInt2;
    paramInt2 = 0 + 1;
    arrayOfInt[paramInt2] = View.EMPTY_STATE_SET;
    arrayOfInt1[paramInt2] = paramInt1;
    return new ColorStateList(arrayOfInt, arrayOfInt1);
  }
  
  private LinearLayout.LayoutParams createLayoutParamsForTabs()
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -1);
    updateTabViewLayoutParams(localLayoutParams);
    return localLayoutParams;
  }
  
  private i createTabView(g paramG)
  {
    Object localObject1 = mTabViewPool;
    if (localObject1 != null) {
      localObject1 = (i)((Pools.Pool)localObject1).acquire();
    } else {
      localObject1 = null;
    }
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = new i(getContext());
    }
    ((i)localObject2).setTab(paramG);
    ((View)localObject2).setFocusable(true);
    ((View)localObject2).setMinimumWidth(getTabMinWidth());
    if (TextUtils.isEmpty(g.getContentDescription(paramG)))
    {
      ((View)localObject2).setContentDescription(g.getText(paramG));
      return localObject2;
    }
    ((View)localObject2).setContentDescription(g.getContentDescription(paramG));
    return localObject2;
  }
  
  private int getDefaultHeight()
  {
    int k = 0;
    int i = 0;
    int n = mTabs.size();
    int j;
    for (;;)
    {
      j = k;
      if (i >= n) {
        break;
      }
      g localG = (g)mTabs.get(i);
      if ((localG != null) && (localG.getIcon() != null) && (!TextUtils.isEmpty(localG.getText())))
      {
        j = 1;
        break;
      }
      i += 1;
    }
    if ((j != 0) && (!this$0)) {
      return 72;
    }
    return 48;
  }
  
  private int getTabMinWidth()
  {
    int i = mRequestedTabMinWidth;
    if (i != -1) {
      return i;
    }
    if (mMode == 0) {
      return mScrollableTabMinWidth;
    }
    return 0;
  }
  
  private int getTabScrollRange()
  {
    return Math.max(0, mTabStrip.getWidth() - getWidth() - getPaddingLeft() - getPaddingRight());
  }
  
  private void removeItem(g paramG)
  {
    int i = items.size() - 1;
    while (i >= 0)
    {
      ((c)items.get(i)).removeLoan(paramG);
      i -= 1;
    }
  }
  
  private void removeTabViewAt(int paramInt)
  {
    i localI = (i)mTabStrip.getChildAt(paramInt);
    mTabStrip.removeViewAt(paramInt);
    if (localI != null)
    {
      localI.reset();
      mTabViewPool.release(localI);
    }
    requestLayout();
  }
  
  private void setSelectedTabView(int paramInt)
  {
    int j = mTabStrip.getChildCount();
    if (paramInt < j)
    {
      int i = 0;
      while (i < j)
      {
        View localView = mTabStrip.getChildAt(i);
        boolean bool2 = false;
        if (i == paramInt) {
          bool1 = true;
        } else {
          bool1 = false;
        }
        localView.setSelected(bool1);
        boolean bool1 = bool2;
        if (i == paramInt) {
          bool1 = true;
        }
        localView.setActivated(bool1);
        i += 1;
      }
    }
  }
  
  private void updateAllTabs()
  {
    int i = 0;
    int j = mTabs.size();
    while (i < j)
    {
      ((g)mTabs.get(i)).updateTab();
      i += 1;
    }
  }
  
  private void updateTabViewLayoutParams(LinearLayout.LayoutParams paramLayoutParams)
  {
    if ((mMode == 1) && (mTabGravity == 0))
    {
      width = 0;
      weight = 1.0F;
      return;
    }
    width = -2;
    weight = 0.0F;
  }
  
  public void a(c paramC)
  {
    if (!items.contains(paramC)) {
      items.add(paramC);
    }
  }
  
  public void addTab(g paramG)
  {
    addTab(paramG, mTabs.isEmpty());
  }
  
  public void addTab(g paramG, int paramInt, boolean paramBoolean)
  {
    if (mParent == this)
    {
      configureTab(paramG, paramInt);
      addTabView(paramG);
      if (paramBoolean) {
        paramG.select();
      }
    }
    else
    {
      throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
    }
  }
  
  public void addTab(g paramG, boolean paramBoolean)
  {
    addTab(paramG, mTabs.size(), paramBoolean);
  }
  
  public void addView(View paramView)
  {
    addViewInternal(paramView);
  }
  
  public void addView(View paramView, int paramInt)
  {
    addViewInternal(paramView);
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    addViewInternal(paramView);
  }
  
  public void addView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    addViewInternal(paramView);
  }
  
  public void b(c paramC)
  {
    items.remove(paramC);
  }
  
  protected g createTabView()
  {
    g localG2 = (g)sTabPool.acquire();
    g localG1 = localG2;
    if (localG2 == null) {
      localG1 = new g();
    }
    return localG1;
  }
  
  int dpToPx(int paramInt)
  {
    return Math.round(getResourcesgetDisplayMetricsdensity * paramInt);
  }
  
  public FrameLayout.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return generateDefaultLayoutParams();
  }
  
  public int getSelectedTabPosition()
  {
    g localG = mSelectedTab;
    if (localG != null) {
      return localG.getPosition();
    }
    return -1;
  }
  
  public g getTabAt(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < getTabCount())) {
      return (g)mTabs.get(paramInt);
    }
    return null;
  }
  
  public int getTabCount()
  {
    return mTabs.size();
  }
  
  public int getTabGravity()
  {
    return mTabGravity;
  }
  
  public ColorStateList getTabIconTint()
  {
    return m;
  }
  
  public int getTabIndicatorGravity()
  {
    return lastPos;
  }
  
  int getTabMaxWidth()
  {
    return mTabMaxWidth;
  }
  
  public int getTabMode()
  {
    return mMode;
  }
  
  public ColorStateList getTabRippleColor()
  {
    return mBackgroundTint;
  }
  
  public Drawable getTabSelectedIndicator()
  {
    return image;
  }
  
  public ColorStateList getTabTextColors()
  {
    return mTabTextColors;
  }
  
  public g newTab()
  {
    g localG = createTabView();
    mParent = this;
    mView = createTabView(localG);
    return localG;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (a == null)
    {
      ViewParent localViewParent = getParent();
      if ((localViewParent instanceof ViewPager)) {
        a((ViewPager)localViewParent, true, true);
      }
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (r)
    {
      setupWithViewPager(null);
      r = false;
    }
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    int i = 0;
    while (i < mTabStrip.getChildCount())
    {
      View localView = mTabStrip.getChildAt(i);
      if ((localView instanceof i)) {
        i.draw((i)localView, paramCanvas);
      }
      i += 1;
    }
    super.onDraw(paramCanvas);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = dpToPx(getDefaultHeight()) + getPaddingTop() + getPaddingBottom();
    int j = View.MeasureSpec.getMode(paramInt2);
    if (j != Integer.MIN_VALUE)
    {
      if (j == 0) {
        paramInt2 = View.MeasureSpec.makeMeasureSpec(i, 1073741824);
      }
    }
    else {
      paramInt2 = View.MeasureSpec.makeMeasureSpec(Math.min(i, View.MeasureSpec.getSize(paramInt2)), 1073741824);
    }
    j = View.MeasureSpec.getSize(paramInt1);
    if (View.MeasureSpec.getMode(paramInt1) != 0)
    {
      i = mRequestedTabMaxWidth;
      if (i <= 0) {
        i = j - dpToPx(56);
      }
      mTabMaxWidth = i;
    }
    super.onMeasure(paramInt1, paramInt2);
    if (getChildCount() == 1)
    {
      j = 0;
      paramInt1 = 0;
      View localView = getChildAt(0);
      i = 0;
      int k = mMode;
      if (k != 0)
      {
        if (k != 1) {
          paramInt1 = i;
        } else if (localView.getMeasuredWidth() != getMeasuredWidth()) {
          paramInt1 = 1;
        }
      }
      else
      {
        paramInt1 = j;
        if (localView.getMeasuredWidth() < getMeasuredWidth()) {
          paramInt1 = 1;
        }
      }
      if (paramInt1 != 0)
      {
        paramInt1 = ViewGroup.getChildMeasureSpec(paramInt2, getPaddingTop() + getPaddingBottom(), getLayoutParamsheight);
        localView.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), paramInt1);
      }
    }
  }
  
  void populateFromPagerAdapter()
  {
    removeAllTabs();
    Object localObject = mPagerAdapter;
    if (localObject != null)
    {
      int j = ((PagerAdapter)localObject).getCount();
      int i = 0;
      while (i < j)
      {
        localObject = newTab();
        ((g)localObject).setText(mPagerAdapter.getPageTitle(i));
        addTab((g)localObject, false);
        i += 1;
      }
      localObject = a;
      if ((localObject != null) && (j > 0))
      {
        i = ((ViewPager)localObject).getCurrentItem();
        if ((i != getSelectedTabPosition()) && (i < getTabCount())) {
          selectTab(getTabAt(i));
        }
      }
    }
  }
  
  public void removeAllTabs()
  {
    int i = mTabStrip.getChildCount() - 1;
    while (i >= 0)
    {
      removeTabViewAt(i);
      i -= 1;
    }
    Iterator localIterator = mTabs.iterator();
    while (localIterator.hasNext())
    {
      g localG = (g)localIterator.next();
      localIterator.remove();
      localG.removeAllTabs();
      removeTabViewAt(localG);
    }
    mSelectedTab = null;
  }
  
  protected boolean removeTabViewAt(g paramG)
  {
    return sTabPool.release(paramG);
  }
  
  void selectTab(g paramG)
  {
    selectTab(paramG, true);
  }
  
  void selectTab(g paramG, boolean paramBoolean)
  {
    g localG = mSelectedTab;
    if (localG == paramG)
    {
      if (localG != null)
      {
        clear(paramG);
        animateToTab(paramG.getPosition());
      }
    }
    else
    {
      int i;
      if (paramG != null) {
        i = paramG.getPosition();
      } else {
        i = -1;
      }
      if (paramBoolean)
      {
        if (((localG == null) || (localG.getPosition() == -1)) && (i != -1)) {
          setScrollPosition(i, 0.0F, true);
        } else {
          animateToTab(i);
        }
        if (i != -1) {
          setSelectedTabView(i);
        }
      }
      mSelectedTab = paramG;
      if (localG != null) {
        removeItem(localG);
      }
      if (paramG != null) {
        addAll(paramG);
      }
    }
  }
  
  public void selectTab(ViewPager paramViewPager, boolean paramBoolean)
  {
    a(paramViewPager, paramBoolean, false);
  }
  
  public void setInlineLabel(boolean paramBoolean)
  {
    if (this$0 != paramBoolean)
    {
      this$0 = paramBoolean;
      int i = 0;
      while (i < mTabStrip.getChildCount())
      {
        View localView = mTabStrip.getChildAt(i);
        if ((localView instanceof i)) {
          ((i)localView).onCreate();
        }
        i += 1;
      }
      applyModeAndGravity();
    }
  }
  
  public void setInlineLabelResource(int paramInt)
  {
    setInlineLabel(getResources().getBoolean(paramInt));
  }
  
  public void setOnTabSelectedListener(c paramC)
  {
    c localC = mOnTabSelectedListener;
    if (localC != null) {
      b(localC);
    }
    mOnTabSelectedListener = paramC;
    if (paramC != null) {
      a(paramC);
    }
  }
  
  void setPagerAdapter(PagerAdapter paramPagerAdapter, boolean paramBoolean)
  {
    PagerAdapter localPagerAdapter = mPagerAdapter;
    if (localPagerAdapter != null)
    {
      DataSetObserver localDataSetObserver = mPagerAdapterObserver;
      if (localDataSetObserver != null) {
        localPagerAdapter.unregisterDataSetObserver(localDataSetObserver);
      }
    }
    mPagerAdapter = paramPagerAdapter;
    if ((paramBoolean) && (paramPagerAdapter != null))
    {
      if (mPagerAdapterObserver == null) {
        mPagerAdapterObserver = new e();
      }
      paramPagerAdapter.registerDataSetObserver(mPagerAdapterObserver);
    }
    populateFromPagerAdapter();
  }
  
  void setScrollAnimatorListener(Animator.AnimatorListener paramAnimatorListener)
  {
    animateToTab();
    mScrollAnimator.addListener(paramAnimatorListener);
  }
  
  public void setScrollPosition(int paramInt, float paramFloat, boolean paramBoolean)
  {
    setScrollPosition(paramInt, paramFloat, paramBoolean, true);
  }
  
  void setScrollPosition(int paramInt, float paramFloat, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i = Math.round(paramInt + paramFloat);
    if (i >= 0)
    {
      if (i >= mTabStrip.getChildCount()) {
        return;
      }
      if (paramBoolean2) {
        mTabStrip.update(paramInt, paramFloat);
      }
      ValueAnimator localValueAnimator = mScrollAnimator;
      if ((localValueAnimator != null) && (localValueAnimator.isRunning())) {
        mScrollAnimator.cancel();
      }
      scrollTo(calculateScrollXForTab(paramInt, paramFloat), 0);
      if (paramBoolean1) {
        setSelectedTabView(i);
      }
    }
  }
  
  public void setSelectedTabIndicator(int paramInt)
  {
    if (paramInt != 0)
    {
      setSelectedTabIndicator(org.org.v4.gui.helpers.Resources.getDrawable(getContext(), paramInt));
      return;
    }
    setSelectedTabIndicator(null);
  }
  
  public void setSelectedTabIndicator(Drawable paramDrawable)
  {
    if (image != paramDrawable)
    {
      image = paramDrawable;
      ViewCompat.postInvalidateOnAnimation(mTabStrip);
    }
  }
  
  public void setSelectedTabIndicatorColor(int paramInt)
  {
    mTabStrip.setSelectedIndicatorColor(paramInt);
  }
  
  public void setSelectedTabIndicatorGravity(int paramInt)
  {
    if (lastPos != paramInt)
    {
      lastPos = paramInt;
      ViewCompat.postInvalidateOnAnimation(mTabStrip);
    }
  }
  
  public void setSelectedTabIndicatorHeight(int paramInt)
  {
    mTabStrip.setSelectedIndicatorHeight(paramInt);
  }
  
  public void setTabGravity(int paramInt)
  {
    if (mTabGravity != paramInt)
    {
      mTabGravity = paramInt;
      applyModeAndGravity();
    }
  }
  
  public void setTabIconTint(ColorStateList paramColorStateList)
  {
    if (m != paramColorStateList)
    {
      m = paramColorStateList;
      updateAllTabs();
    }
  }
  
  public void setTabIconTintResource(int paramInt)
  {
    setTabIconTint(org.org.v4.gui.helpers.Resources.getColorStateList(getContext(), paramInt));
  }
  
  public void setTabIndicatorFullWidth(boolean paramBoolean)
  {
    mStartPlaying = paramBoolean;
    ViewCompat.postInvalidateOnAnimation(mTabStrip);
  }
  
  public void setTabMode(int paramInt)
  {
    if (paramInt != mMode)
    {
      mMode = paramInt;
      applyModeAndGravity();
    }
  }
  
  public void setTabRippleColor(ColorStateList paramColorStateList)
  {
    if (mBackgroundTint != paramColorStateList)
    {
      mBackgroundTint = paramColorStateList;
      int i = 0;
      while (i < mTabStrip.getChildCount())
      {
        paramColorStateList = mTabStrip.getChildAt(i);
        if ((paramColorStateList instanceof i)) {
          i.setBackgroundDrawable((i)paramColorStateList, getContext());
        }
        i += 1;
      }
    }
  }
  
  public void setTabRippleColorResource(int paramInt)
  {
    setTabRippleColor(org.org.v4.gui.helpers.Resources.getColorStateList(getContext(), paramInt));
  }
  
  public void setTabTextColors(ColorStateList paramColorStateList)
  {
    if (mTabTextColors != paramColorStateList)
    {
      mTabTextColors = paramColorStateList;
      updateAllTabs();
    }
  }
  
  public void setTabsFromPagerAdapter(PagerAdapter paramPagerAdapter)
  {
    setPagerAdapter(paramPagerAdapter, false);
  }
  
  public void setUnboundedRipple(boolean paramBoolean)
  {
    if (mView != paramBoolean)
    {
      mView = paramBoolean;
      int i = 0;
      while (i < mTabStrip.getChildCount())
      {
        View localView = mTabStrip.getChildAt(i);
        if ((localView instanceof i)) {
          i.setBackgroundDrawable((i)localView, getContext());
        }
        i += 1;
      }
    }
  }
  
  public void setUnboundedRippleResource(int paramInt)
  {
    setUnboundedRipple(getResources().getBoolean(paramInt));
  }
  
  public void setupWithViewPager(ViewPager paramViewPager)
  {
    selectTab(paramViewPager, true);
  }
  
  public boolean shouldDelayChildPressedState()
  {
    return getTabScrollRange() > 0;
  }
  
  void updateTabViews(boolean paramBoolean)
  {
    int i = 0;
    while (i < mTabStrip.getChildCount())
    {
      View localView = mTabStrip.getChildAt(i);
      localView.setMinimumWidth(getTabMinWidth());
      updateTabViewLayoutParams((LinearLayout.LayoutParams)localView.getLayoutParams());
      if (paramBoolean) {
        localView.requestLayout();
      }
      i += 1;
    }
  }
  
  class a
    implements ValueAnimator.AnimatorUpdateListener
  {
    a() {}
    
    public void onAnimationUpdate(ValueAnimator paramValueAnimator)
    {
      scrollTo(((Integer)paramValueAnimator.getAnimatedValue()).intValue(), 0);
    }
  }
  
  private class b
    implements ViewPager.i
  {
    private boolean d;
    
    b() {}
    
    public void add(ViewPager paramViewPager, PagerAdapter paramPagerAdapter1, PagerAdapter paramPagerAdapter2)
    {
      paramPagerAdapter1 = TabLayout.this;
      if (a == paramViewPager) {
        paramPagerAdapter1.setPagerAdapter(paramPagerAdapter2, d);
      }
    }
    
    void b(boolean paramBoolean)
    {
      d = paramBoolean;
    }
  }
  
  public static abstract interface c<T extends TabLayout.g>
  {
    public abstract void onPostExecute(TabLayout.g paramG);
    
    public abstract void removeLoan(TabLayout.g paramG);
    
    public abstract void setStage(TabLayout.g paramG);
  }
  
  public static abstract interface d
    extends TabLayout.c<TabLayout.g>
  {}
  
  private class e
    extends DataSetObserver
  {
    e() {}
    
    public void onChanged()
    {
      populateFromPagerAdapter();
    }
    
    public void onInvalidated()
    {
      populateFromPagerAdapter();
    }
  }
  
  private class f
    extends LinearLayout
  {
    private final GradientDrawable alpha;
    private ValueAnimator data;
    private int disabled;
    float g;
    private int h = -1;
    private final Paint linePaint;
    int p = -1;
    private int top = -1;
    private int y = -1;
    
    f(Context paramContext)
    {
      super();
      setWillNotDraw(false);
      linePaint = new Paint();
      alpha = new GradientDrawable();
    }
    
    private void update()
    {
      View localView = getChildAt(p);
      int k;
      int m;
      if ((localView != null) && (localView.getWidth() > 0))
      {
        k = localView.getLeft();
        m = localView.getRight();
        TabLayout localTabLayout = TabLayout.this;
        int j = k;
        int i = m;
        if (!mStartPlaying)
        {
          j = k;
          i = m;
          if ((localView instanceof TabLayout.i))
          {
            update((TabLayout.i)localView, TabLayout.access$getMTabTextAppearance(localTabLayout));
            j = (int)access$getMTabTextAppearanceleft;
            i = (int)access$getMTabTextAppearanceright;
          }
        }
        k = j;
        m = i;
        if (g > 0.0F)
        {
          k = j;
          m = i;
          if (p < getChildCount() - 1)
          {
            localView = getChildAt(p + 1);
            int n = localView.getLeft();
            int i1 = localView.getRight();
            localTabLayout = TabLayout.this;
            m = n;
            k = i1;
            if (!mStartPlaying)
            {
              m = n;
              k = i1;
              if ((localView instanceof TabLayout.i))
              {
                update((TabLayout.i)localView, TabLayout.access$getMTabTextAppearance(localTabLayout));
                m = (int)access$getMTabTextAppearanceleft;
                k = (int)access$getMTabTextAppearanceright;
              }
            }
            float f = g;
            j = (int)(m * f + (1.0F - f) * j);
            m = (int)(k * f + (1.0F - f) * i);
            k = j;
          }
        }
      }
      else
      {
        k = -1;
        m = -1;
      }
      add(k, m);
    }
    
    private void update(TabLayout.i paramI, RectF paramRectF)
    {
      int j = TabLayout.i.calculate(paramI);
      int i = j;
      if (j < dpToPx(24)) {
        i = dpToPx(24);
      }
      j = (paramI.getLeft() + paramI.getRight()) / 2;
      int k = i / 2;
      i /= 2;
      paramRectF.set(j - k, 0.0F, i + j, 0.0F);
    }
    
    void add(int paramInt1, int paramInt2)
    {
      if ((paramInt1 != y) || (paramInt2 != top))
      {
        y = paramInt1;
        top = paramInt2;
        ViewCompat.postInvalidateOnAnimation(this);
      }
    }
    
    boolean childrenNeedLayout()
    {
      int i = 0;
      int j = getChildCount();
      while (i < j)
      {
        if (getChildAt(i).getWidth() <= 0) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    
    public void draw(Canvas paramCanvas)
    {
      int i = 0;
      Object localObject = image;
      if (localObject != null) {
        i = ((Drawable)localObject).getIntrinsicHeight();
      }
      if (disabled >= 0) {
        i = disabled;
      }
      int k = 0;
      int j = 0;
      int m = lastPos;
      if (m != 0)
      {
        if (m != 1)
        {
          if (m != 2)
          {
            if (m != 3)
            {
              i = k;
            }
            else
            {
              i = 0;
              j = getHeight();
            }
          }
          else
          {
            k = 0;
            j = i;
            i = k;
          }
        }
        else
        {
          j = (getHeight() - i) / 2;
          k = (getHeight() + i) / 2;
          i = j;
          j = k;
        }
      }
      else
      {
        i = getHeight() - i;
        j = getHeight();
      }
      k = y;
      if ((k >= 0) && (top > k))
      {
        localObject = image;
        if (localObject == null) {
          localObject = alpha;
        }
        localObject = android.support.v4.graphics.drawable.DrawableCompat.wrap((Drawable)localObject);
        ((Drawable)localObject).setBounds(y, i, top, j);
        Paint localPaint = linePaint;
        if (localPaint != null) {
          if (Build.VERSION.SDK_INT == 21) {
            ((Drawable)localObject).setColorFilter(localPaint.getColor(), PorterDuff.Mode.SRC_IN);
          } else {
            android.support.v4.graphics.drawable.DrawableCompat.setTint((Drawable)localObject, localPaint.getColor());
          }
        }
        ((Drawable)localObject).draw(paramCanvas);
      }
      super.draw(paramCanvas);
    }
    
    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      ValueAnimator localValueAnimator = data;
      if ((localValueAnimator != null) && (localValueAnimator.isRunning()))
      {
        data.cancel();
        long l = data.getDuration();
        update(p, Math.round((1.0F - data.getAnimatedFraction()) * (float)l));
        return;
      }
      update();
    }
    
    protected void onMeasure(int paramInt1, int paramInt2)
    {
      super.onMeasure(paramInt1, paramInt2);
      if (View.MeasureSpec.getMode(paramInt1) != 1073741824) {
        return;
      }
      Object localObject = TabLayout.this;
      if ((mMode == 1) && (mTabGravity == 1))
      {
        int m = getChildCount();
        int j = 0;
        int i = 0;
        while (i < m)
        {
          localObject = getChildAt(i);
          k = j;
          if (((View)localObject).getVisibility() == 0) {
            k = Math.max(j, ((View)localObject).getMeasuredWidth());
          }
          i += 1;
          j = k;
        }
        if (j <= 0) {
          return;
        }
        int k = dpToPx(16);
        i = 0;
        if (j * m <= getMeasuredWidth() - k * 2)
        {
          k = 0;
          while (k < m)
          {
            localObject = (LinearLayout.LayoutParams)getChildAt(k).getLayoutParams();
            if ((width != j) || (weight != 0.0F))
            {
              width = j;
              weight = 0.0F;
              i = 1;
            }
            k += 1;
          }
        }
        else
        {
          localObject = TabLayout.this;
          mTabGravity = 0;
          ((TabLayout)localObject).updateTabViews(false);
          i = 1;
        }
        if (i != 0) {
          super.onMeasure(paramInt1, paramInt2);
        }
      }
    }
    
    public void onRtlPropertiesChanged(int paramInt)
    {
      super.onRtlPropertiesChanged(paramInt);
      if ((Build.VERSION.SDK_INT < 23) && (h != paramInt))
      {
        requestLayout();
        h = paramInt;
      }
    }
    
    void setSelectedIndicatorColor(int paramInt)
    {
      if (linePaint.getColor() != paramInt)
      {
        linePaint.setColor(paramInt);
        ViewCompat.postInvalidateOnAnimation(this);
      }
    }
    
    void setSelectedIndicatorHeight(int paramInt)
    {
      if (disabled != paramInt)
      {
        disabled = paramInt;
        ViewCompat.postInvalidateOnAnimation(this);
      }
    }
    
    void update(int paramInt, float paramFloat)
    {
      ValueAnimator localValueAnimator = data;
      if ((localValueAnimator != null) && (localValueAnimator.isRunning())) {
        data.cancel();
      }
      p = paramInt;
      g = paramFloat;
      update();
    }
    
    void update(final int paramInt1, int paramInt2)
    {
      Object localObject = data;
      if ((localObject != null) && (((ValueAnimator)localObject).isRunning())) {
        data.cancel();
      }
      localObject = getChildAt(paramInt1);
      if (localObject == null)
      {
        update();
        return;
      }
      final int i = ((View)localObject).getLeft();
      final int j = ((View)localObject).getRight();
      TabLayout localTabLayout = TabLayout.this;
      if ((!mStartPlaying) && ((localObject instanceof TabLayout.i)))
      {
        update((TabLayout.i)localObject, TabLayout.access$getMTabTextAppearance(localTabLayout));
        i = (int)access$getMTabTextAppearanceleft;
        j = (int)access$getMTabTextAppearanceright;
      }
      final int k = y;
      final int m = top;
      if ((k == i) && (m == j)) {
        return;
      }
      localObject = new ValueAnimator();
      data = ((ValueAnimator)localObject);
      ((ValueAnimator)localObject).setInterpolator(Item.c);
      ((ValueAnimator)localObject).setDuration(paramInt2);
      ((ValueAnimator)localObject).setFloatValues(new float[] { 0.0F, 1.0F });
      ((ValueAnimator)localObject).addUpdateListener(new a(k, i, m, j));
      ((Animator)localObject).addListener(new b(paramInt1));
      ((ValueAnimator)localObject).start();
    }
    
    class a
      implements ValueAnimator.AnimatorUpdateListener
    {
      a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
      
      public void onAnimationUpdate(ValueAnimator paramValueAnimator)
      {
        float f = paramValueAnimator.getAnimatedFraction();
        add(Item.append(k, i, f), Item.append(m, j, f));
      }
    }
    
    class b
      extends AnimatorListenerAdapter
    {
      b(int paramInt) {}
      
      public void onAnimationEnd(Animator paramAnimator)
      {
        paramAnimator = TabLayout.f.this;
        p = paramInt1;
        g = 0.0F;
      }
    }
  }
  
  public static class g
  {
    private CharSequence mContentDesc;
    private View mCustomView;
    private Drawable mIcon;
    public TabLayout mParent;
    private int mPosition = -1;
    private CharSequence mText;
    public TabLayout.i mView;
    
    public g() {}
    
    public View getCustomView()
    {
      return mCustomView;
    }
    
    public Drawable getIcon()
    {
      return mIcon;
    }
    
    public int getPosition()
    {
      return mPosition;
    }
    
    public CharSequence getText()
    {
      return mText;
    }
    
    public boolean isSelected()
    {
      TabLayout localTabLayout = mParent;
      if (localTabLayout != null) {
        return localTabLayout.getSelectedTabPosition() == mPosition;
      }
      throw new IllegalArgumentException("Tab not attached to a TabLayout");
    }
    
    void removeAllTabs()
    {
      mParent = null;
      mView = null;
      mIcon = null;
      mText = null;
      mContentDesc = null;
      mPosition = -1;
      mCustomView = null;
    }
    
    public void select()
    {
      TabLayout localTabLayout = mParent;
      if (localTabLayout != null)
      {
        localTabLayout.selectTab(this);
        return;
      }
      throw new IllegalArgumentException("Tab not attached to a TabLayout");
    }
    
    public g setContentDescription(CharSequence paramCharSequence)
    {
      mContentDesc = paramCharSequence;
      updateTab();
      return this;
    }
    
    public g setCustomView(int paramInt)
    {
      setCustomView(LayoutInflater.from(mView.getContext()).inflate(paramInt, mView, false));
      return this;
    }
    
    public g setCustomView(View paramView)
    {
      mCustomView = paramView;
      updateTab();
      return this;
    }
    
    public g setIcon(Drawable paramDrawable)
    {
      mIcon = paramDrawable;
      updateTab();
      return this;
    }
    
    void setPosition(int paramInt)
    {
      mPosition = paramInt;
    }
    
    public g setText(CharSequence paramCharSequence)
    {
      if ((TextUtils.isEmpty(mContentDesc)) && (!TextUtils.isEmpty(paramCharSequence))) {
        mView.setContentDescription(paramCharSequence);
      }
      mText = paramCharSequence;
      updateTab();
      return this;
    }
    
    void updateTab()
    {
      TabLayout.i localI = mView;
      if (localI != null) {
        localI.update();
      }
    }
  }
  
  public static class h
    implements ViewPager.j
  {
    private int mPreviousScrollState;
    private int mScrollState;
    private final WeakReference<TabLayout> mTabLayoutRef;
    
    public h(TabLayout paramTabLayout)
    {
      mTabLayoutRef = new WeakReference(paramTabLayout);
    }
    
    void a()
    {
      mScrollState = 0;
      mPreviousScrollState = 0;
    }
    
    public void onPageScrollStateChanged(int paramInt)
    {
      mPreviousScrollState = mScrollState;
      mScrollState = paramInt;
    }
    
    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
      TabLayout localTabLayout = (TabLayout)mTabLayoutRef.get();
      if (localTabLayout != null)
      {
        paramInt2 = mScrollState;
        boolean bool2 = false;
        boolean bool1;
        if ((paramInt2 == 2) && (mPreviousScrollState != 1)) {
          bool1 = false;
        } else {
          bool1 = true;
        }
        if ((mScrollState != 2) || (mPreviousScrollState != 0)) {
          bool2 = true;
        }
        localTabLayout.setScrollPosition(paramInt1, paramFloat, bool1, bool2);
      }
    }
    
    public void onPageSelected(int paramInt)
    {
      TabLayout localTabLayout = (TabLayout)mTabLayoutRef.get();
      if ((localTabLayout != null) && (localTabLayout.getSelectedTabPosition() != paramInt) && (paramInt < localTabLayout.getTabCount()))
      {
        int i = mScrollState;
        boolean bool;
        if ((i != 0) && ((i != 2) || (mPreviousScrollState != 0))) {
          bool = false;
        } else {
          bool = true;
        }
        localTabLayout.selectTab(localTabLayout.getTabAt(paramInt), bool);
      }
    }
  }
  
  class i
    extends LinearLayout
  {
    private ImageView mCustomIconView;
    private android.widget.TextView mCustomTextView;
    private View mCustomView;
    private int mDefaultMaxLines = 2;
    private ImageView mIconView;
    private Drawable mShadowDrawable;
    private TabLayout.g mTab;
    private android.widget.TextView mTextView;
    
    public i(Context paramContext)
    {
      super();
      setBackgroundDrawable(paramContext);
      ViewCompat.setPaddingRelative(this, mTabPaddingStart, mTabPaddingTop, mTabPaddingEnd, mTabPaddingBottom);
      setGravity(17);
      setOrientation(this$0 ^ true);
      setClickable(true);
      ViewCompat.a(this, ByteVector.a(getContext(), 1002));
    }
    
    private float approximateLineWidth(Layout paramLayout, int paramInt, float paramFloat)
    {
      return paramLayout.getLineWidth(paramInt) * (paramFloat / paramLayout.getPaint().getTextSize());
    }
    
    private int calculate()
    {
      int m = 0;
      int n = 0;
      int k = 0;
      View[] arrayOfView = new View[3];
      Object localObject = mTextView;
      int j = 0;
      arrayOfView[0] = localObject;
      arrayOfView[1] = mIconView;
      arrayOfView[2] = mCustomView;
      int i3 = arrayOfView.length;
      while (j < i3)
      {
        localObject = arrayOfView[j];
        int i2 = m;
        int i1 = n;
        int i = k;
        if (localObject != null)
        {
          i2 = m;
          i1 = n;
          i = k;
          if (((View)localObject).getVisibility() == 0)
          {
            i1 = ((View)localObject).getLeft();
            i = i1;
            if (m != 0) {
              i = Math.min(n, i1);
            }
            n = i;
            i1 = ((View)localObject).getRight();
            i = i1;
            if (m != 0) {
              i = Math.max(k, i1);
            }
            i2 = 1;
            i1 = n;
          }
        }
        j += 1;
        m = i2;
        n = i1;
        k = i;
      }
      return k - n;
    }
    
    private void onDrawOver(Canvas paramCanvas)
    {
      Drawable localDrawable = mShadowDrawable;
      if (localDrawable != null)
      {
        localDrawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
        mShadowDrawable.draw(paramCanvas);
      }
    }
    
    private void setBackgroundDrawable(Context paramContext)
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a6 = a5\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:552)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:1)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:166)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:331)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:387)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:90)\n\t... 17 more\n");
    }
    
    private void updateTextAndIcon(android.widget.TextView paramTextView, ImageView paramImageView)
    {
      Object localObject1 = mTab;
      Object localObject3 = null;
      if ((localObject1 != null) && (((TabLayout.g)localObject1).getIcon() != null)) {
        localObject1 = android.support.v4.graphics.drawable.DrawableCompat.wrap(mTab.getIcon()).mutate();
      } else {
        localObject1 = null;
      }
      Object localObject2 = mTab;
      if (localObject2 != null) {
        localObject2 = ((TabLayout.g)localObject2).getText();
      } else {
        localObject2 = null;
      }
      if (paramImageView != null) {
        if (localObject1 != null)
        {
          paramImageView.setImageDrawable((Drawable)localObject1);
          paramImageView.setVisibility(0);
          setVisibility(0);
        }
        else
        {
          paramImageView.setVisibility(8);
          paramImageView.setImageDrawable(null);
        }
      }
      boolean bool = TextUtils.isEmpty((CharSequence)localObject2) ^ true;
      if (paramTextView != null) {
        if (bool)
        {
          paramTextView.setText((CharSequence)localObject2);
          paramTextView.setVisibility(0);
          setVisibility(0);
        }
        else
        {
          paramTextView.setVisibility(8);
          paramTextView.setText(null);
        }
      }
      if (paramImageView != null)
      {
        paramTextView = (ViewGroup.MarginLayoutParams)paramImageView.getLayoutParams();
        int j = 0;
        int i = j;
        if (bool)
        {
          i = j;
          if (paramImageView.getVisibility() == 0) {
            i = dpToPx(8);
          }
        }
        if (this$0)
        {
          if (i != MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl.getMarginEnd(paramTextView))
          {
            MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl.setMarginEnd(paramTextView, i);
            bottomMargin = 0;
            paramImageView.setLayoutParams(paramTextView);
            paramImageView.requestLayout();
          }
        }
        else if (i != bottomMargin)
        {
          bottomMargin = i;
          MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl.setMarginEnd(paramTextView, 0);
          paramImageView.setLayoutParams(paramTextView);
          paramImageView.requestLayout();
        }
      }
      paramTextView = mTab;
      if (paramTextView != null) {
        paramTextView = TabLayout.g.getContentDescription(paramTextView);
      } else {
        paramTextView = null;
      }
      if (bool) {
        paramTextView = localObject3;
      }
      TabLayout.Tab.setText(this, paramTextView);
    }
    
    protected void drawableStateChanged()
    {
      super.drawableStateChanged();
      boolean bool2 = false;
      int[] arrayOfInt = getDrawableState();
      Drawable localDrawable = mShadowDrawable;
      boolean bool1 = bool2;
      if (localDrawable != null)
      {
        bool1 = bool2;
        if (localDrawable.isStateful()) {
          bool1 = false | mShadowDrawable.setState(arrayOfInt);
        }
      }
      if (bool1)
      {
        invalidate();
        invalidate();
      }
    }
    
    final void onCreate()
    {
      setOrientation(this$0 ^ true);
      if ((mCustomTextView == null) && (mCustomIconView == null))
      {
        updateTextAndIcon(mTextView, mIconView);
        return;
      }
      updateTextAndIcon(mCustomTextView, mCustomIconView);
    }
    
    public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
    {
      super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
      paramAccessibilityEvent.setClassName(a.c.class.getName());
    }
    
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
    {
      super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
      paramAccessibilityNodeInfo.setClassName(a.c.class.getName());
    }
    
    public void onMeasure(int paramInt1, int paramInt2)
    {
      int j = View.MeasureSpec.getSize(paramInt1);
      int k = View.MeasureSpec.getMode(paramInt1);
      int m = getTabMaxWidth();
      int i = paramInt1;
      if (m > 0) {
        if (k != 0)
        {
          i = paramInt1;
          if (j <= m) {}
        }
        else
        {
          i = View.MeasureSpec.makeMeasureSpec(mTabMaxWidth, Integer.MIN_VALUE);
        }
      }
      super.onMeasure(i, paramInt2);
      if (mTextView != null)
      {
        float f2 = mTabTextSize;
        j = mDefaultMaxLines;
        Object localObject = mIconView;
        float f1;
        if ((localObject != null) && (((View)localObject).getVisibility() == 0))
        {
          paramInt1 = 1;
          f1 = f2;
        }
        else
        {
          localObject = mTextView;
          paramInt1 = j;
          f1 = f2;
          if (localObject != null)
          {
            paramInt1 = j;
            f1 = f2;
            if (((android.widget.TextView)localObject).getLineCount() > 1)
            {
              f1 = mPosition;
              paramInt1 = j;
            }
          }
        }
        f2 = mTextView.getTextSize();
        m = mTextView.getLineCount();
        j = android.support.v4.widget.TextView.getMaxLines(mTextView);
        if ((f1 != f2) || ((j >= 0) && (paramInt1 != j)))
        {
          k = 1;
          j = k;
          if (mMode == 1)
          {
            j = k;
            if (f1 > f2)
            {
              j = k;
              if (m == 1)
              {
                localObject = mTextView.getLayout();
                if (localObject != null)
                {
                  j = k;
                  if (approximateLineWidth((Layout)localObject, 0, f1) <= getMeasuredWidth() - getPaddingLeft() - getPaddingRight()) {}
                }
                else
                {
                  j = 0;
                }
              }
            }
          }
          if (j != 0)
          {
            mTextView.setTextSize(0, f1);
            mTextView.setMaxLines(paramInt1);
            super.onMeasure(i, paramInt2);
          }
        }
      }
    }
    
    public boolean performClick()
    {
      boolean bool = super.performClick();
      if (mTab != null)
      {
        if (!bool) {
          playSoundEffect(0);
        }
        mTab.select();
        return true;
      }
      return bool;
    }
    
    void reset()
    {
      setTab(null);
      setSelected(false);
    }
    
    public void setSelected(boolean paramBoolean)
    {
      int i;
      if (isSelected() != paramBoolean) {
        i = 1;
      } else {
        i = 0;
      }
      super.setSelected(paramBoolean);
      if ((i != 0) && (paramBoolean) && (Build.VERSION.SDK_INT < 16)) {
        sendAccessibilityEvent(4);
      }
      Object localObject = mTextView;
      if (localObject != null) {
        ((android.widget.TextView)localObject).setSelected(paramBoolean);
      }
      localObject = mIconView;
      if (localObject != null) {
        ((ImageView)localObject).setSelected(paramBoolean);
      }
      localObject = mCustomView;
      if (localObject != null) {
        ((View)localObject).setSelected(paramBoolean);
      }
    }
    
    void setTab(TabLayout.g paramG)
    {
      if (paramG != mTab)
      {
        mTab = paramG;
        update();
      }
    }
    
    final void update()
    {
      TabLayout.g localG = mTab;
      PorterDuff.Mode localMode = null;
      if (localG != null) {
        localObject1 = localG.getCustomView();
      } else {
        localObject1 = null;
      }
      if (localObject1 != null)
      {
        Object localObject2 = ((View)localObject1).getParent();
        if (localObject2 != this)
        {
          if (localObject2 != null) {
            ((ViewGroup)localObject2).removeView((View)localObject1);
          }
          addView((View)localObject1);
        }
        mCustomView = ((View)localObject1);
        localObject2 = mTextView;
        if (localObject2 != null) {
          ((View)localObject2).setVisibility(8);
        }
        localObject2 = mIconView;
        if (localObject2 != null)
        {
          ((ImageView)localObject2).setVisibility(8);
          mIconView.setImageDrawable(null);
        }
        mCustomTextView = ((android.widget.TextView)((View)localObject1).findViewById(16908308));
        localObject2 = mCustomTextView;
        if (localObject2 != null) {
          mDefaultMaxLines = android.support.v4.widget.TextView.getMaxLines((android.widget.TextView)localObject2);
        }
        mCustomIconView = ((ImageView)((View)localObject1).findViewById(16908294));
      }
      else
      {
        localObject1 = mCustomView;
        if (localObject1 != null)
        {
          removeView((View)localObject1);
          mCustomView = null;
        }
        mCustomTextView = null;
        mCustomIconView = null;
      }
      Object localObject1 = mCustomView;
      boolean bool2 = false;
      if (localObject1 == null)
      {
        if (mIconView == null)
        {
          localObject1 = (ImageView)LayoutInflater.from(getContext()).inflate(R.layout.design_layout_tab_icon, this, false);
          addView((View)localObject1, 0);
          mIconView = ((ImageView)localObject1);
        }
        localObject1 = localMode;
        if (localG != null)
        {
          localObject1 = localMode;
          if (localG.getIcon() != null) {
            localObject1 = android.support.v4.graphics.drawable.DrawableCompat.wrap(localG.getIcon()).mutate();
          }
        }
        if (localObject1 != null)
        {
          android.support.v4.graphics.drawable.DrawableCompat.setTintList((Drawable)localObject1, m);
          localMode = str;
          if (localMode != null) {
            android.support.v4.graphics.drawable.DrawableCompat.setTintMode((Drawable)localObject1, localMode);
          }
        }
        if (mTextView == null)
        {
          localObject1 = (android.widget.TextView)LayoutInflater.from(getContext()).inflate(R.layout.design_layout_tab_text, this, false);
          addView((View)localObject1);
          mTextView = ((android.widget.TextView)localObject1);
          mDefaultMaxLines = android.support.v4.widget.TextView.getMaxLines(mTextView);
        }
        android.support.v4.widget.TextView.setTextAppearance(mTextView, mTabTextAppearance);
        localObject1 = mTabTextColors;
        if (localObject1 != null) {
          mTextView.setTextColor((ColorStateList)localObject1);
        }
        updateTextAndIcon(mTextView, mIconView);
      }
      else if ((mCustomTextView != null) || (mCustomIconView != null))
      {
        updateTextAndIcon(mCustomTextView, mCustomIconView);
      }
      if ((localG != null) && (!TextUtils.isEmpty(TabLayout.g.getContentDescription(localG)))) {
        setContentDescription(TabLayout.g.getContentDescription(localG));
      }
      boolean bool1 = bool2;
      if (localG != null)
      {
        bool1 = bool2;
        if (localG.isSelected()) {
          bool1 = true;
        }
      }
      setSelected(bool1);
    }
  }
  
  public static class j
    implements TabLayout.d
  {
    private final ViewPager mViewPager;
    
    public j(ViewPager paramViewPager)
    {
      mViewPager = paramViewPager;
    }
    
    public void onPostExecute(TabLayout.g paramG)
    {
      mViewPager.setCurrentItem(paramG.getPosition());
    }
    
    public void removeLoan(TabLayout.g paramG) {}
    
    public void setStage(TabLayout.g paramG) {}
  }
}
