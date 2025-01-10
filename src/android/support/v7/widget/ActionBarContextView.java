package android.support.v7.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.menu.f;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.org.v4.content.R.attr;
import org.org.v4.content.R.id;
import org.org.v4.content.R.layout;
import org.org.v4.content.R.styleable;
import org.org.v4.view.ActionMode;

public class ActionBarContextView
  extends AbsActionBarView
{
  private View mClose;
  private int mCloseItemLayout;
  private View mCustomView;
  private CharSequence mSubtitle;
  private int mSubtitleStyleRes;
  private TextView mSubtitleView;
  private CharSequence mTitle;
  private LinearLayout mTitleLayout;
  private boolean mTitleOptional;
  private int mTitleStyleRes;
  private TextView mTitleView;
  
  public ActionBarContextView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ActionBarContextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.actionModeStyle);
  }
  
  public ActionBarContextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.ActionMode, paramInt, 0);
    ViewCompat.setBackgroundDrawable(this, paramContext.getDrawable(R.styleable.ActionMode_background));
    mTitleStyleRes = paramContext.getResourceId(R.styleable.ActionMode_titleTextStyle, 0);
    mSubtitleStyleRes = paramContext.getResourceId(R.styleable.ActionMode_subtitleTextStyle, 0);
    mContentHeight = paramContext.getLayoutDimension(R.styleable.ActionMode_height, 0);
    mCloseItemLayout = paramContext.getResourceId(R.styleable.ActionMode_closeItemLayout, R.layout.abc_action_mode_close_item_material);
    paramContext.recycle();
  }
  
  private void initTitle()
  {
    if (mTitleLayout == null)
    {
      LayoutInflater.from(getContext()).inflate(R.layout.abc_action_bar_title_item, this);
      mTitleLayout = ((LinearLayout)getChildAt(getChildCount() - 1));
      mTitleView = ((TextView)mTitleLayout.findViewById(R.id.action_bar_title));
      mSubtitleView = ((TextView)mTitleLayout.findViewById(R.id.action_bar_subtitle));
      if (mTitleStyleRes != 0) {
        mTitleView.setTextAppearance(getContext(), mTitleStyleRes);
      }
      if (mSubtitleStyleRes != 0) {
        mSubtitleView.setTextAppearance(getContext(), mSubtitleStyleRes);
      }
    }
    mTitleView.setText(mTitle);
    mSubtitleView.setText(mSubtitle);
    boolean bool2 = TextUtils.isEmpty(mTitle);
    boolean bool1 = TextUtils.isEmpty(mSubtitle) ^ true;
    Object localObject = mSubtitleView;
    int j = 0;
    if (bool1) {
      i = 0;
    } else {
      i = 8;
    }
    ((View)localObject).setVisibility(i);
    localObject = mTitleLayout;
    int i = j;
    if (!(bool2 ^ true)) {
      if (bool1) {
        i = j;
      } else {
        i = 8;
      }
    }
    ((View)localObject).setVisibility(i);
    if (mTitleLayout.getParent() == null) {
      addView(mTitleLayout);
    }
  }
  
  public void closeMode()
  {
    if (mClose == null) {
      killMode();
    }
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new ViewGroup.MarginLayoutParams(-1, -2);
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new ViewGroup.MarginLayoutParams(getContext(), paramAttributeSet);
  }
  
  public CharSequence getSubtitle()
  {
    return mSubtitle;
  }
  
  public CharSequence getTitle()
  {
    return mTitle;
  }
  
  public void initForMode(final ActionMode paramActionMode)
  {
    Object localObject = mClose;
    if (localObject == null)
    {
      mClose = LayoutInflater.from(getContext()).inflate(mCloseItemLayout, this, false);
      addView(mClose);
    }
    else if (((View)localObject).getParent() == null)
    {
      addView(mClose);
    }
    mClose.findViewById(R.id.action_mode_close_button).setOnClickListener(new a(paramActionMode));
    paramActionMode = (f)paramActionMode.getMenu();
    localObject = mActionMenuPresenter;
    if (localObject != null) {
      ((ActionMenuPresenter)localObject).dismissPopupMenus();
    }
    mActionMenuPresenter = new ActionMenuPresenter(getContext());
    mActionMenuPresenter.setReserveOverflow(true);
    localObject = new ViewGroup.LayoutParams(-2, -1);
    paramActionMode.a(mActionMenuPresenter, mPopupContext);
    mMenuView = ((ActionMenuView)mActionMenuPresenter.getMenuView(this));
    ViewCompat.setBackgroundDrawable(mMenuView, null);
    addView(mMenuView, (ViewGroup.LayoutParams)localObject);
  }
  
  public boolean isTitleOptional()
  {
    return mTitleOptional;
  }
  
  public void killMode()
  {
    removeAllViews();
    mCustomView = null;
    mMenuView = null;
  }
  
  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    ActionMenuPresenter localActionMenuPresenter = mActionMenuPresenter;
    if (localActionMenuPresenter != null)
    {
      localActionMenuPresenter.hideOverflowMenu();
      mActionMenuPresenter.hideSubMenus();
    }
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if (paramAccessibilityEvent.getEventType() == 32)
    {
      paramAccessibilityEvent.setSource(this);
      paramAccessibilityEvent.setClassName(getClass().getName());
      paramAccessibilityEvent.setPackageName(getContext().getPackageName());
      paramAccessibilityEvent.setContentDescription(mTitle);
      return;
    }
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramBoolean = ViewUtils.isLayoutRtl(this);
    int i;
    if (paramBoolean) {
      i = paramInt3 - paramInt1 - getPaddingRight();
    } else {
      i = getPaddingLeft();
    }
    int j = getPaddingTop();
    int k = paramInt4 - paramInt2 - getPaddingTop() - getPaddingBottom();
    Object localObject = mClose;
    if ((localObject != null) && (((View)localObject).getVisibility() != 8))
    {
      localObject = (ViewGroup.MarginLayoutParams)mClose.getLayoutParams();
      if (paramBoolean) {
        paramInt2 = rightMargin;
      } else {
        paramInt2 = leftMargin;
      }
      if (paramBoolean) {
        paramInt4 = leftMargin;
      } else {
        paramInt4 = rightMargin;
      }
      paramInt2 = AbsActionBarView.next(i, paramInt2, paramBoolean);
      paramInt2 = AbsActionBarView.next(paramInt2 + positionChild(mClose, paramInt2, j, k, paramBoolean), paramInt4, paramBoolean);
    }
    else
    {
      paramInt2 = i;
    }
    localObject = mTitleLayout;
    paramInt4 = paramInt2;
    if (localObject != null)
    {
      paramInt4 = paramInt2;
      if (mCustomView == null)
      {
        paramInt4 = paramInt2;
        if (((View)localObject).getVisibility() != 8) {
          paramInt4 = paramInt2 + positionChild(mTitleLayout, paramInt2, j, k, paramBoolean);
        }
      }
    }
    localObject = mCustomView;
    if (localObject != null) {
      positionChild((View)localObject, paramInt4, j, k, paramBoolean);
    }
    if (paramBoolean) {
      paramInt1 = getPaddingLeft();
    } else {
      paramInt1 = paramInt3 - paramInt1 - getPaddingRight();
    }
    localObject = mMenuView;
    if (localObject != null) {
      positionChild((View)localObject, paramInt1, j, k, paramBoolean ^ true);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt1);
    int k = 1073741824;
    if (i == 1073741824)
    {
      if (View.MeasureSpec.getMode(paramInt2) != 0)
      {
        int i1 = View.MeasureSpec.getSize(paramInt1);
        i = mContentHeight;
        if (i <= 0) {
          i = View.MeasureSpec.getSize(paramInt2);
        }
        int i2 = getPaddingTop() + getPaddingBottom();
        paramInt1 = i1 - getPaddingLeft() - getPaddingRight();
        int m = i - i2;
        int j = View.MeasureSpec.makeMeasureSpec(m, Integer.MIN_VALUE);
        localObject = mClose;
        int n = 0;
        paramInt2 = paramInt1;
        if (localObject != null)
        {
          paramInt1 = measureChildView((View)localObject, paramInt1, j, 0);
          localObject = (ViewGroup.MarginLayoutParams)mClose.getLayoutParams();
          paramInt2 = paramInt1 - (leftMargin + rightMargin);
        }
        localObject = mMenuView;
        paramInt1 = paramInt2;
        if (localObject != null)
        {
          paramInt1 = paramInt2;
          if (((View)localObject).getParent() == this) {
            paramInt1 = measureChildView(mMenuView, paramInt2, j, 0);
          }
        }
        localObject = mTitleLayout;
        paramInt2 = paramInt1;
        if (localObject != null)
        {
          paramInt2 = paramInt1;
          if (mCustomView == null) {
            if (mTitleOptional)
            {
              paramInt2 = View.MeasureSpec.makeMeasureSpec(0, 0);
              mTitleLayout.measure(paramInt2, j);
              int i3 = mTitleLayout.getMeasuredWidth();
              if (i3 <= paramInt1) {
                j = 1;
              } else {
                j = 0;
              }
              paramInt2 = paramInt1;
              if (j != 0) {
                paramInt2 = paramInt1 - i3;
              }
              localObject = mTitleLayout;
              if (j != 0) {
                paramInt1 = n;
              } else {
                paramInt1 = 8;
              }
              ((View)localObject).setVisibility(paramInt1);
            }
            else
            {
              paramInt2 = measureChildView((View)localObject, paramInt1, j, 0);
            }
          }
        }
        localObject = mCustomView;
        if (localObject != null)
        {
          localObject = ((View)localObject).getLayoutParams();
          if (width != -2) {
            paramInt1 = 1073741824;
          } else {
            paramInt1 = Integer.MIN_VALUE;
          }
          n = width;
          j = paramInt2;
          if (n >= 0) {
            j = Math.min(n, paramInt2);
          }
          if (height != -2) {
            paramInt2 = k;
          } else {
            paramInt2 = Integer.MIN_VALUE;
          }
          n = height;
          k = m;
          if (n >= 0) {
            k = Math.min(n, m);
          }
          mCustomView.measure(View.MeasureSpec.makeMeasureSpec(j, paramInt1), View.MeasureSpec.makeMeasureSpec(k, paramInt2));
        }
        if (mContentHeight <= 0)
        {
          paramInt2 = 0;
          k = getChildCount();
          paramInt1 = 0;
          while (paramInt1 < k)
          {
            j = getChildAt(paramInt1).getMeasuredHeight() + i2;
            i = paramInt2;
            if (j > paramInt2) {
              i = j;
            }
            paramInt1 += 1;
            paramInt2 = i;
          }
          setMeasuredDimension(i1, paramInt2);
          return;
        }
        setMeasuredDimension(i1, i);
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(getClass().getSimpleName());
      ((StringBuilder)localObject).append(" can only be used ");
      ((StringBuilder)localObject).append("with android:layout_height=\"wrap_content\"");
      throw new IllegalStateException(((StringBuilder)localObject).toString());
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(getClass().getSimpleName());
    ((StringBuilder)localObject).append(" can only be used ");
    ((StringBuilder)localObject).append("with android:layout_width=\"match_parent\" (or fill_parent)");
    localObject = new IllegalStateException(((StringBuilder)localObject).toString());
    throw ((Throwable)localObject);
  }
  
  public void setContentHeight(int paramInt)
  {
    mContentHeight = paramInt;
  }
  
  public void setCustomView(View paramView)
  {
    Object localObject = mCustomView;
    if (localObject != null) {
      removeView((View)localObject);
    }
    mCustomView = paramView;
    if (paramView != null)
    {
      localObject = mTitleLayout;
      if (localObject != null)
      {
        removeView((View)localObject);
        mTitleLayout = null;
      }
    }
    if (paramView != null) {
      addView(paramView);
    }
    requestLayout();
  }
  
  public void setSubtitle(CharSequence paramCharSequence)
  {
    mSubtitle = paramCharSequence;
    initTitle();
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    mTitle = paramCharSequence;
    initTitle();
  }
  
  public void setTitleOptional(boolean paramBoolean)
  {
    if (paramBoolean != mTitleOptional) {
      requestLayout();
    }
    mTitleOptional = paramBoolean;
  }
  
  public boolean shouldDelayChildPressedState()
  {
    return false;
  }
  
  public boolean showOverflowMenu()
  {
    ActionMenuPresenter localActionMenuPresenter = mActionMenuPresenter;
    if (localActionMenuPresenter != null) {
      return localActionMenuPresenter.showOverflowMenu();
    }
    return false;
  }
  
  class a
    implements View.OnClickListener
  {
    a(ActionMode paramActionMode) {}
    
    public void onClick(View paramView)
    {
      paramActionMode.finish();
    }
  }
}
