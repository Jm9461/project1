package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.v4.view.Artist;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.view.menu.f;
import android.support.v7.view.menu.l;
import android.support.v7.view.menu.l.a;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.org.v4.content.R.attr;
import org.org.v4.content.R.styleable;
import org.org.v4.gui.helpers.Resources;
import org.org.v4.view.CollapsibleActionView;
import org.org.v4.view.SupportMenuInflater;

public class Toolbar
  extends ViewGroup
{
  private int after;
  private int before;
  private l.a mActionMenuPresenterCallback;
  int mButtonGravity;
  ImageButton mCollapseButtonView;
  private CharSequence mCollapseDescription;
  private Drawable mCollapseIcon;
  private boolean mCollapsible;
  private RtlSpacingHelper mContentInsets;
  private boolean mEatingHover;
  private boolean mEatingTouch;
  View mExpandedActionView;
  private d mExpandedMenuPresenter;
  private int mGravity = 8388627;
  private final ArrayList<View> mHiddenViews = new ArrayList();
  private ImageView mLogoView;
  private int mMaxButtonHeight;
  private MenuBuilder.Callback mMenuBuilderCallback;
  f mMenuItemClickListener;
  private ActionMenuView mMenuView;
  private final ActionMenuView.e mMenuViewItemClickListener = new a();
  private ImageButton mNavButtonView;
  private ActionMenuPresenter mOuterActionMenuPresenter;
  private Context mPopupContext;
  private int mPopupTheme;
  private final Runnable mShowOverflowMenuRunnable = new b();
  private CharSequence mSubtitleText;
  private int mSubtitleTextAppearance;
  private int mSubtitleTextColor;
  private TextView mSubtitleTextView;
  private final int[] mTempMargins = new int[2];
  private final ArrayList<View> mTempViews = new ArrayList();
  private int mTitleMarginBottom;
  private int mTitleMarginEnd;
  private int mTitleMarginStart;
  private int mTitleMarginTop;
  private CharSequence mTitleText;
  private int mTitleTextAppearance;
  private int mTitleTextColor;
  private TextView mTitleTextView;
  private ToolbarWidgetWrapper mWrapper;
  
  public Toolbar(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public Toolbar(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.toolbarStyle);
  }
  
  public Toolbar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = TintTypedArray.obtainStyledAttributes(getContext(), paramAttributeSet, R.styleable.Toolbar, paramInt, 0);
    mTitleTextAppearance = paramContext.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0);
    mSubtitleTextAppearance = paramContext.getResourceId(R.styleable.Toolbar_subtitleTextAppearance, 0);
    mGravity = paramContext.getInteger(R.styleable.Toolbar_android_gravity, mGravity);
    mButtonGravity = paramContext.getInteger(R.styleable.Toolbar_buttonGravity, 48);
    int i = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMargin, 0);
    paramInt = i;
    if (paramContext.hasValue(R.styleable.Toolbar_titleMargins)) {
      paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMargins, i);
    }
    mTitleMarginBottom = paramInt;
    mTitleMarginTop = paramInt;
    mTitleMarginEnd = paramInt;
    mTitleMarginStart = paramInt;
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginStart, -1);
    if (paramInt >= 0) {
      mTitleMarginStart = paramInt;
    }
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginEnd, -1);
    if (paramInt >= 0) {
      mTitleMarginEnd = paramInt;
    }
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginTop, -1);
    if (paramInt >= 0) {
      mTitleMarginTop = paramInt;
    }
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_titleMarginBottom, -1);
    if (paramInt >= 0) {
      mTitleMarginBottom = paramInt;
    }
    mMaxButtonHeight = paramContext.getDimensionPixelSize(R.styleable.Toolbar_maxButtonHeight, -1);
    paramInt = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
    i = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
    int j = paramContext.getDimensionPixelSize(R.styleable.Toolbar_contentInsetLeft, 0);
    int k = paramContext.getDimensionPixelSize(R.styleable.Toolbar_contentInsetRight, 0);
    setTitle();
    mContentInsets.setAbsolute(j, k);
    if ((paramInt != Integer.MIN_VALUE) || (i != Integer.MIN_VALUE)) {
      mContentInsets.setRelative(paramInt, i);
    }
    after = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
    before = paramContext.getDimensionPixelOffset(R.styleable.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
    mCollapseIcon = paramContext.getDrawable(R.styleable.Toolbar_collapseIcon);
    mCollapseDescription = paramContext.getText(R.styleable.Toolbar_collapseContentDescription);
    paramAttributeSet = paramContext.getText(R.styleable.Toolbar_title);
    if (!TextUtils.isEmpty(paramAttributeSet)) {
      setTitle(paramAttributeSet);
    }
    paramAttributeSet = paramContext.getText(R.styleable.Toolbar_subtitle);
    if (!TextUtils.isEmpty(paramAttributeSet)) {
      setSubtitle(paramAttributeSet);
    }
    mPopupContext = getContext();
    setPopupTheme(paramContext.getResourceId(R.styleable.Toolbar_popupTheme, 0));
    paramAttributeSet = paramContext.getDrawable(R.styleable.Toolbar_navigationIcon);
    if (paramAttributeSet != null) {
      setNavigationIcon(paramAttributeSet);
    }
    paramAttributeSet = paramContext.getText(R.styleable.Toolbar_navigationContentDescription);
    if (!TextUtils.isEmpty(paramAttributeSet)) {
      setNavigationContentDescription(paramAttributeSet);
    }
    paramAttributeSet = paramContext.getDrawable(R.styleable.Toolbar_logo);
    if (paramAttributeSet != null) {
      setLogo(paramAttributeSet);
    }
    paramAttributeSet = paramContext.getText(R.styleable.Toolbar_logoDescription);
    if (!TextUtils.isEmpty(paramAttributeSet)) {
      setLogoDescription(paramAttributeSet);
    }
    if (paramContext.hasValue(R.styleable.Toolbar_titleTextColor)) {
      setTitleTextColor(paramContext.getColor(R.styleable.Toolbar_titleTextColor, -1));
    }
    if (paramContext.hasValue(R.styleable.Toolbar_subtitleTextColor)) {
      setSubtitleTextColor(paramContext.getColor(R.styleable.Toolbar_subtitleTextColor, -1));
    }
    paramContext.recycle();
  }
  
  private void addCustomViewsWithGravity(List paramList, int paramInt)
  {
    int j = ViewCompat.getLayoutDirection(this);
    int i = 1;
    if (j != 1) {
      i = 0;
    }
    int k = getChildCount();
    j = GravityCompat.getAbsoluteGravity(paramInt, ViewCompat.getLayoutDirection(this));
    paramList.clear();
    View localView;
    e localE;
    if (i != 0)
    {
      paramInt = k - 1;
      while (paramInt >= 0)
      {
        localView = getChildAt(paramInt);
        localE = (e)localView.getLayoutParams();
        if ((mViewType == 0) && (shouldLayout(localView)) && (getChildHorizontalGravity(gravity) == j)) {
          paramList.add(localView);
        }
        paramInt -= 1;
      }
      return;
    }
    paramInt = 0;
    while (paramInt < k)
    {
      localView = getChildAt(paramInt);
      localE = (e)localView.getLayoutParams();
      if ((mViewType == 0) && (shouldLayout(localView)) && (getChildHorizontalGravity(gravity) == j)) {
        paramList.add(localView);
      }
      paramInt += 1;
    }
  }
  
  private void addSystemView(View paramView, boolean paramBoolean)
  {
    Object localObject = paramView.getLayoutParams();
    if (localObject == null) {
      localObject = generateDefaultLayoutParams();
    } else if (!checkLayoutParams((ViewGroup.LayoutParams)localObject)) {
      localObject = generateLayoutParams((ViewGroup.LayoutParams)localObject);
    } else {
      localObject = (e)localObject;
    }
    mViewType = 1;
    if ((paramBoolean) && (mExpandedActionView != null))
    {
      paramView.setLayoutParams((ViewGroup.LayoutParams)localObject);
      mHiddenViews.add(paramView);
      return;
    }
    addView(paramView, (ViewGroup.LayoutParams)localObject);
  }
  
  private void ensureLogoView()
  {
    if (mLogoView == null) {
      mLogoView = new AppCompatImageView(getContext());
    }
  }
  
  private void ensureMenu()
  {
    ensureMenuView();
    if (mMenuView.peekMenu() == null)
    {
      f localF = (f)mMenuView.getMenu();
      if (mExpandedMenuPresenter == null) {
        mExpandedMenuPresenter = new d();
      }
      mMenuView.setExpandedActionViewsExclusive(true);
      localF.a(mExpandedMenuPresenter, mPopupContext);
    }
  }
  
  private void ensureMenuView()
  {
    if (mMenuView == null)
    {
      mMenuView = new ActionMenuView(getContext());
      mMenuView.setPopupTheme(mPopupTheme);
      mMenuView.setOnMenuItemClickListener(mMenuViewItemClickListener);
      mMenuView.setMenuCallbacks(mActionMenuPresenterCallback, mMenuBuilderCallback);
      e localE = generateDefaultLayoutParams();
      gravity = (0x800005 | mButtonGravity & 0x70);
      mMenuView.setLayoutParams(localE);
      addSystemView(mMenuView, false);
    }
  }
  
  private void ensureNavButtonView()
  {
    if (mNavButtonView == null)
    {
      mNavButtonView = new AppCompatImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
      e localE = generateDefaultLayoutParams();
      gravity = (0x800003 | mButtonGravity & 0x70);
      mNavButtonView.setLayoutParams(localE);
    }
  }
  
  private int getChildHorizontalGravity(int paramInt)
  {
    int i = ViewCompat.getLayoutDirection(this);
    paramInt = GravityCompat.getAbsoluteGravity(paramInt, i) & 0x7;
    if ((paramInt != 1) && (paramInt != 3) && (paramInt != 5))
    {
      if (i == 1) {
        return 5;
      }
    }
    else {
      return paramInt;
    }
    return 3;
  }
  
  private int getChildTop(View paramView, int paramInt)
  {
    e localE = (e)paramView.getLayoutParams();
    int j = paramView.getMeasuredHeight();
    if (paramInt > 0) {
      paramInt = (j - paramInt) / 2;
    } else {
      paramInt = 0;
    }
    int i = getChildVerticalGravity(gravity);
    if (i != 48)
    {
      if (i != 80)
      {
        int k = getPaddingTop();
        paramInt = getPaddingBottom();
        int m = getHeight();
        i = (m - k - paramInt - j) / 2;
        if (i < topMargin)
        {
          paramInt = topMargin;
        }
        else
        {
          j = m - paramInt - j - i - k;
          m = bottomMargin;
          paramInt = i;
          if (j < m) {
            paramInt = Math.max(0, i - (m - j));
          }
        }
        return k + paramInt;
      }
      return getHeight() - getPaddingBottom() - j - bottomMargin - paramInt;
    }
    return getPaddingTop() - paramInt;
  }
  
  private int getChildVerticalGravity(int paramInt)
  {
    int i = paramInt & 0x70;
    paramInt = i;
    if (i != 16)
    {
      paramInt = i;
      if (i != 48)
      {
        paramInt = i;
        if (i != 80) {
          paramInt = mGravity & 0x70;
        }
      }
    }
    return paramInt;
  }
  
  private int getHorizontalMargins(View paramView)
  {
    paramView = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    return MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl.getMarginStart(paramView) + MarginLayoutParamsCompat.MarginLayoutParamsCompatImpl.getMarginEnd(paramView);
  }
  
  private MenuInflater getMenuInflater()
  {
    return new SupportMenuInflater(getContext());
  }
  
  private int getVerticalMargins(View paramView)
  {
    paramView = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    return topMargin + bottomMargin;
  }
  
  private int getViewListMeasuredWidth(List paramList, int[] paramArrayOfInt)
  {
    int m = paramArrayOfInt[0];
    int k = paramArrayOfInt[1];
    int j = 0;
    int n = paramList.size();
    int i = 0;
    while (i < n)
    {
      paramArrayOfInt = (View)paramList.get(i);
      e localE = (e)paramArrayOfInt.getLayoutParams();
      m = leftMargin - m;
      k = rightMargin - k;
      int i1 = Math.max(0, m);
      int i2 = Math.max(0, k);
      m = Math.max(0, -m);
      k = Math.max(0, -k);
      j += paramArrayOfInt.getMeasuredWidth() + i1 + i2;
      i += 1;
    }
    return j;
  }
  
  private boolean isChildOrHidden(View paramView)
  {
    return (paramView.getParent() == this) || (mHiddenViews.contains(paramView));
  }
  
  private int layoutChildLeft(View paramView, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    e localE = (e)paramView.getLayoutParams();
    int i = leftMargin - paramArrayOfInt[0];
    paramInt1 += Math.max(0, i);
    paramArrayOfInt[0] = Math.max(0, -i);
    paramInt2 = getChildTop(paramView, paramInt2);
    i = paramView.getMeasuredWidth();
    paramView.layout(paramInt1, paramInt2, paramInt1 + i, paramView.getMeasuredHeight() + paramInt2);
    return paramInt1 + (rightMargin + i);
  }
  
  private int layoutChildRight(View paramView, int paramInt1, int[] paramArrayOfInt, int paramInt2)
  {
    e localE = (e)paramView.getLayoutParams();
    int i = rightMargin - paramArrayOfInt[1];
    paramInt1 -= Math.max(0, i);
    paramArrayOfInt[1] = Math.max(0, -i);
    paramInt2 = getChildTop(paramView, paramInt2);
    i = paramView.getMeasuredWidth();
    paramView.layout(paramInt1 - i, paramInt2, paramInt1, paramView.getMeasuredHeight() + paramInt2);
    return paramInt1 - (leftMargin + i);
  }
  
  private int measureChildCollapseMargins(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    int i = leftMargin - paramArrayOfInt[0];
    int j = rightMargin - paramArrayOfInt[1];
    int k = Math.max(0, i) + Math.max(0, j);
    paramArrayOfInt[0] = Math.max(0, -i);
    paramArrayOfInt[1] = Math.max(0, -j);
    paramView.measure(ViewGroup.getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight() + k + paramInt2, width), ViewGroup.getChildMeasureSpec(paramInt3, getPaddingTop() + getPaddingBottom() + topMargin + bottomMargin + paramInt4, height));
    return paramView.getMeasuredWidth() + k;
  }
  
  private void measureChildConstrained(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    int i = ViewGroup.getChildMeasureSpec(paramInt1, getPaddingLeft() + getPaddingRight() + leftMargin + rightMargin + paramInt2, width);
    paramInt3 = ViewGroup.getChildMeasureSpec(paramInt3, getPaddingTop() + getPaddingBottom() + topMargin + bottomMargin + paramInt4, height);
    paramInt1 = paramInt3;
    paramInt4 = View.MeasureSpec.getMode(paramInt3);
    paramInt2 = paramInt1;
    if (paramInt4 != 1073741824)
    {
      paramInt2 = paramInt1;
      if (paramInt5 >= 0)
      {
        if (paramInt4 != 0) {
          paramInt5 = Math.min(View.MeasureSpec.getSize(paramInt3), paramInt5);
        }
        paramInt2 = View.MeasureSpec.makeMeasureSpec(paramInt5, 1073741824);
      }
    }
    paramView.measure(i, paramInt2);
  }
  
  private void postShowOverflowMenu()
  {
    removeCallbacks(mShowOverflowMenuRunnable);
    post(mShowOverflowMenuRunnable);
  }
  
  private void setTitle()
  {
    if (mContentInsets == null) {
      mContentInsets = new RtlSpacingHelper();
    }
  }
  
  private boolean shouldCollapse()
  {
    if (!mCollapsible) {
      return false;
    }
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = getChildAt(i);
      if ((shouldLayout(localView)) && (localView.getMeasuredWidth() > 0) && (localView.getMeasuredHeight() > 0)) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private boolean shouldLayout(View paramView)
  {
    return (paramView != null) && (paramView.getParent() == this) && (paramView.getVisibility() != 8);
  }
  
  void addChildrenForExpandedActionView()
  {
    int i = mHiddenViews.size() - 1;
    while (i >= 0)
    {
      addView((View)mHiddenViews.get(i));
      i -= 1;
    }
    mHiddenViews.clear();
  }
  
  public boolean canShowOverflowMenu()
  {
    if (getVisibility() == 0)
    {
      ActionMenuView localActionMenuView = mMenuView;
      if ((localActionMenuView != null) && (localActionMenuView.isOverflowReserved())) {
        return true;
      }
    }
    return false;
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return (super.checkLayoutParams(paramLayoutParams)) && ((paramLayoutParams instanceof e));
  }
  
  public void collapseActionView()
  {
    Object localObject = mExpandedMenuPresenter;
    if (localObject == null) {
      localObject = null;
    } else {
      localObject = mCurrentExpandedItem;
    }
    if (localObject != null) {
      ((MenuItemImpl)localObject).collapseActionView();
    }
  }
  
  public void dismissPopupMenus()
  {
    ActionMenuView localActionMenuView = mMenuView;
    if (localActionMenuView != null) {
      localActionMenuView.dismissPopupMenus();
    }
  }
  
  void ensureCollapseButtonView()
  {
    if (mCollapseButtonView == null)
    {
      mCollapseButtonView = new AppCompatImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
      mCollapseButtonView.setImageDrawable(mCollapseIcon);
      mCollapseButtonView.setContentDescription(mCollapseDescription);
      e localE = generateDefaultLayoutParams();
      gravity = (0x800003 | mButtonGravity & 0x70);
      mViewType = 2;
      mCollapseButtonView.setLayoutParams(localE);
      mCollapseButtonView.setOnClickListener(new c());
    }
  }
  
  protected e generateDefaultLayoutParams()
  {
    return new e(-2, -2);
  }
  
  public e generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new e(getContext(), paramAttributeSet);
  }
  
  protected e generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof e)) {
      return new e((e)paramLayoutParams);
    }
    if ((paramLayoutParams instanceof ActionBar.LayoutParams)) {
      return new e((ActionBar.LayoutParams)paramLayoutParams);
    }
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
      return new e((ViewGroup.MarginLayoutParams)paramLayoutParams);
    }
    return new e(paramLayoutParams);
  }
  
  public int getContentInsetEnd()
  {
    RtlSpacingHelper localRtlSpacingHelper = mContentInsets;
    if (localRtlSpacingHelper != null) {
      return localRtlSpacingHelper.getEnd();
    }
    return 0;
  }
  
  public int getContentInsetEndWithActions()
  {
    int i = before;
    if (i != Integer.MIN_VALUE) {
      return i;
    }
    return getContentInsetEnd();
  }
  
  public int getContentInsetLeft()
  {
    RtlSpacingHelper localRtlSpacingHelper = mContentInsets;
    if (localRtlSpacingHelper != null) {
      return localRtlSpacingHelper.getLeft();
    }
    return 0;
  }
  
  public int getContentInsetRight()
  {
    RtlSpacingHelper localRtlSpacingHelper = mContentInsets;
    if (localRtlSpacingHelper != null) {
      return localRtlSpacingHelper.getRight();
    }
    return 0;
  }
  
  public int getContentInsetStart()
  {
    RtlSpacingHelper localRtlSpacingHelper = mContentInsets;
    if (localRtlSpacingHelper != null) {
      return localRtlSpacingHelper.getStart();
    }
    return 0;
  }
  
  public int getContentInsetStartWithNavigation()
  {
    int i = after;
    if (i != Integer.MIN_VALUE) {
      return i;
    }
    return getContentInsetStart();
  }
  
  public int getCurrentContentInsetEnd()
  {
    int i = 0;
    Object localObject = mMenuView;
    if (localObject != null)
    {
      localObject = ((ActionMenuView)localObject).peekMenu();
      if ((localObject != null) && (((f)localObject).hasVisibleItems())) {
        i = 1;
      } else {
        i = 0;
      }
    }
    if (i != 0) {
      return Math.max(getContentInsetEnd(), Math.max(before, 0));
    }
    return getContentInsetEnd();
  }
  
  public int getCurrentContentInsetLeft()
  {
    if (ViewCompat.getLayoutDirection(this) == 1) {
      return getCurrentContentInsetEnd();
    }
    return getCurrentContentInsetStart();
  }
  
  public int getCurrentContentInsetRight()
  {
    if (ViewCompat.getLayoutDirection(this) == 1) {
      return getCurrentContentInsetStart();
    }
    return getCurrentContentInsetEnd();
  }
  
  public int getCurrentContentInsetStart()
  {
    if (getNavigationIcon() != null) {
      return Math.max(getContentInsetStart(), Math.max(after, 0));
    }
    return getContentInsetStart();
  }
  
  public Drawable getLogo()
  {
    ImageView localImageView = mLogoView;
    if (localImageView != null) {
      return localImageView.getDrawable();
    }
    return null;
  }
  
  public CharSequence getLogoDescription()
  {
    ImageView localImageView = mLogoView;
    if (localImageView != null) {
      return localImageView.getContentDescription();
    }
    return null;
  }
  
  public Menu getMenu()
  {
    ensureMenu();
    return mMenuView.getMenu();
  }
  
  public CharSequence getNavigationContentDescription()
  {
    ImageButton localImageButton = mNavButtonView;
    if (localImageButton != null) {
      return localImageButton.getContentDescription();
    }
    return null;
  }
  
  public Drawable getNavigationIcon()
  {
    ImageButton localImageButton = mNavButtonView;
    if (localImageButton != null) {
      return localImageButton.getDrawable();
    }
    return null;
  }
  
  ActionMenuPresenter getOuterActionMenuPresenter()
  {
    return mOuterActionMenuPresenter;
  }
  
  public Drawable getOverflowIcon()
  {
    ensureMenu();
    return mMenuView.getOverflowIcon();
  }
  
  Context getPopupContext()
  {
    return mPopupContext;
  }
  
  public int getPopupTheme()
  {
    return mPopupTheme;
  }
  
  public CharSequence getSubtitle()
  {
    return mSubtitleText;
  }
  
  public CharSequence getTitle()
  {
    return mTitleText;
  }
  
  public int getTitleMarginBottom()
  {
    return mTitleMarginBottom;
  }
  
  public int getTitleMarginEnd()
  {
    return mTitleMarginEnd;
  }
  
  public int getTitleMarginStart()
  {
    return mTitleMarginStart;
  }
  
  public int getTitleMarginTop()
  {
    return mTitleMarginTop;
  }
  
  public DecorToolbar getWrapper()
  {
    if (mWrapper == null) {
      mWrapper = new ToolbarWidgetWrapper(this, true);
    }
    return mWrapper;
  }
  
  public boolean hasExpandedActionView()
  {
    d localD = mExpandedMenuPresenter;
    return (localD != null) && (mCurrentExpandedItem != null);
  }
  
  public boolean hideOverflowMenu()
  {
    ActionMenuView localActionMenuView = mMenuView;
    return (localActionMenuView != null) && (localActionMenuView.hideOverflowMenu());
  }
  
  public boolean isOverflowMenuShowPending()
  {
    ActionMenuView localActionMenuView = mMenuView;
    return (localActionMenuView != null) && (localActionMenuView.isOverflowMenuShowPending());
  }
  
  public boolean isOverflowMenuShowing()
  {
    ActionMenuView localActionMenuView = mMenuView;
    return (localActionMenuView != null) && (localActionMenuView.isOverflowMenuShowing());
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    removeCallbacks(mShowOverflowMenuRunnable);
  }
  
  public boolean onHoverEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionMasked();
    if (i == 9) {
      mEatingHover = false;
    }
    if (!mEatingHover)
    {
      boolean bool = super.onHoverEvent(paramMotionEvent);
      if ((i == 9) && (!bool)) {
        mEatingHover = true;
      }
    }
    if ((i == 10) || (i == 3)) {
      mEatingHover = false;
    }
    return true;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j;
    if (ViewCompat.getLayoutDirection(this) == 1) {
      j = 1;
    } else {
      j = 0;
    }
    int i1 = getWidth();
    int i4 = getHeight();
    int n = getPaddingLeft();
    int i2 = getPaddingRight();
    int i3 = getPaddingTop();
    int i5 = getPaddingBottom();
    paramInt3 = n;
    int k = i1 - i2;
    int[] arrayOfInt = mTempMargins;
    arrayOfInt[1] = 0;
    arrayOfInt[0] = 0;
    paramInt1 = ViewCompat.getMinimumHeight(this);
    int i;
    if (paramInt1 >= 0) {
      i = Math.min(paramInt1, paramInt4 - paramInt2);
    } else {
      i = 0;
    }
    paramInt1 = paramInt3;
    paramInt2 = k;
    if (shouldLayout(mNavButtonView)) {
      if (j != 0)
      {
        paramInt2 = layoutChildRight(mNavButtonView, k, arrayOfInt, i);
        paramInt1 = paramInt3;
      }
      else
      {
        paramInt1 = layoutChildLeft(mNavButtonView, n, arrayOfInt, i);
        paramInt2 = k;
      }
    }
    paramInt3 = paramInt1;
    paramInt4 = paramInt2;
    if (shouldLayout(mCollapseButtonView)) {
      if (j != 0)
      {
        paramInt4 = layoutChildRight(mCollapseButtonView, paramInt2, arrayOfInt, i);
        paramInt3 = paramInt1;
      }
      else
      {
        paramInt3 = layoutChildLeft(mCollapseButtonView, paramInt1, arrayOfInt, i);
        paramInt4 = paramInt2;
      }
    }
    paramInt2 = paramInt3;
    paramInt1 = paramInt4;
    if (shouldLayout(mMenuView)) {
      if (j != 0)
      {
        paramInt2 = layoutChildLeft(mMenuView, paramInt3, arrayOfInt, i);
        paramInt1 = paramInt4;
      }
      else
      {
        paramInt1 = layoutChildRight(mMenuView, paramInt4, arrayOfInt, i);
        paramInt2 = paramInt3;
      }
    }
    paramInt4 = getCurrentContentInsetLeft();
    paramInt3 = getCurrentContentInsetRight();
    arrayOfInt[0] = Math.max(0, paramInt4 - paramInt2);
    arrayOfInt[1] = Math.max(0, paramInt3 - (i1 - i2 - paramInt1));
    k = Math.max(paramInt2, paramInt4);
    paramInt2 = k;
    int m = Math.min(paramInt1, i1 - i2 - paramInt3);
    paramInt3 = m;
    paramInt1 = paramInt2;
    paramInt4 = paramInt3;
    if (shouldLayout(mExpandedActionView)) {
      if (j != 0)
      {
        paramInt4 = layoutChildRight(mExpandedActionView, m, arrayOfInt, i);
        paramInt1 = paramInt2;
      }
      else
      {
        paramInt1 = layoutChildLeft(mExpandedActionView, k, arrayOfInt, i);
        paramInt4 = paramInt3;
      }
    }
    paramInt3 = paramInt1;
    paramInt2 = paramInt4;
    if (shouldLayout(mLogoView)) {
      if (j != 0)
      {
        paramInt2 = layoutChildRight(mLogoView, paramInt4, arrayOfInt, i);
        paramInt3 = paramInt1;
      }
      else
      {
        paramInt3 = layoutChildLeft(mLogoView, paramInt1, arrayOfInt, i);
        paramInt2 = paramInt4;
      }
    }
    paramBoolean = shouldLayout(mTitleTextView);
    boolean bool = shouldLayout(mSubtitleTextView);
    paramInt1 = 0;
    Object localObject1;
    if (paramBoolean)
    {
      localObject1 = (e)mTitleTextView.getLayoutParams();
      paramInt1 = 0 + (topMargin + mTitleTextView.getMeasuredHeight() + bottomMargin);
    }
    k = paramInt1;
    if (bool)
    {
      localObject1 = (e)mSubtitleTextView.getLayoutParams();
      k = paramInt1 + (topMargin + mSubtitleTextView.getMeasuredHeight() + bottomMargin);
    }
    if ((!paramBoolean) && (!bool))
    {
      paramInt1 = paramInt3;
      paramInt3 = paramInt2;
    }
    else
    {
      if (paramBoolean) {
        localObject1 = mTitleTextView;
      } else {
        localObject1 = mSubtitleTextView;
      }
      if (bool) {
        localObject2 = mSubtitleTextView;
      } else {
        localObject2 = mTitleTextView;
      }
      localObject1 = (e)((View)localObject1).getLayoutParams();
      Object localObject2 = (e)((View)localObject2).getLayoutParams();
      if (paramBoolean) {
        if (mTitleTextView.getMeasuredWidth() > 0) {
          break label699;
        }
      }
      if ((bool) && (mSubtitleTextView.getMeasuredWidth() > 0)) {
        label699:
        paramInt4 = 1;
      } else {
        paramInt4 = 0;
      }
      paramInt1 = mGravity & 0x70;
      if (paramInt1 != 48)
      {
        if (paramInt1 != 80)
        {
          m = (i4 - i3 - i5 - k) / 2;
          paramInt1 = topMargin;
          int i6 = mTitleMarginTop;
          if (m < paramInt1 + i6)
          {
            paramInt1 += i6;
          }
          else
          {
            k = i4 - i5 - k - m - i3;
            i4 = bottomMargin;
            i5 = mTitleMarginBottom;
            paramInt1 = m;
            if (k < i4 + i5) {
              paramInt1 = Math.max(0, m - (bottomMargin + i5 - k));
            }
          }
          paramInt1 = i3 + paramInt1;
        }
        else
        {
          paramInt1 = i4 - i5 - bottomMargin - mTitleMarginBottom - k;
        }
      }
      else {
        paramInt1 = getPaddingTop() + topMargin + mTitleMarginTop;
      }
      if (j != 0)
      {
        if (paramInt4 != 0) {
          j = mTitleMarginStart;
        } else {
          j = 0;
        }
        j -= arrayOfInt[1];
        paramInt2 -= Math.max(0, j);
        arrayOfInt[1] = Math.max(0, -j);
        k = paramInt2;
        j = paramInt2;
        m = paramInt1;
        if (paramBoolean)
        {
          localObject1 = (e)mTitleTextView.getLayoutParams();
          k = paramInt2 - mTitleTextView.getMeasuredWidth();
          m = mTitleTextView.getMeasuredHeight() + paramInt1;
          mTitleTextView.layout(k, paramInt1, paramInt2, m);
          k -= mTitleMarginEnd;
          m += bottomMargin;
        }
        paramInt1 = j;
        if (bool)
        {
          localObject1 = (e)mSubtitleTextView.getLayoutParams();
          paramInt1 = m + topMargin;
          m = mSubtitleTextView.getMeasuredWidth();
          i3 = mSubtitleTextView.getMeasuredHeight();
          mSubtitleTextView.layout(j - m, paramInt1, j, i3 + paramInt1);
          paramInt1 = j - mTitleMarginEnd;
          j = bottomMargin;
        }
        if (paramInt4 != 0) {
          paramInt2 = Math.min(k, paramInt1);
        }
        paramInt1 = paramInt3;
        paramInt3 = paramInt2;
      }
      else
      {
        if (paramInt4 != 0) {
          j = mTitleMarginStart;
        } else {
          j = 0;
        }
        j -= arrayOfInt[0];
        paramInt3 += Math.max(0, j);
        arrayOfInt[0] = Math.max(0, -j);
        j = paramInt3;
        k = paramInt3;
        if (paramBoolean)
        {
          localObject1 = (e)mTitleTextView.getLayoutParams();
          j = mTitleTextView.getMeasuredWidth() + paramInt3;
          m = mTitleTextView.getMeasuredHeight() + paramInt1;
          mTitleTextView.layout(paramInt3, paramInt1, j, m);
          j += mTitleMarginEnd;
          m += bottomMargin;
        }
        else
        {
          m = paramInt1;
        }
        paramInt1 = paramInt3;
        if (bool)
        {
          localObject1 = (e)mSubtitleTextView.getLayoutParams();
          k = m + topMargin;
          m = mSubtitleTextView.getMeasuredWidth() + paramInt3;
          i3 = mSubtitleTextView.getMeasuredHeight();
          mSubtitleTextView.layout(paramInt3, k, m, i3 + k);
          k = m + mTitleMarginEnd;
          paramInt3 = bottomMargin;
        }
        paramInt3 = paramInt2;
        if (paramInt4 != 0)
        {
          paramInt1 = Math.max(j, k);
          paramInt3 = paramInt2;
        }
      }
    }
    addCustomViewsWithGravity(mTempViews, 3);
    paramInt4 = mTempViews.size();
    paramInt2 = 0;
    while (paramInt2 < paramInt4)
    {
      paramInt1 = layoutChildLeft((View)mTempViews.get(paramInt2), paramInt1, arrayOfInt, i);
      paramInt2 += 1;
    }
    addCustomViewsWithGravity(mTempViews, 5);
    paramInt4 = mTempViews.size();
    paramInt2 = 0;
    while (paramInt2 < paramInt4)
    {
      paramInt3 = layoutChildRight((View)mTempViews.get(paramInt2), paramInt3, arrayOfInt, i);
      paramInt2 += 1;
    }
    addCustomViewsWithGravity(mTempViews, 1);
    paramInt4 = getViewListMeasuredWidth(mTempViews, arrayOfInt);
    paramInt2 = n + (i1 - n - i2) / 2 - paramInt4 / 2;
    paramInt4 = paramInt2 + paramInt4;
    if (paramInt2 >= paramInt1)
    {
      paramInt1 = paramInt2;
      if (paramInt4 > paramInt3) {
        paramInt1 = paramInt2 - (paramInt4 - paramInt3);
      }
    }
    paramInt4 = mTempViews.size();
    paramInt3 = 0;
    paramInt2 = paramInt1;
    paramInt1 = paramInt3;
    while (paramInt1 < paramInt4)
    {
      paramInt2 = layoutChildLeft((View)mTempViews.get(paramInt1), paramInt2, arrayOfInt, i);
      paramInt1 += 1;
    }
    mTempViews.clear();
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int m = 0;
    int k = 0;
    int[] arrayOfInt = mTempMargins;
    if (ViewUtils.isLayoutRtl(this))
    {
      i2 = 1;
      i1 = 0;
    }
    else
    {
      i2 = 0;
      i1 = 1;
    }
    int n = 0;
    if (shouldLayout(mNavButtonView))
    {
      measureChildConstrained(mNavButtonView, paramInt1, 0, paramInt2, 0, mMaxButtonHeight);
      n = mNavButtonView.getMeasuredWidth() + getHorizontalMargins(mNavButtonView);
      m = Math.max(0, mNavButtonView.getMeasuredHeight() + getVerticalMargins(mNavButtonView));
      k = View.combineMeasuredStates(0, mNavButtonView.getMeasuredState());
    }
    int j = m;
    int i = k;
    if (shouldLayout(mCollapseButtonView))
    {
      measureChildConstrained(mCollapseButtonView, paramInt1, 0, paramInt2, 0, mMaxButtonHeight);
      n = mCollapseButtonView.getMeasuredWidth() + getHorizontalMargins(mCollapseButtonView);
      j = Math.max(m, mCollapseButtonView.getMeasuredHeight() + getVerticalMargins(mCollapseButtonView));
      i = View.combineMeasuredStates(k, mCollapseButtonView.getMeasuredState());
    }
    k = getCurrentContentInsetStart();
    int i3 = 0 + Math.max(k, n);
    arrayOfInt[i2] = Math.max(0, k - n);
    if (shouldLayout(mMenuView))
    {
      measureChildConstrained(mMenuView, paramInt1, i3, paramInt2, 0, mMaxButtonHeight);
      m = mMenuView.getMeasuredWidth();
      n = getHorizontalMargins(mMenuView);
      k = Math.max(j, mMenuView.getMeasuredHeight() + getVerticalMargins(mMenuView));
      j = View.combineMeasuredStates(i, mMenuView.getMeasuredState());
      i = k;
      k = m + n;
    }
    else
    {
      k = j;
      m = 0;
      j = i;
      i = k;
      k = m;
    }
    m = getCurrentContentInsetEnd();
    n = i3 + Math.max(m, k);
    arrayOfInt[i1] = Math.max(0, m - k);
    if (shouldLayout(mExpandedActionView))
    {
      n += measureChildCollapseMargins(mExpandedActionView, paramInt1, n, paramInt2, 0, arrayOfInt);
      m = Math.max(i, mExpandedActionView.getMeasuredHeight() + getVerticalMargins(mExpandedActionView));
      j = View.combineMeasuredStates(j, mExpandedActionView.getMeasuredState());
    }
    else
    {
      m = i;
    }
    i = j;
    int i1 = m;
    k = n;
    if (shouldLayout(mLogoView))
    {
      k = n + measureChildCollapseMargins(mLogoView, paramInt1, n, paramInt2, 0, arrayOfInt);
      i1 = Math.max(m, mLogoView.getMeasuredHeight() + getVerticalMargins(mLogoView));
      i = View.combineMeasuredStates(j, mLogoView.getMeasuredState());
    }
    i3 = getChildCount();
    j = 0;
    n = k;
    while (j < i3)
    {
      View localView = getChildAt(j);
      k = i;
      m = n;
      i2 = i1;
      if (getLayoutParamsmViewType == 0) {
        if (!shouldLayout(localView))
        {
          k = i;
          m = n;
          i2 = i1;
        }
        else
        {
          m = n + measureChildCollapseMargins(localView, paramInt1, n, paramInt2, 0, arrayOfInt);
          i2 = Math.max(i1, localView.getMeasuredHeight() + getVerticalMargins(localView));
          k = View.combineMeasuredStates(i, localView.getMeasuredState());
        }
      }
      j += 1;
      i = k;
      n = m;
      i1 = i2;
    }
    m = 0;
    k = 0;
    int i4 = mTitleMarginTop + mTitleMarginBottom;
    int i5 = mTitleMarginStart + mTitleMarginEnd;
    j = i;
    if (shouldLayout(mTitleTextView))
    {
      measureChildCollapseMargins(mTitleTextView, paramInt1, n + i5, paramInt2, i4, arrayOfInt);
      m = mTitleTextView.getMeasuredWidth() + getHorizontalMargins(mTitleTextView);
      k = mTitleTextView.getMeasuredHeight() + getVerticalMargins(mTitleTextView);
      j = View.combineMeasuredStates(i, mTitleTextView.getMeasuredState());
    }
    i = j;
    int i2 = m;
    i3 = k;
    if (shouldLayout(mSubtitleTextView))
    {
      i2 = Math.max(m, measureChildCollapseMargins(mSubtitleTextView, paramInt1, n + i5, paramInt2, k + i4, arrayOfInt));
      i3 = k + (mSubtitleTextView.getMeasuredHeight() + getVerticalMargins(mSubtitleTextView));
      i = View.combineMeasuredStates(j, mSubtitleTextView.getMeasuredState());
    }
    j = Math.max(i1, i3);
    i1 = getPaddingLeft();
    i3 = getPaddingRight();
    k = getPaddingTop();
    m = getPaddingBottom();
    n = View.resolveSizeAndState(Math.max(n + i2 + (i1 + i3), getSuggestedMinimumWidth()), paramInt1, 0xFF000000 & i);
    paramInt1 = View.resolveSizeAndState(Math.max(j + (k + m), getSuggestedMinimumHeight()), paramInt2, i << 16);
    if (shouldCollapse()) {
      paramInt1 = 0;
    }
    setMeasuredDimension(n, paramInt1);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof g))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    g localG = (g)paramParcelable;
    super.onRestoreInstanceState(localG.getSuperState());
    paramParcelable = mMenuView;
    if (paramParcelable != null) {
      paramParcelable = paramParcelable.peekMenu();
    } else {
      paramParcelable = null;
    }
    int i = expandedMenuItemId;
    if ((i != 0) && (mExpandedMenuPresenter != null) && (paramParcelable != null))
    {
      paramParcelable = paramParcelable.findItem(i);
      if (paramParcelable != null) {
        paramParcelable.expandActionView();
      }
    }
    if (isOverflowOpen) {
      postShowOverflowMenu();
    }
  }
  
  public void onRtlPropertiesChanged(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      super.onRtlPropertiesChanged(paramInt);
    }
    setTitle();
    RtlSpacingHelper localRtlSpacingHelper = mContentInsets;
    boolean bool = true;
    if (paramInt != 1) {
      bool = false;
    }
    localRtlSpacingHelper.setDirection(bool);
  }
  
  protected Parcelable onSaveInstanceState()
  {
    g localG = new g(super.onSaveInstanceState());
    Object localObject = mExpandedMenuPresenter;
    if (localObject != null)
    {
      localObject = mCurrentExpandedItem;
      if (localObject != null) {
        expandedMenuItemId = ((MenuItemImpl)localObject).getItemId();
      }
    }
    isOverflowOpen = isOverflowMenuShowing();
    return localG;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionMasked();
    if (i == 0) {
      mEatingTouch = false;
    }
    if (!mEatingTouch)
    {
      boolean bool = super.onTouchEvent(paramMotionEvent);
      if ((i == 0) && (!bool)) {
        mEatingTouch = true;
      }
    }
    if ((i == 1) || (i == 3)) {
      mEatingTouch = false;
    }
    return true;
  }
  
  void removeChildrenForExpandedActionView()
  {
    int i = getChildCount() - 1;
    while (i >= 0)
    {
      View localView = getChildAt(i);
      if ((getLayoutParamsmViewType != 2) && (localView != mMenuView))
      {
        removeViewAt(i);
        mHiddenViews.add(localView);
      }
      i -= 1;
    }
  }
  
  public void setCollapsible(boolean paramBoolean)
  {
    mCollapsible = paramBoolean;
    requestLayout();
  }
  
  public void setContentInsetEndWithActions(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = Integer.MIN_VALUE;
    }
    if (i != before)
    {
      before = i;
      if (getNavigationIcon() != null) {
        requestLayout();
      }
    }
  }
  
  public void setContentInsetStartWithNavigation(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = Integer.MIN_VALUE;
    }
    if (i != after)
    {
      after = i;
      if (getNavigationIcon() != null) {
        requestLayout();
      }
    }
  }
  
  public void setContentInsetsAbsolute(int paramInt1, int paramInt2)
  {
    setTitle();
    mContentInsets.setAbsolute(paramInt1, paramInt2);
  }
  
  public void setContentInsetsRelative(int paramInt1, int paramInt2)
  {
    setTitle();
    mContentInsets.setRelative(paramInt1, paramInt2);
  }
  
  public void setLogo(int paramInt)
  {
    setLogo(Resources.getDrawable(getContext(), paramInt));
  }
  
  public void setLogo(Drawable paramDrawable)
  {
    if (paramDrawable != null)
    {
      ensureLogoView();
      if (!isChildOrHidden(mLogoView)) {
        addSystemView(mLogoView, true);
      }
    }
    else
    {
      localImageView = mLogoView;
      if ((localImageView != null) && (isChildOrHidden(localImageView)))
      {
        removeView(mLogoView);
        mHiddenViews.remove(mLogoView);
      }
    }
    ImageView localImageView = mLogoView;
    if (localImageView != null) {
      localImageView.setImageDrawable(paramDrawable);
    }
  }
  
  public void setLogoDescription(int paramInt)
  {
    setLogoDescription(getContext().getText(paramInt));
  }
  
  public void setLogoDescription(CharSequence paramCharSequence)
  {
    if (!TextUtils.isEmpty(paramCharSequence)) {
      ensureLogoView();
    }
    ImageView localImageView = mLogoView;
    if (localImageView != null) {
      localImageView.setContentDescription(paramCharSequence);
    }
  }
  
  public void setMenu(f paramF, ActionMenuPresenter paramActionMenuPresenter)
  {
    if ((paramF == null) && (mMenuView == null)) {
      return;
    }
    ensureMenuView();
    f localF = mMenuView.peekMenu();
    if (localF == paramF) {
      return;
    }
    if (localF != null)
    {
      localF.b(mOuterActionMenuPresenter);
      localF.b(mExpandedMenuPresenter);
    }
    if (mExpandedMenuPresenter == null) {
      mExpandedMenuPresenter = new d();
    }
    paramActionMenuPresenter.setExpandedActionViewsExclusive(true);
    if (paramF != null)
    {
      paramF.a(paramActionMenuPresenter, mPopupContext);
      paramF.a(mExpandedMenuPresenter, mPopupContext);
    }
    else
    {
      paramActionMenuPresenter.initForMenu(mPopupContext, null);
      mExpandedMenuPresenter.initForMenu(mPopupContext, null);
      paramActionMenuPresenter.updateMenuView(true);
      mExpandedMenuPresenter.updateMenuView(true);
    }
    mMenuView.setPopupTheme(mPopupTheme);
    mMenuView.setPresenter(paramActionMenuPresenter);
    mOuterActionMenuPresenter = paramActionMenuPresenter;
  }
  
  public void setMenuCallbacks(l.a paramA, MenuBuilder.Callback paramCallback)
  {
    mActionMenuPresenterCallback = paramA;
    mMenuBuilderCallback = paramCallback;
    ActionMenuView localActionMenuView = mMenuView;
    if (localActionMenuView != null) {
      localActionMenuView.setMenuCallbacks(paramA, paramCallback);
    }
  }
  
  public void setNavigationContentDescription(int paramInt)
  {
    CharSequence localCharSequence;
    if (paramInt != 0) {
      localCharSequence = getContext().getText(paramInt);
    } else {
      localCharSequence = null;
    }
    setNavigationContentDescription(localCharSequence);
  }
  
  public void setNavigationContentDescription(CharSequence paramCharSequence)
  {
    if (!TextUtils.isEmpty(paramCharSequence)) {
      ensureNavButtonView();
    }
    ImageButton localImageButton = mNavButtonView;
    if (localImageButton != null) {
      localImageButton.setContentDescription(paramCharSequence);
    }
  }
  
  public void setNavigationIcon(int paramInt)
  {
    setNavigationIcon(Resources.getDrawable(getContext(), paramInt));
  }
  
  public void setNavigationIcon(Drawable paramDrawable)
  {
    if (paramDrawable != null)
    {
      ensureNavButtonView();
      if (!isChildOrHidden(mNavButtonView)) {
        addSystemView(mNavButtonView, true);
      }
    }
    else
    {
      localImageButton = mNavButtonView;
      if ((localImageButton != null) && (isChildOrHidden(localImageButton)))
      {
        removeView(mNavButtonView);
        mHiddenViews.remove(mNavButtonView);
      }
    }
    ImageButton localImageButton = mNavButtonView;
    if (localImageButton != null) {
      localImageButton.setImageDrawable(paramDrawable);
    }
  }
  
  public void setNavigationOnClickListener(View.OnClickListener paramOnClickListener)
  {
    ensureNavButtonView();
    mNavButtonView.setOnClickListener(paramOnClickListener);
  }
  
  public void setOnMenuItemClickListener(f paramF)
  {
    mMenuItemClickListener = paramF;
  }
  
  public void setOverflowIcon(Drawable paramDrawable)
  {
    ensureMenu();
    mMenuView.setOverflowIcon(paramDrawable);
  }
  
  public void setPopupTheme(int paramInt)
  {
    if (mPopupTheme != paramInt)
    {
      mPopupTheme = paramInt;
      if (paramInt == 0)
      {
        mPopupContext = getContext();
        return;
      }
      mPopupContext = new ContextThemeWrapper(getContext(), paramInt);
    }
  }
  
  public void setSubtitle(int paramInt)
  {
    setSubtitle(getContext().getText(paramInt));
  }
  
  public void setSubtitle(CharSequence paramCharSequence)
  {
    if (!TextUtils.isEmpty(paramCharSequence))
    {
      if (mSubtitleTextView == null)
      {
        localObject = getContext();
        mSubtitleTextView = new AppCompatTextView((Context)localObject);
        mSubtitleTextView.setSingleLine();
        mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
        int i = mSubtitleTextAppearance;
        if (i != 0) {
          mSubtitleTextView.setTextAppearance((Context)localObject, i);
        }
        i = mSubtitleTextColor;
        if (i != 0) {
          mSubtitleTextView.setTextColor(i);
        }
      }
      if (!isChildOrHidden(mSubtitleTextView)) {
        addSystemView(mSubtitleTextView, true);
      }
    }
    else
    {
      localObject = mSubtitleTextView;
      if ((localObject != null) && (isChildOrHidden((View)localObject)))
      {
        removeView(mSubtitleTextView);
        mHiddenViews.remove(mSubtitleTextView);
      }
    }
    Object localObject = mSubtitleTextView;
    if (localObject != null) {
      ((TextView)localObject).setText(paramCharSequence);
    }
    mSubtitleText = paramCharSequence;
  }
  
  public void setSubtitleTextAppearance(Context paramContext, int paramInt)
  {
    mSubtitleTextAppearance = paramInt;
    TextView localTextView = mSubtitleTextView;
    if (localTextView != null) {
      localTextView.setTextAppearance(paramContext, paramInt);
    }
  }
  
  public void setSubtitleTextColor(int paramInt)
  {
    mSubtitleTextColor = paramInt;
    TextView localTextView = mSubtitleTextView;
    if (localTextView != null) {
      localTextView.setTextColor(paramInt);
    }
  }
  
  public void setTitle(int paramInt)
  {
    setTitle(getContext().getText(paramInt));
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    if (!TextUtils.isEmpty(paramCharSequence))
    {
      if (mTitleTextView == null)
      {
        localObject = getContext();
        mTitleTextView = new AppCompatTextView((Context)localObject);
        mTitleTextView.setSingleLine();
        mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
        int i = mTitleTextAppearance;
        if (i != 0) {
          mTitleTextView.setTextAppearance((Context)localObject, i);
        }
        i = mTitleTextColor;
        if (i != 0) {
          mTitleTextView.setTextColor(i);
        }
      }
      if (!isChildOrHidden(mTitleTextView)) {
        addSystemView(mTitleTextView, true);
      }
    }
    else
    {
      localObject = mTitleTextView;
      if ((localObject != null) && (isChildOrHidden((View)localObject)))
      {
        removeView(mTitleTextView);
        mHiddenViews.remove(mTitleTextView);
      }
    }
    Object localObject = mTitleTextView;
    if (localObject != null) {
      ((TextView)localObject).setText(paramCharSequence);
    }
    mTitleText = paramCharSequence;
  }
  
  public void setTitleMarginBottom(int paramInt)
  {
    mTitleMarginBottom = paramInt;
    requestLayout();
  }
  
  public void setTitleMarginEnd(int paramInt)
  {
    mTitleMarginEnd = paramInt;
    requestLayout();
  }
  
  public void setTitleMarginStart(int paramInt)
  {
    mTitleMarginStart = paramInt;
    requestLayout();
  }
  
  public void setTitleMarginTop(int paramInt)
  {
    mTitleMarginTop = paramInt;
    requestLayout();
  }
  
  public void setTitleTextAppearance(Context paramContext, int paramInt)
  {
    mTitleTextAppearance = paramInt;
    TextView localTextView = mTitleTextView;
    if (localTextView != null) {
      localTextView.setTextAppearance(paramContext, paramInt);
    }
  }
  
  public void setTitleTextColor(int paramInt)
  {
    mTitleTextColor = paramInt;
    TextView localTextView = mTitleTextView;
    if (localTextView != null) {
      localTextView.setTextColor(paramInt);
    }
  }
  
  public boolean showOverflowMenu()
  {
    ActionMenuView localActionMenuView = mMenuView;
    return (localActionMenuView != null) && (localActionMenuView.showOverflowMenu());
  }
  
  class a
    implements ActionMenuView.e
  {
    a() {}
    
    public boolean onMenuItemClick(MenuItem paramMenuItem)
    {
      Toolbar.f localF = mMenuItemClickListener;
      if (localF != null) {
        return localF.onMenuItemClick(paramMenuItem);
      }
      return false;
    }
  }
  
  class b
    implements Runnable
  {
    b() {}
    
    public void run()
    {
      showOverflowMenu();
    }
  }
  
  class c
    implements View.OnClickListener
  {
    c() {}
    
    public void onClick(View paramView)
    {
      collapseActionView();
    }
  }
  
  private class d
    implements l
  {
    MenuItemImpl mCurrentExpandedItem;
    f mMenu;
    
    d() {}
    
    public void a(f paramF, boolean paramBoolean) {}
    
    public boolean a(SubMenuBuilder paramSubMenuBuilder)
    {
      return false;
    }
    
    public boolean collapseItemActionView(f paramF, MenuItemImpl paramMenuItemImpl)
    {
      paramF = mExpandedActionView;
      if ((paramF instanceof CollapsibleActionView)) {
        ((CollapsibleActionView)paramF).onActionViewCollapsed();
      }
      paramF = Toolbar.this;
      paramF.removeView(mExpandedActionView);
      paramF = Toolbar.this;
      paramF.removeView(mCollapseButtonView);
      paramF = Toolbar.this;
      mExpandedActionView = null;
      paramF.addChildrenForExpandedActionView();
      mCurrentExpandedItem = null;
      requestLayout();
      paramMenuItemImpl.setActionViewExpanded(false);
      return true;
    }
    
    public boolean expandItemActionView(f paramF, MenuItemImpl paramMenuItemImpl)
    {
      ensureCollapseButtonView();
      paramF = mCollapseButtonView.getParent();
      Toolbar localToolbar = Toolbar.this;
      if (paramF != localToolbar)
      {
        if ((paramF instanceof ViewGroup)) {
          ((ViewGroup)paramF).removeView(mCollapseButtonView);
        }
        paramF = Toolbar.this;
        paramF.addView(mCollapseButtonView);
      }
      mExpandedActionView = paramMenuItemImpl.getActionView();
      mCurrentExpandedItem = paramMenuItemImpl;
      paramF = mExpandedActionView.getParent();
      localToolbar = Toolbar.this;
      if (paramF != localToolbar)
      {
        if ((paramF instanceof ViewGroup)) {
          ((ViewGroup)paramF).removeView(mExpandedActionView);
        }
        paramF = generateDefaultLayoutParams();
        localToolbar = Toolbar.this;
        gravity = (0x800003 | mButtonGravity & 0x70);
        mViewType = 2;
        mExpandedActionView.setLayoutParams(paramF);
        paramF = Toolbar.this;
        paramF.addView(mExpandedActionView);
      }
      removeChildrenForExpandedActionView();
      requestLayout();
      paramMenuItemImpl.setActionViewExpanded(true);
      paramF = mExpandedActionView;
      if ((paramF instanceof CollapsibleActionView)) {
        ((CollapsibleActionView)paramF).onActionViewExpanded();
      }
      return true;
    }
    
    public boolean flagActionItems()
    {
      return false;
    }
    
    public int getId()
    {
      return 0;
    }
    
    public void initForMenu(Context paramContext, f paramF)
    {
      paramContext = mMenu;
      if (paramContext != null)
      {
        MenuItemImpl localMenuItemImpl = mCurrentExpandedItem;
        if (localMenuItemImpl != null) {
          paramContext.collapseItemActionView(localMenuItemImpl);
        }
      }
      mMenu = paramF;
    }
    
    public void onRestoreInstanceState(Parcelable paramParcelable) {}
    
    public Parcelable onSaveInstanceState()
    {
      return null;
    }
    
    public void updateMenuView(boolean paramBoolean)
    {
      if (mCurrentExpandedItem != null)
      {
        int k = 0;
        f localF = mMenu;
        int j = k;
        if (localF != null)
        {
          int m = localF.size();
          int i = 0;
          for (;;)
          {
            j = k;
            if (i >= m) {
              break;
            }
            if (mMenu.getItem(i) == mCurrentExpandedItem)
            {
              j = 1;
              break;
            }
            i += 1;
          }
        }
        if (j == 0) {
          collapseItemActionView(mMenu, mCurrentExpandedItem);
        }
      }
    }
  }
  
  public static class e
    extends ActionBar.LayoutParams
  {
    int mViewType = 0;
    
    public e(int paramInt1, int paramInt2)
    {
      super(paramInt2);
      gravity = 8388627;
    }
    
    public e(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public e(ActionBar.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public e(e paramE)
    {
      super();
      mViewType = mViewType;
    }
    
    public e(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public e(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
      copyMarginsFromCompat(paramMarginLayoutParams);
    }
    
    void copyMarginsFromCompat(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      leftMargin = leftMargin;
      topMargin = topMargin;
      rightMargin = rightMargin;
      bottomMargin = bottomMargin;
    }
  }
  
  public static abstract interface f
  {
    public abstract boolean onMenuItemClick(MenuItem paramMenuItem);
  }
  
  public static class g
    extends Artist
  {
    public static final Parcelable.Creator<g> CREATOR = new a();
    int expandedMenuItemId;
    boolean isOverflowOpen;
    
    public g(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      expandedMenuItemId = paramParcel.readInt();
      boolean bool;
      if (paramParcel.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      }
      isOverflowOpen = bool;
    }
    
    public g(Parcelable paramParcelable)
    {
      super();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
    
    static final class a
      implements Parcelable.ClassLoaderCreator<Toolbar.g>
    {
      a() {}
      
      public Toolbar.g createFromParcel(Parcel paramParcel)
      {
        return new Toolbar.g(paramParcel, null);
      }
      
      public Toolbar.g createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
      {
        return new Toolbar.g(paramParcel, paramClassLoader);
      }
      
      public Toolbar.g[] newArray(int paramInt)
      {
        return new Toolbar.g[paramInt];
      }
    }
  }
}
