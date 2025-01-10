package com.joanzapata.material.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v7.widget.ListViewCompat;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.AbsSavedState;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;
import apps.muzei.api.R.attr;
import apps.muzei.api.R.style;
import apps.muzei.api.R.styleable;
import apps.muzei.api.asm.ClassVisitor;
import apps.muzei.api.internal.ThemeUtil;
import apps.muzei.api.util.GifDrawable;
import apps.muzei.api.util.SmoothProgressDrawable;

public class Spinner
  extends FrameLayout
  implements ClassVisitor
{
  private GifDrawable a;
  private boolean header;
  private SpinnerAdapter mAdapter;
  private j mDataSetObserver = new j(null);
  private boolean mDisableChildrenWhenDisabled;
  private SmoothProgressDrawable mDividerDrawable;
  private int mDividerHeight;
  private int mDividerPadding;
  private int mDropDownWidth;
  private int mGravity;
  private boolean mIsRtl;
  private TextView mLabelView;
  private int mMinHeight;
  private int mMinWidth;
  private f mOnItemClickListener;
  private g mOnItemSelectedListener;
  private e mPopup;
  private h mRecycler = new h(null);
  private boolean mReverse;
  private int mSelectedPosition;
  private d mTempAdapter;
  private Rect mTempRect = new Rect();
  private int mTextColor;
  private int mTextSize;
  
  public Spinner(Context paramContext)
  {
    super(paramContext, null, R.attr.listPopupWindowStyle);
  }
  
  public Spinner(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet, R.attr.listPopupWindowStyle);
  }
  
  public Spinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private void draw()
  {
    GifDrawable localGifDrawable = a;
    if (localGifDrawable != null) {
      localGifDrawable.start(GifDrawable.y, true);
    }
  }
  
  private int getArrowDrawableWidth()
  {
    if (a != null) {
      return mTextSize + mTextColor * 2;
    }
    return 0;
  }
  
  private int getDividerDrawableHeight()
  {
    int i = mDividerHeight;
    if (i > 0) {
      return i + mDividerPadding;
    }
    return 0;
  }
  
  private int getHeight(int paramInt1, int paramInt2)
  {
    if (paramInt2 != -2)
    {
      if (paramInt2 != -1) {
        return View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
      }
      if (paramInt1 > 0) {
        return View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824);
      }
      return View.MeasureSpec.makeMeasureSpec(0, 0);
    }
    if (paramInt1 > 0) {
      return View.MeasureSpec.makeMeasureSpec(paramInt1, Integer.MIN_VALUE);
    }
    return View.MeasureSpec.makeMeasureSpec(0, 0);
  }
  
  private android.widget.TextView getLabelView()
  {
    if (mLabelView == null)
    {
      mLabelView = new TextView(getContext());
      if (Build.VERSION.SDK_INT >= 17)
      {
        TextView localTextView = mLabelView;
        int i;
        if (mIsRtl) {
          i = 4;
        } else {
          i = 3;
        }
        localTextView.setTextDirection(i);
      }
      mLabelView.setSingleLine(true);
      mLabelView.setDuplicateParentStateEnabled(true);
    }
    return mLabelView;
  }
  
  private void init()
  {
    if (mAdapter == null) {
      return;
    }
    if (mLabelView == null)
    {
      removeAllViews();
    }
    else
    {
      i = getChildCount() - 1;
      while (i > 0)
      {
        removeViewAt(i);
        i -= 1;
      }
    }
    int i = mAdapter.getItemViewType(mSelectedPosition);
    View localView = mAdapter.getView(mSelectedPosition, mRecycler.get(i), this);
    localView.setFocusable(false);
    localView.setClickable(false);
    if (localView.getParent() != null) {
      ((ViewGroup)localView.getParent()).removeView(localView);
    }
    super.addView(localView);
    mRecycler.put(i, localView);
  }
  
  private int measureContentWidth(SpinnerAdapter paramSpinnerAdapter, Drawable paramDrawable)
  {
    if (paramSpinnerAdapter == null) {
      return 0;
    }
    int i = 0;
    Object localObject = null;
    int k = 0;
    int i1 = View.MeasureSpec.makeMeasureSpec(0, 0);
    int i2 = View.MeasureSpec.makeMeasureSpec(0, 0);
    int j = Math.max(0, getSelectedItemPosition());
    int i3 = Math.min(paramSpinnerAdapter.getCount(), j + 15);
    j = Math.max(0, j - (15 - (i3 - j)));
    while (j < i3)
    {
      int n = paramSpinnerAdapter.getItemViewType(j);
      int m = k;
      if (n != k)
      {
        m = n;
        localObject = null;
      }
      View localView = paramSpinnerAdapter.getView(j, (View)localObject, this);
      localObject = localView;
      if (localView.getLayoutParams() == null) {
        localView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
      }
      localView.measure(i1, i2);
      i = Math.max(i, localView.getMeasuredWidth());
      j += 1;
      k = m;
    }
    j = i;
    if (paramDrawable != null)
    {
      paramDrawable.getPadding(mTempRect);
      paramSpinnerAdapter = mTempRect;
      j = i + (left + right);
    }
    return j;
  }
  
  private void onDataChanged()
  {
    int i = mSelectedPosition;
    if (i == -1)
    {
      setSelection(0);
      return;
    }
    if (i < mAdapter.getCount())
    {
      init();
      return;
    }
    setSelection(mAdapter.getCount() - 1);
  }
  
  private void show()
  {
    if (!mPopup.isShowing())
    {
      mPopup.show();
      ListView localListView = mPopup.getListView();
      if (localListView != null)
      {
        if (Build.VERSION.SDK_INT >= 11) {
          localListView.setChoiceMode(1);
        }
        localListView.setSelection(getSelectedItemPosition());
        if ((a != null) && (header)) {
          localListView.getViewTreeObserver().addOnPreDrawListener(new Spinner.b(this, localListView));
        }
      }
    }
  }
  
  protected void applyStyle(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super.applyStyle(paramContext, paramAttributeSet, paramInt1, paramInt2);
    removeAllViews();
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.Spinner, paramInt1, paramInt2);
    int i = -1;
    Object localObject2 = null;
    Object localObject3 = null;
    boolean bool1 = true;
    paramInt2 = -1;
    Object localObject1 = null;
    Object localObject4 = null;
    int k = -1;
    int j = 0;
    int i2 = localTypedArray.getIndexCount();
    while (j < i2)
    {
      int i3 = localTypedArray.getIndex(j);
      int m;
      Object localObject5;
      Object localObject6;
      boolean bool2;
      Object localObject7;
      int n;
      int i1;
      if (i3 == R.styleable.Spinner_spn_labelEnable)
      {
        mReverse = localTypedArray.getBoolean(i3, false);
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_labelPadding)
      {
        getLabelView().setPadding(0, 0, 0, localTypedArray.getDimensionPixelSize(i3, 0));
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_labelTextSize)
      {
        n = localTypedArray.getDimensionPixelSize(i3, 0);
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_labelTextColor)
      {
        localObject7 = localTypedArray.getColorStateList(i3);
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_labelTextAppearance)
      {
        getLabelView().setTextAppearance(paramContext, localTypedArray.getResourceId(i3, 0));
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_labelEllipsize)
      {
        m = localTypedArray.getInteger(i3, 0);
        if (m != 1)
        {
          if (m != 2)
          {
            if (m != 3)
            {
              if (m != 4) {
                getLabelView().setEllipsize(TextUtils.TruncateAt.END);
              } else {
                getLabelView().setEllipsize(TextUtils.TruncateAt.MARQUEE);
              }
            }
            else {
              getLabelView().setEllipsize(TextUtils.TruncateAt.END);
            }
          }
          else {
            getLabelView().setEllipsize(TextUtils.TruncateAt.MIDDLE);
          }
        }
        else {
          getLabelView().setEllipsize(TextUtils.TruncateAt.START);
        }
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_label)
      {
        getLabelView().setText(localTypedArray.getString(i3));
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_android_gravity)
      {
        mGravity = localTypedArray.getInt(i3, 0);
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_android_minWidth)
      {
        setMinimumWidth(localTypedArray.getDimensionPixelOffset(i3, 0));
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_android_minHeight)
      {
        setMinimumHeight(localTypedArray.getDimensionPixelOffset(i3, 0));
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_android_dropDownWidth)
      {
        mDropDownWidth = localTypedArray.getLayoutDimension(i3, -2);
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_android_popupBackground)
      {
        mPopup.setBackgroundDrawable(localTypedArray.getDrawable(i3));
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_android_prompt)
      {
        mPopup.setPromptText(localTypedArray.getString(i3));
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_popupItemAnimation)
      {
        mPopup.setHeight(localTypedArray.getResourceId(i3, 0));
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_popupItemAnimOffset)
      {
        mPopup.setMinute(localTypedArray.getInteger(i3, 0));
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_disableChildrenWhenDisabled)
      {
        mDisableChildrenWhenDisabled = localTypedArray.getBoolean(i3, false);
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_arrowSwitchMode)
      {
        header = localTypedArray.getBoolean(i3, false);
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_arrowAnimDuration)
      {
        m = localTypedArray.getInteger(i3, 0);
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_arrowSize)
      {
        mTextSize = localTypedArray.getDimensionPixelSize(i3, 0);
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_arrowPadding)
      {
        mTextColor = localTypedArray.getDimensionPixelSize(i3, 0);
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_arrowColor)
      {
        localObject5 = localTypedArray.getColorStateList(i3);
        m = i;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_arrowInterpolator)
      {
        localObject6 = AnimationUtils.loadInterpolator(paramContext, localTypedArray.getResourceId(i3, 0));
        m = i;
        localObject5 = localObject2;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_arrowAnimClockwise)
      {
        bool2 = localTypedArray.getBoolean(i3, true);
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_dividerHeight)
      {
        mDividerHeight = localTypedArray.getDimensionPixelOffset(i3, 0);
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_dividerPadding)
      {
        mDividerPadding = localTypedArray.getDimensionPixelOffset(i3, 0);
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
      }
      else if (i3 == R.styleable.Spinner_spn_dividerAnimDuration)
      {
        i1 = localTypedArray.getInteger(i3, 0);
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
      }
      else
      {
        m = i;
        localObject5 = localObject2;
        localObject6 = localObject3;
        bool2 = bool1;
        localObject7 = localObject4;
        n = k;
        i1 = paramInt2;
        if (i3 == R.styleable.Spinner_spn_dividerColor)
        {
          localObject1 = localTypedArray.getColorStateList(i3);
          i1 = paramInt2;
          n = k;
          localObject7 = localObject4;
          bool2 = bool1;
          localObject6 = localObject3;
          localObject5 = localObject2;
          m = i;
        }
      }
      j += 1;
      i = m;
      localObject2 = localObject5;
      localObject3 = localObject6;
      bool1 = bool2;
      localObject4 = localObject7;
      k = n;
      paramInt2 = i1;
    }
    localTypedArray.recycle();
    if (localObject4 != null) {
      getLabelView().setTextColor((ColorStateList)localObject4);
    }
    if (k >= 0) {
      getLabelView().setTextSize(0, k);
    }
    if (mReverse) {
      addView(getLabelView(), 0, new ViewGroup.LayoutParams(-2, -2));
    }
    j = mTextSize;
    if (j > 0)
    {
      localObject4 = a;
      if (localObject4 == null)
      {
        localObject4 = localObject2;
        if (localObject2 == null) {
          localObject4 = ColorStateList.valueOf(ThemeUtil.colorControlNormal(paramContext, -16777216));
        }
        j = i;
        if (i < 0) {
          j = 0;
        }
        a = new GifDrawable(GifDrawable.y, mTextSize, (ColorStateList)localObject4, j, localObject3, bool1);
        a.setCallback(this);
      }
      else
      {
        ((GifDrawable)localObject4).start(j);
        a.start(bool1);
        if (localObject2 != null) {
          a.setColor((ColorStateList)localObject2);
        }
        if (i >= 0) {
          a.recycle(i);
        }
        if (localObject3 != null) {
          a.recycle(localObject3);
        }
      }
    }
    else
    {
      localObject2 = a;
      if (localObject2 != null)
      {
        ((Drawable)localObject2).setCallback(null);
        a = null;
      }
    }
    i = mDividerHeight;
    if (i > 0)
    {
      localObject2 = mDividerDrawable;
      if (localObject2 == null)
      {
        i = paramInt2;
        if (paramInt2 < 0) {
          i = 0;
        }
        localObject2 = localObject1;
        if (localObject1 == null)
        {
          localObject1 = new int[] { -16842919 };
          localObject2 = new int[] { 16842919, 16842910 };
          paramInt2 = ThemeUtil.colorControlNormal(paramContext, -16777216);
          j = ThemeUtil.colorControlActivated(paramContext, -16777216);
          localObject2 = new ColorStateList(new int[][] { localObject1, localObject2 }, new int[] { paramInt2, j });
        }
        mDividerDrawable = new SmoothProgressDrawable(mDividerHeight, (ColorStateList)localObject2, i);
        mDividerDrawable.setCallback(this);
      }
      else
      {
        ((SmoothProgressDrawable)localObject2).setDividerHeight(i);
        if (localObject1 != null) {
          mDividerDrawable.setColor((ColorStateList)localObject1);
        }
        if (paramInt2 >= 0) {
          mDividerDrawable.setColor(paramInt2);
        }
      }
    }
    else
    {
      localObject1 = mDividerDrawable;
      if (localObject1 != null)
      {
        ((Drawable)localObject1).setCallback(null);
        mDividerDrawable = null;
      }
    }
    localObject1 = mTempAdapter;
    if (localObject1 != null)
    {
      mPopup.setAdapter((ListAdapter)localObject1);
      mTempAdapter = null;
    }
    localObject1 = mAdapter;
    if (localObject1 != null) {
      setAdapter((SpinnerAdapter)localObject1);
    }
    if (isInEditMode())
    {
      paramContext = new TextView(paramContext, paramAttributeSet, paramInt1);
      paramContext.setText("Item 1");
      super.addView(paramContext);
    }
    requestLayout();
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    Object localObject = mDividerDrawable;
    if (localObject != null) {
      ((SmoothProgressDrawable)localObject).draw(paramCanvas);
    }
    localObject = a;
    if (localObject != null) {
      ((GifDrawable)localObject).draw(paramCanvas);
    }
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    Object localObject = a;
    if (localObject != null) {
      ((Drawable)localObject).setState(getDrawableState());
    }
    localObject = mDividerDrawable;
    if (localObject != null) {
      ((Drawable)localObject).setState(getDrawableState());
    }
  }
  
  public SpinnerAdapter getAdapter()
  {
    return mAdapter;
  }
  
  public int getBaseline()
  {
    View localView = getSelectedView();
    if (localView != null)
    {
      int k = localView.getBaseline();
      if (k < 0) {
        return -1;
      }
      int j = getPaddingTop();
      int i = j;
      TextView localTextView = mLabelView;
      if (localTextView != null) {
        i = j + localTextView.getMeasuredHeight();
      }
      j = getMeasuredHeight() - i - getPaddingBottom() - getDividerDrawableHeight();
      int m = mGravity & 0x70;
      if (m != 48)
      {
        if (m != 80) {
          return (j - localView.getMeasuredHeight()) / 2 + i + k;
        }
        return i + j - localView.getMeasuredHeight() + k;
      }
      return i + k;
    }
    return -1;
  }
  
  public int getDropDownHorizontalOffset()
  {
    return mPopup.getHorizontalOffset();
  }
  
  public int getDropDownVerticalOffset()
  {
    return mPopup.getVerticalOffset();
  }
  
  public int getDropDownWidth()
  {
    return mDropDownWidth;
  }
  
  public Drawable getPopupBackground()
  {
    return mPopup.getBackground();
  }
  
  public Object getSelectedItem()
  {
    SpinnerAdapter localSpinnerAdapter = mAdapter;
    if (localSpinnerAdapter == null) {
      return null;
    }
    return localSpinnerAdapter.getItem(mSelectedPosition);
  }
  
  public int getSelectedItemPosition()
  {
    return mSelectedPosition;
  }
  
  public View getSelectedView()
  {
    View localView = getChildAt(getChildCount() - 1);
    if (localView == mLabelView) {
      return null;
    }
    return localView;
  }
  
  protected void init(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    mReverse = false;
    mDropDownWidth = -2;
    header = false;
    mGravity = 17;
    mDisableChildrenWhenDisabled = false;
    mSelectedPosition = -1;
    mIsRtl = false;
    setWillNotDraw(false);
    mPopup = new e(paramContext, paramAttributeSet, paramInt1, paramInt2);
    mPopup.setModal(true);
    if (isInEditMode()) {
      init(R.style.Material_Widget_Spinner);
    }
    setOnClickListener(new Spinner.a(this));
    super.init(paramContext, paramAttributeSet, paramInt1, paramInt2);
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    e localE = mPopup;
    if ((localE != null) && (localE.isShowing())) {
      mPopup.dismiss();
    }
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    return true;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt3 -= paramInt1;
    int i = paramInt4 - paramInt2;
    paramInt2 = getArrowDrawableWidth();
    if (a != null)
    {
      paramInt4 = getPaddingTop();
      localObject = mLabelView;
      if (localObject == null) {
        paramInt1 = 0;
      } else {
        paramInt1 = ((View)localObject).getMeasuredHeight();
      }
      paramInt1 = paramInt4 + paramInt1;
      paramInt4 = i - getDividerDrawableHeight() - getPaddingBottom();
      if (mIsRtl) {
        a.setBounds(getPaddingLeft(), paramInt1, getPaddingLeft() + paramInt2, paramInt4);
      } else {
        a.setBounds(getWidth() - getPaddingRight() - paramInt2, paramInt1, getWidth() - getPaddingRight(), paramInt4);
      }
    }
    Object localObject = mDividerDrawable;
    if (localObject != null) {
      ((Drawable)localObject).setBounds(getPaddingLeft(), i - mDividerHeight - getPaddingBottom(), paramInt3 - getPaddingRight(), i - getPaddingBottom());
    }
    if (mIsRtl) {
      paramInt1 = getPaddingLeft() + paramInt2;
    } else {
      paramInt1 = getPaddingLeft();
    }
    if (mIsRtl) {
      paramInt4 = paramInt3 - getPaddingRight();
    } else {
      paramInt4 = paramInt3 - getPaddingRight() - paramInt2;
    }
    paramInt3 = getPaddingTop();
    paramInt2 = paramInt3;
    int j = i - getPaddingBottom();
    localObject = mLabelView;
    if (localObject != null)
    {
      if (mIsRtl) {
        ((View)localObject).layout(paramInt4 - ((View)localObject).getMeasuredWidth(), paramInt3, paramInt4, mLabelView.getMeasuredHeight() + paramInt3);
      } else {
        ((View)localObject).layout(paramInt1, paramInt3, ((View)localObject).getMeasuredWidth() + paramInt1, mLabelView.getMeasuredHeight() + paramInt3);
      }
      paramInt2 = paramInt3 + mLabelView.getMeasuredHeight();
    }
    localObject = getSelectedView();
    if (localObject != null)
    {
      i = mGravity & 0x7;
      if (i == 8388611)
      {
        if (mIsRtl) {
          paramInt3 = 5;
        } else {
          paramInt3 = 3;
        }
      }
      else
      {
        paramInt3 = i;
        if (i == 8388613) {
          if (mIsRtl) {
            paramInt3 = 3;
          } else {
            paramInt3 = 5;
          }
        }
      }
      if (paramInt3 != 1)
      {
        if (paramInt3 != 3) {
          if (paramInt3 != 5) {
            paramInt1 = (paramInt4 - paramInt1 - ((View)localObject).getMeasuredWidth()) / 2 + paramInt1;
          } else {
            paramInt1 = paramInt4 - ((View)localObject).getMeasuredWidth();
          }
        }
      }
      else {
        paramInt1 = (paramInt4 - paramInt1 - ((View)localObject).getMeasuredWidth()) / 2 + paramInt1;
      }
      paramInt3 = mGravity & 0x70;
      if (paramInt3 != 16)
      {
        if (paramInt3 != 48) {
          if (paramInt3 != 80) {
            paramInt2 = (j - paramInt2 - ((View)localObject).getMeasuredHeight()) / 2 + paramInt2;
          } else {
            paramInt2 = j - ((View)localObject).getMeasuredHeight();
          }
        }
      }
      else {
        paramInt2 = (j - paramInt2 - ((View)localObject).getMeasuredHeight()) / 2 + paramInt2;
      }
      ((View)localObject).layout(paramInt1, paramInt2, ((View)localObject).getMeasuredWidth() + paramInt1, ((View)localObject).getMeasuredHeight() + paramInt2);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i4 = View.MeasureSpec.getMode(paramInt1);
    int m = View.MeasureSpec.getSize(paramInt1);
    int i3 = View.MeasureSpec.getMode(paramInt2);
    int k = View.MeasureSpec.getSize(paramInt2);
    int i2 = getPaddingLeft() + getPaddingRight() + getArrowDrawableWidth();
    int i1 = getPaddingTop() + getPaddingBottom() + getDividerDrawableHeight();
    paramInt2 = 0;
    int j = 0;
    Object localObject = mLabelView;
    int i = j;
    paramInt1 = paramInt2;
    if (localObject != null)
    {
      i = j;
      paramInt1 = paramInt2;
      if (((View)localObject).getLayoutParams() != null)
      {
        if (i4 == 0) {
          paramInt1 = 0;
        } else {
          paramInt1 = m - i2;
        }
        paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt1, i4);
        paramInt2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        mLabelView.measure(paramInt1, paramInt2);
        paramInt1 = mLabelView.getMeasuredWidth();
        i = mLabelView.getMeasuredHeight();
      }
    }
    paramInt2 = 0;
    j = 0;
    localObject = getSelectedView();
    ViewGroup.LayoutParams localLayoutParams;
    if (localObject != null)
    {
      localLayoutParams = ((View)localObject).getLayoutParams();
      ((View)localObject).measure(getHeight(m - i2, width), getHeight(k - i1 - mLabelView.getMeasuredHeight(), height));
      paramInt2 = ((View)localObject).getMeasuredWidth();
      j = ((View)localObject).getMeasuredHeight();
    }
    int n = Math.max(mMinWidth, Math.max(paramInt1, paramInt2) + i2);
    paramInt1 = n;
    j = Math.max(mMinHeight, j + i + i1);
    paramInt2 = j;
    if (i4 != Integer.MIN_VALUE)
    {
      if (i4 == 1073741824) {
        paramInt1 = m;
      }
    }
    else {
      paramInt1 = Math.min(m, n);
    }
    if (i3 != Integer.MIN_VALUE)
    {
      if (i3 == 1073741824) {
        paramInt2 = k;
      }
    }
    else {
      paramInt2 = Math.min(k, j);
    }
    setMeasuredDimension(paramInt1, paramInt2);
    if (localObject != null)
    {
      localLayoutParams = ((View)localObject).getLayoutParams();
      j = width;
      if (j != -2)
      {
        if (j != -1) {
          paramInt1 = width;
        } else {
          paramInt1 -= i2;
        }
      }
      else {
        paramInt1 = ((View)localObject).getMeasuredWidth();
      }
      j = height;
      if (j != -2)
      {
        if (j != -1) {
          paramInt2 = height;
        } else {
          paramInt2 = paramInt2 - i - i1;
        }
      }
      else {
        paramInt2 = ((View)localObject).getMeasuredHeight();
      }
      if ((((View)localObject).getMeasuredWidth() != paramInt1) || (((View)localObject).getMeasuredHeight() != paramInt2)) {
        ((View)localObject).measure(View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824), View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824));
      }
    }
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (i)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    setSelection(position);
    if (showDropdown)
    {
      paramParcelable = getViewTreeObserver();
      if (paramParcelable != null) {
        paramParcelable.addOnGlobalLayoutListener(new Spinner.c(this));
      }
    }
  }
  
  public void onRtlPropertiesChanged(int paramInt)
  {
    boolean bool = true;
    if (paramInt != 1) {
      bool = false;
    }
    if (mIsRtl != bool)
    {
      mIsRtl = bool;
      TextView localTextView = mLabelView;
      if ((localTextView != null) && (Build.VERSION.SDK_INT >= 17))
      {
        if (mIsRtl) {
          paramInt = 4;
        } else {
          paramInt = 3;
        }
        localTextView.setTextDirection(paramInt);
      }
      requestLayout();
    }
  }
  
  public Parcelable onSaveInstanceState()
  {
    i localI = new i(super.onSaveInstanceState());
    position = getSelectedItemPosition();
    e localE = mPopup;
    boolean bool;
    if ((localE != null) && (localE.isShowing())) {
      bool = true;
    } else {
      bool = false;
    }
    showDropdown = bool;
    return localI;
  }
  
  public boolean performItemClick(View paramView, int paramInt, long paramLong)
  {
    f localF = mOnItemClickListener;
    if (localF != null)
    {
      if (localF.onItemClick(this, paramView, paramInt, paramLong)) {
        setSelection(paramInt);
      }
      return true;
    }
    setSelection(paramInt);
    return false;
  }
  
  public void setAdapter(SpinnerAdapter paramSpinnerAdapter)
  {
    Object localObject = mAdapter;
    if (localObject != null) {
      ((Adapter)localObject).unregisterDataSetObserver(mDataSetObserver);
    }
    mRecycler.clear();
    mAdapter = paramSpinnerAdapter;
    mAdapter.registerDataSetObserver(mDataSetObserver);
    onDataChanged();
    localObject = mPopup;
    if (localObject != null)
    {
      ((e)localObject).setAdapter(new d(paramSpinnerAdapter));
      return;
    }
    mTempAdapter = new d(paramSpinnerAdapter);
  }
  
  public void setDropDownHorizontalOffset(int paramInt)
  {
    mPopup.setHorizontalOffset(paramInt);
  }
  
  public void setDropDownVerticalOffset(int paramInt)
  {
    mPopup.setVerticalOffset(paramInt);
  }
  
  public void setDropDownWidth(int paramInt)
  {
    mDropDownWidth = paramInt;
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    if (mDisableChildrenWhenDisabled)
    {
      int j = getChildCount();
      int i = 0;
      while (i < j)
      {
        getChildAt(i).setEnabled(paramBoolean);
        i += 1;
      }
    }
  }
  
  public void setGravity(int paramInt)
  {
    if (mGravity != paramInt)
    {
      int i = paramInt;
      if ((paramInt & 0x7) == 0) {
        i = paramInt | 0x800003;
      }
      mGravity = i;
      requestLayout();
    }
  }
  
  public void setMinimumHeight(int paramInt)
  {
    mMinHeight = paramInt;
    super.setMinimumHeight(paramInt);
  }
  
  public void setMinimumWidth(int paramInt)
  {
    mMinWidth = paramInt;
    super.setMinimumWidth(paramInt);
  }
  
  public void setOnItemClickListener(f paramF)
  {
    mOnItemClickListener = paramF;
  }
  
  public void setOnItemSelectedListener(g paramG)
  {
    mOnItemSelectedListener = paramG;
  }
  
  public void setPopupBackgroundDrawable(Drawable paramDrawable)
  {
    mPopup.setBackgroundDrawable(paramDrawable);
  }
  
  public void setPopupBackgroundResource(int paramInt)
  {
    setPopupBackgroundDrawable(getContext().getDrawable(paramInt));
  }
  
  public void setSelection(int paramInt)
  {
    Object localObject = mAdapter;
    int i = paramInt;
    if (localObject != null) {
      i = Math.max(0, Math.min(paramInt, ((Adapter)localObject).getCount() - 1));
    }
    if (mSelectedPosition != i)
    {
      mSelectedPosition = i;
      localObject = mOnItemSelectedListener;
      if (localObject != null)
      {
        View localView = getSelectedView();
        SpinnerAdapter localSpinnerAdapter = mAdapter;
        long l;
        if (localSpinnerAdapter == null) {
          l = -1L;
        } else {
          l = localSpinnerAdapter.getItemId(i);
        }
        ((g)localObject).onItemSelected(this, localView, i, l);
      }
      init();
    }
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    return (super.verifyDrawable(paramDrawable)) || (a == paramDrawable) || (mDividerDrawable == paramDrawable);
  }
  
  class d
    implements ListAdapter, SpinnerAdapter, View.OnClickListener
  {
    private AdapterView.OnItemClickListener mItemClickListener;
    private ListAdapter mListAdapter;
    
    public d()
    {
      if ((Spinner.this instanceof ListAdapter)) {
        mListAdapter = ((ListAdapter)Spinner.this);
      }
    }
    
    public boolean areAllItemsEnabled()
    {
      ListAdapter localListAdapter = mListAdapter;
      return (localListAdapter == null) || (localListAdapter.areAllItemsEnabled());
    }
    
    public int getCount()
    {
      SpinnerAdapter localSpinnerAdapter = Spinner.this;
      if (localSpinnerAdapter == null) {
        return 0;
      }
      return localSpinnerAdapter.getCount();
    }
    
    public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      SpinnerAdapter localSpinnerAdapter = Spinner.this;
      if (localSpinnerAdapter == null) {
        return null;
      }
      return localSpinnerAdapter.getDropDownView(paramInt, paramView, paramViewGroup);
    }
    
    public Object getItem(int paramInt)
    {
      SpinnerAdapter localSpinnerAdapter = Spinner.this;
      if (localSpinnerAdapter == null) {
        return null;
      }
      return localSpinnerAdapter.getItem(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      SpinnerAdapter localSpinnerAdapter = Spinner.this;
      if (localSpinnerAdapter == null) {
        return -1L;
      }
      return localSpinnerAdapter.getItemId(paramInt);
    }
    
    public int getItemViewType(int paramInt)
    {
      ListAdapter localListAdapter = mListAdapter;
      if (localListAdapter != null) {
        return localListAdapter.getItemViewType(paramInt);
      }
      return 0;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramView = getDropDownView(paramInt, paramView, paramViewGroup);
      paramView.setOnClickListener(this);
      paramView.setTag(Integer.valueOf(paramInt));
      return paramView;
    }
    
    public int getViewTypeCount()
    {
      ListAdapter localListAdapter = mListAdapter;
      if (localListAdapter != null) {
        return localListAdapter.getViewTypeCount();
      }
      return 1;
    }
    
    public boolean hasStableIds()
    {
      SpinnerAdapter localSpinnerAdapter = Spinner.this;
      return (localSpinnerAdapter != null) && (localSpinnerAdapter.hasStableIds());
    }
    
    public boolean isEmpty()
    {
      return getCount() == 0;
    }
    
    public boolean isEnabled(int paramInt)
    {
      ListAdapter localListAdapter = mListAdapter;
      return (localListAdapter == null) || (localListAdapter.isEnabled(paramInt));
    }
    
    public void onClick(View paramView)
    {
      int i = ((Integer)paramView.getTag()).intValue();
      AdapterView.OnItemClickListener localOnItemClickListener = mItemClickListener;
      if (localOnItemClickListener != null) {
        localOnItemClickListener.onItemClick(null, paramView, i, 0L);
      }
    }
    
    public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
    {
      SpinnerAdapter localSpinnerAdapter = Spinner.this;
      if (localSpinnerAdapter != null) {
        localSpinnerAdapter.registerDataSetObserver(paramDataSetObserver);
      }
    }
    
    public void setOnItemClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
    {
      mItemClickListener = paramOnItemClickListener;
    }
    
    public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
    {
      SpinnerAdapter localSpinnerAdapter = Spinner.this;
      if (localSpinnerAdapter != null) {
        localSpinnerAdapter.unregisterDataSetObserver(paramDataSetObserver);
      }
    }
  }
  
  class e
    extends ListPopupWindow
  {
    private ViewTreeObserver.OnGlobalLayoutListener layoutListener = new a();
    private Spinner.d mAdapter;
    
    public e(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
    {
      super(paramAttributeSet, paramInt1, paramInt2);
      setAnchorView(Spinner.this);
      setModal(true);
      setPromptPosition(0);
      setOnDismissListener(new Spinner.e.b(this, Spinner.this));
    }
    
    void computeContentWidth()
    {
      Object localObject = getBackground();
      int i = 0;
      if (localObject != null)
      {
        ((Drawable)localObject).getPadding(Spinner.access$getMTempRect(Spinner.this));
        if (Spinner.isLayoutRtl(Spinner.this)) {
          i = access$getMTempRectright;
        } else {
          i = -access$getMTempRectleft;
        }
      }
      else
      {
        localObject = Spinner.access$getMTempRect(Spinner.this);
        access$getMTempRectright = 0;
        left = 0;
      }
      int n = getPaddingLeft();
      int i1 = getPaddingRight();
      int i2 = getWidth();
      if (Spinner.access$getMDropDownWidth(Spinner.this) == -2)
      {
        int k = Spinner.measureContentWidth(Spinner.this, mAdapter, getBackground());
        int j = k;
        int m = getContext().getResources().getDisplayMetrics().widthPixels - access$getMTempRectleft - access$getMTempRectright;
        if (k > m) {
          j = m;
        }
        setContentWidth(Math.max(j, i2 - n - i1));
      }
      else if (Spinner.access$getMDropDownWidth(Spinner.this) == -1)
      {
        setContentWidth(i2 - n - i1);
      }
      else
      {
        setContentWidth(Spinner.access$getMDropDownWidth(Spinner.this));
      }
      if (Spinner.isLayoutRtl(Spinner.this)) {
        i += i2 - i1 - getWidth();
      } else {
        i += n;
      }
      setHorizontalOffset(i);
    }
    
    public void setAdapter(ListAdapter paramListAdapter)
    {
      super.setAdapter(paramListAdapter);
      mAdapter = ((Spinner.d)paramListAdapter);
      mAdapter.setOnItemClickListener(new Spinner.e.c(this));
    }
    
    public void setPromptText(CharSequence paramCharSequence) {}
    
    public void show()
    {
      boolean bool = isShowing();
      computeContentWidth();
      setInputMethodMode(2);
      super.show();
      if (bool) {
        return;
      }
      ViewTreeObserver localViewTreeObserver = getViewTreeObserver();
      if (localViewTreeObserver != null) {
        localViewTreeObserver.addOnGlobalLayoutListener(layoutListener);
      }
    }
    
    class a
      implements ViewTreeObserver.OnGlobalLayoutListener
    {
      a() {}
      
      public void onGlobalLayout()
      {
        computeContentWidth();
        Spinner.e.access$getShow(Spinner.e.this);
      }
    }
  }
  
  public abstract interface f
  {
    public abstract boolean onItemClick(Spinner paramSpinner, View paramView, int paramInt, long paramLong);
  }
  
  public abstract interface g
  {
    public abstract void onItemSelected(Spinner paramSpinner, View paramView, int paramInt, long paramLong);
  }
  
  class h
  {
    private final SparseArray<View> mScrapHeap = new SparseArray();
    
    private h() {}
    
    void clear()
    {
      mScrapHeap.clear();
    }
    
    View get(int paramInt)
    {
      View localView = (View)mScrapHeap.get(paramInt);
      if (localView != null) {
        mScrapHeap.delete(paramInt);
      }
      return localView;
    }
    
    public void put(int paramInt, View paramView)
    {
      mScrapHeap.put(paramInt, paramView);
    }
  }
  
  class i
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<com.rey.material.widget.Spinner.i> CREATOR = new a();
    int position;
    boolean showDropdown;
    
    i()
    {
      super();
      position = this$1.readInt();
      boolean bool;
      if (this$1.readByte() != 0) {
        bool = true;
      } else {
        bool = false;
      }
      showDropdown = bool;
    }
    
    i()
    {
      super();
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("AbsSpinner.SavedState{");
      localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      localStringBuilder.append(" position=");
      localStringBuilder.append(position);
      localStringBuilder.append(" showDropdown=");
      localStringBuilder.append(showDropdown);
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(position);
      paramParcel.writeByte((byte)showDropdown);
    }
    
    final class a
      implements Parcelable.Creator<com.rey.material.widget.Spinner.i>
    {
      a() {}
      
      public Spinner.i createFromParcel(Parcel paramParcel)
      {
        return new Spinner.i(paramParcel);
      }
      
      public Spinner.i[] newArray(int paramInt)
      {
        return new Spinner.i[paramInt];
      }
    }
  }
  
  class j
    extends DataSetObserver
  {
    private j() {}
    
    public void onChanged()
    {
      Spinner.access$getOnDataChanged(Spinner.this);
    }
    
    public void onInvalidated()
    {
      Spinner.onItemClick(Spinner.this);
    }
  }
}
