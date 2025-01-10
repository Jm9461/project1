package com.afollestad.materialdialogs.internal;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.o;
import android.support.v7.widget.RecyclerView.t;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ScrollView;
import android.widget.TextView;
import apps.afollestad.materialdialogs.GravityEnum;
import apps.afollestad.materialdialogs.MathArrays.OrderDirection;
import apps.afollestad.materialdialogs.R.attr;
import apps.afollestad.materialdialogs.R.dimen;
import apps.afollestad.materialdialogs.R.id;
import apps.afollestad.materialdialogs.R.styleable;
import apps.afollestad.materialdialogs.base.DialogUtils;

public class MDRootLayout
  extends ViewGroup
{
  private ViewTreeObserver.OnScrollChangedListener mBottomOnScrollChangedListener;
  private int mButtonBarHeight;
  private GravityEnum mButtonGravity = GravityEnum.START;
  private int mButtonHorizontalEdgeMargin;
  private int mButtonPaddingFull;
  private final MDButton[] mButtons = new MDButton[3];
  private View mContent;
  private Paint mDividerPaint;
  private int mDividerWidth;
  private boolean mDrawBottomDivider = false;
  private boolean mDrawTopDivider = false;
  private MathArrays.OrderDirection mForceStack = MathArrays.OrderDirection.INCREASING;
  private boolean mIsStacked = false;
  private int mMaxHeight;
  private boolean mNoTitleNoPadding;
  private int mNoTitlePaddingFull;
  private boolean mReducePaddingNoTitleNoButtons;
  private View mTitleBar;
  private ViewTreeObserver.OnScrollChangedListener mTopOnScrollChangedListener;
  private boolean mUseFullPadding = true;
  
  public MDRootLayout(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null, 0);
  }
  
  public MDRootLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet, 0);
  }
  
  public MDRootLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet, paramInt);
  }
  
  public MDRootLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    init(paramContext, paramAttributeSet, paramInt1);
  }
  
  private void addScrollListener(final ViewGroup paramViewGroup, final boolean paramBoolean1, final boolean paramBoolean2)
  {
    if (((!paramBoolean2) && (mBottomOnScrollChangedListener == null)) || ((paramBoolean2) && (mTopOnScrollChangedListener == null)))
    {
      if ((paramViewGroup instanceof RecyclerView))
      {
        localObject = new b(paramViewGroup, paramBoolean1, paramBoolean2);
        ((RecyclerView)paramViewGroup).addOnScrollListener((RecyclerView.t)localObject);
        ((b)localObject).a((RecyclerView)paramViewGroup, 0, 0);
        return;
      }
      Object localObject = new c(paramViewGroup, paramBoolean1, paramBoolean2);
      if (!paramBoolean2)
      {
        mBottomOnScrollChangedListener = ((ViewTreeObserver.OnScrollChangedListener)localObject);
        paramViewGroup.getViewTreeObserver().addOnScrollChangedListener(mBottomOnScrollChangedListener);
      }
      else
      {
        mTopOnScrollChangedListener = ((ViewTreeObserver.OnScrollChangedListener)localObject);
        paramViewGroup.getViewTreeObserver().addOnScrollChangedListener(mTopOnScrollChangedListener);
      }
      ((ViewTreeObserver.OnScrollChangedListener)localObject).onScrollChanged();
    }
  }
  
  private static boolean canAdapterViewScroll(AdapterView paramAdapterView)
  {
    if (paramAdapterView.getLastVisiblePosition() == -1) {
      return false;
    }
    int i;
    if (paramAdapterView.getFirstVisiblePosition() == 0) {
      i = 1;
    } else {
      i = 0;
    }
    int j;
    if (paramAdapterView.getLastVisiblePosition() == paramAdapterView.getCount() - 1) {
      j = 1;
    } else {
      j = 0;
    }
    if ((i != 0) && (j != 0) && (paramAdapterView.getChildCount() > 0))
    {
      if (paramAdapterView.getChildAt(0).getTop() < paramAdapterView.getPaddingTop()) {
        return true;
      }
      if (paramAdapterView.getChildAt(paramAdapterView.getChildCount() - 1).getBottom() > paramAdapterView.getHeight() - paramAdapterView.getPaddingBottom()) {
        return true;
      }
    }
    else
    {
      return true;
    }
    return false;
  }
  
  public static boolean canRecyclerViewScroll(RecyclerView paramRecyclerView)
  {
    return (paramRecyclerView != null) && (paramRecyclerView.getLayoutManager() != null) && (paramRecyclerView.getLayoutManager().canScrollVertically());
  }
  
  private static boolean canScrollViewScroll(ScrollView paramScrollView)
  {
    if (paramScrollView.getChildCount() == 0) {
      return false;
    }
    int i = paramScrollView.getChildAt(0).getMeasuredHeight();
    return paramScrollView.getMeasuredHeight() - paramScrollView.getPaddingTop() - paramScrollView.getPaddingBottom() < i;
  }
  
  private static boolean canWebViewScroll(WebView paramWebView)
  {
    return paramWebView.getMeasuredHeight() < paramWebView.getContentHeight() * paramWebView.getScale();
  }
  
  private static View getBottomView(ViewGroup paramViewGroup)
  {
    if ((paramViewGroup != null) && (paramViewGroup.getChildCount() != 0))
    {
      int i = paramViewGroup.getChildCount() - 1;
      while (i >= 0)
      {
        View localView = paramViewGroup.getChildAt(i);
        if ((localView.getVisibility() == 0) && (localView.getBottom() == paramViewGroup.getMeasuredHeight())) {
          return localView;
        }
        i -= 1;
      }
    }
    return null;
  }
  
  private static View getTopView(ViewGroup paramViewGroup)
  {
    if ((paramViewGroup != null) && (paramViewGroup.getChildCount() != 0))
    {
      int i = paramViewGroup.getChildCount() - 1;
      while (i >= 0)
      {
        View localView = paramViewGroup.getChildAt(i);
        if ((localView.getVisibility() == 0) && (localView.getTop() == 0)) {
          return localView;
        }
        i -= 1;
      }
    }
    return null;
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    Resources localResources = paramContext.getResources();
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.MDRootLayout, paramInt, 0);
    mReducePaddingNoTitleNoButtons = paramAttributeSet.getBoolean(R.styleable.MDRootLayout_md_reduce_padding_no_title_no_buttons, true);
    paramAttributeSet.recycle();
    mNoTitlePaddingFull = localResources.getDimensionPixelSize(R.dimen.md_notitle_vertical_padding);
    mButtonPaddingFull = localResources.getDimensionPixelSize(R.dimen.md_button_frame_vertical_padding);
    mButtonHorizontalEdgeMargin = localResources.getDimensionPixelSize(R.dimen.md_button_padding_frame_side);
    mButtonBarHeight = localResources.getDimensionPixelSize(R.dimen.md_button_height);
    mDividerPaint = new Paint();
    mDividerWidth = localResources.getDimensionPixelSize(R.dimen.md_divider_height);
    mDividerPaint.setColor(DialogUtils.resolveColor(paramContext, R.attr.md_divider_color));
    setWillNotDraw(false);
  }
  
  private void invalidateDividersForScrollingView(ViewGroup paramViewGroup, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    boolean bool = true;
    if ((paramBoolean1) && (paramViewGroup.getChildCount() > 0))
    {
      View localView = mTitleBar;
      if ((localView != null) && (localView.getVisibility() != 8) && (paramViewGroup.getScrollY() + paramViewGroup.getPaddingTop() > paramViewGroup.getChildAt(0).getTop())) {
        paramBoolean1 = true;
      } else {
        paramBoolean1 = false;
      }
      mDrawBottomDivider = paramBoolean1;
    }
    if ((paramBoolean2) && (paramViewGroup.getChildCount() > 0))
    {
      if ((paramBoolean3) && (paramViewGroup.getScrollY() + paramViewGroup.getHeight() - paramViewGroup.getPaddingBottom() < paramViewGroup.getChildAt(paramViewGroup.getChildCount() - 1).getBottom())) {
        paramBoolean1 = bool;
      } else {
        paramBoolean1 = false;
      }
      mDrawTopDivider = paramBoolean1;
    }
  }
  
  private void invalidateDividersForWebView(WebView paramWebView, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    boolean bool = true;
    if (paramBoolean1)
    {
      View localView = mTitleBar;
      if ((localView != null) && (localView.getVisibility() != 8) && (paramWebView.getScrollY() + paramWebView.getPaddingTop() > 0)) {
        paramBoolean1 = true;
      } else {
        paramBoolean1 = false;
      }
      mDrawBottomDivider = paramBoolean1;
    }
    if (paramBoolean2)
    {
      if ((paramBoolean3) && (paramWebView.getScrollY() + paramWebView.getMeasuredHeight() - paramWebView.getPaddingBottom() < paramWebView.getContentHeight() * paramWebView.getScale())) {
        paramBoolean1 = bool;
      } else {
        paramBoolean1 = false;
      }
      mDrawTopDivider = paramBoolean1;
    }
  }
  
  private void invertGravityIfNecessary()
  {
    if (Build.VERSION.SDK_INT < 17) {
      return;
    }
    if (getResources().getConfiguration().getLayoutDirection() == 1)
    {
      int i = d.$SwitchMap$com$afollestad$materialdialogs$GravityEnum[mButtonGravity.ordinal()];
      if (i != 1)
      {
        if (i != 2) {
          return;
        }
        mButtonGravity = GravityEnum.START;
        return;
      }
      mButtonGravity = GravityEnum.END;
    }
  }
  
  private static boolean isVisible(View paramView)
  {
    boolean bool;
    if ((paramView != null) && (paramView.getVisibility() != 8)) {
      bool = true;
    } else {
      bool = false;
    }
    if ((bool) && ((paramView instanceof MDButton))) {
      return ((MDButton)paramView).getText().toString().trim().length() > 0;
    }
    return bool;
  }
  
  private void setUpDividersVisibility(final View paramView, final boolean paramBoolean1, final boolean paramBoolean2)
  {
    if (paramView == null) {
      return;
    }
    if ((paramView instanceof ScrollView))
    {
      paramView = (ScrollView)paramView;
      if (canScrollViewScroll(paramView))
      {
        addScrollListener(paramView, paramBoolean1, paramBoolean2);
        return;
      }
      if (paramBoolean1) {
        mDrawBottomDivider = false;
      }
      if (paramBoolean2) {
        mDrawTopDivider = false;
      }
      return;
    }
    if ((paramView instanceof AdapterView))
    {
      paramView = (AdapterView)paramView;
      if (canAdapterViewScroll(paramView))
      {
        addScrollListener(paramView, paramBoolean1, paramBoolean2);
        return;
      }
      if (paramBoolean1) {
        mDrawBottomDivider = false;
      }
      if (paramBoolean2) {
        mDrawTopDivider = false;
      }
      return;
    }
    if ((paramView instanceof WebView))
    {
      paramView.getViewTreeObserver().addOnPreDrawListener(new a(paramView, paramBoolean1, paramBoolean2));
      return;
    }
    if ((paramView instanceof RecyclerView))
    {
      boolean bool = canRecyclerViewScroll((RecyclerView)paramView);
      if (paramBoolean1) {
        mDrawBottomDivider = bool;
      }
      if (paramBoolean2) {
        mDrawTopDivider = bool;
      }
      if (bool) {
        addScrollListener((ViewGroup)paramView, paramBoolean1, paramBoolean2);
      }
    }
    else if ((paramView instanceof ViewGroup))
    {
      View localView = getTopView((ViewGroup)paramView);
      setUpDividersVisibility(localView, paramBoolean1, paramBoolean2);
      paramView = getBottomView((ViewGroup)paramView);
      if (paramView != localView) {
        setUpDividersVisibility(paramView, false, true);
      }
    }
  }
  
  public void noTitleNoPadding()
  {
    mNoTitleNoPadding = true;
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    View localView = mContent;
    if (localView != null)
    {
      int i;
      if (mDrawBottomDivider)
      {
        i = localView.getTop();
        paramCanvas.drawRect(0.0F, i - mDividerWidth, getMeasuredWidth(), i, mDividerPaint);
      }
      if (mDrawTopDivider)
      {
        i = mContent.getBottom();
        paramCanvas.drawRect(0.0F, i, getMeasuredWidth(), mDividerWidth + i, mDividerPaint);
      }
    }
  }
  
  public void onFinishInflate()
  {
    super.onFinishInflate();
    int i = 0;
    while (i < getChildCount())
    {
      View localView = getChildAt(i);
      if (localView.getId() == R.id.md_titleFrame) {
        mTitleBar = localView;
      } else if (localView.getId() == R.id.md_buttonDefaultNeutral) {
        mButtons[0] = ((MDButton)localView);
      } else if (localView.getId() == R.id.md_buttonDefaultNegative) {
        mButtons[1] = ((MDButton)localView);
      } else if (localView.getId() == R.id.md_buttonDefaultPositive) {
        mButtons[2] = ((MDButton)localView);
      } else {
        mContent = localView;
      }
      i += 1;
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j = paramInt2;
    if (isVisible(mTitleBar))
    {
      i = mTitleBar.getMeasuredHeight();
      mTitleBar.layout(paramInt1, paramInt2, paramInt3, paramInt2 + i);
      i = paramInt2 + i;
    }
    else
    {
      i = j;
      if (!mNoTitleNoPadding)
      {
        i = j;
        if (mUseFullPadding) {
          i = paramInt2 + mNoTitlePaddingFull;
        }
      }
    }
    Object localObject;
    if (isVisible(mContent))
    {
      localObject = mContent;
      ((View)localObject).layout(paramInt1, i, paramInt3, ((View)localObject).getMeasuredHeight() + i);
    }
    paramBoolean = mIsStacked;
    paramInt2 = 0;
    if (paramBoolean)
    {
      paramInt4 -= mButtonPaddingFull;
      localObject = mButtons;
      j = localObject.length;
      while (paramInt2 < j)
      {
        View localView = localObject[paramInt2];
        i = paramInt4;
        if (isVisible(localView))
        {
          localView.layout(paramInt1, paramInt4 - localView.getMeasuredHeight(), paramInt3, paramInt4);
          i = paramInt4 - localView.getMeasuredHeight();
        }
        paramInt2 += 1;
        paramInt4 = i;
      }
    }
    int k = paramInt4;
    if (mUseFullPadding) {
      k = paramInt4 - mButtonPaddingFull;
    }
    int i1 = k - mButtonBarHeight;
    int m = mButtonHorizontalEdgeMargin;
    paramInt2 = -1;
    paramInt4 = -1;
    int i = paramInt4;
    j = m;
    if (isVisible(mButtons[2]))
    {
      if (mButtonGravity == GravityEnum.END)
      {
        int n = paramInt1 + m;
        j = mButtons[2].getMeasuredWidth() + n;
        i = paramInt4;
        paramInt4 = n;
      }
      else
      {
        j = paramInt3 - m;
        paramInt4 = j - mButtons[2].getMeasuredWidth();
        i = paramInt4;
      }
      mButtons[2].layout(paramInt4, i1, j, k);
      j = m + mButtons[2].getMeasuredWidth();
    }
    paramInt4 = paramInt2;
    if (isVisible(mButtons[1]))
    {
      localObject = mButtonGravity;
      if (localObject == GravityEnum.END)
      {
        paramInt4 = paramInt1 + j;
        j = mButtons[1].getMeasuredWidth() + paramInt4;
      }
      else if (localObject == GravityEnum.START)
      {
        j = paramInt3 - j;
        paramInt4 = j - mButtons[1].getMeasuredWidth();
      }
      else
      {
        paramInt4 = mButtonHorizontalEdgeMargin + paramInt1;
        j = mButtons[1].getMeasuredWidth() + paramInt4;
        paramInt2 = j;
      }
      mButtons[1].layout(paramInt4, i1, j, k);
      paramInt4 = paramInt2;
    }
    if (isVisible(mButtons[0]))
    {
      localObject = mButtonGravity;
      if (localObject == GravityEnum.END)
      {
        paramInt2 = paramInt3 - mButtonHorizontalEdgeMargin;
        paramInt1 = paramInt2 - mButtons[0].getMeasuredWidth();
      }
      else if (localObject == GravityEnum.START)
      {
        paramInt1 += mButtonHorizontalEdgeMargin;
        paramInt2 = mButtons[0].getMeasuredWidth() + paramInt1;
      }
      else
      {
        if ((paramInt4 == -1) && (i != -1))
        {
          paramInt4 = i - mButtons[0].getMeasuredWidth();
          paramInt2 = i;
        }
        else if ((i == -1) && (paramInt4 != -1))
        {
          paramInt2 = mButtons[0].getMeasuredWidth() + paramInt4;
        }
        else
        {
          paramInt2 = i;
          if (i == -1)
          {
            paramInt4 = (paramInt3 - paramInt1) / 2 - mButtons[0].getMeasuredWidth() / 2;
            paramInt2 = mButtons[0].getMeasuredWidth() + paramInt4;
          }
        }
        paramInt1 = paramInt4;
      }
      mButtons[0].layout(paramInt1, i1, paramInt2, k);
    }
    setUpDividersVisibility(mContent, true, true);
  }
  
  public void onMeasure(int paramInt1, int paramInt2)
  {
    int i2 = View.MeasureSpec.getSize(paramInt1);
    int i = View.MeasureSpec.getSize(paramInt2);
    int j = i;
    if (i > mMaxHeight) {
      j = mMaxHeight;
    }
    mUseFullPadding = true;
    i = 0;
    Object localObject = mForceStack;
    boolean bool;
    int n;
    int i3;
    View localView;
    int i1;
    if (localObject == MathArrays.OrderDirection.$SwitchMap$org$apache$commons$math3$util$MathArrays$OrderDirection)
    {
      bool = true;
    }
    else if (localObject == MathArrays.OrderDirection.DECREASING)
    {
      bool = false;
    }
    else
    {
      n = 0;
      localObject = mButtons;
      i3 = localObject.length;
      i = 0;
      k = 0;
      while (k < i3)
      {
        localView = localObject[k];
        i1 = n;
        m = i;
        if (localView != null)
        {
          i1 = n;
          m = i;
          if (isVisible(localView))
          {
            localView.setStacked(false, false);
            measureChild(localView, paramInt1, paramInt2);
            i1 = n + localView.getMeasuredWidth();
            m = 1;
          }
        }
        k += 1;
        n = i1;
        i = m;
      }
      if (n > i2 - getContext().getResources().getDimensionPixelSize(R.dimen.md_neutral_button_margin) * 2) {
        bool = true;
      } else {
        bool = false;
      }
    }
    int m = 0;
    mIsStacked = bool;
    int k = i;
    if (bool)
    {
      localObject = mButtons;
      i3 = localObject.length;
      k = 0;
      m = 0;
      while (m < i3)
      {
        localView = localObject[m];
        i1 = k;
        n = i;
        if (localView != null)
        {
          i1 = k;
          n = i;
          if (isVisible(localView))
          {
            localView.setStacked(true, false);
            measureChild(localView, paramInt1, paramInt2);
            i1 = k + localView.getMeasuredHeight();
            n = 1;
          }
        }
        m += 1;
        k = i1;
        i = n;
      }
      m = k;
      k = i;
    }
    paramInt2 = j;
    i = 0;
    if (k != 0)
    {
      if (mIsStacked)
      {
        paramInt2 = j - m;
        i = mButtonPaddingFull;
        paramInt1 = 0 + i * 2;
        m = 0 + i * 2;
      }
      else
      {
        paramInt2 = j - mButtonBarHeight;
        paramInt1 = 0 + mButtonPaddingFull * 2;
        m = i;
      }
    }
    else
    {
      paramInt1 = 0 + mButtonPaddingFull * 2;
      m = i;
    }
    if (isVisible(mTitleBar))
    {
      mTitleBar.measure(View.MeasureSpec.makeMeasureSpec(i2, 1073741824), 0);
      i = paramInt2 - mTitleBar.getMeasuredHeight();
      n = paramInt1;
    }
    else
    {
      n = paramInt1;
      i = paramInt2;
      if (!mNoTitleNoPadding)
      {
        n = paramInt1 + mNoTitlePaddingFull;
        i = paramInt2;
      }
    }
    paramInt1 = i;
    if (isVisible(mContent))
    {
      mContent.measure(View.MeasureSpec.makeMeasureSpec(i2, 1073741824), View.MeasureSpec.makeMeasureSpec(i - m, Integer.MIN_VALUE));
      if (mContent.getMeasuredHeight() <= i - n)
      {
        if ((mReducePaddingNoTitleNoButtons) && (!isVisible(mTitleBar)) && (k == 0))
        {
          mUseFullPadding = false;
          paramInt1 = i - (mContent.getMeasuredHeight() + m);
        }
        else
        {
          mUseFullPadding = true;
          paramInt1 = i - (mContent.getMeasuredHeight() + n);
        }
      }
      else
      {
        mUseFullPadding = false;
        paramInt1 = 0;
      }
    }
    setMeasuredDimension(i2, j - paramInt1);
  }
  
  public void setButtonGravity(GravityEnum paramGravityEnum)
  {
    mButtonGravity = paramGravityEnum;
    invertGravityIfNecessary();
  }
  
  public void setButtonStackedGravity(GravityEnum paramGravityEnum)
  {
    MDButton[] arrayOfMDButton = mButtons;
    int j = arrayOfMDButton.length;
    int i = 0;
    while (i < j)
    {
      MDButton localMDButton = arrayOfMDButton[i];
      if (localMDButton != null) {
        localMDButton.setStackedGravity(paramGravityEnum);
      }
      i += 1;
    }
  }
  
  public void setDividerColor(int paramInt)
  {
    mDividerPaint.setColor(paramInt);
    invalidate();
  }
  
  public void setMaxHeight(int paramInt)
  {
    mMaxHeight = paramInt;
  }
  
  public void setStackingBehavior(MathArrays.OrderDirection paramOrderDirection)
  {
    mForceStack = paramOrderDirection;
    invalidate();
  }
  
  class a
    implements ViewTreeObserver.OnPreDrawListener
  {
    a(View paramView, boolean paramBoolean1, boolean paramBoolean2) {}
    
    public boolean onPreDraw()
    {
      if (paramView.getMeasuredHeight() != 0)
      {
        if (!MDRootLayout.access$getCanWebViewScroll((WebView)paramView))
        {
          if (paramBoolean1) {
            MDRootLayout.access$setMDrawBottomDivider(MDRootLayout.this, false);
          }
          if (paramBoolean2) {
            MDRootLayout.access$setMDrawTopDivider(MDRootLayout.this, false);
          }
        }
        else
        {
          MDRootLayout.access$getAddScrollListener(MDRootLayout.this, (ViewGroup)paramView, paramBoolean1, paramBoolean2);
        }
        paramView.getViewTreeObserver().removeOnPreDrawListener(this);
      }
      return true;
    }
  }
  
  class b
    extends RecyclerView.t
  {
    b(ViewGroup paramViewGroup, boolean paramBoolean1, boolean paramBoolean2) {}
    
    public void a(RecyclerView paramRecyclerView, int paramInt1, int paramInt2)
    {
      super.a(paramRecyclerView, paramInt1, paramInt2);
      boolean bool2 = false;
      paramRecyclerView = MDRootLayout.access$getMButtons(MDRootLayout.this);
      paramInt2 = paramRecyclerView.length;
      paramInt1 = 0;
      boolean bool1;
      for (;;)
      {
        bool1 = bool2;
        if (paramInt1 >= paramInt2) {
          break;
        }
        Object localObject = paramRecyclerView[paramInt1];
        if ((localObject != null) && (localObject.getVisibility() != 8))
        {
          bool1 = true;
          break;
        }
        paramInt1 += 1;
      }
      MDRootLayout.access$getInvalidateDividersForScrollingView(MDRootLayout.this, paramViewGroup, paramBoolean1, paramBoolean2, bool1);
      invalidate();
    }
  }
  
  class c
    implements ViewTreeObserver.OnScrollChangedListener
  {
    c(ViewGroup paramViewGroup, boolean paramBoolean1, boolean paramBoolean2) {}
    
    public void onScrollChanged()
    {
      boolean bool2 = false;
      Object localObject1 = MDRootLayout.access$getMButtons(MDRootLayout.this);
      int j = localObject1.length;
      int i = 0;
      boolean bool1;
      for (;;)
      {
        bool1 = bool2;
        if (i >= j) {
          break;
        }
        Object localObject2 = localObject1[i];
        if ((localObject2 != null) && (localObject2.getVisibility() != 8))
        {
          bool1 = true;
          break;
        }
        i += 1;
      }
      localObject1 = paramViewGroup;
      if ((localObject1 instanceof WebView)) {
        MDRootLayout.access$getInvalidateDividersForWebView(MDRootLayout.this, (WebView)localObject1, paramBoolean1, paramBoolean2, bool1);
      } else {
        MDRootLayout.access$getInvalidateDividersForScrollingView(MDRootLayout.this, (ViewGroup)localObject1, paramBoolean1, paramBoolean2, bool1);
      }
      invalidate();
    }
  }
}
